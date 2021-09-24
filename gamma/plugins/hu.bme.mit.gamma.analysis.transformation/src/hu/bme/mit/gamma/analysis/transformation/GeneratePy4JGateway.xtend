package hu.bme.mit.gamma.analysis.transformation

import hu.bme.mit.gamma.analysis.AnalysisComponent
import hu.bme.mit.gamma.statechart.interface_.Package

class GeneratePy4JGateway {
	def generate(AnalysisComponent component,String packageName){
		
		var pack=component.eContainer as Package
		var compName=component.analyzedComponent.type.name.toFirstUpper
		'''
		package javaenv;
		
		import «packageName».«pack.name.toLowerCase».«compName»;
		import «packageName».interfaces.«component.aspect.event.port.interfaceRealization.interface.name.toFirstUpper»Interface;
		import py4j.GatewayServer;
		
		public class AnalyzerGateway{
			
				private static DetModelEntryPoint entryPoint;
				
				public AnalyzerGateway() {
					entryPoint=new DetModelEntryPoint();
				}
				
				public DetModelEntryPoint getEntryPoint() {
					return entryPoint;
				}
				
				public static void main(String[] args) {
					GatewayServer gatewayServer=new GatewayServer(new AnalyzerGateway());
					gatewayServer.start();
					System.out.println("Gateway has started!");
				}
				
				
				
				public class DetModelEntryPoint  {
					private «compName» detModel=new «compName»();
					private Monitor monitor=new Monitor();
					
					public DetModelEntryPoint(){
						detModel.get«component.aspect.event.port.name.toFirstUpper»().registerListener(monitor);
					}
					
					public void reset(){
						monitor.reset();
						detModel.reset();
					}
					
					public String getState(){
						return monitor.state;
					}
					
					public int getFreq(){
						return monitor.freq;
					}
					
					public «compName» getDetModel(){
						return detModel;
					}
					
					public «compName» get«component.analyzedComponent.name.toFirstUpper»(){
						return detModel;
					}   
										
					
					private class Monitor implements «component.aspect.event.port.interfaceRealization.interface.name.toFirstUpper»Interface.Listener.Provided {
						
						public String state="run";
						
						public int freq=0;
		
								
						public void reset(){
							state="stop";
							freq=0;
						}
												
						«FOR event : component.aspect.event.port.interfaceRealization.interface.events»
						@Override
						public void raise«event.event.name.toFirstUpper»(){
							«IF event.event==component.aspect.event.event»
								state="stop";
								freq++;
							«ENDIF»
						}
						
						«ENDFOR»
					}
					
				}
		}
		
		
		
		'''
	}
}