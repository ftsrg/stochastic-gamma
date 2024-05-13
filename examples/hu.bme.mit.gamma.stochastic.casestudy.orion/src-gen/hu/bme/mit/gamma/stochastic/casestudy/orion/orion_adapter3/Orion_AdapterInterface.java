package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_adapter3;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.ConnectionStateInterface;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.TimingInterface;

public interface Orion_AdapterInterface {
	
	TimingInterface.Required getTiming();
	ConnectionStateInterface.Provided getSystemStatus();
	
	void reset();
	
	void start();
	
}
