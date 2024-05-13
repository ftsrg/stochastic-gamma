package hu.bme.mit.gamma.casestudy.iotsystem.iotsystem_param;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;

public interface IoTSystemParametric2Interface {
	
	EventStreamInterface.Provided getCarLeave();
	EventStreamInterface.Provided getFailures();
	
	void reset();
	
	void start();
	
}
