package edu.nd.se2018.homework.chipschallenge;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MapExplorer extends Application{	
	Pane root = new AnchorPane();
	final int dimensions = 25;
	Image chipImage;
	ImageView chipImageView;
	
	Image enemyImage;
	ImageView enemyImageView;
	Enemy enemy = new Enemy();
	
	LevelMap levelMap = new LevelMap();
	Scene scene;
	Chip chip;
	boolean nextLevel = false;

	@Override
	public void start(Stage levelStage) throws Exception
	{
				
		levelMap.drawLevel1Map(root.getChildren());
		
		scene = new Scene(root, 625, 625);
		levelStage.setTitle("Chip's Challenge");
		levelStage.setScene(scene);
		levelStage.show();
		loadChipImage();
		beginLevel();
	}
	
	public void loadChipImage() 
	{

		chipImage = new Image("images/chipDown.png",25,25,true,true); 
		chipImageView = new ImageView(chipImage);
				
		chip = new Chip();
		chipImageView.setX(chip.getChipLocation().x * 25); 
		chipImageView.setY(chip.getChipLocation().y * 25);
		
		root.getChildren().add(chipImageView);
	}
	
	public void loadEnemies() {
		enemyImage = new Image("images/bugUP.png",25,25,true,true);
		enemyImageView = new ImageView(enemyImage);
		
		enemy.enemyLocation.x = 13;
		enemy.enemyLocation.y = 18;
		enemyImageView.setX(enemy.getEnemyLocation().x * 25);
		enemyImageView.setY(enemy.getEnemyLocation().y * 25);
		
		root.getChildren().add(enemyImageView);
		chip.addObserver(enemy);
	}
	
	public void beginLevel() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent ke)
			{
				switch(ke.getCode())
				{
					case RIGHT: 
						chipImage = new Image("images/chipRight.png",25,25,true,true); 
						chipImageView.setImage(chipImage);
						checkPosition(chip.getChipLocation().x+1, chip.getChipLocation().y, 3);
						if(nextLevel)
							chase();
							checkCaptured();
						break; 
					case LEFT:
						chipImage = new Image("images/chipLeft.png",25,25,true,true); 
						chipImageView.setImage(chipImage);
						checkPosition(chip.getChipLocation().x-1, chip.getChipLocation().y, 4);
						if(nextLevel)
							chase();
							checkCaptured();
						break; 
					case UP:
						chipImage = new Image("images/chipUp.png",25,25,true,true); 
						chipImageView.setImage(chipImage);
						checkPosition(chip.getChipLocation().x, chip.getChipLocation().y - 1, 1);
						if(nextLevel)
							chase();
							checkCaptured();
						break; 
					case DOWN:
						chipImage = new Image("images/chipDown.png",25,25,true,true); 
						chipImageView.setImage(chipImage);
						checkPosition(chip.getChipLocation().x, chip.getChipLocation().y + 1, 2);
						if(nextLevel)
							chase();
							checkCaptured();
						break;
							
					case ESCAPE:
						System.exit(0);
													
					default:
						break;
				}
				chipImageView.setX(chip.getChipLocation().x*25); 
				chipImageView.setY(chip.getChipLocation().y*25);
				root.getChildren().remove(chipImageView);
				root.getChildren().add(chipImageView);
			}
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
			//System.out.println("Chip got chip");
			levelMap.collectChip(root.getChildren(), x, y, chip.collectedChips);
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
		
		else if(LevelMap.checkForKey(x, y)){
			//System.out.println("Key got");
			levelMap.collectKey(root.getChildren(), x, y, chip.collectedKeys);
			chip.collectedKeys++;
			
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
			beginLevel();
		}
		else if(LevelMap.checkForExit(x, y) && chip.collectedChips == 5) {
			levelMap.drawLevel2Map(root.getChildren());
			nextLevel = true;
			loadChipImage();
			loadEnemies();
			beginLevel();
			
			//System.out.println("LEVEL 2");
		}
			
		else if(LevelMap.checkForGameOver(x, y)) {
			System.out.println("Game Over! You Win!");
			System.exit(0);
		}
	}

	public void chase() {
		enemyImageView.setY(enemy.getEnemyLocation().y * 25);
		enemyImageView.setX(enemy.getEnemyLocation().x * 25);
	}
	
	public void checkCaptured() {
		if(chip.getChipLocation().x == enemy.getEnemyLocation().x && chip.getChipLocation().y == enemy.getEnemyLocation().y) {
			System.out.println("Bug has captured Chip!");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
