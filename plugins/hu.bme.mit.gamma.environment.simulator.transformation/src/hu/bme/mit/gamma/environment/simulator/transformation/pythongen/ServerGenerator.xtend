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
import static extension hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility.*
import hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod

import hu.bme.mit.gamma.codegeneration.java.util.TimingDeterminer

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
		var analysismethod = analysisComponent.analysismethod as SimulationAnalysisMethod
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

def state2num(state):
	if state=="run":
		return 0.0
	else:
		return 1.0

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

elapse_time=0

diagram_cmd_key="DiagramCMD-"
event_source_cmd_key="SourceCMD-"
periodic_source_cmd_key="PeriodicCMD-"
elapse_cmd_key="ElapseCMD-"
delay_cmd_key="DelayCMD-"

sim_stoch_events=dict()

raised_events=list()

diagram_name_dict = {
	«FOR diagramName : diagramNames»
		«diagramName»,
	«ENDFOR»
}


for diagram in diagram_name_dict.keys():
	cmd=diagram_cmd_key+"-"+diagram.replace("::", "__")
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

overall_diagram_svg=""
with open("overall_structure.svg",encoding='UTF-8', errors='space_it') as f:
	overall_diagram_svg =  f.read()
	overall_diagram_svg=overall_diagram_svg.replace("Â","").replace("Ă","").replace("Â","")


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
				name=event_source_cmd_key+(component+"."+port+"."+pevent).replace("()",".").replace("..",".")
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
				name=periodic_source_cmd_key+(component+"."+port+"."+pevent).replace("()",".").replace("..",".")
				call=comp.calls[port][pevent]
				pfailure_names.append(name)
				pfailure_dict.update({name:call})

detmodel.reset()
detmodel.getDetModel().schedule()
«IF TimingDeterminer.INSTANCE.needTimer(analysisComponent.analyzedComponent.type)»
elapse_time=int(detmodel.timer.getEarliestTime())
«ENDIF»
stochmodel.generateEvents()
stochmodel.events.clear()

cmd=""

class MyServer(BaseHTTPRequestHandler):
	
	def send_page(self):
		global url, svg, cmd, sim_stoch_events, raised_events,elapse_time
		command=""
		print(self.requestline)
		if "Reset" in self.requestline:
			detmodel.reset()
			detmodel.getDetModel().schedule()
			sim_stoch_events=dict()
			raised_events=list()
			print("Reset!")
		elif periodic_source_cmd_key in self.requestline:
			command=self.requestline.replace("GET /?pfname=", "").replace(" HTTP/1.1", "")
			print("Raise event ",command,"!")
			stochmodel.time=stochmodel.time+1.0
			raised_events.append(command)
			failure_calls=pfailure_dict[command]
			for failure_call in failure_calls:
				failure_call()
			detmodel.getDetModel().schedule()
			while len(stochmodel.events)>0:
				stochmodel.getEarliestTime()
				event = stochmodel.popEvent()
				sim_stoch_events[event.eventSource.name.replace("()","_").replace(".","")]=event
			# evaluate end condition
			print("Event raised successfully!")
		elif event_source_cmd_key in self.requestline:
			command=self.requestline.replace("GET /?fname=", "").replace(" HTTP/1.1", "")
			raised_events.append(command)
			print("Raise event ",command,"!")
			stochmodel.time=stochmodel.time+1.0
			failure_calls=failure_dict[command]
			for failure_call in failure_calls:
				failure_call()
			detmodel.getDetModel().schedule()
			while len(stochmodel.events)>0:
				stochmodel.getEarliestTime()
				event = stochmodel.popEvent()
				sim_stoch_events[event.eventSource.name.replace("()","_").replace(".","")]=event
			# evaluate end condition
			print("Event raised successfully!")
		elif delay_cmd_key in self.requestline:
			eventkey=self.requestline.replace("GET /?stname=", "").replace(" HTTP/1.1", "").replace(delay_cmd_key+"-","")
			raised_events.append(eventkey)
			event=sim_stoch_events[eventkey]
			stochmodel.time = event.eventTime
			print(event.eventSource.name + ' at time: ' + str(stochmodel.time))
			event.eventCall()
			detmodel.getDetModel().schedule()
			sim_stoch_events.pop(eventkey,None)
			while len(stochmodel.events)>0:
				stochmodel.getEarliestTime()
				event = stochmodel.popEvent()
				sim_stoch_events[event.eventSource.name.replace("()","_").replace(".","")]=event
		«IF TimingDeterminer.INSTANCE.needTimer(analysisComponent.analyzedComponent.type)»
		elif elapse_cmd_key in self.requestline:
			print("Elapse "+str(elapse_time) + ' ms at time: ' + str(stochmodel.time))
			detmodel.timer.elapse(int(elapse_time))
			detmodel.getDetModel().schedule()
		«ENDIF»
		elif diagram_cmd_key in self.requestline:
			command=self.requestline.replace("GET /?dname=", "").replace(" HTTP/1.1", "")
			#url=diagram_url_dict[command]
			svg=diagram_svg_dict[command]
			cmd=command.replace(diagram_cmd_key+"-","")
			print("Diagram change: ", command)
		# Using readlines()

		count = 0
		
		self.send_response(200)
		self.send_header("Content-type", "text/html")
		self.end_headers()
		self.wfile.write(bytes('<html><head><title>Gamma Simulator</title><meta charset="utf-8"></head>', "utf-8"))
		#self.wfile.write(bytes("""<meta http-equiv="refresh" content="0.5" />""", "utf-8"))
		self.wfile.write(bytes("<body>", "utf-8"))
		
		self.wfile.write(bytes("""<table><tbody><tr><td>""", "utf-8"))
		
		self.wfile.write(bytes("""<h3>Simulator commands: </h3>""", "utf-8"))
		
		# Strips the newline character
		self.wfile.write(bytes("""<form action="http://localhost:8080/">""", "utf-8"))
		
		self.wfile.write(bytes("""<label for="dname">Choose diagram:</label><br>	""", "utf-8"))
		self.wfile.write(bytes("""<select name="dname" id="dname">""", "utf-8"))
		for diagram in diagram_name_dict.keys():
			self.wfile.write(bytes("""<option value='"""+diagram_cmd_key+"-"+diagram.replace("::","__")+"""'> """+diagram+""" </option>""", "utf-8"))
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
			self.wfile.write(bytes("""<option value='"""+delay_cmd_key+"-"+stoch_event+"""'>"""+stoch_event+"""</option>""", "utf-8"))
		self.wfile.write(bytes("""</select>""", "utf-8"))
		self.wfile.write(bytes("""<input type="submit" value="Insert event!">""", "utf-8"))
		self.wfile.write(bytes("""</form>""", "utf-8"))
		«IF TimingDeterminer.INSTANCE.needTimer(analysisComponent.analyzedComponent.type)»
		elapse_time=int(detmodel.timer.getEarliestTime())
		if elapse_time<8000000000000000000:
			self.wfile.write(bytes("""<form action="http://localhost:8080/"""+elapse_cmd_key+"""">""", "utf-8"))
			self.wfile.write(bytes("""<input type="submit" value="Elapse time: """+(time.strftime("%Hh%Mm%Ss", time.gmtime(elapse_time/1000.0)) if elapse_time>1000  else str(elapse_time)+"ms  ")+""" ">""", "utf-8"))
			self.wfile.write(bytes("""</form>""", "utf-8"))
		«ENDIF»
		self.wfile.write(bytes("""<form action="http://localhost:8080/ResetDetModel">""", "utf-8"))
		self.wfile.write(bytes("""<input type="submit" value="Reset">""", "utf-8"))
		self.wfile.write(bytes("""</form>""", "utf-8"))
		
		
		self.wfile.write(bytes("""</td><td>""", "utf-8"))
		
		
		self.wfile.write(bytes("""<h3>Raised events: </h3>""", "utf-8"))
		
		self.wfile.write(bytes("""<ol>""", "utf-8"))
		
		for raised_event in raised_events:
			self.wfile.write(bytes("""<li>""", "utf-8"))
			self.wfile.write(bytes(raised_event, "utf-8"))
			self.wfile.write(bytes("""</li>""", "utf-8"))
		
		self.wfile.write(bytes("""</ol>""", "utf-8"))

		self.wfile.write(bytes("""<h3>End conditions: </h3>""", "utf-8"))
		
		self.wfile.write(bytes("""<ul>""", "utf-8"))
		
		«FOR endCondition : analysismethod.endcondition»
			self.wfile.write(bytes("""<li>""", "utf-8"))
			self.wfile.write(bytes("""«generateEndConditionName(endCondition)» : """+ str(detmodel.monitorOf«generateEndConditionName(endCondition)».state), "utf-8"))
			self.wfile.write(bytes("""</li>""", "utf-8"))
		«ENDFOR»
		
		self.wfile.write(bytes("""</ul>""", "utf-8"))
		
		self.wfile.write(bytes("""<h3>Analysis aspects: </h3>""", "utf-8"))
		
		self.wfile.write(bytes("""<ul>""", "utf-8"))
		
		«FOR aspect : analysisComponent.aspect»
			self.wfile.write(bytes("""<li>""", "utf-8"))
			self.wfile.write(bytes("""«aspect.pyroName» : """+ str(«aspect.valueCall»), "utf-8"))
			self.wfile.write(bytes("""</li>""", "utf-8"))
		«ENDFOR»
		
		self.wfile.write(bytes("""</ul>""", "utf-8"))
		
		
		
		self.wfile.write(bytes("""</td></tr></tbody></table>""", "utf-8"))
		
		state_lines=list()
		diagram_svg=svg
		diagram_svg=diagram_svg.replace('lengthAdjust="spacing"','')
		if cmd not in config.keys() and cmd!="":
			print("Eval : ","detmodel.get"+cmd.replace("«analysisComponent.name»__","").replace("__","().get")+"().toString()")
			value=str(eval("detmodel.get"+cmd.replace("«analysisComponent.name»__","").replace("__","().get")+"().toString()"))

			value2=value
			value2=value2.replace("__In","").replace("__Out","").replace("__","::")
			value2=value2.replace("\n",";<br> ")
			value2=value2.replace("\r","")
			state_lines.append("<h3> Variables of "+cmd.replace("__","::")+"</h3> <p>"+value2+"\n</p>")

			value=value.replace("\n","|")
			value=value.replace("\r","")
			value=value.replace(" ","")
			values=value.split("|")
			for pair in values:
				if '=' in pair:
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
						value=value.replace("__In","").replace("__Out","").replace("__","::")
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
		
		self.wfile.write(bytes(overall_diagram_svg, "utf-8"))
		
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