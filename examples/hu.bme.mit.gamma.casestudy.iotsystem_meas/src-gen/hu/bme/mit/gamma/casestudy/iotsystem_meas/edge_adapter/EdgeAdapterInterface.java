package hu.bme.mit.gamma.casestudy.iotsystem_meas.edge_adapter;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.TrafficEventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.DataStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.EventStreamInterface;

public interface EdgeAdapterInterface {
	
	DataStreamInterface.Required getCamera();
	EventStreamInterface.Provided getCarLeave();
	EventStreamInterface.Provided getLostImage();
	TrafficEventStreamInterface.Required getTrafficStream();
	
	void reset();
	
	void start();
	
}
