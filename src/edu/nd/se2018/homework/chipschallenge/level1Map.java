package edu.nd.se2018.homework.chipschallenge;

import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class level1Map {
	Random r = new Random();
	
	public void draw(ObservableList<Node> root, int[][] levelLayout) {
		for(int i = 0; i < 25; i++) //Set background
		{
			for(int j = 0; j < 25; j++) 
			{
				Image blankImage = new Image("images/blankTile.png",25,25,true,true);
				ImageView blankImageView = new ImageView(blankImage);
				blankImageView.setX(i * 25);
				blankImageView.setY(j * 25);
				root.add(blankImageView);
				levelLayout[i][j] = 0;
			}
		}
		for(int i = 0; i < 2; i++) //Outer vert walls
		{
			for(int j = 0; j < 15; j++)
			{
				int wallX = (i * 15 + 5);
				int wallY = (j + 5);
				Image wallImage = new Image("images/wallTile.png",25,25,true,true); 
				ImageView wallImageView = new ImageView(wallImage);
				wallImageView.setX(wallX * 25);
				wallImageView.setY(wallY * 25);
				root.add(wallImageView);
				levelLayout[wallX][wallY] = 1;
			}
		}
		
		for(int i = 0; i < 2; i++) //Outer hori walls
		{
			for(int j = 0; j < 16; j++)
			{
				int wallY = (i * 15 + 5);
				int wallX = (j + 5);
				Image wallImage = new Image("images/wallTile.png",25,25,true,true); 
				ImageView wallImageView = new ImageView(wallImage);
				wallImageView.setX(wallX * 25);
				wallImageView.setY(wallY * 25);
				root.add(wallImageView);
				levelLayout[wallX][wallY] = 1;
			}
		}

		for(int k = 0; k < 25; k++) { //Make random walls in play area
			int wallX = (int)(r.nextInt(15) + 5);
			int wallY = (int)(r.nextInt(15) + 5);
			
			if(levelLayout[wallX][wallY] == 0) {
				Image targetImage = new Image("images/wallTile.png",25,25,true,true);
				ImageView targetImageView = new ImageView(targetImage);
				targetImageView.setX(wallX * 25);
				targetImageView.setY(wallY * 25);
				root.add(targetImageView);

				levelLayout[wallX][wallY] = 1; //Toggle '1' for wall
			}
			else
				k--;
		}
		
		for(int n = 0; n < 5; n++) {
			int chipX = (int)(r.nextInt(15) + 5);
			int chipY = (int)(r.nextInt(15) + 5);
			
			if(levelLayout[chipX][chipY] == 0) {
				Image targetImage = new Image("images/chipItem.png",25,25,true,true);
				ImageView targetImageView = new ImageView(targetImage);
				targetImageView.setX(chipX * 25);
				targetImageView.setY(chipY * 25);
				root.add(targetImageView);

				levelLayout[chipX][chipY] = 2; //Toggle '2' for chip
			}
			else
				n--;
		}
		
		for(int m = 0; m < 1; m++) {
			int refreshX = (int)(r.nextInt(15) + 5);
			int refreshY = (int)(r.nextInt(15) + 5);
			
			if(levelLayout[refreshX][refreshY] == 0) {
				Image targetImage = new Image("images/portal.png",25,25,true,true);
				ImageView targetImageView = new ImageView(targetImage);
				targetImageView.setX(refreshX * 25);
				targetImageView.setY(refreshY * 25);
				root.add(targetImageView);
		
				levelLayout[refreshX][refreshY] = 3; //Toggle '3' for refresh level
			}
			else
				m--;
		}
		
		for(int m = 0; m < 1; m++) {
			int finishX = (int)(r.nextInt(15) + 5);
			int finishY = (int)(r.nextInt(15) + 5);
			
			if(levelLayout[finishX][finishY] == 0) {
				Image targetImage = new Image("images/portal.png",25,25,true,true);
				ImageView targetImageView = new ImageView(targetImage);
				targetImageView.setX(finishX * 25);
				targetImageView.setY(finishY * 25);
				root.add(targetImageView);
		
				levelLayout[finishX][finishY] = 4; //Toggle '9' for exit game
				//System.out.println("exit: " + finishX + ", " + finishY);
			}
			else
				m--;
		}
	}

}
