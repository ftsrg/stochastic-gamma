package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_adapter;

import java.util.Collections;
import java.util.List;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;

import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm_ext.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm.*;

import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system.*;

public class Orion_Adapter implements Runnable, Orion_AdapterInterface {			
	// Thread running this wrapper instance
	private Thread thread;
	// Wrapped synchronous instance
	private OrionStochSystem system;
	// Control port instances
	// Wrapped port instances
	private TimoeutKeepAliveReceiveTimeout_3 timoeutKeepAliveReceiveTimeout_3;
	private TimeoutKapcsolodik_2 timeoutKapcsolodik_2;
	private TimeoutZarva_0 timeoutZarva_0;
	private TimeoutKeepAliveSendTimeout_1 timeoutKeepAliveSendTimeout_1;
	private TimeoutKeepAliveReceiveTimeout_4 timeoutKeepAliveReceiveTimeout_4;
	private TimeoutKapcsolodik_3 timeoutKapcsolodik_3;
	private TimeoutKeepAliveSendTimeout_0 timeoutKeepAliveSendTimeout_0;
	private TimoeutKeepAliveReceiveTimeout_3_req timoeutKeepAliveReceiveTimeout_3_req;
	private TimeoutKapcsolodik_2_req timeoutKapcsolodik_2_req;
	private TimeoutZarva_0_req timeoutZarva_0_req;
	private TimeoutKeepAliveSendTimeout_1_req timeoutKeepAliveSendTimeout_1_req;
	private TimeoutKeepAliveReceiveTimeout_4_req timeoutKeepAliveReceiveTimeout_4_req;
	private TimeoutKapcsolodik_3_req timeoutKapcsolodik_3_req;
	private TimeoutKeepAliveSendTimeout_0_req timeoutKeepAliveSendTimeout_0_req;
	private SystemStatus systemStatus;
	// Main queue
	private LinkedBlockingMultiQueue<String, Event> __asyncQueue = new LinkedBlockingMultiQueue<String, Event>();
	// Subqueues
	private LinkedBlockingMultiQueue<String, Event>.SubQueue queueOfTimoeutKeepAliveReceiveTimeout_3;
	private LinkedBlockingMultiQueue<String, Event>.SubQueue queueOfTimeoutKapcsolodik_2;
	private LinkedBlockingMultiQueue<String, Event>.SubQueue queueOfTimeoutZarva_0;
	private LinkedBlockingMultiQueue<String, Event>.SubQueue queueOfTimeoutKeepAliveSendTimeout_1;
	private LinkedBlockingMultiQueue<String, Event>.SubQueue queueOfTimeoutKeepAliveReceiveTimeout_4;
	private LinkedBlockingMultiQueue<String, Event>.SubQueue queueOfTimeoutKapcsolodik_3;
	private LinkedBlockingMultiQueue<String, Event>.SubQueue queueOfTimeoutKeepAliveSendTimeout_0;
	
	
	public boolean isEventQueueEmpty(){
		return __asyncQueue.isEmpty();
	}
	
	public Orion_Adapter() {
		system = new OrionStochSystem();
		// Wrapped port instances
		timoeutKeepAliveReceiveTimeout_3 = new TimoeutKeepAliveReceiveTimeout_3();
		timeoutKapcsolodik_2 = new TimeoutKapcsolodik_2();
		timeoutZarva_0 = new TimeoutZarva_0();
		timeoutKeepAliveSendTimeout_1 = new TimeoutKeepAliveSendTimeout_1();
		timeoutKeepAliveReceiveTimeout_4 = new TimeoutKeepAliveReceiveTimeout_4();
		timeoutKapcsolodik_3 = new TimeoutKapcsolodik_3();
		timeoutKeepAliveSendTimeout_0 = new TimeoutKeepAliveSendTimeout_0();
		timoeutKeepAliveReceiveTimeout_3_req = new TimoeutKeepAliveReceiveTimeout_3_req();
		timeoutKapcsolodik_2_req = new TimeoutKapcsolodik_2_req();
		timeoutZarva_0_req = new TimeoutZarva_0_req();
		timeoutKeepAliveSendTimeout_1_req = new TimeoutKeepAliveSendTimeout_1_req();
		timeoutKeepAliveReceiveTimeout_4_req = new TimeoutKeepAliveReceiveTimeout_4_req();
		timeoutKapcsolodik_3_req = new TimeoutKapcsolodik_3_req();
		timeoutKeepAliveSendTimeout_0_req = new TimeoutKeepAliveSendTimeout_0_req();
		systemStatus = new SystemStatus();
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
		system.handleBeforeReset();
	}
	
	public void resetVariables() {
		system.resetVariables();
	}
	
	public void resetStateConfigurations() {
		system.resetStateConfigurations();
	}
	
	public void raiseEntryEvents() {
		system.raiseEntryEvents();
	}
	
	public void handleAfterReset() {
		system.handleAfterReset();
		//
	}
	
	/** Creates the subqueues, clocks and enters the wrapped synchronous component. */
	private void init() {
		//system = new OrionStochSystem();
		// Creating subqueues: the negative conversion regarding priorities is needed,
		// because the lbmq marks higher priority with lower integer values
		__asyncQueue.addSubQueue("queueOfTimeoutKeepAliveSendTimeout_0", -(7), (int) 10);
		queueOfTimeoutKeepAliveSendTimeout_0 = __asyncQueue.getSubQueue("queueOfTimeoutKeepAliveSendTimeout_0");
		__asyncQueue.addSubQueue("queueOfTimeoutKapcsolodik_3", -(6), (int) 10);
		queueOfTimeoutKapcsolodik_3 = __asyncQueue.getSubQueue("queueOfTimeoutKapcsolodik_3");
		__asyncQueue.addSubQueue("queueOfTimeoutKeepAliveReceiveTimeout_4", -(5), (int) 10);
		queueOfTimeoutKeepAliveReceiveTimeout_4 = __asyncQueue.getSubQueue("queueOfTimeoutKeepAliveReceiveTimeout_4");
		__asyncQueue.addSubQueue("queueOfTimeoutKeepAliveSendTimeout_1", -(4), (int) 10);
		queueOfTimeoutKeepAliveSendTimeout_1 = __asyncQueue.getSubQueue("queueOfTimeoutKeepAliveSendTimeout_1");
		__asyncQueue.addSubQueue("queueOfTimeoutZarva_0", -(3), (int) 10);
		queueOfTimeoutZarva_0 = __asyncQueue.getSubQueue("queueOfTimeoutZarva_0");
		__asyncQueue.addSubQueue("queueOfTimeoutKapcsolodik_2", -(2), (int) 10);
		queueOfTimeoutKapcsolodik_2 = __asyncQueue.getSubQueue("queueOfTimeoutKapcsolodik_2");
		__asyncQueue.addSubQueue("queueOfTimoeutKeepAliveReceiveTimeout_3", -(1), (int) 10);
		queueOfTimoeutKeepAliveReceiveTimeout_3 = __asyncQueue.getSubQueue("queueOfTimoeutKeepAliveReceiveTimeout_3");
		// The thread has to be started manually
	}
	
	
	// Inner classes representing control ports
	
	// Inner classes representing wrapped ports
	public class TimoeutKeepAliveReceiveTimeout_3 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			queueOfTimoeutKeepAliveReceiveTimeout_3.offer(new Event("TimoeutKeepAliveReceiveTimeout_3.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			system.getTimoeutKeepAliveReceiveTimeout_3().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return system.getTimoeutKeepAliveReceiveTimeout_3().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimoeutKeepAliveReceiveTimeout_3 getTimoeutKeepAliveReceiveTimeout_3() {
		return timoeutKeepAliveReceiveTimeout_3;
	}
	
	public class TimeoutKapcsolodik_2 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			queueOfTimeoutKapcsolodik_2.offer(new Event("TimeoutKapcsolodik_2.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			system.getTimeoutKapcsolodik_2().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return system.getTimeoutKapcsolodik_2().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKapcsolodik_2 getTimeoutKapcsolodik_2() {
		return timeoutKapcsolodik_2;
	}
	
	public class TimeoutZarva_0 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			queueOfTimeoutZarva_0.offer(new Event("TimeoutZarva_0.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			system.getTimeoutZarva_0().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return system.getTimeoutZarva_0().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutZarva_0 getTimeoutZarva_0() {
		return timeoutZarva_0;
	}
	
	public class TimeoutKeepAliveSendTimeout_1 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			queueOfTimeoutKeepAliveSendTimeout_1.offer(new Event("TimeoutKeepAliveSendTimeout_1.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			system.getTimeoutKeepAliveSendTimeout_1().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return system.getTimeoutKeepAliveSendTimeout_1().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKeepAliveSendTimeout_1 getTimeoutKeepAliveSendTimeout_1() {
		return timeoutKeepAliveSendTimeout_1;
	}
	
	public class TimeoutKeepAliveReceiveTimeout_4 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			queueOfTimeoutKeepAliveReceiveTimeout_4.offer(new Event("TimeoutKeepAliveReceiveTimeout_4.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			system.getTimeoutKeepAliveReceiveTimeout_4().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return system.getTimeoutKeepAliveReceiveTimeout_4().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKeepAliveReceiveTimeout_4 getTimeoutKeepAliveReceiveTimeout_4() {
		return timeoutKeepAliveReceiveTimeout_4;
	}
	
	public class TimeoutKapcsolodik_3 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			queueOfTimeoutKapcsolodik_3.offer(new Event("TimeoutKapcsolodik_3.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			system.getTimeoutKapcsolodik_3().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return system.getTimeoutKapcsolodik_3().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKapcsolodik_3 getTimeoutKapcsolodik_3() {
		return timeoutKapcsolodik_3;
	}
	
	public class TimeoutKeepAliveSendTimeout_0 implements SoftwareTimerInterface.Required {
		
		@Override
		public void raiseNewEvent() {
			queueOfTimeoutKeepAliveSendTimeout_0.offer(new Event("TimeoutKeepAliveSendTimeout_0.newEvent"));
		}
		
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			system.getTimeoutKeepAliveSendTimeout_0().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return system.getTimeoutKeepAliveSendTimeout_0().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKeepAliveSendTimeout_0 getTimeoutKeepAliveSendTimeout_0() {
		return timeoutKeepAliveSendTimeout_0;
	}
	
	public class TimoeutKeepAliveReceiveTimeout_3_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return system.getTimoeutKeepAliveReceiveTimeout_3_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			system.getTimoeutKeepAliveReceiveTimeout_3_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return system.getTimoeutKeepAliveReceiveTimeout_3_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimoeutKeepAliveReceiveTimeout_3_req getTimoeutKeepAliveReceiveTimeout_3_req() {
		return timoeutKeepAliveReceiveTimeout_3_req;
	}
	
	public class TimeoutKapcsolodik_2_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return system.getTimeoutKapcsolodik_2_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			system.getTimeoutKapcsolodik_2_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return system.getTimeoutKapcsolodik_2_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKapcsolodik_2_req getTimeoutKapcsolodik_2_req() {
		return timeoutKapcsolodik_2_req;
	}
	
	public class TimeoutZarva_0_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return system.getTimeoutZarva_0_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			system.getTimeoutZarva_0_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return system.getTimeoutZarva_0_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutZarva_0_req getTimeoutZarva_0_req() {
		return timeoutZarva_0_req;
	}
	
	public class TimeoutKeepAliveSendTimeout_1_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return system.getTimeoutKeepAliveSendTimeout_1_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			system.getTimeoutKeepAliveSendTimeout_1_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return system.getTimeoutKeepAliveSendTimeout_1_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKeepAliveSendTimeout_1_req getTimeoutKeepAliveSendTimeout_1_req() {
		return timeoutKeepAliveSendTimeout_1_req;
	}
	
	public class TimeoutKeepAliveReceiveTimeout_4_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return system.getTimeoutKeepAliveReceiveTimeout_4_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			system.getTimeoutKeepAliveReceiveTimeout_4_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return system.getTimeoutKeepAliveReceiveTimeout_4_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKeepAliveReceiveTimeout_4_req getTimeoutKeepAliveReceiveTimeout_4_req() {
		return timeoutKeepAliveReceiveTimeout_4_req;
	}
	
	public class TimeoutKapcsolodik_3_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return system.getTimeoutKapcsolodik_3_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			system.getTimeoutKapcsolodik_3_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return system.getTimeoutKapcsolodik_3_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKapcsolodik_3_req getTimeoutKapcsolodik_3_req() {
		return timeoutKapcsolodik_3_req;
	}
	
	public class TimeoutKeepAliveSendTimeout_0_req implements SoftwareTimerInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return system.getTimeoutKeepAliveSendTimeout_0_req().isRaisedNewEvent();
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			system.getTimeoutKeepAliveSendTimeout_0_req().registerListener(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return system.getTimeoutKeepAliveSendTimeout_0_req().getRegisteredListeners();
		}
		
	}
	
	@Override
	public TimeoutKeepAliveSendTimeout_0_req getTimeoutKeepAliveSendTimeout_0_req() {
		return timeoutKeepAliveSendTimeout_0_req;
	}
	
	public class SystemStatus implements ConnectionStateInterface.Provided {
		
		
		@Override
		public boolean isRaisedConn() {
			return system.getSystemStatus().isRaisedConn();
		}
		
		@Override
		public boolean isRaisedDisconn() {
			return system.getSystemStatus().isRaisedDisconn();
		}
		
		@Override
		public void registerListener(ConnectionStateInterface.Listener.Provided listener) {
			system.getSystemStatus().registerListener(listener);
		}
		
		@Override
		public List<ConnectionStateInterface.Listener.Provided> getRegisteredListeners() {
			return system.getSystemStatus().getRegisteredListeners();
		}
		
	}
	
	@Override
	public SystemStatus getSystemStatus() {
		return systemStatus;
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
			case "TimoeutKeepAliveReceiveTimeout_3.newEvent":
				system.getTimoeutKeepAliveReceiveTimeout_3().raiseNewEvent();
			break;
			case "TimeoutKapcsolodik_2.newEvent":
				system.getTimeoutKapcsolodik_2().raiseNewEvent();
			break;
			case "TimeoutZarva_0.newEvent":
				system.getTimeoutZarva_0().raiseNewEvent();
			break;
			case "TimeoutKeepAliveSendTimeout_1.newEvent":
				system.getTimeoutKeepAliveSendTimeout_1().raiseNewEvent();
			break;
			case "TimeoutKeepAliveReceiveTimeout_4.newEvent":
				system.getTimeoutKeepAliveReceiveTimeout_4().raiseNewEvent();
			break;
			case "TimeoutKapcsolodik_3.newEvent":
				system.getTimeoutKapcsolodik_3().raiseNewEvent();
			break;
			case "TimeoutKeepAliveSendTimeout_0.newEvent":
				system.getTimeoutKeepAliveSendTimeout_0().raiseNewEvent();
			break;
			default:
				throw new IllegalArgumentException("No such event!");
		}
	}
	
	private void performControlActions(Event event) {
		String[] eventName = event.getEvent().split("\\.");
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimoeutKeepAliveReceiveTimeout_3")) {
			system.runFullCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimeoutKapcsolodik_2")) {
			system.runFullCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimeoutZarva_0")) {
			system.runFullCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimeoutKeepAliveSendTimeout_1")) {
			system.runFullCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimeoutKeepAliveReceiveTimeout_4")) {
			system.runFullCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimeoutKapcsolodik_3")) {
			system.runFullCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("TimeoutKeepAliveSendTimeout_0")) {
			system.runFullCycle();
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
	
	public OrionStochSystem getSystem() {
		return system;
	}
	
	

	public String getInQueue(){
		String str="Input events (";
		str=str+"queueOfTimeoutKeepAliveSendTimeout_0 [";
		for (Event event:queueOfTimeoutKeepAliveSendTimeout_0){
			str=str+event.getEvent().toString()+" : ";
			if (event.getValue() != null){
				for (Object value:event.getValue()){
					str=str+" "+value.toString()+",";
				}
			}
		}
		str=str+"], ";
		str=str+"queueOfTimeoutKapcsolodik_3 [";
		for (Event event:queueOfTimeoutKapcsolodik_3){
			str=str+event.getEvent().toString()+" : ";
			if (event.getValue() != null){
				for (Object value:event.getValue()){
					str=str+" "+value.toString()+",";
				}
			}
		}
		str=str+"], ";
		str=str+"queueOfTimeoutKeepAliveReceiveTimeout_4 [";
		for (Event event:queueOfTimeoutKeepAliveReceiveTimeout_4){
			str=str+event.getEvent().toString()+" : ";
			if (event.getValue() != null){
				for (Object value:event.getValue()){
					str=str+" "+value.toString()+",";
				}
			}
		}
		str=str+"], ";
		str=str+"queueOfTimeoutKeepAliveSendTimeout_1 [";
		for (Event event:queueOfTimeoutKeepAliveSendTimeout_1){
			str=str+event.getEvent().toString()+" : ";
			if (event.getValue() != null){
				for (Object value:event.getValue()){
					str=str+" "+value.toString()+",";
				}
			}
		}
		str=str+"], ";
		str=str+"queueOfTimeoutZarva_0 [";
		for (Event event:queueOfTimeoutZarva_0){
			str=str+event.getEvent().toString()+" : ";
			if (event.getValue() != null){
				for (Object value:event.getValue()){
					str=str+" "+value.toString()+",";
				}
			}
		}
		str=str+"], ";
		str=str+"queueOfTimeoutKapcsolodik_2 [";
		for (Event event:queueOfTimeoutKapcsolodik_2){
			str=str+event.getEvent().toString()+" : ";
			if (event.getValue() != null){
				for (Object value:event.getValue()){
					str=str+" "+value.toString()+",";
				}
			}
		}
		str=str+"], ";
		str=str+"queueOfTimoeutKeepAliveReceiveTimeout_3 [";
		for (Event event:queueOfTimoeutKeepAliveReceiveTimeout_3){
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
