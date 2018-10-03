package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MapExplorer extends Application
{	Pane root = new AnchorPane();
	final int dimensions = 25;
	Image chipImage;
	ImageView chipImageView;
	
	LevelMap levelMap = new LevelMap();
	Scene scene;
	Chip chip;
	//Enemies enemies[] = new Enemies [3];
	//ImageView enemiesImageView [] = new ImageView[3];

	@Override
	public void start(Stage levelStage) throws Exception
	{
				
		levelMap.drawLevel1Map(root.getChildren());
		
		scene = new Scene(root, 625, 625);
		levelStage.setTitle("Chip's Challenge");
		levelStage.setScene(scene);
		levelStage.show();
		loadChipImage();
		beginLevel1();
	}
	
	public void loadChipImage() 
	{

		chipImage = new Image("images/chipDown.png",25,25,true,true); 
		chipImageView = new ImageView(chipImage);
		
		//do
		//{			
			chip = new Chip();
			chipImageView.setX(chip.getChipLocation().x * 25); 
			chipImageView.setY(chip.getChipLocation().y * 25);
			
		//}while(LevelMap.levelLayout[chip.getChipLocation().x][chip.getChipLocation().y] != 0);	
		root.getChildren().add(chipImageView);
	}
	
//	public void loadEnemyImages() 
//	{
//		piratechipImage = new Image("images/bugUp.png",scalingFactor,scalingFactor,true,true);
//		for(int i = 0; i < 3; i++)
//		{
//			piratechipImageView[i] = new ImageView(piratechipImage);
//			
//			do
//			{			
//				pirateShips[i] = new Enemies();
//				piratechipImageView[i].setX(pirateShips[i].getPirateShipLocation().x * scalingFactor); 
//				piratechipImageView[i].setY(pirateShips[i].getPirateShipLocation().y * scalingFactor);
//				
//			}while(LevelMap.oceanGrid[pirateShips[i].getPirateShipLocation().x][pirateShips[i].getPirateShipLocation().y] != 0);	
//			root.getChildren().add(piratechipImageView[i]);
//			ship.addObserver(pirateShips[i]);
//		}
//	}
	
	public void beginLevel1() 
	{
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
		{

			@Override
			public void handle(KeyEvent ke)
			{
				switch(ke.getCode())
				{
					case RIGHT: 
						chipImage = new Image("images/chipRight.png",25,25,true,true); 
						chipImageView.setImage(chipImage);
						checkPosition(chip.getChipLocation().x+1, chip.getChipLocation().y, 3);
						break; 
					case LEFT:
						chipImage = new Image("images/chipLeft.png",25,25,true,true); 
						chipImageView.setImage(chipImage);
						checkPosition(chip.getChipLocation().x-1, chip.getChipLocation().y, 4);
						break; 
					case UP:
						chipImage = new Image("images/chipUp.png",25,25,true,true); 
						chipImageView.setImage(chipImage);
						checkPosition(chip.getChipLocation().x, chip.getChipLocation().y - 1, 1);
						break; 
					case DOWN:
						chipImage = new Image("images/chipDown.png",25,25,true,true); 
						chipImageView.setImage(chipImage);
						checkPosition(chip.getChipLocation().x, chip.getChipLocation().y + 1, 2);
						break;
							
					case ESCAPE:
						System.exit(0);
													
					default:
						break;
				}
				chipImageView.setX(chip.getChipLocation().x*25); 
				chipImageView.setY(chip.getChipLocation().y*25);
			}

//			private void chaseShip() 
//			{
//				for(int i = 0; i < 3; i++)
//				{
//					piratechipImageView[i].setY(pirateShips[i].getPirateShipLocation().y * scalingFactor);
//					piratechipImageView[i].setX(pirateShips[i].getPirateShipLocation().x * scalingFactor);
//				}
//			}
		});
	}
	
	public void checkPosition(int x, int y, int i) {
		if(LevelMap.checkForWall(x, y))
		{
			switch(i) {
			case 1:
				chip.goNorth();
				break;
			case 2:
				chip.goSouth();
				break;
			case 3:
				chip.goEast();
				break;
			case 4:
				chip.goWest();
				break;
			}

		}
		else if(LevelMap.checkForChip(x, y)){
			System.out.println("Chip got");
			levelMap.collectChip(root.getChildren(), x, y);
			chip.collectedChips++;
			
			switch(i) {
			case 1:
				chip.goNorth();
				break;
			case 2:
				chip.goSouth();
				break;
			case 3:
				chip.goEast();
				break;
			case 4:
				chip.goWest();
				break;
			}
		}
		else if(LevelMap.checkForReset(x, y) 
				|| (LevelMap.checkForExit(x, y) && chip.collectedChips != 5)) {
			levelMap.drawLevel1Map(root.getChildren());
			loadChipImage();
			beginLevel1();
		}
		else if(LevelMap.checkForExit(x, y) && chip.collectedChips == 5) {
			System.out.println("DONE");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
