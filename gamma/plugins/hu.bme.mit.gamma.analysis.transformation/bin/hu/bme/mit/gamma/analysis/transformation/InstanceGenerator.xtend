package hu.bme.mit.gamma.analysis.transformation

import hu.bme.mit.gamma.environment.model.EnvironmentDelay
import hu.bme.mit.gamma.environment.model.EnvironmentSample
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch
import hu.bme.mit.gamma.environment.model.EnvironmentExternSimulation
import hu.bme.mit.gamma.environment.model.EnvironmentEventSource

class InstanceGenerator {
	
	static def generateClass(){
		'''
		class ElementaryComponent():
			def __init__(self,events):
		'''
	}
	
	static dispatch def generate(EnvironmentDelay instance){
		
	}
	
	static dispatch def generate(EnvironmentSample instance){
		
	}
	
	static dispatch def generate(EnvironmentSwitch instance){
		
	}
	
	static dispatch def generate(EnvironmentExternSimulation instance){
		
	}
	
	static dispatch def generate(EnvironmentEventSource instance){
		
	}
	
}