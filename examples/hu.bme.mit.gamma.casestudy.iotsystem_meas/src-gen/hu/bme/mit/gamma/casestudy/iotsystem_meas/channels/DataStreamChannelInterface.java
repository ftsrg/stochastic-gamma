package hu.bme.mit.gamma.casestudy.iotsystem_meas.channels;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.DataStreamInterface;

public interface DataStreamChannelInterface {			
	
	void registerPort(DataStreamInterface.Provided providedPort);
	
	void registerPort(DataStreamInterface.Required requiredPort);

}
