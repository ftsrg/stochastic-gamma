package hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.channels;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.interfaces.SensorInterface;

public interface SensorChannelInterface {			
	
	void registerPort(SensorInterface.Provided providedPort);
	
	void registerPort(SensorInterface.Required requiredPort);

}
