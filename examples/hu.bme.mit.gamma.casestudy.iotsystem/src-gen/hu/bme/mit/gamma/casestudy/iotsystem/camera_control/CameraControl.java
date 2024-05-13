package hu.bme.mit.gamma.casestudy.iotsystem.camera_control;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.TimerInterface.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;
import hu.bme.mit.gamma.casestudy.iotsystem.camera_control.CameraControlStatemachine.*;

public class CameraControl implements CameraControlInterface, TimedObject{
	// Port instances
	private Requests requests = new Requests();
	private DriverImages driverImages = new DriverImages();
	private NetworkImages networkImages = new NetworkImages();
	// Wrapped statemachine
	private CameraControlStatemachine cameraControl;
	// Indicates which queue is active in a cycle
	private boolean insertQueue = true;
	private boolean processQueue = false;
	// Event queues for the synchronization of statecharts
	private Queue<Event> eventQueue1 = new LinkedList<Event>();
	private Queue<Event> eventQueue2 = new LinkedList<Event>();
	// Clocks
	private TimerInterface timer = new OneThreadedTimer();
	
	public CameraControl() {
		cameraControl = new CameraControlStatemachine();
	}
	
	public void reset() {
		this.handleBeforeReset();
		this.resetVariables();
		this.resetStateConfigurations();
		this.raiseEntryEvents();
		this.handleAfterReset();
	}

	public void handleBeforeReset() {
		// Clearing the in events
		insertQueue = true;
		processQueue = false;
		eventQueue1.clear();
		eventQueue2.clear();
	}

	public void resetVariables() {
		cameraControl.resetVariables();
	}

	public void resetStateConfigurations() {
		cameraControl.resetStateConfigurations();
	}

	public void raiseEntryEvents() {
		cameraControl.raiseEntryEvents();
	}

	public void handleAfterReset() {
		timer.saveTime(this);
		notifyListeners();
	}

	/** Changes the event queues of the component instance. Should be used only be the container (composite system) class. */
	public void changeEventQueues() {
	insertQueue = !insertQueue;
	processQueue = !processQueue;
	}
	
	/** Changes the event queues to which the events are put. Should be used only be a cascade container (composite system) class. */
	public void changeInsertQueue() {
		insertQueue = !insertQueue;
	}
	
	/** Returns whether the eventQueue containing incoming messages is empty. Should be used only be the container (composite system) class. */
	public boolean isEventQueueEmpty() {
		return getInsertQueue().isEmpty();
	}
	
	/** Returns the event queue into which events should be put in the particular cycle. */
	public Queue<Event> getInsertQueue() {
		if (insertQueue) {
			return eventQueue1;
		}
		return eventQueue2;
	}
	
	/** Returns the event queue from which events should be inspected in the particular cycle. */
	private Queue<Event> getProcessQueue() {
		if (processQueue) {
			return eventQueue1;
		}
		return eventQueue2;
	}
	
	public class Requests implements EventStreamInterface.Provided {
		private List<EventStreamInterface.Listener.Provided> listeners = new LinkedList<EventStreamInterface.Listener.Provided>();
		@Override
		public boolean isRaisedNewEvent() {
			return cameraControl.getRequests_newEvent_Out();
		}
		@Override
		public void registerListener(EventStreamInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		@Override
		public List<EventStreamInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public Requests getRequests() {
		return requests;
	}
	
	public class DriverImages implements DataStreamInterface.Required {
		private List<DataStreamInterface.Listener.Required> listeners = new LinkedList<DataStreamInterface.Listener.Required>();
		@Override
		public void raiseNewData(double blurred, boolean car) {
		getInsertQueue().add(new Event("DriverImages.newData", blurred, car));
		}
		@Override
		public void registerListener(DataStreamInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		@Override
		public List<DataStreamInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public DriverImages getDriverImages() {
		return driverImages;
	}
	
	public class NetworkImages implements DataStreamInterface.Provided {
		private List<DataStreamInterface.Listener.Provided> listeners = new LinkedList<DataStreamInterface.Listener.Provided>();
		@Override
		public boolean isRaisedNewData() {
			return cameraControl.getNetworkImages_newData_Out();
		}
		@Override
		public double getBlurred() {
			return cameraControl.getNetworkImages_newData_Out_blurred();
		}
		@Override
		public boolean getCar() {
			return cameraControl.getNetworkImages_newData_Out_car();
		}
		@Override
		public void registerListener(DataStreamInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		@Override
		public List<DataStreamInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public NetworkImages getNetworkImages() {
		return networkImages;
	}
	
	public void runCycle() {
		changeEventQueues();
		runComponent();
	}
	
	//universal scheduling interface
	public void schedule() {
		runCycle();
	}
	
//get the wrapped statemachine
public CameraControlStatemachine getCameraControl(){
	return cameraControl;
}
	
	public void runComponent() {
		Queue<Event> eventQueue = getProcessQueue();
		while (!eventQueue.isEmpty()) {
			Event event = eventQueue.remove();
			switch (event.getEvent()) {
				case "DriverImages.newData": 
					cameraControl.setDriverImages_newData_In(true);
					cameraControl.setDriverImages_newData_In_blurred(((double) event.getValue()[0]));
					cameraControl.setDriverImages_newData_In_car(((boolean) event.getValue()[1]));
				break;
				default:
					throw new IllegalArgumentException("No such event: " + event);
			}
		}
		executeStep();
	}
	
	private void executeStep() {
		cameraControl.runCycle();
		notifyListeners();
	}
	
	/** Interface method, needed for composite component initialization chain. */
	public void notifyAllListeners() {
		notifyListeners();
	}
	
	public void notifyListeners() {
		if (requests.isRaisedNewEvent()) {
			for (EventStreamInterface.Listener.Provided listener : requests.getRegisteredListeners()) {
				listener.raiseNewEvent();
			}
		}
		if (networkImages.isRaisedNewData()) {
			for (DataStreamInterface.Listener.Provided listener : networkImages.getRegisteredListeners()) {
				listener.raiseNewData(cameraControl.getNetworkImages_newData_Out_blurred(), cameraControl.getNetworkImages_newData_Out_car());
			}
		}
	}
	
	public void setTimer(TimerInterface timer) {
		this.timer = timer;
	}
	
	@Override
	public long getEarliestTime(){
				return Long.MAX_VALUE;
	}
	
	public boolean isStateActive(String region, String state) {
		switch (region) {
			case "main":
				return cameraControl.getMain() == Main.valueOf(state);
			case "main2":
				return cameraControl.getMain2() == Main2.valueOf(state);
		}
		return false;
	}
	
	
	
	
	
	@Override
	public String toString() {
		String str=cameraControl.toString();
		str=str+"\n "+getInQueue();
		return str;
	}
	
	public String getInQueue(){
		String str="Input events (";
		for (Event event:getInsertQueue()){
			str=str+event.getEvent().toString()+" : ";
			if (event.getValue() != null){
				for (Object value:event.getValue()){
					str=str+" "+value.toString()+",";
				}
			}
		}
		str=str+")";
		return str;
	}
}
