package hu.bme.mit.gamma.casestudy.iotsystem_meas.camera_driver;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.*;
import java.util.Objects;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.*;

public class ReflectiveCameraDriver implements ReflectiveComponentInterface {
	
	private CameraDriver wrappedComponent;
	// Wrapped contained components
	
	
	public ReflectiveCameraDriver() {
		wrappedComponent = new CameraDriver();
	}
	
	public ReflectiveCameraDriver(CameraDriver wrappedComponent) {
		this.wrappedComponent = wrappedComponent;
	}
	
	public void reset() {
		wrappedComponent.reset();
	}
	
	public CameraDriver getWrappedComponent() {
		return wrappedComponent;
	}
	
	public String[] getPorts() {
		return new String[] { "Traffic", "Requests", "Images" };
	}
	
	public String[] getEvents(String port) {
		switch (port) {
			case "Traffic":
				return new String[] { "carArrives", "carLeaves" };
			case "Requests":
				return new String[] { "newEvent" };
			case "Images":
				return new String[] { "newData" };
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
			case "Traffic.carArrives":
				wrappedComponent.getTraffic().raiseCarArrives();
				break;
			case "Traffic.carLeaves":
				wrappedComponent.getTraffic().raiseCarLeaves();
				break;
			case "Requests.newEvent":
				wrappedComponent.getRequests().raiseNewEvent();
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
			case "Images.newData":
				if (wrappedComponent.getImages().isRaisedNewData()) {
					if (parameters != null) {
						return
							Objects.deepEquals(parameters[0], wrappedComponent.getImages().getBlurred()) && 
																			Objects.deepEquals(parameters[1], wrappedComponent.getImages().getCar())
												;
					}
					else {
						return true;
					}
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
			case "Images.newData":
				return new Object[] {
					wrappedComponent.getImages().getBlurred(), 
					wrappedComponent.getImages().getCar()
				};
			default:
				throw new IllegalArgumentException("Not known port-out event combination: " + portEvent);
		}
	}
	
	public boolean isStateActive(String region, String state) {
		return wrappedComponent.isStateActive(region, state);
	}
	
	public String[] getRegions() {
		return new String[] { "main", "main2" };
	}
	
	public String[] getStates(String region) {
		switch (region) {
			case "main":
				return new String[] { "CarIsVisible", "CarIsNotVisible" };
			case "main2":
				return new String[] { "service" };
		}
		throw new IllegalArgumentException("Not known region: " + region);
	}
	
	public void schedule(String instance) {
		wrappedComponent.runCycle();
	}
	
	public String[] getVariables() {
		return new String[] { "car" };
	}
	
	public Object getValue(String variable) {
		switch (variable) {
			case "car":
				return wrappedComponent.getCar();
		}
		throw new IllegalArgumentException("Not known variable: " + variable);
	}
	
	public String[] getComponents() {
		return new String[] { };
	}
	
	public ReflectiveComponentInterface getComponent(String component) {
		switch (component) {
			// If the class name is given, then it will return itself
			case "CameraDriver":
				return this;
		}
		throw new IllegalArgumentException("Not known component: " + component);
	}
	
}
