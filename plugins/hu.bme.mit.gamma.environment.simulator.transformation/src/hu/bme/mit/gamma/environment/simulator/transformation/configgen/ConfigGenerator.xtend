package hu.bme.mit.gamma.environment.simulator.transformation.configgen

import hu.bme.mit.gamma.environment.analysis.AnalysisComponent
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition
import hu.bme.mit.gamma.statechart.composite.CompositeComponent
import static extension hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures.*
import hu.bme.mit.gamma.statechart.interface_.Package
import static extension hu.bme.mit.gamma.environment.simulator.transformation.util.Util.*


import java.util.List
import hu.bme.mit.gamma.xsts.transformation.GammaToXstsTransformer
import hu.bme.mit.gamma.statechart.interface_.Event
import java.util.LinkedList
import java.util.Set

class ConfigGenerator {
	
	 def generateMapping(ComponentInstance instance,String namespace,List<String> diagramNames){
		var component = instance.derivedType
		var namespace2='''«namespace»__«instance.name.toFirstUpper»'''
		if (component instanceof AsynchronousStatechartDefinition){
			
		} else if (component instanceof StatechartDefinition){
			
		} else if (component instanceof AsynchronousAdapter){
			diagramNames+=
'''
«namespace2» : 
    dummy : "dummy"
    «FOR port : component.wrappedComponent.derivedType.ports»
    	«FOR param : port.parameters»
    		«port.key(param)» : "«port.name.toFirstUpper»::«param.name.toFirstUpper»"
    	«ENDFOR»
    «ENDFOR»
'''
			generateMapping(component.wrappedComponent,namespace2, diagramNames)
		}else  if (component instanceof CompositeComponent){
			diagramNames+=
'''
«namespace2» : 
    dummy : "dummy"
    «FOR port : component.ports»
    	«FOR param : port.parameters»
    		«port.key(param)» : "«port.name.toFirstUpper»::«param.name.toFirstUpper»"
    	«ENDFOR»
    «ENDFOR»
    «FOR inst : component.derivedComponents»
    	«FOR port : inst.derivedType.allPorts.filter[p|p.interfaceRealization.provided]»
    		«FOR param : port.parameters»
    			«port.key(param,inst)» : "«inst.name.toFirstUpper»::«port.name.toFirstUpper»::«param.name.toFirstUpper»"
    		«ENDFOR»
    	«ENDFOR»
    	«IF inst.derivedType instanceof StatechartDefinition»
    		«inst.keyword» : "__STATECHART__«inst.name.toFirstUpper»"
    	«ENDIF»
    «ENDFOR»
'''
			for (inst : component.allInstances){
				generateMapping(inst,namespace2, diagramNames)
			}
		}
	}
	
	def generate(AnalysisComponent analysisComponent){
		var mappings=new LinkedList<String>
		generateMapping(analysisComponent.analyzedComponent, analysisComponent.name,mappings)
		return
'''
«FOR mapping : mappings»
	«mapping»
«ENDFOR»

'''
	}
	

}