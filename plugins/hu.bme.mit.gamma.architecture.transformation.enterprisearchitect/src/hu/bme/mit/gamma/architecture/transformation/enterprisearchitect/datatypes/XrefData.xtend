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

class XrefData {
		public val String name
		public val String type
		public val String client
		public val String supplier
		public val String link
		public val String description
		public val String behavior

		new(
			String name,
			String type,
			String client,
			String supplier,
			String link,
			String description,
			String behavior
		) {
			this.name = name
			this.type = type
			this.client = client
			this.supplier = supplier
			this.link = link
			this.description = description
			this.behavior = behavior
		}
}