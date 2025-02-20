package hu.bme.mit.gamma.casestudy.iotsystem.cloud;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.TimerInterface.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;
import hu.bme.mit.gamma.casestudy.iotsystem.cloud.EdgeStatemachine.*;

public class Edge implements EdgeInterface, TimedObject{
	// Port instances
	private Camera camera = new Camera();
	private TrafficStream trafficStream = new TrafficStream();
	private LostImage lostImage = new LostImage();
	private CarLeave carLeave = new CarLeave();
	// Wrapped statemachine
	private EdgeStatemachine edge;
	// Indicates which queue is active in a cycle
	private boolean insertQueue = true;
	private boolean processQueue = false;
	// Event queues for the synchronization of statecharts
	private Queue<Event> eventQueue1 = new LinkedList<Event>();
	private Queue<Event> eventQueue2 = new LinkedList<Event>();
	// Clocks
	private TimerInterface timer = new OneThreadedTimer();
	
	public Edge() {
		edge = new EdgeStatemachine();
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
		edge.resetVariables();
	}

	public void resetStateConfigurations() {
		edge.resetStateConfigurations();
	}

	public void raiseEntryEvents() {
		edge.raiseEntryEvents();
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
	
	public class Camera implements DataStreamInterface.Required {
		private List<DataStreamInterface.Listener.Required> listeners = new LinkedList<DataStreamInterface.Listener.Required>();
		@Override
		public void raiseNewData(double blurred, boolean car) {
		getInsertQueue().add(new Event("Camera.newData", blurred, car));
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
	
	public Camera getCamera() {
		return camera;
	}
	
	public class TrafficStream implements TrafficEventStreamInterface.Required {
		private List<TrafficEventStreamInterface.Listener.Required> listeners = new LinkedList<TrafficEventStreamInterface.Listener.Required>();
		@Override
		public void raiseCarArrives() {
		getInsertQueue().add(new Event("TrafficStream.carArrives"));
		}
		@Override
		public void raiseCarLeaves() {
		getInsertQueue().add(new Event("TrafficStream.carLeaves"));
		}
		@Override
		public void registerListener(TrafficEventStreamInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		@Override
		public List<TrafficEventStreamInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public TrafficStream getTrafficStream() {
		return trafficStream;
	}
	
	public class LostImage implements EventStreamInterface.Provided {
		private List<EventStreamInterface.Listener.Provided> listeners = new LinkedList<EventStreamInterface.Listener.Provided>();
		@Override
		public boolean isRaisedNewEvent() {
			return edge.getLostImage_newEvent_Out();
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
	
	public LostImage getLostImage() {
		return lostImage;
	}
	
	public class CarLeave implements EventStreamInterface.Provided {
		private List<EventStreamInterface.Listener.Provided> listeners = new LinkedList<EventStreamInterface.Listener.Provided>();
		@Override
		public boolean isRaisedNewEvent() {
			return edge.getCarLeave_newEvent_Out();
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
	
	public CarLeave getCarLeave() {
		return carLeave;
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
public EdgeStatemachine getEdge(){
	return edge;
}
	
	public void runComponent() {
		Queue<Event> eventQueue = getProcessQueue();
		while (!eventQueue.isEmpty()) {
			Event event = eventQueue.remove();
			switch (event.getEvent()) {
				case "Camera.newData": 
					edge.setCamera_newData_In(true);
					edge.setCamera_newData_In_blurred(((double) event.getValue()[0]));
					edge.setCamera_newData_In_car(((boolean) event.getValue()[1]));
				break;
				case "TrafficStream.carArrives": 
					edge.setTrafficStream_carArrives_In(true);
				break;
				case "TrafficStream.carLeaves": 
					edge.setTrafficStream_carLeaves_In(true);
				break;
				default:
					throw new IllegalArgumentException("No such event: " + event);
			}
		}
		executeStep();
	}
	
	private void executeStep() {
		edge.runCycle();
		notifyListeners();
	}
	
	/** Interface method, needed for composite component initialization chain. */
	public void notifyAllListeners() {
		notifyListeners();
	}
	
	public void notifyListeners() {
		if (lostImage.isRaisedNewEvent()) {
			for (EventStreamInterface.Listener.Provided listener : lostImage.getRegisteredListeners()) {
				listener.raiseNewEvent();
			}
		}
		if (carLeave.isRaisedNewEvent()) {
			for (EventStreamInterface.Listener.Provided listener : carLeave.getRegisteredListeners()) {
				listener.raiseNewEvent();
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
				return edge.getMain() == Main.valueOf(state);
		}
		return false;
	}
	
	public double getIsblurred() {
		return edge.getIsblurred();
	}
	
	
	
	
	@Override
	public String toString() {
		String str=edge.toString();
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
