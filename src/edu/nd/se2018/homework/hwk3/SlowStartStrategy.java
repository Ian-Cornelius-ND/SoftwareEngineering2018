package edu.nd.se2018.homework.hwk3;

public class SlowStartStrategy implements Strategy {
	public SlowStartStrategy(){}

	public double move(double currentMile, double maxSpeed){
		if (currentMile < 6){
			currentMile += maxSpeed * .75 / 60; //Converts to miles
		}else if (currentMile < 9){
			currentMile += maxSpeed * .90 / 60;
		}else{
			currentMile += maxSpeed / 60;
		}
		return currentMile;
	}

}
