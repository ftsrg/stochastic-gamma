		package hu.bme.mit.gamma.casestudy.iotsystem.iotsystem_param_4;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.casestudy.iotsystem.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_adapter_param.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_control.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_driver.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.network.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_component_param.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.communication_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.edge_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.cloud.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic_sct.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_software_param.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.casestudy.iotsystem.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class IoTSystemParametric_4 implements IoTSystemParametric_4Interface {
			// Component instances
			private CameraComponentParam camera1;
			private CameraComponentParam camera2;
			private CameraComponentParam camera3;
			private CameraComponentParam camera4;
			private EdgeAdapter edge;
			private RoadTraffic traffic;
			// Environmental Component instances
			// Port instances
			private Failures failures = new Failures();
			private CarLeave carLeave = new CarLeave();
			// Channel instances
			private DataStreamChannelInterface channelOutputDataOfCamera1;
			private DataStreamChannelInterface channelOutputDataOfCamera2;
			private DataStreamChannelInterface channelOutputDataOfCamera3;
			private DataStreamChannelInterface channelOutputDataOfCamera4;
			private TrafficEventStreamChannelInterface channelCarsOfTraffic;
			
			// Fields representing parameters
			private final double failureProb;
			
			protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
			protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
			
			public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
				envComponents.add(component);
				envMap.put(name,component);
			}
			
			public boolean isEventQueueEmpty(){
				return  camera1.isEventQueueEmpty()  &&  camera2.isEventQueueEmpty()  &&  camera3.isEventQueueEmpty()  &&  camera4.isEventQueueEmpty()  &&  edge.isEventQueueEmpty()  &&  traffic.isEventQueueEmpty()     ;
			}
			
			private final int MAX_SCHEDULE=15;
			
			public void schedule(){
				int cntr=0;
				do{
					camera1.schedule();
					camera2.schedule();
					camera3.schedule();
					camera4.schedule();
					edge.schedule();
					traffic.schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in IoTSystemParametric_4! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					camera1.schedule();
					camera2.schedule();
					camera3.schedule();
					camera4.schedule();
					edge.schedule();
					traffic.schedule();
					System.out.println(getInQueue());
					
				}
			}
			
			public IoTSystemParametric_4(double failureProb) {
				this.failureProb = failureProb;
				camera1 = new CameraComponentParam(failureProb);
				camera2 = new CameraComponentParam(failureProb);
				camera3 = new CameraComponentParam(failureProb);
				camera4 = new CameraComponentParam(failureProb);
				edge = new EdgeAdapter();
				traffic = new RoadTraffic();
				failures = new Failures(); 
				carLeave = new CarLeave(); 
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
				camera1.handleBeforeReset();
				camera2.handleBeforeReset();
				camera3.handleBeforeReset();
				camera4.handleBeforeReset();
				edge.handleBeforeReset();
				traffic.handleBeforeReset();
								//
			}

			public void resetVariables() {
				camera1.resetVariables();
				camera2.resetVariables();
				camera3.resetVariables();
				camera4.resetVariables();
				edge.resetVariables();
				traffic.resetVariables();
			}
			
			public void resetStateConfigurations() {
				camera1.resetStateConfigurations();
				camera2.resetStateConfigurations();
				camera3.resetStateConfigurations();
				camera4.resetStateConfigurations();
				edge.resetStateConfigurations();
				traffic.resetStateConfigurations();
			}
			
			public void raiseEntryEvents() {
				camera1.raiseEntryEvents();
				camera2.raiseEntryEvents();
				camera3.raiseEntryEvents();
				camera4.raiseEntryEvents();
				edge.raiseEntryEvents();
				traffic.raiseEntryEvents();
			}

			public void handleAfterReset() {
				camera1.handleAfterReset();
				camera2.handleAfterReset();
				camera3.handleAfterReset();
				camera4.handleAfterReset();
				edge.handleAfterReset();
				traffic.handleAfterReset();
				
				//camera1.schedule();
				//camera2.schedule();
				//camera3.schedule();
				//camera4.schedule();
				//edge.schedule();
				//traffic.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				channelOutputDataOfCamera2 = new DataStreamChannel(camera2.getOutputData());
				channelOutputDataOfCamera2.registerPort(edge.getCamera());
				channelOutputDataOfCamera3 = new DataStreamChannel(camera3.getOutputData());
				channelOutputDataOfCamera3.registerPort(edge.getCamera());
				channelOutputDataOfCamera4 = new DataStreamChannel(camera4.getOutputData());
				channelOutputDataOfCamera4.registerPort(edge.getCamera());
				channelOutputDataOfCamera1 = new DataStreamChannel(camera1.getOutputData());
				channelOutputDataOfCamera1.registerPort(edge.getCamera());
				// Registration of broadcast channels
				channelCarsOfTraffic = new TrafficEventStreamChannel(traffic.getCars());
					channelCarsOfTraffic.registerPort(camera3.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera2.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera4.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera1.getTrafficSensing());
					channelCarsOfTraffic.registerPort(edge.getTrafficStream());
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
					return edge.getCarLeave().isRaisedNewEvent();
				}
				
				
				@Override
				public void registerListener(EventStreamInterface.Listener.Provided listener) {
					edge.getCarLeave().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<EventStreamInterface.Listener.Provided> getRegisteredListeners() {
					List<EventStreamInterface.Listener.Provided> registeredListeners=new ArrayList<EventStreamInterface.Listener.Provided>();
					registeredListeners.addAll(edge.getCarLeave().getRegisteredListeners());
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
				camera1.start();
				camera2.start();
				camera3.start();
				camera4.start();
				edge.start();
				traffic.start();
			}
			
			public boolean isWaiting() {
				return camera1.isWaiting() && camera2.isWaiting() && camera3.isWaiting() && camera4.isWaiting() && edge.isWaiting() && traffic.isWaiting()
					;
			}
			
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			public CameraComponentParam getCamera1() {
				return camera1;
			}
			
			public CameraComponentParam getCamera2() {
				return camera2;
			}
			
			public CameraComponentParam getCamera3() {
				return camera3;
			}
			
			public CameraComponentParam getCamera4() {
				return camera4;
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
					str=str+"\n    camera1 : "+camera1.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera2 : "+camera2.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera3 : "+camera3.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera4 : "+camera4.getInQueue().replace("    ","      ");
					
					str=str+"\n    edge : "+edge.getInQueue().replace("    ","      ");
					
					str=str+"\n    traffic : "+traffic.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
