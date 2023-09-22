package pandemic.roles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pandemic.City;
import pandemic.Disease;
import pandemic.cards.PlayerCard;


public class ScientistTest {

	private City city1;
	private Disease disease;
	private Scientist scientist;

	@Before
	public void init(){
		
		this.disease = new Disease(0);
		this.city1 = new City("ville-1", disease);
		this.scientist = new Scientist("scientist", city1);
	}


	@Test
	public void getRoleTest() {
		assertEquals("Scientist", scientist.getRole());
	}

}
