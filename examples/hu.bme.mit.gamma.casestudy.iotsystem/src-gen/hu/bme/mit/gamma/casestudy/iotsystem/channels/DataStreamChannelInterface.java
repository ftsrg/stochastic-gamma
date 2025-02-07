package hu.bme.mit.gamma.casestudy.iotsystem.channels;

import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.DataStreamInterface;

public interface DataStreamChannelInterface {			
	
	void registerPort(DataStreamInterface.Provided providedPort);
	
	void registerPort(DataStreamInterface.Required requiredPort);

}
