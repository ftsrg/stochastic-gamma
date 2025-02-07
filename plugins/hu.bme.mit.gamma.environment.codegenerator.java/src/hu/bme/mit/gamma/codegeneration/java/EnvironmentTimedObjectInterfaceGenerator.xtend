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
package hu.bme.mit.gamma.codegeneration.java

import hu.bme.mit.gamma.codegeneration.java.util.ElementaryEnvironmentComponentUtility

class EnvironmentTimedObjectInterfaceGenerator {
	 
	 
	protected static final extension ElementaryEnvironmentComponentUtility envUtil = ElementaryEnvironmentComponentUtility.INSTANCE
	
	final String packageName; 
	 
	new (String packageName){
	 	this.packageName=packageName
	}
	 
	def generate(){
	'''
	package «packageName»;
	
	public interface TimedObject {
		public long getEarliestTime();
	}
	'''
	}
	 
	 
}