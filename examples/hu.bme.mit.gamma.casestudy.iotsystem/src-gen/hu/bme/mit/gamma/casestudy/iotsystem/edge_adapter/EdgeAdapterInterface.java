package hu.bme.mit.gamma.casestudy.iotsystem.edge_adapter;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.TrafficEventStreamInterface;

public interface EdgeAdapterInterface {
	
	DataStreamInterface.Required getCamera();
	EventStreamInterface.Provided getLostImage();
	TrafficEventStreamInterface.Required getTrafficStream();
	EventStreamInterface.Provided getCarLeave();
	
	void reset();
	
	void start();
	
}
