package edu.nd.se2018.homework.chipschallenge;

import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LevelMap 
{
	static int[][] levelLayout;
	Random r = new Random();
	
	public void drawLevel1Map(ObservableList<Node> root) {
		levelLayout = new int[25][25];
		level1Map one = new level1Map();
		one.draw(root, levelLayout);
	}
	
	public void drawLevel2Map(ObservableList<Node> root) {
		levelLayout = new int[25][25];
		level2Map two = new level2Map();
		two.draw(root, levelLayout);
	}
	
	
	public void collectChip(ObservableList<Node> root, int x, int y, int c) {
		Image targetImage = new Image("images/blankTile.png",25,25,true,true);
		ImageView targetImageView = new ImageView(targetImage);
		targetImageView.setX(x * 25);
		targetImageView.setY(y * 25);
		root.add(targetImageView);
		levelLayout[x][y] = 0;
		
		Image chipImage = new Image("images/chipItem.png",25,25,true,true);
		ImageView chipImageView = new ImageView(chipImage);
		chipImageView.setX(c * 25);
		chipImageView.setY(0 * 25);
		root.add(chipImageView);
		levelLayout[c][0] = 1;
	}
	
	public void collectKey(ObservableList<Node> root, int x, int y, int k) {
		Image targetImage = new Image("images/blankTile.png",25,25,true,true);
		ImageView targetImageView = new ImageView(targetImage);
		targetImageView.setX(x * 25);
		targetImageView.setY(y * 25);
		root.add(targetImageView);
		levelLayout[x][y] = 0;
		
		switch(k) {
		case 0:
			Image keyImage = new Image("images/blueKey.png",25,25,true,true);
			ImageView keyImageView = new ImageView(keyImage);
			keyImageView.setX(k * 25);
			keyImageView.setY(1 * 25);
			root.add(keyImageView);
			
			//System.out.println("TESTING DOOR");
			
			targetImage = new Image("images/blankTile.png",25,25,true,true);
			targetImageView = new ImageView(targetImage);
			targetImageView.setX(12 * 25);
			targetImageView.setY(8 * 25);
			root.add(targetImageView);
			levelLayout[12][8] = 0;
			break;
		case 1:
			keyImage = new Image("images/greenKey.png",25,25,true,true);
			keyImageView = new ImageView(keyImage);
			keyImageView.setX(k * 25);
			keyImageView.setY(1 * 25);
			root.add(keyImageView);
			
			//System.out.println("TESTING DOOR");
			
			targetImage = new Image("images/blankTile.png",25,25,true,true);
			targetImageView = new ImageView(targetImage);
			targetImageView.setX(12 * 25);
			targetImageView.setY(10 * 25);
			root.add(targetImageView);
			levelLayout[12][10] = 0;
			break;
		case 2:
			keyImage = new Image("images/redKey.png",25,25,true,true);
			keyImageView = new ImageView(keyImage);
			keyImageView.setX(k * 25);
			keyImageView.setY(1 * 25);
			root.add(keyImageView);
			
			//System.out.println("TESTING DOOR");
			
			targetImage = new Image("images/blankTile.png",25,25,true,true);
			targetImageView = new ImageView(targetImage);
			targetImageView.setX(18 * 25);
			targetImageView.setY(6 * 25);
			root.add(targetImageView);
			levelLayout[18][6] = 0;
			break;
		}

	}
	
	public static boolean checkForWall(int x, int y) 
	{
		if(levelLayout[x][y] == 0)
		{
			return true;
		}
		return false;
	}
	
	public static boolean checkForChip(int x, int y) 
	{
		if(levelLayout[x][y] == 2)
		{
			return true;
		}
		return false;
	}

	public static boolean checkForReset(int x, int y) 
	{
		if(levelLayout[x][y] == 3)
		{
			return true;
		}
		return false;
	}
	
	public static boolean checkForExit(int x, int y) 
	{
		if(levelLayout[x][y] == 4)
		{
			return true;
		}
		return false;
	}
	
	public static boolean checkForKey(int x, int y) 
	{
		if(levelLayout[x][y] == 5)
		{
			return true;
		}
		return false;
	}
	
	public static boolean checkForGameOver(int x, int y) 
	{
		if(levelLayout[x][y] == 9)
		{
			return true;
		}
		return false;
	}
	
}
