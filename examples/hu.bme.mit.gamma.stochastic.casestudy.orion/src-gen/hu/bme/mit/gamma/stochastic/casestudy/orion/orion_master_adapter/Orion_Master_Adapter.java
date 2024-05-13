package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_adapter;

import java.util.Collections;
import java.util.List;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;

import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.*;

public class Orion_Master_Adapter implements Runnable, Orion_Master_AdapterInterface {			
	// Thread running this wrapper instance
	private Thread thread;
	// Wrapped synchronous instance
	private Orion_Master_SM_ext master;
	// Control port instances
	// Wrapped port instances
	private Block_Port block_Port;
	private Connection_Port connection_Port;
	private StateMachine_Port stateMachine_Port;
	private Send_StateMachine_Port send_StateMachine_Port;
	private Process_StateMachine_Port process_StateMachine_Port;
	private TimoeutKeepAliveReceiveTimeout_3 timoeutKeepAliveReceiveTimeout_3;
	private TimeoutKapcsolodik_2 timeoutKapcsolodik_2;
	private TimeoutZarva_0 timeoutZarva_0;
	private TimeoutKeepAliveSendTimeout_1 timeoutKeepAliveSendTimeout_1;
	private TimoeutKeepAliveReceiveTimeout_3_req timoeutKeepAliveReceiveTimeout_3_req;
	private TimeoutKapcsolodik_2_req timeoutKapcsolodik_2_req;
	private TimeoutZarva_0_req timeoutZarva_0_req;
	private TimeoutKeepAliveSendTimeout_1_req timeoutKeepAliveSendTimeout_1_req;
	private Status status;
	// Main queue
	private LinkedBlockingMultiQueue<String, Event> __asyncQueue = new LinkedBlockingMultiQueue<String, Event>();
	// Subqueues
	private LinkedBlockingMultiQueue<String, Event>.SubQueue q1;
	
	
	public boolean isEventQueueEmpty(){
		return __asyncQueue.isEmpty();
	}
	
	public Orion_Master_Adapter() {
		master = new Orion_Master_SM_ext();
		// Wrapped port instances
		block_Port = new Block_Port();
		connection_Port = new Connection_Port();
		stateMachine_Port = new StateMachine_Port();
		send_StateMachine_Port = new Send_StateMachine_Port();
		process_StateMachine_Port = new Process_StateMachine_Port();
		timoeutKeepAliveReceiveTimeout_3 = new TimoeutKeepAliveReceiveTimeout_3();
		timeoutKapcsolodik_2 = new TimeoutKapcsolodik_2();
		timeoutZarva_0 = new TimeoutZarva_0();
		timeoutKeepAliveSendTimeout_1 = new TimeoutKeepAliveSendTimeout_1();
		timoeutKeepAliveReceiveTimeout_3_req = new TimoeutKeepAliveReceiveTimeout_3_req();
		timeoutKapcsolodik_2_req = new TimeoutKapcsolodik_2_req();
		timeoutZarva_0_req = new TimeoutZarva_0_req();
		timeoutKeepAliveSendTimeout_1_req = new TimeoutKeepAliveSendTimeout_1_req();
		status = new Status();
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
		master.handleBeforeReset();
	}
	
	public void resetVariables() {
		master.resetVariables();
	}
	
	public void resetStateConfigurations() {
		master.resetStateConfigurations();
	}
	
	public void raiseEntryEvents() {
		master.raiseEntryEvents();
	}
	
	public void handleAfterReset() {
		master.handleAfterReset();
		//
	}
	
	/** Creates the subqueues, clocks and enters the wrapped synchronous component. */
	private void init() {
		master = new Orion_Master_SM_ext();
		// Creating subqueues: the negative conversion regarding priorities is needed,
		// because the lbmq marks higher priority with lower integer values
		__asyncQueue.addSubQueue("q1", -(1), (int) 1);
		q1 = __asyncQueue.getSubQueue("q1");
		// The thread has to be started manually
	}
	
	
	// Inner classes representing control ports
	
	// Inner classes representing wrapped ports
	public class Block_Port implements Block_Interface_ForOrionInterface.Required {
		
		@Override
		public void raiseOperation_Call_SendData() {
			q1.offer(new Event("Block_Port.Operation_Call_SendData"));
		}
		@Override
		public void raiseOperation_Call_Invalid() {
			q1.offer(new Event("Block_Port.Operation_Call_Invalid"));
		}
		
		
		@Override
		public void registerListener(Block_Interface_ForOrionInterface.Listener.Required listener) {
			master.getBlock_Port().registerListener(listener);
		}
		
		@Override
		public List<Block_Interface_ForOrionInterface.Listener.Required> getRegisteredListeners() {
			return master.getBlock_Port().getRegisteredListeners();
		}
		
	}
	
	@Override
	public Block_Port getBlock_Port() {
		return block_Port;
	}
	
	public class Connection_Port implements Connection_Interface_For_OrionInterface.Required {
		
		@Override
		public void raiseOperation_Call_Connect() {
			q1.offer(new Event("Connection_Port.Operation_Call_Connect"));
		}
		@Override
		public void raiseOperation_Call_Disconn() {
			q1.offer(new Event("Connection_Port.Operation_Call_Disconn"));
		}
		
		
		@Override
		public void registerListener(Connection_Interface_For_OrionInterface.Listener.Required listener) {
			master.getConnection_Port().registerListener(listener);
		}
		
		@Override
		public List<Connection_Interface_For_OrionInterface.Listener.Required> getRegisteredListeners() {
			return master.getConnection_Port().getRegisteredListeners();
		}
		
	}
	
	@Override
	public Connection_Port getConnection_Port() {
		return connection_Port;
	}
	
	public class StateMachine_Port implements StateMachine_Interface_For_OrionInterface.Required {
		
		@Override
		public void raiseOrionDisconn() {
			q1.offer(new Event("StateMachine_Port.OrionDisconn"));
		}
		@Override
		public void raiseOrionDisconnCause() {
			q1.offer(new Event("StateMachine_Port.OrionDisconnCause"));
		}
		@Override
		public void raiseOrionConnReq() {
			q1.offer(new Event("StateMachine_Port.OrionConnReq"));
		}
		@Override
		public void raiseOrionAppData() {
			q1.offer(new Event("StateMachine_Port.OrionAppData"));
		}
		@Override
		public void raiseOrionKeepAlive() {
			q1.offer(new Event("StateMachine_Port.OrionKeepAlive"));
		}
		@Override
		public void raiseOrionConnConf() {
			q1.offer(new Event("StateMachine_Port.OrionConnConf"));
		}
		@Override
		public void raiseOrionConnResp() {
			q1.offer(new Event("StateMachine_Port.OrionConnResp"));
		}
		
		
		@Override
		public void registerListener(StateMachine_Interface_For_OrionInterface.Listener.Required listener) {
			master.getStateMachine_Port().registerListener(listener);
		}
		
		@Override
		public List<StateMachine_Interface_For_OrionInterface.Listener.Required> getRegisteredListeners() {
			return master.getStateMachine_Port().getRegisteredListeners();
		}
		
	}
	
	@Override
	public StateMachine_Port getStateMachine_Port() {
		return stateMachine_Port;
	}
	
	public class Send_StateMachine_Port implements StateMachine_Interface_For_OrionInterface.Provided {
		
		
		@Override
		public boolean isRaisedOrionDisconn() {
			return master.getSend_StateMachine_Port().isRaisedOrionDisconn();
		}
		
		@Override
		public boolean isRaisedOrionDisconnCause() {
			return master.getSend_StateMachine_Port().isRaisedOrionDisconnCause();
		}
		
		@Override
		public boolean isRaisedOrionConnReq() {
			return master.getSend_StateMachine_Port().isRaisedOrionConnReq();
		}
		
		@Override
		public boolean isRaisedOrionAppData() {
			return master.getSend_StateMachine_Port().isRaisedOrionAppData();
		}
		
		@Override
		public boolean isRaisedOrionKeepAlive() {
			return master.getSend_StateMachine_Port().isRaisedOrionKeepAlive();
		}
		
		@Override
		public boolean isRaisedOrionConnConf() {
			return master.getSend_StateMachine_Port().isRaisedOrionConnConf();
		}
		
		@Override
		public boolean isRaisedOrionConnResp() {
			return master.getSend_StateMachine_Port().isRaisedOrionConnResp();
		}
		
		@Override
		public void registerListener(StateMachine_Interface_For_OrionInterface.Listener.Provided listener) {
			master.getSend_StateMachine_Port().registerListener(listener);
		}
		
		@Override
		public List<StateMachine_Interface_For_OrionInterface.Listener.Provided> getRegisteredListeners() {
			return master.getSend_StateMachine_Port().getRegisteredListeners();
		}
		
	}
	
	@Override
	public Send_StateMachine_Port getSend_StateMachine_Port() {
		return send_StateMachine_Port;
	}
	
	public class Process_StateMachine_Port implements StateMachine_Interface_For_OrionInterface.Provided {
		
		
		@Override
		public boolean isRaisedOrionDisconn() {
			return master.getProcess_StateMachine_Port().isRaisedOrionDisconn();
		}
		
		@Override
		public boolean isRaisedOrionDisconnCause() {
			return master.getProcess_StateMachine_Port().isRaisedOrionDisconnCause();
		}
		
		@Override
		public boolean isRaisedOrionConnReq() {
			return master.getProcess_StateMachine_Port().isRaisedOrionConnReq();
		}
		
		@Override
		public boolean isRaisedOrionAppData() {
			return master.getProcess_StateMachine_Port().isRaisedOrionAppData();
		}
		
		@Override
		public boolean isRaisedOrionKeepAlive() {
			return master.getProcess_StateMachine_Port().isRaisedOrionKeepAlive();
		}
		
		@Override
		public boolean isRaisedOrionConnConf() {
			return master.getProcess_StateMachine_Port().isRaisedOrionConnConf();
		}
		
		@Override
		public boolean isRaisedOrionConnResp() {
			return master.getProcess_StateMachine_Port().isRaisedOrionConnResp();
		}
		
		@Override
		public void registerListener(StateMachine_Interface_For_OrionInterface.Listener.Provided listener) {
			master.getProcess_StateMachine_Port().registerListener(listener);
		}
		
		@Override
		public List<StateMachine_Interface_For_OrionInterface.Listener.Provided> getRegisteredListeners() {
			return master.getProcess_StateMachine_Port().getRegisteredListeners();
		}
		
	}
	
	@Override
	public Process_StateMachine_Port getProcess_StateMachine_Port() {
		return process_StateMachine_Port;
	}
	
	public class TimoeutKeepAliveReceiveTimeout_3 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			q1.offer(new Event("TimoeutKeepAliveReceiveTimeout_3.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			master.getTimoeutKeepAliveReceiveTimeout_3().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return master.getTimoeutKeepAliveReceiveTimeout_3().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimoeutKeepAliveReceiveTimeout_3 getTimoeutKeepAliveReceiveTimeout_3() {
		return timoeutKeepAliveReceiveTimeout_3;
	}
	
	public class TimeoutKapcsolodik_2 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			q1.offer(new Event("TimeoutKapcsolodik_2.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			master.getTimeoutKapcsolodik_2().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return master.getTimeoutKapcsolodik_2().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKapcsolodik_2 getTimeoutKapcsolodik_2() {
		return timeoutKapcsolodik_2;
	}
	
	public class TimeoutZarva_0 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			q1.offer(new Event("TimeoutZarva_0.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			master.getTimeoutZarva_0().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return master.getTimeoutZarva_0().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutZarva_0 getTimeoutZarva_0() {
		return timeoutZarva_0;
	}
	
	public class TimeoutKeepAliveSendTimeout_1 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			q1.offer(new Event("TimeoutKeepAliveSendTimeout_1.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			master.getTimeoutKeepAliveSendTimeout_1().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return master.getTimeoutKeepAliveSendTimeout_1().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKeepAliveSendTimeout_1 getTimeoutKeepAliveSendTimeout_1() {
		return timeoutKeepAliveSendTimeout_1;
	}
	
	public class TimoeutKeepAliveReceiveTimeout_3_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return master.getTimoeutKeepAliveReceiveTimeout_3_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			master.getTimoeutKeepAliveReceiveTimeout_3_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return master.getTimoeutKeepAliveReceiveTimeout_3_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimoeutKeepAliveReceiveTimeout_3_req getTimoeutKeepAliveReceiveTimeout_3_req() {
		return timoeutKeepAliveReceiveTimeout_3_req;
	}
	
	public class TimeoutKapcsolodik_2_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return master.getTimeoutKapcsolodik_2_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			master.getTimeoutKapcsolodik_2_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return master.getTimeoutKapcsolodik_2_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKapcsolodik_2_req getTimeoutKapcsolodik_2_req() {
		return timeoutKapcsolodik_2_req;
	}
	
	public class TimeoutZarva_0_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return master.getTimeoutZarva_0_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			master.getTimeoutZarva_0_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return master.getTimeoutZarva_0_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutZarva_0_req getTimeoutZarva_0_req() {
		return timeoutZarva_0_req;
	}
	
	public class TimeoutKeepAliveSendTimeout_1_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return master.getTimeoutKeepAliveSendTimeout_1_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			master.getTimeoutKeepAliveSendTimeout_1_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return master.getTimeoutKeepAliveSendTimeout_1_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKeepAliveSendTimeout_1_req getTimeoutKeepAliveSendTimeout_1_req() {
		return timeoutKeepAliveSendTimeout_1_req;
	}
	
	public class Status implements ConnectionStateInterface.Provided {
		
		
		@Override
		public boolean isRaisedConn() {
			return master.getStatus().isRaisedConn();
		}
		
		@Override
		public boolean isRaisedDisconn() {
			return master.getStatus().isRaisedDisconn();
		}
		
		@Override
		public void registerListener(ConnectionStateInterface.Listener.Provided listener) {
			master.getStatus().registerListener(listener);
		}
		
		@Override
		public List<ConnectionStateInterface.Listener.Provided> getRegisteredListeners() {
			return master.getStatus().getRegisteredListeners();
		}
		
	}
	
	@Override
	public Status getStatus() {
		return status;
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
		return false;
	}
	
	private void forwardEvent(Event event) {
		switch (event.getEvent()) {
			case "Block_Port.Operation_Call_SendData":
				master.getBlock_Port().raiseOperation_Call_SendData();
			break;
			case "Block_Port.Operation_Call_Invalid":
				master.getBlock_Port().raiseOperation_Call_Invalid();
			break;
			case "Connection_Port.Operation_Call_Connect":
				master.getConnection_Port().raiseOperation_Call_Connect();
			break;
			case "Connection_Port.Operation_Call_Disconn":
				master.getConnection_Port().raiseOperation_Call_Disconn();
			break;
			case "StateMachine_Port.OrionDisconn":
				master.getStateMachine_Port().raiseOrionDisconn();
			break;
			case "StateMachine_Port.OrionDisconnCause":
				master.getStateMachine_Port().raiseOrionDisconnCause();
			break;
			case "StateMachine_Port.OrionConnReq":
				master.getStateMachine_Port().raiseOrionConnReq();
			break;
			case "StateMachine_Port.OrionAppData":
				master.getStateMachine_Port().raiseOrionAppData();
			break;
			case "StateMachine_Port.OrionKeepAlive":
				master.getStateMachine_Port().raiseOrionKeepAlive();
			break;
			case "StateMachine_Port.OrionConnConf":
				master.getStateMachine_Port().raiseOrionConnConf();
			break;
			case "StateMachine_Port.OrionConnResp":
				master.getStateMachine_Port().raiseOrionConnResp();
			break;
			case "TimeoutKapcsolodik_2.newEvent":
				master.getTimeoutKapcsolodik_2().raiseNewEvent();
			break;
			case "TimeoutKeepAliveSendTimeout_1.newEvent":
				master.getTimeoutKeepAliveSendTimeout_1().raiseNewEvent();
			break;
			case "TimeoutZarva_0.newEvent":
				master.getTimeoutZarva_0().raiseNewEvent();
			break;
			case "TimoeutKeepAliveReceiveTimeout_3.newEvent":
				master.getTimoeutKeepAliveReceiveTimeout_3().raiseNewEvent();
			break;
			default:
				throw new IllegalArgumentException("No such event!");
		}
	}
	
	private void performControlActions(Event event) {
		String[] eventName = event.getEvent().split("\\.");
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("Block_Port")) {
			master.runCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("Connection_Port")) {
			master.runCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("Process_StateMachine_Port")) {
			master.runCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("StateMachine_Port")) {
			master.runCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimeoutKapcsolodik_2")) {
			master.runCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimeoutKeepAliveSendTimeout_1")) {
			master.runCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimeoutZarva_0")) {
			master.runCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimoeutKeepAliveReceiveTimeout_3")) {
			master.runCycle();
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
	
	public Orion_Master_SM_ext getMaster() {
		return master;
	}
	
	

	public String getInQueue(){
		String str="Input events (";
		str=str+"q1 [";
		for (Event event:q1){
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
