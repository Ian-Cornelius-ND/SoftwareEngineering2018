 package edu.nd.se2018.homework.hwk4;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class PirateShip implements Observer{
	Point pirateShipLocation = new Point();
	Point ship;
	
	//New pirate with a random location
	public PirateShip() {
		pirateShipLocation.x = (int)((25)*Math.random());
		pirateShipLocation.y = (int)((25)*Math.random());
	}

	//Moves pirate ship
	public void movePirateShip() {
		//'If' statements executed if adjacent location is closer to Columbus and not an island
		//Pirates are allowed to move diagonally
		if (pirateShipLocation.x != 24 && pirateShipLocation.x - ship.x <= 0 && OceanMap.checkForWater(pirateShipLocation.x + 1, pirateShipLocation.y))
			pirateShipLocation.x++;
		else if(pirateShipLocation.x != 0 && pirateShipLocation.x - ship.x >= 0 && OceanMap.checkForWater(pirateShipLocation.x - 1, pirateShipLocation.y))
			pirateShipLocation.x--;
		if (pirateShipLocation.y != 24 && pirateShipLocation.y - ship.y <= 0 && OceanMap.checkForWater(pirateShipLocation.x, pirateShipLocation.y + 1))
			pirateShipLocation.y++;
		else if(pirateShipLocation.y != 0 && pirateShipLocation.y - ship.y >= 0 && OceanMap.checkForWater(pirateShipLocation.x, pirateShipLocation.y - 1))
			pirateShipLocation.y--;
	}
	
	//Update method of observer overridden to follow Columbus
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Ship){
			ship = ((Ship)o).getShipLocation();
			movePirateShip();			
		}
	}

	public Point getPirateShipLocation() {
		return pirateShipLocation;
	}
	
}
