package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils

import javax.xml.parsers.DocumentBuilderFactory
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets
import java.util.MissingFormatArgumentException
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ConnectorData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.XrefData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ElementData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ObjectPropertyData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.OperationData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.AttributeData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ObjectFileData

class XMLUtils {

	static val DATASET = "Dataset_0"

	static def parseXML(String xml) {
		var xmlstream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_16))
		var documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
		var document = documentBuilder.parse(xmlstream)
		return document
	}

	static def getElementIDs(String xml) {
		var idList = <Integer>newLinkedList
		var document = parseXML(xml)
		var object_id_nodes = document.getElementsByTagName("Object_ID")
		for (i : 0 .. object_id_nodes.length) {
			idList.add(Integer.valueOf(object_id_nodes.item(i).textContent))
		}
		return idList
	}

	static def getElementData(String xml) {
		var dataList = <ElementData>newLinkedList
		var document = parseXML(xml)
		var row_nodes = document.getElementsByTagName("Row")
		if (row_nodes.length > 0) {
			for (i : 0 .. row_nodes.length - 1) {
				var fields = row_nodes.item(i).childNodes
				var String name
				var String objectType
				var String stereotype
				var long elementID = -1
				var long conainerID = -1
				var long packageID = -1
				var long nType = -1
				var String GUID = null
				var String PDATA1
				var String multiplicity
				var String PDATA4

				for (j : 0 .. fields.length - 1) {
					var field = fields.item(j)
					if (field !== null) {
						if (field.nodeName == "Name") {
							name = field.textContent
						}
						if (field.nodeName == "Object_Type") {
							objectType = field.textContent
						}
						if (field.nodeName == "Stereotype") {
							stereotype = field.textContent
						}
						if (field.nodeName == "ea_guid") {
							GUID = field.textContent
						}
						if (field.nodeName == "PDATA1") {
							PDATA1 = field.textContent
						}
						if (field.nodeName == "Multiplicity") {
							multiplicity = field.textContent
						}
						if (field.nodeName == "PDATA4") {
							PDATA4 = field.textContent
						}
						if (field.nodeName == "Object_ID") {
							elementID = Long.valueOf(field.textContent)
						}
						if (field.nodeName == "ParentID" || field.nodeName == "Parent_ID") {
							conainerID = Long.valueOf(field.textContent)
						}
						if (field.nodeName == "Package_ID") {
							packageID = Long.valueOf(field.textContent)
						}
						if (field.nodeName == "NType") {
							nType = Long.valueOf(field.textContent)
						}
					}
				}
				if (elementID == -1) {
					throw new MissingFormatArgumentException("Object ID of element is not in XML : " + xml)
				}
				if (conainerID == -1) {
					throw new MissingFormatArgumentException("Container ID of element is not in XML : " + xml)
				}
				if (packageID == -1) {
					throw new MissingFormatArgumentException("Package ID of element is not in XML : " + xml)
				}
				if (GUID === null || GUID == "") {
					throw new MissingFormatArgumentException("GUID ID of element is not in XML : " + xml)
				}
				dataList.add(new ElementData(
					name,
					objectType,
					stereotype,
					elementID,
					conainerID,
					packageID,
					GUID,
					PDATA1,
					PDATA4,
					multiplicity,
					nType
				))
			}
		}
		return dataList
	}

	static def getOperationData(String xml) {
		var dataList = <OperationData>newLinkedList
		var document = parseXML(xml)
		var row_nodes = document.getElementsByTagName("Row")
		for (i : 0 .. row_nodes.length - 1) {
			var fields = row_nodes.item(i).childNodes
			var String name
			var long element_id
			var String type
			var String style_ex = ""
			var String GUID
			var String scope

			for (j : 0 .. fields.length - 1) {
				var field = fields.item(j)
				if (field.nodeName == "Name") {
					name = field.textContent
				}
				if (field.nodeName == "Object_ID") {
					element_id = Long.valueOf(field.textContent)
				}
				if (field.nodeName == "Type") {
					type = field.textContent
				}
				if (field.nodeName == "ea_guid") {
					GUID = field.textContent
				}
				if (field.nodeName == "Scope") {
					scope = field.textContent
				}
				if (field.nodeName == "StyleEx") {
					style_ex = field.textContent
				}
			}
			dataList.add(new OperationData(
				name,
				type,
				element_id,
				style_ex,
				GUID,
				scope
			))
		}
		return dataList
	}

	static def getAttributeData(String xml) {
		var dataList = <AttributeData>newLinkedList
		var document = parseXML(xml)
		var row_nodes = document.getElementsByTagName("Row")
		for (i : 0 .. row_nodes.length - 1) {
			var fields = row_nodes.item(i).childNodes

			var String name
			var long element_id
			var long type_id
			var String scope
			var String GUID
			var String type
			var boolean isConst
			var long lowerBound
			var long upperBound

			for (j : 0 .. fields.length - 1) {
				var field = fields.item(j)
				if (field.nodeName == "Name") {
					name = field.textContent
				}
				if (field.nodeName == "Object_ID") {
					element_id = Long.valueOf(field.textContent)
				}
				if (field.nodeName == "Classifier") {
					if (field.textContent.isBlank){
						type_id = -1
					}else{
						type_id = Long.valueOf(field.textContent)
					}
				}
				if (field.nodeName == "LowerBound") {
					if (field.textContent.isBlank){
						lowerBound = -1
					}else{
						lowerBound = Long.valueOf(field.textContent)
					}
				}
				if (field.nodeName == "UpperBound") {
					if (field.textContent.isBlank){
						upperBound = -1
					}else{
						upperBound = Long.valueOf(field.textContent)
					}
				}
				if (field.nodeName == "Const") {
					if (field.textContent.isBlank){
						isConst = false
					}else{
						isConst = Long.valueOf(field.textContent)!=0
					}
				}
				if (field.nodeName == "Type") {
					type = field.textContent
				}
				if (field.nodeName == "Scope") {
					scope = field.textContent
				}
				if (field.nodeName == "ea_guid") {
					GUID = field.textContent
				}
			}
			dataList.add(new AttributeData(
				name,
				element_id,
				type_id,
				scope,
				GUID,
				type,
				isConst,
				lowerBound,
				upperBound
			))
		}
		return dataList
	}

	static def getXrefData(String xml) {
		var dataList = <XrefData>newLinkedList
		var document = parseXML(xml)
		var row_nodes = document.getElementsByTagName("Row")
		for (i : 0 .. row_nodes.length - 1) {
			var fields = row_nodes.item(i).childNodes
			var String name
			var String type
			var String client
			var String supplier = ""
			var String link = ""
			var String description = ""
			var String behavior = ""

			for (j : 0 .. fields.length - 1) {
				var field = fields.item(j)
				if (field.nodeName == "Name") {
					name = field.textContent
				}
				if (field.nodeName == "Type") {
					type = field.textContent
				}
				if (field.nodeName == "Client") {
					client = field.textContent
				}
				if (field.nodeName == "Supplier") {
					supplier = field.textContent
				}
				if (field.nodeName == "Link") {
					link = field.textContent
				}
				if (field.nodeName == "Description") {
					description = field.textContent
				}
				if (field.nodeName == "Behavior") {
					behavior = field.textContent
				}
			}
			dataList.add(new XrefData(
				name,
				type,
				client,
				supplier,
				link,
				description,
				behavior
			))
		}
		return dataList
	}

	static def getObjectPropertyData(String xml) {
		var dataList = <ObjectPropertyData>newLinkedList
		var document = parseXML(xml)
		var row_nodes = document.getElementsByTagName("Row")
		for (i : 0 .. row_nodes.length - 1) {
			var fields = row_nodes.item(i).childNodes
			var String property
			var String value = ""
			var String notes = ""
			var long elementID = -1

			for (j : 0 .. fields.length - 1) {
				var field = fields.item(j)
				if (field.nodeName == "Property") {
					property = field.textContent
				}
				if (field.nodeName == "Value") {
					value = field.textContent
				}
				if (field.nodeName == "Notes") {
					notes = field.textContent
				}
				if (field.nodeName == "Object_ID") {
					elementID = Long.valueOf(field.textContent)
				}
			}
			if (property === null || property == "") {
				throw new MissingFormatArgumentException("Property of ObjectProperty is not in XML : " + xml)
			}
			if (elementID == -1) {
				throw new MissingFormatArgumentException(
					"Object ID of related element of ObjectProperty is not in XML : " + xml)
			}
			dataList.add(new ObjectPropertyData(
				property,
				value,
				notes,
				elementID
			))
		}
		return dataList
	}

	static def getObjectFileData(String xml) {
		var dataList = <ObjectFileData>newLinkedList
		var document = parseXML(xml)
		var row_nodes = document.getElementsByTagName("Row")
		for (i : 0 .. row_nodes.length - 1) {
			var fields = row_nodes.item(i).childNodes
			var String name = ""
			var String type = ""
			var long elementID = -1

			for (j : 0 .. fields.length - 1) {
				var field = fields.item(j)
				if (field.nodeName == "FileName") {
					name = field.textContent
				}
				if (field.nodeName == "Type") {
					type = field.textContent
				}
				if (field.nodeName == "Object_ID") {
					elementID = Long.valueOf(field.textContent)
				}
			}
			if (elementID == -1) {
				throw new MissingFormatArgumentException(
					"Object ID of related element of ObjectProperty is not in XML : " + xml)
			}
			dataList.add(new ObjectFileData(
				name,type,elementID
			))
		}
		return dataList
	}

	static def getConnectorData(String xml) {
		var dataList = <ConnectorData>newLinkedList
		var document = parseXML(xml)
		var row_nodes = document.getElementsByTagName("Row")
		for (i : 0 .. row_nodes.length - 1) {
			var fields = row_nodes.item(i).childNodes
			var sourceID = -1
			var targetID = -1
			var name = ""
			var type = ""
			var PDATA1 = ""
			var PDATA2 = ""
			var PDATA3 = ""
			for (j : 0 .. fields.length - 1) {
				var field = fields.item(j)
				if (field.nodeName == "Name") {
					name = field.textContent
				}
				if (field.nodeName == "Description") {
					type = field.textContent
				}
				if (field.nodeName == "START_Object_ID") {
					sourceID = Integer.valueOf(field.textContent)
				}
				if (field.nodeName == "END_Object_ID") {
					targetID = Integer.valueOf(field.textContent)
				}
				if (field.nodeName == "PDATA1") {
					PDATA1 = field.textContent
				}
				if (field.nodeName == "PDATA2") {
					PDATA2 = field.textContent
				}
				if (field.nodeName == "PDATA3") {
					PDATA3 = field.textContent
				}
			}
			if (sourceID == -1) {
				throw new MissingFormatArgumentException("Source of connection is not in XML")
			}
			if (targetID == -1) {
				throw new MissingFormatArgumentException("Target of connection is not in XML")
			}
			dataList.add(new ConnectorData(name, sourceID, targetID, type, PDATA1, PDATA2, PDATA3))
		}
		return dataList
	}

}
