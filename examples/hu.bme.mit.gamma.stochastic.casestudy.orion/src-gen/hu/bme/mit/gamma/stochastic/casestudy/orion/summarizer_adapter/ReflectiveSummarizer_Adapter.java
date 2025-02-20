package hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer_adapter;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import java.util.Objects;
import hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

public class ReflectiveSummarizer_Adapter implements ReflectiveComponentInterface {
	
	private Summarizer_Adapter wrappedComponent;
	// Wrapped contained components
	private ReflectiveComponentInterface summarizerComp = null;
	
	
	public ReflectiveSummarizer_Adapter() {
		wrappedComponent = new Summarizer_Adapter();
	}
	
	public ReflectiveSummarizer_Adapter(Summarizer_Adapter wrappedComponent) {
		this.wrappedComponent = wrappedComponent;
	}
	
	public void reset() {
		wrappedComponent.reset();
	}
	
	public Summarizer_Adapter getWrappedComponent() {
		return wrappedComponent;
	}
	
	public String[] getPorts() {
		return new String[] { "inPort", "outPort" };
	}
	
	public String[] getEvents(String port) {
		switch (port) {
			case "inPort":
				return new String[] { "conn", "disconn" };
			case "outPort":
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
			case "inPort.conn":
				wrappedComponent.getInPort().raiseConn();
				break;
			case "inPort.disconn":
				wrappedComponent.getInPort().raiseDisconn();
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
			case "outPort.conn":
				if (wrappedComponent.getOutPort().isRaisedConn()) {
					return true;
				}
				break;
			case "outPort.disconn":
				if (wrappedComponent.getOutPort().isRaisedDisconn()) {
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
			case "outPort.conn":
				return new Object[0];
			case "outPort.disconn":
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
		return new String[] { "summarizerComp"};
	}
	
	public ReflectiveComponentInterface getComponent(String component) {
		switch (component) {
			case "summarizerComp":
				if (summarizerComp == null) {
					summarizerComp = new ReflectiveSummarizer(wrappedComponent.getSummarizerComp());
				}
				return summarizerComp;
			// If the class name is given, then it will return itself
			case "Summarizer_Adapter":
				return this;
		}
		throw new IllegalArgumentException("Not known component: " + component);
	}
	
}
