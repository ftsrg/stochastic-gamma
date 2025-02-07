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
package hu.bme.mit.gamma.architecture.transformation.traceability

import java.util.HashMap
import hu.bme.mit.gamma.architecture.model.ArchitectureFunction
import hu.bme.mit.gamma.architecture.model.ArchitectureComponent
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.architecture.model.SoftwareComponent
import hu.bme.mit.gamma.architecture.model.StructuralElement

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import com.google.common.collect.HashMultimap
import hu.bme.mit.gamma.architecture.model.Allocation
import java.util.List
import hu.bme.mit.gamma.architecture.model.InformationFlow
import hu.bme.mit.gamma.architecture.transformation.RelationTransfomer
import hu.bme.mit.gamma.architecture.model.Connector
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition
import hu.bme.mit.gamma.architecture.model.InterfaceConnector
import hu.bme.mit.gamma.architecture.model.ArchitecturePort

class AllocationTrace {

	protected val HashMultimap<ArchitectureElement, ArchitectureElement> allocatedToMap = HashMultimap.create
	protected val HashMultimap<ArchitectureElement, ArchitectureElement> allocatedFromMap = HashMultimap.create
	protected extension val RelationTransfomer relationTransfomer
	protected val ArchitectureTrace trace

	new(ArchitectureTrace trace, List<Allocation> allocations) {
		this.trace = trace
		relationTransfomer = new RelationTransfomer(trace)
		for (allocation : allocations) {
			allocatedToMap.put(allocation.source, allocation.target)
			allocatedFromMap.put(allocation.target, allocation.source)
		}
	}

	def isAllocatedTo(ArchitectureElement element) {
		return allocatedToMap.containsKey(element)
	}

	def isAllocatedFrom(ArchitectureElement element) {
		return allocatedFromMap.containsKey(element)
	}

	def getConnector(InformationFlow flow) {
		if (allocatedToMap.containsKey(flow)) {
			val connector = allocatedToMap.get(flow).get(0) as Connector
			return connector
		} else {
			return null
		}
	}

	def getInterfacingComponent(InformationFlow flow,ArchitecturePort phyPort){
		val flowType=flow.flowType
		if (phyPort === null){
			return trace.getInterfaceComponent(flowType)
		}
		for (subPort :phyPort.type.ports.filter[p|trace.get(p.type)==flowType && allocatedToMap.containsKey(p)]){
			return trace.get(allocatedToMap.get(subPort).get(0)) as AsynchronousStatechartDefinition
		} 
		return trace.getInterfaceComponent(flowType)
	}

	def getInterfacingComponent(InformationFlow flow) {
		if (allocatedToMap.containsKey(flow)) {
			val funcConnector = allocatedToMap.get(flow).get(0) as Connector
			if (funcConnector instanceof InterfaceConnector) {
				val connType = funcConnector.type
				val phyConnector = getAllocatedTo(connType) as Connector
				val type = phyConnector.connType
				val flowIF = flow.flowType
				val flowType = trace.get(flowIF)
				val realtedPorts = type.ports.filter[p|p.type == flowType].toList
				if (realtedPorts.size > 1) {
					throw new ArchitectureException('''Information flow is ambigous in interface: «type.name»''', type)
				} else if (realtedPorts.empty) {
					return trace.getInterfaceComponent(flowIF)
				} else {
					val ifFuncs = getAllocatedTo(realtedPorts.get(0))
					if (ifFuncs.size != 1) {
						throw new ArchitectureException('''Ambigous port to IF function allocation with «ifFuncs.size» matches''',
							realtedPorts.get(0))
					} else {
						return trace.get(ifFuncs.get(0)) as AsynchronousStatechartDefinition
					}
				}
			}else{
				return null
			}
		} else {
			return null
		}
	}

	def getAllocatedTo(ArchitectureElement element) {
		if (allocatedToMap.containsKey(element)) {
			return allocatedToMap.get(element)
		} else {
			throw new ArchitectureException("No allocation target is found", element)
		}
	}

	def getAllocatedFrom(ArchitectureElement element) {
		if (allocatedFromMap.containsKey(element)) {
			return allocatedFromMap.get(element)
		} else {
			throw new ArchitectureException("No allocation source is found", element)
		}
	}

	def getPhyComponent(ArchitectureSubcompnent subcompnent) {
		if (allocatedToMap.containsKey(subcompnent)) {
			return allocatedToMap.get(subcompnent)
		} else {
			return subcompnent
		}
	}

	def getPhyComponent(ArchitectureSubfunction subfunction) {
		return getPhyComponent(subfunction.eContainer as ArchitectureSubcompnent)
	}

/* 
 * 	protected val HashMap<ArchitectureFunction,ArchitectureComponent> func2comp = newHashMap
 * 	protected val HashMap<ArchitectureSubfunction,ArchitectureSubcompnent> sfunc2scomp = newHashMap
 * 	
 * 	protected val HashMap<SoftwareComponent,ArchitectureComponent> sw2comp = newHashMap
 * 	protected val HashMap<ArchitectureSubcompnent,ArchitectureSubcompnent> ssw2scomp = newHashMap
 * 	
 * 	new (StructuralElement element){
 * 		for (allocation : element.functionalAllocations){
 * 			add(allocation.source as ArchitectureSubfunction, allocation.target as ArchitectureSubcompnent)
 * 		}
 * 		for (allocation : element.softwareAllocations){
 * 			add(allocation.source as ArchitectureSubcompnent, allocation.target as ArchitectureSubcompnent)
 * 		}
 * 	}
 * 	
 * 	def add(ArchitectureSubfunction subfunction,ArchitectureSubcompnent subcompnent){
 * 		sfunc2scomp.put(subfunction,subcompnent)
 * 	}
 * 	
 * 	def add(ArchitectureSubcompnent software,ArchitectureSubcompnent hardware){
 * 		ssw2scomp.put(software,hardware)
 * 	}
 * 	
 * 	def get(ArchitectureSubfunction subfunction){
 * 		return sfunc2scomp.get(subfunction)
 * 	}
 * 	
 * 	
 * 	def get(ArchitectureSubcompnent software){
 * 		return sfunc2scomp.get(software)
 * 	}
 */
}
