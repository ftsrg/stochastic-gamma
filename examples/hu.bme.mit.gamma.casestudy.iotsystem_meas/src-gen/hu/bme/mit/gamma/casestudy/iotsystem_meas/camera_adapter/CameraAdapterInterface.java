package hu.bme.mit.gamma.casestudy.iotsystem_meas.camera_adapter;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.TrafficEventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.DataStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.EventStreamInterface;

public interface CameraAdapterInterface {
	
	TrafficEventStreamInterface.Required getTrafficSensing();
	EventStreamInterface.Required getSoftwareTimer();
	DataStreamInterface.Provided getImages();
	
	void reset();
	
	void start();
	
}
