package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils

import org.sparx.Repository
import org.sparx.Element
import static extension hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.NamingUtils.*
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ElementData
import java.util.List

class EAUtils {
	
	
	static def getName(Element element){
		return element.GetName.simplifyName
	}
	
	static def getElementDataByGUID(List<ElementData> dataList,String GUID){
		val fdata=dataList.filter[d | d.GUID==GUID].toList
		if (fdata.length==0){
			return null
		}
		return fdata.get(0)
	}
	
	static def getContainedPackage(List<ElementData> dataList, ElementData rootData){
		
		var shallRun=true
		var containedElementIDs=<Long>newHashSet
		containedElementIDs.add(Long.valueOf(rootData.PDATA1))
		
		while(shallRun){
			shallRun=false
			for (data : dataList){
				if (
					containedElementIDs.contains(data.packageID) &&
					(! containedElementIDs.contains(Long.valueOf(data.PDATA1)))
				){
					containedElementIDs.add(Long.valueOf(data.PDATA1))
					shallRun=true
				}
			}
		}
		
		containedElementIDs.remove(rootData.elementID)
		
		return containedElementIDs
	}
	
	static def getContainedPackage(List<ElementData> dataList, String GUID){
		return EAUtils.getContainedPackage(dataList,dataList.getElementDataByGUID(GUID)).toList
	}
	
}