package hu.bme.mit.gamma.analysis.transformation

import hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance
import java.util.List
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponent
import hu.bme.mit.gamma.statechart.composite.SimpleChannel
import hu.bme.mit.gamma.statechart.composite.BroadcastChannel
import hu.bme.mit.gamma.statechart.interface_.RealizationMode

class ElementaryComponentCollector {
	
	static def dispatch List<EnvironmentConnections> collect(
			ElementaryEnvironmentComponentInstance component,
			List<EnvironmentCompositeComponentInstance> callStack
			){
				
		var conn = <EnvironmentConnections>newArrayList()

		var supercomp=component.eContainer as EnvironmentCompositeComponent
				
		EnvironmentConnections.Builder.init(component,callStack)		
		
		for(chan : supercomp.channels){
			if (chan.providedPort.instance == component){
				if (chan instanceof SimpleChannel){
					EnvironmentConnections.Builder.addOutCall(chan.providedPort.port,chan.requiredPort)
				}
				if (chan instanceof BroadcastChannel){
					for(req : chan.requiredPorts){
						EnvironmentConnections.Builder.addOutCall(chan.providedPort.port,req)
					}
				}
			}
			if (chan instanceof SimpleChannel){
				if (chan.requiredPort.instance == component){
					EnvironmentConnections.Builder.addInCall(chan.requiredPort.port,chan.providedPort)
				}
			}
			if (chan instanceof BroadcastChannel){
				chan.requiredPorts.filter[e|e.instance==component]
					.forEach[e|
						EnvironmentConnections.Builder.addOutCall(e.port,chan.providedPort)
					]
			}
		}
		for(bind : supercomp.portBindings){
			
			if (bind.instancePortReference.instance == component){
				if (bind.compositeSystemPort.interfaceRealization.realizationMode == RealizationMode.PROVIDED ){
					EnvironmentConnections.Builder.addOutCall(bind.instancePortReference.port,bind.compositeSystemPort)
				}
				if (bind.compositeSystemPort.interfaceRealization.realizationMode == RealizationMode.REQUIRED ){
					EnvironmentConnections.Builder.addInCall(bind.instancePortReference.port,bind.compositeSystemPort)
					
				}				
			}
		}
		
		
		conn.add(EnvironmentConnections.Builder.build)
		
		return conn
	}
	
	static def dispatch List<EnvironmentConnections> collect(
			EnvironmentCompositeComponentInstance component,
			List<EnvironmentCompositeComponentInstance> callStack
			){
				
		var connections = <EnvironmentConnections>newArrayList()
		var localStack=<EnvironmentCompositeComponentInstance>newArrayList()
		localStack.addAll(callStack)
		localStack.add(component)
		for (subcomp : component.type.environmentComponents){
			connections.addAll(collect(subcomp,localStack))
		}
		
		return connections
	}
}