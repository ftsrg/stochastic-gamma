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
package hu.bme.mit.gamma.environment.simulator.transformation.plantumlgen;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import hu.bme.mit.gamma.action.model.Action;
import hu.bme.mit.gamma.environment.simulator.transformation.util.Util;
import hu.bme.mit.gamma.expression.model.Expression;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.expression.model.VariableDeclaration;
import hu.bme.mit.gamma.expression.util.TypeSerializer;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.AnyTrigger;
import hu.bme.mit.gamma.statechart.interface_.Clock;
import hu.bme.mit.gamma.statechart.interface_.EventReference;
import hu.bme.mit.gamma.statechart.interface_.EventTrigger;
import hu.bme.mit.gamma.statechart.interface_.TimeUnit;
import hu.bme.mit.gamma.statechart.interface_.Trigger;
import hu.bme.mit.gamma.statechart.statechart.AnyPortEventReference;
import hu.bme.mit.gamma.statechart.statechart.BinaryTrigger;
import hu.bme.mit.gamma.statechart.statechart.BinaryType;
import hu.bme.mit.gamma.statechart.statechart.ChoiceState;
import hu.bme.mit.gamma.statechart.statechart.ClockTickReference;
import hu.bme.mit.gamma.statechart.statechart.CompositeElement;
import hu.bme.mit.gamma.statechart.statechart.DeepHistoryState;
import hu.bme.mit.gamma.statechart.statechart.EntryState;
import hu.bme.mit.gamma.statechart.statechart.ForkState;
import hu.bme.mit.gamma.statechart.statechart.InitialState;
import hu.bme.mit.gamma.statechart.statechart.JoinState;
import hu.bme.mit.gamma.statechart.statechart.MergeState;
import hu.bme.mit.gamma.statechart.statechart.OnCycleTrigger;
import hu.bme.mit.gamma.statechart.statechart.OpaqueTrigger;
import hu.bme.mit.gamma.statechart.statechart.PortEventReference;
import hu.bme.mit.gamma.statechart.statechart.PseudoState;
import hu.bme.mit.gamma.statechart.statechart.Region;
import hu.bme.mit.gamma.statechart.statechart.ShallowHistoryState;
import hu.bme.mit.gamma.statechart.statechart.State;
import hu.bme.mit.gamma.statechart.statechart.StateNode;
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition;
import hu.bme.mit.gamma.statechart.statechart.TimeoutDeclaration;
import hu.bme.mit.gamma.statechart.statechart.TimeoutEventReference;
import hu.bme.mit.gamma.statechart.statechart.Transition;
import hu.bme.mit.gamma.statechart.statechart.UnaryTrigger;
import hu.bme.mit.gamma.statechart.statechart.UnaryType;
import hu.bme.mit.gamma.statechart.util.ActionSerializer;
import hu.bme.mit.gamma.statechart.util.ExpressionSerializer;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class StatechartToPlantUmlTransformer {
  protected final StatechartDefinition statechart;

  @Extension
  protected ActionSerializer actionSerializer = ActionSerializer.INSTANCE;

  @Extension
  protected ExpressionSerializer expressionSerializer = ExpressionSerializer.INSTANCE;

  @Extension
  protected TypeSerializer typeSerializer = TypeSerializer.INSTANCE;

  public StatechartToPlantUmlTransformer(final StatechartDefinition statechart) {
    this.statechart = statechart;
  }

  public String execute() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@startuml");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<style>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(".squareStyle {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("RoundCorner 0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</style>");
    _builder.newLine();
    _builder.append("\t");
    String _mainRegionSearch = this.mainRegionSearch(this.statechart);
    _builder.append(_mainRegionSearch, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("@enduml");
    _builder.newLine();
    return _builder.toString();
  }

  protected String _transformTrigger(final EventTrigger eventTrigger) {
    return this.transformEventReference(eventTrigger.getEventReference());
  }

  protected String _transformTrigger(final AnyTrigger anyTrigger) {
    return "any";
  }

  protected String _transformTrigger(final OnCycleTrigger onCycleTrigger) {
    return "cycle";
  }

  protected String _transformTrigger(final OpaqueTrigger opaqueTrigger) {
    return opaqueTrigger.getTrigger();
  }

  protected String _transformTrigger(final BinaryTrigger binaryTrigger) {
    final Trigger leftOperand = binaryTrigger.getLeftOperand();
    final Trigger rightOperand = binaryTrigger.getRightOperand();
    final BinaryType type = binaryTrigger.getType();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("(");
    String _transformTrigger = this.transformTrigger(leftOperand);
    _builder.append(_transformTrigger);
    _builder.append(" ");
    String _transformOperator = this.transformOperator(type);
    _builder.append(_transformOperator);
    _builder.append("\\n");
    String _transformTrigger_1 = this.transformTrigger(rightOperand);
    _builder.append(_transformTrigger_1);
    _builder.append(")");
    return _builder.toString();
  }

  protected String transformOperator(final BinaryType type) {
    if (type != null) {
      switch (type) {
        case AND:
          return "&&";
        case OR:
          return "||";
        case XOR:
          return "^";
        case IMPLY:
          return "->";
        case EQUAL:
          return "==";
        default:
          throw new IllegalArgumentException(("Not supported binary type: " + type));
      }
    } else {
      throw new IllegalArgumentException(("Not supported binary type: " + type));
    }
  }

  protected String _transformTrigger(final UnaryTrigger unaryTrigger) {
    final UnaryType type = unaryTrigger.getType();
    final Trigger operand = unaryTrigger.getOperand();
    StringConcatenation _builder = new StringConcatenation();
    String _transformOperator = this.transformOperator(type);
    _builder.append(_transformOperator);
    _builder.append("(");
    String _transformTrigger = this.transformTrigger(operand);
    _builder.append(_transformTrigger);
    _builder.append(")");
    return _builder.toString();
  }

  protected String transformOperator(final UnaryType type) {
    if (type != null) {
      switch (type) {
        case NOT:
          return "!";
        default:
          throw new IllegalArgumentException(("Not supported unary type: " + type));
      }
    } else {
      throw new IllegalArgumentException(("Not supported unary type: " + type));
    }
  }

  protected String _transformEventReference(final PortEventReference portEventReference) {
    String _name = portEventReference.getPort().getName();
    String _plus = (_name + ".");
    String _name_1 = portEventReference.getEvent().getName();
    return (_plus + _name_1);
  }

  protected String _transformEventReference(final TimeoutEventReference timeoutEventReference) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("timeout ");
    String _name = timeoutEventReference.getTimeout().getName();
    _builder.append(_name);
    return _builder.toString();
  }

  protected String _transformEventReference(final ClockTickReference clockTickReference) {
    final Clock clock = clockTickReference.getClock();
    String _name = clock.getName();
    String _plus = (_name + " : ");
    Expression _value = clock.getTimeSpecification().getValue();
    String _plus_1 = (_plus + _value);
    String _plus_2 = (_plus_1 + " ");
    TimeUnit _unit = clock.getTimeSpecification().getUnit();
    return (_plus_2 + _unit);
  }

  protected String _transformEventReference(final AnyPortEventReference anyPortEventReference) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = anyPortEventReference.getPort().getName();
    _builder.append(_name);
    _builder.append(".any");
    return _builder.toString();
  }

  protected String _transformPseudoState(final ForkState forkState) {
    String _name = forkState.getName();
    String _plus = ("state " + _name);
    return (_plus + " <<fork>>");
  }

  protected String _transformPseudoState(final JoinState joinState) {
    String _name = joinState.getName();
    String _plus = ("state " + _name);
    return (_plus + " <<join>>");
  }

  protected String _transformPseudoState(final ChoiceState choiceState) {
    String _name = choiceState.getName();
    String _plus = ("state " + _name);
    return (_plus + " <<choice>>");
  }

  protected String _transformPseudoState(final MergeState mergeState) {
    String _name = mergeState.getName();
    String _plus = ("state " + _name);
    return (_plus + " <<choice>>");
  }

  protected String _transformPseudoState(final EntryState entryState) {
    return null;
  }

  protected String transformAction(final Action action) {
    return this.actionSerializer.serialize(action).replaceAll(System.lineSeparator(), "\\\\n");
  }

  /**
   * regionSearch(StateNode, StatechartDefinition)
   * 
   * This method searches the inner states of composite states. The parameters are the following:
   * The state whose regions we want to find, and the statechart, from which all transitions can be easily
   * accessed.
   * This method is necessary because of the syntax of PlantUml. Composite states are defined this way:
   * 
   * state State1{
   * 	[*] --> State2
   * }
   * 
   * Internal states have to be listed "inside" the composite state.
   * The regions of composite states help us to achieve this.
   * This method checks if the state received as parameter has regions, with the help of the
   * regionDispatch() method.
   * If it has, then it will search the pseudostates first (except initial and history states), then the
   * entry/exit actions of simple and composite states, and lastly, the transitions.
   * This searching order is necessary because of how PlantUML works.
   * If there are multiple regions in the state, it will separate them.
   */
  protected String regionSearch(final StateNode state, final StatechartDefinition statechart) {
    EList<Region> _regionsDispatch = this.regionsDispatch(state);
    boolean _tripleNotEquals = (_regionsDispatch != null);
    if (_tripleNotEquals) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("state ");
      String _name = state.getName();
      _builder.append(_name);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      {
        EList<Region> _regionsDispatch_1 = this.regionsDispatch(state);
        for(final Region region : _regionsDispatch_1) {
          {
            EList<StateNode> _stateNodes = region.getStateNodes();
            for(final StateNode pseudo : _stateNodes) {
              {
                if ((pseudo instanceof PseudoState)) {
                  _builder.append("\t");
                  String _transformPseudoState = this.transformPseudoState(((PseudoState)pseudo));
                  _builder.append(_transformPseudoState, "\t");
                  _builder.newLineIfNotEmpty();
                }
              }
            }
          }
          {
            EList<StateNode> _stateNodes_1 = region.getStateNodes();
            for(final StateNode inner : _stateNodes_1) {
              _builder.append("\t");
              String _regionSearch = this.regionSearch(inner, statechart);
              _builder.append(_regionSearch, "\t");
              _builder.newLineIfNotEmpty();
              {
                if ((!(inner instanceof PseudoState))) {
                  {
                    String _stateActionsSearch = this.stateActionsSearch(inner);
                    boolean _tripleNotEquals_1 = (_stateActionsSearch != null);
                    if (_tripleNotEquals_1) {
                      _builder.append("\t");
                      _builder.append("\t");
                      String _stateActionsSearch_1 = this.stateActionsSearch(inner);
                      _builder.append(_stateActionsSearch_1, "\t\t");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                }
              }
              {
                EList<Transition> _transitions = statechart.getTransitions();
                for(final Transition itransition : _transitions) {
                  {
                    StateNode _sourceState = itransition.getSourceState();
                    boolean _equals = Objects.equal(_sourceState, inner);
                    if (_equals) {
                      _builder.append("\t");
                      _builder.append("\t");
                      String _stateSearch = this.stateSearch(itransition);
                      _builder.append(_stateSearch, "\t\t");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                }
              }
            }
          }
          {
            EList<StateNode> _stateNodes_2 = region.getStateNodes();
            for(final StateNode inner_1 : _stateNodes_2) {
            }
          }
          {
            if (((((Object[])Conversions.unwrapArray(this.regionsDispatch(state), Object.class)).length > 1) && (region != IterableExtensions.<Region>last(this.regionsDispatch(state))))) {
              _builder.append("\t");
              _builder.append("--");
              _builder.newLine();
            }
          }
        }
      }
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      final String result = _builder.toString();
      return result;
    } else {
      final Object result_1 = null;
      return ((String)result_1);
    }
  }

  /**
   * stateActionsSearch(StateNode)
   * 
   * This method searches for the actions of non-pseudostates.
   * If the state received as parameter has entry or exit actions, it gathers and
   * returns them in the "result" variable.
   */
  protected String stateActionsSearch(final StateNode statenode) {
    final State state = ((State) statenode);
    if (((!state.getEntryActions().isEmpty()) || (!state.getExitActions().isEmpty()))) {
      StringConcatenation _builder = new StringConcatenation();
      {
        boolean _isEmpty = state.getEntryActions().isEmpty();
        boolean _not = (!_isEmpty);
        if (_not) {
          {
            EList<Action> _entryActions = state.getEntryActions();
            for(final Action entry : _entryActions) {
              String _name = statenode.getName();
              _builder.append(_name);
              _builder.append(" : entry / ");
              String _transformAction = this.transformAction(entry);
              _builder.append(_transformAction);
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      {
        boolean _isEmpty_1 = state.getExitActions().isEmpty();
        boolean _not_1 = (!_isEmpty_1);
        if (_not_1) {
          {
            EList<Action> _exitActions = state.getExitActions();
            for(final Action exit : _exitActions) {
              String _name_1 = statenode.getName();
              _builder.append(_name_1);
              _builder.append(" : exit / ");
              String _transformAction_1 = this.transformAction(exit);
              _builder.append(_transformAction_1);
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      final String result = _builder.toString();
      return result;
    } else {
      final Object result_1 = null;
      return ((String)result_1);
    }
  }

  /**
   * regionsDispatch(StateNode)
   * 
   * Contrary to the name, this is not a real dispatch method.
   * It returns the regions of non-pseudostates, or null.
   */
  protected EList<Region> regionsDispatch(final StateNode state) {
    if ((!(state instanceof PseudoState))) {
      final CompositeElement statecomp = ((CompositeElement) state);
      boolean _isEmpty = statecomp.getRegions().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        final EList<Region> region = statecomp.getRegions();
        return region;
      } else {
        return null;
      }
    }
    return null;
  }

  /**
   * mainRegionSearch(StatechartDefitnition)
   * 
   * This method has a similar functionality to the regionSearch() method, but this is for the uppermost,
   * main region. The result of this method is the mainString variable, which contains the whole visualization.
   */
  protected String mainRegionSearch(final StatechartDefinition statechart) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("state ");
    String _name = statechart.getName();
    _builder.append(_name);
    _builder.append(" <<squareStyle>> {");
    _builder.newLineIfNotEmpty();
    {
      EList<Region> _regions = statechart.getRegions();
      for(final Region main : _regions) {
        _builder.append("\t");
        _builder.append("\'region: ");
        String _keyword = Util.keyword(main);
        _builder.append(_keyword, "\t");
        _builder.newLineIfNotEmpty();
        {
          EList<StateNode> _stateNodes = main.getStateNodes();
          for(final StateNode pseudo : _stateNodes) {
            {
              if ((pseudo instanceof PseudoState)) {
                _builder.append("\t");
                String _transformPseudoState = this.transformPseudoState(((PseudoState)pseudo));
                _builder.append(_transformPseudoState, "\t");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        {
          Iterable<State> _filter = Iterables.<State>filter(main.getStateNodes(), State.class);
          for(final State mainstate : _filter) {
            _builder.append("\t");
            String _regionSearch = this.regionSearch(mainstate, statechart);
            _builder.append(_regionSearch, "\t");
            _builder.newLineIfNotEmpty();
            {
              if ((!(mainstate instanceof PseudoState))) {
                {
                  String _stateActionsSearch = this.stateActionsSearch(mainstate);
                  boolean _tripleNotEquals = (_stateActionsSearch != null);
                  if (_tripleNotEquals) {
                    _builder.append("\t");
                    String _stateActionsSearch_1 = this.stateActionsSearch(mainstate);
                    _builder.append(_stateActionsSearch_1, "\t");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        {
          EList<Transition> _transitions = statechart.getTransitions();
          for(final Transition transition : _transitions) {
            {
              EList<StateNode> _stateNodes_1 = main.getStateNodes();
              for(final StateNode mainstate_1 : _stateNodes_1) {
                {
                  StateNode _sourceState = transition.getSourceState();
                  boolean _equals = Objects.equal(_sourceState, mainstate_1);
                  if (_equals) {
                    _builder.append("\t");
                    String _stateSearch = this.stateSearch(transition);
                    _builder.append(_stateSearch, "\t");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        _builder.append("\t");
        _builder.newLine();
        {
          boolean _isLastRegion = this.isLastRegion(statechart.getRegions(), main);
          boolean _not = (!_isLastRegion);
          if (_not) {
            _builder.append("\t");
            _builder.append("--");
            _builder.newLine();
          }
        }
        _builder.append("\t");
        _builder.newLine();
      }
    }
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    final String mainString = _builder.toString();
    return mainString;
  }

  protected boolean isLastRegion(final EList<Region> regions, final Region region) {
    final int size = regions.size();
    boolean _contains = regions.contains(region);
    if (_contains) {
      int _indexOf = regions.indexOf(region);
      boolean _equals = (_indexOf == (size - 1));
      if (_equals) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  /**
   * stateSearch(Transition)
   * 
   * This method searches the source and target state of the transition received as parameter.
   * This is where the visualization of the initial and history states is handled, as well as
   * the obtaining of the guards and triggers of transitions.
   * The end result will look like this:
   * 
   * State1 -> State2 : trigger [guard] / action
   */
  protected String stateSearch(final Transition transition) {
    final StateNode source = transition.getSourceState();
    final Trigger trigger = transition.getTrigger();
    final Expression guard = transition.getGuard();
    final EList<Action> effects = transition.getEffects();
    final StateNode target = transition.getTargetState();
    String arrow = "";
    if (((source instanceof EntryState) || (StatechartModelDerivedFeatures.isOrthogonal(StatechartModelDerivedFeatures.getParentRegion(source)) && StatechartModelDerivedFeatures.isState(target)))) {
      arrow = "->";
    } else {
      arrow = "-->";
    }
    StringConcatenation _builder = new StringConcatenation();
    String _sourceText = this.getSourceText(transition);
    _builder.append(_sourceText);
    _builder.append(" ");
    _builder.append(arrow);
    _builder.append(" ");
    String _name = target.getName();
    _builder.append(_name);
    {
      boolean _isEmpty = StatechartModelDerivedFeatures.isEmpty(transition);
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append(" : ");
      }
    }
    {
      if ((trigger != null)) {
        String _transformTrigger = this.transformTrigger(trigger);
        _builder.append(_transformTrigger);
      }
    }
    _builder.append(" ");
    {
      if ((guard != null)) {
        _builder.append("\\n[");
        String _replaceAll = this.expressionSerializer.serialize(guard).replaceAll("\\|\\|", "||\\\\n").replaceAll("\\&\\&", "&&\\\\n");
        _builder.append(_replaceAll);
        _builder.append("]");
      }
    }
    {
      boolean _hasElements = false;
      for(final Action effect : effects) {
        if (!_hasElements) {
          _hasElements = true;
          _builder.append(" /\\n");
        } else {
          _builder.appendImmediate("\\n", "");
        }
        String _transformAction = this.transformAction(effect);
        _builder.append(_transformAction);
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }

  protected String getSourceText(final Transition transition) {
    final StateNode source = transition.getSourceState();
    boolean _matched = false;
    if (source instanceof InitialState) {
      _matched=true;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[*]");
      return _builder.toString();
    }
    if (!_matched) {
      if (source instanceof ShallowHistoryState) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("[H]");
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (source instanceof DeepHistoryState) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("[H]");
        return _builder.toString();
      }
    }
    return source.getName();
  }

  protected String listVariablesInNote(final StatechartDefinition statechart) {
    final EList<ParameterDeclaration> parameterDeclarations = statechart.getParameterDeclarations();
    final EList<VariableDeclaration> variableDeclarations = statechart.getVariableDeclarations();
    final EList<TimeoutDeclaration> timeoutDeclarations = statechart.getTimeoutDeclarations();
    if (((variableDeclarations.isEmpty() && timeoutDeclarations.isEmpty()) && parameterDeclarations.isEmpty())) {
      StringConcatenation _builder = new StringConcatenation();
      return _builder.toString();
    }
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("legend top");
    _builder_1.newLine();
    {
      for(final ParameterDeclaration parameter : parameterDeclarations) {
        _builder_1.append(" \t");
        _builder_1.append("param ");
        String _name = parameter.getName();
        _builder_1.append(_name, " \t");
        _builder_1.append(": ");
        String _serialize = this.typeSerializer.serialize(parameter.getType());
        _builder_1.append(_serialize, " \t");
        _builder_1.newLineIfNotEmpty();
      }
    }
    {
      for(final VariableDeclaration variable : variableDeclarations) {
        _builder_1.append("\t");
        _builder_1.append("var ");
        String _name_1 = variable.getName();
        _builder_1.append(_name_1, "\t");
        _builder_1.append(": ");
        String _serialize_1 = this.typeSerializer.serialize(variable.getType());
        _builder_1.append(_serialize_1, "\t");
        {
          Expression _expression = variable.getExpression();
          boolean _tripleNotEquals = (_expression != null);
          if (_tripleNotEquals) {
            _builder_1.append(" = ");
            String _serialize_2 = this.expressionSerializer.serialize(variable.getExpression());
            _builder_1.append(_serialize_2, "\t");
          }
        }
        _builder_1.newLineIfNotEmpty();
      }
    }
    {
      for(final TimeoutDeclaration timeout : timeoutDeclarations) {
        _builder_1.append("\t");
        _builder_1.append("timeout ");
        String _name_2 = timeout.getName();
        _builder_1.append(_name_2, "\t");
        _builder_1.newLineIfNotEmpty();
      }
    }
    _builder_1.append("endlegend");
    _builder_1.newLine();
    return _builder_1.toString();
  }

  protected String transformTrigger(final Trigger anyTrigger) {
    if (anyTrigger instanceof AnyTrigger) {
      return _transformTrigger((AnyTrigger)anyTrigger);
    } else if (anyTrigger instanceof EventTrigger) {
      return _transformTrigger((EventTrigger)anyTrigger);
    } else if (anyTrigger instanceof BinaryTrigger) {
      return _transformTrigger((BinaryTrigger)anyTrigger);
    } else if (anyTrigger instanceof OnCycleTrigger) {
      return _transformTrigger((OnCycleTrigger)anyTrigger);
    } else if (anyTrigger instanceof OpaqueTrigger) {
      return _transformTrigger((OpaqueTrigger)anyTrigger);
    } else if (anyTrigger instanceof UnaryTrigger) {
      return _transformTrigger((UnaryTrigger)anyTrigger);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(anyTrigger).toString());
    }
  }

  protected String transformEventReference(final EventReference anyPortEventReference) {
    if (anyPortEventReference instanceof AnyPortEventReference) {
      return _transformEventReference((AnyPortEventReference)anyPortEventReference);
    } else if (anyPortEventReference instanceof ClockTickReference) {
      return _transformEventReference((ClockTickReference)anyPortEventReference);
    } else if (anyPortEventReference instanceof PortEventReference) {
      return _transformEventReference((PortEventReference)anyPortEventReference);
    } else if (anyPortEventReference instanceof TimeoutEventReference) {
      return _transformEventReference((TimeoutEventReference)anyPortEventReference);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(anyPortEventReference).toString());
    }
  }

  protected String transformPseudoState(final PseudoState choiceState) {
    if (choiceState instanceof ChoiceState) {
      return _transformPseudoState((ChoiceState)choiceState);
    } else if (choiceState instanceof EntryState) {
      return _transformPseudoState((EntryState)choiceState);
    } else if (choiceState instanceof ForkState) {
      return _transformPseudoState((ForkState)choiceState);
    } else if (choiceState instanceof JoinState) {
      return _transformPseudoState((JoinState)choiceState);
    } else if (choiceState instanceof MergeState) {
      return _transformPseudoState((MergeState)choiceState);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(choiceState).toString());
    }
  }
}
