package hu.bme.mit.gamma.analysis.transformation

import hu.bme.mit.gamma.analysis.AnalysisComponent
import hu.bme.mit.gamma.statechart.interface_.Package
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance
import hu.bme.mit.gamma.action.model.AssignmentStatement
import hu.bme.mit.gamma.expression.model.IntegerTypeDefinition
import hu.bme.mit.gamma.expression.model.Type
import hu.bme.mit.gamma.expression.util.TypeSerializer
import hu.bme.mit.gamma.analysis.ParameterDistribution
import hu.bme.mit.gamma.analysis.MeanParameter
import hu.bme.mit.gamma.analysis.AnalysisAspect
import hu.bme.mit.gamma.analysis.EndCondition
import hu.bme.mit.gamma.analysis.MeanTime
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator
import hu.bme.mit.gamma.stochastic.stochastic.StochasticExpression

class Py4JGatewayGenerator {
	
	
	
	def generate(AnalysisComponent component,String packageName){
		
		var pack=component.eContainer as Package
		var compName= (component.analyzedComponent instanceof EnvironmentAsynchronousCompositeComponentInstance) ?
			 (component.analyzedComponent as EnvironmentAsynchronousCompositeComponentInstance).type.name.toFirstUpper :
			 (component.analyzedComponent as EnvironmentSynchronousCompositeComponentInstance).type.name.toFirstUpper
		var i=0
		var interfaces=component.aspect.map[a|a.event.port.interfaceRealization.interface].toSet.toList
		'''
		package javaenv;
		
		import «packageName».«pack.name.toLowerCase».«compName»;
		«FOR a : component.aspect»
			import «packageName».interfaces.«a.event.port.interfaceRealization.interface.name.toFirstUpper»Interface;
		«ENDFOR»
		//import py4j.GatewayServer; great old times...
		

				
				
				
				public class DetModelEntryPoint  {
					
					public «compName» detModel=new «compName»(«TransformationUtility.generateDetmodelParamsInit(component)»);
					
					
					«FOR a : component.aspect»
						public MonitorOf«TransformationUtility.generateAspectName(a)» monitorOf«TransformationUtility.generateAspectName(a)»=new MonitorOf«TransformationUtility.generateAspectName(a)»();
					«ENDFOR»
					«FOR endCondition : component.endcondition»
						public MonitorOf«TransformationUtility.generateEndConditionName(endCondition)» monitorOf«TransformationUtility.generateEndConditionName(endCondition)»= new MonitorOf«TransformationUtility.generateEndConditionName(endCondition)»();
					«ENDFOR»
					
					
					public DetModelEntryPoint(){
						«FOR a : component.aspect»
							detModel.get«a.event.port.name.toFirstUpper»().registerListener(monitorOf«TransformationUtility.generateAspectName(a)»);
						«ENDFOR»
						«FOR endCondition : component.endcondition»
							detModel.get«endCondition.event.port.name.toFirstUpper»().registerListener(monitorOf«TransformationUtility.generateEndConditionName(endCondition)»);
						«ENDFOR»
					}
					
					public void reset(«TransformationUtility.generateDetmodelParamsReset(component)») {
						/*
						detModel=null;
						System.gc();
						detModel=new «compName»(«TransformationUtility.generateDetmodelParamsResetInit(component)»);
						«FOR a : component.aspect»
							detModel.get«a.event.port.name.toFirstUpper»().registerListener(monitorOf«TransformationUtility.generateAspectName(a)»);
						«ENDFOR»
						«FOR endCondition : component.endcondition»
							detModel.get«endCondition.event.port.name.toFirstUpper»().registerListener(monitorOf«TransformationUtility.generateEndConditionName(endCondition)»);
						«ENDFOR»
						*/
						«FOR a : component.aspect»
							monitorOf«TransformationUtility.generateAspectName(a)».reset();
						«ENDFOR»											
						«FOR endCondition : component.endcondition»
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
					
					«FOR endCondition : component.endcondition»
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
											
					«FOR event : a.event.port.interfaceRealization.interface.events»
					@Override
					public void raise«event.event.name.toFirstUpper»(«TransformationUtility.generateParameters(event.event)»){
						«IF event.event==a.event.event»
							state="stop";
							freq++;
							«IF a instanceof ParameterDistribution»
								parameter=«TransformationUtility.generateName(a.parameter)»
							«ENDIF»
							«IF a instanceof MeanParameter»
								sumParameter=sumParameter+«TransformationUtility.generateName(a.parameter)»
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
											
					«FOR event : e.event.port.interfaceRealization.interface.events»
					@Override
					public void raise«event.event.name.toFirstUpper»(«TransformationUtility.generateParameters(event.event)»){
						«IF event.event==e.event.event»
							state="stop";
						«ENDIF»
					}
					
					«ENDFOR»
		}

'''		
	}
	
}