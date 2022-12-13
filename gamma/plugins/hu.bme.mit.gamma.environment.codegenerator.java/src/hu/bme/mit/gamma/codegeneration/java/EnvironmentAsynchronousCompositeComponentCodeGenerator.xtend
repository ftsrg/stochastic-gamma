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
import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
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
import static extension hu.bme.mit.gamma.codegeneration.java.TypeTransformer.*

class EnvironmentAsynchronousCompositeComponentCodeGenerator {
	
	protected final String PACKAGE_NAME
	// 
	protected final extension TimingDeterminer timingDeterminer = TimingDeterminer.INSTANCE
	protected final extension Trace trace
	protected final extension NameGenerator nameGenerator
	protected final extension TypeTransformer typeTransformer
	protected final extension ComponentCodeGenerator componentCodeGenerator
	protected final extension CompositeComponentCodeGenerator compositeComponentCodeGenerator

	new(String packageName, Trace trace) {
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
	
	protected def generateEnvironmentInports(AbstractAsynchronousCompositeComponent component){
		'''
		«var comp=component as EnvironmentAsynchronousCompositeComponent»
		«var types=comp.environmentComponents
			.filter[c|c instanceof EnvironmentAsynchronousCompositeComponentInstance]
				.map[c|c as EnvironmentAsynchronousCompositeComponentInstance]
					.map[c|c.type]»
		«FOR t : types»
			import «PACKAGE_NAME».«t.name.toLowerCase».*;
		«ENDFOR»
		'''
	}
	
	protected def createEnvironmentAsynchronousCompositeComponentClass( AbstractAsynchronousCompositeComponent component, int channelId1, int channelId2) '''
		package «component.generateComponentPackageName»;
		
		«component.generateCompositeSystemImports»
		
		«component.generateEnvironmentInports»
		
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
			«FOR channel : SimpleChannels.Matcher.on(engine).getAllValuesOfsimpleChannel(component, null, null)»
				private «channel.providedPort.port.interfaceRealization.interface.generateChannelInterfaceName» channel«channel.providedPort.port.name.toFirstUpper»Of«channel.providedPort.instance.name.toFirstUpper»;
			«ENDFOR»
			«FOR channel : BroadcastChannels.Matcher.on(engine).getAllValuesOfbroadcastChannel(component, null, null)»
				private «channel.providedPort.port.interfaceRealization.interface.generateChannelInterfaceName» channel«channel.providedPort.port.name.toFirstUpper»Of«channel.providedPort.instance.name.toFirstUpper»;
			«ENDFOR»
			«component.generateParameterDeclarationFields»
			
			public boolean isEmpty(){
				return «FOR comp : component.instances SEPARATOR " && " » «comp.name».isEmpty() «ENDFOR»;
			}
			
			public void schedule(){
				while(!isEmpty()){
					«FOR inst : component.instances»
						«inst.name».schedule();
					«ENDFOR»
				}
			}
			«IF component.needTimer»
				public «component.generateComponentClassName»(«FOR parameter : component.parameterDeclarations SEPARATOR ", " AFTER ", "»«parameter.type.transformType» «parameter.name»«ENDFOR»«Namings.YAKINDU_TIMER_INTERFACE» timer) {
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
				«FOR instance : component.components»
					«instance.name».reset();
				«ENDFOR»
				«IF component instanceof EnvironmentAsynchronousCompositeComponent»
					«FOR envComp : component.environmentComponents»
						«IF envComp instanceof EnvironmentAsynchronousCompositeComponentInstance»
							«envComp.name».reset();
						«ENDIF»
					«ENDFOR»
				«ENDIF»
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				«FOR channelMatch : SimpleChannels.Matcher.on(engine).getAllMatches(component, null, null, null)»
					«IF ((!(channelMatch.providedPort.instance instanceof ElementaryEnvironmentComponentInstance
										)) && (!(channelMatch.requiredPort.instance instanceof ElementaryEnvironmentComponentInstance)))»
						channel«channelMatch.providedPort.port.name.toFirstUpper»Of«channelMatch.providedPort.instance.name.toFirstUpper» = new «channelMatch.providedPort.port.interfaceRealization.interface.generateChannelName»(«channelMatch.providedPort.instance.name».get«channelMatch.providedPort.port.name.toFirstUpper»());
						channel«channelMatch.providedPort.port.name.toFirstUpper»Of«channelMatch.providedPort.instance.name.toFirstUpper».registerPort(«channelMatch.requiredPort.instance.name».get«channelMatch.requiredPort.port.name.toFirstUpper»());
					«ENDIF»
				«ENDFOR»
				// Registration of broadcast channels
				«FOR channel : BroadcastChannels.Matcher.on(engine).getAllValuesOfbroadcastChannel(component, null, null)»
					«IF (!(channel.providedPort.instance instanceof ElementaryEnvironmentComponentInstance))»
					channel«channel.providedPort.port.name.toFirstUpper»Of«channel.providedPort.instance.name.toFirstUpper» = new «channel.providedPort.port.interfaceRealization.interface.generateChannelName»(«channel.providedPort.instance.name».get«channel.providedPort.port.name.toFirstUpper»());
«««					Broadcast channels can have incoming messages in case of asynchronous components
						«FOR channelMatch : BroadcastChannels.Matcher.on(engine).getAllMatches(component, channel, null, null)»
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
				
					«systemPort.delegateRaisingMethods» 
					
					«systemPort.delegateOutMethods»
					
					@Override
					public void registerListener(«systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper» listener) {
						«FOR portDef : systemPort.portBindings»
							«IF ! (portDef.instancePortReference.instance instanceof ElementaryEnvironmentComponentInstance)»
								«portDef.instancePortReference.instance.name».get«portDef.instancePortReference.port.name.toFirstUpper»().registerListener(listener);
							«ENDIF»
						«ENDFOR»
					}
					
					@Override
					public List<«systemPort.interfaceRealization.interface.implementationName».Listener.«systemPort.interfaceRealization.realizationMode.toString.toLowerCase.toFirstUpper»> getRegisteredListeners() {
						«FOR portDef : systemPort.portBindings»
							«IF ! (portDef.instancePortReference.instance instanceof ElementaryEnvironmentComponentInstance)»
								return «portDef.instancePortReference.instance.name».get«portDef.instancePortReference.port.name.toFirstUpper»().getRegisteredListeners();
							«ENDIF»
						«ENDFOR»
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
					«ENDIF»;
			}
			
			«IF component.needTimer»
				/** Setter for the timer e.g., a virtual timer. */
				public void setTimer(«Namings.YAKINDU_TIMER_INTERFACE» timer) {
					«FOR instance : component.components»
						«IF instance.type.needTimer»
							«instance.name».setTimer(timer);
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
			
		}
	'''
	
}