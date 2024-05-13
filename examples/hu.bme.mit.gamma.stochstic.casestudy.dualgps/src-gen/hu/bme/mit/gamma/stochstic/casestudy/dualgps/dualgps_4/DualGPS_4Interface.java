package hu.bme.mit.gamma.stochstic.casestudy.dualgps.dualgps_4;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.SensorInterface;

public interface DualGPS_4Interface {
	
	SensorInterface.Provided getCommunication();
	
	void reset();
	
	void start();
	
}
