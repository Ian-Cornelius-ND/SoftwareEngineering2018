package edu.nd.se2018.homework.hwk5.model.infrastructure;

import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;

import edu.nd.se2018.homework.hwk5.model.infrastructure.gate.CrossingGate;


/**
 * Creates all infrastructure for the simulation
 * @author Ian Cornelius
 *
 */
public class MapBuilder {
	HashMap<String, Road> roads;
	HashMap<String, CrossingGate> gates;
	HashMap<String, RailwayTracks> tracks;
	
	public MapBuilder(){
		roads = new HashMap<String,Road>();	
		gates = new HashMap<String,CrossingGate>();
		tracks = new HashMap<String,RailwayTracks>();
		buildRoads();
		buildCrossingGates();
		buildTracks();
		assignGatesToRoads();
		buildCarFactories();
	}

	private void buildRoads(){
		//Created new crossing road
		roads.put("Right",new Road(new Point(800,0),new Point (800,1000),Direction.SOUTH,true,false));
		roads.put("Left",new Road(new Point(400,0),new Point (400,1000),Direction.SOUTH,true,false));		
		roads.put("Cross",new Road(new Point(415,400),new Point (785,400),Direction.EAST,true,true));	
	}
	
	private void buildCrossingGates(){
		gates.put("Gate1", new CrossingGate(780,480, "Gate1"));
		gates.put("Gate2", new CrossingGate(380,480, "Gate2"));		
	}
	
	private void buildTracks(){
		//Created new track
		tracks.put("Royal", new RailwayTracks(new Point(0,500),new Point(1200,500)));
		tracks.put("Blue", new RailwayTracks(new Point(0,550),new Point(1200,550)));

	}
	
	private void assignGatesToRoads(){
		roads.get("Right").assignGate(gates.get("Gate1"), gates.get("Gate2"));
		roads.get("Left").assignGate(gates.get("Gate2"), gates.get("Gate1"));
	}
	
	private void buildCarFactories(){
		roads.get("Right").addCarFactory();
		roads.get("Left").addCarFactory();
	}
	
	public Collection<CrossingGate> getAllGates(){
		return gates.values();
	}
	
	public Collection<RailwayTracks> getTracks(){
		return tracks.values();
	}
	
	public Collection<Road> getRoads(){
		return roads.values();
	}
	
	public RailwayTracks getTrack(String name){
		return tracks.get("Royal");
	}
}
