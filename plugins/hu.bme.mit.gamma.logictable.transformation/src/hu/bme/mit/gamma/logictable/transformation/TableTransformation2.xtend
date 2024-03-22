package hu.bme.mit.gamma.logictable.transformation

import hu.bme.mit.gamma.statechart.interface_.Interface
import java.util.List
import java.io.FileInputStream
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.CellType
import java.util.ArrayList
import java.io.FileNotFoundException
import java.io.IOException
import hu.bme.mit.gamma.dialog.DialogUtil
import java.util.Iterator
import org.apache.poi.ss.usermodel.Cell
import hu.bme.mit.gamma.statechart.interface_.Persistency
import hu.bme.mit.gamma.expression.model.EnumerableTypeDefinition
import hu.bme.mit.gamma.expression.model.TypeDeclaration
import hu.bme.mit.gamma.expression.model.EnumerationTypeDefinition
import hu.bme.mit.gamma.expression.model.TypeReference
import hu.bme.mit.gamma.expression.model.IntegerTypeDefinition
import hu.bme.mit.gamma.expression.model.RationalTypeDefinition
import hu.bme.mit.gamma.expression.model.DecimalTypeDefinition
import org.apache.poi.ss.usermodel.Row
import java.io.File
import java.util.Set
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy
import java.time.LocalDateTime
import hu.bme.mit.gamma.statechart.interface_.Event
import static extension hu.bme.mit.gamma.expression.derivedfeatures.ExpressionModelDerivedFeatures.*
import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
import static extension hu.bme.mit.gamma.expression.util.ExpressionSerializer.*
import hu.bme.mit.gamma.expression.util.ExpressionSerializer
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.action.model.ActionModelFactory
import hu.bme.mit.gamma.statechart.statechart.TransitionPriority
import hu.bme.mit.gamma.statechart.statechart.SchedulingOrder
import hu.bme.mit.gamma.statechart.interface_.Port
import org.eclipse.xtend.lib.macro.declaration.EnumerationTypeDeclaration
import java.math.BigInteger
import hu.bme.mit.gamma.expression.model.BooleanTypeDefinition
import hu.bme.mit.gamma.util.GammaEcoreUtil
import java.math.BigDecimal
import hu.bme.mit.gamma.architecture.transformation.errors.GammaTransformationException

class TableTransformation2 {

	private FileInputStream excelFile = null

	val expModelFactory = ExpressionModelFactory.eINSTANCE
	val sctModelFactory = StatechartModelFactory.eINSTANCE
	val ifModelFactory = InterfaceModelFactory.eINSTANCE
	val actModelFactory = ActionModelFactory.eINSTANCE

	protected extension val GammaEcoreUtil gammaEcoreUtil = GammaEcoreUtil.INSTANCE

	def isBlank(Cell cell) {
		if (cell === null || cell.getCellType() == CellType.BLANK) {
			return true
		}
		if (cell.cellType == CellType.STRING) {
			if (cell.stringCellValue == "") {
				return true
			}
		}
		return false
	}

	protected def readIn(Row row) {
		var cells = new ArrayList<String>()
		var i = 0
		while (true) {
			var cell = row.getCell(i, MissingCellPolicy.RETURN_BLANK_AS_NULL)
			if (isBlank(cell)) {
				return cells
			}
			if (cell.cellType == CellType.STRING){
				cells.add(cell.stringCellValue)
			}else if (cell.cellType == CellType.NUMERIC){
				cells.add((cell.numericCellValue as int).toString)
			}else {
				throw new GammaTransformationException("Table Transformation Exception: Unsupported cell type in the header "+cell.cellType.toString)
			}
			i++
		}
	}

	protected def readOut(Row row) {
		var cell = null as Cell
		var cells = new ArrayList<String>()
		var i = 0
		do {
			cell = row.getCell(i)
			i++
		} while (!isBlank(cell));
		while (true) {
			cell = row.getCell(i)
			if (cell === null || cell.getCellType() == CellType.BLANK) {
				return cells
			}
			if (cell.cellType == CellType.STRING) {
				if (cell.stringCellValue == "") {
					return cells
				}
			}
			cells.add(cell.stringCellValue)
			i++
		}
	}

	def generate(String file, List<Interface> interfaces, AsynchronousStatechartDefinition statechart) {

		statechart.transitionPriority = TransitionPriority.ORDER_BASED
		statechart.schedulingOrder = SchedulingOrder.TOP_DOWN
		val mainRegion = sctModelFactory.createRegion
		mainRegion.name = "Table_Main_Region"
		statechart.regions += mainRegion
		val initState = sctModelFactory.createInitialState
		initState.name = "Table_Initial_State"
		val mainState = sctModelFactory.createState
		mainState.name = "Table_Main_State"
		val choiceState = sctModelFactory.createChoiceState
		choiceState.name = "Table_Choice_State"
		mainRegion.stateNodes += initState
		mainRegion.stateNodes += mainState
		mainRegion.stateNodes += choiceState
		val initTrans = sctModelFactory.createTransition
		initTrans.sourceState = initState
		initTrans.targetState = mainState
		statechart.transitions += initTrans
		val anyTrans = sctModelFactory.createTransition
		anyTrans.sourceState = mainState
		anyTrans.targetState = choiceState
		anyTrans.trigger = ifModelFactory.createAnyTrigger
		statechart.transitions += anyTrans

		var inPortNames = new ArrayList<String>()
		var outPortNames = new ArrayList<String>()

		var inPortIfNames = new ArrayList<String>()
		var outPortIfNames = new ArrayList<String>()
		var outEvents = new ArrayList<Event>()
		var isFirst = true
		try {
			excelFile = new FileInputStream(file)
			val workbook = new XSSFWorkbook(excelFile);
			val datatypeSheet = workbook.getSheetAt(0);
			val compName = datatypeSheet.sheetName
			var rowIterator = datatypeSheet.iterator();

			var headerRow = rowIterator.next
			inPortNames = readIn(headerRow)
			outPortNames = readOut(headerRow)
			var ifRow = rowIterator.next
			inPortIfNames = readIn(ifRow)
			outPortIfNames = readOut(ifRow)

			val inPorts = new ArrayList<Port>()
			val outPorts = new ArrayList<Port>()
			for (k : 0 .. inPortNames.length - 1) {
				val portName = inPortNames.get(k)
				val portIFName = inPortIfNames.get(k)
				val port = statechart.ports.filter [ p |
					p.name.equals(portName.simplifyName + portIFName.simplifyName.toFirstUpper + "In")
				].iterator
				if (!port.hasNext) {
					throw new GammaTransformationException(
						"Exception occurred during table '" + portName.simplifyName + portIFName +
							"In' not found in Excel file: " + file,statechart)
				}
				inPorts.add(port.next)
			}
			for (k : 0 .. outPortNames.length - 1) {
				val portName = outPortNames.get(k)
				val portIFName = outPortIfNames.get(k)
				val port = statechart.ports.filter [ p |
					p.name.equals(portName.simplifyName + portIFName.simplifyName.toFirstUpper + "Out")
				].iterator
				if (!port.hasNext) {
					throw new GammaTransformationException(
						"Exception occurred during table '" + portName.simplifyName + portIFName +
							"Out' not found in Excel file: " + file,statechart)
				}
				outPorts.add(port.next)
			}

			while (rowIterator.hasNext) {
				val row = rowIterator.next
				val transition = sctModelFactory.createTransition
				transition.sourceState = choiceState
				transition.targetState = mainState
				statechart.transitions += transition

				val guardExpr = expModelFactory.createAndExpression

				val cellIterator = row.cellIterator

				for (j : 0 .. inPortNames.length - 1) {
					val cell = cellIterator.next
					val inPort = inPorts.get(j)
					if (!cell.blank) {
						if (cell.cellType == CellType.STRING) {
							val value = cell.stringCellValue.simplifyName
							if (!(value.isBlank || value == "any" || value == "ANY" || value == "N/A" ||
								value == "NA")) {
								val paramIt = inPort.inputEvents.flatMap[e|e.parameterDeclarations].filter [ p |
									p.typeDefinition instanceof EnumerableTypeDefinition
								].iterator
								if (! paramIt.hasNext) {
									throw new GammaTransformationException(
										"Table transformation error, event enumeration parameter cannot be found: " +
											inPort.name + " in Excel file: " + file,statechart)
								}
								val param = paramIt.next
								val ref = ifModelFactory.createEventParameterReferenceExpression
								ref.port = inPort
								ref.event = inPort.inputEvents.filter [ e |
									!e.parameterDeclarations.filter [ p |
										p.typeDefinition instanceof EnumerableTypeDefinition
									].empty
								].iterator.next
								ref.parameter = param
								val eqExpr = expModelFactory.createEqualityExpression
								eqExpr.leftOperand = ref
								val enumLitExp = expModelFactory.createEnumerationLiteralExpression
								val typeRefExep = expModelFactory.createTypeReference
								typeRefExep.reference = param.typeDefinition.typeDeclaration
								enumLitExp.typeReference = typeRefExep
								val litIt = (param.typeDefinition as EnumerationTypeDefinition).literals.filter [ l |
									l.name.equals(value)
								].iterator
								if (! litIt.hasNext) {
									throw new GammaTransformationException(
										"Table transformation error, enumeration literal cannot be found of event: " +
											inPort.name + "." + ref.event.name + ", literal: " + value +
											" in Excel file: " + file,statechart)
								}
								enumLitExp.reference = litIt.next
								eqExpr.rightOperand = enumLitExp
								guardExpr.operands += eqExpr
							}
						}
						if (cell.cellType == CellType.NUMERIC) {
							val value = cell.numericCellValue
							val paramIt = inPort.inputEvents.flatMap[e|e.parameterDeclarations].filter [ p |
								p.typeDefinition instanceof IntegerTypeDefinition
							].iterator
							if (! paramIt.hasNext) {
								throw new GammaTransformationException(
									"Table transformation error, event integer parameter cannot be found: " + value +
										" in Excel file: " + file,statechart)
							}
							val param = paramIt.next
							val ref = ifModelFactory.createEventParameterReferenceExpression
							ref.port = inPort
							ref.event = inPort.inputEvents.filter [ e |
								!e.parameterDeclarations.filter[p|p.typeDefinition instanceof IntegerTypeDefinition].
									empty
							].iterator.next
							ref.parameter = param
							val eqExpr = expModelFactory.createEqualityExpression
							if (value == (value as int)) {
								val intLitExp = expModelFactory.createIntegerLiteralExpression
								intLitExp.value = new BigInteger((value as int).toString)
								eqExpr.rightOperand = intLitExp
							} else {
								val decLitExp = expModelFactory.createDecimalLiteralExpression
								decLitExp.value = new BigDecimal(value.toString)
								eqExpr.rightOperand = decLitExp
							}
							eqExpr.leftOperand = ref
							guardExpr.operands += eqExpr

						}
						if (cell.cellType == CellType.BOOLEAN) {
							val value = cell.booleanCellValue
							val paramIt = inPort.inputEvents.flatMap[e|e.parameterDeclarations].filter [ p |
								p.typeDefinition instanceof BooleanTypeDefinition
							].iterator
							if (! paramIt.hasNext) {
								throw new GammaTransformationException(
									"Table transformation error, event Boolean parameter cannot be found: " + value +
										" in Excel file: " + file,statechart)
							}
							val param = paramIt.next
							val ref = ifModelFactory.createEventParameterReferenceExpression
							ref.port = inPort
							ref.event = inPort.inputEvents.filter [ e |
								!e.parameterDeclarations.filter[p|p.typeDefinition instanceof BooleanTypeDefinition].
									empty
							].iterator.next
							ref.parameter = param
							if (value) {
								guardExpr.operands += ref
							} else {
								val notExpr = expModelFactory.createNotExpression
								notExpr.operand = ref
								guardExpr.operands += ref
							}
						}
					}
				}
				if (guardExpr.operands.length == 1) {
					transition.guard = guardExpr.operands.get(0)
				} else if (!guardExpr.operands.empty) {
					transition.guard = guardExpr
				} else {
					transition.guard = expModelFactory.createElseExpression()
				}
				for (j : 0 .. outPortNames.length - 1) {
					val cell = cellIterator.next
					val outPort = outPorts.get(j)

					if (cell.cellType == CellType.STRING) {
						val value = cell.stringCellValue
						val paramIt = outPort.outputEvents.flatMap[e|e.parameterDeclarations].filter [ p |
							p.typeDefinition instanceof EnumerableTypeDefinition
						].iterator
						if (! paramIt.hasNext) {
							throw new GammaTransformationException(
								"Table transformation error, event enumeration parameter cannot be found: " +
									outPort.name + " in Excel file: " + file,statechart)
						}
						val param = paramIt.next
						val raiseAct = sctModelFactory.createRaiseEventAction
						raiseAct.port = outPort
						raiseAct.event = outPort.outputEvents.filter [ e |
							!e.parameterDeclarations.filter[p|p.typeDefinition instanceof EnumerableTypeDefinition].
								empty
						].iterator.next
						val enumLitExp = expModelFactory.createEnumerationLiteralExpression
						val typeRefExep = expModelFactory.createTypeReference
						typeRefExep.reference = param.typeDefinition.typeDeclaration
						enumLitExp.typeReference = typeRefExep
						val enumLitREfIt = (param.typeDefinition as EnumerationTypeDefinition).literals.filter [ l |
							l.name.equals(value)
						].iterator
						if (! enumLitREfIt.hasNext) {
							throw new GammaTransformationException(
								"Table transformation error, enumeration literal cannot be found of event: " +
									outPort.name + "." + raiseAct.event.name + ", literal: " + value +
									" in Excel file: " + file,statechart)
						}
						enumLitExp.reference = enumLitREfIt.next
						raiseAct.arguments += enumLitExp
						transition.effects += raiseAct;
						if (isFirst) {
							initTrans.effects += raiseAct.clone
						}
					}
					if (cell.cellType == CellType.NUMERIC) {
						val value = cell.numericCellValue
						val raiseAct = sctModelFactory.createRaiseEventAction
						raiseAct.port = outPort
						val eventIt = outPort.outputEvents.filter [ e |
							!e.parameterDeclarations.filter[p|p.typeDefinition instanceof IntegerTypeDefinition].empty
						].iterator
						if (! eventIt.hasNext) {
							throw new GammaTransformationException(
								"Table transformation error, event integer parameter cannot be found: " + value +
									" in Excel file: " + file,statechart)
						}

						raiseAct.event = eventIt.next
						if (value == (value as int)) {
							val intLitExp = expModelFactory.createIntegerLiteralExpression
							intLitExp.value = new BigInteger((value as int).toString)
							raiseAct.arguments += intLitExp
						} else {
							val decLitExp = expModelFactory.createDecimalLiteralExpression
							decLitExp.value = new BigDecimal(value.toString)
							raiseAct.arguments += decLitExp
						}
						transition.effects += raiseAct;
						if (isFirst) {
							initTrans.effects += raiseAct.clone
						}

					}
					if (cell.cellType == CellType.BOOLEAN) {
						val value = cell.booleanCellValue
						val raiseAct = sctModelFactory.createRaiseEventAction
						raiseAct.port = outPort
						val eventIt = outPort.outputEvents.filter [ e |
							!e.parameterDeclarations.filter[p|p.typeDefinition instanceof BooleanTypeDefinition].empty
						].iterator
						if (! eventIt.hasNext) {
							throw new GammaTransformationException(
								"Table transformation error, event boolean parameter cannot be found: " + value +
									" in Excel file: " + file,statechart)
						}
						raiseAct.event = eventIt.next
						if (value) {
							raiseAct.arguments += expModelFactory.createTrueExpression
						} else {
							raiseAct.arguments += expModelFactory.createFalseExpression
						}
						transition.effects += raiseAct;
						if (isFirst) {
							initTrans.effects += raiseAct.clone
						}
					}

				}
				isFirst = false
			}
			excelFile.close
		} catch (Exception e) {
			e.printStackTrace();
			excelFile.close
			throw e
		// DialogUtil.showError(e.message)
		// return e.message
		}
	}

	static def simplifyName(String name) {
		if (name == "anonym") {
			return ""
		}
		var simplifiedName = name.replace(".", "_")
		// replace formbidden characters
		val forbiddenCharacters = #["-", ":", " ", "(", ")", "{", "}", "[", "]", "{", "}", ",", ";", ";", "%", "!", "&",
			"/", "+"]
		for (c : forbiddenCharacters) {
			simplifiedName = simplifiedName.replace(c, "_")
		}
		// remove double '_'-s
		while (simplifiedName.contains("__")) {
			simplifiedName = simplifiedName.replaceAll("__", "_")
		}
		// first digit character checking
		if (simplifiedName.matches("^[0-9]")) {
			simplifiedName = "_" + simplifiedName
		}
		return simplifiedName
	}

}
