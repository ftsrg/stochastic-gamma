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

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.statechart.composite.InstancePortReference
import hu.bme.mit.gamma.statechart.interface_.Port
import java.util.List

class EnvironmentConnections {
	                                             
	public final Multimap<Port,String>                  inCalls 
	public final Multimap<Port,String>                  outCalls
	public final String                                 componentCall
	public final ElementaryEnvironmentComponentInstance component
	
	private new(
		    Multimap<Port,String>                  _inCalls      ,
		    Multimap<Port,String>                  _outCalls     ,
		    String                                 _componentCall,
		    ElementaryEnvironmentComponentInstance _component    
	){
		this.inCalls      =_inCalls      
		this.outCalls     =_outCalls     
		this.componentCall=_componentCall
		this.component    =_component    
	}
	 
	static class Builder {
		
		static Multimap<Port,String> inCalls 
		static Multimap<Port,String> outCalls
		static String componentCall
		static ElementaryEnvironmentComponentInstance component
		
		
		static def void addInCall(Port port,InstancePortReference listenerPort){
			if (! (listenerPort.instance instanceof ElementaryEnvironmentComponentInstance)){			
				inCalls.put(port,".get"+listenerPort.instance.name.toFirstUpper+"().get"+listenerPort.port.name.toFirstUpper+"()")
			}
		}
		
		static def void addInCall(Port port,Port listenerPort){
			inCalls.put(port,".get"+listenerPort.name.toFirstUpper+"()")
		}
		
		static def void addOutCall(Port port,InstancePortReference listenerPort){
			if (! (listenerPort.instance instanceof ElementaryEnvironmentComponentInstance)){	
				outCalls.put(port,".get"+listenerPort.instance.name.toFirstUpper+"().get"+listenerPort.port.name.toFirstUpper+"()")
			}else{
				outCalls.put(port,"_self.components['"+TransformationUtility.generateEnvCompName(componentCall,listenerPort.instance as ElementaryEnvironmentComponentInstance)+"']")
			}
		}
		
		static def void addOutCall(Port port,Port listenerPort){
			outCalls.put(port,".get"+listenerPort.name.toFirstUpper+"()")
		}
		
		static def void init(
			ElementaryEnvironmentComponentInstance _component,
			List<ComponentInstance> callStack
		){
			componentCall=""
			for(comp:callStack){
				componentCall=componentCall+".get"+comp.name.toFirstUpper+"()"
			}
			component = _component
			inCalls = HashMultimap.<Port,String>create()
			outCalls = HashMultimap.<Port,String>create()
		}
		
		static def EnvironmentConnections build(){
			val conn = new EnvironmentConnections(
				inCalls      ,
				outCalls     ,
				componentCall,
				component     
			)
			inCalls = HashMultimap.<Port,String>create()
			outCalls = HashMultimap.<Port,String>create()
			return conn
		}
	}	
	
}
