package hu.bme.mit.gamma.stochstic.casestudy.dualgps.dualgps;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.SensorInterface;

public interface DualGPS_32Interface {
	
	SensorInterface.Provided getCommunication();
	
	void reset();
	
	void start();
	
}
