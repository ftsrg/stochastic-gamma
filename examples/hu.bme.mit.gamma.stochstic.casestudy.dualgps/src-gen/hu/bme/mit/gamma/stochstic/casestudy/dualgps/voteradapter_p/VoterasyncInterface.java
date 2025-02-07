package hu.bme.mit.gamma.stochstic.casestudy.dualgps.voteradapter_p;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.HardwareFailureInterface;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.SensorInterface;

public interface VoterasyncInterface {
	
	SensorInterface.Required getSensor();
	SensorInterface.Provided getCommunication();
	HardwareFailureInterface.Required getFaults();
	
	void reset();
	
	void start();
	
}
