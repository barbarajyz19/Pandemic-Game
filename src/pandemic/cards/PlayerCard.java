package pandemic.cards;

import pandemic.roles.Player;

/*
 * class for player card
 */
public class PlayerCard extends Card{
	/**
	 * initialize player card
	 * @param cityName
	 * @param diseaseName
	 */
	public PlayerCard(String cityName, int diseaseName) {
		super(cityName,diseaseName);
	}
	/**
	 * says if the card is an epidemic card
	 * @return true if the card is epidemic
	 */
	public boolean isEpidemicCard() {
		return this.getDiseaseName() == -1;
	}
	
	/**
	 * give the description
	 * @param p the player
	 */
	public void description(Player p) {
		System.out.println("La carte est une carte JOUEUR : la ville est '" + p.getCity().getName() +"' ,avec le virus '"+ this.diseaseName +"'");
	}
	
	/**
	 * toString for get the type of the card
	 * @return the card 
	 */
	public String toString() {
		return "PlayerCard";
	}
}