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

package hu.bme.mit.gamma.environment.language.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class EnvironmentLanguageGenerator extends AbstractGenerator {

	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
	/*	val p=resource.contents.get(0) as Package 
		val simulations=p.components.filter[comp | comp instanceof EnvironmentAsynchronousCompositeComponent]
					.flatMap[comp | (comp as EnvironmentAsynchronousCompositeComponent).environmentComponents]
					.filter[comp | comp instanceof EnvironmentExternSimulation]
					.map[comp | comp as EnvironmentExternSimulation].toList
		var generator=new PythonCoSimulatorClassGenerator()
		for (simulation : simulations){
			var stack=<EnvironmentCompositeComponentInstance>newArrayList()
			var connections=ElementaryComponentCollector.collect(simulation,stack)
			fsa.generateFile(
				simulation.name+'_temp.py', 
				generator.generateExternSimulationTemplateClasses(connections)
			)
		} */
	}
}
