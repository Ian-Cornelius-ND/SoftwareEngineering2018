package edu.nd.se2018.homework.hwk3;

public class EarlySprintStrategy implements Strategy {
	public EarlySprintStrategy(){}

	public double move(double currentMile, double maxSpeed){
		if (currentMile < 2){
			currentMile += maxSpeed / 60;
		}else{
			currentMile += maxSpeed * .75 / 60;
		}
		return currentMile;
	}
}
