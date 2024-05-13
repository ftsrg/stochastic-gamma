package hu.bme.mit.gamma.stochstic.casestudy.dualgps.gps;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.TimerInterface.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.gps.GPSStatemachine.*;

public class GPS implements GPSInterface, TimedObject{
	// Port instances
	private Faults faults = new Faults();
	private Communication communication = new Communication();
	// Wrapped statemachine
	private GPSStatemachine gPS;
	// Indicates which queue is active in a cycle
	private boolean insertQueue = true;
	private boolean processQueue = false;
	// Event queues for the synchronization of statecharts
	private Queue<Event> eventQueue1 = new LinkedList<Event>();
	private Queue<Event> eventQueue2 = new LinkedList<Event>();
	// Clocks
	private TimerInterface timer = new OneThreadedTimer();
	
	public GPS() {
		gPS = new GPSStatemachine();
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
		gPS.resetVariables();
	}

	public void resetStateConfigurations() {
		gPS.resetStateConfigurations();
	}

	public void raiseEntryEvents() {
		gPS.raiseEntryEvents();
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
	
	public class Faults implements HardwareFailureInterface.Required {
		private List<HardwareFailureInterface.Listener.Required> listeners = new LinkedList<HardwareFailureInterface.Listener.Required>();
		@Override
		public void raiseFailure() {
		getInsertQueue().add(new Event("Faults.failure"));
		}
		@Override
		public void registerListener(HardwareFailureInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		@Override
		public List<HardwareFailureInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public Faults getFaults() {
		return faults;
	}
	
	public class Communication implements SensorInterface.Provided {
		private List<SensorInterface.Listener.Provided> listeners = new LinkedList<SensorInterface.Listener.Provided>();
		@Override
		public boolean isRaisedFailstop() {
			return gPS.getCommunication_failstop_Out();
		}
		@Override
		public void registerListener(SensorInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		@Override
		public List<SensorInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public Communication getCommunication() {
		return communication;
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
public GPSStatemachine getGPS(){
	return gPS;
}
	
	public void runComponent() {
		Queue<Event> eventQueue = getProcessQueue();
		while (!eventQueue.isEmpty()) {
			Event event = eventQueue.remove();
			switch (event.getEvent()) {
				case "Faults.failure": 
					gPS.setFaults_failure_In(true);
				break;
				default:
					throw new IllegalArgumentException("No such event: " + event);
			}
		}
		executeStep();
	}
	
	private void executeStep() {
		gPS.runCycle();
		notifyListeners();
	}
	
	/** Interface method, needed for composite component initialization chain. */
	public void notifyAllListeners() {
		notifyListeners();
	}
	
	public void notifyListeners() {
		if (communication.isRaisedFailstop()) {
			for (SensorInterface.Listener.Provided listener : communication.getRegisteredListeners()) {
				listener.raiseFailstop();
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
				return gPS.getMain() == Main.valueOf(state);
		}
		return false;
	}
	
	
	
	
	
	@Override
	public String toString() {
		String str=gPS.toString();
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
