package pandemic.action;

import static org.junit.Assert.*;

import org.junit.Test;

import pandemic.City;
import pandemic.Disease;
import pandemic.roles.Globetrotter;
import pandemic.roles.Player;

public class PassTest {

	private City city1;
	private Player player1;
	private Pass pass;
	

	@Test
	public void test() {
		this.city1 = new City("ville-1", new Disease(0));
		this.player1 = new Player("Alice", city1);
		this.pass = new Pass("pass");
		assertEquals(player1.getAction(), 0);
		pass.execute(player1);
		pass.execute(player1);
		pass.execute(player1);
		assertTrue(pass.isPossible(player1));
		pass.execute(player1);
		assertEquals(player1.getAction(), 4);
		assertFalse(pass.isPossible(player1));
		pass.execute(player1);
		assertEquals(player1.getAction(), 4);
		assertFalse(pass.isPossible(player1));
	}

}
