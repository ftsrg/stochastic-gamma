		package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_benchmark_system_8;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm_ext.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer_adapter.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_adapter.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_env.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class OrionBenchMarkSystem_8 implements OrionBenchMarkSystem_8Interface {
			// Component instances
			private Orion_Environment subSystem1_;
			private Orion_Environment subSystem2_;
			private Orion_Environment subSystem3_;
			private Orion_Environment subSystem4_;
			private Orion_Environment subSystem5_;
			private Orion_Environment subSystem6_;
			private Orion_Environment subSystem7_;
			private Orion_Environment subSystem8_;
			private Summarizer_Adapter summarizer;
			// Environmental Component instances
			// Port instances
			private SystemConnStatus systemConnStatus = new SystemConnStatus();
			// Channel instances
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem3_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem8_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem7_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem6_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem5_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem2_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem1_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem4_;
			
			
			protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
			protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
			
			public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
				envComponents.add(component);
				envMap.put(name,component);
			}
			
			public boolean isEventQueueEmpty(){
				return  subSystem1_.isEventQueueEmpty()  &&  subSystem2_.isEventQueueEmpty()  &&  subSystem3_.isEventQueueEmpty()  &&  subSystem4_.isEventQueueEmpty()  &&  subSystem5_.isEventQueueEmpty()  &&  subSystem6_.isEventQueueEmpty()  &&  subSystem7_.isEventQueueEmpty()  &&  subSystem8_.isEventQueueEmpty()  &&  summarizer.isEventQueueEmpty()     ;
			}
			
			private final int MAX_SCHEDULE=15;
			
			public void schedule(){
				int cntr=0;
				do{
					subSystem1_.schedule();
					subSystem2_.schedule();
					subSystem3_.schedule();
					subSystem4_.schedule();
					subSystem5_.schedule();
					subSystem6_.schedule();
					subSystem7_.schedule();
					subSystem8_.schedule();
					summarizer.schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in OrionBenchMarkSystem_8! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					subSystem1_.schedule();
					subSystem2_.schedule();
					subSystem3_.schedule();
					subSystem4_.schedule();
					subSystem5_.schedule();
					subSystem6_.schedule();
					subSystem7_.schedule();
					subSystem8_.schedule();
					summarizer.schedule();
					System.out.println(getInQueue());
					
				}
			}
			
			public OrionBenchMarkSystem_8() {
				subSystem1_ = new Orion_Environment();
				subSystem2_ = new Orion_Environment();
				subSystem3_ = new Orion_Environment();
				subSystem4_ = new Orion_Environment();
				subSystem5_ = new Orion_Environment();
				subSystem6_ = new Orion_Environment();
				subSystem7_ = new Orion_Environment();
				subSystem8_ = new Orion_Environment();
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
				subSystem1_.handleBeforeReset();
				subSystem2_.handleBeforeReset();
				subSystem3_.handleBeforeReset();
				subSystem4_.handleBeforeReset();
				subSystem5_.handleBeforeReset();
				subSystem6_.handleBeforeReset();
				subSystem7_.handleBeforeReset();
				subSystem8_.handleBeforeReset();
				summarizer.handleBeforeReset();
								//
			}

			public void resetVariables() {
				subSystem1_.resetVariables();
				subSystem2_.resetVariables();
				subSystem3_.resetVariables();
				subSystem4_.resetVariables();
				subSystem5_.resetVariables();
				subSystem6_.resetVariables();
				subSystem7_.resetVariables();
				subSystem8_.resetVariables();
				summarizer.resetVariables();
			}
			
			public void resetStateConfigurations() {
				subSystem1_.resetStateConfigurations();
				subSystem2_.resetStateConfigurations();
				subSystem3_.resetStateConfigurations();
				subSystem4_.resetStateConfigurations();
				subSystem5_.resetStateConfigurations();
				subSystem6_.resetStateConfigurations();
				subSystem7_.resetStateConfigurations();
				subSystem8_.resetStateConfigurations();
				summarizer.resetStateConfigurations();
			}
			
			public void raiseEntryEvents() {
				subSystem1_.raiseEntryEvents();
				subSystem2_.raiseEntryEvents();
				subSystem3_.raiseEntryEvents();
				subSystem4_.raiseEntryEvents();
				subSystem5_.raiseEntryEvents();
				subSystem6_.raiseEntryEvents();
				subSystem7_.raiseEntryEvents();
				subSystem8_.raiseEntryEvents();
				summarizer.raiseEntryEvents();
			}

			public void handleAfterReset() {
				subSystem1_.handleAfterReset();
				subSystem2_.handleAfterReset();
				subSystem3_.handleAfterReset();
				subSystem4_.handleAfterReset();
				subSystem5_.handleAfterReset();
				subSystem6_.handleAfterReset();
				subSystem7_.handleAfterReset();
				subSystem8_.handleAfterReset();
				summarizer.handleAfterReset();
				
				//subSystem1_.schedule();
				//subSystem2_.schedule();
				//subSystem3_.schedule();
				//subSystem4_.schedule();
				//subSystem5_.schedule();
				//subSystem6_.schedule();
				//subSystem7_.schedule();
				//subSystem8_.schedule();
				//summarizer.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				channelSystemConnStatusOfSubSystem1_ = new ConnectionStateChannel(subSystem1_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem1_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem6_ = new ConnectionStateChannel(subSystem6_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem6_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem5_ = new ConnectionStateChannel(subSystem5_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem5_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem8_ = new ConnectionStateChannel(subSystem8_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem8_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem7_ = new ConnectionStateChannel(subSystem7_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem7_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem4_ = new ConnectionStateChannel(subSystem4_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem4_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem3_ = new ConnectionStateChannel(subSystem3_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem3_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem2_ = new ConnectionStateChannel(subSystem2_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem2_.registerPort(summarizer.getInPort());
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
				subSystem1_.start();
				subSystem2_.start();
				subSystem3_.start();
				subSystem4_.start();
				subSystem5_.start();
				subSystem6_.start();
				subSystem7_.start();
				subSystem8_.start();
				summarizer.start();
			}
			
			public boolean isWaiting() {
				return subSystem1_.isWaiting() && subSystem2_.isWaiting() && subSystem3_.isWaiting() && subSystem4_.isWaiting() && subSystem5_.isWaiting() && subSystem6_.isWaiting() && subSystem7_.isWaiting() && subSystem8_.isWaiting() && summarizer.isWaiting()
					;
			}
			
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			public Orion_Environment getSubSystem1_() {
				return subSystem1_;
			}
			
			public Orion_Environment getSubSystem2_() {
				return subSystem2_;
			}
			
			public Orion_Environment getSubSystem3_() {
				return subSystem3_;
			}
			
			public Orion_Environment getSubSystem4_() {
				return subSystem4_;
			}
			
			public Orion_Environment getSubSystem5_() {
				return subSystem5_;
			}
			
			public Orion_Environment getSubSystem6_() {
				return subSystem6_;
			}
			
			public Orion_Environment getSubSystem7_() {
				return subSystem7_;
			}
			
			public Orion_Environment getSubSystem8_() {
				return subSystem8_;
			}
			
			public Summarizer_Adapter getSummarizer() {
				return summarizer;
			}
			

			public String getInQueue(){
				if (!isEventQueueEmpty()){
					String str="Input of components [";
					str=str+"\n    subSystem1_ : "+subSystem1_.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem2_ : "+subSystem2_.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem3_ : "+subSystem3_.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem4_ : "+subSystem4_.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem5_ : "+subSystem5_.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem6_ : "+subSystem6_.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem7_ : "+subSystem7_.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem8_ : "+subSystem8_.getInQueue().replace("    ","      ");
					
					str=str+"\n    summarizer : "+summarizer.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
