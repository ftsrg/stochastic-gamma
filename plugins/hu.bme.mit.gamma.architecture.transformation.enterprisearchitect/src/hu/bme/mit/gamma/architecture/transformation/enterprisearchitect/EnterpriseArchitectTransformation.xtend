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
import hu.bme.mit.gamma.architecture.model.InforationFlow
import hu.bme.mit.gamma.architecture.model.Connector
import hu.bme.mit.gamma.architecture.model.ArchitecturePort

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

	new(
		String functionPackageGUID,
		String electricalPackageGUID,
		String mechanicalPackageGUID,
		String softwarePackageGUID,
		String systemPackageGUID 
	) {
		val pid = searchPID
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
		eaDataLoader = new EADataLoader(repository, trace)
		elementTransformation = new EAElementTransformation(repository, trace, containingElement)
		connectorTransformation = new EAConnectorTransformation(repository, trace, containingElement)

	}

	def searchPID() {
		var pid = -1 as long
		for (ProcessHandle p : ProcessHandle.allProcesses().toList()) {
			if (p.info().command().toString().contains("EA.exe")) {
				pid = p.pid()
			}
		}
		if (pid == -1) {
			throw new IllegalAccessException("Cannot find Enterprise Architect process")
		}
		return pid
	}

	def getInterfaces(int packageID) {
		val xml = runQuery(SQLUtils.getPkgInterfaceBlocks(packageID).toString)
// val interfaceIDs
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
							if ((type instanceof ArchitectureComponent) || (type instanceof hu.bme.mit.gamma.architecture.model.System)) {
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
		val allPorts= <ArchitecturePort>newLinkedList
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
						allPorts+=port.transformPort
					} catch (Exception e) {
						logger.log(Level.SEVERE, '''
							An exception occured during the transformation of port «port.name» with GUID=«port.GUID», PDATA1=«port.PDATA1», ParentID=«port.conainerID»
						''')
						e.printStackTrace
					}
				}
			}
		}
		
		allPorts.forEach[p | containingElement.put(p,containingElement.get(p.eContainer as StructuralElement))]

		val flowData = eaDataLoader.loadAllFlows
		var allFlows = <InforationFlow>newLinkedList 
		for (data : flowData){
			data.transformFlow
		}

		val allocationData = eaDataLoader.loadAllAllocation
		var allAllocation = <Allocation>newLinkedList 
		for (data : allocationData){
			allAllocation.add(data.transformAllocation)
		}

		val connectorData = eaDataLoader.loadAllConnectors
		var allConnectors = <Connector>newLinkedList 
		for (data : connectorData){
			allConnectors.add(data.transformConnector)
		}

		val interfaceConnectorData = eaDataLoader.loadAllInterfaceConnectors
		var allInterfaceConnectors = <Connector>newLinkedList 
		for (data : interfaceConnectorData){
			allInterfaceConnectors.add(data.transformInterfaceConnector)
		}


		val union = allFlows + allAllocation + allConnectors + allInterfaceConnectors
		logger.log(Level.INFO, "Transformation is finished")

		return root_pkg

	}

}
