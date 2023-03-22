package hu.bme.mit.gamma.environment.analysis.transformation

import hu.bme.mit.gamma.environment.analysis.AnalysisComponent
import hu.bme.mit.gamma.environment.analysis.transformation.javagen.JavaEntryClassGenerator
import hu.bme.mit.gamma.environment.analysis.transformation.pythongen.PyroSimulatorGenerator
import hu.bme.mit.gamma.statechart.interface_.Package
import java.io.File
import java.io.FileWriter
import java.net.URI
import java.util.List
import java.util.logging.Level
import java.util.logging.Logger
import org.eclipse.emf.ecore.resource.Resource
import hu.bme.mit.gamma.util.FileUtil

class AnalysisTransformation { 
	
	var logger = Logger.getLogger("GammaLogger")
	val futil=FileUtil.INSTANCE
	
	def transformandrun(Resource resource,URI uri,String packageName,List<String> uriList,String BasePackage){
		
		var pyGenURI=BasePackage+File.separator+"simulator-gen"+File.separator+"simulator.py"
		var gatewayGenURI=BasePackage+File.separator+"gateway-gen"+File.separator+"javaenv"+File.separator+"DetModelEntryPoint.java"
		
		var pythonGenerator = new PyroSimulatorGenerator()
		var javaGenerator= new JavaEntryClassGenerator()
		var pck=resource.getContents().get(0) as Package
		var acomp=pck.components.filter[e|e instanceof AnalysisComponent].get(0) as AnalysisComponent
		logger.log(Level.INFO, "Python Simulator URI: " + pyGenURI)
		logger.log(Level.INFO, "Java Simulator URI: " + gatewayGenURI)
		futil.saveString(pyGenURI, pythonGenerator.generate(acomp,packageName).toString )
		futil.saveString(gatewayGenURI,javaGenerator.generate(acomp,packageName).toString)

        
		logger.log(Level.INFO,"Analysis has been finnished")
		
	}
	

	
	/**
	 * Creates a Java class from the the given code at the location specified by the given URI.
	 */
	
}