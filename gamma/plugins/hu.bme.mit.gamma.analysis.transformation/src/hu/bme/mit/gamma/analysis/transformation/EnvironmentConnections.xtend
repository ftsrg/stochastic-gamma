package hu.bme.mit.gamma.analysis.transformation

import hu.bme.mit.gamma.statechart.composite.InstancePortReference
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance
import java.util.List
import hu.bme.mit.gamma.environment.model.EnvironmentCompositeComponentInstance
import java.util.Map
import hu.bme.mit.gamma.statechart.interface_.Port
import com.google.common.collect.Multimap
import com.google.common.collect.HashMultimap

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
			inCalls.put(port,".get"+listenerPort.instance.name.toFirstUpper+"().get"+listenerPort.port.name.toFirstUpper+"()")
		}
		
		static def void addInCall(Port port,Port listenerPort){
			inCalls.put(port,".get"+listenerPort.name.toFirstUpper+"()")
		}
		
		static def void addOutCall(Port port,InstancePortReference listenerPort){
			outCalls.put(port,".get"+listenerPort.instance.name.toFirstUpper+"().get"+listenerPort.port.name.toFirstUpper+"()")
		}
		
		static def void addOutCall(Port port,Port listenerPort){
			outCalls.put(port,".get"+listenerPort.name.toFirstUpper+"()")
		}
		
		static def void init(
			ElementaryEnvironmentComponentInstance _component,
			List<EnvironmentCompositeComponentInstance> callStack
		){
			componentCall=""
			for(comp:callStack){
				componentCall=".get"+comp.name.toFirstUpper+"()"+componentCall
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