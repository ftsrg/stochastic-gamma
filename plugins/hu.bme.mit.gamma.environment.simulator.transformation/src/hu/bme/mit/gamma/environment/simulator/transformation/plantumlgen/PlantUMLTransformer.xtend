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
package hu.bme.mit.gamma.environment.simulator.transformation.plantumlgen

import hu.bme.mit.gamma.environment.model.EnvironmentComponent
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter
import hu.bme.mit.gamma.statechart.composite.CompositeComponent
import hu.bme.mit.gamma.statechart.interface_.Component
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition

class PlantUMLTransformer {
	
	def String getComponentPlantUmlCode(Component component) {
		if (component instanceof StatechartDefinition) {
			val statechartDefinition =  component as StatechartDefinition
			var transformer = new StatechartToPlantUmlTransformer(statechartDefinition);
			return transformer.execute();
		} else if (component instanceof EnvironmentComponent) {
			val composite = component as CompositeComponent;
			var transformer = new EnvironmentToPlantUmlTransformer(composite);
			return transformer.execute();
		} else if (component instanceof AsynchronousAdapter) {
			val adapter = component as AsynchronousAdapter;
			var transformer = new AdapterToPlantUmlTransformer(adapter);
			return transformer.execute();
		}if (component instanceof CompositeComponent) {
			var composite = component as CompositeComponent;
			var transformer = new CompositeToPlantUmlTransformer(composite);
			return transformer.execute();
		}
		return ""; // To counter nullptr exceptions
	}
}