/**
 * Copyright (c) 2018-2020 Contributors to the Gamma project
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * SPDX-License-Identifier: EPL-1.0
 */
package hu.bme.mit.gamma.codegeneration.java;

import hu.bme.mit.gamma.codegeneration.java.queries.BroadcastChannels;
import hu.bme.mit.gamma.codegeneration.java.queries.SimpleChannels;
import hu.bme.mit.gamma.codegeneration.java.util.Namings;
import hu.bme.mit.gamma.codegeneration.java.util.TimingDeterminer;
import hu.bme.mit.gamma.environment.model.AbstractEnvironmentCompositeComponent;
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.statechart.composite.AbstractAsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.composite.BroadcastChannel;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.PortBinding;
import hu.bme.mit.gamma.statechart.composite.SimpleChannel;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.Port;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class EnvironmentAsynchronousCompositeComponentCodeGenerator {
  protected final String PACKAGE_NAME;
  
  @Extension
  protected final TimingDeterminer timingDeterminer = TimingDeterminer.INSTANCE;
  
  @Extension
  protected final Trace trace;
  
  @Extension
  protected final NameGenerator nameGenerator;
  
  @Extension
  protected final TypeTransformer typeTransformer;
  
  @Extension
  protected final ComponentCodeGenerator componentCodeGenerator;
  
  @Extension
  protected final CompositeComponentCodeGenerator compositeComponentCodeGenerator;
  
  public EnvironmentAsynchronousCompositeComponentCodeGenerator(final String packageName, final Trace trace) {
    this.PACKAGE_NAME = packageName;
    this.trace = trace;
    NameGenerator _nameGenerator = new NameGenerator(this.PACKAGE_NAME);
    this.nameGenerator = _nameGenerator;
    TypeTransformer _typeTransformer = new TypeTransformer(trace);
    this.typeTransformer = _typeTransformer;
    ComponentCodeGenerator _componentCodeGenerator = new ComponentCodeGenerator(this.trace);
    this.componentCodeGenerator = _componentCodeGenerator;
    CompositeComponentCodeGenerator _compositeComponentCodeGenerator = new CompositeComponentCodeGenerator(this.PACKAGE_NAME, this.trace);
    this.compositeComponentCodeGenerator = _compositeComponentCodeGenerator;
  }
  
  /**
   * Creates the Java code of the asynchronous composite class, containing asynchronous components.
   */
  protected CharSequence generateEnvironmentInports(final AbstractAsynchronousCompositeComponent component) {
    StringConcatenation _builder = new StringConcatenation();
    EnvironmentAsynchronousCompositeComponent comp = ((EnvironmentAsynchronousCompositeComponent) component);
    _builder.newLineIfNotEmpty();
    final Function1<EnvironmentComponentInstance, Boolean> _function = (EnvironmentComponentInstance c) -> {
      return Boolean.valueOf((c instanceof EnvironmentAsynchronousCompositeComponentInstance));
    };
    final Function1<EnvironmentComponentInstance, EnvironmentAsynchronousCompositeComponentInstance> _function_1 = (EnvironmentComponentInstance c) -> {
      return ((EnvironmentAsynchronousCompositeComponentInstance) c);
    };
    final Function1<EnvironmentAsynchronousCompositeComponentInstance, EnvironmentAsynchronousCompositeComponent> _function_2 = (EnvironmentAsynchronousCompositeComponentInstance c) -> {
      return c.getType();
    };
    Iterable<EnvironmentAsynchronousCompositeComponent> types = IterableExtensions.<EnvironmentAsynchronousCompositeComponentInstance, EnvironmentAsynchronousCompositeComponent>map(IterableExtensions.<EnvironmentComponentInstance, EnvironmentAsynchronousCompositeComponentInstance>map(IterableExtensions.<EnvironmentComponentInstance>filter(comp.getEnvironmentComponents(), _function), _function_1), _function_2);
    _builder.newLineIfNotEmpty();
    {
      for(final EnvironmentAsynchronousCompositeComponent t : types) {
        _builder.append("import ");
        _builder.append(this.PACKAGE_NAME);
        _builder.append(".");
        String _lowerCase = t.getName().toLowerCase();
        _builder.append(_lowerCase);
        _builder.append(".*;");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  protected CharSequence createEnvironmentAsynchronousCompositeComponentClass(final AbstractAsynchronousCompositeComponent component, final int channelId1, final int channelId2) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    CharSequence _generateComponentPackageName = this.nameGenerator.generateComponentPackageName(component);
    _builder.append(_generateComponentPackageName);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _generateCompositeSystemImports = this.compositeComponentCodeGenerator.generateCompositeSystemImports(component);
    _builder.append(_generateCompositeSystemImports);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _generateEnvironmentInports = this.generateEnvironmentInports(component);
    _builder.append(_generateEnvironmentInports);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public class ");
    String _generateComponentClassName = this.nameGenerator.generateComponentClassName(component);
    _builder.append(_generateComponentClassName);
    _builder.append(" implements ");
    String _generatePortOwnerInterfaceName = this.nameGenerator.generatePortOwnerInterfaceName(component);
    _builder.append(_generatePortOwnerInterfaceName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("// Component instances");
    _builder.newLine();
    {
      EList<AsynchronousComponentInstance> _components = component.getComponents();
      for(final AsynchronousComponentInstance instance : _components) {
        _builder.append("\t");
        _builder.append("private ");
        String _generateComponentClassName_1 = this.nameGenerator.generateComponentClassName(instance.getType());
        _builder.append(_generateComponentClassName_1, "\t");
        _builder.append(" ");
        String _name = instance.getName();
        _builder.append(_name, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      if ((component instanceof EnvironmentAsynchronousCompositeComponent)) {
        _builder.append("\t");
        _builder.append("// Environmental Component instances");
        _builder.newLine();
        {
          EList<EnvironmentComponentInstance> _environmentComponents = ((EnvironmentAsynchronousCompositeComponent)component).getEnvironmentComponents();
          for(final EnvironmentComponentInstance instance_1 : _environmentComponents) {
            {
              if ((instance_1 instanceof EnvironmentAsynchronousCompositeComponentInstance)) {
                _builder.append("\t");
                _builder.append("private ");
                String _generateComponentClassName_2 = this.nameGenerator.generateComponentClassName(((EnvironmentAsynchronousCompositeComponentInstance)instance_1).getType());
                _builder.append(_generateComponentClassName_2, "\t");
                _builder.append(" ");
                String _name_1 = ((EnvironmentAsynchronousCompositeComponentInstance)instance_1).getName();
                _builder.append(_name_1, "\t");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("// Port instances");
    _builder.newLine();
    {
      EList<Port> _ports = component.getPorts();
      for(final Port port : _ports) {
        _builder.append("\t");
        _builder.append("private ");
        String _firstUpper = StringExtensions.toFirstUpper(port.getName());
        _builder.append(_firstUpper, "\t");
        _builder.append(" ");
        String _firstLower = StringExtensions.toFirstLower(port.getName());
        _builder.append(_firstLower, "\t");
        _builder.append(" = new ");
        String _firstUpper_1 = StringExtensions.toFirstUpper(port.getName());
        _builder.append(_firstUpper_1, "\t");
        _builder.append("();");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("// Channel instances");
    _builder.newLine();
    {
      Set<SimpleChannel> _allValuesOfsimpleChannel = SimpleChannels.Matcher.on(this.trace.engine).getAllValuesOfsimpleChannel(component, null, null);
      for(final SimpleChannel channel : _allValuesOfsimpleChannel) {
        _builder.append("\t");
        _builder.append("private ");
        String _generateChannelInterfaceName = this.nameGenerator.generateChannelInterfaceName(channel.getProvidedPort().getPort().getInterfaceRealization().getInterface());
        _builder.append(_generateChannelInterfaceName, "\t");
        _builder.append(" channel");
        String _firstUpper_2 = StringExtensions.toFirstUpper(channel.getProvidedPort().getPort().getName());
        _builder.append(_firstUpper_2, "\t");
        _builder.append("Of");
        String _firstUpper_3 = StringExtensions.toFirstUpper(channel.getProvidedPort().getInstance().getName());
        _builder.append(_firstUpper_3, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      Set<BroadcastChannel> _allValuesOfbroadcastChannel = BroadcastChannels.Matcher.on(this.trace.engine).getAllValuesOfbroadcastChannel(component, null, null);
      for(final BroadcastChannel channel_1 : _allValuesOfbroadcastChannel) {
        _builder.append("\t");
        _builder.append("private ");
        String _generateChannelInterfaceName_1 = this.nameGenerator.generateChannelInterfaceName(channel_1.getProvidedPort().getPort().getInterfaceRealization().getInterface());
        _builder.append(_generateChannelInterfaceName_1, "\t");
        _builder.append(" channel");
        String _firstUpper_4 = StringExtensions.toFirstUpper(channel_1.getProvidedPort().getPort().getName());
        _builder.append(_firstUpper_4, "\t");
        _builder.append("Of");
        String _firstUpper_5 = StringExtensions.toFirstUpper(channel_1.getProvidedPort().getInstance().getName());
        _builder.append(_firstUpper_5, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    CharSequence _generateParameterDeclarationFields = this.componentCodeGenerator.generateParameterDeclarationFields(component);
    _builder.append(_generateParameterDeclarationFields, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean isEmpty(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ");
    {
      List<ComponentInstance> _instances = StatechartModelDerivedFeatures.getInstances(component);
      boolean _hasElements = false;
      for(final ComponentInstance comp : _instances) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(" && ", "\t\t");
        }
        _builder.append(" ");
        String _name_2 = comp.getName();
        _builder.append(_name_2, "\t\t");
        _builder.append(".isEmpty() ");
      }
    }
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void schedule(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("while(!isEmpty()){");
    _builder.newLine();
    {
      List<ComponentInstance> _instances_1 = StatechartModelDerivedFeatures.getInstances(component);
      for(final ComponentInstance inst : _instances_1) {
        _builder.append("\t\t\t");
        String _name_3 = inst.getName();
        _builder.append(_name_3, "\t\t\t");
        _builder.append(".schedule();");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    {
      boolean _needTimer = this.timingDeterminer.needTimer(component);
      if (_needTimer) {
        _builder.append("\t");
        _builder.append("public ");
        String _generateComponentClassName_3 = this.nameGenerator.generateComponentClassName(component);
        _builder.append(_generateComponentClassName_3, "\t");
        _builder.append("(");
        {
          EList<ParameterDeclaration> _parameterDeclarations = component.getParameterDeclarations();
          boolean _hasElements_1 = false;
          for(final ParameterDeclaration parameter : _parameterDeclarations) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(", ", "\t");
            }
            String _transformType = this.typeTransformer.transformType(parameter.getType());
            _builder.append(_transformType, "\t");
            _builder.append(" ");
            String _name_4 = parameter.getName();
            _builder.append(_name_4, "\t");
          }
          if (_hasElements_1) {
            _builder.append(", ", "\t");
          }
        }
        _builder.append(Namings.YAKINDU_TIMER_INTERFACE, "\t");
        _builder.append(" timer) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        CharSequence _createInstances = this.compositeComponentCodeGenerator.createInstances(component);
        _builder.append(_createInstances, "\t\t");
        _builder.newLineIfNotEmpty();
        {
          if ((component instanceof EnvironmentAsynchronousCompositeComponent)) {
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("// Environmental Component instances");
            _builder.newLine();
            {
              EList<EnvironmentComponentInstance> _environmentComponents_1 = ((EnvironmentAsynchronousCompositeComponent)component).getEnvironmentComponents();
              for(final EnvironmentComponentInstance instance_2 : _environmentComponents_1) {
                {
                  if ((instance_2 instanceof EnvironmentAsynchronousCompositeComponentInstance)) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    String _name_5 = ((EnvironmentAsynchronousCompositeComponentInstance)instance_2).getName();
                    _builder.append(_name_5, "\t\t\t");
                    _builder.append(" = new ");
                    String _firstUpper_6 = StringExtensions.toFirstUpper(((EnvironmentAsynchronousCompositeComponentInstance)instance_2).getType().getName());
                    _builder.append(_firstUpper_6, "\t\t\t");
                    _builder.append("();");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("setTimer(timer);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("init(); // Init is not called in setTimer like in the wrapper as it would be unnecessary");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _generateComponentClassName_4 = this.nameGenerator.generateComponentClassName(component);
    _builder.append(_generateComponentClassName_4, "\t");
    _builder.append("(");
    {
      EList<ParameterDeclaration> _parameterDeclarations_1 = component.getParameterDeclarations();
      boolean _hasElements_2 = false;
      for(final ParameterDeclaration parameter_1 : _parameterDeclarations_1) {
        if (!_hasElements_2) {
          _hasElements_2 = true;
        } else {
          _builder.appendImmediate(", ", "\t");
        }
        String _transformType_1 = this.typeTransformer.transformType(parameter_1.getType());
        _builder.append(_transformType_1, "\t");
        _builder.append(" ");
        String _name_6 = parameter_1.getName();
        _builder.append(_name_6, "\t");
      }
    }
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    CharSequence _createInstances_1 = this.compositeComponentCodeGenerator.createInstances(component);
    _builder.append(_createInstances_1, "\t\t");
    _builder.newLineIfNotEmpty();
    {
      if ((component instanceof EnvironmentAsynchronousCompositeComponent)) {
        _builder.append("\t\t");
        _builder.append("// Environmental Component instances");
        _builder.newLine();
        {
          EList<EnvironmentComponentInstance> _environmentComponents_2 = ((EnvironmentAsynchronousCompositeComponent)component).getEnvironmentComponents();
          for(final EnvironmentComponentInstance instance_3 : _environmentComponents_2) {
            {
              if ((instance_3 instanceof EnvironmentAsynchronousCompositeComponentInstance)) {
                _builder.append("\t\t");
                _builder.append("\t");
                String _name_7 = ((EnvironmentAsynchronousCompositeComponentInstance)instance_3).getName();
                _builder.append(_name_7, "\t\t\t");
                _builder.append(" = new ");
                String _firstUpper_7 = StringExtensions.toFirstUpper(((EnvironmentAsynchronousCompositeComponentInstance)instance_3).getType().getName());
                _builder.append(_firstUpper_7, "\t\t\t");
                _builder.append("();");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("\t\t");
    _builder.append("init();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Resets the contained statemachines recursively. Must be called to initialize the component. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void reset() {");
    _builder.newLine();
    {
      EList<AsynchronousComponentInstance> _components_1 = component.getComponents();
      for(final AsynchronousComponentInstance instance_4 : _components_1) {
        _builder.append("\t\t");
        String _name_8 = instance_4.getName();
        _builder.append(_name_8, "\t\t");
        _builder.append(".reset();");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      if ((component instanceof EnvironmentAsynchronousCompositeComponent)) {
        {
          EList<EnvironmentComponentInstance> _environmentComponents_3 = ((EnvironmentAsynchronousCompositeComponent)component).getEnvironmentComponents();
          for(final EnvironmentComponentInstance envComp : _environmentComponents_3) {
            {
              if ((envComp instanceof EnvironmentAsynchronousCompositeComponentInstance)) {
                _builder.append("\t\t");
                String _name_9 = ((EnvironmentAsynchronousCompositeComponentInstance)envComp).getName();
                _builder.append(_name_9, "\t\t");
                _builder.append(".reset();");
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
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Creates the channel mappings and enters the wrapped statemachines. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private void init() {\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// Registration of simple channels");
    _builder.newLine();
    {
      Collection<SimpleChannels.Match> _allMatches = SimpleChannels.Matcher.on(this.trace.engine).getAllMatches(component, null, null, null);
      for(final SimpleChannels.Match channelMatch : _allMatches) {
        {
          if (((!(channelMatch.getProvidedPort().getInstance() instanceof ElementaryEnvironmentComponentInstance)) && (!(channelMatch.getRequiredPort().getInstance() instanceof ElementaryEnvironmentComponentInstance)))) {
            _builder.append("\t\t");
            _builder.append("channel");
            String _firstUpper_8 = StringExtensions.toFirstUpper(channelMatch.getProvidedPort().getPort().getName());
            _builder.append(_firstUpper_8, "\t\t");
            _builder.append("Of");
            String _firstUpper_9 = StringExtensions.toFirstUpper(channelMatch.getProvidedPort().getInstance().getName());
            _builder.append(_firstUpper_9, "\t\t");
            _builder.append(" = new ");
            String _generateChannelName = this.nameGenerator.generateChannelName(channelMatch.getProvidedPort().getPort().getInterfaceRealization().getInterface());
            _builder.append(_generateChannelName, "\t\t");
            _builder.append("(");
            String _name_10 = channelMatch.getProvidedPort().getInstance().getName();
            _builder.append(_name_10, "\t\t");
            _builder.append(".get");
            String _firstUpper_10 = StringExtensions.toFirstUpper(channelMatch.getProvidedPort().getPort().getName());
            _builder.append(_firstUpper_10, "\t\t");
            _builder.append("());");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("channel");
            String _firstUpper_11 = StringExtensions.toFirstUpper(channelMatch.getProvidedPort().getPort().getName());
            _builder.append(_firstUpper_11, "\t\t");
            _builder.append("Of");
            String _firstUpper_12 = StringExtensions.toFirstUpper(channelMatch.getProvidedPort().getInstance().getName());
            _builder.append(_firstUpper_12, "\t\t");
            _builder.append(".registerPort(");
            String _name_11 = channelMatch.getRequiredPort().getInstance().getName();
            _builder.append(_name_11, "\t\t");
            _builder.append(".get");
            String _firstUpper_13 = StringExtensions.toFirstUpper(channelMatch.getRequiredPort().getPort().getName());
            _builder.append(_firstUpper_13, "\t\t");
            _builder.append("());");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t\t");
    _builder.append("// Registration of broadcast channels");
    _builder.newLine();
    {
      Set<BroadcastChannel> _allValuesOfbroadcastChannel_1 = BroadcastChannels.Matcher.on(this.trace.engine).getAllValuesOfbroadcastChannel(component, null, null);
      for(final BroadcastChannel channel_2 : _allValuesOfbroadcastChannel_1) {
        {
          ComponentInstance _instance = channel_2.getProvidedPort().getInstance();
          boolean _not = (!(_instance instanceof ElementaryEnvironmentComponentInstance));
          if (_not) {
            _builder.append("\t\t");
            _builder.append("channel");
            String _firstUpper_14 = StringExtensions.toFirstUpper(channel_2.getProvidedPort().getPort().getName());
            _builder.append(_firstUpper_14, "\t\t");
            _builder.append("Of");
            String _firstUpper_15 = StringExtensions.toFirstUpper(channel_2.getProvidedPort().getInstance().getName());
            _builder.append(_firstUpper_15, "\t\t");
            _builder.append(" = new ");
            String _generateChannelName_1 = this.nameGenerator.generateChannelName(channel_2.getProvidedPort().getPort().getInterfaceRealization().getInterface());
            _builder.append(_generateChannelName_1, "\t\t");
            _builder.append("(");
            String _name_12 = channel_2.getProvidedPort().getInstance().getName();
            _builder.append(_name_12, "\t\t");
            _builder.append(".get");
            String _firstUpper_16 = StringExtensions.toFirstUpper(channel_2.getProvidedPort().getPort().getName());
            _builder.append(_firstUpper_16, "\t\t");
            _builder.append("());");
            _builder.newLineIfNotEmpty();
            {
              Collection<BroadcastChannels.Match> _allMatches_1 = BroadcastChannels.Matcher.on(this.trace.engine).getAllMatches(component, channel_2, null, null);
              for(final BroadcastChannels.Match channelMatch_1 : _allMatches_1) {
                {
                  ComponentInstance _instance_1 = channelMatch_1.getRequiredPort().getInstance();
                  boolean _not_1 = (!(_instance_1 instanceof ElementaryEnvironmentComponentInstance));
                  if (_not_1) {
                    _builder.append("\t\t");
                    _builder.append("\t");
                    _builder.append("channel");
                    String _firstUpper_17 = StringExtensions.toFirstUpper(channelMatch_1.getProvidedPort().getPort().getName());
                    _builder.append(_firstUpper_17, "\t\t\t");
                    _builder.append("Of");
                    String _firstUpper_18 = StringExtensions.toFirstUpper(channelMatch_1.getProvidedPort().getInstance().getName());
                    _builder.append(_firstUpper_18, "\t\t\t");
                    _builder.append(".registerPort(");
                    String _name_13 = channelMatch_1.getRequiredPort().getInstance().getName();
                    _builder.append(_name_13, "\t\t\t");
                    _builder.append(".get");
                    String _firstUpper_19 = StringExtensions.toFirstUpper(channelMatch_1.getRequiredPort().getPort().getName());
                    _builder.append(_firstUpper_19, "\t\t\t");
                    _builder.append("());");
                    _builder.newLineIfNotEmpty();
                  }
                }
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
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// Inner classes representing Ports");
    _builder.newLine();
    {
      EList<Port> _ports_1 = component.getPorts();
      boolean _hasElements_3 = false;
      for(final Port systemPort : _ports_1) {
        if (!_hasElements_3) {
          _hasElements_3 = true;
        } else {
          _builder.appendImmediate("\n", "\t");
        }
        _builder.append("\t");
        _builder.append("public class ");
        String _firstUpper_20 = StringExtensions.toFirstUpper(systemPort.getName());
        _builder.append(_firstUpper_20, "\t");
        _builder.append(" implements ");
        String _implementationName = Namings.getImplementationName(systemPort.getInterfaceRealization().getInterface());
        _builder.append(_implementationName, "\t");
        _builder.append(".");
        String _firstUpper_21 = StringExtensions.toFirstUpper(systemPort.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
        _builder.append(_firstUpper_21, "\t");
        _builder.append(" {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        CharSequence _delegateRaisingMethods = this.compositeComponentCodeGenerator.delegateRaisingMethods(systemPort);
        _builder.append(_delegateRaisingMethods, "\t\t");
        _builder.append(" ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        CharSequence _delegateOutMethods = this.compositeComponentCodeGenerator.delegateOutMethods(systemPort);
        _builder.append(_delegateOutMethods, "\t\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("public void registerListener(");
        String _implementationName_1 = Namings.getImplementationName(systemPort.getInterfaceRealization().getInterface());
        _builder.append(_implementationName_1, "\t\t");
        _builder.append(".Listener.");
        String _firstUpper_22 = StringExtensions.toFirstUpper(systemPort.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
        _builder.append(_firstUpper_22, "\t\t");
        _builder.append(" listener) {");
        _builder.newLineIfNotEmpty();
        {
          Collection<PortBinding> _portBindings = StatechartModelDerivedFeatures.getPortBindings(systemPort);
          for(final PortBinding portDef : _portBindings) {
            {
              ComponentInstance _instance_2 = portDef.getInstancePortReference().getInstance();
              boolean _not_2 = (!(_instance_2 instanceof ElementaryEnvironmentComponentInstance));
              if (_not_2) {
                _builder.append("\t");
                _builder.append("\t\t");
                String _name_14 = portDef.getInstancePortReference().getInstance().getName();
                _builder.append(_name_14, "\t\t\t");
                _builder.append(".get");
                String _firstUpper_23 = StringExtensions.toFirstUpper(portDef.getInstancePortReference().getPort().getName());
                _builder.append(_firstUpper_23, "\t\t\t");
                _builder.append("().registerListener(listener);");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("public List<");
        String _implementationName_2 = Namings.getImplementationName(systemPort.getInterfaceRealization().getInterface());
        _builder.append(_implementationName_2, "\t\t");
        _builder.append(".Listener.");
        String _firstUpper_24 = StringExtensions.toFirstUpper(systemPort.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
        _builder.append(_firstUpper_24, "\t\t");
        _builder.append("> getRegisteredListeners() {");
        _builder.newLineIfNotEmpty();
        {
          Collection<PortBinding> _portBindings_1 = StatechartModelDerivedFeatures.getPortBindings(systemPort);
          for(final PortBinding portDef_1 : _portBindings_1) {
            {
              ComponentInstance _instance_3 = portDef_1.getInstancePortReference().getInstance();
              boolean _not_3 = (!(_instance_3 instanceof ElementaryEnvironmentComponentInstance));
              if (_not_3) {
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("return ");
                String _name_15 = portDef_1.getInstancePortReference().getInstance().getName();
                _builder.append(_name_15, "\t\t\t");
                _builder.append(".get");
                String _firstUpper_25 = StringExtensions.toFirstUpper(portDef_1.getInstancePortReference().getPort().getName());
                _builder.append(_firstUpper_25, "\t\t\t");
                _builder.append("().getRegisteredListeners();");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public ");
        String _firstUpper_26 = StringExtensions.toFirstUpper(systemPort.getName());
        _builder.append(_firstUpper_26, "\t");
        _builder.append(" get");
        String _firstUpper_27 = StringExtensions.toFirstUpper(systemPort.getName());
        _builder.append(_firstUpper_27, "\t");
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("return ");
        String _firstLower_1 = StringExtensions.toFirstLower(systemPort.getName());
        _builder.append(_firstLower_1, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Starts the running of the asynchronous component. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void start() {");
    _builder.newLine();
    {
      EList<AsynchronousComponentInstance> _components_2 = component.getComponents();
      for(final AsynchronousComponentInstance instance_5 : _components_2) {
        _builder.append("\t\t");
        String _name_16 = instance_5.getName();
        _builder.append(_name_16, "\t\t");
        _builder.append(".start();");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      if ((component instanceof AbstractEnvironmentCompositeComponent)) {
        {
          EList<EnvironmentComponentInstance> _environmentComponents_4 = ((AbstractEnvironmentCompositeComponent)component).getEnvironmentComponents();
          for(final EnvironmentComponentInstance envInstance : _environmentComponents_4) {
            {
              if ((envInstance instanceof EnvironmentAsynchronousCompositeComponentInstance)) {
                _builder.append("\t\t");
                String _name_17 = ((EnvironmentAsynchronousCompositeComponentInstance)envInstance).getName();
                _builder.append(_name_17, "\t\t");
                _builder.append(".start();");
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
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean isWaiting() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ");
    {
      EList<AsynchronousComponentInstance> _components_3 = component.getComponents();
      boolean _hasElements_4 = false;
      for(final AsynchronousComponentInstance instance_6 : _components_3) {
        if (!_hasElements_4) {
          _hasElements_4 = true;
        } else {
          _builder.appendImmediate(" && ", "\t\t");
        }
        String _name_18 = instance_6.getName();
        _builder.append(_name_18, "\t\t");
        _builder.append(".isWaiting()");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      if ((component instanceof AbstractEnvironmentCompositeComponent)) {
        {
          EList<EnvironmentComponentInstance> _environmentComponents_5 = ((AbstractEnvironmentCompositeComponent)component).getEnvironmentComponents();
          for(final EnvironmentComponentInstance envInstance_1 : _environmentComponents_5) {
            {
              if ((envInstance_1 instanceof EnvironmentAsynchronousCompositeComponentInstance)) {
                _builder.append("\t\t\t");
                _builder.append("\" && \"");
                String _name_19 = ((EnvironmentAsynchronousCompositeComponentInstance)envInstance_1).getName();
                _builder.append(_name_19, "\t\t\t");
                _builder.append(".isWaiting()");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("\t\t\t\t\t");
      }
    }
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      boolean _needTimer_1 = this.timingDeterminer.needTimer(component);
      if (_needTimer_1) {
        _builder.append("\t");
        _builder.append("/** Setter for the timer e.g., a virtual timer. */");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public void setTimer(");
        _builder.append(Namings.YAKINDU_TIMER_INTERFACE, "\t");
        _builder.append(" timer) {");
        _builder.newLineIfNotEmpty();
        {
          EList<AsynchronousComponentInstance> _components_4 = component.getComponents();
          for(final AsynchronousComponentInstance instance_7 : _components_4) {
            {
              boolean _needTimer_2 = this.timingDeterminer.needTimer(instance_7.getType());
              if (_needTimer_2) {
                _builder.append("\t");
                _builder.append("\t");
                String _name_20 = instance_7.getName();
                _builder.append(_name_20, "\t\t");
                _builder.append(".setTimer(timer);");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        {
          if ((component instanceof AbstractEnvironmentCompositeComponent)) {
            {
              EList<EnvironmentComponentInstance> _environmentComponents_6 = ((AbstractEnvironmentCompositeComponent)component).getEnvironmentComponents();
              for(final EnvironmentComponentInstance envInstance_2 : _environmentComponents_6) {
                {
                  if ((envInstance_2 instanceof EnvironmentAsynchronousCompositeComponentInstance)) {
                    {
                      boolean _needTimer_3 = this.timingDeterminer.needTimer(((EnvironmentAsynchronousCompositeComponentInstance)envInstance_2).getType());
                      if (_needTimer_3) {
                        _builder.append("\t");
                        _builder.append("\t");
                        String _name_21 = ((EnvironmentAsynchronousCompositeComponentInstance)envInstance_2).getName();
                        _builder.append(_name_21, "\t\t");
                        _builder.append(".setTimer(timer);");
                        _builder.newLineIfNotEmpty();
                      }
                    }
                  }
                }
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
    _builder.append("\t");
    _builder.append("/**  Getter for component instances, e.g., enabling to check their states. */");
    _builder.newLine();
    {
      EList<AsynchronousComponentInstance> _components_5 = component.getComponents();
      boolean _hasElements_5 = false;
      for(final AsynchronousComponentInstance instance_8 : _components_5) {
        if (!_hasElements_5) {
          _hasElements_5 = true;
        } else {
          _builder.appendImmediate("\n", "\t");
        }
        _builder.append("\t");
        _builder.append("public ");
        String _generateComponentClassName_5 = this.nameGenerator.generateComponentClassName(instance_8.getType());
        _builder.append(_generateComponentClassName_5, "\t");
        _builder.append(" get");
        String _firstUpper_28 = StringExtensions.toFirstUpper(instance_8.getName());
        _builder.append(_firstUpper_28, "\t");
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("return ");
        String _name_22 = instance_8.getName();
        _builder.append(_name_22, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    {
      if ((component instanceof AbstractEnvironmentCompositeComponent)) {
        {
          EList<EnvironmentComponentInstance> _environmentComponents_7 = ((AbstractEnvironmentCompositeComponent)component).getEnvironmentComponents();
          for(final EnvironmentComponentInstance envInstance_3 : _environmentComponents_7) {
            {
              if ((envInstance_3 instanceof EnvironmentAsynchronousCompositeComponentInstance)) {
                _builder.append("\t");
                _builder.append("public ");
                String _generateComponentClassName_6 = this.nameGenerator.generateComponentClassName(((EnvironmentAsynchronousCompositeComponentInstance)envInstance_3).getType());
                _builder.append(_generateComponentClassName_6, "\t");
                _builder.append(" get");
                String _firstUpper_29 = StringExtensions.toFirstUpper(((EnvironmentAsynchronousCompositeComponentInstance)envInstance_3).getName());
                _builder.append(_firstUpper_29, "\t");
                _builder.append("() {");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("return ");
                String _name_23 = ((EnvironmentAsynchronousCompositeComponentInstance)envInstance_3).getName();
                _builder.append(_name_23, "\t\t");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("}");
                _builder.newLine();
              }
            }
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
