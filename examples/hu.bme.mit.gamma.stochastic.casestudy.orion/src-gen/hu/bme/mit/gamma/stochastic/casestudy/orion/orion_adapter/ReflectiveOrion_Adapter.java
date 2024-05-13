package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_adapter;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import java.util.Objects;
import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm_ext.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm.*;

public class ReflectiveOrion_Adapter implements ReflectiveComponentInterface {
	
	private Orion_Adapter wrappedComponent;
	// Wrapped contained components
	private ReflectiveComponentInterface system = null;
	
	
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
		return new String[] { "TimoeutKeepAliveReceiveTimeout_3", "TimeoutKapcsolodik_2", "TimeoutZarva_0", "TimeoutKeepAliveSendTimeout_1", "TimeoutKeepAliveReceiveTimeout_4", "TimeoutKapcsolodik_3", "TimeoutKeepAliveSendTimeout_0", "TimoeutKeepAliveReceiveTimeout_3_req", "TimeoutKapcsolodik_2_req", "TimeoutZarva_0_req", "TimeoutKeepAliveSendTimeout_1_req", "TimeoutKeepAliveReceiveTimeout_4_req", "TimeoutKapcsolodik_3_req", "TimeoutKeepAliveSendTimeout_0_req", "SystemStatus" };
	}
	
	public String[] getEvents(String port) {
		switch (port) {
			case "TimoeutKeepAliveReceiveTimeout_3":
				return new String[] { "newEvent" };
			case "TimeoutKapcsolodik_2":
				return new String[] { "newEvent" };
			case "TimeoutZarva_0":
				return new String[] { "newEvent" };
			case "TimeoutKeepAliveSendTimeout_1":
				return new String[] { "newEvent" };
			case "TimeoutKeepAliveReceiveTimeout_4":
				return new String[] { "newEvent" };
			case "TimeoutKapcsolodik_3":
				return new String[] { "newEvent" };
			case "TimeoutKeepAliveSendTimeout_0":
				return new String[] { "newEvent" };
			case "TimoeutKeepAliveReceiveTimeout_3_req":
				return new String[] { "newEvent" };
			case "TimeoutKapcsolodik_2_req":
				return new String[] { "newEvent" };
			case "TimeoutZarva_0_req":
				return new String[] { "newEvent" };
			case "TimeoutKeepAliveSendTimeout_1_req":
				return new String[] { "newEvent" };
			case "TimeoutKeepAliveReceiveTimeout_4_req":
				return new String[] { "newEvent" };
			case "TimeoutKapcsolodik_3_req":
				return new String[] { "newEvent" };
			case "TimeoutKeepAliveSendTimeout_0_req":
				return new String[] { "newEvent" };
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
			case "TimoeutKeepAliveReceiveTimeout_3.newEvent":
				wrappedComponent.getTimoeutKeepAliveReceiveTimeout_3().raiseNewEvent();
				break;
			case "TimeoutKapcsolodik_2.newEvent":
				wrappedComponent.getTimeoutKapcsolodik_2().raiseNewEvent();
				break;
			case "TimeoutZarva_0.newEvent":
				wrappedComponent.getTimeoutZarva_0().raiseNewEvent();
				break;
			case "TimeoutKeepAliveSendTimeout_1.newEvent":
				wrappedComponent.getTimeoutKeepAliveSendTimeout_1().raiseNewEvent();
				break;
			case "TimeoutKeepAliveReceiveTimeout_4.newEvent":
				wrappedComponent.getTimeoutKeepAliveReceiveTimeout_4().raiseNewEvent();
				break;
			case "TimeoutKapcsolodik_3.newEvent":
				wrappedComponent.getTimeoutKapcsolodik_3().raiseNewEvent();
				break;
			case "TimeoutKeepAliveSendTimeout_0.newEvent":
				wrappedComponent.getTimeoutKeepAliveSendTimeout_0().raiseNewEvent();
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
			case "TimoeutKeepAliveReceiveTimeout_3_req.newEvent":
				if (wrappedComponent.getTimoeutKeepAliveReceiveTimeout_3_req().isRaisedNewEvent()) {
					return true;
				}
				break;
			case "TimeoutKapcsolodik_2_req.newEvent":
				if (wrappedComponent.getTimeoutKapcsolodik_2_req().isRaisedNewEvent()) {
					return true;
				}
				break;
			case "TimeoutZarva_0_req.newEvent":
				if (wrappedComponent.getTimeoutZarva_0_req().isRaisedNewEvent()) {
					return true;
				}
				break;
			case "TimeoutKeepAliveSendTimeout_1_req.newEvent":
				if (wrappedComponent.getTimeoutKeepAliveSendTimeout_1_req().isRaisedNewEvent()) {
					return true;
				}
				break;
			case "TimeoutKeepAliveReceiveTimeout_4_req.newEvent":
				if (wrappedComponent.getTimeoutKeepAliveReceiveTimeout_4_req().isRaisedNewEvent()) {
					return true;
				}
				break;
			case "TimeoutKapcsolodik_3_req.newEvent":
				if (wrappedComponent.getTimeoutKapcsolodik_3_req().isRaisedNewEvent()) {
					return true;
				}
				break;
			case "TimeoutKeepAliveSendTimeout_0_req.newEvent":
				if (wrappedComponent.getTimeoutKeepAliveSendTimeout_0_req().isRaisedNewEvent()) {
					return true;
				}
				break;
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
			case "TimoeutKeepAliveReceiveTimeout_3_req.newEvent":
				return new Object[0];
			case "TimeoutKapcsolodik_2_req.newEvent":
				return new Object[0];
			case "TimeoutZarva_0_req.newEvent":
				return new Object[0];
			case "TimeoutKeepAliveSendTimeout_1_req.newEvent":
				return new Object[0];
			case "TimeoutKeepAliveReceiveTimeout_4_req.newEvent":
				return new Object[0];
			case "TimeoutKapcsolodik_3_req.newEvent":
				return new Object[0];
			case "TimeoutKeepAliveSendTimeout_0_req.newEvent":
				return new Object[0];
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
