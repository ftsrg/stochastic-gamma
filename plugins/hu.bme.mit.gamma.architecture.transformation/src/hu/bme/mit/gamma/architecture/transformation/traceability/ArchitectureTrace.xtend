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
import hu.bme.mit.gamma.expression.model.NamedElement
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import hu.bme.mit.gamma.architecture.model.ArchitectureInterface
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.architecture.model.ValueType
import hu.bme.mit.gamma.expression.model.Type
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.statechart.interface_.Package
import hu.bme.mit.gamma.statechart.interface_.Component
import java.io.File
import hu.bme.mit.gamma.architecture.transformation.errors.GammaTransformationException
import hu.bme.mit.gamma.architecture.model.ArchitecturePort
import java.util.Set
import hu.bme.mit.gamma.statechart.interface_.Port
import com.google.common.collect.Multimap
import com.google.common.collect.ArrayListMultimap
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException
import hu.bme.mit.gamma.architecture.model.ArchitectureComponent
import hu.bme.mit.gamma.architecture.transformation.builder.InterfaceComponentBuilder
import java.util.Map
import hu.bme.mit.gamma.architecture.model.Connector

class ArchitectureTrace {

	protected HashMap<ArchitectureElement, NamedElement> arch2gamma = <ArchitectureElement, NamedElement>newHashMap
	protected HashMap<NamedElement, ArchitectureElement> gamma2arch = <NamedElement, ArchitectureElement>newHashMap
	protected HashMap<ArchitectureInterface, AsynchronousStatechartDefinition> interfaceMap = <ArchitectureInterface, AsynchronousStatechartDefinition>newHashMap
	protected HashMap<ValueType, Type> typeMap = <ValueType, Type>newHashMap
	protected HashMap<Type, ValueType> typeMap2 = <Type, ValueType>newHashMap
	protected HashMap<Component, Package> packageMap = <Component, Package>newHashMap
	protected HashMap<Package, String> pathMap = <Package, String>newHashMap
	public final Multimap<ArchitecturePort, Port> phyPortMap = ArrayListMultimap.create();
	protected val Map<Component, InterfaceComponentBuilder> ifCompBulderMap = newHashMap
	protected val Map<Connector, InterfaceComponentBuilder> ifCompBulderMap2 = newHashMap

	protected val hwErrorSctMap = <ArchitectureComponent, AsynchronousStatechartDefinition>newHashMap

	protected val Package interfacePackage

	new() {
		interfacePackage = InterfaceModelFactory.eINSTANCE.createPackage
		interfacePackage.name = "interfaces"
		pathMap.put(interfacePackage, "interfaces")
	}

	def add(Component component, Connector connector, InterfaceComponentBuilder builder) {
		ifCompBulderMap.put(component, builder)
		ifCompBulderMap2.put(connector, builder)
	}

	def getInterfaceComponentBuilder(Component component) {
		if (ifCompBulderMap.containsKey(component)) {
			return ifCompBulderMap.get(component)
		} else {
			throw new GammaTransformationException("No InterfaceComponentBuilder is found", component)
		}
	}

	def getInterfaceComponentBuilder(Connector connector) {
		if (ifCompBulderMap2.containsKey(connector)) {
			return ifCompBulderMap2.get(connector)
		} else {
			throw new GammaTransformationException("No InterfaceComponentBuilder is found", connector)
		}
	}

	def addComponentPackage(Component component, Package pkg) {
		if (packageMap.keySet.contains(component)) {
			throw new GammaTransformationException("Component is already packaged : " + component.name, component)
		}
		packageMap.put(component, pkg)
		pathMap.put(pkg, "")
	}

	def setPackagePath(Package pkg, String path) {
		if (path.empty) {
			pathMap.put(pkg, path)
		} else {
			pathMap.put(pkg, "/" + path)
		}
	}

	def getPackagePath(Package pkg) {
		if (pathMap.containsKey(pkg)) {
			return pathMap.get(pkg)
		} else {
			return ""
		}
	}

	def getPackage(Component component) {
		return packageMap.get(component)
	}

	def getInterfacePackage() {
		return interfacePackage
	}

	def add(ValueType valueType, Type type) {
		if (contains(type)) {
			throw new GammaTransformationException("Value Type already transformed", valueType)
		}
		typeMap.put(valueType, type)
		typeMap2.put(type, valueType)
	}

	def get(ValueType valueType) {
		if (!contains(valueType)) {
			throw new GammaTransformationException("Value Type not transformed", valueType)
		}
		return typeMap.get(valueType)
	}

	def get(Type type) {
		if (!contains(type)) {
			throw new GammaTransformationException("Type not transformed")
		}
		return typeMap2.get(type)
	}

	def addInterface(ArchitectureInterface architectureInterface,
		AsynchronousStatechartDefinition asynchronousStatechartDefinition) {
		interfaceMap.put(architectureInterface, asynchronousStatechartDefinition)
	}

	def addHWErrorSct(ArchitectureComponent component, AsynchronousStatechartDefinition statechart) {
		hwErrorSctMap.put(component, statechart)
	}

	def getHWErrorSct(ArchitectureComponent component) {
		if (!hwErrorSctMap.containsKey(component)) {
			throw new GammaTransformationException("Component has no error statechart", component)
		}
		return hwErrorSctMap.get(component)
	}

	def containsHWErrorSct(ArchitectureComponent component) {
		return hwErrorSctMap.containsKey(component);
	}

	def containsHWErrorSct(ArchitectureElement component) {
		if (component instanceof ArchitectureComponent) {
			return hwErrorSctMap.containsKey(component);
		} else {
			return false;
		}
	}

	def add(ArchitectureElement architectureElement, NamedElement gammaElement) {
		arch2gamma.put(architectureElement, gammaElement)
		gamma2arch.put(gammaElement, architectureElement)
	}

	def get(ArchitectureElement element) {
		if (!contains(element)) {
			throw new GammaTransformationException("Element not transformed", element)
		}
		return arch2gamma.get(element)
	}

	def get(NamedElement element) {
		if (!contains(element)) {
			if (hwErrorSctMap.containsValue(element)){
				for (key:hwErrorSctMap.keySet){
					if (hwErrorSctMap.get(key) === element){
						return key as ArchitectureElement;
					}
				}
			}
			throw new GammaTransformationException('''Element "«element.name»" not traced''', element)
		}
		return gamma2arch.get(element)
	}

	def AsynchronousStatechartDefinition getInterfaceComponent(ArchitectureInterface architectureInterface) {
		return interfaceMap.get(architectureInterface)
	}

	def AsynchronousStatechartDefinition getInterfaceComponent(Interface _interface) {
		return interfaceMap.get(get(_interface))
	}

	def isAutogenInterfaceComponent(AsynchronousStatechartDefinition comp) {
		return interfaceMap.containsValue(comp)
	}

	def getPackages() {
		return packageMap.values.toSet
	}

	def getPrimitiveFunctionPackages() {
		return packageMap.values.filter[p|pathMap.get(p) == "/primitive_functions"].toSet
	}

	def getComponentFunctionPackages() {
		return packageMap.values.filter[p|pathMap.get(p) == "/component_functions"].toSet
	}

	def getInterfaceComponentPackages() {
		return packageMap.values.filter[p|pathMap.get(p) == "/interface_components"].toSet
	}

	def getCommunicationComponentPackages() {
		return packageMap.values.filter[p|pathMap.get(p) == "/communication"].toSet
	}

	def getElectronicComponentPackages() {
		return packageMap.values.filter[p|pathMap.get(p) == "/electronic_components"].toSet
	}

	def getSubsystemHardwarePackages() {
		return packageMap.values.filter[p|pathMap.get(p) == "/subsystem_hardware"].toSet
	}

	def getHWErrorStatechartPackages() {
		return packageMap.values.filter[p|pathMap.get(p) == "/hw_error_sct"].toSet
	}

	def getSubsystemPackages() {
		return packageMap.values.filter[p|pathMap.get(p) == "/subsystems"].toSet
	}

	def getSystemPackages() {
		return packageMap.values.filter[p|pathMap.get(p) == "/systems"].toSet
	}

	def contains(ArchitectureElement element) {
		return arch2gamma.containsKey(element)
	}

	def contains(NamedElement element) {
		return gamma2arch.containsKey(element)
	}

	def contains(Type type) {
		return typeMap2.containsKey(type)
	}

	def contains(ValueType type) {
		return typeMap.containsKey(type)
	}

	def contains(Package pkg) {
		return pathMap.containsKey(pkg)
	}

	def getPhyPorts(ArchitecturePort port) {
		if (port.eContainer instanceof ArchitectureSubcompnent) {
			val realPort = (port.eContainer as ArchitectureSubcompnent).type.ports.filter[p|p.name == port.name].filter [ p |
				p.type === port.type
			].filter[p|p.conjugated == port.conjugated]
			if (realPort.size != 1) {
				throw new ArchitectureException(
					"Inconsistent physical subcomponent-component type ports, " + realPort.size + " matches", port)
			}
			return phyPortMap.get(realPort.get(0))
		} else {
			return phyPortMap.get(port)
		}
	}

}
