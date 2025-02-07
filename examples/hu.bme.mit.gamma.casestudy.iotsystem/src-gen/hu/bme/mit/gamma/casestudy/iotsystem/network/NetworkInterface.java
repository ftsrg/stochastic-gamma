package hu.bme.mit.gamma.casestudy.iotsystem.network;

import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;

public interface NetworkInterface {

	public DataStreamInterface.Required getImageIn();
	public DataStreamInterface.Provided getImageOut();
	public DataStreamInterface.Required getImageLoss();
	
	void runCycle();
	void reset();

}
