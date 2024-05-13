package hu.bme.mit.gamma.casestudy.iotsystem_meas.communication_adapter;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.DataStreamInterface;

public interface NetworkAdapterInterface {
	
	DataStreamInterface.Required getImageLoss();
	DataStreamInterface.Required getImageIn();
	DataStreamInterface.Provided getImageOut();
	
	void reset();
	
	void start();
	
}
