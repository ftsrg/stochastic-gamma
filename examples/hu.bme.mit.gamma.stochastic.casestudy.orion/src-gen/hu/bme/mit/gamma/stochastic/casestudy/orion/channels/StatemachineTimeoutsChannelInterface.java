package hu.bme.mit.gamma.stochastic.casestudy.orion.channels;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.StatemachineTimeoutsInterface;

public interface StatemachineTimeoutsChannelInterface {			
	
	void registerPort(StatemachineTimeoutsInterface.Provided providedPort);
	
	void registerPort(StatemachineTimeoutsInterface.Required requiredPort);

}
