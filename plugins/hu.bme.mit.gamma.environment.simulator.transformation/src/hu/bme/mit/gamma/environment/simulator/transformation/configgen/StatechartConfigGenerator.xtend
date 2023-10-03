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