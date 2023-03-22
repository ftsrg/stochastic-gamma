package hu.bme.mit.gamma.codegeneration.java

import hu.bme.mit.gamma.codegeneration.java.util.ElementaryEnvironmentComponentUtility

class EnvironmentSchedulingInterfaceGenerator {
	 
	 
	protected static final extension ElementaryEnvironmentComponentUtility envUtil = ElementaryEnvironmentComponentUtility.INSTANCE
	
	final String packageName; 
	 
	new (String packageName){
	 	this.packageName=packageName
	}
	 
	 def generate(){
	 	'''
	 	package «packageName».«envUtil.schedulingInterfacePackage»;
	 	
	 	public interface «envUtil.schedulingInterfaceName» {
	 		public void schedule();
	 		public boolean isEmpty();
	 	}
	 	
	 	'''
	 }
	 
	 
}