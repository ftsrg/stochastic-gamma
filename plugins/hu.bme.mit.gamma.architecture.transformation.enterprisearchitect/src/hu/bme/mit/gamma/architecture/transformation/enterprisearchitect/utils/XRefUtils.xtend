package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils

import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.XrefData

class XRefUtils {
	
	static def isStereotype(XrefData data){
		return data.description.contains("@STEREO")
	}
	
	static def isSignalReception(XrefData data){
		return data.type.equals("Signal")
	}
	
	
}