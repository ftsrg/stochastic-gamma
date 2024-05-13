package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm2;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.TimerInterface.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm2.Orion_Master_SMStatemachine.*;

public class Orion_Master_SM implements Orion_Master_SMInterface, TimedObject{
	// Port instances
	private Send_StateMachine_Port send_StateMachine_Port = new Send_StateMachine_Port();
	private StateMachine_Port stateMachine_Port = new StateMachine_Port();
	private Status status = new Status();
	// Wrapped statemachine
	private Orion_Master_SMStatemachine orion_Master_SM;
	// Indicates which queue is active in a cycle
	private boolean insertQueue = true;
	private boolean processQueue = false;
	// Event queues for the synchronization of statecharts
	private Queue<Event> eventQueue1 = new LinkedList<Event>();
	private Queue<Event> eventQueue2 = new LinkedList<Event>();
	// Clocks
	private TimerInterface timer = new OneThreadedTimer();
	
	public Orion_Master_SM() {
		orion_Master_SM = new Orion_Master_SMStatemachine();
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
		orion_Master_SM.resetVariables();
	}

	public void resetStateConfigurations() {
		orion_Master_SM.resetStateConfigurations();
	}

	public void raiseEntryEvents() {
		orion_Master_SM.raiseEntryEvents();
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
	
	public class Send_StateMachine_Port implements StateMachine_Interface_For_OrionInterface.Provided {
		private List<StateMachine_Interface_For_OrionInterface.Listener.Provided> listeners = new LinkedList<StateMachine_Interface_For_OrionInterface.Listener.Provided>();
		@Override
		public boolean isRaisedOrionKeepAlive() {
			return orion_Master_SM.getSend_StateMachine_Port_OrionKeepAlive_Out();
		}
		@Override
		public boolean isRaisedOrionConnConf() {
			return orion_Master_SM.getSend_StateMachine_Port_OrionConnConf_Out();
		}
		@Override
		public boolean isRaisedOrionDisconnCause() {
			return orion_Master_SM.getSend_StateMachine_Port_OrionDisconnCause_Out();
		}
		@Override
		public boolean isRaisedOrionAppData() {
			return orion_Master_SM.getSend_StateMachine_Port_OrionAppData_Out();
		}
		@Override
		public boolean isRaisedOrionConnResp() {
			return orion_Master_SM.getSend_StateMachine_Port_OrionConnResp_Out();
		}
		@Override
		public boolean isRaisedOrionDisconn() {
			return orion_Master_SM.getSend_StateMachine_Port_OrionDisconn_Out();
		}
		@Override
		public boolean isRaisedOrionConnReq() {
			return orion_Master_SM.getSend_StateMachine_Port_OrionConnReq_Out();
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
	
	public Send_StateMachine_Port getSend_StateMachine_Port() {
		return send_StateMachine_Port;
	}
	
	public class StateMachine_Port implements StateMachine_Interface_For_OrionInterface.Required {
		private List<StateMachine_Interface_For_OrionInterface.Listener.Required> listeners = new LinkedList<StateMachine_Interface_For_OrionInterface.Listener.Required>();
		@Override
		public void raiseOrionKeepAlive() {
		getInsertQueue().add(new Event("StateMachine_Port.OrionKeepAlive"));
		}
		@Override
		public void raiseOrionConnConf() {
		getInsertQueue().add(new Event("StateMachine_Port.OrionConnConf"));
		}
		@Override
		public void raiseOrionDisconnCause() {
		getInsertQueue().add(new Event("StateMachine_Port.OrionDisconnCause"));
		}
		@Override
		public void raiseOrionAppData() {
		getInsertQueue().add(new Event("StateMachine_Port.OrionAppData"));
		}
		@Override
		public void raiseOrionConnResp() {
		getInsertQueue().add(new Event("StateMachine_Port.OrionConnResp"));
		}
		@Override
		public void raiseOrionDisconn() {
		getInsertQueue().add(new Event("StateMachine_Port.OrionDisconn"));
		}
		@Override
		public void raiseOrionConnReq() {
		getInsertQueue().add(new Event("StateMachine_Port.OrionConnReq"));
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
	
	public StateMachine_Port getStateMachine_Port() {
		return stateMachine_Port;
	}
	
	public class Status implements ConnectionStateInterface.Provided {
		private List<ConnectionStateInterface.Listener.Provided> listeners = new LinkedList<ConnectionStateInterface.Listener.Provided>();
		@Override
		public boolean isRaisedDisconn() {
			return orion_Master_SM.getStatus_disconn_Out();
		}
		@Override
		public boolean isRaisedConn() {
			return orion_Master_SM.getStatus_conn_Out();
		}
		@Override
		public void registerListener(ConnectionStateInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		@Override
		public List<ConnectionStateInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public Status getStatus() {
		return status;
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
public Orion_Master_SMStatemachine getOrion_Master_SM(){
	return orion_Master_SM;
}
	
	public void runComponent() {
		Queue<Event> eventQueue = getProcessQueue();
		while (!eventQueue.isEmpty()) {
			Event event = eventQueue.remove();
			switch (event.getEvent()) {
				case "StateMachine_Port.OrionKeepAlive": 
					orion_Master_SM.setStateMachine_Port_OrionKeepAlive_In(true);
				break;
				case "StateMachine_Port.OrionConnConf": 
					orion_Master_SM.setStateMachine_Port_OrionConnConf_In(true);
				break;
				case "StateMachine_Port.OrionDisconnCause": 
					orion_Master_SM.setStateMachine_Port_OrionDisconnCause_In(true);
				break;
				case "StateMachine_Port.OrionAppData": 
					orion_Master_SM.setStateMachine_Port_OrionAppData_In(true);
				break;
				case "StateMachine_Port.OrionConnResp": 
					orion_Master_SM.setStateMachine_Port_OrionConnResp_In(true);
				break;
				case "StateMachine_Port.OrionDisconn": 
					orion_Master_SM.setStateMachine_Port_OrionDisconn_In(true);
				break;
				case "StateMachine_Port.OrionConnReq": 
					orion_Master_SM.setStateMachine_Port_OrionConnReq_In(true);
				break;
				default:
					throw new IllegalArgumentException("No such event: " + event);
			}
		}
		executeStep();
	}
	
	private void executeStep() {
		int elapsedTime = (int) timer.getElapsedTime(this, TimeUnit.MILLISECOND);
		orion_Master_SM.setClosedTimeout(orion_Master_SM.getClosedTimeout() + elapsedTime);
		orion_Master_SM.runCycle();
		timer.saveTime(this);
		notifyListeners();
	}
	
	/** Interface method, needed for composite component initialization chain. */
	public void notifyAllListeners() {
		notifyListeners();
	}
	
	public void notifyListeners() {
		if (send_StateMachine_Port.isRaisedOrionKeepAlive()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : send_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionKeepAlive();
			}
		}
		if (send_StateMachine_Port.isRaisedOrionConnConf()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : send_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionConnConf();
			}
		}
		if (send_StateMachine_Port.isRaisedOrionDisconnCause()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : send_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionDisconnCause();
			}
		}
		if (send_StateMachine_Port.isRaisedOrionAppData()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : send_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionAppData();
			}
		}
		if (send_StateMachine_Port.isRaisedOrionConnResp()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : send_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionConnResp();
			}
		}
		if (send_StateMachine_Port.isRaisedOrionDisconn()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : send_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionDisconn();
			}
		}
		if (send_StateMachine_Port.isRaisedOrionConnReq()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : send_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionConnReq();
			}
		}
		if (status.isRaisedDisconn()) {
			for (ConnectionStateInterface.Listener.Provided listener : status.getRegisteredListeners()) {
				listener.raiseDisconn();
			}
		}
		if (status.isRaisedConn()) {
			for (ConnectionStateInterface.Listener.Provided listener : status.getRegisteredListeners()) {
				listener.raiseConn();
			}
		}
	}
	
	public void setTimer(TimerInterface timer) {
		this.timer = timer;
	}
	
	@Override
	public long getEarliestTime(){
		int elapsedTime = (int) timer.getElapsedTime(this, TimeUnit.MILLISECOND);
		long earliestTime=Long.MAX_VALUE;
		long ClosedTimeoutLimit=5000;
		long ClosedTimeoutTime= orion_Master_SM.getClosedTimeout()+elapsedTime;
		long ClosedTimeoutRemainingTime=ClosedTimeoutLimit-ClosedTimeoutTime;
		if ( (ClosedTimeoutTime>=0) 
			&& (ClosedTimeoutRemainingTime > 0)
			&& (ClosedTimeoutRemainingTime<earliestTime) ) {
			earliestTime=ClosedTimeoutRemainingTime;
		}
		return earliestTime;
	}
	
	public boolean isStateActive(String region, String state) {
		switch (region) {
			case "main_region_1":
				return orion_Master_SM.getMain_region_1() == Main_region_1.valueOf(state);
			case "SendRegion":
				return orion_Master_SM.getSendRegion() == SendRegion.valueOf(state);
			case "RecieveRegion":
				return orion_Master_SM.getRecieveRegion() == RecieveRegion.valueOf(state);
		}
		return false;
	}
	
	
	
	
	
	@Override
	public String toString() {
		String str=orion_Master_SM.toString();
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
