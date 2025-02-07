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
package hu.bme.mit.gamma.architecture.transformation.builder

import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.environment.model.EnvironmentModelFactory
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory
import hu.bme.mit.gamma.statechart.composite.CompositeModelFactory

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory
import hu.bme.mit.gamma.architecture.transformation.ElementTransformer
import hu.bme.mit.gamma.architecture.transformation.RelationTransfomer
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.environment.stochastic.stochastic.StochasticFactory
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory

import java.math.BigDecimal
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.statechart.interface_.EventDirection
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource

class FailureModelGenerator {
	
	//val component=
	static val stochModelFactory = EnvironmentModelFactory.eINSTANCE
	static val sctModelFactory = StatechartModelFactory.eINSTANCE
	static val cmpModelFactory = CompositeModelFactory.eINSTANCE
	static val ifModelFactory = InterfaceModelFactory.eINSTANCE
	static val distModelFactory = StochasticFactory.eINSTANCE
	static val expModelFactory = ExpressionModelFactory.eINSTANCE
	
	val ArchitectureTrace trace
	
	
	
	static int nameCNTR=0
	
	static val defaultRate="100.0"
	
	val extension ElementTransformer elementTransformer
	val extension RelationTransfomer relationTransformer
	
	new(ArchitectureTrace trace){
		//this.component = stochModelFactory.createEnvironmentAsynchronousCompositeComponent
		//this.instance = stochModelFactory.createEnvironmentAsynchronousCompositeComponentInstance
		
		this.trace=trace
		this.elementTransformer=new ElementTransformer(trace)
		this.relationTransformer=new RelationTransfomer(trace)
			
		
	}
	
	
	
	def generateSource(String name,Interface _interface){
		
		val failureSource=generateSource(name)
		failureSource.addPort(_interface)

		return failureSource
	}
	
	def generateSource(String name){
		
		val failureSource=stochModelFactory.createEnvironmentEventSource
		failureSource.name = name + "_Failures"//+nameCNTR++
		val failureRule= stochModelFactory.createStochasticRule
		val filter= stochModelFactory.createComponentFilter
		val dist= distModelFactory.createExponentialRandomVariable
		val rate = expModelFactory.createDecimalLiteralExpression
		rate.value = new BigDecimal(defaultRate)
		dist.rate = rate
		failureRule.stochasticModel = dist
		failureRule.filter+=filter
		failureSource.behaviorRules+=failureRule
		
		

		return failureSource
	}
	def generatePSource(String name){
		
		val failureSource=stochModelFactory.createEnvironmentPeriodicEventSource
		failureSource.name = name + "_Failures"//+nameCNTR++
		val failureRule= stochModelFactory.createStochasticRule
		val filter= stochModelFactory.createComponentFilter
		val dist= distModelFactory.createExponentialRandomVariable
		val rate = expModelFactory.createDecimalLiteralExpression
		rate.value = new BigDecimal(defaultRate)
		dist.rate = rate
		failureRule.stochasticModel = dist
		failureRule.filter+=filter
		failureSource.behaviorRules+=failureRule
		
		

		return failureSource
	}
	def addPort(EnvironmentEventSource source,Interface _interface){
		addPort(source, _interface,"")
	}
	
	def addPort(EnvironmentEventSource source,Interface _interface,String name){
		val failurePort=ElementTransformer._createPort(_interface,name,false)
		source.outports+=failurePort/* 
		val failureRule= stochModelFactory.createStochasticRule
		val filter= stochModelFactory.createComponentFilter
		val dist= distModelFactory.createExponentialRandomVariable
		val rate = expModelFactory.createDecimalLiteralExpression
		rate.value = new BigDecimal(defaultRate)
		dist.rate = rate
		failureRule.stochasticModel = dist
		failureRule.filter+=filter
		source.behaviorRules+=failureRule*/

		return failurePort
		
	}
	
}