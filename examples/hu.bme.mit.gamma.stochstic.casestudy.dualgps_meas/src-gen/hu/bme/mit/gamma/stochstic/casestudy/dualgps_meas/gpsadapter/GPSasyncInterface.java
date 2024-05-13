package hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.gpsadapter;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.interfaces.SensorInterface;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.interfaces.HardwareFailureInterface;

public interface GPSasyncInterface {
	
	SensorInterface.Provided getCommunication();
	HardwareFailureInterface.Required getFaults();
	
	void reset();
	
	void start();
	
}
