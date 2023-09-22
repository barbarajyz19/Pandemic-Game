package pandemic.action;

import pandemic.*;
import pandemic.roles.Player;

public class Treat implements Action {

	protected String name;
	protected int treatDisease;

	/** constructor of Treat */
	public Treat(String name) {
		this.name = name;
		this.treatDisease = 0;
	}

	/**
	 * get the name of the action
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * player treat the disease if the cure is found the player remove all cube else the player remove just one cube
	 * @param p the player who want to treat the disease
	 */
	public void execute(Player p) {
		Disease disease = p.getCity().getDisease();
		if (this.isPossible(p)) {
			if (disease.getCureFound()) {
				p.getCity().resetCube(disease);
				this.displayCube(p);
			} else {
				p.getCity().removeACube(disease);
				this.displayCure(p);
			}
			p.addAction();
		}
	}

	/**
	 * true if the city need to be treat, if the city has cube and if the number of action for the player is good else false
	 * @return true if the city need to be treat
	 */
	public boolean isPossible(Player p) {
		Disease disease = p.getCity().getDisease();
		if (p.getCity().getNbCube(disease) > 0 && p.getAction() < 4) {
			return true;
		}
		return false;
	}
	/**
	 * display when the player remove a cube
	 * @param p the player
	 */
	public void displayCube(Player p) {
		System.out.println(p.getName() + " a retiré un cube de la " + p.getCity());
	}
	/**
	 * display when the player treat
	 * @param p the player
	 */
	public void displayCure(Player p) {
		System.out.println(p.getName() + " a traité avec le remède la " + p.getCity());
	}
}
