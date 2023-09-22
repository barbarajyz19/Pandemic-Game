package pandemic.roles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pandemic.City;
import pandemic.Disease;


public class DoctorTest {

	private City city1;
	private Disease disease;
	private Doctor doctor;

	@Before
	public void init(){
		
		this.disease = new Disease(0);
		this.city1 = new City("ville-1", disease);
		this.doctor = new Doctor("doctor", city1);
	}


	@Test
	public void getRoleTest() {
		assertEquals("Doctor", doctor.getRole());
	}

}
