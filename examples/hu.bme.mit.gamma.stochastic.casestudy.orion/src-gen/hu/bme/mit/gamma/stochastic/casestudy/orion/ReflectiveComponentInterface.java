package hu.bme.mit.gamma.stochastic.casestudy.orion;

import java.util.Objects;

public interface ReflectiveComponentInterface {
	
	void reset();
			
	String[] getPorts();
			
	String[] getEvents(String port);
			
	void raiseEvent(String port, String event, Object[] parameters);
	
	default boolean isRaisedEvent(String port, String event) {
		return isRaisedEvent(port, event, null);
	}
	
	boolean isRaisedEvent(String port, String event, Object[] parameters);
	
	Object[] getEventParameterValues(String port, String event);
	
	void schedule(String instance);
	
	default void schedule() {
		schedule(null);
	}
	
	boolean isStateActive(String region, String state);
	
	String[] getRegions();
	
	String[] getStates(String region);
	
	String[] getVariables();
	
	Object getValue(String variable);
	
	default boolean checkVariableValue(String variable, Object expectedValue) {
		return Objects.deepEquals(getValue(variable), expectedValue);
	}
	
	String[] getComponents();
	
	ReflectiveComponentInterface getComponent(String component);
	
}
