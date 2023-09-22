package pandemic.action;

import pandemic.roles.Player;

public class ScientistResearch extends Research {

	/** constructor of ScientistResearch */
	public ScientistResearch(String name) {
		super(name);
	}
	
	/**
	 * player discover a cure
	 * @param p the player who want to discover a cure
	 */
	public void execute(Player p) {
		if (this.isPossible(p)) {
			p.removeSeveralCardsWithDiseaseName(4);
			p.getCity().getDisease().setNbCube(0);
			p.getCity().getDisease().setCureFound();
			p.addAction();
		}
		this.display(p);
	}

	/**
	 * true if their is already a station, the number of action for the player is good, if the cure is not already found and if the player has 4 cards for the same disease so the player can cure otherwise false
	 * @param p the player who want to cure
	 * @return true if the player can cure
	 */
	public boolean isPossible(Player p) {
		if (p.getCity().getStation() && p.getAction() < 4) {
			if (!(p.getCity().getDisease().getCureFound())) {
				if (4 <= p.getNbCardsForADisease(p.getCity().getDisease().getName())) {
					return true;
				}
			}
		}
		return false;
	}
}
