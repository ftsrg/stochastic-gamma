package hu.bme.mit.gamma.codegeneration.java;

import hu.bme.mit.gamma.codegeneration.java.queries.AnyPortTriggersOfWrappers;
import hu.bme.mit.gamma.codegeneration.java.queries.ClockTriggersOfWrappers;
import hu.bme.mit.gamma.codegeneration.java.queries.PortEventTriggersOfWrappers;
import hu.bme.mit.gamma.codegeneration.java.queries.QueuesOfClocks;
import hu.bme.mit.gamma.codegeneration.java.util.Namings;
import hu.bme.mit.gamma.expression.model.Expression;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.expression.model.Type;
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter;
import hu.bme.mit.gamma.statechart.composite.ControlSpecification;
import hu.bme.mit.gamma.statechart.composite.MessageQueue;
import hu.bme.mit.gamma.statechart.interface_.AnyTrigger;
import hu.bme.mit.gamma.statechart.interface_.Clock;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.interface_.SimpleTrigger;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class EnvironmentAsynchronousAdapterCodeGenerator extends AsynchronousAdapterCodeGenerator {
  public EnvironmentAsynchronousAdapterCodeGenerator(final String packageName, final Trace trace) {
    super(packageName, trace);
  }

  /**
   * Creates the Java code of the synchronous composite class, containing the statemachine instances.
   */
  @Override
  public CharSequence createAsynchronousAdapterClass(final AsynchronousAdapter component) {
    CharSequence _xblockexpression = null;
    {
      int clockId = 0;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package ");
      CharSequence _generateComponentPackageName = this.nameGenerator.generateComponentPackageName(component);
      _builder.append(_generateComponentPackageName);
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      CharSequence _generateWrapperImports = this.generateWrapperImports(component);
      _builder.append(_generateWrapperImports);
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("public class ");
      String _generateComponentClassName = this.nameGenerator.generateComponentClassName(component);
      _builder.append(_generateComponentClassName);
      _builder.append(" implements Runnable, ");
      String _generatePortOwnerInterfaceName = this.nameGenerator.generatePortOwnerInterfaceName(component);
      _builder.append(_generatePortOwnerInterfaceName);
      _builder.append(" {\t\t\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("// Thread running this wrapper instance");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("private Thread thread;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("// Wrapped synchronous instance");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("private ");
      String _generateComponentClassName_1 = this.nameGenerator.generateComponentClassName(component.getWrappedComponent().getType());
      _builder.append(_generateComponentClassName_1, "\t");
      _builder.append(" ");
      String _generateWrappedComponentName = this.nameGenerator.generateWrappedComponentName(component);
      _builder.append(_generateWrappedComponentName, "\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("// Control port instances");
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
      _builder.append("// Wrapped port instances");
      _builder.newLine();
      {
        EList<Port> _ports_1 = component.getWrappedComponent().getType().getPorts();
        for(final Port port_1 : _ports_1) {
          _builder.append("\t");
          _builder.append("private ");
          String _firstUpper_1 = StringExtensions.toFirstUpper(port_1.getName());
          _builder.append(_firstUpper_1, "\t");
          _builder.append(" ");
          String _firstLower_1 = StringExtensions.toFirstLower(port_1.getName());
          _builder.append(_firstLower_1, "\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        boolean _isEmpty = component.getClocks().isEmpty();
        boolean _not = (!_isEmpty);
        if (_not) {
          _builder.append("\t");
          _builder.append("// Clocks");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("private ");
          _builder.append(Namings.YAKINDU_TIMER_INTERFACE, "\t");
          _builder.append(" timerService;");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        EList<Clock> _clocks = component.getClocks();
        for(final Clock clock : _clocks) {
          _builder.append("\t");
          _builder.append("private final int ");
          String _name = clock.getName();
          _builder.append(_name, "\t");
          _builder.append(" = ");
          int _plusPlus = clockId++;
          _builder.append(_plusPlus, "\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("// Main queue");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("private LinkedBlockingMultiQueue<String, Event> __asyncQueue = new LinkedBlockingMultiQueue<String, Event>();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("// Subqueues");
      _builder.newLine();
      {
        EList<MessageQueue> _messageQueues = component.getMessageQueues();
        for(final MessageQueue queue : _messageQueues) {
          _builder.append("\t");
          _builder.append("private LinkedBlockingMultiQueue<String, Event>.SubQueue ");
          String _name_1 = queue.getName();
          _builder.append(_name_1, "\t");
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
      {
        boolean _needTimer = this.timingDeterminer.needTimer(component);
        if (_needTimer) {
          _builder.append("\t");
          _builder.append("public ");
          String _generateComponentClassName_2 = this.nameGenerator.generateComponentClassName(component);
          _builder.append(_generateComponentClassName_2, "\t");
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
              Class<? extends Type> _class = parameter.getType().getClass();
              _builder.append(_class, "\t");
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
          CharSequence _createInstances = this.createInstances(component);
          _builder.append(_createInstances, "\t\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("setTimer(timer);");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("// Init is done in setTimer");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public boolean isEventQueueEmpty(){");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return __asyncQueue.isEmpty();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public ");
      String _generateComponentClassName_3 = this.nameGenerator.generateComponentClassName(component);
      _builder.append(_generateComponentClassName_3, "\t");
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
          String _name_3 = parameter_1.getName();
          _builder.append(_name_3, "\t");
        }
      }
      _builder.append(") {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      CharSequence _createInstances_1 = this.createInstances(component);
      _builder.append(_createInstances_1, "\t\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      {
        boolean _isEmpty_1 = component.getClocks().isEmpty();
        boolean _not_1 = (!_isEmpty_1);
        if (_not_1) {
          _builder.append("this.timerService = new TimerService();");
        }
      }
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("init();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/** Resets the wrapped component. Must be called to initialize the component. */");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void reset() {");
      _builder.newLine();
      _builder.append("\t\t");
      String _generateWrappedComponentName_1 = this.nameGenerator.generateWrappedComponentName(component);
      _builder.append(_generateWrappedComponentName_1, "\t\t");
      _builder.append(".reset();");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/** Creates the subqueues, clocks and enters the wrapped synchronous component. */");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("private void init() {");
      _builder.newLine();
      _builder.append("\t\t");
      String _generateWrappedComponentName_2 = this.nameGenerator.generateWrappedComponentName(component);
      _builder.append(_generateWrappedComponentName_2, "\t\t");
      _builder.append(" = new ");
      String _generateComponentClassName_4 = this.nameGenerator.generateComponentClassName(component.getWrappedComponent().getType());
      _builder.append(_generateComponentClassName_4, "\t\t");
      _builder.append("(");
      {
        EList<Expression> _arguments = component.getWrappedComponent().getArguments();
        boolean _hasElements_2 = false;
        for(final Expression argument : _arguments) {
          if (!_hasElements_2) {
            _hasElements_2 = true;
          } else {
            _builder.appendImmediate(", ", "\t\t");
          }
          String _serialize = this.expressionSerializer.serialize(argument);
          _builder.append(_serialize, "\t\t");
        }
      }
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("// Creating subqueues: the negative conversion regarding priorities is needed,");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("// because the lbmq marks higher priority with lower integer values");
      _builder.newLine();
      {
        final Comparator<MessageQueue> _function = (MessageQueue a, MessageQueue b) -> {
          int _compareTo = a.getPriority().compareTo(b.getPriority());
          return ((-1) * _compareTo);
        };
        List<MessageQueue> _sortWith = IterableExtensions.<MessageQueue>sortWith(component.getMessageQueues(), _function);
        for(final MessageQueue queue_1 : _sortWith) {
          _builder.append("\t\t");
          _builder.append("__asyncQueue.addSubQueue(\"");
          String _name_4 = queue_1.getName();
          _builder.append(_name_4, "\t\t");
          _builder.append("\", -(");
          BigInteger _priority = queue_1.getPriority();
          _builder.append(_priority, "\t\t");
          _builder.append("), (int) ");
          String _serialize_1 = this.expressionSerializer.serialize(queue_1.getCapacity());
          _builder.append(_serialize_1, "\t\t");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          String _name_5 = queue_1.getName();
          _builder.append(_name_5, "\t\t");
          _builder.append(" = __asyncQueue.getSubQueue(\"");
          String _name_6 = queue_1.getName();
          _builder.append(_name_6, "\t\t");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t\t");
      {
        boolean _isEmpty_2 = component.getClocks().isEmpty();
        boolean _not_2 = (!_isEmpty_2);
        if (_not_2) {
          _builder.append("// Creating clock callbacks for the single timer service");
        }
      }
      _builder.newLineIfNotEmpty();
      {
        Collection<QueuesOfClocks.Match> _allMatches = QueuesOfClocks.Matcher.on(this.trace.getEngine()).getAllMatches(component, null, null);
        for(final QueuesOfClocks.Match match : _allMatches) {
          _builder.append("\t\t");
          _builder.append("timerService.setTimer(createTimerCallback(), ");
          String _name_7 = match.getClock().getName();
          _builder.append(_name_7, "\t\t");
          _builder.append(", ");
          String _valueInMs = this.getValueInMs(match.getClock().getTimeSpecification());
          _builder.append(_valueInMs, "\t\t");
          _builder.append(", true);");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t\t");
      _builder.append("// The thread has to be started manually");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      {
        boolean _isEmpty_3 = component.getClocks().isEmpty();
        boolean _not_3 = (!_isEmpty_3);
        if (_not_3) {
          _builder.append("\t");
          _builder.append("private ");
          _builder.append(Namings.TIMER_CALLBACK_INTERFACE, "\t");
          _builder.append(" createTimerCallback() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("return new ");
          _builder.append(Namings.TIMER_CALLBACK_INTERFACE, "\t\t");
          _builder.append("() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("@Override");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("public void timeElapsed(int eventId) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t\t\t");
          _builder.append("switch (eventId) {");
          _builder.newLine();
          {
            Collection<QueuesOfClocks.Match> _allMatches_1 = QueuesOfClocks.Matcher.on(this.trace.getEngine()).getAllMatches(component, null, null);
            for(final QueuesOfClocks.Match match_1 : _allMatches_1) {
              _builder.append("\t");
              _builder.append("\t\t\t\t");
              _builder.append("case ");
              String _name_8 = match_1.getClock().getName();
              _builder.append(_name_8, "\t\t\t\t\t");
              _builder.append(":");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t\t\t\t");
              _builder.append("\t");
              String _name_9 = match_1.getQueue().getName();
              _builder.append(_name_9, "\t\t\t\t\t\t");
              _builder.append(".offer(new Event(\"");
              String _name_10 = match_1.getClock().getName();
              _builder.append(_name_10, "\t\t\t\t\t\t");
              _builder.append("\"));");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t\t\t\t");
              _builder.append("break;");
              _builder.newLine();
            }
          }
          _builder.append("\t");
          _builder.append("\t\t\t\t");
          _builder.append("default:");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t\t\t\t\t");
          _builder.append("throw new IllegalArgumentException(\"No such event id: \" + eventId);");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t\t\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("};");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("// Inner classes representing control ports");
      _builder.newLine();
      {
        EList<Port> _ports_2 = component.getPorts();
        boolean _hasElements_3 = false;
        for(final Port port_2 : _ports_2) {
          if (!_hasElements_3) {
            _hasElements_3 = true;
          } else {
            _builder.appendImmediate("\n", "\t");
          }
          _builder.append("\t");
          _builder.append("public class ");
          String _firstUpper_2 = StringExtensions.toFirstUpper(port_2.getName());
          _builder.append(_firstUpper_2, "\t");
          _builder.append(" implements ");
          String _implementationName = Namings.getImplementationName(port_2.getInterfaceRealization().getInterface());
          _builder.append(_implementationName, "\t");
          _builder.append(".");
          String _firstUpper_3 = StringExtensions.toFirstUpper(port_2.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
          _builder.append(_firstUpper_3, "\t");
          _builder.append(" {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          CharSequence _delegateWrapperRaisingMethods = this.delegateWrapperRaisingMethods(port_2);
          _builder.append(_delegateWrapperRaisingMethods, "\t\t");
          _builder.append(" ");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          CharSequence _delegateWrapperControlOutMethods = this.delegateWrapperControlOutMethods(port_2);
          _builder.append(_delegateWrapperControlOutMethods, "\t\t");
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
          String _implementationName_1 = Namings.getImplementationName(port_2.getInterfaceRealization().getInterface());
          _builder.append(_implementationName_1, "\t\t");
          _builder.append(".Listener.");
          String _firstUpper_4 = StringExtensions.toFirstUpper(port_2.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
          _builder.append(_firstUpper_4, "\t\t");
          _builder.append(" listener) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("// No operation as out event are not interpreted in case of control ports");
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
          String _implementationName_2 = Namings.getImplementationName(port_2.getInterfaceRealization().getInterface());
          _builder.append(_implementationName_2, "\t\t");
          _builder.append(".Listener.");
          String _firstUpper_5 = StringExtensions.toFirstUpper(port_2.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
          _builder.append(_firstUpper_5, "\t\t");
          _builder.append("> getRegisteredListeners() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("// Empty list as out event are not interpreted in case of control ports");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("return Collections.emptyList();");
          _builder.newLine();
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
          String _firstUpper_6 = StringExtensions.toFirstUpper(port_2.getName());
          _builder.append(_firstUpper_6, "\t");
          _builder.append(" get");
          String _firstUpper_7 = StringExtensions.toFirstUpper(port_2.getName());
          _builder.append(_firstUpper_7, "\t");
          _builder.append("() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("return ");
          String _firstLower_2 = StringExtensions.toFirstLower(port_2.getName());
          _builder.append(_firstLower_2, "\t\t");
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
      _builder.append("// Inner classes representing wrapped ports");
      _builder.newLine();
      {
        EList<Port> _ports_3 = component.getWrappedComponent().getType().getPorts();
        boolean _hasElements_4 = false;
        for(final Port port_3 : _ports_3) {
          if (!_hasElements_4) {
            _hasElements_4 = true;
          } else {
            _builder.appendImmediate("\n", "\t");
          }
          _builder.append("\t");
          _builder.append("public class ");
          String _firstUpper_8 = StringExtensions.toFirstUpper(port_3.getName());
          _builder.append(_firstUpper_8, "\t");
          _builder.append(" implements ");
          String _implementationName_3 = Namings.getImplementationName(port_3.getInterfaceRealization().getInterface());
          _builder.append(_implementationName_3, "\t");
          _builder.append(".");
          String _firstUpper_9 = StringExtensions.toFirstUpper(port_3.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
          _builder.append(_firstUpper_9, "\t");
          _builder.append(" {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          CharSequence _delegateWrapperRaisingMethods_1 = this.delegateWrapperRaisingMethods(port_3);
          _builder.append(_delegateWrapperRaisingMethods_1, "\t\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          CharSequence _delegateWrapperOutMethods = this.delegateWrapperOutMethods(port_3, this.nameGenerator.generateWrappedComponentName(component));
          _builder.append(_delegateWrapperOutMethods, "\t\t");
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
          String _implementationName_4 = Namings.getImplementationName(port_3.getInterfaceRealization().getInterface());
          _builder.append(_implementationName_4, "\t\t");
          _builder.append(".Listener.");
          String _firstUpper_10 = StringExtensions.toFirstUpper(port_3.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
          _builder.append(_firstUpper_10, "\t\t");
          _builder.append(" listener) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t\t");
          String _generateWrappedComponentName_3 = this.nameGenerator.generateWrappedComponentName(component);
          _builder.append(_generateWrappedComponentName_3, "\t\t\t");
          _builder.append(".get");
          String _firstUpper_11 = StringExtensions.toFirstUpper(port_3.getName());
          _builder.append(_firstUpper_11, "\t\t\t");
          _builder.append("().registerListener(listener);");
          _builder.newLineIfNotEmpty();
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
          String _implementationName_5 = Namings.getImplementationName(port_3.getInterfaceRealization().getInterface());
          _builder.append(_implementationName_5, "\t\t");
          _builder.append(".Listener.");
          String _firstUpper_12 = StringExtensions.toFirstUpper(port_3.getInterfaceRealization().getRealizationMode().toString().toLowerCase());
          _builder.append(_firstUpper_12, "\t\t");
          _builder.append("> getRegisteredListeners() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("return ");
          String _generateWrappedComponentName_4 = this.nameGenerator.generateWrappedComponentName(component);
          _builder.append(_generateWrappedComponentName_4, "\t\t\t");
          _builder.append(".get");
          String _firstUpper_13 = StringExtensions.toFirstUpper(port_3.getName());
          _builder.append(_firstUpper_13, "\t\t\t");
          _builder.append("().getRegisteredListeners();");
          _builder.newLineIfNotEmpty();
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
          String _firstUpper_14 = StringExtensions.toFirstUpper(port_3.getName());
          _builder.append(_firstUpper_14, "\t");
          _builder.append(" get");
          String _firstUpper_15 = StringExtensions.toFirstUpper(port_3.getName());
          _builder.append(_firstUpper_15, "\t");
          _builder.append("() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("return ");
          String _firstLower_3 = StringExtensions.toFirstLower(port_3.getName());
          _builder.append(_firstLower_3, "\t\t");
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
      _builder.append("/** Manual scheduling. */");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void schedule() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append(Namings.GAMMA_EVENT_CLASS, "\t\t");
      _builder.append(" ");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t\t");
      _builder.append(" = __asyncQueue.poll();");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("if (");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t\t");
      _builder.append(" == null) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      _builder.append("// There was no event in the queue");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("return;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("processEvent(");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t\t");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/** Operation. */");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void run() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("while (!Thread.currentThread().isInterrupted()) {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("try {");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append(Namings.GAMMA_EVENT_CLASS, "\t\t\t\t");
      _builder.append(" ");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t\t\t\t");
      _builder.append(" = __asyncQueue.take();\t\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("processEvent(");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t\t\t\t");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      _builder.append("} catch (InterruptedException e) {");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("thread.interrupt();");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("private void processEvent(");
      _builder.append(Namings.GAMMA_EVENT_CLASS, "\t");
      _builder.append(" ");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t");
      _builder.append(") {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("if (!isControlEvent(");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t\t");
      _builder.append(")) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      _builder.append("// Event is forwarded to the wrapped component");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("forwardEvent(");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t\t\t");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("performControlActions(");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t\t");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("private boolean isControlEvent(");
      _builder.append(Namings.GAMMA_EVENT_CLASS, "\t");
      _builder.append(" ");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t");
      _builder.append(") {");
      _builder.newLineIfNotEmpty();
      {
        if ((component.getPorts().isEmpty() && component.getClocks().isEmpty())) {
          _builder.append("\t\t");
          _builder.append("return false;");
          _builder.newLine();
        } else {
          _builder.append("\t\t");
          _builder.append("String portName = ");
          _builder.append(this.EVENT_INSTANCE_NAME, "\t\t");
          _builder.append(".getEvent().split(\"\\\\.\")[0];");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("return ");
          {
            EList<Port> _ports_4 = component.getPorts();
            boolean _hasElements_5 = false;
            for(final Port port_4 : _ports_4) {
              if (!_hasElements_5) {
                _hasElements_5 = true;
              } else {
                _builder.appendImmediate(" || ", "\t\t");
              }
              _builder.append("portName.equals(\"");
              String _name_11 = port_4.getName();
              _builder.append(_name_11, "\t\t");
              _builder.append("\")");
            }
          }
          {
            if (((!component.getPorts().isEmpty()) && (!component.getClocks().isEmpty()))) {
              _builder.append(" || ");
            }
          }
          {
            EList<Clock> _clocks_1 = component.getClocks();
            boolean _hasElements_6 = false;
            for(final Clock clock_1 : _clocks_1) {
              if (!_hasElements_6) {
                _hasElements_6 = true;
              } else {
                _builder.appendImmediate(" || ", "\t\t");
              }
              _builder.append("portName.equals(\"");
              String _name_12 = clock_1.getName();
              _builder.append(_name_12, "\t\t");
              _builder.append("\")");
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
      _builder.append("private void forwardEvent(");
      _builder.append(Namings.GAMMA_EVENT_CLASS, "\t");
      _builder.append(" ");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t");
      _builder.append(") {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("switch (");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t\t");
      _builder.append(".getEvent()) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      CharSequence _generateWrapperEventHandlers = this.generateWrapperEventHandlers(component);
      _builder.append(_generateWrapperEventHandlers, "\t\t\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      _builder.append("default:");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("throw new IllegalArgumentException(\"No such event!\");");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("private void performControlActions(");
      _builder.append(Namings.GAMMA_EVENT_CLASS, "\t");
      _builder.append(" ");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t");
      _builder.append(") {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("String[] eventName = ");
      _builder.append(this.EVENT_INSTANCE_NAME, "\t\t");
      _builder.append(".getEvent().split(\"\\\\.\");");
      _builder.newLineIfNotEmpty();
      {
        EList<ControlSpecification> _controlSpecifications = component.getControlSpecifications();
        for(final ControlSpecification controlSpecification : _controlSpecifications) {
          {
            SimpleTrigger _trigger = controlSpecification.getTrigger();
            if ((_trigger instanceof AnyTrigger)) {
              _builder.append("\t\t");
              _builder.append("// Any trigger");
              _builder.newLine();
              _builder.append("\t\t");
              CharSequence _generateRunCycle = this.generateRunCycle(controlSpecification.getControlFunction(), this.nameGenerator.generateWrappedComponentName(component));
              _builder.append(_generateRunCycle, "\t\t");
              _builder.newLineIfNotEmpty();
              _builder.append("\t\t");
              _builder.append("return;");
              _builder.newLine();
            } else {
              {
                Collection<AnyPortTriggersOfWrappers.Match> _allMatches_2 = AnyPortTriggersOfWrappers.Matcher.on(this.trace.getEngine()).getAllMatches(component, controlSpecification, null, null);
                for(final AnyPortTriggersOfWrappers.Match match_2 : _allMatches_2) {
                  _builder.append("\t\t");
                  _builder.append("// Port trigger");
                  _builder.newLine();
                  _builder.append("\t\t");
                  _builder.append("if (eventName.length == 2 && eventName[0].equals(\"");
                  String _name_13 = match_2.getPort().getName();
                  _builder.append(_name_13, "\t\t");
                  _builder.append("\")) {");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t\t");
                  _builder.append("\t");
                  CharSequence _generateRunCycle_1 = this.generateRunCycle(match_2.getControlFunction(), this.nameGenerator.generateWrappedComponentName(component));
                  _builder.append(_generateRunCycle_1, "\t\t\t");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t\t");
                  _builder.append("\t");
                  _builder.append("return;");
                  _builder.newLine();
                  _builder.append("\t\t");
                  _builder.append("}");
                  _builder.newLine();
                }
              }
              {
                Collection<PortEventTriggersOfWrappers.Match> _allMatches_3 = PortEventTriggersOfWrappers.Matcher.on(this.trace.getEngine()).getAllMatches(component, controlSpecification, null, null, null);
                for(final PortEventTriggersOfWrappers.Match match_3 : _allMatches_3) {
                  _builder.append("\t\t");
                  _builder.append("// Port event trigger");
                  _builder.newLine();
                  _builder.append("\t\t");
                  _builder.append("if (eventName.length == 2 && eventName[0].equals(\"");
                  String _name_14 = match_3.getPort().getName();
                  _builder.append(_name_14, "\t\t");
                  _builder.append("\") && eventName[1].equals(\"");
                  String _name_15 = match_3.getEvent().getName();
                  _builder.append(_name_15, "\t\t");
                  _builder.append("\")) {");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t\t");
                  _builder.append("\t");
                  CharSequence _generateRunCycle_2 = this.generateRunCycle(match_3.getControlFunction(), this.nameGenerator.generateWrappedComponentName(component));
                  _builder.append(_generateRunCycle_2, "\t\t\t");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t\t");
                  _builder.append("\t");
                  _builder.append("return;");
                  _builder.newLine();
                  _builder.append("\t\t");
                  _builder.append("}");
                  _builder.newLine();
                }
              }
              {
                Collection<ClockTriggersOfWrappers.Match> _allMatches_4 = ClockTriggersOfWrappers.Matcher.on(this.trace.getEngine()).getAllMatches(component, controlSpecification, null, null);
                for(final ClockTriggersOfWrappers.Match match_4 : _allMatches_4) {
                  _builder.append("\t\t");
                  _builder.append("// Clock trigger");
                  _builder.newLine();
                  _builder.append("\t\t");
                  _builder.append("if (eventName.length == 1 && eventName[0].equals(\"");
                  String _name_16 = match_4.getClock().getName();
                  _builder.append(_name_16, "\t\t");
                  _builder.append("\")) {");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t\t");
                  _builder.append("\t");
                  CharSequence _generateRunCycle_3 = this.generateRunCycle(match_4.getControlFunction(), this.nameGenerator.generateWrappedComponentName(component));
                  _builder.append(_generateRunCycle_3, "\t\t\t");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t\t");
                  _builder.append("\t");
                  _builder.append("return;");
                  _builder.newLine();
                  _builder.append("\t\t");
                  _builder.append("}");
                  _builder.newLine();
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
      _builder.append("/** Starts this wrapper instance on a thread. */");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void start() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("thread = new Thread(this);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("thread.start();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public boolean isWaiting() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return thread.getState() == Thread.State.WAITING;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/** Stops the thread running this wrapper instance. */");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void interrupt() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("thread.interrupt();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public ");
      String _generateComponentClassName_5 = this.nameGenerator.generateComponentClassName(component.getWrappedComponent().getType());
      _builder.append(_generateComponentClassName_5, "\t");
      _builder.append(" get");
      String _firstUpper_16 = StringExtensions.toFirstUpper(this.nameGenerator.generateWrappedComponentName(component));
      _builder.append(_firstUpper_16, "\t");
      _builder.append("() {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("return ");
      String _generateWrappedComponentName_5 = this.nameGenerator.generateWrappedComponentName(component);
      _builder.append(_generateWrappedComponentName_5, "\t\t");
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
          _builder.append("public void setTimer(");
          _builder.append(Namings.UNIFIED_TIMER_INTERFACE, "\t");
          _builder.append(" timer) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          {
            boolean _isEmpty_4 = component.getClocks().isEmpty();
            boolean _not_4 = (!_isEmpty_4);
            if (_not_4) {
              _builder.append("timerService = timer;");
            }
          }
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          {
            boolean _needTimer_2 = this.timingDeterminer.needTimer(component.getWrappedComponent().getType());
            if (_needTimer_2) {
              String _generateWrappedComponentName_6 = this.nameGenerator.generateWrappedComponentName(component);
              _builder.append(_generateWrappedComponentName_6, "\t\t");
              _builder.append(".setTimer(timer);");
            }
          }
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("init(); // To set the service into functioning state with clocks (so that \"after 1 s\" works with new timer as well)");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
}
