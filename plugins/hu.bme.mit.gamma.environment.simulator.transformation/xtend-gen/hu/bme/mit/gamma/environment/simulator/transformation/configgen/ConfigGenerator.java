package hu.bme.mit.gamma.environment.simulator.transformation.configgen;

import hu.bme.mit.gamma.environment.analysis.AnalysisComponent;
import hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures;
import hu.bme.mit.gamma.environment.simulator.transformation.util.Util;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.CompositeComponent;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition;
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class ConfigGenerator {
  public Object generateMapping(final ComponentInstance instance, final String namespace, final List<String> diagramNames) {
    Object _xblockexpression = null;
    {
      Component component = EnvironmentModelDerivedFeatures.getDerivedType(instance);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(namespace);
      _builder.append("__");
      String _firstUpper = StringExtensions.toFirstUpper(instance.getName());
      _builder.append(_firstUpper);
      String namespace2 = _builder.toString();
      Object _xifexpression = null;
      if ((component instanceof AsynchronousStatechartDefinition)) {
        _xifexpression = null;
      } else {
        Object _xifexpression_1 = null;
        if ((component instanceof StatechartDefinition)) {
          _xifexpression_1 = null;
        } else {
          Object _xifexpression_2 = null;
          if ((component instanceof AsynchronousAdapter)) {
            Object _xblockexpression_1 = null;
            {
              StringConcatenation _builder_1 = new StringConcatenation();
              _builder_1.append(namespace2);
              _builder_1.append(" : ");
              _builder_1.newLineIfNotEmpty();
              _builder_1.append("    ");
              _builder_1.append("dummy : \"dummy\"");
              _builder_1.newLine();
              {
                EList<Port> _ports = EnvironmentModelDerivedFeatures.getDerivedType(((AsynchronousAdapter)component).getWrappedComponent()).getPorts();
                for(final Port port : _ports) {
                  {
                    Iterable<ParameterDeclaration> _parameters = Util.parameters(port);
                    for(final ParameterDeclaration param : _parameters) {
                      _builder_1.append("    ");
                      CharSequence _key = Util.key(port, param);
                      _builder_1.append(_key, "    ");
                      _builder_1.append(" : \"");
                      String _firstUpper_1 = StringExtensions.toFirstUpper(port.getName());
                      _builder_1.append(_firstUpper_1, "    ");
                      _builder_1.append("::");
                      String _firstUpper_2 = StringExtensions.toFirstUpper(param.getName());
                      _builder_1.append(_firstUpper_2, "    ");
                      _builder_1.append("\"");
                      _builder_1.newLineIfNotEmpty();
                    }
                  }
                }
              }
              diagramNames.add(_builder_1.toString());
              _xblockexpression_1 = this.generateMapping(((AsynchronousAdapter)component).getWrappedComponent(), namespace2, diagramNames);
            }
            _xifexpression_2 = _xblockexpression_1;
          } else {
            if ((component instanceof CompositeComponent)) {
              StringConcatenation _builder_1 = new StringConcatenation();
              _builder_1.append(namespace2);
              _builder_1.append(" : ");
              _builder_1.newLineIfNotEmpty();
              _builder_1.append("    ");
              _builder_1.append("dummy : \"dummy\"");
              _builder_1.newLine();
              {
                EList<Port> _ports = ((CompositeComponent)component).getPorts();
                for(final Port port : _ports) {
                  {
                    Iterable<ParameterDeclaration> _parameters = Util.parameters(port);
                    for(final ParameterDeclaration param : _parameters) {
                      _builder_1.append("    ");
                      CharSequence _key = Util.key(port, param);
                      _builder_1.append(_key, "    ");
                      _builder_1.append(" : \"");
                      String _firstUpper_1 = StringExtensions.toFirstUpper(port.getName());
                      _builder_1.append(_firstUpper_1, "    ");
                      _builder_1.append("::");
                      String _firstUpper_2 = StringExtensions.toFirstUpper(param.getName());
                      _builder_1.append(_firstUpper_2, "    ");
                      _builder_1.append("\"");
                      _builder_1.newLineIfNotEmpty();
                    }
                  }
                }
              }
              {
                List<? extends ComponentInstance> _derivedComponents = StatechartModelDerivedFeatures.getDerivedComponents(((CompositeComponent)component));
                for(final ComponentInstance inst : _derivedComponents) {
                  {
                    final Function1<Port, Boolean> _function = (Port p) -> {
                      return Boolean.valueOf(StatechartModelDerivedFeatures.isProvided(p.getInterfaceRealization()));
                    };
                    Iterable<Port> _filter = IterableExtensions.<Port>filter(StatechartModelDerivedFeatures.getAllPorts(EnvironmentModelDerivedFeatures.getDerivedType(inst)), _function);
                    for(final Port port_1 : _filter) {
                      {
                        Iterable<ParameterDeclaration> _parameters_1 = Util.parameters(port_1);
                        for(final ParameterDeclaration param_1 : _parameters_1) {
                          _builder_1.append("    ");
                          CharSequence _key_1 = Util.key(port_1, param_1, inst);
                          _builder_1.append(_key_1, "    ");
                          _builder_1.append(" : \"");
                          String _firstUpper_3 = StringExtensions.toFirstUpper(inst.getName());
                          _builder_1.append(_firstUpper_3, "    ");
                          _builder_1.append("::");
                          String _firstUpper_4 = StringExtensions.toFirstUpper(port_1.getName());
                          _builder_1.append(_firstUpper_4, "    ");
                          _builder_1.append("::");
                          String _firstUpper_5 = StringExtensions.toFirstUpper(param_1.getName());
                          _builder_1.append(_firstUpper_5, "    ");
                          _builder_1.append("\"");
                          _builder_1.newLineIfNotEmpty();
                        }
                      }
                    }
                  }
                  {
                    Component _derivedType = EnvironmentModelDerivedFeatures.getDerivedType(inst);
                    if ((_derivedType instanceof StatechartDefinition)) {
                      _builder_1.append("    ");
                      String _keyword = Util.keyword(inst);
                      _builder_1.append(_keyword, "    ");
                      _builder_1.append(" : \"__STATECHART__");
                      String _firstUpper_6 = StringExtensions.toFirstUpper(inst.getName());
                      _builder_1.append(_firstUpper_6, "    ");
                      _builder_1.append("\"");
                      _builder_1.newLineIfNotEmpty();
                    }
                  }
                }
              }
              diagramNames.add(_builder_1.toString());
              List<ComponentInstance> _allInstances = StatechartModelDerivedFeatures.getAllInstances(component);
              for (final ComponentInstance inst_1 : _allInstances) {
                this.generateMapping(inst_1, namespace2, diagramNames);
              }
            }
          }
          _xifexpression_1 = _xifexpression_2;
        }
        _xifexpression = _xifexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }

  public String generate(final AnalysisComponent analysisComponent) {
    LinkedList<String> mappings = new LinkedList<String>();
    this.generateMapping(analysisComponent.getAnalyzedComponent(), analysisComponent.getName(), mappings);
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final String mapping : mappings) {
        _builder.append(mapping);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    return _builder.toString();
  }
}
