package hu.bme.mit.gamma.analysis.transformation

import hu.bme.mit.gamma.environment.model.EnvironmentDelay
import hu.bme.mit.gamma.environment.model.EnvironmentSample
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch
import hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource
import hu.bme.mit.gamma.stochastic.stochastic.BrownianKernel
import hu.bme.mit.gamma.stochastic.stochastic.PeriodicKernel
import hu.bme.mit.gamma.stochastic.stochastic.LinearKernel
import hu.bme.mit.gamma.stochastic.stochastic.RBFKernel
import hu.bme.mit.gamma.stochastic.stochastic.SumKernel
import hu.bme.mit.gamma.stochastic.stochastic.PythonSimulation
import hu.bme.mit.gamma.stochastic.stochastic.InfluxDB
import hu.bme.mit.gamma.stochastic.stochastic.DiracProcess
import hu.bme.mit.gamma.stochastic.stochastic.FittedGaussianProcess
import hu.bme.mit.gamma.stochastic.stochastic.ExponentialRandomVariable
import hu.bme.mit.gamma.stochastic.stochastic.NormalRandomVariable
import java.util.List
import hu.bme.mit.gamma.stochastic.stochastic.RandomVariable
import hu.bme.mit.gamma.environment.model.StochasticRule
import hu.bme.mit.gamma.environment.model.SimulationRule
import hu.bme.mit.gamma.stochastic.stochastic.BernoulliRandomVariable
import hu.bme.mit.gamma.stochastic.stochastic.ContinouosRandomVariable
import hu.bme.mit.gamma.stochastic.stochastic.DiscreteRandomVariable
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.stochastic.stochastic.CategoricalProbabaility
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.environment.model.EventFilter
import hu.bme.mit.gamma.environment.model.PortFilter
import hu.bme.mit.gamma.environment.model.ComponentFilter
import hu.bme.mit.gamma.stochastic.stochastic.WeibullRandomVariable
import hu.bme.mit.gamma.stochastic.stochastic.GammaRandomVariable
import hu.bme.mit.gamma.stochastic.stochastic.FittedExponentialRandomVariable
import hu.bme.mit.gamma.stochastic.stochastic.FittedNormalRandomVariable
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator

class PythonClassGenerator {
	
	static Integer cntr=0
	final String packageName
	static Integer distcntr=0
	final ExpressionEvaluator expEval;
	
	new(String packageName){
		this.packageName=packageName
		expEval=ExpressionEvaluator.INSTANCE;
	}
	
	
	
	def generateRules(EnvironmentConnections connection){
		'''
		{
		«FOR port : connection.component.outports SEPARATOR ", "»
			"«port.name.toFirstUpper»" : {
			«FOR event : port.interfaceRealization.interface.events SEPARATOR ", "» 
				«var rules=connection.component.behaviorRules
						.filter[r|!r.filter.filter[f|f instanceof EventFilter].filter[f|(f as EventFilter).event.event==event.event].empty].toList»
				#«if(rules.isEmpty){
					rules=connection.component.behaviorRules
						.filter[r|!r.filter.filter[f|f instanceof PortFilter].filter[f|(f as PortFilter).port==port].empty]
							.toList
				}»
				#«if(rules.isEmpty){
					rules=connection.component.behaviorRules
						.filter[r|!r.filter.filter[f|f instanceof ComponentFilter].empty]
							.toList
				}»
				«IF (!rules.empty)»
					«var rule=rules.get(0)»
					"«event.event.name.toFirstUpper»" : 
					«IF rule instanceof SimulationRule»
						«rule.simulation.simulationClassName»Instance
					«ELSEIF rule instanceof StochasticRule»
						«generateStochasticModel(rule
							.stochasticModel,connection.component.name)»
					«ENDIF»
				«ENDIF»
			«ENDFOR»
			}
		«ENDFOR»
		}
		'''
	}
	
	def generateSimulationInstances(List<EnvironmentConnections> connections){
		'''
		«var simrules=connections.flatMap[c|c.component.behaviorRules]
			.filter[r|r instanceof SimulationRule]
				.map[r|r as SimulationRule]»
		«FOR simrule : simrules»
			«simrule.simulation.simulationClassName»Instance = «simrule.simulation.simulationClassName»()
		«ENDFOR»
		'''
	}
	
	def generateCalls(EnvironmentConnections connection){
		'''
		{
		«IF !connection.outCalls.keys.empty»
		«FOR port : connection.component.outports SEPARATOR ", "»
			"«port.name.toFirstUpper»" : [
			«FOR call : connection.outCalls.get(connection.outCalls.keys.get(0)) SEPARATOR ", "»
				«IF call.charAt(0)=='_'»
					«connection.componentCall.replaceAll(".","")»«call»
				«ELSE»
					sctmodel«connection.componentCall»«call»
				«ENDIF»
			«ENDFOR»
			]
		«ENDFOR»		
		«ENDIF»
		}
		'''
	}
	
	def generateCallEvents(EnvironmentConnections connection){
		'''
		{
		«IF !connection.outCalls.keys.empty»
		«FOR port : connection.component.outports SEPARATOR ", "»
			"«port.name.toFirstUpper»" : {
			«FOR call : connection.outCalls.get(port) SEPARATOR ", "»
				«FOR event : port.interfaceRealization.interface.events.map[e|e.event]SEPARATOR ", "»
					"«event.name.toFirstUpper»" : (lambda:sctmodel«connection.componentCall»«call».raise«event.name.toFirstUpper»(«generateFuncParams(event)»))
				«ENDFOR»
			«ENDFOR»
			}
		«ENDFOR»		
		«ENDIF»
		}
		'''
	}
	
	def generatePortArray(EnvironmentConnections connection){
		'''
		[«FOR port : connection.component.outports SEPARATOR ', '»"«port.name.toFirstUpper»"«ENDFOR»]
		'''
	}
	def generateInterfaceSubClass(Interface i){
		'''
		
			class Java:
				implements = ["«packageName».interfaces.«i.name.toFirstUpper»Interface$Listener$Provided"]
		'''
	}
	
	
	def generatePortevents(EnvironmentConnections connection){
		'''
		{
		«FOR port : connection.component.outports SEPARATOR ", "»
			"«port.name.toFirstUpper»" : [
				«FOR event : port.interfaceRealization.interface.events SEPARATOR ", "»
					"«event.event.name.toFirstUpper»"
				«ENDFOR»
			]
		«ENDFOR»
		}
		'''
	}
	
	def generateInPort(EnvironmentConnections connection){
		'''sctmodel«connection.componentCall»«connection.inCalls.values.get(0)»'''
	}
	
	def generateFuncParams(Event event) '''«FOR parameter : event.parameterDeclarations SEPARATOR ", "»«parameter.name.toFirstLower»«ENDFOR»'''
	
	def generateEventSourceClass(){
		'''
		class EventSource():
			def __init__(self,name,calls,rules,portevents,detmodel):
				self.calls=calls
				self.rules=rules
				self.portevents=portevents
				self.detmodel=detmodel
				
				
			def generateEvents(self):
				self.events=[]
				ports=list(self.calls.keys())
				#iterating through ports
				for port in ports:
					pevents=self.portevents[port]
					#iterating through events
					for pevent in pevents:
						rule=self.rules[port][pevent]
						call=self.calls[port][pevent]
						time=rule.calc(port+"."+pevent,0.0)
						if time>=0:
							#iterating through port connections
							self.events.append(Event(self,time,call))

			def getEvents(self):
				eventcopy=self.events.copy()
				self.events.clear()
				return eventcopy
		'''
	}
	
	def generatePeriodicEventSourceClass(){
		'''
		class PeriodicEventSource():
			def __init__(self,name,calls,rules,portevents,detmodel):
				self.name=name
				self.calls=calls
				self.rules=rules
				self.portevents=portevents
				self.detmodel=detmodel
			
			def generateEvents(self):
				self.events=[]
				ports=list(self.calls.keys())
				#iterating through self.ports
				for port in ports:
					pevents=self.portevents[port]
					#iterating through events
					for pevent in pevents:
						call=self.calls[port][pevent]
						rule=self.rules[port][pevent]
						simulationtime=0.0
						while simulationtime < simTime:
							simulationtime=simulationtime+rule.calc(port+"."+pevent,simulationtime)
							#iterating through port connections
							self.events.append(Event(self,simulationtime,call))

			def getEvents(self):
				eventcopy=self.events.copy()
				self.events.clear()
				return eventcopy
		'''
	}
	
	def generateSampleClasses(List<EnvironmentConnections> connections){
		'''
		«var interfaces=connections.filter[c|c.component instanceof EnvironmentSample]
									.map[c|c.component.outports.get(0).interfaceRealization.interface].toSet»
		«FOR i : interfaces»
			class Sample«i.name.toFirstUpper»():
				def __init__(self,name,inport,calls,rules,detmodel,actual_time):
					self.name=name
					callitem=calls.popitem()#only one out port
					self.calls=callitem[1]
					self.port=callitem[0]
					self.rules=rules.popitem()[1]#only one out port
					self.detmodel=detmodel
					self.event_cntr=0
					self.events=[]
					inport.registerListener(self)
					self.actual_time=actual_time
						
				def getEvents(self):
					eventcopy=self.events.copy()
					self.events.clear()
					return eventcopy

				def generateEvents(self):
					self.events=[]
					#definition of the interface functions
				«FOR event : i.events»
					def raise«event.event.name.toFirstUpper»(self,«generateFuncParams(event.event)»):
						«event.event.parameterDeclarations.get(0).name.toFirstLower»=self.rules["«event.event.name.toFirstUpper»"].calc(self.port+"."+"«event.event.name.toFirstUpper»",self.actual_time[0])
						#«event.event.parameterDeclarations.get(0).type»
						self.event_cntr=self.event_cntr+1
						for call in self.calls:
							callEvent=lambda:call.raise«event.event.name.toFirstUpper»(«generateFuncParams(event.event)»);
							self.events.append(Event(self,self.actual_time[0],callEvent))
				«ENDFOR»
			
			«generateInterfaceSubClass(i)»
		«ENDFOR»									
		'''
	}
	
	def generateDelayClasses(List<EnvironmentConnections> connections){
		var interfaces=connections.filter[c|c.component instanceof EnvironmentDelay]
									.map[c|c.component.outports.get(0).interfaceRealization.interface].toSet
		var classes=new StringBuilder()
		for (i : interfaces){
			classes.append(
				'''
				class Delay«i.name.toFirstUpper»():
					def __init__(self,name,inport,calls,rules,detmodel,actual_time):
						self.name=name
						callitem=calls.popitem()#only one out port
						self.calls=callitem[1]
						self.port=callitem[0]
						self.rules=rules.popitem()[1]#only one out port
						self.detmodel=detmodel
						self.event_cntr=0
						self.events=[]
						inport.registerListener(self)
						self.actual_time=actual_time
						
						
					def generateEvents(self):
						self.events=[]
						
						
					def getEvents(self):
						eventcopy=self.events.copy()
						self.events.clear()
						return eventcopy
					
					#definition of the interface functions
					«FOR event : i.events»
						def raise«event.event.name.toFirstUpper»(self,«generateFuncParams(event.event)»):
							time=self.rules["«event.event.name.toFirstUpper»"].calc(self.port+"."+"«event.event.name.toFirstUpper»",self.actual_time[0])
							self.event_cntr=self.event_cntr+1
							failureTime=time+self.actual_time[0]
							for callitem in self.calls:
								callEvent=lambda:callitem.raise«event.event.name.toFirstUpper»(«generateFuncParams(event.event)»);
								self.events.append(Event(self,failureTime,callEvent))
					«ENDFOR»
				«generateInterfaceSubClass(i)»
				
				'''
			)
		}
		return classes.toString
	}
	
	def generateSwitchClasses(List<EnvironmentConnections> connections){
		var interfaces=connections.filter[c|c.component instanceof EnvironmentSwitch]
									.map[c|c.component.outports.get(0).interfaceRealization.interface].toSet
		var classes=new StringBuilder()
		for (i : interfaces){
			classes.append(
				'''
				class Switch«i.name.toFirstUpper»():
					def __init__(self,name,inport,calls,portarray,categorical,detmodel,actual_time):
						self.name=name
						self.calls=calls
						self.portarray=portarray
						self.categorical=categorical
						self.detmodel=detmodel
						self.event_cntr=0
						self.events=[]
						inport.registerListener(self)
						self.actual_time=actual_time			
									
					def generateEvents(self):
						self.events=[]
						
					def getEvents(self):
						eventcopy=self.events.copy()
						self.events.clear()
						return eventcopy
					
					#definition of the interface functions
					«FOR event : i.events»
						def raise«event.event.name.toFirstUpper»(self,«generateFuncParams(event.event)»):
							port=self.portarray[self.categorical.calc()]
							eventcalls=self.calls[port]#["«event.event.name.toFirstUpper»"]
							self.event_cntr=self.event_cntr+1
							for call in eventcalls:
								callEvent=lambda:call.raise«event.event.name.toFirstUpper»(«generateFuncParams(event.event)»);
								self.events.append(Event(self,self.actual_time[0],callEvent))
					«ENDFOR»
				«generateInterfaceSubClass(i)»
				'''
			)
		}
		return classes.toString
	}
	
	def generateExternSimulationTemplateClasses(List<EnvironmentConnections> connections){
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
	
	
	def generateCategorical(EnvironmentConnections connection){
		'''
		«var rules=connection.component.behaviorRules
			.map[r|r as StochasticRule]»
		RandomVariable(
			dist=pyro.distributions.Categorical(
				torch.tensor([
				«FOR port : connection.component.outports SEPARATOR ", "»
					«rules.filter[r|r.filter.map[f|(f as PortFilter).port].contains(port)]
						.map[r|Double.toString(expEval.evaluateDecimal((r.stochasticModel as CategoricalProbabaility).probability))].get(0)»
				«ENDFOR»
				])),
			name="«connection.component.name.toFirstUpper»«(distcntr++).toString»")
		'''
	}
	
	def generateSwitchInstances(List<EnvironmentConnections> connections){
		'''
		«FOR connection : connections»
		components.append(
			Switch«connection.component.inports.get(0).interfaceRealization.interface.name.toFirstUpper»(
				name  = "«TransformationUtility.generateEnvCompName(connection)»",
				inport=«generateInPort(connection)»,
				calls=«generateCalls(connection)»,
				portarray=«generatePortArray(connection)»,
				categorical=«generateCategorical(connection)»,
				detmodel=sctmodel,
				actual_time = actualTime
			)
		)
		«ENDFOR»
		'''
	}
	
	
	def generateDelayInstances(List<EnvironmentConnections> connections){
		

		
		var interfaces=connections.filter[c|c.component instanceof EnvironmentDelay]
									.map[c|c.component.outports.get(0).interfaceRealization.interface].toSet
		'''
		
		«FOR connection : connections»
		components.append(Delay«connection.component.outports.get(0).interfaceRealization.interface.name.toFirstUpper»(
			name  = "«TransformationUtility.generateEnvCompName(connection)»",
			inport=«generateInPort(connection)»,
			calls = «generateCalls(connection)»,
			rules = «generateRules(connection)»,
			detmodel = sctmodel,
			actual_time = actualTime
		))
		«ENDFOR»
		
		'''
		
	}
	
	def generateSampleInstances(List<EnvironmentConnections> connections){
		

		
		var interfaces=connections.filter[c|c.component instanceof EnvironmentDelay]
									.map[c|c.component.outports.get(0).interfaceRealization.interface].toSet
		'''
		
		«FOR connection : connections»
		components.append(Sample«connection.component.outports.get(0).interfaceRealization.interface.name.toFirstUpper»(
			name  = "«TransformationUtility.generateEnvCompName(connection)»",
			inport=«generateInPort(connection)»,
			calls = «generateCalls(connection)»,
			rules = «generateRules(connection)»,
			detmodel = sctmodel,
			actual_time = actualTime
		))
		«ENDFOR»
		
		'''
		
	}
	
	
	def generatePeriodicEventSourceInstances(List<EnvironmentConnections> connections){
		'''
		«FOR connection : connections»
			components.append(PeriodicEventSource(
				name  = "«TransformationUtility.generateEnvCompName(connection)»",
				calls = «generateCallEvents(connection)»,
				rules = «generateRules(connection)»,
				portevents = «generatePortevents(connection)»,
				detmodel = sctmodel
			))
		«ENDFOR»
		'''
	}
	
	
	def generateEventSourceInstances(List<EnvironmentConnections> connections){
		'''
		«FOR connection : connections»
			components.append(EventSource(
				name  = "«TransformationUtility.generateEnvCompName(connection)»",
				calls = «generateCallEvents(connection)»,
				rules = «generateRules(connection)»,
				portevents = «generatePortevents(connection)»,
				detmodel = sctmodel
			))
		«ENDFOR»
		'''
	}
	
	
	def generateEventClass(){
		'''
		class Event():
			def __init__(self,eventSource,eventTime):
				self.eventSource=eventSource
				self.eventTime=eventTime
			def __init__(self,eventSource,eventTime,eventCall):
				self.eventSource=eventSource
				self.eventTime=eventTime
				self.eventCall=eventCall
		'''
	}
	
	
	
	
	def generateRandomVariableClass(){
		'''
		class RandomVariable():
			def __init__(self,dist,name,N=100000):
				self.dist=dist
				self.name=name
				self.event_cntr=N-1
				self.meta_cntr=-1
				self.N=N
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				if self.N>0:
					if self.event_cntr==self.N:
						self.event_cntr=0
						self.meta_cntr=self.meta_cntr+1
						
						self.samples=pyro.sample(self.name+"_sample_"+str(self.meta_cntr),self.dist.expand([self.N]))
					return self.samples[self.event_cntr].item()
				else:
					return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()

		'''
	}
	
	def generateRandomVariableClass_old(){
		'''
		class RandomVariable():
			def __init__(self,dist,name):
				self.dist=dist
				self.name=name
				self.event_cntr=0
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
		'''
	}
	
	def generateContinuousRandomVariableClass(){
		'''
		class ContinuousRandomVariable():
			def __init__(self,dist,name,N=100000):
				self.dist=dist
				self.name=name
				self.event_cntr=N-1
				self.meta_cntr=-1
				self.N=N
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				if self.N>0:
					if self.event_cntr==self.N:
						self.event_cntr=0
						self.meta_cntr=self.meta_cntr+1
						self.samples=pyro.sample(self.name+"_sample_"+str(self.meta_cntr),self.dist.expand([self.N]))
					return self.samples[self.event_cntr].item()
				else:
					return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
		'''
	}
	
	def generateContinuousRandomVariableClass_old(){
		'''
		class ContinuousRandomVariable():
			def __init__(self,dist,name):
				self.dist=dist
				self.name=name
				self.event_cntr=0
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
		'''
	}
	
	def generateDiscreteRandomVariableClass(){
		'''
		class DiscreteRandomVariable():
			def __init__(self,dist,name):
				self.dist=dist
				self.name=name
				self.event_cntr=0
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()-1.0
		'''
	}
	
	def generateGaussProcessClass(){
		'''
		class GaussProcess():
			def __init__(self, dataset, kernel, lr, name):
				self.name=name
				self.event_cntr=0
				points=dataset.points
				x = []
				t = []
				y = []
				i = 0
				t0 = 0
				for p in points:
					if i == 0:
						t0 = datetime.datetime.strptime(p.pop("time"), '%Y-%m-%dT%H:%M:%S.%fZ')
						t.append(t0)
					else:
						t.append(datetime.datetime.strptime(p.pop("time"), '%Y-%m-%dT%H:%M:%S.%fZ'))
					t[i] = t[i] - t0
					x.append(t[i].total_seconds())
					yi = list(p.values())
					if len(yi) == 1:
						y.append(yi[0])
					else:
						y.append(yi)
					i = i + 1
				
				x = torch.tensor(x)
				y = torch.tensor(y)
				X = x
				
				# initialize the inducing inputs
				Xu = torch.arange(1.) / 6.0
				
				#kernel = gp.kernels.Sum(gp.kernels.Periodic(input_dim=1), gp.kernels.Brownian(input_dim=1))
				# we increase the jitter for better numerical stability
				sgpr = gp.models.SparseGPRegression(X=X, y=y, kernel=kernel, Xu=Xu, jitter=1.0e-5)
				
				# the way we setup inference is similar to above
				optimizer = torch.optim.Adam(sgpr.parameters(), lr=lr)
				loss_fn = pyro.infer.Trace_ELBO().differentiable_loss
				losses = []
				num_steps = 2000
				for i in range(num_steps):
					optimizer.zero_grad()
					loss = loss_fn(sgpr.model, sgpr.guide)
					if i % 20 == 0:
						print("Step: ", i, " Loss: ", loss)
					loss.backward()
					optimizer.step()
					losses.append(loss.item())
				self.gp=sgpr
				
			def calc(self,event,time):
				self.event_cntr=self.event_cntr+1
				mu,sig=self.gp.forward(torch.tensor([time]), full_cov=False, noiseless=False)
				return pyro.sample(self.name+"_sample_GP_"+str(self.event_cntr),pyro.distributions.Normal(mu,sig)).item()
		'''
	}
	
	
	def generateFittedExponentialRandomVariableClass(){
		'''
		class FittedExponentialRandomVariable():
			def model(self,data):
				rate = pyro.param("alpha_q", torch.tensor(1.0),
					constraint=constraints.positive)
				for i in range(len(data)):
					# observe datapoint i using the bernoulli likelihood
					pyro.sample("obs_{}".format(i), dist.Exponential(rate), obs=(torch.tensor(data[i])))
			
			def guide(self,data):
				pass
							
			def __init__(self,name,source,lr):
				self.name=name
				self.event_cntr=0
				#preprocess data
				data=[float(p["data"]) for p in list(source.points)]
				md=min(data)
				data=[d-md for d in data]
				# setup the optimizer
				adam_params = {"lr": lr, "betas": (0.99, 0.999)}
				optimizer = Adam(adam_params)
				n_steps=2000
				# setup the inference algorithm
				svi = SVI(self.model, self.guide, optimizer, loss=Trace_ELBO())
				
				losses=list()
				ploss=100000000.0
				# do gradient steps
				for step in range(n_steps):
					loss=svi.step(data)
					losses.append(loss)
					if step % 10 == 0:
						print("loss=",loss)
					if ploss-loss<0.001:
						break
					ploss=loss
				
				# grab the learned variational parameters
				m = pyro.param("alpha_q").item()
				print(m)
				
				xx = list(np.arange(0, 4/m, 0.1/m))
				yy = []
				d=dist.Exponential(m)
				self.dist=d
				
				for x in xx:
					y=float(exp(dist.Exponential(m).log_prob(torch.tensor(x))))
					yy.append(y)
				fig1, a1 = plt.subplots()
				fig2, a2 = plt.subplots()
				
				a1.plot(losses)
				a1.set_ylabel('loss')
				a1.set_xlabel('step')
				a1.set_title("model fitting process")
				a2.hist(data,bins=10)
				a2.plot(xx,yy)
				a2.set_ylabel('frequency')
				a2.set_xlabel('delay (s)')
				a2.set_title("model fitting results")
				
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
		'''
	}
	
	
	def generateFittedNormalRandomVariableClass(){
		'''
		class FittedNormalRandomVariable():
			def model(self,data):
				alpha_q = pyro.param("alpha_q", torch.tensor(1.0),
					constraint=constraints.positive)
				beta_q = pyro.param("beta_q", torch.tensor(1.0),
						constraint=constraints.positive)
				for i in range(len(data)):
					# observe datapoint i using the bernoulli likelihood
					pyro.sample("obs_{}".format(i), dist.Normal(alpha_q,beta_q), obs=torch.tensor(data[i]))
			
			def guide(self,data):
				pass
			
			def __init__(self,name,source,lr):
				self.name=name
				self.event_cntr=0
				data=[float(p["data"]) for p in list(source.points)]
				# setup the optimizer
				adam_params = {"lr": lr, "betas": (0.99, 0.999)}
				optimizer = Adam(adam_params)
				n_steps=2000
				# setup the inference algorithm
				svi = SVI(self.model, self.guide, optimizer, loss=Trace_ELBO())
				
				losses=list()
				ploss=100000000.0
				# do gradient steps
				for step in range(n_steps):
					loss=svi.step(data)
					losses.append(loss)
					if step % 10 == 0:
						print("loss=",loss)
					if ploss-loss<0.001:
						break
					ploss=loss
				
				# grab the learned variational parameters
				m = pyro.param("alpha_q").item()
				s = pyro.param("beta_q").item()
				print(m)
				print(s)
				
				xx = list(np.arange(m-2*s, m+2*s, s/10))
				yy = []
				d=dist.Normal(loc=torch.tensor(m),scale=torch.tensor(s))
				self.dist=d
				
				for x in xx:
					y=float(exp(dist.Normal(loc=torch.tensor(m),scale=torch.tensor(s)).log_prob(torch.tensor(x))))
					yy.append(y)
				fig1, a1 = plt.subplots()
				fig2, a2 = plt.subplots()
				
				a1.plot(losses)
				a1.set_ylabel('loss')
				a1.set_xlabel('step')
				a1.set_title("model fitting process")
				a2.hist(data,bins=10)
				a2.plot(xx,yy)
				a2.set_ylabel('frequency')
				a2.set_xlabel('delay (s)')
				a2.set_title("model fitting results")
				
			def calc(self,event=0,time=0):
				self.event_cntr=self.event_cntr+1
				return pyro.sample(self.name+"_sample_"+str(self.event_cntr),self.dist).item()
		'''
	}
	
	
	dispatch def generateStochasticModel(FittedExponentialRandomVariable variable,String name){
		'''
		FittedExponentialRandomVariable(
			source=«generateDatasetInstance(variable.source)»,
			name="FittedExpVariable«name»«(distcntr++).toString»",
			lr=«variable.lr.toString»
		)
		'''
	}
	dispatch def generateStochasticModel(FittedNormalRandomVariable variable,String name){
		'''
		FittedNormalRandomVariable(
			source=«generateDatasetInstance(variable.source)»,
			name="FittedNormVariable«name»«(distcntr++).toString»",
			lr=«variable.lr.toString»
		)
		'''
	}
	dispatch def generateStochasticModel(ContinouosRandomVariable variable,String name){
		'''
		ContinuousRandomVariable(«generateDitribution(variable)»,"ContRandomVarriable«name»«(distcntr++).toString»")
		'''
	}
	
	dispatch def generateStochasticModel(DiscreteRandomVariable variable,String name){
		'''
		ContinuousRandomVariable(«generateDitribution(variable)»,"DiscRandomVarriable«name»«(distcntr++).toString»")
		'''
	}
	
	dispatch def generateStochasticModel(FittedGaussianProcess variable,String name){
		'''
		GaussProcess(
			dataset=«generateDatasetInstance(variable.source)»,
			kernel=«generateKernel(variable.kernel)»,
			lr=«Double.toString(variable.lr)»,
			name="FittedGaussianProcess«name»«(distcntr++).toString»"
		)
		'''
	}
	
	
	dispatch def generateDitribution(NormalRandomVariable dist){
	'''
	pyro.distributions.Normal(
			torch.tensor(«Double.toString(expEval.evaluateDecimal(dist.mean))»),
			torch.tensor(«Double.toString(expEval.evaluateDecimal(dist.scale))»)
	)
	'''
	}
	
	dispatch def generateDitribution(WeibullRandomVariable dist){
	'''
	pyro.distributions.Weibull(
			concentration=torch.tensor(«Double.toString(expEval.evaluateDecimal(dist.shape))»),
			shape=torch.tensor(«Double.toString(expEval.evaluateDecimal(dist.scale))»)
	)
	'''
	}
	dispatch def generateDitribution(GammaRandomVariable dist){
	'''
	pyro.distributions.Gamma(
			concentration=torch.tensor(«Double.toString(expEval.evaluateDecimal(dist.shape))»),
			rate=torch.tensor(«Double.toString(expEval.evaluateDecimal(dist.scale))»)
	)
	'''
	}
	
	dispatch def generateDitribution(ExponentialRandomVariable dist){
		'''pyro.distributions.Exponential(torch.tensor(«Double.toString(expEval.evaluateDecimal(dist.rate))»))'''
	}
	
	dispatch def generateDitribution(BernoulliRandomVariable dist){
		'''pyro.distributions.Bernoulli(torch.tensor(«Double.toString(expEval.evaluateDecimal(dist.probability))»))'''
	}
	
	dispatch def generateStochasticProcess(FittedGaussianProcess gp){
		'''
		MLGaussProcess(
			lr=float(«gp.lr.toString»),
			dataset=«generateDatasetInstance(gp.source)»,
			kernel=«generateKernel(gp.kernel)»
		),dist=None,dirac=None
		'''
	}
	
	dispatch def generateDitribution(DiracProcess dirac){
		'''
		dirac=«generateDatasetInstance(dirac.source)»,dist=None,mlgp=None
		'''
	}
	
	dispatch def generateDatasetInstance(InfluxDB dataset){
		'''
		Dataset(
			dbname="«dataset.dbname»",
			ip="«dataset.ip»",
			port=«dataset.port»,
			query="""«dataset.query»""" ,
			script=None
		)
		'''
	}
	
	def generateDatasetClass(){
		'''
		class Dataset():
		
			def __init__(self,dbname,ip,port,query=None,script=None):
				if query is not None:
					client = InfluxDBClient(ip, int(port), database=dbname)
					result = client.query(query)
					points = result.get_points()
					self.points=points
				elif script is not None:
					exec(script)
		'''
	}
	
	dispatch def generateDatasetInstance(PythonSimulation dataset){
		'''Dataset(
			dbname=None,
			query=None,
			ip=None,
			port=None, 
			script=r"""
«dataset.script»
"""
		)'''
	}
	
	static dispatch def generateKernel(BrownianKernel kernel){
		return '''gp.kernels.Brownian(input_dim=1)'''
	}
	static dispatch def generateKernel(PeriodicKernel kernel){
		return '''gp.kernels.Periodic(input_dim=1)'''
	}
	static dispatch def generateKernel(LinearKernel kernel){
		return '''gp.kernels.Linear(input_dim=1) '''
	}
	static dispatch def generateKernel(RBFKernel kernel){
		return '''gp.kernels.RBF(input_dim=1)'''
	}
	static dispatch def generateKernel(SumKernel kernel){
		return '''gp.kernels.Sum(«generateKernel(kernel.kernels.get(0))»,«generateKernel(kernel.kernels.get(1))»)'''
	}
	
	
}