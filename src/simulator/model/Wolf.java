package simulator.model;

import simulator.misc.Vector2D;

public class Wolf extends Animal{

	final static String WOLF_GENETIC_CODE = "Wolf";
	final static double INIT_SIGHT_WOLF = 50.0;
	final static double INIT_SPEED_WOLF = 60.0;
	final static double BOOST_FACTOR_WOLF = 3.0;
	final static double MAX_AGE_WOLF = 14.0;
	final static double FOOD_THRESHOLD_WOLF = 50.0;
	final static double FOOD_DROP_BOOST_FACTOR_WOLF = 1.2;
	final static double FOOD_DROP_RATE_WOLF = 18.0;
	final static double FOOD_EAT_VALUE_WOLF = 50.0;
	final static double DESIRE_THRESHOLD_WOLF = 65.0;
	final static double DESIRE_INCREASE_RATE_WOLF = 30.0;
	final static double PREGNANT_PROBABILITY_WOLF = 0.75;
	
	private SelectionStrategy huntingStrategy;
	private Animal huntTarget;
	
	
	public Wolf(SelectionStrategy mateStrategy, SelectionStrategy huntingStrategy,  Vector2D pos) {
		super(WOLF_GENETIC_CODE, Diet.CARNIVORE, INIT_SIGHT_WOLF, INIT_SPEED_WOLF, mateStrategy, pos);
		
	}
	
	protected Wolf(Animal p1, Animal p2) {
		super(p1, p2);
		this.huntingStrategy = p1.huntingStrategy;
		this.huntTarget = null;
	}
	
	
	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void setNormalStateAction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void setMateStateAction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void setHungerStateAction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void setDangerStateAction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void setDeadStateAction() {
		// TODO Auto-generated method stub
		
	}
	
	
}
