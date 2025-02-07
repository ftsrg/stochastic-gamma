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
package hu.bme.mit.gamma.environment.language;

import org.eclipse.xtext.formatting.IFormatter;

import hu.bme.mit.gamma.environment.language.formatter.EnvironmentLanguageFormatter;
import hu.bme.mit.gamma.statechart.language.formatting.StatechartLanguageFormatter;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class EnvironmentLanguageRuntimeModule extends AbstractEnvironmentLanguageRuntimeModule {
	
	@Override
	public Class<? extends IFormatter> bindIFormatter() {
		return EnvironmentLanguageFormatter.class;
	}
}
