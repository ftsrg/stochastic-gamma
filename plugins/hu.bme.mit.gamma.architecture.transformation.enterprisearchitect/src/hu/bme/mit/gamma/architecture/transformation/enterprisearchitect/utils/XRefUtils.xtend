package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils

import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.datatypes.XrefData
import java.util.List
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability.ElementTrace
import java.util.MissingFormatArgumentException
import java.util.logging.Logger
import java.util.logging.Level

class XRefUtils {
	
	protected static Logger logger = Logger.getLogger("GammaLogger");
	
	static def isStereotype(XrefData data){
		return data.description.contains("@STEREO")
	}
	
	static def isSignalReception(XrefData data){
		return data.behavior.equals("Signal")
	}
	
	
	static def isConjugation(XrefData data){
		return data.description.contains("@PROP=@NAME=isConjugated@ENDNAME")
	}
	
	static def isConjugated(XrefData data){
		if(data.description.contains("@PROP=@NAME=isConjugated@ENDNAME;@TYPE=Boolean@ENDTYPE;@VALU=0@ENDVALU;")){
			return false
		} else if(data.description.contains("@PROP=@NAME=isConjugated@ENDNAME;@TYPE=Boolean@ENDTYPE;@VALU=-1@ENDVALU;")){
			return true
		} else if(data.description.contains("@PROP=@NAME=isConjugated@ENDNAME;@TYPE=Boolean@ENDTYPE;@VALU=1@ENDVALU;")){
			return true
		} else {
			throw new MissingFormatArgumentException("Conjugation cannot be parsed from XRef data: "+data.supplier+" and "+data.description)
		}
	}
	
	static def createConjugationMap(List<XrefData> dataList){
		val conjugationMap = <String,Boolean>newHashMap
		for (data: dataList){
			if (data.isConjugation){
				if (conjugationMap.containsKey(data.client)){
					//throw new MissingFormatArgumentException("Duplicated conjugation in t_xref: "+data.client)
					logger.log(Level.WARNING,"Duplicated conjugation in t_xref: "+data.client)
				}
				conjugationMap.put(data.client,data.conjugated)
			}
		}
		return conjugationMap
	}
	
	
	
	static def getSignalReceptionLinks(List<XrefData> dataList){
		for (data : dataList){
			if (data.isSignalReception){
				
			}
		}
	}
	
	static def getStereotypeMap(List<XrefData> dataList){
		for (data : dataList){
			if (data.isStereotype){
				
			}
		}
	}
	
}