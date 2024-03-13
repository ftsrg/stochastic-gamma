package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes

import java.util.List
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.EADataLoader

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*

class FileData {

	public val List<ConnectorData> tracingData
	public val List<ElementData> artifactData
	public val List<ObjectFileData> fileData

	new(EADataLoader eaDataLoader, List<Long> packageIDs) {

		this.tracingData = eaDataLoader.loadAllTraces
		this.artifactData = eaDataLoader.loadAllArtifact(packageIDs)
		this.fileData= eaDataLoader.loadAllObjectFileData.filter[d|d.name.matches('''.*(xlsx|xls|xlsm)$''')].toList

	}

}
