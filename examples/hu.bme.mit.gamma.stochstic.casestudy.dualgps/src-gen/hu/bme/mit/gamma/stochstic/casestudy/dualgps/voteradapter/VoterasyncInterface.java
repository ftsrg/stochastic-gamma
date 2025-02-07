package hu.bme.mit.gamma.stochstic.casestudy.dualgps.voteradapter;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.SensorInterface;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.HardwareFailureInterface;

public interface VoterasyncInterface {
	
	HardwareFailureInterface.Required getFaults();
	SensorInterface.Provided getCommunication();
	SensorInterface.Required getSensor1();
	SensorInterface.Required getSensor2();
	
	void reset();
	
	void start();
	
}
