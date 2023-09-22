package pandemic.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pandemic.City;
import pandemic.Disease;
import pandemic.roles.Player;

public class ExpertBuildTest {

	/* players */
	private Player player1;

	/* action */
	private ExpertBuild expertBuild;

	/* cities */
	private City city1;

	@Before
	public void init() {
		this.city1 = new City("ville-1", new Disease(0));

		this.expertBuild = new ExpertBuild("Expert Build");

		this.player1 = new Player("Alice", city1);

	}

	/* isPossibleTest */
	@Test
	public void isPossibleTest() {
		assertTrue(expertBuild.isPossible(player1));
		city1.getAResearchStation();
		assertFalse(expertBuild.isPossible(player1));

	}

	/* executeTest */
	@Test
	public void executeTest() {
		assertFalse(city1.getStation());
		expertBuild.execute(player1);
		assertTrue(city1.getStation());
	}

}