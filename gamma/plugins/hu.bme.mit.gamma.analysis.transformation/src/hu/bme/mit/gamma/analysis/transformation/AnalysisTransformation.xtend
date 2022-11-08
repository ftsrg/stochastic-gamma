package hu.bme.mit.gamma.analysis.transformation

import com.google.inject.Inject
import org.eclipse.emf.ecore.resource.Resource
import hu.bme.mit.gamma.analysis.AnalysisComponent
import java.io.File
import java.io.FileWriter
import java.lang.reflect.Field
import java.net.URI;
import java.util.List
import hu.bme.mit.gamma.analysis.transformation.pythonrunner.PythonInterpreter
import hu.bme.mit.gamma.analysis.transformation.pythonrunner.StdConsoleProvider
import hu.bme.mit.gamma.statechart.interface_.Package
import hu.bme.mit.gamma.analysis.transformation.pythonrunner.IStartInfo
import hu.bme.mit.gamma.analysis.transformation.pythonrunner.ExecutionResult
import java.util.logging.Logger
import java.util.logging.Level

class AnalysisTransformation {
	
	var logger = Logger.getLogger("GammaLogger")
	
	def transformandrun(Resource resource,URI uri,String packageName,List<String> uriList,String BasePackage){
		
		var pyGenURI=BasePackage+File.separator+"simulator-gen"+File.separator+"simulator.py"
		var gatewayGenURI=BasePackage+File.separator+"gateway-gen"+File.separator+"javaenv"+File.separator+"DetModelEntryPoint.java"
		
		var pythonGenerator = new PyroSimulatorGenerator()
		var javaGenerator= new Py4JGatewayGenerator()
		var pck=resource.getContents().get(0) as Package
		var acomp=pck.components.filter[e|e instanceof AnalysisComponent].get(0) as AnalysisComponent
		System.out.println(pythonGenerator.generate(acomp,packageName))
		System.out.println(javaGenerator.generate(acomp,packageName))
		saveCode(pythonGenerator.generate(acomp,packageName),pyGenURI)
		saveCode(javaGenerator.generate(acomp,packageName),gatewayGenURI)

        
		logger.log(Level.INFO,"Analysis has been finnished")


		
		
	}
	
	def transform(Resource resource,URI uri,String packageName,List<String> uriList,String BasePackage){
		
		var pyGenURI=BasePackage+File.separator+"simulator-gen"+File.separator+"simulator.py"
		var gatewayGenURI=BasePackage+File.separator+"gateway-gen"+File.separator+"epasenv"+File.separator+"AnalyzerGateway.java"
		
		var pythonGenerator = new PyroSimulatorGenerator()
		var javaGenerator= new Py4JGatewayGenerator()
		var pck=resource.getContents().get(0) as Package
		var acomp=pck.components.filter[e|e instanceof AnalysisComponent].get(0) as AnalysisComponent
		System.out.println(pythonGenerator.generate(acomp,packageName))
		System.out.println(javaGenerator.generate(acomp,packageName))
		saveCode(pythonGenerator.generate(acomp,packageName),pyGenURI)
		saveCode(javaGenerator.generate(acomp,packageName),gatewayGenURI)
		var compiler=new DynamicCompilation()
		var gatewayLoc=BasePackage+File.separator+"gateway-gen"+File.separator+"epasenv"+File.separator
		
		var libLoc=BasePackage+File.separator+"lib"
		compiler.compileAndRun(gatewayGenURI,gatewayLoc,uriList,libLoc)
		
		logger.log(Level.INFO,"Creating Python interpreter...")
		var interpreter = new PythonInterpreter(new IStartInfo() {
            
            override getPythonCommand() {
                return "python3.6";
            }

            
            override getWorkingDirectory() {
                return new File("./")
            }
        }, new StdConsoleProvider())
        
        
        
        var pythonscript=pythonGenerator.generate(acomp,packageName).toString
        
		logger.log(Level.INFO,"Starting Python interpreter")
		
        interpreter.runCode(pythonscript)
        
		logger.log(Level.INFO,"Analysis has been finnished")


		
		
	}
	
	/**
	 * Creates a Java class from the the given code at the location specified by the given URI.
	 */
	protected def saveCode(CharSequence code, String uri) {
		new File(uri.substring(0, uri.lastIndexOf(File.separator))).mkdirs
		val fw = new FileWriter(uri)
		fw.write(code.toString)
		fw.close
		return 
	}
	
}