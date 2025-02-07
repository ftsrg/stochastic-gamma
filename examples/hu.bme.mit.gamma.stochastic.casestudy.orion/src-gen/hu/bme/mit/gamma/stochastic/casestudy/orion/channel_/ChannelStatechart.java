package hu.bme.mit.gamma.stochastic.casestudy.orion.channel_;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.TimerInterface.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.ChannelStatechartStatemachine.*;

public class ChannelStatechart implements ChannelStatechartInterface, TimedObject{
	// Port instances
	private Output output = new Output();
	private Input input = new Input();
	// Wrapped statemachine
	private ChannelStatechartStatemachine channelStatechart;
	// Indicates which queue is active in a cycle
	private boolean insertQueue = true;
	private boolean processQueue = false;
	// Event queues for the synchronization of statecharts
	private Queue<Event> eventQueue1 = new LinkedList<Event>();
	private Queue<Event> eventQueue2 = new LinkedList<Event>();
	// Clocks
	private TimerInterface timer = new OneThreadedTimer();
	
	public ChannelStatechart() {
		channelStatechart = new ChannelStatechartStatemachine();
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
		channelStatechart.resetVariables();
	}

	public void resetStateConfigurations() {
		channelStatechart.resetStateConfigurations();
	}

	public void raiseEntryEvents() {
		channelStatechart.raiseEntryEvents();
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
	
	public class Output implements StateMachine_Interface_For_OrionInterface.Provided {
		private List<StateMachine_Interface_For_OrionInterface.Listener.Provided> listeners = new LinkedList<StateMachine_Interface_For_OrionInterface.Listener.Provided>();
		@Override
		public boolean isRaisedOrionKeepAlive() {
			return channelStatechart.getOutput_OrionKeepAlive_Out();
		}
		@Override
		public boolean isRaisedOrionDisconn() {
			return channelStatechart.getOutput_OrionDisconn_Out();
		}
		@Override
		public boolean isRaisedOrionDisconnCause() {
			return channelStatechart.getOutput_OrionDisconnCause_Out();
		}
		@Override
		public boolean isRaisedOrionAppData() {
			return channelStatechart.getOutput_OrionAppData_Out();
		}
		@Override
		public boolean isRaisedOrionConnConf() {
			return channelStatechart.getOutput_OrionConnConf_Out();
		}
		@Override
		public boolean isRaisedOrionConnResp() {
			return channelStatechart.getOutput_OrionConnResp_Out();
		}
		@Override
		public boolean isRaisedOrionConnReq() {
			return channelStatechart.getOutput_OrionConnReq_Out();
		}
		@Override
		public void registerListener(StateMachine_Interface_For_OrionInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		@Override
		public List<StateMachine_Interface_For_OrionInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public Output getOutput() {
		return output;
	}
	
	public class Input implements StateMachine_Interface_For_OrionInterface.Required {
		private List<StateMachine_Interface_For_OrionInterface.Listener.Required> listeners = new LinkedList<StateMachine_Interface_For_OrionInterface.Listener.Required>();
		@Override
		public void raiseOrionKeepAlive() {
		getInsertQueue().add(new Event("Input.OrionKeepAlive"));
		}
		@Override
		public void raiseOrionDisconn() {
		getInsertQueue().add(new Event("Input.OrionDisconn"));
		}
		@Override
		public void raiseOrionDisconnCause() {
		getInsertQueue().add(new Event("Input.OrionDisconnCause"));
		}
		@Override
		public void raiseOrionAppData() {
		getInsertQueue().add(new Event("Input.OrionAppData"));
		}
		@Override
		public void raiseOrionConnConf() {
		getInsertQueue().add(new Event("Input.OrionConnConf"));
		}
		@Override
		public void raiseOrionConnResp() {
		getInsertQueue().add(new Event("Input.OrionConnResp"));
		}
		@Override
		public void raiseOrionConnReq() {
		getInsertQueue().add(new Event("Input.OrionConnReq"));
		}
		@Override
		public void registerListener(StateMachine_Interface_For_OrionInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		@Override
		public List<StateMachine_Interface_For_OrionInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public Input getInput() {
		return input;
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
public ChannelStatechartStatemachine getChannelStatechart(){
	return channelStatechart;
}
	
	public void runComponent() {
		Queue<Event> eventQueue = getProcessQueue();
		while (!eventQueue.isEmpty()) {
			Event event = eventQueue.remove();
			switch (event.getEvent()) {
				case "Input.OrionKeepAlive": 
					channelStatechart.setInput_OrionKeepAlive_In(true);
				break;
				case "Input.OrionDisconn": 
					channelStatechart.setInput_OrionDisconn_In(true);
				break;
				case "Input.OrionDisconnCause": 
					channelStatechart.setInput_OrionDisconnCause_In(true);
				break;
				case "Input.OrionAppData": 
					channelStatechart.setInput_OrionAppData_In(true);
				break;
				case "Input.OrionConnConf": 
					channelStatechart.setInput_OrionConnConf_In(true);
				break;
				case "Input.OrionConnResp": 
					channelStatechart.setInput_OrionConnResp_In(true);
				break;
				case "Input.OrionConnReq": 
					channelStatechart.setInput_OrionConnReq_In(true);
				break;
				default:
					throw new IllegalArgumentException("No such event: " + event);
			}
		}
		executeStep();
	}
	
	private void executeStep() {
		channelStatechart.runCycle();
		notifyListeners();
	}
	
	/** Interface method, needed for composite component initialization chain. */
	public void notifyAllListeners() {
		notifyListeners();
	}
	
	public void notifyListeners() {
		if (output.isRaisedOrionKeepAlive()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : output.getRegisteredListeners()) {
				listener.raiseOrionKeepAlive();
			}
		}
		if (output.isRaisedOrionDisconn()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : output.getRegisteredListeners()) {
				listener.raiseOrionDisconn();
			}
		}
		if (output.isRaisedOrionDisconnCause()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : output.getRegisteredListeners()) {
				listener.raiseOrionDisconnCause();
			}
		}
		if (output.isRaisedOrionAppData()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : output.getRegisteredListeners()) {
				listener.raiseOrionAppData();
			}
		}
		if (output.isRaisedOrionConnConf()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : output.getRegisteredListeners()) {
				listener.raiseOrionConnConf();
			}
		}
		if (output.isRaisedOrionConnResp()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : output.getRegisteredListeners()) {
				listener.raiseOrionConnResp();
			}
		}
		if (output.isRaisedOrionConnReq()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : output.getRegisteredListeners()) {
				listener.raiseOrionConnReq();
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
			case "OrionDisconnRegion":
				return channelStatechart.getOrionDisconnRegion() == OrionDisconnRegion.valueOf(state);
		}
		return false;
	}
	
	
	
	
	
	@Override
	public String toString() {
		String str=channelStatechart.toString();
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
