package edu.nd.se2018.homework.hwk3;

public class SteadyRunStrategy implements Strategy {
	public SteadyRunStrategy(){}

	public double move(double currentMile, double maxSpeed){
		currentMile += maxSpeed * .8 / 60;
		return currentMile;
	}
}
