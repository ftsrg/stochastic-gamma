		package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_benchmark_system_2;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm_ext.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_adapter.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer_adapter.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_env_param.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class OrionBenchMarkSystem_Param_2 implements OrionBenchMarkSystem_Param_2Interface {
			// Component instances
			private Orion_Environment_Param subSystem1_;
			private Summarizer_Adapter summarizer;
			// Environmental Component instances
			// Port instances
			private SystemConnStatus systemConnStatus = new SystemConnStatus();
			// Channel instances
			private ConnectionStateChannelInterface channelSystemConnStatusOfSubSystem1_;
			
			// Fields representing parameters
			private final double timerMean;
			
			protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
			protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
			
			public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
				envComponents.add(component);
				envMap.put(name,component);
			}
			
			public boolean isEventQueueEmpty(){
				return  subSystem1_.isEventQueueEmpty()  &&  summarizer.isEventQueueEmpty()     ;
			}
			
			private final int MAX_SCHEDULE=15;
			
			public void schedule(){
				int cntr=0;
				do{
					subSystem1_.schedule();
					summarizer.schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in OrionBenchMarkSystem_Param_2! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					subSystem1_.schedule();
					summarizer.schedule();
					System.out.println(getInQueue());
					
				}
			}
			
			public OrionBenchMarkSystem_Param_2(double timerMean) {
				this.timerMean = timerMean;
				subSystem1_ = new Orion_Environment_Param(timerMean);
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
				summarizer.handleBeforeReset();
								//
			}

			public void resetVariables() {
				subSystem1_.resetVariables();
				summarizer.resetVariables();
			}
			
			public void resetStateConfigurations() {
				subSystem1_.resetStateConfigurations();
				summarizer.resetStateConfigurations();
			}
			
			public void raiseEntryEvents() {
				subSystem1_.raiseEntryEvents();
				summarizer.raiseEntryEvents();
			}

			public void handleAfterReset() {
				subSystem1_.handleAfterReset();
				summarizer.handleAfterReset();
				
				//subSystem1_.schedule();
				//summarizer.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
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
				summarizer.start();
			}
			
			public boolean isWaiting() {
				return subSystem1_.isWaiting() && summarizer.isWaiting()
					;
			}
			
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			public Orion_Environment_Param getSubSystem1_() {
				return subSystem1_;
			}
			
			public Summarizer_Adapter getSummarizer() {
				return summarizer;
			}
			

			public String getInQueue(){
				if (!isEventQueueEmpty()){
					String str="Input of components [";
					str=str+"\n    subSystem1_ : "+subSystem1_.getInQueue().replace("    ","      ");
					
					str=str+"\n    summarizer : "+summarizer.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
