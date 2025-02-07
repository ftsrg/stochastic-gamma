package hu.bme.mit.gamma.stochastic.casestudy.orion.channels;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.TimingInterface;

public interface TimingChannelInterface {			
	
	void registerPort(TimingInterface.Provided providedPort);
	
	void registerPort(TimingInterface.Required requiredPort);

}
