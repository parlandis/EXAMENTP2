package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import simulator.factories.Factory;


public class Simulator implements JSONable {
	private RegionManager reMng;
	private Factory<Animal> animalsFactory;
	private Factory<Region> regionsFactory;
	private List<Animal> listaA;
	double time;
}
