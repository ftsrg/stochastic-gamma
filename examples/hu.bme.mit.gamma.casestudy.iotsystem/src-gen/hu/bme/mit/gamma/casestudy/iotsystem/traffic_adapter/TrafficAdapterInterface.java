package hu.bme.mit.gamma.casestudy.iotsystem.traffic_adapter;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.TrafficEventStreamInterface;

public interface TrafficAdapterInterface {
	
	EventStreamInterface.Required getCarArrives();
	TrafficEventStreamInterface.Provided getTrafficStream();
	EventStreamInterface.Provided getCarArrivesOut();
	EventStreamInterface.Required getCarLeaves();
	
	void reset();
	
	void start();
	
}
