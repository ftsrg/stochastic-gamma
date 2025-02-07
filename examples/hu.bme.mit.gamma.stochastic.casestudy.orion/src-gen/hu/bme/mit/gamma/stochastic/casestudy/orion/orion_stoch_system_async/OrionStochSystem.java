		package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system_async;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm_async.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_adapter.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm_ext.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_adapter.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class OrionStochSystem implements OrionStochSystemInterface {
			// Component instances
			private Orion_Master_Adapter master;
			private Orion_Slave_Adapter slave;
			private Status_SM connStatus;
			// Environmental Component instances
			// Port instances
			private TimoeutKeepAliveReceiveTimeout_3 timoeutKeepAliveReceiveTimeout_3 = new TimoeutKeepAliveReceiveTimeout_3();
			private TimeoutKapcsolodik_2 timeoutKapcsolodik_2 = new TimeoutKapcsolodik_2();
			private TimeoutZarva_0 timeoutZarva_0 = new TimeoutZarva_0();
			private TimeoutKeepAliveSendTimeout_1 timeoutKeepAliveSendTimeout_1 = new TimeoutKeepAliveSendTimeout_1();
			private TimeoutKeepAliveReceiveTimeout_4 timeoutKeepAliveReceiveTimeout_4 = new TimeoutKeepAliveReceiveTimeout_4();
			private TimeoutKapcsolodik_3 timeoutKapcsolodik_3 = new TimeoutKapcsolodik_3();
			private TimeoutKeepAliveSendTimeout_0 timeoutKeepAliveSendTimeout_0 = new TimeoutKeepAliveSendTimeout_0();
			private TimoeutKeepAliveReceiveTimeout_3_req timoeutKeepAliveReceiveTimeout_3_req = new TimoeutKeepAliveReceiveTimeout_3_req();
			private TimeoutKapcsolodik_2_req timeoutKapcsolodik_2_req = new TimeoutKapcsolodik_2_req();
			private TimeoutZarva_0_req timeoutZarva_0_req = new TimeoutZarva_0_req();
			private TimeoutKeepAliveSendTimeout_1_req timeoutKeepAliveSendTimeout_1_req = new TimeoutKeepAliveSendTimeout_1_req();
			private TimeoutKeepAliveReceiveTimeout_4_req timeoutKeepAliveReceiveTimeout_4_req = new TimeoutKeepAliveReceiveTimeout_4_req();
			private TimeoutKapcsolodik_3_req timeoutKapcsolodik_3_req = new TimeoutKapcsolodik_3_req();
			private TimeoutKeepAliveSendTimeout_0_req timeoutKeepAliveSendTimeout_0_req = new TimeoutKeepAliveSendTimeout_0_req();
			private SystemStatus systemStatus = new SystemStatus();
			// Channel instances
			private ConnectionStateChannelInterface channelStatusOfSlave;
			private StateMachine_Interface_For_OrionChannelInterface channelOutputOfMasterSlaveChannel;
			private ConnectionStateChannelInterface channelStatusOfMaster;
			private StateMachine_Interface_For_OrionChannelInterface channelOutputOfSlaveMasterChannel;
			private StateMachine_Interface_For_OrionChannelInterface channelSend_StateMachine_PortOfMaster;
			private StateMachine_Interface_For_OrionChannelInterface channelSend_StateMachine_PortOfSlave;
			
			
			protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
			protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
			
			public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
				envComponents.add(component);
				envMap.put(name,component);
			}
			
			public boolean isEventQueueEmpty(){
				return  master.isEventQueueEmpty()  &&  slave.isEventQueueEmpty()  &&  connStatus.isEventQueueEmpty()   &&   envMap.get("masterSlaveChannel").isEventQueueEmpty() &&   envMap.get("slaveMasterChannel").isEventQueueEmpty() &&   true &&   true &&   true &&   true &&   true &&   true &&   true  ;
			}
			
			private final int MAX_SCHEDULE=15;
			
			public void schedule(){
				int cntr=0;
				do{
					master.schedule();
					slave.schedule();
					connStatus.schedule();
					envMap.get("masterSlaveChannel").schedule();
					envMap.get("slaveMasterChannel").schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in OrionStochSystem! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					master.schedule();
					slave.schedule();
					connStatus.schedule();
					envMap.get("masterSlaveChannel").schedule();
					envMap.get("slaveMasterChannel").schedule();
					System.out.println(getInQueue());
					if (!envMap.get("masterSlaveChannel").isEventQueueEmpty()){
						System.out.println("    elementary stochastic component masterSlaveChannel is not empty");
					}
					if (!envMap.get("slaveMasterChannel").isEventQueueEmpty()){
						System.out.println("    elementary stochastic component slaveMasterChannel is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component DelayKeepAliveReceiveTimeout_3 is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component DelayKapcsolodik_2 is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component DelayZarva_0 is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component DelayKeepAliveSendTimeout_1 is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component DelayKeepAliveReceiveTimeout_4 is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component DelayKapcsolodik_3 is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component DelayKeepAliveSendTimeout_0 is not empty");
					}
					
				}
			}
			
			public OrionStochSystem() {
				master = new Orion_Master_Adapter();
				slave = new Orion_Slave_Adapter();
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
								//
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
				
				//master.schedule();
				//slave.schedule();
				//connStatus.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				channelStatusOfSlave = new ConnectionStateChannel(slave.getStatus());
				channelStatusOfSlave.registerPort(connStatus.getSlaveStatus());
				channelStatusOfMaster = new ConnectionStateChannel(master.getStatus());
				channelStatusOfMaster.registerPort(connStatus.getMasterStatus());
				// Registration of broadcast channels
			}
			
			// Inner classes representing Ports
			public class TimoeutKeepAliveReceiveTimeout_3 implements SoftwareTimerInterface.Required {
				
				
				List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
				
				
				@Override
				public void raiseNewEvent() {
					master.getTimoeutKeepAliveReceiveTimeout_3().raiseNewEvent();
				}
				
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
					master.getTimoeutKeepAliveReceiveTimeout_3().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
					registeredListeners.addAll(master.getTimoeutKeepAliveReceiveTimeout_3().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimoeutKeepAliveReceiveTimeout_3 getTimoeutKeepAliveReceiveTimeout_3() {
				return timoeutKeepAliveReceiveTimeout_3;
			}
			
			public class TimeoutKapcsolodik_2 implements SoftwareTimerInterface.Required {
				
				
				List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
				
				
				@Override
				public void raiseNewEvent() {
					master.getTimeoutKapcsolodik_2().raiseNewEvent();
				}
				
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
					master.getTimeoutKapcsolodik_2().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
					registeredListeners.addAll(master.getTimeoutKapcsolodik_2().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimeoutKapcsolodik_2 getTimeoutKapcsolodik_2() {
				return timeoutKapcsolodik_2;
			}
			
			public class TimeoutZarva_0 implements SoftwareTimerInterface.Required {
				
				
				List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
				
				
				@Override
				public void raiseNewEvent() {
					master.getTimeoutZarva_0().raiseNewEvent();
				}
				
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
					master.getTimeoutZarva_0().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
					registeredListeners.addAll(master.getTimeoutZarva_0().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimeoutZarva_0 getTimeoutZarva_0() {
				return timeoutZarva_0;
			}
			
			public class TimeoutKeepAliveSendTimeout_1 implements SoftwareTimerInterface.Required {
				
				
				List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
				
				
				@Override
				public void raiseNewEvent() {
					master.getTimeoutKeepAliveSendTimeout_1().raiseNewEvent();
				}
				
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
					master.getTimeoutKeepAliveSendTimeout_1().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
					registeredListeners.addAll(master.getTimeoutKeepAliveSendTimeout_1().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimeoutKeepAliveSendTimeout_1 getTimeoutKeepAliveSendTimeout_1() {
				return timeoutKeepAliveSendTimeout_1;
			}
			
			public class TimeoutKeepAliveReceiveTimeout_4 implements SoftwareTimerInterface.Required {
				
				
				List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
				
				
				@Override
				public void raiseNewEvent() {
					slave.getTimeoutKeepAliveReceiveTimeout_4().raiseNewEvent();
				}
				
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
					slave.getTimeoutKeepAliveReceiveTimeout_4().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
					registeredListeners.addAll(slave.getTimeoutKeepAliveReceiveTimeout_4().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimeoutKeepAliveReceiveTimeout_4 getTimeoutKeepAliveReceiveTimeout_4() {
				return timeoutKeepAliveReceiveTimeout_4;
			}
			
			public class TimeoutKapcsolodik_3 implements SoftwareTimerInterface.Required {
				
				
				List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
				
				
				@Override
				public void raiseNewEvent() {
					slave.getTimeoutKapcsolodik_3().raiseNewEvent();
				}
				
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
					slave.getTimeoutKapcsolodik_3().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
					registeredListeners.addAll(slave.getTimeoutKapcsolodik_3().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimeoutKapcsolodik_3 getTimeoutKapcsolodik_3() {
				return timeoutKapcsolodik_3;
			}
			
			public class TimeoutKeepAliveSendTimeout_0 implements SoftwareTimerInterface.Required {
				
				
				List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
				
				
				@Override
				public void raiseNewEvent() {
					slave.getTimeoutKeepAliveSendTimeout_0().raiseNewEvent();
				}
				
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Required listener) {
					slave.getTimeoutKeepAliveSendTimeout_0().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Required> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Required> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Required>();
					registeredListeners.addAll(slave.getTimeoutKeepAliveSendTimeout_0().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimeoutKeepAliveSendTimeout_0 getTimeoutKeepAliveSendTimeout_0() {
				return timeoutKeepAliveSendTimeout_0;
			}
			
			public class TimoeutKeepAliveReceiveTimeout_3_req implements SoftwareTimerInterface.Provided {
				
				
				List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedNewEvent() {
					return master.getTimoeutKeepAliveReceiveTimeout_3_req().isRaisedNewEvent();
				}
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
					master.getTimoeutKeepAliveReceiveTimeout_3_req().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
					registeredListeners.addAll(master.getTimoeutKeepAliveReceiveTimeout_3_req().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimoeutKeepAliveReceiveTimeout_3_req getTimoeutKeepAliveReceiveTimeout_3_req() {
				return timoeutKeepAliveReceiveTimeout_3_req;
			}
			
			public class TimeoutKapcsolodik_2_req implements SoftwareTimerInterface.Provided {
				
				
				List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedNewEvent() {
					return master.getTimeoutKapcsolodik_2_req().isRaisedNewEvent();
				}
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
					master.getTimeoutKapcsolodik_2_req().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
					registeredListeners.addAll(master.getTimeoutKapcsolodik_2_req().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimeoutKapcsolodik_2_req getTimeoutKapcsolodik_2_req() {
				return timeoutKapcsolodik_2_req;
			}
			
			public class TimeoutZarva_0_req implements SoftwareTimerInterface.Provided {
				
				
				List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedNewEvent() {
					return master.getTimeoutZarva_0_req().isRaisedNewEvent();
				}
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
					master.getTimeoutZarva_0_req().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
					registeredListeners.addAll(master.getTimeoutZarva_0_req().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimeoutZarva_0_req getTimeoutZarva_0_req() {
				return timeoutZarva_0_req;
			}
			
			public class TimeoutKeepAliveSendTimeout_1_req implements SoftwareTimerInterface.Provided {
				
				
				List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedNewEvent() {
					return master.getTimeoutKeepAliveSendTimeout_1_req().isRaisedNewEvent();
				}
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
					master.getTimeoutKeepAliveSendTimeout_1_req().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
					registeredListeners.addAll(master.getTimeoutKeepAliveSendTimeout_1_req().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimeoutKeepAliveSendTimeout_1_req getTimeoutKeepAliveSendTimeout_1_req() {
				return timeoutKeepAliveSendTimeout_1_req;
			}
			
			public class TimeoutKeepAliveReceiveTimeout_4_req implements SoftwareTimerInterface.Provided {
				
				
				List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedNewEvent() {
					return slave.getTimeoutKeepAliveReceiveTimeout_4_req().isRaisedNewEvent();
				}
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
					slave.getTimeoutKeepAliveReceiveTimeout_4_req().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
					registeredListeners.addAll(slave.getTimeoutKeepAliveReceiveTimeout_4_req().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimeoutKeepAliveReceiveTimeout_4_req getTimeoutKeepAliveReceiveTimeout_4_req() {
				return timeoutKeepAliveReceiveTimeout_4_req;
			}
			
			public class TimeoutKapcsolodik_3_req implements SoftwareTimerInterface.Provided {
				
				
				List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedNewEvent() {
					return slave.getTimeoutKapcsolodik_3_req().isRaisedNewEvent();
				}
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
					slave.getTimeoutKapcsolodik_3_req().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
					registeredListeners.addAll(slave.getTimeoutKapcsolodik_3_req().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimeoutKapcsolodik_3_req getTimeoutKapcsolodik_3_req() {
				return timeoutKapcsolodik_3_req;
			}
			
			public class TimeoutKeepAliveSendTimeout_0_req implements SoftwareTimerInterface.Provided {
				
				
				List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedNewEvent() {
					return slave.getTimeoutKeepAliveSendTimeout_0_req().isRaisedNewEvent();
				}
				
				
				@Override
				public void registerListener(SoftwareTimerInterface.Listener.Provided listener) {
					slave.getTimeoutKeepAliveSendTimeout_0_req().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SoftwareTimerInterface.Listener.Provided> getRegisteredListeners() {
					List<SoftwareTimerInterface.Listener.Provided> registeredListeners=new ArrayList<SoftwareTimerInterface.Listener.Provided>();
					registeredListeners.addAll(slave.getTimeoutKeepAliveSendTimeout_0_req().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public TimeoutKeepAliveSendTimeout_0_req getTimeoutKeepAliveSendTimeout_0_req() {
				return timeoutKeepAliveSendTimeout_0_req;
			}
			
			public class SystemStatus implements ConnectionStateInterface.Provided {
				
				
				List<ConnectionStateInterface.Listener.Provided> registeredListeners=new ArrayList<ConnectionStateInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedConn() {
					return connStatus.getSystemStatus().isRaisedConn();
				}
				@Override
				public boolean isRaisedDisconn() {
					return connStatus.getSystemStatus().isRaisedDisconn();
				}
				
				
				@Override
				public void registerListener(ConnectionStateInterface.Listener.Provided listener) {
					connStatus.getSystemStatus().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<ConnectionStateInterface.Listener.Provided> getRegisteredListeners() {
					List<ConnectionStateInterface.Listener.Provided> registeredListeners=new ArrayList<ConnectionStateInterface.Listener.Provided>();
					registeredListeners.addAll(connStatus.getSystemStatus().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public SystemStatus getSystemStatus() {
				return systemStatus;
			}
			
			/** Starts the running of the asynchronous component. */
			@Override
			public void start() {
				master.start();
				slave.start();
				connStatus.start();
			}
			
			public boolean isWaiting() {
				return master.isWaiting() && slave.isWaiting() && connStatus.isWaiting()
					;
			}
			
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			public Orion_Master_Adapter getMaster() {
				return master;
			}
			
			public Orion_Slave_Adapter getSlave() {
				return slave;
			}
			
			public Status_SM getConnStatus() {
				return connStatus;
			}
			

			public String getInQueue(){
				if (!isEventQueueEmpty()){
					String str="Input of components [";
					str=str+"\n    master : "+master.getInQueue().replace("    ","      ");
					
					str=str+"\n    slave : "+slave.getInQueue().replace("    ","      ");
					
					str=str+"\n    connStatus : "+connStatus.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
