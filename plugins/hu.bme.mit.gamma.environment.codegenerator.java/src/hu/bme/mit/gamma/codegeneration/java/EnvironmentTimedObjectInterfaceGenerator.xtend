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