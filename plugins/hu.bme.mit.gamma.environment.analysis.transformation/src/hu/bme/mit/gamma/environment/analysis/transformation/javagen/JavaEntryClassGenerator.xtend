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
package hu.bme.mit.gamma.environment.analysis.transformation.javagen

import hu.bme.mit.gamma.environment.analysis.AnalysisAspect
import hu.bme.mit.gamma.environment.analysis.AnalysisComponent
import hu.bme.mit.gamma.environment.analysis.EndCondition
import hu.bme.mit.gamma.environment.analysis.MeanParameter
import hu.bme.mit.gamma.environment.analysis.ParameterDistribution
import hu.bme.mit.gamma.environment.analysis.transformation.util.TransformationUtility
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance
import hu.bme.mit.gamma.statechart.interface_.Package
import hu.bme.mit.gamma.environment.analysis.ObserveCondition
import hu.bme.mit.gamma.environment.analysis.ObserveParameter
import hu.bme.mit.gamma.environment.analysis.SimulationAnalysisMethod
import hu.bme.mit.gamma.codegeneration.java.util.TimingDeterminer
import static extension hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures.*
import static extension hu.bme.mit.gamma.codegeneration.java.util.Namings.*

class JavaEntryClassGenerator {
	
	
	def generate(AnalysisComponent component,String packageName){
		
		var pack=component.eContainer as Package
		var compName= (component.analyzedComponent instanceof EnvironmentAsynchronousCompositeComponentInstance) ?
			 (component.analyzedComponent as EnvironmentAsynchronousCompositeComponentInstance).type.name.toFirstUpper :
			 (component.analyzedComponent as EnvironmentSynchronousCompositeComponentInstance).type.name.toFirstUpper
		var i=0
		var interfaces=component.aspect.map[a|a.event.port.interfaceRealization.interface].toSet.toList
		var analysismethod = component.analysismethod as SimulationAnalysisMethod
		var componentPackageName=(component.analyzedComponent.type.eContainer as Package).name.toLowerCase
		'''
		package javaenv;
		
		import «packageName».«componentPackageName».«compName»;
		import «packageName».VirtualTimerService;
		
		«FOR a : component.aspect»
			import «packageName».interfaces.«a.event.port.interfaceRealization.interface.name.toFirstUpper»Interface;
		«ENDFOR»

		«FOR a : component.conditions»
			import «packageName».interfaces.«a.event.port.interfaceRealization.interface.name.toFirstUpper»Interface;
		«ENDFOR»

		«FOR a : analysismethod.endcondition»
			import «packageName».interfaces.«a.event.port.interfaceRealization.interface.name.toFirstUpper»Interface;
		«ENDFOR»
		
		«FOR _package : component.containingPackage.componentImports.toSet»
			import «_package.getPackageString(packageName
				
			)».*;
		«ENDFOR»
				
		//import py4j.GatewayServer; great old times... :)
		
		
		
		
		public class «component.name.toFirstUpper»EntryPoint  {
			
			public «compName» detModel=new «compName»(«TransformationUtility.generateDetmodelParamsInit(component)»);
			«IF TimingDeterminer.INSTANCE.needTimer(component.analyzedComponent.type)»
				public VirtualTimerService timer=new VirtualTimerService();
			«ENDIF»
			«FOR a : component.aspect»
				public MonitorOf«TransformationUtility.generateAspectName(a)» monitorOf«TransformationUtility.generateAspectName(a)»=new MonitorOf«TransformationUtility.generateAspectName(a)»();
			«ENDFOR»

			«FOR c : component.conditions»
				public MonitorOf«TransformationUtility.generateAspectName(c)» monitorOf«TransformationUtility.generateAspectName(c)»=new MonitorOf«TransformationUtility.generateAspectName(c)»();
			«ENDFOR»

			«FOR endCondition : analysismethod.endcondition»
				public MonitorOf«TransformationUtility.generateEndConditionName(endCondition)» monitorOf«TransformationUtility.generateEndConditionName(endCondition)»= new MonitorOf«TransformationUtility.generateEndConditionName(endCondition)»();
			«ENDFOR»
			
			
			public «component.name.toFirstUpper»EntryPoint(){
				
				«IF TimingDeterminer.INSTANCE.needTimer(component.analyzedComponent.type)»
					detModel.setTimer(this.timer);
				«ENDIF»
				
				
				«FOR a : component.aspect»
					detModel.get«a.event.port.name.toFirstUpper»().registerListener(monitorOf«TransformationUtility.generateAspectName(a)»);
				«ENDFOR»

				«FOR c : component.conditions»
					detModel.get«c.event.port.name.toFirstUpper»().registerListener(monitorOf«TransformationUtility.generateAspectName(c)»);
				«ENDFOR»

				«FOR endCondition : analysismethod.endcondition»
					detModel.get«endCondition.event.port.name.toFirstUpper»().registerListener(monitorOf«TransformationUtility.generateEndConditionName(endCondition)»);
				«ENDFOR»
			}

			public void reset() {
				«IF TimingDeterminer.INSTANCE.needTimer(component.analyzedComponent.type)»
					timer.reset();
				«ENDIF»
				/*
				detModel=null;
				System.gc();
				detModel=new «compName»(«TransformationUtility.generateDetmodelParamsResetInit(component)»);
				«FOR a : component.aspect»
					detModel.get«a.event.port.name.toFirstUpper»().registerListener(monitorOf«TransformationUtility.generateAspectName(a)»);
				«ENDFOR»
				«FOR c : component.conditions»
					detModel.get«c.event.port.name.toFirstUpper»().registerListener(monitorOf«TransformationUtility.generateAspectName(c)»);
				«ENDFOR»
				«FOR endCondition : analysismethod.endcondition»
					detModel.get«endCondition.event.port.name.toFirstUpper»().registerListener(monitorOf«TransformationUtility.generateEndConditionName(endCondition)»);
				«ENDFOR»
				*/
				«FOR a : component.aspect»
					monitorOf«TransformationUtility.generateAspectName(a)».reset();
				«ENDFOR»
				«FOR c : component.conditions»
					monitorOf«TransformationUtility.generateAspectName(c)».reset();
				«ENDFOR»	
				«FOR endCondition : analysismethod.endcondition»
					monitorOf«TransformationUtility.generateEndConditionName(endCondition)».reset();
				«ENDFOR»
				detModel.reset();
				
			}
			
			
			public «compName» getDetModel(){
				return detModel;
			}
			
			public «compName» get«component.analyzedComponent.name.toFirstUpper»(){
				return detModel;
			}   

			«FOR a : component.aspect»
				«generateMonitor(a)»
			«ENDFOR»

			«FOR c : component.conditions»
				«generateMonitor(c)»
			«ENDFOR»

			«FOR endCondition : analysismethod.endcondition»
				«generateMonitor(endCondition)»
			«ENDFOR»


		}
		
		
		
		
		'''
	}
	
	def generateMonitor(AnalysisAspect a){'''
	
		public class MonitorOf«TransformationUtility.generateAspectName(a)» implements «a.event.port.interfaceRealization.interface.name.toFirstUpper»Interface.Listener.Provided {
					
					public String state="run";
					
					public int freq=0;
					
					public double parameter=0.0/0.0;//NaN is the initial value intentionally 
					public double meanParameter=0.0;
					public double sumParameter=0.0;
					
					public void getValue(){}
					
							
					public void reset(){
						state="run";
						freq=0;
						parameter=0.0/0.0;
						sumParameter=0.0;
						meanParameter=0.0;
					}
											
					«FOR event : a.event.port.allEvents»
					@Override
					public void raise«event.name.toFirstUpper»(«TransformationUtility.generateParameters(event)»){
						«IF event==a.event.event»
							state="stop";
							freq++;
							«IF a instanceof ObserveParameter»
								parameter=«TransformationUtility.generateName(a.parameter)»
							«ENDIF»
							«IF a instanceof ParameterDistribution»
								parameter=«TransformationUtility.generateName(a.parameter)»
							«ENDIF»
							«IF a instanceof MeanParameter»
								sumParameter=sumParameter+«TransformationUtility.generateFloatParameter(a.parameter)»;
								meanParameter=sumParameter/freq;
							«ENDIF»
						«ENDIF»
					}
					
					«ENDFOR»
		}
'''		
	}
	
	
	def generateMonitor(EndCondition e){'''
	
		public class MonitorOf«TransformationUtility.generateEndConditionName(e)» implements «e.event.port.interfaceRealization.interface.name.toFirstUpper»Interface.Listener.Provided {
					
					public String state="run";
					
							
					public void reset(){
						state="run";
					}
											
					«FOR event : e.event.port.allEvents»
					@Override
					public void raise«event.name.toFirstUpper»(«TransformationUtility.generateParameters(event)»){
						«IF event==e.event.event»
							state="stop";
						«ENDIF»
					}
					
					«ENDFOR»
		}
'''		
	}
	
}