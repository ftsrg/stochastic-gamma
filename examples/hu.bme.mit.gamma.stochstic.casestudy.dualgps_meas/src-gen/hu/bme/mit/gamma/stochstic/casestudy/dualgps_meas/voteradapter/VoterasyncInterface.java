package hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.voteradapter;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.interfaces.SensorInterface;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.interfaces.HardwareFailureInterface;

public interface VoterasyncInterface {
	
	HardwareFailureInterface.Required getFaults();
	SensorInterface.Provided getCommunication();
	SensorInterface.Required getSensor();
	
	void reset();
	
	void start();
	
}
