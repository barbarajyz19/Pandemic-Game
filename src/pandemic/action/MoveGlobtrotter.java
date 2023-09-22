package pandemic.action;

import pandemic.City;
import pandemic.roles.Player;

public class MoveGlobtrotter extends Move {

	/** constructor of MoveGlobtrotter */
	public MoveGlobtrotter(String name) {
		super(name);

	}
	/**
	 * redefinition of execute for player expert 
	 * player can move everywhere 
	 * @param p the player
	 * @param city the city
	 */
	public void execute(Player p, City city) {
		if(this.isPossible(p)) {
			p.setCity(city);
			p.addAction();
		}
		this.display(p);
	}
}
