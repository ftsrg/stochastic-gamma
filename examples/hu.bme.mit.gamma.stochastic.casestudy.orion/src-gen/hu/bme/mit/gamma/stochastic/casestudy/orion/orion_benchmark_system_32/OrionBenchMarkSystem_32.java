		package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_benchmark_system_32;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_adapter.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm_ext.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_env.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer_adapter.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class OrionBenchMarkSystem_32 implements OrionBenchMarkSystem_32Interface {
			// Component instances
			private Orion_Environment subSystem1;
			private Orion_Environment subSystem2;
			private Orion_Environment subSystem3;
			private Orion_Environment subSystem4;
			private Orion_Environment subSystem5;
			private Orion_Environment subSystem6;
			private Orion_Environment subSystem7;
			private Orion_Environment subSystem8;
			private Orion_Environment subSystem9;
			private Orion_Environment subSystem10;
			private Orion_Environment subSystem11;
			private Orion_Environment subSystem12;
			private Orion_Environment subSystem13;
			private Orion_Environment subSystem14;
			private Orion_Environment subSystem15;
			private Orion_Environment subSystem16;
			private Orion_Environment subSystem17;
			private Orion_Environment subSystem18;
			private Orion_Environment subSystem19;
			private Orion_Environment subSystem20;
			private Orion_Environment subSystem21;
			private Orion_Environment subSystem22;
			private Orion_Environment subSystem23;
			private Orion_Environment subSystem24;
			private Orion_Environment subSystem25;
			private Orion_Environment subSystem26;
			private Orion_Environment subSystem27;
			private Orion_Environment subSystem28;
			private Orion_Environment subSystem29;
			private Orion_Environment subSystem30;
			private Orion_Environment subSystem31;
			private Orion_Environment subSystem32;
			private Summarizer_Adapter summarizer;
			// Environmental Component instances
			// Port instances
			private SystemConnStatus systemConnStatus = new SystemConnStatus();
			// Channel instances
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem20;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem19;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem3;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem9;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem29;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem24;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem15;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem11;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem28;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem27;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem26;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem14;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem2;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem31;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem12;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem4;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem16;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem5;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem1;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem10;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem7;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem8;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem23;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem32;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem22;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem17;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem30;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem18;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem6;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem21;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem13;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem25;
			
			
			protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
			protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
			
			public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
				envComponents.add(component);
				envMap.put(name,component);
			}
			
			public boolean isEventQueueEmpty(){
				return  subSystem1.isEventQueueEmpty()  &&  subSystem2.isEventQueueEmpty()  &&  subSystem3.isEventQueueEmpty()  &&  subSystem4.isEventQueueEmpty()  &&  subSystem5.isEventQueueEmpty()  &&  subSystem6.isEventQueueEmpty()  &&  subSystem7.isEventQueueEmpty()  &&  subSystem8.isEventQueueEmpty()  &&  subSystem9.isEventQueueEmpty()  &&  subSystem10.isEventQueueEmpty()  &&  subSystem11.isEventQueueEmpty()  &&  subSystem12.isEventQueueEmpty()  &&  subSystem13.isEventQueueEmpty()  &&  subSystem14.isEventQueueEmpty()  &&  subSystem15.isEventQueueEmpty()  &&  subSystem16.isEventQueueEmpty()  &&  subSystem17.isEventQueueEmpty()  &&  subSystem18.isEventQueueEmpty()  &&  subSystem19.isEventQueueEmpty()  &&  subSystem20.isEventQueueEmpty()  &&  subSystem21.isEventQueueEmpty()  &&  subSystem22.isEventQueueEmpty()  &&  subSystem23.isEventQueueEmpty()  &&  subSystem24.isEventQueueEmpty()  &&  subSystem25.isEventQueueEmpty()  &&  subSystem26.isEventQueueEmpty()  &&  subSystem27.isEventQueueEmpty()  &&  subSystem28.isEventQueueEmpty()  &&  subSystem29.isEventQueueEmpty()  &&  subSystem30.isEventQueueEmpty()  &&  subSystem31.isEventQueueEmpty()  &&  subSystem32.isEventQueueEmpty()  &&  summarizer.isEventQueueEmpty()     ;
			}
			
			private final int MAX_SCHEDULE=15;
			
			public void schedule(){
				int cntr=0;
				do{
					subSystem1.schedule();
					subSystem2.schedule();
					subSystem3.schedule();
					subSystem4.schedule();
					subSystem5.schedule();
					subSystem6.schedule();
					subSystem7.schedule();
					subSystem8.schedule();
					subSystem9.schedule();
					subSystem10.schedule();
					subSystem11.schedule();
					subSystem12.schedule();
					subSystem13.schedule();
					subSystem14.schedule();
					subSystem15.schedule();
					subSystem16.schedule();
					subSystem17.schedule();
					subSystem18.schedule();
					subSystem19.schedule();
					subSystem20.schedule();
					subSystem21.schedule();
					subSystem22.schedule();
					subSystem23.schedule();
					subSystem24.schedule();
					subSystem25.schedule();
					subSystem26.schedule();
					subSystem27.schedule();
					subSystem28.schedule();
					subSystem29.schedule();
					subSystem30.schedule();
					subSystem31.schedule();
					subSystem32.schedule();
					summarizer.schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in OrionBenchMarkSystem_32! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					subSystem1.schedule();
					subSystem2.schedule();
					subSystem3.schedule();
					subSystem4.schedule();
					subSystem5.schedule();
					subSystem6.schedule();
					subSystem7.schedule();
					subSystem8.schedule();
					subSystem9.schedule();
					subSystem10.schedule();
					subSystem11.schedule();
					subSystem12.schedule();
					subSystem13.schedule();
					subSystem14.schedule();
					subSystem15.schedule();
					subSystem16.schedule();
					subSystem17.schedule();
					subSystem18.schedule();
					subSystem19.schedule();
					subSystem20.schedule();
					subSystem21.schedule();
					subSystem22.schedule();
					subSystem23.schedule();
					subSystem24.schedule();
					subSystem25.schedule();
					subSystem26.schedule();
					subSystem27.schedule();
					subSystem28.schedule();
					subSystem29.schedule();
					subSystem30.schedule();
					subSystem31.schedule();
					subSystem32.schedule();
					summarizer.schedule();
					System.out.println(getInQueue());
					
				}
			}
			
			public OrionBenchMarkSystem_32() {
				subSystem1 = new Orion_Environment();
				subSystem2 = new Orion_Environment();
				subSystem3 = new Orion_Environment();
				subSystem4 = new Orion_Environment();
				subSystem5 = new Orion_Environment();
				subSystem6 = new Orion_Environment();
				subSystem7 = new Orion_Environment();
				subSystem8 = new Orion_Environment();
				subSystem9 = new Orion_Environment();
				subSystem10 = new Orion_Environment();
				subSystem11 = new Orion_Environment();
				subSystem12 = new Orion_Environment();
				subSystem13 = new Orion_Environment();
				subSystem14 = new Orion_Environment();
				subSystem15 = new Orion_Environment();
				subSystem16 = new Orion_Environment();
				subSystem17 = new Orion_Environment();
				subSystem18 = new Orion_Environment();
				subSystem19 = new Orion_Environment();
				subSystem20 = new Orion_Environment();
				subSystem21 = new Orion_Environment();
				subSystem22 = new Orion_Environment();
				subSystem23 = new Orion_Environment();
				subSystem24 = new Orion_Environment();
				subSystem25 = new Orion_Environment();
				subSystem26 = new Orion_Environment();
				subSystem27 = new Orion_Environment();
				subSystem28 = new Orion_Environment();
				subSystem29 = new Orion_Environment();
				subSystem30 = new Orion_Environment();
				subSystem31 = new Orion_Environment();
				subSystem32 = new Orion_Environment();
				summarizer = new Summarizer_Adapter();
				systemConnStatus = new SystemConnStatus(); 
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
				subSystem1.handleBeforeReset();
				subSystem2.handleBeforeReset();
				subSystem3.handleBeforeReset();
				subSystem4.handleBeforeReset();
				subSystem5.handleBeforeReset();
				subSystem6.handleBeforeReset();
				subSystem7.handleBeforeReset();
				subSystem8.handleBeforeReset();
				subSystem9.handleBeforeReset();
				subSystem10.handleBeforeReset();
				subSystem11.handleBeforeReset();
				subSystem12.handleBeforeReset();
				subSystem13.handleBeforeReset();
				subSystem14.handleBeforeReset();
				subSystem15.handleBeforeReset();
				subSystem16.handleBeforeReset();
				subSystem17.handleBeforeReset();
				subSystem18.handleBeforeReset();
				subSystem19.handleBeforeReset();
				subSystem20.handleBeforeReset();
				subSystem21.handleBeforeReset();
				subSystem22.handleBeforeReset();
				subSystem23.handleBeforeReset();
				subSystem24.handleBeforeReset();
				subSystem25.handleBeforeReset();
				subSystem26.handleBeforeReset();
				subSystem27.handleBeforeReset();
				subSystem28.handleBeforeReset();
				subSystem29.handleBeforeReset();
				subSystem30.handleBeforeReset();
				subSystem31.handleBeforeReset();
				subSystem32.handleBeforeReset();
				summarizer.handleBeforeReset();
								//
			}

			public void resetVariables() {
				subSystem1.resetVariables();
				subSystem2.resetVariables();
				subSystem3.resetVariables();
				subSystem4.resetVariables();
				subSystem5.resetVariables();
				subSystem6.resetVariables();
				subSystem7.resetVariables();
				subSystem8.resetVariables();
				subSystem9.resetVariables();
				subSystem10.resetVariables();
				subSystem11.resetVariables();
				subSystem12.resetVariables();
				subSystem13.resetVariables();
				subSystem14.resetVariables();
				subSystem15.resetVariables();
				subSystem16.resetVariables();
				subSystem17.resetVariables();
				subSystem18.resetVariables();
				subSystem19.resetVariables();
				subSystem20.resetVariables();
				subSystem21.resetVariables();
				subSystem22.resetVariables();
				subSystem23.resetVariables();
				subSystem24.resetVariables();
				subSystem25.resetVariables();
				subSystem26.resetVariables();
				subSystem27.resetVariables();
				subSystem28.resetVariables();
				subSystem29.resetVariables();
				subSystem30.resetVariables();
				subSystem31.resetVariables();
				subSystem32.resetVariables();
				summarizer.resetVariables();
			}
			
			public void resetStateConfigurations() {
				subSystem1.resetStateConfigurations();
				subSystem2.resetStateConfigurations();
				subSystem3.resetStateConfigurations();
				subSystem4.resetStateConfigurations();
				subSystem5.resetStateConfigurations();
				subSystem6.resetStateConfigurations();
				subSystem7.resetStateConfigurations();
				subSystem8.resetStateConfigurations();
				subSystem9.resetStateConfigurations();
				subSystem10.resetStateConfigurations();
				subSystem11.resetStateConfigurations();
				subSystem12.resetStateConfigurations();
				subSystem13.resetStateConfigurations();
				subSystem14.resetStateConfigurations();
				subSystem15.resetStateConfigurations();
				subSystem16.resetStateConfigurations();
				subSystem17.resetStateConfigurations();
				subSystem18.resetStateConfigurations();
				subSystem19.resetStateConfigurations();
				subSystem20.resetStateConfigurations();
				subSystem21.resetStateConfigurations();
				subSystem22.resetStateConfigurations();
				subSystem23.resetStateConfigurations();
				subSystem24.resetStateConfigurations();
				subSystem25.resetStateConfigurations();
				subSystem26.resetStateConfigurations();
				subSystem27.resetStateConfigurations();
				subSystem28.resetStateConfigurations();
				subSystem29.resetStateConfigurations();
				subSystem30.resetStateConfigurations();
				subSystem31.resetStateConfigurations();
				subSystem32.resetStateConfigurations();
				summarizer.resetStateConfigurations();
			}
			
			public void raiseEntryEvents() {
				subSystem1.raiseEntryEvents();
				subSystem2.raiseEntryEvents();
				subSystem3.raiseEntryEvents();
				subSystem4.raiseEntryEvents();
				subSystem5.raiseEntryEvents();
				subSystem6.raiseEntryEvents();
				subSystem7.raiseEntryEvents();
				subSystem8.raiseEntryEvents();
				subSystem9.raiseEntryEvents();
				subSystem10.raiseEntryEvents();
				subSystem11.raiseEntryEvents();
				subSystem12.raiseEntryEvents();
				subSystem13.raiseEntryEvents();
				subSystem14.raiseEntryEvents();
				subSystem15.raiseEntryEvents();
				subSystem16.raiseEntryEvents();
				subSystem17.raiseEntryEvents();
				subSystem18.raiseEntryEvents();
				subSystem19.raiseEntryEvents();
				subSystem20.raiseEntryEvents();
				subSystem21.raiseEntryEvents();
				subSystem22.raiseEntryEvents();
				subSystem23.raiseEntryEvents();
				subSystem24.raiseEntryEvents();
				subSystem25.raiseEntryEvents();
				subSystem26.raiseEntryEvents();
				subSystem27.raiseEntryEvents();
				subSystem28.raiseEntryEvents();
				subSystem29.raiseEntryEvents();
				subSystem30.raiseEntryEvents();
				subSystem31.raiseEntryEvents();
				subSystem32.raiseEntryEvents();
				summarizer.raiseEntryEvents();
			}

			public void handleAfterReset() {
				subSystem1.handleAfterReset();
				subSystem2.handleAfterReset();
				subSystem3.handleAfterReset();
				subSystem4.handleAfterReset();
				subSystem5.handleAfterReset();
				subSystem6.handleAfterReset();
				subSystem7.handleAfterReset();
				subSystem8.handleAfterReset();
				subSystem9.handleAfterReset();
				subSystem10.handleAfterReset();
				subSystem11.handleAfterReset();
				subSystem12.handleAfterReset();
				subSystem13.handleAfterReset();
				subSystem14.handleAfterReset();
				subSystem15.handleAfterReset();
				subSystem16.handleAfterReset();
				subSystem17.handleAfterReset();
				subSystem18.handleAfterReset();
				subSystem19.handleAfterReset();
				subSystem20.handleAfterReset();
				subSystem21.handleAfterReset();
				subSystem22.handleAfterReset();
				subSystem23.handleAfterReset();
				subSystem24.handleAfterReset();
				subSystem25.handleAfterReset();
				subSystem26.handleAfterReset();
				subSystem27.handleAfterReset();
				subSystem28.handleAfterReset();
				subSystem29.handleAfterReset();
				subSystem30.handleAfterReset();
				subSystem31.handleAfterReset();
				subSystem32.handleAfterReset();
				summarizer.handleAfterReset();
				
				//subSystem1.schedule();
				//subSystem2.schedule();
				//subSystem3.schedule();
				//subSystem4.schedule();
				//subSystem5.schedule();
				//subSystem6.schedule();
				//subSystem7.schedule();
				//subSystem8.schedule();
				//subSystem9.schedule();
				//subSystem10.schedule();
				//subSystem11.schedule();
				//subSystem12.schedule();
				//subSystem13.schedule();
				//subSystem14.schedule();
				//subSystem15.schedule();
				//subSystem16.schedule();
				//subSystem17.schedule();
				//subSystem18.schedule();
				//subSystem19.schedule();
				//subSystem20.schedule();
				//subSystem21.schedule();
				//subSystem22.schedule();
				//subSystem23.schedule();
				//subSystem24.schedule();
				//subSystem25.schedule();
				//subSystem26.schedule();
				//subSystem27.schedule();
				//subSystem28.schedule();
				//subSystem29.schedule();
				//subSystem30.schedule();
				//subSystem31.schedule();
				//subSystem32.schedule();
				//summarizer.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				channelSystemConnStatusOfSubSystem5 = new ConnectionStateChannel(subSystem5.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem5.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem28 = new ConnectionStateChannel(subSystem28.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem28.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem14 = new ConnectionStateChannel(subSystem14.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem14.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem12 = new ConnectionStateChannel(subSystem12.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem12.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem31 = new ConnectionStateChannel(subSystem31.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem31.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem17 = new ConnectionStateChannel(subSystem17.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem17.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem8 = new ConnectionStateChannel(subSystem8.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem8.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem26 = new ConnectionStateChannel(subSystem26.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem26.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem23 = new ConnectionStateChannel(subSystem23.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem23.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem21 = new ConnectionStateChannel(subSystem21.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem21.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem24 = new ConnectionStateChannel(subSystem24.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem24.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem18 = new ConnectionStateChannel(subSystem18.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem18.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem30 = new ConnectionStateChannel(subSystem30.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem30.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem1 = new ConnectionStateChannel(subSystem1.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem1.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem4 = new ConnectionStateChannel(subSystem4.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem4.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem2 = new ConnectionStateChannel(subSystem2.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem2.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem10 = new ConnectionStateChannel(subSystem10.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem10.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem16 = new ConnectionStateChannel(subSystem16.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem16.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem27 = new ConnectionStateChannel(subSystem27.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem27.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem7 = new ConnectionStateChannel(subSystem7.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem7.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem13 = new ConnectionStateChannel(subSystem13.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem13.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem15 = new ConnectionStateChannel(subSystem15.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem15.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem22 = new ConnectionStateChannel(subSystem22.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem22.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem19 = new ConnectionStateChannel(subSystem19.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem19.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem20 = new ConnectionStateChannel(subSystem20.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem20.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem32 = new ConnectionStateChannel(subSystem32.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem32.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem9 = new ConnectionStateChannel(subSystem9.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem9.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem25 = new ConnectionStateChannel(subSystem25.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem25.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem29 = new ConnectionStateChannel(subSystem29.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem29.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem11 = new ConnectionStateChannel(subSystem11.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem11.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem3 = new ConnectionStateChannel(subSystem3.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem3.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem6 = new ConnectionStateChannel(subSystem6.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem6.registerPort(summarizer.getInPort());
				// Registration of broadcast channels
			}
			
			// Inner classes representing Ports
			public class SystemConnStatus implements ConnectionStateInterface.Provided {
				
				
				List<ConnectionStateInterface.Listener.Provided> registeredListeners=new ArrayList<ConnectionStateInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedConn() {
					return summarizer.getOutPort().isRaisedConn();
				}
				@Override
				public boolean isRaisedDisconn() {
					return summarizer.getOutPort().isRaisedDisconn();
				}
				
				
				@Override
				public void registerListener(ConnectionStateInterface.Listener.Provided listener) {
					summarizer.getOutPort().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<ConnectionStateInterface.Listener.Provided> getRegisteredListeners() {
					List<ConnectionStateInterface.Listener.Provided> registeredListeners=new ArrayList<ConnectionStateInterface.Listener.Provided>();
					registeredListeners.addAll(summarizer.getOutPort().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public SystemConnStatus getSystemConnStatus() {
				return systemConnStatus;
			}
			
			/** Starts the running of the asynchronous component. */
			@Override
			public void start() {
				subSystem1.start();
				subSystem2.start();
				subSystem3.start();
				subSystem4.start();
				subSystem5.start();
				subSystem6.start();
				subSystem7.start();
				subSystem8.start();
				subSystem9.start();
				subSystem10.start();
				subSystem11.start();
				subSystem12.start();
				subSystem13.start();
				subSystem14.start();
				subSystem15.start();
				subSystem16.start();
				subSystem17.start();
				subSystem18.start();
				subSystem19.start();
				subSystem20.start();
				subSystem21.start();
				subSystem22.start();
				subSystem23.start();
				subSystem24.start();
				subSystem25.start();
				subSystem26.start();
				subSystem27.start();
				subSystem28.start();
				subSystem29.start();
				subSystem30.start();
				subSystem31.start();
				subSystem32.start();
				summarizer.start();
			}
			
			public boolean isWaiting() {
				return subSystem1.isWaiting() && subSystem2.isWaiting() && subSystem3.isWaiting() && subSystem4.isWaiting() && subSystem5.isWaiting() && subSystem6.isWaiting() && subSystem7.isWaiting() && subSystem8.isWaiting() && subSystem9.isWaiting() && subSystem10.isWaiting() && subSystem11.isWaiting() && subSystem12.isWaiting() && subSystem13.isWaiting() && subSystem14.isWaiting() && subSystem15.isWaiting() && subSystem16.isWaiting() && subSystem17.isWaiting() && subSystem18.isWaiting() && subSystem19.isWaiting() && subSystem20.isWaiting() && subSystem21.isWaiting() && subSystem22.isWaiting() && subSystem23.isWaiting() && subSystem24.isWaiting() && subSystem25.isWaiting() && subSystem26.isWaiting() && subSystem27.isWaiting() && subSystem28.isWaiting() && subSystem29.isWaiting() && subSystem30.isWaiting() && subSystem31.isWaiting() && subSystem32.isWaiting() && summarizer.isWaiting()
					;
			}
			
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			public Orion_Environment getSubSystem1() {
				return subSystem1;
			}
			
			public Orion_Environment getSubSystem2() {
				return subSystem2;
			}
			
			public Orion_Environment getSubSystem3() {
				return subSystem3;
			}
			
			public Orion_Environment getSubSystem4() {
				return subSystem4;
			}
			
			public Orion_Environment getSubSystem5() {
				return subSystem5;
			}
			
			public Orion_Environment getSubSystem6() {
				return subSystem6;
			}
			
			public Orion_Environment getSubSystem7() {
				return subSystem7;
			}
			
			public Orion_Environment getSubSystem8() {
				return subSystem8;
			}
			
			public Orion_Environment getSubSystem9() {
				return subSystem9;
			}
			
			public Orion_Environment getSubSystem10() {
				return subSystem10;
			}
			
			public Orion_Environment getSubSystem11() {
				return subSystem11;
			}
			
			public Orion_Environment getSubSystem12() {
				return subSystem12;
			}
			
			public Orion_Environment getSubSystem13() {
				return subSystem13;
			}
			
			public Orion_Environment getSubSystem14() {
				return subSystem14;
			}
			
			public Orion_Environment getSubSystem15() {
				return subSystem15;
			}
			
			public Orion_Environment getSubSystem16() {
				return subSystem16;
			}
			
			public Orion_Environment getSubSystem17() {
				return subSystem17;
			}
			
			public Orion_Environment getSubSystem18() {
				return subSystem18;
			}
			
			public Orion_Environment getSubSystem19() {
				return subSystem19;
			}
			
			public Orion_Environment getSubSystem20() {
				return subSystem20;
			}
			
			public Orion_Environment getSubSystem21() {
				return subSystem21;
			}
			
			public Orion_Environment getSubSystem22() {
				return subSystem22;
			}
			
			public Orion_Environment getSubSystem23() {
				return subSystem23;
			}
			
			public Orion_Environment getSubSystem24() {
				return subSystem24;
			}
			
			public Orion_Environment getSubSystem25() {
				return subSystem25;
			}
			
			public Orion_Environment getSubSystem26() {
				return subSystem26;
			}
			
			public Orion_Environment getSubSystem27() {
				return subSystem27;
			}
			
			public Orion_Environment getSubSystem28() {
				return subSystem28;
			}
			
			public Orion_Environment getSubSystem29() {
				return subSystem29;
			}
			
			public Orion_Environment getSubSystem30() {
				return subSystem30;
			}
			
			public Orion_Environment getSubSystem31() {
				return subSystem31;
			}
			
			public Orion_Environment getSubSystem32() {
				return subSystem32;
			}
			
			public Summarizer_Adapter getSummarizer() {
				return summarizer;
			}
			

			public String getInQueue(){
				if (!isEventQueueEmpty()){
					String str="Input of components [";
					str=str+"\n    subSystem1 : "+subSystem1.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem2 : "+subSystem2.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem3 : "+subSystem3.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem4 : "+subSystem4.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem5 : "+subSystem5.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem6 : "+subSystem6.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem7 : "+subSystem7.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem8 : "+subSystem8.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem9 : "+subSystem9.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem10 : "+subSystem10.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem11 : "+subSystem11.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem12 : "+subSystem12.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem13 : "+subSystem13.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem14 : "+subSystem14.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem15 : "+subSystem15.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem16 : "+subSystem16.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem17 : "+subSystem17.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem18 : "+subSystem18.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem19 : "+subSystem19.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem20 : "+subSystem20.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem21 : "+subSystem21.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem22 : "+subSystem22.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem23 : "+subSystem23.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem24 : "+subSystem24.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem25 : "+subSystem25.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem26 : "+subSystem26.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem27 : "+subSystem27.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem28 : "+subSystem28.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem29 : "+subSystem29.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem30 : "+subSystem30.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem31 : "+subSystem31.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem32 : "+subSystem32.getInQueue().replace("    ","      ");
					
					str=str+"\n    summarizer : "+summarizer.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
