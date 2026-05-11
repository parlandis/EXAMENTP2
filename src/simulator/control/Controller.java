package simulator.control;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.AnimalInfo;
import simulator.model.MapInfo;
import simulator.model.Simulator;
import simulator.view.SimpleObjectViewer;
import simulator.view.SimpleObjectViewer.ObjInfo;

public class Controller { 
	private Simulator sim;

	public Controller(Simulator sim) {
		this.sim = sim;
	}
	
	
	public void loadData(JSONObject data) {
		
	}
	
	private List<ObjInfo> toAnimalsInfo(List<? extends AnimalInfo> animals) {
	
	}
	
	public void run(double t, double dt, boolean sv, OutputStream out) {
		
	}
}
