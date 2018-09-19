package edu.nd.se2018.homework.hwk4;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class PirateShip implements Observer{
	Point pirateShipLocation = new Point();
	Point ship;
	
	public PirateShip() {
		pirateShipLocation.x = (int)((25)*Math.random());
		pirateShipLocation.y = (int)((25)*Math.random());
	}

	public void movePirateShip() {
		if (pirateShipLocation.x - ship.x < 0 && OceanMap.checkForIsland(pirateShipLocation.x + 1, pirateShipLocation.y))
			pirateShipLocation.x++;
		else if(OceanMap.checkForIsland(pirateShipLocation.x - 1, pirateShipLocation.y))
			pirateShipLocation.x--;
		
		if (pirateShipLocation.y - ship.y < 0 && OceanMap.checkForIsland(pirateShipLocation.x, pirateShipLocation.y + 1))
			pirateShipLocation.y++;
		else if(OceanMap.checkForIsland(pirateShipLocation.x, pirateShipLocation.y - 1))
			pirateShipLocation.y--;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o instanceof Ship)
		{
			ship = ((Ship)o).getShipLocation();
			movePirateShip();			
		}
	}

	public Point getPirateShipLocation() {
		return pirateShipLocation;
	}
	
}
