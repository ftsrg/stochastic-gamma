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

package hu.bme.mit.gamma.environment.language.ui.wizard

import org.eclipse.core.runtime.Status
import org.eclipse.jdt.core.JavaCore
import org.eclipse.xtext.ui.XtextProjectHelper
import org.eclipse.xtext.ui.util.PluginProjectFactory
import org.eclipse.xtext.ui.wizard.template.IProjectGenerator
import org.eclipse.xtext.ui.wizard.template.IProjectTemplateProvider
import org.eclipse.xtext.ui.wizard.template.ProjectTemplate

import static org.eclipse.core.runtime.IStatus.*
import org.eclipse.core.runtime.FileLocator
import org.eclipse.core.runtime.URIUtil
import java.net.URL
import org.eclipse.core.runtime.Path
import org.eclipse.core.runtime.Platform
import org.osgi.framework.Bundle
import hu.bme.mit.gamma.util.FileUtil
import java.io.File
import java.nio.file.Paths
import java.nio.file.Files
import java.util.stream.Stream
import java.util.stream.Collectors

/**
 * Create a list with all project templates to be shown in the template new project wizard.
 * 
 * Each template is able to generate one or more projects. Each project can be configured such that any number of files are included.
 */
class EnvironmentLanguageProjectTemplateProvider implements IProjectTemplateProvider {
	override getProjectTemplates() {
		#[new GenericStochasticGammaProject, new IoTStochasticGammaProject, new DualGPSStochasticGammaProject]
	}
}

@ProjectTemplate(label="Stochastic Gamma Composition Modeling", icon="stochastic_gamma16.png", description="<p><b>Stochastic Gamma Composition Modeling</b></p>
<p>This is a wizard to create a Stochastic Gamma Composition Modeling project.</p>")
final class GenericStochasticGammaProject {
	val advanced = check("Custom model name:", false)
	val advancedGroup = group("Properties")
	val name = text("Model name:", "The name of the main model", advancedGroup)
	
	// val path = text("Package:", "mydsl", "The package path to place the files in", advancedGroup)
	override protected updateVariables() {
		name.enabled = advanced.value
		// path.enabled = advanced.value
		if (!advanced.value) {
			name.value = "Model"
		// path.value = "sgcd"
		}
	}

	override protected validate() {
		// if (path.value.matches('[a-z][a-z0-9_]*(/[a-z][a-z0-9_]*)*'))
		// null
		// else
		// new Status(ERROR, "Wizard", "'" + path + "' is not a valid package name")
		null
	}

	override generateProjects(IProjectGenerator generator) {
		generator.generate(new PluginProjectFactory => [
			projectName = projectInfo.projectName
			location = projectInfo.locationPath
			val model_name=name.toString.replaceAll('''\s''',"_").toFirstUpper
			val pkg_name=model_name.toLowerCase
			projectNatures += #[JavaCore.NATURE_ID, "org.eclipse.pde.PluginNature", XtextProjectHelper.NATURE_ID]
			builderIds += #[JavaCore.BUILDER_ID, XtextProjectHelper.BUILDER_ID]
			folders += "src"
			folders += "src-gen"
			folders += "gateway-gen"
			folders += "simulator-gen"
			addFile('''model/«pkg_name».sgcd''', '''
				package «pkg_name»
				import "interfaces"
				stochastic async «model_name» [
				] {
					
				}
			''')
			addFile('''model/interfaces.gcd''', '''
				package inerfaces
				
				interface TestInterface{
					out event newEvent
				}
				
			''')
		])
	}
}

@ProjectTemplate(label="IoT Camera System Example Project", icon="stochastic_gamma16.png", description="<p><b>IoT Camera System Example Project</b></p>
<p>This is a wizard to create  Stochastic Gamma Composition Project of an IoT camera system.</p>")
final class IoTStochasticGammaProject {
	//val advanced = check("Advanced:", false)
	//val advancedGroup = group("Properties")
	//val name = text("Model name:", "The name of the main model", advancedGroup)

	// val path = text("Package:", "mydsl", "The package path to place the files in", advancedGroup)
	override protected updateVariables() {
		//name.enabled = advanced.value
		// path.enabled = advanced.value
		//if (!advanced.value) {
		//	name.value = "Xtext"
		// path.value = "sgcd"
		//}
	}

	override protected validate() {
		// if (path.value.matches('[a-z][a-z0-9_]*(/[a-z][a-z0-9_]*)*'))
		// null
		// else
		// new Status(ERROR, "Wizard", "'" + path + "' is not a valid package name")
		null
	}

	override generateProjects(IProjectGenerator generator) {
		generator.generate(new PluginProjectFactory => [
			projectName = projectInfo.projectName
			location = projectInfo.locationPath
			projectNatures += #[JavaCore.NATURE_ID, "org.eclipse.pde.PluginNature", XtextProjectHelper.NATURE_ID]
			builderIds += #[JavaCore.BUILDER_ID, XtextProjectHelper.BUILDER_ID]
			folders += "src"
			folders += "src-gen"
			folders += "gateway-gen"
			folders += "simulator-gen"
			var futil = FileUtil.INSTANCE;
			var bundle = Platform.getBundle("hu.bme.mit.gamma.environment.language.ui")
			var url_m = FileLocator.find(bundle, new Path("/resources/iot-models"));
			var urls = Files.list(Paths.get(FileLocator.toFileURL(url_m).toURI)).collect(Collectors.toList())
			for (url : urls) {
				var file=url.toFile
				var filename=file.name
				if (file.file){
					var contents = futil.loadString(file);			
					addFile("model/"+filename, contents)
				}
			}
		])
	}

}


@ProjectTemplate(label="Dual GPS System Example Project", icon="stochastic_gamma16.png", description="<p><b>Dual GPS System Example Project</b></p>
<p>This is a wizard to create  Stochastic Gamma Composition Project of an Dual GPS System. The model was originally developed as a case study of the AADL Error Annex and OSTATE 2.</p>")
final class DualGPSStochasticGammaProject {
	//val advanced = check("Advanced:", false)
	//val advancedGroup = group("Properties")
	//val name = text("Model name:", "The name of the main model", advancedGroup)

	// val path = text("Package:", "mydsl", "The package path to place the files in", advancedGroup)
	override protected updateVariables() {
		//name.enabled = advanced.value
		// path.enabled = advanced.value
		//if (!advanced.value) {
		//	name.value = "Xtext"
		// path.value = "sgcd"
		//}
	}

	override protected validate() {
		// if (path.value.matches('[a-z][a-z0-9_]*(/[a-z][a-z0-9_]*)*'))
		// null
		// else
		// new Status(ERROR, "Wizard", "'" + path + "' is not a valid package name")
		null
	}

	override generateProjects(IProjectGenerator generator) {
		generator.generate(new PluginProjectFactory => [
			projectName = projectInfo.projectName
			location = projectInfo.locationPath
			projectNatures += #[JavaCore.NATURE_ID, "org.eclipse.pde.PluginNature", XtextProjectHelper.NATURE_ID]
			builderIds += #[JavaCore.BUILDER_ID, XtextProjectHelper.BUILDER_ID]
			folders += "src"
			folders += "src-gen"
			folders += "gateway-gen"
			folders += "simulator-gen"
			var futil = FileUtil.INSTANCE;
			var bundle = Platform.getBundle("hu.bme.mit.gamma.environment.language.ui")
			var url_m = FileLocator.find(bundle, new Path("/resources/gps-models"));
			var urls = Files.list(Paths.get(FileLocator.toFileURL(url_m).toURI)).collect(Collectors.toList())
			for (url : urls) {
				var file=url.toFile
				var filename=file.name
				if (file.file){
					var contents = futil.loadString(file);			
					addFile("model/"+filename, contents)
				}
			}
		])
	}

}
