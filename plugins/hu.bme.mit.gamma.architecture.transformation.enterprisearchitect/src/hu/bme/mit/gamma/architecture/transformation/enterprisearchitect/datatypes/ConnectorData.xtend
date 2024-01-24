package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes

class ConnectorData {
		public val int sourceID;
		public val int targetID;
		public val String Name;
		public val String Type;

		new(String Name, int sourceID, int targetID, String Type) {
			this.Name = Name
			this.Type = Type
			this.sourceID = sourceID
			this.targetID = targetID
		}

		new(String Name, int sourceID, int targetID) {
			this.Name = Name
			this.sourceID = sourceID
			this.targetID = targetID
			this.Type = ""
		}
}