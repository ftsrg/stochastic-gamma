package hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer_adapter;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.ConnectionStateInterface;

public interface Summarizer_AdapterInterface {
	
	ConnectionStateInterface.Provided getOutPort();
	ConnectionStateInterface.Required getInPort();
	
	void reset();
	
	void start();
	
}
