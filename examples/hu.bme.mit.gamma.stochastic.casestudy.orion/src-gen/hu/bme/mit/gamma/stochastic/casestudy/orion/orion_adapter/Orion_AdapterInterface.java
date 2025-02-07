package hu.bme.mit.gamma.stochastic.casestudy.orion.orion_adapter;

import hu.bme.mit.gamma.stochastic.casestudy.orion.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.ConnectionStateInterface;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.SoftwareTimerInterface;

public interface Orion_AdapterInterface {
	
	SoftwareTimerInterface.Required getTimeoutZarva_0();
	SoftwareTimerInterface.Provided getTimoeutKeepAliveReceiveTimeout_3_req();
	ConnectionStateInterface.Provided getSystemStatus();
	SoftwareTimerInterface.Required getTimeoutKeepAliveSendTimeout_1();
	SoftwareTimerInterface.Required getTimeoutKapcsolodik_3();
	SoftwareTimerInterface.Provided getTimeoutZarva_0_req();
	SoftwareTimerInterface.Required getTimeoutKeepAliveSendTimeout_0();
	SoftwareTimerInterface.Provided getTimeoutKapcsolodik_2_req();
	SoftwareTimerInterface.Required getTimeoutKapcsolodik_2();
	SoftwareTimerInterface.Provided getTimeoutKapcsolodik_3_req();
	SoftwareTimerInterface.Required getTimoeutKeepAliveReceiveTimeout_3();
	SoftwareTimerInterface.Provided getTimeoutKeepAliveSendTimeout_1_req();
	SoftwareTimerInterface.Provided getTimeoutKeepAliveReceiveTimeout_4_req();
	SoftwareTimerInterface.Provided getTimeoutKeepAliveSendTimeout_0_req();
	SoftwareTimerInterface.Required getTimeoutKeepAliveReceiveTimeout_4();
	
	void reset();
	
	void start();
	
}
