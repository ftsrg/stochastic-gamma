package hu.bme.mit.gamma.casestudy.iotsystem_meas.traffic_adapter;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.TrafficEventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.EventStreamInterface;

public interface TrafficAdapterInterface {
	
	EventStreamInterface.Required getCarLeaves();
	EventStreamInterface.Provided getCarArrivesOut();
	EventStreamInterface.Required getCarArrives();
	TrafficEventStreamInterface.Provided getTrafficStream();
	
	void reset();
	
	void start();
	
}
