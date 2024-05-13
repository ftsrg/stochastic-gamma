package hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.gps;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.interfaces.*;

public interface GPSInterface {

	public HardwareFailureInterface.Required getFaults();
	public SensorInterface.Provided getCommunication();
	
	void runCycle();
	void reset();

}
