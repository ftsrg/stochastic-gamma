/**
 * Copyright (c) 2018-2024 Contributors to the Gamma project
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * SPDX-License-Identifier: EPL-1.0
 */
package hu.bme.mit.gamma.logictable.transformation;

import com.google.common.base.Objects;
import hu.bme.mit.gamma.action.model.Action;
import hu.bme.mit.gamma.action.model.ActionModelFactory;
import hu.bme.mit.gamma.action.model.AssignmentStatement;
import hu.bme.mit.gamma.action.model.Block;
import hu.bme.mit.gamma.action.model.Branch;
import hu.bme.mit.gamma.action.model.IfStatement;
import hu.bme.mit.gamma.architecture.transformation.errors.GammaTransformationException;
import hu.bme.mit.gamma.expression.derivedfeatures.ExpressionModelDerivedFeatures;
import hu.bme.mit.gamma.expression.model.AndExpression;
import hu.bme.mit.gamma.expression.model.BooleanTypeDefinition;
import hu.bme.mit.gamma.expression.model.DecimalLiteralExpression;
import hu.bme.mit.gamma.expression.model.DirectReferenceExpression;
import hu.bme.mit.gamma.expression.model.EnumerableTypeDefinition;
import hu.bme.mit.gamma.expression.model.EnumerationLiteralDefinition;
import hu.bme.mit.gamma.expression.model.EnumerationLiteralExpression;
import hu.bme.mit.gamma.expression.model.EnumerationTypeDefinition;
import hu.bme.mit.gamma.expression.model.EqualityExpression;
import hu.bme.mit.gamma.expression.model.Expression;
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory;
import hu.bme.mit.gamma.expression.model.FalseExpression;
import hu.bme.mit.gamma.expression.model.InequalityExpression;
import hu.bme.mit.gamma.expression.model.IntegerLiteralExpression;
import hu.bme.mit.gamma.expression.model.IntegerTypeDefinition;
import hu.bme.mit.gamma.expression.model.NotExpression;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.expression.model.TrueExpression;
import hu.bme.mit.gamma.expression.model.TypeDefinition;
import hu.bme.mit.gamma.expression.model.TypeReference;
import hu.bme.mit.gamma.expression.model.VariableDeclaration;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.EventParameterReferenceExpression;
import hu.bme.mit.gamma.statechart.interface_.Interface;
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition;
import hu.bme.mit.gamma.statechart.statechart.ChoiceState;
import hu.bme.mit.gamma.statechart.statechart.InitialState;
import hu.bme.mit.gamma.statechart.statechart.RaiseEventAction;
import hu.bme.mit.gamma.statechart.statechart.Region;
import hu.bme.mit.gamma.statechart.statechart.SchedulingOrder;
import hu.bme.mit.gamma.statechart.statechart.State;
import hu.bme.mit.gamma.statechart.statechart.StateNode;
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory;
import hu.bme.mit.gamma.statechart.statechart.Transition;
import hu.bme.mit.gamma.statechart.statechart.TransitionPriority;
import hu.bme.mit.gamma.util.GammaEcoreUtil;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class TableTransformation2 {
  private FileInputStream excelFile = null;

  private final ExpressionModelFactory expModelFactory = ExpressionModelFactory.eINSTANCE;

  private final StatechartModelFactory sctModelFactory = StatechartModelFactory.eINSTANCE;

  private final InterfaceModelFactory ifModelFactory = InterfaceModelFactory.eINSTANCE;

  private final ActionModelFactory actModelFactory = ActionModelFactory.eINSTANCE;

  @Extension
  protected final GammaEcoreUtil gammaEcoreUtil = GammaEcoreUtil.INSTANCE;

  public boolean isBlank(final Cell cell) {
    if (((cell == null) || Objects.equal(cell.getCellType(), CellType.BLANK))) {
      return true;
    }
    CellType _cellType = cell.getCellType();
    boolean _equals = Objects.equal(_cellType, CellType.STRING);
    if (_equals) {
      String _stringCellValue = cell.getStringCellValue();
      boolean _equals_1 = Objects.equal(_stringCellValue, "");
      if (_equals_1) {
        return true;
      }
    }
    return false;
  }

  protected ArrayList<String> readIn(final Row row) {
    try {
      ArrayList<String> cells = new ArrayList<String>();
      int i = 0;
      while (true) {
        {
          Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
          boolean _isBlank = this.isBlank(cell);
          if (_isBlank) {
            return cells;
          }
          CellType _cellType = cell.getCellType();
          boolean _equals = Objects.equal(_cellType, CellType.STRING);
          if (_equals) {
            cells.add(cell.getStringCellValue());
          } else {
            CellType _cellType_1 = cell.getCellType();
            boolean _equals_1 = Objects.equal(_cellType_1, CellType.NUMERIC);
            if (_equals_1) {
              double _numericCellValue = cell.getNumericCellValue();
              cells.add(Integer.valueOf(((int) _numericCellValue)).toString());
            } else {
              String _string = cell.getCellType().toString();
              String _plus = ("Table Transformation Exception: Unsupported cell type in the header " + _string);
              throw new GammaTransformationException(_plus);
            }
          }
          i++;
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  protected ArrayList<String> readOut(final Row row) {
    Cell cell = ((Cell) null);
    ArrayList<String> cells = new ArrayList<String>();
    int i = 0;
    do {
      {
        cell = row.getCell(i);
        i++;
      }
    } while((!this.isBlank(cell)));
    while (true) {
      {
        cell = row.getCell(i);
        if (((cell == null) || Objects.equal(cell.getCellType(), CellType.BLANK))) {
          return cells;
        }
        CellType _cellType = cell.getCellType();
        boolean _equals = Objects.equal(_cellType, CellType.STRING);
        if (_equals) {
          String _stringCellValue = cell.getStringCellValue();
          boolean _equals_1 = Objects.equal(_stringCellValue, "");
          if (_equals_1) {
            return cells;
          }
        }
        cells.add(cell.getStringCellValue());
        i++;
      }
    }
  }

  public AssignmentStatement createAssignment(final VariableDeclaration declaration, final int newValue) {
    final DirectReferenceExpression ref = this.expModelFactory.createDirectReferenceExpression();
    ref.setDeclaration(declaration);
    final AssignmentStatement setVar = this.actModelFactory.createAssignmentStatement();
    setVar.setLhs(ref);
    final IntegerLiteralExpression lit = this.expModelFactory.createIntegerLiteralExpression();
    String _string = Integer.valueOf(newValue).toString();
    BigInteger _bigInteger = new BigInteger(_string);
    lit.setValue(_bigInteger);
    setVar.setRhs(lit);
    return setVar;
  }

  public void generate(final String file, final List<Interface> interfaces, final AsynchronousStatechartDefinition statechart) {
    try {
      statechart.setTransitionPriority(TransitionPriority.ORDER_BASED);
      statechart.setSchedulingOrder(SchedulingOrder.TOP_DOWN);
      final Region mainRegion = this.sctModelFactory.createRegion();
      mainRegion.setName("Table_Main_Region");
      EList<Region> _regions = statechart.getRegions();
      _regions.add(mainRegion);
      final InitialState initState = this.sctModelFactory.createInitialState();
      initState.setName("Table_Initial_State");
      final State mainState = this.sctModelFactory.createState();
      mainState.setName("Table_Main_State");
      final ChoiceState choiceState = this.sctModelFactory.createChoiceState();
      choiceState.setName("Table_Choice_State");
      EList<StateNode> _stateNodes = mainRegion.getStateNodes();
      _stateNodes.add(initState);
      EList<StateNode> _stateNodes_1 = mainRegion.getStateNodes();
      _stateNodes_1.add(mainState);
      EList<StateNode> _stateNodes_2 = mainRegion.getStateNodes();
      _stateNodes_2.add(choiceState);
      final Transition initTrans = this.sctModelFactory.createTransition();
      initTrans.setSourceState(initState);
      initTrans.setTargetState(mainState);
      EList<Transition> _transitions = statechart.getTransitions();
      _transitions.add(initTrans);
      final Transition anyTrans = this.sctModelFactory.createTransition();
      anyTrans.setSourceState(mainState);
      anyTrans.setTargetState(choiceState);
      anyTrans.setTrigger(this.ifModelFactory.createAnyTrigger());
      EList<Transition> _transitions_1 = statechart.getTransitions();
      _transitions_1.add(anyTrans);
      final VariableDeclaration lineVarDecl = this.expModelFactory.createVariableDeclaration();
      lineVarDecl.setName("line_cntr_variable");
      lineVarDecl.setType(this.expModelFactory.createIntegerTypeDefinition());
      EList<VariableDeclaration> _variableDeclarations = statechart.getVariableDeclarations();
      _variableDeclarations.add(lineVarDecl);
      int lineCNTR = 0;
      EList<Action> _effects = initTrans.getEffects();
      AssignmentStatement _createAssignment = this.createAssignment(lineVarDecl, 1);
      _effects.add(_createAssignment);
      ArrayList<String> inPortNames = new ArrayList<String>();
      ArrayList<String> outPortNames = new ArrayList<String>();
      ArrayList<String> inPortIfNames = new ArrayList<String>();
      ArrayList<String> outPortIfNames = new ArrayList<String>();
      ArrayList<Event> outEvents = new ArrayList<Event>();
      boolean isFirst = true;
      try {
        FileInputStream _fileInputStream = new FileInputStream(file);
        this.excelFile = _fileInputStream;
        final XSSFWorkbook workbook = new XSSFWorkbook(this.excelFile);
        final XSSFSheet datatypeSheet = workbook.getSheetAt(0);
        final String compName = datatypeSheet.getSheetName();
        Iterator<Row> rowIterator = datatypeSheet.iterator();
        Row headerRow = rowIterator.next();
        inPortNames = this.readIn(headerRow);
        outPortNames = this.readOut(headerRow);
        Row ifRow = rowIterator.next();
        inPortIfNames = this.readIn(ifRow);
        outPortIfNames = this.readOut(ifRow);
        final ArrayList<Port> inPorts = new ArrayList<Port>();
        final ArrayList<Port> outPorts = new ArrayList<Port>();
        final ArrayList<String> _converted_inPortNames = (ArrayList<String>)inPortNames;
        int _length = ((Object[])Conversions.unwrapArray(_converted_inPortNames, Object.class)).length;
        int _minus = (_length - 1);
        IntegerRange _upTo = new IntegerRange(0, _minus);
        for (final Integer k : _upTo) {
          {
            final String portName = inPortNames.get((k).intValue());
            final String portIFName = inPortIfNames.get((k).intValue());
            final Function1<Port, Boolean> _function = (Port p) -> {
              String _name = p.getName();
              String _simplifyName = TableTransformation2.simplifyName(portName);
              String _firstUpper = StringExtensions.toFirstUpper(TableTransformation2.simplifyName(portIFName));
              String _plus = (_simplifyName + _firstUpper);
              String _plus_1 = (_plus + "In");
              return Boolean.valueOf(_name.equals(_plus_1));
            };
            final Iterator<Port> port = IterableExtensions.<Port>filter(statechart.getPorts(), _function).iterator();
            boolean _hasNext = port.hasNext();
            boolean _not = (!_hasNext);
            if (_not) {
              String _simplifyName = TableTransformation2.simplifyName(portName);
              String _plus = ("Exception occurred during table \'" + _simplifyName);
              String _plus_1 = (_plus + portIFName);
              String _plus_2 = (_plus_1 + 
                "In\' not found in Excel file: ");
              String _plus_3 = (_plus_2 + file);
              throw new GammaTransformationException(_plus_3, statechart);
            }
            inPorts.add(port.next());
          }
        }
        final ArrayList<String> _converted_outPortNames = (ArrayList<String>)outPortNames;
        int _length_1 = ((Object[])Conversions.unwrapArray(_converted_outPortNames, Object.class)).length;
        int _minus_1 = (_length_1 - 1);
        IntegerRange _upTo_1 = new IntegerRange(0, _minus_1);
        for (final Integer k_1 : _upTo_1) {
          {
            final String portName = outPortNames.get((k_1).intValue());
            final String portIFName = outPortIfNames.get((k_1).intValue());
            final Function1<Port, Boolean> _function = (Port p) -> {
              String _name = p.getName();
              String _simplifyName = TableTransformation2.simplifyName(portName);
              String _firstUpper = StringExtensions.toFirstUpper(TableTransformation2.simplifyName(portIFName));
              String _plus = (_simplifyName + _firstUpper);
              String _plus_1 = (_plus + "Out");
              return Boolean.valueOf(_name.equals(_plus_1));
            };
            final Iterator<Port> port = IterableExtensions.<Port>filter(statechart.getPorts(), _function).iterator();
            boolean _hasNext = port.hasNext();
            boolean _not = (!_hasNext);
            if (_not) {
              String _simplifyName = TableTransformation2.simplifyName(portName);
              String _plus = ("Exception occurred during table \'" + _simplifyName);
              String _plus_1 = (_plus + portIFName);
              String _plus_2 = (_plus_1 + 
                "Out\' not found in Excel file: ");
              String _plus_3 = (_plus_2 + file);
              throw new GammaTransformationException(_plus_3, statechart);
            }
            outPorts.add(port.next());
          }
        }
        while (rowIterator.hasNext()) {
          {
            lineCNTR++;
            final Row row = rowIterator.next();
            final Transition transition = this.sctModelFactory.createTransition();
            transition.setSourceState(choiceState);
            transition.setTargetState(mainState);
            EList<Transition> _transitions_2 = statechart.getTransitions();
            _transitions_2.add(transition);
            final InequalityExpression lineEqExpr = this.expModelFactory.createInequalityExpression();
            final DirectReferenceExpression varRef = this.expModelFactory.createDirectReferenceExpression();
            varRef.setDeclaration(lineVarDecl);
            final IntegerLiteralExpression lit = this.expModelFactory.createIntegerLiteralExpression();
            String _string = Integer.valueOf(lineCNTR).toString();
            BigInteger _bigInteger = new BigInteger(_string);
            lit.setValue(_bigInteger);
            lineEqExpr.setLeftOperand(lit);
            lineEqExpr.setRightOperand(varRef);
            final IfStatement ifExp = this.actModelFactory.createIfStatement();
            final Branch branch = this.actModelFactory.createBranch();
            final Block block = this.actModelFactory.createBlock();
            branch.setGuard(lineEqExpr);
            branch.setAction(block);
            EList<Action> _actions = block.getActions();
            AssignmentStatement _createAssignment_1 = this.createAssignment(lineVarDecl, lineCNTR);
            _actions.add(_createAssignment_1);
            EList<Branch> _conditionals = ifExp.getConditionals();
            _conditionals.add(branch);
            EList<Action> _effects_1 = transition.getEffects();
            _effects_1.add(ifExp);
            final AndExpression guardExpr = this.expModelFactory.createAndExpression();
            final Iterator<Cell> cellIterator = row.cellIterator();
            final ArrayList<String> _converted_inPortNames_1 = (ArrayList<String>)inPortNames;
            int _length_2 = ((Object[])Conversions.unwrapArray(_converted_inPortNames_1, Object.class)).length;
            int _minus_2 = (_length_2 - 1);
            IntegerRange _upTo_2 = new IntegerRange(0, _minus_2);
            for (final Integer j : _upTo_2) {
              {
                final Cell cell = cellIterator.next();
                final Port inPort = inPorts.get((j).intValue());
                boolean _isBlank = this.isBlank(cell);
                boolean _not = (!_isBlank);
                if (_not) {
                  CellType _cellType = cell.getCellType();
                  boolean _equals = Objects.equal(_cellType, CellType.STRING);
                  if (_equals) {
                    final String value = TableTransformation2.simplifyName(cell.getStringCellValue());
                    boolean _not_1 = (!((((value.isBlank() || Objects.equal(value, "any")) || Objects.equal(value, "ANY")) || Objects.equal(value, "N/A")) || 
                      Objects.equal(value, "NA")));
                    if (_not_1) {
                      final Function1<Event, EList<ParameterDeclaration>> _function = (Event e) -> {
                        return e.getParameterDeclarations();
                      };
                      final Function1<ParameterDeclaration, Boolean> _function_1 = (ParameterDeclaration p) -> {
                        TypeDefinition _typeDefinition = ExpressionModelDerivedFeatures.getTypeDefinition(p);
                        return Boolean.valueOf((_typeDefinition instanceof EnumerableTypeDefinition));
                      };
                      final Iterator<ParameterDeclaration> paramIt = IterableExtensions.<ParameterDeclaration>filter(IterableExtensions.<Event, ParameterDeclaration>flatMap(StatechartModelDerivedFeatures.getInputEvents(inPort), _function), _function_1).iterator();
                      boolean _hasNext = paramIt.hasNext();
                      boolean _not_2 = (!_hasNext);
                      if (_not_2) {
                        String _name = inPort.getName();
                        String _plus = ("Table transformation error, event enumeration parameter cannot be found: " + _name);
                        String _plus_1 = (_plus + " in Excel file: ");
                        String _plus_2 = (_plus_1 + file);
                        throw new GammaTransformationException(_plus_2, statechart);
                      }
                      final ParameterDeclaration param = paramIt.next();
                      final EventParameterReferenceExpression ref = this.ifModelFactory.createEventParameterReferenceExpression();
                      ref.setPort(inPort);
                      final Function1<Event, Boolean> _function_2 = (Event e) -> {
                        final Function1<ParameterDeclaration, Boolean> _function_3 = (ParameterDeclaration p) -> {
                          TypeDefinition _typeDefinition = ExpressionModelDerivedFeatures.getTypeDefinition(p);
                          return Boolean.valueOf((_typeDefinition instanceof EnumerableTypeDefinition));
                        };
                        boolean _isEmpty = IterableExtensions.isEmpty(IterableExtensions.<ParameterDeclaration>filter(e.getParameterDeclarations(), _function_3));
                        return Boolean.valueOf((!_isEmpty));
                      };
                      ref.setEvent(IterableExtensions.<Event>filter(StatechartModelDerivedFeatures.getInputEvents(inPort), _function_2).iterator().next());
                      ref.setParameter(param);
                      final EqualityExpression eqExpr = this.expModelFactory.createEqualityExpression();
                      eqExpr.setLeftOperand(ref);
                      final EnumerationLiteralExpression enumLitExp = this.expModelFactory.createEnumerationLiteralExpression();
                      final TypeReference typeRefExep = this.expModelFactory.createTypeReference();
                      typeRefExep.setReference(ExpressionModelDerivedFeatures.getTypeDeclaration(ExpressionModelDerivedFeatures.getTypeDefinition(param)));
                      enumLitExp.setTypeReference(typeRefExep);
                      TypeDefinition _typeDefinition = ExpressionModelDerivedFeatures.getTypeDefinition(param);
                      final Function1<EnumerationLiteralDefinition, Boolean> _function_3 = (EnumerationLiteralDefinition l) -> {
                        return Boolean.valueOf(l.getName().equals(value));
                      };
                      final Iterator<EnumerationLiteralDefinition> litIt = IterableExtensions.<EnumerationLiteralDefinition>filter(((EnumerationTypeDefinition) _typeDefinition).getLiterals(), _function_3).iterator();
                      boolean _hasNext_1 = litIt.hasNext();
                      boolean _not_3 = (!_hasNext_1);
                      if (_not_3) {
                        String _name_1 = inPort.getName();
                        String _plus_3 = ("Table transformation error, enumeration literal cannot be found of event: " + _name_1);
                        String _plus_4 = (_plus_3 + ".");
                        String _name_2 = ref.getEvent().getName();
                        String _plus_5 = (_plus_4 + _name_2);
                        String _plus_6 = (_plus_5 + ", literal: ");
                        String _plus_7 = (_plus_6 + value);
                        String _plus_8 = (_plus_7 + 
                          " in Excel file: ");
                        String _plus_9 = (_plus_8 + file);
                        throw new GammaTransformationException(_plus_9, statechart);
                      }
                      enumLitExp.setReference(litIt.next());
                      eqExpr.setRightOperand(enumLitExp);
                      EList<Expression> _operands = guardExpr.getOperands();
                      _operands.add(eqExpr);
                    }
                  }
                  CellType _cellType_1 = cell.getCellType();
                  boolean _equals_1 = Objects.equal(_cellType_1, CellType.NUMERIC);
                  if (_equals_1) {
                    final double value_1 = cell.getNumericCellValue();
                    final Function1<Event, EList<ParameterDeclaration>> _function_4 = (Event e) -> {
                      return e.getParameterDeclarations();
                    };
                    final Function1<ParameterDeclaration, Boolean> _function_5 = (ParameterDeclaration p) -> {
                      TypeDefinition _typeDefinition_1 = ExpressionModelDerivedFeatures.getTypeDefinition(p);
                      return Boolean.valueOf((_typeDefinition_1 instanceof IntegerTypeDefinition));
                    };
                    final Iterator<ParameterDeclaration> paramIt_1 = IterableExtensions.<ParameterDeclaration>filter(IterableExtensions.<Event, ParameterDeclaration>flatMap(StatechartModelDerivedFeatures.getInputEvents(inPort), _function_4), _function_5).iterator();
                    boolean _hasNext_2 = paramIt_1.hasNext();
                    boolean _not_4 = (!_hasNext_2);
                    if (_not_4) {
                      throw new GammaTransformationException(
                        ((("Table transformation error, event integer parameter cannot be found: " + Double.valueOf(value_1)) + 
                          " in Excel file: ") + file), statechart);
                    }
                    final ParameterDeclaration param_1 = paramIt_1.next();
                    final EventParameterReferenceExpression ref_1 = this.ifModelFactory.createEventParameterReferenceExpression();
                    ref_1.setPort(inPort);
                    final Function1<Event, Boolean> _function_6 = (Event e) -> {
                      final Function1<ParameterDeclaration, Boolean> _function_7 = (ParameterDeclaration p) -> {
                        TypeDefinition _typeDefinition_1 = ExpressionModelDerivedFeatures.getTypeDefinition(p);
                        return Boolean.valueOf((_typeDefinition_1 instanceof IntegerTypeDefinition));
                      };
                      boolean _isEmpty = IterableExtensions.isEmpty(IterableExtensions.<ParameterDeclaration>filter(e.getParameterDeclarations(), _function_7));
                      return Boolean.valueOf((!_isEmpty));
                    };
                    ref_1.setEvent(IterableExtensions.<Event>filter(StatechartModelDerivedFeatures.getInputEvents(inPort), _function_6).iterator().next());
                    ref_1.setParameter(param_1);
                    final EqualityExpression eqExpr_1 = this.expModelFactory.createEqualityExpression();
                    if ((value_1 == ((int) value_1))) {
                      final IntegerLiteralExpression intLitExp = this.expModelFactory.createIntegerLiteralExpression();
                      String _string_1 = Integer.valueOf(((int) value_1)).toString();
                      BigInteger _bigInteger_1 = new BigInteger(_string_1);
                      intLitExp.setValue(_bigInteger_1);
                      eqExpr_1.setRightOperand(intLitExp);
                    } else {
                      final DecimalLiteralExpression decLitExp = this.expModelFactory.createDecimalLiteralExpression();
                      String _string_2 = Double.valueOf(value_1).toString();
                      BigDecimal _bigDecimal = new BigDecimal(_string_2);
                      decLitExp.setValue(_bigDecimal);
                      eqExpr_1.setRightOperand(decLitExp);
                    }
                    eqExpr_1.setLeftOperand(ref_1);
                    EList<Expression> _operands_1 = guardExpr.getOperands();
                    _operands_1.add(eqExpr_1);
                  }
                  CellType _cellType_2 = cell.getCellType();
                  boolean _equals_2 = Objects.equal(_cellType_2, CellType.BOOLEAN);
                  if (_equals_2) {
                    final boolean value_2 = cell.getBooleanCellValue();
                    final Function1<Event, EList<ParameterDeclaration>> _function_7 = (Event e) -> {
                      return e.getParameterDeclarations();
                    };
                    final Function1<ParameterDeclaration, Boolean> _function_8 = (ParameterDeclaration p) -> {
                      TypeDefinition _typeDefinition_1 = ExpressionModelDerivedFeatures.getTypeDefinition(p);
                      return Boolean.valueOf((_typeDefinition_1 instanceof BooleanTypeDefinition));
                    };
                    final Iterator<ParameterDeclaration> paramIt_2 = IterableExtensions.<ParameterDeclaration>filter(IterableExtensions.<Event, ParameterDeclaration>flatMap(StatechartModelDerivedFeatures.getInputEvents(inPort), _function_7), _function_8).iterator();
                    boolean _hasNext_3 = paramIt_2.hasNext();
                    boolean _not_5 = (!_hasNext_3);
                    if (_not_5) {
                      throw new GammaTransformationException(
                        ((("Table transformation error, event Boolean parameter cannot be found: " + Boolean.valueOf(value_2)) + 
                          " in Excel file: ") + file), statechart);
                    }
                    final ParameterDeclaration param_2 = paramIt_2.next();
                    final EventParameterReferenceExpression ref_2 = this.ifModelFactory.createEventParameterReferenceExpression();
                    ref_2.setPort(inPort);
                    final Function1<Event, Boolean> _function_9 = (Event e) -> {
                      final Function1<ParameterDeclaration, Boolean> _function_10 = (ParameterDeclaration p) -> {
                        TypeDefinition _typeDefinition_1 = ExpressionModelDerivedFeatures.getTypeDefinition(p);
                        return Boolean.valueOf((_typeDefinition_1 instanceof BooleanTypeDefinition));
                      };
                      boolean _isEmpty = IterableExtensions.isEmpty(IterableExtensions.<ParameterDeclaration>filter(e.getParameterDeclarations(), _function_10));
                      return Boolean.valueOf((!_isEmpty));
                    };
                    ref_2.setEvent(IterableExtensions.<Event>filter(StatechartModelDerivedFeatures.getInputEvents(inPort), _function_9).iterator().next());
                    ref_2.setParameter(param_2);
                    if (value_2) {
                      EList<Expression> _operands_2 = guardExpr.getOperands();
                      _operands_2.add(ref_2);
                    } else {
                      final NotExpression notExpr = this.expModelFactory.createNotExpression();
                      notExpr.setOperand(ref_2);
                      EList<Expression> _operands_3 = guardExpr.getOperands();
                      _operands_3.add(ref_2);
                    }
                  }
                }
              }
            }
            int _size = guardExpr.getOperands().size();
            boolean _greaterThan = (_size > 1);
            if (_greaterThan) {
              transition.setGuard(guardExpr);
            } else {
              int _size_1 = guardExpr.getOperands().size();
              boolean _equals = (_size_1 == 1);
              if (_equals) {
                transition.setGuard(guardExpr.getOperands().get(0));
              } else {
                transition.setGuard(this.expModelFactory.createElseExpression());
              }
            }
            final ArrayList<String> _converted_outPortNames_1 = (ArrayList<String>)outPortNames;
            int _length_3 = ((Object[])Conversions.unwrapArray(_converted_outPortNames_1, Object.class)).length;
            int _minus_3 = (_length_3 - 1);
            IntegerRange _upTo_3 = new IntegerRange(0, _minus_3);
            for (final Integer j_1 : _upTo_3) {
              {
                final Cell cell = cellIterator.next();
                final Port outPort = outPorts.get((j_1).intValue());
                CellType _cellType = cell.getCellType();
                boolean _equals_1 = Objects.equal(_cellType, CellType.STRING);
                if (_equals_1) {
                  final String value = cell.getStringCellValue();
                  final Function1<Event, EList<ParameterDeclaration>> _function = (Event e) -> {
                    return e.getParameterDeclarations();
                  };
                  final Function1<ParameterDeclaration, Boolean> _function_1 = (ParameterDeclaration p) -> {
                    TypeDefinition _typeDefinition = ExpressionModelDerivedFeatures.getTypeDefinition(p);
                    return Boolean.valueOf((_typeDefinition instanceof EnumerableTypeDefinition));
                  };
                  final Iterator<ParameterDeclaration> paramIt = IterableExtensions.<ParameterDeclaration>filter(IterableExtensions.<Event, ParameterDeclaration>flatMap(StatechartModelDerivedFeatures.getOutputEvents(outPort), _function), _function_1).iterator();
                  boolean _hasNext = paramIt.hasNext();
                  boolean _not = (!_hasNext);
                  if (_not) {
                    String _name = outPort.getName();
                    String _plus = ("Table transformation error, event enumeration parameter cannot be found: " + _name);
                    String _plus_1 = (_plus + " in Excel file: ");
                    String _plus_2 = (_plus_1 + file);
                    throw new GammaTransformationException(_plus_2, statechart);
                  }
                  final ParameterDeclaration param = paramIt.next();
                  final RaiseEventAction raiseAct = this.sctModelFactory.createRaiseEventAction();
                  raiseAct.setPort(outPort);
                  final Function1<Event, Boolean> _function_2 = (Event e) -> {
                    final Function1<ParameterDeclaration, Boolean> _function_3 = (ParameterDeclaration p) -> {
                      TypeDefinition _typeDefinition = ExpressionModelDerivedFeatures.getTypeDefinition(p);
                      return Boolean.valueOf((_typeDefinition instanceof EnumerableTypeDefinition));
                    };
                    boolean _isEmpty = IterableExtensions.isEmpty(IterableExtensions.<ParameterDeclaration>filter(e.getParameterDeclarations(), _function_3));
                    return Boolean.valueOf((!_isEmpty));
                  };
                  raiseAct.setEvent(IterableExtensions.<Event>filter(StatechartModelDerivedFeatures.getOutputEvents(outPort), _function_2).iterator().next());
                  final EnumerationLiteralExpression enumLitExp = this.expModelFactory.createEnumerationLiteralExpression();
                  final TypeReference typeRefExep = this.expModelFactory.createTypeReference();
                  typeRefExep.setReference(ExpressionModelDerivedFeatures.getTypeDeclaration(ExpressionModelDerivedFeatures.getTypeDefinition(param)));
                  enumLitExp.setTypeReference(typeRefExep);
                  TypeDefinition _typeDefinition = ExpressionModelDerivedFeatures.getTypeDefinition(param);
                  final Function1<EnumerationLiteralDefinition, Boolean> _function_3 = (EnumerationLiteralDefinition l) -> {
                    return Boolean.valueOf(l.getName().equals(value));
                  };
                  final Iterator<EnumerationLiteralDefinition> enumLitREfIt = IterableExtensions.<EnumerationLiteralDefinition>filter(((EnumerationTypeDefinition) _typeDefinition).getLiterals(), _function_3).iterator();
                  boolean _hasNext_1 = enumLitREfIt.hasNext();
                  boolean _not_1 = (!_hasNext_1);
                  if (_not_1) {
                    String _name_1 = outPort.getName();
                    String _plus_3 = ("Table transformation error, enumeration literal cannot be found of event: " + _name_1);
                    String _plus_4 = (_plus_3 + ".");
                    String _name_2 = raiseAct.getEvent().getName();
                    String _plus_5 = (_plus_4 + _name_2);
                    String _plus_6 = (_plus_5 + ", literal: ");
                    String _plus_7 = (_plus_6 + value);
                    String _plus_8 = (_plus_7 + 
                      " in Excel file: ");
                    String _plus_9 = (_plus_8 + file);
                    throw new GammaTransformationException(_plus_9, statechart);
                  }
                  enumLitExp.setReference(enumLitREfIt.next());
                  EList<Expression> _arguments = raiseAct.getArguments();
                  _arguments.add(enumLitExp);
                  EList<Action> _actions_1 = block.getActions();
                  _actions_1.add(raiseAct);
                  if (isFirst) {
                    EList<Action> _effects_2 = initTrans.getEffects();
                    RaiseEventAction _clone = this.gammaEcoreUtil.<RaiseEventAction>clone(raiseAct);
                    _effects_2.add(_clone);
                  }
                }
                CellType _cellType_1 = cell.getCellType();
                boolean _equals_2 = Objects.equal(_cellType_1, CellType.NUMERIC);
                if (_equals_2) {
                  final double value_1 = cell.getNumericCellValue();
                  final RaiseEventAction raiseAct_1 = this.sctModelFactory.createRaiseEventAction();
                  raiseAct_1.setPort(outPort);
                  final Function1<Event, Boolean> _function_4 = (Event e) -> {
                    final Function1<ParameterDeclaration, Boolean> _function_5 = (ParameterDeclaration p) -> {
                      TypeDefinition _typeDefinition_1 = ExpressionModelDerivedFeatures.getTypeDefinition(p);
                      return Boolean.valueOf((_typeDefinition_1 instanceof IntegerTypeDefinition));
                    };
                    boolean _isEmpty = IterableExtensions.isEmpty(IterableExtensions.<ParameterDeclaration>filter(e.getParameterDeclarations(), _function_5));
                    return Boolean.valueOf((!_isEmpty));
                  };
                  final Iterator<Event> eventIt = IterableExtensions.<Event>filter(StatechartModelDerivedFeatures.getOutputEvents(outPort), _function_4).iterator();
                  boolean _hasNext_2 = eventIt.hasNext();
                  boolean _not_2 = (!_hasNext_2);
                  if (_not_2) {
                    throw new GammaTransformationException(
                      ((("Table transformation error, event integer parameter cannot be found: " + Double.valueOf(value_1)) + 
                        " in Excel file: ") + file), statechart);
                  }
                  raiseAct_1.setEvent(eventIt.next());
                  if ((value_1 == ((int) value_1))) {
                    final IntegerLiteralExpression intLitExp = this.expModelFactory.createIntegerLiteralExpression();
                    String _string_1 = Integer.valueOf(((int) value_1)).toString();
                    BigInteger _bigInteger_1 = new BigInteger(_string_1);
                    intLitExp.setValue(_bigInteger_1);
                    EList<Expression> _arguments_1 = raiseAct_1.getArguments();
                    _arguments_1.add(intLitExp);
                  } else {
                    final DecimalLiteralExpression decLitExp = this.expModelFactory.createDecimalLiteralExpression();
                    String _string_2 = Double.valueOf(value_1).toString();
                    BigDecimal _bigDecimal = new BigDecimal(_string_2);
                    decLitExp.setValue(_bigDecimal);
                    EList<Expression> _arguments_2 = raiseAct_1.getArguments();
                    _arguments_2.add(decLitExp);
                  }
                  EList<Action> _actions_2 = block.getActions();
                  _actions_2.add(raiseAct_1);
                  if (isFirst) {
                    EList<Action> _effects_3 = initTrans.getEffects();
                    RaiseEventAction _clone_1 = this.gammaEcoreUtil.<RaiseEventAction>clone(raiseAct_1);
                    _effects_3.add(_clone_1);
                  }
                }
                CellType _cellType_2 = cell.getCellType();
                boolean _equals_3 = Objects.equal(_cellType_2, CellType.BOOLEAN);
                if (_equals_3) {
                  final boolean value_2 = cell.getBooleanCellValue();
                  final RaiseEventAction raiseAct_2 = this.sctModelFactory.createRaiseEventAction();
                  raiseAct_2.setPort(outPort);
                  final Function1<Event, Boolean> _function_5 = (Event e) -> {
                    final Function1<ParameterDeclaration, Boolean> _function_6 = (ParameterDeclaration p) -> {
                      TypeDefinition _typeDefinition_1 = ExpressionModelDerivedFeatures.getTypeDefinition(p);
                      return Boolean.valueOf((_typeDefinition_1 instanceof BooleanTypeDefinition));
                    };
                    boolean _isEmpty = IterableExtensions.isEmpty(IterableExtensions.<ParameterDeclaration>filter(e.getParameterDeclarations(), _function_6));
                    return Boolean.valueOf((!_isEmpty));
                  };
                  final Iterator<Event> eventIt_1 = IterableExtensions.<Event>filter(StatechartModelDerivedFeatures.getOutputEvents(outPort), _function_5).iterator();
                  boolean _hasNext_3 = eventIt_1.hasNext();
                  boolean _not_3 = (!_hasNext_3);
                  if (_not_3) {
                    throw new GammaTransformationException(
                      ((("Table transformation error, event boolean parameter cannot be found: " + Boolean.valueOf(value_2)) + 
                        " in Excel file: ") + file), statechart);
                  }
                  raiseAct_2.setEvent(eventIt_1.next());
                  if (value_2) {
                    EList<Expression> _arguments_3 = raiseAct_2.getArguments();
                    TrueExpression _createTrueExpression = this.expModelFactory.createTrueExpression();
                    _arguments_3.add(_createTrueExpression);
                  } else {
                    EList<Expression> _arguments_4 = raiseAct_2.getArguments();
                    FalseExpression _createFalseExpression = this.expModelFactory.createFalseExpression();
                    _arguments_4.add(_createFalseExpression);
                  }
                  EList<Action> _actions_3 = block.getActions();
                  _actions_3.add(raiseAct_2);
                  if (isFirst) {
                    EList<Action> _effects_4 = initTrans.getEffects();
                    RaiseEventAction _clone_2 = this.gammaEcoreUtil.<RaiseEventAction>clone(raiseAct_2);
                    _effects_4.add(_clone_2);
                  }
                }
              }
            }
            isFirst = false;
          }
        }
        this.excelFile.close();
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          e.printStackTrace();
          this.excelFile.close();
          throw e;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  public static String simplifyName(final String name) {
    boolean _equals = Objects.equal(name, "anonym");
    if (_equals) {
      return "";
    }
    String simplifiedName = name.replace(".", "_");
    final List<String> forbiddenCharacters = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("-", ":", " ", "(", ")", "{", "}", "[", "]", "{", "}", ",", ";", ";", "%", "!", "&", "/", "+"));
    for (final String c : forbiddenCharacters) {
      simplifiedName = simplifiedName.replace(c, "_");
    }
    while (simplifiedName.contains("__")) {
      simplifiedName = simplifiedName.replaceAll("__", "_");
    }
    boolean _matches = simplifiedName.matches("^[0-9]");
    if (_matches) {
      simplifiedName = ("_" + simplifiedName);
    }
    return simplifiedName;
  }
}
