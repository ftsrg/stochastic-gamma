/********************************************************************************
 * Copyright (c) 2018-2022 Contributors to the Gamma project
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * SPDX-License-Identifier: EPL-1.0
 ********************************************************************************/
package hu.bme.mit.gamma.environment.plantuml.transformation

import hu.bme.mit.gamma.expression.util.ExpressionSerializer
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent
import hu.bme.mit.gamma.statechart.composite.CompositeComponent
import hu.bme.mit.gamma.statechart.composite.SynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.interface_.RealizationMode

import static extension hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures.*
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponent
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent
import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource
import hu.bme.mit.gamma.environment.model.EnvironmentPeriodicEventSource
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch
import hu.bme.mit.gamma.environment.model.EnvironmentDelay
import hu.bme.mit.gamma.environment.model.EnvironmentSample
import hu.bme.mit.gamma.environment.model.StochasticRule
import hu.bme.mit.gamma.environment.model.ComponentFilter
import hu.bme.mit.gamma.environment.model.PortFilter
import hu.bme.mit.gamma.environment.model.EventFilter
import hu.bme.mit.gamma.environment.stochastic.stochastic.NormalRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.WeibullRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.GammaRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.UniformRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.LogNormalRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.ParetoRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.BetaRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.ExponentialRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.BernoulliRandomVariable
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticModel
import hu.bme.mit.gamma.environment.model.ParameterFilter
import hu.bme.mit.gamma.environment.stochastic.stochastic.CategoricalProbabaility

class CompositeToPlantUmlTransformer {
		
	
	protected final CompositeComponent composite
	
	protected extension ExpressionSerializer expressionSerializer = ExpressionSerializer.INSTANCE
	
	protected int rank=0
	protected int rank2=0
	protected int noteCNTR=0
	
	new(CompositeComponent composite) {
		this.composite = composite
	}
	
	private def getKindString(CompositeComponent composite) {
		if (composite instanceof EnvironmentSynchronousCompositeComponent) {
			return "stochastic synchronous"
		} else if (composite instanceof SynchronousCompositeComponent) {
			return "synchronous"
		} else if (composite instanceof EnvironmentCascadeCompositeComponent) {
			return "stochastic cascade"
		} else if (composite instanceof CascadeCompositeComponent) {
			return "cascade"
		} else if (composite instanceof EnvironmentAsynchronousCompositeComponent) {
			return "stochastic asynchronous"
		}else if (composite instanceof AsynchronousCompositeComponent) {
			return "asynchronous"
		}
	}
		def String execute2() '''
		@startuml
		<style>
		title {
		  FontSize 12
		}
		</style>
		
		skinparam shadowing false
		skinparam linetype ortho
		!theme plain
		'left to right direction
		skinparam nodesep 30
		skinparam ranksep 30
		skinparam defaultTextAlignment center
		skinparam ComponentStereotypeFontSize 10
		skinparam HeaderFontSize 12
		skinparam padding 5
		skinparam componentStyle rectangle
		
		component "«composite.name»"<<«composite.kindString»>> {
			
			«FOR component : composite.derivedComponents»
				component "<size:12>«component.name»" as «component.name»  {
					«FOR port : component.derivedType.allPorts»
						«IF port.interfaceRealization.realizationMode == RealizationMode.REQUIRED»
							portin "«port.name»" as «component.name»__«port.name»
						«ENDIF»
						«IF port.interfaceRealization.realizationMode == RealizationMode.PROVIDED»
							portout "«port.name»" as «component.name»__«port.name»
						«ENDIF»
					«ENDFOR»
				}
			«ENDFOR»

			«FOR component : composite.environmentComponents»
				component "<size:12>«component.name»" as «component.name» <<Stochastic «envType(component as ElementaryEnvironmentComponentInstance)»>>{
					«FOR port : (component as ElementaryEnvironmentComponentInstance).inports»
						portin "«port.name»" as «component.name»__«port.name»
					«ENDFOR»
					«FOR port : (component as ElementaryEnvironmentComponentInstance).outports»
						portout "«port.name»" as «component.name»__«port.name»
					«ENDFOR»
					note "«FOR rule : (component as ElementaryEnvironmentComponentInstance).behaviorRules»«IF rule instanceof StochasticRule»«FOR filter : rule.filter»«filterType(filter)» «ENDFOR»: «generateDitribution(rule.stochasticModel)»\n«ENDIF»«ENDFOR»" as N«noteCNTR++»
				}
			«ENDFOR»
			
			«FOR port : composite.ports»
				«IF port.interfaceRealization.realizationMode == RealizationMode.REQUIRED»
					portin «port.name»
				«ENDIF»
				«IF port.interfaceRealization.realizationMode == RealizationMode.PROVIDED»
					portout «port.name»
				«ENDIF»
			«ENDFOR»
			
			«FOR binding : composite.portBindings»
				«IF binding.instancePortReference.port.interfaceRealization.realizationMode == RealizationMode.REQUIRED»
					«binding.compositeSystemPort.name» <... «binding.instancePortReference.instance.name»__«binding.instancePortReference.port.name»
				«ENDIF»
				«IF binding.instancePortReference.port.interfaceRealization.realizationMode == RealizationMode.PROVIDED»
					«binding.instancePortReference.instance.name»__«binding.instancePortReference.port.name» ...> «binding.compositeSystemPort.name»
				«ENDIF»
				'«composite.name» "«binding.compositeSystemPort.name»" #.# "«binding.instancePortReference.port.name»" «binding.instancePortReference.instance.name»
			«ENDFOR»
			
			
			«FOR channel : composite.channels»
				«FOR requiredPort : channel.requiredPorts»
					«channel.providedPort.instance.name»__«channel.providedPort.port.name» -0)- «requiredPort.instance.name»__«requiredPort.port.name» : "«requiredPort.port.interface.name»" 
				«ENDFOR»
			«ENDFOR»
		}
		

		@enduml
	'''
	def String execute() '''
		@startuml
		skinparam shadowing false
		!theme plain
		left to right direction
		skinparam nodesep 20
		skinparam ranksep 60
		skinparam defaultTextAlignment center
		skinparam linetype polyline
		skinparam padding 4
		
		component "«composite.name»"<<«composite.kindString»>> {
			
			«FOR component : composite.derivedComponents»
				component  «component.name»  [
				{{
				digraph G {
				graph [pad=0]
				n [ margin=0 height=«(0.3+component.derivedType.allPorts.length*0.4).toString» width=«(0.1+component.derivedType.ports.length*0.1).toString» shape=plaintext fontname="SansSerif" label="«component.name» : «component.getDerivedType.name»"]
				}
				}}
				]
			«ENDFOR»

			«FOR component : composite.environmentComponents»
				component «component.name» <<Stochastic «envType(component as ElementaryEnvironmentComponentInstance)»>>[
				«component.name»
				«FOR rule : (component as ElementaryEnvironmentComponentInstance).behaviorRules»
					«IF rule instanceof StochasticRule»«FOR filter : rule.filter»«filterType(filter)» «ENDFOR»: «generateDitribution(rule.stochasticModel)»«ENDIF»
				«ENDFOR»
				]
			«ENDFOR»
			
			«FOR port : composite.ports»
				«IF port.interfaceRealization.realizationMode == RealizationMode.REQUIRED»
					portin «port.name»
				«ENDIF»
				«IF port.interfaceRealization.realizationMode == RealizationMode.PROVIDED»
					portout «port.name»
				«ENDIF»
			«ENDFOR»
			
			«FOR binding : composite.portBindings»
				«IF binding.instancePortReference.port.interfaceRealization.realizationMode == RealizationMode.REQUIRED»
					«binding.compositeSystemPort.name» ..# "«binding.instancePortReference.port.name»" «binding.instancePortReference.instance.name»
				«ENDIF»
				«IF binding.instancePortReference.port.interfaceRealization.realizationMode == RealizationMode.PROVIDED»
					«binding.instancePortReference.instance.name» "«binding.instancePortReference.port.name»" #.. «binding.compositeSystemPort.name»
				«ENDIF»
				'«composite.name» "«binding.compositeSystemPort.name»" #.# "«binding.instancePortReference.port.name»" «binding.instancePortReference.instance.name»
			«ENDFOR»
			
			
			«FOR channel : composite.channels»
				«FOR requiredPort : channel.requiredPorts»
					«channel.providedPort.instance.name» "«channel.providedPort.port.name»" #---0)--# "«requiredPort.port.name»" «requiredPort.instance.name» : "<size:10>//«requiredPort.port.interface.name»//" 
				«ENDFOR»
			«ENDFOR»
		}
		

		@enduml
	'''
	def linkGen(int rank){
		var sb=new StringBuilder
		sb.append("-")
		for (i:0..<1){
			sb.append("-")
		}
		return sb.toString
	}
	def linkGen2(int rank){
		var sb=new StringBuilder
		sb.append(".")
		for (i:0..<0){
			sb.append(".")
		}
		return sb.toString
	}
	
	dispatch def generateDitribution(NormalRandomVariable dist){
		'''Normal(loc=«expressionSerializer.serialize((dist.mean))»,scale=«expressionSerializer.serialize((dist.scale))»)'''
	}
	
	dispatch def generateDitribution(WeibullRandomVariable dist){
		'''Weibull(concentration=«expressionSerializer.serialize((dist.shape))»,shape=«expressionSerializer.serialize((dist.scale))»)'''
	}
	dispatch def generateDitribution(GammaRandomVariable dist){
		'''Gamma(concentration=«expressionSerializer.serialize((dist.shape))»,rate=«expressionSerializer.serialize((dist.scale))»)'''
	}
	dispatch def generateDitribution(UniformRandomVariable dist){
		'''Uniform(low=«expressionSerializer.serialize((dist.lowerBound))»,high=«expressionSerializer.serialize((dist.upperBound))»)'''
	}
	dispatch def generateDitribution(LogNormalRandomVariable dist){
		'''LogNormal(loc=«expressionSerializer.serialize((dist.mean))»,scale=«expressionSerializer.serialize((dist.scale))»)'''
	}
	dispatch def generateDitribution(ParetoRandomVariable dist){
		'''Pareto(alpha=«expressionSerializer.serialize((dist.alpha))»,scale=«expressionSerializer.serialize((dist.scale))»)'''
	}
	dispatch def generateDitribution(BetaRandomVariable dist){
		'''Beta(concentration1=«expressionSerializer.serialize((dist.apha))»,concentration0=«expressionSerializer.serialize((dist.beta))»)'''
	}
	
	dispatch def generateDitribution(ExponentialRandomVariable dist){
		'''Exponential(«expressionSerializer.serialize((dist.rate))»)'''
	}
	
	dispatch def generateDitribution(BernoulliRandomVariable dist){
		'''Bernoulli(«expressionSerializer.serialize((dist.probability))»)'''
	}
	
	dispatch def generateDitribution(CategoricalProbabaility dist){
		'''prob = «expressionSerializer.serialize((dist.probability))»'''
	}
	
	dispatch def generateDitribution(StochasticModel dist){
		'''Stochastic'''
	}
	
	protected static dispatch def filterType(ComponentFilter filter){
		return "*.*"
	}
	
	protected static dispatch def filterType(ParameterFilter filter){
		return '''«filter.event.port.name».«filter.event.event.name»::«filter.parameter.name»'''
	}
	
	protected static dispatch def filterType(EventFilter filter){
		return '''«filter.event.port.name».«filter.event.event.name»'''
	}
	
	protected static dispatch def filterType(PortFilter filter){
		return '''«filter.port.name».*'''
	}
	
	
	protected static dispatch def envType(EnvironmentEventSource comp){
		return "Source"
	}
	
	protected static dispatch def envType(EnvironmentPeriodicEventSource comp){
		return "Periodic Source"
	}
	
	protected static dispatch def envType(EnvironmentSwitch comp){
		return "Switch"
	}
	
	protected static dispatch def envType(EnvironmentDelay comp){
		return "Delay"
	}
	
	protected static dispatch def envType(EnvironmentSample comp){
		return "Sample"
	}
}