		package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_benchmark_system_32;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer_adapter.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm_ext.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_env_param.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_adapter.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class OrionBenchMarkSystem_Param_32 implements OrionBenchMarkSystem_Param_32Interface {
			// Component instances
			private Orion_Environment_Param subSystem1_;
			private Orion_Environment_Param subSystem2_;
			private Orion_Environment_Param subSystem3_;
			private Orion_Environment_Param subSystem4_;
			private Orion_Environment_Param subSystem5_;
			private Orion_Environment_Param subSystem6_;
			private Orion_Environment_Param subSystem7_;
			private Orion_Environment_Param subSystem8_;
			private Orion_Environment_Param subSystem9_;
			private Orion_Environment_Param subSystem10;
			private Orion_Environment_Param subSystem11;
			private Orion_Environment_Param subSystem12;
			private Orion_Environment_Param subSystem13;
			private Orion_Environment_Param subSystem14;
			private Orion_Environment_Param subSystem15;
			private Orion_Environment_Param subSystem16;
			private Summarizer_Adapter summarizer;
			// Environmental Component instances
			// Port instances
			private SystemConnStatus systemConnStatus = new SystemConnStatus();
			// Channel instances
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem6_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem1_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem3_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem8_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem15;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem12;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem4_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem10;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem16;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem5_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem9_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem2_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem7_;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem14;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem11;
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem13;
			
			// Fields representing parameters
			private final double timerMean;
			
			protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
			protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
			
			public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
				envComponents.add(component);
				envMap.put(name,component);
			}
			
			public boolean isEventQueueEmpty(){
				return  subSystem1_.isEventQueueEmpty()  &&  subSystem2_.isEventQueueEmpty()  &&  subSystem3_.isEventQueueEmpty()  &&  subSystem4_.isEventQueueEmpty()  &&  subSystem5_.isEventQueueEmpty()  &&  subSystem6_.isEventQueueEmpty()  &&  subSystem7_.isEventQueueEmpty()  &&  subSystem8_.isEventQueueEmpty()  &&  subSystem9_.isEventQueueEmpty()  &&  subSystem10.isEventQueueEmpty()  &&  subSystem11.isEventQueueEmpty()  &&  subSystem12.isEventQueueEmpty()  &&  subSystem13.isEventQueueEmpty()  &&  subSystem14.isEventQueueEmpty()  &&  subSystem15.isEventQueueEmpty()  &&  subSystem16.isEventQueueEmpty()  &&  summarizer.isEventQueueEmpty()     ;
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
					subSystem9_.schedule();
					subSystem10.schedule();
					subSystem11.schedule();
					subSystem12.schedule();
					subSystem13.schedule();
					subSystem14.schedule();
					subSystem15.schedule();
					subSystem16.schedule();
					summarizer.schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in OrionBenchMarkSystem_Param_32! -----------");
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
					subSystem9_.schedule();
					subSystem10.schedule();
					subSystem11.schedule();
					subSystem12.schedule();
					subSystem13.schedule();
					subSystem14.schedule();
					subSystem15.schedule();
					subSystem16.schedule();
					summarizer.schedule();
					System.out.println(getInQueue());
					
				}
			}
			
			public OrionBenchMarkSystem_Param_32(double timerMean) {
				this.timerMean = timerMean;
				subSystem1_ = new Orion_Environment_Param(timerMean);
				subSystem2_ = new Orion_Environment_Param(timerMean);
				subSystem3_ = new Orion_Environment_Param(timerMean);
				subSystem4_ = new Orion_Environment_Param(timerMean);
				subSystem5_ = new Orion_Environment_Param(timerMean);
				subSystem6_ = new Orion_Environment_Param(timerMean);
				subSystem7_ = new Orion_Environment_Param(timerMean);
				subSystem8_ = new Orion_Environment_Param(timerMean);
				subSystem9_ = new Orion_Environment_Param(timerMean);
				subSystem10 = new Orion_Environment_Param(timerMean);
				subSystem11 = new Orion_Environment_Param(timerMean);
				subSystem12 = new Orion_Environment_Param(timerMean);
				subSystem13 = new Orion_Environment_Param(timerMean);
				subSystem14 = new Orion_Environment_Param(timerMean);
				subSystem15 = new Orion_Environment_Param(timerMean);
				subSystem16 = new Orion_Environment_Param(timerMean);
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
				subSystem9_.handleBeforeReset();
				subSystem10.handleBeforeReset();
				subSystem11.handleBeforeReset();
				subSystem12.handleBeforeReset();
				subSystem13.handleBeforeReset();
				subSystem14.handleBeforeReset();
				subSystem15.handleBeforeReset();
				subSystem16.handleBeforeReset();
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
				subSystem9_.resetVariables();
				subSystem10.resetVariables();
				subSystem11.resetVariables();
				subSystem12.resetVariables();
				subSystem13.resetVariables();
				subSystem14.resetVariables();
				subSystem15.resetVariables();
				subSystem16.resetVariables();
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
				subSystem9_.resetStateConfigurations();
				subSystem10.resetStateConfigurations();
				subSystem11.resetStateConfigurations();
				subSystem12.resetStateConfigurations();
				subSystem13.resetStateConfigurations();
				subSystem14.resetStateConfigurations();
				subSystem15.resetStateConfigurations();
				subSystem16.resetStateConfigurations();
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
				subSystem9_.raiseEntryEvents();
				subSystem10.raiseEntryEvents();
				subSystem11.raiseEntryEvents();
				subSystem12.raiseEntryEvents();
				subSystem13.raiseEntryEvents();
				subSystem14.raiseEntryEvents();
				subSystem15.raiseEntryEvents();
				subSystem16.raiseEntryEvents();
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
				subSystem9_.handleAfterReset();
				subSystem10.handleAfterReset();
				subSystem11.handleAfterReset();
				subSystem12.handleAfterReset();
				subSystem13.handleAfterReset();
				subSystem14.handleAfterReset();
				subSystem15.handleAfterReset();
				subSystem16.handleAfterReset();
				summarizer.handleAfterReset();
				
				//subSystem1_.schedule();
				//subSystem2_.schedule();
				//subSystem3_.schedule();
				//subSystem4_.schedule();
				//subSystem5_.schedule();
				//subSystem6_.schedule();
				//subSystem7_.schedule();
				//subSystem8_.schedule();
				//subSystem9_.schedule();
				//subSystem10.schedule();
				//subSystem11.schedule();
				//subSystem12.schedule();
				//subSystem13.schedule();
				//subSystem14.schedule();
				//subSystem15.schedule();
				//subSystem16.schedule();
				//summarizer.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				channelSystemConnStatusOfSubSystem3_ = new ConnectionStateChannel(subSystem3_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem3_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem8_ = new ConnectionStateChannel(subSystem8_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem8_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem5_ = new ConnectionStateChannel(subSystem5_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem5_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem6_ = new ConnectionStateChannel(subSystem6_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem6_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem4_ = new ConnectionStateChannel(subSystem4_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem4_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem2_ = new ConnectionStateChannel(subSystem2_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem2_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem16 = new ConnectionStateChannel(subSystem16.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem16.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem7_ = new ConnectionStateChannel(subSystem7_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem7_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem13 = new ConnectionStateChannel(subSystem13.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem13.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem11 = new ConnectionStateChannel(subSystem11.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem11.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem14 = new ConnectionStateChannel(subSystem14.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem14.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem12 = new ConnectionStateChannel(subSystem12.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem12.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem9_ = new ConnectionStateChannel(subSystem9_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem9_.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem10 = new ConnectionStateChannel(subSystem10.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem10.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem15 = new ConnectionStateChannel(subSystem15.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem15.registerPort(summarizer.getInPort());
				channelSystemConnStatusOfSubSystem1_ = new ConnectionStateChannel(subSystem1_.getSystemConnStatus());
				channelSystemConnStatusOfSubSystem1_.registerPort(summarizer.getInPort());
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
				subSystem9_.start();
				subSystem10.start();
				subSystem11.start();
				subSystem12.start();
				subSystem13.start();
				subSystem14.start();
				subSystem15.start();
				subSystem16.start();
				summarizer.start();
			}
			
			public boolean isWaiting() {
				return subSystem1_.isWaiting() && subSystem2_.isWaiting() && subSystem3_.isWaiting() && subSystem4_.isWaiting() && subSystem5_.isWaiting() && subSystem6_.isWaiting() && subSystem7_.isWaiting() && subSystem8_.isWaiting() && subSystem9_.isWaiting() && subSystem10.isWaiting() && subSystem11.isWaiting() && subSystem12.isWaiting() && subSystem13.isWaiting() && subSystem14.isWaiting() && subSystem15.isWaiting() && subSystem16.isWaiting() && summarizer.isWaiting()
					;
			}
			
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			public Orion_Environment_Param getSubSystem1_() {
				return subSystem1_;
			}
			
			public Orion_Environment_Param getSubSystem2_() {
				return subSystem2_;
			}
			
			public Orion_Environment_Param getSubSystem3_() {
				return subSystem3_;
			}
			
			public Orion_Environment_Param getSubSystem4_() {
				return subSystem4_;
			}
			
			public Orion_Environment_Param getSubSystem5_() {
				return subSystem5_;
			}
			
			public Orion_Environment_Param getSubSystem6_() {
				return subSystem6_;
			}
			
			public Orion_Environment_Param getSubSystem7_() {
				return subSystem7_;
			}
			
			public Orion_Environment_Param getSubSystem8_() {
				return subSystem8_;
			}
			
			public Orion_Environment_Param getSubSystem9_() {
				return subSystem9_;
			}
			
			public Orion_Environment_Param getSubSystem10() {
				return subSystem10;
			}
			
			public Orion_Environment_Param getSubSystem11() {
				return subSystem11;
			}
			
			public Orion_Environment_Param getSubSystem12() {
				return subSystem12;
			}
			
			public Orion_Environment_Param getSubSystem13() {
				return subSystem13;
			}
			
			public Orion_Environment_Param getSubSystem14() {
				return subSystem14;
			}
			
			public Orion_Environment_Param getSubSystem15() {
				return subSystem15;
			}
			
			public Orion_Environment_Param getSubSystem16() {
				return subSystem16;
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
					
					str=str+"\n    subSystem9_ : "+subSystem9_.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem10 : "+subSystem10.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem11 : "+subSystem11.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem12 : "+subSystem12.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem13 : "+subSystem13.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem14 : "+subSystem14.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem15 : "+subSystem15.getInQueue().replace("    ","      ");
					
					str=str+"\n    subSystem16 : "+subSystem16.getInQueue().replace("    ","      ");
					
					str=str+"\n    summarizer : "+summarizer.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
