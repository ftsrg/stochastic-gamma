package javaenv;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.iotsystem.IoTSystem;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.VirtualTimerService;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.EventStreamInterface;


import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.EventStreamInterface;

import hu.bme.mit.gamma.casestudy.iotsystem_meas.edge_adapter.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.network.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.camera_control.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.interfaces.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.communication_adapter.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.traffic.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.camera_component.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.camera_driver.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.camera_adapter.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.cloud.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.traffic_sct.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.camera_software.*;
import hu.bme.mit.gamma.casestudy.iotsystem_meas.traffic_adapter.*;
		
//import py4j.GatewayServer; great old times... :)




public class ServiceAvailabilityEntryPoint  {
	
	public IoTSystem detModel=new IoTSystem();
	public MonitorOfAspectSystem_Failures_NewEvent monitorOfAspectSystem_Failures_NewEvent=new MonitorOfAspectSystem_Failures_NewEvent();


	public MonitorOfEndConditionSystem_CarLeave_NewEvent monitorOfEndConditionSystem_CarLeave_NewEvent= new MonitorOfEndConditionSystem_CarLeave_NewEvent();
	
	
	public ServiceAvailabilityEntryPoint(){
		
		
		
		detModel.getFailures().registerListener(monitorOfAspectSystem_Failures_NewEvent);


		detModel.getCarLeave().registerListener(monitorOfEndConditionSystem_CarLeave_NewEvent);
	}

	public void reset() {
		/*
		detModel=null;
		System.gc();
		detModel=new IoTSystem();
		detModel.getFailures().registerListener(monitorOfAspectSystem_Failures_NewEvent);
		detModel.getCarLeave().registerListener(monitorOfEndConditionSystem_CarLeave_NewEvent);
		*/
		monitorOfAspectSystem_Failures_NewEvent.reset();
		monitorOfEndConditionSystem_CarLeave_NewEvent.reset();
		detModel.reset();
		
	}
	
	
	public IoTSystem getDetModel(){
		return detModel;
	}
	
	public IoTSystem getSystem(){
		return detModel;
	}   

	
		public class MonitorOfAspectSystem_Failures_NewEvent implements EventStreamInterface.Listener.Provided {
					
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
					public void raiseNewEvent(){
						state="stop";
						freq++;
					}
					
		}


	
		public class MonitorOfEndConditionSystem_CarLeave_NewEvent implements EventStreamInterface.Listener.Provided {
					
					public String state="run";
					
							
					public void reset(){
						state="run";
					}
											
					@Override
					public void raiseNewEvent(){
						state="stop";
					}
					
		}


}




