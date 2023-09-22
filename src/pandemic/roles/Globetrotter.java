package pandemic.roles;

import pandemic.City;
import pandemic.action.*;

/*
 * class for globetrotter
 */
public class Globetrotter extends Player implements Role{

	
	public Globetrotter(String name, City city) {
		super(name,city);
		this.move = new MoveGlobtrotter("move");
	}

	/**
	 * get the role 
	 * @return the role 
	 */
	public String getRole() {
		return "Globetrotter";
	}
	
}


