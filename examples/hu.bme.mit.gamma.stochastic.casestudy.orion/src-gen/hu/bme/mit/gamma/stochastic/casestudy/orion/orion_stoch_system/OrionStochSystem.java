package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system;

import java.util.List;
import java.util.LinkedList;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm_ext.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm.*;


import hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class OrionStochSystem implements OrionStochSystemInterface {
	// Component instances
	private Orion_Master_SM_ext master;
	private Orion_Slave_SM_ext slave;
	private Status_SM connStatus;
	// Environmental Component instances
	// Port instances
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
	
	
				
	protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
	protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
				
	public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
		envComponents.add(component);
		envMap.put(name,component);
	}
	
	
	public OrionStochSystem() {
		master = new Orion_Master_SM_ext();
		slave = new Orion_Slave_SM_ext();
		connStatus = new Status_SM();
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
		slave.getStatus().registerListener(connStatus.getSlaveStatus());
		connStatus.getSlaveStatus().registerListener(slave.getStatus());
		master.getStatus().registerListener(connStatus.getMasterStatus());
		connStatus.getMasterStatus().registerListener(master.getStatus());
		// Registration of broadcast channels
	}
	
	// Inner classes representing Ports
	public class TimoeutKeepAliveReceiveTimeout_3 implements SoftwareTimerInterface.Required,SoftwareTimerInterface.Listener.Required {
		private List<SoftwareTimerInterface.Listener.Required> listeners = new LinkedList<SoftwareTimerInterface.Listener.Required>();
		
		public TimoeutKeepAliveReceiveTimeout_3() {
			// Registering the listener to the contained component
			master.getTimoeutKeepAliveReceiveTimeout_3().registerListener(new TimoeutKeepAliveReceiveTimeout_3Util());
		}
		
		@Override
		public void raiseNewEvent() {
			master.getTimoeutKeepAliveReceiveTimeout_3().raiseNewEvent();
		}
		
		
		
		// Class for the setting of the boolean fields (events)
		private class TimoeutKeepAliveReceiveTimeout_3Util implements SoftwareTimerInterface.Listener.Required {
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
		}
		
	}
	
	@Override
	public TimoeutKeepAliveReceiveTimeout_3 getTimoeutKeepAliveReceiveTimeout_3() {
		return timoeutKeepAliveReceiveTimeout_3;
	}
	
	public class TimeoutKapcsolodik_2 implements SoftwareTimerInterface.Required,SoftwareTimerInterface.Listener.Required {
		private List<SoftwareTimerInterface.Listener.Required> listeners = new LinkedList<SoftwareTimerInterface.Listener.Required>();
		
		public TimeoutKapcsolodik_2() {
			// Registering the listener to the contained component
			master.getTimeoutKapcsolodik_2().registerListener(new TimeoutKapcsolodik_2Util());
		}
		
		@Override
		public void raiseNewEvent() {
			master.getTimeoutKapcsolodik_2().raiseNewEvent();
		}
		
		
		
		// Class for the setting of the boolean fields (events)
		private class TimeoutKapcsolodik_2Util implements SoftwareTimerInterface.Listener.Required {
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
		}
		
	}
	
	@Override
	public TimeoutKapcsolodik_2 getTimeoutKapcsolodik_2() {
		return timeoutKapcsolodik_2;
	}
	
	public class TimeoutZarva_0 implements SoftwareTimerInterface.Required,SoftwareTimerInterface.Listener.Required {
		private List<SoftwareTimerInterface.Listener.Required> listeners = new LinkedList<SoftwareTimerInterface.Listener.Required>();
		
		public TimeoutZarva_0() {
			// Registering the listener to the contained component
			master.getTimeoutZarva_0().registerListener(new TimeoutZarva_0Util());
		}
		
		@Override
		public void raiseNewEvent() {
			master.getTimeoutZarva_0().raiseNewEvent();
		}
		
		
		
		// Class for the setting of the boolean fields (events)
		private class TimeoutZarva_0Util implements SoftwareTimerInterface.Listener.Required {
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
		}
		
	}
	
	@Override
	public TimeoutZarva_0 getTimeoutZarva_0() {
		return timeoutZarva_0;
	}
	
	public class TimeoutKeepAliveSendTimeout_1 implements SoftwareTimerInterface.Required,SoftwareTimerInterface.Listener.Required {
		private List<SoftwareTimerInterface.Listener.Required> listeners = new LinkedList<SoftwareTimerInterface.Listener.Required>();
		
		public TimeoutKeepAliveSendTimeout_1() {
			// Registering the listener to the contained component
			master.getTimeoutKeepAliveSendTimeout_1().registerListener(new TimeoutKeepAliveSendTimeout_1Util());
		}
		
		@Override
		public void raiseNewEvent() {
			master.getTimeoutKeepAliveSendTimeout_1().raiseNewEvent();
		}
		
		
		
		// Class for the setting of the boolean fields (events)
		private class TimeoutKeepAliveSendTimeout_1Util implements SoftwareTimerInterface.Listener.Required {
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
		}
		
	}
	
	@Override
	public TimeoutKeepAliveSendTimeout_1 getTimeoutKeepAliveSendTimeout_1() {
		return timeoutKeepAliveSendTimeout_1;
	}
	
	public class TimeoutKeepAliveReceiveTimeout_4 implements SoftwareTimerInterface.Required,SoftwareTimerInterface.Listener.Required {
		private List<SoftwareTimerInterface.Listener.Required> listeners = new LinkedList<SoftwareTimerInterface.Listener.Required>();
		
		public TimeoutKeepAliveReceiveTimeout_4() {
			// Registering the listener to the contained component
			slave.getTimeoutKeepAliveReceiveTimeout_4().registerListener(new TimeoutKeepAliveReceiveTimeout_4Util());
		}
		
		@Override
		public void raiseNewEvent() {
			slave.getTimeoutKeepAliveReceiveTimeout_4().raiseNewEvent();
		}
		
		
		
		// Class for the setting of the boolean fields (events)
		private class TimeoutKeepAliveReceiveTimeout_4Util implements SoftwareTimerInterface.Listener.Required {
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
		}
		
	}
	
	@Override
	public TimeoutKeepAliveReceiveTimeout_4 getTimeoutKeepAliveReceiveTimeout_4() {
		return timeoutKeepAliveReceiveTimeout_4;
	}
	
	public class TimeoutKapcsolodik_3 implements SoftwareTimerInterface.Required,SoftwareTimerInterface.Listener.Required {
		private List<SoftwareTimerInterface.Listener.Required> listeners = new LinkedList<SoftwareTimerInterface.Listener.Required>();
		
		public TimeoutKapcsolodik_3() {
			// Registering the listener to the contained component
			slave.getTimeoutKapcsolodik_3().registerListener(new TimeoutKapcsolodik_3Util());
		}
		
		@Override
		public void raiseNewEvent() {
			slave.getTimeoutKapcsolodik_3().raiseNewEvent();
		}
		
		
		
		// Class for the setting of the boolean fields (events)
		private class TimeoutKapcsolodik_3Util implements SoftwareTimerInterface.Listener.Required {
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
		}
		
	}
	
	@Override
	public TimeoutKapcsolodik_3 getTimeoutKapcsolodik_3() {
		return timeoutKapcsolodik_3;
	}
	
	public class TimeoutKeepAliveSendTimeout_0 implements SoftwareTimerInterface.Required,SoftwareTimerInterface.Listener.Required {
		private List<SoftwareTimerInterface.Listener.Required> listeners = new LinkedList<SoftwareTimerInterface.Listener.Required>();
		
		public TimeoutKeepAliveSendTimeout_0() {
			// Registering the listener to the contained component
			slave.getTimeoutKeepAliveSendTimeout_0().registerListener(new TimeoutKeepAliveSendTimeout_0Util());
		}
		
		@Override
		public void raiseNewEvent() {
			slave.getTimeoutKeepAliveSendTimeout_0().raiseNewEvent();
		}
		
		
		
		// Class for the setting of the boolean fields (events)
		private class TimeoutKeepAliveSendTimeout_0Util implements SoftwareTimerInterface.Listener.Required {
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
		}
		
	}
	
	@Override
	public TimeoutKeepAliveSendTimeout_0 getTimeoutKeepAliveSendTimeout_0() {
		return timeoutKeepAliveSendTimeout_0;
	}
	
	public class TimoeutKeepAliveReceiveTimeout_3_req implements SoftwareTimerInterface.Provided,SoftwareTimerInterface.Listener.Provided {
		private List<SoftwareTimerInterface.Listener.Provided> listeners = new LinkedList<SoftwareTimerInterface.Listener.Provided>();
		boolean isRaisedNewEvent;
		
		public TimoeutKeepAliveReceiveTimeout_3_req() {
			// Registering the listener to the contained component
			master.getTimoeutKeepAliveReceiveTimeout_3_req().registerListener(new TimoeutKeepAliveReceiveTimeout_3_reqUtil());
		}
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return isRaisedNewEvent;
		}
		@Override
		public void raiseNewEvent() {
			isRaisedNewEvent = true;
		}
		
		
		// Class for the setting of the boolean fields (events)
		private class TimoeutKeepAliveReceiveTimeout_3_reqUtil implements SoftwareTimerInterface.Listener.Provided {
			@Override
			public void raiseNewEvent() {
				isRaisedNewEvent = true;
			}
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
			isRaisedNewEvent = false;
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
			if (isRaisedNewEvent) {
				for (SoftwareTimerInterface.Listener.Provided listener : listeners) {
					listener.raiseNewEvent();
				}
			}
		}
		
	}
	
	@Override
	public TimoeutKeepAliveReceiveTimeout_3_req getTimoeutKeepAliveReceiveTimeout_3_req() {
		return timoeutKeepAliveReceiveTimeout_3_req;
	}
	
	public class TimeoutKapcsolodik_2_req implements SoftwareTimerInterface.Provided,SoftwareTimerInterface.Listener.Provided {
		private List<SoftwareTimerInterface.Listener.Provided> listeners = new LinkedList<SoftwareTimerInterface.Listener.Provided>();
		boolean isRaisedNewEvent;
		
		public TimeoutKapcsolodik_2_req() {
			// Registering the listener to the contained component
			master.getTimeoutKapcsolodik_2_req().registerListener(new TimeoutKapcsolodik_2_reqUtil());
		}
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return isRaisedNewEvent;
		}
		@Override
		public void raiseNewEvent() {
			isRaisedNewEvent = true;
		}
		
		
		// Class for the setting of the boolean fields (events)
		private class TimeoutKapcsolodik_2_reqUtil implements SoftwareTimerInterface.Listener.Provided {
			@Override
			public void raiseNewEvent() {
				isRaisedNewEvent = true;
			}
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
			isRaisedNewEvent = false;
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
			if (isRaisedNewEvent) {
				for (SoftwareTimerInterface.Listener.Provided listener : listeners) {
					listener.raiseNewEvent();
				}
			}
		}
		
	}
	
	@Override
	public TimeoutKapcsolodik_2_req getTimeoutKapcsolodik_2_req() {
		return timeoutKapcsolodik_2_req;
	}
	
	public class TimeoutZarva_0_req implements SoftwareTimerInterface.Provided,SoftwareTimerInterface.Listener.Provided {
		private List<SoftwareTimerInterface.Listener.Provided> listeners = new LinkedList<SoftwareTimerInterface.Listener.Provided>();
		boolean isRaisedNewEvent;
		
		public TimeoutZarva_0_req() {
			// Registering the listener to the contained component
			master.getTimeoutZarva_0_req().registerListener(new TimeoutZarva_0_reqUtil());
		}
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return isRaisedNewEvent;
		}
		@Override
		public void raiseNewEvent() {
			isRaisedNewEvent = true;
		}
		
		
		// Class for the setting of the boolean fields (events)
		private class TimeoutZarva_0_reqUtil implements SoftwareTimerInterface.Listener.Provided {
			@Override
			public void raiseNewEvent() {
				isRaisedNewEvent = true;
			}
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
			isRaisedNewEvent = false;
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
			if (isRaisedNewEvent) {
				for (SoftwareTimerInterface.Listener.Provided listener : listeners) {
					listener.raiseNewEvent();
				}
			}
		}
		
	}
	
	@Override
	public TimeoutZarva_0_req getTimeoutZarva_0_req() {
		return timeoutZarva_0_req;
	}
	
	public class TimeoutKeepAliveSendTimeout_1_req implements SoftwareTimerInterface.Provided,SoftwareTimerInterface.Listener.Provided {
		private List<SoftwareTimerInterface.Listener.Provided> listeners = new LinkedList<SoftwareTimerInterface.Listener.Provided>();
		boolean isRaisedNewEvent;
		
		public TimeoutKeepAliveSendTimeout_1_req() {
			// Registering the listener to the contained component
			master.getTimeoutKeepAliveSendTimeout_1_req().registerListener(new TimeoutKeepAliveSendTimeout_1_reqUtil());
		}
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return isRaisedNewEvent;
		}
		@Override
		public void raiseNewEvent() {
			isRaisedNewEvent = true;
		}
		
		
		// Class for the setting of the boolean fields (events)
		private class TimeoutKeepAliveSendTimeout_1_reqUtil implements SoftwareTimerInterface.Listener.Provided {
			@Override
			public void raiseNewEvent() {
				isRaisedNewEvent = true;
			}
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
			isRaisedNewEvent = false;
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
			if (isRaisedNewEvent) {
				for (SoftwareTimerInterface.Listener.Provided listener : listeners) {
					listener.raiseNewEvent();
				}
			}
		}
		
	}
	
	@Override
	public TimeoutKeepAliveSendTimeout_1_req getTimeoutKeepAliveSendTimeout_1_req() {
		return timeoutKeepAliveSendTimeout_1_req;
	}
	
	public class TimeoutKeepAliveReceiveTimeout_4_req implements SoftwareTimerInterface.Provided,SoftwareTimerInterface.Listener.Provided {
		private List<SoftwareTimerInterface.Listener.Provided> listeners = new LinkedList<SoftwareTimerInterface.Listener.Provided>();
		boolean isRaisedNewEvent;
		
		public TimeoutKeepAliveReceiveTimeout_4_req() {
			// Registering the listener to the contained component
			slave.getTimeoutKeepAliveReceiveTimeout_4_req().registerListener(new TimeoutKeepAliveReceiveTimeout_4_reqUtil());
		}
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return isRaisedNewEvent;
		}
		@Override
		public void raiseNewEvent() {
			isRaisedNewEvent = true;
		}
		
		
		// Class for the setting of the boolean fields (events)
		private class TimeoutKeepAliveReceiveTimeout_4_reqUtil implements SoftwareTimerInterface.Listener.Provided {
			@Override
			public void raiseNewEvent() {
				isRaisedNewEvent = true;
			}
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
			isRaisedNewEvent = false;
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
			if (isRaisedNewEvent) {
				for (SoftwareTimerInterface.Listener.Provided listener : listeners) {
					listener.raiseNewEvent();
				}
			}
		}
		
	}
	
	@Override
	public TimeoutKeepAliveReceiveTimeout_4_req getTimeoutKeepAliveReceiveTimeout_4_req() {
		return timeoutKeepAliveReceiveTimeout_4_req;
	}
	
	public class TimeoutKapcsolodik_3_req implements SoftwareTimerInterface.Provided,SoftwareTimerInterface.Listener.Provided {
		private List<SoftwareTimerInterface.Listener.Provided> listeners = new LinkedList<SoftwareTimerInterface.Listener.Provided>();
		boolean isRaisedNewEvent;
		
		public TimeoutKapcsolodik_3_req() {
			// Registering the listener to the contained component
			slave.getTimeoutKapcsolodik_3_req().registerListener(new TimeoutKapcsolodik_3_reqUtil());
		}
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return isRaisedNewEvent;
		}
		@Override
		public void raiseNewEvent() {
			isRaisedNewEvent = true;
		}
		
		
		// Class for the setting of the boolean fields (events)
		private class TimeoutKapcsolodik_3_reqUtil implements SoftwareTimerInterface.Listener.Provided {
			@Override
			public void raiseNewEvent() {
				isRaisedNewEvent = true;
			}
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
			isRaisedNewEvent = false;
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
			if (isRaisedNewEvent) {
				for (SoftwareTimerInterface.Listener.Provided listener : listeners) {
					listener.raiseNewEvent();
				}
			}
		}
		
	}
	
	@Override
	public TimeoutKapcsolodik_3_req getTimeoutKapcsolodik_3_req() {
		return timeoutKapcsolodik_3_req;
	}
	
	public class TimeoutKeepAliveSendTimeout_0_req implements SoftwareTimerInterface.Provided,SoftwareTimerInterface.Listener.Provided {
		private List<SoftwareTimerInterface.Listener.Provided> listeners = new LinkedList<SoftwareTimerInterface.Listener.Provided>();
		boolean isRaisedNewEvent;
		
		public TimeoutKeepAliveSendTimeout_0_req() {
			// Registering the listener to the contained component
			slave.getTimeoutKeepAliveSendTimeout_0_req().registerListener(new TimeoutKeepAliveSendTimeout_0_reqUtil());
		}
		
		
		@Override
		public boolean isRaisedNewEvent() {
			return isRaisedNewEvent;
		}
		@Override
		public void raiseNewEvent() {
			isRaisedNewEvent = true;
		}
		
		
		// Class for the setting of the boolean fields (events)
		private class TimeoutKeepAliveSendTimeout_0_reqUtil implements SoftwareTimerInterface.Listener.Provided {
			@Override
			public void raiseNewEvent() {
				isRaisedNewEvent = true;
			}
		}
		
		@Override
		public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
			listeners.add(listener);
		}
		
		@Override
		public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
			return listeners;
		}
		
		/** Resetting the boolean event flags to false. */
		public void clear() {
			isRaisedNewEvent = false;
		}
		
		/** Notifying the registered listeners. */
		public void notifyListeners() {
			if (isRaisedNewEvent) {
				for (SoftwareTimerInterface.Listener.Provided listener : listeners) {
					listener.raiseNewEvent();
				}
			}
		}
		
	}
	
	@Override
	public TimeoutKeepAliveSendTimeout_0_req getTimeoutKeepAliveSendTimeout_0_req() {
		return timeoutKeepAliveSendTimeout_0_req;
	}
	
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
		getTimoeutKeepAliveReceiveTimeout_3().clear();
		getTimeoutKapcsolodik_2().clear();
		getTimeoutZarva_0().clear();
		getTimeoutKeepAliveSendTimeout_1().clear();
		getTimeoutKeepAliveReceiveTimeout_4().clear();
		getTimeoutKapcsolodik_3().clear();
		getTimeoutKeepAliveSendTimeout_0().clear();
		getTimoeutKeepAliveReceiveTimeout_3_req().clear();
		getTimeoutKapcsolodik_2_req().clear();
		getTimeoutZarva_0_req().clear();
		getTimeoutKeepAliveSendTimeout_1_req().clear();
		getTimeoutKeepAliveReceiveTimeout_4_req().clear();
		getTimeoutKapcsolodik_3_req().clear();
		getTimeoutKeepAliveSendTimeout_0_req().clear();
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
		getTimoeutKeepAliveReceiveTimeout_3().notifyListeners();
		getTimeoutKapcsolodik_2().notifyListeners();
		getTimeoutZarva_0().notifyListeners();
		getTimeoutKeepAliveSendTimeout_1().notifyListeners();
		getTimeoutKeepAliveReceiveTimeout_4().notifyListeners();
		getTimeoutKapcsolodik_3().notifyListeners();
		getTimeoutKeepAliveSendTimeout_0().notifyListeners();
		getTimoeutKeepAliveReceiveTimeout_3_req().notifyListeners();
		getTimeoutKapcsolodik_2_req().notifyListeners();
		getTimeoutZarva_0_req().notifyListeners();
		getTimeoutKeepAliveSendTimeout_1_req().notifyListeners();
		getTimeoutKeepAliveReceiveTimeout_4_req().notifyListeners();
		getTimeoutKapcsolodik_3_req().notifyListeners();
		getTimeoutKeepAliveSendTimeout_0_req().notifyListeners();
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

	
	/**  Getter for component instances, e.g., enabling to check their states. */
	public Orion_Master_SM_ext getMaster() {
		return master;
	}
	
	public Orion_Slave_SM_ext getSlave() {
		return slave;
	}
	
	public Status_SM getConnStatus() {
		return connStatus;
	}
	
	// Environmental Component instances
	
	
}
