package javaenv;

import hu.bme.mit.gamma.casestudy.iotsystem.iotsystem.IoTSystem;
import hu.bme.mit.gamma.casestudy.iotsystem.VirtualTimerService;

import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;


import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;

import hu.bme.mit.gamma.casestudy.iotsystem.camera_driver.*;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;
import hu.bme.mit.gamma.casestudy.iotsystem.camera_adapter.*;
import hu.bme.mit.gamma.casestudy.iotsystem.network.*;
import hu.bme.mit.gamma.casestudy.iotsystem.edge_adapter.*;
import hu.bme.mit.gamma.casestudy.iotsystem.cloud.*;
import hu.bme.mit.gamma.casestudy.iotsystem.camera_component.*;
import hu.bme.mit.gamma.casestudy.iotsystem.traffic.*;
import hu.bme.mit.gamma.casestudy.iotsystem.camera_control.*;
import hu.bme.mit.gamma.casestudy.iotsystem.traffic_adapter.*;
import hu.bme.mit.gamma.casestudy.iotsystem.traffic_sct.*;
import hu.bme.mit.gamma.casestudy.iotsystem.communication_adapter.*;
import hu.bme.mit.gamma.casestudy.iotsystem.camera_software.*;
		
//import py4j.GatewayServer; great old times... :)




public class DetModelEntryPoint  {
	
	public IoTSystem detModel=new IoTSystem();
	public MonitorOfAspectSystem_Failures_NewEvent monitorOfAspectSystem_Failures_NewEvent=new MonitorOfAspectSystem_Failures_NewEvent();


	public MonitorOfEndConditionSystem_CarLeave_NewEvent monitorOfEndConditionSystem_CarLeave_NewEvent= new MonitorOfEndConditionSystem_CarLeave_NewEvent();
	
	
	public DetModelEntryPoint(){
		
		
		
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




