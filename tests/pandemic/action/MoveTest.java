package pandemic.action;

import static org.junit.Assert.*;

import org.junit.Test;

import pandemic.City;
import pandemic.Disease;
import pandemic.roles.Player;

public class MoveTest {

	@Test
	public void executeTestWhenCitiesAreNeighbors() {
		Disease d = new Disease(1);
		City c1 = new City("city1" , d);
		City c2 = new City("city2" , d);
		City c3 = new City("city3" , d);
		c1.addNeighbors(c2);
		c2.addNeighbors(c3);
		Player p = new Player("camille", c1);
		Move m = new Move("move");
		m.execute(p);
		assertEquals(p.getCity(),c2);
		m.execute(p);
		assertEquals(p.getCity(),c3);
	}
	
	@Test
	public void executeTestWhenCitiesAreNotNeighbors() {
		Disease d = new Disease(1);
		City c1 = new City("city1" , d);
		Player p = new Player("camille", c1);
		Move m = new Move("move");
		m.execute(p);
		assertEquals(p.getCity(),c1);
	}
}
