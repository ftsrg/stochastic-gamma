package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.TimerInterface.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.Orion_Master_SM_extStatemachine.*;

public class Orion_Master_SM_ext implements Orion_Master_SM_extInterface, TimedObject{
	// Port instances
	private Block_Port block_Port = new Block_Port();
	private Connection_Port connection_Port = new Connection_Port();
	private StateMachine_Port stateMachine_Port = new StateMachine_Port();
	private Send_StateMachine_Port send_StateMachine_Port = new Send_StateMachine_Port();
	private Process_StateMachine_Port process_StateMachine_Port = new Process_StateMachine_Port();
	private TimoeutKeepAliveReceiveTimeout_3 timoeutKeepAliveReceiveTimeout_3 = new TimoeutKeepAliveReceiveTimeout_3();
	private TimeoutKapcsolodik_2 timeoutKapcsolodik_2 = new TimeoutKapcsolodik_2();
	private TimeoutZarva_0 timeoutZarva_0 = new TimeoutZarva_0();
	private TimeoutKeepAliveSendTimeout_1 timeoutKeepAliveSendTimeout_1 = new TimeoutKeepAliveSendTimeout_1();
	private TimoeutKeepAliveReceiveTimeout_3_req timoeutKeepAliveReceiveTimeout_3_req = new TimoeutKeepAliveReceiveTimeout_3_req();
	private TimeoutKapcsolodik_2_req timeoutKapcsolodik_2_req = new TimeoutKapcsolodik_2_req();
	private TimeoutZarva_0_req timeoutZarva_0_req = new TimeoutZarva_0_req();
	private TimeoutKeepAliveSendTimeout_1_req timeoutKeepAliveSendTimeout_1_req = new TimeoutKeepAliveSendTimeout_1_req();
	private Status status = new Status();
	// Wrapped statemachine
	private Orion_Master_SM_extStatemachine orion_Master_SM_ext;
	// Indicates which queue is active in a cycle
	private boolean insertQueue = true;
	private boolean processQueue = false;
	// Event queues for the synchronization of statecharts
	private Queue<Event> eventQueue1 = new LinkedList<Event>();
	private Queue<Event> eventQueue2 = new LinkedList<Event>();
	// Clocks
	private TimerInterface timer = new OneThreadedTimer();
	
	public Orion_Master_SM_ext() {
		orion_Master_SM_ext = new Orion_Master_SM_extStatemachine();
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
		orion_Master_SM_ext.resetVariables();
	}

	public void resetStateConfigurations() {
		orion_Master_SM_ext.resetStateConfigurations();
	}

	public void raiseEntryEvents() {
		orion_Master_SM_ext.raiseEntryEvents();
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
	
	public class Block_Port implements Block_Interface_ForOrionInterface.Required {
		private List<Block_Interface_ForOrionInterface.Listener.Required> listeners = new LinkedList<Block_Interface_ForOrionInterface.Listener.Required>();
		@Override
		public void raiseOperation_Call_Invalid() {
		getInsertQueue().add(new Event("Block_Port.Operation_Call_Invalid"));
		}
		@Override
		public void raiseOperation_Call_SendData() {
		getInsertQueue().add(new Event("Block_Port.Operation_Call_SendData"));
		}
		@Override
		public void registerListener(Block_Interface_ForOrionInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		@Override
		public List<Block_Interface_ForOrionInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public Block_Port getBlock_Port() {
		return block_Port;
	}
	
	public class Connection_Port implements Connection_Interface_For_OrionInterface.Required {
		private List<Connection_Interface_For_OrionInterface.Listener.Required> listeners = new LinkedList<Connection_Interface_For_OrionInterface.Listener.Required>();
		@Override
		public void raiseOperation_Call_Disconn() {
		getInsertQueue().add(new Event("Connection_Port.Operation_Call_Disconn"));
		}
		@Override
		public void raiseOperation_Call_Connect() {
		getInsertQueue().add(new Event("Connection_Port.Operation_Call_Connect"));
		}
		@Override
		public void registerListener(Connection_Interface_For_OrionInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		@Override
		public List<Connection_Interface_For_OrionInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public Connection_Port getConnection_Port() {
		return connection_Port;
	}
	
	public class StateMachine_Port implements StateMachine_Interface_For_OrionInterface.Required {
		private List<StateMachine_Interface_For_OrionInterface.Listener.Required> listeners = new LinkedList<StateMachine_Interface_For_OrionInterface.Listener.Required>();
		@Override
		public void raiseOrionKeepAlive() {
		getInsertQueue().add(new Event("StateMachine_Port.OrionKeepAlive"));
		}
		@Override
		public void raiseOrionDisconn() {
		getInsertQueue().add(new Event("StateMachine_Port.OrionDisconn"));
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
		public void raiseOrionConnConf() {
		getInsertQueue().add(new Event("StateMachine_Port.OrionConnConf"));
		}
		@Override
		public void raiseOrionConnResp() {
		getInsertQueue().add(new Event("StateMachine_Port.OrionConnResp"));
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
	
	public class Send_StateMachine_Port implements StateMachine_Interface_For_OrionInterface.Provided {
		private List<StateMachine_Interface_For_OrionInterface.Listener.Provided> listeners = new LinkedList<StateMachine_Interface_For_OrionInterface.Listener.Provided>();
		@Override
		public boolean isRaisedOrionKeepAlive() {
			return orion_Master_SM_ext.getSend_StateMachine_Port_OrionKeepAlive_Out();
		}
		@Override
		public boolean isRaisedOrionDisconn() {
			return orion_Master_SM_ext.getSend_StateMachine_Port_OrionDisconn_Out();
		}
		@Override
		public boolean isRaisedOrionDisconnCause() {
			return orion_Master_SM_ext.getSend_StateMachine_Port_OrionDisconnCause_Out();
		}
		@Override
		public boolean isRaisedOrionAppData() {
			return orion_Master_SM_ext.getSend_StateMachine_Port_OrionAppData_Out();
		}
		@Override
		public boolean isRaisedOrionConnConf() {
			return orion_Master_SM_ext.getSend_StateMachine_Port_OrionConnConf_Out();
		}
		@Override
		public boolean isRaisedOrionConnResp() {
			return orion_Master_SM_ext.getSend_StateMachine_Port_OrionConnResp_Out();
		}
		@Override
		public boolean isRaisedOrionConnReq() {
			return orion_Master_SM_ext.getSend_StateMachine_Port_OrionConnReq_Out();
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
	
	public class Process_StateMachine_Port implements StateMachine_Interface_For_OrionInterface.Provided {
		private List<StateMachine_Interface_For_OrionInterface.Listener.Provided> listeners = new LinkedList<StateMachine_Interface_For_OrionInterface.Listener.Provided>();
		@Override
		public boolean isRaisedOrionKeepAlive() {
			return orion_Master_SM_ext.getProcess_StateMachine_Port_OrionKeepAlive_Out();
		}
		@Override
		public boolean isRaisedOrionDisconn() {
			return orion_Master_SM_ext.getProcess_StateMachine_Port_OrionDisconn_Out();
		}
		@Override
		public boolean isRaisedOrionDisconnCause() {
			return orion_Master_SM_ext.getProcess_StateMachine_Port_OrionDisconnCause_Out();
		}
		@Override
		public boolean isRaisedOrionAppData() {
			return orion_Master_SM_ext.getProcess_StateMachine_Port_OrionAppData_Out();
		}
		@Override
		public boolean isRaisedOrionConnConf() {
			return orion_Master_SM_ext.getProcess_StateMachine_Port_OrionConnConf_Out();
		}
		@Override
		public boolean isRaisedOrionConnResp() {
			return orion_Master_SM_ext.getProcess_StateMachine_Port_OrionConnResp_Out();
		}
		@Override
		public boolean isRaisedOrionConnReq() {
			return orion_Master_SM_ext.getProcess_StateMachine_Port_OrionConnReq_Out();
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
	
	public Process_StateMachine_Port getProcess_StateMachine_Port() {
		return process_StateMachine_Port;
	}
	
	public class TimoeutKeepAliveReceiveTimeout_3 implements SoftwareTimerInterface.Required {
		private List<SoftwareTimerInterface.Listener.Required> listeners = new LinkedList<SoftwareTimerInterface.Listener.Required>();
		@Override
		public void raiseNewEvent() {
		getInsertQueue().add(new Event("TimoeutKeepAliveReceiveTimeout_3.newEvent"));
		}
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public TimoeutKeepAliveReceiveTimeout_3 getTimoeutKeepAliveReceiveTimeout_3() {
		return timoeutKeepAliveReceiveTimeout_3;
	}
	
	public class TimeoutKapcsolodik_2 implements SoftwareTimerInterface.Required {
		private List<SoftwareTimerInterface.Listener.Required> listeners = new LinkedList<SoftwareTimerInterface.Listener.Required>();
		@Override
		public void raiseNewEvent() {
		getInsertQueue().add(new Event("TimeoutKapcsolodik_2.newEvent"));
		}
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public TimeoutKapcsolodik_2 getTimeoutKapcsolodik_2() {
		return timeoutKapcsolodik_2;
	}
	
	public class TimeoutZarva_0 implements SoftwareTimerInterface.Required {
		private List<SoftwareTimerInterface.Listener.Required> listeners = new LinkedList<SoftwareTimerInterface.Listener.Required>();
		@Override
		public void raiseNewEvent() {
		getInsertQueue().add(new Event("TimeoutZarva_0.newEvent"));
		}
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public TimeoutZarva_0 getTimeoutZarva_0() {
		return timeoutZarva_0;
	}
	
	public class TimeoutKeepAliveSendTimeout_1 implements SoftwareTimerInterface.Required {
		private List<SoftwareTimerInterface.Listener.Required> listeners = new LinkedList<SoftwareTimerInterface.Listener.Required>();
		@Override
		public void raiseNewEvent() {
		getInsertQueue().add(new Event("TimeoutKeepAliveSendTimeout_1.newEvent"));
		}
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public TimeoutKeepAliveSendTimeout_1 getTimeoutKeepAliveSendTimeout_1() {
		return timeoutKeepAliveSendTimeout_1;
	}
	
	public class TimoeutKeepAliveReceiveTimeout_3_req implements SoftwareTimerInterface.Provided {
		private List<SoftwareTimerInterface.Listener.Provided> listeners = new LinkedList<SoftwareTimerInterface.Listener.Provided>();
		@Override
		public boolean isRaisedNewEvent() {
			return orion_Master_SM_ext.getTimoeutKeepAliveReceiveTimeout_3_req_newEvent_Out();
		}
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public TimoeutKeepAliveReceiveTimeout_3_req getTimoeutKeepAliveReceiveTimeout_3_req() {
		return timoeutKeepAliveReceiveTimeout_3_req;
	}
	
	public class TimeoutKapcsolodik_2_req implements SoftwareTimerInterface.Provided {
		private List<SoftwareTimerInterface.Listener.Provided> listeners = new LinkedList<SoftwareTimerInterface.Listener.Provided>();
		@Override
		public boolean isRaisedNewEvent() {
			return orion_Master_SM_ext.getTimeoutKapcsolodik_2_req_newEvent_Out();
		}
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public TimeoutKapcsolodik_2_req getTimeoutKapcsolodik_2_req() {
		return timeoutKapcsolodik_2_req;
	}
	
	public class TimeoutZarva_0_req implements SoftwareTimerInterface.Provided {
		private List<SoftwareTimerInterface.Listener.Provided> listeners = new LinkedList<SoftwareTimerInterface.Listener.Provided>();
		@Override
		public boolean isRaisedNewEvent() {
			return orion_Master_SM_ext.getTimeoutZarva_0_req_newEvent_Out();
		}
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public TimeoutZarva_0_req getTimeoutZarva_0_req() {
		return timeoutZarva_0_req;
	}
	
	public class TimeoutKeepAliveSendTimeout_1_req implements SoftwareTimerInterface.Provided {
		private List<SoftwareTimerInterface.Listener.Provided> listeners = new LinkedList<SoftwareTimerInterface.Listener.Provided>();
		@Override
		public boolean isRaisedNewEvent() {
			return orion_Master_SM_ext.getTimeoutKeepAliveSendTimeout_1_req_newEvent_Out();
		}
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
	}
	
	public TimeoutKeepAliveSendTimeout_1_req getTimeoutKeepAliveSendTimeout_1_req() {
		return timeoutKeepAliveSendTimeout_1_req;
	}
	
	public class Status implements ConnectionStateInterface.Provided {
		private List<ConnectionStateInterface.Listener.Provided> listeners = new LinkedList<ConnectionStateInterface.Listener.Provided>();
		@Override
		public boolean isRaisedConn() {
			return orion_Master_SM_ext.getStatus_conn_Out();
		}
		@Override
		public boolean isRaisedDisconn() {
			return orion_Master_SM_ext.getStatus_disconn_Out();
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
public Orion_Master_SM_extStatemachine getOrion_Master_SM_ext(){
	return orion_Master_SM_ext;
}
	
	public void runComponent() {
		Queue<Event> eventQueue = getProcessQueue();
		while (!eventQueue.isEmpty()) {
			Event event = eventQueue.remove();
			switch (event.getEvent()) {
				case "Block_Port.Operation_Call_Invalid": 
					orion_Master_SM_ext.setBlock_Port_Operation_Call_Invalid_In(true);
				break;
				case "Block_Port.Operation_Call_SendData": 
					orion_Master_SM_ext.setBlock_Port_Operation_Call_SendData_In(true);
				break;
				case "Connection_Port.Operation_Call_Disconn": 
					orion_Master_SM_ext.setConnection_Port_Operation_Call_Disconn_In(true);
				break;
				case "Connection_Port.Operation_Call_Connect": 
					orion_Master_SM_ext.setConnection_Port_Operation_Call_Connect_In(true);
				break;
				case "StateMachine_Port.OrionKeepAlive": 
					orion_Master_SM_ext.setStateMachine_Port_OrionKeepAlive_In(true);
				break;
				case "StateMachine_Port.OrionDisconn": 
					orion_Master_SM_ext.setStateMachine_Port_OrionDisconn_In(true);
				break;
				case "StateMachine_Port.OrionDisconnCause": 
					orion_Master_SM_ext.setStateMachine_Port_OrionDisconnCause_In(true);
				break;
				case "StateMachine_Port.OrionAppData": 
					orion_Master_SM_ext.setStateMachine_Port_OrionAppData_In(true);
				break;
				case "StateMachine_Port.OrionConnConf": 
					orion_Master_SM_ext.setStateMachine_Port_OrionConnConf_In(true);
				break;
				case "StateMachine_Port.OrionConnResp": 
					orion_Master_SM_ext.setStateMachine_Port_OrionConnResp_In(true);
				break;
				case "StateMachine_Port.OrionConnReq": 
					orion_Master_SM_ext.setStateMachine_Port_OrionConnReq_In(true);
				break;
				case "TimoeutKeepAliveReceiveTimeout_3.newEvent": 
					orion_Master_SM_ext.setTimoeutKeepAliveReceiveTimeout_3_newEvent_In(true);
				break;
				case "TimeoutKapcsolodik_2.newEvent": 
					orion_Master_SM_ext.setTimeoutKapcsolodik_2_newEvent_In(true);
				break;
				case "TimeoutZarva_0.newEvent": 
					orion_Master_SM_ext.setTimeoutZarva_0_newEvent_In(true);
				break;
				case "TimeoutKeepAliveSendTimeout_1.newEvent": 
					orion_Master_SM_ext.setTimeoutKeepAliveSendTimeout_1_newEvent_In(true);
				break;
				default:
					throw new IllegalArgumentException("No such event: " + event);
			}
		}
		executeStep();
	}
	
	private void executeStep() {
		orion_Master_SM_ext.runCycle();
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
		if (send_StateMachine_Port.isRaisedOrionDisconn()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : send_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionDisconn();
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
		if (send_StateMachine_Port.isRaisedOrionConnConf()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : send_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionConnConf();
			}
		}
		if (send_StateMachine_Port.isRaisedOrionConnResp()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : send_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionConnResp();
			}
		}
		if (send_StateMachine_Port.isRaisedOrionConnReq()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : send_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionConnReq();
			}
		}
		if (process_StateMachine_Port.isRaisedOrionKeepAlive()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : process_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionKeepAlive();
			}
		}
		if (process_StateMachine_Port.isRaisedOrionDisconn()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : process_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionDisconn();
			}
		}
		if (process_StateMachine_Port.isRaisedOrionDisconnCause()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : process_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionDisconnCause();
			}
		}
		if (process_StateMachine_Port.isRaisedOrionAppData()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : process_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionAppData();
			}
		}
		if (process_StateMachine_Port.isRaisedOrionConnConf()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : process_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionConnConf();
			}
		}
		if (process_StateMachine_Port.isRaisedOrionConnResp()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : process_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionConnResp();
			}
		}
		if (process_StateMachine_Port.isRaisedOrionConnReq()) {
			for (StateMachine_Interface_For_OrionInterface.Listener.Provided listener : process_StateMachine_Port.getRegisteredListeners()) {
				listener.raiseOrionConnReq();
			}
		}
		if (timoeutKeepAliveReceiveTimeout_3_req.isRaisedNewEvent()) {
			for (SoftwareTimerInterface.Listener.Provided listener : timoeutKeepAliveReceiveTimeout_3_req.getRegisteredListeners()) {
				listener.raiseNewEvent();
			}
		}
		if (timeoutKapcsolodik_2_req.isRaisedNewEvent()) {
			for (SoftwareTimerInterface.Listener.Provided listener : timeoutKapcsolodik_2_req.getRegisteredListeners()) {
				listener.raiseNewEvent();
			}
		}
		if (timeoutZarva_0_req.isRaisedNewEvent()) {
			for (SoftwareTimerInterface.Listener.Provided listener : timeoutZarva_0_req.getRegisteredListeners()) {
				listener.raiseNewEvent();
			}
		}
		if (timeoutKeepAliveSendTimeout_1_req.isRaisedNewEvent()) {
			for (SoftwareTimerInterface.Listener.Provided listener : timeoutKeepAliveSendTimeout_1_req.getRegisteredListeners()) {
				listener.raiseNewEvent();
			}
		}
		if (status.isRaisedConn()) {
			for (ConnectionStateInterface.Listener.Provided listener : status.getRegisteredListeners()) {
				listener.raiseConn();
			}
		}
		if (status.isRaisedDisconn()) {
			for (ConnectionStateInterface.Listener.Provided listener : status.getRegisteredListeners()) {
				listener.raiseDisconn();
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
			case "main_region_of_Orion_Master_SM":
				return orion_Master_SM_ext.getMain_region_of_Orion_Master_SM() == Main_region_of_Orion_Master_SM.valueOf(state);
			case "region_1_in_Kapcsolodva_4":
				return orion_Master_SM_ext.getRegion_1_in_Kapcsolodva_4() == Region_1_in_Kapcsolodva_4.valueOf(state);
			case "region_2_in_Kapcsolodva_4":
				return orion_Master_SM_ext.getRegion_2_in_Kapcsolodva_4() == Region_2_in_Kapcsolodva_4.valueOf(state);
		}
		return false;
	}
	
	
	
	
	
	@Override
	public String toString() {
		String str=orion_Master_SM_ext.toString();
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
