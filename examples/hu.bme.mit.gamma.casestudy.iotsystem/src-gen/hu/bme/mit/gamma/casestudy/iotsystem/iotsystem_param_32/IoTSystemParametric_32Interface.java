package hu.bme.mit.gamma.casestudy.iotsystem.iotsystem_param_32;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;

public interface IoTSystemParametric_32Interface {
	
	EventStreamInterface.Provided getFailures();
	EventStreamInterface.Provided getCarLeave();
	
	void reset();
	
	void start();
	
}
