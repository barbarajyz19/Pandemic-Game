package pandemic.roles;

import java.util.List;

import pandemic.City;
import pandemic.cards.PlayerCard;

/*
 * interface for role
 */
public interface Role {

	/* initialization of the methods for each player*/
	public String getName();
	/*hand of the player*/
	public  int getNbCards();
	public int getNbCardsForADisease(int disease);
	public List<PlayerCard> getHand();
	public List<PlayerCard> takeCards(PlayerCard card1, PlayerCard card2);
	/*player location*/
	public City getCity();
	public void setCity(City city);
}

