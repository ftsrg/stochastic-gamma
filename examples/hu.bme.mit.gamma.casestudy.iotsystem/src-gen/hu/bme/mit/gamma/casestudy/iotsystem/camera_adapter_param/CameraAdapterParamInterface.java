package hu.bme.mit.gamma.casestudy.iotsystem.camera_adapter_param;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.TrafficEventStreamInterface;

public interface CameraAdapterParamInterface {
	
	EventStreamInterface.Required getSoftwareTimer();
	TrafficEventStreamInterface.Required getTrafficSensing();
	DataStreamInterface.Provided getImages();
	
	void reset();
	
	void start();
	
}
