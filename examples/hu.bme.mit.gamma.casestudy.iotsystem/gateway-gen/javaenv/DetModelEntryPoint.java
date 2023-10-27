package javaenv;

import hu.bme.mit.gamma.casestudy.iotsystem.iotsystem_param.IoTSystemParametric;
import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.EventStreamInterface;
//import py4j.GatewayServer; great old times...

		
		
		
		public class DetModelEntryPoint  {
			
			public IoTSystemParametric detModel=new IoTSystemParametric(0.0
			);


			public MonitorOfAspectSystemFailuresNewEvent monitorOfAspectSystemFailuresNewEvent=new MonitorOfAspectSystemFailuresNewEvent();


			public MonitorOfEndConditionSystemCarLeaveNewEvent monitorOfEndConditionSystemCarLeaveNewEvent= new MonitorOfEndConditionSystemCarLeaveNewEvent();
			
			
			public DetModelEntryPoint(){

				detModel.getFailures().registerListener(monitorOfAspectSystemFailuresNewEvent);


				detModel.getCarLeave().registerListener(monitorOfEndConditionSystemCarLeaveNewEvent);
			}

			public void reset() {
				/*
				detModel=null;
				System.gc();
				detModel=new IoTSystemParametric(delayMean
				);
				detModel.getFailures().registerListener(monitorOfAspectSystemFailuresNewEvent);
				detModel.getCarLeave().registerListener(monitorOfEndConditionSystemCarLeaveNewEvent);
				*/
				monitorOfAspectSystemFailuresNewEvent.reset();
				monitorOfEndConditionSystemCarLeaveNewEvent.reset();
				detModel.reset();
			}
			
			
			public IoTSystemParametric getDetModel(){
				return detModel;
			}
			
			public IoTSystemParametric getSystem(){
				return detModel;
			}   

			
				public class MonitorOfAspectSystemFailuresNewEvent implements EventStreamInterface.Listener.Provided {
							
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


			
				public class MonitorOfEndConditionSystemCarLeaveNewEvent implements EventStreamInterface.Listener.Provided {
							
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




