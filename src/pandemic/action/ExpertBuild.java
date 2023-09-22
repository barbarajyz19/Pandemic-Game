package pandemic.action;

import pandemic.roles.Player;

public class ExpertBuild extends Build {

	/** constructor of ExpertBuild */
	public ExpertBuild(String name) {
		super(name);
	}

	/**
	 * redefinition of isPossible for player expert 
	 * true if their is no station already and the number of action is good so the player can build a station otherwise false
	 * @param p the player
	 * @return true if build a station is possible
	 */
	public boolean isPossible(Player p) {
		return p.getCity().getStation() == false && p.getAction() < 4;
	}
}
