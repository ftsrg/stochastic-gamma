package hu.bme.mit.gamma.casestudy.iotsystem.edge_adapter;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.TrafficEventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;

public interface EdgeAdapterInterface {
	
	EventStreamInterface.Provided getLostImage();
	DataStreamInterface.Required getCamera();
	TrafficEventStreamInterface.Required getTrafficStream();
	EventStreamInterface.Provided getCarLeave();
	
	void reset();
	
	void start();
	
}
