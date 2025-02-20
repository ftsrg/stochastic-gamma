package hu.bme.mit.gamma.casestudy.iotsystem.camera_adapter;

import java.util.Collections;
import java.util.List;

import hu.bme.mit.gamma.casestudy.iotsystem.*;

import hu.bme.mit.gamma.casestudy.iotsystem.camera_driver.*;
import hu.bme.mit.gamma.casestudy.iotsystem.camera_software.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;
import hu.bme.mit.gamma.casestudy.iotsystem.camera_control.*;

import hu.bme.mit.gamma.casestudy.iotsystem.camera_software.*;

public class CameraAdapter implements Runnable, CameraAdapterInterface {			
	// Thread running this wrapper instance
	private Thread thread;
	// Wrapped synchronous instance
	private CameraSoftware cameraSoftware;
	// Control port instances
	private SoftwareTimer softwareTimer;
	// Wrapped port instances
	private TrafficSensing trafficSensing;
	private Images images;
	// Main queue
	private LinkedBlockingMultiQueue<String, Event> __asyncQueue = new LinkedBlockingMultiQueue<String, Event>();
	// Subqueues
	private LinkedBlockingMultiQueue<String, Event>.SubQueue trafficQueue;
	private LinkedBlockingMultiQueue<String, Event>.SubQueue triggerQueue;
	
	
	public boolean isEventQueueEmpty(){
		return __asyncQueue.isEmpty();
	}
	
	public CameraAdapter() {
		cameraSoftware = new CameraSoftware();
		softwareTimer = new SoftwareTimer();
		// Wrapped port instances
		trafficSensing = new TrafficSensing();
		images = new Images();
		init();
	}
	
	/** Resets the wrapped component. Must be called to initialize the component. */
	@Override
	public void reset(){
		this.handleBeforeReset();
		this.resetVariables();
		this.resetStateConfigurations();
		this.raiseEntryEvents();
		this.handleAfterReset();
	}
	
	public void handleBeforeReset() {
		//interrupt();
		//
		cameraSoftware.handleBeforeReset();
	}
	
	public void resetVariables() {
		cameraSoftware.resetVariables();
	}
	
	public void resetStateConfigurations() {
		cameraSoftware.resetStateConfigurations();
	}
	
	public void raiseEntryEvents() {
		cameraSoftware.raiseEntryEvents();
	}
	
	public void handleAfterReset() {
		cameraSoftware.handleAfterReset();
		//
	}
	
	/** Creates the subqueues, clocks and enters the wrapped synchronous component. */
	private void init() {
		//cameraSoftware = new CameraSoftware();
		// Creating subqueues: the negative conversion regarding priorities is needed,
		// because the lbmq marks higher priority with lower integer values
		__asyncQueue.addSubQueue("triggerQueue", -(2), (int) 10);
		triggerQueue = __asyncQueue.getSubQueue("triggerQueue");
		__asyncQueue.addSubQueue("trafficQueue", -(1), (int) 10);
		trafficQueue = __asyncQueue.getSubQueue("trafficQueue");
		// The thread has to be started manually
	}
	
	
	// Inner classes representing control ports
	public class SoftwareTimer implements EventStreamInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			triggerQueue.offer(new Event("SoftwareTimer.newEvent"));
		}
		
		
		@Override
		public void registerListener(EventStreamInterface.Listener.Required listener) {
			// No operation as out event are not interpreted in case of control ports
		}
		
		@Override
		public List<EventStreamInterface.Listener.Required> getRegisteredListeners() {
			// Empty list as out event are not interpreted in case of control ports
			return Collections.emptyList();
		}
		
	}
	
	@Override
	public SoftwareTimer getSoftwareTimer() {
		return softwareTimer;
	}
	
	// Inner classes representing wrapped ports
	public class TrafficSensing implements TrafficEventStreamInterface.Required {
		
		@Override
		public void raiseCarArrives() {
			trafficQueue.offer(new Event("TrafficSensing.carArrives"));
		}
		@Override
		public void raiseCarLeaves() {
			trafficQueue.offer(new Event("TrafficSensing.carLeaves"));
		}
		
		
		@Override
		public void registerListener(TrafficEventStreamInterface.Listener.Required listener) {
			cameraSoftware.getTrafficSensing().registerListener(listener);
		}
		
		@Override
		public List<TrafficEventStreamInterface.Listener.Required> getRegisteredListeners() {
			return cameraSoftware.getTrafficSensing().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TrafficSensing getTrafficSensing() {
		return trafficSensing;
	}
	
	public class Images implements DataStreamInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewData() {
			return cameraSoftware.getImages().isRaisedNewData();
		}
		@Override
		public double getBlurred() {
			return cameraSoftware.getImages().getBlurred();
		}
		@Override
		public boolean getCar() {
			return cameraSoftware.getImages().getCar();
		}
		
		@Override
		public void registerListener(DataStreamInterface.Listener.Provided listener) {
			cameraSoftware.getImages().registerListener(listener);
		}
		
		@Override
		public List<DataStreamInterface.Listener.Provided> getRegisteredListeners() {
			return cameraSoftware.getImages().getRegisteredListeners();
		}
		
	}
	
	@Override
	public Images getImages() {
		return images;
	}
	
	/** Manual scheduling. */
	public void schedule() {
		Event event = __asyncQueue.poll();
		if (event == null) {
			// There was no event in the queue
			return;
		}
		processEvent(event);
	}
	
	/** Operation. */
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Event event = __asyncQueue.take();		
				processEvent(event);
			} catch (InterruptedException e) {
				thread.interrupt();
			}
		}
	}
	
	private void processEvent(Event event) {
		if (!isControlEvent(event)) {
			// Event is forwarded to the wrapped component
			forwardEvent(event);
		}
		performControlActions(event);
	}
	
	private boolean isControlEvent(Event event) {
		String portName = event.getEvent().split("\\.")[0];
		return portName.equals("SoftwareTimer");
	}
	
	private void forwardEvent(Event event) {
		switch (event.getEvent()) {
			case "TrafficSensing.carArrives":
				cameraSoftware.getTrafficSensing().raiseCarArrives();
			break;
			case "TrafficSensing.carLeaves":
				cameraSoftware.getTrafficSensing().raiseCarLeaves();
			break;
			default:
				throw new IllegalArgumentException("No such event!");
		}
	}
	
	private void performControlActions(Event event) {
		String[] eventName = event.getEvent().split("\\.");
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("SoftwareTimer")) {
			cameraSoftware.runCycle();
			return;
		}
	}
	
	/** Starts this wrapper instance on a thread. */
	@Override
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public boolean isWaiting() {
		return thread.getState() == Thread.State.WAITING;
	}
	
	/** Stops the thread running this wrapper instance. */
	public void interrupt() {
		thread.interrupt();
	}
	
	public CameraSoftware getCameraSoftware() {
		return cameraSoftware;
	}
	
	

	public String getInQueue(){
		String str="Input events (";
		str=str+"triggerQueue [";
		for (Event event:triggerQueue){
			str=str+event.getEvent().toString()+" : ";
			if (event.getValue() != null){
				for (Object value:event.getValue()){
					str=str+" "+value.toString()+",";
				}
			}
		}
		str=str+"], ";
		str=str+"trafficQueue [";
		for (Event event:trafficQueue){
			str=str+event.getEvent().toString()+" : ";
			if (event.getValue() != null){
				for (Object value:event.getValue()){
					str=str+" "+value.toString()+",";
				}
			}
		}
		str=str+"], ";
		str=str+")";
		return str;
	}
}
