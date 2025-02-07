/********************************************************************************
 * Copyright (c) 2018-2024 Contributors to the Gamma project
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * SPDX-License-Identifier: EPL-1.0
 ********************************************************************************/
package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect

import hu.bme.mit.gamma.action.model.Action
import hu.bme.mit.gamma.action.model.ActionModelFactory
import hu.bme.mit.gamma.architecture.model.ValueType
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.AttributeData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ConnectorData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ElementData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.StatemachineData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.XrefData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.parser.ExpressionParser
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability.ElementTrace
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException
import hu.bme.mit.gamma.architecture.transformation.errors.GammaTransformationException
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.expression.model.NamedElement
import hu.bme.mit.gamma.expression.model.ReferenceExpression
import hu.bme.mit.gamma.expression.model.Type
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.statechart.interface_.TimeUnit
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition
import hu.bme.mit.gamma.statechart.statechart.InitialState
import hu.bme.mit.gamma.statechart.statechart.PseudoState
import hu.bme.mit.gamma.statechart.statechart.Region
import hu.bme.mit.gamma.statechart.statechart.State
import hu.bme.mit.gamma.statechart.statechart.StateNode
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.util.GammaEcoreUtil
import java.math.BigInteger
import java.util.List
import java.util.logging.Level
import java.util.logging.Logger

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.OperationData
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition
import hu.bme.mit.gamma.statechart.interface_.Persistency
import java.util.regex.Pattern
import hu.bme.mit.gamma.architecture.model.ArchitectureComponent

class GammaAppender {

	protected extension val GammaEcoreUtil gammaEcoreUtil = GammaEcoreUtil.INSTANCE

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
	val ExpressionParser parser

	val expModelFactory = ExpressionModelFactory.eINSTANCE
	val sctModelFactory = StatechartModelFactory.eINSTANCE
	val ifModelFactory = InterfaceModelFactory.eINSTANCE
	val actModelFactory = ActionModelFactory.eINSTANCE

	val guid2gamma = <String, NamedElement>newHashMap
	val id2state = <Long, StateNode>newHashMap
	// val gamma2guid = <NamedElement,String>newHashMap
	val guid2statemachine = <String, NamedElement>newHashMap
	val id2statemachine = <Long, AsynchronousStatechartDefinition>newHashMap

	val inCNTRs = <Long, Integer>newHashMap
	val outCNTRs = <Long, Integer>newHashMap

	protected Logger logger = Logger.getLogger("GammaLogger");

	var int psNameCNTR = 0
	var int toNameCNTR = 0
	var int regionCNTR = 0

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
		this.parser = new ExpressionParser(archTrace)

	}

	def getByGUID(String GUID) {
		return guid2gamma.get(GUID)
	}

	def getRegionGUID(XrefData data) {
		val descList = data.description.split(";")
		val conainingRegionGUID = descList.get(2).replace("@VALU=", "").replace("@ENDVALU", "")
		return conainingRegionGUID
	}

	def isRegionDefinition(XrefData data) {
		return data.name == "Partitions" && data.type == "element property"
	}

	def isRegionContainment(XrefData data) {
		return data.name == "CustomProperties" && data.type == "element property" &&
			data.description.contains("OwningRegion")
	}

	def appendEmmptySct(StatechartDefinition sct) {
		val main_region = sctModelFactory.createRegion
		main_region.name = "main_region"
		val init = sctModelFactory.createInitialState
		init.name = "init_state"
		val state1 = sctModelFactory.createState
		state1.name = "operational"
		main_region.stateNodes += init
		main_region.stateNodes += state1
		sct.regions += main_region
		val tr1 = sctModelFactory.createTransition
		tr1.sourceState = init
		tr1.targetState = state1
		sct.transitions += tr1
	}

	def createRegion(XrefData data) {

		val descriptionList = data.description.split("@ENDPAR;")

		for (descrition : descriptionList) {
			val descList = descrition.split(";")
			val name = descList.get(1).replace("Name=", "")
			val guid = descList.get(3).replace("GUID=", "")
			val conatinerGuid = data.client

			val region = sctModelFactory.createRegion
			region.name = name.gammaName + regionCNTR++
			guid2gamma.put(guid, region)

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
	}

	def createStateContainment(XrefData data) {
		val conainingRegionGUID = data.regionGUID
		val stateGUID = data.client
		val state = guid2gamma.get(stateGUID) as StateNode
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
		id2statemachine.put(data.elementID, id2statemachine.get(data.conainerID))
	}

	def processStatemachine(ElementData data) {
		val element=elementTrace.get(data.conainerID)
		if (archTrace.containsHWErrorSct(element)) {
			val errorSct = archTrace.getHWErrorSct(element as ArchitectureComponent)
			guid2gamma.put(data.GUID, errorSct)
			id2statemachine.put(new Long(data.elementID), errorSct)
			return
		}
		val container = archTrace.get(element)
		if (container instanceof AsynchronousStatechartDefinition) {
			
			// val region = sctModelFactory.createRegion
			// region.name = data.name.gammaName
			// container.regions+=region
			guid2gamma.put(data.GUID, container)
			id2statemachine.put(new Long(data.elementID), container)
		//handling hw error statemachines
		
		} else{
			// logger.log(Level.WARNING, '''Container od statemachine is not transformed to Gamma GUID=«data.GUID»''')
			throw new GammaTransformationException("Block can have statemachine if it contains no internal structure",
				container)
		}
	}

	def processAttributeData(AttributeData data) {

		if (id2statemachine.containsKey(data.element_id)) {
			val sct = id2statemachine.get(data.element_id)
			val varDecl = expModelFactory.createVariableDeclaration
			varDecl.name = data.name.gammaName

			var Type type = null
			if (data.type_id == -1) {
				// throw new UnsupportedOperationException("UML primitive type library not supported yet for attributes"+data.name)
				switch data.type {
					case "int": type = expModelFactory.createIntegerTypeDefinition
					case "Integer": type = expModelFactory.createIntegerTypeDefinition
					case "byte": type = expModelFactory.createIntegerTypeDefinition
					case "short": type = expModelFactory.createIntegerTypeDefinition
					case "long": type = expModelFactory.createIntegerTypeDefinition
					case "double": type = expModelFactory.createDecimalTypeDefinition
					case "boolean": type = expModelFactory.createBooleanTypeDefinition
					default: type = expModelFactory.createDecimalTypeDefinition
				}
			} else {
				type = archTrace.get(elementTrace.get(data.type_id) as ValueType).clone()
			}

			varDecl.type = type
			sct.variableDeclarations += varDecl
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
					"Unknown pseudostate type in state diagram name: " + data.name + "; NType=" + data.nType, state)
		}
		val name = data.name.gammaName + psNameCNTR++
		state.name = name
		guid2gamma.put(data.GUID, state)
		id2state.put(data.elementID, state)
		id2statemachine.put(data.elementID, id2statemachine.get(data.conainerID))
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
		id2statemachine.put(data.elementID, id2statemachine.get(data.conainerID))
		return state
	}

	def createTransition(ConnectorData data) {
		if (id2statemachine.containsKey(data.sourceID)) {
			val statemachine = id2statemachine.get(data.sourceID)
			var sourceState = id2state.get(data.sourceID)
			var targetState = id2state.get(data.targetID)
			val effects = transformEffect(statemachine, data.PDATA3)
			if (!(sourceState instanceof InitialState)) {
				if (data.PDATA1.contains(",")) {
					val merge = sctModelFactory.createMergeState
					val reg = (targetState.eContainer as Region)
					reg.stateNodes += merge
					merge.name = "autoMerge" + psNameCNTR++
					val tr1 = sctModelFactory.createTransition
					tr1.sourceState = merge
					tr1.targetState = targetState
					statemachine.transitions += tr1
					targetState = merge
				}
				val triggerStrList = data.PDATA1.split('''\,''')
				for (triggerStr : triggerStrList) {
					val transition = sctModelFactory.createTransition
					transition.sourceState = sourceState
					transition.targetState = targetState
					transition.guard = parser.eval(data.PDATA2, statemachine)
					transition.effects += effects
					if (triggerStr.blank) {
					} else if (triggerStr == "any") {
						transition.trigger = ifModelFactory.createAnyTrigger
					} else if (triggerStr == "cycle") {
						transition.trigger = sctModelFactory.createOnCycleTrigger
					} else if (triggerStr.contains("after")) {
						val timeOut = sctModelFactory.createTimeoutDeclaration
						timeOut.name = "timeout" + transition.sourceState.name + toNameCNTR++
						statemachine.timeoutDeclarations += timeOut
						val trigger = ifModelFactory.createEventTrigger
						val ref = sctModelFactory.createTimeoutEventReference
						ref.timeout = timeOut
						trigger.eventReference = ref
						transition.trigger = trigger
						if (sourceState instanceof State) {
							// val timeList = triggerStr.split('''\s+''')
							val timeValue = expModelFactory.createIntegerLiteralExpression
							timeValue.value = new BigInteger(triggerStr.replaceAll('''(after|ms|s|h|\s)''', ""))
							val timeUnit = switch triggerStr.replaceAll('''(after|\d|\s)''',"") {
								case "ms":
									TimeUnit.MILLISECOND
								case "s":
									TimeUnit.SECOND
								case "h":
									TimeUnit.HOUR
								default:
									throw new ArchitectureException("Time trigger cannot be parsed : " + triggerStr)
							}
							val act = sctModelFactory.createSetTimeoutAction
							val t = ifModelFactory.createTimeSpecification
							t.value = timeValue
							t.unit = timeUnit
							act.time = t
							act.timeoutDeclaration = timeOut
							sourceState.entryActions += act
						} else {
							throw new ArchitectureException(
								"Source state of transition shall not be pseudostate Source ID=" + data.sourceID)
						}
					} else if (triggerStr.replace(" ", "").contains("change(")) {
						val fpRefList = triggerStr.replace(" ", "").replace("change(", "").replace(")", "").
							split('''\.''')
						val portName = fpRefList.get(0)
						val fpName = fpRefList.get(1)
						val eventTrig = ifModelFactory.createEventTrigger

						if (fpName.equals("any")) {
							val ref = sctModelFactory.createAnyPortEventReference
							val portIt = statemachine.ports.filter[p|p.name.contains(portName)].iterator
							if (!portIt.hasNext()) {
								throw new GammaTransformationException(
									"Trigger Parser Exception; Port reference cannot be found: '" + portName +
										"' in '" + triggerStr + "'", statemachine);
							}
							ref.port = portIt.next
							eventTrig.eventReference = ref
						} else {
							val ref = sctModelFactory.createPortEventReference
							val portIt = statemachine.ports.filter[p|p.name.contains(portName)].iterator
							if (!portIt.hasNext()) {
								throw new GammaTransformationException(
									"Trigger Parser Exception; Port reference cannot be found: '" + portName +
										"' in '" + triggerStr + "'", statemachine);
							}
							ref.port = portIt.next
							val eventIt = ref.port.allEvents.filter [ e |
								e.name.equals("changeOf" + fpName.toFirstUpper)
							].iterator
							if (!eventIt.hasNext()) {
								throw new GammaTransformationException(
									"Trigger Parser Exception; Event reference cannot be found '" + "changeOf" +
										fpName.toFirstUpper + "' in '" + triggerStr + "'", statemachine);
							}
							ref.event = eventIt.next
							eventTrig.eventReference = ref
						}
						transition.trigger = eventTrig

					} else if (triggerStr.replace(" ", "").contains(".any")) {
						val fpRefList = triggerStr.replace(" ", "").split('''\.''')
						val portName = fpRefList.get(0)
						val eventName = fpRefList.get(1)
						val eventTrig = ifModelFactory.createEventTrigger
						val ref = sctModelFactory.createAnyPortEventReference
						val portIt = statemachine.ports.filter[p|p.name.contains(portName)].iterator
						if (!portIt.hasNext()) {
							throw new GammaTransformationException(
								"Trigger Parser Exception; Port reference cannot be found: '" + portName + "' in '" +
									triggerStr + "'", statemachine);
						}
						ref.port = portIt.next
						eventTrig.eventReference = ref
						transition.trigger = eventTrig
					} else if (triggerStr.contains(".")) {
						val fpRefList = triggerStr.replace(" ", "").split('''\.''')
						val portName = fpRefList.get(0)
						val eventName = fpRefList.get(1)
						val eventTrig = ifModelFactory.createEventTrigger
						val ref = sctModelFactory.createPortEventReference
						val portIt = statemachine.ports.filter[p|p.name.contains(portName)].iterator
						if (!portIt.hasNext()) {
							throw new GammaTransformationException(
								"Trigger Parser Exception; Port reference cannot be found: '" + portName + "' in '" +
									triggerStr + "'", statemachine);
						}
						ref.port = portIt.next
						val eventIt = ref.port.allEvents.filter [ e |
							e.name.equals(eventName)
						].iterator
						if (!eventIt.hasNext()) {
							throw new GammaTransformationException(
								"Trigger Parser Exception; Event reference cannot be found '" + eventName + "' in '" +
									triggerStr + "'", statemachine);
						}
						ref.event = eventIt.next
						eventTrig.eventReference = ref
						transition.trigger = eventTrig
					} else {
						val signalRefStr = triggerStr.replace(" ", "").gammaName
						val eventIt = statemachine.inputEvents.filter[e|e.name == signalRefStr].iterator
						if (!eventIt.hasNext) {
							throw new GammaTransformationException(
								"Statemachine signal trigger cannot be found: " + triggerStr.gammaName, statemachine)
						}
						val event = eventIt.next
						val portList = statemachine.ports.filter[p|p.inputEvents.contains(event)].toList
						if (portList.length != 1) {
							throw new GammaTransformationException(
								"Statemachine signal trigger source port cannot be found: " + triggerStr.gammaName +
									", " + portList.length + "  matches", statemachine)
						}
						val eventTrig = ifModelFactory.createEventTrigger
						val ref = sctModelFactory.createPortEventReference
						ref.port = portList.get(0)
						ref.event = event
						eventTrig.eventReference = ref
						transition.trigger = eventTrig
					}
					statemachine.transitions += transition
				}
			} else {
				val transition = sctModelFactory.createTransition
				transition.sourceState = id2state.get(data.sourceID)
				transition.targetState = id2state.get(data.targetID)
				transition.guard = parser.eval(data.PDATA2, statemachine);
				transition.effects += effects
				statemachine.transitions += transition
			}
		}
	}

	def transformEffect(AsynchronousStatechartDefinition statechart, String effectString) {

		val effects = <Action>newLinkedList

		if (effectString.isBlank) {
			return effects
		}

		var effectStringList = effectString.replaceAll("(\\r|\\n|\\r\\n)+", "").split(";");
		for (actionStr : effectStringList) {
			if (actionStr.isBlank) {
			} else if (actionStr.contains(":=")) {
				val statementList = actionStr.split(":=")
				val lhsStr = statementList.get(0)
				val rhsStr = statementList.get(1)
				if (lhsStr.contains(".")) {
					val fpList = lhsStr.split("\\.")
					val portName = fpList.get(0).replace(" ", "")
					val fpName = fpList.get(1).replace(" ", "")
					val exp = parser.eval(rhsStr, statechart)
					val act = sctModelFactory.createRaiseEventAction
					val portList = statechart.allPortsWithOutput.filter[p|p.name.contains(portName.gammaName)].toList
					if (portList.length > 1) {
						throw new GammaTransformationException('''Port reference "«portName»" in action "«actionStr»" is ambigous ''',
							statechart)
					}
					if (portList.length == 0) {
						throw new GammaTransformationException('''Port reference "«portName»" in action "«actionStr»" is not found ''',
							statechart)
					}
					val port = portList.get(0)
					act.port = port
					val eventList = act.port.allEvents.filter[e|e.name.equals("changeOf" + fpName.toFirstUpper)].toList
					if (eventList.length > 1) {
						throw new GammaTransformationException('''Event reference "«fpName»" in action "«actionStr»" is ambigous ''',
							statechart)
					}
					if (eventList.length == 0) {
						throw new GammaTransformationException('''Event reference "«fpName»" in action "«actionStr»" is not found ''',
							statechart)
					}
					act.event = eventList.get(0)
					act.arguments += exp
					val varRef = parser.searchVariable("var_" + port.name + "_" + fpName)
					val ifExp = actModelFactory.createIfStatement
					val eqExp = expModelFactory.createInequalityExpression
					val branch = actModelFactory.createBranch
					val block = actModelFactory.createBlock
					val setVar = actModelFactory.createAssignmentStatement
					setVar.lhs = varRef.clone
					setVar.rhs = exp.clone
					block.actions += setVar
					eqExp.leftOperand = varRef
					eqExp.rightOperand = exp.clone
					block.actions += act
					branch.action = block
					branch.guard = eqExp
					ifExp.conditionals += branch
					effects += ifExp
				} else {
					val act = actModelFactory.createAssignmentStatement
					act.lhs = parser.eval(statementList.get(0), statechart) as ReferenceExpression
					act.rhs = parser.eval(statementList.get(1), statechart)
					effects += act
				}

			} else if (actionStr.matches("^(raise|send).+")) {
				// val split_reg='''\\('''
				// val eventStrList = actionStr.replaceAll("^(raise|send)", "").split(split_reg)
				if (!actionStr.matches('''.+\..+\(.*\)''')) {
					throw new GammaTransformationException("Raised event must end with '( ... )' : " + actionStr,
						statechart)
				}
				val actionStr2 = actionStr.replaceAll('''^(raise|send)\s*''', "")
				val eventStr = actionStr2.substring(0, actionStr2.indexOf('(')) // eventStrList.get(0)
				val argStr = actionStr2.substring(actionStr2.indexOf('(') + 1, actionStr2.indexOf(')')) // effectStringList.get(1).replace(')', '')
				val portName = eventStr.substring(0, eventStr.indexOf('.')).replace(" ", '')
				val eventName = eventStr.substring(eventStr.indexOf('.') + 1).replace(" ", '')

				val act = sctModelFactory.createRaiseEventAction
				val portList = statechart.ports.filter[p|p.name.contains(portName)].toList
				if (portList.size != 1) {
					throw new GammaTransformationException('''Port not found for raised event '«portName»' statechart ports are «statechart.ports.map[p|p.name].toList» matches are «portList.map[p|p.name]»''',
						statechart)
				}
				act.port = portList.get(0)
				val eventList = act.port.allEvents.filter[e|e.name.equals(eventName)].toList
				if (eventList.size != 1) {
					throw new GammaTransformationException('''Port event not found for raised event '«portName».«eventName»' statechart port events are «act.port.allEvents.map[e|e.name].toList» matches are «eventList.map[e|e.name]»''',
						statechart)
				}
				act.event = eventList.get(0)

				if (!argStr.blank) {
					val exp = parser.eval(argStr, statechart)
					act.arguments += exp
				}
				effects += act

			} else {
				throw new GammaTransformationException('''Action "«actionStr»" in Effect "«effectString»" cannot be parsed''',
					statechart)
			}
		}

		return effects

	}

	def processOperation(OperationData data) {
		if (id2state.containsKey(data.element_id) && id2statemachine.containsKey(data.element_id)) {
			if (data.type == "entry") {
				val state = id2state.get(data.element_id) as State
				val sct = id2statemachine.get(data.element_id)
				state.entryActions += transformEffect(sct, data.name)
			} else if (data.type == "exit") {
				val state = id2state.get(data.element_id) as State
				val sct = id2statemachine.get(data.element_id)
				state.exitActions += transformEffect(sct, data.name)
			}

		}
	}

	def execute() {

		for (elementID : elementTrace.allElementIDs) {
			if (archTrace.contains(elementTrace.get(elementID))) {
				val GUID = elementTrace.getGUID(elementID)
				val element = elementTrace.get(elementID)
				val gammaElement = archTrace.get(element)
				guid2gamma.put(GUID, gammaElement)
				if (gammaElement instanceof AsynchronousStatechartDefinition) {
					id2statemachine.put(elementID, gammaElement)
				} else if (element instanceof ArchitectureComponent) {
					if (archTrace.containsHWErrorSct(element)) {
						val errorSct = archTrace.getHWErrorSct(element)
						guid2gamma.put(GUID, errorSct)
					}
				}
			}
		}

		for (data : statemachineData.statemachineData) {
			processStatemachine(data)
		}

		var shallRun = true
		val processedStates = <ElementData>newHashSet
		while (shallRun) {
			shallRun = false
			for (data : statemachineData.stateData) {
				if ((!processedStates.contains(data)) && id2statemachine.containsKey(data.conainerID)) {
					createState(data)
					processedStates.add(data)
					shallRun = true
				}
			}
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

		val regionDataBuff = <XrefData>newHashSet
		val regionContDataBuff = <XrefData>newHashSet

		shallRun = true
		while (shallRun) {
			shallRun = false
			for (data : statemachineData.regionData) {
				if (guid2gamma.containsKey(data.client) && (!regionDataBuff.contains(data))) {
					data.createRegion
					shallRun = true
					regionDataBuff.add(data)
				}
			}
			for (data : statemachineData.regionContainmentData) {
				val regGUID = data.regionGUID
				if (guid2gamma.containsKey(regGUID) && (!regionContDataBuff.contains(data))) {
					data.createStateContainment
					shallRun = true
					regionContDataBuff.add(data)
				}
			}
		}

		for (data : statemachineData.attributeData) {
			processAttributeData(data)
		}

		for (sct_pkg : archTrace.primitiveFunctionPackages) {
			val sct = sct_pkg.allComponents.get(0) as AsynchronousStatechartDefinition
			if (sct.transitions.empty) {
				for (port : sct.ports) {
					for (pevent : port.outputEvents) {
						if (pevent.persistency == Persistency.PERSISTENT) {
							for (param : pevent.parameterDeclarations) {
								val varDecl = expModelFactory.createVariableDeclaration
								varDecl.name = "var_" + port.name + "_" + param.name
								varDecl.type = param.type.clone
								sct.variableDeclarations += varDecl
							}
						}
					}
				}
			}
		}

		for (data : statemachineData.transitionData) {
			createTransition(data)
		}

		for (data : statemachineData.operationData) {
			processOperation(data)
		}

		for (sct_pkg : archTrace.primitiveFunctionPackages) {
			val sct = sct_pkg.allComponents.get(0) as AsynchronousStatechartDefinition
			if (sct.regions.empty) {
				appendEmmptySct(sct)
			}
		}
		for (sct_pkg : archTrace.getHWErrorStatechartPackages) {
			val sct = sct_pkg.allComponents.get(0) as AsynchronousStatechartDefinition
			if (sct.regions.empty) {
				appendEmmptySct(sct)
			}
		}

	}

}
