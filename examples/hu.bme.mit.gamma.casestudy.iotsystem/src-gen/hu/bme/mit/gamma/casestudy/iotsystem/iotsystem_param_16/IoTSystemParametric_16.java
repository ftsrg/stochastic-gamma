		package hu.bme.mit.gamma.casestudy.iotsystem.iotsystem_param_16;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.casestudy.iotsystem.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_control.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.edge_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.communication_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_driver.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.cloud.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic_sct.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_component_param.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_adapter_param.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_software_param.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.network.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.casestudy.iotsystem.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class IoTSystemParametric_16 implements IoTSystemParametric_16Interface {
			// Component instances
			private CameraComponentParam camera1;
			private CameraComponentParam camera2;
			private CameraComponentParam camera3;
			private CameraComponentParam camera4;
			private CameraComponentParam camera5;
			private CameraComponentParam camera6;
			private CameraComponentParam camera7;
			private CameraComponentParam camera8;
			private CameraComponentParam camera9;
			private CameraComponentParam camera10;
			private CameraComponentParam camera11;
			private CameraComponentParam camera12;
			private CameraComponentParam camera13;
			private CameraComponentParam camera14;
			private CameraComponentParam camera15;
			private CameraComponentParam camera16;
			private EdgeAdapter edge;
			private RoadTraffic traffic;
			// Environmental Component instances
			// Port instances
			private Failures failures = new Failures();
			private CarLeave carLeave = new CarLeave();
			// Channel instances
			private DataStreamChannelInterface channelOutputDataOfCamera8;
			private DataStreamChannelInterface channelOutputDataOfCamera4;
			private DataStreamChannelInterface channelOutputDataOfCamera3;
			private DataStreamChannelInterface channelOutputDataOfCamera14;
			private DataStreamChannelInterface channelOutputDataOfCamera6;
			private DataStreamChannelInterface channelOutputDataOfCamera12;
			private DataStreamChannelInterface channelOutputDataOfCamera2;
			private DataStreamChannelInterface channelOutputDataOfCamera10;
			private DataStreamChannelInterface channelOutputDataOfCamera16;
			private DataStreamChannelInterface channelOutputDataOfCamera7;
			private DataStreamChannelInterface channelOutputDataOfCamera11;
			private DataStreamChannelInterface channelOutputDataOfCamera9;
			private DataStreamChannelInterface channelOutputDataOfCamera15;
			private DataStreamChannelInterface channelOutputDataOfCamera5;
			private DataStreamChannelInterface channelOutputDataOfCamera1;
			private DataStreamChannelInterface channelOutputDataOfCamera13;
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
				return  camera1.isEventQueueEmpty()  &&  camera2.isEventQueueEmpty()  &&  camera3.isEventQueueEmpty()  &&  camera4.isEventQueueEmpty()  &&  camera5.isEventQueueEmpty()  &&  camera6.isEventQueueEmpty()  &&  camera7.isEventQueueEmpty()  &&  camera8.isEventQueueEmpty()  &&  camera9.isEventQueueEmpty()  &&  camera10.isEventQueueEmpty()  &&  camera11.isEventQueueEmpty()  &&  camera12.isEventQueueEmpty()  &&  camera13.isEventQueueEmpty()  &&  camera14.isEventQueueEmpty()  &&  camera15.isEventQueueEmpty()  &&  camera16.isEventQueueEmpty()  &&  edge.isEventQueueEmpty()  &&  traffic.isEventQueueEmpty()     ;
			}
			
			private final int MAX_SCHEDULE=15;
			
			public void schedule(){
				int cntr=0;
				do{
					camera1.schedule();
					camera2.schedule();
					camera3.schedule();
					camera4.schedule();
					camera5.schedule();
					camera6.schedule();
					camera7.schedule();
					camera8.schedule();
					camera9.schedule();
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
					System.out.println("Infinite scheduling in IoTSystemParametric_16! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					camera1.schedule();
					camera2.schedule();
					camera3.schedule();
					camera4.schedule();
					camera5.schedule();
					camera6.schedule();
					camera7.schedule();
					camera8.schedule();
					camera9.schedule();
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
			
			public IoTSystemParametric_16(double failureProb) {
				this.failureProb = failureProb;
				camera1 = new CameraComponentParam(failureProb);
				camera2 = new CameraComponentParam(failureProb);
				camera3 = new CameraComponentParam(failureProb);
				camera4 = new CameraComponentParam(failureProb);
				camera5 = new CameraComponentParam(failureProb);
				camera6 = new CameraComponentParam(failureProb);
				camera7 = new CameraComponentParam(failureProb);
				camera8 = new CameraComponentParam(failureProb);
				camera9 = new CameraComponentParam(failureProb);
				camera10 = new CameraComponentParam(failureProb);
				camera11 = new CameraComponentParam(failureProb);
				camera12 = new CameraComponentParam(failureProb);
				camera13 = new CameraComponentParam(failureProb);
				camera14 = new CameraComponentParam(failureProb);
				camera15 = new CameraComponentParam(failureProb);
				camera16 = new CameraComponentParam(failureProb);
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
				camera5.handleBeforeReset();
				camera6.handleBeforeReset();
				camera7.handleBeforeReset();
				camera8.handleBeforeReset();
				camera9.handleBeforeReset();
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
			}

			public void resetVariables() {
				camera1.resetVariables();
				camera2.resetVariables();
				camera3.resetVariables();
				camera4.resetVariables();
				camera5.resetVariables();
				camera6.resetVariables();
				camera7.resetVariables();
				camera8.resetVariables();
				camera9.resetVariables();
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
				camera1.resetStateConfigurations();
				camera2.resetStateConfigurations();
				camera3.resetStateConfigurations();
				camera4.resetStateConfigurations();
				camera5.resetStateConfigurations();
				camera6.resetStateConfigurations();
				camera7.resetStateConfigurations();
				camera8.resetStateConfigurations();
				camera9.resetStateConfigurations();
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
				camera1.raiseEntryEvents();
				camera2.raiseEntryEvents();
				camera3.raiseEntryEvents();
				camera4.raiseEntryEvents();
				camera5.raiseEntryEvents();
				camera6.raiseEntryEvents();
				camera7.raiseEntryEvents();
				camera8.raiseEntryEvents();
				camera9.raiseEntryEvents();
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
				camera1.handleAfterReset();
				camera2.handleAfterReset();
				camera3.handleAfterReset();
				camera4.handleAfterReset();
				camera5.handleAfterReset();
				camera6.handleAfterReset();
				camera7.handleAfterReset();
				camera8.handleAfterReset();
				camera9.handleAfterReset();
				camera10.handleAfterReset();
				camera11.handleAfterReset();
				camera12.handleAfterReset();
				camera13.handleAfterReset();
				camera14.handleAfterReset();
				camera15.handleAfterReset();
				camera16.handleAfterReset();
				edge.handleAfterReset();
				traffic.handleAfterReset();
				
				//camera1.schedule();
				//camera2.schedule();
				//camera3.schedule();
				//camera4.schedule();
				//camera5.schedule();
				//camera6.schedule();
				//camera7.schedule();
				//camera8.schedule();
				//camera9.schedule();
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
				channelOutputDataOfCamera16 = new DataStreamChannel(camera16.getOutputData());
				channelOutputDataOfCamera16.registerPort(edge.getCamera());
				channelOutputDataOfCamera9 = new DataStreamChannel(camera9.getOutputData());
				channelOutputDataOfCamera9.registerPort(edge.getCamera());
				channelOutputDataOfCamera5 = new DataStreamChannel(camera5.getOutputData());
				channelOutputDataOfCamera5.registerPort(edge.getCamera());
				channelOutputDataOfCamera14 = new DataStreamChannel(camera14.getOutputData());
				channelOutputDataOfCamera14.registerPort(edge.getCamera());
				channelOutputDataOfCamera7 = new DataStreamChannel(camera7.getOutputData());
				channelOutputDataOfCamera7.registerPort(edge.getCamera());
				channelOutputDataOfCamera13 = new DataStreamChannel(camera13.getOutputData());
				channelOutputDataOfCamera13.registerPort(edge.getCamera());
				channelOutputDataOfCamera1 = new DataStreamChannel(camera1.getOutputData());
				channelOutputDataOfCamera1.registerPort(edge.getCamera());
				channelOutputDataOfCamera11 = new DataStreamChannel(camera11.getOutputData());
				channelOutputDataOfCamera11.registerPort(edge.getCamera());
				channelOutputDataOfCamera10 = new DataStreamChannel(camera10.getOutputData());
				channelOutputDataOfCamera10.registerPort(edge.getCamera());
				channelOutputDataOfCamera4 = new DataStreamChannel(camera4.getOutputData());
				channelOutputDataOfCamera4.registerPort(edge.getCamera());
				channelOutputDataOfCamera2 = new DataStreamChannel(camera2.getOutputData());
				channelOutputDataOfCamera2.registerPort(edge.getCamera());
				channelOutputDataOfCamera12 = new DataStreamChannel(camera12.getOutputData());
				channelOutputDataOfCamera12.registerPort(edge.getCamera());
				channelOutputDataOfCamera15 = new DataStreamChannel(camera15.getOutputData());
				channelOutputDataOfCamera15.registerPort(edge.getCamera());
				channelOutputDataOfCamera8 = new DataStreamChannel(camera8.getOutputData());
				channelOutputDataOfCamera8.registerPort(edge.getCamera());
				channelOutputDataOfCamera3 = new DataStreamChannel(camera3.getOutputData());
				channelOutputDataOfCamera3.registerPort(edge.getCamera());
				channelOutputDataOfCamera6 = new DataStreamChannel(camera6.getOutputData());
				channelOutputDataOfCamera6.registerPort(edge.getCamera());
				// Registration of broadcast channels
				channelCarsOfTraffic = new TrafficEventStreamChannel(traffic.getCars());
					channelCarsOfTraffic.registerPort(camera3.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera13.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera15.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera12.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera16.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera10.getTrafficSensing());
					channelCarsOfTraffic.registerPort(edge.getTrafficStream());
					channelCarsOfTraffic.registerPort(camera5.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera11.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera4.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera14.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera9.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera2.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera6.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera7.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera1.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera8.getTrafficSensing());
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
				camera5.start();
				camera6.start();
				camera7.start();
				camera8.start();
				camera9.start();
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
				return camera1.isWaiting() && camera2.isWaiting() && camera3.isWaiting() && camera4.isWaiting() && camera5.isWaiting() && camera6.isWaiting() && camera7.isWaiting() && camera8.isWaiting() && camera9.isWaiting() && camera10.isWaiting() && camera11.isWaiting() && camera12.isWaiting() && camera13.isWaiting() && camera14.isWaiting() && camera15.isWaiting() && camera16.isWaiting() && edge.isWaiting() && traffic.isWaiting()
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
			
			public CameraComponentParam getCamera5() {
				return camera5;
			}
			
			public CameraComponentParam getCamera6() {
				return camera6;
			}
			
			public CameraComponentParam getCamera7() {
				return camera7;
			}
			
			public CameraComponentParam getCamera8() {
				return camera8;
			}
			
			public CameraComponentParam getCamera9() {
				return camera9;
			}
			
			public CameraComponentParam getCamera10() {
				return camera10;
			}
			
			public CameraComponentParam getCamera11() {
				return camera11;
			}
			
			public CameraComponentParam getCamera12() {
				return camera12;
			}
			
			public CameraComponentParam getCamera13() {
				return camera13;
			}
			
			public CameraComponentParam getCamera14() {
				return camera14;
			}
			
			public CameraComponentParam getCamera15() {
				return camera15;
			}
			
			public CameraComponentParam getCamera16() {
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
					str=str+"\n    camera1 : "+camera1.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera2 : "+camera2.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera3 : "+camera3.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera4 : "+camera4.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera5 : "+camera5.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera6 : "+camera6.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera7 : "+camera7.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera8 : "+camera8.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera9 : "+camera9.getInQueue().replace("    ","      ");
					
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
