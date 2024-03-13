package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect

import org.sparx.Repository
import org.sparx.Services
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.EAUtils
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.XMLUtils
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.SQLUtils
import hu.bme.mit.gamma.architecture.model.ModelFactory

import static extension hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.EAUtils.*
import hu.bme.mit.gamma.architecture.model.ArchitectureInterface
import hu.bme.mit.gamma.architecture.model.StructuralElement
import hu.bme.mit.gamma.architecture.model.ArchitectureFunction
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import java.util.Map
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.architecture.model.ArchitectureComponent
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ConnectorData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.NamingUtils
import java.util.List
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability.ElementTrace
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ElementData
import hu.bme.mit.gamma.architecture.model.PhysicalElement
import java.util.HashSet
import java.util.logging.Logger
import java.util.logging.Level
import hu.bme.mit.gamma.architecture.model.Allocation
import hu.bme.mit.gamma.architecture.model.InformationFlow
import hu.bme.mit.gamma.architecture.model.Connector
import hu.bme.mit.gamma.architecture.model.ArchitecturePort

import static extension hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.XRefUtils.*
import static extension hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.ObjPropUtils.*
import hu.bme.mit.gamma.architecture.model.FlowProperty
import hu.bme.mit.gamma.architecture.model.System
import hu.bme.mit.gamma.architecture.model.ArchitecturePackage
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.StatemachineData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.FileData

class EnterpriseArchitectTransformation {

	Repository repository
	ElementTrace trace
	ModelFactory modelFactory

	Map<ArchitectureElement, StructuralElement> containingElement
	Map<ElementData, ElementData> containingElement2

	val String functionPackageGUID
	val String electricalPackageGUID
	val String mechanicalPackageGUID
	val String softwarePackageGUID
	val String systemPackageGUID

	protected static extension EAElementTransformation elementTransformation
	protected static extension EAConnectorTransformation connectorTransformation

	protected Logger logger = Logger.getLogger("GammaLogger");

	protected static extension EADataLoader eaDataLoader

	val ArchitecturePackage root_pkg 

	new(
		String functionPackageGUID,
		String electricalPackageGUID,
		String mechanicalPackageGUID,
		String softwarePackageGUID,
		String systemPackageGUID
	) {
		val pid = EAUtils.searchPID
		this.functionPackageGUID = functionPackageGUID
		this.electricalPackageGUID = electricalPackageGUID
		this.mechanicalPackageGUID = mechanicalPackageGUID
		this.softwarePackageGUID = softwarePackageGUID
		this.systemPackageGUID = systemPackageGUID

		repository = Services.GetRepository(pid as int)
		trace = new ElementTrace()
		modelFactory = ModelFactory.eINSTANCE
		containingElement = newHashMap
		containingElement2 = newHashMap
		// eaDataLoader = new EADataLoader(repository, trace)
		eaDataLoader = new EADataLoader(repository)
		elementTransformation = new EAElementTransformation(trace, containingElement)
		connectorTransformation = new EAConnectorTransformation(repository, trace, containingElement)
		root_pkg = createPackage("ArchitectureModel")
	}

	new(
		String functionPackageGUID,
		String systemPackageGUID
	) {
		val pid = EAUtils.searchPID
		this.functionPackageGUID = functionPackageGUID
		this.electricalPackageGUID = ""
		this.mechanicalPackageGUID = ""
		this.softwarePackageGUID = ""
		this.systemPackageGUID = systemPackageGUID

		repository = Services.GetRepository(pid as int)
		trace = new ElementTrace()
		modelFactory = ModelFactory.eINSTANCE
		containingElement = newHashMap
		containingElement2 = newHashMap
		// eaDataLoader = new EADataLoader(repository, trace)
		eaDataLoader = new EADataLoader(repository)
		elementTransformation = new EAElementTransformation(trace, containingElement)
		connectorTransformation = new EAConnectorTransformation(repository, trace, containingElement)
		root_pkg = createPackage("ArchitectureModel")

		trace.rootPkg = root_pkg
	}

	def showElement(ArchitectureElement element) {
		val elementObj = repository.GetElementByID(trace.get(element).intValue)
		repository.ShowInProjectView(elementObj)
	}

	def getInterfaces(int packageID) {
		val xml = runQuery(SQLUtils.getPkgInterfaceBlocks(packageID).toString)
// val interfaceIDs
	}

	def transformInterfaces(List<Long> packageIDs) {

		logger.log(Level.INFO, "Transforming functional interface models")
	
		transformValueTypes(packageIDs)
		
		val interfaceBlockData = eaDataLoader.loadInterfaceBlocksFromPackage(packageIDs)
		val interfaceData = eaDataLoader.loadInterfacesFromPackage(packageIDs)
		val signalData = eaDataLoader.loadInterfacesFromPackage(packageIDs)

		root_pkg.architectureelement += signalData.map[d|d.transformSignal].toList
		root_pkg.architectureelement += interfaceData.map[d|d.transformInterface].toList
		root_pkg.architectureelement += interfaceBlockData.map[d|d.transformInterface].toList
		
		transformOperations
		
		transformFlowProperties(packageIDs)
		
		transformAttributes
	}
	
	
	
	def transformFlowProperties(List<Long> packageIDs){
		val allFlowPropertiesData = eaDataLoader.loadAllFlowProperties(packageIDs)
		val allFlowProperties = allFlowPropertiesData.map[d|d.transformFlowProperty].toList
		val objectPropertyData = eaDataLoader.loadAllObjectPropertyData
		val directionMap = objectPropertyData.createDirectionMap
		for (flowProp : allFlowProperties) {
			flowProp.direction = directionMap.get(trace.get(flowProp))
		}
	}

	def transformValueTypes(List<Long> packageIDs) {
		val allValueTypeData = eaDataLoader.loadAllValueTypes(packageIDs)
		val allValueTypes = allValueTypeData.map[d|d.transformValueType].toList
		root_pkg.architectureelement += allValueTypes
		
		val allEnumData = eaDataLoader.loadAllEnums(packageIDs)
		val allEnums = allEnumData.map[d|d.transformEnum].toList
		root_pkg.architectureelement += allEnums
		
	}

	def transformOperations() {
		val operationData=eaDataLoader.loadAllOperationData
		for (data:operationData){
			if(data.style_ex.contains("Signal")){
				data.transformReception
			}
		}
	}

	def transformAttributes() {
		val attributeData = eaDataLoader.loadAllAttributes
		for (data : attributeData) {
			data.transformAttribute
		}
	}

	def execute2() {

		logger.log(Level.INFO, "Loading packages")

		val packageData = eaDataLoader.loadAllPackages

		logger.log(Level.INFO, "Transforming functional models")

		val functionalPackageData = packageData.getContainedPackage(functionPackageGUID)

		transformInterfaces(functionalPackageData)

		val functionData = eaDataLoader.loadBlocksFromPackage(functionalPackageData)
		// val functionInterfaceData = eaDataLoader.loadInterfaceBlocksFromPackage(functionalPackageData)
		val functions = functionData.map[d|d.transformFunction]
		// val functionInterfaces = functionInterfaceData.map[d|d.transformInterface]
		root_pkg.architectureelement += functions
		// root_pkg.architectureelement += functionInterfaces
		// logger.log(Level.INFO, "Transforming system models")
		val systemPackageData = packageData.getContainedPackage(systemPackageGUID)
		val systemData = eaDataLoader.loadBlocksFromPackage(systemPackageData)
		val systemInterfaceData = eaDataLoader.loadInterfaceBlocksFromPackage(systemPackageData)
		val systemComponents = systemData.map[d|d.transformSystemComponent]
		val systemInterfaces = systemInterfaceData.map[d|d.transformInterface]
		root_pkg.architectureelement += systemComponents
		root_pkg.architectureelement += systemInterfaces

		val allParts = eaDataLoader.loadAllParts
		val allPortData = eaDataLoader.loadAllPorts

		logger.log(Level.INFO, "Transforming value types and flow properties")

		//val allFlowPropertiesData = eaDataLoader.loadAllFlowProperties(functionalPackageData)
		

		val xrefData = eaDataLoader.loadAllXRefData
		//val objectPropertyData = eaDataLoader.loadAllObjectPropertyData

		// val allValueTypes = allValueTypeData.map[d|d.transformValueType].toList
		//root_pkg.architectureelement += allValueTypes
		/*val allFlowProperties = allFlowPropertiesData.map[d|d.transformFlowProperty].toList
		val directionMap = objectPropertyData.createDirectionMap
		for (flowProp : allFlowProperties) {
			flowProp.direction = directionMap.get(trace.get(flowProp))
		}*/

		var allSubfunctions = <ArchitectureSubfunction>newLinkedList
		var allSubcomponent = <ArchitectureSubcompnent>newLinkedList

		functions.forEach[c|containingElement.put(c, c)]
		systemComponents.forEach[c|containingElement.put(c, c)]

		logger.log(Level.INFO, "Transforming parts and ports")
		var shall_run = true
		while (shall_run) {
			shall_run = false

			for (part : allParts) {
				try {
					if (trace.contains(part.conainerID) && (!trace.contains(part.elementID))) {
						var container = trace.get(part.conainerID)
						if (part.PDATA1 === null || part.PDATA1 == "") {
							logger.log(Level.WARNING, '''Part «part.name» with GUID=«part.GUID» has no type''')
						} else if (! trace.contains(part.PDATA1)) {
							logger.log(
								Level.
									SEVERE, '''The type of part «part.name» with GUID=«part.GUID» has no type, Type GUID=«part.PDATA1»''')
						} else {
							val type_id = trace.get(part.PDATA1)
							val type = trace.get(type_id)
							if (type instanceof ArchitectureFunction) {
								val subfunction = part.transformSubfunction
								allSubfunctions += subfunction
								containingElement.put(subfunction, containingElement.get(container))
							}
							if ((type instanceof ArchitectureComponent) ||
								(type instanceof hu.bme.mit.gamma.architecture.model.System)) {
								val subcomponent = part.transformSubcomponent
								allSubcomponent += subcomponent
								containingElement.put(subcomponent, containingElement.get(container))

							}
							shall_run = true
						}
					}
				} catch (Exception e) {
					logger.log(Level.SEVERE, '''
						An exception occured during the transformation of part «part.name» with GUID=«part.GUID», PDATA1=«part.PDATA1», ParentID=«part.conainerID»
					''')
					e.printStackTrace
				}
			}
		}
		val allPorts = <ArchitecturePort>newLinkedList
		val conjugationMap = xrefData.createConjugationMap
		for (port : allPortData) {
			if (trace.contains(port.conainerID)) {
				if (port.PDATA1 === null || port.PDATA1 == "") {
					logger.log(Level.WARNING, '''Port «port.name» with GUID=«port.GUID» has no type''')
				} else if (! trace.contains(port.PDATA1)) {
					logger.log(
						Level.
							SEVERE, '''The type of port «port.name» with GUID=«port.GUID» has no type, Type GUID=«port.PDATA1»''')
				} else {
					try {
						if (conjugationMap.containsKey(port.GUID)) {
							allPorts += port.transformPort(conjugationMap.get(port.GUID))
						} else {
							allPorts += port.transformPort
						}
					} catch (Exception e) {
						logger.log(Level.SEVERE, '''
							An exception occured during the transformation of port «port.name» with GUID=«port.GUID», PDATA1=«port.PDATA1», ParentID=«port.conainerID»
						''')
						e.printStackTrace
					}
				}
			}
		}

		allPorts.forEach[p|containingElement.put(p, containingElement.get(p.eContainer as StructuralElement))]

		logger.log(Level.INFO, "Transform information flows")

		val flowData = eaDataLoader.loadAllFlows
		var allFlows = <InformationFlow>newLinkedList
		for (data : flowData) {
			data.transformFlow
		}

		logger.log(Level.INFO, "Transform allocations")

		val allocationData = eaDataLoader.loadAllAllocation
		var allAllocation = <Allocation>newLinkedList
		for (data : allocationData) {
			allAllocation.add(data.transformAllocation)
		}

		logger.log(Level.INFO, "Transform connectors")

		val connectorData = eaDataLoader.loadAllConnectors
		var allConnectors = <Connector>newLinkedList
		for (data : connectorData) {
			allConnectors.add(data.transformConnector)
		}

		logger.log(Level.INFO, "Transform realizations")
		val realizationData=eaDataLoader.loadAllRealization
		for (data:realizationData){
			data.transformRealization
		}
		
		logger.log(Level.INFO, "Transform generalization")
		val generalizationData=eaDataLoader.loadAllGeneralization
		for (data:realizationData){
			data.transformGeneralization
		}
		
		
		logger.log(Level.INFO, "Loading statemachine data")
		
		trace.statemachineData=new StatemachineData(eaDataLoader,functionalPackageData)
		trace.fileData=new FileData(eaDataLoader,functionalPackageData)
		
		logger.log(Level.INFO, "Architecture Transformation is finished")

		return trace

	}

	def execute() {

		var root_pkg = createPackage("ArchitectureModel")

		logger.log(Level.INFO, "Loading packages")

		val packageData = eaDataLoader.loadAllPackages

		logger.log(Level.INFO, "Transforming functional models")

		val functionalPackageData = packageData.getContainedPackage(functionPackageGUID)
		val functionData = eaDataLoader.loadBlocksFromPackage(functionalPackageData)
		val functionInterfaceData = eaDataLoader.loadInterfaceBlocksFromPackage(functionalPackageData)
		val functions = functionData.map[d|d.transformFunction]
		val functionInterfaces = functionInterfaceData.map[d|d.transformInterface]
		root_pkg.architectureelement += functions
		root_pkg.architectureelement += functionInterfaces

		logger.log(Level.INFO, "Transforming electrical models")

		val electricalPackageData = packageData.getContainedPackage(electricalPackageGUID)
		val electricalData = eaDataLoader.loadBlocksFromPackage(electricalPackageData)
		val electricalInterfaceData = eaDataLoader.loadInterfaceBlocksFromPackage(electricalPackageData)
		val electricalComponents = electricalData.map[d|d.transformElectronicComponent]
		val electricalInterfaces = electricalInterfaceData.map[d|d.transformInterface]
		root_pkg.architectureelement += electricalComponents
		root_pkg.architectureelement += electricalInterfaces

		logger.log(Level.INFO, "Transforming mechanical models")

		val mechanicalPackageData = packageData.getContainedPackage(mechanicalPackageGUID)
		val mechanicalData = eaDataLoader.loadBlocksFromPackage(mechanicalPackageData)
		val mechanicalInterfaceData = eaDataLoader.loadInterfaceBlocksFromPackage(mechanicalPackageData)
		val mechanicalComponents = mechanicalData.map[d|d.transformElectronicComponent]
		val mechanicalInterfaces = mechanicalInterfaceData.map[d|d.transformInterface]
		root_pkg.architectureelement += mechanicalComponents
		root_pkg.architectureelement += mechanicalInterfaces

		logger.log(Level.INFO, "Transforming software models")

		val softwarePackageData = packageData.getContainedPackage(softwarePackageGUID)
		val softwareData = eaDataLoader.loadBlocksFromPackage(softwarePackageData)
		val softwareInterfaceData = eaDataLoader.loadInterfaceBlocksFromPackage(softwarePackageData)
		val softwareComponents = softwareData.map[d|d.transformSoftwareComponent]
		val softwareInterfaces = softwareInterfaceData.map[d|d.transformInterface]
		root_pkg.architectureelement += softwareComponents
		root_pkg.architectureelement += softwareInterfaces

		logger.log(Level.INFO, "Transforming system models")

		val systemPackageData = packageData.getContainedPackage(systemPackageGUID)
		val systemData = eaDataLoader.loadBlocksFromPackage(systemPackageData)
		val systemInterfaceData = eaDataLoader.loadInterfaceBlocksFromPackage(systemPackageData)
		val systemComponents = systemData.map[d|d.transformSystemComponent]
		val systemInterfaces = systemInterfaceData.map[d|d.transformInterface]
		root_pkg.architectureelement += systemComponents
		root_pkg.architectureelement += systemInterfaces

		val allParts = eaDataLoader.loadAllParts
		val allPortData = eaDataLoader.loadAllPorts

		var allSubfunctions = <ArchitectureSubfunction>newLinkedList
		var allSubcomponent = <ArchitectureSubcompnent>newLinkedList

		functions.forEach[c|containingElement.put(c, c)]
		electricalComponents.forEach[c|containingElement.put(c, c)]
		mechanicalComponents.forEach[c|containingElement.put(c, c)]
		softwareComponents.forEach[c|containingElement.put(c, c)]
		systemComponents.forEach[c|containingElement.put(c, c)]

		logger.log(Level.INFO, "Transforming parts and ports")

		var shall_run = true
		while (shall_run) {
			shall_run = false

			for (part : allParts) {
				try {
					if (trace.contains(part.conainerID) && (!trace.contains(part.elementID))) {
						var container = trace.get(part.conainerID)
						if (part.PDATA1 === null || part.PDATA1 == "") {
							logger.log(Level.WARNING, '''Part «part.name» with GUID=«part.GUID» has no type''')
						} else if (! trace.contains(part.PDATA1)) {
							logger.log(
								Level.
									SEVERE, '''The type of part «part.name» with GUID=«part.GUID» has no type, Type GUID=«part.PDATA1»''')
						} else {
							val type_id = trace.get(part.PDATA1)
							val type = trace.get(type_id)
							if (type instanceof ArchitectureFunction) {
								val subfunction = part.transformSubfunction
								allSubfunctions += subfunction
								containingElement.put(subfunction, containingElement.get(container))
							}
							if ((type instanceof ArchitectureComponent) || (type instanceof System)) {
								val subcomponent = part.transformSubcomponent
								allSubcomponent += subcomponent
								containingElement.put(subcomponent, containingElement.get(container))

							}
							shall_run = true
						}
					}
				} catch (Exception e) {
					logger.log(Level.SEVERE, '''
						An exception occured during the transformation of part «part.name» with GUID=«part.GUID», PDATA1=«part.PDATA1», ParentID=«part.conainerID»
					''')
					e.printStackTrace
				}
			}
		}
		val allPorts = <ArchitecturePort>newLinkedList
		for (portData : allPortData) {
			if (trace.contains(portData.conainerID)) {
				if (portData.PDATA1 === null || portData.PDATA1 == "") {
					logger.log(Level.WARNING, '''Port «portData.name» with GUID=«portData.GUID» has no type''')
				} else if (! trace.contains(portData.PDATA1)) {
					logger.log(
						Level.
							SEVERE, '''The type of port «portData.name» with GUID=«portData.GUID» has no type, Type GUID=«portData.PDATA1»''')
				} else {
					try {
						val port = portData.transformPort
						allPorts += port
					} catch (Exception e) {
						logger.log(Level.SEVERE, '''
							An exception occured during the transformation of port «portData.name» with GUID=«portData.GUID», PDATA1=«portData.PDATA1», ParentID=«portData.conainerID»
						''')
						e.printStackTrace
					}
				}
			}
		}

		allPorts.forEach[p|containingElement.put(p, containingElement.get(p.eContainer as StructuralElement))]

		val flowData = eaDataLoader.loadAllFlows
		var allFlows = <InformationFlow>newLinkedList
		for (data : flowData) {
			data.transformFlow
		}

		val allocationData = eaDataLoader.loadAllAllocation
		var allAllocation = <Allocation>newLinkedList
		for (data : allocationData) {
			allAllocation.add(data.transformAllocation)
		}

		val connectorData = eaDataLoader.loadAllConnectors
		var allConnectors = <Connector>newLinkedList
		for (data : connectorData) {
			allConnectors.add(data.transformConnector)
		}

		val interfaceConnectorData = eaDataLoader.loadAllInterfaceConnectors
		var allInterfaceConnectors = <Connector>newLinkedList
		for (data : interfaceConnectorData) {
			allInterfaceConnectors.add(data.transformInterfaceConnector)
		}

		val union = allFlows + allAllocation + allConnectors + allInterfaceConnectors
		
		logger.log(Level.INFO, "Loading statemachine data")
		
		trace.statemachineData=new StatemachineData(eaDataLoader,functionalPackageData)
		
		logger.log(Level.INFO, "Transformation is finished")

		return root_pkg

	}

}
