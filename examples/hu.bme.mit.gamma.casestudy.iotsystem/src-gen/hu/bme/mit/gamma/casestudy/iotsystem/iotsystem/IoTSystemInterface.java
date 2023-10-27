package hu.bme.mit.gamma.casestudy.iotsystem.iotsystem;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;

public interface IoTSystemInterface {
	
	EventStreamInterface.Provided getCarLeave();
	EventStreamInterface.Provided getFailures();
	
	void reset();
	
	void start();
	
}
