package pandemic.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pandemic.City;
import pandemic.Disease;
import pandemic.cards.EpidemicCard;
import pandemic.cards.PlayerCard;
import pandemic.roles.Player;

public class BuildTest {

	/* players */
	private Player player1;

	/* action */
	private Build build;

	/* cities */
	private City city1;

	/* cards */
	private PlayerCard card1;

	@Before
	public void init() {
		this.city1 = new City("ville-1", new Disease(0));

		this.build = new Build("Build");

		this.player1 = new Player("Alice", city1);

		this.card1 = new PlayerCard("ville-1", 0);
	}

	/* isPossibleTest */
	@Test
	public void isPossibleTest1(){
		/* player has card player for construction */
		assertFalse(build.isPossible(player1));
		assertFalse(player1.getHand().contains(card1));
		player1.takeCards(card1, null);
		assertTrue(build.isPossible(player1));
		assertTrue(player1.getHand().contains(card1));
	}

	@Test
	public void isPossibleTest2() {
		/* player has no card player for construction */
		assertFalse(build.isPossible(player1));
		assertFalse(player1.getHand().contains(card1));
	}

	@Test
	public void isPossibleTest3(){
		/* player has card but their is already a station */
		city1.getAResearchStation();
		assertTrue(city1.getStation());
		player1.takeCards(card1, null);
		assertTrue(player1.getHand().contains(card1));
		assertFalse(build.isPossible(player1));
	}

	/* executeTest */
	@Test
	public void executeTest1(){
		/* player has card player for construction */
		player1.takeCards(card1, null);
		assertTrue(player1.getHand().contains(card1));
		build.execute(player1);
		assertTrue(city1.getStation());
		assertEquals(player1.getAction(),1);
	}

	@Test
	public void executeTest2(){
		/* player has no card player for construction */
		assertFalse(build.isPossible(player1));
		assertFalse(player1.getHand().contains(card1));
		build.execute(player1);
		assertFalse(city1.getStation());

	}

	@Test
	public void executeTest3() {
		/* player has card but their is already a station */
		city1.getAResearchStation();
		assertTrue(city1.getStation());
		assertFalse(build.isPossible(player1));
		player1.takeCards(card1, null);
		assertTrue(player1.getHand().contains(card1));
		assertFalse(build.isPossible(player1));
		build.execute(player1);
		// true because their is a station before
		assertTrue(city1.getStation());

	}

}
