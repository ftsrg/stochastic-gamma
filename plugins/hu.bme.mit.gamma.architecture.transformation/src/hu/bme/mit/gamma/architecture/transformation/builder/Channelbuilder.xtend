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
import hu.bme.mit.gamma.architecture.transformation.errors.GammaTransformationException

import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
import hu.bme.mit.gamma.statechart.interface_.RealizationMode

class Channelbuilder {

	val Table<ComponentInstance, Port, List<InstancePortReference>> table = HashBasedTable.create
	val ifModelFactory = InterfaceModelFactory.eINSTANCE
	val cmpModelFactory = CompositeModelFactory.eINSTANCE

	var isBuilt = false

	def add(ComponentInstance sourceInst, Port sourcePort, ComponentInstance targetInst, Port targetPort) {
		if (sourceInst === null || sourcePort === null || targetInst === null || targetPort === null) {
			throw new GammaTransformationException("Channel references null object")
		}
		if (!sourceInst.derivedType.ports.contains(sourcePort)){
			throw new GammaTransformationException('''Channel references inconsistent source port reference «sourceInst.name».«sourcePort.name» «sourceInst.derivedType.ports.map[p|p.name].toList»''')
		}
		if (!targetInst.derivedType.ports.contains(targetPort)){
			throw new GammaTransformationException('''Channel references inconsistent target port reference «targetInst.name».«targetPort.name» «targetInst.derivedType.ports.map[p|p.name].toList»''')
		}
		if (sourcePort.interfaceRealization.realizationMode==RealizationMode.REQUIRED){
			throw new GammaTransformationException('''Channel references inconsistent source port direction «sourceInst.name».«sourcePort.name»''')
		}
		if (targetPort.interfaceRealization.realizationMode==RealizationMode.PROVIDED){
			throw new GammaTransformationException('''Channel references inconsistent target port direction «targetInst.name».«targetPort.name»''')
		}
		val channel = cmpModelFactory.createSimpleChannel
		val targetRef = cmpModelFactory.createInstancePortReference
		targetRef.instance = targetInst
		targetRef.port = targetPort
		if (!table.contains(sourceInst, sourcePort)) {
			val targetList = <InstancePortReference>newArrayList
			table.put(sourceInst, sourcePort, targetList)
			targetList.add(targetRef)
		} else {
			val targetList = table.get(sourceInst, sourcePort)
			targetList.add(targetRef)
		}
	}

	def build() {
		//if (!isBuilt) {
			val channels = <Channel>newArrayList
			for (sourceInst : table.rowKeySet) {
				for (sourcePort : table.rowMap.get(sourceInst).keySet) {
					val targetList = table.get(sourceInst, sourcePort).filter[ref|ref !== null].toSet
					val sourceRef = cmpModelFactory.createInstancePortReference
					sourceRef.instance = sourceInst
					sourceRef.port = sourcePort
					if (targetList.length == 1) {
						val channel = cmpModelFactory.createSimpleChannel
						channel.providedPort = sourceRef
						channel.requiredPort = targetList.get(0)
						channels += channel
					} else if (targetList.length > 1) {
						val channel = cmpModelFactory.createBroadcastChannel
						channel.providedPort = sourceRef
						channel.requiredPorts += targetList
						channels += channel
					} else {
						throw new GammaTransformationException("Channel builder internal error")
					}
				}
			}
			//isBuilt = true
			table.clear
			return channels
		//} else {
		//	throw new GammaTransformationException("Channel builder is already built")
		//}
	}

}
