package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.json.JSONArray;
import org.json.JSONObject;


public class RegionManager implements AnimalMapView {
	protected int cols;
	protected int rows;
	protected int width;
	protected int height;
	private int regWidth;
	private int regHeight;
	protected Map<Animal, Region> animalRegion;
	protected Region[][] regions;
	
	
	
	@Override
	public int getCols() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getRows() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getRegionWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getRegionHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getFood(AnimalInfo a, double dt) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Animal> getAnimalsInRange(Animal e, Predicate<Animal> filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}