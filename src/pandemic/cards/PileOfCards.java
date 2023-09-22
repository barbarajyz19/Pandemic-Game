package pandemic.cards;

import java.util.*;
/*
 * class for pile of cards
 */
public class PileOfCards<T extends Card>{
	/*list of cards*/
	protected List<T> listCards;
	/*the number of card(s)*/
	protected int nbCards;
	
	/**
	 * the constructor
	 */
	public PileOfCards() {
		this.listCards = new ArrayList<T>();
	}
	
	/**
	 * give the list of the cards
	 * @return the list of the cards
	 */
	public List<T> getListCards(){
		return this.listCards;
	}
	
    /**
	* unstacks PileOfCards
	* @return the card at the top of our pile
	*/
	public T unstack() {
		T depcard = null;
		if (! this.isEmpty()) {
		  depcard = this.listCards.get(this.listCards.size()-1);
		  this.listCards.remove(this.listCards.size()-1);
		  
		}
		return depcard;
	}
	/**
	* @return true if the pile is empty and False if not
	*/
	public boolean isEmpty() {
		return this.listCards.size()==0;
	}
	
	/**
	* stack the PileOfCards
	* @param stackedList the pile of cards to stack on the pile
	*/
	public void stack(PileOfCards<T> stackedList){
		for(T c : stackedList.listCards) {
		  this.listCards.add(this.listCards.size(), c);
		}
	}
	/**
	 * stack one card
	 * @param c the card
	 */
	public void stackOneCard(T c){
		  this.listCards.add(this.listCards.size(), c);
	}
	
	/**
	* mix a pile of cards .
	*/
	public void mixCards() {
		Collections.shuffle(this.listCards);
	}
}