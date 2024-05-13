package hu.bme.mit.gamma.casestudy.iotsystem_meas.cloud;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.*;

public interface EdgeInterface {

	public DataStreamInterface.Required getCamera();
	public TrafficEventStreamInterface.Required getTrafficStream();
	public EventStreamInterface.Provided getLostImage();
	public EventStreamInterface.Provided getCarLeave();
	
	void runCycle();
	void reset();

}
