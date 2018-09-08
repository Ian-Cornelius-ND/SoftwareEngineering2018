package edu.nd.se2018.homework.hwk3;

public class Main {
	/*
	 * Homework3:
	 * Horse Racing.
	 * September 6, 2018
	 * 
	 * @author Ian Cornelius
	 */
	public static void main(String args[]) {
		Race race = new Race(10);
		race.enrollHorse("Dasher", 0, 18, new EarlySprintStrategy());
		race.enrollHorse("Dancer", 1, 20, new SlowStartStrategy());
		race.enrollHorse("Prancer", 2, 19, new SteadyRunStrategy());
		race.enrollHorse("Vixen", 3, 21, new EarlySprintStrategy());
		race.enrollHorse("Comet", 4, 20, new SlowStartStrategy());
		System.out.println(race.race());
	}

}
