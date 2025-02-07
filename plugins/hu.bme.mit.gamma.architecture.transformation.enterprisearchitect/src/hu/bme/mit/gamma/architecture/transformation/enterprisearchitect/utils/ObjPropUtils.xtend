/********************************************************************************
 * Copyright (c) 2018-2024 Contributors to the Gamma project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * SPDX-License-Identifier: EPL-1.0
 ********************************************************************************/
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