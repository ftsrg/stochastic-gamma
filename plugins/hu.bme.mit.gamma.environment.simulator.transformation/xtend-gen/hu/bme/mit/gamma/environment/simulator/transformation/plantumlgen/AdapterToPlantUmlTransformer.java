/**
 * Copyright (c) 2018-2023 Contributors to the Gamma project
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * SPDX-License-Identifier: EPL-1.0
 */
package hu.bme.mit.gamma.environment.simulator.transformation.plantumlgen;

import hu.bme.mit.gamma.environment.simulator.transformation.util.Util;
import hu.bme.mit.gamma.expression.util.ExpressionSerializer;
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter;
import hu.bme.mit.gamma.statechart.composite.ControlSpecification;
import hu.bme.mit.gamma.statechart.composite.EventPassing;
import hu.bme.mit.gamma.statechart.composite.MessageQueue;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.AnyTrigger;
import hu.bme.mit.gamma.statechart.interface_.Clock;
import hu.bme.mit.gamma.statechart.interface_.EventReference;
import hu.bme.mit.gamma.statechart.interface_.EventTrigger;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.interface_.SimpleTrigger;
import hu.bme.mit.gamma.statechart.statechart.AnyPortEventReference;
import hu.bme.mit.gamma.statechart.statechart.ClockTickReference;
import hu.bme.mit.gamma.statechart.statechart.PortEventReference;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class AdapterToPlantUmlTransformer {
  protected final AsynchronousAdapter adapter;

  @Extension
  protected ExpressionSerializer expressionSerializer = ExpressionSerializer.INSTANCE;

  public AdapterToPlantUmlTransformer(final AsynchronousAdapter adapter) {
    this.adapter = adapter;
  }

  protected String _simpleConn(final AnyPortEventReference source, final EventReference target, final String queueName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if ((target == null)) {
        _builder.append("c_");
        String _name = source.getPort().getName();
        _builder.append(_name);
        _builder.append(" ...> ");
        _builder.append(queueName);
        _builder.append(" : \"any\"");
        _builder.newLineIfNotEmpty();
        _builder.append(queueName);
        _builder.append(" ...> comp_");
        String _name_1 = source.getPort().getName();
        _builder.append(_name_1);
        _builder.append(" : \"any\"");
        _builder.newLineIfNotEmpty();
      } else {
        if ((target instanceof AnyPortEventReference)) {
          _builder.append("c_");
          String _name_2 = ((AnyPortEventReference)target).getPort().getName();
          _builder.append(_name_2);
          _builder.append(" ...> ");
          _builder.append(queueName);
          _builder.append(" : \"any\"");
          _builder.newLineIfNotEmpty();
          _builder.append(queueName);
          _builder.append(" ...> comp_");
          String _name_3 = source.getPort().getName();
          _builder.append(_name_3);
          _builder.append(" : \"any\"");
          _builder.newLineIfNotEmpty();
        } else {
          if ((target instanceof PortEventReference)) {
            _builder.append("c_");
            String _name_4 = ((PortEventReference)target).getPort().getName();
            _builder.append(_name_4);
            _builder.append(" ...> ");
            _builder.append(queueName);
            _builder.append(" : \"");
            String _name_5 = ((PortEventReference)target).getEvent().getName();
            _builder.append(_name_5);
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append(queueName);
            _builder.append(" ...> comp_");
            String _name_6 = source.getPort().getName();
            _builder.append(_name_6);
            _builder.append(" : \"any\"");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder.toString();
  }

  protected String _simpleConn(final PortEventReference source, final EventReference target, final String queueName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if ((target == null)) {
        _builder.append("c_");
        String _name = source.getPort().getName();
        _builder.append(_name);
        _builder.append(" ..> ");
        _builder.append(queueName);
        _builder.append(" : \"any\"");
        _builder.newLineIfNotEmpty();
        _builder.append(queueName);
        _builder.append(" ..> comp_");
        String _name_1 = source.getPort().getName();
        _builder.append(_name_1);
        _builder.append(" : \"");
        String _name_2 = source.getEvent().getName();
        _builder.append(_name_2);
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
      } else {
        if ((target instanceof AnyPortEventReference)) {
          _builder.append("c_");
          String _name_3 = ((AnyPortEventReference)target).getPort().getName();
          _builder.append(_name_3);
          _builder.append(" ..> ");
          _builder.append(queueName);
          _builder.append(" : \"any\"");
          _builder.newLineIfNotEmpty();
          _builder.append(queueName);
          _builder.append(" ..> comp_");
          String _name_4 = source.getPort().getName();
          _builder.append(_name_4);
          _builder.append(" : \"");
          String _name_5 = source.getEvent().getName();
          _builder.append(_name_5);
          _builder.append("\"");
          _builder.newLineIfNotEmpty();
        } else {
          if ((target instanceof PortEventReference)) {
            _builder.append("c_");
            String _name_6 = ((PortEventReference)target).getPort().getName();
            _builder.append(_name_6);
            _builder.append(" ..> ");
            _builder.append(queueName);
            _builder.append(" : \"");
            String _name_7 = ((PortEventReference)target).getEvent().getName();
            _builder.append(_name_7);
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append(queueName);
            _builder.append(" ..> comp_");
            String _name_8 = source.getPort().getName();
            _builder.append(_name_8);
            _builder.append(" : \"");
            String _name_9 = source.getEvent().getName();
            _builder.append(_name_9);
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder.toString();
  }

  protected String _getRef(final PortEventReference ref) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = ref.getPort().getName();
    _builder.append(_name);
    _builder.append(".");
    String _name_1 = ref.getEvent().getName();
    _builder.append(_name_1);
    return _builder.toString();
  }

  protected String _getRef(final AnyPortEventReference ref) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = ref.getPort().getName();
    _builder.append(_name);
    _builder.append(".any");
    return _builder.toString();
  }

  protected String _getRef(final ClockTickReference ref) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = ref.getClock().getName();
    _builder.append(_name);
    return _builder.toString();
  }

  protected String _trigger(final AnyTrigger trigger) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("any");
    return _builder.toString();
  }

  protected String _trigger(final EventTrigger trigger) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = StatechartModelDerivedFeatures.getEventSource(trigger).getName();
    _builder.append(_name);
    _builder.append(".");
    String _ref = this.getRef(trigger.getEventReference());
    _builder.append(_ref);
    return _builder.toString();
  }

  public String execute() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@startuml");
    _builder.newLine();
    _builder.append("skinparam shadowing false");
    _builder.newLine();
    _builder.newLine();
    _builder.append("skinparam shadowing false");
    _builder.newLine();
    _builder.append("!theme plain");
    _builder.newLine();
    _builder.append("left to right direction");
    _builder.newLine();
    _builder.append("skinparam nodesep 30");
    _builder.newLine();
    _builder.append("skinparam ranksep 30");
    _builder.newLine();
    _builder.newLine();
    _builder.append("skinparam padding 5");
    _builder.newLine();
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
    String _name = this.adapter.getName();
    _builder.append(_name);
    _builder.append("\"<<Asynchronous Adapter>> {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    {
      List<Port> _allPortsWithInput = StatechartModelDerivedFeatures.getAllPortsWithInput(this.adapter.getWrappedComponent().getType());
      for(final Port port : _allPortsWithInput) {
        _builder.append("portin \"");
        String _name_1 = port.getName();
        _builder.append(_name_1);
        _builder.append("\" as c_");
        String _name_2 = port.getName();
        _builder.append(_name_2);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      List<Port> _allPortsWithInput_1 = StatechartModelDerivedFeatures.getAllPortsWithInput(this.adapter);
      for(final Port port_1 : _allPortsWithInput_1) {
        _builder.append("portin \"");
        String _name_3 = port_1.getName();
        _builder.append(_name_3);
        _builder.append("\" as c_");
        String _name_4 = port_1.getName();
        _builder.append(_name_4);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      List<Port> _allPortsWithOutput = StatechartModelDerivedFeatures.getAllPortsWithOutput(this.adapter.getWrappedComponent().getType());
      for(final Port port_2 : _allPortsWithOutput) {
        _builder.append("portout \"");
        String _name_5 = port_2.getName();
        _builder.append(_name_5);
        _builder.append("\" as c_");
        String _name_6 = port_2.getName();
        _builder.append(_name_6);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      List<Port> _allPortsWithOutput_1 = StatechartModelDerivedFeatures.getAllPortsWithOutput(this.adapter);
      for(final Port port_3 : _allPortsWithOutput_1) {
        _builder.append("portout \"");
        String _name_7 = port_3.getName();
        _builder.append(_name_7);
        _builder.append("\" as c_");
        String _name_8 = port_3.getName();
        _builder.append(_name_8);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("component \"");
    String _name_9 = this.adapter.getWrappedComponent().getName();
    _builder.append(_name_9);
    _builder.append(" : ");
    String _name_10 = this.adapter.getWrappedComponent().getType().getName();
    _builder.append(_name_10);
    _builder.append("\" as comp {");
    _builder.newLineIfNotEmpty();
    {
      List<Port> _allPortsWithInput_2 = StatechartModelDerivedFeatures.getAllPortsWithInput(this.adapter.getWrappedComponent().getType());
      for(final Port port_4 : _allPortsWithInput_2) {
        _builder.append("\t");
        _builder.append("portin \"");
        String _name_11 = port_4.getName();
        _builder.append(_name_11, "\t");
        _builder.append("\" as comp_");
        String _name_12 = port_4.getName();
        _builder.append(_name_12, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      List<Port> _allPortsWithOutput_2 = StatechartModelDerivedFeatures.getAllPortsWithOutput(this.adapter.getWrappedComponent().getType());
      for(final Port port_5 : _allPortsWithOutput_2) {
        _builder.append("\t");
        _builder.append("portout \"");
        String _name_13 = port_5.getName();
        _builder.append(_name_13, "\t");
        _builder.append("\" as comp_");
        String _name_14 = port_5.getName();
        _builder.append(_name_14, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    {
      List<Port> _allPortsWithOutput_3 = StatechartModelDerivedFeatures.getAllPortsWithOutput(this.adapter.getWrappedComponent().getType());
      for(final Port port_6 : _allPortsWithOutput_3) {
        _builder.append("comp_");
        String _name_15 = port_6.getName();
        _builder.append(_name_15);
        _builder.append(" ...> c_");
        String _name_16 = port_6.getName();
        _builder.append(_name_16);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      EList<MessageQueue> _messageQueues = this.adapter.getMessageQueues();
      for(final MessageQueue queue : _messageQueues) {
        _builder.append("queue ");
        String _name_17 = queue.getName();
        _builder.append(_name_17);
        _builder.append("  [");
        _builder.newLineIfNotEmpty();
        String _name_18 = queue.getName();
        _builder.append(_name_18);
        _builder.newLineIfNotEmpty();
        _builder.append("capacity=");
        String _serialize = this.expressionSerializer.serialize(queue.getCapacity());
        _builder.append(_serialize);
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("priority=");
        BigInteger _priority = queue.getPriority();
        _builder.append(_priority);
        _builder.newLineIfNotEmpty();
        _builder.append("]");
        _builder.newLine();
        {
          EList<EventPassing> _eventPassings = queue.getEventPassings();
          for(final EventPassing passing : _eventPassings) {
            String _simpleConn = this.simpleConn(passing.getSource(), passing.getTarget(), queue.getName());
            _builder.append(_simpleConn);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.newLine();
    _builder.append("card Triggers[");
    _builder.newLine();
    _builder.append("Triggers");
    _builder.newLine();
    _builder.append("----");
    _builder.newLine();
    {
      EList<ControlSpecification> _controlSpecifications = this.adapter.getControlSpecifications();
      for(final ControlSpecification control : _controlSpecifications) {
        _builder.append("when ");
        String _trigger = this.trigger(control.getTrigger());
        _builder.append(_trigger);
        _builder.append(" / ");
        String _replaceAll = control.getControlFunction().toString().toLowerCase().replaceAll("_", " ");
        _builder.append(_replaceAll);
        _builder.newLineIfNotEmpty();
        _builder.append("....");
        _builder.newLine();
      }
    }
    _builder.append("]");
    _builder.newLine();
    {
      boolean _isEmpty = this.adapter.getClocks().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("card Clocks[");
        _builder.newLine();
        _builder.append("Clocks");
        _builder.newLine();
        _builder.append("----");
        _builder.newLine();
        {
          EList<Clock> _clocks = this.adapter.getClocks();
          for(final Clock clock : _clocks) {
            _builder.append("clock ");
            String _name_19 = clock.getName();
            _builder.append(_name_19);
            _builder.append(" : ");
            String _serialize_1 = this.expressionSerializer.serialize(clock.getTimeSpecification().getValue());
            _builder.append(_serialize_1);
            _builder.append(" ");
            String _name_20 = clock.getTimeSpecification().getUnit().getName();
            _builder.append(_name_20);
            _builder.newLineIfNotEmpty();
            _builder.append("....");
            _builder.newLine();
          }
        }
        _builder.append("]");
        _builder.newLine();
      }
    }
    _builder.append("}");
    _builder.newLine();
    {
      List<Port> _allPortsWithOutput_4 = StatechartModelDerivedFeatures.getAllPortsWithOutput(this.adapter);
      for(final Port port_7 : _allPortsWithOutput_4) {
        String _noteAdpt = Util.noteAdpt(port_7);
        _builder.append(_noteAdpt);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("@enduml");
    _builder.newLine();
    return _builder.toString();
  }

  public String simpleConn(final EventReference source, final EventReference target, final String queueName) {
    if (source instanceof AnyPortEventReference) {
      return _simpleConn((AnyPortEventReference)source, target, queueName);
    } else if (source instanceof PortEventReference) {
      return _simpleConn((PortEventReference)source, target, queueName);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(source, target, queueName).toString());
    }
  }

  public String getRef(final EventReference ref) {
    if (ref instanceof AnyPortEventReference) {
      return _getRef((AnyPortEventReference)ref);
    } else if (ref instanceof ClockTickReference) {
      return _getRef((ClockTickReference)ref);
    } else if (ref instanceof PortEventReference) {
      return _getRef((PortEventReference)ref);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(ref).toString());
    }
  }

  public String trigger(final SimpleTrigger trigger) {
    if (trigger instanceof AnyTrigger) {
      return _trigger((AnyTrigger)trigger);
    } else if (trigger instanceof EventTrigger) {
      return _trigger((EventTrigger)trigger);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(trigger).toString());
    }
  }
}
