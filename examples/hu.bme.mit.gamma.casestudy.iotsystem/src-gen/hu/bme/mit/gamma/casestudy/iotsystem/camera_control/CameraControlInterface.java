package hu.bme.mit.gamma.casestudy.iotsystem.camera_control;

import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;

public interface CameraControlInterface {

	public EventStreamInterface.Provided getRequests();
	public DataStreamInterface.Required getDriverImages();
	public DataStreamInterface.Provided getNetworkImages();
	
	void runCycle();
	void reset();

}
