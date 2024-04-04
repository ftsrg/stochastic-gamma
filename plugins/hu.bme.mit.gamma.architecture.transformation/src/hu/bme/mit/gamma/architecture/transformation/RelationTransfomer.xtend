package hu.bme.mit.gamma.architecture.transformation

import hu.bme.mit.gamma.architecture.model.InformationFlow
import hu.bme.mit.gamma.architecture.model.InterfaceConnector
import hu.bme.mit.gamma.architecture.model.ArchitecturePort
import hu.bme.mit.gamma.architecture.model.ArchitectureFunction
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.statechart.composite.CompositeModelFactory
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponent
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import hu.bme.mit.gamma.architecture.model.ArchitectureComponent
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.architecture.model.System
import hu.bme.mit.gamma.architecture.model.Connector
import hu.bme.mit.gamma.architecture.model.Generalisation
import hu.bme.mit.gamma.architecture.model.ArchitectureInterface
import hu.bme.mit.gamma.architecture.model.FunctionalElement
import hu.bme.mit.gamma.architecture.model.ElectronicComponent

class RelationTransfomer {

	val ArchitectureTrace trace

	val stochModelFactory = EnvironmentModelFactory.eINSTANCE
	val sctModelFactory = StatechartModelFactory.eINSTANCE
	val ifModelFactory = InterfaceModelFactory.eINSTANCE
	val cmpModelFactory = CompositeModelFactory.eINSTANCE

	new(ArchitectureTrace trace) {
		this.trace = trace
	}

	static def isInPortBinding(InformationFlow flow) {
		if (flow.source instanceof ArchitecturePort) {
			if (flow.source.eContainer instanceof ArchitectureFunction) {
				return true
			}
		}
		return false
	}

	static def isOutPortBinding(InformationFlow flow) {
		if (flow.target instanceof ArchitecturePort) {
			if (flow.target.eContainer instanceof ArchitectureFunction) {
				return true
			}
		}
		return false
	}

	static def isInPortBinding2(InformationFlow flow) {
		if (flow.source instanceof ArchitecturePort) {
			if (flow.source.eContainer instanceof ArchitectureComponent) {
				return true
			}
		}
		return false
	}

	static def isOutPortBinding2(InformationFlow flow) {
		if (flow.target instanceof ArchitecturePort) {
			if (flow.target.eContainer instanceof ArchitectureComponent) {
				return true
			}
		}
		return false
	}

	static def isPort(ArchitectureElement element) {
		return element instanceof ArchitecturePort
	}

	static def isComponentPort(ArchitectureElement element) {
		if (element.isPort) {
			return element.eContainer instanceof ArchitectureComponent
		}
		return false
	}

	static def isFunctionPort(ArchitectureElement element) {
		if (element.isPort) {
			return element.eContainer instanceof ArchitectureFunction
		}
		return false
	}

	static def isSubsystem(ArchitectureElement element) {
		if (element instanceof ArchitectureSubcompnent) {
			return element.type instanceof System
		}
		return false
	}

	static def isFunctionalPort(ArchitectureElement element) {
		if (element.isPort) {
			return (element.eContainer instanceof ArchitectureFunction) ||
				(element.eContainer instanceof ArchitectureSubfunction )
		}
		return false
	}

	static def isFunction(ArchitectureElement element) {
		return element instanceof ArchitectureFunction
	}

	static def isSubfunction(ArchitectureElement element) {
		return element instanceof ArchitectureSubfunction
	}

	static def isSubcomponentPort(ArchitectureElement element) {
		if (element.isPort) {
			return element.eContainer instanceof ArchitectureSubcompnent
		}
		return false
	}

	static def isSubfunctionPort(ArchitectureElement element) {
		if (element.isPort) {
			return element.eContainer instanceof ArchitectureSubfunction
		}
		return false
	}

	static def isFunctionalConnector(Connector connector) {
		return connector.source.isSubfunctionPort || connector.source.isSubfunction ||
			connector.target.isSubfunctionPort || connector.target.isSubfunction
	}

	// true if  subsys(.port) --conn-- subsys(.port)
	static def isSubsystemEnd(ArchitectureElement element) {
		if (element instanceof ArchitecturePort) {
			if (element.eContainer instanceof ArchitectureSubcompnent) {
				if ((element.eContainer as ArchitectureSubcompnent).type instanceof System) {
					return true
				}
			}
			return false
		} else {
			if (element instanceof ArchitectureSubcompnent) {
				if ((element as ArchitectureSubcompnent).type instanceof System) {
					return true
				}
			}
		}
		return false
	}

	// true if  hwcomp(.port) --conn-- hwcomp(.port)
	static def isElectronicEnd(ArchitectureElement element) {
		if (element instanceof ArchitecturePort) {
			if (element.eContainer instanceof ArchitectureSubcompnent) {
				if ((element.eContainer as ArchitectureSubcompnent).type instanceof ElectronicComponent) {
					return true
				}
			}
		}
		return false
	}

	static def isSubsystemConnector(Connector connector) {
		return (connector.source.isSubsystemEnd && connector.target.isSubsystemEnd) ||
			(connector.source.isSubsystemEnd && connector.target.isComponentPort) ||
			(connector.target.isSubsystemEnd && connector.source.isComponentPort)
	}

	static def isElectronicConnector(Connector connector) {
		return connector.source.isElectronicEnd && connector.target.isElectronicEnd
	}

	static def isElectronicInputConnector(Connector connector) {
		return connector.target.isElectronicEnd && connector.source.isComponentPort
	}

	static def isElectronicOutputConnector(Connector connector) {
		return connector.source.isElectronicEnd && connector.target.isComponentPort
	}

	static def isSystemFlow(InformationFlow flow) {
		val source = flow.source
		val target = flow.target
		var ArchitectureSubcompnent sourceComp
		var ArchitectureSubcompnent targetComp
		if (source.isSubfunctionPort) {
			sourceComp = source.eContainer.eContainer as ArchitectureSubcompnent
		} else if (source instanceof ArchitectureSubfunction) {
			sourceComp = source.eContainer as ArchitectureSubcompnent
		} else {
			return false
		}
		if (target.isSubfunctionPort) {
			targetComp = target.eContainer.eContainer as ArchitectureSubcompnent
		} else if (target instanceof ArchitectureSubfunction) {
			targetComp = target.eContainer as ArchitectureSubcompnent
		} else {
			return false
		}
		return !(sourceComp === targetComp)
	}

	static def isSubsystemFlow(InformationFlow flow) {
		val source = flow.source
		val target = flow.target
		var ArchitectureSubcompnent sourceComp
		var ArchitectureSubcompnent targetComp
		if (source.isSubfunctionPort) {
			sourceComp = source.eContainer.eContainer as ArchitectureSubcompnent
		} else if (source instanceof ArchitectureSubfunction) {
			sourceComp = source.eContainer as ArchitectureSubcompnent
		} else {
			return false
		}
		if (target.isSubfunctionPort) {
			targetComp = target.eContainer.eContainer as ArchitectureSubcompnent
		} else if (target instanceof ArchitectureSubfunction) {
			targetComp = target.eContainer as ArchitectureSubcompnent
		} else {
			return false
		}
		return (sourceComp === targetComp)
	}

	static def isSystemInputFlow(InformationFlow flow) {
		val source = flow.source
		val target = flow.target
		return source.isComponentPort && (target.isSubfunctionPort || target.isSubfunction)
	}

	static def isSystemOutputFlow(InformationFlow flow) {
		val source = flow.source
		val target = flow.target
		return target.isComponentPort && (source.isSubfunctionPort || source.isSubfunction)
	}

	def createInstancePortRef(ComponentInstance inst, Port port) {
		val ref = cmpModelFactory.createInstancePortReference()
		ref.instance = inst
		ref.port = port
		return ref
	}

	def createPortBinding(Port port, ComponentInstance inst, Port instPort) {
		val bind = cmpModelFactory.createPortBinding
		bind.instancePortReference = createInstancePortRef(inst, instPort)
		bind.compositeSystemPort = port
		return bind
	}

	def getSourceInstPortRef(InformationFlow flow) {
		val port = trace.get(flow.source) as Port
		val inst = trace.get(flow.source.eContainer as ArchitectureElement) as ComponentInstance

	}

	def getFlowType(InformationFlow flow) {
		var flowType = flow.type
		if (flow.target.isFunctionalPort) {
			val targetPort = flow.target as ArchitecturePort
			if (flowType === null) {
				flowType = targetPort.type
			} else if (flowType !== targetPort.type) {
				throw new ArchitectureException("Inconsistent flow types at target", flow)
			}
		}
		if (flow.source.isFunctionalPort) {
			val sourcePort = flow.source as ArchitecturePort
			if (flowType === null) {
				flowType = sourcePort.type
			} else if (flowType !== null && flowType !== sourcePort.type) {
				throw new ArchitectureException("Inconsistent flow types at source", flow)
			}
		}
		if (flowType === null) {
			throw new ArchitectureException("Flow type is not defined", flow)
		}
		return trace.get(flowType) as Interface
	}

	def getFlowSourceInst(InformationFlow flow) {
		if (flow.source.isSubfunctionPort) {
			return trace.get(flow.source.eContainer as ArchitectureSubfunction) as ComponentInstance
		} else if (flow.source instanceof ArchitectureSubfunction) {
			return trace.get(flow.source) as ComponentInstance
		} else {
			throw new ArchitectureException("Source of flow is incorrectly connected", flow.source)
		}
	}

	def getFlowTargetInst(InformationFlow flow) {
		if (flow.target.isSubfunctionPort) {
			return trace.get(flow.target.eContainer as ArchitectureSubfunction) as ComponentInstance
		} else if (flow.target instanceof ArchitectureSubfunction) {
			return trace.get(flow.target) as ComponentInstance
		} else {
			throw new ArchitectureException("Target of flow is incorrectly connected", flow.source)
		}
	}

	static def getSourceSubsystem(InformationFlow flow) {
		if (flow.source.isSubfunctionPort) {
			return flow.source.eContainer.eContainer as ArchitectureSubcompnent
		} else if (flow.source instanceof ArchitectureSubfunction) {
			return flow.source.eContainer as ArchitectureSubcompnent
		} else {
			throw new ArchitectureException("Source of flow is incorrectly connected", flow.source)
		}
	}

	static def getTargetSubsystem(InformationFlow flow) {
		if (flow.target.isSubfunctionPort) {
			return flow.target.eContainer.eContainer as ArchitectureSubcompnent
		} else if (flow.target instanceof ArchitectureSubfunction) {
			return flow.target.eContainer as ArchitectureSubcompnent
		} else {
			throw new ArchitectureException("Target of flow is incorrectly connected", flow.source)
		}
	}

	static def getSourceSubsystem(Connector connector) {
		if (connector.source.isSubcomponentPort) {
			return connector.source.eContainer as ArchitectureSubcompnent
		} else if (connector.source instanceof ArchitectureSubcompnent) {
			return connector.source as ArchitectureSubcompnent
		} else {
			throw new ArchitectureException("Source of connector is incorrectly connected", connector.source)
		}
	}

	static def getTargetSubsystem(Connector connector) {
		if (connector.target.isSubcomponentPort) {
			return connector.target.eContainer as ArchitectureSubcompnent
		} else if (connector.target instanceof ArchitectureSubcompnent) {
			return connector.target as ArchitectureSubcompnent
		} else {
			throw new ArchitectureException("Target of connector is incorrectly connected", connector.source)
		}
	}

	def transformIfGeneralisation(Generalisation generalisation) {
		if (generalisation.source instanceof ArchitectureInterface) {
			val sourceInterface = trace.get(generalisation.source) as Interface
			val targetInterface = trace.get(generalisation.target) as Interface
			sourceInterface.parents += targetInterface
		}
	}

}
