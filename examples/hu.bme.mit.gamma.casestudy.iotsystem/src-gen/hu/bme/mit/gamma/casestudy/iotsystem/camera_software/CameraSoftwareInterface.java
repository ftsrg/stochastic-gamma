package hu.bme.mit.gamma.casestudy.iotsystem.camera_software;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.TrafficEventStreamInterface;

public interface CameraSoftwareInterface {
	
	DataStreamInterface.Provided getImages();
	TrafficEventStreamInterface.Required getTrafficSensing();
	
	void reset();
	
	void runCycle();
	void runFullCycle();
	
}
