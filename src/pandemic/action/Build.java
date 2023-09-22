package pandemic.action;

import pandemic.roles.*;

public class Build implements Action {

	protected String name;

	/** constructor of Build */
	public Build(String name) {
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
	 * build a research station in the city
	 * @param p the player
	 */
	public void execute(Player p) {
		if (this.isPossible(p)) {
			p.removeACardWithCityName();
			p.getCity().getAResearchStation();
			p.addAction();
		}		
		this.display(p);
	}

	/**
	 * true if the player has a card of the city, if there is no station on the city and the number of action is good so he can build a station otherwise false
	 * @param p the player
	 * @return true if build a station is possible
	 */
	public boolean isPossible(Player p) {
		if (!p.getCity().getStation() && p.getAction() < 4) {
			for (int i = 0; i < p.getNbCards(); i++) {
				if (p.getHand().get(i).getCityName().equals(p.getCity().getName())) {
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
		System.out.println(p.getName() + " a construit une station dans la " + p.getCity());
	}

}
