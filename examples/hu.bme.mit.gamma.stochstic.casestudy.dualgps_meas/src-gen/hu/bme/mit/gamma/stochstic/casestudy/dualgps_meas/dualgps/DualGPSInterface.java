package hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.dualgps;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.interfaces.SensorInterface;

public interface DualGPSInterface {
	
	SensorInterface.Provided getCommunication();
	
	void reset();
	
	void start();
	
}
