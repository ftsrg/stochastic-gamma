package hu.bme.mit.gamma.casestudy.iotsystem.camera_adapter;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.TrafficEventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;

public interface CameraAdapterInterface {
	
	EventStreamInterface.Required getSoftwareTimer();
	DataStreamInterface.Provided getImages();
	TrafficEventStreamInterface.Required getTrafficSensing();
	
	void reset();
	
	void start();
	
}
