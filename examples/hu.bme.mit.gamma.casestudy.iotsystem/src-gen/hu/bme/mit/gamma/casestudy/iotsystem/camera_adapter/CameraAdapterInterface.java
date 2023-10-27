package hu.bme.mit.gamma.casestudy.iotsystem.camera_adapter;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.TrafficEventStreamInterface;

public interface CameraAdapterInterface {
	
	TrafficEventStreamInterface.Required getTrafficSensing();
	DataStreamInterface.Provided getImages();
	EventStreamInterface.Required getSoftwareTimer();
	
	void reset();
	
	void start();
	
}
