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
package hu.bme.mit.gamma.environment.simulator.transformation.configgen

import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter
import java.util.Set
import hu.bme.mit.gamma.statechart.composite.CompositeComponent
import static extension hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures.*
import hu.bme.mit.gamma.environment.analysis.AnalysisComponent
import java.util.HashSet

class StatechartConfigGenerator {
	
	def collectStatecharts(ComponentInstance instance, Set<StatechartDefinition> statecharts){
		var component = instance.derivedType
		if (component instanceof StatechartDefinition){
			statecharts.add(component)
		} else if (component instanceof AsynchronousAdapter){
			collectStatecharts(component.wrappedComponent,statecharts)
		}else  if (component instanceof CompositeComponent){
			for (inst : component.derivedComponents){
				collectStatecharts(inst,statecharts)
			}
		}
	}
	
	def generate(AnalysisComponent analysisComponent){
		var statecharts=new HashSet<StatechartDefinition>
		collectStatecharts(analysisComponent.analyzedComponent,statecharts)
		return
'''
«FOR statechart : statecharts»
	«statechart.name» : [«FOR region : statechart.allRegions SEPARATOR ", "»"«region.name»"«ENDFOR»]
«ENDFOR»
'''
	}
}