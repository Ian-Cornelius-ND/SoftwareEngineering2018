package edu.nd.se2018.homework.hwk4;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OceanMap {
	static int[][] oceanGrid = new int[25][25]; 
	final int dimensions = 25;
	
	//Colors map with grid lines, water, and islands
	public void drawMap(ObservableList<Node> root, int scalingFactor) {
		for(int i = 0; i < dimensions; i++) {
			for(int j = 0; j < dimensions; j++) {
				Rectangle rect = new Rectangle(i*scalingFactor, j*scalingFactor, scalingFactor, scalingFactor);
				rect.setStroke(Color.BLACK);
				rect.setFill(Color.TURQUOISE);
				root.add(rect);
				oceanGrid[i][j] = 0;
			}
		}
		
		//'For' loop to draw islands, placing island images at randomly designated positions
		//For loop limit indicates number of islands
		for(int k = 0; k < 50; k++) {
			int islandX = (int)((25)*Math.random());
			int islandY = (int)((25)*Math.random());
			
			if(oceanGrid[islandX][islandY] == 0) {
				Image targetImage = new Image("images/island.png",25,25,true,true);
				ImageView targetImageView = new ImageView(targetImage);
				targetImageView.setX(islandX * scalingFactor);
				targetImageView.setY(islandY * scalingFactor);
				root.add(targetImageView);

				oceanGrid[islandX][islandY] = 1; //Toggle '1' for island
			}
		}
	}
	
	//Simple check if a position is an island or water
	public static boolean checkForWater(int x, int y) {
		if(oceanGrid[x][y] == 1)
			return false;
		return true;
	}
	
}
