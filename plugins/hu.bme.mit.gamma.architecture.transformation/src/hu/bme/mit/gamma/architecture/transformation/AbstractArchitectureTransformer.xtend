package hu.bme.mit.gamma.architecture.transformation

import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.statechart.composite.CompositeModelFactory
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.util.GammaEcoreUtil
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.architecture.model.ArchitectureComponent
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent
import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import static extension hu.bme.mit.gamma.architecture.transformation.RelationTransfomer.*
import hu.bme.mit.gamma.architecture.model.InformationFlow
import hu.bme.mit.gamma.architecture.model.ArchitecturePort
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import java.util.Set
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.statechart.interface_.RealizationMode
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponent
import hu.bme.mit.gamma.architecture.transformation.builder.Channelbuilder
import java.util.Map

abstract class AbstractArchitectureTransformer {

	protected val ArchitectureTrace trace

	protected static val stochModelFactory = EnvironmentModelFactory.eINSTANCE
	protected static val sctModelFactory = StatechartModelFactory.eINSTANCE
	protected static val ifModelFactory = InterfaceModelFactory.eINSTANCE
	protected static val cmpModelFactory = CompositeModelFactory.eINSTANCE
	protected static val exprModelFactory = ExpressionModelFactory.eINSTANCE
	
	protected val Map<AsynchronousComponentInstance, Set<Port>>  instancePorts 

	protected extension val GammaEcoreUtil gammaEcoreUtil = GammaEcoreUtil.INSTANCE
	
	protected val extension ElementTransformer elementTransformer
	protected val extension RelationTransfomer relationTransformer
	
	protected val Channelbuilder channelBuilder

	new(ArchitectureTrace trace) {
		this.trace = trace
		this.elementTransformer = new ElementTransformer(trace)
		this.relationTransformer = new RelationTransfomer(trace)
		this.channelBuilder = new Channelbuilder
		this.instancePorts = <AsynchronousComponentInstance, Set<Port>>newHashMap
	}


	def getFlowSourcePortLoose(InformationFlow flow) {

		var type = flow.flowType

		if (flow.source instanceof ArchitecturePort) {
			val port = flow.source as ArchitecturePort
			val name = port.name.gammaName
			if (port.eContainer instanceof ArchitectureSubfunction) {
				val subfunc = port.eContainer as ArchitectureSubfunction
				val inst = trace.get(subfunc) as AsynchronousComponentInstance
				val sPort = findOutputPortLoose(inst, type, name)
				return sPort
			} else {
				throw new ArchitectureException("Flow source cannot recognized",
					flow.source.eContainer as ArchitectureElement)
			}
		} else if (flow.source instanceof ArchitectureSubfunction) {
			val name = flow.gammaName
			val inst = trace.get(flow.source) as AsynchronousComponentInstance
			var sPort = findOutputPortLoose(inst, type, name)
			return sPort
		} else {
			throw new ArchitectureException("Flow source cannot recognized", flow.source)
		}
	}

	def getFlowTargetPortLoose(InformationFlow flow) {

		var type = flow.flowType

		if (flow.target instanceof ArchitecturePort) {
			val port = flow.target as ArchitecturePort
			if (port.eContainer instanceof ArchitectureSubfunction) {
				val subfunc = port.eContainer as ArchitectureSubfunction
				val inst = trace.get(subfunc) as AsynchronousComponentInstance
				val sPort = findInputPortLoose(inst, type, port.name.gammaName)
				return sPort
			} else {
				throw new ArchitectureException("Flow target cannot recognized",
					flow.target.eContainer as ArchitectureElement)
			}
		} else if (flow.target instanceof ArchitectureSubfunction) {
			val inst = trace.get(flow.target) as AsynchronousComponentInstance
			var sPort = findInputPortLoose(inst, type)
			return sPort
		} else {
			throw new ArchitectureException("Flow target cannot recognized", flow.target)
		}
	}

	def findInputPortLoose(AsynchronousComponentInstance instance, Interface _interface) {
		val ports = instancePorts.get(instance)
		val matches = ports.filter [ port |
			port.interfaceRealization.interface == _interface &&
				port.interfaceRealization.realizationMode == RealizationMode.REQUIRED
		]
		if (matches.empty) {
			throw new ArchitectureException('''Subfunction port with type: '«_interface.name»"" cannot be found in function: '«instance.name»' «instance.type.ports.map[p|p.name]» ''', trace.get(instance))
		}
		val port = matches.get(0)
		ports.remove(port)
		return port
	}

	def findInputPortLoose(AsynchronousComponentInstance instance, Interface _interface, String name) {
		val ports = instancePorts.get(instance)
		var _name=name
		if (!name.matches('''.*«_interface.name»In$''')){
			_name=name+_interface.name+"In"
		} 
		val namef=_name
		val matches = ports.filter [ port |
			port.interfaceRealization.interface == _interface &&
				port.interfaceRealization.realizationMode == RealizationMode.REQUIRED && port.name== namef
		]
		if (matches.empty) {
			throw new ArchitectureException('''Subfunction port: '«namef»' cannot be found in function: '«instance.name»' «instance.type.ports.map[p|p.name]» ''',
				trace.get(instance))
		}
		val port = matches.get(0)
		ports.remove(port)
		return port
	}

	def findOutputPortLoose(AsynchronousComponentInstance instance, Interface _interface, String name) {
		val ports = instance.type.ports
		var _name=name
		if (!name.matches('''.*«_interface.name»Out$''')){
			_name=name+_interface.name+"Out"
		}
		val namef=_name
		var Iterable<Port> matches
		if (name.isBlank) {
			matches = ports.filter [ port |
				port.interfaceRealization.interface == _interface &&
					port.interfaceRealization.realizationMode == RealizationMode.PROVIDED
			]
		} else {
			matches = ports.filter [ port |
				port.interfaceRealization.interface == _interface &&
					port.interfaceRealization.realizationMode == RealizationMode.PROVIDED && namef==port.name
			]
			if (matches.empty) {
				matches = ports.filter [ port |
					port.interfaceRealization.interface == _interface &&
						port.interfaceRealization.realizationMode == RealizationMode.PROVIDED
				]	
			}
		}
		if (matches.empty) {
			throw new ArchitectureException('''Subfunction port: '«namef»' cannot be found in function: '«instance.name»' «instance.type.ports.map[p|p.name]» ''', trace.get(instance))
		}
		val port = matches.get(0)
		return port
	}

	def findPortLoose(AsynchronousComponent component, Interface _interface, String name, boolean conj) {
		val relMode = (conj) ? RealizationMode.REQUIRED : RealizationMode.PROVIDED
		val relPorts = component.ports.filter [ port |
			port.interfaceRealization.interface == _interface && port.interfaceRealization.realizationMode == relMode
		].toList

		val exactMatches = relPorts.filter[port|port.name == name].toList
		if (!exactMatches.isEmpty) {
			return exactMatches.get(0)
		}
		val anonymMatches = relPorts.filter[port|port.name == ""].toList
		if (anonymMatches.length == 1) {
			return anonymMatches.get(0)
		}

	}
}
