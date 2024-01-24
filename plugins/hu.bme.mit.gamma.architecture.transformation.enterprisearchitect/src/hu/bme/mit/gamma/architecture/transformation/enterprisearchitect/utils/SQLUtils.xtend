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
	select object_id
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
	
	static def getAllPackages()'''
	select 
	*
	from t_object 
	where Object_Type = "Package"
	'''
	
	static def getAllBlocks()'''
	select 
	*
	from t_object 
	where
		(Object_Type = "Class" and Stereotype = "block") or 
		Object_Type = "Block"
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
	(Stereotype is null or Stereotype="")
	'''
	
	static def getAllFlows()'''
	select 
		t_connector.Start_Object_ID as START_Object_ID,
		t_connector.END_Object_ID as END_Object_ID,
		t_connector.Name as Name,
		t_xref.Description as Description
	from t_connector
	left join t_xref on t_xref.client = t_connector.ea_guid
	where 
		(t_connector.Connector_Type = 'InformationFlow') AND
		(t_xref.Behavior = 'conveyed');
	'''
	
	static def getAllConnectors()'''
	select  t_connector.Start_Object_ID as START_Object_ID,
			t_connector.END_Object_ID as END_Object_ID,
			t_connector.Name as Name
	from t_connector 
	where Connector_Type = 'Connector'
	'''
	
	static def getAllInterfaceConnectors()'''
	select 
		t_connector.Start_Object_ID as START_Object_ID, 
		t_connector.End_Object_ID as END_Object_ID, 
		t_connector.Name as Name, 
		t_object.ea_guid as Description
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
	

}