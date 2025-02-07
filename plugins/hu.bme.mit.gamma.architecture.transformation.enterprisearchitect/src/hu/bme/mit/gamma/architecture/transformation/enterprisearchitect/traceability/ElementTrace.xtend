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
package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability

import java.util.HashMap
import hu.bme.mit.gamma.architecture.model.ArchitectureInterface
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import org.sparx.Element
import java.util.Map
import java.util.Set
import java.util.HashSet
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ElementData
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException
import hu.bme.mit.gamma.architecture.model.ArchitecturePackage
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.StatemachineData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.FileData
import hu.bme.mit.gamma.architecture.transformation.errors.GammaTransformationException
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ConnectorData
import hu.bme.mit.gamma.architecture.model.ArchitectureRelation
import com.google.common.collect.HashMultimap
import hu.bme.mit.gamma.architecture.model.Allocation
import java.util.List

class ElementTrace {
	
	protected Map<Long,ArchitectureElement> id2element
	protected Map<ArchitectureElement,Long> element2id
	protected Map<Long,Element> id2eaelement
	protected Map<ArchitectureElement,Element> element2eaelement
	protected Map<Long,ElementData> id2elementdata
	protected Map<ArchitectureElement,ElementData> element2elementdata
	protected Map<Long,String> id2guid
	protected Map<String,Long> guid2id
	protected Set<ArchitectureElement> elements
	protected Set<Long> elementIDs
	protected ArchitecturePackage root_pkg
	protected val Map<Long,String> proxyConnMap=newHashMap
	
	protected HashMultimap<String,ArchitectureRelation> connectorMap=HashMultimap.create
	
	public var StatemachineData statemachineData
	public var FileData fileData
	
	public var List<Allocation> allAllocations
	
	def getRootPkg(){
		return root_pkg
	}
	
	def setRootPkg(ArchitecturePackage rootPkg){
		root_pkg=rootPkg
	}
	
	new(){
		id2element = new HashMap<Long,ArchitectureElement>
		element2id = new HashMap<ArchitectureElement,Long>
		id2eaelement = new HashMap<Long,Element>
		element2eaelement = new HashMap<ArchitectureElement,Element>
		id2elementdata = new HashMap<Long,ElementData>
		element2elementdata = new HashMap<ArchitectureElement,ElementData>
		id2guid = new HashMap<Long,String>
		guid2id = new HashMap<String,Long>
		elements = new HashSet<ArchitectureElement>
		elementIDs = new HashSet<Long>
	}
	
	def addProxyConnData(ElementData data){
		proxyConnMap.put(data.elementID,data.classifierGUID)
	}
	
	def getProxied(long elementID){
		if (isProxied(elementID)){
			return getProxiedRelation(elementID)
		}else{
			get(elementID)
		}
	}
	
	def isProxied(long elementID){
		return proxyConnMap.containsKey(elementID)
	}
	
	def getProxiedRelation(long elementID){
		if (!isProxied(elementID)){
			throw new ArchitectureException("Element is not proxied: "+elementID)
		}
		return getConnector(proxyConnMap.get(elementID)).get(0)
	}
	
	def addConnector(long elementID, ArchitectureElement element, Element eaElement){
		id2element.put(elementID,element)
		element2id.put(element,elementID)
		id2eaelement.put(elementID,eaElement)
		element2eaelement.put(element,eaElement)
		val guid=eaElement.GetElementGUID
		id2guid.put(elementID,guid)
		guid2id.put(guid,elementID)
		elements.add(element)
		elementIDs.add(elementID)
	}
	
	def add(ArchitectureElement element, ElementData elementData){
		id2element.put(elementData.elementID,element)
		element2id.put(element,elementData.elementID)
		id2elementdata.put(elementData.elementID,elementData)
		element2elementdata.put(element,elementData)
		val guid=elementData.GUID
		id2guid.put(elementData.elementID,guid)
		guid2id.put(guid,elementData.elementID)
		elements.add(element)
		elementIDs.add(elementData.elementID)
	}
	
	def addConnector(ArchitectureRelation relation, ConnectorData connectorData){
		connectorMap.put(connectorData.GUID,relation)
	}
	
	def getConnector(String GUID){
		check(GUID)
		return connectorMap.get(GUID)
	}

	protected def check(String GUID){
		if(!contains(GUID)){
			throw new ArchitectureException("GUID cannot be found in architecture trace: "+GUID)
		}
	}

	protected def check(long elementID){
		if(!contains(elementID)){
			throw new ArchitectureException("ElementID cannot be found in architecture trace: "+elementID)
		}
	}

	protected def check(ArchitectureElement element){
		if(!contains(element)){
			throw new GammaTransformationException("ArchitectureElement cannot be found in architecture trace: "+element.name+" "+element.class)
		}
	}

	def get(String GUID){
		check(GUID)
		return guid2id.get(GUID)
	}
	
	def get(long elementID){
		check(elementID)
		return id2element.get(elementID)
	}
	
	def getGUID(long elementID){
		check(elementID)
		return id2guid.get(elementID)
	}
	
	def get(ArchitectureElement element){
		check(element)
		return element2id.get(element)
	}
	
	def getEAElement(long elementID){
		return id2eaelement.get(elementID)
	}
	
	def getEAElement(ArchitectureElement element){
		return element2eaelement.get(element)
	}
	
	def contains(Long elementID){
		return id2element.containsKey(elementID)
	}
	
	def contains(ArchitectureElement element){
		return element2id.containsKey(element)
	}
	
	def contains(String guid){
		return guid2id.containsKey(guid) || connectorMap.containsKey(guid)
	}
	
	def getAllElements(){
		return elements.toList
	}
	def getAllElementIDs(){
		return elementIDs.toList
	}
	
	def getContainerID(long elementID){
		return id2elementdata.get(elementID).conainerID
	}
	
	def getContainer(ArchitectureElement element){
		return id2element.get(element2elementdata.get(element).conainerID)
	}
	
	def getPropertyType(Long elementID){
		return id2element.get(element2elementdata.get(elementID).PDATA1)
	}
	
	def getPropertyType(ElementData data){
		val id=get(data.PDATA1)
		return get(id)
	}
	
	
	
}