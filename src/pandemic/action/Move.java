package pandemic.action;


import pandemic.City;
import pandemic.roles.Player;

public class Move implements Action {

	protected String name;

	/** constructor of Move */
	public Move(String name) {
		this.name = name;
	}

	/**
	 * get the name of the action
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * player move to another city if is neighbors
	 * @param p the player who want to move
	 */
	public void execute(Player p) {
		if (this.isPossible(p)) {
			if (p.getCity().getNeighbors().size()>0) {
				City c =p.cityMove();
				p.setCity(c);
			}
		}
		p.addAction();
		this.display(p);
	}

	/**
	 * true if the number of the player action is good so the player can move in the city otherwise false
	 * @param p the player who want to move
	 * @return true if the player can move 
	 */
	public boolean isPossible(Player p) {
		if (p.getAction() < 4) {
			return true;
		}
		return false;
	}
	
	/**
	 * display the action of the player
	 * @param p the player
	 */
	public void display(Player p) {
		System.out.println(p.getName() + " s'est déplacé dans la " + p.getCity().getName());
	}
}
