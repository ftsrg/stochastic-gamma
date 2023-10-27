package hu.bme.mit.gamma.casestudy.iotsystem.camera_component;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.TrafficEventStreamInterface;

public interface CameraComponentInterface {
	
	TrafficEventStreamInterface.Required getTrafficSensing();
	DataStreamInterface.Provided getOutputData();
	
	void reset();
	
	void start();
	
}
