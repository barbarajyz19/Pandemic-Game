package pandemic;
import pandemic.cards.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Map;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
/** class for maps */
public class Maps {
	/*attributes for cities*/
	/*map for the neighbors*/
	protected Map<City, List<City>> neighbors;
	/*the list of the cities with station*/
	private List<City> citiesWithStation;
	/*max number station*/
	protected static final int MAX_STATION = 6;
	/*list of the cities in the map*/
	private List<City> listCities;

	/*attributes for diseases*/
	/*map for sickness*/
	protected Map<City, Disease> diseases;
	/*list of remedies found*/
	protected List<Disease> remedyFound;
	/*map for the remaining number of cubes*/
	protected Map <String , Integer> nb_remaining_cube;

	/*attributes for piles of cards*/
	/*player cards pile*/
	protected PileOfCards<PlayerCard> playerCardsPile;
	/*disease cards pile*/
	protected PileOfCards<DiseaseCard> diseaseCardsPile;
	/*discard pile of player cards*/
	protected PileOfCards<PlayerCard> discardPlayerCardsPile;
	/*discard pile of disease cards*/ 
	protected PileOfCards<DiseaseCard> discardDiseaseCardsPile;


	/**
	 * initialize the maps
	 */
	public Maps () {   
		this.neighbors = new HashMap<City, List<City>>();
		this.diseases = new HashMap<City, Disease>();
		this.nb_remaining_cube = new HashMap<String, Integer>();
		this.remedyFound = new ArrayList<Disease>();
		this.citiesWithStation = new ArrayList<City>();
		this.listCities = new ArrayList<City>();
		this.diseaseCardsPile = new PileOfCards<>();
		this.playerCardsPile = new PileOfCards<>();
		this.discardPlayerCardsPile = new PileOfCards<>();
		this.discardDiseaseCardsPile = new PileOfCards<>();
	}



	/*Cities*/

	/**
	 * give the neighbors of the city
	 * @return a map with a city and all the neighbors of this city 
	 */
	public Map<City, List<City>> getNeighbors(){
		return this.neighbors;	
	}

	/**
	 * give cities with Station
	 * @return a list of cities
	 */
	public List<City> getCitiesWithStation(){
		return this.citiesWithStation;
	}
	
	/**
	 * give cities in the map
	 * @return a list of cities in the map
	 */
	public List<City> getListOfCities(){
		return this.listCities;
	}

	/**
	 * give the number of the station
	 * @return number of station
	 */
	public int getNbStations() {
		return this.citiesWithStation.size();
	}

	/**
	 * add station if the station maximum is reached (when exception was raised)
	 * @param cityToAdd city ​​to which we must add the station
	 * @param cityToRemove city ​​to which we must remove the station
	 * @throws MaxStationException if MAX_STATION is reached
	 */
	public void addStation(City cityToAdd, City cityToRemove)throws MaxStationException{
		this.removeStation(cityToRemove);
		this.addStation(cityToAdd);

	}
	/**
	 * add station when the number max of station is not reached
	 * @param cityToAdd city ​​in which to add the station
	 * @throws MaxStationException if MAX_STATION is reached
	 */
	public void addStation(City cityToAdd)  throws MaxStationException{
		if (this.getNbStations() < MAX_STATION) {
			this.citiesWithStation.add(cityToAdd);
			cityToAdd.getAResearchStation();
		} else {
			throw new MaxStationException("Le nombre maximum de Station de Recherche est atteint, il faut donc déplacer une Station d'une autre ville");
		}
	}

	/**
	 * remove the station on the city
	 * @param city the city in which the station must be removed
	 */
	public void removeStation(City city) {
		if(city.getStation()) {
			city.lostAResearchStation();
			for(int i=0; i<getNbStations();i++) {
				if(city==this.citiesWithStation.get(i)) {
					this.citiesWithStation.remove(i);
				}
			}
		}

	}

	/**
	 * give all cities 
	 * @return a list with cities 
	 */
	public Set<City> getAllCities(){
		return this.getNeighbors().keySet();	
	}

	/*Diseases*/
	/**
	 * give the list of remedies found
	 * @return list of remedies found
	 */
	public List<Disease> getRemedyFound() {
		return this.remedyFound;
	}

	/**
	 * give the disease present in the cities
	 * @return a map where the cities are the keys and the diseases are the values
	 */
	public Map <City, Disease> getDiseases(){
		return this.diseases;
	}

	/**
	 * give the number of remaining cube 
	 * @return a map where string are the type of cubes and the integers are the number of remaining cubes of that type
	 */
	public Map<String , Integer> getNbRemainingCubes() {
		return this.nb_remaining_cube;
	}



	/*Piles Of Cards*/
	/**
	 * give the pile of player cards
	 * @return the pile of player cards
	 */
	public PileOfCards<PlayerCard> getPileOfPlayerCards(){
		return this.playerCardsPile;
	}

	/**
	 * give the discard pile of player cards
	 * @return the discard pile of player cards
	 */
	public PileOfCards<PlayerCard> getDiscardPileOfPlayerCards(){
		return this.discardPlayerCardsPile;
	}

	/**
	 * give the pile of disease cards
	 * @return the pile of disease cards
	 */
	public PileOfCards<DiseaseCard> getPileOfDiseaseCards(){
		return this.diseaseCardsPile;
	}

	/**
	 * give the discard pile of disease cards
	 * @return the discard pile of disease cards
	 */
	public PileOfCards<DiseaseCard> getDiscardPileOfDiseaseCards(){
		return this.discardDiseaseCardsPile;
	}

	/**
	 * give the number of remaining player cards in the pile of cards
	 * @return the number of remaining player cards in the pile of cards
	 */
	public int getNbRemainingPlayerCards() {
		return this.playerCardsPile.getListCards().size();
	}

	/**
	 * give the number of remaining disease cards in the pile of cards
	 * @return the number of remaining disease cards in the pile of cards
	 */
	public int getNbRemainingDiseaseCards() {
		return this.diseaseCardsPile.getListCards().size();
	}



	/*Other*/ 
	/**
	 * initialize the map of the game
	 * @param FileName the name of the file 
	 * @throws FileNotFoundException if the file specified cannot be found
	 */

	public void initializeMap(String FileName) throws FileNotFoundException {
		FileReader reader = new FileReader(FileName);
		JSONObject villes = new JSONObject(new JSONTokener(reader));
		JSONObject maladies = villes.getJSONObject("cities");
		JSONObject entry = villes.getJSONObject("neighbors");
		Iterator<String> datakeys = maladies.keys();
		List<Disease> listDiseases = new ArrayList<>();
		listDiseases.add(new Disease(0));
		listDiseases.add(new Disease(1));
		listDiseases.add(new Disease(2));
		listDiseases.add(new Disease(3));
		while (datakeys.hasNext()) {
			String d = datakeys.next();
			for(int i=0; i<listDiseases.size();i++) {
				if(maladies.getInt(d)==listDiseases.get(i).getName()) {
					City ville = new City(d,listDiseases.get(i));
					this.diseases.put(ville, ville.getDisease());
					this.listCities.add(ville);
				}
			}
			PlayerCard pcardtostack = new PlayerCard(d, maladies.getInt(d));
			DiseaseCard dcardtostack = new DiseaseCard(d, maladies.getInt(d));
			playerCardsPile.stackOneCard(pcardtostack);
			diseaseCardsPile.stackOneCard(dcardtostack);
		}
		diseaseCardsPile.mixCards();
		Iterator<String> datakeys2 = entry.keys();
		while (datakeys2.hasNext()) {
			String c = datakeys2.next();
			for(int i=0; i<listCities.size();i++) {
				if(c.equals(this.listCities.get(i).getName())) {
					JSONArray k = entry.getJSONArray(c);
					List<City> liste = new ArrayList<>();
					for (Object o : k) {
	                    String voi = (String)o;
	                    for(int j=0; j<this.listCities.size();j++){
	                        if(voi.equals(this.listCities.get(j).getName())){
	                            liste.add(this.listCities.get(j));
	                            this.listCities.get(i).addNeighbors(this.listCities.get(j));
	                        }
	                    }
	                }
					this.neighbors.put(this.listCities.get(i), liste);
				}
			}
		}
		
		/*add the epidemic cards*/
		PileOfCards<PlayerCard> pileIntermediaire1 = new PileOfCards<PlayerCard>();
		for(int i =0; i < 10; i++) {
			pileIntermediaire1.stackOneCard(this.playerCardsPile.unstack());
		}
		pileIntermediaire1.stackOneCard(new EpidemicCard());
		pileIntermediaire1.mixCards();

		PileOfCards<PlayerCard> pileIntermediaire2 = new PileOfCards<PlayerCard>();
		for(int i =0; i < 10; i++) {
			pileIntermediaire2.stackOneCard(this.playerCardsPile.unstack());
		}
		pileIntermediaire2.stackOneCard(new EpidemicCard());
		pileIntermediaire2.mixCards();

		PileOfCards<PlayerCard> pileIntermediaire3 = new PileOfCards<PlayerCard>();
		for(int i =0; i < 10; i++) {
			pileIntermediaire3.stackOneCard(this.playerCardsPile.unstack());
		}
		pileIntermediaire3.stackOneCard(new EpidemicCard());
		pileIntermediaire3.mixCards();


		this.playerCardsPile.stackOneCard(new EpidemicCard());
		this.playerCardsPile.mixCards();
		this.playerCardsPile.stack(pileIntermediaire3);
		this.playerCardsPile.stack(pileIntermediaire2);
		this.playerCardsPile.stack(pileIntermediaire1);
	}

}
