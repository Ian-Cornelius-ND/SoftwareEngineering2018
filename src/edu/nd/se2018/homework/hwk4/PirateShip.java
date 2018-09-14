package edu.nd.se2018.homework.hwk4;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class PirateShip implements Observer{
	Point pirateShipLocation = new Point();
	
	public PirateShip() {
		pirateShipLocation.x = (int)((25)*Math.random());
		pirateShipLocation.y = (int)((25)*Math.random());
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public Point getPirateShipLocation() {
		return pirateShipLocation;
	}
	
}
