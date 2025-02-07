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
package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect

import org.sparx.Repository
import hu.bme.mit.gamma.architecture.model.ModelFactory
import java.util.Map
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import hu.bme.mit.gamma.architecture.model.StructuralElement
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.SQLUtils
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ConnectorData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.NamingUtils
import hu.bme.mit.gamma.architecture.model.ArchitectureInterface
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.XMLUtils
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability.ElementTrace
import hu.bme.mit.gamma.architecture.model.SoftwareAllocation
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.architecture.model.SoftwareComponent
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.architecture.model.ArchitectureFunction
import hu.bme.mit.gamma.architecture.model.Allocation
import hu.bme.mit.gamma.architecture.model.InformationFlow
import java.util.logging.Logger
import java.util.logging.Level
import hu.bme.mit.gamma.architecture.model.InterfacingElement
import com.google.common.collect.HashMultimap
import hu.bme.mit.gamma.architecture.model.Connector

class EAConnectorTransformation {

	// Repository repository
	ElementTrace trace
	ModelFactory modelFactory

	protected Logger logger = Logger.getLogger("GammaLogger");

	Map<ArchitectureElement, StructuralElement> containingElement;

	// protected static extension EADataLoader eaDataLoader
	new(Repository repository, ElementTrace trace, Map<ArchitectureElement, StructuralElement> containingElement) {
		// this.repository=repository
		this.trace = trace
		this.modelFactory = ModelFactory.eINSTANCE
		this.containingElement = containingElement
	// this.eaDataLoader=new EADataLoader(repository)
	}

	new(ElementTrace trace, Map<ArchitectureElement, StructuralElement> containingElement) {
		// this.repository=repository
		this.trace = trace
		this.modelFactory = ModelFactory.eINSTANCE
		this.containingElement = containingElement
	// this.eaDataLoader=new EADataLoader(repository)
	}

	def transformFlow(ConnectorData connectorData) {
		if (trace.contains(connectorData.sourceID) && trace.contains(connectorData.targetID)) {
			for (typeStr : connectorData.Type.split(",")) {
				var flow = modelFactory.createInformationFlow
				flow.source = trace.get(connectorData.sourceID)
				flow.target = trace.get(connectorData.targetID)
				if (!typeStr.blank) {
					val typeID = trace.get(typeStr)
					val type = trace.get(typeID)
					flow.type = type as ArchitectureInterface
				}
				flow.name = NamingUtils.simplifyName(connectorData.Name)
				var container = containingElement.get(flow.source)
				container.relations.add(flow)
				trace.addConnector(flow,connectorData)
			}
		}
		return null
	}

	def Allocation transformAllocation(ConnectorData connectorData) {

		if ((trace.contains(connectorData.sourceID) || trace.isProxied(connectorData.sourceID)) && 
			(trace.contains(connectorData.targetID) || trace.isProxied(connectorData.targetID))
		) {
			val source = trace.get(connectorData.sourceID)
			if (source instanceof ArchitectureSubcompnent || source instanceof SoftwareComponent) {
				return transformSoftwareAllocation(connectorData)
			} else if (source instanceof ArchitectureSubfunction || source instanceof ArchitectureFunction) {
				return transformFunctionalAllocation(connectorData)
			} else {
				//logger.log(Level.WARNING, '''Illegal source type for Allocation: «source»''')
				return transformFunctionalAllocation(connectorData)
			}

		}
		return null
	}

	def transformFunctionalAllocation(ConnectorData connectorData) {
		var allocation = modelFactory.createFunctionalAllocation
		allocation.source = trace.getProxied(connectorData.sourceID)
		allocation.target = trace.getProxied(connectorData.targetID)
		allocation.name = NamingUtils.simplifyName(connectorData.Name)
		var container = containingElement.get(allocation.source)
		if (container === null){
			trace.rootPkg.relations+=allocation
		}else{
			container.relations.add(allocation)
		}
		trace.addConnector(allocation,connectorData)
		return allocation
	}

	def transformSoftwareAllocation(ConnectorData connectorData) {
		var allocation = modelFactory.createSoftwareAllocation
		allocation.source = trace.getProxied(connectorData.sourceID)
		allocation.target = trace.getProxied(connectorData.targetID)
		allocation.name = NamingUtils.simplifyName(connectorData.Name)
		var container = containingElement.get(allocation.source)
		if (container === null){
			trace.rootPkg.relations+=allocation
		}else{
			container.relations.add(allocation)
		}
		trace.addConnector(allocation,connectorData)
		return allocation
	}

	def transformSimpleAllocation(ConnectorData connectorData) {
		var allocation = modelFactory.createAllocation
		allocation.source = trace.getProxied(connectorData.sourceID)
		allocation.target = trace.getProxied(connectorData.targetID)
		allocation.name = NamingUtils.simplifyName(connectorData.Name)
		var container = containingElement.get(allocation.source)
		if (container === null){
			trace.rootPkg.relations+=allocation
		}else{
			container.relations.add(allocation)
		}
		trace.addConnector(allocation,connectorData)
		return allocation
	}

	def transformConnector(ConnectorData connectorData) {
		if (trace.contains(connectorData.sourceID) && trace.contains(connectorData.targetID)) {
			var connector = modelFactory.createConnector
			connector.source = trace.get(connectorData.sourceID)
			connector.target = trace.get(connectorData.targetID)
			connector.name = NamingUtils.simplifyName(connectorData.Name)
			var container = containingElement.get(connector.source)
			container.relations.add(connector)
			trace.addConnector(connector,connectorData)
			return connector

		}
		return null
	}

	def transformInterfaceConnector(ConnectorData connectorData) {
		if (trace.contains(connectorData.sourceID) && trace.contains(connectorData.targetID)) {
			var connector = modelFactory.createInterfaceConnector
			connector.source = trace.get(connectorData.sourceID)
			connector.target = trace.get(connectorData.targetID)
			val typeID = trace.get(connectorData.Type)
			val type = trace.get(typeID)
			connector.name = NamingUtils.simplifyName(connectorData.Name)
			connector.type = type as ArchitectureInterface
			var container = containingElement.get(connector.source)
			container.relations.add(connector)
			trace.addConnector(connector,connectorData)
			return connector
		}
		return null
	}

	def transformRealization(ConnectorData connectorData) {
		if (trace.contains(connectorData.sourceID) && trace.contains(connectorData.targetID)) {
			val source = trace.get(connectorData.sourceID) as InterfacingElement
			val target = trace.get(connectorData.targetID) as ArchitectureInterface
			source.providedInterfaces += target
		}
	}

	def transformGeneralization(ConnectorData connectorData) {
		if (trace.contains(connectorData.sourceID) && trace.contains(connectorData.targetID)) {
			val source = trace.get(connectorData.sourceID)
			val target = trace.get(connectorData.targetID)
			if ((source instanceof InterfacingElement) && (target instanceof ArchitectureInterface)) {
				(source as InterfacingElement).providedInterfaces += target as ArchitectureInterface
			}
		}
	}
	
	def transformFlowAllocations(HashMultimap<String,String> map){
		val allocations=<Allocation>newLinkedList
		for (connectorGUID:map.keys){
			if (trace.contains(connectorGUID)){
				val connector=trace.getConnector(connectorGUID).get(0)
				for (flowGUID:map.get(connectorGUID)){
					for (flow : trace.getConnector(flowGUID)){
						val allocation=modelFactory.createAllocation
						allocation.source=flow
						allocation.target=connector
						allocations.add(allocation)
					}
				}
			}
		}
		return allocations
	}

}
