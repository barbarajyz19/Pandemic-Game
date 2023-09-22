package pandemic.action;

import pandemic.roles.Player;

/* interface of action */
public interface Action {

	public abstract void execute(Player p);
	public boolean isPossible(Player p);
	public String getName();
}
