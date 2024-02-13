package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect

import org.sparx.Repository

import hu.bme.mit.gamma.architecture.model.ModelFactory

import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import hu.bme.mit.gamma.architecture.model.StructuralElement
import java.util.Map
import hu.bme.mit.gamma.architecture.model.ArchitectureFunction
import hu.bme.mit.gamma.architecture.model.ArchitectureComponent
import hu.bme.mit.gamma.architecture.model.ArchitectureInterface
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.SQLUtils
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.XMLUtils
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ElementData
import hu.bme.mit.gamma.architecture.model.FunctionalElement
import hu.bme.mit.gamma.architecture.model.PhysicalElement
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability.ElementTrace

import static extension hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.EAUtils.*
import static extension hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.NamingUtils.*
import hu.bme.mit.gamma.architecture.model.SoftwareComponent
import hu.bme.mit.gamma.architecture.model.MechanicalComponent
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.architecture.model.ArchitecturePort
import hu.bme.mit.gamma.architecture.model.ElectronicComponent
import hu.bme.mit.gamma.architecture.model.FlowProperty
import hu.bme.mit.gamma.architecture.model.ValueType
import hu.bme.mit.gamma.architecture.model.Direction

class EAElementTransformation {
	
	//Repository repository
	ElementTrace trace
	ModelFactory modelFactory
	
	Map<ArchitectureElement, StructuralElement> containingElement;
	//protected static extension EADataLoader eaDataLoader
		
	new(ElementTrace trace, Map<ArchitectureElement, StructuralElement> containingElement) {
		//this.repository=repository
		this.trace=trace
		this.modelFactory=ModelFactory.eINSTANCE
		this.containingElement = containingElement
		//this.eaDataLoader=new EADataLoader(repository,trace)
		//this.eaDataLoader=new EADataLoader(repository)
	}

	def createPackage(String name){
		var pkg = modelFactory.createArchitecturePackage
		pkg.name = name
		return pkg
	}
/* 
	def transformInterface(int elementID) {
		var eaElement = repository.GetElementByID(elementID)
		var architectureInterface = modelFactory.createArchitectureInterface
		architectureInterface.name = eaElement.name
		trace.add(elementID, architectureInterface, eaElement)
		return architectureInterface
	}


	def transformPrimitiveFunction(int elementID) {
		var eaElement = repository.GetElementByID(elementID)
		var primitiveFunction = modelFactory.createArchitectureSubfunction
		primitiveFunction.name = eaElement.name
		trace.add(elementID, primitiveFunction, eaElement)
		val type = getType(primitiveFunction)
		primitiveFunction.type = type as ArchitectureFunction
		val ports = transformPorts(primitiveFunction)
		primitiveFunction.ports.addAll(ports)
		return primitiveFunction
	}

	def ArchitectureSubfunction transformSubfunction(int elementID) {
		var eaElement = repository.GetElementByID(elementID)
		var subfunction = modelFactory.createArchitectureSubfunction
		subfunction.name = eaElement.name
		trace.add(elementID, subfunction, eaElement)
		val type = getType(subfunction)
		subfunction.type = type as ArchitectureFunction
		val ports = transformPorts(subfunction)
		subfunction.ports.addAll(ports)
		val subsubfunctionIDs = getPartIDs(subfunction)
		val subsubfunctions = subsubfunctionIDs.map[f|transformSubfunction(f)]
		subfunction.subfunctions.addAll(subsubfunctions)
		val container = containingElement.get(subfunction)
		subsubfunctions.forEach[f|containingElement.put(f, container)]
		ports.forEach[p|containingElement.put(p, container)]
		return subfunction
	}
	
	def transformFunction(int elementID) {
		var eaElement = repository.GetElementByID(elementID)
		var function = modelFactory.createArchitectureFunction
		function.name = eaElement.name
		trace.add(elementID, function, eaElement)
		val ports = transformPorts(function)
		function.ports.addAll(ports)
		val subfunctionIDs = getPartIDs(function)
		val subfunctions = subfunctionIDs.map[f|transformSubfunction(f)]
		function.subfunctions.addAll(subfunctions)
		val fref=function
		subfunctions.forEach[f|containingElement.put(f, fref)]
		ports.forEach[p|containingElement.put(p, fref)]
		return function
	}

	def transformComponent(int elementID) {
		
	}

	def transformSubcomponent(int elementID) {		
		var eaElement = repository.GetElementByID(elementID)
		var subcomponent = modelFactory.createArchitectureSubcompnent
		subcomponent.name = eaElement.name
		trace.add(elementID, subcomponent, eaElement)
		val type = getType(subcomponent)
		subcomponent.type = type as ArchitectureComponent
		val ports = transformPorts(subcomponent)
		subcomponent.ports.addAll(ports)
		val subfunctionIDs = getPartIDs(subcomponent)
		val subfunctions = subfunctionIDs.map[f|transformSubfunction(f)]
		subcomponent.subfunctions.addAll(subfunctions)
		val containerFunction = containingElement.get(subcomponent)
		subfunctions.forEach[f|containingElement.put(f, containerFunction)]
		return subcomponent
	}

	def transformPort(int elementID) {
		var eaElement = repository.GetElementByID(elementID)
		var architecturePort = modelFactory.createArchitecturePort
		architecturePort.name = eaElement.name
		trace.add(elementID, architecturePort, eaElement)
		val type = getType(architecturePort)
		architecturePort.type = type as ArchitectureInterface
		return architecturePort
	}
*/

	def transformPort(ElementData data) {
		if (trace.contains(data.elementID)){
			return trace.get(data.elementID) as ArchitecturePort
		}
		var architecturePort = modelFactory.createArchitecturePort
		architecturePort.name = data.name.simplifyName
		trace.add(architecturePort, data)
		val type = trace.getPropertyType(data)
		architecturePort.type = type as ArchitectureInterface
		val container = trace.get(data.conainerID) as StructuralElement
		container.ports += architecturePort
		return architecturePort
	}

	def transformPort(ElementData data, boolean conj) {
		if (trace.contains(data.elementID)){
			return trace.get(data.elementID) as ArchitecturePort
		}
		var architecturePort = modelFactory.createArchitecturePort
		architecturePort.name = data.name.simplifyName
		trace.add(architecturePort, data)
		val type = trace.getPropertyType(data)
		architecturePort.type = type as ArchitectureInterface
		architecturePort.conjugated = conj
		val container = trace.get(data.conainerID) as StructuralElement
		container.ports += architecturePort
		return architecturePort
	}

	def transformSubfunction(ElementData data) {
		if (trace.contains(data.elementID)){
			return trace.get(data.elementID) as ArchitectureSubfunction
		}
		var architectureSubfunction = modelFactory.createArchitectureSubfunction
		architectureSubfunction.name = data.name.simplifyName
		trace.add(architectureSubfunction, data)
		val type = trace.getPropertyType(data)
		architectureSubfunction.type = type as ArchitectureFunction
		val container = trace.get(data.conainerID) as FunctionalElement
		container.subfunctions += architectureSubfunction
		return architectureSubfunction
	}

	def transformSubcomponent(ElementData data) {
		if (trace.contains(data.elementID)){
			return trace.get(data.elementID) as ArchitectureSubcompnent
		}
		var architectureSubcomponent = modelFactory.createArchitectureSubcompnent
		architectureSubcomponent.name = data.name.simplifyName
		trace.add(architectureSubcomponent, data)
		val type = trace.getPropertyType(data)
		architectureSubcomponent.type = type as ArchitectureComponent
		val container = trace.get(data.conainerID) as PhysicalElement
		container.subcompnents += architectureSubcomponent
		return architectureSubcomponent
	}

	def transformInterface(ElementData data) {
		if (trace.contains(data.elementID)){
			return trace.get(data.elementID) as ArchitectureInterface
		}
		var architectureInterface = modelFactory.createArchitectureInterface
		architectureInterface.name = data.name.simplifyName
		trace.add(architectureInterface, data)
		return architectureInterface
	}

	def transformValueType(ElementData data) {
		if (trace.contains(data.elementID)){
			return trace.get(data.elementID) as ValueType
		}
		var valueType = modelFactory.createValueType
		valueType.name = data.name.simplifyName
		trace.add(valueType, data)
		return valueType
	}

	def transformFlowProperty(ElementData data) {
		if (trace.contains(data.elementID)){
			return trace.get(data.elementID) as FlowProperty
		}
		var flowProperty = modelFactory.createFlowProperty
		flowProperty.name = data.name.simplifyName
		trace.add(flowProperty, data)
		val type = trace.getPropertyType(data)
		flowProperty.type = type as ValueType
		val container = trace.get(data.conainerID) as ArchitectureInterface
		container.flowproperties += flowProperty
		return flowProperty
	}

	def transformFunction(ElementData data) {
		if (trace.contains(data.elementID)){
			return trace.get(data.elementID) as ArchitectureFunction
		}
		var architectureFunction = modelFactory.createArchitectureFunction
		architectureFunction.name = data.name.simplifyName
		trace.add(architectureFunction, data)
		return architectureFunction
	}

	def transformElectronicComponent(ElementData data) {
		if (trace.contains(data.elementID)){
			return trace.get(data.elementID) as ElectronicComponent
		}
		var architectureComponent = modelFactory.createElectronicComponent
		architectureComponent.name = data.name.simplifyName
		trace.add(architectureComponent, data)
		return architectureComponent
	}

	def transformMechanicalComponent(ElementData data) {
		if (trace.contains(data.elementID)){
			return trace.get(data.elementID) as MechanicalComponent
		}
		var architectureComponent = modelFactory.createMechanicalComponent
		architectureComponent.name = data.name.simplifyName
		trace.add(architectureComponent, data)
		return architectureComponent
	}
	
	def transformSoftwareComponent(ElementData data) {
		if (trace.contains(data.elementID)){
			return trace.get(data.elementID) as SoftwareComponent
		}
		var architectureComponent = modelFactory.createSoftwareComponent
		architectureComponent.name = data.name.simplifyName
		trace.add(architectureComponent, data)
		return architectureComponent
	}
	
	def transformSystemComponent(ElementData data) {
		if (trace.contains(data.elementID)){
			return trace.get(data.elementID) as hu.bme.mit.gamma.architecture.model.System
		}
		var architectureSystemComponent = modelFactory.createSystem
		architectureSystemComponent.name = data.name.simplifyName
		trace.add(architectureSystemComponent, data)
		return architectureSystemComponent
	}

/*

	def transformPorts(StructuralElement element) {
		val elementID = trace.get(element)
		val xml = runQuery(SQLUtils.getPorts(elementID).toString)
		val portIDs = XMLUtils.getElementIDs(xml)
		var ports = portIDs.map[portID|transformPort(portID)]
		return ports
	}

 */

}