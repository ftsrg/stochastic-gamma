package hu.bme.mit.gamma.environment.simulator.transformation.pythongen

import hu.bme.mit.gamma.environment.analysis.AnalysisComponent
import hu.bme.mit.gamma.statechart.composite.CompositeComponent
import java.util.HashSet
import hu.bme.mit.gamma.statechart.interface_.Component

import static extension hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures.*
import java.util.List
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition
import java.util.LinkedList

class ServerGenerator {
	
	def generateDiagramNames(ComponentInstance instance,String namespace,List<String> diagramNames){
		var component = instance.derivedType
		var namespace2='''«namespace»::«instance.name.toFirstUpper»'''
		diagramNames+='''"«namespace2»" : "«component.name»"'''
		if (component instanceof AsynchronousStatechartDefinition){
		} else if (component instanceof StatechartDefinition){
		} else if (component instanceof AsynchronousAdapter){
			generateDiagramNames(component.wrappedComponent,namespace2, diagramNames)
		}else  if (component instanceof CompositeComponent){
			for (inst : component.derivedComponents){
				generateDiagramNames(inst,namespace2, diagramNames)
			}
		}
	}
	
	
	def generate(AnalysisComponent analysisComponent){
		val diagramNames = new LinkedList<String>
		generateDiagramNames(analysisComponent.analyzedComponent,analysisComponent.name,diagramNames)
		return 
'''

from simulator import *
# Python 3 server for visualizing the simulator
from http.server import BaseHTTPRequestHandler, HTTPServer
import time
import re
from pathlib import Path
import yaml
from yaml import Loader, Dumper

with open("config.yml", "r") as ymlfile:
	config = yaml.load(ymlfile, Loader=Loader)

with open("config_sct.yml", "r") as ymlfile:
	config_sct = yaml.load(ymlfile, Loader=Loader)

hostName = "localhost"
serverPort = 8080

diagrams = ["system_context_diagram.svg","trq_calc.svg","tsu_diag.svg"]
diagram_cmd_dict = dict()
diagram_name_dict = dict()
diagram_url_dict = dict()
diagram_svg_dict = dict()

sim_stoch_events=dict()

diagram_name_dict = {
	«FOR diagramName : diagramNames»
		«diagramName»,
	«ENDFOR»
}


for diagram in diagram_name_dict.keys():
	cmd="Diagram-"+diagram.replace("::", "__")
	name=diagram_name_dict[diagram]
	diagram_cmd_dict.update({diagram : cmd})
	diagram_name_dict.update({diagram : name})
	diagram_url_dict.update({cmd : diagram})
	svg = ""
	#svg = Path(name+".svg",encoding='utf-8', errors='space_it').read_text()
	with open(name+".svg",encoding='UTF-8', errors='space_it') as f:
		svg =  f.read()
	svg=svg.replace("Â","").replace("Ă","").replace("Â","")
	diagram_svg_dict.update({cmd : svg})


svg=""

url=diagrams[0]

failure_names=[]
failure_dict=dict()
for component in stochmodel.components.keys():
	comp=stochmodel.components[component]
	if (isinstance(comp, EventSource)):
		ports=list(comp.calls.keys())
		#iterating through ports
		for port in ports:
			pevents=comp.portevents[port]
			#iterating through events
			for pevent in pevents:
				name="Failure"+(component+"."+port+"."+pevent).replace("()",".").replace("..",".")
				call=comp.calls[port][pevent]
				failure_names.append(name)
				failure_dict.update({name:call})

pfailure_names=[]
pfailure_dict=dict()
for component in stochmodel.components.keys():
	comp=stochmodel.components[component]
	if (isinstance(comp, PeriodicEventSource)):
		ports=list(comp.calls.keys())
		#iterating through ports
		for port in ports:
			pevents=comp.portevents[port]
			#iterating through events
			for pevent in pevents:
				name="PFailure"+(component+"."+port+"."+pevent).replace("()",".").replace("..",".")
				call=comp.calls[port][pevent]
				pfailure_names.append(name)
				pfailure_dict.update({name:call})

detmodel.reset()
detmodel.getDetModel().schedule()

stochmodel.generateEvents()
stochmodel.events.clear()

cmd=""

class MyServer(BaseHTTPRequestHandler):
	
	def send_page(self):
		global url, svg, cmd
		command=""
		print(self.requestline)
		if "Reset" in self.requestline:
			detmodel.reset()
			detmodel.getDetModel().schedule()
			print("Reset!")
		elif "PFailure" in self.requestline:
			command=self.requestline.replace("GET /?pfname=", "").replace(" HTTP/1.1", "")
			print("Raise event ",command,"!")
			stochmodel.time=stochmodel.time+1.0
			failure_calls=pfailure_dict[command]
			for failure_call in failure_calls:
				failure_call()
			detmodel.getDetModel().schedule()
			while len(stochmodel.events)>0:
				event = stochmodel.popEvent()
				sim_stoch_events[event.eventSource.name.replace("()","_").replace(".","")]=event
			# evaluate end condition
			print("Event raised successfully!")
		elif "Failure" in self.requestline:
			command=self.requestline.replace("GET /?fname=", "").replace(" HTTP/1.1", "")
			print("Raise event ",command,"!")
			stochmodel.time=stochmodel.time+1.0
			failure_calls=failure_dict[command]
			for failure_call in failure_calls:
				failure_call()
			detmodel.getDetModel().schedule()
			while len(stochmodel.events)>0:
				event = stochmodel.popEvent()
				sim_stoch_events[event.eventSource.name.replace("()","_").replace(".","")]=event
			# evaluate end condition
			print("Event raised successfully!")
		elif "StochEvent" in self.requestline:
			eventkey=self.requestline.replace("GET /?stname=", "").replace(" HTTP/1.1", "").replace("StochEvent-","")
			event=sim_stoch_events[eventkey]
			stochmodel.time = event.eventTime
			print(event.eventSource.name + ' at time: ' + str(stochmodel.time))
			event.eventCall()
			detmodel.getDetModel().schedule()
			sim_stoch_events.pop(eventkey,None)
		elif "Diagram" in self.requestline:
			command=self.requestline.replace("GET /?dname=", "").replace(" HTTP/1.1", "")
			#url=diagram_url_dict[command]
			svg=diagram_svg_dict[command]
			cmd=command.replace("Diagram-","")
			print("Diagram change: ", command)
		# Using readlines()

		count = 0
		
		self.send_response(200)
		self.send_header("Content-type", "text/html")
		self.end_headers()
		self.wfile.write(bytes('<html><head><title>Gamma Simulator</title><meta charset="utf-8"></head>', "utf-8"))
		#self.wfile.write(bytes("""<meta http-equiv="refresh" content="0.5" />""", "utf-8"))
		self.wfile.write(bytes("<body>", "utf-8"))
		# Strips the newline character
		self.wfile.write(bytes("""<form action="http://localhost:8080/">""", "utf-8"))
		
		self.wfile.write(bytes("""<label for="dname">Choose diagram:</label><br>	""", "utf-8"))
		self.wfile.write(bytes("""<select name="dname" id="dname">""", "utf-8"))
		for diagram in diagram_name_dict.keys():
			self.wfile.write(bytes("""<option value='"""+"Diagram-"+diagram.replace("::","__")+"""'> """+diagram+""" </option>""", "utf-8"))
		self.wfile.write(bytes("""</select>""", "utf-8"))
		self.wfile.write(bytes("""<input type="submit" value="Choose">""", "utf-8"))
		self.wfile.write(bytes("""</form>""", "utf-8"))


		self.wfile.write(bytes("""<form action="http://localhost:8080/">""", "utf-8"))
		self.wfile.write(bytes("""<label for="fname">Events of Stochastic Source Components:</label><br>	""", "utf-8"))
		self.wfile.write(bytes("""<select name="fname" id="fname">""", "utf-8"))
		self.wfile.write(bytes("""<option value='no failure'> no event </option>""", "utf-8"))
		for failure in failure_names:	
			self.wfile.write(bytes("""<option value='"""+failure+"""'>"""+failure+"""</option>""", "utf-8"))
		self.wfile.write(bytes("""</select>""", "utf-8"))
		self.wfile.write(bytes("""<input type="submit" value="Insert event!">""", "utf-8"))
		self.wfile.write(bytes("""</form>""", "utf-8"))


		self.wfile.write(bytes("""<form action="http://localhost:8080/">""", "utf-8"))
		self.wfile.write(bytes("""<label for="pfname">Events of Stochastic Periodic Source Components:</label><br>	""", "utf-8"))
		self.wfile.write(bytes("""<select name="pfname" id="pfname">""", "utf-8"))
		self.wfile.write(bytes("""<option value='no failure'> no event </option>""", "utf-8"))
		for failure in pfailure_names:	
			self.wfile.write(bytes("""<option value='"""+failure+"""'>"""+failure+"""</option>""", "utf-8"))
		self.wfile.write(bytes("""</select>""", "utf-8"))
		self.wfile.write(bytes("""<input type="submit" value="Insert event!">""", "utf-8"))
		self.wfile.write(bytes("""</form>""", "utf-8"))


		self.wfile.write(bytes("""<form action="http://localhost:8080/">""", "utf-8"))
		self.wfile.write(bytes("""<label for="stname">Events of Stochastic Delay Components:</label><br>	""", "utf-8"))
		self.wfile.write(bytes("""<select name="stname" id="stname">""", "utf-8"))
		self.wfile.write(bytes("""<option value='no failure'> no event </option>""", "utf-8"))
		for stoch_event in sim_stoch_events.keys():	
			self.wfile.write(bytes("""<option value='"""+"StochEvent-"+stoch_event+"""'>"""+stoch_event+"""</option>""", "utf-8"))
		self.wfile.write(bytes("""</select>""", "utf-8"))
		self.wfile.write(bytes("""<input type="submit" value="Insert event!">""", "utf-8"))
		self.wfile.write(bytes("""</form>""", "utf-8"))


		self.wfile.write(bytes("""<form action="http://localhost:8080/ResetDetModel">""", "utf-8"))
		self.wfile.write(bytes("""<input type="submit" value="Reset">""", "utf-8"))
		self.wfile.write(bytes("""</form>""", "utf-8"))
		state_lines=list()
		diagram_svg=svg
		diagram_svg=diagram_svg.replace('lengthAdjust="spacing"','')
		if cmd not in config.keys() and cmd!="":
			print("Eval : ","detmodel.get"+cmd.replace("«analysisComponent.name»__","").replace("__","().get")+"().toString()")
			value=str(eval("detmodel.get"+cmd.replace("«analysisComponent.name»__","").replace("__","().get")+"().toString()"))

			value2=value
			value2=value2.replace("_","::")
			value2=value2.replace("\n",";<br> ")
			value2=value2.replace("\r","")
			state_lines.append("<h3> Variables of "+cmd.replace("__","::")+"</h3> <p>"+value2+"\n</p>")

			value=value.replace("\n","|")
			value=value.replace("\r","")
			value=value.replace(" ","")
			values=value.split("|")
			for pair in values:
				vars=pair.split('=')
				var_name=vars[0]
				var_value=vars[1]
				if var_name in config_sct[diagram_name_dict[cmd.replace("__","::")]]:
					diagram_svg=diagram_svg.replace(">"+var_value,'font-weight="bold" text-decoration = "underline">'+var_value)
		elif cmd!='':
			for option in config[cmd].keys():
				if option != "dummy":
					value=""
					param=""
					if "__STATECHART__" in config[cmd][option]:
						print("Eval : ", "detmodel.get"+cmd.replace("«analysisComponent.name»__","").replace("__","().get")+"().get"+config[cmd][option].replace("__STATECHART__","")+"().toString()")
						value=str(eval("detmodel.get"+cmd.replace("«analysisComponent.name»__","").replace("__","().get")+"().get"+config[cmd][option].replace("__STATECHART__","")+"().toString()"))
						value=value.replace("_","::")
						value=value.replace("\n",";<br> ")
						value=value.replace("\r","")
						state_lines.append("<h3>"+option+":</h3> <p>"+value+"\n</p>")
					else:
						param=option.split("_")[-1].replace('$','')
						print("Eval : ","detmodel.get"+cmd.replace("«analysisComponent.name»__","").replace("__","().get")+"().get"+config[cmd][option].replace("::","().get")+"()")
						value=str(eval("detmodel.get"+cmd.replace("«analysisComponent.name»__","").replace("__","().get")+"().get"+config[cmd][option].replace("::","().get")+"()"))
						s=""
						if len(value)+len(param)<len(option):
							for i in range(len(option)-len(value)-len(param)):
								s=s+"."
						diagram_svg=diagram_svg.replace(option,param+s+value)
		self.wfile.write(bytes(diagram_svg, "utf-8"))
		
		for line in state_lines:
			self.wfile.write(bytes(line, "utf-8"))

		
		self.wfile.write(bytes("</body></html>", "utf-8"))
	
	def do_GET(self):
		self.send_page()
		
	def do_POST(self):
		#Reads post request body
		print("Post: ")
		self._set_headers()
		content_len = int(self.headers.getheader('content-length', 0))
		post_body = self.rfile.read(content_len)
		self.send_page()
		print(("received post request:<br>{}".format(post_body)))

if __name__ == "__main__":		
	webServer = HTTPServer((hostName, serverPort), MyServer)
	print("Server started http://%s:%s" % (hostName, serverPort))

	try:
		webServer.serve_forever()
	except KeyboardInterrupt:
		pass

	webServer.server_close()
	print("Server stopped.")


'''
	}
}