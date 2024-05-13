package hu.bme.mit.gamma.casestudy.iotsystem_meas.camera_component;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.TrafficEventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.DataStreamInterface;

public interface CameraComponentInterface {
	
	TrafficEventStreamInterface.Required getTrafficSensing();
	DataStreamInterface.Provided getOutputData();
	
	void reset();
	
	void start();
	
}
