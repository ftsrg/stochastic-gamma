		package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_env_param;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_adapter.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm_ext.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm.*;
		import hu.bme.mit.gamma.stochastic.casestudy.orion.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.stochastic.casestudy.orion.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class Orion_Environment_Param implements Orion_Environment_ParamInterface {
			// Component instances
			private Orion_Adapter orionSystem;
			// Environmental Component instances
			// Port instances
			private SystemConnStatus systemConnStatus = new SystemConnStatus();
			// Channel instances
			private SoftwareTimerChannelInterface channelTimeoutKapcsolodik_3OfTimerKapcsolodik_3;
			private SoftwareTimerChannelInterface channelTimeoutKeepAliveSendTimeout_1OfTimerKeepAliveSendTimeout_1;
			private SoftwareTimerChannelInterface channelTimeoutKapcsolodik_2OfTimerKapcsolodik_2;
			private SoftwareTimerChannelInterface channelTimeoutKeepAliveReceiveTimeout_4OfTimerKeepAliveReceiveTimeout_4;
			private SoftwareTimerChannelInterface channelTimeoutKeepAliveSendTimeout_0OfTimerKeepAliveSendTimeout_0;
			private SoftwareTimerChannelInterface channelTimeoutZarva_0OfTimerZarva_0;
			private SoftwareTimerChannelInterface channelTimoeutKeepAliveReceiveTimeout_3OfTimerKeepAliveReceiveTimeout_3;
			
			// Fields representing parameters
			private final double timerMean;
			
			protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
			protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
			
			public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
				envComponents.add(component);
				envMap.put(name,component);
			}
			
			public boolean isEventQueueEmpty(){
				return  orionSystem.isEventQueueEmpty()   &&   true &&   true &&   true &&   true &&   true &&   true &&   true  ;
			}
			
			private final int MAX_SCHEDULE=15;
			
			public void schedule(){
				int cntr=0;
				do{
					orionSystem.schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in Orion_Environment_Param! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					orionSystem.schedule();
					System.out.println(getInQueue());
					if (!true){
						System.out.println("    elementary stochastic component TimerKeepAliveReceiveTimeout_3 is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component TimerKapcsolodik_2 is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component TimerZarva_0 is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component TimerKeepAliveSendTimeout_1 is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component TimerKeepAliveReceiveTimeout_4 is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component TimerKapcsolodik_3 is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component TimerKeepAliveSendTimeout_0 is not empty");
					}
					
				}
			}
			
			public Orion_Environment_Param(double timerMean) {
				this.timerMean = timerMean;
				orionSystem = new Orion_Adapter();
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
				orionSystem.handleBeforeReset();
								//
			}

			public void resetVariables() {
				orionSystem.resetVariables();
			}
			
			public void resetStateConfigurations() {
				orionSystem.resetStateConfigurations();
			}
			
			public void raiseEntryEvents() {
				orionSystem.raiseEntryEvents();
			}

			public void handleAfterReset() {
				orionSystem.handleAfterReset();
				
				//orionSystem.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				// Registration of broadcast channels
			}
			
			// Inner classes representing Ports
			public class SystemConnStatus implements ConnectionStateInterface.Provided {
				
				
				List<ConnectionStateInterface.Listener.Provided> registeredListeners=new ArrayList<ConnectionStateInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedConn() {
					return orionSystem.getSystemStatus().isRaisedConn();
				}
				@Override
				public boolean isRaisedDisconn() {
					return orionSystem.getSystemStatus().isRaisedDisconn();
				}
				
				
				@Override
				public void registerListener(ConnectionStateInterface.Listener.Provided listener) {
					orionSystem.getSystemStatus().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<ConnectionStateInterface.Listener.Provided> getRegisteredListeners() {
					List<ConnectionStateInterface.Listener.Provided> registeredListeners=new ArrayList<ConnectionStateInterface.Listener.Provided>();
					registeredListeners.addAll(orionSystem.getSystemStatus().getRegisteredListeners());
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
				orionSystem.start();
			}
			
			public boolean isWaiting() {
				return orionSystem.isWaiting()
					;
			}
			
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			public Orion_Adapter getOrionSystem() {
				return orionSystem;
			}
			

			public String getInQueue(){
				if (!isEventQueueEmpty()){
					String str="Input of components [";
					str=str+"\n    orionSystem : "+orionSystem.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
