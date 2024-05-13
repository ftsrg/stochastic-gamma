package javaenv;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.dualgps_32.DualGPS_32;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.VirtualTimerService;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.SensorInterface;


import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.SensorInterface;

import hu.bme.mit.gamma.stochstic.casestudy.dualgps.gps.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.gpsadapter.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.interfaces.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.voter_p.*;
import hu.bme.mit.gamma.stochstic.casestudy.dualgps.voteradapter_p.*;
		
//import py4j.GatewayServer; great old times... :)




public class Reliability_32EntryPoint  {
	
	public DualGPS_32 detModel=new DualGPS_32();
	public MonitorOfAspectSystem_Communication_Failstop monitorOfAspectSystem_Communication_Failstop=new MonitorOfAspectSystem_Communication_Failstop();


	public MonitorOfEndConditionSystem_Communication_Failstop monitorOfEndConditionSystem_Communication_Failstop= new MonitorOfEndConditionSystem_Communication_Failstop();
	
	
	public Reliability_32EntryPoint(){
		
		
		
		detModel.getCommunication().registerListener(monitorOfAspectSystem_Communication_Failstop);


		detModel.getCommunication().registerListener(monitorOfEndConditionSystem_Communication_Failstop);
	}

	public void reset() {
		/*
		detModel=null;
		System.gc();
		detModel=new DualGPS_32();
		detModel.getCommunication().registerListener(monitorOfAspectSystem_Communication_Failstop);
		detModel.getCommunication().registerListener(monitorOfEndConditionSystem_Communication_Failstop);
		*/
		monitorOfAspectSystem_Communication_Failstop.reset();
		monitorOfEndConditionSystem_Communication_Failstop.reset();
		detModel.reset();
		
	}
	
	
	public DualGPS_32 getDetModel(){
		return detModel;
	}
	
	public DualGPS_32 getSystem(){
		return detModel;
	}   

	
		public class MonitorOfAspectSystem_Communication_Failstop implements SensorInterface.Listener.Provided {
					
					public String state="run";
					
					public int freq=0;
					
					public double parameter=0.0/0.0;//NaN is the initial value intentionally 
					public double meanParameter=0.0;
					public double sumParameter=0.0;
					
					public void getValue(){}
					
							
					public void reset(){
						state="run";
						freq=0;
						parameter=0.0/0.0;
						sumParameter=0.0;
						meanParameter=0.0;
					}
											
					@Override
					public void raiseFailstop(){
						state="stop";
						freq++;
					}
					
		}


	
		public class MonitorOfEndConditionSystem_Communication_Failstop implements SensorInterface.Listener.Provided {
					
					public String state="run";
					
							
					public void reset(){
						state="run";
					}
											
					@Override
					public void raiseFailstop(){
						state="stop";
					}
					
		}


}




