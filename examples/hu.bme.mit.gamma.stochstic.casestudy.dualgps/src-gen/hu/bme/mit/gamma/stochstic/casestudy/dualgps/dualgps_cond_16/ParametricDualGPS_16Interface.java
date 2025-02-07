package hu.bme.mit.gamma.stochstic.casestudy.dualgps.dualgps_cond_16;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.SensorInterface;

public interface ParametricDualGPS_16Interface {
	
	SensorInterface.Provided getCommunication();
	
	void reset();
	
	void start();
	
}
