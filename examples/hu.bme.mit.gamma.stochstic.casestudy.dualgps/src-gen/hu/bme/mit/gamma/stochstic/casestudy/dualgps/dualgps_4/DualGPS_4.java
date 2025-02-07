		package hu.bme.mit.gamma.stochstic.casestudy.dualgps.dualgps_4;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.gps.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.gpsadapter.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.voteradapter_p.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.voter_p.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class DualGPS_4 implements DualGPS_4Interface {
			// Component instances
			private Voterasync Voter;
			private GPSasync GPS1;
			private GPSasync GPS2;
			private GPSasync GPS3;
			private GPSasync GPS4;
			// Environmental Component instances
			// Port instances
			private Communication communication = new Communication();
			// Channel instances
			private SensorChannelInterface channelCommunicationOfGPS2;
			private HardwareFailureChannelInterface channelFaultsOfGPS4_Failure;
			private SensorChannelInterface channelCommunicationOfGPS1;
			private SensorChannelInterface channelCommunicationOfGPS3;
			private HardwareFailureChannelInterface channelFaultsOfGPS2_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS3_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS1_Failure;
			private SensorChannelInterface channelCommunicationOfGPS4;
			private HardwareFailureChannelInterface channelFaultsOfVoter_Failure;
			
			
			protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
			protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
			
			public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
				envComponents.add(component);
				envMap.put(name,component);
			}
			
			public boolean isEventQueueEmpty(){
				return  Voter.isEventQueueEmpty()  &&  GPS1.isEventQueueEmpty()  &&  GPS2.isEventQueueEmpty()  &&  GPS3.isEventQueueEmpty()  &&  GPS4.isEventQueueEmpty()   &&   true &&   true &&   true &&   true &&   true  ;
			}
			
			private final int MAX_SCHEDULE=15;
			
			public void schedule(){
				int cntr=0;
				do{
					Voter.schedule();
					GPS1.schedule();
					GPS2.schedule();
					GPS3.schedule();
					GPS4.schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in DualGPS_4! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					Voter.schedule();
					GPS1.schedule();
					GPS2.schedule();
					GPS3.schedule();
					GPS4.schedule();
					System.out.println(getInQueue());
					if (!true){
						System.out.println("    elementary stochastic component GPS1_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS2_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS3_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS4_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component Voter_Failure is not empty");
					}
					
				}
			}
			
			public DualGPS_4() {
				Voter = new Voterasync(4);
				GPS1 = new GPSasync();
				GPS2 = new GPSasync();
				GPS3 = new GPSasync();
				GPS4 = new GPSasync();
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
				Voter.handleBeforeReset();
				GPS1.handleBeforeReset();
				GPS2.handleBeforeReset();
				GPS3.handleBeforeReset();
				GPS4.handleBeforeReset();
								//
			}

			public void resetVariables() {
				Voter.resetVariables();
				GPS1.resetVariables();
				GPS2.resetVariables();
				GPS3.resetVariables();
				GPS4.resetVariables();
			}
			
			public void resetStateConfigurations() {
				Voter.resetStateConfigurations();
				GPS1.resetStateConfigurations();
				GPS2.resetStateConfigurations();
				GPS3.resetStateConfigurations();
				GPS4.resetStateConfigurations();
			}
			
			public void raiseEntryEvents() {
				Voter.raiseEntryEvents();
				GPS1.raiseEntryEvents();
				GPS2.raiseEntryEvents();
				GPS3.raiseEntryEvents();
				GPS4.raiseEntryEvents();
			}

			public void handleAfterReset() {
				Voter.handleAfterReset();
				GPS1.handleAfterReset();
				GPS2.handleAfterReset();
				GPS3.handleAfterReset();
				GPS4.handleAfterReset();
				
				//Voter.schedule();
				//GPS1.schedule();
				//GPS2.schedule();
				//GPS3.schedule();
				//GPS4.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				channelCommunicationOfGPS3 = new SensorChannel(GPS3.getCommunication());
				channelCommunicationOfGPS3.registerPort(Voter.getSensor());
				channelCommunicationOfGPS4 = new SensorChannel(GPS4.getCommunication());
				channelCommunicationOfGPS4.registerPort(Voter.getSensor());
				channelCommunicationOfGPS1 = new SensorChannel(GPS1.getCommunication());
				channelCommunicationOfGPS1.registerPort(Voter.getSensor());
				channelCommunicationOfGPS2 = new SensorChannel(GPS2.getCommunication());
				channelCommunicationOfGPS2.registerPort(Voter.getSensor());
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
				Voter.start();
				GPS1.start();
				GPS2.start();
				GPS3.start();
				GPS4.start();
			}
			
			public boolean isWaiting() {
				return Voter.isWaiting() && GPS1.isWaiting() && GPS2.isWaiting() && GPS3.isWaiting() && GPS4.isWaiting()
					;
			}
			
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			public Voterasync getVoter() {
				return Voter;
			}
			
			public GPSasync getGPS1() {
				return GPS1;
			}
			
			public GPSasync getGPS2() {
				return GPS2;
			}
			
			public GPSasync getGPS3() {
				return GPS3;
			}
			
			public GPSasync getGPS4() {
				return GPS4;
			}
			

			public String getInQueue(){
				if (!isEventQueueEmpty()){
					String str="Input of components [";
					str=str+"\n    Voter : "+Voter.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS1 : "+GPS1.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS2 : "+GPS2.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS3 : "+GPS3.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS4 : "+GPS4.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
