package hu.bme.mit.gamma.casestudy.iotsystem.iotsystem_param_8;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;

public interface IoTSystemParametric_8Interface {
	
	EventStreamInterface.Provided getCarLeave();
	EventStreamInterface.Provided getFailures();
	
	void reset();
	
	void start();
	
}
