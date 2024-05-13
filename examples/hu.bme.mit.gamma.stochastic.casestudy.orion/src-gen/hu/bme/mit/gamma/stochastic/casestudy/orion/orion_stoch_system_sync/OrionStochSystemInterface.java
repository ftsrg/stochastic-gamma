package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system_sync;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.ConnectionStateInterface;

public interface OrionStochSystemInterface {
	
	ConnectionStateInterface.Provided getSystemStatus();
	
	void reset();
	
	void runCycle();
	void runFullCycle();
	
}
