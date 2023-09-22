package pandemic;

import pandemic.roles.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
	private Game game;
	private Player doc;
	private Player sci;
	private Player glo;
	private Player exp;
	private City c1;
	
	
	@Before
	public void init(){
		this.game = new Game();
		/*recovery of the first city*/
		Set<City> cities = new HashSet<>();
		cities = this.game.getMaps().getAllCities();
		List<City> cities2 = new ArrayList<>(cities);
		this.c1 = cities2.get(0);
		/*creation of the players*/
    	this.exp = new Doctor("Frodo", this.c1);
    	this.glo = new Globetrotter("Bilbo", this.c1);
    	this.doc = new Doctor("Alice", this.c1);
    	this.sci = new Scientist("Bob", this.c1);
	}
	@Test
	public void randomCityTest() {
		Set<City> cities = this.game.getMaps().getAllCities();
		List<City> cities2 = new ArrayList<>(cities);
		for(int i=0; i<1000;i++) {
			City c = this.game.aleaCity();
			assertTrue(cities2.contains(c));
		}
	}
	
	@Test
	public void distributionCardsForTwoPlayersTest()/*throws MaxCardException*/ {
		this.game.addPlayer(this.exp);
		this.game.addPlayer(this.glo);
		this.game.distributionFirstCards();
		assertEquals(this.game.getPlayers().get(0), this.exp);
		assertEquals(this.game.getPlayers().get(1), this.glo);
		assertEquals(this.game.getNbPlayers(),2);
		assertEquals(this.game.getPlayers().get(0).getNbCards(),4);
	}
	
	@Test
	public void distributionCardsForThreePlayersTest()/*throws MaxCardException*/ {
		this.game.addPlayer(this.exp);
		this.game.addPlayer(this.glo);
		this.game.addPlayer(this.doc);
		this.game.distributionFirstCards();
		assertEquals(this.game.getPlayers().get(2), this.doc);
		assertEquals(this.game.getNbPlayers(),3);
		assertEquals(this.game.getPlayers().get(0).getNbCards(),3);
	}
	
	@Test
	public void distributionCardsForFourPlayersTest()/*throws MaxCardException*/ {
		this.game.addPlayer(this.exp);
		this.game.addPlayer(this.glo);
		this.game.addPlayer(this.doc);
		this.game.addPlayer(this.sci);
		this.game.distributionFirstCards();
		assertEquals(this.game.getPlayers().get(3), this.sci);
		assertEquals(this.game.getNbPlayers(),4);
		assertEquals(this.game.getPlayers().get(0).getNbCards(),2);
	}
	
	@Test
	public void initialInfectionTest() {
		this.game.initialInfection();
		Set<City> cities = this.game.getMaps().getAllCities();
		List<City> listCities = new ArrayList<>(cities);
		int citiesOneCube=0, citiesTwoCubes = 0, citiesThreeCubes =0, citiesNoCubes=0;
		for(City c : listCities) {
			if(c.getNbCube(c.getDisease())==1) {
				citiesOneCube++;
			}else if(c.getNbCube(c.getDisease())==2) {
				citiesTwoCubes++;
			}else if(c.getNbCube(c.getDisease())==3) {
				citiesThreeCubes++;
			}else {
				citiesNoCubes++;
			}
		}
		assertEquals(citiesOneCube,3);
		assertEquals(citiesTwoCubes,3);
		assertEquals(citiesThreeCubes,3);
		assertEquals(citiesNoCubes, (listCities.size()-9));
	}
	
	@Test
	public void nextPlayerTest() {
		this.game.addPlayer(this.exp);
		this.game.addPlayer(this.glo);
		this.game.addPlayer(this.doc);
		this.game.addPlayer(this.sci);
		/*verification if nextPlayer set the current player by the first player of the list */
		this.game.nextPlayer();
		assertEquals(this.game.getCurrentPlayer(),this.exp);
		assertEquals(this.game.getCurrentPlayer(),this.game.getPlayers().get(0));
		/*pass to the second player of the list*/
		this.game.nextPlayer();
		assertEquals(this.game.getCurrentPlayer(),this.glo);
		assertEquals(this.game.getCurrentPlayer(),this.game.getPlayers().get(1));
		/*pass to the third player of the list*/
		this.game.nextPlayer();
		assertEquals(this.game.getCurrentPlayer(),this.doc);
		assertEquals(this.game.getCurrentPlayer(),this.game.getPlayers().get(2));
		/*pass to the fourth player of the list*/
		this.game.nextPlayer();
		assertEquals(this.game.getCurrentPlayer(),this.sci);
		assertEquals(this.game.getCurrentPlayer(),this.game.getPlayers().get(3));
		/*the current player return to the first player*/
		this.game.nextPlayer();
		assertEquals(this.game.getCurrentPlayer(),this.exp);
		assertEquals(this.game.getCurrentPlayer(),this.game.getPlayers().get(0));
	}

}
