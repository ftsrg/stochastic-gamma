package hu.bme.mit.gamma.casestudy.iotsystem.camera_control;

import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;

public class CameraControlStatemachine {
	
	enum Main2 {__Inactive__, MainOperation2}
	enum Main {__Inactive__, MainOperation}
	private boolean Requests_newEvent_Out;
	private boolean NetworkImages_newData_Out;
	private boolean DriverImages_newData_In;
	private double DriverImages_newData_In_blurred;
	private boolean DriverImages_newData_In_car;
	private double NetworkImages_newData_Out_blurred;
	private boolean NetworkImages_newData_Out_car;
	private Main2 main2;
	private Main main;
	
	public CameraControlStatemachine() {
	}
	
	public void reset() {
		this.main2 = Main2.__Inactive__;
		this.main = Main.__Inactive__;
		clearOutEvents();
		clearInEvents();
		this.main2 = Main2.__Inactive__;
		this.main = Main.__Inactive__;
		this.DriverImages_newData_In = false;
		this.Requests_newEvent_Out = false;
		this.NetworkImages_newData_Out = false;
		this.DriverImages_newData_In_blurred = 0;
		this.DriverImages_newData_In_car = false;
		this.NetworkImages_newData_Out_blurred = 0;
		this.NetworkImages_newData_Out_car = false;
		this.main = Main.MainOperation;
		this.main2 = Main2.MainOperation2;
	}
	
	public void setRequests_newEvent_Out(boolean Requests_newEvent_Out) {
		this.Requests_newEvent_Out = Requests_newEvent_Out;
	}
	
	public boolean getRequests_newEvent_Out() {
		return Requests_newEvent_Out;
	}
	
	public void setNetworkImages_newData_Out(boolean NetworkImages_newData_Out) {
		this.NetworkImages_newData_Out = NetworkImages_newData_Out;
	}
	
	public boolean getNetworkImages_newData_Out() {
		return NetworkImages_newData_Out;
	}
	
	public void setDriverImages_newData_In(boolean DriverImages_newData_In) {
		this.DriverImages_newData_In = DriverImages_newData_In;
	}
	
	public boolean getDriverImages_newData_In() {
		return DriverImages_newData_In;
	}
	
	public void setDriverImages_newData_In_blurred(double DriverImages_newData_In_blurred) {
		this.DriverImages_newData_In_blurred = DriverImages_newData_In_blurred;
	}
	
	public double getDriverImages_newData_In_blurred() {
		return DriverImages_newData_In_blurred;
	}
	
	public void setDriverImages_newData_In_car(boolean DriverImages_newData_In_car) {
		this.DriverImages_newData_In_car = DriverImages_newData_In_car;
	}
	
	public boolean getDriverImages_newData_In_car() {
		return DriverImages_newData_In_car;
	}
	
	public void setNetworkImages_newData_Out_blurred(double NetworkImages_newData_Out_blurred) {
		this.NetworkImages_newData_Out_blurred = NetworkImages_newData_Out_blurred;
	}
	
	public double getNetworkImages_newData_Out_blurred() {
		return NetworkImages_newData_Out_blurred;
	}
	
	public void setNetworkImages_newData_Out_car(boolean NetworkImages_newData_Out_car) {
		this.NetworkImages_newData_Out_car = NetworkImages_newData_Out_car;
	}
	
	public boolean getNetworkImages_newData_Out_car() {
		return NetworkImages_newData_Out_car;
	}
	
	public void setMain2(Main2 main2) {
		this.main2 = main2;
	}
	
	public Main2 getMain2() {
		return main2;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public Main getMain() {
		return main;
	}
	
	public void runCycle() {
		clearOutEvents();
		changeState();
		clearInEvents();
	}

	private void changeState() {
		if ((this.main == Main.MainOperation)) {
			this.Requests_newEvent_Out = true;
			this.main = Main.MainOperation;
		}
		if ((((this.main2 == Main2.MainOperation2)) && (this.DriverImages_newData_In))) {
			this.NetworkImages_newData_Out_blurred = this.DriverImages_newData_In_blurred;
			this.NetworkImages_newData_Out_car = this.DriverImages_newData_In_car;
			this.NetworkImages_newData_Out = true;
			this.main2 = Main2.MainOperation2;
		}
	}
	
	private void clearOutEvents() {
		Requests_newEvent_Out = false;
		NetworkImages_newData_Out = false;
	}
	
	private void clearInEvents() {
		DriverImages_newData_In = false;
	}
	
	@Override
	public String toString() {
		return
			"Requests_newEvent_Out = " + Requests_newEvent_Out + System.lineSeparator() +
			"NetworkImages_newData_Out = " + NetworkImages_newData_Out + System.lineSeparator() +
			"DriverImages_newData_In = " + DriverImages_newData_In + System.lineSeparator() +
			"DriverImages_newData_In_blurred = " + DriverImages_newData_In_blurred + System.lineSeparator() +
			"DriverImages_newData_In_car = " + DriverImages_newData_In_car + System.lineSeparator() +
			"NetworkImages_newData_Out_blurred = " + NetworkImages_newData_Out_blurred + System.lineSeparator() +
			"NetworkImages_newData_Out_car = " + NetworkImages_newData_Out_car + System.lineSeparator() +
			"main2 = " + main2 + System.lineSeparator() +
			"main = " + main
		;
	}
	
}
