package hu.bme.mit.gamma.codegenerator.java

import hu.bme.mit.gamma.codegenerator.java.AsynchronousAdapterCodeGenerator
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter

import static extension hu.bme.mit.gamma.codegenerator.java.util.Namings.*
import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
import hu.bme.mit.gamma.codegenerator.java.queries.QueuesOfClocks
import hu.bme.mit.gamma.codegenerator.java.queries.AnyPortTriggersOfWrappers
import hu.bme.mit.gamma.codegenerator.java.queries.PortEventTriggersOfWrappers
import hu.bme.mit.gamma.codegenerator.java.queries.ClockTriggersOfWrappers
import hu.bme.mit.gamma.statechart.interface_.AnyTrigger

class EnvironmentAsynchronousAdapterCodeGenerator extends AsynchronousAdapterCodeGenerator {
	
	new(String packageName, Trace trace) {
		super(packageName, trace)
	}
	
	
	/**
	* Creates the Java code of the synchronous composite class, containing the statemachine instances.
	*/
	override createAsynchronousAdapterClass(AsynchronousAdapter component) {
		var clockId = 0
	'''
		package «component.generateComponentPackageName»;
		
		«component.generateWrapperImports»
		
		public class «component.generateComponentClassName» implements Runnable, «component.generatePortOwnerInterfaceName» {			
			// Thread running this wrapper instance
			private Thread thread;
			// Wrapped synchronous instance
			private «component.wrappedComponent.type.generateComponentClassName» «component.generateWrappedComponentName»;
			// Control port instances
			«FOR port : component.ports»
				private «port.name.toFirstUpper» «port.name.toFirstLower»;
			«ENDFOR»
			// Wrapped port instances
			«FOR port : component.wrappedComponent.type.ports»
				private «port.name.toFirstUpper» «port.name.toFirstLower»;
			«ENDFOR»
			«IF !component.clocks.empty»
				// Clocks
				private «YAKINDU_TIMER_INTERFACE» timerService;
			«ENDIF»
			«FOR clock : component.clocks»
				private final int «clock.name» = «clockId++»;
			«ENDFOR»
			// Main queue
			private LinkedBlockingMultiQueue<String, Event> __asyncQueue = new LinkedBlockingMultiQueue<String, Event>();
			// Subqueues
			«FOR queue : component.messageQueues»
				private LinkedBlockingMultiQueue<String, Event>.SubQueue «queue.name»;
			«ENDFOR»
			«component.generateParameterDeclarationFields»
			
			«IF component.needTimer»
				public «component.generateComponentClassName»(«FOR parameter : component.parameterDeclarations SEPARATOR ", " AFTER ", "»«parameter.type.class» «parameter.name»«ENDFOR»«UNIFIED_TIMER_INTERFACE» timer) {
					«component.createInstances»
					setTimer(timer);
					// Init is done in setTimer
				}
			«ENDIF»
			
			public boolean isEmpty(){
				return __asyncQueue.isEmpty();
			}
			
			public «component.generateComponentClassName»(«FOR parameter : component.parameterDeclarations SEPARATOR ", "»«parameter.name»«ENDFOR») {
				«component.createInstances»
				«IF !component.clocks.empty»this.timerService = new TimerService();«ENDIF»
				init();
			}
			
			/** Resets the wrapped component. Must be called to initialize the component. */
			@Override
			public void reset() {
				«component.generateWrappedComponentName».reset();
			}
			
			/** Creates the subqueues, clocks and enters the wrapped synchronous component. */
			private void init() {
				«component.generateWrappedComponentName» = new «component.wrappedComponent.type.generateComponentClassName»(«FOR argument : component.wrappedComponent.arguments SEPARATOR ", "»«argument.serialize»«ENDFOR»);
				// Creating subqueues: the negative conversion regarding priorities is needed,
				// because the lbmq marks higher priority with lower integer values
				«FOR queue : component.messageQueues.sortWith(a, b | -1 * (a.priority.compareTo(b.priority)))»
					__asyncQueue.addSubQueue("«queue.name»", -(«queue.priority»), (int) «queue.capacity.serialize»);
					«queue.name» = __asyncQueue.getSubQueue("«queue.name»");
				«ENDFOR»
				«IF !component.clocks.empty»// Creating clock callbacks for the single timer service«ENDIF»
				«FOR match : QueuesOfClocks.Matcher.on(engine).getAllMatches(component, null, null)»
					 timerService.setTimer(createTimerCallback(), «match.clock.name», «match.clock.timeSpecification.valueInMs», true);
				«ENDFOR»
				// The thread has to be started manually
			}
			
			«IF !component.clocks.empty»
				private «TIMER_CALLBACK_INTERFACE» createTimerCallback() {
					return new «TIMER_CALLBACK_INTERFACE»() {
						@Override
						public void timeElapsed(int eventId) {
							switch (eventId) {
								«FOR match : QueuesOfClocks.Matcher.on(engine).getAllMatches(component, null, null)»
									case «match.clock.name»:
										«match.queue.name».offer(new Event("«match.clock.name»"));
									break;
								«ENDFOR»
								default:
									throw new IllegalArgumentException("No such event id: " + eventId);
							}
						}
					};
				}
			«ENDIF»
			
			// Inner classes representing control ports
			«FOR port : component.ports SEPARATOR "\n"»
				public class «port.name.toFirstUpper» implements «port.interfaceRealization.interface.implementationName».«port.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper» {
					
					«port.delegateWrapperRaisingMethods» 
					
					«port.delegateWrapperControlOutMethods»
					
					@Override
					public void registerListener(«port.interfaceRealization.interface.implementationName».Listener.«port.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper» listener) {
						// No operation as out event are not interpreted in case of control ports
					}
					
					@Override
					public List<«port.interfaceRealization.interface.implementationName».Listener.«port.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper»> getRegisteredListeners() {
						// Empty list as out event are not interpreted in case of control ports
						return Collections.emptyList();
					}
					
				}
				
				@Override
				public «port.name.toFirstUpper» get«port.name.toFirstUpper»() {
					return «port.name.toFirstLower»;
				}
			«ENDFOR»
			
			// Inner classes representing wrapped ports
			«FOR port : component.wrappedComponent.type.ports SEPARATOR "\n"»
				public class «port.name.toFirstUpper» implements «port.interfaceRealization.interface.implementationName».«port.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper» {
					
					«port.delegateWrapperRaisingMethods»
					
					«port.delegateWrapperOutMethods(component.generateWrappedComponentName)»
					
					@Override
					public void registerListener(«port.interfaceRealization.interface.implementationName».Listener.«port.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper» listener) {
						«component.generateWrappedComponentName».get«port.name.toFirstUpper»().registerListener(listener);
					}
					
					@Override
					public List<«port.interfaceRealization.interface.implementationName».Listener.«port.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper»> getRegisteredListeners() {
						return «component.generateWrappedComponentName».get«port.name.toFirstUpper»().getRegisteredListeners();
					}
					
				}
				
				@Override
				public «port.name.toFirstUpper» get«port.name.toFirstUpper»() {
					return «port.name.toFirstLower»;
				}
			«ENDFOR»
			
			/** Manual scheduling. */
			public void schedule() {
				«GAMMA_EVENT_CLASS» «EVENT_INSTANCE_NAME» = __asyncQueue.poll();
				if («EVENT_INSTANCE_NAME» == null) {
					// There was no event in the queue
					return;
				}
				processEvent(«EVENT_INSTANCE_NAME»);
			}
			
			/** Operation. */
			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					try {
						«GAMMA_EVENT_CLASS» «EVENT_INSTANCE_NAME» = __asyncQueue.take();		
						processEvent(«EVENT_INSTANCE_NAME»);
					} catch (InterruptedException e) {
						thread.interrupt();
					}
				}
			}
			
			private void processEvent(«GAMMA_EVENT_CLASS» «EVENT_INSTANCE_NAME») {
				if (!isControlEvent(«EVENT_INSTANCE_NAME»)) {
					// Event is forwarded to the wrapped component
					forwardEvent(«EVENT_INSTANCE_NAME»);
				}
				performControlActions(«EVENT_INSTANCE_NAME»);
			}
			
			private boolean isControlEvent(«GAMMA_EVENT_CLASS» «EVENT_INSTANCE_NAME») {
				«IF component.ports.empty && component.clocks.empty»
					return false;
				«ELSE»
					String portName = «EVENT_INSTANCE_NAME».getEvent().split("\\.")[0];
					return «FOR port : component.ports SEPARATOR " || "»portName.equals("«port.name»")«ENDFOR»«IF !component.ports.empty && !component.clocks.empty» || «ENDIF»«FOR clock : component.clocks SEPARATOR " || "»portName.equals("«clock.name»")«ENDFOR»;
				«ENDIF»
			}
			
			private void forwardEvent(«GAMMA_EVENT_CLASS» «EVENT_INSTANCE_NAME») {
				switch («EVENT_INSTANCE_NAME».getEvent()) {
					«component.generateWrapperEventHandlers()»
					default:
						throw new IllegalArgumentException("No such event!");
				}
			}
			
			private void performControlActions(«GAMMA_EVENT_CLASS» «EVENT_INSTANCE_NAME») {
				String[] eventName = «EVENT_INSTANCE_NAME».getEvent().split("\\.");
				«FOR controlSpecification : component.controlSpecifications»
					«IF controlSpecification.trigger instanceof AnyTrigger»
						// Any trigger
						«controlSpecification.controlFunction.generateRunCycle(component.generateWrappedComponentName)»
						return;
					«ELSE»
						«FOR match : AnyPortTriggersOfWrappers.Matcher.on(engine).getAllMatches(component, controlSpecification, null, null)»
							// Port trigger
							if (eventName.length == 2 && eventName[0].equals("«match.port.name»")) {
								«match.controlFunction.generateRunCycle(component.generateWrappedComponentName)»
								return;
							}
						«ENDFOR»
						«FOR match : PortEventTriggersOfWrappers.Matcher.on(engine).getAllMatches(component, controlSpecification, null, null, null)»
							// Port event trigger
							if (eventName.length == 2 && eventName[0].equals("«match.port.name»") && eventName[1].equals("«match.event.name»")) {
								«match.controlFunction.generateRunCycle(component.generateWrappedComponentName)»
								return;
							}
						«ENDFOR»
						«FOR match : ClockTriggersOfWrappers.Matcher.on(engine).getAllMatches(component, controlSpecification, null, null)»
							// Clock trigger
							if (eventName.length == 1 && eventName[0].equals("«match.clock.name»")) {
								«match.controlFunction.generateRunCycle(component.generateWrappedComponentName)»
								return;
							}
						«ENDFOR»
					«ENDIF»
				«ENDFOR»
			}
			
			/** Starts this wrapper instance on a thread. */
			@Override
			public void start() {
				thread = new Thread(this);
				thread.start();
			}
			
			public boolean isWaiting() {
				return thread.getState() == Thread.State.WAITING;
			}
			
			/** Stops the thread running this wrapper instance. */
			public void interrupt() {
				thread.interrupt();
			}
			
			public «component.wrappedComponent.type.generateComponentClassName» get«component.generateWrappedComponentName.toFirstUpper»() {
				return «component.generateWrappedComponentName»;
			}
			
			«IF component.needTimer»
				public void setTimer(«UNIFIED_TIMER_INTERFACE» timer) {
					«IF !component.clocks.empty»timerService = timer;«ENDIF»
					«IF component.wrappedComponent.type.needTimer»«component.generateWrappedComponentName».setTimer(timer);«ENDIF»
					init(); // To set the service into functioning state with clocks (so that "after 1 s" works with new timer as well)
				}
			«ENDIF»
			
		}
		'''
	}
	
	
}