package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_adapter;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import java.util.Objects;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

public class ReflectiveOrion_Master_Adapter implements ReflectiveComponentInterface {
	
	private Orion_Master_Adapter wrappedComponent;
	// Wrapped contained components
	private ReflectiveComponentInterface master = null;
	
	
	public ReflectiveOrion_Master_Adapter() {
		wrappedComponent = new Orion_Master_Adapter();
	}
	
	public ReflectiveOrion_Master_Adapter(Orion_Master_Adapter wrappedComponent) {
		this.wrappedComponent = wrappedComponent;
	}
	
	public void reset() {
		wrappedComponent.reset();
	}
	
	public Orion_Master_Adapter getWrappedComponent() {
		return wrappedComponent;
	}
	
	public String[] getPorts() {
		return new String[] { "Block_Port", "Connection_Port", "StateMachine_Port", "Send_StateMachine_Port", "Process_StateMachine_Port", "TimoeutKeepAliveReceiveTimeout_3", "TimeoutKapcsolodik_2", "TimeoutZarva_0", "TimeoutKeepAliveSendTimeout_1", "TimoeutKeepAliveReceiveTimeout_3_req", "TimeoutKapcsolodik_2_req", "TimeoutZarva_0_req", "TimeoutKeepAliveSendTimeout_1_req", "Status" };
	}
	
	public String[] getEvents(String port) {
		switch (port) {
			case "Block_Port":
				return new String[] { "Operation_Call_SendData", "Operation_Call_Invalid" };
			case "Connection_Port":
				return new String[] { "Operation_Call_Connect", "Operation_Call_Disconn" };
			case "StateMachine_Port":
				return new String[] { "OrionDisconn", "OrionDisconnCause", "OrionConnReq", "OrionAppData", "OrionKeepAlive", "OrionConnConf", "OrionConnResp" };
			case "Send_StateMachine_Port":
				return new String[] { "OrionDisconn", "OrionDisconnCause", "OrionConnReq", "OrionAppData", "OrionKeepAlive", "OrionConnConf", "OrionConnResp" };
			case "Process_StateMachine_Port":
				return new String[] { "OrionDisconn", "OrionDisconnCause", "OrionConnReq", "OrionAppData", "OrionKeepAlive", "OrionConnConf", "OrionConnResp" };
			case "TimoeutKeepAliveReceiveTimeout_3":
				return new String[] { "newEvent" };
			case "TimeoutKapcsolodik_2":
				return new String[] { "newEvent" };
			case "TimeoutZarva_0":
				return new String[] { "newEvent" };
			case "TimeoutKeepAliveSendTimeout_1":
				return new String[] { "newEvent" };
			case "TimoeutKeepAliveReceiveTimeout_3_req":
				return new String[] { "newEvent" };
			case "TimeoutKapcsolodik_2_req":
				return new String[] { "newEvent" };
			case "TimeoutZarva_0_req":
				return new String[] { "newEvent" };
			case "TimeoutKeepAliveSendTimeout_1_req":
				return new String[] { "newEvent" };
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
			case "Block_Port.Operation_Call_SendData":
				wrappedComponent.getBlock_Port().raiseOperation_Call_SendData();
				break;
			case "Block_Port.Operation_Call_Invalid":
				wrappedComponent.getBlock_Port().raiseOperation_Call_Invalid();
				break;
			case "Connection_Port.Operation_Call_Connect":
				wrappedComponent.getConnection_Port().raiseOperation_Call_Connect();
				break;
			case "Connection_Port.Operation_Call_Disconn":
				wrappedComponent.getConnection_Port().raiseOperation_Call_Disconn();
				break;
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
			case "Process_StateMachine_Port.OrionDisconn":
				if (wrappedComponent.getProcess_StateMachine_Port().isRaisedOrionDisconn()) {
					return true;
				}
				break;
			case "Process_StateMachine_Port.OrionDisconnCause":
				if (wrappedComponent.getProcess_StateMachine_Port().isRaisedOrionDisconnCause()) {
					return true;
				}
				break;
			case "Process_StateMachine_Port.OrionConnReq":
				if (wrappedComponent.getProcess_StateMachine_Port().isRaisedOrionConnReq()) {
					return true;
				}
				break;
			case "Process_StateMachine_Port.OrionAppData":
				if (wrappedComponent.getProcess_StateMachine_Port().isRaisedOrionAppData()) {
					return true;
				}
				break;
			case "Process_StateMachine_Port.OrionKeepAlive":
				if (wrappedComponent.getProcess_StateMachine_Port().isRaisedOrionKeepAlive()) {
					return true;
				}
				break;
			case "Process_StateMachine_Port.OrionConnConf":
				if (wrappedComponent.getProcess_StateMachine_Port().isRaisedOrionConnConf()) {
					return true;
				}
				break;
			case "Process_StateMachine_Port.OrionConnResp":
				if (wrappedComponent.getProcess_StateMachine_Port().isRaisedOrionConnResp()) {
					return true;
				}
				break;
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
			case "Process_StateMachine_Port.OrionDisconn":
				return new Object[0];
			case "Process_StateMachine_Port.OrionDisconnCause":
				return new Object[0];
			case "Process_StateMachine_Port.OrionConnReq":
				return new Object[0];
			case "Process_StateMachine_Port.OrionAppData":
				return new Object[0];
			case "Process_StateMachine_Port.OrionKeepAlive":
				return new Object[0];
			case "Process_StateMachine_Port.OrionConnConf":
				return new Object[0];
			case "Process_StateMachine_Port.OrionConnResp":
				return new Object[0];
			case "TimoeutKeepAliveReceiveTimeout_3_req.newEvent":
				return new Object[0];
			case "TimeoutKapcsolodik_2_req.newEvent":
				return new Object[0];
			case "TimeoutZarva_0_req.newEvent":
				return new Object[0];
			case "TimeoutKeepAliveSendTimeout_1_req.newEvent":
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
		return new String[] { "master"};
	}
	
	public ReflectiveComponentInterface getComponent(String component) {
		switch (component) {
			case "master":
				if (master == null) {
					master = new ReflectiveOrion_Master_SM_ext(wrappedComponent.getMaster());
				}
				return master;
			// If the class name is given, then it will return itself
			case "Orion_Master_Adapter":
				return this;
		}
		throw new IllegalArgumentException("Not known component: " + component);
	}
	
}
