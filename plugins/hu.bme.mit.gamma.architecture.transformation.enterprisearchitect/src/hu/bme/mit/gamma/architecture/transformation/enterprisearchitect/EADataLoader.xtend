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
import org.sparx.Services
import hu.bme.mit.gamma.architecture.model.StructuralElement
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.SQLUtils
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.XMLUtils
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability.ElementTrace
import java.util.List

class EADataLoader {

	Repository repository
	//ElementTrace trace
	
	/* 
	new(Repository repository, ElementTrace trace) {
		this.repository=repository
		this.trace=trace
	}
	* */
		
	new(Repository repository) {
		this.repository=repository
	}
	
	def String runQuery(String sqlQuery){
		repository.SQLQuery(sqlQuery)
	}
	
	def loadAllPackages(){
		val xml=runQuery(SQLUtils.allPackages.toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}

	def loadAllXRefData(){
		val xml=runQuery(SQLUtils.allXRef.toString)
		val data = XMLUtils.getXrefData(xml)
		return data
	}

	def loadAllObjectPropertyData(){
		val xml=runQuery(SQLUtils.allObjectProperties.toString)
		val data = XMLUtils.getObjectPropertyData(xml)
		return data
	}

	def loadBlocksFromPackage(int packageID){
		val xml=runQuery(SQLUtils.getAllBlocks(packageID).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadBlocksFromPackage(List<Long> packageIDs){
		val xml=runQuery(SQLUtils.getAllBlocks(packageIDs).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	
	def loadInterfaceBlocksFromPackage(List<Long> packageIDs){
		val xml=runQuery(SQLUtils.getAllInterfaceBlocks(packageIDs).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadAllInterfaceFromPackage(int packageID){
		val xml=runQuery(SQLUtils.getAllInterfaceBlocks(packageID).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadInterfacesFromPackage(List<Long> packageIDs){
		val xml=runQuery(SQLUtils.getAllInterfaces(packageIDs).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadAllValueTypes(List<Long> packageIDs){
		val xml=runQuery(SQLUtils.getAllValueTypes(packageIDs).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadAllEnums(List<Long> packageIDs){
		val xml=runQuery(SQLUtils.getAllEnnums(packageIDs).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadAllValueTypes(){
		val xml=runQuery(SQLUtils.allValueTypes.toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadAllProxyConnectors(){
		val xml=runQuery(SQLUtils.getAllProxyConnectors.toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadAllParts(){
		val xml=runQuery(SQLUtils.getAllParts().toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadAllFlowProperties(){
		val xml=runQuery(SQLUtils.getAllFlowProperties().toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadAllFlowProperties(List<Long> packageIDs){
		val xml=runQuery(SQLUtils.getAllFlowProperties(packageIDs).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadAllPorts(){
		val xml=runQuery(SQLUtils.getAllPorts().toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadAllSignals(){
		val xml=runQuery(SQLUtils.getAllSignals().toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadAllSignals(List<Long> packageIDs){
		val xml=runQuery(SQLUtils.getAllSignals(packageIDs).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadAllFlows(){
		val xml=runQuery(SQLUtils.getAllFlows().toString)
		val data = XMLUtils.getConnectorData(xml)
		return data
	}

	def loadAllAllocation(){
		val xml=runQuery(SQLUtils.getAllAllocations().toString)
		val data = XMLUtils.getConnectorData(xml)
		return data
	}

	def loadAllRealization(){
		val xml=runQuery(SQLUtils.getAllRealisations().toString)
		val data = XMLUtils.getConnectorData(xml)
		return data
	}

	def loadAllGeneralization(){
		val xml=runQuery(SQLUtils.getAllGeneralization().toString)
		val data = XMLUtils.getConnectorData(xml)
		return data
	}

	def loadAllTransitions(){
		val xml=runQuery(SQLUtils.getAllTransitions().toString)
		val data = XMLUtils.getConnectorData(xml)
		return data
	}

	def loadAllConnectors(){
		val xml=runQuery(SQLUtils.getAllConnectors().toString)
		val data = XMLUtils.getConnectorData(xml)
		return data
	}
	
	def loadAllInterfaceConnectors(){
		val xml=runQuery(SQLUtils.getAllInterfaceConnectors().toString)
		val data = XMLUtils.getConnectorData(xml)
		return data
	}
	
	def loadAllTraces(){
		val xml=runQuery(SQLUtils.getAllTraces().toString)
		val data = XMLUtils.getConnectorData(xml)
		return data
	}
	
	def loadAllAttributes(){
		val xml=runQuery(SQLUtils.getAllAttributes2().toString)
		val data = XMLUtils.getAttributeData(xml)
		return data
	}

	def loadAllOperationData(){
		val xml=runQuery(SQLUtils.getAllOperations().toString)
		val data = XMLUtils.getOperationData(xml)
		return data
	}

	def loadAllStateData(List<Long> packageIDs){
		val xml=runQuery(SQLUtils.getAllStates(packageIDs).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}

	def loadAllStatemachineData(List<Long> packageIDs){
		val xml=runQuery(SQLUtils.getAllStatemachines(packageIDs).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}

	def loadAllPseudostateData(List<Long> packageIDs){
		val xml=runQuery(SQLUtils.getAllPseudostates(packageIDs).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}

	def loadAllSyncStateData(List<Long> packageIDs){
		val xml=runQuery(SQLUtils.getAllSyncStates(packageIDs).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}

	def loadAllArtifact(List<Long> packageIDs){
		val xml=runQuery(SQLUtils.getAllArtifacts(packageIDs).toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}

	def loadAllObjectFileData(){
		val xml=runQuery(SQLUtils.getAllObjectFiles().toString)
		val data = XMLUtils.getObjectFileData(xml)
		return data
	}

/* 
	def getPartIDs(StructuralElement element) {
		val elementID = trace.get(element)
		val xml = runQuery(SQLUtils.getParts(elementID).toString)
		val partIDs = XMLUtils.getElementIDs(xml)
		return partIDs
	}

	def getType(ArchitectureElement element) {
		var eaElement = trace.getEAElement(element)
		val typeID = eaElement.GetPropertyType
		val type = trace.get(typeID)
		return type
	}
	*/
}