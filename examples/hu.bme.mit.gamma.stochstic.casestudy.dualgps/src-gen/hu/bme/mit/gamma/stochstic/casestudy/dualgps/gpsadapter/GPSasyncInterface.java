package hu.bme.mit.gamma.stochstic.casestudy.dualgps.gpsadapter;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.HardwareFailureInterface;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.SensorInterface;

public interface GPSasyncInterface {
	
	SensorInterface.Provided getCommunication();
	HardwareFailureInterface.Required getFaults();
	
	void reset();
	
	void start();
	
}
