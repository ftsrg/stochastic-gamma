package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils

import javax.xml.parsers.DocumentBuilderFactory
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets
import java.util.MissingFormatArgumentException
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ConnectorData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.XrefData
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ElementData

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
					}
				}
				if (elementID == -1) {
					throw new MissingFormatArgumentException("Object ID of element is not in XML")
				}
				if (conainerID == -1) {
					throw new MissingFormatArgumentException("Container ID of element is not in XML")
				}
				if (packageID == -1) {
					throw new MissingFormatArgumentException("Package ID of element is not in XML")
				}
				if (GUID === null || GUID == "") {
					throw new MissingFormatArgumentException("GUID ID of element is not in XML")
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
					multiplicity
				))
			}
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
			var String supplier
			var String link
			var String description

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
			}
			dataList.add(new XrefData(
				name,
				type,
				client,
				supplier,
				link,
				description
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
			}
			if (sourceID == -1) {
				throw new MissingFormatArgumentException("Source of connection is not in XML")
			}
			if (targetID == -1) {
				throw new MissingFormatArgumentException("Target of connection is not in XML")
			}
			dataList.add(new ConnectorData(name, sourceID, targetID, type))
		}
		return dataList
	}

}
