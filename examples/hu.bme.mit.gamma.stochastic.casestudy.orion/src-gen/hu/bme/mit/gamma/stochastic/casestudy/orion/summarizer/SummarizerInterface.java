package hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;

public interface SummarizerInterface {

	public ConnectionStateInterface.Required getInPort();
	public ConnectionStateInterface.Provided getOutPort();
	
	void runCycle();
	void reset();

}
