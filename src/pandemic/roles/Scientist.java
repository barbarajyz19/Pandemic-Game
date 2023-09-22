package pandemic.roles;

import pandemic.City;
import pandemic.action.*;

public class Scientist extends Player implements Role{
	
	public Scientist(String name, City city) {
		super(name,city);
		this.research = new ScientistResearch("research");
	}

	/**
	 * For get the role 
	 * @return the role 
	 */
	public String getRole() {
		return "Scientist";
	}
}

