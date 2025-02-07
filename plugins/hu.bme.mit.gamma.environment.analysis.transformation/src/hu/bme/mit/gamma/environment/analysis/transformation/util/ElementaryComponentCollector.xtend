/********************************************************************************
 * Copyright (c) 2018-2024 Contributors to the Gamma project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * SPDX-License-Identifier: EPL-1.0
 ********************************************************************************/
package hu.bme.mit.gamma.environment.analysis.transformation.util

import java.util.List
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance
import hu.bme.mit.gamma.statechart.composite.SimpleChannel
import hu.bme.mit.gamma.statechart.composite.BroadcastChannel
import hu.bme.mit.gamma.statechart.interface_.RealizationMode
import hu.bme.mit.gamma.environment.model.AbstractEnvironmentCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.AbstractEnvironmentCompositeComponent
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance
import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent
import hu.bme.mit.gamma.environment.model.util.EnvironmentModelSwitch
import hu.bme.mit.gamma.environment.analysis.transformation.util.EnvironmentConnections

class ElementaryComponentCollector {
	 
	

	static def dispatch List<EnvironmentConnections> collect(
			ElementaryEnvironmentComponentInstance component,
			List<ComponentInstance> callStack
			){
				
		var conn = <EnvironmentConnections>newArrayList()
		
		if (component.eContainer instanceof EnvironmentAsynchronousCompositeComponent){
			var supercomp=component.eContainer as EnvironmentAsynchronousCompositeComponent
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
					for(req : chan.requiredPorts){
						if (req.instance==component){
							EnvironmentConnections.Builder.addInCall(req.port,chan.providedPort)
						}
					}
					/*chan.requiredPorts.filter[e|e.instance==component]
						.forEach[e|
							EnvironmentConnections.Builder.addInCall(e.port,chan.providedPort)
						]*/
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
				
		if (component.eContainer instanceof EnvironmentSynchronousCompositeComponent){
			var supercomp=component.eContainer as EnvironmentSynchronousCompositeComponent
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
							EnvironmentConnections.Builder.addInCall(e.port,chan.providedPort)
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
		
		if (component.eContainer instanceof EnvironmentCascadeCompositeComponent){
			var supercomp=component.eContainer as EnvironmentCascadeCompositeComponent
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
							EnvironmentConnections.Builder.addInCall	(e.port,chan.providedPort)
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
				
	}
	
	
	static def dispatch List<EnvironmentConnections> collect(
			EnvironmentAsynchronousCompositeComponentInstance component,
			List<ComponentInstance> callStack
			){
				
		var connections = <EnvironmentConnections>newArrayList()
		var localStack=<ComponentInstance>newArrayList()
		localStack.addAll(callStack)
		localStack.add(component)
		for (subcomp : component.type.environmentComponents){
			connections.addAll(collect(subcomp,localStack))
		}
		for (subcomp : component.type.components){
			connections.addAll(collect(subcomp,localStack))
		}
		return connections			
		

	}
	
		
	static def dispatch List<EnvironmentConnections> collect(
			EnvironmentSynchronousCompositeComponentInstance component,
			List<ComponentInstance> callStack
			){
				
		var connections = <EnvironmentConnections>newArrayList()
		var localStack=<ComponentInstance>newArrayList()
		localStack.addAll(callStack)
		localStack.add(component)
		for (subcomp : component.type.environmentComponents){
			connections.addAll(collect(subcomp,localStack))
		}
		for (subcomp : component.type.components){
			connections.addAll(collect(subcomp,localStack))
		}
		return connections			
		
	}
		
	static def dispatch List<EnvironmentConnections> collect(
			EnvironmentCascadeCompositeComponentInstance component,
			List<ComponentInstance> callStack
			){
				
		var connections = <EnvironmentConnections>newArrayList()
		var localStack=<ComponentInstance>newArrayList()
		localStack.addAll(callStack)
		localStack.add(component)
		for (subcomp : component.type.environmentComponents){
			connections.addAll(collect(subcomp,localStack))
		}
		for (subcomp : component.type.components){
			connections.addAll(collect(subcomp,localStack))
		}
		return connections			
	}
	

	static def dispatch List<EnvironmentConnections> collect(
			AsynchronousComponentInstance component,
			List<ComponentInstance> callStack
			){
		var connections = <EnvironmentConnections>newArrayList()
		var localStack=<ComponentInstance>newArrayList()
		localStack.addAll(callStack)
		localStack.add(component)
		if(component.type instanceof EnvironmentAsynchronousCompositeComponent){
			var comptype = component.type as EnvironmentAsynchronousCompositeComponent
			for (subcomp : comptype.environmentComponents){
				connections.addAll(collect(subcomp,localStack))
			}
			for (subcomp : comptype.components){
				connections.addAll(collect(subcomp,localStack))
			}
			return connections			
		}
		if (component.type instanceof AsynchronousAdapter) {
			var inst=(component.type as AsynchronousAdapter).wrappedComponent
			connections.addAll(collect(inst,localStack))
		}
		return connections
	} 
	
	static def dispatch List<EnvironmentConnections> collect(
			SynchronousComponentInstance component,
			List<ComponentInstance> callStack
			){
		var connections = <EnvironmentConnections>newArrayList()
		var localStack=<ComponentInstance>newArrayList()
		localStack.addAll(callStack)
		localStack.add(component)
		if(component.type instanceof EnvironmentSynchronousCompositeComponent){
			var comptype = component.type as EnvironmentSynchronousCompositeComponent
			for (subcomp : comptype.environmentComponents){
				connections.addAll(collect(subcomp,localStack))
			}
			for (subcomp : comptype.components){
				connections.addAll(collect(subcomp,localStack))
			}
			return connections			
		}else if(component.type instanceof EnvironmentCascadeCompositeComponent){
			var comptype = component.type as EnvironmentCascadeCompositeComponent
			for (subcomp : comptype.environmentComponents){
				connections.addAll(collect(subcomp,localStack))
			}
			for (subcomp : comptype.components){
				connections.addAll(collect(subcomp,localStack))
			}
			return connections			
		}else{
			return connections
		}
	}
}
