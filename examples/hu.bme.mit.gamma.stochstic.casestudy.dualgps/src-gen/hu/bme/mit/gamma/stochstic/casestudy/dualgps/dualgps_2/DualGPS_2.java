		package hu.bme.mit.gamma.stochstic.casestudy.dualgps.dualgps_2;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.gps.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.voteradapter_p.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.gpsadapter.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.voter_p.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class DualGPS_2 implements DualGPS_2Interface {
			// Component instances
			private GPSasync GPS1;
			private GPSasync GPS2;
			private Voterasync Voter;
			// Environmental Component instances
			// Port instances
			private Communication communication = new Communication();
			// Channel instances
			private HardwareFailureChannelInterface channelFaultsOfVoter_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS2_Failure;
			private SensorChannelInterface channelCommunicationOfGPS1;
			private SensorChannelInterface channelCommunicationOfGPS2;
			private HardwareFailureChannelInterface channelFaultsOfGPS1_Failure;
			
			
			protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
			protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
			
			public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
				envComponents.add(component);
				envMap.put(name,component);
			}
			
			public boolean isEventQueueEmpty(){
				return  GPS1.isEventQueueEmpty()  &&  GPS2.isEventQueueEmpty()  &&  Voter.isEventQueueEmpty()   &&   true &&   true &&   true  ;
			}
			
			private final int MAX_SCHEDULE=15;
			
			public void schedule(){
				int cntr=0;
				do{
					GPS1.schedule();
					GPS2.schedule();
					Voter.schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in DualGPS_2! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					GPS1.schedule();
					GPS2.schedule();
					Voter.schedule();
					System.out.println(getInQueue());
					if (!true){
						System.out.println("    elementary stochastic component GPS1_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS2_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component Voter_Failure is not empty");
					}
					
				}
			}
			
			public DualGPS_2() {
				GPS1 = new GPSasync();
				GPS2 = new GPSasync();
				Voter = new Voterasync(2);
				communication = new Communication(); 
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
				GPS1.handleBeforeReset();
				GPS2.handleBeforeReset();
				Voter.handleBeforeReset();
								//
			}

			public void resetVariables() {
				GPS1.resetVariables();
				GPS2.resetVariables();
				Voter.resetVariables();
			}
			
			public void resetStateConfigurations() {
				GPS1.resetStateConfigurations();
				GPS2.resetStateConfigurations();
				Voter.resetStateConfigurations();
			}
			
			public void raiseEntryEvents() {
				GPS1.raiseEntryEvents();
				GPS2.raiseEntryEvents();
				Voter.raiseEntryEvents();
			}

			public void handleAfterReset() {
				GPS1.handleAfterReset();
				GPS2.handleAfterReset();
				Voter.handleAfterReset();
				
				//GPS1.schedule();
				//GPS2.schedule();
				//Voter.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				channelCommunicationOfGPS2 = new SensorChannel(GPS2.getCommunication());
				channelCommunicationOfGPS2.registerPort(Voter.getSensor());
				channelCommunicationOfGPS1 = new SensorChannel(GPS1.getCommunication());
				channelCommunicationOfGPS1.registerPort(Voter.getSensor());
				// Registration of broadcast channels
			}
			
			// Inner classes representing Ports
			public class Communication implements SensorInterface.Provided {
				
				
				List<SensorInterface.Listener.Provided> registeredListeners=new ArrayList<SensorInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedFailstop() {
					return Voter.getCommunication().isRaisedFailstop();
				}
				
				
				@Override
				public void registerListener(SensorInterface.Listener.Provided listener) {
					Voter.getCommunication().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<SensorInterface.Listener.Provided> getRegisteredListeners() {
					List<SensorInterface.Listener.Provided> registeredListeners=new ArrayList<SensorInterface.Listener.Provided>();
					registeredListeners.addAll(Voter.getCommunication().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public Communication getCommunication() {
				return communication;
			}
			
			/** Starts the running of the asynchronous component. */
			@Override
			public void start() {
				GPS1.start();
				GPS2.start();
				Voter.start();
			}
			
			public boolean isWaiting() {
				return GPS1.isWaiting() && GPS2.isWaiting() && Voter.isWaiting()
					;
			}
			
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			public GPSasync getGPS1() {
				return GPS1;
			}
			
			public GPSasync getGPS2() {
				return GPS2;
			}
			
			public Voterasync getVoter() {
				return Voter;
			}
			

			public String getInQueue(){
				if (!isEventQueueEmpty()){
					String str="Input of components [";
					str=str+"\n    GPS1 : "+GPS1.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS2 : "+GPS2.getInQueue().replace("    ","      ");
					
					str=str+"\n    Voter : "+Voter.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
