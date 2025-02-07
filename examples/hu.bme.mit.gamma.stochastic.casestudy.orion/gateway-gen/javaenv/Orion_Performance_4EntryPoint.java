package javaenv;

import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_benchmark_system_4.OrionBenchMarkSystem_4;
import hu.bme.mit.gamma.stochastic.casestudy.orion.VirtualTimerService;

import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.ConnectionStateInterface;


import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.ConnectionStateInterface;

import hu.bme.mit.gamma.stochastic.casestudy.orion.status_sm.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_master_sm_ext.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.channel_.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.interfaces.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_slave_sm_ext.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_env.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_adapter.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.summarizer_adapter.*;
import hu.bme.mit.gamma.stochastic.casestudy.orion.orion_stoch_system.*;
		
//import py4j.GatewayServer; great old times... :)




public class Orion_Performance_4EntryPoint  {
	
	public OrionBenchMarkSystem_4 detModel=new OrionBenchMarkSystem_4();
	public MonitorOfAspectOrion_SystemConnStatus_Conn monitorOfAspectOrion_SystemConnStatus_Conn=new MonitorOfAspectOrion_SystemConnStatus_Conn();


	public MonitorOfEndConditionOrion_SystemConnStatus_Conn monitorOfEndConditionOrion_SystemConnStatus_Conn= new MonitorOfEndConditionOrion_SystemConnStatus_Conn();
	
	
	public Orion_Performance_4EntryPoint(){
		
		
		
		detModel.getSystemConnStatus().registerListener(monitorOfAspectOrion_SystemConnStatus_Conn);


		detModel.getSystemConnStatus().registerListener(monitorOfEndConditionOrion_SystemConnStatus_Conn);
	}

	public void reset() {
		/*
		detModel=null;
		System.gc();
		detModel=new OrionBenchMarkSystem_4();
		detModel.getSystemConnStatus().registerListener(monitorOfAspectOrion_SystemConnStatus_Conn);
		detModel.getSystemConnStatus().registerListener(monitorOfEndConditionOrion_SystemConnStatus_Conn);
		*/
		monitorOfAspectOrion_SystemConnStatus_Conn.reset();
		monitorOfEndConditionOrion_SystemConnStatus_Conn.reset();
		detModel.reset();
		
	}
	
	
	public OrionBenchMarkSystem_4 getDetModel(){
		return detModel;
	}
	
	public OrionBenchMarkSystem_4 getOrion(){
		return detModel;
	}   

	
		public class MonitorOfAspectOrion_SystemConnStatus_Conn implements ConnectionStateInterface.Listener.Provided {
					
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
					public void raiseConn(){
						state="stop";
						freq++;
					}
					
					@Override
					public void raiseDisconn(){
					}
					
		}


	
		public class MonitorOfEndConditionOrion_SystemConnStatus_Conn implements ConnectionStateInterface.Listener.Provided {
					
					public String state="run";
					
							
					public void reset(){
						state="run";
					}
											
					@Override
					public void raiseConn(){
						state="stop";
					}
					
					@Override
					public void raiseDisconn(){
					}
					
		}


}




