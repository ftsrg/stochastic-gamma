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
