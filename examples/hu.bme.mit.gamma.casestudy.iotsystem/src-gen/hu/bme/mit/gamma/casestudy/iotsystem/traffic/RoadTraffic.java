		package hu.bme.mit.gamma.casestudy.iotsystem.traffic;
		
		import java.util.List;
		import java.util.LinkedList;
		
		import hu.bme.mit.gamma.casestudy.iotsystem.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic_sct.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.traffic_adapter.*;
		import hu.bme.mit.gamma.casestudy.iotsystem.channels.*;
		
		import java.util.ArrayList;
		
		import hu.bme.mit.gamma.casestudy.iotsystem.scheduling.ElementaryComponentSchedulingInterface;
		import java.util.Map;
		import java.util.ArrayList;
		import java.util.HashMap;
		
		public class RoadTraffic implements RoadTrafficInterface {
			// Component instances
			private TrafficAdapter trafficSct;
			// Environmental Component instances
			// Port instances
			private Cars cars = new Cars();
			// Channel instances
			private EventStreamChannelInterface channelCarsOfCarArrival;
			private EventStreamChannelInterface channelCarOutOfCarDelay;
			private EventStreamChannelInterface channelCarArrivesOutOfTrafficSct;
			
			
			protected List<ElementaryComponentSchedulingInterface> envComponents = new ArrayList<ElementaryComponentSchedulingInterface>();
			protected Map<String,ElementaryComponentSchedulingInterface> envMap = new HashMap<String,ElementaryComponentSchedulingInterface>();
			
			public void registerEnvironmentComponent(String name,ElementaryComponentSchedulingInterface component){
				envComponents.add(component);
				envMap.put(name,component);
			}
			
			public boolean isEventQueueEmpty(){
				return  trafficSct.isEventQueueEmpty()   &&   true &&   true  ;
			}
			
			private final int MAX_SCHEDULE=15;
			
			public void schedule(){
				int cntr=0;
				do{
					trafficSct.schedule();
					cntr++;
				}while(!isEventQueueEmpty() && cntr < MAX_SCHEDULE);
				
				if (cntr==MAX_SCHEDULE) {
					System.out.println("Infinite scheduling in RoadTraffic! -----------");
					System.out.println(getInQueue());
					System.out.println("Execute extra scheduling step...");
					trafficSct.schedule();
					System.out.println(getInQueue());
					if (!true){
						System.out.println("    elementary stochastic component carDelay is not empty");
					}
					if (!true){
						System.out.println("    elementary stochastic component carArrival is not empty");
					}
					
				}
			}
			
			public RoadTraffic() {
				trafficSct = new TrafficAdapter();
				cars = new Cars(); 
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
				trafficSct.handleBeforeReset();
								//
			}

			public void resetVariables() {
				trafficSct.resetVariables();
			}
			
			public void resetStateConfigurations() {
				trafficSct.resetStateConfigurations();
			}
			
			public void raiseEntryEvents() {
				trafficSct.raiseEntryEvents();
			}

			public void handleAfterReset() {
				trafficSct.handleAfterReset();
				
				//trafficSct.schedule();
			}
			
			/** Creates the channel mappings and enters the wrapped statemachines. */
			private void init() {				
				// Registration of simple channels
				// Registration of broadcast channels
			}
			
			// Inner classes representing Ports
			public class Cars implements TrafficEventStreamInterface.Provided {
				
				
				List<TrafficEventStreamInterface.Listener.Provided> registeredListeners=new ArrayList<TrafficEventStreamInterface.Listener.Provided>();
				
				
				
				@Override
				public boolean isRaisedCarArrives() {
					return trafficSct.getTrafficStream().isRaisedCarArrives();
				}
				@Override
				public boolean isRaisedCarLeaves() {
					return trafficSct.getTrafficStream().isRaisedCarLeaves();
				}
				
				
				@Override
				public void registerListener(TrafficEventStreamInterface.Listener.Provided listener) {
					trafficSct.getTrafficStream().registerListener(listener);
					registeredListeners.add(listener);
				}
				
				@Override
				public List<TrafficEventStreamInterface.Listener.Provided> getRegisteredListeners() {
					List<TrafficEventStreamInterface.Listener.Provided> registeredListeners=new ArrayList<TrafficEventStreamInterface.Listener.Provided>();
					registeredListeners.addAll(trafficSct.getTrafficStream().getRegisteredListeners());
					return registeredListeners;
				}
				
			}
			
			@Override
			public Cars getCars() {
				return cars;
			}
			
			/** Starts the running of the asynchronous component. */
			@Override
			public void start() {
				trafficSct.start();
			}
			
			public boolean isWaiting() {
				return trafficSct.isWaiting()
					;
			}
			
			
			/**  Getter for component instances, e.g., enabling to check their states. */
			public TrafficAdapter getTrafficSct() {
				return trafficSct;
			}
			

			public String getInQueue(){
				if (!isEventQueueEmpty()){
					String str="Input of components [";
					str=str+"\n    trafficSct : "+trafficSct.getInQueue().replace("    ","      ");
					str=str+"]";
					return str;
				}else{
					return "";
				}
			}
			
		}
