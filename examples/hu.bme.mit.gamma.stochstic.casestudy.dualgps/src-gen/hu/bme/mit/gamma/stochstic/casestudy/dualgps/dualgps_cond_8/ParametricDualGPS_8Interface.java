package hu.bme.mit.gamma.stochstic.casestudy.dualgps.dualgps_cond_8;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.SensorInterface;

public interface ParametricDualGPS_8Interface {
	
	SensorInterface.Provided getCommunication();
	
	void reset();
	
	void start();
	
}
