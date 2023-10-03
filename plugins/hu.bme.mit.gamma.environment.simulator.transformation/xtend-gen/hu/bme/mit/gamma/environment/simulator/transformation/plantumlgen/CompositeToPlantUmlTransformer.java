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
import hu.bme.mit.gamma.environment.simulator.transformation.util.Util;
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
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class CompositeToPlantUmlTransformer {
  public enum LayoutType {
    UMLComponentDiagramStyle,

    UMLCompositeStructureDiagramStyle,

    SysMLInternalBlockDiagramStyle;
  }

  public enum LineStyle {
    Orthogonal,

    Polyline,

    Curved;
  }

  protected final CompositeComponent composite;

  protected final int padding = 2;

  protected final int verticalSpacing = 60;

  protected final int horizontalSpacing = 60;

  protected final boolean leftToRightDirection = false;

  protected final boolean topToBottomDirection = false;

  protected final CompositeToPlantUmlTransformer.LineStyle lineStyle = CompositeToPlantUmlTransformer.LineStyle.Curved;

  protected final CompositeToPlantUmlTransformer.LayoutType layoutType = CompositeToPlantUmlTransformer.LayoutType.SysMLInternalBlockDiagramStyle;

  public String generateSkinparams(final int padding, final int verticalSpacing, final int horizontalSpacing, final boolean leftToRightDirection, final boolean topToBottomDirection, final CompositeToPlantUmlTransformer.LineStyle lineStyle) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("skinparam shadowing false");
    _builder.newLine();
    _builder.append("!theme plain");
    _builder.newLine();
    {
      boolean _equals = Objects.equal(lineStyle, CompositeToPlantUmlTransformer.LineStyle.Orthogonal);
      if (_equals) {
        _builder.append("skinparam linetype ortho");
        _builder.newLine();
      }
    }
    {
      boolean _equals_1 = Objects.equal(lineStyle, CompositeToPlantUmlTransformer.LineStyle.Polyline);
      if (_equals_1) {
        _builder.append("skinparam linetype polyline");
        _builder.newLine();
      }
    }
    {
      if (leftToRightDirection) {
        _builder.append("left to right direction");
        _builder.newLine();
      }
    }
    {
      if (topToBottomDirection) {
        _builder.append("top to bottom direction");
        _builder.newLine();
      }
    }
    _builder.append("skinparam nodesep ");
    _builder.append(verticalSpacing);
    _builder.newLineIfNotEmpty();
    _builder.append("skinparam ranksep ");
    _builder.append(horizontalSpacing);
    _builder.newLineIfNotEmpty();
    _builder.append("skinparam padding ");
    _builder.append(padding);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }

  @Extension
  protected ExpressionSerializer expressionSerializer = ExpressionSerializer.INSTANCE;

  public CompositeToPlantUmlTransformer(final CompositeComponent composite) {
    this.composite = composite;
  }

  private String getKindString(final CompositeComponent composite) {
    if ((composite instanceof SynchronousCompositeComponent)) {
      return "synchronous";
    } else {
      if ((composite instanceof CascadeCompositeComponent)) {
        return "cascade";
      } else {
        if ((composite instanceof AsynchronousCompositeComponent)) {
          return "asynchronous";
        }
      }
    }
    return null;
  }

  public String execute() {
    final CompositeToPlantUmlTransformer.LayoutType layoutType = this.layoutType;
    if (layoutType != null) {
      switch (layoutType) {
        case UMLComponentDiagramStyle:
          return this.executeUMLComponentDiagramStyle();
        case UMLCompositeStructureDiagramStyle:
          return this.executeUMLCompositeStructureDiagramStyle();
        case SysMLInternalBlockDiagramStyle:
          return this.executeSysMLInternalBlockDiagramStyle();
        default:
          return this.executeSysMLInternalBlockDiagramStyle();
      }
    } else {
      return this.executeSysMLInternalBlockDiagramStyle();
    }
  }

  public String executeUMLComponentDiagramStyle() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@startuml");
    _builder.newLine();
    _builder.append("skinparam shadowing false");
    _builder.newLine();
    _builder.newLine();
    _builder.append("skinparam interface<<Invisible>> {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("borderColor Transparent");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("backgroundColor Transparent");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("stereotypeFontColor Transparent");
    _builder.newLine();
    _builder.append("}");
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
    {
      List<? extends ComponentInstance> _derivedComponents = StatechartModelDerivedFeatures.getDerivedComponents(this.composite);
      for(final ComponentInstance component : _derivedComponents) {
        _builder.append("\t");
        _builder.append("agent ");
        String _name_1 = component.getName();
        _builder.append(_name_1, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      EList<Channel> _channels = this.composite.getChannels();
      for(final Channel channel : _channels) {
        _builder.append("\t");
        _builder.append("interface \"");
        String _name_2 = channel.getProvidedPort().getPort().getInterfaceRealization().getInterface().getName();
        _builder.append(_name_2, "\t");
        _builder.append("\" as ");
        String _name_3 = channel.getProvidedPort().getInstance().getName();
        _builder.append(_name_3, "\t");
        _builder.append("___");
        String _name_4 = channel.getProvidedPort().getPort().getName();
        _builder.append(_name_4, "\t");
        _builder.append("___");
        String _name_5 = channel.getProvidedPort().getPort().getInterfaceRealization().getInterface().getName();
        _builder.append(_name_5, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _name_6 = channel.getProvidedPort().getInstance().getName();
        _builder.append(_name_6, "\t");
        _builder.append(" #- ");
        String _name_7 = channel.getProvidedPort().getInstance().getName();
        _builder.append(_name_7, "\t");
        _builder.append("___");
        String _name_8 = channel.getProvidedPort().getPort().getName();
        _builder.append(_name_8, "\t");
        _builder.append("___");
        String _name_9 = channel.getProvidedPort().getPort().getInterfaceRealization().getInterface().getName();
        _builder.append(_name_9, "\t");
        _builder.newLineIfNotEmpty();
        {
          List<InstancePortReference> _requiredPorts = StatechartModelDerivedFeatures.getRequiredPorts(channel);
          for(final InstancePortReference requiredPort : _requiredPorts) {
            _builder.append("\t");
            String _name_10 = channel.getProvidedPort().getInstance().getName();
            _builder.append(_name_10, "\t");
            _builder.append("___");
            String _name_11 = channel.getProvidedPort().getPort().getName();
            _builder.append(_name_11, "\t");
            _builder.append("___");
            String _name_12 = channel.getProvidedPort().getPort().getInterfaceRealization().getInterface().getName();
            _builder.append(_name_12, "\t");
            _builder.append(" )--# ");
            String _name_13 = requiredPort.getInstance().getName();
            _builder.append(_name_13, "\t");
            _builder.append(" : ");
            String _name_14 = requiredPort.getPort().getName();
            _builder.append(_name_14, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    {
      EList<PortBinding> _portBindings = this.composite.getPortBindings();
      for(final PortBinding binding : _portBindings) {
        {
          RealizationMode _realizationMode = binding.getInstancePortReference().getPort().getInterfaceRealization().getRealizationMode();
          boolean _equals = Objects.equal(_realizationMode, RealizationMode.REQUIRED);
          if (_equals) {
            _builder.append("interface ");
            String _name_15 = binding.getInstancePortReference().getPort().getInterfaceRealization().getInterface().getName();
            _builder.append(_name_15);
            _builder.append(" as ");
            String _name_16 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_16);
            _builder.append("___");
            String _name_17 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_17);
            _builder.append("___");
            String _name_18 = binding.getInstancePortReference().getPort().getInterfaceRealization().getInterface().getName();
            _builder.append(_name_18);
            _builder.append("<<Invisible>>");
            _builder.newLineIfNotEmpty();
            String _name_19 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_19);
            _builder.append(" #-( ");
            String _name_20 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_20);
            _builder.append("___");
            String _name_21 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_21);
            _builder.append("___");
            String _name_22 = binding.getInstancePortReference().getPort().getInterfaceRealization().getInterface().getName();
            _builder.append(_name_22);
            _builder.append(" : ");
            String _name_23 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_23);
            _builder.newLineIfNotEmpty();
          }
        }
        {
          RealizationMode _realizationMode_1 = binding.getInstancePortReference().getPort().getInterfaceRealization().getRealizationMode();
          boolean _equals_1 = Objects.equal(_realizationMode_1, RealizationMode.PROVIDED);
          if (_equals_1) {
            _builder.append("interface ");
            String _name_24 = binding.getInstancePortReference().getPort().getInterfaceRealization().getInterface().getName();
            _builder.append(_name_24);
            _builder.append(" as ");
            String _name_25 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_25);
            _builder.append("___");
            String _name_26 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_26);
            _builder.append("___");
            String _name_27 = binding.getInstancePortReference().getPort().getInterfaceRealization().getInterface().getName();
            _builder.append(_name_27);
            _builder.newLineIfNotEmpty();
            String _name_28 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_28);
            _builder.append(" #- ");
            String _name_29 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_29);
            _builder.append("___");
            String _name_30 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_30);
            _builder.append("___");
            String _name_31 = binding.getInstancePortReference().getPort().getInterfaceRealization().getInterface().getName();
            _builder.append(_name_31);
            _builder.append(" : ");
            String _name_32 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_32);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("@enduml");
    _builder.newLine();
    return _builder.toString();
  }

  public String executeUMLCompositeStructureDiagramStyle() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@startuml");
    _builder.newLine();
    _builder.append("skinparam defaultTextAlignment center");
    _builder.newLine();
    String _generateSkinparams = this.generateSkinparams(4, 20, 60, true, false, CompositeToPlantUmlTransformer.LineStyle.Polyline);
    _builder.append(_generateSkinparams);
    _builder.newLineIfNotEmpty();
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
        int _length = ((Object[])Conversions.unwrapArray(StatechartModelDerivedFeatures.getAllPorts(StatechartModelDerivedFeatures.getDerivedType(component)), Object.class)).length;
        double _multiply = (_length * 0.4);
        String _string = Double.valueOf((0.3 + _multiply)).toString();
        _builder.append(_string, "\t");
        _builder.append(" width=");
        int _length_1 = ((Object[])Conversions.unwrapArray(StatechartModelDerivedFeatures.getDerivedType(component).getPorts(), Object.class)).length;
        double _multiply_1 = (_length_1 * 0.1);
        String _string_1 = Double.valueOf((0.1 + _multiply_1)).toString();
        _builder.append(_string_1, "\t");
        _builder.append(" shape=plaintext fontname=\"SansSerif\" label=\"");
        String _name_2 = component.getName();
        _builder.append(_name_2, "\t");
        _builder.append(" : ");
        String _name_3 = StatechartModelDerivedFeatures.getDerivedType(component).getName();
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
            String _name_4 = port.getName();
            _builder.append(_name_4, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          RealizationMode _realizationMode_1 = port.getInterfaceRealization().getRealizationMode();
          boolean _equals_1 = Objects.equal(_realizationMode_1, RealizationMode.PROVIDED);
          if (_equals_1) {
            _builder.append("\t");
            _builder.append("portout ");
            String _name_5 = port.getName();
            _builder.append(_name_5, "\t");
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
            String _name_6 = binding.getCompositeSystemPort().getName();
            _builder.append(_name_6, "\t");
            _builder.append(" ..# \"");
            String _name_7 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_7, "\t");
            _builder.append("\" ");
            String _name_8 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_8, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          RealizationMode _realizationMode_3 = binding.getInstancePortReference().getPort().getInterfaceRealization().getRealizationMode();
          boolean _equals_3 = Objects.equal(_realizationMode_3, RealizationMode.PROVIDED);
          if (_equals_3) {
            _builder.append("\t");
            String _name_9 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_9, "\t");
            _builder.append(" \"");
            String _name_10 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_10, "\t");
            _builder.append("\" #.. ");
            String _name_11 = binding.getCompositeSystemPort().getName();
            _builder.append(_name_11, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("\'");
        String _name_12 = this.composite.getName();
        _builder.append(_name_12, "\t");
        _builder.append(" \"");
        String _name_13 = binding.getCompositeSystemPort().getName();
        _builder.append(_name_13, "\t");
        _builder.append("\" #.# \"");
        String _name_14 = binding.getInstancePortReference().getPort().getName();
        _builder.append(_name_14, "\t");
        _builder.append("\" ");
        String _name_15 = binding.getInstancePortReference().getInstance().getName();
        _builder.append(_name_15, "\t");
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
            String _name_16 = channel.getProvidedPort().getInstance().getName();
            _builder.append(_name_16, "\t");
            _builder.append(" \"");
            String _name_17 = channel.getProvidedPort().getPort().getName();
            _builder.append(_name_17, "\t");
            _builder.append("\" #--0)--# \"");
            String _name_18 = requiredPort.getPort().getName();
            _builder.append(_name_18, "\t");
            _builder.append("\" ");
            String _name_19 = requiredPort.getInstance().getName();
            _builder.append(_name_19, "\t");
            _builder.append(" : \"<size:10>//");
            String _name_20 = StatechartModelDerivedFeatures.getInterface(requiredPort.getPort()).getName();
            _builder.append(_name_20, "\t");
            _builder.append("//\" ");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("@enduml");
    _builder.newLine();
    return _builder.toString();
  }

  public String executeSysMLInternalBlockDiagramStyle() {
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
    _builder.append("!theme plain");
    _builder.newLine();
    _builder.append("skinparam defaultTextAlignment center");
    _builder.newLine();
    _builder.append("skinparam ComponentStereotypeFontSize 10");
    _builder.newLine();
    _builder.append("skinparam componentStyle rectangle");
    _builder.newLine();
    String _generateSkinparams = this.generateSkinparams(2, 40, 70, false, false, CompositeToPlantUmlTransformer.LineStyle.Curved);
    _builder.append(_generateSkinparams);
    _builder.newLineIfNotEmpty();
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
        String _name_2 = StatechartModelDerivedFeatures.getDerivedType(component).getName();
        _builder.append(_name_2, "\t");
        _builder.append("\" as ");
        String _name_3 = component.getName();
        _builder.append(_name_3, "\t");
        _builder.append("  {");
        _builder.newLineIfNotEmpty();
        {
          List<Port> _allPorts = StatechartModelDerivedFeatures.getAllPorts(StatechartModelDerivedFeatures.getDerivedType(component));
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
                    _builder.append("\t");
                    _builder.append("\t");
                    String _note = Util.note(port, component);
                    _builder.append(_note, "\t\t");
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
    _builder.append("\t");
    _builder.newLine();
    {
      EList<Port> _ports = this.composite.getPorts();
      for(final Port port_1 : _ports) {
        {
          RealizationMode _realizationMode_2 = port_1.getInterfaceRealization().getRealizationMode();
          boolean _equals_2 = Objects.equal(_realizationMode_2, RealizationMode.REQUIRED);
          if (_equals_2) {
            _builder.append("\t");
            _builder.append("portin \"");
            String _name_12 = port_1.getName();
            _builder.append(_name_12, "\t");
            _builder.append(":\\n ~");
            String _name_13 = StatechartModelDerivedFeatures.getInterface(port_1).getName();
            _builder.append(_name_13, "\t");
            _builder.append("\" as ");
            String _name_14 = port_1.getName();
            _builder.append(_name_14, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          RealizationMode _realizationMode_3 = port_1.getInterfaceRealization().getRealizationMode();
          boolean _equals_3 = Objects.equal(_realizationMode_3, RealizationMode.PROVIDED);
          if (_equals_3) {
            _builder.append("\t");
            _builder.append("portout \"");
            String _name_15 = port_1.getName();
            _builder.append(_name_15, "\t");
            _builder.append(":\\n ");
            String _name_16 = StatechartModelDerivedFeatures.getInterface(port_1).getName();
            _builder.append(_name_16, "\t");
            _builder.append("\" as ");
            String _name_17 = port_1.getName();
            _builder.append(_name_17, "\t");
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
            String _name_18 = binding.getCompositeSystemPort().getName();
            _builder.append(_name_18, "\t");
            _builder.append(" . ");
            String _name_19 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_19, "\t");
            _builder.append("__");
            String _name_20 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_20, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          RealizationMode _realizationMode_5 = binding.getInstancePortReference().getPort().getInterfaceRealization().getRealizationMode();
          boolean _equals_5 = Objects.equal(_realizationMode_5, RealizationMode.PROVIDED);
          if (_equals_5) {
            _builder.append("\t");
            String _name_21 = binding.getInstancePortReference().getInstance().getName();
            _builder.append(_name_21, "\t");
            _builder.append("__");
            String _name_22 = binding.getInstancePortReference().getPort().getName();
            _builder.append(_name_22, "\t");
            _builder.append(" .. ");
            String _name_23 = binding.getCompositeSystemPort().getName();
            _builder.append(_name_23, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("\'");
        String _name_24 = this.composite.getName();
        _builder.append(_name_24, "\t");
        _builder.append(" \"");
        String _name_25 = binding.getCompositeSystemPort().getName();
        _builder.append(_name_25, "\t");
        _builder.append("\" #.# \"");
        String _name_26 = binding.getInstancePortReference().getPort().getName();
        _builder.append(_name_26, "\t");
        _builder.append("\" ");
        String _name_27 = binding.getInstancePortReference().getInstance().getName();
        _builder.append(_name_27, "\t");
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
                String _name_28 = channel.getProvidedPort().getInstance().getName();
                _builder.append(_name_28, "\t");
                _builder.append("__");
                String _name_29 = channel.getProvidedPort().getPort().getName();
                _builder.append(_name_29, "\t");
                _builder.append(" ---> ");
                String _name_30 = requiredPort.getInstance().getName();
                _builder.append(_name_30, "\t");
                _builder.append("__");
                String _name_31 = requiredPort.getPort().getName();
                _builder.append(_name_31, "\t");
                _builder.newLineIfNotEmpty();
              } else {
                if ((StatechartModelDerivedFeatures.getOutputEvents(channel.getProvidedPort().getPort()).isEmpty() && (!StatechartModelDerivedFeatures.getInputEvents(channel.getProvidedPort().getPort()).isEmpty()))) {
                  _builder.append("\t");
                  String _name_32 = channel.getProvidedPort().getInstance().getName();
                  _builder.append(_name_32, "\t");
                  _builder.append("__");
                  String _name_33 = channel.getProvidedPort().getPort().getName();
                  _builder.append(_name_33, "\t");
                  _builder.append(" <--- ");
                  String _name_34 = requiredPort.getInstance().getName();
                  _builder.append(_name_34, "\t");
                  _builder.append("__");
                  String _name_35 = requiredPort.getPort().getName();
                  _builder.append(_name_35, "\t");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("\t");
                  String _name_36 = channel.getProvidedPort().getInstance().getName();
                  _builder.append(_name_36, "\t");
                  _builder.append("__");
                  String _name_37 = channel.getProvidedPort().getPort().getName();
                  _builder.append(_name_37, "\t");
                  _builder.append(" <---> ");
                  String _name_38 = requiredPort.getInstance().getName();
                  _builder.append(_name_38, "\t");
                  _builder.append("__");
                  String _name_39 = requiredPort.getPort().getName();
                  _builder.append(_name_39, "\t");
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
    {
      EList<Port> _ports_1 = this.composite.getPorts();
      for(final Port port_2 : _ports_1) {
        String _note_2 = Util.note(port_2);
        _builder.append(_note_2);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("@enduml");
    _builder.newLine();
    return _builder.toString();
  }
}
