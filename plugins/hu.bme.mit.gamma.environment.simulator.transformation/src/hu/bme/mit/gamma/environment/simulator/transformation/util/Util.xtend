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
package hu.bme.mit.gamma.environment.simulator.transformation.util

import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*
import hu.bme.mit.gamma.statechart.interface_.Port
import hu.bme.mit.gamma.statechart.interface_.Event
import hu.bme.mit.gamma.statechart.composite.ComponentInstance
import hu.bme.mit.gamma.statechart.composite.AsynchronousAdapter
import hu.bme.mit.gamma.expression.model.ParameterDeclaration
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition
import hu.bme.mit.gamma.xsts.transformation.GammaToXstsTransformer
import hu.bme.mit.gamma.statechart.lowlevel.transformation.GammaToLowlevelTransformer
import java.util.AbstractMap.SimpleEntry
import hu.bme.mit.gamma.xsts.model.XSTS
import hu.bme.mit.gamma.lowlevel.xsts.transformation.traceability.L2STrace;
import hu.bme.mit.gamma.lowlevel.xsts.transformation.LowlevelToXstsTransformer
import hu.bme.mit.gamma.lowlevel.xsts.transformation.TransitionMerging
import hu.bme.mit.gamma.statechart.statechart.Region
import java.security.MessageDigest

class Util {
	static def keyword(String name) {
		return '''$«name»______________________$'''
	}

	static def key(Port port, ParameterDeclaration param) '''$«port.name.toFirstUpper»_«param.name.toFirstUpper»$'''

	// static def key(Port port, ParameterDeclaration param,ComponentInstance instance)
	// '''$«instance.name.toFirstUpper»_«port.name.toFirstUpper»_«param.name.toFirstUpper»$'''
	static def key(Port port, ParameterDeclaration param, ComponentInstance instance) {
		return makeSHA1Hash('''$«instance.name.toFirstUpper»_«port.name.toFirstUpper»_«param.name.toFirstUpper»$''')
	}

	static def keyword(Port port) {
		return '''«FOR param : port.parameters SEPARATOR "\\n"»«port.key(param)»«ENDFOR»'''
	}

	static def keyword(Port port, ComponentInstance instance) {
		return '''«FOR param : port.parameters SEPARATOR "\\n"»«port.key(param,instance)»«ENDFOR»'''
	}

	static def keyword(ComponentInstance instance) {
		val type = instance.derivedType
		if (type instanceof StatechartDefinition) {
			return '''«instance.name»_SCT'''
		/* 
		 * val transformer = new GammaToLowlevelTransformer();
		 * val lowlevelPackage = transformer.transformAndWrap(type) as hu.bme.mit.gamma.statechart.lowlevel.model.Package;
		 * val lowlevelTransformer = new LowlevelToXstsTransformer(lowlevelPackage, false, TransitionMerging.HIERARCHICAL);
		 * val resultModels = lowlevelTransformer.execute as SimpleEntry<XSTS, L2STrace>
		 * val xSts = resultModels.getKey();
		 * return 
		 * 	'''$«FOR variable : xSts.variableGroups
		 * 									.map[it.variables]
		 * 									.flatten
		 SEPARATOR '_'»«variable.name»«ENDFOR»$'''*/
		} else {
			return ""
		}

	}

	static def keyword(Region region) {
		return '''$$_«region.name»_$$'''
	}

	static def parameters(Port port) {
		return port.outputEvents.flatMap[e|e.parameterDeclarations]
	}

	static def note(Port port) {
		if (port.parameters.isEmpty) {
			return "";
		} else {
			return '''
				note "«port.keyword»" as note_«port.name»
				«IF port.interfaceRealization.isProvided»
					«port.name» .d. note_«port.name»
				«ELSE»
					note_«port.name» .d. «port.name»
				«ENDIF»
			''';
		}
	}

	static def noteAdpt(Port port) {
		if (port.parameters.isEmpty) {
			return "";
		} else {
			return '''
				note "«port.keyword»" as note_«port.name»
				c_«port.name» .. note_«port.name»
			''';
		}
	}

	static def note(ComponentInstance instance) {
		if (instance.derivedType instanceof StatechartDefinition) {
			return '''
				'note "«instance.keyword»" as note_«instance.name»
				'note_«instance.name» .. «instance.name»
			''';
		}
	}

	static def note(Port port, ComponentInstance instance) {
		if (port.parameters.isEmpty) {
			return ""
		} else {
			return '''
				note "«port.keyword(instance)»" as note_«instance.name»_«port.name»
				note_«instance.name»_«port.name» .. «instance.name»__«port.name»
			'''
		}
	}

	static public def String makeSHA1Hash(String input) {
		val md = MessageDigest.getInstance("SHA1");
		md.reset();
		val buffer = input.getBytes("UTF-8");
		md.update(buffer);
		val digest = md.digest();

		var hexStr = "";
		for (i : 0 .. digest.length-1-4) {
			hexStr += Integer.toString(digest.get(i).bitwiseAnd(0xff) + 0x100, 16).substring(1);
		}
		return hexStr;
	}
}
