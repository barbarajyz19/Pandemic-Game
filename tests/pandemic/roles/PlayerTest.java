package pandemic.roles;

import java.util.*;

import pandemic.City;
import pandemic.Disease;
import pandemic.action.*;
import pandemic.cards.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	/*players*/
	private Player player1;

	/*card of the player 1*/
	private List<PlayerCard> liste1;

	/*cities*/
	private City city1;

	/*cards*/
	private PlayerCard card1; 
	private PlayerCard card2; 
	private PlayerCard card3;
	private PlayerCard card4; 
	
	/*actions*/
	Move move = new Move("move");
	Treat treat = new Treat("treat");
	Research research = new Research("research");	
	Pass pass = new Pass("pass");
	Build build = new Build("build");


	@Before
	public void init(){
		this.city1 = new City("ville-1", new Disease(0));

		this.player1 = new Player("Alice",city1);
		this.liste1 = new ArrayList<PlayerCard>();

		this.card1 = new PlayerCard("ville-1", 0);
		this.card2 = new PlayerCard("ville-2", 0);
		this.card3 = new PlayerCard("ville-3", 0);
		this.card4 = new PlayerCard("ville-4", 0);
	}

	@Test
	public void PlayerInitializationTest() {
		assertEquals(player1.getName(),"Alice");
		assertEquals(player1.getNbCards(), 0);	
		assertEquals(player1.getHand(),liste1);
		assertEquals(player1.getCity(),city1);
	}


	/* takeCards */
	@Test
	public void takeCardsTest1(){
		/*Player has 7 cards, should discard 1 card*/
		player1.takeCards(card1, card2);
		player1.takeCards(card1, card2);
		player1.takeCards(card1, card2);
		assertEquals(6, player1.getNbCards());
		player1.takeCards(card1, card2);
	    assertEquals(7, player1.getNbCards());
	}
	
	@Test
	public void takeCardsMax2Test(){
		/*Player has 8 cards, should discard 2 cards*/
		this.takeCardsTest1();
		player1.takeCards(card1, null);
		assertEquals(7, player1.getNbCards());
	    player1.takeCards(card1, card2);
	    assertEquals(7, player1.getNbCards());

	}
	
	/*removeCard*/
	@Test
	public void removeACardWithCityNameTest(){
		player1.takeCards(card1, card2);
		assertEquals(card1.getCityName(), player1.getCity().getName());
		assertEquals(2, player1.getNbCards());
		player1.removeACardWithCityName();
		assertEquals(1, player1.getNbCards());
	}
	
	@Test
	public void removeSeveralCardsWithDiseaseNameTest(){
		player1.takeCards(card1, card2);
		player1.takeCards(card3, card4);
		assertEquals(card1.getDiseaseName(), player1.getCity().getDisease().getName());
		assertEquals(4, player1.getNbCards());
		player1.removeSeveralCardsWithDiseaseName(1);
		assertEquals(3, player1.getNbCards());
	}
	
	@Test
	public void removeSeveralCardsWithDiseaseNameTest1(){
		player1.takeCards(card1, card2);
		player1.takeCards(card3, card4);
		assertEquals(card1.getDiseaseName(), player1.getCity().getDisease().getName());
		assertEquals(4, player1.getNbCards());
		player1.removeSeveralCardsWithDiseaseName(2);
		assertEquals(2, player1.getNbCards());
	}

	@Test
	public void removeSeveralCardsWithDiseaseNameTest2(){
		player1.takeCards(card1, card2);
		player1.takeCards(card3, card4);
		assertEquals(card1.getDiseaseName(), player1.getCity().getDisease().getName());
		assertEquals(4, player1.getNbCards());
		player1.removeSeveralCardsWithDiseaseName(0);
		assertEquals(4, player1.getNbCards());
	}
	
	@Test
	public void addActionTest(){
		assertEquals(player1.getAction(), 0);
		player1.addAction();
		player1.addAction();
		assertEquals(player1.getAction(), 2);
		
	}
	
	@Test
	public void resetActionTest(){
		assertEquals(player1.getAction(), 0);
		player1.addAction();
		player1.addAction();
		assertEquals(player1.getAction(), 2);
		player1.resetAction();
		assertEquals(player1.getAction(), 0);
	}

}

