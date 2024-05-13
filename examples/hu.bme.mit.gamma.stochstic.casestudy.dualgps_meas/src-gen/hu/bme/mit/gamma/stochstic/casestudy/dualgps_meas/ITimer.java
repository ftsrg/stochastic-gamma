package hu.bme.mit.gamma.stochstic.casestudy.dualgps_meas;

public interface ITimer {
	
	void setTimer(ITimerCallback callback, int eventID, long time, boolean isPeriodic);
	void unsetTimer(ITimerCallback callback, int eventID);
	
}
