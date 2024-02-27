package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes

class ConnectorData {
		public val long sourceID;
		public val long targetID;
		public val String Name;
		public val String Type;
		public val String PDATA1;
		public val String PDATA2;

		new(String Name, int sourceID, int targetID, String Type,String PDATA1,String PDATA2) {
			this.Name = Name
			this.Type = Type
			this.sourceID = sourceID
			this.targetID = targetID
			this.PDATA1 = PDATA1
			this.PDATA2 = PDATA2
		}

		new(String Name, int sourceID, int targetID) {
			this.Name = Name
			this.sourceID = sourceID
			this.targetID = targetID
			this.Type = ""
			this.PDATA1 = ''
			this.PDATA2 = ""
		}
}