package hu.bme.mit.gamma.casestudy.iotsystem.camera_component;

import java.util.List;
import java.util.LinkedList;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.communication_adapter.*;
import hu.bme.mit.gamma.casestudy.iotsystem.camera_control.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;
import hu.bme.mit.gamma.casestudy.iotsystem.camera_software.*;
import hu.bme.mit.gamma.casestudy.iotsystem.camera_adapter.*;
import hu.bme.mit.gamma.casestudy.iotsystem.camera_driver.*;
import hu.bme.mit.gamma.casestudy.iotsystem.network.*;
import hu.bme.mit.gamma.casestudy.iotsystem.channels.*;


import hu.bme.mit.gamma.casestudy.iotsystem.scheduling.ElementaryComponentSchedulingInterface;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class CameraComponent implements CameraComponentInterface {
	// Component instances
	private CameraAdapter software;
	private NetworkAdapter networkQueue;
	// Environmental Component instances
	// Port instances
	private TrafficSensing trafficSensing = new TrafficSensing();
	private OutputData outputData = new OutputData();
	// Channel instances
	private DataStreamChannelInterface channelImageOutOfNetworkLoss;
	private DataStreamChannelInterface channelImagesOfSoftware;
	private DataStreamChannelInterface channelLostImagesOfNetworkLoss;
	private EventStreamChannelInterface channelEventsOfSoftwareTimer;
	
	
	protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
	protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
	
	public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
		envComponents.add(component);
		envMap.put(name,component);
	}
	
	public boolean isEventQueueEmpty(){
		return  software.isEventQueueEmpty()  &&  networkQueue.isEventQueueEmpty()   &&   true &&   envMap.get("networkLoss").isEventQueueEmpty()  ;
	}
	
	public void schedule(){
		while(!isEventQueueEmpty()){
			software.schedule();
			networkQueue.schedule();
			envMap.get("networkLoss").schedule();
		}
	}
	
	public CameraComponent() {
		software = new CameraAdapter();
		networkQueue = new NetworkAdapter();
		trafficSensing = new TrafficSensing();
		outputData = new OutputData();
		// Environmental Component instances
		init();
	}
	
	/** Resets the contained statemachines recursively. Must be called to initialize the component. */
	@Override
	public void reset() {
		software.reset();
		networkQueue.reset();
	}
	
	/** Creates the channel mappings and enters the wrapped statemachines. */
	private void init() {				
		// Registration of simple channels
		// Registration of broadcast channels
	}
	
	// Inner classes representing Ports
	public class TrafficSensing implements TrafficEventStreamInterface.Required {
	
		@Override
		public void raiseCarArrives() {
			software.getTrafficSensing().raiseCarArrives();
		}
		
		@Override
		public void raiseCarLeaves() {
			software.getTrafficSensing().raiseCarLeaves();
		}
		
		
		@Override
		public void registerListener(TrafficEventStreamInterface.Listener.Required listener) {
			software.getTrafficSensing().registerListener(listener);
		}
		
		@Override
		public List<TrafficEventStreamInterface.Listener.Required> getRegisteredListeners() {
			return software.getTrafficSensing().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TrafficSensing getTrafficSensing() {
		return trafficSensing;
	}
	
	public class OutputData implements DataStreamInterface.Provided {
	
		
		@Override
		public boolean isRaisedNewData() {
			return networkQueue.getImageOut().isRaisedNewData();
		}
		@Override
		public double getBlurred() {
			return networkQueue.getImageOut().getBlurred();
		}
		@Override
		public boolean getCar() {
			return networkQueue.getImageOut().getCar();
		}
		
		@Override
		public void registerListener(DataStreamInterface.Listener.Provided listener) {
			networkQueue.getImageOut().registerListener(listener);
		}
		
		@Override
		public List<DataStreamInterface.Listener.Provided> getRegisteredListeners() {
			return networkQueue.getImageOut().getRegisteredListeners();
		}
		
	}
	
	@Override
	public OutputData getOutputData() {
		return outputData;
	}
	
	/** Starts the running of the asynchronous component. */
	@Override
	public void start() {
		software.start();
		networkQueue.start();
	}
	
	public boolean isWaiting() {
		return software.isWaiting() && networkQueue.isWaiting()
					;
	}
	
	
	/**  Getter for component instances, e.g., enabling to check their states. */
	public CameraAdapter getSoftware() {
		return software;
	}
	
	public NetworkAdapter getNetworkQueue() {
		return networkQueue;
	}
	
}