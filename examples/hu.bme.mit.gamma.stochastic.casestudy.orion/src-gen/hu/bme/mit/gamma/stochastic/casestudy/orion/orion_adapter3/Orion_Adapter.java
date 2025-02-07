package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_adapter3;

import java.util.Collections;
import java.util.List;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;

import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system_sync.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm.*;

import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system_sync.*;

public class Orion_Adapter implements Runnable, Orion_AdapterInterface {			
	// Thread running this wrapper instance
	private Thread thread;
	// Wrapped synchronous instance
	private OrionStochSystem system;
	// Control port instances
	private Timing timing;
	// Wrapped port instances
	private SystemStatus systemStatus;
	// Main queue
	private LinkedBlockingMultiQueue<String, Event> __asyncQueue = new LinkedBlockingMultiQueue<String, Event>();
	// Subqueues
	private LinkedBlockingMultiQueue<String, Event>.SubQueue queueClock;
	
	public Orion_Adapter(UnifiedTimerInterface timer) {
		system = new OrionStochSystem();
		timing = new Timing();
		// Wrapped port instances
		systemStatus = new SystemStatus();
		setTimer(timer);
		// Init is done in setTimer
	}
	
	public boolean isEventQueueEmpty(){
		return __asyncQueue.isEmpty();
	}
	
	public Orion_Adapter() {
		system = new OrionStochSystem();
		timing = new Timing();
		// Wrapped port instances
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
		__asyncQueue.addSubQueue("queueClock", -(1), (int) 1);
		queueClock = __asyncQueue.getSubQueue("queueClock");
		// The thread has to be started manually
	}
	
	
	// Inner classes representing control ports
	public class Timing implements TimingInterface.Required {
		
		@Override
		public void raiseSchedule() {
			queueClock.offer(new Event("timing.schedule"));
		}
		
		
		@Override
		public void registerListener(TimingInterface.Listener.Required listener) {
			// No operation as out event are not interpreted in case of control ports
		}
		
		@Override
		public List<TimingInterface.Listener.Required> getRegisteredListeners() {
			// Empty list as out event are not interpreted in case of control ports
			return Collections.emptyList();
		}
		
	}
	
	@Override
	public Timing getTiming() {
		return timing;
	}
	
	// Inner classes representing wrapped ports
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
		String portName = event.getEvent().split("\\.")[0];
		return portName.equals("timing");
	}
	
	private void forwardEvent(Event event) {
		switch (event.getEvent()) {
			default:
				throw new IllegalArgumentException("No such event!");
		}
	}
	
	private void performControlActions(Event event) {
		String[] eventName = event.getEvent().split("\\.");
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("timing")) {
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
	
	public void setTimer(UnifiedTimerInterface timer) {
		system.setTimer(timer);
		//init(); // To set the service into functioning state with clocks (so that "after 1 s" works with new timer as well)
	}
	

	public String getInQueue(){
		String str="Input events (";
		str=str+"queueClock [";
		for (Event event:queueClock){
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
