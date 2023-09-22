package pandemic.cards;

/*
 * class for epidemic card
 */
public class EpidemicCard extends PlayerCard {
	/**
	 *the constructor : creates a card with no particular information 
	 */
	public EpidemicCard() {
		super("epidemic card",-1);
	}
	
	/**
	 * the description
	 */
	public void description() {
		System.out.println("La carte est une carte EPIDEMIE : une phase d'infection est déclenché");
	}
	
	/**
	 * toString for get the type of the card
	 * @return the card 
	 */
	public String toString() {
		return "EpidemicCard";
	}

}
