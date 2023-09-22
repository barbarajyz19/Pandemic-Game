package pandemic.action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pandemic.City;
import pandemic.Disease;
import pandemic.roles.Player;

public class TreatTest {

	/* players */
	private Player player1;

	/* action */
	private Treat treat;

	/* cities */
	private City city1;

	/* disease */
	private Disease disease;

	@Before
	public void init() {
		this.disease = new Disease(0);

		this.city1 = new City("ville-1", disease);

		this.treat = new Treat("Treat");

		this.player1 = new Player("Alice", city1);

	}

	/* isPossibleTest */
	@Test
	public void isPossibleTest() {
		assertEquals(city1.getNbCube(disease), 0);
		assertFalse(treat.isPossible(player1)); // player can't treat if there is no cube in the city
		city1.addACube(disease);
		assertEquals(city1.getNbCube(disease), 1);
		assertTrue(treat.isPossible(player1)); // player can treat if there is at least one cube in the city
	}

	/* executeTest */
	@Test
	public void executeTest() {
		assertEquals(0, city1.getNbCube(disease));
		city1.addACube(disease);
		city1.addACube(disease);
		city1.addACube(disease);
		assertEquals(3, city1.getNbCube(disease));
		treat.execute(player1);
		assertEquals(2, city1.getNbCube(disease));

	}
	
	@Test
	public void executeCureFoundTest() {
		assertEquals(0, city1.getNbCube(disease));
		city1.addACube(disease);
		city1.addACube(disease);
		city1.addACube(disease);
		assertEquals(3, city1.getNbCube(disease));
		disease.setCureFound();
		treat.execute(player1);
		assertEquals(0, city1.getNbCube(disease));

	}

}
