package hu.bme.mit.gamma.architecture.transformation.traceability

import java.util.HashMap
import hu.bme.mit.gamma.expression.model.NamedElement
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import hu.bme.mit.gamma.architecture.model.ArchitectureInterface
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.architecture.model.ValueType
import hu.bme.mit.gamma.expression.model.Type

class ArchitectureTrace {
	
	protected HashMap<ArchitectureElement, NamedElement> arch2gamma= <ArchitectureElement, NamedElement>newHashMap
	protected HashMap<NamedElement, ArchitectureElement> gamma2arch= <NamedElement, ArchitectureElement>newHashMap
	protected HashMap<ArchitectureInterface, AsynchronousStatechartDefinition> interfaceMap= <ArchitectureInterface, AsynchronousStatechartDefinition>newHashMap
	protected HashMap<ValueType, Type> typeMap= <ValueType, Type>newHashMap
	protected HashMap<Type, ValueType> typeMap2= <Type, ValueType>newHashMap
	
	new(){
		
	}
	
	def add(ValueType valueType, Type type){
		typeMap.put(valueType,type)
		typeMap2.put(type,valueType)
	}
	
	def get(ValueType valueType){
		return typeMap.get(valueType)
	}
	
	def get(Type type){
		return typeMap2.get(type)
	}
	
	def addInterface(ArchitectureInterface architectureInterface, AsynchronousStatechartDefinition asynchronousStatechartDefinition){
		interfaceMap.put(architectureInterface,asynchronousStatechartDefinition)
	}
	
	def add(ArchitectureElement architectureElement, NamedElement gammaElement){
		arch2gamma.put(architectureElement,gammaElement)
		gamma2arch.put(gammaElement,architectureElement)
	}
	
	def get(ArchitectureElement element){
		return arch2gamma.get(element)
	}
	
	def get(NamedElement element){
		return gamma2arch.get(element)
	}
	
	def AsynchronousStatechartDefinition getInterfaceComponent(ArchitectureInterface architectureInterface){
		return interfaceMap.get(architectureInterface)
	}
	
	def AsynchronousStatechartDefinition getInterfaceComponent(Interface _interface){
		return interfaceMap.get(get(_interface))
	}
	
}