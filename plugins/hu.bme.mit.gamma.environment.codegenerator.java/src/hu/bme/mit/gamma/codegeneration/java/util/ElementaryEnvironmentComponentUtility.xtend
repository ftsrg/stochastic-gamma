package hu.bme.mit.gamma.codegeneration.java.util

import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance
import hu.bme.mit.gamma.environment.stochastic.stochastic.util.StochasticSwitch
import hu.bme.mit.gamma.environment.model.EnvironmentSample
import hu.bme.mit.gamma.environment.model.EnvironmentSwitch

class ElementaryEnvironmentComponentUtility {
	public static val INSTANCE = new ElementaryEnvironmentComponentUtility
	
	def getSchedulingInterfaceName(){
		return "ElementaryComponentSchedulingInterface";
	}
	
	def getSchedulingInterfacePackage(){
		'''scheduling'''
	}
	
	def getScheduingInterfaceImport(String basePackageName){
		'''
		import «basePackageName».«schedulingInterfacePackage».«schedulingInterfaceName»;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		'''
	}
	
	def getListDefinition(){
	'''
	protected List<«schedulingInterfaceName»> envComponents = new ArrayList<«schedulingInterfaceName»>();
	protected Map<String,«schedulingInterfaceName»> envMap = new HashMap<String,«schedulingInterfaceName»>();
	'''
	}
	
	def getRegisterFunc(){
		'''
		public void registerEnvironmentComponent(String name,«schedulingInterfaceName» component){
			envComponents.add(component);
			envMap.put(name,component);
		}
		'''
	}
	
	def getScheduleCall(ElementaryEnvironmentComponentInstance component){
		if (component instanceof EnvironmentSwitch || component instanceof EnvironmentSample){
			return '''envMap.get("«component.name»").schedule();'''
		}else {
			return "";
		}
	}
	
	def getIsEmptyCall(ElementaryEnvironmentComponentInstance component){
		if (component instanceof EnvironmentSwitch || component instanceof EnvironmentSample){
			return '''envMap.get("«component.name»").isEventQueueEmpty()'''
		}else {
			return "true";
		}
	}
	
}