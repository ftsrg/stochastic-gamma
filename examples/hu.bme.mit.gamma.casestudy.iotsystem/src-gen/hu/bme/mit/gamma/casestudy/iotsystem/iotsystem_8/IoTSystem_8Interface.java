package hu.bme.mit.gamma.casestudy.iotsystem.iotsystem_8;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;

public interface IoTSystem_8Interface {
	
	EventStreamInterface.Provided getCarLeave();
	EventStreamInterface.Provided getFailures();
	
	void reset();
	
	void start();
	
}
