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
import hu.bme.mit.gamma.dialog.DialogUtil;
import hu.bme.mit.gamma.expression.derivedfeatures.ExpressionModelDerivedFeatures;
import hu.bme.mit.gamma.expression.model.DecimalTypeDefinition;
import hu.bme.mit.gamma.expression.model.EnumerableTypeDefinition;
import hu.bme.mit.gamma.expression.model.IntegerTypeDefinition;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.expression.model.RationalTypeDefinition;
import hu.bme.mit.gamma.expression.model.Type;
import hu.bme.mit.gamma.expression.model.TypeDeclaration;
import hu.bme.mit.gamma.expression.model.TypeReference;
import hu.bme.mit.gamma.expression.util.ExpressionSerializer;
import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.EventDeclaration;
import hu.bme.mit.gamma.statechart.interface_.Interface;
import hu.bme.mit.gamma.statechart.interface_.Persistency;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class TableTransformation {
  private FileInputStream excelFile = null;

  protected final ExpressionSerializer expSerializer = ExpressionSerializer.INSTANCE;

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
    ArrayList<String> cells = new ArrayList<String>();
    int i = 0;
    while (true) {
      {
        Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        boolean _isBlank = this.isBlank(cell);
        if (_isBlank) {
          return cells;
        }
        cells.add(cell.getStringCellValue());
        i++;
      }
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

  protected String getCondition(final Interface _interface, final Cell cell, final String portName) {
    final Function1<EventDeclaration, Event> _function = (EventDeclaration e) -> {
      return e.getEvent();
    };
    final Function1<Event, Boolean> _function_1 = (Event e) -> {
      Persistency _persistency = e.getPersistency();
      return Boolean.valueOf(Objects.equal(_persistency, Persistency.PERSISTENT));
    };
    Event event = IterableExtensions.<Event>toList(IterableExtensions.<Event>filter(ListExtensions.<EventDeclaration, Event>map(_interface.getEvents(), _function), _function_1)).get(0);
    ParameterDeclaration param = event.getParameterDeclarations().get(0);
    CellType _cellType = cell.getCellType();
    boolean _equals = Objects.equal(_cellType, CellType.BLANK);
    if (_equals) {
      return "true";
    }
    CellType _cellType_1 = cell.getCellType();
    boolean _equals_1 = Objects.equal(_cellType_1, CellType.STRING);
    if (_equals_1) {
      String _stringCellValue = cell.getStringCellValue();
      boolean _equals_2 = Objects.equal(_stringCellValue, "any");
      if (_equals_2) {
        return "true";
      }
    }
    Type _type = param.getType();
    if ((_type instanceof TypeReference)) {
      Type _type_1 = param.getType();
      final TypeDeclaration type = ((TypeReference) _type_1).getReference();
      Type _type_2 = type.getType();
      if ((_type_2 instanceof EnumerableTypeDefinition)) {
        CellType _cellType_2 = cell.getCellType();
        boolean _equals_3 = Objects.equal(_cellType_2, CellType.STRING);
        if (_equals_3) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(portName);
          _builder.append(".");
          String _name = event.getName();
          _builder.append(_name);
          _builder.append("::");
          String _name_1 = param.getName();
          _builder.append(_name_1);
          _builder.append("==");
          String _name_2 = type.getName();
          _builder.append(_name_2);
          _builder.append("::");
          String _stringCellValue_1 = cell.getStringCellValue();
          _builder.append(_stringCellValue_1);
          return _builder.toString();
        } else {
          String _name_3 = type.getName();
          String _plus = ("Wrong cell type in the excel table. Cell type must be string. Type name: " + _name_3);
          throw new UnsupportedOperationException(_plus);
        }
      } else {
        String _name_4 = type.getName();
        String _plus_1 = ("Only enumeration type reference are supported in the Gamma model. Type name: " + _name_4);
        throw new UnsupportedOperationException(_plus_1);
      }
    }
    if ((((param.getType() instanceof IntegerTypeDefinition) || (param.getType() instanceof RationalTypeDefinition)) || (param.getType() instanceof DecimalTypeDefinition))) {
      CellType _cellType_3 = cell.getCellType();
      boolean _equals_4 = Objects.equal(_cellType_3, CellType.NUMERIC);
      if (_equals_4) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(portName);
        _builder_1.append(".");
        String _name_5 = event.getName();
        _builder_1.append(_name_5);
        _builder_1.append("::");
        String _name_6 = param.getName();
        _builder_1.append(_name_6);
        _builder_1.append("==");
        String _string = Double.valueOf(cell.getNumericCellValue()).toString();
        _builder_1.append(_string);
        return _builder_1.toString();
      } else {
        CellType _cellType_4 = cell.getCellType();
        boolean _equals_5 = Objects.equal(_cellType_4, CellType.STRING);
        if (_equals_5) {
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append(portName);
          _builder_2.append(".");
          String _name_7 = event.getName();
          _builder_2.append(_name_7);
          _builder_2.append("::");
          String _name_8 = param.getName();
          _builder_2.append(_name_8);
          _builder_2.append(" ");
          String _string_1 = cell.getStringCellValue().toString();
          _builder_2.append(_string_1);
          return _builder_2.toString();
        } else {
          throw new UnsupportedOperationException("Wrong cell type in the excel table. Cell type must be string or numeric.");
        }
      }
    }
    return "";
  }

  protected String getEffect(final Interface _interface, final Cell cell, final String portName) {
    final Function1<EventDeclaration, Event> _function = (EventDeclaration e) -> {
      return e.getEvent();
    };
    final Function1<Event, Boolean> _function_1 = (Event e) -> {
      Persistency _persistency = e.getPersistency();
      return Boolean.valueOf(Objects.equal(_persistency, Persistency.PERSISTENT));
    };
    Event event = IterableExtensions.<Event>toList(IterableExtensions.<Event>filter(ListExtensions.<EventDeclaration, Event>map(_interface.getEvents(), _function), _function_1)).get(0);
    ParameterDeclaration param = event.getParameterDeclarations().get(0);
    CellType _cellType = cell.getCellType();
    boolean _equals = Objects.equal(_cellType, CellType.BLANK);
    if (_equals) {
      return "";
    }
    CellType _cellType_1 = cell.getCellType();
    boolean _equals_1 = Objects.equal(_cellType_1, CellType.STRING);
    if (_equals_1) {
      String _stringCellValue = cell.getStringCellValue();
      boolean _equals_2 = Objects.equal(_stringCellValue, "NE");
      if (_equals_2) {
        return "";
      }
    }
    Type _type = param.getType();
    if ((_type instanceof TypeReference)) {
      Type _type_1 = param.getType();
      final TypeDeclaration type = ((TypeReference) _type_1).getReference();
      Type _type_2 = type.getType();
      if ((_type_2 instanceof EnumerableTypeDefinition)) {
        CellType _cellType_2 = cell.getCellType();
        boolean _equals_3 = Objects.equal(_cellType_2, CellType.STRING);
        if (_equals_3) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("raise ");
          _builder.append(portName);
          _builder.append(".");
          String _name = event.getName();
          _builder.append(_name);
          _builder.append("(");
          String _name_1 = type.getName();
          _builder.append(_name_1);
          _builder.append("::");
          String _stringCellValue_1 = cell.getStringCellValue();
          _builder.append(_stringCellValue_1);
          _builder.append(");");
          return _builder.toString();
        } else {
          String _name_2 = type.getName();
          String _plus = ("Wrong cell type in the excel table. Cell type must be string. Type name: " + _name_2);
          throw new UnsupportedOperationException(_plus);
        }
      } else {
        String _name_3 = type.getName();
        String _plus_1 = ("Only enumeration type reference are supported in the Gamma model. Type name: " + _name_3);
        throw new UnsupportedOperationException(_plus_1);
      }
    }
    if ((((param.getType() instanceof IntegerTypeDefinition) || (param.getType() instanceof RationalTypeDefinition)) || (param.getType() instanceof DecimalTypeDefinition))) {
      CellType _cellType_3 = cell.getCellType();
      boolean _equals_4 = Objects.equal(_cellType_3, CellType.NUMERIC);
      if (_equals_4) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("raise ");
        _builder_1.append(portName);
        _builder_1.append(".");
        String _name_4 = event.getName();
        _builder_1.append(_name_4);
        _builder_1.append("(");
        String _string = Double.valueOf(cell.getNumericCellValue()).toString();
        _builder_1.append(_string);
        _builder_1.append(");");
        return _builder_1.toString();
      } else {
        CellType _cellType_4 = cell.getCellType();
        boolean _equals_5 = Objects.equal(_cellType_4, CellType.STRING);
        if (_equals_5) {
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("raise ");
          _builder_2.append(portName);
          _builder_2.append(".");
          String _name_5 = event.getName();
          _builder_2.append(_name_5);
          _builder_2.append("(");
          String _string_1 = cell.getStringCellValue().toString();
          _builder_2.append(_string_1);
          _builder_2.append(");");
          return _builder_2.toString();
        } else {
          throw new UnsupportedOperationException("Wrong cell type in the excel table. Cell type must be string or numeric.");
        }
      }
    }
    return "";
  }

  public ArrayList<Interface> convert2IF(final List<String> ifNames, final Set<Interface> interfaces) {
    ArrayList<Interface> ifList = new ArrayList<Interface>();
    for (final String ifName : ifNames) {
      {
        boolean found = false;
        for (final Interface i : interfaces) {
          String _name = i.getName();
          boolean _equals = Objects.equal(_name, ifName);
          if (_equals) {
            ifList.add(i);
            found = true;
          }
        }
        if ((!found)) {
          throw new UnsupportedOperationException(("Interface type is not found in interface list: " + ifName));
        }
      }
    }
    return ifList;
  }

  public String generate(final String file, final Set<Interface> interfaces, final String fileName, final Set<String> interfacePaths) {
    try {
      ArrayList<String> inPortNames = new ArrayList<String>();
      ArrayList<String> outPortNames = new ArrayList<String>();
      ArrayList<String> inPortIfNames = new ArrayList<String>();
      ArrayList<String> outPortIfNames = new ArrayList<String>();
      ArrayList<Interface> inInterfaces = new ArrayList<Interface>();
      ArrayList<Interface> outInterfaces = new ArrayList<Interface>();
      ArrayList<Event> outEvents = new ArrayList<Event>();
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
        inInterfaces = this.convert2IF(inPortIfNames, interfaces);
        outInterfaces = this.convert2IF(outPortIfNames, interfaces);
        final ArrayList<String> _converted_outPortNames = (ArrayList<String>)outPortNames;
        int _length = ((Object[])Conversions.unwrapArray(_converted_outPortNames, Object.class)).length;
        int _minus = (_length - 1);
        IntegerRange _upTo = new IntegerRange(0, _minus);
        for (final Integer k : _upTo) {
          final Predicate<EventDeclaration> _function = (EventDeclaration e) -> {
            int _length_1 = ((Object[])Conversions.unwrapArray(e.getEvent().getParameterDeclarations(), Object.class)).length;
            return (_length_1 > 0);
          };
          outEvents.add(outInterfaces.get((k).intValue()).getEvents().stream().filter(_function).toList().get(0).getEvent());
        }
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("// automatically generated Gamma statechart");
        _builder.newLine();
        _builder.append("// time of model generation: ");
        String _string = LocalDateTime.now().toString();
        _builder.append(_string);
        _builder.newLineIfNotEmpty();
        _builder.append("// generated from Excel table: ");
        _builder.append(file);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("package ");
        _builder.append(fileName);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        {
          for(final String ifPackage : interfacePaths) {
            _builder.append("import \"");
            _builder.append(ifPackage);
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.newLine();
        _builder.append("@TransitionPriority=order-based");
        _builder.newLine();
        _builder.append("statechart ");
        String _firstUpper = StringExtensions.toFirstUpper(compName);
        _builder.append(_firstUpper);
        _builder.append(" [");
        _builder.newLineIfNotEmpty();
        _builder.append(" \t");
        _builder.append("//Input ports ");
        int i = 0;
        _builder.newLineIfNotEmpty();
        {
          for(final String portName : inPortNames) {
            _builder.append(" \t");
            _builder.append("port ");
            _builder.append(portName, " \t");
            _builder.append(" : requires ");
            int _plusPlus = i++;
            String _get = inPortIfNames.get(_plusPlus);
            _builder.append(_get, " \t");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append(" \t");
        _builder.append("//Output ports ");
        _builder.append(i = 0, " \t");
        _builder.newLineIfNotEmpty();
        {
          for(final String portName_1 : outPortNames) {
            _builder.append(" \t");
            _builder.append("port ");
            _builder.append(portName_1, " \t");
            _builder.append(" : provides ");
            int _plusPlus_1 = i++;
            String _get_1 = outPortIfNames.get(_plusPlus_1);
            _builder.append(_get_1, " \t");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("]{");
        _builder.newLine();
        _builder.append(" \t");
        _builder.append("transition from init_0 to state_0 / ");
        {
          final ArrayList<String> _converted_outPortNames_1 = (ArrayList<String>)outPortNames;
          int _length_1 = ((Object[])Conversions.unwrapArray(_converted_outPortNames_1, Object.class)).length;
          int _minus_1 = (_length_1 - 1);
          IntegerRange _upTo_1 = new IntegerRange(0, _minus_1);
          boolean _hasElements = false;
          for(final Integer k_1 : _upTo_1) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate("", " \t");
            }
            _builder.append("raise ");
            String _get_2 = outPortNames.get((k_1).intValue());
            _builder.append(_get_2, " \t");
            _builder.append(".");
            String _name = outEvents.get((k_1).intValue()).getName();
            _builder.append(_name, " \t");
            _builder.append("(");
            String _serialize = this.expSerializer.serialize(ExpressionModelDerivedFeatures.getDefaultExpression(outEvents.get((k_1).intValue()).getParameterDeclarations().get(0).getType()));
            _builder.append(_serialize, " \t");
            _builder.append(");");
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append(" \t");
        _builder.append("transition from state_0 to c_0 when any");
        _builder.newLine();
        {
          List<Row> _list = IteratorExtensions.<Row>toList(rowIterator);
          for(final Row row : _list) {
            Iterator<Cell> cellIterator = row.cellIterator();
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("transition from c_0 to state_0 [");
            {
              final ArrayList<String> _converted_inPortNames = (ArrayList<String>)inPortNames;
              int _length_2 = ((Object[])Conversions.unwrapArray(_converted_inPortNames, Object.class)).length;
              int _minus_2 = (_length_2 - 1);
              IntegerRange _upTo_2 = new IntegerRange(0, _minus_2);
              boolean _hasElements_1 = false;
              for(final Integer j : _upTo_2) {
                if (!_hasElements_1) {
                  _hasElements_1 = true;
                } else {
                  _builder.appendImmediate(" and ", "\t");
                }
                String _condition = this.getCondition(inInterfaces.get((j).intValue()), cellIterator.next(), inPortNames.get((j).intValue()));
                _builder.append(_condition, "\t");
              }
            }
            _builder.append("]/  ");
            {
              final ArrayList<String> _converted_outPortNames_2 = (ArrayList<String>)outPortNames;
              int _length_3 = ((Object[])Conversions.unwrapArray(_converted_outPortNames_2, Object.class)).length;
              int _minus_3 = (_length_3 - 1);
              IntegerRange _upTo_3 = new IntegerRange(0, _minus_3);
              boolean _hasElements_2 = false;
              for(final Integer j_1 : _upTo_3) {
                if (!_hasElements_2) {
                  _hasElements_2 = true;
                } else {
                  _builder.appendImmediate("", "\t");
                }
                _builder.append(" ");
                String _effect = this.getEffect(outInterfaces.get((j_1).intValue()), cellIterator.next(), outPortNames.get((j_1).intValue()));
                _builder.append(_effect, "\t");
              }
            }
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append(" \t");
        _builder.append("transition from c_0 to state_0 [else] //default \"else\" transition");
        _builder.newLine();
        _builder.append(" \t");
        _builder.append("region main_0 {");
        _builder.newLine();
        _builder.append(" \t\t");
        _builder.append("initial init_0");
        _builder.newLine();
        _builder.append(" \t\t");
        _builder.append("state state_0");
        _builder.newLine();
        _builder.append(" \t\t");
        _builder.append("choice c_0");
        _builder.newLine();
        _builder.append(" \t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        String sct_gen = _builder.toString();
        this.excelFile.close();
        return sct_gen;
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          e.printStackTrace();
          this.excelFile.close();
          DialogUtil.showError(e.getMessage());
          return e.getMessage();
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
