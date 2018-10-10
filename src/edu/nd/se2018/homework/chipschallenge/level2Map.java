package edu.nd.se2018.homework.chipschallenge;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class level2Map {
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
			
		//making inner hori walls
			for(int i = 0; i < 5; i++)
			{
				for(int j = 5; j < 21; j++)
				{
					int wallY = (i * 3 + 5);
					int wallX = j;
					if(wallY != 8 && wallX != 14) {
						Image wallImage = new Image("images/wallTile.png",25,25,true,true); 
						ImageView wallImageView = new ImageView(wallImage);
						wallImageView.setX(wallX * 25);
						wallImageView.setY(wallY * 25);
						root.add(wallImageView);
						levelLayout[wallX][wallY] = 1;
					}
				}
			}
			
		//making key walls
			for(int i = 4; i < 6; i++)
			{
				for(int j = 5; j < 13; j++)
				{
					int wallY = (i * 2 - 1);
					int wallX = j;
					Image wallImage = new Image("images/wallTile.png",25,25,true,true); 
					ImageView wallImageView = new ImageView(wallImage);
					wallImageView.setX(wallX * 25);
					wallImageView.setY(wallY * 25);
					root.add(wallImageView);
					levelLayout[wallX][wallY] = 1;
					}
				}
			
			for(int j = 6; j < 11; j++)
			{
					int wallY = j;
					int wallX = 18;
					Image wallImage = new Image("images/wallTile.png",25,25,true,true); 
					ImageView wallImageView = new ImageView(wallImage);
					wallImageView.setX(wallX * 25);
					wallImageView.setY(wallY * 25);
					root.add(wallImageView);
					levelLayout[wallX][wallY] = 1;
			}
			
			
			
		//placing keys
			Image blueKeyImage = new Image("images/blueKey.png",25,25,true,true); 
			ImageView blueKeyImageView = new ImageView(blueKeyImage);
			blueKeyImageView.setX(6 * 25);
			blueKeyImageView.setY(6 * 25);
			root.add(blueKeyImageView);
			levelLayout[6][6] = 5;
			
			Image greenKeyImage = new Image("images/greenKey.png",25,25,true,true); 
			ImageView greenKeyImageView = new ImageView(greenKeyImage);
			greenKeyImageView.setX(6 * 25);
			greenKeyImageView.setY(8 * 25);
			root.add(greenKeyImageView);
			levelLayout[6][8] = 5;
			
			Image redKeyImage = new Image("images/redKey.png",25,25,true,true); 
			ImageView redKeyImageView = new ImageView(redKeyImage);
			redKeyImageView.setX(6 * 25);
			redKeyImageView.setY(10 * 25);
			root.add(redKeyImageView);
			levelLayout[6][10] = 5;
			
		//placing locks
			Image blueKeyDoor = new Image("images/blueKeyWall.png",25,25,true,true); 
			ImageView blueKeyDoorView = new ImageView(blueKeyDoor);
			blueKeyDoorView.setX(12 * 25);
			blueKeyDoorView.setY(8 * 25);
			root.add(blueKeyDoorView);
			levelLayout[12][8] = 1;
			
			Image redKeyDoor = new Image("images/redKeyWall.png",25,25,true,true); 
			ImageView redKeyDoorView = new ImageView(redKeyDoor);
			redKeyDoorView.setX(18 * 25);
			redKeyDoorView.setY(6 * 25);
			root.add(redKeyDoorView);
			levelLayout[18][6] = 1;
			
			Image greenKeyDoor = new Image("images/greenKeyWall.png",25,25,true,true); 
			ImageView greenKeyDoorView = new ImageView(greenKeyDoor);
			greenKeyDoorView.setX(12 * 25);
			greenKeyDoorView.setY(10 * 25);
			root.add(greenKeyDoorView);
			levelLayout[12][10] = 1;
			
		//placing chip
			Image chipItem = new Image("images/chipItem.png",25,25,true,true); 
			ImageView chipItemView = new ImageView(chipItem);
			chipItemView.setX(19 * 25);
			chipItemView.setY(6 * 25);
			root.add(chipItemView);
			levelLayout[19][6] = 2;
			
		//placing exit
			Image targetImage = new Image("images/portal.png",25,25,true,true);
			ImageView targetImageView = new ImageView(targetImage);
			targetImageView.setX(19 * 25);
			targetImageView.setY(10 * 25);
			root.add(targetImageView);
	
			levelLayout[19][10] = 9; //Toggle '4' for exit level
			//System.out.println("exit: " + 19 + ", " + 10);
	}

}
