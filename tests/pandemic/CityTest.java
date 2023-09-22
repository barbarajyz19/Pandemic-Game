package pandemic;

import java.util.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CityTest {
	private Disease d;
	private Disease d2;
	private City c;
	private City c2;
	private City c3;
	private ArrayList<City> list;
	private Map<Disease, Boolean> outbreaks;
	
	@Before
	public void init(){
		this.d = new Disease(1);
		this.d2 = new Disease(2);
    	this.c = new City("city1",d);
    	this.c2 = new City("city2",d);
    	this.c3 = new City("city3",d);
		this.list = new ArrayList<City>();
		this.outbreaks = new HashMap<Disease, Boolean>(); 
	}
	@Test 
    public void CityInitializationTest(){
    	assertEquals(c.getName(),"city1");
    	assertEquals(c.getDisease(),d);
    	assertEquals(c.getNbCube(d),0);
    	assertEquals(c.getOutbreakSites(),outbreaks);
    	assertEquals(c.getStation(),false);
    	assertEquals(c.getNeighbors(),list);
		assertEquals(c.getRoundOutbreakSite(),false);
    }
	@Test
	public void addDiseaseTest() {
		c.addDisease(d);
		assertEquals(c.getNbCube(d),0);
		c.addACube(d);
		assertEquals(c.getNbCube(d),1);
		c.addDisease(d2);
		assertEquals(c.getNbCube(d2),0);
		assertEquals(c.getNbCube(d),1);		
	}
    @Test
    public void AddNeighbors() {
    	assertEquals(c.getNeighbors(),list);/*before add the cities in the neighborhood*/
    	c.addNeighbors(c2);/*add the neighbors of the city c*/
    	c.addNeighbors(c3);
    	list.add(c2);/*add the cities c2 and c3 in a list of city*/
    	list.add(c3);
    	assertEquals(c.getNeighbors(),list);/*after add the neighborhood*/
    }
    
    @Test
    public void AddCubesForTheInitialInfectionTest() {
    	Disease dis = c.getDisease();
    	/*initialization one cube*/
    	c.addCubesInitialization(1);
    	assertEquals(c.getNbCube(dis),1);
    	assertEquals(dis.getNbCube(),23);
    	c.removeACube(dis);
    	/*initialization two cubes*/
    	c.addCubesInitialization(2);
    	assertEquals(c.getNbCube(dis),2);
    	assertEquals(dis.getNbCube(),22);
    	c.removeACube(dis);
    	c.removeACube(dis);
    	/*initialization three cubes*/
    	c.addCubesInitialization(3);
    	assertEquals(c.getNbCube(dis),3);
    	assertEquals(dis.getNbCube(),21);
    	c.removeACube(dis);
    	c.removeACube(dis);
    	c.removeACube(dis);
    	/*try four cubes*/
    	c.addCubesInitialization(4);
    	assertEquals(c.getNbCube(dis),0);
    	assertEquals(dis.getNbCube(),24);
    }
    
    @Test
    public void AddAndRemoveACubeTest(){
    	c.addACube(d);/*add a first cube*/
    	this.outbreaks.put(d, true);
    	assertEquals(c.getNbCube(d),1);
    	assertFalse(c.getOutbreakSiteWithDisease(d));
    	assertFalse(c.getRoundOutbreakSite());
    	c.addACube(d);/*add a second cube*/
    	assertEquals(c.getNbCube(d),2);
    	assertFalse(c.getOutbreakSiteWithDisease(d));
    	assertFalse(c.getRoundOutbreakSite());
    	c.addACube(d);/*add a third cube*/
    	assertEquals(c.getNbCube(d),3);
    	/*focus become true because there is 3 cubes of the same disease*/
    	assertTrue(c.getOutbreakSiteWithDisease(d));
    	assertEquals(c.getOutbreakSites(),outbreaks);
    	assertFalse(c.getRoundOutbreakSite());
    	c.addACube(d);/*try to add a new cube but don't do it because there is already 3 cubes of the disease*/
    	assertEquals(c.getOutbreakSiteWithDisease(d),true);
    	assertEquals(c.getNbCube(d),3);
    	c.nearbyTowns(d);
    	assertTrue(c.getRoundOutbreakSite());
    	
    	c.removeACube(d);/*remove a first cube*/
    	assertEquals(c.getNbCube(d),2);
    	c.removeACube(d);/*remove a second cube*/
    	assertEquals(c.getNbCube(d),1);
    	c.removeACube(d);/*remove a third cube*/
    	assertEquals(c.getNbCube(d),0);
    	c.removeACube(d);/*don't remove a cube because there is no cube*/
    	assertEquals(c.getNbCube(d),0);
    }
    
    @Test /* à modif pour le cas où le foyer peut devenir plusieurs fois un foyer mais sur différents tours*/
    public void BecomeAFocusTest() {
    	c.addNeighbors(c2);/*add neighbors to the city c and c2*/
    	c.addNeighbors(c3);
    	c2.addNeighbors(c);
    	c2.addNeighbors(c3);
    	c.addACube(d);
    	c.addACube(d);
    	c.addACube(d);/*c have 3 cubes of the disease*/
    	c.nearbyTowns(d);/*possible because outbreak_site is true and roundOutbreakSite is wrong*/
    	assertEquals(d.getNbCube(), 19);/*verification of the number of remaining cubes of disease*/
    	assertEquals(c2.getNbCube(d),1);
    	assertEquals(c3.getNbCube(d),1);
    	c2.addACube(d);
    	c2.addACube(d);/*c2 have 3 cubes of the disease*/
    	c2.nearbyTowns(d);/*possible because outbreak_site is true and roundOutbreakSite is wrong*/
    	assertEquals(d.getNbCube(), 16);/*verification of the number of remaining cubes of disease*/
    	assertEquals(c3.getNbCube(d),2);
    	c.addACube(d);
    	c.nearbyTowns(d);/*impossible because outbreak_site is true and roundOutbreakSite is true*/
    	assertEquals(c3.getNbCube(d),2);/*verify that the city don't spread the disease a second time*/
    	c.resetRoundOutbreakSite();/*reset the round*/
    	c.addACube(d);
    	c.nearbyTowns(d);/*possible because outbreak_site is true and roundOutbreakSite is wrong*/
    	assertEquals(c3.getNbCube(d),3);
    }
    
    @Test
    public void CityGetsAResearchStationTest() {
    	c.getAResearchStation();/*add a station in the city*/
    	assertEquals(c.getStation(),true);
    	c.lostAResearchStation();/*remove a station in the city*/
    	assertEquals(c.getStation(),false);
    }  
    
    @Test
    public void resetCubeTest() {
    	c.addACube(d);
    	c.addACube(d);
    	c.addACube(d);
    	assertEquals(c.getNbCube(d),3);
    	c.resetCube(d);
    	assertEquals(c.getNbCube(d),0);	
    }
}

