/********************************************************************************
 * Copyright (c) 2018 Contributors to the Gamma project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * SPDX-License-Identifier: EPL-1.0
 ********************************************************************************/
package hu.bme.mit.gamma.environment.language.ui.serializer;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import com.google.inject.Injector;

import hu.bme.mit.gamma.environment.language.ui.internal.LanguageActivator;
import hu.bme.mit.gamma.environment.model.utils.EnvironmentModelDerivedFeatures;
import hu.bme.mit.gamma.environment.model.utils.EnvironmentModelValidator;
import hu.bme.mit.gamma.expression.model.NamedElement;
import hu.bme.mit.gamma.expression.util.ExpressionModelValidator.ValidationResult;
import hu.bme.mit.gamma.expression.util.ExpressionModelValidator.ValidationResultMessage;
import hu.bme.mit.gamma.language.util.serialization.GammaLanguageSerializer;
import hu.bme.mit.gamma.statechart.composite.AsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.CompositeComponent;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.statechart.interface_.Package;

public class EnvironmentLanguageSerializer {

	protected final EnvironmentModelValidator validator = EnvironmentModelValidator.INSTANCE;
	protected final EnvironmentModelDerivedFeatures derivedFeatures = EnvironmentModelDerivedFeatures.INSTANCE;

	public void serialize(EObject rootElem, String parentFolder, String fileName) throws IOException {
		// This is how an injected object can be retrieved
		Injector injector = LanguageActivator.getInstance()
				.getInjector(LanguageActivator.HU_BME_MIT_GAMMA_ENVIRONMENT_LANGUAGE_ENVIRONMENTLANGUAGE);
		GammaLanguageSerializer serializer = injector.getInstance(GammaLanguageSerializer.class);
		Package pkg = (Package) rootElem;
		var messages = new LinkedList<ValidationResultMessage>();
		if (!pkg.getComponents().isEmpty()) {
			for (Component component : pkg.getComponents()) {
				messages.addAll(validator.checkNameUniqueness(component.getPorts()));
				if (component instanceof CompositeComponent) {
					messages.addAll(validator
							.checkNameUniqueness(derivedFeatures.getDerivedComponents((CompositeComponent) component)));
				}
			}
		}
		if (messages.stream().filter(m -> m.getResult().equals(ValidationResult.ERROR)).iterator().hasNext()) {
			throw new RuntimeException(
					"Error occured during validation of before serializing '" + pkg.getName() + "' : \n"
							+ messages.stream()
									.map(m -> "[" + m.getResult().toString() + "] " + m.getResultText()
											+ ";   Referenced element: " + getName(m.getReferenceInfo().getSource()))
									.collect(Collectors.joining(";\n ")));
		}
		serializer.save(rootElem, URI.decode(parentFolder + File.separator + fileName));
	}
	
	protected String getName(EObject object) { 
		if (object instanceof NamedElement) {
			return ((NamedElement) object).getName();
		}else {
			return "";
		}
	}

}
