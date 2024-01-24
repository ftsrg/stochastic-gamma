package hu.bme.mit.gamma.architecture.transformation

import hu.bme.mit.gamma.architecture.transformation.traceability.AllocationTrace
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.architecture.model.ArchitectureSubfunction

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import static extension hu.bme.mit.gamma.architecture.transformation.RelationTransfomer.*
import hu.bme.mit.gamma.architecture.model.ArchitectureSubcompnent
import hu.bme.mit.gamma.architecture.model.InforationFlow
import hu.bme.mit.gamma.architecture.model.InterfaceConnector
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory
import hu.bme.mit.gamma.environment.model.impl.EnvironmentModelFactoryImpl
import hu.bme.mit.gamma.architecture.model.ArchitecturePort
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.architecture.model.ArchitectureElement
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.statechart.interface_.RealizationMode
import hu.bme.mit.gamma.architecture.model.ArchitectureFunction
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponent
import hu.bme.mit.gamma.architecture.transformation.builder.Channelbuilder
import hu.bme.mit.gamma.architecture.transformation.builder.SingletonComponentBuilder
import hu.bme.mit.gamma.architecture.transformation.builder.InterfaceComponentBuilder
import com.google.common.collect.Table
import com.google.common.collect.HashBasedTable

class SystemTransformer {

	val hu.bme.mit.gamma.architecture.model.System system

	val AllocationTrace allocationTrace
	val ArchitectureTrace trace 
	val stochModelFactory = EnvironmentModelFactory.eINSTANCE
	val sctModelFactory = StatechartModelFactory.eINSTANCE
	val ifModelFactory = InterfaceModelFactory.eINSTANCE
	val EnvironmentAsynchronousCompositeComponent systemComponent 
	val sysChannelBuilder = new Channelbuilder
	val subsysMap = <ArchitectureSubcompnent,SingletonComponentBuilder>newHashMap
	val ifcompMap = <InterfaceConnector,InterfaceComponentBuilder>newHashMap
	val Table<ArchitectureElement,ArchitectureElement,InterfaceComponentBuilder> ifCompTable= HashBasedTable.create
	
	val extension ElementTransformer elementTransformer
	val extension RelationTransfomer relationTransformer

	new(hu.bme.mit.gamma.architecture.model.System system) {
		this.system = system
		this.trace = new ArchitectureTrace
		this.allocationTrace = new AllocationTrace(system)
		this.systemComponent=stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		this.elementTransformer=new ElementTransformer(trace)
		this.relationTransformer=new RelationTransfomer(trace)
	}

	def getPhyComponent(ArchitectureSubfunction subfunction) {
		return trace.get(allocationTrace.get(subfunction))
	}

	def getPhyComponent(ArchitectureSubcompnent subcompnent) {
		if (subcompnent.isSoftware) {
			return trace.get(allocationTrace.get(subcompnent))
		} else {
			return trace.get(subcompnent)
		}

	}

	def transform(InforationFlow flow) {
		if (flow.target === flow.source) {
		} else {
		}
	}

	def createPort(InforationFlow flow, boolean conj) {
		val port = ifModelFactory.createPort
		var name = flow.gammaName
		var ifrel = ifModelFactory.createInterfaceRealization
		val _interface = trace.get(flow.type) as Interface
		if (name == "") {
			name = _interface.name
		}
		if (conj) {
			ifrel.realizationMode = RealizationMode.REQUIRED
			name = name + "In"
		} else {
			ifrel.realizationMode = RealizationMode.PROVIDED
			name = name + "Out"
		}
		ifrel.interface = _interface
		port.name = name
		port.interfaceRealization = ifrel
		return port
	}

	def transform(InterfaceConnector connector) {
		var ifComponent = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		ifComponent.name=connector.gammaName
		if (connector.source instanceof ArchitecturePort) {
		}
		if (connector.source instanceof ArchitectureSubcompnent) {
		}
		if (connector.target instanceof ArchitecturePort) {
		}
		if (connector.target instanceof ArchitectureSubcompnent) {
		}
	}

	def execute() {
		
		systemComponent.name = system.gammaName

		// transforming the component functions into Gamma Components
		for (subcomponent : system.subcompnents) {
			for (subfunction : subcomponent.subfunctions) {
				val function=subfunction.type as ArchitectureFunction
				val functionTransformer=new FunctionTransformer(function, trace)
				functionTransformer.execute
				subfunction.transformSubfunction
			}
		}
		
		// transforming the system ports
		for (archPort : system.ports){
			//val port = archPort.transformPort
			//systemComponent.ports+=port
		}
		
		// create builder for the subsystems
		for (subcomponent : system.subcompnents) {
			val builder=new SingletonComponentBuilder(subcomponent,trace)
			subsysMap.put(subcomponent,builder)
		}

		// transforming the interface connectors into Gamma Components
		for (connector : system.interfaceConnectors) {
			val builder=new InterfaceComponentBuilder(connector,trace)
			ifcompMap.put(connector,builder)
			ifCompTable.put(connector.sourceSubsystem,connector.targetSubsystem,builder)
			if (! connector.source.isComponentPort){
				val sourceSubsystem=connector.sourceSubsystem
				val sourceSubsystemBuilder=subsysMap.get(sourceSubsystem)
				val sourceIFCompInst=builder.createInstance(sourceSubsystem)
				sourceSubsystemBuilder.addInstance(sourceIFCompInst)
			}
			if (! connector.target.isComponentPort){
				val targetSubsystem=connector.targetSubsystem
				val sourceSubsystemBuilder=subsysMap.get(targetSubsystem)
				val sourceIFCompInst=builder.createInstance(targetSubsystem)
				sourceSubsystemBuilder.addInstance(sourceIFCompInst)
			}
		}

		// transforming the information flows into ports and channels
		for (flow : system.informationFlows) {
			
			if (flow.isSubsystemFlow){
				
				val sourceInst = flow.flowSourceInst
				val targetInst = flow.flowTargetInst
				val sourcePort = getFlowSourcePort(flow)
				val targetPort = getFlowTargetPort(flow)
				val builder=subsysMap.get(flow.sourceSubsystem)
				builder.addChannel(sourceInst,sourcePort,targetInst,targetPort)
				
			} else if (flow.isSystemFlow){
				
				val sourceInst = flow.flowSourceInst
				val targetInst = flow.flowTargetInst
				val sourceSubsystem=flow.sourceSubsystem
				val targetSubsystem=flow.targetSubsystem
				val sourcePort = getFlowSourcePort(flow)
				val targetPort = getFlowTargetPort(flow)
				val sourceComponentBuilder=subsysMap.get(sourceSubsystem)
				val targetComponentBuilder=subsysMap.get(targetSubsystem)
				val ifComponentBuilder = ifCompTable.get(sourceSubsystem,targetSubsystem)
				ifComponentBuilder.addFlow(flow)
				val sourceIFCompInst= ifComponentBuilder.getInstance(sourceSubsystem)
				val targetIFCompInst= ifComponentBuilder.getInstance(targetSubsystem)
				val inIFCompPort= ifComponentBuilder.getInPort (flow)
				val outIFCompPort= ifComponentBuilder.getOutPort(flow)
				val inSubsysCompPort= targetComponentBuilder.getInPort (flow)
				val outSubsysCompPort= sourceComponentBuilder.getOutPort(flow)
				
				
				// add connections
				// func -o)- ifComp -> port -o)- port -> ifComp -o)- func
				
				sourceComponentBuilder.addChannel(sourceInst,sourcePort,sourceIFCompInst,inIFCompPort)
				sourceComponentBuilder.addBinding(outSubsysCompPort,sourceIFCompInst,outSubsysCompPort)
				sysChannelBuilder.add(sourceComponentBuilder.instance,outSubsysCompPort,targetComponentBuilder.instance,inSubsysCompPort)
				targetComponentBuilder.addBinding(inSubsysCompPort,targetIFCompInst,inIFCompPort)
				targetComponentBuilder.addChannel(targetIFCompInst,outIFCompPort,targetInst,targetPort)
				
				
				
			} else if (flow.isSystemInputFlow){
				
				val sourcePort=flow.source as ArchitecturePort
				
				val targetInst = flow.flowTargetInst
				val targetSubsystem=flow.targetSubsystem
				val targetPort = getFlowTargetPort(flow)
				val targetComponentBuilder=subsysMap.get(targetSubsystem)
				val ifComponentBuilder = ifCompTable.get(flow.source,targetSubsystem)
				ifComponentBuilder.addFlow(flow)
				val targetIFCompInst= ifComponentBuilder.getInstance(targetSubsystem)
				val inIFCompPort= ifComponentBuilder.getInPort (flow)
				val outIFCompPort= ifComponentBuilder.getOutPort(flow)
				val inSubsysCompPort= targetComponentBuilder.getInPort (flow)
				
				val sysPort = createPort(trace.get(flow.type) as Interface,flow.gammaName+"_"+sourcePort.gammaName,true)
				systemComponent.ports+=sysPort
				
				// add connections
				// sys.port -> subsys.port -> ifComp -o)- func
				
				systemComponent.portBindings+=createPortBinding(sysPort,targetComponentBuilder.instance,inSubsysCompPort)
				targetComponentBuilder.addBinding(inSubsysCompPort,targetIFCompInst,inIFCompPort)
				targetComponentBuilder.addChannel(targetIFCompInst,outIFCompPort,targetInst,targetPort)
				
			} else if (flow.isSystemOutputFlow){
				
				val targetPort=flow.source as ArchitecturePort
				
				val sourceInst = flow.flowSourceInst
				val sourceSubsystem=flow.sourceSubsystem
				val sourcePort = getFlowSourcePort(flow)
				val sourceComponentBuilder=subsysMap.get(sourceSubsystem)
				val ifComponentBuilder = ifCompTable.get(sourceSubsystem,targetPort)
				ifComponentBuilder.addFlow(flow)
				val sourceIFCompInst= ifComponentBuilder.getInstance(sourceSubsystem)
				val inIFCompPort= ifComponentBuilder.getInPort (flow)
				val outIFCompPort= ifComponentBuilder.getOutPort(flow)
				val outSubsysCompPort= sourceComponentBuilder.getOutPort(flow)
				
				val sysPort = createPort(trace.get(flow.type) as Interface,flow.gammaName+"_"+targetPort.gammaName,false)
				systemComponent.ports+=sysPort				
				
				// add connections
				// func -o)- ifComp -> port -> sys.port
				
				sourceComponentBuilder.addChannel(sourceInst,sourcePort,sourceIFCompInst,inIFCompPort)
				sourceComponentBuilder.addBinding(outSubsysCompPort,sourceIFCompInst,outSubsysCompPort)

			} else {
				throw new ArchitectureException("The information flow is incorrectly connected",flow.source)
			}
			
		}
		
		

	}
	
		
	def getFlowSourcePort(InforationFlow flow){
		
		if (flow.source instanceof ArchitecturePort){
			val port=flow.source as ArchitecturePort
			if (port.eContainer instanceof ArchitectureSubfunction){
				val subfunc=port.eContainer as ArchitectureSubfunction
				val gammaComp=trace.get(subfunc) as AsynchronousComponent
				val sPort = findPort(gammaComp,port)
				if (sPort === null){
					throw new ArchitectureException("Subfunction port cannot be found in function",port)
				}
				return sPort
			}
		}
		if (flow.source instanceof ArchitectureSubfunction){
			val type = trace.get(flow.type) as Interface
			val name = flow.outPortName
			val comp = trace.get(flow.source) as AsynchronousComponent
			var sPort = findPort(comp,type,name,false)
			if (sPort === null){
				throw new ArchitectureException("Subfunction port cannot be found in function",flow.target)
			}
			return sPort
		}
	}
	
	def getFlowTargetPort(InforationFlow flow){
		if (flow.target instanceof ArchitecturePort){
			val port=flow.target as ArchitecturePort
			if (port.eContainer instanceof ArchitectureSubfunction){
				val subfunc=port.eContainer as ArchitectureSubfunction
				val gammaComp=trace.get(subfunc) as AsynchronousComponent
				val sPort = findPort(gammaComp,port)
				if (sPort === null){
					throw new ArchitectureException("Subfunction port cannot be found in function",port)
				}
				return sPort
			}
		}
		if (flow.target instanceof ArchitectureSubfunction){
			val type = trace.get(flow.type) as Interface
			val name = flow.inPortName
			val comp = trace.get(flow.target) as AsynchronousComponent
			var sPort = findPort(comp,type,name,true)
			if (sPort === null){
				throw new ArchitectureException("Subfunction port cannot be found in function",flow.target)
			}
			return sPort
		}
	}

}
