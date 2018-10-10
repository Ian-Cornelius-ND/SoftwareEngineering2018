package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class Enemy implements Observer
{
	Point enemyLocation = new Point();
	Point chip;	
	
	public Enemy() 
	{
		enemyLocation.x = 0;
		enemyLocation.y = 0;
	}

	public void moveEnemy() 
	{
		if (enemyLocation.x - chip.x < 0 && LevelMap.checkForWall(enemyLocation.x + 1, enemyLocation.y))
		{
			enemyLocation.x++;
		}
		else if(LevelMap.checkForWall(enemyLocation.x - 1, enemyLocation.y))
			enemyLocation.x--;
		
		if (enemyLocation.y - chip.y < 0 && LevelMap.checkForWall(enemyLocation.x, enemyLocation.y + 1))
		{
			enemyLocation.y++;
		}
		else if(LevelMap.checkForWall(enemyLocation.x, enemyLocation.y - 1))
		{
			enemyLocation.y--;
		}
	}

	@Override
	public void update(Observable s, Object arg1) 
	{
		if (s instanceof Chip)
		{
			chip = ((Chip)s).getChipLocation();
			moveEnemy();			
		}	
	}

	public Point getEnemyLocation() 
	{
		return enemyLocation;
	}
	
}
