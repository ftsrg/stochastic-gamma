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

class ConnectorData {
		public val long sourceID;
		public val long targetID;
		public val String Name;
		public val String Type;
		public val String PDATA1;
		public val String PDATA2;
		public val String PDATA3;
		public val long connectorID;
		public val String GUID

		new(String Name, int sourceID, int targetID, String Type,
			String PDATA1,String PDATA2,String PDATA3,
			long connectorID,String GUID
		) {
			this.Name = Name
			this.Type = Type
			this.sourceID = sourceID
			this.targetID = targetID
			this.PDATA1 = PDATA1
			this.PDATA2 = PDATA2
			this.PDATA3 = PDATA3
			this.connectorID=connectorID
			this.GUID=GUID
		}

		new(String Name, int sourceID, int targetID) {
			this.Name = Name
			this.sourceID = sourceID
			this.targetID = targetID
			this.Type = ""
			this.PDATA1 = ''
			this.PDATA2 = ""
			this.PDATA3 = ""
			this.connectorID=-1
			this.GUID=""
		}
}