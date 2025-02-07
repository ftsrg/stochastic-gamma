package hu.bme.mit.gamma.casestudy.iotsystem.iotsystem_4;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;

public interface IoTSystem_4Interface {
	
	EventStreamInterface.Provided getCarLeave();
	EventStreamInterface.Provided getFailures();
	
	void reset();
	
	void start();
	
}
