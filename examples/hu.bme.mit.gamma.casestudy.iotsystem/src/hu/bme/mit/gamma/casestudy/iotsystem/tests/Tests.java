package hu.bme.mit.gamma.casestudy.iotsystem.tests;

import hu.bme.mit.gamma.casestudy.iotsystem.iotsystem.IoTSystem;
import javaenv.DetModelEntryPoint;

public class Tests {

	public static void main(String[] args) {
		//IoTSystem detModel=new IoTSystem();
		DetModelEntryPoint detmodel=new DetModelEntryPoint();
		detmodel.reset();
		System.out.println("Test is finished!");
	}

}
