package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_benchmark_system_16;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.ConnectionStateInterface;

public interface OrionBenchMarkSystem_Param_16Interface {
	
	ConnectionStateInterface.Provided getSystemConnStatus();
	
	void reset();
	
	void start();
	
}
