package hu.bme.mit.gamma.casestudy.iotsystem.camera_component;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.TrafficEventStreamInterface;

public interface CameraComponentInterface {
	
	DataStreamInterface.Provided getOutputData();
	TrafficEventStreamInterface.Required getTrafficSensing();
	
	void reset();
	
	void start();
	
}
