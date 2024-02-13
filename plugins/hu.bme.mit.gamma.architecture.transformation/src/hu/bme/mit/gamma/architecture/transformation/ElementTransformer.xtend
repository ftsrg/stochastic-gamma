package hu.bme.mit.gamma.architecture.transformation

import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.architecture.model.InforationFlow
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
import interface_.InterfacePackage
import hu.bme.mit.gamma.expression.model.ExpressionPackage

class ElementTransformer {
	
	val ArchitectureTrace trace
	
	val stochModelFactory = EnvironmentModelFactory.eINSTANCE
	val sctModelFactory = StatechartModelFactory.eINSTANCE
	val ifModelFactory = InterfaceModelFactory.eINSTANCE
	val cmpModelFactory = CompositeModelFactory.eINSTANCE
	val exprModelFactory = ExpressionModelFactory.eINSTANCE
	
	
	protected extension val GammaEcoreUtil gammaEcoreUtil = GammaEcoreUtil.INSTANCE
	
	new(ArchitectureTrace trace){
		this.trace=trace
	}
	
	
	def packageElement(Interface _interface){
		val pkg=trace.interfacePackage
		pkg.interfaces+=_interface
	}
	
	def packageElement(Component component){
		val pkg=ifModelFactory.createPackage
		pkg.name=component.name.toLowerCase
		pkg.components+=component
		pkg.imports+=component.allInstances.map[inst| inst.derivedType.containingPackage].toSet
		pkg.imports+=trace.interfacePackage
		trace.addComponentPackage(component,pkg)
	}
	
	def packageElement(Component component,String path){
		val pkg=ifModelFactory.createPackage
		pkg.name=component.name.toLowerCase
		pkg.components+=component
		pkg.imports+=component.allInstances.map[inst| inst.derivedType.containingPackage].toSet
		pkg.imports+=trace.interfacePackage
		trace.addComponentPackage(component,pkg)
		trace.setPackagePath(pkg,path)
	}
	
	val directionMap = Map.of(
		Direction.IN, EventDirection.IN,
		Direction.OUT, EventDirection.OUT,
		Direction.INOUT, EventDirection.INOUT,
		Direction.NONE, EventDirection.INOUT
	)
	
	
	def transformPrimitiveFunction(ArchitectureFunction architectureFunction){
		val sct=sctModelFactory.createAsynchronousStatechartDefinition
		sct.name=architectureFunction.gammaName
		for (archPort : architectureFunction.ports){
			sct.ports+=transformPort(archPort)
		}		
		val main_region=sctModelFactory.createRegion
		main_region.name="main_region"
		val init=sctModelFactory.createInitialState
		init.name="init_state"
		val state1=sctModelFactory.createState
		state1.name="operational"
		main_region.stateNodes+=init
		main_region.stateNodes+=state1
		sct.regions+=main_region
		val tr1=sctModelFactory.createTransition
		tr1.sourceState = init
		tr1.targetState=state1
		sct.transitions+=tr1
		
		trace.add(architectureFunction,sct)
		packageElement(sct,"primitive_functions")
		
	}
	
	def generateInterfaceComponent(Interface _interface){
		val comp=sctModelFactory.createAsynchronousStatechartDefinition
		comp.name="InterfaceComponent"+_interface.name.toFirstUpper
		comp.ports+=createPort(_interface,"In",true)
		comp.ports+=createPort(_interface,"Out",false)
		

		val main_region=sctModelFactory.createRegion
		main_region.name="main_region"
		val init=sctModelFactory.createInitialState
		init.name="init_state"
		val state1=sctModelFactory.createState
		state1.name="operational"
		main_region.stateNodes+=init
		main_region.stateNodes+=state1
		comp.regions+=main_region
		val tr1=sctModelFactory.createTransition
		tr1.sourceState = init
		tr1.targetState=state1
		comp.transitions+=tr1
		packageElement(comp,"interface_components")
		trace.addInterface(trace.get(_interface) as ArchitectureInterface,comp)
	}
	
	def transformInterface(ArchitectureInterface architectureInterface){
		val gammaInterface=ifModelFactory.createInterface
		gammaInterface.name=architectureInterface.gammaName
		trace.add(architectureInterface,gammaInterface)
		for (prop : architectureInterface.flowproperties){
			prop.transformFlowProperty
		}
		return gammaInterface
	}
	
	def transformValueType(ValueType valueType){
		var Type type;
		if (valueType.name=="Real"){
			type= exprModelFactory.createDecimalTypeDefinition
		}else if (valueType.name=="Integer") {
			type= exprModelFactory.createIntegerTypeDefinition
		}else if (valueType.name=="Number") {
			type= exprModelFactory.createIntegerTypeDefinition
		}else if (valueType.name=="Boolean") {
			type= exprModelFactory.createBooleanTypeDefinition
		}else{
			type = exprModelFactory.createDecimalTypeDefinition
		}
		trace.add(valueType,type)
		return type
	}
	
	def transformFlowProperty(FlowProperty flowProperty){
		val type = trace.get(flowProperty.type)
		val name = flowProperty.gammaName
		val eventName="changeOf"+name.toFirstUpper
		val _interface=trace.get(flowProperty.eContainer as ArchitectureInterface) as Interface
		val eventDeclaration = ifModelFactory.createEventDeclaration
		val event=ifModelFactory.createEvent
		val parameterDeclaration = exprModelFactory.createParameterDeclaration
		parameterDeclaration.name = name
		parameterDeclaration.type = type.clone
		event.parameterDeclarations+=parameterDeclaration
		event.name = eventName
		event.persistency = Persistency.PERSISTENT
		eventDeclaration.event = event
		eventDeclaration.direction=directionMap.get(flowProperty.direction)
		_interface.events+=eventDeclaration
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
		trace.add(archPort,port)
		return port
	}
	
	
	def createPort(Interface _interface, String name, boolean conj) {
		val port = ifModelFactory.createPort
		var ifrel = ifModelFactory.createInterfaceRealization
		var gname=""
		if (conj) {
			ifrel.realizationMode = RealizationMode.REQUIRED
			gname=name+_interface.name+"In"
		} else {
			ifrel.realizationMode = RealizationMode.PROVIDED
			gname=name+_interface.name+"Out"
		}
		ifrel.interface = _interface
		port.name = gname
		port.interfaceRealization = ifrel
		return port
	}
	
	
	def findPort(AsynchronousComponent component, Interface _interface, String name, boolean conj){
		val relMode= (conj) ? RealizationMode.REQUIRED : RealizationMode.PROVIDED
		val relPorts=component.ports.filter[port | port.interfaceRealization.interface == _interface && port.interfaceRealization.realizationMode==relMode].toList
		
		val exactMatches=relPorts.filter[port | port.name==name].toList
		if (!exactMatches.isEmpty){
			return exactMatches.get(0)
		}
		val anonymMatches=relPorts.filter[port | port.name==""]
		if (anonymMatches.length==1){
			return anonymMatches.get(0)
		}
		
	}
	
	def findPort(AsynchronousComponent component, ArchitecturePort archPort){
		return findPort(component,trace.get(archPort) as Interface,archPort.gammaName,archPort.conjugated)
	}
		
	def getGammaSource(InforationFlow flow){
		if (flow.source instanceof ArchitecturePort){
			val port=flow.source as ArchitecturePort
			if (port.eContainer instanceof ArchitectureFunction){
				return trace.get(port) as Port
			}
			if (port.eContainer instanceof ArchitectureSubfunction){
				val subfunc=port.eContainer as ArchitectureSubfunction
				val gammaComp=trace.get(subfunc) as AsynchronousComponent
				return findPort(gammaComp,port)
			}
		}
		if (flow.source instanceof ArchitectureSubfunction){
			val type = trace.get(flow.type) as Interface
			val name = flow.outPortName
			val comp = trace.get(flow.source) as AsynchronousComponent
			return findPort(comp,type,name,false)
		}
	}
	
	def getGammaTarget(InforationFlow flow){
		if (flow.target instanceof ArchitecturePort){
			val port=flow.target as ArchitecturePort
			if (port.eContainer instanceof ArchitectureFunction){
				return trace.get(port) as Port
			}
			if (port.eContainer instanceof ArchitectureSubfunction){
				val subfunc=port.eContainer as ArchitectureSubfunction
				val gammaComp=trace.get(subfunc) as AsynchronousComponent
				return findPort(gammaComp,port)
			}
		}
		if (flow.target instanceof ArchitectureSubfunction){
			val type = trace.get(flow.type) as Interface
			val name = flow.outPortName
			val comp = trace.get(flow.source) as AsynchronousComponent
			return findPort(comp,type,name,true)
		}
	}
	
	def transformSubfunction(ArchitectureSubfunction subfunction){
		val inst=cmpModelFactory.createAsynchronousComponentInstance
		inst.name = subfunction.gammaName
		val type = trace.get(subfunction.type) as AsynchronousComponent
		inst.type = type//.clone
		trace.add(subfunction,inst)
		return inst
	}
	

	
	def transformSubcomponent(ArchitectureSubcompnent subcompnent){
		val inst=cmpModelFactory.createAsynchronousComponentInstance
		inst.name = subcompnent.gammaName
		inst.type = trace.get(subcompnent.type) as AsynchronousComponent
		trace.add(subcompnent,inst)
		return inst
	}
	
	def transformPrimitiveFunction(PrimitiveFunction function){
		val statechart=sctModelFactory.createAsynchronousStatechartDefinition
		statechart.name=function.gammaName
		for (functionPort : function.ports){
			statechart.ports+=functionPort.transformPort
		}
		trace.add(function,statechart)
		return statechart
	}
	
	
	

}