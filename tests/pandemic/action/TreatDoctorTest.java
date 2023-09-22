package pandemic.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pandemic.City;
import pandemic.Disease;
import pandemic.roles.Player;

public class TreatDoctorTest {

	/* players */
	private Player player1;

	/* action */
	private TreatDoctor treat;

	/* cities */
	private City city1;

	/* disease */
	private Disease disease;

	@Before
	public void init(){

		this.disease = new Disease(0);

		this.city1 = new City("ville-1", disease);

		this.treat = new TreatDoctor("Treat Doctor");

		this.player1 = new Player("Alice", city1);

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
		assertEquals(0, city1.getNbCube(disease));

	}

}
