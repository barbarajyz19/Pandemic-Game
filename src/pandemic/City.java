package pandemic;

import java.util.*;

/*
 * class for City
 */
public class City{
	/*the name of the city*/
	protected String name;
	/*map for the number of cubes*/
	protected Map<Disease,Integer> diseases;
	/*the neighbors of the city*/
	protected List<City> neighbors;
	/*lets you know if there is a focus in the city and with which disease*/
	protected Map<Disease, Boolean> outbreakSites;
	/*disease at the creation*/
	protected Disease disease;
	/*true if there is a station in the city*/
	protected boolean station;
	/*true if the city have already be a focus during this round*/
	protected boolean roundOutbreakSite;

	/**
	 * Constructor of city
	 * @param name name of the City
	 * @param disease number of disease
	 */
	public City(String name, Disease disease){
		this.name = name;
		this.disease = disease;
		this.diseases = new HashMap<Disease,Integer>();
		this.outbreakSites = new HashMap<Disease, Boolean>();
		this.station = false;
		this.neighbors = new ArrayList<City>();
		this.roundOutbreakSite=false;
		this.addDisease(disease);
	}
	/**
	 * give the name
	 * @return the name of the city
	 */
	public String getName(){
		return this.name;
	}

	/*Disease*/
	/**
	 * give if there is a outbreak sites with the disease
	 * @return a map with the disease and true if there is a outbreak site with the disease else false
	 */
	public Map<Disease, Boolean> getOutbreakSites(){
		return this.outbreakSites;
	}
	
	/**
	 * true if the disease have caused an outbreak site else false
	 * @param disease the disease
	 * @return true if the disease have caused an outbreak site
	 */
	public boolean getOutbreakSiteWithDisease(Disease disease) {
		if(this.outbreakSites.containsKey(disease)) {
			return this.outbreakSites.get(disease);
		}else {
			this.outbreakSites.put(disease, false);
			return false;
		}
	}

	/**
	 * return a boolean to find out if the city was a outbreak site during the round
	 * @return true if the city have already be a outbreak site this turn
	 */
	public boolean getRoundOutbreakSite() {
		return this.roundOutbreakSite;
	}

	/**
	 * give the diseases
	 * @return the set of diseases
	 */
	public Set<Disease> getDiseases(){
		return this.diseases.keySet();
	}
	/**
	 * give the disease at the creation
	 * @return Disease the disease
	 */
	public Disease getDisease() {
		return this.disease;
	}

	/**
	 * give number of cubes 
	 * if the disease is not defined in the HashMap then it returns 0
	 * @param d the disease
	 * @return the number of cube
	 */
	public int getNbCube(Disease d){
		if (this.diseases.containsKey(d))
			return this.diseases.get(d);
		else
			return 0;
	}

	/**
	 * add disease on the city hash map diseases
	 * @param d the disease that we must add to the city
	 */
	public void addDisease(Disease d) {
		this.diseases.put(d, 0);
	}


	/**
	 * add a cube in the city and becomes outbreak site
	 * when we add a cube then we add in the HashMap the disease couple, number of cubes
	 * @param disease the disease
	 */
	public void addACube(Disease disease) {
		if(disease.getNbCube()>0 && this.getNbCube(disease) < 3){
			int i = this.getNbCube(disease) + 1;
			this.diseases.remove(disease);
			this.diseases.put(disease, i);
			System.out.println(this.getName() + " possède " + this.getNbCube(disease) + " cube(s) de la maladie " + disease.getName());
			disease.removeACube();
		}else if(disease.getNbCube()>0 && this.getNbCube(disease) == 3) {
			System.out.println(this.getName() + " possède déjà 3 cubes de la maladie" + disease.getName()); 
		}
		this.setOutbreakSite(disease);
	}

	/**
	 * add 1, 2 or 3 cubes of the disease by default in the City for the initial infection
	 * @param nbCubes is equal to 1, 2 or 3 and is the number of disease cubes to add to the city
	 */
	public void addCubesInitialization(int nbCubes) {
		if(nbCubes<=3 && nbCubes>0) {
			this.diseases.put(this.disease, nbCubes);
			for(int i=0; i<nbCubes; i++) {
				this.disease.removeACube();
			}
		}
	}

	/**
	 * spread to nearby towns
	 * @param disease the disease
	 */
	public void nearbyTowns(Disease disease){
		if (this.getOutbreakSiteWithDisease(disease) && !this.roundOutbreakSite){
			for (City city : neighbors){
				city.addACube(disease);
			}
			this.roundOutbreakSite=true;
		}
	} 
	/**
	 * set outbreak_site on true if the city have 3 cubes of disease else false
	 * @param disease the disease
	 */
	public void setOutbreakSite(Disease disease) {
		if(this.getNbCube(disease)==3) {
			if(this.outbreakSites.containsKey(disease)) {
				this.outbreakSites.remove(disease);
			}
			this.outbreakSites.put(disease, true);
		}else {
			if(this.outbreakSites.containsKey(disease)) {
				this.outbreakSites.remove(disease);
			}
			this.outbreakSites.put(disease, false);
		}
	}

	/**
	 * function to use at the end of the round to reset the fact that the city became a focus during this round
	 */
	public void resetRoundOutbreakSite() {
		this.roundOutbreakSite=false;
	}

	/**
	 * remove a cube in the city
	 * @param disease the disease
	 */
	public void removeACube(Disease disease) {
		if(this.getNbCube(disease) > 0) {
			int i = this.getNbCube(disease) - 1;
			this.diseases.remove(disease);
			this.diseases.put(disease, i);
			disease.addCube();
			this.setOutbreakSite(disease);
		}
	}
	/**
	 * reset the number of cube in the city
	 * @param disease
	 */
	public void resetCube(Disease disease) {
		int nbCube = this.getNbCube(disease);
		for (int i = 0; i < nbCube; i++) {
			this.removeACube(disease);
		}
	}

	/*Neighbors*/
	/**
	 * give the neighbors of the city
	 * @return the list of the neighbors
	 */
	public List<City> getNeighbors(){
		return this.neighbors;
	}

	/**
	 * add neighbors
	 * @param c neighbors city
	 */
	public void addNeighbors(City c) {
		this.neighbors.add(c);
	}
	/**
	 * says if a city is neighbor to another
	 * @param city
	 * @return true if the cities are nearby
	 */
	public boolean isNeighbor(City city) {
		return this.getNeighbors().contains(city);
	}

	/*Research Station*/
	/**
	 * true if there is a station in the city else false
	 * @return true if there is a station of research
	 */
	public boolean getStation(){
		return this.station;
	}
	/**
	 * true if the city has a research station
	 */
	public void getAResearchStation() {
		this.station = true;
	}  
	/**
	 * false if the research station is move to another city
	 */
	public void lostAResearchStation() {
		this.station = false;
	}
	/**
	 * return the name of the city
	 * @return string the name of the city
	 */
	public String toString() {
		return this.name;	 
	}
	/**
	 * display neighbors
	 */
	public void displayNeighbors() {
		System.out.println("ville :"+ this.name);
		System.out.println("voisins");
		for (City city : neighbors) {
			System.out.println(city.getName());
			
		} 
	}

}


