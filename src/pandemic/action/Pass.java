package pandemic.action;

import pandemic.roles.Player;

public class Pass implements Action {

	protected String name;

	/** constructor of Pass */
	public Pass(String name) {
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
	 * player pass its turn
	 * @param p the player who want to pass
	 */
	public void execute(Player p) {
		if(this.isPossible(p)) {
			p.addAction();
		}
		this.display(p);
	}

	/**
	 * check if the number of action for the player is good so he can pass its turn else false
	 * @param p the player who want to pass
	 * @return true if the player can pass, false instead
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
		System.out.println(p.getName() + " a passé son tour");
	}

}
