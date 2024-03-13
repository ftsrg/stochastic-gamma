package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect

import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.FileData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability.ElementTrace
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ElementData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ObjectFileData
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.action.model.ActionModelFactory
import java.io.File
import java.io.FileInputStream
import hu.bme.mit.gamma.logictable.transformation.TableTransformation2

class TableAppender {
	val FileData fileData
	val ElementTrace elementTrace
	val ArchitectureTrace architectureTrace
	val fileMap = <AsynchronousStatechartDefinition, String>newHashMap

	val expModelFactory = ExpressionModelFactory.eINSTANCE
	val sctModelFactory = StatechartModelFactory.eINSTANCE
	val ifModelFactory = InterfaceModelFactory.eINSTANCE
	val actModelFactory = ActionModelFactory.eINSTANCE

	new(ArchitectureTrace architectureTrace, ElementTrace elementTrace) {
		this.fileData = elementTrace.fileData
		this.elementTrace = elementTrace
		this.architectureTrace = architectureTrace
	}

	def execute() {

		val artifactMap = <Long, ElementData>newHashMap
		val filedataMap = <Long, ObjectFileData>newHashMap

		for (data : fileData.artifactData) {
			artifactMap.put(data.elementID, data)
		}

		for (data : fileData.fileData) {
			if (elementTrace.contains(data.elementID)) {
				val namedElement = architectureTrace.get(elementTrace.get(data.elementID))
				if (namedElement instanceof AsynchronousStatechartDefinition) {
					fileMap.put(namedElement, data.name)
				} else {
					filedataMap.put(data.elementID, data)
				}
			} else {
				filedataMap.put(data.elementID, data)
			}
		}

		for (data : fileData.tracingData) {
			if (artifactMap.containsKey(data.targetID)) {
				val artifact = artifactMap.get(data.targetID)
				val source = architectureTrace.get(elementTrace.get(data.sourceID))
				if (source instanceof AsynchronousStatechartDefinition) {
					if (filedataMap.containsKey(artifact.elementID)) {
						fileMap.put(source, filedataMap.get(artifact.elementID).name)
					}
				}
			}
		}
		val interfaces=architectureTrace.interfacePackage.interfaces
		val transformation=new TableTransformation2
		for (sct:fileMap.keySet){
			val file=fileMap.get(sct)
			transformation.generate(file,interfaces,sct)
		}

	}

}
