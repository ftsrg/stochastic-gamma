package hu.bme.mit.gamma.architecture.transformation

import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.architecture.model.InformationFlow
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.statechart.interface_.RealizationMode

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import hu.bme.mit.gamma.architecture.model.ArchitecturePort
import hu.bme.mit.gamma.architecture.model.ArchitectureFunction
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponent
import hu.bme.mit.gamma.statechart.interface_.InterfaceRealization
import hu.bme.mit.gamma.architecture.model.ArchitectureInterface
import hu.bme.mit.gamma.statechart.composite.CompositeModelFactory
import hu.bme.mit.gamma.architecture.transformation.builder.Channelbuilder
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import java.util.Map
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.architecture.model.ValueType
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException
import hu.bme.mit.gamma.expression.model.Type
import hu.bme.mit.gamma.architecture.model.FlowProperty
import hu.bme.mit.gamma.architecture.model.Direction
import hu.bme.mit.gamma.statechart.interface_.EventDirection
import hu.bme.mit.gamma.architecture.model.PrimitiveFunction
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.interface_.Component

import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
import hu.bme.mit.gamma.statechart.interface_.Persistency
import hu.bme.mit.gamma.expression.model.DecimalTypeDefinition
import org.eclipse.emf.ecore.util.EcoreUtil
import hu.bme.mit.gamma.util.GammaEcoreUtil
import org.eclipse.emf.ecore.EcorePackage
import hu.bme.mit.gamma.expression.model.ExpressionPackage
import hu.bme.mit.gamma.architecture.model.EnumValueType
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import hu.bme.mit.gamma.architecture.transformation.builder.FailureModelGenerator
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition
import java.util.Collection
import java.util.List
import hu.bme.mit.gamma.architecture.transformation.errors.GammaTransformationException
import hu.bme.mit.gamma.statechart.statechart.SchedulingOrder
import hu.bme.mit.gamma.statechart.statechart.TransitionPriority
import hu.bme.mit.gamma.statechart.interface_.ComponentAnnotation

class ElementTransformer {

	val ArchitectureTrace trace

	static val stochModelFactory = EnvironmentModelFactory.eINSTANCE
	static val sctModelFactory = StatechartModelFactory.eINSTANCE
	static val ifModelFactory = InterfaceModelFactory.eINSTANCE
	static val cmpModelFactory = CompositeModelFactory.eINSTANCE
	static val exprModelFactory = ExpressionModelFactory.eINSTANCE

	protected extension val GammaEcoreUtil gammaEcoreUtil = GammaEcoreUtil.INSTANCE

	public static val Interface failureInterface = createFailureInterface()
	public static val Event failureEvent = failureInterface.events.get(0).event

	new(ArchitectureTrace trace) {
		this.trace = trace
		trace.interfacePackage.interfaces += failureInterface
	}

	def getFailurePort(AsynchronousComponent comp) {
		return comp.ports.filter[p|p.interface == failureInterface].get(0)
	}

	protected static def createFailureInterface() {
		val if_ = ifModelFactory.createInterface
		if_.name = "InterfaceFailures"
		val event = ifModelFactory.createEvent
		event.name = "failure"
		val eventDecl = ifModelFactory.createEventDeclaration
		eventDecl.event = event
		eventDecl.direction = EventDirection.OUT
		if_.events += eventDecl
		return if_
	}

	def packageElement(Interface _interface) {
		val pkg = trace.interfacePackage
		pkg.interfaces += _interface
	}

	def packageElement(Component component) {
		val pkg = ifModelFactory.createPackage
		pkg.name = component.name.toLowerCase
		pkg.components += component
		pkg.imports += component.instances.map[inst|inst.derivedType.containingPackage].toSet
		pkg.imports += trace.interfacePackage
		trace.addComponentPackage(component, pkg)
	}

	def packageElement(Component component, String path) {
		val pkg = ifModelFactory.createPackage
		pkg.name = component.name.toLowerCase
		pkg.components += component
		pkg.imports += component.instances.map[inst|inst.derivedType.containingPackage].toSet
		pkg.imports += trace.interfacePackage
		trace.addComponentPackage(component, pkg)
		trace.setPackagePath(pkg, path)
	}

	val directionMap = Map.of(
		Direction.IN,
		EventDirection.IN,
		Direction.OUT,
		EventDirection.OUT,
		Direction.INOUT,
		EventDirection.INOUT,
		Direction.NONE,
		EventDirection.INOUT
	)

	def transformPrimitiveFunction(ArchitectureFunction architectureFunction) {
		val sct = sctModelFactory.createAsynchronousStatechartDefinition
		sct.name = architectureFunction.gammaName
		for (archPort : architectureFunction.ports) {
			sct.ports += transformPort(archPort)
		}
		sct.schedulingOrder = SchedulingOrder.TOP_DOWN
		sct.transitionPriority = TransitionPriority.VALUE_BASED
		sct.annotations+=sctModelFactory.createRunUponExternalEventOrInternalTimeoutAnnotation

		for (failureInterface : architectureFunction.providedInterfaces) {
			val gammaIf = trace.get(failureInterface) as Interface
			sct.ports += createPort(gammaIf, "", true)
		}

		trace.add(architectureFunction, sct)
		packageElement(sct, "primitive_functions")

	}

	def generateInterfaceComponent(Interface _interface) {
		val comp = sctModelFactory.createAsynchronousStatechartDefinition
		comp.name = "Interfacing_" + _interface.name.toFirstUpper
		val inport = createPort(_interface, "In", true)
		comp.ports += inport
		val outport = createPort(_interface, "Out", false)
		comp.ports += outport
		val failureport = createPort(failureInterface, "Failure", true)
		comp.ports += failureport

		val main_region = sctModelFactory.createRegion
		main_region.name = "main_region"
		val init = sctModelFactory.createInitialState
		init.name = "init_state"
		val state1 = sctModelFactory.createState
		state1.name = "Operational_State"
		main_region.stateNodes += init
		main_region.stateNodes += state1
		comp.regions += main_region
		val tr1 = sctModelFactory.createTransition
		tr1.sourceState = init
		tr1.targetState = state1
		comp.transitions += tr1

		for (event : inport.inputEvents) {
			val tr = sctModelFactory.createTransition
			tr.sourceState = state1
			tr.targetState = state1
			val eventRef = sctModelFactory.createPortEventReference
			val trig = ifModelFactory.createEventTrigger
			eventRef.port = inport
			eventRef.event = event
			trig.eventReference = eventRef
			tr.trigger = trig
			val ract = sctModelFactory.createRaiseEventAction
			ract.event = event
			ract.port = outport
			for (param : event.parameterDeclarations) {
				val paramRef = ifModelFactory.createEventParameterReferenceExpression()
				paramRef.port = inport
				paramRef.event = event
				paramRef.parameter = param
				ract.arguments += paramRef
			}
			tr.effects += ract
			comp.transitions += tr

		}

		val state2 = sctModelFactory.createState
		state2.name = "Failure_State"
		val tr2 = sctModelFactory.createTransition
		tr2.sourceState = state1
		tr2.targetState = state2
		val eventRef2 = sctModelFactory.createPortEventReference
		val trig2 = ifModelFactory.createEventTrigger
		eventRef2.port = failureport
		eventRef2.event = failureEvent
		trig2.eventReference = eventRef2
		tr2.trigger = trig2
		main_region.stateNodes += state2
		comp.transitions += tr2

		for (event : outport.outputEvents) {
			if (!event.parameterDeclarations.empty) {
				val ract = sctModelFactory.createRaiseEventAction
				ract.event = event
				ract.port = outport
				for (param : event.parameterDeclarations) {
					ract.arguments += param.defaultExpression
				}
				state2.entryActions += ract
			}
		}

		packageElement(comp, "interface_components")

		trace.addInterface(trace.get(_interface) as ArchitectureInterface, comp)
	}

	def transformInterface(ArchitectureInterface architectureInterface) {
		val gammaInterface = ifModelFactory.createInterface
		gammaInterface.name = architectureInterface.gammaName
		trace.add(architectureInterface, gammaInterface)
		for (prop : architectureInterface.flowproperties) {
			prop.transformFlowProperty
		}
		for (event : architectureInterface.events) {
			gammaInterface.events += event.clone
		}
		return gammaInterface
	}

	def transformInterfaceGeneralization(ArchitectureInterface architectureInterface) {
		val gammaInterface = trace.get(architectureInterface) as Interface
		for (parent : architectureInterface.parents) {
			val parentIf = trace.get(parent as ArchitectureElement) as Interface
			gammaInterface.parents += parentIf
		}
		for (parent : architectureInterface.providedInterfaces) {
			val parentIf = trace.get(parent) as Interface
			gammaInterface.parents += parentIf
		}
		return gammaInterface
	}

	def Type transformValueType(ValueType valueType) {
		if (trace.contains(valueType)) {
			return trace.get(valueType)
		}
		var Type type;
		if (valueType instanceof EnumValueType) {
			val pkg = trace.interfacePackage
			pkg.typeDeclarations
			val decl = exprModelFactory.createTypeDeclaration
			decl.name = valueType.name + "_Enum"
			val enum1 = exprModelFactory.createEnumerationTypeDefinition
			for (attr : valueType.valueproperties) {
				val literal = exprModelFactory.createEnumerationLiteralDefinition
				literal.name = attr.name.gammaName
				enum1.literals += literal
			}
			if (valueType.valueproperties.empty) {
				val field = exprModelFactory.createEnumerationLiteralDefinition
				field.name = "defaultLiteral"
				enum1.literals += field
			}
			decl.type = enum1
			val typeref = exprModelFactory.createTypeReference
			typeref.reference = decl
			pkg.typeDeclarations += decl
			type = typeref
		} else if (valueType.name == "Real" || valueType.name == "double" || valueType.name == "double_Type") {
			type = exprModelFactory.createDecimalTypeDefinition
		} else if (valueType.name == "Integer" || valueType.name == "Number" || valueType.name == "integer" ||
			valueType.name == "integer_Type") {
			type = exprModelFactory.createIntegerTypeDefinition
		} else if (valueType.name == "Boolean" || valueType.name == "boolean" || valueType.name == "boolean_Type") {
			type = exprModelFactory.createBooleanTypeDefinition
		} else {
			val pkg = trace.interfacePackage
			pkg.typeDeclarations
			val decl = exprModelFactory.createTypeDeclaration
			decl.name = valueType.name + "_Type"
			val record = exprModelFactory.createRecordTypeDefinition
			for (attr : valueType.valueproperties) {
				val field = exprModelFactory.createFieldDeclaration
				field.name = attr.name.gammaName
				field.type = transformValueType(attr.type).clone
				record.fieldDeclarations += field
			}
			if (valueType.valueproperties.empty) {
				val field = exprModelFactory.createFieldDeclaration
				field.name = "data"
				field.type = exprModelFactory.createDecimalTypeDefinition
				record.fieldDeclarations += field
			}
			decl.type = record
			val typeref = exprModelFactory.createTypeReference
			typeref.reference = decl
			pkg.typeDeclarations += decl
			type = typeref
		}
		trace.add(valueType, type)
		return type
	}

	def transformFlowProperty(FlowProperty flowProperty) {
		val type = trace.get(flowProperty.type)
		val name = flowProperty.gammaName
		val eventName = "changeOf" + name.toFirstUpper
		val _interface = trace.get(flowProperty.eContainer as ArchitectureInterface) as Interface
		val eventDeclaration = ifModelFactory.createEventDeclaration
		val event = ifModelFactory.createEvent
		val parameterDeclaration = exprModelFactory.createParameterDeclaration
		parameterDeclaration.name = name
		parameterDeclaration.type = type.clone
		event.parameterDeclarations += parameterDeclaration
		event.name = eventName
		event.persistency = Persistency.PERSISTENT
		eventDeclaration.event = event
		eventDeclaration.direction = directionMap.get(flowProperty.direction)
		_interface.events += eventDeclaration
	}

	def transformPort(ArchitecturePort archPort) {
		val port = ifModelFactory.createPort
		var name = archPort.gammaName
		var ifrel = ifModelFactory.createInterfaceRealization
		val _interface = trace.get(archPort.type) as Interface
		if (name == "") {
			name = _interface.name
			if (archPort.conjugated) {
				name = name + "In"
			} else {
				name = name + "Out"
			}
		}
		if (archPort.conjugated) {
			ifrel.realizationMode = RealizationMode.REQUIRED
		} else {
			ifrel.realizationMode = RealizationMode.PROVIDED
		}
		ifrel.interface = _interface
		port.name = name
		port.interfaceRealization = ifrel
		trace.add(archPort, port)
		return port
	}

	static def _createPort(Interface _interface, String name, boolean conj) {
		val port = ifModelFactory.createPort
		var ifrel = ifModelFactory.createInterfaceRealization
		var gname = ""
		if (conj) {
			ifrel.realizationMode = RealizationMode.REQUIRED
			gname = name + _interface.name + "In"
		} else {
			ifrel.realizationMode = RealizationMode.PROVIDED
			gname = name + _interface.name + "Out"
		}
		ifrel.interface = _interface
		port.name = gname
		port.interfaceRealization = ifrel
		return port
	}

	def createPort(Interface _interface, String name, boolean conj) {
		//val name=_name.gammaName
		val port = ifModelFactory.createPort
		var ifrel = ifModelFactory.createInterfaceRealization
		var gname = ""
		if (conj) {
			ifrel.realizationMode = RealizationMode.REQUIRED
			gname = name + _interface.name + "In"
		} else {
			ifrel.realizationMode = RealizationMode.PROVIDED
			gname = name + _interface.name + "Out"
		}
		ifrel.interface = _interface
		port.name = gname
		port.interfaceRealization = ifrel
		return port
	}

	def findPort(AsynchronousComponent component, Interface _interface, String name, boolean conj) {
		val relMode = (conj) ? RealizationMode.REQUIRED : RealizationMode.PROVIDED
		val relPorts = component.ports.filter [ port |
			port.interfaceRealization.interface == _interface && port.interfaceRealization.realizationMode == relMode
		].toList

		val exactMatches = relPorts.filter[port|port.name == name].toList
		if (!exactMatches.isEmpty) {
			return exactMatches.get(0)
		}
		val anonymMatches = relPorts.filter[port|port.name == ""]
		if (anonymMatches.length == 1) {
			return anonymMatches.get(0)
		}

	}

	def findPort(AsynchronousComponent component, ArchitecturePort archPort) {
		return findPort(component, trace.get(archPort) as Interface, archPort.gammaName, archPort.conjugated)
	}
	

	def findConnections(ArchitecturePort p1, ArchitecturePort p2) {
		val matches = <List<Port>>newLinkedList
		val ports1 = trace.getPhyPorts(p1)
		val ports2 = trace.getPhyPorts(p2)
		val p1name = p1.name.gammaName
		val p2name = p2.name.gammaName
		for (port1 : ports1) {
			val p2matches = ports2.filter [ p |
				p.name.replaceFirst('''(In|Out)$''', "").replaceFirst("^" + p2name+"_", "") ==
					port1.name.replaceFirst("^" + p1name+"_", "").replaceFirst('''(In|Out)$''', "")
			].toList
			if (p2matches.size != 1) {
				throw new ArchitectureException(
					'''Cannot connect functional endpoints of physical connector endpoints [« p1.name»:«p1.type.name»::«port1.name»] -> [«p2.name»:«p2.type.name» «p2matches.size»::(«ports2.map[p|p.name].toList.toString»)] matches''', p1)
			}
			matches.add(<Port>newLinkedList(port1, p2matches.get(0)))
		}
		return matches
	}

	def findConnection(ArchitecturePort aPort, Interface interface_, String name, RealizationMode realizationMode) {
		val ports = trace.getPhyPorts(aPort)
		val pname = aPort.name.gammaName
		val p2matches = ports.filter [ p |
			p.name.replaceFirst('''«interface_.name»(In|Out)$''', "").replaceFirst("^" + pname+"_", "") == name
		].filter[p|p.interfaceRealization.realizationMode == realizationMode].toList
		if (p2matches.size != 1) {
			throw new ArchitectureException(
				'''Cannot connect functional end-points of physical connector endpoints «aPort.name»:«aPort.type.name»| [«ports.map[p|p.name+":"+p.interface.name].toList»] ----	 «name»:«interface_.name» with «p2matches.size» matches''', aPort)
		}
		return p2matches.get(0)
	}
	/*def getGammaSource(InformationFlow flow) {
		if (flow.source instanceof ArchitecturePort) {
			val port = flow.source as ArchitecturePort
			if (port.eContainer instanceof ArchitectureFunction) {
				return trace.get(port) as Port
			}
			if (port.eContainer instanceof ArchitectureSubfunction) {
				val subfunc = port.eContainer as ArchitectureSubfunction
				val gammaComp = trace.get(subfunc) as AsynchronousComponent
				return findPort(gammaComp, port)
			}
		}
		if (flow.source instanceof ArchitectureSubfunction) {
			val type = flow.flowtype
			val name = flow.outPortName
			val comp = trace.get(flow.source) as AsynchronousComponent
			return findPort(comp, type, name, false)
		}
	}

	def getGammaTarget(InformationFlow flow) {
		if (flow.target instanceof ArchitecturePort) {
			val port = flow.target as ArchitecturePort
			if (port.eContainer instanceof ArchitectureFunction) {
				return trace.get(port) as Port
			}
			if (port.eContainer instanceof ArchitectureSubfunction) {
				val subfunc = port.eContainer as ArchitectureSubfunction
				val gammaComp = trace.get(subfunc) as AsynchronousComponent
				return findPort(gammaComp, port)
			}
		}
		if (flow.target instanceof ArchitectureSubfunction) {
			val type = trace.get(flow.type) as Interface
			val name = flow.outPortName
			val comp = trace.get(flow.source) as AsynchronousComponent
			return findPort(comp, type, name, true)
		}
	}*/

	def transformSubfunction(ArchitectureSubfunction subfunction) {
		val inst = cmpModelFactory.createAsynchronousComponentInstance
		inst.name = subfunction.gammaName
		val type = trace.get(subfunction.type) as AsynchronousComponent
		inst.type = type // .clone
		trace.add(subfunction, inst)
		return inst
	}

	def transformSubcomponent(ArchitectureSubcompnent subcompnent) {
		val inst = cmpModelFactory.createAsynchronousComponentInstance
		inst.name = subcompnent.gammaName
		inst.type = trace.get(subcompnent.type) as AsynchronousComponent
		trace.add(subcompnent, inst)
		return inst
	}

	def transformPrimitiveFunction(PrimitiveFunction function) {
		val statechart = sctModelFactory.createAsynchronousStatechartDefinition
		statechart.name = function.gammaName
		for (functionPort : function.ports) {
			statechart.ports += functionPort.transformPort
		}
		trace.add(function, statechart)
		return statechart
	}

}
