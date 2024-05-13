package hu.bme.mit.gamma.casestudy.iotsystem_meas.camera_software;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.TrafficEventStreamInterface;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.DataStreamInterface;

public interface CameraSoftwareInterface {
	
	TrafficEventStreamInterface.Required getTrafficSensing();
	DataStreamInterface.Provided getImages();
	
	void reset();
	
	void runCycle();
	void runFullCycle();
	
}
