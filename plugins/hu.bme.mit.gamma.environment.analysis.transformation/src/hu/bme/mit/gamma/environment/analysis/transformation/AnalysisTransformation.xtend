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
	
	def transformandrun(Resource resource,URI uri,String packageName,List<String> uriList,String basePackage){
		
		var pck=resource.getContents().get(0) as Package
		var acomp=pck.components.filter[e|e instanceof AnalysisComponent].get(0) as AnalysisComponent
		
		var pyGenURI=basePackage+File.separator+"simulator-gen"+File.separator+acomp.name.toLowerCase+"_simulator.py"
		var gatewayGenURI=basePackage+File.separator+"gateway-gen"+File.separator+"javaenv"+File.separator+acomp.name.toFirstUpper+"EntryPoint.java"
		
		var pythonGenerator = new PyroSimulatorGenerator()
		var javaGenerator= new JavaEntryClassGenerator()
		
		logger.log(Level.INFO, "Python Simulator URI: " + pyGenURI)
		logger.log(Level.INFO, "Java Simulator URI: " + gatewayGenURI)
		futil.saveString(pyGenURI, pythonGenerator.generate(acomp,packageName,basePackage).toString )
		futil.saveString(gatewayGenURI,javaGenerator.generate(acomp,packageName).toString)

        
		logger.log(Level.INFO,"Analysis has been finnished")
		
	}
	

	
	/**
	 * Creates a Java class from the the given code at the location specified by the given URI.
	 */
	
}