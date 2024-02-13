package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils

import java.util.List
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.ObjectPropertyData
import java.util.Map
import hu.bme.mit.gamma.architecture.model.Direction

class ObjPropUtils {
	
	static val directionMap = Map.of(
		"in", Direction.IN, 
		"out", Direction.OUT, 
		"inout", Direction.INOUT, 
		"none", Direction.NONE
	)
	static def transformDirection(String dir){
		return directionMap.get(dir)
	}
	
	static def createDirectionMap(List<ObjectPropertyData> data){
		val dirMap=<Long,Direction>newHashMap
		for (d:data){
			if(d.property=="direction"){
				dirMap.put(new Long(d.elementID),transformDirection(d.value))
			}
		}
		return dirMap
	}
	
	
}