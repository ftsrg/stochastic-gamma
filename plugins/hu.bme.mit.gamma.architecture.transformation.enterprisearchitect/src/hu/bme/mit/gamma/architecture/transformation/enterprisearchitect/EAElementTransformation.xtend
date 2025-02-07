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
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.statechart.interface_.EventDirection
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.OperationData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.AttributeData
import hu.bme.mit.gamma.architecture.model.EnumValueType
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.architecture.model.ArchitecturePackage
import hu.bme.mit.gamma.architecture.model.PhysicalInterface

class EAElementTransformation {

	ElementTrace trace
	ModelFactory modelFactory

	val ifModelFactory = InterfaceModelFactory.eINSTANCE
	val exprModelFactory = ExpressionModelFactory.eINSTANCE
	Map<ArchitectureElement, StructuralElement> containingElement;

	new(ElementTrace trace, Map<ArchitectureElement, StructuralElement> containingElement) {
		this.trace = trace
		this.modelFactory = ModelFactory.eINSTANCE
		this.containingElement = containingElement
	}

	def createPackage(String name) {
		var pkg = modelFactory.createArchitecturePackage
		pkg.name = name
		return pkg
	}

	def transformPort(ElementData data) {
		if (trace.contains(data.elementID)) {
			return trace.get(data.elementID) as ArchitecturePort
		}
		var architecturePort = modelFactory.createArchitecturePort
		architecturePort.name = data.name.simplifyName
		trace.add(architecturePort, data)
		val type = trace.getPropertyType(data)
		architecturePort.type = type as ArchitectureInterface
		val container = trace.get(data.conainerID) 
		container.ports += architecturePort
		return architecturePort
	}

	def transformPort(ElementData data, boolean conj) {
		if (trace.contains(data.elementID)) {
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
		if (trace.contains(data.elementID)) {
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
		if (trace.contains(data.elementID)) {
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
		if (trace.contains(data.elementID)) {
			return trace.get(data.elementID) as ArchitectureInterface
		}
		var architectureInterface = modelFactory.createArchitectureInterface
		architectureInterface.name = data.name.simplifyName
		trace.add(architectureInterface, data)
		return architectureInterface
	}
	
	def transformPhysicalInterface(ElementData data) {
		if (trace.contains(data.elementID)) {
			return trace.get(data.elementID) as PhysicalInterface
		}
		var architectureInterface = modelFactory.createPhysicalInterface
		architectureInterface.name = data.name.simplifyName
		trace.add(architectureInterface, data)
		return architectureInterface
	}

	def transformValueType(ElementData data) {
		if (trace.contains(data.elementID)) {
			return trace.get(data.elementID) as ValueType
		}
		var valueType = modelFactory.createValueType
		valueType.name = data.name.simplifyName
		trace.add(valueType, data)
		return valueType
	}

	def transformEnum(ElementData data) {
		if (trace.contains(data.elementID)) {
			return trace.get(data.elementID) as EnumValueType 
		}
		var valueType = modelFactory.createEnumValueType
		valueType.name = data.name.simplifyName
		trace.add(valueType, data)
		return valueType
	}

	def transformFlowProperty(ElementData data) {
		if (trace.contains(data.elementID)) {
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
		if (trace.contains(data.elementID)) {
			return trace.get(data.elementID) as ArchitectureFunction
		}
		var architectureFunction = modelFactory.createArchitectureFunction
		architectureFunction.name = data.name.simplifyName
		trace.add(architectureFunction, data)
		return architectureFunction
	}

	def transformElectronicComponent(ElementData data) {
		if (trace.contains(data.elementID)) {
			return trace.get(data.elementID) as ElectronicComponent
		}
		var architectureComponent = modelFactory.createElectronicComponent
		architectureComponent.name = data.name.simplifyName
		trace.add(architectureComponent, data)
		return architectureComponent
	}

	def transformMechanicalComponent(ElementData data) {
		if (trace.contains(data.elementID)) {
			return trace.get(data.elementID) as MechanicalComponent
		}
		var architectureComponent = modelFactory.createMechanicalComponent
		architectureComponent.name = data.name.simplifyName
		trace.add(architectureComponent, data)
		return architectureComponent
	}

	def transformSoftwareComponent(ElementData data) {
		if (trace.contains(data.elementID)) {
			return trace.get(data.elementID) as SoftwareComponent
		}
		var architectureComponent = modelFactory.createSoftwareComponent
		architectureComponent.name = data.name.simplifyName
		trace.add(architectureComponent, data)
		return architectureComponent
	}

	def transformSystemComponent(ElementData data) {
		if (trace.contains(data.elementID)) {
			return trace.get(data.elementID) as hu.bme.mit.gamma.architecture.model.System
		}
		var architectureSystemComponent = modelFactory.createSystem
		architectureSystemComponent.name = data.name.simplifyName
		trace.add(architectureSystemComponent, data)
		return architectureSystemComponent
	}

	def transformSignal(ElementData data) {
		if (trace.contains(data.elementID)) {
			return trace.get(data.elementID) as hu.bme.mit.gamma.architecture.model.System
		}
		var architectureInterface = modelFactory.createArchitectureInterface
		architectureInterface.name = data.name.simplifyName + "SignalInterface"
		val event = ifModelFactory.createEvent
		event.name = data.name.simplifyName
		val eventDecl = ifModelFactory.createEventDeclaration
		eventDecl.event = event
		eventDecl.direction = EventDirection.OUT
		architectureInterface.events.add(eventDecl)
		trace.add(architectureInterface, data)
		return architectureInterface
	}

	def transformPhysicalSignal(ElementData data) {
		if (trace.contains(data.elementID)) {
			return trace.get(data.elementID) as hu.bme.mit.gamma.architecture.model.System
		}
		var architectureInterface = modelFactory.createPhysicalInterface
		architectureInterface.name = data.name.simplifyName + "SignalInterface"
		val event = ifModelFactory.createEvent
		event.name = data.name.simplifyName
		val eventDecl = ifModelFactory.createEventDeclaration
		eventDecl.event = event
		eventDecl.direction = EventDirection.OUT
		architectureInterface.events.add(eventDecl)
		trace.add(architectureInterface, data)
		return architectureInterface
	}

	def transformReception(OperationData data) {
		val elementGUID = data.style_ex.replace("Reception=1;SignalGUID=", "").replace(";", "")
		if (trace.contains(elementGUID) && trace.contains(data.element_id)) {
			val signalInterface = trace.get(trace.get(elementGUID)) as ArchitectureInterface
			val architectureInterface = trace.get(data.element_id) as ArchitectureInterface
			architectureInterface.parents += signalInterface
		}
	}

	def transformAttribute(AttributeData data) {
		if (trace.contains(data.element_id)) {
			val container = trace.get(data.element_id)
			if (container instanceof ArchitectureInterface) {
				var flowProperty = modelFactory.createFlowProperty
				flowProperty.name = data.name.simplifyName
				var ValueType type = null 
				if (data.type_id<=0){
					//throw new UnsupportedOperationException("UML primitive type library not supported yet for attributes"+data.name)
					type=modelFactory.createValueType
					type.name=data.type.simplifyName
					trace.rootPkg.architectureelement+=type
				}else{
					type=trace.get(data.type_id) as ValueType
				}
				flowProperty.type = type
				flowProperty.direction = Direction.OUT
				container.flowproperties += flowProperty
			} else if (container instanceof EnumValueType) {
				var literal = modelFactory.createValueProperty
				literal.name = data.name.simplifyName
				container.valueproperties += literal
			} else if (container instanceof ValueType) {
				var property = modelFactory.createValueProperty
				property.name = data.name.simplifyName
				var ValueType type = null 
				if (data.type_id==-1){
					//throw new UnsupportedOperationException("UML primitive type library not supported yet for attributes"+data.name)
					type=modelFactory.createValueType
					type.name=data.type.simplifyName
				}else{
					type=trace.get(data.type_id) as ValueType
				}
				property.type = type
				container.valueproperties += property
			}
		}

	}

}
