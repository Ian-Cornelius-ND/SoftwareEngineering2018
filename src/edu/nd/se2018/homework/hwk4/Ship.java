package edu.nd.se2018.homework.hwk4;

import java.awt.Point;
import java.util.Observable;

public class Ship extends Observable{
	Point shipLocation = new Point();
	
	public Ship() {
		shipLocation.x = (int)((25)*Math.random()); //Sets initial location of ship somewhere 
		shipLocation.y = (int)((25)*Math.random()); //between s[0][0] and [25][25]
	}
	
	public Point getShipLocation() {
		return shipLocation;
	}
	
	public void goNorth() {
		if(this.shipLocation.y - 1 >= 0)
			this.shipLocation.y -= 1; 
	}
	public void goEast() {
		if(this.shipLocation.x + 1 <= 24)
			this.shipLocation.x += 1;
	}
	public void goSouth() {
		if(this.shipLocation.y + 1 <= 24)
			this.shipLocation.y += 1;
	}
	public void goWest() {
		if(this.shipLocation.x - 1 >= 0)
			this.shipLocation.x -= 1;
	}
}
