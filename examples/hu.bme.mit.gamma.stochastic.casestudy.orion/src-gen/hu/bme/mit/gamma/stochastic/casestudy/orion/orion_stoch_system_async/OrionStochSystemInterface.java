package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system_async;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.SoftwareTimerInterface;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.ConnectionStateInterface;

public interface OrionStochSystemInterface {
	
	SoftwareTimerInterface.Required getTimeoutKapcsolodik_3();
	SoftwareTimerInterface.Provided getTimoeutKeepAliveReceiveTimeout_3_req();
	SoftwareTimerInterface.Provided getTimeoutKapcsolodik_3_req();
	SoftwareTimerInterface.Provided getTimeoutKeepAliveReceiveTimeout_4_req();
	ConnectionStateInterface.Provided getSystemStatus();
	SoftwareTimerInterface.Required getTimeoutKapcsolodik_2();
	SoftwareTimerInterface.Required getTimoeutKeepAliveReceiveTimeout_3();
	SoftwareTimerInterface.Provided getTimeoutKapcsolodik_2_req();
	SoftwareTimerInterface.Required getTimeoutZarva_0();
	SoftwareTimerInterface.Required getTimeoutKeepAliveReceiveTimeout_4();
	SoftwareTimerInterface.Provided getTimeoutZarva_0_req();
	SoftwareTimerInterface.Required getTimeoutKeepAliveSendTimeout_0();
	SoftwareTimerInterface.Provided getTimeoutKeepAliveSendTimeout_1_req();
	SoftwareTimerInterface.Provided getTimeoutKeepAliveSendTimeout_0_req();
	SoftwareTimerInterface.Required getTimeoutKeepAliveSendTimeout_1();
	
	void reset();
	
	void start();
	
}
