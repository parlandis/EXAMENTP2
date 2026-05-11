package simulator.model;

import java.util.spi.CalendarNameProvider;

import simulator.misc.Utils;
import simulator.misc.Vector2D;

public class Sheep extends Animal {
	
	final static String SHEEP_GENETIC_CODE = "Sheep";
	final static double INIT_SIGHT_SHEEP = 40.0;
	final static double INIT_SPEED_SHEEP = 35.0;
	final static double BOOST_FACTOR_SHEEP = 2.0;
	final static double MAX_AGE_SHEEP = 8.0;
	final static double FOOD_DROP_BOOST_FACTOR_SHEEP = 1.2;
	final static double FOOD_DROP_RATE_SHEEP = 20.0;
	final static double DESIRE_THRESHOLD_SHEEP = 65.0;
	final static double DESIRE_INCREASE_RATE_SHEEP = 40.0;
	final static double PREGNANT_PROBABILITY_SHEEP = 0.9;

	private Animal dangerSource;
	private SelectionStrategy dangerStrategy;
	
	public Sheep(SelectionStrategy mateStrategy, SelectionStrategy dangerStrategy,  Vector2D pos) {
		super(SHEEP_GENETIC_CODE, Diet.HERBIVORE, INIT_SIGHT_SHEEP, INIT_SPEED_SHEEP, mateStrategy, pos);
		this.dangerStrategy = dangerStrategy;
		
	}

	protected Sheep(Sheep p1, Animal p2) {
		super(p1, p2);
		this.dangerStrategy = p1.dangerStrategy;
		this.dangerSource = null;
	}

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		switch(this.state) {
		case DEAD:  // No hacemos nada 
			return; 
		case NORMAL: 
			if(pos.distanceTo(dest) > 8.0) dest = new Vector2D(Utils.RAND.nextDouble(0, regionMngr.getWidth() - 1) , Utils.RAND.nextDouble(0, regionMngr.getHeight() - 1) );
			move(speed*dt*Math.exp((energy-100.0)*0.007)); // 1.2
			this.age += dt; //1.3
			this.energy -= 20 * dt; 
			if( energy < 0.0) energy = 0.0; //1.4 
			this.desire += 40.0 *dt;
			if(desire > 100.0 ) this.desire = 100.0; // 1.5
			//2 
			if(this.dangerSource == null) this.dangerStrategy.select(dangerSource, null);//2.1
			else setDangerStateAction();
			return;
			
		case DANGER:
			if(dangerSource!= null && dangerSource.state == State.DEAD ) {  //1 
				this.dangerSource = null;
			}
			
			if(dangerSource == null) {
				if(pos.distanceTo(dest) > 8.0) dest = new Vector2D(Utils.RAND.nextDouble(0, regionMngr.getWidth() - 1) , Utils.RAND.nextDouble(0, regionMngr.getHeight() - 1) );
				move(speed*dt*Math.exp((energy-100.0)*0.007)); // 1.2
				this.age += dt; //1.3
				this.energy -= 20 * dt; 
				if( energy < 0.0) energy = 0.0; //1.4 
				this.desire += 40.0 *dt;
				if(desire > 100.0 ) this.desire = 100.0; // 1.5
			}
			else {
				dest = pos.plus(pos.minus(dangerSource.getPosition()).direction()); // 2.1
				move(2.0*speed*dt*Math.exp((energy-100.0)*0.007)); // 2.2
				this.age += dt; //2.3
				this.energy -= 20.0*1.2*dt; //2.4
				if(this.energy < 0.0) energy = 0.0;
				this.desire += 40.0*dt;
				if(desire > 100.0) desire = 100.0; //2.5		
			}
			
			//3
			if(dangerSource == null || dangerSource.pos.distanceTo(this.pos) > sightRange) {
				this.dangerSource = dangerStrategy.select(dangerSource, null); //3.1.a
				if(dangerSource == null) {
					if(desire < 65.0) setNormalStateAction(); // 3.1.b.a
					else setMateStateAction(); //3.1.b.b.
				}
			}
			return;
		case MATE: 
			if(mateTarget != null && (mateTarget.isDead() || mateTarget.pos.distanceTo(pos) > sightRange)) {
				mateTarget = null; //1
			}
			if(mateTarget == null) {
				mateTarget = mateStrategy.select(mateTarget, null); //2
				if(mateTarget == null) {
					if(pos.distanceTo(dest) > 8.0) dest = new Vector2D(Utils.RAND.nextDouble(0, regionMngr.getWidth() - 1) , Utils.RAND.nextDouble(0, regionMngr.getHeight() - 1) );
					move(speed*dt*Math.exp((energy-100.0)*0.007)); // 1.2
					this.age += dt; //1.3
					this.energy -= 20 * dt; 
					if( energy < 0.0) energy = 0.0; //1.4 
					this.desire += 40.0 *dt;
					if(desire > 100.0 ) this.desire = 100.0; // 1.5
				}
				else{ 
					dest = mateTarget.pos; //2.1
					move(2.0*speed*dt*Math.exp((energy-100.0)*0.007)); //2.2
					this.age += dt; //2.3
					energy -= 20.0*1.2*dt; //2.4
					if(energy < 0.0) energy = 0.0;
					desire += 40.0 *dt;
					if(desire > 100.0) desire = 100.0; //2.5
					
					if(mateTarget.pos.distanceTo(pos) < sightRange) { //2.6
						this.desire = 0.0; 
						this.mateTarget.desire = 0.0;  //2.6.a
						if(baby == null) {
							if (Utils.RAND.nextDouble() < PREGNANT_PROBABILITY_SHEEP) { //2.6.b
								this.baby = new Sheep(this, mateTarget); 
							}
							this.mateTarget = null;
						}
						
					}
					//3
					if(dangerSource == null) {
						this.dangerSource = this.dangerStrategy.select(dangerSource, null);
					}
					//4
					if(dangerSource != null) {
						setDangerStateAction();
					}
					else {
						if(desire < 65.0) {
							setNormalStateAction();
						}
					}
				}
				
				
			}
		case HUNGER:
			return;
		}
		
	}

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2D getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGeneticCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Diet getDiet() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSightRange() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector2D getDestination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPregnant() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void setNormalStateAction() {

		
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
		this.state = state.DANGER;		
	}

	@Override
	protected void setDeadStateAction() {
		// TODO Auto-generated method stub
		
	}

}
