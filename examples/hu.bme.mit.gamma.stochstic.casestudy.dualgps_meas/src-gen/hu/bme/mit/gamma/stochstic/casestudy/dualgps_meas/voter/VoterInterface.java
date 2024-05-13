package hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.voter;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.interfaces.*;

public interface VoterInterface {

	public HardwareFailureInterface.Required getFaults();
	public SensorInterface.Required getSensor();
	public SensorInterface.Provided getCommunication();
	
	void runCycle();
	void reset();

}
