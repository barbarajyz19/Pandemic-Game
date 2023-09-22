package pandemic.action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pandemic.City;
import pandemic.Disease;
import pandemic.cards.PlayerCard;
import pandemic.roles.Player;

public class ResearchTest {

	/* players */
	private Player player1;

	/* action */
	private Research research;

	/* cities */
	private City city1;

	/* disease */
	private Disease disease;
	/* cards */
	private PlayerCard card1;
	private PlayerCard card2;
	private PlayerCard card3;
	private PlayerCard card4;
	private PlayerCard card5;

	@Before
	public void init(){
		this.disease = new Disease(0);
		this.city1 = new City("ville-1", disease);

		this.research = new Research("Research");

		this.player1 = new Player("Alice", city1);

		this.card1 = new PlayerCard("ville-1", 0);
		this.card2 = new PlayerCard("ville-2", 0);
		this.card3 = new PlayerCard("ville-3", 0);
		this.card4 = new PlayerCard("ville-4", 0);
		this.card5 = new PlayerCard("ville-5", 0);

		player1.takeCards(card1, card2);
		player1.takeCards(card3, card4);
	}

	/* isPossibleTest */
	@Test
	public void isPossibleTest(){
		assertFalse(research.isPossible(player1)); // player can't cure without research station
		city1.getAResearchStation();
		city1.getDisease();
		assertFalse(research.isPossible(player1)); // player can't cure with 4 cards for the same disease
		player1.takeCards(card5, null);
		assertEquals(player1.getNbCards(), 5);
		assertTrue(research.isPossible(player1)); // player can cure with 5 cards for the same disease
	}

	/* executeTest */
	@Test
	public void executeTest(){
		city1.getDisease().setNbCube(4);
		city1.getAResearchStation();
		player1.takeCards(card5, null);
		research.execute(player1);

		assertTrue(disease.getCureFound()); // check that the cure is found
		assertEquals(0, disease.getNbCube()); // check that all cubes have been removed
	}

}
