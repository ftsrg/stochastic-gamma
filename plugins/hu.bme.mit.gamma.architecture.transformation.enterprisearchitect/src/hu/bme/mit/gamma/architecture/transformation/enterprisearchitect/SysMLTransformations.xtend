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
package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect

import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils.EAUtils
import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability.ElementTrace
import hu.bme.mit.gamma.statechart.language.ui.serializer.StatechartLanguageSerializer
import java.util.HashMap
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace
import hu.bme.mit.gamma.architecture.transformation.ElementTransformer

import static extension hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils.*
import hu.bme.mit.gamma.statechart.interface_.Package
import org.eclipse.emf.common.util.URI
import hu.bme.mit.gamma.statechart.language.ui.internal.LanguageActivator
import org.eclipse.emf.ecore.EObject
import java.io.IOException
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import java.io.File
import org.eclipse.emf.ecore.resource.ResourceSet
import java.util.logging.Level
import com.google.inject.Injector
import org.eclipse.emf.ecore.resource.Resource
import java.util.Collections

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import hu.bme.mit.gamma.architecture.model.ArchitectureInterface
import hu.bme.mit.gamma.statechart.interface_.Interface
import hu.bme.mit.gamma.architecture.transformation.FunctionTransformer
import hu.bme.mit.gamma.architecture.transformation.SystemTransformer
import hu.bme.mit.gamma.architecture.transformation.ComponentTransformer
import hu.bme.mit.gamma.architecture.transformation.HWErrorSCTTransformer
import hu.bme.mit.gamma.architecture.transformation.traceability.AllocationTrace

class SysMLTransformations {

	// final static String functionPackageGUID="";
	final static String electricalPackageGUID = "";
	final static String mechanicalPackageGUID = "";
	final static String softwarePackageGUID = "";

	// final static String systemPackageGUID="";
	static def transformInterface(String folderURI) {
		var pkgID = 77;
		var loader = EAUtils.createDataLoader();
		var data = loader.loadAllInterfaceFromPackage(pkgID);
		var eaTrace = new ElementTrace();
		var archTansformer = new EAElementTransformation(eaTrace, newHashMap);
		var gammaTrace = new ArchitectureTrace();
		var gammaTransformer = new ElementTransformer(gammaTrace);
		var archInterface = archTansformer.transformInterface(data.get(0));
		var gammaInterface = gammaTransformer.transformInterface(archInterface)
		gammaTransformer.packageElement(gammaInterface)
	// val serializer = new StatechartLanguageSerializer();
	// serializer.serialize(gammaTrace.interfacePackage, folderURI,gammaInterface.getName()+".gcd" );
	}

	static def transformArchitecture(ElementTrace eaTrace) {
		var root_pkg = eaTrace.rootPkg
		
		var gammaTrace = new ArchitectureTrace();
		val gammaTransformer = new ElementTransformer(gammaTrace);
		val functionTransformer = new FunctionTransformer(gammaTrace)
		val componentTransformer = new ComponentTransformer(gammaTrace)
		var archInterfaces = root_pkg.interfaces
		var valueTypes = root_pkg.valueTypes
		for (vt : valueTypes) {
			gammaTransformer.transformValueType(vt)
		}
		var gammaInterfaces = <Interface>newArrayList
		for (i : archInterfaces) {
			gammaTransformer.transformInterface(i)
		}
		
		for (i : archInterfaces) {
			val gammaInterface = gammaTransformer.transformInterfaceGeneralization(i)
			gammaTransformer.packageElement(gammaInterface)
			gammaInterfaces += gammaInterface
			gammaTransformer.generateInterfaceComponent(gammaInterface)
		}
		
		val primitiveFunctions = root_pkg.primitiveFunctions
		for (function : primitiveFunctions) {
			gammaTransformer.transformPrimitiveFunction(function)
		}
		
		
		val allocationTrace=new AllocationTrace(gammaTrace,eaTrace.allAllocations.filter[a|a!==null].toList)

		val componentFunctions = root_pkg.componentFunctions
		var shallRun = true
		while (shallRun) {
			shallRun = false
			for (function : componentFunctions) {
				if (gammaTransformer.isTransformable(function)){
					functionTransformer.transform(function)
					shallRun=true
				}
			}
		}

		val primitiveHardwareComponents = root_pkg.primitiveHardwareComponents
		for (component : primitiveHardwareComponents) {
			componentTransformer.transform(component)
		}
		
		
		val subsystems = root_pkg.subsystems
		val subsysTransformer=new HWErrorSCTTransformer(gammaTrace)
		for (subsystem : subsystems){
			subsysTransformer.transform(subsystem)
		}

		val systems = root_pkg.system
		for (system : systems) {
			SystemTransformer.transformSystem(system, gammaTrace, allocationTrace)
		}

		return gammaTrace
	}
}
