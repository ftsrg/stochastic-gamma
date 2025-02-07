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

class ObjectPropertyData {
		public val String property
		public val String value
		public val String notes
		public val long elementID

		new(
			String property,
			String value,
			String notes,
			long elementID
		) {
			this.property = property
			this.value = value
			this.notes = notes
			this.elementID = elementID
		}
}