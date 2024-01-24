package hu.bme.mit.gamma.architecture.transformation.builder

import hu.bme.mit.gamma.statechart.composite.InstancePortReference
import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.statechart.composite.CompositeModelFactory
import java.util.List
import java.util.ArrayList
import hu.bme.mit.gamma.statechart.composite.Channel

class Channelbuilder {

	val Table<ComponentInstance,Port,List<InstancePortReference>> table= HashBasedTable.create
	val ifModelFactory = InterfaceModelFactory.eINSTANCE
	val cmpModelFactory = CompositeModelFactory.eINSTANCE
	


	
	def add(ComponentInstance sourceInst,Port sourcePort,ComponentInstance targetInst,Port targetPort){
		val channel = cmpModelFactory.createSimpleChannel
		val targetRef = cmpModelFactory.createInstancePortReference
		targetRef.instance = targetInst
		targetRef.port=targetPort
		if (!table.contains(sourceInst,sourcePort)){
			val targetList=<InstancePortReference>newArrayList
			table.put(sourceInst,sourcePort,targetList)
			targetList.add(targetRef)
		}else{
			val targetList=table.get(sourceInst,sourcePort)
			targetList.add(targetRef)
		}
	}
	
	def build(){
		val channels = <Channel> newArrayList
		for (sourceInst : table.rowKeySet){
			for (sourcePort : table.rowMap.get(sourceInst).keySet){
				val targetList=table.get(sourceInst,sourcePort)
				val sourceRef=cmpModelFactory.createInstancePortReference
				sourceRef.instance=sourceInst
				sourceRef.port=sourcePort
				if (targetList.length == 1){
					val channel = cmpModelFactory.createSimpleChannel
					channel.providedPort = sourceRef
					channel.requiredPort = targetList.get(0)
				}else{
					val channel = cmpModelFactory.createBroadcastChannel
					channel.providedPort=sourceRef
					channel.requiredPorts+=targetList
				}
			}
		}
		return channels
	}
	
}