		package hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.dualgps;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.gpsadapter.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.voteradapter.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.voter.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.interfaces.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.gps.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class DualGPS implements DualGPSInterface {
			// Component instances
			private Voterasync Voter;
			private GPSasync GPS1;
			private GPSasync GPS2;
			private GPSasync GPS3;
			private GPSasync GPS4;
			private GPSasync GPS5;
			private GPSasync GPS6;
			private GPSasync GPS7;
			private GPSasync GPS8;
			// Environmental Component instances
			// Port instances
			private Communication communication = new Communication();
			// Channel instances
			private SensorChannelInterface channelCommunicationOfGPS8;
			private HardwareFailureChannelInterface channelFaultsOfGPS3_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS2_Failure;
			private SensorChannelInterface channelCommunicationOfGPS4;
			private SensorChannelInterface channelCommunicationOfGPS1;
			private HardwareFailureChannelInterface channelFaultsOfGPS7_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS8_Failure;
			private SensorChannelInterface channelCommunicationOfGPS5;
			private HardwareFailureChannelInterface channelFaultsOfGPS1_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS4_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS6_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS5_Failure;
			private SensorChannelInterface channelCommunicationOfGPS7;
			private SensorChannelInterface channelCommunicationOfGPS6;
			private HardwareFailureChannelInterface channelFaultsOfVoter_Failure;
			private SensorChannelInterface channelCommunicationOfGPS3;
			private SensorChannelInterface channelCommunicationOfGPS2;
			
			
			protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
			protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
			
			public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
				envComponents.add(component);
				envMap.put(name,component);
			}
			
			public boolean isEventQueueEmpty(){
				return  Voter.isEventQueueEmpty()  &&  GPS1.isEventQueueEmpty()  &&  GPS2.isEventQueueEmpty()  &&  GPS3.isEventQueueEmpty()  &&  GPS4.isEventQueueEmpty()  &&  GPS5.isEventQueueEmpty()  &&  GPS6.isEventQueueEmpty()  &&  GPS7.isEventQueueEmpty()  &&  GPS8.isEventQueueEmpty()   &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true  ;
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
					GPS5.schedule();
					GPS6.schedule();
					GPS7.schedule();
					GPS8.schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in DualGPS! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					Voter.schedule();
					GPS1.schedule();
					GPS2.schedule();
					GPS3.schedule();
					GPS4.schedule();
					GPS5.schedule();
					GPS6.schedule();
					GPS7.schedule();
					GPS8.schedule();
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
						System.out.println("    elementary stochastic component GPS5_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS6_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS7_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS8_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component Voter_Failure is not empty");
					}
					
				}
			}
			
			public DualGPS() {
				Voter = new Voterasync(8);
				GPS1 = new GPSasync();
				GPS2 = new GPSasync();
				GPS3 = new GPSasync();
				GPS4 = new GPSasync();
				GPS5 = new GPSasync();
				GPS6 = new GPSasync();
				GPS7 = new GPSasync();
				GPS8 = new GPSasync();
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
				GPS5.handleBeforeReset();
				GPS6.handleBeforeReset();
				GPS7.handleBeforeReset();
				GPS8.handleBeforeReset();
								//
			}

			public void resetVariables() {
				Voter.resetVariables();
				GPS1.resetVariables();
				GPS2.resetVariables();
				GPS3.resetVariables();
				GPS4.resetVariables();
				GPS5.resetVariables();
				GPS6.resetVariables();
				GPS7.resetVariables();
				GPS8.resetVariables();
			}
			
			public void resetStateConfigurations() {
				Voter.resetStateConfigurations();
				GPS1.resetStateConfigurations();
				GPS2.resetStateConfigurations();
				GPS3.resetStateConfigurations();
				GPS4.resetStateConfigurations();
				GPS5.resetStateConfigurations();
				GPS6.resetStateConfigurations();
				GPS7.resetStateConfigurations();
				GPS8.resetStateConfigurations();
			}
			
			public void raiseEntryEvents() {
				Voter.raiseEntryEvents();
				GPS1.raiseEntryEvents();
				GPS2.raiseEntryEvents();
				GPS3.raiseEntryEvents();
				GPS4.raiseEntryEvents();
				GPS5.raiseEntryEvents();
				GPS6.raiseEntryEvents();
				GPS7.raiseEntryEvents();
				GPS8.raiseEntryEvents();
			}

			public void handleAfterReset() {
				Voter.handleAfterReset();
				GPS1.handleAfterReset();
				GPS2.handleAfterReset();
				GPS3.handleAfterReset();
				GPS4.handleAfterReset();
				GPS5.handleAfterReset();
				GPS6.handleAfterReset();
				GPS7.handleAfterReset();
				GPS8.handleAfterReset();
				
				//Voter.schedule();
				//GPS1.schedule();
				//GPS2.schedule();
				//GPS3.schedule();
				//GPS4.schedule();
				//GPS5.schedule();
				//GPS6.schedule();
				//GPS7.schedule();
				//GPS8.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				channelCommunicationOfGPS3 = new SensorChannel(GPS3.getCommunication());
				channelCommunicationOfGPS3.registerPort(Voter.getSensor());
				channelCommunicationOfGPS7 = new SensorChannel(GPS7.getCommunication());
				channelCommunicationOfGPS7.registerPort(Voter.getSensor());
				channelCommunicationOfGPS1 = new SensorChannel(GPS1.getCommunication());
				channelCommunicationOfGPS1.registerPort(Voter.getSensor());
				channelCommunicationOfGPS5 = new SensorChannel(GPS5.getCommunication());
				channelCommunicationOfGPS5.registerPort(Voter.getSensor());
				channelCommunicationOfGPS6 = new SensorChannel(GPS6.getCommunication());
				channelCommunicationOfGPS6.registerPort(Voter.getSensor());
				channelCommunicationOfGPS4 = new SensorChannel(GPS4.getCommunication());
				channelCommunicationOfGPS4.registerPort(Voter.getSensor());
				channelCommunicationOfGPS8 = new SensorChannel(GPS8.getCommunication());
				channelCommunicationOfGPS8.registerPort(Voter.getSensor());
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
				GPS5.start();
				GPS6.start();
				GPS7.start();
				GPS8.start();
			}
			
			public boolean isWaiting() {
				return Voter.isWaiting() && GPS1.isWaiting() && GPS2.isWaiting() && GPS3.isWaiting() && GPS4.isWaiting() && GPS5.isWaiting() && GPS6.isWaiting() && GPS7.isWaiting() && GPS8.isWaiting()
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
			
			public GPSasync getGPS5() {
				return GPS5;
			}
			
			public GPSasync getGPS6() {
				return GPS6;
			}
			
			public GPSasync getGPS7() {
				return GPS7;
			}
			
			public GPSasync getGPS8() {
				return GPS8;
			}
			

			public String getInQueue(){
				if (!isEventQueueEmpty()){
					String str="Input of components [";
					str=str+"\n    Voter : "+Voter.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS1 : "+GPS1.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS2 : "+GPS2.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS3 : "+GPS3.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS4 : "+GPS4.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS5 : "+GPS5.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS6 : "+GPS6.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS7 : "+GPS7.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS8 : "+GPS8.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
