/********************************************************************************
 * Copyright (c) 2018-2020 Contributors to the Gamma project
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
import hu.bme.mit.gamma.codegeneration.java.util.Namings
import hu.bme.mit.gamma.codegeneration.java.util.TimingDeterminer
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent

import static extension hu.bme.mit.gamma.codegeneration.java.util.Namings.*
import hu.bme.mit.gamma.statechart.composite.AbstractAsynchronousCompositeComponent
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance
import hu.bme.mit.gamma.environment.model.AbstractEnvironmentCompositeComponent
import hu.bme.mit.gamma.codegeneration.java.Trace
import hu.bme.mit.gamma.codegeneration.java.NameGenerator
import hu.bme.mit.gamma.codegeneration.java.TypeTransformer
import hu.bme.mit.gamma.codegeneration.java.ComponentCodeGenerator
import hu.bme.mit.gamma.codegeneration.java.CompositeComponentCodeGenerator
import static extension hu.bme.mit.gamma.codegeneration.java.CompositeComponentCodeGenerator.*
import hu.bme.mit.gamma.codegeneration.java.util.ElementaryEnvironmentComponentUtility
import hu.bme.mit.gamma.statechart.interface_.Port
import static extension hu.bme.mit.gamma.statechart.util.ActionSerializer.*
import static extension hu.bme.mit.gamma.statechart.util.ExpressionSerializer.*
import static extension hu.bme.mit.gamma.expression.derivedfeatures.ExpressionModelDerivedFeatures.*
//import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
import static extension hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures.*
import static extension hu.bme.mit.gamma.codegeneration.java.EventDeclarationHandler.*
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.expression.model.ParameterDeclaration
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition

class EnvironmentAsynchronousCompositeComponentCodeGenerator {

	protected final String PACKAGE_NAME

	//
	protected final extension TimingDeterminer timingDeterminer = TimingDeterminer.INSTANCE
	protected final extension Trace trace
	protected final extension NameGenerator nameGenerator
	protected final extension TypeTransformer typeTransformer
	protected final extension ComponentCodeGenerator componentCodeGenerator
	protected final extension CompositeComponentCodeGenerator compositeComponentCodeGenerator
	protected val envUtil = ElementaryEnvironmentComponentUtility.INSTANCE

	new(String packageName, Trace trace) {
		val SDA = EventDeclarationHandler
		this.PACKAGE_NAME = packageName
		this.trace = trace
		this.nameGenerator = new NameGenerator(this.PACKAGE_NAME)
		this.typeTransformer = new TypeTransformer(trace)
		this.componentCodeGenerator = new ComponentCodeGenerator(this.trace)
		this.compositeComponentCodeGenerator = new CompositeComponentCodeGenerator(this.PACKAGE_NAME, this.trace)
	}

	/**
	 * Creates the Java code of the asynchronous composite class, containing asynchronous components.
	 */
	protected def generateEnvironmentInports(AbstractAsynchronousCompositeComponent component) {
		'''
			«IF component instanceof EnvironmentAsynchronousCompositeComponent»
				«var comp=component as EnvironmentAsynchronousCompositeComponent»
				«var types=comp.environmentComponents
			.filter[c|c instanceof EnvironmentAsynchronousCompositeComponentInstance]
				.map[c|c as EnvironmentAsynchronousCompositeComponentInstance]
					.map[c|c.type]»
				«FOR t : types»
					import «PACKAGE_NAME».«t.name.toLowerCase».*;
				«ENDFOR»
			«ENDIF»
		'''
	}

	protected def createEnvironmentAsynchronousCompositeComponentClass(AbstractAsynchronousCompositeComponent component,
		int channelId1, int channelId2) '''
		package «component.generateComponentPackageName»;
		
		«component.generateCompositeSystemImports»
		
		«component.generateEnvironmentInports»
		import java.util.ArrayList;
		
		«envUtil.getScheduingInterfaceImport(PACKAGE_NAME)»
		
		public class «component.generateComponentClassName» implements «component.generatePortOwnerInterfaceName» {
			// Component instances
			«FOR instance : component.components»
				private «instance.type.generateComponentClassName» «instance.name»;
			«ENDFOR»
			«IF component instanceof EnvironmentAsynchronousCompositeComponent»
				// Environmental Component instances
				«FOR instance : component.environmentComponents»
						«IF instance instanceof EnvironmentAsynchronousCompositeComponentInstance»
							private «instance.type.generateComponentClassName» «instance.name»;
						«ENDIF»
				«ENDFOR»
			«ENDIF»	
			// Port instances
			«FOR port : component.ports»
				private «port.name.toFirstUpper» «port.name.toFirstLower» = new «port.name.toFirstUpper»();
			«ENDFOR»
			// Channel instances
			«FOR channel : SimpleChannels.Matcher.on(trace.getEngine).getAllValuesOfsimpleChannel(component, null, null)»
				private «channel.providedPort.port.interfaceRealization.interface.generateChannelInterfaceName» channel«channel.providedPort.port.name.toFirstUpper»Of«channel.providedPort.instance.name.toFirstUpper»;
			«ENDFOR»
			«FOR channel : BroadcastChannels.Matcher.on(trace.getEngine).getAllValuesOfbroadcastChannel(component, null, null)»
				private «channel.providedPort.port.interfaceRealization.interface.generateChannelInterfaceName» channel«channel.providedPort.port.name.toFirstUpper»Of«channel.providedPort.instance.name.toFirstUpper»;
			«ENDFOR»
			
			«component.generateParameterDeclarationFields»
			
			«envUtil.listDefinition»
			
			«envUtil.registerFunc»
			
			public boolean isEventQueueEmpty(){
				return «FOR comp : component.instances SEPARATOR " && "» «comp.name».isEventQueueEmpty() «ENDFOR»«IF component.components.empty»true«ENDIF» «IF component instanceof EnvironmentAsynchronousCompositeComponent » «IF !component.environmentComponents.empty»&& «ENDIF» «FOR inst : component.environmentComponents SEPARATOR " && "» «envUtil.getIsEmptyCall(inst as ElementaryEnvironmentComponentInstance)» «ENDFOR» «ENDIF»;
			}
			
			private final int MAX_SCHEDULE=15;
			
			public void schedule(){
				int cntr=0;
				do{
					«FOR inst : component.instances»
						«inst.name».schedule();
					«ENDFOR»
					«IF component instanceof EnvironmentAsynchronousCompositeComponent»
						«FOR inst : component.environmentComponents»
							«envUtil.getScheduleCall(inst as ElementaryEnvironmentComponentInstance)»
						«ENDFOR»
					«ENDIF»
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in «component.name»! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					«FOR inst : component.instances»
						«inst.name».schedule();
					«ENDFOR»
					«IF component instanceof EnvironmentAsynchronousCompositeComponent»
						«FOR inst : component.environmentComponents»
							«envUtil.getScheduleCall(inst as ElementaryEnvironmentComponentInstance)»
						«ENDFOR»
					«ENDIF»
					System.out.println(getInQueue());
					«IF component instanceof EnvironmentAsynchronousCompositeComponent »
						«FOR inst : component.environmentComponents»
							if (!«envUtil.getIsEmptyCall(inst as ElementaryEnvironmentComponentInstance)»){
								System.out.println("    elementary stochastic component «inst.name» is not empty");
							}
						«ENDFOR» 
					«ENDIF»
					
				}
			}
			«IF component.needTimer»
				public «component.generateComponentClassName»(«FOR parameter : component.parameterDeclarations SEPARATOR ", " AFTER ", "»«parameter.type.transformType» «parameter.name»«ENDFOR»«Namings.UNIFIED_TIMER_INTERFACE» timer) {
					«component.createInstances»
					«IF component instanceof EnvironmentAsynchronousCompositeComponent»
					// Environmental Component instances
						«FOR instance : component.environmentComponents»
							«IF instance instanceof EnvironmentAsynchronousCompositeComponentInstance»
								«instance.name» = new «instance.type.name.toFirstUpper»();
							«ENDIF»
						«ENDFOR»
					«ENDIF»	
					setTimer(timer);
					init(); // Init is not called in setTimer like in the wrapper as it would be unnecessary
				}
			«ENDIF»
			
			public «component.generateComponentClassName»(«FOR parameter : component.parameterDeclarations SEPARATOR ", "»«parameter.type.transformType» «parameter.name»«ENDFOR») {
				«component.createInstances»
				«IF component instanceof EnvironmentAsynchronousCompositeComponent»
				// Environmental Component instances
					«FOR instance : component.environmentComponents»
						«IF instance instanceof EnvironmentAsynchronousCompositeComponentInstance»
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
								//
				«IF component instanceof EnvironmentAsynchronousCompositeComponent»
					«FOR envComp : component.environmentComponents»
						«IF envComp instanceof EnvironmentAsynchronousCompositeComponentInstance»
							//«envComp.name».reset();
						«ENDIF»
					«ENDFOR»
					«FOR port : component.ports.filter[p|p.detPortBindings.empty]»
						«port.name.toFirstLower».reset();
					«ENDFOR»
				«ENDIF»
			}

			«component.generateResetMethods»

			public void handleAfterReset() {
				«component.executeHandleAfterReset»
				
				«FOR instance : component.components»
					//«instance.name».schedule();
				«ENDFOR»
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				«FOR channelMatch : SimpleChannels.Matcher.on(trace.getEngine).getAllMatches(component, null, null, null)»
					«IF ((!(channelMatch.providedPort.instance instanceof ElementaryEnvironmentComponentInstance
										)) && (!(channelMatch.requiredPort.instance instanceof ElementaryEnvironmentComponentInstance)))»
						channel«channelMatch.providedPort.port.name.toFirstUpper»Of«channelMatch.providedPort.instance.name.toFirstUpper» = new «channelMatch.providedPort.port.interfaceRealization.interface.generateChannelName»(«channelMatch.providedPort.instance.name».get«channelMatch.providedPort.port.name.toFirstUpper»());
						channel«channelMatch.providedPort.port.name.toFirstUpper»Of«channelMatch.providedPort.instance.name.toFirstUpper».registerPort(«channelMatch.requiredPort.instance.name».get«channelMatch.requiredPort.port.name.toFirstUpper»());
					«ENDIF»
				«ENDFOR»
				// Registration of broadcast channels
				«FOR channel : BroadcastChannels.Matcher.on(trace.getEngine).getAllValuesOfbroadcastChannel(component, null, null)»
					«IF (!(channel.providedPort.instance instanceof ElementaryEnvironmentComponentInstance))»
					channel«channel.providedPort.port.name.toFirstUpper»Of«channel.providedPort.instance.name.toFirstUpper» = new «channel.providedPort.port.interfaceRealization.interface.generateChannelName»(«channel.providedPort.instance.name».get«channel.providedPort.port.name.toFirstUpper»());
«««					Broadcast channels can have incoming messages in case of asynchronous components
						«FOR channelMatch : BroadcastChannels.Matcher.on(trace.getEngine).getAllMatches(component, channel, null, null)»
							«IF(!(channelMatch.requiredPort.instance instanceof ElementaryEnvironmentComponentInstance))»
								channel«channelMatch.providedPort.port.name.toFirstUpper»Of«channelMatch.providedPort.instance.name.toFirstUpper».registerPort(«channelMatch.requiredPort.instance.name».get«channelMatch.requiredPort.port.name.toFirstUpper»());
							«ENDIF»
						«ENDFOR»
					«ENDIF»
				«ENDFOR»
			}
			
			// Inner classes representing Ports
			«FOR systemPort : component.ports SEPARATOR "\n"»
				public class «systemPort.name.toFirstUpper» implements «systemPort.interfaceRealization.interface.implementationName».«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper» {
					
					
					List<«systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper»> registeredListeners=new ArrayList<«systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper»>();
					
					
					«systemPort.delegateRaisingMethods» 
					
					«systemPort.delegateDetOutMethods»
					
					«IF systemPort.detPortBindings.empty»
						«FOR event:systemPort.outputEvents»
							public void raise«event.name.toFirstUpper»(«event.generateParameters»){
								for («systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper» listener : registeredListeners){
									listener.raise«event.name.toFirstUpper»(«event.generateArguments»);
								}
								«FOR param : event.parameterDeclarations»
								this.«param.generateName»=«param.generateName»;
								«ENDFOR»
							}
						«ENDFOR»
						public void reset(){
							«FOR param : systemPort.outputEvents.flatMap[e|e.parameterDeclarations]»
								this.«param.generateName»=«ExpressionSerializer.INSTANCE.serialize(param.type.defaultExpression)»;
							«ENDFOR»
						}
					«ENDIF»
					
					@Override
					public void registerListener(«systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper» listener) {
						«FOR portDef : systemPort.portBindings»
							«IF ! (portDef.instancePortReference.instance instanceof ElementaryEnvironmentComponentInstance)»
								«portDef.instancePortReference.instance.name».get«portDef.instancePortReference.port.name.toFirstUpper»().registerListener(listener);
							«ENDIF»
						«ENDFOR»
						registeredListeners.add(listener);
					}
					
					@Override
					public List<«systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper»> getRegisteredListeners() {
						List<«systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper»> registeredListeners=new ArrayList<«systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper»>();
						«FOR portDef : systemPort.portBindings»
							«IF ! (portDef.instancePortReference.instance instanceof ElementaryEnvironmentComponentInstance)»
								registeredListeners.addAll(«portDef.instancePortReference.instance.name».get«portDef.instancePortReference.port.name.toFirstUpper»().getRegisteredListeners());
							«ENDIF»
						«ENDFOR»
						return registeredListeners;
					}
					
				}
				
				@Override
				public «systemPort.name.toFirstUpper» get«systemPort.name.toFirstUpper»() {
					return «systemPort.name.toFirstLower»;
				}
			«ENDFOR»
			
			/** Starts the running of the asynchronous component. */
			@Override
			public void start() {
				«FOR instance : component.components»
					«instance.name».start();
				«ENDFOR»
				«IF component instanceof AbstractEnvironmentCompositeComponent»
					«FOR envInstance : component.environmentComponents»
						«IF envInstance instanceof EnvironmentAsynchronousCompositeComponentInstance»
							«envInstance.name».start();
						«ENDIF»
					«ENDFOR»
				«ENDIF»
			}
			
			public boolean isWaiting() {
				return «FOR instance : component.components SEPARATOR " && "»«instance.name».isWaiting()«ENDFOR»
					«IF component instanceof AbstractEnvironmentCompositeComponent»
						«FOR envInstance : component.environmentComponents»
							«IF envInstance instanceof EnvironmentAsynchronousCompositeComponentInstance»
								" && "«envInstance.name».isWaiting()
							«ENDIF»
						«ENDFOR»
					«ENDIF»
					«IF component.components.empty»false«ENDIF»;
			}
			
			«IF component.needTimer»
				/** Setter for the timer e.g., a virtual timer. */
				public void setTimer(«Namings.UNIFIED_TIMER_INTERFACE» timer) {
					«FOR instance : component.components»
						«IF !(instance instanceof ElementaryEnvironmentComponentInstance)»
							«IF instance.type.needTimer»
								«instance.name».setTimer(timer);
							«ENDIF»
						«ENDIF»
					«ENDFOR»
					«IF component instanceof AbstractEnvironmentCompositeComponent»
						«FOR envInstance : component.environmentComponents»
							«IF envInstance instanceof EnvironmentAsynchronousCompositeComponentInstance»
								«IF envInstance.type.needTimer»
									«envInstance.name».setTimer(timer);
								«ENDIF»
							«ENDIF»
						«ENDFOR»
					«ENDIF»
				}
			«ENDIF»
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			«FOR instance : component.components SEPARATOR "\n"»
				public «instance.type.generateComponentClassName» get«instance.name.toFirstUpper»() {
					return «instance.name»;
				}
			«ENDFOR»
			«IF component instanceof AbstractEnvironmentCompositeComponent»
				«FOR envInstance : component.environmentComponents»
					«IF envInstance instanceof EnvironmentAsynchronousCompositeComponentInstance»
						public «envInstance.type.generateComponentClassName» get«envInstance.name.toFirstUpper»() {
							return «envInstance.name»;
						}
					«ENDIF»
				«ENDFOR»
			«ENDIF»
			

			public String getInQueue(){
				if (!isEventQueueEmpty()){
					String str="Input of components [";
					«FOR instance : component.components SEPARATOR "\n"»
						str=str+"\n    «instance.name» : "+«instance.name».getInQueue().replace("    ","      ");
					«ENDFOR»
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
	'''

	/**
	 * Generates methods that for in-event raisings in the case of composite components.
	 */
	def CharSequence delegateDetRaisingMethods(Port systemPort) '''
		«FOR event : systemPort.inputEvents SEPARATOR System.lineSeparator»
			@Override
			public void raise«event.name.toFirstUpper»(« event.generateParameters») {
				«FOR connector : systemPort.detPortBindings»
					«connector.instancePortReference.instance.name».get«connector.instancePortReference.port.name.toFirstUpper»().raise«event.name.toFirstUpper»(«event.generateArguments»);
				«ENDFOR»	
			}
		«ENDFOR»
	'''

	/**
	 * Generates methods for out-event check delegations in the case of composite components.
	 */
	def CharSequence delegateDetOutMethods(Port systemPort) '''
		«««		Simple flag checks
		«FOR event : systemPort.outputEvents»
			@Override
			public boolean isRaised«event.name.toFirstUpper»() {
				«IF systemPort.detPortBindings.empty»
					return false;
				«ELSE»
					«FOR connector : systemPort.portBindings»
						return «connector.instancePortReference.instance.name».get«connector.instancePortReference.port.name.toFirstUpper»().isRaised«event.name.toFirstUpper»();
					«ENDFOR»
				«ENDIF»
			}
		«««			ValueOf checks
			«FOR parameter : event.parameterDeclarations»
				
					«IF systemPort.detPortBindings.empty» 
						«parameter.type.transformType» «parameter.generateName»=«ExpressionSerializer.INSTANCE.serialize(parameter.type.defaultExpression)»;
					«ENDIF»
					@Override
					public «parameter.type.transformType» get«parameter.name.toFirstUpper»() {
						«IF systemPort.detPortBindings.empty»
							«IF parameter.type.primitive»
								return «parameter.name»;
							«ELSE»
								return null;
							«ENDIF»
						«ELSE»
							«FOR connector : systemPort.detPortBindings»
								return «connector.instancePortReference.instance.name».get«connector.instancePortReference.port.name.toFirstUpper»().get«parameter.name.toFirstUpper»();
							«ENDFOR»
						«ENDIF»
					}
			«ENDFOR»
		«ENDFOR»
	'''

	def generateName(ParameterDeclaration parameter) '''«parameter.name.toFirstLower»'''

	def generateParameters(
		Event event) '''«FOR parameter : event.parameterDeclarations SEPARATOR ", "»«parameter.type.transformType» «parameter.generateName»«ENDFOR»'''

	def generateArguments(
		Event event) '''«FOR parameter : event.parameterDeclarations SEPARATOR ", "»«parameter.generateName»«ENDFOR»'''
}
