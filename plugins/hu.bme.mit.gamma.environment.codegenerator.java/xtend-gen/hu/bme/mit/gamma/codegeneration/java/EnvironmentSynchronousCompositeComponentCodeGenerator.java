package hu.bme.mit.gamma.codegeneration.java;

import hu.bme.mit.gamma.codegeneration.java.queries.BroadcastChannels;
import hu.bme.mit.gamma.codegeneration.java.queries.SimpleChannels;
import hu.bme.mit.gamma.codegeneration.java.util.ElementaryEnvironmentComponentUtility;
import hu.bme.mit.gamma.codegeneration.java.util.Namings;
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures;
import hu.bme.mit.gamma.expression.derivedfeatures.ExpressionModelDerivedFeatures;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.statechart.composite.AbstractSynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.PortBinding;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponent;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance;
import hu.bme.mit.gamma.statechart.composite.SynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.Event;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class EnvironmentSynchronousCompositeComponentCodeGenerator extends SynchronousCompositeComponentCodeGenerator {
  private ViatraQueryEngine engine;

  protected final ElementaryEnvironmentComponentUtility envUtil = ElementaryEnvironmentComponentUtility.INSTANCE;

  protected final ExpressionSerializer expSerializer = ExpressionSerializer.INSTANCE;

  public EnvironmentSynchronousCompositeComponentCodeGenerator(final String packageName, final String yakinduPackageName, final Trace trace) {
    super(packageName, yakinduPackageName, trace);
    this.engine = trace.getEngine();
  }

  /**
   * Creates the Java code of the synchronous composite class, containing the statemachine instances.
   */
  protected CharSequence _generateEnvironmentInports(final EnvironmentCascadeCompositeComponent component) {
    StringConcatenation _builder = new StringConcatenation();
    final Function1<EnvironmentComponentInstance, Boolean> _function = (EnvironmentComponentInstance c) -> {
      return Boolean.valueOf((c instanceof EnvironmentSynchronousCompositeComponentInstance));
    };
    final Function1<EnvironmentComponentInstance, EnvironmentSynchronousCompositeComponentInstance> _function_1 = (EnvironmentComponentInstance c) -> {
      return ((EnvironmentSynchronousCompositeComponentInstance) c);
    };
    final Function1<EnvironmentSynchronousCompositeComponentInstance, EnvironmentSynchronousCompositeComponent> _function_2 = (EnvironmentSynchronousCompositeComponentInstance c) -> {
      return c.getType();
    };
    Iterable<EnvironmentSynchronousCompositeComponent> types = IterableExtensions.<EnvironmentSynchronousCompositeComponentInstance, EnvironmentSynchronousCompositeComponent>map(IterableExtensions.<EnvironmentComponentInstance, EnvironmentSynchronousCompositeComponentInstance>map(IterableExtensions.<EnvironmentComponentInstance>filter(component.getEnvironmentComponents(), _function), _function_1), _function_2);
    _builder.newLineIfNotEmpty();
    {
      for(final EnvironmentSynchronousCompositeComponent t : types) {
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

  protected CharSequence _generateEnvironmentInports(final EnvironmentSynchronousCompositeComponent component) {
    StringConcatenation _builder = new StringConcatenation();
    final Function1<EnvironmentComponentInstance, Boolean> _function = (EnvironmentComponentInstance c) -> {
      return Boolean.valueOf((c instanceof EnvironmentSynchronousCompositeComponentInstance));
    };
    final Function1<EnvironmentComponentInstance, EnvironmentSynchronousCompositeComponentInstance> _function_1 = (EnvironmentComponentInstance c) -> {
      return ((EnvironmentSynchronousCompositeComponentInstance) c);
    };
    final Function1<EnvironmentSynchronousCompositeComponentInstance, EnvironmentSynchronousCompositeComponent> _function_2 = (EnvironmentSynchronousCompositeComponentInstance c) -> {
      return c.getType();
    };
    Iterable<EnvironmentSynchronousCompositeComponent> types = IterableExtensions.<EnvironmentSynchronousCompositeComponentInstance, EnvironmentSynchronousCompositeComponent>map(IterableExtensions.<EnvironmentComponentInstance, EnvironmentSynchronousCompositeComponentInstance>map(IterableExtensions.<EnvironmentComponentInstance>filter(component.getEnvironmentComponents(), _function), _function_1), _function_2);
    _builder.newLineIfNotEmpty();
    {
      for(final EnvironmentSynchronousCompositeComponent t : types) {
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

  protected CharSequence _generateEnvironmentInports(final CascadeCompositeComponent component) {
    return " ";
  }

  protected CharSequence _generateEnvironmentInports(final SynchronousCompositeComponent component) {
    return " ";
  }

  protected CharSequence createEnvironmentCompositeComponentClass(final AbstractSynchronousCompositeComponent component) {
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
    CharSequence _scheduingInterfaceImport = this.envUtil.getScheduingInterfaceImport(this.PACKAGE_NAME);
    _builder.append(_scheduingInterfaceImport);
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
      EList<SynchronousComponentInstance> _components = component.getComponents();
      for(final SynchronousComponentInstance instance : _components) {
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
      if ((component instanceof EnvironmentSynchronousCompositeComponent)) {
        _builder.append("\t");
        _builder.append("// Environmental Component instances");
        _builder.newLine();
        {
          EList<EnvironmentComponentInstance> _environmentComponents = ((EnvironmentSynchronousCompositeComponent)component).getEnvironmentComponents();
          for(final EnvironmentComponentInstance instance_1 : _environmentComponents) {
            {
              if ((instance_1 instanceof EnvironmentSynchronousCompositeComponentInstance)) {
                _builder.append("\t");
                _builder.append("private ");
                String _generateComponentClassName_2 = this.nameGenerator.generateComponentClassName(((EnvironmentSynchronousCompositeComponentInstance)instance_1).getType());
                _builder.append(_generateComponentClassName_2, "\t");
                _builder.append(" ");
                String _name_1 = ((EnvironmentSynchronousCompositeComponentInstance)instance_1).getName();
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
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateParameterDeclarationFields = this.componentCodeGenerator.generateParameterDeclarationFields(component);
    _builder.append(_generateParameterDeclarationFields, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _listDefinition = this.envUtil.getListDefinition();
    _builder.append(_listDefinition, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _registerFunc = this.envUtil.getRegisterFunc();
    _builder.append(_registerFunc, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
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
          boolean _hasElements = false;
          for(final ParameterDeclaration parameter : _parameterDeclarations) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "\t");
            }
            String _transformType = this.typeTransformer.transformType(parameter.getType());
            _builder.append(_transformType, "\t");
            _builder.append(" ");
            String _name_2 = parameter.getName();
            _builder.append(_name_2, "\t");
          }
          if (_hasElements) {
            _builder.append(", ", "\t");
          }
        }
        _builder.append(Namings.UNIFIED_TIMER_INTERFACE, "\t");
        _builder.append(" timer) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        CharSequence _createInstances = this.compositeComponentCodeGenerator.createInstances(component);
        _builder.append(_createInstances, "\t\t");
        _builder.newLineIfNotEmpty();
        {
          if ((component instanceof EnvironmentSynchronousCompositeComponent)) {
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("// Environmental Component instances");
            _builder.newLine();
            {
              EList<EnvironmentComponentInstance> _environmentComponents_1 = ((EnvironmentSynchronousCompositeComponent)component).getEnvironmentComponents();
              for(final EnvironmentComponentInstance instance_2 : _environmentComponents_1) {
                {
                  if ((instance_2 instanceof EnvironmentSynchronousCompositeComponentInstance)) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    String _name_3 = ((EnvironmentSynchronousCompositeComponentInstance)instance_2).getName();
                    _builder.append(_name_3, "\t\t\t");
                    _builder.append(" = new ");
                    String _firstUpper_1 = StringExtensions.toFirstUpper(((EnvironmentSynchronousCompositeComponentInstance)instance_2).getType().getName());
                    _builder.append(_firstUpper_1, "\t\t\t");
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
        _builder.append("init();");
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
      boolean _hasElements_1 = false;
      for(final ParameterDeclaration parameter_1 : _parameterDeclarations_1) {
        if (!_hasElements_1) {
          _hasElements_1 = true;
        } else {
          _builder.appendImmediate(", ", "\t");
        }
        String _transformType_1 = this.typeTransformer.transformType(parameter_1.getType());
        _builder.append(_transformType_1, "\t");
        _builder.append(" ");
        String _name_4 = parameter_1.getName();
        _builder.append(_name_4, "\t");
      }
    }
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    CharSequence _createInstances_1 = this.compositeComponentCodeGenerator.createInstances(component);
    _builder.append(_createInstances_1, "\t\t");
    _builder.newLineIfNotEmpty();
    {
      if ((component instanceof EnvironmentSynchronousCompositeComponent)) {
        _builder.append("\t\t");
        _builder.append("// Environmental Component instances");
        _builder.newLine();
        {
          EList<EnvironmentComponentInstance> _environmentComponents_2 = ((EnvironmentSynchronousCompositeComponent)component).getEnvironmentComponents();
          for(final EnvironmentComponentInstance instance_3 : _environmentComponents_2) {
            {
              if ((instance_3 instanceof EnvironmentSynchronousCompositeComponentInstance)) {
                _builder.append("\t\t");
                _builder.append("\t");
                String _name_5 = ((EnvironmentSynchronousCompositeComponentInstance)instance_3).getName();
                _builder.append(_name_5, "\t\t\t");
                _builder.append(" = new ");
                String _firstUpper_2 = StringExtensions.toFirstUpper(((EnvironmentSynchronousCompositeComponentInstance)instance_3).getType().getName());
                _builder.append(_firstUpper_2, "\t\t\t");
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
      EList<SynchronousComponentInstance> _components_1 = component.getComponents();
      for(final SynchronousComponentInstance instance_4 : _components_1) {
        _builder.append("\t\t");
        String _name_6 = instance_4.getName();
        _builder.append(_name_6, "\t\t");
        _builder.append(".reset();");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      if ((component instanceof CascadeCompositeComponent)) {
        _builder.append("\t\t");
        _builder.append("// Setting only a single queue for cascade statecharts");
        _builder.newLine();
        {
          final Function1<SynchronousComponentInstance, Boolean> _function = (SynchronousComponentInstance it) -> {
            SynchronousComponent _type = it.getType();
            return Boolean.valueOf((_type instanceof StatechartDefinition));
          };
          Iterable<SynchronousComponentInstance> _filter = IterableExtensions.<SynchronousComponentInstance>filter(((CascadeCompositeComponent)component).getComponents(), _function);
          for(final SynchronousComponentInstance instance_5 : _filter) {
            _builder.append("\t\t");
            String _name_7 = instance_5.getName();
            _builder.append(_name_7, "\t\t");
            _builder.append(".change");
            String _firstUpper_3 = StringExtensions.toFirstUpper(this.INSERT_QUEUE);
            _builder.append(_firstUpper_3, "\t\t");
            _builder.append("();");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      if ((component instanceof EnvironmentSynchronousCompositeComponent)) {
        {
          EList<EnvironmentComponentInstance> _environmentComponents_3 = ((EnvironmentSynchronousCompositeComponent)component).getEnvironmentComponents();
          for(final EnvironmentComponentInstance envComp : _environmentComponents_3) {
            {
              if ((envComp instanceof EnvironmentSynchronousCompositeComponentInstance)) {
                _builder.append("\t\t");
                String _name_8 = ((EnvironmentSynchronousCompositeComponentInstance)envComp).getName();
                _builder.append(_name_8, "\t\t");
                _builder.append(".reset();");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("\t\t");
    _builder.append("clearPorts();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// Initializing chain of listeners and events ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("notifyAllListeners();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Creates the channel mappings and enters the wrapped statemachines. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private void init() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// Registration of simple channels");
    _builder.newLine();
    {
      Collection<SimpleChannels.Match> _allMatches = SimpleChannels.Matcher.on(this.engine).getAllMatches(component, null, null, null);
      for(final SimpleChannels.Match channelMatch : _allMatches) {
        {
          if (((!(channelMatch.getProvidedPort().getInstance() instanceof ElementaryEnvironmentComponentInstance)) && (!(channelMatch.getRequiredPort().getInstance() instanceof ElementaryEnvironmentComponentInstance)))) {
            _builder.append("\t\t");
            String _name_9 = channelMatch.getProvidedPort().getInstance().getName();
            _builder.append(_name_9, "\t\t");
            _builder.append(".get");
            String _firstUpper_4 = StringExtensions.toFirstUpper(channelMatch.getProvidedPort().getPort().getName());
            _builder.append(_firstUpper_4, "\t\t");
            _builder.append("().registerListener(");
            String _name_10 = channelMatch.getRequiredPort().getInstance().getName();
            _builder.append(_name_10, "\t\t");
            _builder.append(".get");
            String _firstUpper_5 = StringExtensions.toFirstUpper(channelMatch.getRequiredPort().getPort().getName());
            _builder.append(_firstUpper_5, "\t\t");
            _builder.append("());");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            String _name_11 = channelMatch.getRequiredPort().getInstance().getName();
            _builder.append(_name_11, "\t\t");
            _builder.append(".get");
            String _firstUpper_6 = StringExtensions.toFirstUpper(channelMatch.getRequiredPort().getPort().getName());
            _builder.append(_firstUpper_6, "\t\t");
            _builder.append("().registerListener(");
            String _name_12 = channelMatch.getProvidedPort().getInstance().getName();
            _builder.append(_name_12, "\t\t");
            _builder.append(".get");
            String _firstUpper_7 = StringExtensions.toFirstUpper(channelMatch.getProvidedPort().getPort().getName());
            _builder.append(_firstUpper_7, "\t\t");
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
      Collection<BroadcastChannels.Match> _allMatches_1 = BroadcastChannels.Matcher.on(this.engine).getAllMatches(component, null, null, null);
      for(final BroadcastChannels.Match channelMatch_1 : _allMatches_1) {
        {
          if (((!(channelMatch_1.getProvidedPort().getInstance() instanceof ElementaryEnvironmentComponentInstance)) && (!(channelMatch_1.getRequiredPort().getInstance() instanceof ElementaryEnvironmentComponentInstance)))) {
            _builder.append("\t\t");
            String _name_13 = channelMatch_1.getProvidedPort().getInstance().getName();
            _builder.append(_name_13, "\t\t");
            _builder.append(".get");
            String _firstUpper_8 = StringExtensions.toFirstUpper(channelMatch_1.getProvidedPort().getPort().getName());
            _builder.append(_firstUpper_8, "\t\t");
            _builder.append("().registerListener(");
            String _name_14 = channelMatch_1.getRequiredPort().getInstance().getName();
            _builder.append(_name_14, "\t\t");
            _builder.append(".get");
            String _firstUpper_9 = StringExtensions.toFirstUpper(channelMatch_1.getRequiredPort().getPort().getName());
            _builder.append(_firstUpper_9, "\t\t");
            _builder.append("());");
            _builder.newLineIfNotEmpty();
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
      boolean _hasElements_2 = false;
      for(final Port systemPort : _ports_1) {
        if (!_hasElements_2) {
          _hasElements_2 = true;
        } else {
          _builder.appendImmediate("\n", "\t");
        }
        _builder.append("\t");
        _builder.append("public class ");
        String _firstUpper_10 = StringExtensions.toFirstUpper(systemPort.getName());
        _builder.append(_firstUpper_10, "\t");
        _builder.append(" implements ");
        String _implementationName = Namings.getImplementationName(systemPort.getInterfaceRealization().getInterface());
        _builder.append(_implementationName, "\t");
        _builder.append(".");
        String _firstUpper_11 = StringExtensions.toFirstUpper(systemPort.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
        _builder.append(_firstUpper_11, "\t");
        _builder.append(",");
        String _implementationName_1 = Namings.getImplementationName(systemPort.getInterfaceRealization().getInterface());
        _builder.append(_implementationName_1, "\t");
        _builder.append(".Listener.");
        String _firstUpper_12 = StringExtensions.toFirstUpper(systemPort.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
        _builder.append(_firstUpper_12, "\t");
        _builder.append(" {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("private List<");
        String _implementationName_2 = Namings.getImplementationName(systemPort.getInterfaceRealization().getInterface());
        _builder.append(_implementationName_2, "\t\t");
        _builder.append(".Listener.");
        String _firstUpper_13 = StringExtensions.toFirstUpper(systemPort.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
        _builder.append(_firstUpper_13, "\t\t");
        _builder.append("> listeners = new LinkedList<");
        String _implementationName_3 = Namings.getImplementationName(systemPort.getInterfaceRealization().getInterface());
        _builder.append(_implementationName_3, "\t\t");
        _builder.append(".Listener.");
        String _firstUpper_14 = StringExtensions.toFirstUpper(systemPort.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
        _builder.append(_firstUpper_14, "\t\t");
        _builder.append(">();");
        _builder.newLineIfNotEmpty();
        {
          List<Event> _outputEvents = StatechartModelDerivedFeatures.getOutputEvents(systemPort);
          for(final Event event : _outputEvents) {
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("boolean isRaised");
            String _firstUpper_15 = StringExtensions.toFirstUpper(event.getName());
            _builder.append(_firstUpper_15, "\t\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            {
              EList<ParameterDeclaration> _parameterDeclarations_2 = event.getParameterDeclarations();
              for(final ParameterDeclaration parameter_2 : _parameterDeclarations_2) {
                _builder.append("\t");
                _builder.append("\t");
                String _transformType_2 = this.typeTransformer.transformType(parameter_2.getType());
                _builder.append(_transformType_2, "\t\t");
                _builder.append(" ");
                CharSequence _generateName = this.gammaEventDeclarationHandler.generateName(parameter_2);
                _builder.append(_generateName, "\t\t");
                _builder.append(" = ");
                String _serialize = this.expSerializer.serialize(ExpressionModelDerivedFeatures.getDefaultExpression(parameter_2.getType()));
                _builder.append(_serialize, "\t\t");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("public ");
        String _firstUpper_16 = StringExtensions.toFirstUpper(systemPort.getName());
        _builder.append(_firstUpper_16, "\t\t");
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("// Registering the listener to the contained component");
        _builder.newLine();
        {
          Collection<PortBinding> _portBindings = StatechartModelDerivedFeatures.getPortBindings(systemPort);
          for(final PortBinding portBinding : _portBindings) {
            {
              ComponentInstance _instance = portBinding.getInstancePortReference().getInstance();
              boolean _not = (!(_instance instanceof ElementaryEnvironmentComponentInstance));
              if (_not) {
                _builder.append("\t");
                _builder.append("\t\t");
                String _name_15 = portBinding.getInstancePortReference().getInstance().getName();
                _builder.append(_name_15, "\t\t\t");
                _builder.append(".get");
                String _firstUpper_17 = StringExtensions.toFirstUpper(portBinding.getInstancePortReference().getPort().getName());
                _builder.append(_firstUpper_17, "\t\t\t");
                _builder.append("().registerListener(new ");
                String _firstUpper_18 = StringExtensions.toFirstUpper(portBinding.getCompositeSystemPort().getName());
                _builder.append(_firstUpper_18, "\t\t\t");
                _builder.append("Util());");
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
        CharSequence _delegateRaisingMethods = this.compositeComponentCodeGenerator.delegateRaisingMethods(systemPort);
        _builder.append(_delegateRaisingMethods, "\t\t");
        _builder.append(" ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        CharSequence _implementOutMethods = this.compositeComponentCodeGenerator.implementOutMethods(systemPort);
        _builder.append(_implementOutMethods, "\t\t");
        _builder.newLineIfNotEmpty();
        {
          List<Event> _outputEvents_1 = StatechartModelDerivedFeatures.getOutputEvents(systemPort);
          boolean _hasElements_3 = false;
          for(final Event event_1 : _outputEvents_1) {
            if (!_hasElements_3) {
              _hasElements_3 = true;
            } else {
              _builder.appendImmediate("\n", "\t\t");
            }
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("@Override");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("public void raise");
            String _firstUpper_19 = StringExtensions.toFirstUpper(event_1.getName());
            _builder.append(_firstUpper_19, "\t\t");
            _builder.append("(");
            CharSequence _generateParameters = this.gammaEventDeclarationHandler.generateParameters(event_1);
            _builder.append(_generateParameters, "\t\t");
            _builder.append(") {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("isRaised");
            String _firstUpper_20 = StringExtensions.toFirstUpper(event_1.getName());
            _builder.append(_firstUpper_20, "\t\t\t");
            _builder.append(" = true;");
            _builder.newLineIfNotEmpty();
            {
              EList<ParameterDeclaration> _parameterDeclarations_3 = event_1.getParameterDeclarations();
              for(final ParameterDeclaration parameter_3 : _parameterDeclarations_3) {
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                String _firstUpper_21 = StringExtensions.toFirstUpper(systemPort.getName());
                _builder.append(_firstUpper_21, "\t\t\t");
                _builder.append(".this.");
                CharSequence _generateName_1 = this.gammaEventDeclarationHandler.generateName(parameter_3);
                _builder.append(_generateName_1, "\t\t\t");
                _builder.append(" = ");
                CharSequence _generateName_2 = this.gammaEventDeclarationHandler.generateName(parameter_3);
                _builder.append(_generateName_2, "\t\t\t");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("// Class for the setting of the boolean fields (events)");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("private class ");
        String _firstUpper_22 = StringExtensions.toFirstUpper(systemPort.getName());
        _builder.append(_firstUpper_22, "\t\t");
        _builder.append("Util implements ");
        String _implementationName_4 = Namings.getImplementationName(systemPort.getInterfaceRealization().getInterface());
        _builder.append(_implementationName_4, "\t\t");
        _builder.append(".Listener.");
        String _firstUpper_23 = StringExtensions.toFirstUpper(systemPort.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
        _builder.append(_firstUpper_23, "\t\t");
        _builder.append(" {");
        _builder.newLineIfNotEmpty();
        {
          List<Event> _outputEvents_2 = StatechartModelDerivedFeatures.getOutputEvents(systemPort);
          boolean _hasElements_4 = false;
          for(final Event event_2 : _outputEvents_2) {
            if (!_hasElements_4) {
              _hasElements_4 = true;
            } else {
              _builder.appendImmediate("\n", "\t\t\t");
            }
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("@Override");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("public void raise");
            String _firstUpper_24 = StringExtensions.toFirstUpper(event_2.getName());
            _builder.append(_firstUpper_24, "\t\t\t");
            _builder.append("(");
            CharSequence _generateParameters_1 = this.gammaEventDeclarationHandler.generateParameters(event_2);
            _builder.append(_generateParameters_1, "\t\t\t");
            _builder.append(") {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("isRaised");
            String _firstUpper_25 = StringExtensions.toFirstUpper(event_2.getName());
            _builder.append(_firstUpper_25, "\t\t\t\t");
            _builder.append(" = true;");
            _builder.newLineIfNotEmpty();
            {
              EList<ParameterDeclaration> _parameterDeclarations_4 = event_2.getParameterDeclarations();
              for(final ParameterDeclaration parameter_4 : _parameterDeclarations_4) {
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("\t");
                String _firstUpper_26 = StringExtensions.toFirstUpper(systemPort.getName());
                _builder.append(_firstUpper_26, "\t\t\t\t");
                _builder.append(".this.");
                CharSequence _generateName_3 = this.gammaEventDeclarationHandler.generateName(parameter_4);
                _builder.append(_generateName_3, "\t\t\t\t");
                _builder.append(" = ");
                CharSequence _generateName_4 = this.gammaEventDeclarationHandler.generateName(parameter_4);
                _builder.append(_generateName_4, "\t\t\t\t");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("}");
            _builder.newLine();
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
        _builder.append("public void registerListener(");
        String _implementationName_5 = Namings.getImplementationName(systemPort.getInterfaceRealization().getInterface());
        _builder.append(_implementationName_5, "\t\t");
        _builder.append(".Listener.");
        String _firstUpper_27 = StringExtensions.toFirstUpper(systemPort.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
        _builder.append(_firstUpper_27, "\t\t");
        _builder.append(" listener) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("listeners.add(listener);");
        _builder.newLine();
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
        String _implementationName_6 = Namings.getImplementationName(systemPort.getInterfaceRealization().getInterface());
        _builder.append(_implementationName_6, "\t\t");
        _builder.append(".Listener.");
        String _firstUpper_28 = StringExtensions.toFirstUpper(systemPort.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
        _builder.append(_firstUpper_28, "\t\t");
        _builder.append("> getRegisteredListeners() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("return listeners;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("/** Resetting the boolean event flags to false. */");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("public void clear() {");
        _builder.newLine();
        {
          List<Event> _outputEvents_3 = StatechartModelDerivedFeatures.getOutputEvents(systemPort);
          for(final Event event_3 : _outputEvents_3) {
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("isRaised");
            String _firstUpper_29 = StringExtensions.toFirstUpper(event_3.getName());
            _builder.append(_firstUpper_29, "\t\t\t");
            _builder.append(" = false;");
            _builder.newLineIfNotEmpty();
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
        _builder.append("/** Notifying the registered listeners. */");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("public void notifyListeners() {");
        _builder.newLine();
        {
          List<Event> _outputEvents_4 = StatechartModelDerivedFeatures.getOutputEvents(systemPort);
          for(final Event event_4 : _outputEvents_4) {
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("if (isRaised");
            String _firstUpper_30 = StringExtensions.toFirstUpper(event_4.getName());
            _builder.append(_firstUpper_30, "\t\t\t");
            _builder.append(") {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("for (");
            String _implementationName_7 = Namings.getImplementationName(systemPort.getInterfaceRealization().getInterface());
            _builder.append(_implementationName_7, "\t\t\t\t");
            _builder.append(".Listener.");
            String _firstUpper_31 = StringExtensions.toFirstUpper(systemPort.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
            _builder.append(_firstUpper_31, "\t\t\t\t");
            _builder.append(" listener : listeners) {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("\t\t");
            _builder.append("listener.raise");
            String _firstUpper_32 = StringExtensions.toFirstUpper(event_4.getName());
            _builder.append(_firstUpper_32, "\t\t\t\t\t");
            _builder.append("(");
            CharSequence _generateArguments = this.gammaEventDeclarationHandler.generateArguments(event_4);
            _builder.append(_generateArguments, "\t\t\t\t\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("}");
            _builder.newLine();
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
        String _firstUpper_33 = StringExtensions.toFirstUpper(systemPort.getName());
        _builder.append(_firstUpper_33, "\t");
        _builder.append(" get");
        String _firstUpper_34 = StringExtensions.toFirstUpper(systemPort.getName());
        _builder.append(_firstUpper_34, "\t");
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
    _builder.append("/** Clears the the boolean flags of all out-events in each contained port. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private void clearPorts() {");
    _builder.newLine();
    {
      EList<PortBinding> _portBindings_1 = component.getPortBindings();
      for(final PortBinding portBinding_1 : _portBindings_1) {
        _builder.append("\t\t");
        _builder.append("get");
        String _firstUpper_35 = StringExtensions.toFirstUpper(portBinding_1.getCompositeSystemPort().getName());
        _builder.append(_firstUpper_35, "\t\t");
        _builder.append("().clear();");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Notifies all registered listeners in each contained port. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void notifyAllListeners() {");
    _builder.newLine();
    {
      EList<SynchronousComponentInstance> _components_2 = component.getComponents();
      for(final SynchronousComponentInstance subcomponent : _components_2) {
        _builder.append("\t\t");
        String _name_16 = subcomponent.getName();
        _builder.append(_name_16, "\t\t");
        _builder.append(".notifyAllListeners();");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      if ((component instanceof EnvironmentSynchronousCompositeComponent)) {
        {
          EList<EnvironmentComponentInstance> _environmentComponents_4 = ((EnvironmentSynchronousCompositeComponent)component).getEnvironmentComponents();
          for(final EnvironmentComponentInstance envComp_1 : _environmentComponents_4) {
            {
              if ((envComp_1 instanceof EnvironmentSynchronousCompositeComponentInstance)) {
                _builder.append("\t\t");
                String _name_17 = ((EnvironmentSynchronousCompositeComponentInstance)envComp_1).getName();
                _builder.append(_name_17, "\t\t");
                _builder.append(".notifyListeners();");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("\t\t");
    _builder.append("notifyListeners();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void notifyListeners() {");
    _builder.newLine();
    {
      EList<PortBinding> _portBindings_2 = component.getPortBindings();
      for(final PortBinding portBinding_2 : _portBindings_2) {
        _builder.append("\t\t");
        _builder.append("get");
        String _firstUpper_36 = StringExtensions.toFirstUpper(portBinding_2.getCompositeSystemPort().getName());
        _builder.append(_firstUpper_36, "\t\t");
        _builder.append("().notifyListeners();");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      if ((component instanceof SynchronousCompositeComponent)) {
        _builder.append("\t");
        _builder.append("/** Changes the event and process queues of all component instances. Should be used only be the container (composite system) class. */");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public void change");
        String _firstUpper_37 = StringExtensions.toFirstUpper(this.EVENT_QUEUE);
        _builder.append(_firstUpper_37, "\t");
        _builder.append("s() {");
        _builder.newLineIfNotEmpty();
        {
          final Function1<SynchronousComponentInstance, Boolean> _function_1 = (SynchronousComponentInstance it) -> {
            SynchronousComponent _type = it.getType();
            return Boolean.valueOf((!(_type instanceof CascadeCompositeComponent)));
          };
          Iterable<SynchronousComponentInstance> _filter_1 = IterableExtensions.<SynchronousComponentInstance>filter(((SynchronousCompositeComponent)component).getComponents(), _function_1);
          for(final SynchronousComponentInstance instance_6 : _filter_1) {
            _builder.append("\t");
            _builder.append("\t");
            String _name_18 = instance_6.getName();
            _builder.append(_name_18, "\t\t");
            _builder.append(".change");
            String _firstUpper_38 = StringExtensions.toFirstUpper(this.EVENT_QUEUE);
            _builder.append(_firstUpper_38, "\t\t");
            _builder.append("s();");
            _builder.newLineIfNotEmpty();
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
    _builder.append("/** Returns whether all event queues of the contained component instances are empty. ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Should be used only be the container (composite system) class. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean is");
    String _firstUpper_39 = StringExtensions.toFirstUpper(this.EVENT_QUEUE);
    _builder.append(_firstUpper_39, "\t");
    _builder.append("Empty() {");
    _builder.newLineIfNotEmpty();
    {
      boolean _isEmpty = component.getComponents().isEmpty();
      if (_isEmpty) {
        _builder.append("\t\t");
        _builder.append("return true;");
        _builder.newLine();
      } else {
        _builder.append("\t\t");
        _builder.append("return ");
        {
          EList<SynchronousComponentInstance> _components_3 = component.getComponents();
          boolean _hasElements_5 = false;
          for(final SynchronousComponentInstance instance_7 : _components_3) {
            if (!_hasElements_5) {
              _hasElements_5 = true;
            } else {
              _builder.appendImmediate(" && ", "\t\t");
            }
            String _name_19 = instance_7.getName();
            _builder.append(_name_19, "\t\t");
            _builder.append(".is");
            String _firstUpper_40 = StringExtensions.toFirstUpper(this.EVENT_QUEUE);
            _builder.append(_firstUpper_40, "\t\t");
            _builder.append("Empty()");
          }
        }
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Initiates cycle runs until all event queues of component instances are empty. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void runFullCycle() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("do {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("runCycle();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("while (!is");
    String _firstUpper_41 = StringExtensions.toFirstUpper(this.EVENT_QUEUE);
    _builder.append(_firstUpper_41, "\t\t");
    _builder.append("Empty() ");
    String _generateIsEmpty = this.generateIsEmpty(component);
    _builder.append(_generateIsEmpty, "\t\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Changes event queues and initiates a cycle run.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* This should be the execution point from an asynchronous component. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void runCycle() {");
    _builder.newLine();
    {
      if ((component instanceof SynchronousCompositeComponent)) {
        _builder.append("\t\t");
        _builder.append("// Changing the insert and process queues for all synchronous subcomponents");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("change");
        String _firstUpper_42 = StringExtensions.toFirstUpper(this.EVENT_QUEUE);
        _builder.append(_firstUpper_42, "\t\t");
        _builder.append("s();");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("// Composite type-dependent behavior");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("runComponent();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Initiates a cycle run without changing the event queues.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Should be used only be the container (composite system) class. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void runComponent() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// Running contained components");
    _builder.newLine();
    {
      List<SynchronousComponentInstance> _scheduledInstances = EnvironmentModelDerivedFeatures.getScheduledInstances(component);
      for(final SynchronousComponentInstance instance_8 : _scheduledInstances) {
        {
          if ((instance_8 instanceof ElementaryEnvironmentComponentInstance)) {
            _builder.append("\t\t");
            String _scheduleCall = this.envUtil.getScheduleCall(((ElementaryEnvironmentComponentInstance)instance_8));
            _builder.append(_scheduleCall, "\t\t");
            _builder.newLineIfNotEmpty();
          } else {
            if (((component instanceof CascadeCompositeComponent) && (instance_8.getType() instanceof SynchronousCompositeComponent))) {
              _builder.append("\t\t");
              String _name_20 = instance_8.getName();
              _builder.append(_name_20, "\t\t");
              _builder.append(".runCycle();");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("\t\t");
              String _name_21 = instance_8.getName();
              _builder.append(_name_21, "\t\t");
              _builder.append(".runComponent();");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
    }
    {
      if ((component instanceof EnvironmentSynchronousCompositeComponent)) {
        {
          EList<EnvironmentComponentInstance> _environmentComponents_5 = ((EnvironmentSynchronousCompositeComponent)component).getEnvironmentComponents();
          for(final EnvironmentComponentInstance instance_9 : _environmentComponents_5) {
            {
              if ((instance_9 instanceof EnvironmentSynchronousCompositeComponentInstance)) {
                _builder.append("\t\t");
                String _name_22 = ((EnvironmentSynchronousCompositeComponentInstance)instance_9).getName();
                _builder.append(_name_22, "\t\t");
                _builder.append(".runCycle();");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("\t\t");
    _builder.append("// Notifying registered listeners");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("notifyListeners();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// Ends with the clearing of the out-event flags");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("clearPorts();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    {
      boolean _needTimer_1 = this.timingDeterminer.needTimer(component);
      if (_needTimer_1) {
        _builder.append("\t");
        _builder.append("/** Setter for the timer e.g., a virtual timer. */");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public void setTimer(");
        _builder.append(Namings.UNIFIED_TIMER_INTERFACE, "\t");
        _builder.append(" timer) {");
        _builder.newLineIfNotEmpty();
        {
          EList<SynchronousComponentInstance> _components_4 = component.getComponents();
          for(final SynchronousComponentInstance instance_10 : _components_4) {
            {
              boolean _needTimer_2 = this.timingDeterminer.needTimer(instance_10.getType());
              if (_needTimer_2) {
                _builder.append("\t");
                _builder.append("\t");
                String _name_23 = instance_10.getName();
                _builder.append(_name_23, "\t\t");
                _builder.append(".setTimer(timer);");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("reset();");
        _builder.newLine();
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
      EList<SynchronousComponentInstance> _components_5 = component.getComponents();
      boolean _hasElements_6 = false;
      for(final SynchronousComponentInstance instance_11 : _components_5) {
        if (!_hasElements_6) {
          _hasElements_6 = true;
        } else {
          _builder.appendImmediate("\n", "\t");
        }
        _builder.append("\t");
        _builder.append("public ");
        String _generateComponentClassName_5 = this.nameGenerator.generateComponentClassName(instance_11.getType());
        _builder.append(_generateComponentClassName_5, "\t");
        _builder.append(" get");
        String _firstUpper_43 = StringExtensions.toFirstUpper(instance_11.getName());
        _builder.append(_firstUpper_43, "\t");
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("return ");
        String _name_24 = instance_11.getName();
        _builder.append(_name_24, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      if ((component instanceof EnvironmentSynchronousCompositeComponent)) {
        _builder.append("\t");
        _builder.append("// Environmental Component instances");
        _builder.newLine();
        {
          EList<EnvironmentComponentInstance> _environmentComponents_6 = ((EnvironmentSynchronousCompositeComponent)component).getEnvironmentComponents();
          for(final EnvironmentComponentInstance instance_12 : _environmentComponents_6) {
            {
              if ((instance_12 instanceof EnvironmentSynchronousCompositeComponentInstance)) {
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("public ");
                String _generateComponentClassName_6 = this.nameGenerator.generateComponentClassName(((EnvironmentSynchronousCompositeComponentInstance)instance_12).getType());
                _builder.append(_generateComponentClassName_6, "\t\t");
                _builder.append(" get");
                String _firstUpper_44 = StringExtensions.toFirstUpper(((EnvironmentSynchronousCompositeComponentInstance)instance_12).getName());
                _builder.append(_firstUpper_44, "\t\t");
                _builder.append("() {");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("return ");
                String _name_25 = ((EnvironmentSynchronousCompositeComponentInstance)instance_12).getName();
                _builder.append(_name_25, "\t\t\t");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
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
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }

  protected String _generateIsEmpty(final EnvironmentSynchronousCompositeComponent component) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isEmpty = component.getEnvironmentComponents().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("&& ");
      }
    }
    _builder.append("  ");
    {
      EList<EnvironmentComponentInstance> _environmentComponents = component.getEnvironmentComponents();
      boolean _hasElements = false;
      for(final EnvironmentComponentInstance inst : _environmentComponents) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(" && ", "");
        }
        _builder.append(" ");
        String _isEmptyCall = this.envUtil.getIsEmptyCall(((ElementaryEnvironmentComponentInstance) inst));
        _builder.append(_isEmptyCall);
        _builder.append(" ");
      }
    }
    return _builder.toString();
  }

  protected String _generateIsEmpty(final EnvironmentCascadeCompositeComponent component) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isEmpty = component.getEnvironmentComponents().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("&& ");
      }
    }
    _builder.append("  ");
    {
      EList<EnvironmentComponentInstance> _environmentComponents = component.getEnvironmentComponents();
      boolean _hasElements = false;
      for(final EnvironmentComponentInstance inst : _environmentComponents) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(" && ", "");
        }
        _builder.append(" ");
        String _isEmptyCall = this.envUtil.getIsEmptyCall(((ElementaryEnvironmentComponentInstance) inst));
        _builder.append(_isEmptyCall);
        _builder.append(" ");
      }
    }
    return _builder.toString();
  }

  protected String _generateIsEmpty(final AbstractSynchronousCompositeComponent component) {
    return "";
  }

  protected CharSequence generateEnvironmentInports(final AbstractSynchronousCompositeComponent component) {
    if (component instanceof EnvironmentCascadeCompositeComponent) {
      return _generateEnvironmentInports((EnvironmentCascadeCompositeComponent)component);
    } else if (component instanceof EnvironmentSynchronousCompositeComponent) {
      return _generateEnvironmentInports((EnvironmentSynchronousCompositeComponent)component);
    } else if (component instanceof CascadeCompositeComponent) {
      return _generateEnvironmentInports((CascadeCompositeComponent)component);
    } else if (component instanceof SynchronousCompositeComponent) {
      return _generateEnvironmentInports((SynchronousCompositeComponent)component);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(component).toString());
    }
  }

  public String generateIsEmpty(final AbstractSynchronousCompositeComponent component) {
    if (component instanceof EnvironmentCascadeCompositeComponent) {
      return _generateIsEmpty((EnvironmentCascadeCompositeComponent)component);
    } else if (component instanceof EnvironmentSynchronousCompositeComponent) {
      return _generateIsEmpty((EnvironmentSynchronousCompositeComponent)component);
    } else if (component != null) {
      return _generateIsEmpty(component);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(component).toString());
    }
  }
}
