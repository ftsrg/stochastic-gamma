		package hu.bme.mit.gamma.casestudy.iotsystem.iotsystem_param_32;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.casestudy.iotsystem.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_control.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_software_param.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_component_param.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.communication_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_adapter_param.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.camera_driver.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.cloud.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.edge_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.network.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic_sct.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.casestudy.iotsystem.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class IoTSystemParametric_32 implements IoTSystemParametric_32Interface {
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
			private CameraComponentParam camera17;
			private CameraComponentParam camera18;
			private CameraComponentParam camera19;
			private CameraComponentParam camera20;
			private CameraComponentParam camera21;
			private CameraComponentParam camera22;
			private CameraComponentParam camera23;
			private CameraComponentParam camera24;
			private CameraComponentParam camera25;
			private CameraComponentParam camera26;
			private CameraComponentParam camera27;
			private CameraComponentParam camera28;
			private CameraComponentParam camera29;
			private CameraComponentParam camera30;
			private CameraComponentParam camera31;
			private CameraComponentParam camera32;
			private EdgeAdapter edge;
			private RoadTraffic traffic;
			// Environmental Component instances
			// Port instances
			private Failures failures = new Failures();
			private CarLeave carLeave = new CarLeave();
			// Channel instances
			private DataStreamChannelInterface channelOutputDataOfCamera14;
			private DataStreamChannelInterface channelOutputDataOfCamera21;
			private DataStreamChannelInterface channelOutputDataOfCamera6;
			private DataStreamChannelInterface channelOutputDataOfCamera11;
			private DataStreamChannelInterface channelOutputDataOfCamera22;
			private DataStreamChannelInterface channelOutputDataOfCamera13;
			private DataStreamChannelInterface channelOutputDataOfCamera31;
			private DataStreamChannelInterface channelOutputDataOfCamera5;
			private DataStreamChannelInterface channelOutputDataOfCamera24;
			private DataStreamChannelInterface channelOutputDataOfCamera27;
			private DataStreamChannelInterface channelOutputDataOfCamera15;
			private DataStreamChannelInterface channelOutputDataOfCamera23;
			private DataStreamChannelInterface channelOutputDataOfCamera25;
			private DataStreamChannelInterface channelOutputDataOfCamera19;
			private DataStreamChannelInterface channelOutputDataOfCamera28;
			private DataStreamChannelInterface channelOutputDataOfCamera17;
			private DataStreamChannelInterface channelOutputDataOfCamera8;
			private DataStreamChannelInterface channelOutputDataOfCamera32;
			private DataStreamChannelInterface channelOutputDataOfCamera4;
			private DataStreamChannelInterface channelOutputDataOfCamera2;
			private DataStreamChannelInterface channelOutputDataOfCamera9;
			private DataStreamChannelInterface channelOutputDataOfCamera18;
			private DataStreamChannelInterface channelOutputDataOfCamera29;
			private DataStreamChannelInterface channelOutputDataOfCamera7;
			private DataStreamChannelInterface channelOutputDataOfCamera10;
			private DataStreamChannelInterface channelOutputDataOfCamera30;
			private DataStreamChannelInterface channelOutputDataOfCamera3;
			private DataStreamChannelInterface channelOutputDataOfCamera20;
			private DataStreamChannelInterface channelOutputDataOfCamera1;
			private DataStreamChannelInterface channelOutputDataOfCamera26;
			private DataStreamChannelInterface channelOutputDataOfCamera16;
			private DataStreamChannelInterface channelOutputDataOfCamera12;
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
				return  camera1.isEventQueueEmpty()  &&  camera2.isEventQueueEmpty()  &&  camera3.isEventQueueEmpty()  &&  camera4.isEventQueueEmpty()  &&  camera5.isEventQueueEmpty()  &&  camera6.isEventQueueEmpty()  &&  camera7.isEventQueueEmpty()  &&  camera8.isEventQueueEmpty()  &&  camera9.isEventQueueEmpty()  &&  camera10.isEventQueueEmpty()  &&  camera11.isEventQueueEmpty()  &&  camera12.isEventQueueEmpty()  &&  camera13.isEventQueueEmpty()  &&  camera14.isEventQueueEmpty()  &&  camera15.isEventQueueEmpty()  &&  camera16.isEventQueueEmpty()  &&  camera17.isEventQueueEmpty()  &&  camera18.isEventQueueEmpty()  &&  camera19.isEventQueueEmpty()  &&  camera20.isEventQueueEmpty()  &&  camera21.isEventQueueEmpty()  &&  camera22.isEventQueueEmpty()  &&  camera23.isEventQueueEmpty()  &&  camera24.isEventQueueEmpty()  &&  camera25.isEventQueueEmpty()  &&  camera26.isEventQueueEmpty()  &&  camera27.isEventQueueEmpty()  &&  camera28.isEventQueueEmpty()  &&  camera29.isEventQueueEmpty()  &&  camera30.isEventQueueEmpty()  &&  camera31.isEventQueueEmpty()  &&  camera32.isEventQueueEmpty()  &&  edge.isEventQueueEmpty()  &&  traffic.isEventQueueEmpty()     ;
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
					camera17.schedule();
					camera18.schedule();
					camera19.schedule();
					camera20.schedule();
					camera21.schedule();
					camera22.schedule();
					camera23.schedule();
					camera24.schedule();
					camera25.schedule();
					camera26.schedule();
					camera27.schedule();
					camera28.schedule();
					camera29.schedule();
					camera30.schedule();
					camera31.schedule();
					camera32.schedule();
					edge.schedule();
					traffic.schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in IoTSystemParametric_32! -----------");
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
					camera17.schedule();
					camera18.schedule();
					camera19.schedule();
					camera20.schedule();
					camera21.schedule();
					camera22.schedule();
					camera23.schedule();
					camera24.schedule();
					camera25.schedule();
					camera26.schedule();
					camera27.schedule();
					camera28.schedule();
					camera29.schedule();
					camera30.schedule();
					camera31.schedule();
					camera32.schedule();
					edge.schedule();
					traffic.schedule();
					System.out.println(getInQueue());
					
				}
			}
			
			public IoTSystemParametric_32(double failureProb) {
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
				camera17 = new CameraComponentParam(failureProb);
				camera18 = new CameraComponentParam(failureProb);
				camera19 = new CameraComponentParam(failureProb);
				camera20 = new CameraComponentParam(failureProb);
				camera21 = new CameraComponentParam(failureProb);
				camera22 = new CameraComponentParam(failureProb);
				camera23 = new CameraComponentParam(failureProb);
				camera24 = new CameraComponentParam(failureProb);
				camera25 = new CameraComponentParam(failureProb);
				camera26 = new CameraComponentParam(failureProb);
				camera27 = new CameraComponentParam(failureProb);
				camera28 = new CameraComponentParam(failureProb);
				camera29 = new CameraComponentParam(failureProb);
				camera30 = new CameraComponentParam(failureProb);
				camera31 = new CameraComponentParam(failureProb);
				camera32 = new CameraComponentParam(failureProb);
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
				camera17.handleBeforeReset();
				camera18.handleBeforeReset();
				camera19.handleBeforeReset();
				camera20.handleBeforeReset();
				camera21.handleBeforeReset();
				camera22.handleBeforeReset();
				camera23.handleBeforeReset();
				camera24.handleBeforeReset();
				camera25.handleBeforeReset();
				camera26.handleBeforeReset();
				camera27.handleBeforeReset();
				camera28.handleBeforeReset();
				camera29.handleBeforeReset();
				camera30.handleBeforeReset();
				camera31.handleBeforeReset();
				camera32.handleBeforeReset();
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
				camera17.resetVariables();
				camera18.resetVariables();
				camera19.resetVariables();
				camera20.resetVariables();
				camera21.resetVariables();
				camera22.resetVariables();
				camera23.resetVariables();
				camera24.resetVariables();
				camera25.resetVariables();
				camera26.resetVariables();
				camera27.resetVariables();
				camera28.resetVariables();
				camera29.resetVariables();
				camera30.resetVariables();
				camera31.resetVariables();
				camera32.resetVariables();
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
				camera17.resetStateConfigurations();
				camera18.resetStateConfigurations();
				camera19.resetStateConfigurations();
				camera20.resetStateConfigurations();
				camera21.resetStateConfigurations();
				camera22.resetStateConfigurations();
				camera23.resetStateConfigurations();
				camera24.resetStateConfigurations();
				camera25.resetStateConfigurations();
				camera26.resetStateConfigurations();
				camera27.resetStateConfigurations();
				camera28.resetStateConfigurations();
				camera29.resetStateConfigurations();
				camera30.resetStateConfigurations();
				camera31.resetStateConfigurations();
				camera32.resetStateConfigurations();
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
				camera17.raiseEntryEvents();
				camera18.raiseEntryEvents();
				camera19.raiseEntryEvents();
				camera20.raiseEntryEvents();
				camera21.raiseEntryEvents();
				camera22.raiseEntryEvents();
				camera23.raiseEntryEvents();
				camera24.raiseEntryEvents();
				camera25.raiseEntryEvents();
				camera26.raiseEntryEvents();
				camera27.raiseEntryEvents();
				camera28.raiseEntryEvents();
				camera29.raiseEntryEvents();
				camera30.raiseEntryEvents();
				camera31.raiseEntryEvents();
				camera32.raiseEntryEvents();
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
				camera17.handleAfterReset();
				camera18.handleAfterReset();
				camera19.handleAfterReset();
				camera20.handleAfterReset();
				camera21.handleAfterReset();
				camera22.handleAfterReset();
				camera23.handleAfterReset();
				camera24.handleAfterReset();
				camera25.handleAfterReset();
				camera26.handleAfterReset();
				camera27.handleAfterReset();
				camera28.handleAfterReset();
				camera29.handleAfterReset();
				camera30.handleAfterReset();
				camera31.handleAfterReset();
				camera32.handleAfterReset();
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
				//camera17.schedule();
				//camera18.schedule();
				//camera19.schedule();
				//camera20.schedule();
				//camera21.schedule();
				//camera22.schedule();
				//camera23.schedule();
				//camera24.schedule();
				//camera25.schedule();
				//camera26.schedule();
				//camera27.schedule();
				//camera28.schedule();
				//camera29.schedule();
				//camera30.schedule();
				//camera31.schedule();
				//camera32.schedule();
				//edge.schedule();
				//traffic.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				channelOutputDataOfCamera6 = new DataStreamChannel(camera6.getOutputData());
				channelOutputDataOfCamera6.registerPort(edge.getCamera());
				channelOutputDataOfCamera21 = new DataStreamChannel(camera21.getOutputData());
				channelOutputDataOfCamera21.registerPort(edge.getCamera());
				channelOutputDataOfCamera24 = new DataStreamChannel(camera24.getOutputData());
				channelOutputDataOfCamera24.registerPort(edge.getCamera());
				channelOutputDataOfCamera11 = new DataStreamChannel(camera11.getOutputData());
				channelOutputDataOfCamera11.registerPort(edge.getCamera());
				channelOutputDataOfCamera5 = new DataStreamChannel(camera5.getOutputData());
				channelOutputDataOfCamera5.registerPort(edge.getCamera());
				channelOutputDataOfCamera2 = new DataStreamChannel(camera2.getOutputData());
				channelOutputDataOfCamera2.registerPort(edge.getCamera());
				channelOutputDataOfCamera8 = new DataStreamChannel(camera8.getOutputData());
				channelOutputDataOfCamera8.registerPort(edge.getCamera());
				channelOutputDataOfCamera7 = new DataStreamChannel(camera7.getOutputData());
				channelOutputDataOfCamera7.registerPort(edge.getCamera());
				channelOutputDataOfCamera32 = new DataStreamChannel(camera32.getOutputData());
				channelOutputDataOfCamera32.registerPort(edge.getCamera());
				channelOutputDataOfCamera30 = new DataStreamChannel(camera30.getOutputData());
				channelOutputDataOfCamera30.registerPort(edge.getCamera());
				channelOutputDataOfCamera23 = new DataStreamChannel(camera23.getOutputData());
				channelOutputDataOfCamera23.registerPort(edge.getCamera());
				channelOutputDataOfCamera3 = new DataStreamChannel(camera3.getOutputData());
				channelOutputDataOfCamera3.registerPort(edge.getCamera());
				channelOutputDataOfCamera4 = new DataStreamChannel(camera4.getOutputData());
				channelOutputDataOfCamera4.registerPort(edge.getCamera());
				channelOutputDataOfCamera29 = new DataStreamChannel(camera29.getOutputData());
				channelOutputDataOfCamera29.registerPort(edge.getCamera());
				channelOutputDataOfCamera10 = new DataStreamChannel(camera10.getOutputData());
				channelOutputDataOfCamera10.registerPort(edge.getCamera());
				channelOutputDataOfCamera18 = new DataStreamChannel(camera18.getOutputData());
				channelOutputDataOfCamera18.registerPort(edge.getCamera());
				channelOutputDataOfCamera12 = new DataStreamChannel(camera12.getOutputData());
				channelOutputDataOfCamera12.registerPort(edge.getCamera());
				channelOutputDataOfCamera25 = new DataStreamChannel(camera25.getOutputData());
				channelOutputDataOfCamera25.registerPort(edge.getCamera());
				channelOutputDataOfCamera13 = new DataStreamChannel(camera13.getOutputData());
				channelOutputDataOfCamera13.registerPort(edge.getCamera());
				channelOutputDataOfCamera28 = new DataStreamChannel(camera28.getOutputData());
				channelOutputDataOfCamera28.registerPort(edge.getCamera());
				channelOutputDataOfCamera22 = new DataStreamChannel(camera22.getOutputData());
				channelOutputDataOfCamera22.registerPort(edge.getCamera());
				channelOutputDataOfCamera31 = new DataStreamChannel(camera31.getOutputData());
				channelOutputDataOfCamera31.registerPort(edge.getCamera());
				channelOutputDataOfCamera15 = new DataStreamChannel(camera15.getOutputData());
				channelOutputDataOfCamera15.registerPort(edge.getCamera());
				channelOutputDataOfCamera17 = new DataStreamChannel(camera17.getOutputData());
				channelOutputDataOfCamera17.registerPort(edge.getCamera());
				channelOutputDataOfCamera27 = new DataStreamChannel(camera27.getOutputData());
				channelOutputDataOfCamera27.registerPort(edge.getCamera());
				channelOutputDataOfCamera19 = new DataStreamChannel(camera19.getOutputData());
				channelOutputDataOfCamera19.registerPort(edge.getCamera());
				channelOutputDataOfCamera9 = new DataStreamChannel(camera9.getOutputData());
				channelOutputDataOfCamera9.registerPort(edge.getCamera());
				channelOutputDataOfCamera1 = new DataStreamChannel(camera1.getOutputData());
				channelOutputDataOfCamera1.registerPort(edge.getCamera());
				channelOutputDataOfCamera20 = new DataStreamChannel(camera20.getOutputData());
				channelOutputDataOfCamera20.registerPort(edge.getCamera());
				channelOutputDataOfCamera16 = new DataStreamChannel(camera16.getOutputData());
				channelOutputDataOfCamera16.registerPort(edge.getCamera());
				channelOutputDataOfCamera26 = new DataStreamChannel(camera26.getOutputData());
				channelOutputDataOfCamera26.registerPort(edge.getCamera());
				channelOutputDataOfCamera14 = new DataStreamChannel(camera14.getOutputData());
				channelOutputDataOfCamera14.registerPort(edge.getCamera());
				// Registration of broadcast channels
				channelCarsOfTraffic = new TrafficEventStreamChannel(traffic.getCars());
					channelCarsOfTraffic.registerPort(camera3.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera7.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera15.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera5.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera13.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera8.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera24.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera16.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera30.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera17.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera6.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera28.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera18.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera21.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera9.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera1.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera32.getTrafficSensing());
					channelCarsOfTraffic.registerPort(edge.getTrafficStream());
					channelCarsOfTraffic.registerPort(camera26.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera10.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera20.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera23.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera29.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera4.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera19.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera11.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera27.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera2.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera12.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera25.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera22.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera14.getTrafficSensing());
					channelCarsOfTraffic.registerPort(camera31.getTrafficSensing());
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
				camera17.start();
				camera18.start();
				camera19.start();
				camera20.start();
				camera21.start();
				camera22.start();
				camera23.start();
				camera24.start();
				camera25.start();
				camera26.start();
				camera27.start();
				camera28.start();
				camera29.start();
				camera30.start();
				camera31.start();
				camera32.start();
				edge.start();
				traffic.start();
			}
			
			public boolean isWaiting() {
				return camera1.isWaiting() && camera2.isWaiting() && camera3.isWaiting() && camera4.isWaiting() && camera5.isWaiting() && camera6.isWaiting() && camera7.isWaiting() && camera8.isWaiting() && camera9.isWaiting() && camera10.isWaiting() && camera11.isWaiting() && camera12.isWaiting() && camera13.isWaiting() && camera14.isWaiting() && camera15.isWaiting() && camera16.isWaiting() && camera17.isWaiting() && camera18.isWaiting() && camera19.isWaiting() && camera20.isWaiting() && camera21.isWaiting() && camera22.isWaiting() && camera23.isWaiting() && camera24.isWaiting() && camera25.isWaiting() && camera26.isWaiting() && camera27.isWaiting() && camera28.isWaiting() && camera29.isWaiting() && camera30.isWaiting() && camera31.isWaiting() && camera32.isWaiting() && edge.isWaiting() && traffic.isWaiting()
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
			
			public CameraComponentParam getCamera17() {
				return camera17;
			}
			
			public CameraComponentParam getCamera18() {
				return camera18;
			}
			
			public CameraComponentParam getCamera19() {
				return camera19;
			}
			
			public CameraComponentParam getCamera20() {
				return camera20;
			}
			
			public CameraComponentParam getCamera21() {
				return camera21;
			}
			
			public CameraComponentParam getCamera22() {
				return camera22;
			}
			
			public CameraComponentParam getCamera23() {
				return camera23;
			}
			
			public CameraComponentParam getCamera24() {
				return camera24;
			}
			
			public CameraComponentParam getCamera25() {
				return camera25;
			}
			
			public CameraComponentParam getCamera26() {
				return camera26;
			}
			
			public CameraComponentParam getCamera27() {
				return camera27;
			}
			
			public CameraComponentParam getCamera28() {
				return camera28;
			}
			
			public CameraComponentParam getCamera29() {
				return camera29;
			}
			
			public CameraComponentParam getCamera30() {
				return camera30;
			}
			
			public CameraComponentParam getCamera31() {
				return camera31;
			}
			
			public CameraComponentParam getCamera32() {
				return camera32;
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
					
					str=str+"\n    camera17 : "+camera17.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera18 : "+camera18.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera19 : "+camera19.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera20 : "+camera20.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera21 : "+camera21.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera22 : "+camera22.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera23 : "+camera23.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera24 : "+camera24.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera25 : "+camera25.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera26 : "+camera26.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera27 : "+camera27.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera28 : "+camera28.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera29 : "+camera29.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera30 : "+camera30.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera31 : "+camera31.getInQueue().replace("    ","      ");
					
					str=str+"\n    camera32 : "+camera32.getInQueue().replace("    ","      ");
					
					str=str+"\n    edge : "+edge.getInQueue().replace("    ","      ");
					
					str=str+"\n    traffic : "+traffic.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
