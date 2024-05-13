package hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.channels;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas.interfaces.HardwareFailureInterface;

public interface HardwareFailureChannelInterface {			
	
	void registerPort(HardwareFailureInterface.Provided providedPort);
	
	void registerPort(HardwareFailureInterface.Required requiredPort);

}
