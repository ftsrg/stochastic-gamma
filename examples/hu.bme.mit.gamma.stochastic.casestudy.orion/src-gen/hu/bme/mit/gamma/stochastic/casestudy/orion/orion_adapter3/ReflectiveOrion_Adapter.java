package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_adapter3;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import java.util.Objects;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system_sync.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm.*;

public class ReflectiveOrion_Adapter implements ReflectiveComponentInterface {
	
	private Orion_Adapter wrappedComponent;
	// Wrapped contained components
	private ReflectiveComponentInterface system = null;
	
	public ReflectiveOrion_Adapter(UnifiedTimerInterface timer) {
		this();
		wrappedComponent.setTimer(timer);
	}
	
	public ReflectiveOrion_Adapter() {
		wrappedComponent = new Orion_Adapter();
	}
	
	public ReflectiveOrion_Adapter(Orion_Adapter wrappedComponent) {
		this.wrappedComponent = wrappedComponent;
	}
	
	public void reset() {
		wrappedComponent.reset();
	}
	
	public Orion_Adapter getWrappedComponent() {
		return wrappedComponent;
	}
	
	public String[] getPorts() {
		return new String[] { "timing", "SystemStatus" };
	}
	
	public String[] getEvents(String port) {
		switch (port) {
			case "timing":
				return new String[] { "schedule" };
			case "SystemStatus":
				return new String[] { "conn", "disconn" };
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
			case "timing.schedule":
				wrappedComponent.getTiming().raiseSchedule();
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
			case "SystemStatus.conn":
				if (wrappedComponent.getSystemStatus().isRaisedConn()) {
					return true;
				}
				break;
			case "SystemStatus.disconn":
				if (wrappedComponent.getSystemStatus().isRaisedDisconn()) {
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
			case "SystemStatus.conn":
				return new Object[0];
			case "SystemStatus.disconn":
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
		return new String[] { "system"};
	}
	
	public ReflectiveComponentInterface getComponent(String component) {
		switch (component) {
			case "system":
				if (system == null) {
					system = new ReflectiveOrionStochSystem(wrappedComponent.getSystem());
				}
				return system;
			// If the class name is given, then it will return itself
			case "Orion_Adapter":
				return this;
		}
		throw new IllegalArgumentException("Not known component: " + component);
	}
	
}
