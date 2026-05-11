package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class Region implements Entity, FoodSupplier, RegionInfo {
	protected List<Animal> animales;
	protected int nH;
	protected int nC;
	
	protected Region() {
		animales = new ArrayList<>();
		nH = 0;
		nC = 0;
	}

	@Override
	public List<AnimalInfo> getAnimalsInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getFoodAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getFood(AnimalInfo a, double dt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}
	
	
}
