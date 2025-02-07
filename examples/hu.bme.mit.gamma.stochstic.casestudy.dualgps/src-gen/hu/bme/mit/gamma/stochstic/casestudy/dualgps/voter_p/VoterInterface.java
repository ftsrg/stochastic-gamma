package hu.bme.mit.gamma.stochstic.casestudy.dualgps.voter_p;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.*;

public interface VoterInterface {

	public HardwareFailureInterface.Required getFaults();
	public SensorInterface.Required getSensor();
	public SensorInterface.Provided getCommunication();
	
	void runCycle();
	void reset();

}
