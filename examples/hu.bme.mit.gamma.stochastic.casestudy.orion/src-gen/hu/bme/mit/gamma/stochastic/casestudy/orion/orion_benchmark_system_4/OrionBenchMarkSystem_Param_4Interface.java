package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_benchmark_system_4;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.ConnectionStateInterface;

public interface OrionBenchMarkSystem_Param_4Interface {
	
	ConnectionStateInterface.Provided getSystemConnStatus();
	
	void reset();
	
	void start();
	
}
