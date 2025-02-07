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

class ElementData {
		
		public val String name
		public val String objectType
		public val String stereotype
		public val long elementID
		public val long conainerID
		public val long packageID
		public val long nType
		public val String GUID
		public val String PDATA1 // property type for packages, primary package ID for packages
		public val String PDATA4 // multiplicity for ports
		public val String multiplicity
		public val String classifierGUID

		new(
			String name,
			String objectType,
			String stereotype,
			long elementID,
			long conainerID,
			long packageID,
			String GUID,
			String PDATA1,
			String PDATA4,
			String multiplicity,
			long nType,
			String classifierGUID
		) {
			this.name = name
			this.objectType = objectType
			this.stereotype = stereotype
			this.elementID = elementID
			this.conainerID = conainerID
			this.packageID = packageID
			this.nType = nType
			this.GUID = GUID
			this.PDATA1 = PDATA1
			this.PDATA4 = PDATA4
			this.multiplicity = multiplicity
			this.classifierGUID=classifierGUID
		}
	

}