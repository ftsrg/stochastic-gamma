package hu.bme.mit.gamma.casestudy.iotsystem.communication_adapter;

import hu.bme.mit.gamma.casestudy.iotsystem.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;

public interface NetworkAdapterInterface {
	
	DataStreamInterface.Required getImageLoss();
	DataStreamInterface.Provided getImageOut();
	DataStreamInterface.Required getImageIn();
	
	void reset();
	
	void start();
	
}
