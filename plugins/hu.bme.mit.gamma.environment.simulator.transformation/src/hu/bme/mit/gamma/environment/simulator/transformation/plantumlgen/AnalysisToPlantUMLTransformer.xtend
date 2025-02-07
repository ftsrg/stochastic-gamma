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

import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.statechart.composite.AsynchronousComponentInstance
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition
import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance
import hu.bme.mit.gamma.statechart.composite.SynchronousCompositeComponent
import hu.bme.mit.gamma.statechart.statechart.SynchronousStatechartDefinition
import hu.bme.mit.gamma.environment.model.EnvironmentAsynchronousCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance
import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponentInstance

class AnalysisToPlantUMLTransformer {
	
	
	
	def String execute(ComponentInstance inst,String namespace){
	return '''
	@startuml
	«transform(inst,namespace)»
	@enduml
	'''
	}

	def String executeWBS(ComponentInstance inst,String namespace){
	return 
'''@startwbs
«transformWBS(inst,namespace,"*")»
@endwbs'''
	}

	
	def String transform(ComponentInstance inst,String namespace){
		if (inst instanceof EnvironmentAsynchronousCompositeComponentInstance){
			return '''
			component "[[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name» «inst.name» : «inst.type.name»]]" {
				«FOR comp_inst : inst.type.components.sortBy[c|c.name]»
				«transform(comp_inst,namespace+"__"+inst.name.toFirstUpper)»
				«ENDFOR»
			}
			'''
		}
		if (inst instanceof EnvironmentSynchronousCompositeComponentInstance){
			return '''
			component "[[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name» «inst.name» : «inst.type.name»]]" {
				«FOR comp_inst : inst.type.components.sortBy[c|c.name]»
				«transform(comp_inst,namespace+"__"+inst.name)»
				«ENDFOR»
			}
			'''
		} 
		if (inst instanceof EnvironmentCascadeCompositeComponentInstance){
			return '''
			component "[[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name» «inst.name» : «inst.type.name»]]" {
				«FOR comp_inst : inst.type.components.sortBy[c|c.name]»
				«transform(comp_inst,namespace+"__"+inst.name)»
				«ENDFOR»
			}
			'''
		}
		if (inst instanceof AsynchronousComponentInstance){
			val t=inst.type
			if (t instanceof AsynchronousCompositeComponent){
				return '''
				component "[[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name» «inst.name» : «t.name»]]" {
					«FOR comp_inst : t.components»
					«transform(comp_inst,namespace+"__"+inst.name)»
					«ENDFOR»
				}
				'''
			}
			if (t instanceof AsynchronousStatechartDefinition){
				return '''
				component "[[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name» «inst.name» : «t.name»]]"
				'''
			}
		}
		
		if (inst instanceof SynchronousComponentInstance){
		val t=inst.type
		if (t instanceof SynchronousCompositeComponent){
				return '''
				component "[[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name» «inst.name» : «t.name»]]" {
					«FOR comp_inst : t.components»
					«transform(comp_inst,namespace+"__"+inst.name)»
					«ENDFOR»
				}
				'''
		}
		if (t instanceof SynchronousStatechartDefinition){
			return '''
			component "[[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name» «inst.name» : «t.name»]]"
			'''
		}
			
		}
		return ""
	}
	
	
	def String transformWBS(ComponentInstance inst,String namespace,String stars){
		if (inst instanceof EnvironmentAsynchronousCompositeComponentInstance){
			return '''
			«stars» «inst.name» : «inst.type.name» [[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name.toFirstUpper» link]]
			«FOR comp_inst : inst.type.components.sortBy[c|c.name]»
			«transformWBS(comp_inst,namespace+"__"+inst.name.toFirstUpper,stars+"*")»
			«ENDFOR»
			'''
		}
		if (inst instanceof EnvironmentSynchronousCompositeComponentInstance){
			return '''
			«stars» «inst.name» : «inst.type.name» [[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name.toFirstUpper» link]]
			«FOR comp_inst : inst.type.components.sortBy[c|c.name]»
			«transformWBS(comp_inst,namespace+"__"+inst.name.toFirstUpper,stars+"*")»
			«ENDFOR»
			'''
		} 
		if (inst instanceof EnvironmentCascadeCompositeComponentInstance){
			return '''
			«stars» «inst.name» : «inst.type.name» [[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name.toFirstUpper» link]]
			«FOR comp_inst : inst.type.components.sortBy[c|c.name]»
			«transformWBS(comp_inst,namespace+"__"+inst.name.toFirstUpper,stars+"*")»
			«ENDFOR»
			'''
		}
		if (inst instanceof AsynchronousComponentInstance){
			val t=inst.type
			if (t instanceof AsynchronousCompositeComponent){
			return '''
			«stars» «inst.name» : «inst.type.name.toFirstUpper» [[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name.toFirstUpper» link]]
			«FOR comp_inst : t.components.sortBy[c|c.name]»
			«transformWBS(comp_inst,namespace+"__"+inst.name.toFirstUpper,stars+"*")»
			«ENDFOR»
			'''
			}
			if (t instanceof AsynchronousStatechartDefinition){
				return '''
				«stars» «inst.name» : «inst.type.name» [[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name.toFirstUpper» link]]
				'''
			}
		}
		
		if (inst instanceof SynchronousComponentInstance){
		val t=inst.type
		if (t instanceof SynchronousCompositeComponent){
			return '''
			«stars» «inst.name» : «inst.type.name» [[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name.toFirstUpper» link]]
			«FOR comp_inst : t.components.sortBy[c|c.name]»
			«transformWBS(comp_inst,namespace+"__"+inst.name.toFirstUpper,stars+"*")»
			«ENDFOR»
			'''
		}
		if (t instanceof SynchronousStatechartDefinition){
			return '''
			«stars» «inst.name» : «inst.type.name» [[http://localhost:8080/?dname=DiagramCMD--«namespace»__«inst.name» link]]
			'''
		}
			
		}
		return ""
	}
	
	
	
	
}