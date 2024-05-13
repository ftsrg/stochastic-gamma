package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system_sync;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import java.util.Objects;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm.*;

public class ReflectiveOrionStochSystem implements ReflectiveComponentInterface {
	
	private OrionStochSystem wrappedComponent;
	// Wrapped contained components
	private ReflectiveComponentInterface master = null;
	private ReflectiveComponentInterface slave = null;
	private ReflectiveComponentInterface connStatus = null;
	
	public ReflectiveOrionStochSystem(UnifiedTimerInterface timer) {
		this();
		wrappedComponent.setTimer(timer);
	}
	
	public ReflectiveOrionStochSystem() {
		wrappedComponent = new OrionStochSystem();
	}
	
	public ReflectiveOrionStochSystem(OrionStochSystem wrappedComponent) {
		this.wrappedComponent = wrappedComponent;
	}
	
	public void reset() {
		wrappedComponent.reset();
	}
	
	public OrionStochSystem getWrappedComponent() {
		return wrappedComponent;
	}
	
	public String[] getPorts() {
		return new String[] { "SystemStatus" };
	}
	
	public String[] getEvents(String port) {
		switch (port) {
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
		return new String[] { "master", "slave", "connStatus"};
	}
	
	public ReflectiveComponentInterface getComponent(String component) {
		switch (component) {
			case "master":
				if (master == null) {
					master = new ReflectiveOrion_Master_SM(wrappedComponent.getMaster());
				}
				return master;
			case "slave":
				if (slave == null) {
					slave = new ReflectiveOrion_Slave_SM(wrappedComponent.getSlave());
				}
				return slave;
			case "connStatus":
				if (connStatus == null) {
					connStatus = new ReflectiveStatus_SM(wrappedComponent.getConnStatus());
				}
				return connStatus;
		}
		throw new IllegalArgumentException("Not known component: " + component);
	}
	
}
