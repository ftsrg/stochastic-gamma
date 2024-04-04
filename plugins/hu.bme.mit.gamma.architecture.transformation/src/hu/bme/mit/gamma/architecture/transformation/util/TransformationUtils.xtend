package hu.bme.mit.gamma.architecture.transformation.util

import hu.bme.mit.gamma.architecture.model.ArchitectureFunction
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.architecture.model.SoftwareComponent
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import hu.bme.mit.gamma.architecture.model.ArchitectureRelations
import hu.bme.mit.gamma.architecture.model.StructuralElement
import hu.bme.mit.gamma.architecture.model.InterfaceConnector
import hu.bme.mit.gamma.architecture.model.InformationFlow
import hu.bme.mit.gamma.architecture.model.FunctionalAllocation
import hu.bme.mit.gamma.architecture.model.SoftwareAllocation
import hu.bme.mit.gamma.architecture.model.ArchitecturePort
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponent
import hu.bme.mit.gamma.statechart.interface_.RealizationMode
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.architecture.model.ArchitecturePackage
import hu.bme.mit.gamma.architecture.model.ArchitectureInterface
import hu.bme.mit.gamma.architecture.model.ValueType
import hu.bme.mit.gamma.architecture.model.System
import hu.bme.mit.gamma.architecture.model.Connector
import hu.bme.mit.gamma.architecture.model.Allocation
import hu.bme.mit.gamma.architecture.model.ElectronicComponent

class TransformationUtils {

	static def getPhysicalComponent(ArchitectureFunction function) {
		val container = function.eContainer
		if (container instanceof ArchitectureSubcompnent) {
			if (container.isSoftware) {
			}
		}
	}

	static def isSoftware(ArchitectureSubcompnent subcompnent) {
		return subcompnent.type instanceof SoftwareComponent
	}

	static def getGammaName(ArchitectureElement element) {
		return element.name.gammaName
	}
	
	static def getGammaName(ArchitectureSubfunction subfunction) {
		if (subfunction.name == "" || subfunction.name===null){
			return "inst"+subfunction.type.gammaName
		}
		return "inst"+subfunction.name.gammaName
	}
	
	static def getGammaName(ArchitectureSubcompnent subcompnent) {
		if (subcompnent.name.isBlank){
			return subcompnent.type.gammaName
		}
		return subcompnent.name.gammaName+"_"+subcompnent.type.gammaName
	}
	
	static def getGammaName(ArchitectureRelations relation) {
		return relation.name.gammaName
	}
	
	static def getGammaName(InterfaceConnector connector) {
		return connector.name.gammaName+"_"+connector.type.gammaName
	}
	
	static def getGammaName(ArchitecturePort port) {
		if (port.conjugated){
			return port.name.gammaName+port.type.gammaName+"In"
		}else{
			return port.name.gammaName+port.type.gammaName+"Out"
		}
	}
	
	static def getFailureInterfaceName(ArchitectureInterface architectureInterface){
		return architectureInterface.gammaName
	}
	
	static def outPortName(InformationFlow flow){
		return flow.gammaName+flow.type.gammaName+"Out"
	}
	
	static def inPortName(InformationFlow flow){
		return flow.gammaName+flow.type.gammaName+"In"
	}
	
	static def getGammaName(String archName) {
		var name = archName
		if (name === null || name.empty || name.blank ) {
			return ""
		}
		name = name.replaceAll('''[.,;:\-\(\)\s<>\{\}]''', "_")
		while (name.contains("__")) {
			name = name.replace("__", "_")
		}
		if (name.charAt(0) <= '9' && name.charAt(0) >= '0') {
			name = "_" + name
		}
		return name
	}

	static def getInterfaces(ArchitecturePackage pkg){
		return pkg.architectureelement.filter[e | e instanceof ArchitectureInterface].map[e|e as ArchitectureInterface].toList
	}

	static def getValueTypes(ArchitecturePackage pkg){
		return pkg.architectureelement.filter[e | e instanceof ValueType].map[e|e as ValueType].toList
	}

	static def getFunctions(ArchitecturePackage pkg){
		return pkg.architectureelement.filter[e | e instanceof ArchitectureFunction].map[e|e as ArchitectureFunction].toList
	}


	static def getSystem(ArchitecturePackage pkg){
		return pkg.architectureelement.filter[e | e instanceof System].map[e|e as System].filter[sys|(!sys.relations.empty) && (!sys.subcompnents.empty) ].toList
	}

	static def getPrimitiveHardwareComponents(ArchitecturePackage pkg){
		return pkg.architectureelement.filter[e | e instanceof ElectronicComponent].map[e|e as ElectronicComponent].filter[comp|(comp.subcompnents.empty) && (!comp.subfunctions.empty) ].toList
	}

	static def getComponentFunctions(ArchitecturePackage pkg){
		return pkg.architectureelement.filter[e | e instanceof ArchitectureFunction].map[e|e as ArchitectureFunction].filter[f|!f.subfunctions.empty].toList
	}

	static def getPrimitiveFunctions(ArchitecturePackage pkg){
		return pkg.architectureelement.filter[e | e instanceof ArchitectureFunction].map[e|e as ArchitectureFunction].filter[f|f.subfunctions.empty].toList
	}

	static def getConnectors(StructuralElement element){
		return element.relations.filter[r | r instanceof Connector].map[r|r as Connector].toList
	}

	static def getInterfaceConnectors(StructuralElement element){
		return element.relations.filter[r | r instanceof InterfaceConnector].map[r|r as InterfaceConnector].toList
	}

	static def getInformationFlows(StructuralElement element){
		return element.relations.filter[r | r instanceof InformationFlow].map[r|r as InformationFlow].toList
	}

	static def getFunctionalAllocations(StructuralElement element){
		return element.relations.filter[r | r instanceof FunctionalAllocation].map[r|r as FunctionalAllocation].toList
	}
	
	static def getSoftwareAllocations(StructuralElement element){
		return element.relations.filter[r | r instanceof SoftwareAllocation].map[r|r as SoftwareAllocation].toList
	}

	static def getAllocations(StructuralElement element){
		return element.relations.filter[r | r instanceof Allocation].map[r|r as Allocation].toList
	}

	static def providedPorts(AsynchronousComponent component){
		return component.ports.filter[p | p.interfaceRealization.realizationMode == RealizationMode.PROVIDED].toList
	}

	static def requiredPorts(AsynchronousComponent component){
		return component.ports.filter[p | p.interfaceRealization.realizationMode == RealizationMode.REQUIRED].toList
	}


}
