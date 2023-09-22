package pandemic.action;

import static org.junit.Assert.*;

import org.junit.Test;

import pandemic.City;
import pandemic.Disease;
import pandemic.Game;
import pandemic.roles.*;

public class MoveGlobtrotterTest {

	@Test
	public void executeTestWhenCitiesAreNeighbors(){
		Disease d = new Disease(1);
		City c1 = new City("city1" , d);
		City c2 = new City("city2" , d);
		City c3 = new City("city3" , d);
		Game game = new Game();
		c1.addNeighbors(c2);
		Globetrotter p = new Globetrotter("camille", c1);
		MoveGlobtrotter m = new MoveGlobtrotter("globtrotterMove");
		City city = game.aleaCity();
		m.execute(p,city);
		assertEquals(p.getCity(),city);
	}

	@Test
	public void executeTestWhenCitiesAreNotNeighbors() {
		Disease d = new Disease(1);
		City c1 = new City("city1" , d);
		City c2 = new City("city2" , d);
		Globetrotter p = new Globetrotter("camille", c1);
		MoveGlobtrotter m = new MoveGlobtrotter("move");
		m.execute(p,c2);
		assertEquals(p.getCity(),c2);
	}
	
}
