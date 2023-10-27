package hu.bme.mit.gamma.casestudy.iotsystem.communication_adapter;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;

public interface NetworkAdapterInterface {
	
	DataStreamInterface.Required getImageIn();
	DataStreamInterface.Provided getImageOut();
	DataStreamInterface.Required getImageLoss();
	
	void reset();
	
	void start();
	
}
