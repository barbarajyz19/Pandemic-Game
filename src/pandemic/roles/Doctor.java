package pandemic.roles;

import pandemic.City;
import pandemic.action.*;

public class Doctor extends Player implements Role {
	
	public Doctor(String name, City city) {
		super(name,city);
		this.treat = new TreatDoctor("treat");
	}
	
	/**
	 * For get the role 
	 * @return the role 
	 */
	public String getRole() {
		return "Doctor";
	}
	
}
