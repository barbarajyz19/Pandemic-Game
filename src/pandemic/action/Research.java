package pandemic.action;

import pandemic.roles.Player;

public class Research implements Action {

	protected String name;

	/** constructor of Research */
	public Research(String name) {
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
	 * player discover a cure with 5 cards
	 * @param p the player who want to discover a cure
	 */
	public void execute(Player p) {
		if (this.isPossible(p)) {
			p.removeSeveralCardsWithDiseaseName(5);
			p.getCity().getDisease().setNbCube(0);
			p.getCity().getDisease().setCureFound();
			p.addAction();
		}
		this.display(p);
	}

	/**
	 * true if their is already a station, the number of action for the player is good, if the cure is not already found and if the player has 5 cards for the same disease so the player can cure otherwise false
	 * @param p the player who want to discover a cure
	 * @return true if the player can cure
	 */
	public boolean isPossible(Player p) {
		if (p.getCity().getStation() && p.getAction() < 4) {
			if (!(p.getCity().getDisease().getCureFound())) {
				if (5 <= p.getNbCardsForADisease(p.getCity().getDisease().getName())) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * display the action of the player
	 * @param p the player
	 */
	public void display(Player p) {
		System.out.println(p.getName() + " a découvert un remède pour la " + p.getCity());
	}
}
