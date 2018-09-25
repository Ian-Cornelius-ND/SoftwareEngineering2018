package edu.nd.se2018.homework.hwk5.model.vehicles;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import edu.nd.se2018.homework.hwk5.model.infrastructure.Direction;
import edu.nd.se2018.homework.hwk5.model.infrastructure.gate.CrossingGate;



/**
 * Very basic car factory.  Creates the car and registers it with the crossing gate and the car infront of it.
 * @author Ian Cornelius
 *
 */
public class CarFactory {
	
	private Collection<CrossingGate> gates = null;
	private Car previousCar = null;
	private ArrayList<Car> cars = new ArrayList<Car>();
	Direction direction;
	Point location;
	CrossingGate changedGate;
	
	public CarFactory(){}
	
	public CarFactory(Direction direction, Point location, Collection<CrossingGate> gates, CrossingGate changedGate){
		this.direction = direction;
		this.location = location;
		this.gates = gates;
		this.changedGate = changedGate;
	}
	
	
	//Create new cars
	public Car buildCar(){
		if (previousCar == null || location.y < previousCar.getVehicleY()-100){
			Car car = new Car(location.x,location.y, gates, changedGate);	
			double speedVariable = (Math.random() * 10)/10;
			car.setSpeed((2-speedVariable)*1.5); 
			
			//Add car/gate observers
			for(CrossingGate gate: gates){
				gate.addObserver(car);
				if(gate != null && gate.getTrafficCommand()=="STOP")
					car.setGateDownFlag(true); //Change made from piazza
			}
			
			//Add car/car observers
			if (previousCar != null)
				previousCar.addObserver(car);
			previousCar = car;
			
			cars.add(car);
			return car;
		} else 
			return null;
	}

	// We will get a concurrency error if we try to delete cars whilst iterating through the array list
	// so we perform this in two stages.
	// 1.  Loop through the list and identify which cars are off the screen.  Add them to 'toDelete' array.
	// 2.  Iterate through toDelete and remove the cars from the original arrayList.
	public ArrayList<Car> removeOffScreenCars() {
		// Removing cars from the array list.
		ArrayList<Car> toDelete = new ArrayList<Car>();
		for(Car car: cars){
			car.move();					
			if (car.offScreen())
				toDelete.add(car);
			
		}   
		for (Car car: toDelete)
			cars.remove(car);
		return toDelete;
	}
}
