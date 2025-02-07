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

class OperationData {
		public val String name
		public val String type
		public val long element_id
		public val String style_ex
		public val String GUID
		public val String scope

		new(
			String name,
			String type,
			long element_id,
			String style_ex,
			String GUID,
			String scope
		) {
			this.name = name
			this.type = type
			this.element_id = element_id
			this.style_ex = style_ex
			this.GUID = GUID
			this.scope=scope
		}
}