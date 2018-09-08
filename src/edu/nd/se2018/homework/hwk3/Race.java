package edu.nd.se2018.homework.hwk3;

public class Race {
	int length;
	Horse horses[] = new Horse[5]; // 5 horses per race
	int numHorses = 0; 		   	   // Keeps track of horses in case horses < 5
	int raceUpdateCount = 0;	   // Counting number of printouts displayed to screen

	public Race(){
		length = 10;
	}

	public Race(int length){
		this.length = length;
	}

	public void enrollHorse(String name, int horseNum, int maxSpeed, Strategy s) {
		horses[numHorses] = new Horse(name, horseNum, maxSpeed, s);
		numHorses++;
	}
	
	public void printHorses(){
		for (int i = 0; i < numHorses; i++) {
			System.out.println(horses[i].getName() + " distance: " + Math.floor(horses[i].getMile()*100)/100);
		}
	}

	public String race(){
		boolean raceFinished = false;
		while (!raceFinished){
			for (int i = 0; i < numHorses; i++){
				horses[i].setMile(horses[i].run()); //Update their current distance
			}
			
			for (int j = 0; j < numHorses; j++){ // Check for winners
				if (horses[j].getMile() >= this.length) {
					raceFinished = true;
					break;
				}
			}
			
			if (raceUpdateCount % 10 == 0 && !raceFinished){ // Print horses periodically
				printHorses();
				System.out.println("-----Update " + raceUpdateCount/10 + "-----");
			}
		
			raceUpdateCount++;
		}
		return announceWinner(); // Calculate winner once race ends
	}

	public String announceWinner() // Print winner method
	{
		double furthest = 0; // Keep track of farthest mileage
		int furthestIndex = 0; // Index of farthest mile
		printHorses();
		
		for (int i = 0; i < numHorses; i++) {
			if (furthest < horses[i].getMile()){ 
				furthest = horses[i].getMile();
				furthestIndex = i;
			}
		}
		return horses[furthestIndex].getName() + " wins!";
	}

}
