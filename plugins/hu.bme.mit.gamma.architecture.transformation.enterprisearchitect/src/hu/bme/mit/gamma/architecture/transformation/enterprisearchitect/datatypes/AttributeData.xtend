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