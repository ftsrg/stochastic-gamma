package hu.bme.mit.gamma.casestudy.iotsystem.edge_adapter;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.TrafficEventStreamInterface;

public interface EdgeAdapterInterface {
	
	EventStreamInterface.Provided getLostImage();
	DataStreamInterface.Required getCamera();
	EventStreamInterface.Provided getCarLeave();
	TrafficEventStreamInterface.Required getTrafficStream();
	
	void reset();
	
	void start();
	
}
