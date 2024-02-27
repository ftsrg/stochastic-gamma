package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect

import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.AttributeData
import java.util.List
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.XrefData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ObjectPropertyData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.OperationData
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability.ElementTrace
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.expression.model.EnumerationTypeDefinition

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import hu.bme.mit.gamma.expression.model.RecordTypeDefinition
import hu.bme.mit.gamma.expression.model.Type
import hu.bme.mit.gamma.architecture.model.ValueType
import hu.bme.mit.gamma.statechart.interface_.Component
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponent
import hu.bme.mit.gamma.statechart.composite.SynchronousComponent
import hu.bme.mit.gamma.architecture.transformation.errors.GammaTransformationException
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ElementData
import hu.bme.mit.gamma.statechart.interface_.EventDirection
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.expression.model.NamedElement
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition
import hu.bme.mit.gamma.statechart.statechart.State
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException
import hu.bme.mit.gamma.statechart.statechart.Region
import hu.bme.mit.gamma.statechart.statechart.PseudoState
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ConnectorData
import hu.bme.mit.gamma.statechart.statechart.StateNode
import hu.bme.mit.gamma.statechart.statechart.InitialState
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.StatemachineData

class GammaAppender {
	/* 
	 * 	val List<AttributeData> attributeDataList
	 * 	val List<XrefData> xrefDataList
	 * 	val List<ObjectPropertyData> objectPropertyDataList
	 * 	val List<OperationData> operationDataList
	 * 	val List<ElementData> elementDataList
	 */
	val ArchitectureTrace archTrace
	val ElementTrace elementTrace

	val StatemachineData statemachineData

	val expModelFactory = ExpressionModelFactory.eINSTANCE
	val sctModelFactory = StatechartModelFactory.eINSTANCE
	val ifModelFactory = InterfaceModelFactory.eINSTANCE

	val guid2gamma = <String, NamedElement>newHashMap
	val id2state = <Long, StateNode>newHashMap
	// val gamma2guid = <NamedElement,String>newHashMap
	val guid2statemachine = <String, NamedElement>newHashMap

	val inCNTRs = <Long, Integer>newHashMap
	val outCNTRs = <Long, Integer>newHashMap

	new(
		/* 
		 * List<AttributeData> attributeDataList, List<ElementData> elementDataList, List<XrefData> xrefDataList,
		 * List<ObjectPropertyData> objectPropertyDataList, List<OperationData> operationDataList,
		 */
		ArchitectureTrace archTrace,
		ElementTrace elementTrace
	) {
		/* 
		 * this.attributeDataList = attributeDataList
		 * this.xrefDataList = xrefDataList
		 * this.objectPropertyDataList = objectPropertyDataList
		 * this.operationDataList = operationDataList
		 * this.elementDataList = elementDataList
		 */
		this.statemachineData = elementTrace.statemachineData
		this.archTrace = archTrace
		this.elementTrace = elementTrace

	}

	def getByGUID(String GUID) {
		return guid2gamma.get(GUID)
	}

	def isRegionDefinition(XrefData data) {
		return data.name == "Partitions" && data.type == "element property"
	}

	def isRegionContainment(XrefData data) {
		return data.name == "CustomProperties" && data.type == "element property" &&
			data.description.contains("OwningRegion")
	}

	def createRegion(XrefData data) {
		val descList = data.description.split(";")
		val name = descList.get(1).replace("Name=", "")
		val guid = descList.get(3).replace("GUID=", "")
		val conatinerGuid = data.client

		val region = sctModelFactory.createRegion
		region.name = name.gammaName

		val container = getByGUID(conatinerGuid)
		if (container instanceof AsynchronousStatechartDefinition) {
			container.regions += region
			guid2statemachine.put(guid, container)
		} else if (container instanceof State) {
			container.regions += region
			guid2statemachine.put(guid, guid2statemachine.get(container))
		} else {
			throw new GammaTransformationException("Region cannot be mapped to container " + guid, container)
		}
	}

	def createStateContainment(XrefData data) {
		val descList = data.description.split(";")
		val conainingRegionGUID = descList.get(2).replace("@VALU=", "").replace("@ENDVALU", "")
		val stateGUID = data.client
		val state = guid2gamma.get(stateGUID) as State
		val region = guid2gamma.get(conainingRegionGUID) as Region
		region.stateNodes += state
		guid2statemachine.put(stateGUID, guid2statemachine.get(region.eContainer as NamedElement))
	}

	def createState(ElementData data) {
		val state = sctModelFactory.createState
		val name = data.name.gammaName
		state.name = name
		guid2gamma.put(data.GUID, state)
		id2state.put(data.elementID, state)
	}

	def processStatemachine(ElementData data) {
		val container = archTrace.get(elementTrace.get(data.conainerID))
		if (container instanceof AsynchronousStatechartDefinition) {
			guid2gamma.put(data.GUID, container)
		}
	}

	def createPseudostate(ElementData data) {
		var PseudoState state
		switch (data.nType as int) {
			case 3:
				state = sctModelFactory.createInitialState
			case 11:
				state = sctModelFactory.createChoiceState
			case 5:
				state = sctModelFactory.createDeepHistoryState
			case 4:
				throw new GammaTransformationException(
					"Final state node is not supported yet in state diagrams " + data.name)
			case 10:
				state = sctModelFactory.createMergeState
				
			default:
				throw new GammaTransformationException(
					"Unknown pseudostate type in state diagram name: " + data.name + "; NType=" + data.nType)
		}
		val name = data.name.gammaName
		state.name = name
		guid2gamma.put(data.GUID, state)
		id2state.put(data.elementID, state)
	}

	def labelSynchronizations(List<ElementData> elementData, List<ConnectorData> connectorData) {
		for (data : elementData) {
			inCNTRs.put(data.elementID, 0)
			outCNTRs.put(data.elementID, 0)
		}
		for (data : connectorData) {
			if (inCNTRs.containsKey(data.targetID)) {
				inCNTRs.put(data.targetID, inCNTRs.get(data.targetID) + 1)
			}
		}
		for (data : connectorData) {
			if (outCNTRs.containsKey(data.sourceID)) {
				outCNTRs.put(data.targetID, outCNTRs.get(data.sourceID) + 1)
			}
		}
	}

	def createForkJoin(ElementData data) {
		var PseudoState state
		if (outCNTRs.get(data.elementID) > 1 && inCNTRs.get(data.elementID) == 1) {
			state = sctModelFactory.createForkState
		} else if (outCNTRs.get(data.elementID) == 1 && inCNTRs.get(data.elementID) > 1) {
			state = sctModelFactory.createJoinState
		}
		state.name = data.name.gammaName
		guid2gamma.put(data.GUID, state)
		id2state.put(data.elementID, state)
		return state
	}

	def createTransition(ConnectorData data) {
		val transition = sctModelFactory.createTransition
		transition.sourceState = id2state.get(data.sourceID)
		transition.sourceState = id2state.get(data.targetID)
		if (!(id2state.get(data.sourceID) instanceof InitialState)) {
			transition.trigger = ifModelFactory.createAnyTrigger
		}
	}

	def execute() {

		for (elementID : elementTrace.allElementIDs) {
			if (archTrace.contains(elementTrace.get(elementID))) {
				val GUID = elementTrace.getGUID(elementID)
				val gammaElement = archTrace.get(elementTrace.get(elementID))
				guid2gamma.put(GUID, gammaElement)
			}
		}

		var shallRun = true

		for (data : statemachineData.statemachineData) {
			processStatemachine(data)
		}

		for (data : statemachineData.stateData) {
			createState(data)
		}

		for (data : statemachineData.pseudoStateData) {
			createState(data)
		}

		for (data : statemachineData.pseudoStateData) {
			createPseudostate(data)
		}

		labelSynchronizations(statemachineData.syncStateData, statemachineData.transitionData)

		for (data : statemachineData.syncStateData) {
			createForkJoin(data)
		}

		while (shallRun) {
			shallRun = false
			for (data : statemachineData.regionData) {
				if (guid2gamma.containsKey(data.client)) {
					data.createRegion
					shallRun = true
				}
			}
			for (data : statemachineData.regionContainmentData) {
				if (guid2gamma.containsKey(data.client)) {
					data.createStateContainment
					shallRun = true
				}
			}
		}

		for (data : statemachineData.transitionData) {
			createTransition(data)
		}

	}

}
