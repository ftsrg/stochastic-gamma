package hu.bme.mit.gamma.casestudy.iotsystem.camera_software;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.TrafficEventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;

public interface CameraSoftwareInterface {
	
	DataStreamInterface.Provided getImages();
	TrafficEventStreamInterface.Required getTrafficSensing();
	
	void reset();
	
	void runCycle();
	void runFullCycle();
	
}
