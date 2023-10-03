package hu.bme.mit.gamma.environment.simulator.transformation.util;

import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.statechart.Region;
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class Util {
  public static String keyword(final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("$");
    _builder.append(name);
    _builder.append("______________________$");
    return _builder.toString();
  }

  public static CharSequence key(final Port port, final ParameterDeclaration param) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("$");
    String _firstUpper = StringExtensions.toFirstUpper(port.getName());
    _builder.append(_firstUpper);
    _builder.append("_");
    String _firstUpper_1 = StringExtensions.toFirstUpper(param.getName());
    _builder.append(_firstUpper_1);
    _builder.append("$");
    return _builder;
  }

  public static CharSequence key(final Port port, final ParameterDeclaration param, final ComponentInstance instance) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("$");
    String _firstUpper = StringExtensions.toFirstUpper(instance.getName());
    _builder.append(_firstUpper);
    _builder.append("_");
    String _firstUpper_1 = StringExtensions.toFirstUpper(port.getName());
    _builder.append(_firstUpper_1);
    _builder.append("_");
    String _firstUpper_2 = StringExtensions.toFirstUpper(param.getName());
    _builder.append(_firstUpper_2);
    _builder.append("$");
    return _builder;
  }

  public static String keyword(final Port port) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Iterable<ParameterDeclaration> _parameters = Util.parameters(port);
      boolean _hasElements = false;
      for(final ParameterDeclaration param : _parameters) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate("\\n", "");
        }
        CharSequence _key = Util.key(port, param);
        _builder.append(_key);
      }
    }
    return _builder.toString();
  }

  public static String keyword(final ComponentInstance instance) {
    final Component type = StatechartModelDerivedFeatures.getDerivedType(instance);
    if ((type instanceof StatechartDefinition)) {
      StringConcatenation _builder = new StringConcatenation();
      String _name = instance.getName();
      _builder.append(_name);
      _builder.append("_SCT");
      return _builder.toString();
    } else {
      return "";
    }
  }

  public static String keyword(final Port port, final ComponentInstance instance) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Iterable<ParameterDeclaration> _parameters = Util.parameters(port);
      boolean _hasElements = false;
      for(final ParameterDeclaration param : _parameters) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate("\\n", "");
        }
        CharSequence _key = Util.key(port, param, instance);
        _builder.append(_key);
      }
    }
    return _builder.toString();
  }

  public static String keyword(final Region region) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("$$_");
    String _name = region.getName();
    _builder.append(_name);
    _builder.append("_$$");
    return _builder.toString();
  }

  public static Iterable<ParameterDeclaration> parameters(final Port port) {
    final Function1<Event, EList<ParameterDeclaration>> _function = (Event e) -> {
      return e.getParameterDeclarations();
    };
    return IterableExtensions.<Event, ParameterDeclaration>flatMap(StatechartModelDerivedFeatures.getOutputEvents(port), _function);
  }

  public static String note(final Port port) {
    boolean _isEmpty = IterableExtensions.isEmpty(Util.parameters(port));
    if (_isEmpty) {
      return "";
    } else {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("note \"");
      String _keyword = Util.keyword(port);
      _builder.append(_keyword);
      _builder.append("\" as note_");
      String _name = port.getName();
      _builder.append(_name);
      _builder.newLineIfNotEmpty();
      {
        boolean _isProvided = StatechartModelDerivedFeatures.isProvided(port.getInterfaceRealization());
        if (_isProvided) {
          String _name_1 = port.getName();
          _builder.append(_name_1);
          _builder.append(" .d. note_");
          String _name_2 = port.getName();
          _builder.append(_name_2);
          _builder.newLineIfNotEmpty();
        } else {
          _builder.append("note_");
          String _name_3 = port.getName();
          _builder.append(_name_3);
          _builder.append(" .d. ");
          String _name_4 = port.getName();
          _builder.append(_name_4);
          _builder.newLineIfNotEmpty();
        }
      }
      return _builder.toString();
    }
  }

  public static String noteAdpt(final Port port) {
    boolean _isEmpty = IterableExtensions.isEmpty(Util.parameters(port));
    if (_isEmpty) {
      return "";
    } else {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("note \"");
      String _keyword = Util.keyword(port);
      _builder.append(_keyword);
      _builder.append("\" as note_");
      String _name = port.getName();
      _builder.append(_name);
      _builder.newLineIfNotEmpty();
      _builder.append("c_");
      String _name_1 = port.getName();
      _builder.append(_name_1);
      _builder.append(" .. note_");
      String _name_2 = port.getName();
      _builder.append(_name_2);
      _builder.newLineIfNotEmpty();
      return _builder.toString();
    }
  }

  public static String note(final ComponentInstance instance) {
    Component _derivedType = StatechartModelDerivedFeatures.getDerivedType(instance);
    if ((_derivedType instanceof StatechartDefinition)) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("note \"");
      String _keyword = Util.keyword(instance);
      _builder.append(_keyword);
      _builder.append("\" as note_");
      String _name = instance.getName();
      _builder.append(_name);
      _builder.newLineIfNotEmpty();
      _builder.append("note_");
      String _name_1 = instance.getName();
      _builder.append(_name_1);
      _builder.append(" .. ");
      String _name_2 = instance.getName();
      _builder.append(_name_2);
      _builder.newLineIfNotEmpty();
      return _builder.toString();
    }
    return null;
  }

  public static String note(final Port port, final ComponentInstance instance) {
    boolean _isEmpty = IterableExtensions.isEmpty(Util.parameters(port));
    if (_isEmpty) {
      return "";
    } else {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("note \"");
      String _keyword = Util.keyword(port, instance);
      _builder.append(_keyword);
      _builder.append("\" as note_");
      String _name = instance.getName();
      _builder.append(_name);
      _builder.append("_");
      String _name_1 = port.getName();
      _builder.append(_name_1);
      _builder.newLineIfNotEmpty();
      _builder.append("note_");
      String _name_2 = instance.getName();
      _builder.append(_name_2);
      _builder.append("_");
      String _name_3 = port.getName();
      _builder.append(_name_3);
      _builder.append(" .. ");
      String _name_4 = instance.getName();
      _builder.append(_name_4);
      _builder.append("__");
      String _name_5 = port.getName();
      _builder.append(_name_5);
      _builder.newLineIfNotEmpty();
      return _builder.toString();
    }
  }
}
