package hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.gps;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.*;
import java.util.Objects;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.interfaces.*;

public class ReflectiveGPS implements ReflectiveComponentInterface {
	
	private GPS wrappedComponent;
	// Wrapped contained components
	
	
	public ReflectiveGPS() {
		wrappedComponent = new GPS();
	}
	
	public ReflectiveGPS(GPS wrappedComponent) {
		this.wrappedComponent = wrappedComponent;
	}
	
	public void reset() {
		wrappedComponent.reset();
	}
	
	public GPS getWrappedComponent() {
		return wrappedComponent;
	}
	
	public String[] getPorts() {
		return new String[] { "Faults", "Communication" };
	}
	
	public String[] getEvents(String port) {
		switch (port) {
			case "Faults":
				return new String[] { "failure" };
			case "Communication":
				return new String[] { "failstop" };
			default:
				throw new IllegalArgumentException("Not known port: " + port);
		}
	}
	
	public void raiseEvent(String port, String event) {
		raiseEvent(port, event, null);
	}
	
	public void raiseEvent(String port, String event, Object[] parameters) {
		String portEvent = port + "." + event;
		switch (portEvent) {
			case "Faults.failure":
				wrappedComponent.getFaults().raiseFailure();
				break;
			default:
				throw new IllegalArgumentException("Not known port-in event combination: " + portEvent);
		}
	}
	
	public boolean isRaisedEvent(String port, String event) {
		return isRaisedEvent(port, event, null);
	}
	
	public boolean isRaisedEvent(String port, String event, Object[] parameters) {
		String portEvent = port + "." + event;
		switch (portEvent) {
			case "Communication.failstop":
				if (wrappedComponent.getCommunication().isRaisedFailstop()) {
					return true;
				}
				break;
			default:
				throw new IllegalArgumentException("Not known port-out event combination: " + portEvent);
		}
		return false;
	}
	
	public Object[] getEventParameterValues(String port, String event) {
		String portEvent = port + "." + event;
		switch (portEvent) {
			case "Communication.failstop":
				return new Object[0];
			default:
				throw new IllegalArgumentException("Not known port-out event combination: " + portEvent);
		}
	}
	
	public boolean isStateActive(String region, String state) {
		return wrappedComponent.isStateActive(region, state);
	}
	
	public String[] getRegions() {
		return new String[] { "main" };
	}
	
	public String[] getStates(String region) {
		switch (region) {
			case "main":
				return new String[] { "operation_", "failstop" };
		}
		throw new IllegalArgumentException("Not known region: " + region);
	}
	
	public void schedule(String instance) {
		wrappedComponent.runCycle();
	}
	
	public String[] getVariables() {
		return new String[] {  };
	}
	
	public Object getValue(String variable) {
		switch (variable) {
		}
		throw new IllegalArgumentException("Not known variable: " + variable);
	}
	
	public String[] getComponents() {
		return new String[] { };
	}
	
	public ReflectiveComponentInterface getComponent(String component) {
		switch (component) {
			// If the class name is given, then it will return itself
			case "GPS":
				return this;
		}
		throw new IllegalArgumentException("Not known component: " + component);
	}
	
}
