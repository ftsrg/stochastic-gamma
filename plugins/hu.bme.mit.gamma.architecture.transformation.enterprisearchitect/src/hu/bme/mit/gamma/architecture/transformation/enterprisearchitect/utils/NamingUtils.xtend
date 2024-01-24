package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils

class NamingUtils {
	static def simplifyName(String name){
		var simplifiedName = name.replace(".","_")
		// replace formbidden characters
		val forbiddenCharacters=#["-",":"," ","(",")","{","}","[","]","{","}",",",";",";","%","!","&","/","+"]
		for (c : forbiddenCharacters){
			simplifiedName = simplifiedName.replace(c,"_")
		}
		// remove double '_'-s
		while (simplifiedName.contains("__")){
			simplifiedName=simplifiedName.replaceAll("__","_")
		}
		// first digit character checking
		if (simplifiedName.matches("^[0-9]")){
			simplifiedName="_"+simplifiedName
		}
		return simplifiedName
	}
	

}