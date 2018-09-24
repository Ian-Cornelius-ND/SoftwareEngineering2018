package edu.nd.se2018.homework.hwk5.model.vehicles;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.hwk5.model.infrastructure.gate.CrossingGate;
import edu.nd.se2018.homework.hwk5.view.CarImageSelector;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * Represents Car object
 * @author Ian Cornelius
 *
 */
public class Car extends Observable implements IVehicle, Observer{
	private ImageView ivCar;
	private double currentX = 0;
	private double currentY = 0;
	private double originalY = 0;
	private boolean gateDown = false;
	private double leadCarY = -1; 
	private double speed = 0.5;
	private boolean changeLanes = false;					
	private Collection<CrossingGate> gates;
	private CrossingGate changeGate;	//Get gate to switch observer to
		
	/**
	 * Constructor
	 * @param x initial x coordinate of car
	 * @param y initial y coordinate of car
	 * @param gates 
	 */
	public Car(int x, int y, Collection<CrossingGate> gates, CrossingGate changeGate){
		this.currentX = x;
		this.currentY = y;
		this.gates = gates;
		this.changeGate = changeGate;
		originalY = y;
		ivCar = new ImageView(CarImageSelector.getImage());
		ivCar.setX(getVehicleX());
		ivCar.setY(getVehicleY());
	}
		
	@Override
	public Node getImageView() {
		return ivCar;
	}
	
	public boolean gateIsClosed(){
		return gateDown;
	}
	
	public double getVehicleX(){
		return currentX;
	}
	public double getVehicleY(){
		return currentY;
	}
	
	public void move(){
		boolean canMove = true; 
		boolean carCrossing = false;
		//Check if right cars could change lanes (i.e. at road coordinates) and give chance to cross
		if(getVehicleY() < 394 && getVehicleY() > 390 && getVehicleX() == 791 && Math.random() > .995 && carCrossing == false) {
			changeLanes = true;
			carCrossing = true;
			ivCar.setRotate(ivCar.getRotate()+90);
			//Puts cars in an array to easier access
			Object[] gatesArray = gates.toArray();				
			
			//Deletes observer gate from car and adds the other
			((CrossingGate)gatesArray[0]).deleteObserver(this);
			changeGate.addObserver(this);
		}
		
		if(changeLanes){
			ivCar.setX(ivCar.getX()-1);			
			this.currentX--;
			
			//When car reaches other lane
			if(ivCar.getX() == 391)	{
			//Disconnect from lane:
				this.leadCarY = -1;
				changeLanes = false;
				ivCar.setRotate(ivCar.getRotate()-90);
			}
			setChanged();
			notifyObservers();
			//end disconnect
		}
		
		//else, move as usual
		else{
			// First case.  Car is at the front of the stopping line.
			if (gateDown && getVehicleY() < 430 && getVehicleY()> 390)
				canMove = false;
			
			// Second case. Car is too close too other car.
			if (leadCarY != -1  && getDistanceToLeadCar() < 100)
				canMove = false;
			
			if (canMove && carCrossing == false){
				currentY+=speed;
				ivCar.setY(currentY);
				setChanged();
				notifyObservers();
			}
		}
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	public void setGateDownFlag(boolean gateDown){
		this.gateDown = gateDown;
	}
	
	public boolean offScreen(){
		if (currentY > 1020)
			return true;
		else
			return false;			
	}
		
	public void reset(){
		currentY = originalY;
	}
	
	public double getDistanceToLeadCar(){
		return Math.abs(leadCarY-getVehicleY());
	}
	
	public void removeLeadCar(){
		leadCarY = -1;
	}

	@Override
	public void update(Observable o, Object arg1) {
		if (o instanceof Car){
			leadCarY = (((Car)o).getVehicleY());
		if (leadCarY > 1020 || (((Car)o).getMoveWest()))							//If the lead car is out of range or is moving west, set lead car to -1 
			{
				leadCarY = -1;
			}
		}
			
		if (o instanceof CrossingGate){
			CrossingGate gate = (CrossingGate)o;
			if(gate.getTrafficCommand()=="STOP")			
				gateDown = true;
			else
				gateDown = false;
					
		}				
	}

	private boolean getMoveWest() {
		return this.changeLanes;
	}	
}
