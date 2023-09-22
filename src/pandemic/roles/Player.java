package pandemic.roles;


import java.util.*;

import pandemic.*;
import pandemic.cards.*;
import pandemic.action.*;

public class Player implements Role {
	/*the player name*/
	private String name;
	/*hand of the player*/
	private List<PlayerCard> hand;
	/*city ​​on which the player is*/
	protected City city;
	/*the maximum number of cards the player can have in their hand*/
	private static final int MAX_CARDS = 7;
	/*actions performed in a turn */
	protected int nbActions = 0;
	/*number of actions to perform*/
	protected final int nbactionstorealise = 4;
	/*treat action*/
	protected Treat treat;
	/*research action*/
	protected Research research;
	/*pass action*/
	protected Pass pass;
	/*build action*/
	protected Build build;
	/*move action*/
	protected Move move;
	/*list of action possible*/
	protected List<Action> actionPossible;


	public Player (String name, City city) {
		this.name = name;
		this.hand = new ArrayList<PlayerCard>();
		this.city = city;
		this.move = new Move("move");
		this.treat = new Treat("treat");
		this.research = new Research("research");
		this.pass = new Pass("pass");
		this.build = new Build("build"); 
		this.actionPossible = new ArrayList<Action>();
	}
	

	/*the player*/
	/**
	 * give the name of the player
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}


	/*hand of the player*/
	/**
	 * give the number of cards in the hand of the player
	 * @return the number of cards
	 */
	public  int getNbCards() {
		return this.hand.size();
	}


	/**
	 * return the number of cards with a specific disease
	 * @param disease the disease for which we want to know the number of cards in the hand of the player
	 * @return nbCards the number of cards with a specific disease
	 */
	public int getNbCardsForADisease(int disease) {
		int nbCards =0;
		for (int i = 0; i<getNbCards();i++) {
			if(disease==this.hand.get(i).getDiseaseName()) {
				nbCards++;
			}
		}
		return nbCards;
	}
	/**
	 * give the list of cards in the hand of the player
	 * @return the list of cards
	 */
	public List<PlayerCard> getHand(){
		return this.hand;
	}
	
	/**
	 * true if the player still have an action to do
	 * @return boolean if the player can play
	 */
	public boolean canplay() {
		return this.nbActions < this.nbactionstorealise;
	}
	
	/**
	 * add two cards to the hand of the player
	 * @param card1 the first card added
	 * @param card2 the second card added
	 * @return cardsToDiscard the cards thats go to the discard pile
	 */
	public List<PlayerCard> takeCards(PlayerCard card1, PlayerCard card2){
		List<PlayerCard> cardsToDiscard = new ArrayList<>();
		if(!(card1==null)) {
			this.hand.add(card1);
		}
		if(!(card2==null)) {
			this.hand.add(card2);
		}
		if(getNbCards()==MAX_CARDS+1) {
			PlayerCard card3 = this.aleaCard();
			this.hand.remove(this.hand.indexOf(card3));
			cardsToDiscard.add(card3);
		}else if(getNbCards()==MAX_CARDS+2) {
			PlayerCard card3 = this.aleaCard();
			this.hand.remove(this.hand.indexOf(card3));
			PlayerCard card4 = this.aleaCard();
			this.hand.remove(this.hand.indexOf(card4));
			cardsToDiscard.add(card3);
			cardsToDiscard.add(card4);
		}
		return cardsToDiscard;
	}
	
	/**
	 * choose a card in the hand of the player
	 * @return a card in the hand of the player
	 */
	public PlayerCard aleaCard() {
		if (this.hand == null || this.hand.isEmpty()) {
			System.out.println("Aucune cartes dans la main du joueur");
		}
		Random random = new Random();
		int randomIndex = random.nextInt(this.hand.size());
		return this.hand.get(randomIndex);
	}
	/**
	 * remove a card from the hand of the player and return the card to allow to add it in the discard pile
	 * when the action need one card
	 * @return card/cardToRemove the card which go in the discard pile
	 */
	public List<PlayerCard> removeACardWithCityName() {
		List<PlayerCard> cardToRemove = new ArrayList<>();
		for (int i = 0; i < this.getNbCards(); i++) {
			if (this.hand.get(i).getCityName().equals(this.city.getName())) {
				cardToRemove.add(this.hand.get(i));
				this.hand.remove(i);
				return cardToRemove;
			}
		}
		return null;
	}
	
	/**
	 * remove several cards from the hand of the player and return the list of this cards to allow to add them in the discard pile
	 * @param nbCards the number of cards we need to remove
	 * @return cardsToRemove the list of cards which go in the discard pile
	 */
	public List<PlayerCard> removeSeveralCardsWithDiseaseName(int nbCards){
		List<PlayerCard> cardsToRemove = new ArrayList<>();
		while(nbCards>0) {
		    int i=0;
		    while (i<this.getNbCards()){
		        if(this.hand.get(i).getDiseaseName()==this.city.getDisease().getName()){
		            cardsToRemove.add(this.hand.get(i));
		            this.hand.remove(i);
		            i=this.getNbCards();
		        }
		    }
		    nbCards--;
		}
		return cardsToRemove;
	}
  
	/*player location*/
	/**
     * give alea city to move
     * @return city
     */
	public City cityMove() {
		List<City> neighbors = new ArrayList<>(this.getCity().getNeighbors());
	    Random randomizer = new Random();
	    City random = neighbors.get(randomizer.nextInt(neighbors.size()));
	    return random;
	}
    
	/**
	 * give the city ​​on which the player is
	 * @return the city
	 */
	public City getCity() {
		return this.city;
	}
	
	/*
	 * 
	 */
	public void setCity(City city) {
		this.city = city;
	}
	
	/*action player*/
	/**
	 * create the list of possible actions for the player
	 */
	public void actionPossibleForPlayer() {
		actionPossible.clear();
		if (this.move.isPossible(this)) {
			actionPossible.add(this.move);
		}
		if (this.treat.isPossible(this)) {
			actionPossible.add(this.treat);
		}
		if (this.pass.isPossible(this)) {
			actionPossible.add(this.pass);
		}
		if (this.research.isPossible(this)) {
			actionPossible.add(this.research);
		}
		if (this.build.isPossible(this)) {
			actionPossible.add(this.build);
		}
	}
	/**
	 * give the list of possible action
	 * @return list
	 */
	public List<Action> getActionPossible(){
		return this.actionPossible;
	}
	/**
	 * give the number of actions the player has made
	 * @return the number of actions
	 */
	public int getAction() {
		return this.nbActions;
		
	}
	
	/**
	 * add an action to the player
	 */
	public void addAction() {
		this.nbActions += 1;
	}
	
	/**
	 * reset the actions
	 */
	public void resetAction() {
		this.nbActions = 0;
	}
	
	/**
	 * give the role
	 * @return the role
	 */
    public String getRole() {
    	return "Player";
    }
    /**
     * display name, role and city of the player
     */
    public void displayPlayer() {
    	System.out.println(this.getName() + " est un " + this.getRole() + " et se situe dans " + this.getCity().getName());
    }
	

}


