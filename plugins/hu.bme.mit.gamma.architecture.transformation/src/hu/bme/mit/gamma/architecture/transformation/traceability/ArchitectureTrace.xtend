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

class ArchitectureTrace {

	protected HashMap<ArchitectureElement, NamedElement> arch2gamma = <ArchitectureElement, NamedElement>newHashMap
	protected HashMap<NamedElement, ArchitectureElement> gamma2arch = <NamedElement, ArchitectureElement>newHashMap
	protected HashMap<ArchitectureInterface, AsynchronousStatechartDefinition> interfaceMap = <ArchitectureInterface, AsynchronousStatechartDefinition>newHashMap
	protected HashMap<ValueType, Type> typeMap = <ValueType, Type>newHashMap
	protected HashMap<Type, ValueType> typeMap2 = <Type, ValueType>newHashMap
	protected HashMap<Component, Package> packageMap = <Component, Package>newHashMap
	protected HashMap<Package, String> pathMap = <Package, String>newHashMap

	protected val Package interfacePackage

	new() {
		interfacePackage = InterfaceModelFactory.eINSTANCE.createPackage
		interfacePackage.name = "interfaces"
		pathMap.put(interfacePackage,"interfaces")
	}

	def addComponentPackage(Component component, Package pkg){
		packageMap.put(component,pkg)
		pathMap.put(pkg,"")
	}
	
	def setPackagePath(Package pkg, String path){
		if (path.empty){
			pathMap.put(pkg,path)
		}else{	
			pathMap.put(pkg,"/"+path)
		}
	}
	
	def getPackagePath(Package pkg){
		if(pathMap.containsKey(pkg)){
			return pathMap.get(pkg)
		}else{
			return ""
		}
	}

	def getPackage(Component component){
		return packageMap.get(component)
	}

	def getInterfacePackage() {
		return interfacePackage
	}

	def add(ValueType valueType, Type type) {
		if (contains(type)){
			throw new GammaTransformationException("Value Type already transformed",valueType)
		}
		typeMap.put(valueType, type)
		typeMap2.put(type, valueType)
	}

	def get(ValueType valueType) {
		if (!contains(valueType)){
			throw new GammaTransformationException("Value Type not transformed",valueType)
		}
		return typeMap.get(valueType)
	}

	def get(Type type) {
		if (!contains(type)){
			throw new GammaTransformationException("Type not transformed")
		}
		return typeMap2.get(type)
	}

	def addInterface(ArchitectureInterface architectureInterface,
		AsynchronousStatechartDefinition asynchronousStatechartDefinition) {
		interfaceMap.put(architectureInterface, asynchronousStatechartDefinition)
	}

	def add(ArchitectureElement architectureElement, NamedElement gammaElement) {
		arch2gamma.put(architectureElement, gammaElement)
		gamma2arch.put(gammaElement, architectureElement)
	}

	def get(ArchitectureElement element) {
		if (!contains(element)){
			throw new GammaTransformationException("Element not transformed",element)
		}
		return arch2gamma.get(element)
	}

	def get(NamedElement element) {
		if (!contains(element)){
			throw new GammaTransformationException("Element not traced",element)
		}
		return gamma2arch.get(element)
	}

	def AsynchronousStatechartDefinition getInterfaceComponent(ArchitectureInterface architectureInterface) {
		return interfaceMap.get(architectureInterface)
	}

	def AsynchronousStatechartDefinition getInterfaceComponent(Interface _interface) {
		return interfaceMap.get(get(_interface))
	}
	
	def getPackages(){
		return packageMap.values
	}
	
	def getPrimitiveFunctionPackages(){
		return packageMap.values.filter[p|pathMap.get(p)=="/primitive_functions"]
	}
	
	def getComponentFunctionPackages(){
		return packageMap.values.filter[p|pathMap.get(p)=="/component_functions"]
	}
	
	def getInterfaceComponentPackages(){
		return packageMap.values.filter[p|pathMap.get(p)=="/interface_components"]
	}
	
	def getCommunicationComponentPackages(){
		return packageMap.values.filter[p|pathMap.get(p)=="/communication"]
	}
	
	
	def getSubsystemPackages(){
		return packageMap.values.filter[p|pathMap.get(p)=="/subsystems"]
	}
	
	def getSystemPackages(){
		return packageMap.values.filter[p|pathMap.get(p)=="/systems"]
	}
	
	def contains(ArchitectureElement element){
		return arch2gamma.containsKey(element)
	}
	def contains(NamedElement element){
		return gamma2arch.containsKey(element)
	}
	def contains(Type type){
		return typeMap2.containsKey(type)
	}
	def contains(ValueType type){
		return typeMap.containsKey(type)
	}
	def contains(Package pkg){
		return pathMap.containsKey(pkg)
	}
	
	
}
