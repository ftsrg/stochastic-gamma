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

import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
import hu.bme.mit.gamma.statechart.interface_.Interface
import java.util.List
import java.util.ArrayList
import hu.bme.mit.gamma.expression.util.TypeSerializer
import hu.bme.mit.gamma.expression.model.EnumerationTypeDefinition

class InterfaceToPlantUmlTransformer {
	
	protected final List<Interface>  interfaces
	protected final List<EnumerationTypeDefinition> enums
	protected extension ExpressionSerializer expressionSerializer = ExpressionSerializer.INSTANCE
	protected extension TypeSerializer typeSerializer = TypeSerializer.INSTANCE
	
	protected  List<Interface>  externalParents=new ArrayList()
	
	new(List<Interface> interfaces,List<EnumerationTypeDefinition> enums) {
		this.interfaces = interfaces
		this.enums=enums
	}
	//
	
	def String execute() '''
		@startuml
		skinparam shadowing false
		
		«FOR _enum : enums »
			enum «_enum.serialize» {
				«FOR item : _enum.literals»
					«item.name»
				«ENDFOR»
			}
		«ENDFOR»
		
		«FOR _interface : interfaces»
			«ifGenerate(_interface)»
			«FOR parent : _interface.parents»
				«IF interfaces.contains(parent)»
					«_interface.name» --|> «parent.name»
				«ELSEIF externalParents.contains(parent)»
					«_interface.name» --|> «parent.name»
				«ELSE»
					'«externalParents.add(parent)»
					package «parent.containingPackage.name» {
						«ifGenerate(parent)»
					}
					«_interface.name» --|> «parent.name»
				«ENDIF»
			«ENDFOR»
		«ENDFOR»
		
		@enduml
	'''
	
	def ifGenerate(Interface _interface)
	'''
	interface «_interface.name» {
	«FOR event : _interface.events»
		«event.direction.name().toLowerCase» event «event.event.name» («FOR param : event.event.parameterDeclarations SEPARATOR ", "»«param.name» : «param.type.serialize»«ENDFOR»)
	«ENDFOR»
	}
	'''
	
}