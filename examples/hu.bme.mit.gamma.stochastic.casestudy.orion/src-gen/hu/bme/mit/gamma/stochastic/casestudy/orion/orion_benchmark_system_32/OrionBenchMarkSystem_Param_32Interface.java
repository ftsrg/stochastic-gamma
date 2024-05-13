package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_benchmark_system_32;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.ConnectionStateInterface;

public interface OrionBenchMarkSystem_Param_32Interface {
	
	ConnectionStateInterface.Provided getSystemConnStatus();
	
	void reset();
	
	void start();
	
}
