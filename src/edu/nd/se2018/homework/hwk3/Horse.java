package edu.nd.se2018.homework.hwk3;

public class Horse{

	 // Horse variables
	String name;
	double horseNumber;
	double maxSpeed;
	Strategy strategy;
	double currentMile;

	public Horse(){
		this.name = "Horsey";
		this.horseNumber = 0;
		this.maxSpeed = 25;
		this.strategy = new EarlySprintStrategy();
		this.currentMile = 0;
	}

	public Horse(String name, int horseNum, int maxSpeed, Strategy s){
		this.name = name;
		this.horseNumber = horseNum;
		this.maxSpeed = maxSpeed;
		this.strategy = s;
		this.currentMile = 0;
	}

	public double run(){
		return strategy.move(currentMile, maxSpeed);
	}

	public double getMile(){
		return currentMile;
	}
	
	public void setMile(double currentMile){
		this.currentMile = currentMile;
	}

	public String getName(){
		return name;
	}

	public void setStrategy(Strategy s){
		this.strategy = s;
	}
}
