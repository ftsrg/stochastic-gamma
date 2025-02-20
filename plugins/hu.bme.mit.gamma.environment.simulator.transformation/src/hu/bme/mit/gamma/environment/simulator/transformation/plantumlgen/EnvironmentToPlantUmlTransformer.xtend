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
package hu.bme.mit.gamma.environment.simulator.transformation.plantumlgen

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

import static extension hu.bme.mit.gamma.environment.simulator.transformation.util.Util.*
import com.google.common.collect.Table
import com.google.common.collect.HashBasedTable
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.statechart.interface_.Port
import java.util.List
import hu.bme.mit.gamma.statechart.composite.InstancePortReference
import java.util.Set
import java.util.Random

class EnvironmentToPlantUmlTransformer {

	protected final CompositeComponent composite

	protected extension ExpressionSerializer expressionSerializer = ExpressionSerializer.INSTANCE

	protected int rank = 0
	protected int rank2 = 0
	protected int noteCNTR = 0

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
		} else if (composite instanceof AsynchronousCompositeComponent) {
			return "asynchronous"
		}
	}
	
	def String execute(){
		if (composite.channels.size>40){
			return execute4
		}else{
			return execute3
		}
	}

	def String execute4() {

		val Table<ComponentInstance, ComponentInstance, List<Set<String>>> table = HashBasedTable.create

		for (channel : composite.channels) {
			val provPort = channel.providedPort.port
			val provInst = channel.providedPort.instance
			for (reqPortRef : channel.requiredPorts) {
				val reqPort = reqPortRef.port
				val reqInst = reqPortRef.instance
				val str = '''«provPort.name»,«reqPort.name»'''
				if ((!table.contains(provInst, reqInst)) && (!table.contains(reqInst, provInst))) {
					val List<Set<String>> l1=<Set<String>>newArrayList(<String>newHashSet,<String>newHashSet)
					l1.get(0).add(provPort.name)
					l1.get(1).add(reqPort.name)
					table.put(provInst, reqInst, l1)
				} else if (table.contains(provInst, reqInst)) {
					val d=table.get(provInst, reqInst)
					d.get(0).add(provPort.name)
					d.get(1).add(reqPort.name)
				} else {
					val d=table.get(reqInst, provInst)
					d.get(0).add(reqPort.name)
					d.get(1).add(provPort.name)
				}
			}
		}
		val rnd=new Random
		return '''
			@startuml
			
			allowmixing
			!pragma layout  elk
			
			<style>
			title {
			  FontSize 12
			}
			</style>
			
			skinparam component {
				backgroundColor DarkKhaki
			}
			
			skinparam shadowing false
			'skinparam linetype ortho
			!theme plain
			'left to right direction
			'top to bottom direction
			skinparam nodesep 100
			skinparam ranksep 100
			skinparam defaultTextAlignment center
			skinparam ComponentStereotypeFontSize 10
			skinparam HeaderFontSize 12
			skinparam padding 2
			'skinparam componentStyle rectangle
			
			component "«composite.name»"<<«composite.kindString»>> {
				
				«FOR component : composite.derivedComponents.sortBy[c|c.name]»
					component "<size:12>«component.name»:\n<size:12>«component.derivedType.name»" as «component.name» #EEEEEE  {
««««						json «component.name»_Outputs {
««««						«FOR port : component.derivedType.allPortsWithOutput SEPARATOR ", \n"»"«port.name»" : [«FOR param : port.parameters SEPARATOR ", "»"«key(port,param,component)»"«ENDFOR»]«ENDFOR»
						
««««						}
					}
					note right of «component.name» 
							«FOR port : component.derivedType.allPortsWithOutput SEPARATOR ""»
					| «port.name» | «FOR param : port.parameters SEPARATOR "\\n "»«key(port,param,component)»«ENDFOR» |
							«ENDFOR»
					endnote
				«ENDFOR»
			
				«FOR component : composite.environmentComponents.sortBy[c|c.name]»
				component "<size:12>«component.name» \n----\n«FOR rule : (component as ElementaryEnvironmentComponentInstance).behaviorRules»«IF rule instanceof StochasticRule»<size:10>«FOR filter : rule.filter»«filterType(filter)» «ENDFOR»: «generateDitribution(rule.stochasticModel)»\n«ENDIF»«ENDFOR»" as «component.name» <<Stochastic «envType(component as ElementaryEnvironmentComponentInstance)»>>{
			
					}
				«ENDFOR»
				
				«FOR port : composite.ports»
				«IF port.interfaceRealization.realizationMode == RealizationMode.REQUIRED»
					'portin "«port.name»:\n ~«port.interface.name»" as «port.name»
				«ENDIF»
				«IF port.interfaceRealization.realizationMode == RealizationMode.PROVIDED»
					'portout "«port.name»:\n «port.interface.name»" as «port.name»
				«ENDIF»
				«ENDFOR»
				
				«FOR binding : composite.portBindings»
				«IF binding.instancePortReference.port.interfaceRealization.realizationMode == RealizationMode.REQUIRED»
					'«binding.compositeSystemPort.name» ..d.> «binding.instancePortReference.instance.name»
				«ENDIF»
				«IF binding.instancePortReference.port.interfaceRealization.realizationMode == RealizationMode.PROVIDED»
					'«binding.instancePortReference.instance.name» ..d.> «binding.compositeSystemPort.name»
				«ENDIF»
				«ENDFOR»
				
				
				«FOR provInst : table.rowKeySet»
				«FOR reqInst : table.columnKeySet»
					«IF !(table.get(provInst,reqInst).isNullOrEmpty)»
											«provInst.name» "«FOR p: table.get(provInst,reqInst).get(0) SEPARATOR ", \\n"»«p»«ENDFOR» " #---# "«FOR p: table.get(provInst,reqInst).get(1) SEPARATOR ", \\n"»«p»«ENDFOR» " «reqInst.name» 
«««											«provInst.name» "«rnd.nextInt(99)»" #--# "«rnd.nextInt(99)»" «reqInst.name» 
					«ENDIF»
				«ENDFOR»
				«ENDFOR»
			}
				
				
«««			«FOR port : composite.ports»
«««				«port.note»
«««			«ENDFOR»
				
			@enduml
		'''
	}

	def String execute2() '''
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
				component «component.name» [
				«component.name» : Stochastic «envType(component as ElementaryEnvironmentComponentInstance)»
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
				«channel.providedPort.instance.name» "«channel.providedPort.port.name»" #--0)--# "«requiredPort.port.name»" «requiredPort.instance.name» : "<size:10>//«requiredPort.port.interface.name»//" 
			«ENDFOR»
			«ENDFOR»
		'}
		
		
		@enduml
	'''

	def String execute3() '''
		@startuml
		<style>
		title {
		  FontSize 12
		}
		</style>
		
		skinparam shadowing false
		'skinparam linetype ortho
		!theme plain
		'left to right direction
		top to bottom direction
		skinparam nodesep 60
		skinparam ranksep 30
		skinparam defaultTextAlignment center
		skinparam ComponentStereotypeFontSize 10
		skinparam HeaderFontSize 12
		skinparam padding 1
		skinparam componentStyle rectangle
		
		component "«composite.name»"<<«composite.kindString»>> {
			
			«FOR component : composite.derivedComponents»
				component "<size:12>«component.name»:\n<size:12>«component.derivedType.name»" as «component.name»  {
					«FOR port : component.derivedType.allPorts»
						«IF true»
							«IF port.interfaceRealization.realizationMode == RealizationMode.REQUIRED»
								portin "«port.name»:\n ~«port.interface.name» " as «component.name»__«port.name»
							«ENDIF»
							«IF port.interfaceRealization.realizationMode == RealizationMode.PROVIDED»
								portout "«port.name»:\n «port.interface.name»" as «component.name»__«port.name»
								«port.note(component)»
							«ENDIF»
						«ENDIF»
					«ENDFOR»
				}
				«component.note»
			«ENDFOR»
		
			«FOR component : composite.environmentComponents»
			component "<size:12>«component.name» \n----\n«FOR rule : (component as ElementaryEnvironmentComponentInstance).behaviorRules»«IF rule instanceof StochasticRule»<size:10>«FOR filter : rule.filter»«filterType(filter)» «ENDFOR»: «generateDitribution(rule.stochasticModel)»\n«ENDIF»«ENDFOR»" as «component.name» <<Stochastic «envType(component as ElementaryEnvironmentComponentInstance)»>>{
			«FOR port : (component as ElementaryEnvironmentComponentInstance).inports»
				«IF true»
					portin "«port.name»\n ~«port.interface.name»" as «component.name»__«port.name»
				«ENDIF»
			«ENDFOR»
			«FOR port : (component as ElementaryEnvironmentComponentInstance).outports»
				«IF true»
					portout "«port.name»:\n «port.interface.name»" as «component.name»__«port.name»
				«ENDIF»
			«ENDFOR»
			}
			«ENDFOR»
			
			«FOR port : composite.ports»
			«IF port.interfaceRealization.realizationMode == RealizationMode.REQUIRED»
				portin "«port.name»:\n ~«port.interface.name»" as «port.name»
			«ENDIF»
			«IF port.interfaceRealization.realizationMode == RealizationMode.PROVIDED»
				portout "«port.name»:\n «port.interface.name»" as «port.name»
			«ENDIF»
			«ENDFOR»
			
			«FOR binding : composite.portBindings»
			«IF binding.instancePortReference.port.interfaceRealization.realizationMode == RealizationMode.REQUIRED»
				«binding.compositeSystemPort.name» .d.> «binding.instancePortReference.instance.name»__«binding.instancePortReference.port.name»
			«ENDIF»
			«IF binding.instancePortReference.port.interfaceRealization.realizationMode == RealizationMode.PROVIDED»
				«binding.instancePortReference.instance.name»__«binding.instancePortReference.port.name» .d.> «binding.compositeSystemPort.name»
			«ENDIF»
			'«composite.name» "«binding.compositeSystemPort.name»" #.# "«binding.instancePortReference.port.name»" «binding.instancePortReference.instance.name»
			«ENDFOR»
			
			
			«FOR channel : composite.channels»
			«FOR requiredPort : channel.requiredPorts»
				«IF (!channel.providedPort.port.outputEvents.empty) && (channel.providedPort.port.inputEvents.empty)»
					«channel.providedPort.instance.name»__«channel.providedPort.port.name» ----> «requiredPort.instance.name»__«requiredPort.port.name»
				«ELSEIF (channel.providedPort.port.outputEvents.empty) && (!channel.providedPort.port.inputEvents.empty)»
					«channel.providedPort.instance.name»__«channel.providedPort.port.name» <---- «requiredPort.instance.name»__«requiredPort.port.name»
				«ELSE»
					«channel.providedPort.instance.name»__«channel.providedPort.port.name» <----> «requiredPort.instance.name»__«requiredPort.port.name»
				«ENDIF»
			«ENDFOR»
			«ENDFOR»
		}
		
		
		«FOR port : composite.ports»
			«port.note»
		«ENDFOR»
		
		@enduml
	'''

	def linkGen(int rank) {
		var sb = new StringBuilder
		sb.append("-")
		for (i : 0 ..< 1) {
			sb.append("-")
		}
		return sb.toString
	}

	def linkGen2(int rank) {
		var sb = new StringBuilder
		sb.append(".")
		for (i : 0 ..< 0) {
			sb.append(".")
		}
		return sb.toString
	}

	dispatch def generateDitribution(NormalRandomVariable dist) {
		'''Normal(loc=«expressionSerializer.serialize((dist.mean))»,scale=«expressionSerializer.serialize((dist.scale))»)'''
	}

	dispatch def generateDitribution(WeibullRandomVariable dist) {
		'''Weibull(concentration=«expressionSerializer.serialize((dist.shape))»,shape=«expressionSerializer.serialize((dist.scale))»)'''
	}

	dispatch def generateDitribution(GammaRandomVariable dist) {
		'''Gamma(concentration=«expressionSerializer.serialize((dist.shape))»,rate=«expressionSerializer.serialize((dist.scale))»)'''
	}

	dispatch def generateDitribution(UniformRandomVariable dist) {
		'''Uniform(low=«expressionSerializer.serialize((dist.lowerBound))»,high=«expressionSerializer.serialize((dist.upperBound))»)'''
	}

	dispatch def generateDitribution(LogNormalRandomVariable dist) {
		'''LogNormal(loc=«expressionSerializer.serialize((dist.mean))»,scale=«expressionSerializer.serialize((dist.scale))»)'''
	}

	dispatch def generateDitribution(ParetoRandomVariable dist) {
		'''Pareto(alpha=«expressionSerializer.serialize((dist.alpha))»,scale=«expressionSerializer.serialize((dist.scale))»)'''
	}

	dispatch def generateDitribution(BetaRandomVariable dist) {
		'''Beta(concentration1=«expressionSerializer.serialize((dist.apha))»,concentration0=«expressionSerializer.serialize((dist.beta))»)'''
	}

	dispatch def generateDitribution(ExponentialRandomVariable dist) {
		'''Exponential(«expressionSerializer.serialize((dist.rate))»)'''
	}

	dispatch def generateDitribution(BernoulliRandomVariable dist) {
		'''Bernoulli(«expressionSerializer.serialize((dist.probability))»)'''
	}

	dispatch def generateDitribution(CategoricalProbabaility dist) {
		'''prob = «expressionSerializer.serialize((dist.probability))»'''
	}

	dispatch def generateDitribution(StochasticModel dist) {
		'''Stochastic'''
	}

	protected static dispatch def filterType(ComponentFilter filter) {
		return "*.*"
	}

	protected static dispatch def filterType(ParameterFilter filter) {
		return '''«filter.event.port.name».«filter.event.event.name»::«filter.parameter.name»'''
	}

	protected static dispatch def filterType(EventFilter filter) {
		return '''«filter.event.port.name».«filter.event.event.name»'''
	}

	protected static dispatch def filterType(PortFilter filter) {
		return '''«filter.port.name».*'''
	}

	protected static dispatch def envType(EnvironmentEventSource comp) {
		return "Source"
	}

	protected static dispatch def envType(EnvironmentPeriodicEventSource comp) {
		return "Periodic Source"
	}

	protected static dispatch def envType(EnvironmentSwitch comp) {
		return "Switch"
	}

	protected static dispatch def envType(EnvironmentDelay comp) {
		return "Delay"
	}

	protected static dispatch def envType(EnvironmentSample comp) {
		return "Sample"
	}
}
