package pandemic;
import pandemic.action.*;
import java.util.*;
import pandemic.roles.*;
import pandemic.cards.*;

public class Game {
	/**the map of the game*/
	protected Maps map;
	/**list of players*/
	protected List<Player> players;
	/**the current player*/
	private Player currentPlayer;
	/**the number of player*/
	protected static int nbPlayers;
	/** The list of the Role that can be play in the game*/
	private ArrayList<String> listOfRole;
	/** The list of the city with infection*/
	private List<City> listCityInfection;
	/** Overall infection rate */
	private int infectionRate;
	/**the number max of cards in the hand of the player*/
	static final int NB_MAX_CARDS = 7;
	/**the number max of players*/
	static final int NB_MAX_PLAYERS = 4;
	/**the number of households*/
	private int nb_outbreak_site;
	/**number max of outbreak site*/
	private static final int MAX_OUTBREAK_SITE = 8;
	/**true if the game is finished else false*/
	private boolean gameFinished;
	

	public Game(){
		this.map = new Maps();
		try {
			map.initializeMap("repertoire.json");
		}catch (Exception e) {
			System.out.print("file not found");
		}
		this.players = new ArrayList<Player>();
		this.listCityInfection = new ArrayList<City>();
		this.infectionRate = 2;
		this.listOfRole = new ArrayList<String>();
		this.currentPlayer = new Player("",new City("",new Disease(0)));/*just for the initialization of the current player*/
		this.nb_outbreak_site = 0;
		this.gameFinished = false;
	}

	/*getters*/
	/**
	 * give the map
	 * @return the map
	 */
	public Maps getMaps() {
		return this.map;
	}

	/**
	 * give the infection rate
	 * @return the infection rate
	 */
	public int getInfectionRate() {
		return this.infectionRate;
	}
	/**
	 * ask to have the number of players
	 */
	public void askPlayer() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Combien de joueurs participent ? ");
        nbPlayers = scanner.nextInt();
		if (nbPlayers>=2 && nbPlayers<=4) {
	        System.out.println("La partie a " + nbPlayers + " joueurs");
	        scanner.close();
		} else {
			this.askPlayer();
		}
    }
	/**
	 * the function which allow to play
	 */

	public void play(){
		this.initPlayers();
		this.currentPlayer = this.players.get(0);/*the current player is by default the first in the list*/
		this.displayAllPlayers();
		System.out.println("initialisation de l'infection : ");
		this.initialInfection();
		while(!this.gameFinished) {
			System.out.println("_____________________________________________________________________________________");
			this.getCurrentPlayer().displayPlayer();
			while(this.getCurrentPlayer().canplay() && !this.gameFinished) {
				System.out.println("________________________");
				this.displayActions(this.getCurrentPlayer());
				Action action = this.aleaAction(this.getCurrentPlayer());
				if(action instanceof MoveGlobtrotter) { /*if the player is a globetrotter, we need an additional parameter so the player can move on every city*/
					MoveGlobtrotter move = (MoveGlobtrotter) action;
					move.execute(this.getCurrentPlayer(), this.aleaCity());
				}else {
					action.execute(this.getCurrentPlayer());
					if (this.map.remedyFound.size() >= 4) {/*if the action is to research a cure*/
						this.gameFinished = true;
					}
				}
			}
			this.takeCards();
			if(!this.gameFinished) {/*don't do the functions if the game is finished*/
				this.currentPlayer.resetAction();
				this.takeDiseaseCards(1);
				for (City city : this.map.getAllCities()) {
			        if(city.getRoundOutbreakSite()) {
			        	this.nb_outbreak_site++;
			        	city.resetRoundOutbreakSite();
			        }
			    }
				this.nextPlayer();
			}
		}
		this.displayGameFinished();
	}
	
	/**
	 * add a cube of disease in the city take in the pile of disease cards
	 * we do it the number of cards we need 1 in play() or the number equals to the infection rate in takeCards() when a card is an epidemic card
	 * @param nbCards the number of disease cards we need to take
	 */
	public void takeDiseaseCards(int nbCards) {
	    Map<String, City> cityMap = new HashMap<>();
	    for (City city : this.map.getAllCities()) {
	        cityMap.put(city.getName(), city);
	    }
	    for (int i=0; i<nbCards; i++) {
	        DiseaseCard dCard = this.map.diseaseCardsPile.unstack();
	        if (dCard == null) {
	            this.gameFinished = true;
	        } else {
	            City city = cityMap.get(dCard.getCityName());
	            Disease disease = city.getDisease();
	            if (city.getDisease().getNbCube() == 0) {
	                this.gameFinished = true;
	            } else {
	                city.addACube(disease);
	                if(city.getOutbreakSiteWithDisease(disease) && !city.getRoundOutbreakSite() && disease.getNbCube()>=city.getNeighbors().size()) {
	                	city.nearbyTowns(disease);
	    				for(City neighbor : city.getNeighbors()) {
	    					if(neighbor.getOutbreakSiteWithDisease(disease) && !neighbor.getRoundOutbreakSite() && disease.getNbCube()>=neighbor.getNeighbors().size()) {
	    						neighbor.nearbyTowns(disease);
		    				}else if(neighbor.getOutbreakSiteWithDisease(disease) && !neighbor.getRoundOutbreakSite() && disease.getNbCube()<neighbor.getNeighbors().size()) {
		    					this.gameFinished=true;
		    				}
	    				}
	    			}else if(city.getOutbreakSiteWithDisease(disease) && !city.getRoundOutbreakSite() && disease.getNbCube()<city.getNeighbors().size()) {
	    				this.gameFinished=true;
	    			}
	            }
	            this.map.discardDiseaseCardsPile.stackOneCard(dCard);
	        }
	    }
	}
	
	/**
	 * true is the game is lost
	 * @return true if the game is lost
	 */
	public boolean gameLost() {
		if( this.nb_outbreak_site >= MAX_OUTBREAK_SITE){
			gameFinished = true;
			System.out.println("Le nombre de foyer d'infection à atteint 8");
			return true;
		} else if (!this.cubeForDiseases()) {
			gameFinished = true;
			System.out.println("Il n'y a plus de cube disponible pour la maladie");
			return true;
		} else if (this.map.getPileOfDiseaseCards().getListCards().size()==0) {
			System.out.println("Il n'y a plus de cartes maladies");
			return true;
		}else if(this.map.getPileOfPlayerCards().getListCards().size()==0) {
			System.out.println("Il n'y a plus de cartes joueurs");
			return true;
		}else if (this.cubeForDiseases() && !this.gameWon()) {
			System.out.println("Il n'y a plus assez de cube disponible pour la phase d'infection dû au fait que l'une des villes soit devenue un foyer");
			return true;
		}
		return false;
	}
	/**
	 * see if the diseases still have cubes
	 * @return false if a disease has no more cubes available
	 */
	public boolean cubeForDiseases() {
		for(City c : this.map.getAllCities()) {
			for (Disease d: c.getDiseases()) {
				if (d.getNbCube() == 0) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * true if the game is finished else false
	 * @return true if the game is finished
	 */
	public boolean getGameFinished(){
		return this.gameFinished ;
	}

	/**
	 * true if the game is won  else false
	 * @return true if the game is won
	 */
	public boolean gameWon() {
		if (this.map.remedyFound.size() >= 4) {
			gameFinished = true;
			return true;
		}
		return false;
	}

	/*Diseases*/
	/**
	 * initial infection :
	 * add 3 cubes of disease for the first three cities
	 * and add 2 cubes of disease for the the next three cities
	 * and add 1 cube of disease for the last three cities
	 */
	public void initialInfection() {
		int nbCubes = 3;
		Set<City> set = this.map.getAllCities();
		List<City> list = new ArrayList<>(set);
		while(nbCubes!=0) {
			for(int i=0; i<3; i++) {
				DiseaseCard card = this.map.diseaseCardsPile.unstack();
				this.map.discardDiseaseCardsPile.stackOneCard(card);
				int index = 0;
				while(index<list.size()) {
					if(list.get(index).getName().equals(card.getCityName())) {
						list.get(index).addCubesInitialization(nbCubes);
						this.listCityInfection.add(list.get(index));
						System.out.println(list.get(index).getName() + " possede " + list.get(index).getNbCube(list.get(index).getDisease()) + " cubes de la maladie " + list.get(index).getDisease().getName());
						index=list.size()+1;
					}
					index++;
				}
			}
			nbCubes--;	
		}
	}


	/*Cards*/
	/**
	 * distribution of the first cards of the game in the hand of the players
	 * 2 cards by player if there are 4 players
	 * 3 cards if there are 3 players
	 * 4 cards if there are 2 players
	 */
	public void distributionFirstCards(){

		if (this.players.size() == 4) {
			for(Player p : this.players) {p.takeCards(this.map.playerCardsPile.unstack(),this.map.playerCardsPile.unstack());}
		}
		else if(this.players.size() == 2) {
			for(Player p : this.players) {p.takeCards(this.map.playerCardsPile.unstack(),this.map.playerCardsPile.unstack());
			p.takeCards(this.map.playerCardsPile.unstack(),this.map.playerCardsPile.unstack());     
			}
		}
		else if(this.players.size() == 3) {
			for(Player p : this.players) {p.takeCards(this.map.playerCardsPile.unstack(),this.map.playerCardsPile.unstack());
			p.takeCards(this.map.playerCardsPile.unstack(),null);
			}
		}
	}

	/**
	 * allows the player to take cards 
	 */
	public boolean takeCards(){
		List<PlayerCard> cardsToDiscard = new ArrayList<>();
		PlayerCard card1 = this.map.playerCardsPile.unstack();
		PlayerCard card2 = this.map.playerCardsPile.unstack();
		if(card1==null || card2==null) {
			this.gameFinished=true;
			return false;
		}else {
			if(card1 instanceof EpidemicCard) {
				System.out.println("________");
				this.infectionRate++;
				System.out.println("Une carte épidémique a été piochée, le taux d'infection est à "+this.infectionRate);
				cardsToDiscard = this.currentPlayer.takeCards(null, card2);
				this.map.getDiscardPileOfPlayerCards().stackOneCard(card1);
				this.takeDiseaseCards(1);/*we take only one disease card whatever the rate of infection*/
				/*returns the discarded disease cards to the pile of disease cards and empty the discard pile*/
				this.map.getDiscardPileOfDiseaseCards().mixCards();
				this.map.getPileOfDiseaseCards().stack(this.map.getDiscardPileOfDiseaseCards());
				this.map.discardDiseaseCardsPile = new PileOfCards<DiseaseCard>();
				System.out.println("Phase épidémique terminée");
				System.out.println("________");
			}else if(card2 instanceof EpidemicCard) {
				System.out.println("________");
				this.infectionRate++;
				System.out.println("Une carte épidémique a été piochée, le taux d'infection est à "+this.infectionRate);
				cardsToDiscard = this.currentPlayer.takeCards(card1, null);
				this.map.getDiscardPileOfPlayerCards().stackOneCard(card2);
				this.takeDiseaseCards(1);/*we take only one disease card whatever the rate of infection*/
				/*returns the discarded disease cards to the pile of disease cards and empty the discard pile*/
				this.map.getDiscardPileOfDiseaseCards().mixCards();
				this.map.getPileOfDiseaseCards().stack(this.map.getDiscardPileOfDiseaseCards());
				this.map.discardDiseaseCardsPile = new PileOfCards<DiseaseCard>();
				System.out.println("Phase épidémique terminée");
				System.out.println("________");
			}else {
				cardsToDiscard = this.currentPlayer.takeCards(card1, card2);
			}
			if(!(cardsToDiscard.isEmpty())) {
				for(int i=0; i<cardsToDiscard.size()-1;i++) {
					this.getMaps().discardPlayerCardsPile.stackOneCard(cardsToDiscard.get(i));
				}
			}
		}
		return true;
	}


	/**
	 * display the hand of p the player
	 * @param p the player we want to display the hand 
	 */
	public void displayHand(Player p) {
		System.out.println(p.getName() + " has " + p.getHand());
	}



	/*players*/
	/**
	 * give the number of players
	 * @return the number of players
	 */
	public int getNbPlayers() {
		return this.players.size();
	}

	/**
	 * give the list of players
	 * @return the list of players
	 */
	public List<Player> getPlayers(){
		return this.players;
	}

	/**
	 * give the list of city with infection 
	 * @return the list of city
	 */
	public List<City> getCityWithInfection(){
		return this.listCityInfection;
	}
	/**
	 * give the current player
	 * @return the current player
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}


	/**
	 * add player on the game
	 * @param p the player
	 */
	public void addPlayer(Player p) {
		if(getNbPlayers()<NB_MAX_PLAYERS) {
			this.players.add(p);
		}
	}

	/**
	 * initialize the players
	 */
	public void initPlayers(){
		City city = this.aleaCity();
		city.getAResearchStation();
		if (nbPlayers >= 2) {
			Player camille = new Globetrotter("Camille", city);
			Player barbara = new Doctor("Barbara", city);
			this.players.add(camille);
			this.players.add(barbara);
		} if (nbPlayers >= 3) {
			Player nolwenn = new Scientist("Nolwenn", city);
			this.players.add(nolwenn);
		} if (nbPlayers == 4) {
			Player anis = new Expert("Anis", city);
			this.players.add(anis);
		}
		distributionFirstCards();
	}


	/**
	 * displayer for actions possibles
	 * @param p the player
	 */
	public void displayActions(Player p) {
		System.out.println("Action(s) disponible(s):");
		p.actionPossibleForPlayer();
		for(Action action : p.getActionPossible()) {
			System.out.println(action.getName().toUpperCase() + " ");
		}
	}

	/**
	 * displayer for action
	 * @param action the action
	 */
	public void displayAction(Action action) {
		System.out.println("Action Effectuée : " + action.getName().toUpperCase() + " ");
	}

	/**
	 * display all the players
	 */
	public void displayAllPlayers() {
		System.out.println("_____________________________________________________________________________________");
		for (Player p : this.players) {
			p.displayPlayer();
		}
		System.out.println("_____________________________________________________________________________________");
	}
	/**
	 * display when the game was finished
	 */
	public void displayGameFinished() {
		if(this.gameWon()) {
			System.out.print("BRAVOO");
		} else if(this.gameLost()){
			System.out.print("Dommage, vous avez perdu");
		}
	}
	/**
	 * give the number of roles
	 * @return the number of roles
	 */
	public int getNbRole() {
		return this.listOfRole.size();
	}
	/**
	 * give the index in the list of the current player
	 * @return the index in the list of the current player
	 */
	public int indexOfTheCurrentPlayer() {
		if(this.players.contains(this.currentPlayer)) {
			return this.players.indexOf(this.currentPlayer);
		}else {
			return 0;
		}
	}

	/**
	 * change the current player for the next player
	 */
	public void nextPlayer() {
		int index = indexOfTheCurrentPlayer() +1 ;
		if((this.currentPlayer.getName()=="") || (index==getNbPlayers())) {/*for the beginning of the game or if the current player is the last player of the list*/
			this.currentPlayer=this.players.get(0);
		}else if(index < getNbPlayers()) {/*for the next player if the current player is not the last player of the list*/
			this.currentPlayer=this.players.get(index);
		}
	}


	/*actions*/
	/**
	 * gives a random action
	 * @param p the player
	 * @return random action
	 */
	public Action aleaAction(Player p) {
		List<Action> list = p.getActionPossible();
		if (list == null || list.isEmpty()) {
			System.out.println("Aucune action possible");
		}
		Random random = new Random();
		int randomIndex = random.nextInt(list.size());
		this.displayAction(list.get(randomIndex));
		return list.get(randomIndex);
	}

	/*cities*/
	/**
	 * give the number of outbreak site
	 * @return number of outbreak site
	 */
	public int getNbOutbreakSite() {
		return this.nb_outbreak_site;
	}
	
	/**
	 * choose city on list of city
	 * @return city
	 */
	public City aleaCity() {
		Set<City> list = this.map.getAllCities();
		List<City> res = new ArrayList<>(list);
		Random randomizer = new Random();
		City random = res.get(randomizer.nextInt(res.size()));
		return random;
	}
}
