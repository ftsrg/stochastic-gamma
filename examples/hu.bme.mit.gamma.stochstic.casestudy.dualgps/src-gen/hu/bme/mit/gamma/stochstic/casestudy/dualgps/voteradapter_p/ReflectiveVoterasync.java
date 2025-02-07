package hu.bme.mit.gamma.stochstic.casestudy.dualgps.voteradapter_p;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.*;
import java.util.Objects;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.gps.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.voter_p.*;

public class ReflectiveVoterasync implements ReflectiveComponentInterface {
	
	private Voterasync wrappedComponent;
	// Wrapped contained components
	private ReflectiveComponentInterface voter = null;
	
	
	public ReflectiveVoterasync(int sensor_num) {
		wrappedComponent = new Voterasync(sensor_num);
	}
	
	public ReflectiveVoterasync(Voterasync wrappedComponent) {
		this.wrappedComponent = wrappedComponent;
	}
	
	public void reset() {
		wrappedComponent.reset();
	}
	
	public Voterasync getWrappedComponent() {
		return wrappedComponent;
	}
	
	public String[] getPorts() {
		return new String[] { "Faults", "Sensor", "Communication" };
	}
	
	public String[] getEvents(String port) {
		switch (port) {
			case "Faults":
				return new String[] { "failure" };
			case "Sensor":
				return new String[] { "failstop" };
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
			case "Sensor.failstop":
				wrappedComponent.getSensor().raiseFailstop();
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
		return false;
	}
	
	public String[] getRegions() {
		return new String[] {  };
	}
	
	public String[] getStates(String region) {
		switch (region) {
		}
		throw new IllegalArgumentException("Not known region: " + region);
	}
	
	public void schedule(String instance) {
		wrappedComponent.schedule();
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
		return new String[] { "voter"};
	}
	
	public ReflectiveComponentInterface getComponent(String component) {
		switch (component) {
			case "voter":
				if (voter == null) {
					voter = new ReflectiveVoter(wrappedComponent.getVoter());
				}
				return voter;
			// If the class name is given, then it will return itself
			case "Voterasync":
				return this;
		}
		throw new IllegalArgumentException("Not known component: " + component);
	}
	
}
