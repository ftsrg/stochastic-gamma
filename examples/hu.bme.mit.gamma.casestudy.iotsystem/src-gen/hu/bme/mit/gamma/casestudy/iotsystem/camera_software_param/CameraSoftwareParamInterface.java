package hu.bme.mit.gamma.casestudy.iotsystem.camera_software_param;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.TrafficEventStreamInterface;

public interface CameraSoftwareParamInterface {
	
	TrafficEventStreamInterface.Required getTrafficSensing();
	DataStreamInterface.Provided getImages();
	
	void reset();
	
	void runCycle();
	void runFullCycle();
	
}
