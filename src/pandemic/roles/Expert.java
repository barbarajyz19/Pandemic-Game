package pandemic.roles;

import pandemic.City;
import pandemic.action.*;

public class Expert extends Player implements Role{

	public Expert(String name, City city) {
		super(name,city);
		this.build = new ExpertBuild("build"); 
	}
	
	/**
	 * For get the role 
	 * @return the role 
	 */
	public String getRole() {
		return "Expert";
	}
}

