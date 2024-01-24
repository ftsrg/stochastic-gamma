package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes

class XrefData {
		public val String name
		public val String type
		public val String client
		public val String supplier
		public val String link
		public val String description

		new(
			String name,
			String type,
			String client,
			String supplier,
			String link,
			String description
		) {
			this.name = name
			this.type = type
			this.client = client
			this.supplier = supplier
			this.link = link
			this.description = description
		}
}