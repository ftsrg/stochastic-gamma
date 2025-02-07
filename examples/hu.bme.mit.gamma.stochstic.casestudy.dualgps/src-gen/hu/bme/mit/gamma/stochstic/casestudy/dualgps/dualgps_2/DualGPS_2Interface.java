package hu.bme.mit.gamma.stochstic.casestudy.dualgps.dualgps_2;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.SensorInterface;

public interface DualGPS_2Interface {
	
	SensorInterface.Provided getCommunication();
	
	void reset();
	
	void start();
	
}
