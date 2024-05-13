package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_env_param_3;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.ConnectionStateInterface;

public interface Orion_Environment_Param_3Interface {
	
	ConnectionStateInterface.Provided getSystemConnStatus();
	
	void reset();
	
	void start();
	
}
