package edu.nd.se2018.homework.hwk4;

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

public class OceanExplorer extends Application{
	int[][] islandMap;
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
	Ship ship = new Ship();
	PirateShip pirateShip1 = new PirateShip();
	PirateShip pirateShip2 = new PirateShip();
	

	@Override
	public void start(Stage oceanStage) throws Exception {
				
		oceanMap.drawMap(root.getChildren(), scalingFactor);
		
		scene = new Scene(root, 625, 625);
		oceanStage.setTitle("The Ocean Blue!");
		oceanStage.setScene(scene);
		oceanStage.show();
		loadShipImage();
		loadPirateShipImages();
		startSailing();
	}
	
	public void loadShipImage() {
		System.out.println(ship.getShipLocation());//Test ship point
		
		shipImage = new Image("images/ColumbusShip.png",25,25,true,true);
		shipImageView = new ImageView(shipImage);
		
		if(oceanMap.oceanGrid[ship.getShipLocation().x][ship.getShipLocation().y] == 0) {
			shipImageView.setX(ship.getShipLocation().x * scalingFactor); 
			shipImageView.setY(ship.getShipLocation().y * scalingFactor);
		}
		else {
			System.out.println("SHIPWRECKED");
			ship = new Ship();
			shipImageView.setX(ship.getShipLocation().x * scalingFactor); 
			shipImageView.setY(ship.getShipLocation().y * scalingFactor);
		}		
		root.getChildren().add(shipImageView);
	}
	
	public void loadPirateShipImages() {
		System.out.println(pirateShip1.getPirateShipLocation());//Test pirate point
		System.out.println(pirateShip2.getPirateShipLocation());//Test pirate point
		
		pirateShipImage = new Image("images/pirateship.png",25,25,true,true);
		pirateShipImageView = new ImageView(pirateShipImage);
		pirateShipImageView2 = new ImageView(pirateShipImage);
		
		if(oceanMap.oceanGrid[pirateShip1.getPirateShipLocation().x][pirateShip1.getPirateShipLocation().y] == 0) {
			pirateShipImageView.setX(pirateShip1.getPirateShipLocation().x * scalingFactor); 
			pirateShipImageView.setY(pirateShip1.getPirateShipLocation().y * scalingFactor);
		}
		else {
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
		
		root.getChildren().add(pirateShipImageView);
		root.getChildren().add(pirateShipImageView2);
	}
	
	public void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent ke) { 
				switch(ke.getCode()){
					case RIGHT: 
						if(ship.getShipLocation().x + 1 < 25)
							if(oceanMap.checkForIsland(ship.getShipLocation().x + 1, ship.getShipLocation().y))
								ship.goEast();
							break; 
					case LEFT:
						if(ship.getShipLocation().x - 1 >= 0)
							if(oceanMap.checkForIsland(ship.getShipLocation().x - 1, ship.getShipLocation().y))
								ship.goWest();
							break; 
					case UP:
						if(ship.getShipLocation().y - 1 >= 0)
							if(oceanMap.checkForIsland(ship.getShipLocation().x, ship.getShipLocation().y - 1))
								ship.goNorth();
							break; 
					case DOWN:
						if(ship.getShipLocation().y + 1 < 25)
							if(oceanMap.checkForIsland(ship.getShipLocation().x, ship.getShipLocation().y + 1))
								ship.goSouth();
							break; 
					default:
						break;
				} 
				shipImageView.setX(ship.getShipLocation().x*scalingFactor); 
				shipImageView.setY(ship.getShipLocation().y*scalingFactor);
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
