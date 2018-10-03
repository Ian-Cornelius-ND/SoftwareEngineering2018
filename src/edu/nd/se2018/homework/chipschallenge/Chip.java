package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Chip extends Observable
{
	List<Observer> observers = new LinkedList<Observer>();
	Point chipLocation = new Point();
	int collectedChips = 0;
	
	public Chip()
	{
		chipLocation.x = 13;
		chipLocation.y = 7;
	}
	
	public Point getChipLocation() 
	{
		return chipLocation;
	}
	
	public void goNorth()
	{
		if(this.chipLocation.y - 1 >= 0)
		{
			this.chipLocation.y -= 1; 
			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
			notifyObservers();  // Now notify observers.
		}
		
	}
	
	public void goSouth()
	{
		if(this.chipLocation.y + 1 <= 24)
		{
			this.chipLocation.y += 1;
			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
			notifyObservers();  // Now notify observers.
		}
	}
	
	public void goEast() 
	{
		if(this.chipLocation.x + 1 <= 24)
		{
			this.chipLocation.x += 1;
			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
			notifyObservers();  // Now notify observers.
		}
	}
	
	public void goWest() 
	{
		if(this.chipLocation.x - 1 >= 0)
		{
			this.chipLocation.x -= 1;
			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
			notifyObservers();  // Now notify observers.
		}
	}
}
