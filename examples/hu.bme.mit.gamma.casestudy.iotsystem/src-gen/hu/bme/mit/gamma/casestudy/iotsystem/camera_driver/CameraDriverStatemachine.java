package hu.bme.mit.gamma.casestudy.iotsystem.camera_driver;

import hu.bme.mit.gamma.casestudy.iotsystem.interfaces.*;

public class CameraDriverStatemachine {
	
	enum Main {__Inactive__, CarIsVisible, CarIsNotVisible}
	enum Main2 {__Inactive__, service}
	private boolean Images_newData_Out;
	private double Images_newData_Out_blurred;
	private boolean Images_newData_Out_car;
	private boolean Requests_newEvent_In;
	private boolean Traffic_carArrives_In;
	private boolean Traffic_carLeaves_In;
	private Main main;
	private Main2 main2;
	private boolean car;
	
	public CameraDriverStatemachine() {
	}
	
	public void reset() {
		this.handleBeforeReset();
		this.resetVariables();
		this.resetStateConfigurations();
		this.raiseEntryEvents();
		this.handleAfterReset();
	}

	public void handleBeforeReset() {
		this.main = Main.__Inactive__;
		this.main2 = Main2.__Inactive__;
		clearOutEvents();
		clearInEvents();
	}
	public void resetVariables() {
		this.Requests_newEvent_In = false;
		this.Traffic_carArrives_In = false;
		this.Traffic_carLeaves_In = false;
		this.Images_newData_Out = false;
		this.Images_newData_Out_blurred = 0;
		this.Images_newData_Out_car = false;
	}

	public void resetStateConfigurations() {
		this.main = Main.CarIsNotVisible;
		this.main2 = Main2.service;
	}

	public void raiseEntryEvents() {
		this.car = false;
	}

	public void handleAfterReset() {
		// Empty
	}
	//
	public void setImages_newData_Out(boolean Images_newData_Out) {
		this.Images_newData_Out = Images_newData_Out;
	}
	
	public boolean getImages_newData_Out() {
		return Images_newData_Out;
	}
	
	public void setImages_newData_Out_blurred(double Images_newData_Out_blurred) {
		this.Images_newData_Out_blurred = Images_newData_Out_blurred;
	}
	
	public double getImages_newData_Out_blurred() {
		return Images_newData_Out_blurred;
	}
	
	public void setImages_newData_Out_car(boolean Images_newData_Out_car) {
		this.Images_newData_Out_car = Images_newData_Out_car;
	}
	
	public boolean getImages_newData_Out_car() {
		return Images_newData_Out_car;
	}
	
	public void setRequests_newEvent_In(boolean Requests_newEvent_In) {
		this.Requests_newEvent_In = Requests_newEvent_In;
	}
	
	public boolean getRequests_newEvent_In() {
		return Requests_newEvent_In;
	}
	
	public void setTraffic_carArrives_In(boolean Traffic_carArrives_In) {
		this.Traffic_carArrives_In = Traffic_carArrives_In;
	}
	
	public boolean getTraffic_carArrives_In() {
		return Traffic_carArrives_In;
	}
	
	public void setTraffic_carLeaves_In(boolean Traffic_carLeaves_In) {
		this.Traffic_carLeaves_In = Traffic_carLeaves_In;
	}
	
	public boolean getTraffic_carLeaves_In() {
		return Traffic_carLeaves_In;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public Main getMain() {
		return main;
	}
	
	public void setMain2(Main2 main2) {
		this.main2 = main2;
	}
	
	public Main2 getMain2() {
		return main2;
	}
	
	public void setCar(boolean car) {
		this.car = car;
	}
	
	public boolean getCar() {
		return car;
	}
	
	public void runCycle() {
		clearOutEvents();
		changeState();
		clearInEvents();
	}

	private void changeState() {
		if ((((this.main == Main.CarIsVisible)) && (this.Traffic_carLeaves_In))) {
			this.main = Main.CarIsNotVisible;
			this.car = false;
		}
		else {
			if ((((this.main == Main.CarIsNotVisible)) && (this.Traffic_carArrives_In))) {
				this.main = Main.CarIsVisible;
				this.car = true;
			}
		}
		if ((((this.main2 == Main2.service)) && (this.Requests_newEvent_In))) {
			this.Images_newData_Out_blurred = 0.0;
			this.Images_newData_Out_car = this.car;
			this.Images_newData_Out = true;
			this.main2 = Main2.service;
		}
	}
	
	private void clearOutEvents() {
		Images_newData_Out = false;
	}
	
	private void clearInEvents() {
		Requests_newEvent_In = false;
		Traffic_carArrives_In = false;
		Traffic_carLeaves_In = false;
	}
	
	@Override
	public String toString() {
		return
			"Images_newData_Out = " + Images_newData_Out + System.lineSeparator() +
			"Images_newData_Out_blurred = " + Images_newData_Out_blurred + System.lineSeparator() +
			"Images_newData_Out_car = " + Images_newData_Out_car + System.lineSeparator() +
			"Requests_newEvent_In = " + Requests_newEvent_In + System.lineSeparator() +
			"Traffic_carArrives_In = " + Traffic_carArrives_In + System.lineSeparator() +
			"Traffic_carLeaves_In = " + Traffic_carLeaves_In + System.lineSeparator() +
			"main = " + main + System.lineSeparator() +
			"main2 = " + main2 + System.lineSeparator() +
			"car = " + car
		;
	}
	
}
