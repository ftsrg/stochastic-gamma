/**
 * Copyright (c) 2018-2022 Contributors to the Gamma project
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * SPDX-License-Identifier: EPL-1.0
 */
package hu.bme.mit.gamma.environment.simulator.transformation.plantumlgen;

import com.google.common.base.Objects;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.bme.mit.gamma.environment.model.ComponentFilter;
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentDelay;
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource;
import hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource;
import hu.bme.mit.gamma.environment.model.EnvironmentRule;
import hu.bme.mit.gamma.environment.model.EnvironmentSample;
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EventFilter;
import hu.bme.mit.gamma.environment.model.Filter;
import hu.bme.mit.gamma.environment.model.ParameterFilter;
import hu.bme.mit.gamma.environment.model.PortFilter;
import hu.bme.mit.gamma.environment.model.StochasticRule;
import hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures;
import hu.bme.mit.gamma.environment.simulator.transformation.util.Util;
import hu.bme.mit.gamma.environment.stochastic.stochastic.BernoulliRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.BetaRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.CategoricalProbabaility;
import hu.bme.mit.gamma.environment.stochastic.stochastic.ExponentialRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.GammaRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.LogNormalRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.NormalRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.ParetoRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticModel;
import hu.bme.mit.gamma.environment.stochastic.stochastic.UniformRandomVariable;
import hu.bme.mit.gamma.environment.stochastic.stochastic.WeibullRandomVariable;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.expression.util.ExpressionSerializer;
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.Channel;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.CompositeComponent;
import hu.bme.mit.gamma.statechart.composite.InstancePortReference;
import hu.bme.mit.gamma.statechart.composite.PortBinding;
import hu.bme.mit.gamma.statechart.composite.SynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.interface_.RealizationMode;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class EnvironmentToPlantUmlTransformer {
  protected final CompositeComponent composite;

  @Extension
  protected ExpressionSerializer expressionSerializer = ExpressionSerializer.INSTANCE;

  protected int rank = 0;

  protected int rank2 = 0;

  protected int noteCNTR = 0;

  public EnvironmentToPlantUmlTransformer(final CompositeComponent composite) {
    this.composite = composite;
  }

  private String getKindString(final CompositeComponent composite) {
    if ((composite instanceof EnvironmentSynchronousCompositeComponent)) {
      return "stochastic synchronous";
    } else {
      if ((composite instanceof SynchronousCompositeComponent)) {
        return "synchronous";
      } else {
        if ((composite instanceof EnvironmentCascadeCompositeComponent)) {
          return "stochastic cascade";
        } else {
          if ((composite instanceof CascadeCompositeComponent)) {
            return "cascade";
          } else {
            if ((composite instanceof EnvironmentAsynchronousCompositeComponent)) {
              return "stochastic asynchronous";
            } else {
              if ((composite instanceof AsynchronousCompositeComponent)) {
                return "asynchronous";
              }
            }
          }
        }
      }
    }
    return null;
  }

  public String execute() {
    int _size = this.composite.getChannels().size();
    boolean _greaterThan = (_size > 40);
    if (_greaterThan) {
      return this.execute4();
    } else {
      return this.execute3();
    }
  }

  public String execute4() {
    final Table<ComponentInstance, ComponentInstance, List<Set<String>>> table = HashBasedTable.<ComponentInstance, ComponentInstance, List<Set<String>>>create();
    EList<Channel> _channels = this.composite.getChannels();
    for (final Channel channel : _channels) {
      {
        final Port provPort = channel.getProvidedPort().getPort();
        final ComponentInstance provInst = channel.getProvidedPort().getInstance();
        List<InstancePortReference> _requiredPorts = StatechartModelDerivedFeatures.getRequiredPorts(channel);
        for (final InstancePortReference reqPortRef : _requiredPorts) {
          {
            final Port reqPort = reqPortRef.getPort();
            final ComponentInstance reqInst = reqPortRef.getInstance();
            StringConcatenation _builder = new StringConcatenation();
            String _name = provPort.getName();
            _builder.append(_name);
            _builder.append(",");
            String _name_1 = reqPort.getName();
            _builder.append(_name_1);
            final String str = _builder.toString();
            if (((!table.contains(provInst, reqInst)) && (!table.contains(reqInst, provInst)))) {
              final List<Set<String>> l1 = CollectionLiterals.<Set<String>>newArrayList(CollectionLiterals.<String>newHashSet(), CollectionLiterals.<String>newHashSet());
              l1.get(0).add(provPort.getName());
              l1.get(1).add(reqPort.getName());
              table.put(provInst, reqInst, l1);
            } else {
              boolean _contains = table.contains(provInst, reqInst);
              if (_contains) {
                final List<Set<String>> d = table.get(provInst, reqInst);
                d.get(0).add(provPort.getName());
                d.get(1).add(reqPort.getName());
              } else {
                final List<Set<String>> d_1 = table.get(reqInst, provInst);
                d_1.get(0).add(reqPort.getName());
                d_1.get(1).add(provPort.getName());
              }
            }
          }
        }
      }
    }
    final Random rnd = new Random();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@startuml");
    _builder.newLine();
    _builder.append("allowmixing");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<style>");
    _builder.newLine();
    _builder.append("title {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("FontSize 12");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("</style>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("skinparam shadowing false");
    _builder.newLine();
    _builder.append("\'skinparam linetype ortho");
    _builder.newLine();
    _builder.append("!theme plain");
    _builder.newLine();
    _builder.append("\'left to right direction");
    _builder.newLine();
    _builder.append("\'top to bottom direction");
    _builder.newLine();
    _builder.append("skinparam nodesep 100");
    _builder.newLine();
    _builder.append("skinparam ranksep 100");
    _builder.newLine();
    _builder.append("skinparam defaultTextAlignment center");
    _builder.newLine();
    _builder.append("skinparam ComponentStereotypeFontSize 10");
    _builder.newLine();
    _builder.append("skinparam HeaderFontSize 12");
    _builder.newLine();
    _builder.append("skinparam padding 2");
    _builder.newLine();
    _builder.append("skinparam componentStyle rectangle");
    _builder.newLine();
    _builder.newLine();
    _builder.append("component \"");
    String _name = this.composite.getName();
    _builder.append(_name);
    _builder.append("\"<<");
    String _kindString = this.getKindString(this.composite);
    _builder.append(_kindString);
    _builder.append(">> {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    {
      final Function1<ComponentInstance, String> _function = (ComponentInstance c) -> {
        return c.getName();
      };
      List<? extends ComponentInstance> _sortBy = IterableExtensions.sortBy(StatechartModelDerivedFeatures.getDerivedComponents(this.composite), _function);
      for(final ComponentInstance component : _sortBy) {
        _builder.append("\t");
        _builder.append("component \"<size:12>");
        String _name_1 = component.getName();
        _builder.append(_name_1, "\t");
        _builder.append(":\\n<size:12>");
        String _name_2 = EnvironmentModelDerivedFeatures.getDerivedType(component).getName();
        _builder.append(_name_2, "\t");
        _builder.append("\" as ");
        String _name_3 = component.getName();
        _builder.append(_name_3, "\t");
        _builder.append("  {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("note right of ");
        String _name_4 = component.getName();
        _builder.append(_name_4, "\t");
        _builder.append(" ");
        _builder.newLineIfNotEmpty();
        {
          List<Port> _allPortsWithOutput = StatechartModelDerivedFeatures.getAllPortsWithOutput(EnvironmentModelDerivedFeatures.getDerivedType(component));
          boolean _hasElements = false;
          for(final Port port : _allPortsWithOutput) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate("", "");
            }
            _builder.append("| ");
            String _name_5 = port.getName();
            _builder.append(_name_5);
            _builder.append(" | ");
            {
              Iterable<ParameterDeclaration> _parameters = Util.parameters(port);
              boolean _hasElements_1 = false;
              for(final ParameterDeclaration param : _parameters) {
                if (!_hasElements_1) {
                  _hasElements_1 = true;
                } else {
                  _builder.appendImmediate("\\n ", "");
                }
                String _key = Util.key(port, param, component);
                _builder.append(_key);
              }
            }
            _builder.append(" |");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("endnote");
        _builder.newLine();
      }
    }
    _builder.newLine();
    {
      final Function1<EnvironmentComponentInstance, String> _function_1 = (EnvironmentComponentInstance c) -> {
        return c.getName();
      };
      List<EnvironmentComponentInstance> _sortBy_1 = IterableExtensions.<EnvironmentComponentInstance, String>sortBy(EnvironmentModelDerivedFeatures.getEnvironmentComponents(this.composite), _function_1);
      for(final EnvironmentComponentInstance component_1 : _sortBy_1) {
        _builder.append("\t");
        _builder.append("component \"<size:12>");
        String _name_6 = component_1.getName();
        _builder.append(_name_6, "\t");
        _builder.append(" \\n----\\n");
        {
          EList<EnvironmentRule> _behaviorRules = ((ElementaryEnvironmentComponentInstance) component_1).getBehaviorRules();
          for(final EnvironmentRule rule : _behaviorRules) {
            {
              if ((rule instanceof StochasticRule)) {
                _builder.append("<size:10>");
                {
                  EList<Filter> _filter = ((StochasticRule)rule).getFilter();
                  for(final Filter filter : _filter) {
                    String _filterType = EnvironmentToPlantUmlTransformer.filterType(filter);
                    _builder.append(_filterType, "\t");
                    _builder.append(" ");
                  }
                }
                _builder.append(": ");
                CharSequence _generateDitribution = this.generateDitribution(((StochasticRule)rule).getStochasticModel());
                _builder.append(_generateDitribution, "\t");
                _builder.append("\\n");
              }
            }
          }
        }
        _builder.append("\" as ");
        String _name_7 = component_1.getName();
        _builder.append(_name_7, "\t");
        _builder.append(" <<Stochastic ");
        String _envType = EnvironmentToPlantUmlTransformer.envType(((ElementaryEnvironmentComponentInstance) component_1));
        _builder.append(_envType, "\t");
        _builder.append(">>{");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      EList<Port> _ports = this.composite.getPorts();
      for(final Port port_1 : _ports) {
        {
          RealizationMode _realizationMode = port_1.getInterfaceRealization().getRealizationMode();
          boolean _equals = Objects.equal(_realizationMode, RealizationMode.REQUIRED);
          if (_equals) {
            _builder.append("\t");
            _builder.append("\'portin \"");
            String _name_8 = port_1.getName();
            _builder.append(_name_8, "\t");
            _builder.append(":\\n ~");
            String _name_9 = StatechartModelDerivedFeatures.getInterface(port_1).getName();
            _builder.append(_name_9, "\t");
            _builder.append("\" as ");
            String _name_10 = port_1.getName();
            _builder.append(_name_10, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          RealizationMode _realizationMode_1 = port_1.getInterfaceRealization().getRealizationMode();
          boolean _equals_1 = Objects.equal(_realizationMode_1, RealizationMode.PROVIDED);
          if (_equals_1) {
            _builder.append("\t");
            _builder.append("\'portout \"");
            String _name_11 = port_1.getName();
            _builder.append(_name_11, "\t");
            _builder.append(":\\n ");
            String _name_12 = StatechartModelDerivedFeatures.getInterface(port_1).getName();
            _builder.append(_name_12, "\t");
            _builder.append("\" as ");
            String _name_13 = port_1.getName();
            _builder.append(_name_13, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      EList<PortBinding> _portBindings = this.composite.getPortBindings();
      for(final PortBinding binding : _portBindings) {
        {
          RealizationMode _realizationMode_2 = binding.getInstancePortReference().getPort().getInterfaceRealization().getRealizationMode();
          boolean _equals_2 = Objects.equal(_realizationMode_2, RealizationMode.REQUIRED);
          if (_equals_2) {
            _builder.append("\t");
            _builder.append("\'");
            String _name_14 = binding.getCompositeSystemPort().getName();
            _builder.append(_name_14, "\t");
            _builder.append(" ..d.> ");
            String _name_15 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_15, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          RealizationMode _realizationMode_3 = binding.getInstancePortReference().getPort().getInterfaceRealization().getRealizationMode();
          boolean _equals_3 = Objects.equal(_realizationMode_3, RealizationMode.PROVIDED);
          if (_equals_3) {
            _builder.append("\t");
            _builder.append("\'");
            String _name_16 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_16, "\t");
            _builder.append(" ..d.> ");
            String _name_17 = binding.getCompositeSystemPort().getName();
            _builder.append(_name_17, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      Set<ComponentInstance> _rowKeySet = table.rowKeySet();
      for(final ComponentInstance provInst : _rowKeySet) {
        {
          Set<ComponentInstance> _columnKeySet = table.columnKeySet();
          for(final ComponentInstance reqInst : _columnKeySet) {
            {
              boolean _isNullOrEmpty = IterableExtensions.isNullOrEmpty(table.get(provInst, reqInst));
              boolean _not = (!_isNullOrEmpty);
              if (_not) {
                _builder.append("\t");
                String _name_18 = provInst.getName();
                _builder.append(_name_18, "\t");
                _builder.append(" \"");
                {
                  Set<String> _get = table.get(provInst, reqInst).get(0);
                  boolean _hasElements_2 = false;
                  for(final String p : _get) {
                    if (!_hasElements_2) {
                      _hasElements_2 = true;
                    } else {
                      _builder.appendImmediate(", \\n", "\t");
                    }
                    _builder.append(p, "\t");
                  }
                }
                _builder.append(" \" #---# \"");
                {
                  Set<String> _get_1 = table.get(provInst, reqInst).get(1);
                  boolean _hasElements_3 = false;
                  for(final String p_1 : _get_1) {
                    if (!_hasElements_3) {
                      _hasElements_3 = true;
                    } else {
                      _builder.appendImmediate(", \\n", "\t");
                    }
                    _builder.append(p_1, "\t");
                  }
                }
                _builder.append(" \" ");
                String _name_19 = reqInst.getName();
                _builder.append(_name_19, "\t");
                _builder.append(" ");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("@enduml");
    _builder.newLine();
    return _builder.toString();
  }

  public String execute2() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@startuml");
    _builder.newLine();
    _builder.append("skinparam shadowing false");
    _builder.newLine();
    _builder.append("!theme plain");
    _builder.newLine();
    _builder.append("left to right direction");
    _builder.newLine();
    _builder.append("skinparam nodesep 20");
    _builder.newLine();
    _builder.append("skinparam ranksep 60");
    _builder.newLine();
    _builder.append("skinparam defaultTextAlignment center");
    _builder.newLine();
    _builder.append("skinparam linetype polyline");
    _builder.newLine();
    _builder.append("skinparam padding 4");
    _builder.newLine();
    _builder.newLine();
    _builder.append("component \"");
    String _name = this.composite.getName();
    _builder.append(_name);
    _builder.append("\"<<");
    String _kindString = this.getKindString(this.composite);
    _builder.append(_kindString);
    _builder.append(">> {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    {
      List<? extends ComponentInstance> _derivedComponents = StatechartModelDerivedFeatures.getDerivedComponents(this.composite);
      for(final ComponentInstance component : _derivedComponents) {
        _builder.append("\t");
        _builder.append("component  ");
        String _name_1 = component.getName();
        _builder.append(_name_1, "\t");
        _builder.append("  [");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("{{");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("digraph G {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("graph [pad=0]");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("n [ margin=0 height=");
        int _length = ((Object[])Conversions.unwrapArray(StatechartModelDerivedFeatures.getAllPorts(EnvironmentModelDerivedFeatures.getDerivedType(component)), Object.class)).length;
        double _multiply = (_length * 0.4);
        String _string = Double.valueOf((0.3 + _multiply)).toString();
        _builder.append(_string, "\t");
        _builder.append(" width=");
        int _length_1 = ((Object[])Conversions.unwrapArray(EnvironmentModelDerivedFeatures.getDerivedType(component).getPorts(), Object.class)).length;
        double _multiply_1 = (_length_1 * 0.1);
        String _string_1 = Double.valueOf((0.1 + _multiply_1)).toString();
        _builder.append(_string_1, "\t");
        _builder.append(" shape=plaintext fontname=\"SansSerif\" label=\"");
        String _name_2 = component.getName();
        _builder.append(_name_2, "\t");
        _builder.append(" : ");
        String _name_3 = EnvironmentModelDerivedFeatures.getDerivedType(component).getName();
        _builder.append(_name_3, "\t");
        _builder.append("\"]");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("]");
        _builder.newLine();
      }
    }
    _builder.newLine();
    {
      List<EnvironmentComponentInstance> _environmentComponents = EnvironmentModelDerivedFeatures.getEnvironmentComponents(this.composite);
      for(final EnvironmentComponentInstance component_1 : _environmentComponents) {
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("component ");
        String _name_4 = component_1.getName();
        _builder.append(_name_4, "\t\t");
        _builder.append(" [");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        String _name_5 = component_1.getName();
        _builder.append(_name_5, "\t\t");
        _builder.append(" : Stochastic ");
        String _envType = EnvironmentToPlantUmlTransformer.envType(((ElementaryEnvironmentComponentInstance) component_1));
        _builder.append(_envType, "\t\t");
        _builder.newLineIfNotEmpty();
        {
          EList<EnvironmentRule> _behaviorRules = ((ElementaryEnvironmentComponentInstance) component_1).getBehaviorRules();
          for(final EnvironmentRule rule : _behaviorRules) {
            _builder.append("\t");
            _builder.append("\t");
            {
              if ((rule instanceof StochasticRule)) {
                {
                  EList<Filter> _filter = ((StochasticRule)rule).getFilter();
                  for(final Filter filter : _filter) {
                    String _filterType = EnvironmentToPlantUmlTransformer.filterType(filter);
                    _builder.append(_filterType, "\t\t");
                    _builder.append(" ");
                  }
                }
                _builder.append(": ");
                CharSequence _generateDitribution = this.generateDitribution(((StochasticRule)rule).getStochasticModel());
                _builder.append(_generateDitribution, "\t\t");
              }
            }
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("]");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      EList<Port> _ports = this.composite.getPorts();
      for(final Port port : _ports) {
        {
          RealizationMode _realizationMode = port.getInterfaceRealization().getRealizationMode();
          boolean _equals = Objects.equal(_realizationMode, RealizationMode.REQUIRED);
          if (_equals) {
            _builder.append("\t");
            _builder.append("portin ");
            String _name_6 = port.getName();
            _builder.append(_name_6, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          RealizationMode _realizationMode_1 = port.getInterfaceRealization().getRealizationMode();
          boolean _equals_1 = Objects.equal(_realizationMode_1, RealizationMode.PROVIDED);
          if (_equals_1) {
            _builder.append("\t");
            _builder.append("portout ");
            String _name_7 = port.getName();
            _builder.append(_name_7, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      EList<PortBinding> _portBindings = this.composite.getPortBindings();
      for(final PortBinding binding : _portBindings) {
        {
          RealizationMode _realizationMode_2 = binding.getInstancePortReference().getPort().getInterfaceRealization().getRealizationMode();
          boolean _equals_2 = Objects.equal(_realizationMode_2, RealizationMode.REQUIRED);
          if (_equals_2) {
            _builder.append("\t");
            String _name_8 = binding.getCompositeSystemPort().getName();
            _builder.append(_name_8, "\t");
            _builder.append(" ..# \"");
            String _name_9 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_9, "\t");
            _builder.append("\" ");
            String _name_10 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_10, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          RealizationMode _realizationMode_3 = binding.getInstancePortReference().getPort().getInterfaceRealization().getRealizationMode();
          boolean _equals_3 = Objects.equal(_realizationMode_3, RealizationMode.PROVIDED);
          if (_equals_3) {
            _builder.append("\t");
            String _name_11 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_11, "\t");
            _builder.append(" \"");
            String _name_12 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_12, "\t");
            _builder.append("\" #.. ");
            String _name_13 = binding.getCompositeSystemPort().getName();
            _builder.append(_name_13, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("\'");
        String _name_14 = this.composite.getName();
        _builder.append(_name_14, "\t");
        _builder.append(" \"");
        String _name_15 = binding.getCompositeSystemPort().getName();
        _builder.append(_name_15, "\t");
        _builder.append("\" #.# \"");
        String _name_16 = binding.getInstancePortReference().getPort().getName();
        _builder.append(_name_16, "\t");
        _builder.append("\" ");
        String _name_17 = binding.getInstancePortReference().getInstance().getName();
        _builder.append(_name_17, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<Channel> _channels = this.composite.getChannels();
      for(final Channel channel : _channels) {
        {
          List<InstancePortReference> _requiredPorts = StatechartModelDerivedFeatures.getRequiredPorts(channel);
          for(final InstancePortReference requiredPort : _requiredPorts) {
            _builder.append("\t");
            String _name_18 = channel.getProvidedPort().getInstance().getName();
            _builder.append(_name_18, "\t");
            _builder.append(" \"");
            String _name_19 = channel.getProvidedPort().getPort().getName();
            _builder.append(_name_19, "\t");
            _builder.append("\" #--0)--# \"");
            String _name_20 = requiredPort.getPort().getName();
            _builder.append(_name_20, "\t");
            _builder.append("\" ");
            String _name_21 = requiredPort.getInstance().getName();
            _builder.append(_name_21, "\t");
            _builder.append(" : \"<size:10>//");
            String _name_22 = StatechartModelDerivedFeatures.getInterface(requiredPort.getPort()).getName();
            _builder.append(_name_22, "\t");
            _builder.append("//\" ");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\'}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("@enduml");
    _builder.newLine();
    return _builder.toString();
  }

  public String execute3() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@startuml");
    _builder.newLine();
    _builder.append("<style>");
    _builder.newLine();
    _builder.append("title {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("FontSize 12");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("</style>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("skinparam shadowing false");
    _builder.newLine();
    _builder.append("\'skinparam linetype ortho");
    _builder.newLine();
    _builder.append("!theme plain");
    _builder.newLine();
    _builder.append("\'left to right direction");
    _builder.newLine();
    _builder.append("top to bottom direction");
    _builder.newLine();
    _builder.append("skinparam nodesep 60");
    _builder.newLine();
    _builder.append("skinparam ranksep 30");
    _builder.newLine();
    _builder.append("skinparam defaultTextAlignment center");
    _builder.newLine();
    _builder.append("skinparam ComponentStereotypeFontSize 10");
    _builder.newLine();
    _builder.append("skinparam HeaderFontSize 12");
    _builder.newLine();
    _builder.append("skinparam padding 1");
    _builder.newLine();
    _builder.append("skinparam componentStyle rectangle");
    _builder.newLine();
    _builder.newLine();
    _builder.append("component \"");
    String _name = this.composite.getName();
    _builder.append(_name);
    _builder.append("\"<<");
    String _kindString = this.getKindString(this.composite);
    _builder.append(_kindString);
    _builder.append(">> {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    {
      List<? extends ComponentInstance> _derivedComponents = StatechartModelDerivedFeatures.getDerivedComponents(this.composite);
      for(final ComponentInstance component : _derivedComponents) {
        _builder.append("\t");
        _builder.append("component \"<size:12>");
        String _name_1 = component.getName();
        _builder.append(_name_1, "\t");
        _builder.append(":\\n<size:12>");
        String _name_2 = EnvironmentModelDerivedFeatures.getDerivedType(component).getName();
        _builder.append(_name_2, "\t");
        _builder.append("\" as ");
        String _name_3 = component.getName();
        _builder.append(_name_3, "\t");
        _builder.append("  {");
        _builder.newLineIfNotEmpty();
        {
          List<Port> _allPorts = StatechartModelDerivedFeatures.getAllPorts(EnvironmentModelDerivedFeatures.getDerivedType(component));
          for(final Port port : _allPorts) {
            {
              if (true) {
                {
                  RealizationMode _realizationMode = port.getInterfaceRealization().getRealizationMode();
                  boolean _equals = Objects.equal(_realizationMode, RealizationMode.REQUIRED);
                  if (_equals) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("portin \"");
                    String _name_4 = port.getName();
                    _builder.append(_name_4, "\t\t");
                    _builder.append(":\\n ~");
                    String _name_5 = StatechartModelDerivedFeatures.getInterface(port).getName();
                    _builder.append(_name_5, "\t\t");
                    _builder.append(" \" as ");
                    String _name_6 = component.getName();
                    _builder.append(_name_6, "\t\t");
                    _builder.append("__");
                    String _name_7 = port.getName();
                    _builder.append(_name_7, "\t\t");
                    _builder.newLineIfNotEmpty();
                  }
                }
                {
                  RealizationMode _realizationMode_1 = port.getInterfaceRealization().getRealizationMode();
                  boolean _equals_1 = Objects.equal(_realizationMode_1, RealizationMode.PROVIDED);
                  if (_equals_1) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("portout \"");
                    String _name_8 = port.getName();
                    _builder.append(_name_8, "\t\t");
                    _builder.append(":\\n ");
                    String _name_9 = StatechartModelDerivedFeatures.getInterface(port).getName();
                    _builder.append(_name_9, "\t\t");
                    _builder.append("\" as ");
                    String _name_10 = component.getName();
                    _builder.append(_name_10, "\t\t");
                    _builder.append("__");
                    String _name_11 = port.getName();
                    _builder.append(_name_11, "\t\t");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t");
                    String _note = Util.note(port, component);
                    _builder.append(_note, "\t\t");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        String _note_1 = Util.note(component);
        _builder.append(_note_1, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      List<EnvironmentComponentInstance> _environmentComponents = EnvironmentModelDerivedFeatures.getEnvironmentComponents(this.composite);
      for(final EnvironmentComponentInstance component_1 : _environmentComponents) {
        _builder.append("\t");
        _builder.append("component \"<size:12>");
        String _name_12 = component_1.getName();
        _builder.append(_name_12, "\t");
        _builder.append(" \\n----\\n");
        {
          EList<EnvironmentRule> _behaviorRules = ((ElementaryEnvironmentComponentInstance) component_1).getBehaviorRules();
          for(final EnvironmentRule rule : _behaviorRules) {
            {
              if ((rule instanceof StochasticRule)) {
                _builder.append("<size:10>");
                {
                  EList<Filter> _filter = ((StochasticRule)rule).getFilter();
                  for(final Filter filter : _filter) {
                    String _filterType = EnvironmentToPlantUmlTransformer.filterType(filter);
                    _builder.append(_filterType, "\t");
                    _builder.append(" ");
                  }
                }
                _builder.append(": ");
                CharSequence _generateDitribution = this.generateDitribution(((StochasticRule)rule).getStochasticModel());
                _builder.append(_generateDitribution, "\t");
                _builder.append("\\n");
              }
            }
          }
        }
        _builder.append("\" as ");
        String _name_13 = component_1.getName();
        _builder.append(_name_13, "\t");
        _builder.append(" <<Stochastic ");
        String _envType = EnvironmentToPlantUmlTransformer.envType(((ElementaryEnvironmentComponentInstance) component_1));
        _builder.append(_envType, "\t");
        _builder.append(">>{");
        _builder.newLineIfNotEmpty();
        {
          EList<Port> _inports = ((ElementaryEnvironmentComponentInstance) component_1).getInports();
          for(final Port port_1 : _inports) {
            {
              if (true) {
                _builder.append("\t");
                _builder.append("portin \"");
                String _name_14 = port_1.getName();
                _builder.append(_name_14, "\t");
                _builder.append("\\n ~");
                String _name_15 = StatechartModelDerivedFeatures.getInterface(port_1).getName();
                _builder.append(_name_15, "\t");
                _builder.append("\" as ");
                String _name_16 = component_1.getName();
                _builder.append(_name_16, "\t");
                _builder.append("__");
                String _name_17 = port_1.getName();
                _builder.append(_name_17, "\t");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        {
          EList<Port> _outports = ((ElementaryEnvironmentComponentInstance) component_1).getOutports();
          for(final Port port_2 : _outports) {
            {
              if (true) {
                _builder.append("\t");
                _builder.append("portout \"");
                String _name_18 = port_2.getName();
                _builder.append(_name_18, "\t");
                _builder.append(":\\n ");
                String _name_19 = StatechartModelDerivedFeatures.getInterface(port_2).getName();
                _builder.append(_name_19, "\t");
                _builder.append("\" as ");
                String _name_20 = component_1.getName();
                _builder.append(_name_20, "\t");
                _builder.append("__");
                String _name_21 = port_2.getName();
                _builder.append(_name_21, "\t");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      EList<Port> _ports = this.composite.getPorts();
      for(final Port port_3 : _ports) {
        {
          RealizationMode _realizationMode_2 = port_3.getInterfaceRealization().getRealizationMode();
          boolean _equals_2 = Objects.equal(_realizationMode_2, RealizationMode.REQUIRED);
          if (_equals_2) {
            _builder.append("\t");
            _builder.append("portin \"");
            String _name_22 = port_3.getName();
            _builder.append(_name_22, "\t");
            _builder.append(":\\n ~");
            String _name_23 = StatechartModelDerivedFeatures.getInterface(port_3).getName();
            _builder.append(_name_23, "\t");
            _builder.append("\" as ");
            String _name_24 = port_3.getName();
            _builder.append(_name_24, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          RealizationMode _realizationMode_3 = port_3.getInterfaceRealization().getRealizationMode();
          boolean _equals_3 = Objects.equal(_realizationMode_3, RealizationMode.PROVIDED);
          if (_equals_3) {
            _builder.append("\t");
            _builder.append("portout \"");
            String _name_25 = port_3.getName();
            _builder.append(_name_25, "\t");
            _builder.append(":\\n ");
            String _name_26 = StatechartModelDerivedFeatures.getInterface(port_3).getName();
            _builder.append(_name_26, "\t");
            _builder.append("\" as ");
            String _name_27 = port_3.getName();
            _builder.append(_name_27, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      EList<PortBinding> _portBindings = this.composite.getPortBindings();
      for(final PortBinding binding : _portBindings) {
        {
          RealizationMode _realizationMode_4 = binding.getInstancePortReference().getPort().getInterfaceRealization().getRealizationMode();
          boolean _equals_4 = Objects.equal(_realizationMode_4, RealizationMode.REQUIRED);
          if (_equals_4) {
            _builder.append("\t");
            String _name_28 = binding.getCompositeSystemPort().getName();
            _builder.append(_name_28, "\t");
            _builder.append(" .d.> ");
            String _name_29 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_29, "\t");
            _builder.append("__");
            String _name_30 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_30, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          RealizationMode _realizationMode_5 = binding.getInstancePortReference().getPort().getInterfaceRealization().getRealizationMode();
          boolean _equals_5 = Objects.equal(_realizationMode_5, RealizationMode.PROVIDED);
          if (_equals_5) {
            _builder.append("\t");
            String _name_31 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_31, "\t");
            _builder.append("__");
            String _name_32 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_32, "\t");
            _builder.append(" .d.> ");
            String _name_33 = binding.getCompositeSystemPort().getName();
            _builder.append(_name_33, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("\'");
        String _name_34 = this.composite.getName();
        _builder.append(_name_34, "\t");
        _builder.append(" \"");
        String _name_35 = binding.getCompositeSystemPort().getName();
        _builder.append(_name_35, "\t");
        _builder.append("\" #.# \"");
        String _name_36 = binding.getInstancePortReference().getPort().getName();
        _builder.append(_name_36, "\t");
        _builder.append("\" ");
        String _name_37 = binding.getInstancePortReference().getInstance().getName();
        _builder.append(_name_37, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<Channel> _channels = this.composite.getChannels();
      for(final Channel channel : _channels) {
        {
          List<InstancePortReference> _requiredPorts = StatechartModelDerivedFeatures.getRequiredPorts(channel);
          for(final InstancePortReference requiredPort : _requiredPorts) {
            {
              if (((!StatechartModelDerivedFeatures.getOutputEvents(channel.getProvidedPort().getPort()).isEmpty()) && StatechartModelDerivedFeatures.getInputEvents(channel.getProvidedPort().getPort()).isEmpty())) {
                _builder.append("\t");
                String _name_38 = channel.getProvidedPort().getInstance().getName();
                _builder.append(_name_38, "\t");
                _builder.append("__");
                String _name_39 = channel.getProvidedPort().getPort().getName();
                _builder.append(_name_39, "\t");
                _builder.append(" ----> ");
                String _name_40 = requiredPort.getInstance().getName();
                _builder.append(_name_40, "\t");
                _builder.append("__");
                String _name_41 = requiredPort.getPort().getName();
                _builder.append(_name_41, "\t");
                _builder.newLineIfNotEmpty();
              } else {
                if ((StatechartModelDerivedFeatures.getOutputEvents(channel.getProvidedPort().getPort()).isEmpty() && (!StatechartModelDerivedFeatures.getInputEvents(channel.getProvidedPort().getPort()).isEmpty()))) {
                  _builder.append("\t");
                  String _name_42 = channel.getProvidedPort().getInstance().getName();
                  _builder.append(_name_42, "\t");
                  _builder.append("__");
                  String _name_43 = channel.getProvidedPort().getPort().getName();
                  _builder.append(_name_43, "\t");
                  _builder.append(" <---- ");
                  String _name_44 = requiredPort.getInstance().getName();
                  _builder.append(_name_44, "\t");
                  _builder.append("__");
                  String _name_45 = requiredPort.getPort().getName();
                  _builder.append(_name_45, "\t");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("\t");
                  String _name_46 = channel.getProvidedPort().getInstance().getName();
                  _builder.append(_name_46, "\t");
                  _builder.append("__");
                  String _name_47 = channel.getProvidedPort().getPort().getName();
                  _builder.append(_name_47, "\t");
                  _builder.append(" <----> ");
                  String _name_48 = requiredPort.getInstance().getName();
                  _builder.append(_name_48, "\t");
                  _builder.append("__");
                  String _name_49 = requiredPort.getPort().getName();
                  _builder.append(_name_49, "\t");
                  _builder.newLineIfNotEmpty();
                }
              }
            }
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    {
      EList<Port> _ports_1 = this.composite.getPorts();
      for(final Port port_4 : _ports_1) {
        String _note_2 = Util.note(port_4);
        _builder.append(_note_2);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("@enduml");
    _builder.newLine();
    return _builder.toString();
  }

  public String linkGen(final int rank) {
    StringBuilder sb = new StringBuilder();
    sb.append("-");
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, 1, true);
    for (final Integer i : _doubleDotLessThan) {
      sb.append("-");
    }
    return sb.toString();
  }

  public String linkGen2(final int rank) {
    StringBuilder sb = new StringBuilder();
    sb.append(".");
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, 0, true);
    for (final Integer i : _doubleDotLessThan) {
      sb.append(".");
    }
    return sb.toString();
  }

  protected CharSequence _generateDitribution(final NormalRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Normal(loc=");
    String _serialize = this.expressionSerializer.serialize(dist.getMean());
    _builder.append(_serialize);
    _builder.append(",scale=");
    String _serialize_1 = this.expressionSerializer.serialize(dist.getScale());
    _builder.append(_serialize_1);
    _builder.append(")");
    return _builder;
  }

  protected CharSequence _generateDitribution(final WeibullRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Weibull(concentration=");
    String _serialize = this.expressionSerializer.serialize(dist.getShape());
    _builder.append(_serialize);
    _builder.append(",shape=");
    String _serialize_1 = this.expressionSerializer.serialize(dist.getScale());
    _builder.append(_serialize_1);
    _builder.append(")");
    return _builder;
  }

  protected CharSequence _generateDitribution(final GammaRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Gamma(concentration=");
    String _serialize = this.expressionSerializer.serialize(dist.getShape());
    _builder.append(_serialize);
    _builder.append(",rate=");
    String _serialize_1 = this.expressionSerializer.serialize(dist.getScale());
    _builder.append(_serialize_1);
    _builder.append(")");
    return _builder;
  }

  protected CharSequence _generateDitribution(final UniformRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Uniform(low=");
    String _serialize = this.expressionSerializer.serialize(dist.getLowerBound());
    _builder.append(_serialize);
    _builder.append(",high=");
    String _serialize_1 = this.expressionSerializer.serialize(dist.getUpperBound());
    _builder.append(_serialize_1);
    _builder.append(")");
    return _builder;
  }

  protected CharSequence _generateDitribution(final LogNormalRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("LogNormal(loc=");
    String _serialize = this.expressionSerializer.serialize(dist.getMean());
    _builder.append(_serialize);
    _builder.append(",scale=");
    String _serialize_1 = this.expressionSerializer.serialize(dist.getScale());
    _builder.append(_serialize_1);
    _builder.append(")");
    return _builder;
  }

  protected CharSequence _generateDitribution(final ParetoRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Pareto(alpha=");
    String _serialize = this.expressionSerializer.serialize(dist.getAlpha());
    _builder.append(_serialize);
    _builder.append(",scale=");
    String _serialize_1 = this.expressionSerializer.serialize(dist.getScale());
    _builder.append(_serialize_1);
    _builder.append(")");
    return _builder;
  }

  protected CharSequence _generateDitribution(final BetaRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Beta(concentration1=");
    String _serialize = this.expressionSerializer.serialize(dist.getApha());
    _builder.append(_serialize);
    _builder.append(",concentration0=");
    String _serialize_1 = this.expressionSerializer.serialize(dist.getBeta());
    _builder.append(_serialize_1);
    _builder.append(")");
    return _builder;
  }

  protected CharSequence _generateDitribution(final ExponentialRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Exponential(");
    String _serialize = this.expressionSerializer.serialize(dist.getRate());
    _builder.append(_serialize);
    _builder.append(")");
    return _builder;
  }

  protected CharSequence _generateDitribution(final BernoulliRandomVariable dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Bernoulli(");
    String _serialize = this.expressionSerializer.serialize(dist.getProbability());
    _builder.append(_serialize);
    _builder.append(")");
    return _builder;
  }

  protected CharSequence _generateDitribution(final CategoricalProbabaility dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("prob = ");
    String _serialize = this.expressionSerializer.serialize(dist.getProbability());
    _builder.append(_serialize);
    return _builder;
  }

  protected CharSequence _generateDitribution(final StochasticModel dist) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Stochastic");
    return _builder;
  }

  protected static String _filterType(final ComponentFilter filter) {
    return "*.*";
  }

  protected static String _filterType(final ParameterFilter filter) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = filter.getEvent().getPort().getName();
    _builder.append(_name);
    _builder.append(".");
    String _name_1 = filter.getEvent().getEvent().getName();
    _builder.append(_name_1);
    _builder.append("::");
    String _name_2 = filter.getParameter().getName();
    _builder.append(_name_2);
    return _builder.toString();
  }

  protected static String _filterType(final EventFilter filter) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = filter.getEvent().getPort().getName();
    _builder.append(_name);
    _builder.append(".");
    String _name_1 = filter.getEvent().getEvent().getName();
    _builder.append(_name_1);
    return _builder.toString();
  }

  protected static String _filterType(final PortFilter filter) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = filter.getPort().getName();
    _builder.append(_name);
    _builder.append(".*");
    return _builder.toString();
  }

  protected static String _envType(final EnvironmentEventSource comp) {
    return "Source";
  }

  protected static String _envType(final EnvironmentPeriodicEventSource comp) {
    return "Periodic Source";
  }

  protected static String _envType(final EnvironmentSwitch comp) {
    return "Switch";
  }

  protected static String _envType(final EnvironmentDelay comp) {
    return "Delay";
  }

  protected static String _envType(final EnvironmentSample comp) {
    return "Sample";
  }

  public CharSequence generateDitribution(final StochasticModel dist) {
    if (dist instanceof BernoulliRandomVariable) {
      return _generateDitribution((BernoulliRandomVariable)dist);
    } else if (dist instanceof BetaRandomVariable) {
      return _generateDitribution((BetaRandomVariable)dist);
    } else if (dist instanceof ExponentialRandomVariable) {
      return _generateDitribution((ExponentialRandomVariable)dist);
    } else if (dist instanceof GammaRandomVariable) {
      return _generateDitribution((GammaRandomVariable)dist);
    } else if (dist instanceof LogNormalRandomVariable) {
      return _generateDitribution((LogNormalRandomVariable)dist);
    } else if (dist instanceof NormalRandomVariable) {
      return _generateDitribution((NormalRandomVariable)dist);
    } else if (dist instanceof ParetoRandomVariable) {
      return _generateDitribution((ParetoRandomVariable)dist);
    } else if (dist instanceof UniformRandomVariable) {
      return _generateDitribution((UniformRandomVariable)dist);
    } else if (dist instanceof WeibullRandomVariable) {
      return _generateDitribution((WeibullRandomVariable)dist);
    } else if (dist instanceof CategoricalProbabaility) {
      return _generateDitribution((CategoricalProbabaility)dist);
    } else if (dist != null) {
      return _generateDitribution(dist);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(dist).toString());
    }
  }

  protected static String filterType(final Filter filter) {
    if (filter instanceof ParameterFilter) {
      return _filterType((ParameterFilter)filter);
    } else if (filter instanceof ComponentFilter) {
      return _filterType((ComponentFilter)filter);
    } else if (filter instanceof EventFilter) {
      return _filterType((EventFilter)filter);
    } else if (filter instanceof PortFilter) {
      return _filterType((PortFilter)filter);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(filter).toString());
    }
  }

  protected static String envType(final ElementaryEnvironmentComponentInstance comp) {
    if (comp instanceof EnvironmentDelay) {
      return _envType((EnvironmentDelay)comp);
    } else if (comp instanceof EnvironmentEventSource) {
      return _envType((EnvironmentEventSource)comp);
    } else if (comp instanceof EnvironmentPeriodicEventSource) {
      return _envType((EnvironmentPeriodicEventSource)comp);
    } else if (comp instanceof EnvironmentSample) {
      return _envType((EnvironmentSample)comp);
    } else if (comp instanceof EnvironmentSwitch) {
      return _envType((EnvironmentSwitch)comp);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(comp).toString());
    }
  }
}
