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

class AttributeData {
		public val String name
		public val long element_id
		public val long type_id
		public val String scope
		public val String GUID
		public val String type
		public val boolean isConst
		public val long lowerBound
		public val long upperBound

		new(
			String name,
			long element_id,
			long type_id,
			String scope,
			String GUID,
			String type,
			boolean isConst,
			long lowerBound,
			long upperBound
		) {
			this.name = name
			this.element_id = element_id
			this.type_id = type_id
			this.scope = scope
			this.GUID = GUID
			this.type = type
			this.isConst=isConst
			this.lowerBound=lowerBound
			this.upperBound=upperBound
		}
}