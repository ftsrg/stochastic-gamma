package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system_sync;

import java.util.List;
import java.util.LinkedList;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm.*;


import hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class OrionStochSystem implements OrionStochSystemInterface {
	// Component instances
	private Orion_Master_SM master;
	private Orion_Slave_SM slave;
	private Status_SM connStatus;
	// Environmental Component instances
	// Port instances
	private SystemStatus systemStatus;
	
	
				
	protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
	protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
				
	public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
		envComponents.add(component);
		envMap.put(name,component);
	}
	
	public OrionStochSystem(UnifiedTimerInterface timer) {
		master = new Orion_Master_SM();
		slave = new Orion_Slave_SM();
		connStatus = new Status_SM();
		systemStatus = new SystemStatus(); 
		// Environmental Component instances
		setTimer(timer);
		init();
	}
	
	public OrionStochSystem() {
		master = new Orion_Master_SM();
		slave = new Orion_Slave_SM();
		connStatus = new Status_SM();
		systemStatus = new SystemStatus(); 
		// Environmental Component instances
		init();
	}
	
	/** Resets the contained statemachines recursively. Must be called to initialize the component. */
	@Override
	public void reset() {
		this.handleBeforeReset();
		this.resetVariables();
		this.resetStateConfigurations();
		this.raiseEntryEvents();
		this.handleAfterReset();
	}

	public void handleBeforeReset() {
		//
		master.handleBeforeReset();
		slave.handleBeforeReset();
		connStatus.handleBeforeReset();
	}

	public void resetVariables() {
		master.resetVariables();
		slave.resetVariables();
		connStatus.resetVariables();
	}
	
	public void resetStateConfigurations() {
		master.resetStateConfigurations();
		slave.resetStateConfigurations();
		connStatus.resetStateConfigurations();
	}
	
	public void raiseEntryEvents() {
		master.raiseEntryEvents();
		slave.raiseEntryEvents();
		connStatus.raiseEntryEvents();
	}

	public void handleAfterReset() {
		master.handleAfterReset();
		slave.handleAfterReset();
		connStatus.handleAfterReset();
		clearPorts();
		// Initializing chain of listeners and events 
		notifyAllListeners();
	}
	
	/** Creates the channel mappings and enters the wrapped statemachines. */
	private void init() {
		// Registration of simple channels
		master.getStatus().registerListener(connStatus.getMasterStatus());
		connStatus.getMasterStatus().registerListener(master.getStatus());
		slave.getStatus().registerListener(connStatus.getSlaveStatus());
		connStatus.getSlaveStatus().registerListener(slave.getStatus());
		// Registration of broadcast channels
	}
	
	// Inner classes representing Ports
	public class SystemStatus implements ConnectionStateInterface.Provided,ConnectionStateInterface.Listener.Provided {
		private List<ConnectionStateInterface.Listener.Provided> listeners = new LinkedList<ConnectionStateInterface.Listener.Provided>();
		boolean isRaisedConn;
		boolean isRaisedDisconn;
		
		public SystemStatus() {
			// Registering the listener to the contained component
			connStatus.getSystemStatus().registerListener(new SystemStatusUtil());
		}
		
		
		@Override
		public boolean isRaisedConn() {
			return isRaisedConn;
		}
		
		@Override
		public boolean isRaisedDisconn() {
			return isRaisedDisconn;
		}
		@Override
		public void raiseConn() {
			isRaisedConn = true;
		}
		
		@Override
		public void raiseDisconn() {
			isRaisedDisconn = true;
		}
		
		
		// Class for the setting of the boolean fields (events)
		private class SystemStatusUtil implements ConnectionStateInterface.Listener.Provided {
			@Override
			public void raiseConn() {
				isRaisedConn = true;
			}
			
			@Override
			public void raiseDisconn() {
				isRaisedDisconn = true;
			}
		}
		
		@Override
		public void registerListener(ConnectionStateInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<ConnectionStateInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
			isRaisedConn = false;
			isRaisedDisconn = false;
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
			if (isRaisedConn) {
				for (ConnectionStateInterface.Listener.Provided listener : listeners) {
					listener.raiseConn();
				}
			}
			if (isRaisedDisconn) {
				for (ConnectionStateInterface.Listener.Provided listener : listeners) {
					listener.raiseDisconn();
				}
			}
		}
		
	}
	
	@Override
	public SystemStatus getSystemStatus() {
		return systemStatus;
	}
	
	/** Clears the the boolean flags of all out-events in each contained port. */
	private void clearPorts() {
		getSystemStatus().clear();
	}
	
	/** Notifies all registered listeners in each contained port. */
	public void notifyAllListeners() {
		master.notifyAllListeners();
		slave.notifyAllListeners();
		connStatus.notifyAllListeners();

		notifyListeners();
	}
	
	public void notifyListeners() {
		getSystemStatus().notifyListeners();
	}
	
	/** Changes the event and process queues of all component instances. Should be used only be the container (composite system) class. */
	public void changeEventQueues() {
		master.changeEventQueues();
		slave.changeEventQueues();
		connStatus.changeEventQueues();
	}
	
	/** Returns whether all event queues of the contained component instances are empty. 
	Should be used only be the container (composite system) class. */
	public boolean isEventQueueEmpty() {
		return master.isEventQueueEmpty() && slave.isEventQueueEmpty() && connStatus.isEventQueueEmpty();
	}
	
	/** Initiates cycle runs until all event queues of component instances are empty. */
	@Override
	public void runFullCycle() {
		do {
			runCycle();
		}
		while (!isEventQueueEmpty() &&    envMap.get("masterSlaveChannel").isEventQueueEmpty() &&   envMap.get("slaveMasterChannel").isEventQueueEmpty() );
	}
	
	/** Changes event queues and initiates a cycle run.
	 * This should be the execution point from an asynchronous component. */
	@Override
	public void runCycle() {
		// Changing the insert and process queues for all synchronous subcomponents
		changeEventQueues();
		// Composite type-dependent behavior
		runComponent();
	}
	
	/** Initiates a cycle run without changing the event queues.
	 * Should be used only be the container (composite system) class. */
	public void runComponent() {
		// Running contained components
		master.runComponent();
		slave.runComponent();
		connStatus.runComponent();
		// Notifying registered listeners
		notifyListeners();
		
		// Ends with the clearing of the out-event flags
		clearPorts();
	}

	/** Setter for the timer e.g., a virtual timer. */
	public void setTimer(UnifiedTimerInterface timer) {
		master.setTimer(timer);
		slave.setTimer(timer);
		reset();
	}
	
	/**  Getter for component instances, e.g., enabling to check their states. */
	public Orion_Master_SM getMaster() {
		return master;
	}
	
	public Orion_Slave_SM getSlave() {
		return slave;
	}
	
	public Status_SM getConnStatus() {
		return connStatus;
	}
	
	// Environmental Component instances
	
	
}
