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