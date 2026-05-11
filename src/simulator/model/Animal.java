package simulator.model;

import org.json.JSONObject;

import simulator.misc.Utils;
import simulator.misc.Vector2D;

public abstract class Animal implements Entity, AnimalInfo {
	// Constantes
	final static double INIT_ENERGY = 100.0;
	final static double MUTATION_TOLERANCE = 0.2;
	final static double NEARBY_FACTOR = 60.0;
	final static double COLLISION_RANGE = 8;
	final static double HUNGER_DECAY_EXP_FACTOR = 0.007;
	final static double MAX_ENERGY = 100.0;
	final static double MAX_DESIRE = 100.0;

	// Atributos
	protected String geneticCode;
	protected Diet diet;
	protected State state;
	protected Vector2D pos;
	protected Vector2D dest;
	protected double energy;
	protected double speed;
	protected double age;
	protected double desire;
	protected double sightRange;
	protected Animal mateTarget;
	protected Animal baby;
	protected AnimalMapView regionMngr;
	protected SelectionStrategy mateStrategy;
	
	
	protected Animal(String geneticCode, Diet diet, double sightRange, double initSpeed, SelectionStrategy mateStrategy, Vector2D pos) throws IllegalArgumentException{
		if(geneticCode == null || sightRange < 0 || initSpeed < 0 || mateStrategy == null) {
			throw new IllegalArgumentException("Argumentos no validos para crear el animal");
		}
		
		this.geneticCode = geneticCode;
		this.diet = diet;
		this.sightRange = sightRange;
		this.pos = pos;
		this.mateStrategy = mateStrategy;
		this.speed = Utils.getRandomizedParameter(initSpeed, 0.1);
		this.state = State.NORMAL;
		this.energy = 100.0;
		this.desire = 0.0;
		this.dest = null;
		this.baby= null;
		this.regionMngr = null;
	}
	
	protected Animal(Animal p1, Animal p2) {
		
		this.state = State.NORMAL;
		this.mateTarget = null;
		this.dest = null;
		this.baby= null;
		this.regionMngr = null;
		this.desire = 0.0;
		this.geneticCode = p1.geneticCode;
		this.diet = p1.diet;
		this.mateStrategy = p2.mateStrategy;
		this.energy = (p1.energy + p2.energy) /2;
		this.pos = p1.getPosition().plus(Vector2D.get_random_vector(-1,1).scale(60.0*(Utils.RAND.nextGaussian()+1)));
		this.sightRange = Utils.getRandomizedParameter((p1.getSightRange()+p2.getSightRange())/2, 0.2);
		this.speed = Utils.getRandomizedParameter((p1.getSpeed()+p2.getSpeed())/2, 0.2);
	}
	
	protected void init(AnimalMapView regMngr) {
		this.regionMngr = regMngr;
		if(this.pos == null) {
			this.pos = new Vector2D(Utils.RAND.nextDouble(0, regionMngr.getWidth() - 1) , Utils.RAND.nextDouble(0, regionMngr.getHeight() - 1) );
		}
		this.dest = new Vector2D(Utils.RAND.nextDouble(0, regionMngr.getWidth() - 1) , Utils.RAND.nextDouble(0, regionMngr.getHeight() - 1) );
	}
	
	public Animal deliverBaby() {
		Animal bebe = baby;
		baby = null;
		return bebe;
	}
	
	protected void move(double speed) {
		pos = pos.plus(dest.minus(pos).direction().scale(speed));
	}
	
	protected void setState(State state) { 
		this.state = state;
		switch (state) {
			case NORMAL:
				setNormalStateAction();
				break;
			case HUNGER:
				setHungerStateAction();
				break;
			case MATE:
				setMateStateAction();
				break;
			case DANGER:
				setDangerStateAction();
				break;
			case DEAD:
				setDeadStateAction();
				break;
		}
	}
	
	
	@Override
	public State getState() {
		return this.state;
	}

	@Override
	public Vector2D getPosition() {
		return this.pos;
	}

	@Override
	public String getGeneticCode() {
		return this.geneticCode;
	}

	@Override
	public Diet getDiet() {
		return this.diet;
	}

	@Override
	public double getSpeed() {
		return this.speed;
	}

	@Override
	public double getSightRange() {
		return this.sightRange;
	}

	@Override
	public double getEnergy() {

		return this.energy;
	}

	@Override
	public double getAge() {
		return this.age;
	}

	@Override
	public Vector2D getDestination() {
		return this.dest;
	}

	@Override
	public boolean isPregnant() {
		return this.baby != null;
	}

	protected boolean isDead() {
		return this.state == State.DEAD;
	}

	
	abstract protected void setNormalStateAction();
	abstract protected void setMateStateAction();
	abstract protected void setHungerStateAction();
	abstract protected void setDangerStateAction();
	abstract protected void setDeadStateAction();
	
	
	public JSONObject asJSON() {
		JSONObject res = new JSONObject();
		res.put("pos", pos.asJSONArray());
		res.put("gcode", this.geneticCode);
		res.put("diet", this.diet);
		res.put("State", this.state);
	
		return res;
	}
	
	

}
