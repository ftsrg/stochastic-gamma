package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm2;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import java.util.Objects;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

public class ReflectiveOrion_Master_SM implements ReflectiveComponentInterface {
	
	private Orion_Master_SM wrappedComponent;
	// Wrapped contained components
	
	public ReflectiveOrion_Master_SM(UnifiedTimerInterface timer) {
		this();
		wrappedComponent.setTimer(timer);
	}
	
	public ReflectiveOrion_Master_SM() {
		wrappedComponent = new Orion_Master_SM();
	}
	
	public ReflectiveOrion_Master_SM(Orion_Master_SM wrappedComponent) {
		this.wrappedComponent = wrappedComponent;
	}
	
	public void reset() {
		wrappedComponent.reset();
	}
	
	public Orion_Master_SM getWrappedComponent() {
		return wrappedComponent;
	}
	
	public String[] getPorts() {
		return new String[] { "Send_StateMachine_Port", "StateMachine_Port", "Status" };
	}
	
	public String[] getEvents(String port) {
		switch (port) {
			case "Send_StateMachine_Port":
				return new String[] { "OrionDisconn", "OrionDisconnCause", "OrionConnReq", "OrionAppData", "OrionKeepAlive", "OrionConnConf", "OrionConnResp" };
			case "StateMachine_Port":
				return new String[] { "OrionDisconn", "OrionDisconnCause", "OrionConnReq", "OrionAppData", "OrionKeepAlive", "OrionConnConf", "OrionConnResp" };
			case "Status":
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
			case "StateMachine_Port.OrionDisconn":
				wrappedComponent.getStateMachine_Port().raiseOrionDisconn();
				break;
			case "StateMachine_Port.OrionDisconnCause":
				wrappedComponent.getStateMachine_Port().raiseOrionDisconnCause();
				break;
			case "StateMachine_Port.OrionConnReq":
				wrappedComponent.getStateMachine_Port().raiseOrionConnReq();
				break;
			case "StateMachine_Port.OrionAppData":
				wrappedComponent.getStateMachine_Port().raiseOrionAppData();
				break;
			case "StateMachine_Port.OrionKeepAlive":
				wrappedComponent.getStateMachine_Port().raiseOrionKeepAlive();
				break;
			case "StateMachine_Port.OrionConnConf":
				wrappedComponent.getStateMachine_Port().raiseOrionConnConf();
				break;
			case "StateMachine_Port.OrionConnResp":
				wrappedComponent.getStateMachine_Port().raiseOrionConnResp();
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
			case "Send_StateMachine_Port.OrionDisconn":
				if (wrappedComponent.getSend_StateMachine_Port().isRaisedOrionDisconn()) {
					return true;
				}
				break;
			case "Send_StateMachine_Port.OrionDisconnCause":
				if (wrappedComponent.getSend_StateMachine_Port().isRaisedOrionDisconnCause()) {
					return true;
				}
				break;
			case "Send_StateMachine_Port.OrionConnReq":
				if (wrappedComponent.getSend_StateMachine_Port().isRaisedOrionConnReq()) {
					return true;
				}
				break;
			case "Send_StateMachine_Port.OrionAppData":
				if (wrappedComponent.getSend_StateMachine_Port().isRaisedOrionAppData()) {
					return true;
				}
				break;
			case "Send_StateMachine_Port.OrionKeepAlive":
				if (wrappedComponent.getSend_StateMachine_Port().isRaisedOrionKeepAlive()) {
					return true;
				}
				break;
			case "Send_StateMachine_Port.OrionConnConf":
				if (wrappedComponent.getSend_StateMachine_Port().isRaisedOrionConnConf()) {
					return true;
				}
				break;
			case "Send_StateMachine_Port.OrionConnResp":
				if (wrappedComponent.getSend_StateMachine_Port().isRaisedOrionConnResp()) {
					return true;
				}
				break;
			case "Status.conn":
				if (wrappedComponent.getStatus().isRaisedConn()) {
					return true;
				}
				break;
			case "Status.disconn":
				if (wrappedComponent.getStatus().isRaisedDisconn()) {
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
			case "Send_StateMachine_Port.OrionDisconn":
				return new Object[0];
			case "Send_StateMachine_Port.OrionDisconnCause":
				return new Object[0];
			case "Send_StateMachine_Port.OrionConnReq":
				return new Object[0];
			case "Send_StateMachine_Port.OrionAppData":
				return new Object[0];
			case "Send_StateMachine_Port.OrionKeepAlive":
				return new Object[0];
			case "Send_StateMachine_Port.OrionConnConf":
				return new Object[0];
			case "Send_StateMachine_Port.OrionConnResp":
				return new Object[0];
			case "Status.conn":
				return new Object[0];
			case "Status.disconn":
				return new Object[0];
			default:
				throw new IllegalArgumentException("Not known port-out event combination: " + portEvent);
		}
	}
	
	public boolean isStateActive(String region, String state) {
		return wrappedComponent.isStateActive(region, state);
	}
	
	public String[] getRegions() {
		return new String[] { "main_region_1", "SendRegion", "RecieveRegion" };
	}
	
	public String[] getStates(String region) {
		switch (region) {
			case "main_region_1":
				return new String[] { "Closed", "Connecting", "Connected" };
			case "SendRegion":
				return new String[] { "KeepAliveSendTimeout" };
			case "RecieveRegion":
				return new String[] { "KeepAliveRecieveTimeout" };
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
			case "Orion_Master_SM":
				return this;
		}
		throw new IllegalArgumentException("Not known component: " + component);
	}
	
}
