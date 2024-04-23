package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils

import java.util.List

class SQLUtils {
	
	
	static def getParts(long elementID) '''
	select * from t_object where object_type='Part' and parentid=«elementID»;
	'''
	
	
	static def getPorts(long elementID) '''
	select object_id from t_object where object_type='Port' and parentid=«elementID»;
	'''
	
	static def getAllocations(long elementID) '''
	select END_Object_ID from t_connector where Connector_Type='Abstraction' AND stereotype='allocate' AND Start_Object_ID=«elementID»;
	'''
	
	
	static def getConnectors(long elementID) '''
	select END_Object_ID, Name from t_connector where Connector_Type = 'Connector' AND Start_Object_ID=«elementID»;
	'''
	
	static def getInterfaceConnectors(long elementID) '''
	select 
		t_connector.Start_Object_ID as START_Object_ID, 
		t_connector.End_Object_ID as END_Object_ID, 
		t_connector.Name as Name, 
		t_object.ea_guid as Description
	from t_connector
	left join t_object on t_object.object_id=t_connector.PDATA1
	where 
		t_connector.SubType='Class' AND
		t_connector.Connector_Type='Association' AND
		t_connector.Start_Object_ID=«elementID»;
	'''
	
	
	static def getInformationFlows(long elementID) '''
	select 
		t_connector.Start_Object_ID as START_Object_ID,
		t_connector.END_Object_ID as END_Object_ID,
		t_connector.Name as Name,
		t_xref.Description as Description
	from t_connector
	left join t_xref on t_xref.client=t_connector.ea_guid
	where 
		(t_connector.Connector_Type='InformationFlow') AND 
		(t_connector.Start_Object_ID=«elementID») AND 
		(t_xref.Behavior='conveyed');
	'''
	
	
	static def getConjugatedBool(String GUID)'''
	select Description from t_xref where '«GUID»' = Client;
	'''
	
	static def getReferenceBool(String GUID)'''
	select Description from t_xref where '«GUID»' = Client;
	'''
	
	static def getPkgBlocks(long packageID)'''
	select object_id from t_object 	where Stereotype="block" and Package_ID=«packageID»;
	'''
	
	static def getPkgPackages(long packageID)'''
	select PDATA1 from t_object where object_type='Package' and Package_ID=«packageID»;
	'''
	
	static def getPkgInterfaceBlocks(long packageID)'''
	select object_id
	from t_object
	where
		Object_Type = "Class" and
		Stereotype="InterfaceBlock" and
		Package_ID=«packageID»;
	'''
	
	static def getAllArchitectureElements() '''
	select * 
	from t_object 
	where 
		object_type='Port' or
		object_type='Part' or
		object_type='Class' or
		object_type='Package';
	'''
	
	static def getAllInterfaceBlocks()'''
	select object_id
	from t_object
	where
		Object_Type = "Class" and
		Stereotype="InterfaceBlock"
	'''
	
	static def getAllInterfaceBlocks(long packageID)'''
	select *
	from t_object
	where
		Object_Type = "Class" and
		Stereotype="InterfaceBlock" and
		Package_ID=«packageID»;
	'''
	
	static def getAllInterfaceBlocks(List<Long> packageIDs)'''
	select 
	*
	from t_object 
	where
		Object_Type = "Class" and
		Stereotype="InterfaceBlock" and (
		«FOR packageID : packageIDs SEPARATOR " OR "»
			Package_ID=«packageID»
		«ENDFOR»
		)
	'''
	
	static def getAllInterfaces()'''
	select object_id
	from t_object
	where
		Object_Type = "Interface" 
	'''
	
	static def getAllInterface(long packageID)'''
	select *
	from t_object
	where
		Object_Type = "Interface" and
		Package_ID=«packageID»;
	'''
	
	static def getAllInterface(List<Long> packageIDs)'''
	select 
	*
	from t_object 
	where
		Object_Type = "Interface" and (
		«FOR packageID : packageIDs SEPARATOR " OR "»
			Package_ID=«packageID»
		«ENDFOR»
		)
	'''
	
	static def getAllPackages()'''
	select 
	*
	from t_object 
	where Object_Type = "Package"
	'''
	
	static def getAllXRef()'''
	select 
	*
	from t_xref
	'''
	
	static def getAllObjectProperties()'''
	select 
	*
	from t_objectproperties
	'''
	
	static def getAllBlocks()'''
	select 
	*
	from t_object 
	where
		(Object_Type = "Class" and Stereotype = "block") or 
		Object_Type = "Block"
	'''
	
	static def getAllValueTypes()'''
	select 
	*
	from t_object 
	where
		Object_Type = "DataType" or Object_Type = "ValueType"
	'''
	
	static def getAllValueTypes(List<Long> packageIDs)'''
	select 
	*
	from t_object 
	where
		(Object_Type = "DataType" or Object_Type = "ValueType") and (
		«FOR packageID : packageIDs SEPARATOR " OR "»
			Package_ID=«packageID»
		«ENDFOR»
		)
	'''
	
	static def getAllEnnums(List<Long> packageIDs)'''
	select 
	*
	from t_object 
	where
		(Object_Type = "Enumeration") and (
		«FOR packageID : packageIDs SEPARATOR " OR "»
			Package_ID=«packageID»
		«ENDFOR»
		)
	'''
	
	static def getAllBlocks(long packageID)'''
	select 
	*
	from t_object 
	where
		((Object_Type = "Class" and Stereotype = "block") or 
		Object_Type = "Block") and
		Package_ID=«packageID»
	'''
	
	static def getAllBlocks(List<Long> packageIDs)'''
	select 
	*
	from t_object 
	where
		((Object_Type = "Class" and Stereotype = "block") or 
		Object_Type = "Block") and (
		«FOR packageID : packageIDs SEPARATOR " OR "»
			Package_ID=«packageID»
		«ENDFOR»
		)
	'''
	
	static def getAllInterfaces(long packageID)'''
	select 
	*
	from t_object 
	where
		Object_Type = "Interface" and
		Package_ID=«packageID»
	'''
	
	static def getAllInterfaces(List<Long> packageIDs)'''
	select 
	*
	from t_object 
	where
		Object_Type = "Interface" and (
		«FOR packageID : packageIDs SEPARATOR " OR "»
			Package_ID=«packageID»
		«ENDFOR»
		)
	'''
	
	static def getAllSignals()'''
	select 
	*
	from t_object 
	where
		Object_Type = "Signal" 
	'''
	
	static def getAllSignals(long packageID)'''
	select 
	*
	from t_object 
	where
		Object_Type = "Signal" and
		Package_ID=«packageID»
	'''
	
	static def getAllSignals(List<Long> packageIDs)'''
	select 
	*
	from t_object 
	where
		Object_Type = "Signal" and (
		«FOR packageID : packageIDs SEPARATOR " OR "»
			Package_ID=«packageID»
		«ENDFOR»
		)
	'''
	
	static def getAllStatemachines(List<Long> packageIDs)'''
	select 
	*
	from t_object 
	where
		Object_Type = "Statemachine" and (
		«FOR packageID : packageIDs SEPARATOR " OR "»
			Package_ID=«packageID»
		«ENDFOR»
		)
	'''
	
	static def getAllStates(List<Long> packageIDs)'''
	select 
	*
	from t_object 
	where
		Object_Type = "State" and (
		«FOR packageID : packageIDs SEPARATOR " OR "»
			Package_ID=«packageID»
		«ENDFOR»
		)
	'''
	
	static def getAllPseudostates(List<Long> packageIDs)'''
	select 
	*
	from t_object 
	where
		Object_Type = "StateNode" and (
		«FOR packageID : packageIDs SEPARATOR " OR "»
			Package_ID=«packageID»
		«ENDFOR»
		)
	'''
	
	static def getAllSyncStates(List<Long> packageIDs)'''
	select 
	*
	from t_object 
	where
		Object_Type = "Synchronization" and (
		«FOR packageID : packageIDs SEPARATOR " OR "»
			Package_ID=«packageID»
		«ENDFOR»
		)
	'''
	
	static def getAllFlowProperties(List<Long> packageIDs)'''
	select 
	*
	from t_object 
	where
		(
			Object_Type = "Property" or 
			Object_Type ="Part" or 
			Object_Type ="FlowProperty"
		) and Stereotype="FlowProperty" and
		(
			«FOR packageID : packageIDs SEPARATOR " OR "»
				Package_ID=«packageID»
			«ENDFOR»
		)
	'''
	
	static def getAllFlowProperties()'''
	select 
	*
	from t_object 
	where 
		(
			Object_Type = "Property" or 
			Object_Type ="Part" or 
			Object_Type ="FlowProperty"
		) 
		and Stereotype = "FlowProperty"
	'''
	
	static def getAllPorts()'''
	select 
	*
	from t_object 
	where Object_Type = "Port"
	'''
	
	static def getAllParts()'''
	select 
	*
	from t_object 
	where Object_Type = "Part" and
	(Stereotype is null or Stereotype="" or Stereotype="JunctionPart")
	'''
	
	static def getAllEnumLiterals()'''
	select 
	*
	from t_attribute
	where Stereotype="enum"
	'''
	
	static def getAllAttributes()'''
	select 
	*
	from t_attribute
	where Stereotype is null or Stereotype=""
	'''
	
	static def getAllAttributes2()'''
	select 
	*
	from t_attribute
	'''
	
	static def getAllOperations()'''
	select 
	*
	from t_operation
	'''
	
	static def getAllFlows()'''
	select 
		t_connector.Start_Object_ID as START_Object_ID,
		t_connector.END_Object_ID as END_Object_ID,
		t_connector.Name as Name,
		t_xref.Description as Description
	from t_connector
	left join (select * from t_xref where t_xref.Behavior = 'conveyed') t_xref 
		on  t_connector.ea_guid = t_xref.client 
	where 
		(t_connector.Connector_Type = 'InformationFlow') 
	'''
	
	static def getAllConnectors()'''
	select  t_connector.Start_Object_ID as START_Object_ID,
			t_connector.END_Object_ID as END_Object_ID,
			t_connector.Name as Name,
			t_connector.PDATA1 as PDATA1,
			t_connector.PDATA2 as PDATA2
	from t_connector 
	where Connector_Type = 'Connector'
	'''
	
	static def getAllInterfaceConnectors()'''
	select 
		t_connector.Start_Object_ID as START_Object_ID, 
		t_connector.End_Object_ID as END_Object_ID, 
		t_connector.Name as Name, 
		t_object.ea_guid as Description,
		t_connector.PDATA1 as PDATA1,
		t_connector.PDATA2 as PDATA2
	from t_connector
	left join t_object on t_object.object_id=t_connector.PDATA1
	where 
		t_connector.SubType = 'Class' AND
		t_connector.Connector_Type = 'Association'
	'''
	
	static def getAllAllocations()'''
	select	t_connector.Start_Object_ID as START_Object_ID, 
			t_connector.End_Object_ID as END_Object_ID, 
			t_connector.Name as Name
	from t_connector 
	where 
		Connector_Type = 'Abstraction' AND
		stereotype = 'allocate'
	'''
	
	static def getAllRealisations()'''
	select	t_connector.Start_Object_ID as START_Object_ID, 
			t_connector.End_Object_ID as END_Object_ID, 
			t_connector.Name as Name
	from t_connector 
	where 
		Connector_Type = 'Realisation'
	'''
	
	static def getAllTransitions()'''
	select	t_connector.Start_Object_ID as START_Object_ID, 
			t_connector.End_Object_ID as END_Object_ID, 
			t_connector.Name as Name,
			PDATA1,
			PDATA2,
			PDATA3
	from t_connector 
	where 
		Connector_Type = 'StateFlow'
	'''
	
	static def getAllGeneralization()'''
	select	t_connector.Start_Object_ID as START_Object_ID, 
			t_connector.End_Object_ID as END_Object_ID, 
			t_connector.Name as Name
	from t_connector 
	where 
		Connector_Type = 'Generalization'
	'''
	
	static def getAllTraces()'''
	select	t_connector.Start_Object_ID as START_Object_ID, 
			t_connector.End_Object_ID as END_Object_ID, 
			t_connector.Name as Name
	from t_connector 
	where 
		Connector_Type = 'Dependency' and (Stereotype is null or Stereotype="Trace" or Stereotype="")
	'''
	
	static def getAllObjectFiles()'''
	select	*
	from t_objectfiles
	'''
	
	static def getAllArtifacts(List<Long> packageIDs)'''
	select 
	*
	from t_object 
	where
		Object_Type = "Artifact" and (
		«FOR packageID : packageIDs SEPARATOR " OR "»
			Package_ID=«packageID»
		«ENDFOR»
		)
	'''
	

}