package pandemic.roles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pandemic.City;
import pandemic.Disease;

public class GlobetrotterTest {

	private City city1;
	private Disease disease;
	private Globetrotter globetrotter;

	@Before
	public void init(){
		
		this.disease = new Disease(0);
		this.city1 = new City("ville-1", disease);
		this.globetrotter = new Globetrotter("globetrotter", city1);
	}


	@Test
	public void getRoleTest() {
		assertEquals("Globetrotter", globetrotter.getRole());
	}

}
