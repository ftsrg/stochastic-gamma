package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_adapter;

import java.util.Collections;
import java.util.List;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;

import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm_ext.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm_ext.*;

public class Orion_Slave_Adapter implements Runnable, Orion_Slave_AdapterInterface {			
	// Thread running this wrapper instance
	private Thread thread;
	// Wrapped synchronous instance
	private Orion_Slave_SM_ext slave;
	// Control port instances
	// Wrapped port instances
	private Block_Port block_Port;
	private Connection_Port connection_Port;
	private StateMachine_Port stateMachine_Port;
	private Send_StateMachine_Port send_StateMachine_Port;
	private Process_StateMachine_Port process_StateMachine_Port;
	private Handle_StateMachine_Port handle_StateMachine_Port;
	private TimeoutKeepAliveReceiveTimeout_4 timeoutKeepAliveReceiveTimeout_4;
	private TimeoutKapcsolodik_3 timeoutKapcsolodik_3;
	private TimeoutKeepAliveSendTimeout_0 timeoutKeepAliveSendTimeout_0;
	private TimeoutKeepAliveReceiveTimeout_4_req timeoutKeepAliveReceiveTimeout_4_req;
	private TimeoutKapcsolodik_3_req timeoutKapcsolodik_3_req;
	private TimeoutKeepAliveSendTimeout_0_req timeoutKeepAliveSendTimeout_0_req;
	private Status status;
	// Main queue
	private LinkedBlockingMultiQueue<String, Event> __asyncQueue = new LinkedBlockingMultiQueue<String, Event>();
	// Subqueues
	private LinkedBlockingMultiQueue<String, Event>.SubQueue q1;
	
	
	public boolean isEventQueueEmpty(){
		return __asyncQueue.isEmpty();
	}
	
	public Orion_Slave_Adapter() {
		slave = new Orion_Slave_SM_ext();
		// Wrapped port instances
		block_Port = new Block_Port();
		connection_Port = new Connection_Port();
		stateMachine_Port = new StateMachine_Port();
		send_StateMachine_Port = new Send_StateMachine_Port();
		process_StateMachine_Port = new Process_StateMachine_Port();
		handle_StateMachine_Port = new Handle_StateMachine_Port();
		timeoutKeepAliveReceiveTimeout_4 = new TimeoutKeepAliveReceiveTimeout_4();
		timeoutKapcsolodik_3 = new TimeoutKapcsolodik_3();
		timeoutKeepAliveSendTimeout_0 = new TimeoutKeepAliveSendTimeout_0();
		timeoutKeepAliveReceiveTimeout_4_req = new TimeoutKeepAliveReceiveTimeout_4_req();
		timeoutKapcsolodik_3_req = new TimeoutKapcsolodik_3_req();
		timeoutKeepAliveSendTimeout_0_req = new TimeoutKeepAliveSendTimeout_0_req();
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
		slave.handleBeforeReset();
	}
	
	public void resetVariables() {
		slave.resetVariables();
	}
	
	public void resetStateConfigurations() {
		slave.resetStateConfigurations();
	}
	
	public void raiseEntryEvents() {
		slave.raiseEntryEvents();
	}
	
	public void handleAfterReset() {
		slave.handleAfterReset();
		//
	}
	
	/** Creates the subqueues, clocks and enters the wrapped synchronous component. */
	private void init() {
		slave = new Orion_Slave_SM_ext();
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
			slave.getBlock_Port().registerListener(listener);
		}
		
		@Override
		public List<Block_Interface_ForOrionInterface.Listener.Required> getRegisteredListeners() {
			return slave.getBlock_Port().getRegisteredListeners();
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
			slave.getConnection_Port().registerListener(listener);
		}
		
		@Override
		public List<Connection_Interface_For_OrionInterface.Listener.Required> getRegisteredListeners() {
			return slave.getConnection_Port().getRegisteredListeners();
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
			slave.getStateMachine_Port().registerListener(listener);
		}
		
		@Override
		public List<StateMachine_Interface_For_OrionInterface.Listener.Required> getRegisteredListeners() {
			return slave.getStateMachine_Port().getRegisteredListeners();
		}
		
	}
	
	@Override
	public StateMachine_Port getStateMachine_Port() {
		return stateMachine_Port;
	}
	
	public class Send_StateMachine_Port implements StateMachine_Interface_For_OrionInterface.Provided {
		
		
		@Override
		public boolean isRaisedOrionDisconn() {
			return slave.getSend_StateMachine_Port().isRaisedOrionDisconn();
		}
		
		@Override
		public boolean isRaisedOrionDisconnCause() {
			return slave.getSend_StateMachine_Port().isRaisedOrionDisconnCause();
		}
		
		@Override
		public boolean isRaisedOrionConnReq() {
			return slave.getSend_StateMachine_Port().isRaisedOrionConnReq();
		}
		
		@Override
		public boolean isRaisedOrionAppData() {
			return slave.getSend_StateMachine_Port().isRaisedOrionAppData();
		}
		
		@Override
		public boolean isRaisedOrionKeepAlive() {
			return slave.getSend_StateMachine_Port().isRaisedOrionKeepAlive();
		}
		
		@Override
		public boolean isRaisedOrionConnConf() {
			return slave.getSend_StateMachine_Port().isRaisedOrionConnConf();
		}
		
		@Override
		public boolean isRaisedOrionConnResp() {
			return slave.getSend_StateMachine_Port().isRaisedOrionConnResp();
		}
		
		@Override
		public void registerListener(StateMachine_Interface_For_OrionInterface.Listener.Provided listener) {
			slave.getSend_StateMachine_Port().registerListener(listener);
		}
		
		@Override
		public List<StateMachine_Interface_For_OrionInterface.Listener.Provided> getRegisteredListeners() {
			return slave.getSend_StateMachine_Port().getRegisteredListeners();
		}
		
	}
	
	@Override
	public Send_StateMachine_Port getSend_StateMachine_Port() {
		return send_StateMachine_Port;
	}
	
	public class Process_StateMachine_Port implements StateMachine_Interface_For_OrionInterface.Provided {
		
		
		@Override
		public boolean isRaisedOrionDisconn() {
			return slave.getProcess_StateMachine_Port().isRaisedOrionDisconn();
		}
		
		@Override
		public boolean isRaisedOrionDisconnCause() {
			return slave.getProcess_StateMachine_Port().isRaisedOrionDisconnCause();
		}
		
		@Override
		public boolean isRaisedOrionConnReq() {
			return slave.getProcess_StateMachine_Port().isRaisedOrionConnReq();
		}
		
		@Override
		public boolean isRaisedOrionAppData() {
			return slave.getProcess_StateMachine_Port().isRaisedOrionAppData();
		}
		
		@Override
		public boolean isRaisedOrionKeepAlive() {
			return slave.getProcess_StateMachine_Port().isRaisedOrionKeepAlive();
		}
		
		@Override
		public boolean isRaisedOrionConnConf() {
			return slave.getProcess_StateMachine_Port().isRaisedOrionConnConf();
		}
		
		@Override
		public boolean isRaisedOrionConnResp() {
			return slave.getProcess_StateMachine_Port().isRaisedOrionConnResp();
		}
		
		@Override
		public void registerListener(StateMachine_Interface_For_OrionInterface.Listener.Provided listener) {
			slave.getProcess_StateMachine_Port().registerListener(listener);
		}
		
		@Override
		public List<StateMachine_Interface_For_OrionInterface.Listener.Provided> getRegisteredListeners() {
			return slave.getProcess_StateMachine_Port().getRegisteredListeners();
		}
		
	}
	
	@Override
	public Process_StateMachine_Port getProcess_StateMachine_Port() {
		return process_StateMachine_Port;
	}
	
	public class Handle_StateMachine_Port implements StateMachine_Interface_For_OrionInterface.Provided {
		
		
		@Override
		public boolean isRaisedOrionDisconn() {
			return slave.getHandle_StateMachine_Port().isRaisedOrionDisconn();
		}
		
		@Override
		public boolean isRaisedOrionDisconnCause() {
			return slave.getHandle_StateMachine_Port().isRaisedOrionDisconnCause();
		}
		
		@Override
		public boolean isRaisedOrionConnReq() {
			return slave.getHandle_StateMachine_Port().isRaisedOrionConnReq();
		}
		
		@Override
		public boolean isRaisedOrionAppData() {
			return slave.getHandle_StateMachine_Port().isRaisedOrionAppData();
		}
		
		@Override
		public boolean isRaisedOrionKeepAlive() {
			return slave.getHandle_StateMachine_Port().isRaisedOrionKeepAlive();
		}
		
		@Override
		public boolean isRaisedOrionConnConf() {
			return slave.getHandle_StateMachine_Port().isRaisedOrionConnConf();
		}
		
		@Override
		public boolean isRaisedOrionConnResp() {
			return slave.getHandle_StateMachine_Port().isRaisedOrionConnResp();
		}
		
		@Override
		public void registerListener(StateMachine_Interface_For_OrionInterface.Listener.Provided listener) {
			slave.getHandle_StateMachine_Port().registerListener(listener);
		}
		
		@Override
		public List<StateMachine_Interface_For_OrionInterface.Listener.Provided> getRegisteredListeners() {
			return slave.getHandle_StateMachine_Port().getRegisteredListeners();
		}
		
	}
	
	@Override
	public Handle_StateMachine_Port getHandle_StateMachine_Port() {
		return handle_StateMachine_Port;
	}
	
	public class TimeoutKeepAliveReceiveTimeout_4 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			q1.offer(new Event("TimeoutKeepAliveReceiveTimeout_4.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			slave.getTimeoutKeepAliveReceiveTimeout_4().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return slave.getTimeoutKeepAliveReceiveTimeout_4().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKeepAliveReceiveTimeout_4 getTimeoutKeepAliveReceiveTimeout_4() {
		return timeoutKeepAliveReceiveTimeout_4;
	}
	
	public class TimeoutKapcsolodik_3 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			q1.offer(new Event("TimeoutKapcsolodik_3.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			slave.getTimeoutKapcsolodik_3().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return slave.getTimeoutKapcsolodik_3().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKapcsolodik_3 getTimeoutKapcsolodik_3() {
		return timeoutKapcsolodik_3;
	}
	
	public class TimeoutKeepAliveSendTimeout_0 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			q1.offer(new Event("TimeoutKeepAliveSendTimeout_0.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			slave.getTimeoutKeepAliveSendTimeout_0().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return slave.getTimeoutKeepAliveSendTimeout_0().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKeepAliveSendTimeout_0 getTimeoutKeepAliveSendTimeout_0() {
		return timeoutKeepAliveSendTimeout_0;
	}
	
	public class TimeoutKeepAliveReceiveTimeout_4_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return slave.getTimeoutKeepAliveReceiveTimeout_4_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			slave.getTimeoutKeepAliveReceiveTimeout_4_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return slave.getTimeoutKeepAliveReceiveTimeout_4_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKeepAliveReceiveTimeout_4_req getTimeoutKeepAliveReceiveTimeout_4_req() {
		return timeoutKeepAliveReceiveTimeout_4_req;
	}
	
	public class TimeoutKapcsolodik_3_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return slave.getTimeoutKapcsolodik_3_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			slave.getTimeoutKapcsolodik_3_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return slave.getTimeoutKapcsolodik_3_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKapcsolodik_3_req getTimeoutKapcsolodik_3_req() {
		return timeoutKapcsolodik_3_req;
	}
	
	public class TimeoutKeepAliveSendTimeout_0_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return slave.getTimeoutKeepAliveSendTimeout_0_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			slave.getTimeoutKeepAliveSendTimeout_0_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return slave.getTimeoutKeepAliveSendTimeout_0_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKeepAliveSendTimeout_0_req getTimeoutKeepAliveSendTimeout_0_req() {
		return timeoutKeepAliveSendTimeout_0_req;
	}
	
	public class Status implements ConnectionStateInterface.Provided {
		
		
		@Override
		public boolean isRaisedConn() {
			return slave.getStatus().isRaisedConn();
		}
		
		@Override
		public boolean isRaisedDisconn() {
			return slave.getStatus().isRaisedDisconn();
		}
		
		@Override
		public void registerListener(ConnectionStateInterface.Listener.Provided listener) {
			slave.getStatus().registerListener(listener);
		}
		
		@Override
		public List<ConnectionStateInterface.Listener.Provided> getRegisteredListeners() {
			return slave.getStatus().getRegisteredListeners();
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
				slave.getBlock_Port().raiseOperation_Call_SendData();
			break;
			case "Block_Port.Operation_Call_Invalid":
				slave.getBlock_Port().raiseOperation_Call_Invalid();
			break;
			case "Connection_Port.Operation_Call_Connect":
				slave.getConnection_Port().raiseOperation_Call_Connect();
			break;
			case "Connection_Port.Operation_Call_Disconn":
				slave.getConnection_Port().raiseOperation_Call_Disconn();
			break;
			case "StateMachine_Port.OrionDisconn":
				slave.getStateMachine_Port().raiseOrionDisconn();
			break;
			case "StateMachine_Port.OrionDisconnCause":
				slave.getStateMachine_Port().raiseOrionDisconnCause();
			break;
			case "StateMachine_Port.OrionConnReq":
				slave.getStateMachine_Port().raiseOrionConnReq();
			break;
			case "StateMachine_Port.OrionAppData":
				slave.getStateMachine_Port().raiseOrionAppData();
			break;
			case "StateMachine_Port.OrionKeepAlive":
				slave.getStateMachine_Port().raiseOrionKeepAlive();
			break;
			case "StateMachine_Port.OrionConnConf":
				slave.getStateMachine_Port().raiseOrionConnConf();
			break;
			case "StateMachine_Port.OrionConnResp":
				slave.getStateMachine_Port().raiseOrionConnResp();
			break;
			case "TimeoutKapcsolodik_3.newEvent":
				slave.getTimeoutKapcsolodik_3().raiseNewEvent();
			break;
			case "TimeoutKeepAliveReceiveTimeout_4.newEvent":
				slave.getTimeoutKeepAliveReceiveTimeout_4().raiseNewEvent();
			break;
			case "TimeoutKeepAliveSendTimeout_0.newEvent":
				slave.getTimeoutKeepAliveSendTimeout_0().raiseNewEvent();
			break;
			default:
				throw new IllegalArgumentException("No such event!");
		}
	}
	
	private void performControlActions(Event event) {
		String[] eventName = event.getEvent().split("\\.");
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("Block_Port")) {
			slave.runCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("Connection_Port")) {
			slave.runCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("StateMachine_Port")) {
			slave.runCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimeoutKapcsolodik_3")) {
			slave.runCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimeoutKeepAliveReceiveTimeout_4")) {
			slave.runCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimeoutKeepAliveSendTimeout_0")) {
			slave.runCycle();
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
	
	public Orion_Slave_SM_ext getSlave() {
		return slave;
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
