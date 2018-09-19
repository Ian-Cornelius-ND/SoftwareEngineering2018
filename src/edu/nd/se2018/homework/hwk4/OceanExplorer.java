package edu.nd.se2018.homework.hwk4;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OceanExplorer extends Application{
	//Instantiate variables
	Pane root = new AnchorPane();
	final int dimensions = 25;
	final int islandCount = 25;
	final int scalingFactor = 25;
	Image shipImage;
	ImageView shipImageView;

	ImageView pirateShipImageView;
	ImageView pirateShipImageView2;
	Image pirateShipImage;
	
	OceanMap oceanMap = new OceanMap();
	Scene scene;
	
	//Creates new ships with random starting locations
	Ship ship = new Ship();
	PirateShip pirateShip1 = new PirateShip();
	PirateShip pirateShip2 = new PirateShip();
	

	@Override
	public void start(Stage oceanStage) throws Exception {
		//Sets stage and draws images
		oceanMap.drawMap(root.getChildren(), scalingFactor);
		scene = new Scene(root, 625, 625);
		oceanStage.setTitle("Columbus vs. Pirates");
		oceanStage.setScene(scene);
		oceanStage.show();
		loadShipImage();
		loadPirateShipImages();
		startSailing();
	}
	
	public void loadShipImage() {	
		
		//Place Columbus ship
		shipImage = new Image("images/ColumbusShip.png",25,25,true,true);
		shipImageView = new ImageView(shipImage);
		
		if(oceanMap.oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y] == 0) {
			//Places new ship on water...hopefully
			shipImageView.setX(ship.getShipLocation().x * scalingFactor); 
			shipImageView.setY(ship.getShipLocation().y * scalingFactor);
		}
		else {
			//'else' occurs when ship happens to be placed on as island.
			//If this occurs, a new ship will be created instead.
			
			//System.out.println("SHIPWRECKED"); //Test print
			ship = new Ship();
			shipImageView.setX(ship.getShipLocation().x * scalingFactor); 
			shipImageView.setY(ship.getShipLocation().y * scalingFactor);
		}		
		root.getChildren().add(shipImageView);
	}
	
	public void loadPirateShipImages() {
		
		//Places 2 pirate ships on map
		pirateShipImage = new Image("images/pirateship.gif",25,25,true,true);
		pirateShipImageView = new ImageView(pirateShipImage);
		pirateShipImageView2 = new ImageView(pirateShipImage);
		
		if(oceanMap.oceanGrid[pirateShip1.getPirateShipLocation().x][pirateShip1.getPirateShipLocation().y] == 0) {
			//New pirate ship
			pirateShipImageView.setX(pirateShip1.getPirateShipLocation().x * scalingFactor); 
			pirateShipImageView.setY(pirateShip1.getPirateShipLocation().y * scalingFactor);
		}
		else {
			//See above comment regarding these else statements
			System.out.println("SHIPWRECKED");
			pirateShip1 = new PirateShip();
			pirateShipImageView.setX(pirateShip1.getPirateShipLocation().x * scalingFactor); 
			pirateShipImageView.setY(pirateShip1.getPirateShipLocation().y * scalingFactor);
		}		
		
		if(oceanMap.oceanGrid[pirateShip2.getPirateShipLocation().x][pirateShip2.getPirateShipLocation().y] == 0) {
			pirateShipImageView2.setX(pirateShip2.getPirateShipLocation().x * scalingFactor); 
			pirateShipImageView2.setY(pirateShip2.getPirateShipLocation().y * scalingFactor);
		}
		else {
			System.out.println("SHIPWRECKED");
			pirateShip2 = new PirateShip();
			pirateShipImageView2.setX(pirateShip2.getPirateShipLocation().x * scalingFactor); 
			pirateShipImageView2.setY(pirateShip2.getPirateShipLocation().y * scalingFactor);
		}
		
		//Now that two pirate ships are created, they are added to root.getChildren()
		//and are made observers of the ship.
		
		root.getChildren().add(pirateShipImageView);
		root.getChildren().add(pirateShipImageView2);
		ship.addObserver(pirateShip1);
		ship.addObserver(pirateShip2);
	}
	
	public void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent ke) { 
				switch(ke.getCode()){
					//Following 'if' statements are executed if the desired ship movement is both
					//not out of bounds and between spaces of water
				
					case RIGHT: 
						if(ship.getShipLocation().x + 1 < 25 && oceanMap.checkForWater(ship.getShipLocation().x + 1, ship.getShipLocation().y)) {
								ship.goEast();
								chase();
								checkCaptured();
							}
							break; 
					case LEFT:
						if(ship.getShipLocation().x - 1 >= 0 && oceanMap.checkForWater(ship.getShipLocation().x - 1, ship.getShipLocation().y)) {
								ship.goWest();
								chase();
								checkCaptured();
							}
							break; 
					case UP:
						if(ship.getShipLocation().y - 1 >= 0 && oceanMap.checkForWater(ship.getShipLocation().x, ship.getShipLocation().y - 1)) {
								ship.goNorth();
								chase();
								checkCaptured();
							}
							break; 
					case DOWN:
						if(ship.getShipLocation().y + 1 < 25 && oceanMap.checkForWater(ship.getShipLocation().x, ship.getShipLocation().y + 1)) {
								ship.goSouth();
								chase();
								checkCaptured();
							}
							break; 
					default:
						break;
				} 
				
				//Adjust ship image based on movement
				shipImageView.setX(ship.getShipLocation().x*scalingFactor); 
				shipImageView.setY(ship.getShipLocation().y*scalingFactor);
			}
		});
	}
	
	public void chase() {
		//Update pirate images to follow ship
		pirateShipImageView.setY(pirateShip1.getPirateShipLocation().y * scalingFactor);
		pirateShipImageView.setX(pirateShip1.getPirateShipLocation().x * scalingFactor);
		pirateShipImageView2.setY(pirateShip2.getPirateShipLocation().y * scalingFactor);
		pirateShipImageView2.setX(pirateShip2.getPirateShipLocation().x * scalingFactor);
	}
	
	public void checkCaptured() {
		//Prints statement if either pirate catches ship
		if(ship.getShipLocation().getX() == pirateShip1.getPirateShipLocation().getX() && ship.getShipLocation().getY() == pirateShip1.getPirateShipLocation().getY()
				|| ship.getShipLocation().getX() == pirateShip2.getPirateShipLocation().getX() && ship.getShipLocation().getY() == pirateShip2.getPirateShipLocation().getY()){
			System.out.println("Pirate ship has captured Columbus!");
			}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
