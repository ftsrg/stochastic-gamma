		package hu.bme.mit.gamma.stochstic.casestudy.dualgps.dualgps_32;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.voter_p.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.gpsadapter.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.voteradapter_p.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.gps.*;
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.stochstic.casestudy.dualgps.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class DualGPS_32 implements DualGPS_32Interface {
			// Component instances
			private Voterasync Voter;
			private GPSasync GPS1_;
			private GPSasync GPS2_;
			private GPSasync GPS3_;
			private GPSasync GPS4_;
			private GPSasync GPS5_;
			private GPSasync GPS6_;
			private GPSasync GPS7_;
			private GPSasync GPS8_;
			private GPSasync GPS9_;
			private GPSasync GPS10;
			private GPSasync GPS11;
			private GPSasync GPS12;
			private GPSasync GPS13;
			private GPSasync GPS14;
			private GPSasync GPS15;
			private GPSasync GPS16;
			private GPSasync GPS17;
			private GPSasync GPS18;
			private GPSasync GPS19;
			private GPSasync GPS20;
			private GPSasync GPS21;
			private GPSasync GPS22;
			private GPSasync GPS23;
			private GPSasync GPS24;
			private GPSasync GPS25;
			private GPSasync GPS26;
			private GPSasync GPS27;
			private GPSasync GPS28;
			private GPSasync GPS29;
			private GPSasync GPS30;
			private GPSasync GPS31;
			private GPSasync GPS32;
			// Environmental Component instances
			// Port instances
			private Communication communication = new Communication();
			// Channel instances
			private HardwareFailureChannelInterface channelFaultsOfGPS5__Failure;
			private SensorChannelInterface channelCommunicationOfGPS1_;
			private HardwareFailureChannelInterface channelFaultsOfGPS23_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS29_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS19_Failure;
			private SensorChannelInterface channelCommunicationOfGPS17;
			private SensorChannelInterface channelCommunicationOfGPS5_;
			private HardwareFailureChannelInterface channelFaultsOfGPS16_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS24_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS15_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS27_Failure;
			private SensorChannelInterface channelCommunicationOfGPS3_;
			private HardwareFailureChannelInterface channelFaultsOfGPS14_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS22_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS9__Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS10_Failure;
			private SensorChannelInterface channelCommunicationOfGPS11;
			private SensorChannelInterface channelCommunicationOfGPS21;
			private HardwareFailureChannelInterface channelFaultsOfGPS11_Failure;
			private HardwareFailureChannelInterface channelFaultsOfVoter_Failure;
			private SensorChannelInterface channelCommunicationOfGPS7_;
			private HardwareFailureChannelInterface channelFaultsOfGPS18_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS6__Failure;
			private SensorChannelInterface channelCommunicationOfGPS31;
			private SensorChannelInterface channelCommunicationOfGPS9_;
			private SensorChannelInterface channelCommunicationOfGPS10;
			private SensorChannelInterface channelCommunicationOfGPS8_;
			private HardwareFailureChannelInterface channelFaultsOfGPS12_Failure;
			private SensorChannelInterface channelCommunicationOfGPS16;
			private SensorChannelInterface channelCommunicationOfGPS30;
			private SensorChannelInterface channelCommunicationOfGPS6_;
			private HardwareFailureChannelInterface channelFaultsOfGPS17_Failure;
			private SensorChannelInterface channelCommunicationOfGPS29;
			private SensorChannelInterface channelCommunicationOfGPS32;
			private HardwareFailureChannelInterface channelFaultsOfGPS25_Failure;
			private SensorChannelInterface channelCommunicationOfGPS15;
			private HardwareFailureChannelInterface channelFaultsOfGPS2__Failure;
			private SensorChannelInterface channelCommunicationOfGPS25;
			private HardwareFailureChannelInterface channelFaultsOfGPS31_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS26_Failure;
			private SensorChannelInterface channelCommunicationOfGPS26;
			private SensorChannelInterface channelCommunicationOfGPS27;
			private SensorChannelInterface channelCommunicationOfGPS12;
			private HardwareFailureChannelInterface channelFaultsOfGPS3__Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS32_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS13_Failure;
			private SensorChannelInterface channelCommunicationOfGPS23;
			private HardwareFailureChannelInterface channelFaultsOfGPS21_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS4__Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS8__Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS20_Failure;
			private SensorChannelInterface channelCommunicationOfGPS4_;
			private SensorChannelInterface channelCommunicationOfGPS20;
			private SensorChannelInterface channelCommunicationOfGPS18;
			private SensorChannelInterface channelCommunicationOfGPS24;
			private SensorChannelInterface channelCommunicationOfGPS19;
			private HardwareFailureChannelInterface channelFaultsOfGPS1__Failure;
			private SensorChannelInterface channelCommunicationOfGPS22;
			private SensorChannelInterface channelCommunicationOfGPS2_;
			private HardwareFailureChannelInterface channelFaultsOfGPS28_Failure;
			private HardwareFailureChannelInterface channelFaultsOfGPS7__Failure;
			private SensorChannelInterface channelCommunicationOfGPS14;
			private SensorChannelInterface channelCommunicationOfGPS28;
			private HardwareFailureChannelInterface channelFaultsOfGPS30_Failure;
			private SensorChannelInterface channelCommunicationOfGPS13;
			
			
			protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
			protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
			
			public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
				envComponents.add(component);
				envMap.put(name,component);
			}
			
			public boolean isEventQueueEmpty(){
				return  Voter.isEventQueueEmpty()  &&  GPS1_.isEventQueueEmpty()  &&  GPS2_.isEventQueueEmpty()  &&  GPS3_.isEventQueueEmpty()  &&  GPS4_.isEventQueueEmpty()  &&  GPS5_.isEventQueueEmpty()  &&  GPS6_.isEventQueueEmpty()  &&  GPS7_.isEventQueueEmpty()  &&  GPS8_.isEventQueueEmpty()  &&  GPS9_.isEventQueueEmpty()  &&  GPS10.isEventQueueEmpty()  &&  GPS11.isEventQueueEmpty()  &&  GPS12.isEventQueueEmpty()  &&  GPS13.isEventQueueEmpty()  &&  GPS14.isEventQueueEmpty()  &&  GPS15.isEventQueueEmpty()  &&  GPS16.isEventQueueEmpty()  &&  GPS17.isEventQueueEmpty()  &&  GPS18.isEventQueueEmpty()  &&  GPS19.isEventQueueEmpty()  &&  GPS20.isEventQueueEmpty()  &&  GPS21.isEventQueueEmpty()  &&  GPS22.isEventQueueEmpty()  &&  GPS23.isEventQueueEmpty()  &&  GPS24.isEventQueueEmpty()  &&  GPS25.isEventQueueEmpty()  &&  GPS26.isEventQueueEmpty()  &&  GPS27.isEventQueueEmpty()  &&  GPS28.isEventQueueEmpty()  &&  GPS29.isEventQueueEmpty()  &&  GPS30.isEventQueueEmpty()  &&  GPS31.isEventQueueEmpty()  &&  GPS32.isEventQueueEmpty()   &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true &&   true  ;
			}
			
			private final int MAX_SCHEDULE=15;
			
			public void schedule(){
				int cntr=0;
				do{
					Voter.schedule();
					GPS1_.schedule();
					GPS2_.schedule();
					GPS3_.schedule();
					GPS4_.schedule();
					GPS5_.schedule();
					GPS6_.schedule();
					GPS7_.schedule();
					GPS8_.schedule();
					GPS9_.schedule();
					GPS10.schedule();
					GPS11.schedule();
					GPS12.schedule();
					GPS13.schedule();
					GPS14.schedule();
					GPS15.schedule();
					GPS16.schedule();
					GPS17.schedule();
					GPS18.schedule();
					GPS19.schedule();
					GPS20.schedule();
					GPS21.schedule();
					GPS22.schedule();
					GPS23.schedule();
					GPS24.schedule();
					GPS25.schedule();
					GPS26.schedule();
					GPS27.schedule();
					GPS28.schedule();
					GPS29.schedule();
					GPS30.schedule();
					GPS31.schedule();
					GPS32.schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in DualGPS_32! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					Voter.schedule();
					GPS1_.schedule();
					GPS2_.schedule();
					GPS3_.schedule();
					GPS4_.schedule();
					GPS5_.schedule();
					GPS6_.schedule();
					GPS7_.schedule();
					GPS8_.schedule();
					GPS9_.schedule();
					GPS10.schedule();
					GPS11.schedule();
					GPS12.schedule();
					GPS13.schedule();
					GPS14.schedule();
					GPS15.schedule();
					GPS16.schedule();
					GPS17.schedule();
					GPS18.schedule();
					GPS19.schedule();
					GPS20.schedule();
					GPS21.schedule();
					GPS22.schedule();
					GPS23.schedule();
					GPS24.schedule();
					GPS25.schedule();
					GPS26.schedule();
					GPS27.schedule();
					GPS28.schedule();
					GPS29.schedule();
					GPS30.schedule();
					GPS31.schedule();
					GPS32.schedule();
					System.out.println(getInQueue());
					if (!true){
						System.out.println("    elementary stochastic component GPS1__Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS2__Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS3__Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS4__Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS5__Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS6__Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS7__Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS8__Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS9__Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS10_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS11_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS12_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS13_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS14_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS15_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS16_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS17_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS18_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS19_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS20_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS21_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS22_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS23_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS24_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS25_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS26_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS27_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS28_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS29_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS30_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS31_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component GPS32_Failure is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component Voter_Failure is not empty");
					}
					
				}
			}
			
			public DualGPS_32() {
				Voter = new Voterasync(32);
				GPS1_ = new GPSasync();
				GPS2_ = new GPSasync();
				GPS3_ = new GPSasync();
				GPS4_ = new GPSasync();
				GPS5_ = new GPSasync();
				GPS6_ = new GPSasync();
				GPS7_ = new GPSasync();
				GPS8_ = new GPSasync();
				GPS9_ = new GPSasync();
				GPS10 = new GPSasync();
				GPS11 = new GPSasync();
				GPS12 = new GPSasync();
				GPS13 = new GPSasync();
				GPS14 = new GPSasync();
				GPS15 = new GPSasync();
				GPS16 = new GPSasync();
				GPS17 = new GPSasync();
				GPS18 = new GPSasync();
				GPS19 = new GPSasync();
				GPS20 = new GPSasync();
				GPS21 = new GPSasync();
				GPS22 = new GPSasync();
				GPS23 = new GPSasync();
				GPS24 = new GPSasync();
				GPS25 = new GPSasync();
				GPS26 = new GPSasync();
				GPS27 = new GPSasync();
				GPS28 = new GPSasync();
				GPS29 = new GPSasync();
				GPS30 = new GPSasync();
				GPS31 = new GPSasync();
				GPS32 = new GPSasync();
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
				GPS1_.handleBeforeReset();
				GPS2_.handleBeforeReset();
				GPS3_.handleBeforeReset();
				GPS4_.handleBeforeReset();
				GPS5_.handleBeforeReset();
				GPS6_.handleBeforeReset();
				GPS7_.handleBeforeReset();
				GPS8_.handleBeforeReset();
				GPS9_.handleBeforeReset();
				GPS10.handleBeforeReset();
				GPS11.handleBeforeReset();
				GPS12.handleBeforeReset();
				GPS13.handleBeforeReset();
				GPS14.handleBeforeReset();
				GPS15.handleBeforeReset();
				GPS16.handleBeforeReset();
				GPS17.handleBeforeReset();
				GPS18.handleBeforeReset();
				GPS19.handleBeforeReset();
				GPS20.handleBeforeReset();
				GPS21.handleBeforeReset();
				GPS22.handleBeforeReset();
				GPS23.handleBeforeReset();
				GPS24.handleBeforeReset();
				GPS25.handleBeforeReset();
				GPS26.handleBeforeReset();
				GPS27.handleBeforeReset();
				GPS28.handleBeforeReset();
				GPS29.handleBeforeReset();
				GPS30.handleBeforeReset();
				GPS31.handleBeforeReset();
				GPS32.handleBeforeReset();
								//
			}

			public void resetVariables() {
				Voter.resetVariables();
				GPS1_.resetVariables();
				GPS2_.resetVariables();
				GPS3_.resetVariables();
				GPS4_.resetVariables();
				GPS5_.resetVariables();
				GPS6_.resetVariables();
				GPS7_.resetVariables();
				GPS8_.resetVariables();
				GPS9_.resetVariables();
				GPS10.resetVariables();
				GPS11.resetVariables();
				GPS12.resetVariables();
				GPS13.resetVariables();
				GPS14.resetVariables();
				GPS15.resetVariables();
				GPS16.resetVariables();
				GPS17.resetVariables();
				GPS18.resetVariables();
				GPS19.resetVariables();
				GPS20.resetVariables();
				GPS21.resetVariables();
				GPS22.resetVariables();
				GPS23.resetVariables();
				GPS24.resetVariables();
				GPS25.resetVariables();
				GPS26.resetVariables();
				GPS27.resetVariables();
				GPS28.resetVariables();
				GPS29.resetVariables();
				GPS30.resetVariables();
				GPS31.resetVariables();
				GPS32.resetVariables();
			}
			
			public void resetStateConfigurations() {
				Voter.resetStateConfigurations();
				GPS1_.resetStateConfigurations();
				GPS2_.resetStateConfigurations();
				GPS3_.resetStateConfigurations();
				GPS4_.resetStateConfigurations();
				GPS5_.resetStateConfigurations();
				GPS6_.resetStateConfigurations();
				GPS7_.resetStateConfigurations();
				GPS8_.resetStateConfigurations();
				GPS9_.resetStateConfigurations();
				GPS10.resetStateConfigurations();
				GPS11.resetStateConfigurations();
				GPS12.resetStateConfigurations();
				GPS13.resetStateConfigurations();
				GPS14.resetStateConfigurations();
				GPS15.resetStateConfigurations();
				GPS16.resetStateConfigurations();
				GPS17.resetStateConfigurations();
				GPS18.resetStateConfigurations();
				GPS19.resetStateConfigurations();
				GPS20.resetStateConfigurations();
				GPS21.resetStateConfigurations();
				GPS22.resetStateConfigurations();
				GPS23.resetStateConfigurations();
				GPS24.resetStateConfigurations();
				GPS25.resetStateConfigurations();
				GPS26.resetStateConfigurations();
				GPS27.resetStateConfigurations();
				GPS28.resetStateConfigurations();
				GPS29.resetStateConfigurations();
				GPS30.resetStateConfigurations();
				GPS31.resetStateConfigurations();
				GPS32.resetStateConfigurations();
			}
			
			public void raiseEntryEvents() {
				Voter.raiseEntryEvents();
				GPS1_.raiseEntryEvents();
				GPS2_.raiseEntryEvents();
				GPS3_.raiseEntryEvents();
				GPS4_.raiseEntryEvents();
				GPS5_.raiseEntryEvents();
				GPS6_.raiseEntryEvents();
				GPS7_.raiseEntryEvents();
				GPS8_.raiseEntryEvents();
				GPS9_.raiseEntryEvents();
				GPS10.raiseEntryEvents();
				GPS11.raiseEntryEvents();
				GPS12.raiseEntryEvents();
				GPS13.raiseEntryEvents();
				GPS14.raiseEntryEvents();
				GPS15.raiseEntryEvents();
				GPS16.raiseEntryEvents();
				GPS17.raiseEntryEvents();
				GPS18.raiseEntryEvents();
				GPS19.raiseEntryEvents();
				GPS20.raiseEntryEvents();
				GPS21.raiseEntryEvents();
				GPS22.raiseEntryEvents();
				GPS23.raiseEntryEvents();
				GPS24.raiseEntryEvents();
				GPS25.raiseEntryEvents();
				GPS26.raiseEntryEvents();
				GPS27.raiseEntryEvents();
				GPS28.raiseEntryEvents();
				GPS29.raiseEntryEvents();
				GPS30.raiseEntryEvents();
				GPS31.raiseEntryEvents();
				GPS32.raiseEntryEvents();
			}

			public void handleAfterReset() {
				Voter.handleAfterReset();
				GPS1_.handleAfterReset();
				GPS2_.handleAfterReset();
				GPS3_.handleAfterReset();
				GPS4_.handleAfterReset();
				GPS5_.handleAfterReset();
				GPS6_.handleAfterReset();
				GPS7_.handleAfterReset();
				GPS8_.handleAfterReset();
				GPS9_.handleAfterReset();
				GPS10.handleAfterReset();
				GPS11.handleAfterReset();
				GPS12.handleAfterReset();
				GPS13.handleAfterReset();
				GPS14.handleAfterReset();
				GPS15.handleAfterReset();
				GPS16.handleAfterReset();
				GPS17.handleAfterReset();
				GPS18.handleAfterReset();
				GPS19.handleAfterReset();
				GPS20.handleAfterReset();
				GPS21.handleAfterReset();
				GPS22.handleAfterReset();
				GPS23.handleAfterReset();
				GPS24.handleAfterReset();
				GPS25.handleAfterReset();
				GPS26.handleAfterReset();
				GPS27.handleAfterReset();
				GPS28.handleAfterReset();
				GPS29.handleAfterReset();
				GPS30.handleAfterReset();
				GPS31.handleAfterReset();
				GPS32.handleAfterReset();
				
				//Voter.schedule();
				//GPS1_.schedule();
				//GPS2_.schedule();
				//GPS3_.schedule();
				//GPS4_.schedule();
				//GPS5_.schedule();
				//GPS6_.schedule();
				//GPS7_.schedule();
				//GPS8_.schedule();
				//GPS9_.schedule();
				//GPS10.schedule();
				//GPS11.schedule();
				//GPS12.schedule();
				//GPS13.schedule();
				//GPS14.schedule();
				//GPS15.schedule();
				//GPS16.schedule();
				//GPS17.schedule();
				//GPS18.schedule();
				//GPS19.schedule();
				//GPS20.schedule();
				//GPS21.schedule();
				//GPS22.schedule();
				//GPS23.schedule();
				//GPS24.schedule();
				//GPS25.schedule();
				//GPS26.schedule();
				//GPS27.schedule();
				//GPS28.schedule();
				//GPS29.schedule();
				//GPS30.schedule();
				//GPS31.schedule();
				//GPS32.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				channelCommunicationOfGPS26 = new SensorChannel(GPS26.getCommunication());
				channelCommunicationOfGPS26.registerPort(Voter.getSensor());
				channelCommunicationOfGPS1_ = new SensorChannel(GPS1_.getCommunication());
				channelCommunicationOfGPS1_.registerPort(Voter.getSensor());
				channelCommunicationOfGPS12 = new SensorChannel(GPS12.getCommunication());
				channelCommunicationOfGPS12.registerPort(Voter.getSensor());
				channelCommunicationOfGPS19 = new SensorChannel(GPS19.getCommunication());
				channelCommunicationOfGPS19.registerPort(Voter.getSensor());
				channelCommunicationOfGPS8_ = new SensorChannel(GPS8_.getCommunication());
				channelCommunicationOfGPS8_.registerPort(Voter.getSensor());
				channelCommunicationOfGPS31 = new SensorChannel(GPS31.getCommunication());
				channelCommunicationOfGPS31.registerPort(Voter.getSensor());
				channelCommunicationOfGPS20 = new SensorChannel(GPS20.getCommunication());
				channelCommunicationOfGPS20.registerPort(Voter.getSensor());
				channelCommunicationOfGPS29 = new SensorChannel(GPS29.getCommunication());
				channelCommunicationOfGPS29.registerPort(Voter.getSensor());
				channelCommunicationOfGPS30 = new SensorChannel(GPS30.getCommunication());
				channelCommunicationOfGPS30.registerPort(Voter.getSensor());
				channelCommunicationOfGPS23 = new SensorChannel(GPS23.getCommunication());
				channelCommunicationOfGPS23.registerPort(Voter.getSensor());
				channelCommunicationOfGPS9_ = new SensorChannel(GPS9_.getCommunication());
				channelCommunicationOfGPS9_.registerPort(Voter.getSensor());
				channelCommunicationOfGPS14 = new SensorChannel(GPS14.getCommunication());
				channelCommunicationOfGPS14.registerPort(Voter.getSensor());
				channelCommunicationOfGPS32 = new SensorChannel(GPS32.getCommunication());
				channelCommunicationOfGPS32.registerPort(Voter.getSensor());
				channelCommunicationOfGPS28 = new SensorChannel(GPS28.getCommunication());
				channelCommunicationOfGPS28.registerPort(Voter.getSensor());
				channelCommunicationOfGPS24 = new SensorChannel(GPS24.getCommunication());
				channelCommunicationOfGPS24.registerPort(Voter.getSensor());
				channelCommunicationOfGPS22 = new SensorChannel(GPS22.getCommunication());
				channelCommunicationOfGPS22.registerPort(Voter.getSensor());
				channelCommunicationOfGPS6_ = new SensorChannel(GPS6_.getCommunication());
				channelCommunicationOfGPS6_.registerPort(Voter.getSensor());
				channelCommunicationOfGPS27 = new SensorChannel(GPS27.getCommunication());
				channelCommunicationOfGPS27.registerPort(Voter.getSensor());
				channelCommunicationOfGPS13 = new SensorChannel(GPS13.getCommunication());
				channelCommunicationOfGPS13.registerPort(Voter.getSensor());
				channelCommunicationOfGPS18 = new SensorChannel(GPS18.getCommunication());
				channelCommunicationOfGPS18.registerPort(Voter.getSensor());
				channelCommunicationOfGPS16 = new SensorChannel(GPS16.getCommunication());
				channelCommunicationOfGPS16.registerPort(Voter.getSensor());
				channelCommunicationOfGPS21 = new SensorChannel(GPS21.getCommunication());
				channelCommunicationOfGPS21.registerPort(Voter.getSensor());
				channelCommunicationOfGPS17 = new SensorChannel(GPS17.getCommunication());
				channelCommunicationOfGPS17.registerPort(Voter.getSensor());
				channelCommunicationOfGPS10 = new SensorChannel(GPS10.getCommunication());
				channelCommunicationOfGPS10.registerPort(Voter.getSensor());
				channelCommunicationOfGPS3_ = new SensorChannel(GPS3_.getCommunication());
				channelCommunicationOfGPS3_.registerPort(Voter.getSensor());
				channelCommunicationOfGPS11 = new SensorChannel(GPS11.getCommunication());
				channelCommunicationOfGPS11.registerPort(Voter.getSensor());
				channelCommunicationOfGPS5_ = new SensorChannel(GPS5_.getCommunication());
				channelCommunicationOfGPS5_.registerPort(Voter.getSensor());
				channelCommunicationOfGPS15 = new SensorChannel(GPS15.getCommunication());
				channelCommunicationOfGPS15.registerPort(Voter.getSensor());
				channelCommunicationOfGPS2_ = new SensorChannel(GPS2_.getCommunication());
				channelCommunicationOfGPS2_.registerPort(Voter.getSensor());
				channelCommunicationOfGPS4_ = new SensorChannel(GPS4_.getCommunication());
				channelCommunicationOfGPS4_.registerPort(Voter.getSensor());
				channelCommunicationOfGPS7_ = new SensorChannel(GPS7_.getCommunication());
				channelCommunicationOfGPS7_.registerPort(Voter.getSensor());
				channelCommunicationOfGPS25 = new SensorChannel(GPS25.getCommunication());
				channelCommunicationOfGPS25.registerPort(Voter.getSensor());
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
				GPS1_.start();
				GPS2_.start();
				GPS3_.start();
				GPS4_.start();
				GPS5_.start();
				GPS6_.start();
				GPS7_.start();
				GPS8_.start();
				GPS9_.start();
				GPS10.start();
				GPS11.start();
				GPS12.start();
				GPS13.start();
				GPS14.start();
				GPS15.start();
				GPS16.start();
				GPS17.start();
				GPS18.start();
				GPS19.start();
				GPS20.start();
				GPS21.start();
				GPS22.start();
				GPS23.start();
				GPS24.start();
				GPS25.start();
				GPS26.start();
				GPS27.start();
				GPS28.start();
				GPS29.start();
				GPS30.start();
				GPS31.start();
				GPS32.start();
			}
			
			public boolean isWaiting() {
				return Voter.isWaiting() && GPS1_.isWaiting() && GPS2_.isWaiting() && GPS3_.isWaiting() && GPS4_.isWaiting() && GPS5_.isWaiting() && GPS6_.isWaiting() && GPS7_.isWaiting() && GPS8_.isWaiting() && GPS9_.isWaiting() && GPS10.isWaiting() && GPS11.isWaiting() && GPS12.isWaiting() && GPS13.isWaiting() && GPS14.isWaiting() && GPS15.isWaiting() && GPS16.isWaiting() && GPS17.isWaiting() && GPS18.isWaiting() && GPS19.isWaiting() && GPS20.isWaiting() && GPS21.isWaiting() && GPS22.isWaiting() && GPS23.isWaiting() && GPS24.isWaiting() && GPS25.isWaiting() && GPS26.isWaiting() && GPS27.isWaiting() && GPS28.isWaiting() && GPS29.isWaiting() && GPS30.isWaiting() && GPS31.isWaiting() && GPS32.isWaiting()
					;
			}
			
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			public Voterasync getVoter() {
				return Voter;
			}
			
			public GPSasync getGPS1_() {
				return GPS1_;
			}
			
			public GPSasync getGPS2_() {
				return GPS2_;
			}
			
			public GPSasync getGPS3_() {
				return GPS3_;
			}
			
			public GPSasync getGPS4_() {
				return GPS4_;
			}
			
			public GPSasync getGPS5_() {
				return GPS5_;
			}
			
			public GPSasync getGPS6_() {
				return GPS6_;
			}
			
			public GPSasync getGPS7_() {
				return GPS7_;
			}
			
			public GPSasync getGPS8_() {
				return GPS8_;
			}
			
			public GPSasync getGPS9_() {
				return GPS9_;
			}
			
			public GPSasync getGPS10() {
				return GPS10;
			}
			
			public GPSasync getGPS11() {
				return GPS11;
			}
			
			public GPSasync getGPS12() {
				return GPS12;
			}
			
			public GPSasync getGPS13() {
				return GPS13;
			}
			
			public GPSasync getGPS14() {
				return GPS14;
			}
			
			public GPSasync getGPS15() {
				return GPS15;
			}
			
			public GPSasync getGPS16() {
				return GPS16;
			}
			
			public GPSasync getGPS17() {
				return GPS17;
			}
			
			public GPSasync getGPS18() {
				return GPS18;
			}
			
			public GPSasync getGPS19() {
				return GPS19;
			}
			
			public GPSasync getGPS20() {
				return GPS20;
			}
			
			public GPSasync getGPS21() {
				return GPS21;
			}
			
			public GPSasync getGPS22() {
				return GPS22;
			}
			
			public GPSasync getGPS23() {
				return GPS23;
			}
			
			public GPSasync getGPS24() {
				return GPS24;
			}
			
			public GPSasync getGPS25() {
				return GPS25;
			}
			
			public GPSasync getGPS26() {
				return GPS26;
			}
			
			public GPSasync getGPS27() {
				return GPS27;
			}
			
			public GPSasync getGPS28() {
				return GPS28;
			}
			
			public GPSasync getGPS29() {
				return GPS29;
			}
			
			public GPSasync getGPS30() {
				return GPS30;
			}
			
			public GPSasync getGPS31() {
				return GPS31;
			}
			
			public GPSasync getGPS32() {
				return GPS32;
			}
			

			public String getInQueue(){
				if (!isEventQueueEmpty()){
					String str="Input of components [";
					str=str+"\n    Voter : "+Voter.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS1_ : "+GPS1_.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS2_ : "+GPS2_.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS3_ : "+GPS3_.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS4_ : "+GPS4_.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS5_ : "+GPS5_.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS6_ : "+GPS6_.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS7_ : "+GPS7_.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS8_ : "+GPS8_.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS9_ : "+GPS9_.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS10 : "+GPS10.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS11 : "+GPS11.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS12 : "+GPS12.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS13 : "+GPS13.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS14 : "+GPS14.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS15 : "+GPS15.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS16 : "+GPS16.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS17 : "+GPS17.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS18 : "+GPS18.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS19 : "+GPS19.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS20 : "+GPS20.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS21 : "+GPS21.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS22 : "+GPS22.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS23 : "+GPS23.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS24 : "+GPS24.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS25 : "+GPS25.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS26 : "+GPS26.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS27 : "+GPS27.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS28 : "+GPS28.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS29 : "+GPS29.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS30 : "+GPS30.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS31 : "+GPS31.getInQueue().replace("    ","      ");
					
					str=str+"\n    GPS32 : "+GPS32.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
