package hu.bme.mit.gamma.casestudy.iotsystem_meas.communication_adapter;

import java.util.Collections;
import java.util.List;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.*;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.network.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.*;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.network.*;

public class NetworkAdapter implements Runnable, NetworkAdapterInterface {			
	// Thread running this wrapper instance
	private Thread thread;
	// Wrapped synchronous instance
	private Network network;
	// Control port instances
	// Wrapped port instances
	private ImageIn imageIn;
	private ImageOut imageOut;
	private ImageLoss imageLoss;
	// Main queue
	private LinkedBlockingMultiQueue<String, Event> __asyncQueue = new LinkedBlockingMultiQueue<String, Event>();
	// Subqueues
	private LinkedBlockingMultiQueue<String, Event>.SubQueue imageInQueue;
	private LinkedBlockingMultiQueue<String, Event>.SubQueue imageLossQueue;
	
	
	public boolean isEventQueueEmpty(){
		return __asyncQueue.isEmpty();
	}
	
	public NetworkAdapter() {
		network = new Network();
		// Wrapped port instances
		imageIn = new ImageIn();
		imageOut = new ImageOut();
		imageLoss = new ImageLoss();
		init();
	}
	
	/** Resets the wrapped component. Must be called to initialize the component. */
	@Override
	public void reset(){
		this.handleBeforeReset();
		this.resetVariables();
		this.resetStateConfigurations();
		this.raiseEntryEvents();
		this.handleAfterReset();
	}
	
	public void handleBeforeReset() {
		//interrupt();
		//
		network.handleBeforeReset();
	}
	
	public void resetVariables() {
		network.resetVariables();
	}
	
	public void resetStateConfigurations() {
		network.resetStateConfigurations();
	}
	
	public void raiseEntryEvents() {
		network.raiseEntryEvents();
	}
	
	public void handleAfterReset() {
		network.handleAfterReset();
		//
	}
	
	/** Creates the subqueues, clocks and enters the wrapped synchronous component. */
	private void init() {
		network = new Network();
		// Creating subqueues: the negative conversion regarding priorities is needed,
		// because the lbmq marks higher priority with lower integer values
		__asyncQueue.addSubQueue("imageLossQueue", -(2), (int) 1);
		imageLossQueue = __asyncQueue.getSubQueue("imageLossQueue");
		__asyncQueue.addSubQueue("imageInQueue", -(1), (int) 1);
		imageInQueue = __asyncQueue.getSubQueue("imageInQueue");
		// The thread has to be started manually
	}
	
	
	// Inner classes representing control ports
	
	// Inner classes representing wrapped ports
	public class ImageIn implements DataStreamInterface.Required {
		
		@Override
		public void raiseNewData(double blurred, boolean car) {
			imageInQueue.offer(new Event("ImageIn.newData", blurred, car));
		}
		
		
		@Override
		public void registerListener(DataStreamInterface.Listener.Required listener) {
			network.getImageIn().registerListener(listener);
		}
		
		@Override
		public List<DataStreamInterface.Listener.Required> getRegisteredListeners() {
			return network.getImageIn().getRegisteredListeners();
		}
		
	}
	
	@Override
	public ImageIn getImageIn() {
		return imageIn;
	}
	
	public class ImageOut implements DataStreamInterface.Provided {
		
		
		@Override
		public boolean isRaisedNewData() {
			return network.getImageOut().isRaisedNewData();
		}
		@Override
		public double getBlurred() {
			return network.getImageOut().getBlurred();
		}
		@Override
		public boolean getCar() {
			return network.getImageOut().getCar();
		}
		
		@Override
		public void registerListener(DataStreamInterface.Listener.Provided listener) {
			network.getImageOut().registerListener(listener);
		}
		
		@Override
		public List<DataStreamInterface.Listener.Provided> getRegisteredListeners() {
			return network.getImageOut().getRegisteredListeners();
		}
		
	}
	
	@Override
	public ImageOut getImageOut() {
		return imageOut;
	}
	
	public class ImageLoss implements DataStreamInterface.Required {
		
		@Override
		public void raiseNewData(double blurred, boolean car) {
			imageLossQueue.offer(new Event("ImageLoss.newData", blurred, car));
		}
		
		
		@Override
		public void registerListener(DataStreamInterface.Listener.Required listener) {
			network.getImageLoss().registerListener(listener);
		}
		
		@Override
		public List<DataStreamInterface.Listener.Required> getRegisteredListeners() {
			return network.getImageLoss().getRegisteredListeners();
		}
		
	}
	
	@Override
	public ImageLoss getImageLoss() {
		return imageLoss;
	}
	
	/** Manual scheduling. */
	public void schedule() {
		Event event = __asyncQueue.poll();
		if (event == null) {
			// There was no event in the queue
			return;
		}
		processEvent(event);
	}
	
	/** Operation. */
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Event event = __asyncQueue.take();		
				processEvent(event);
			} catch (InterruptedException e) {
				thread.interrupt();
			}
		}
	}
	
	private void processEvent(Event event) {
		if (!isControlEvent(event)) {
			// Event is forwarded to the wrapped component
			forwardEvent(event);
		}
		performControlActions(event);
	}
	
	private boolean isControlEvent(Event event) {
		return false;
	}
	
	private void forwardEvent(Event event) {
		switch (event.getEvent()) {
			case "ImageIn.newData":
				network.getImageIn().raiseNewData( (double) event.getValue()[0],  (boolean) event.getValue()[1]);
			break;
			case "ImageLoss.newData":
				network.getImageLoss().raiseNewData( (double) event.getValue()[0],  (boolean) event.getValue()[1]);
			break;
			default:
				throw new IllegalArgumentException("No such event!");
		}
	}
	
	private void performControlActions(Event event) {
		String[] eventName = event.getEvent().split("\\.");
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("ImageIn")) {
			network.runCycle();
			return;
		}
		// Port trigger
		if (eventName.length == 2 && eventName[0].equals("ImageLoss")) {
			network.runCycle();
			return;
		}
	}
	
	/** Starts this wrapper instance on a thread. */
	@Override
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public boolean isWaiting() {
		return thread.getState() == Thread.State.WAITING;
	}
	
	/** Stops the thread running this wrapper instance. */
	public void interrupt() {
		thread.interrupt();
	}
	
	public Network getNetwork() {
		return network;
	}
	
	

	public String getInQueue(){
		String str="Input events (";
		str=str+"imageLossQueue [";
		for (Event event:imageLossQueue){
			str=str+event.getEvent().toString()+" : ";
			for (Object value:event.getValue()){
				str=str+" "+value.toString()+",";
			}
		}
		str=str+"], ";
		str=str+"imageInQueue [";
		for (Event event:imageInQueue){
			str=str+event.getEvent().toString()+" : ";
			for (Object value:event.getValue()){
				str=str+" "+value.toString()+",";
			}
		}
		str=str+"], ";
		str=str+")";
		return str;
	}
}
