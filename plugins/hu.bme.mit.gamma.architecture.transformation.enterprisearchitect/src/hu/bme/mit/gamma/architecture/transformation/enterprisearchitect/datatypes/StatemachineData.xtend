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

class StatemachineData {

	public val List<ConnectorData> transitionData
	public val List<XrefData> xrefData
	public val List<XrefData> regionData
	public val List<XrefData> regionContainmentData
	public val List<ElementData> statemachineData
	public val List<ElementData> stateData
	public val List<ElementData> pseudoStateData
	public val List<ElementData> syncStateData
	public val List<AttributeData> attributeData 
	public val List<OperationData> operationData 

	new(EADataLoader eaDataLoader, List<Long> packageIDs) {

		this.transitionData = eaDataLoader.loadAllTransitions
		this.xrefData = eaDataLoader.loadAllXRefData
		this.regionData = xrefData.filter[data|data.isRegionDefinition].toList
		this.regionContainmentData = xrefData.filter[data|data.isRegionContainment].toList
		this.statemachineData = eaDataLoader.loadAllStatemachineData(packageIDs)
		this.stateData = eaDataLoader.loadAllStateData(packageIDs)
		this.pseudoStateData = eaDataLoader.loadAllPseudostateData(packageIDs)
		this.syncStateData = eaDataLoader.loadAllSyncStateData(packageIDs)
		this.attributeData = eaDataLoader.loadAllAttributes
		this.operationData = eaDataLoader.loadAllOperationData

	}

	protected def isRegionDefinition(XrefData data) {
		return data.name == "Partitions" && data.type == "element property"
	}

	protected def isRegionContainment(XrefData data) {
		return data.name == "CustomProperties" && data.type == "element property" &&
			data.description.contains("OwningRegion")
	}

}
