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

package hu.bme.mit.gamma.environment.language.validation;

import org.eclipse.xtext.validation.Check;

import hu.bme.mit.gamma.environment.model.AbstractEnvironmentCompositeComponent;
import hu.bme.mit.gamma.environment.model.EnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.utils.EnvironmentModelValidator;
import hu.bme.mit.gamma.expression.model.ArgumentedElement;
import hu.bme.mit.gamma.statechart.composite.BroadcastChannel;
import hu.bme.mit.gamma.statechart.composite.CascadeCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.Channel;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.composite.ComponentInstanceReferenceExpression;
import hu.bme.mit.gamma.statechart.composite.InstancePortReference;
import hu.bme.mit.gamma.statechart.composite.PortBinding;
import hu.bme.mit.gamma.statechart.composite.ScheduledAsynchronousCompositeComponent;
import hu.bme.mit.gamma.statechart.composite.SimpleChannel;
import hu.bme.mit.gamma.statechart.interface_.Component;
import hu.bme.mit.gamma.statechart.interface_.Port;
import hu.bme.mit.gamma.statechart.statechart.AnyPortEventReference;
import hu.bme.mit.gamma.statechart.statechart.PortEventReference;

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
public class EnvironmentLanguageValidator extends AbstractEnvironmentLanguageValidator {
	

	protected EnvironmentModelValidator environmentModelValidator = EnvironmentModelValidator.INSTANCE;
	
	public EnvironmentLanguageValidator() {
		super.expressionModelValidator = environmentModelValidator;
		super.actionModelValidator = environmentModelValidator;
	}
	
	//Elementary Environmental Components
	

	@Check
	public void checkComponentSepratation(Component component) {
		//if (component instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkComponentSepratation(component));
	}
	
	// Expressions 
	
	@Check
	public void checkArgumentTypes(ArgumentedElement element) {
		//if (element instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		//if (element instanceof EnvironmentComponentInstance) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkArgumentTypes(element));
	}
	
	
	// Statechart
	
	@Check
	public void checkUnusedDeclarations(Component component) {
		//if (component instanceof AbstractEnvironmentCompositeComponent) 
		//	return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkUnusedDeclarations(component));
	}
	
	@Check
	public void checkTransitionEventTriggers(PortEventReference portEventReference) {
		//if (portEventReference.getPort().eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		//if (portEventReference.getPort().eContainer()  instanceof EnvironmentComponentInstance) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkTransitionEventTriggers(portEventReference));
	}
		
	// Composite system
			
	@Check
	public void checkParameters(ComponentInstance instance) {
		if (instance instanceof EnvironmentComponentInstance) return; //no validation needed
		handleValidationResultMessage(environmentModelValidator.checkParameters(instance));
	}
	
	@Check
	public void checkComponentInstanceArguments(ComponentInstance instance) {
		if (instance instanceof EnvironmentComponentInstance) return; //no validation needed
		handleValidationResultMessage(environmentModelValidator.checkComponentInstanceArguments(instance));
	}

	@Check
	public void checkPortBinding(Port port) {
		//if (port.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		//if (port.eContainer()  instanceof EnvironmentComponentInstance) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkPortBinding(port));
	}

	@Check
	public void checkSimpleChannelInterfaces(SimpleChannel channel) {
		handleValidationResultMessage(environmentModelValidator.checkSimpleChannelInterfaces(channel));
	}

	@Check
	public void checkBroadcastChannelInterfaces(BroadcastChannel channel) {
		handleValidationResultMessage(environmentModelValidator.checkBroadcastChannelInterfaces(channel));
	}
	
	@Check
	public void checkComponentInstances(ComponentInstance instance) {
		return;
		//if (instance instanceof EnvironmentComponentInstance) 
		//	return; //todo: add validation
		//handleValidationResultMessage(statechartModelValidator.checkComponentInstances(instance));
	}
	
	@Check
	public void checkPortBindingUniqueness(PortBinding portBinding) {
		//if (portBinding.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		//if (portBinding.eContainer()  instanceof EnvironmentComponentInstance) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkPortBindingUniqueness(portBinding));
	}
	
	@Check
	public void checkPortBinding(PortBinding portDefinition) {
		//if (portDefinition.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		//if (portDefinition.eContainer()  instanceof EnvironmentComponentInstance) return; //todo: add validation
		//handleValidationResultMessage(environmentModelValidator.checkPortBinding(portDefinition));
	}
	
	@Check
	public void checkInstancePortReference(InstancePortReference reference) {
		//if (reference.getPort().eContainer()  instanceof EnvironmentComponentInstance) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkInstancePortReference(reference));
	}
	
	@Check
	public void checkPortBindingWithSimpleChannel(SimpleChannel channel) {
		if (channel.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkPortBindingWithSimpleChannel(channel));	
	}
	
	@Check
	public void checkPortBindingWithBroadcastChannel(BroadcastChannel channel) {
		//if (channel.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkPortBindingWithBroadcastChannel(channel));		
	}
	
	@Check
	public void checkChannelProvidedPorts(Channel channel) {
		//if (channel.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkChannelProvidedPorts(channel));
	}
	
	@Check
	public void checkChannelRequiredPorts(SimpleChannel channel) {
		//if (channel.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkChannelRequiredPorts(channel));
	}
	
	@Check
	public void checkChannelRequiredPorts(BroadcastChannel channel) {
		//if (channel.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkChannelRequiredPorts(channel));
	}
	
	@Check
	public void checkChannelInput(Channel channel) {
		if (channel.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkChannelInput(channel));		
	}
	
	@Check
	public void checkSimpleChannelOutput(SimpleChannel channel) {
		if (channel.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkSimpleChannelOutput(channel));
	}
	
	@Check
	public void checkBroadcastChannelOutput(BroadcastChannel channel) {
		if (channel.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkBroadcastChannelOutput(channel));	
	}
	
	@Check
	public void checkCascadeLoopChannels(SimpleChannel channel) {
		if (channel.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkCascadeLoopChannels(channel));
	}
	
	@Check
	public void checkCascadeLoopChannels(BroadcastChannel channel) {
		if (channel.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkCascadeLoopChannels(channel));
	}
	
	// Wrapper
	
	@Check
	public void checkWrapperPortName(Port port) {
		if (port.eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		if (port.eContainer()  instanceof EnvironmentComponentInstance) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkWrapperPortName(port));
	}
	@Check
	public void checkMessageQueueAnyEventReferences(AnyPortEventReference anyPortEventReference) {
		if (anyPortEventReference.getPort().eContainer() instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		if (anyPortEventReference.getPort().eContainer()  instanceof EnvironmentComponentInstance) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator
				.checkMessageQueueAnyEventReferences(anyPortEventReference));
	}
	
	@Check
	public void checkExecutionLists(CascadeCompositeComponent cascade) {
		if (cascade instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkExecutionLists(cascade));
	}
	
	@Check
	public void checkExecutionLists(ScheduledAsynchronousCompositeComponent scheduledComponent) {
		if (scheduledComponent instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkExecutionLists(scheduledComponent));
	}
	
	@Check
	public void checkComponents(ScheduledAsynchronousCompositeComponent scheduledComponent) {
		if (scheduledComponent instanceof AbstractEnvironmentCompositeComponent) return; //todo: add validation
		handleValidationResultMessage(environmentModelValidator.checkComponents(scheduledComponent));
	}
	
	@Check
	public void checkComponentInstanceReferences(ComponentInstanceReferenceExpression reference) {
		handleValidationResultMessage(environmentModelValidator.checkComponentInstanceReferences(reference));
	}
	

}
