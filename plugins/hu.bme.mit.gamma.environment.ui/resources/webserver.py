from simulator import *
# Python 3 server example
from http.server import BaseHTTPRequestHandler, HTTPServer
import time
import re
from pathlib import Path
import yaml

with open("viz_config.yml", "r") as ymlfile:
    config = yaml.load(ymlfile)

hostName = "localhost"
serverPort = 8080

diagrams = ["system_context_diagram.svg","trq_calc.svg","tsu_diag.svg"]
diagram_cmd_dict = dict()
diagram_name_dict = dict()
diagram_url_dict = dict()
diagram_svg_dict = dict()


for diagram in diagrams:
    cmd="Diagram-"+diagram.replace(".svg", "").replace("_","-")
    name=cmd.replace("_"," ")
    diagram_cmd_dict.update({diagram : cmd})
    diagram_name_dict.update({diagram : name})
    diagram_url_dict.update({cmd : diagram})
    svg = Path(diagram).read_text()
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

detmodel.reset()
detmodel.getEPAS().schedule()

stochmodel.generateEvents()
stochmodel.events.clear()



class MyServer(BaseHTTPRequestHandler):
    
    def send_page(self):
        global url, svg
        print(self.requestline)
        if "Reset" in self.requestline:
            detmodel.reset()
            detmodel.getEPAS().schedule()
            print("Reset!")
        elif "Failure" in self.requestline:
            command=self.requestline.replace("GET /?fname=", "").replace(" HTTP/1.1", "")
            print("Raise failure ",command,"!")
            stochmodel.time=stochmodel.time+1.0
            failure_call=failure_dict[command]
            failure_call()
            detmodel.getEPAS().schedule()
            while len(stochmodel.events)>0:
                event = stochmodel.popEvent()
                stochmodel.time = event.eventTime
                print(event.eventSource.name + ' at time: ' + str(stochmodel.time))
                event.eventCall()
                detmodel.getEPAS().schedule()
                detmodel.getEPAS().schedule()
            # evaluate end condition
            print("Failure raised successfully!")
        elif "Diagram" in self.requestline:
            command=self.requestline.replace("GET /?dname=", "").replace(" HTTP/1.1", "")
            url=diagram_url_dict[command]
            svg=diagram_svg_dict[command]
            print("Diagram change: ", command)
        # Using readlines()

        count = 0
        
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()
        self.wfile.write(bytes("<html><head><title>Gamma Simulator</title></head>", "utf-8"))
        #self.wfile.write(bytes("""<meta http-equiv="refresh" content="0.5" />""", "utf-8"))
        self.wfile.write(bytes("<body>", "utf-8"))
        # Strips the newline character
        self.wfile.write(bytes("""<form action="http://localhost:8080/">""", "utf-8"))
        self.wfile.write(bytes("""<label for="dname">Choose diagram:</label><br>    """, "utf-8"))
        self.wfile.write(bytes("""<select name="dname" id="dname">""", "utf-8"))
        for diagram in diagrams:
            self.wfile.write(bytes("""<option value='"""+diagram_cmd_dict[diagram]+"""'> """+diagram_name_dict[diagram]+""" </option>""", "utf-8"))
        self.wfile.write(bytes("""</select>""", "utf-8"))
        self.wfile.write(bytes("""<input type="submit" value="Choose">""", "utf-8"))
        self.wfile.write(bytes("""</form>""", "utf-8"))
        
        self.wfile.write(bytes("""<form action="http://localhost:8080/">""", "utf-8"))
        self.wfile.write(bytes("""<label for="fname">Failure mode:</label><br>    """, "utf-8"))
        self.wfile.write(bytes("""<select name="fname" id="fname">""", "utf-8"))
        self.wfile.write(bytes("""<option value='no failure'> no failure </option>""", "utf-8"))
        for failure in failure_names:    
            self.wfile.write(bytes("""<option value='"""+failure+"""'>"""+failure+"""</option>""", "utf-8"))
        self.wfile.write(bytes("""</select>""", "utf-8"))
        self.wfile.write(bytes("""<input type="submit" value="Insert failure!">""", "utf-8"))
        self.wfile.write(bytes("""</form>""", "utf-8"))
        
        
        self.wfile.write(bytes("""<form action="http://localhost:8080/ResetDetModel">""", "utf-8"))
        self.wfile.write(bytes("""<input type="submit" value="Reset">""", "utf-8"))
        self.wfile.write(bytes("""</form>""", "utf-8"))
        
        
        
        diagram_svg=svg
        for option in config["epas"].keys():
            diagram_svg=diagram_svg.replace("$"+option+"$",str(eval("detmodel.get"+config["epas"][option].replace("::","().get")+"()")))
        self.wfile.write(bytes(diagram_svg, "utf-8"))
        #for url in diagrams:#
        #file1 = open(url, 'r', encoding="utf-8")
        #print("open: ",url)
        #Lines = file1.readlines()
        #for line in Lines:
        #    l=str(line.strip()).replace("Â","")
        #    l=re.sub("\s"," ",l)
        #    for option in config["epas"].keys():
        #        if option in l:
        #            l=l.replace("$"+option+"$",str(eval("detmodel.get"+config["epas"][option].replace("::","().get")+"()")))
        #    self.wfile.write(bytes(l, "utf-8"))
        #    print(l)
        #file1.close()
            #print(l)

        
        self.wfile.write(bytes("</body></html>", "utf-8"))
    
    def do_GET(self):
        self.send_page()
        
    def do_POST(self):
        '''Reads post request body'''
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