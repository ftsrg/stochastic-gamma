package hu.bme.mit.gamma.casestudy.iotsystem_meas.network;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.*;

public interface NetworkInterface {

	public DataStreamInterface.Required getImageIn();
	public DataStreamInterface.Provided getImageOut();
	public DataStreamInterface.Required getImageLoss();
	
	void runCycle();
	void reset();

}
