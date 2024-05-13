		package hu.bme.mit.gamma.casestudy.iotsystem.iotsystem_16;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.casestudy.iotsystem.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_control.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_component.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_driver.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.edge_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic_sct.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.communication_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.cloud.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_software.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.network.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.casestudy.iotsystem.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class IoTSystem_16 implements IoTSystem_16Interface {
			// Component instances
			private CameraComponent camera1_;
			private CameraComponent camera2_;
			private CameraComponent camera3_;
			private CameraComponent camera4_;
			private CameraComponent camera5_;
			private CameraComponent camera6_;
			private CameraComponent camera7_;
			private CameraComponent camera8_;
			private CameraComponent camera9_;
			private CameraComponent camera10;
			private CameraComponent camera11;
			private CameraComponent camera12;
			private CameraComponent camera13;
			private CameraComponent camera14;
			private CameraComponent camera15;
			private CameraComponent camera16;
			private EdgeAdapter edge;
			private RoadTraffic traffic;
			// Environmental Component instances
			// Port instances
			private Failures failures = new Failures();
			private CarLeave carLeave = new CarLeave();
			// Channel instances
			private DataStreamChannelInterface channelOutputDataOfCamera11;
			private DataStreamChannelInterface channelOutputDataOfCamera2_;
			private DataStreamChannelInterface channelOutputDataOfCamera1_;
			private DataStreamChannelInterface channelOutputDataOfCamera14;
			private DataStreamChannelInterface channelOutputDataOfCamera3_;
			private DataStreamChannelInterface channelOutputDataOfCamera4_;
			private DataStreamChannelInterface channelOutputDataOfCamera7_;
			private DataStreamChannelInterface channelOutputDataOfCamera15;
			private DataStreamChannelInterface channelOutputDataOfCamera16;
			private DataStreamChannelInterface channelOutputDataOfCamera12;
			private DataStreamChannelInterface channelOutputDataOfCamera13;
			private DataStreamChannelInterface channelOutputDataOfCamera9_;
			private DataStreamChannelInterface channelOutputDataOfCamera6_;
			private DataStreamChannelInterface channelOutputDataOfCamera5_;
			private DataStreamChannelInterface channelOutputDataOfCamera8_;
			private DataStreamChannelInterface channelOutputDataOfCamera10;
			private TrafficEventStreamChannelInterface channelCarsOfTraffic;
			
			
			protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
			protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
			
			public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
				envComponents.add(component);
				envMap.put(name,component);
			}
			
			public boolean isEventQueueEmpty(){
				return  camera1_.isEventQueueEmpty()  &&  camera2_.isEventQueueEmpty()  &&  camera3_.isEventQueueEmpty()  &&  camera4_.isEventQueueEmpty()  &&  camera5_.isEventQueueEmpty()  &&  camera6_.isEventQueueEmpty()  &&  camera7_.isEventQueueEmpty()  &&  camera8_.isEventQueueEmpty()  &&  camera9_.isEventQueueEmpty()  &&  camera10.isEventQueueEmpty()  &&  camera11.isEventQueueEmpty()  &&  camera12.isEventQueueEmpty()  &&  camera13.isEventQueueEmpty()  &&  camera14.isEventQueueEmpty()  &&  camera15.isEventQueueEmpty()  &&  camera16.isEventQueueEmpty()  &&  edge.isEventQueueEmpty()  &&  traffic.isEventQueueEmpty()     ;
			}
			
			private final int MAX_SCHEDULE=15;
			
			public void schedule(){
				int cntr=0;
				do{
					camera1_.schedule();
					camera2_.schedule();
					camera3_.schedule();
					camera4_.schedule();
					camera5_.schedule();
					camera6_.schedule();
					camera7_.schedule();
					camera8_.schedule();
					camera9_.schedule();
					camera10.schedule();
					camera11.schedule();
					camera12.schedule();
					camera13.schedule();
					camera14.schedule();
					camera15.schedule();
					camera16.schedule();
					edge.schedule();
					traffic.schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in IoTSystem_16! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					camera1_.schedule();
					camera2_.schedule();
					camera3_.schedule();
					camera4_.schedule();
					camera5_.schedule();
					camera6_.schedule();
					camera7_.schedule();
					camera8_.schedule();
					camera9_.schedule();
					camera10.schedule();
					camera11.schedule();
					camera12.schedule();
					camera13.schedule();
					camera14.schedule();
					camera15.schedule();
					camera16.schedule();
					edge.schedule();
					traffic.schedule();
					System.out.println(getInQueue());
					
				}
			}
			
			public IoTSystem_16() {
				camera1_ = new CameraComponent();
				camera2_ = new CameraComponent();
				camera3_ = new CameraComponent();
				camera4_ = new CameraComponent();
				camera5_ = new CameraComponent();
				camera6_ = new CameraComponent();
				camera7_ = new CameraComponent();
				camera8_ = new CameraComponent();
				camera9_ = new CameraComponent();
				camera10 = new CameraComponent();
				camera11 = new CameraComponent();
				camera12 = new CameraComponent();
				camera13 = new CameraComponent();
				camera14 = new CameraComponent();
				camera15 = new CameraComponent();
				camera16 = new CameraComponent();
				edge = new EdgeAdapter();
				traffic = new RoadTraffic();
				failures = new Failures(); 
				carLeave = new CarLeave(); // Unbound
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
				camera1_.handleBeforeReset();
				camera2_.handleBeforeReset();
				camera3_.handleBeforeReset();
				camera4_.handleBeforeReset();
				camera5_.handleBeforeReset();
				camera6_.handleBeforeReset();
				camera7_.handleBeforeReset();
				camera8_.handleBeforeReset();
				camera9_.handleBeforeReset();
				camera10.handleBeforeReset();
				camera11.handleBeforeReset();
				camera12.handleBeforeReset();
				camera13.handleBeforeReset();
				camera14.handleBeforeReset();
				camera15.handleBeforeReset();
				camera16.handleBeforeReset();
				edge.handleBeforeReset();
				traffic.handleBeforeReset();
								//
				carLeave.reset();
			}

			public void resetVariables() {
				camera1_.resetVariables();
				camera2_.resetVariables();
				camera3_.resetVariables();
				camera4_.resetVariables();
				camera5_.resetVariables();
				camera6_.resetVariables();
				camera7_.resetVariables();
				camera8_.resetVariables();
				camera9_.resetVariables();
				camera10.resetVariables();
				camera11.resetVariables();
				camera12.resetVariables();
				camera13.resetVariables();
				camera14.resetVariables();
				camera15.resetVariables();
				camera16.resetVariables();
				edge.resetVariables();
				traffic.resetVariables();
			}
			
			public void resetStateConfigurations() {
				camera1_.resetStateConfigurations();
				camera2_.resetStateConfigurations();
				camera3_.resetStateConfigurations();
				camera4_.resetStateConfigurations();
				camera5_.resetStateConfigurations();
				camera6_.resetStateConfigurations();
				camera7_.resetStateConfigurations();
				camera8_.resetStateConfigurations();
				camera9_.resetStateConfigurations();
				camera10.resetStateConfigurations();
				camera11.resetStateConfigurations();
				camera12.resetStateConfigurations();
				camera13.resetStateConfigurations();
				camera14.resetStateConfigurations();
				camera15.resetStateConfigurations();
				camera16.resetStateConfigurations();
				edge.resetStateConfigurations();
				traffic.resetStateConfigurations();
			}
			
			public void raiseEntryEvents() {
				camera1_.raiseEntryEvents();
				camera2_.raiseEntryEvents();
				camera3_.raiseEntryEvents();
				camera4_.raiseEntryEvents();
				camera5_.raiseEntryEvents();
				camera6_.raiseEntryEvents();
				camera7_.raiseEntryEvents();
				camera8_.raiseEntryEvents();
				camera9_.raiseEntryEvents();
				camera10.raiseEntryEvents();
				camera11.raiseEntryEvents();
				camera12.raiseEntryEvents();
				camera13.raiseEntryEvents();
				camera14.raiseEntryEvents();
				camera15.raiseEntryEvents();
				camera16.raiseEntryEvents();
				edge.raiseEntryEvents();
				traffic.raiseEntryEvents();
			}

			public void handleAfterReset() {
				camera1_.handleAfterReset();
				camera2_.handleAfterReset();
				camera3_.handleAfterReset();
				camera4_.handleAfterReset();
				camera5_.handleAfterReset();
				camera6_.handleAfterReset();
				camera7_.handleAfterReset();
				camera8_.handleAfterReset();
				camera9_.handleAfterReset();
				camera10.handleAfterReset();
				camera11.handleAfterReset();
				camera12.handleAfterReset();
				camera13.handleAfterReset();
				camera14.handleAfterReset();
				camera15.handleAfterReset();
				camera16.handleAfterReset();
				edge.handleAfterReset();
				traffic.handleAfterReset();
				
				//camera1_.schedule();
				//camera2_.schedule();
				//camera3_.schedule();
				//camera4_.schedule();
				//camera5_.schedule();
				//camera6_.schedule();
				//camera7_.schedule();
				//camera8_.schedule();
				//camera9_.schedule();
				//camera10.schedule();
				//camera11.schedule();
				//camera12.schedule();
				//camera13.schedule();
				//camera14.schedule();
				//camera15.schedule();
				//camera16.schedule();
				//edge.schedule();
				//traffic.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				channelOutputDataOfCamera14 = new DataStreamChannel(camera14.getOutputData());
				channelOutputDataOfCamera14.registerPort(edge.getCamera());
				channelOutputDataOfCamera8_ = new DataStreamChannel(camera8_.getOutputData());
				channelOutputDataOfCamera8_.registerPort(edge.getCamera());
				channelOutputDataOfCamera11 = new DataStreamChannel(camera11.getOutputData());
				channelOutputDataOfCamera11.registerPort(edge.getCamera());
				channelOutputDataOfCamera13 = new DataStreamChannel(camera13.getOutputData());
				channelOutputDataOfCamera13.registerPort(edge.getCamera());
				channelOutputDataOfCamera5_ = new DataStreamChannel(camera5_.getOutputData());
				channelOutputDataOfCamera5_.registerPort(edge.getCamera());
				channelOutputDataOfCamera16 = new DataStreamChannel(camera16.getOutputData());
				channelOutputDataOfCamera16.registerPort(edge.getCamera());
				channelOutputDataOfCamera6_ = new DataStreamChannel(camera6_.getOutputData());
				channelOutputDataOfCamera6_.registerPort(edge.getCamera());
				channelOutputDataOfCamera10 = new DataStreamChannel(camera10.getOutputData());
				channelOutputDataOfCamera10.registerPort(edge.getCamera());
				channelOutputDataOfCamera7_ = new DataStreamChannel(camera7_.getOutputData());
				channelOutputDataOfCamera7_.registerPort(edge.getCamera());
				channelOutputDataOfCamera4_ = new DataStreamChannel(camera4_.getOutputData());
				channelOutputDataOfCamera4_.registerPort(edge.getCamera());
				channelOutputDataOfCamera12 = new DataStreamChannel(camera12.getOutputData());
				channelOutputDataOfCamera12.registerPort(edge.getCamera());
				channelOutputDataOfCamera2_ = new DataStreamChannel(camera2_.getOutputData());
				channelOutputDataOfCamera2_.registerPort(edge.getCamera());
				channelOutputDataOfCamera9_ = new DataStreamChannel(camera9_.getOutputData());
				channelOutputDataOfCamera9_.registerPort(edge.getCamera());
				channelOutputDataOfCamera1_ = new DataStreamChannel(camera1_.getOutputData());
				channelOutputDataOfCamera1_.registerPort(edge.getCamera());
				channelOutputDataOfCamera3_ = new DataStreamChannel(camera3_.getOutputData());
				channelOutputDataOfCamera3_.registerPort(edge.getCamera());
				channelOutputDataOfCamera15 = new DataStreamChannel(camera15.getOutputData());
				channelOutputDataOfCamera15.registerPort(edge.getCamera());
				// Registration of broadcast channels
				channelCarsOfTraffic = new TrafficEventStreamChannel(traffic.getCars());
					channelCarsOfTraffic.registerPort(camera16.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera12.getTrafficSensing());
					channelCarsOfTraffic.registerPort(edge.getTrafficStream());
					channelCarsOfTraffic.registerPort(camera13.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera3_.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera6_.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera5_.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera2_.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera4_.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera10.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera8_.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera14.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera1_.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera11.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera15.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera7_.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera9_.getTrafficSensing());
			}
			
			// Inner classes representing Ports
			public class Failures implements EventStreamInterface.Provided {
				
				
				List<EventStreamInterface.Listener.Provided> registeredListeners=new ArrayList<EventStreamInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedNewEvent() {
					return edge.getLostImage().isRaisedNewEvent();
				}
				
				
				@Override
				public void registerListener(EventStreamInterface.Listener.Provided listener) {
					edge.getLostImage().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<EventStreamInterface.Listener.Provided> getRegisteredListeners() {
					List<EventStreamInterface.Listener.Provided> registeredListeners=new ArrayList<EventStreamInterface.Listener.Provided>();
					registeredListeners.addAll(edge.getLostImage().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public Failures getFailures() {
				return failures;
			}
			
			public class CarLeave implements EventStreamInterface.Provided {
				
				
				List<EventStreamInterface.Listener.Provided> registeredListeners=new ArrayList<EventStreamInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedNewEvent() {
					return false;
				}
				
				public void raiseNewEvent(){
					for (EventStreamInterface.Listener.Provided listener : registeredListeners){
						listener.raiseNewEvent();
					}
				}
				public void reset(){
				}
				
				@Override
				public void registerListener(EventStreamInterface.Listener.Provided listener) {
					registeredListeners.add(listener);
				}
				
				@Override
				public List<EventStreamInterface.Listener.Provided> getRegisteredListeners() {
					List<EventStreamInterface.Listener.Provided> registeredListeners=new ArrayList<EventStreamInterface.Listener.Provided>();
					return registeredListeners;
				}
				
			}
			
			@Override
			public CarLeave getCarLeave() {
				return carLeave;
			}
			
			/** Starts the running of the asynchronous component. */
			@Override
			public void start() {
				camera1_.start();
				camera2_.start();
				camera3_.start();
				camera4_.start();
				camera5_.start();
				camera6_.start();
				camera7_.start();
				camera8_.start();
				camera9_.start();
				camera10.start();
				camera11.start();
				camera12.start();
				camera13.start();
				camera14.start();
				camera15.start();
				camera16.start();
				edge.start();
				traffic.start();
			}
			
			public boolean isWaiting() {
				return camera1_.isWaiting() && camera2_.isWaiting() && camera3_.isWaiting() && camera4_.isWaiting() && camera5_.isWaiting() && camera6_.isWaiting() && camera7_.isWaiting() && camera8_.isWaiting() && camera9_.isWaiting() && camera10.isWaiting() && camera11.isWaiting() && camera12.isWaiting() && camera13.isWaiting() && camera14.isWaiting() && camera15.isWaiting() && camera16.isWaiting() && edge.isWaiting() && traffic.isWaiting()
					;
			}
			
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			public CameraComponent getCamera1_() {
				return camera1_;
			}
			
			public CameraComponent getCamera2_() {
				return camera2_;
			}
			
			public CameraComponent getCamera3_() {
				return camera3_;
			}
			
			public CameraComponent getCamera4_() {
				return camera4_;
			}
			
			public CameraComponent getCamera5_() {
				return camera5_;
			}
			
			public CameraComponent getCamera6_() {
				return camera6_;
			}
			
			public CameraComponent getCamera7_() {
				return camera7_;
			}
			
			public CameraComponent getCamera8_() {
				return camera8_;
			}
			
			public CameraComponent getCamera9_() {
				return camera9_;
			}
			
			public CameraComponent getCamera10() {
				return camera10;
			}
			
			public CameraComponent getCamera11() {
				return camera11;
			}
			
			public CameraComponent getCamera12() {
				return camera12;
			}
			
			public CameraComponent getCamera13() {
				return camera13;
			}
			
			public CameraComponent getCamera14() {
				return camera14;
			}
			
			public CameraComponent getCamera15() {
				return camera15;
			}
			
			public CameraComponent getCamera16() {
				return camera16;
			}
			
			public EdgeAdapter getEdge() {
				return edge;
			}
			
			public RoadTraffic getTraffic() {
				return traffic;
			}
			

			public String getInQueue(){
				if (!isEventQueueEmpty()){
					String str="Input of components [";
					str=str+"\n    camera1_ : "+camera1_.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera2_ : "+camera2_.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera3_ : "+camera3_.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera4_ : "+camera4_.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera5_ : "+camera5_.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera6_ : "+camera6_.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera7_ : "+camera7_.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera8_ : "+camera8_.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera9_ : "+camera9_.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera10 : "+camera10.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera11 : "+camera11.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera12 : "+camera12.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera13 : "+camera13.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera14 : "+camera14.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera15 : "+camera15.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera16 : "+camera16.getInQueue().replace("    ","      ");
					
					str=str+"\n    edge : "+edge.getInQueue().replace("    ","      ");
					
					str=str+"\n    traffic : "+traffic.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
