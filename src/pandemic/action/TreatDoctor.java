package pandemic.action;

import pandemic.Disease;
import pandemic.roles.Player;

public class TreatDoctor extends Treat {

	/** constructor of TreatDoctor */
	public TreatDoctor(String name) {
		super(name);
	}

	/**
	 * player treat the disease even if the cure for the disease is not found 
	 * @param p the player who want to treat the disease
	 */
	public void execute(Player p) {
		Disease disease = p.getCity().getDisease();
		if (this.isPossible(p)) {
			p.getCity().resetCube(disease);
		} 
		this.display(p);
		this.treatDisease += 1;
	}
	/**
	 * display the action of the player
	 * @param p the player
	 */
	public void display(Player p) {
		System.out.println(p.getName() + " a traité la " + p.getCity());
	}
}
