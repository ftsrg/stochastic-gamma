package hu.bme.mit.gamma.casestudy.iotsystem.iotsystem_2;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;

public interface IoTSystem_2Interface {
	
	EventStreamInterface.Provided getFailures();
	EventStreamInterface.Provided getCarLeave();
	
	void reset();
	
	void start();
	
}
