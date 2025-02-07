/********************************************************************************
 * Copyright (c) 2018-2024 Contributors to the Gamma project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * SPDX-License-Identifier: EPL-1.0
 ********************************************************************************/
package hu.bme.mit.gamma.codegeneration.java

import hu.bme.mit.gamma.codegeneration.java.queries.BroadcastChannels
import hu.bme.mit.gamma.codegeneration.java.queries.SimpleChannels
import static extension hu.bme.mit.gamma.codegeneration.java.util.Namings.*
import hu.bme.mit.gamma.statechart.composite.AbstractSynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent
import hu.bme.mit.gamma.statechart.composite.SynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.interface_.EventDeclaration
import hu.bme.mit.gamma.statechart.interface_.EventDirection
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition
import java.util.Collections
import hu.bme.mit.gamma.codegenerator.*
import hu.bme.mit.gamma.codegenerator.utils.*
import static extension hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures.*
import static extension hu.bme.mit.gamma.statechart.util.StatechartUtil.*
import static extension hu.bme.mit.gamma.codegeneration.java.TypeTransformer.*
import hu.bme.mit.gamma.codegeneration.java.util.Namings
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent
import hu.bme.mit.gamma.codegeneration.java.SynchronousCompositeComponentCodeGenerator
import hu.bme.mit.gamma.codegeneration.java.Trace
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine
import hu.bme.mit.gamma.codegeneration.java.util.ElementaryEnvironmentComponentUtility

class EnvironmentSynchronousCompositeComponentCodeGenerator extends SynchronousCompositeComponentCodeGenerator{
	
	
	ViatraQueryEngine engine;

	protected val envUtil = ElementaryEnvironmentComponentUtility.INSTANCE
	protected val expSerializer = ExpressionSerializer.INSTANCE
	

	new(String packageName, String yakinduPackageName, Trace trace) {
		super(packageName,yakinduPackageName,trace)
		engine=trace.getEngine
	}
	
	
	
	
	
	/**
	* Creates the Java code of the synchronous composite class, containing the statemachine instances.
	*/
	
	dispatch protected def generateEnvironmentInports(EnvironmentCascadeCompositeComponent component){
		
		'''
		«var types=component.environmentComponents
			.filter[c|c instanceof EnvironmentSynchronousCompositeComponentInstance]
				.map[c|c as EnvironmentSynchronousCompositeComponentInstance]
					.map[c|c.type]»
		«FOR t : types»
			import «PACKAGE_NAME».«t.name.toLowerCase».*;
		«ENDFOR»
		'''
	}
	
	dispatch protected def generateEnvironmentInports(EnvironmentSynchronousCompositeComponent component){
		'''
		«var types=component.environmentComponents
			.filter[c|c instanceof EnvironmentSynchronousCompositeComponentInstance]
				.map[c|c as EnvironmentSynchronousCompositeComponentInstance]
					.map[c|c.type]»
		«FOR t : types»
			import «PACKAGE_NAME».«t.name.toLowerCase».*;
		«ENDFOR»
		'''
	}
		
	dispatch protected def generateEnvironmentInports(CascadeCompositeComponent component){
		" "
		/*'''
		«var types=component.components
			.filter[c|c instanceof SynchronousCompositeComponentInstance]
				.map[c|c as SynchronousCompositeComponentInstance]
					.map[c|c.type]»
		«FOR t : types»
			import «PACKAGE_NAME».«t.name.toLowerCase».*;
		«ENDFOR»
		'''*/
	}
	
	dispatch protected def generateEnvironmentInports(SynchronousCompositeComponent component){
		" "
		/*'''
		«var types=component.components
			.filter[c|c instanceof SynchronousCompositeComponentInstance]
				.map[c|c as SynchronousCompositeComponentInstance]
					.map[c|c.type]»
		«FOR t : types»
			import «PACKAGE_NAME».«t.name.toLowerCase».*;
		«ENDFOR»
		'''*/
	}
	
	protected def createEnvironmentCompositeComponentClass(AbstractSynchronousCompositeComponent component) '''
		package «component.generateComponentPackageName»;
		
		«component.generateCompositeSystemImports»
		
		«component.generateEnvironmentInports»
		
		«envUtil.getScheduingInterfaceImport(PACKAGE_NAME)»
		
		public class «component.generateComponentClassName» implements «component.generatePortOwnerInterfaceName» {
			// Component instances
			«FOR instance : component.components»
				private «instance.type.generateComponentClassName» «instance.name»;
			«ENDFOR»
			«IF component instanceof EnvironmentSynchronousCompositeComponent»
				// Environmental Component instances
				«FOR instance : component.environmentComponents»
						«IF instance instanceof EnvironmentSynchronousCompositeComponentInstance»
							private «instance.type.generateComponentClassName» «instance.name»;
						«ENDIF»
				«ENDFOR»
			«ENDIF»			
			// Port instances
			«FOR port : component.ports»
				private «port.name.toFirstUpper» «port.name.toFirstLower»;
			«ENDFOR»
			
			«component.generateParameterDeclarationFields»
			
						
			«envUtil.listDefinition»
						
			«envUtil.registerFunc»
			
			«IF component.needTimer»
				public «component.generateComponentClassName»(«FOR parameter : component.parameterDeclarations SEPARATOR ", " AFTER ", "»«parameter.type.transformType» «parameter.name»«ENDFOR»«Namings.UNIFIED_TIMER_INTERFACE» timer) {
					«component.createInstances»
					«IF component instanceof EnvironmentSynchronousCompositeComponent»
					// Environmental Component instances
						«FOR instance : component.environmentComponents»
							«IF instance instanceof EnvironmentSynchronousCompositeComponentInstance»
								«instance.name» = new «instance.type.name.toFirstUpper»();
							«ENDIF»
						«ENDFOR»
					«ENDIF»					
					setTimer(timer);
					init();
				}
			«ENDIF»
			
			public «component.generateComponentClassName»(«FOR parameter : component.parameterDeclarations SEPARATOR ", "»«parameter.type.transformType» «parameter.name»«ENDFOR») {
				«component.createInstances»
				«IF component instanceof EnvironmentSynchronousCompositeComponent»
				// Environmental Component instances
					«FOR instance : component.environmentComponents»
						«IF instance instanceof EnvironmentSynchronousCompositeComponentInstance»
								«instance.name» = new «instance.type.name.toFirstUpper»();
						«ENDIF»
					«ENDFOR»
				«ENDIF»					
				init();
			}
			
			/** Resets the contained statemachines recursively. Must be called to initialize the component. */
			@Override
			public void reset() {
				this.handleBeforeReset();
				this.resetVariables();
				this.resetStateConfigurations();
				this.raiseEntryEvents();
				this.handleAfterReset();
			}

			public void handleBeforeReset() {
				//
				«component.executeHandleBeforeReset»
				«IF component instanceof EnvironmentSynchronousCompositeComponent»
					«FOR port : component.ports.filter[p|p.detPortBindings.empty]»
						«port.name.toFirstLower».reset();
					«ENDFOR»
				«ENDIF»
			}

			«component.generateResetMethods»

			public void handleAfterReset() {
				«component.executeHandleAfterReset»
				«IF component instanceof CascadeCompositeComponent»
					// Setting only a single queue for cascade statecharts
					«FOR instance : component.components.filter[it.type instanceof StatechartDefinition]»
						«instance.name».change«INSERT_QUEUE.toFirstUpper»();
					«ENDFOR»
				«ENDIF»
				«IF component instanceof EnvironmentSynchronousCompositeComponent»
					«FOR envComp : component.environmentComponents»
						«IF envComp instanceof EnvironmentSynchronousCompositeComponentInstance»
							//«envComp.name».reset();
						«ENDIF»
					«ENDFOR»
				«ENDIF»
				clearPorts();
				// Initializing chain of listeners and events 
				notifyAllListeners();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {
				// Registration of simple channels
				«FOR channelMatch : SimpleChannels.Matcher.on(engine).getAllMatches(component, null, null, null)»
					«IF (!(channelMatch.providedPort.instance instanceof ElementaryEnvironmentComponentInstance
					) && (!(channelMatch.requiredPort.instance instanceof ElementaryEnvironmentComponentInstance)))»
						«channelMatch.providedPort.instance.name».get«channelMatch.providedPort.port.name.toFirstUpper»().registerListener(«channelMatch.requiredPort.instance.name».get«channelMatch.requiredPort.port.name.toFirstUpper»());
						«channelMatch.requiredPort.instance.name».get«channelMatch.requiredPort.port.name.toFirstUpper»().registerListener(«channelMatch.providedPort.instance.name».get«channelMatch.providedPort.port.name.toFirstUpper»());
					«ENDIF»
				«ENDFOR»
				// Registration of broadcast channels
				«FOR channelMatch : BroadcastChannels.Matcher.on(engine).getAllMatches(component, null, null, null)»
					«IF (!(channelMatch.providedPort.instance instanceof ElementaryEnvironmentComponentInstance
										) && (!(channelMatch.requiredPort.instance instanceof ElementaryEnvironmentComponentInstance)))»
						«channelMatch.providedPort.instance.name».get«channelMatch.providedPort.port.name.toFirstUpper»().registerListener(«channelMatch.requiredPort.instance.name».get«channelMatch.requiredPort.port.name.toFirstUpper»());
					«ENDIF»
				«ENDFOR»
			}
			
			// Inner classes representing Ports
			«FOR systemPort : component.ports SEPARATOR "\n"»
				public class «systemPort.name.toFirstUpper» implements «systemPort.interfaceRealization.interface.implementationName».«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper»,«systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper» {
					private List<«systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper»> listeners = new LinkedList<«systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper»>();
«««					Cascade components need their raised events saved (multiple schedule of a component in a single turn)
					«FOR event : systemPort.outputEvents»
						boolean isRaised«event.name.toFirstUpper»;
						«FOR parameter : event.parameterDeclarations»
							«parameter.type.transformType» «parameter.generateName» = «expSerializer.serialize(parameter.type.defaultExpression)»;
						«ENDFOR»
					«ENDFOR»
					
					public «systemPort.name.toFirstUpper»() {
						// Registering the listener to the contained component
						«FOR portBinding : systemPort.portBindings»
							«IF ! (portBinding.instancePortReference.instance instanceof ElementaryEnvironmentComponentInstance)»
								«portBinding.instancePortReference.instance.name».get«portBinding.instancePortReference.port.name.toFirstUpper»().registerListener(new «portBinding.compositeSystemPort.name.toFirstUpper»Util());
							«ENDIF»
						«ENDFOR»
					}
					
					«systemPort.delegateRaisingMethods» 
					
					«systemPort.implementOutMethods»
					«FOR event : systemPort.outputEvents SEPARATOR "\n"»
						@Override
						public void raise«event.name.toFirstUpper»(«event.generateParameters») {
							isRaised«event.name.toFirstUpper» = true;
							«FOR parameter : event.parameterDeclarations»
								«systemPort.name.toFirstUpper».this.«parameter.generateName» = «parameter.generateName»;
							«ENDFOR»
						}
					«ENDFOR»
					
					
					// Class for the setting of the boolean fields (events)
					private class «systemPort.name.toFirstUpper»Util implements «systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper» {
						«FOR event : systemPort.outputEvents SEPARATOR "\n"»
							@Override
							public void raise«event.name.toFirstUpper»(«event.generateParameters») {
								isRaised«event.name.toFirstUpper» = true;
								«FOR parameter : event.parameterDeclarations»
									«systemPort.name.toFirstUpper».this.«parameter.generateName» = «parameter.generateName»;
								«ENDFOR»
							}
						«ENDFOR»
					}
					
					@Override
					public void registerListener(«systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper» listener) {
						listeners.add(listener);
					}
					
					@Override
					public List<«systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper»> getRegisteredListeners() {
						return listeners;
					}
					
					/** Resetting the boolean event flags to false. */
					public void clear() {
						«FOR event : systemPort.outputEvents»
							isRaised«event.name.toFirstUpper» = false;
						«ENDFOR»
					}
					
					/** Notifying the registered listeners. */
					public void notifyListeners() {
						«FOR event : systemPort.outputEvents»
							if (isRaised«event.name.toFirstUpper») {
								for («systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper» listener : listeners) {
									listener.raise«event.name.toFirstUpper»(«event.generateArguments»);
								}
							}
						«ENDFOR»
					}
					
				}
				
				@Override
				public «systemPort.name.toFirstUpper» get«systemPort.name.toFirstUpper»() {
					return «systemPort.name.toFirstLower»;
				}
			«ENDFOR»
			
			/** Clears the the boolean flags of all out-events in each contained port. */
			private void clearPorts() {
				«FOR portBinding : component.portBindings»
					get«portBinding.compositeSystemPort.name.toFirstUpper»().clear();
				«ENDFOR»
			}
			
			/** Notifies all registered listeners in each contained port. */
			public void notifyAllListeners() {
«««				This subcomponent notification is necessery in hierarchical composite components
				«FOR subcomponent : component.components»
					«subcomponent.name».notifyAllListeners();
				«ENDFOR»

				«IF component instanceof EnvironmentSynchronousCompositeComponent»
					«FOR envComp : component.environmentComponents»
						«IF envComp instanceof EnvironmentSynchronousCompositeComponentInstance»
							«envComp.name».notifyListeners();
						«ENDIF»
					«ENDFOR»
				«ENDIF»
				notifyListeners();
			}
			
			public void notifyListeners() {
				«FOR portBinding : component.portBindings»
					get«portBinding.compositeSystemPort.name.toFirstUpper»().notifyListeners();
				«ENDFOR»
			}
			
			«IF component instanceof SynchronousCompositeComponent»
				/** Changes the event and process queues of all component instances. Should be used only be the container (composite system) class. */
				public void change«EVENT_QUEUE.toFirstUpper»s() {
					«FOR instance : component.components.filter[!(it.type instanceof CascadeCompositeComponent)]»
						«instance.name».change«EVENT_QUEUE.toFirstUpper»s();
					«ENDFOR»
				}
			«ENDIF»
			
			/** Returns whether all event queues of the contained component instances are empty. 
			Should be used only be the container (composite system) class. */
			public boolean is«EVENT_QUEUE.toFirstUpper»Empty() {
				«IF component.components.isEmpty»
					return true;
				«ELSE»
					return «FOR instance : component.components SEPARATOR " && "»«instance.name».is«EVENT_QUEUE.toFirstUpper»Empty()«ENDFOR»;
				«ENDIF»
			}
			
			/** Initiates cycle runs until all event queues of component instances are empty. */
			@Override
			public void runFullCycle() {
				do {
					runCycle();
				}
				while (!is«EVENT_QUEUE.toFirstUpper»Empty() «generateIsEmpty(component)»);
			}
			
			/** Changes event queues and initiates a cycle run.
			 * This should be the execution point from an asynchronous component. */
			@Override
			public void runCycle() {
				«IF component instanceof SynchronousCompositeComponent»
					// Changing the insert and process queues for all synchronous subcomponents
					change«EVENT_QUEUE.toFirstUpper»s();
				«ENDIF»
				// Composite type-dependent behavior
				runComponent();
			}
			
			/** Initiates a cycle run without changing the event queues.
			 * Should be used only be the container (composite system) class. */
			public void runComponent() {
				// Running contained components
				«FOR instance : component.scheduledInstances»
					«IF instance instanceof ElementaryEnvironmentComponentInstance»
						«envUtil.getScheduleCall(instance)»
					«ELSEIF component instanceof CascadeCompositeComponent && instance.type instanceof SynchronousCompositeComponent»
						«instance.name».runCycle();
					«ELSE»
						«instance.name».runComponent();
					«ENDIF»
				«ENDFOR»
				«IF component instanceof EnvironmentSynchronousCompositeComponent»
					«FOR instance : component.environmentComponents»
						«IF instance instanceof EnvironmentSynchronousCompositeComponentInstance»
							«instance.name».runCycle();
						«ENDIF»
					«ENDFOR»					
				«ENDIF»				
				// Notifying registered listeners
				notifyListeners();
				
				// Ends with the clearing of the out-event flags
				clearPorts();
			}
		
			«IF component.needTimer»
				/** Setter for the timer e.g., a virtual timer. */
				public void setTimer(«Namings.UNIFIED_TIMER_INTERFACE» timer) {
					«FOR instance : component.components»
						«IF instance.type.needTimer»
							«instance.name».setTimer(timer);
						«ENDIF»
					«ENDFOR»
					reset();
				}
			«ENDIF»
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			«FOR instance : component.components SEPARATOR "\n"»
				public «instance.type.generateComponentClassName» get«instance.name.toFirstUpper»() {
					return «instance.name»;
				}
			«ENDFOR»
			
			«IF component instanceof EnvironmentSynchronousCompositeComponent»
			// Environmental Component instances
				«FOR instance : component.environmentComponents»
					«IF instance instanceof EnvironmentSynchronousCompositeComponentInstance»
						public «instance.type.generateComponentClassName» get«instance.name.toFirstUpper»() {
							return «instance.name»;
						}
					«ENDIF»
				«ENDFOR»
			«ENDIF»			
			
			
		}
	'''
	
	dispatch def generateIsEmpty(EnvironmentSynchronousCompositeComponent component){
		return '''«IF !component.environmentComponents.empty»&& «ENDIF»  «FOR inst : component.environmentComponents SEPARATOR " && "» «envUtil.getIsEmptyCall(inst as ElementaryEnvironmentComponentInstance)» «ENDFOR»'''
	}
	
	dispatch def generateIsEmpty(EnvironmentCascadeCompositeComponent component){
		return '''«IF !component.environmentComponents.empty»&& «ENDIF»  «FOR inst : component.environmentComponents SEPARATOR " && "» «envUtil.getIsEmptyCall(inst as ElementaryEnvironmentComponentInstance)» «ENDFOR»'''
	}
	dispatch def generateIsEmpty(AbstractSynchronousCompositeComponent component){
		return ""
}
	
}