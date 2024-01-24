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
	ElementTrace trace
	
		
	new(Repository repository, ElementTrace trace) {
		this.repository=repository
		this.trace=trace
	}
	
	def String runQuery(String sqlQuery){
		repository.SQLQuery(sqlQuery)
	}
	
	def loadAllPackages(){
		val xml=runQuery(SQLUtils.allPackages.toString)
		val data = XMLUtils.getElementData(xml)
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
	
	def loadAllParts(){
		val xml=runQuery(SQLUtils.getAllParts().toString)
		val data = XMLUtils.getElementData(xml)
		return data
	}
	
	def loadAllPorts(){
		val xml=runQuery(SQLUtils.getAllPorts().toString)
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
	
}