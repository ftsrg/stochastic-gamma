package hu.bme.mit.gamma.environment.analysis.transformation.pythongen

import hu.bme.mit.gamma.environment.model.SimulationRule
import java.util.List
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.environment.analysis.transformation.util.EnvironmentConnections

class PythonCoSimulatorClassGenerator {
		
		
	static def generateFuncParams(Event event) '''«FOR parameter : event.parameterDeclarations SEPARATOR ", "»«parameter.name.toFirstLower»«ENDFOR»'''
	
	
	static def generateExternSimulationTemplateClasses(String packageName, List<EnvironmentConnections> connections){
		'''

		#these are template classes for external simulation
		#behavior is not specified only the interfaces
		#TODO: add simulation behavior
				#TODO: move the class into another file, this file might regenerate regularly on build
		#TODO: remove 'ExternSimulationTemplateClass' from class name
		«FOR connection : connections»
			«var classname=(connection.component.behaviorRules.get(0) as SimulationRule)
							.simulation.simulationClassName»
			«var interfaces=connection.component.inports
				.map[p|p.interfaceRealization.interface]
					.toSet»
			class «classname»ExternSimulationTemplateClass():
				#interface 
				«FOR i : interfaces»
					class InPort«i.name.toFirstUpper»():
						def __init__(self,portname,portcall):
							portcall.registerListener(self)
							self.portcall=portcall
							self.name=portname
							self.events=dict()
					
					def getEvents(self,globalevents):
						globalevents.update(self.events)
						self.events.clear()
						
					#definition of the interface functions
					«FOR event : i.events»
						def raise«event.event.name.toFirstUpper»(self,«generateFuncParams(event.event)»):
							self.events[portname+"->«event.event.name.toFirstUpper»",(«generateFuncParams(event.event)»)]
					«ENDFOR»
					
					class Java:
						implements = ["«packageName».interfaces.«i.name.toFirstUpper»Interface$Listener$Provided"]
				«ENDFOR»
					
				#functions calling output event
				#Do not modify these functions!
				«FOR port : connection.component.outports»
					«FOR event : port.interfaceRealization.interface.events.map[e|e.event]»
						def call«port.name.toFirstUpper»«event.name.toFirstUpper»(«generateFuncParams(event)»):
							«FOR outCall : connection.outCalls.get(port)»
								callEvent=lambda:self.detmodel«connection.componentCall»«outCall».raise«event.name.toFirstUpper»(«generateFuncParams(event)»)
								self.events.append(Event(self,actualTime,callEvent))
							«ENDFOR»
					«ENDFOR»
				«ENDFOR»
					
				def generateEvents(self):
					self.events=[]
				
				
				def getEvents(self):
					eventcopy=self.events.copy()
					self.events.clear()
					return eventcopy
				
				def __init__(self,detmodel):
					self.detmodel=detmodel
					#init input ports
					self.inports=[
					«FOR inport : connection.component.inports SEPARATOR ", "»
						«IF connection.inCalls.get(inport).size>0»
							InPort«inport.interfaceRealization.interface.name.toFirstUpper»(
								portname="«inport.name.toFirstUpper»",
								portcall=detmodel«connection.componentCall»«connection.inCalls.get(inport).get(0)»
							)
						«ENDIF»
					«ENDFOR»
					]
					self.inevents=dict()
					self.outevents=list()
					#TODO: init simulation
					#...
					pass
								
				def collectInEvents(self):
					self.inevents.clear()
					for port in inports:
						port.getEvents(self.inevents)
					
				def updateInputs(self):
					#collect the incoming events
					self.collectInEvents()
					#input check template
					«FOR port : connection.component.inports»
						#check «port.name.toFirstUpper»
						«FOR event : port.interfaceRealization.interface.events.map[e|e.event]»
							if "«port.name.toFirstUpper»_«event.name.toFirstUpper»" in self.inevents:
								parameters=self.inevents["«port.name.toFirstUpper»_«event.name.toFirstUpper»"]
								#TODO: handle the event
								#...
								pass
						«ENDFOR»
					«ENDFOR»
					
								
				def updateSimulation(self):
					#TODO: update the simulation
					#...
					pass
					
				def release(self):
					#TODO: release all allocated resources
					#...
					pass
					
				
		«ENDFOR»
		'''
	}
}