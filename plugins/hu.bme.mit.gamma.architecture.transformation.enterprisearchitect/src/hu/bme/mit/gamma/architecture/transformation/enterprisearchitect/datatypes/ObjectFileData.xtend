package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes

class ObjectFileData {
		
		public val String name
		public val String type
		public val long elementID

		new(
			String name,
			String type,
			long elementID
		) {
			this.name = name
			this.type = type
			this.elementID = elementID
		}
	

}