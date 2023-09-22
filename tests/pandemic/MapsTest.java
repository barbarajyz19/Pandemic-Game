package pandemic;

import java.util.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MapsTest {
	private Maps m;
	private Map<City, List<City>> map;
	private Map<City, Disease> map2;
	private Map<String, Integer> map3;
	private List<Disease> list;
	private City c;
	private City c2;
	private City c3;
	private City c4;
	private City c5;
	private City c6;
	private City c7;
	private Disease d;
	
	@Before
	public void init() {
		this.m = new Maps();
		this.map = new HashMap<City, List<City>>();
		this.map2 = new HashMap<City, Disease>();
		this.map3 = new HashMap<String, Integer>();
		this.list = new ArrayList<Disease>();
		this.d = new Disease(1);
		this.c = new City("city1",d); 
    	this.c2 = new City("city2",d);
    	this.c3 = new City("city3",d);
    	this.c4 = new City("city",d);
    	this.c5 = new City("city5",d);
    	this.c6 = new City("city6",d);
    	this.c7 = new City("city7",d);
	}
	@Test
	public void MapsInitializationOnlyConstructorTest() {
		assertEquals(m.getNeighbors(),map);
		assertEquals(m.getDiseases(),map2);
		assertEquals(m.getRemedyFound(),list);
		assertEquals(m.getNbRemainingPlayerCards(),0);/*because there is no cards in the beginning*/
		assertEquals(m.getNbRemainingCubes(),map3);
		assertEquals(m.getNbStations(),0);
	}
	@Test(expected=MaxStationException.class)
	public void AddStationTestWhenMaxStationIsReached() throws MaxStationException {
		assertEquals(m.getNbStations(),0);
		m.addStation(c);
		m.addStation(c2);
		m.addStation(c3);
		m.addStation(c4);
		m.addStation(c5);
		m.addStation(c6);
		m.addStation(c7);
	}
	@Test
	public void AddStationTest() throws MaxStationException{
		assertEquals(m.getNbStations(),0);
		m.addStation(c);
		assertEquals(m.getNbStations(),1);
	}
	@Test
	public void AddStationWhenMaxStationAndRemoveStation() throws MaxStationException {
		try {
			m.addStation(c);
			m.addStation(c2);
			m.addStation(c3);
			m.addStation(c4);
			m.addStation(c5);
			m.addStation(c6);
			assertEquals(m.getNbStations(),6);
			m.addStation(c7);
			assertEquals(m.getNbStations(),6);
		} catch (MaxStationException e){
			assertEquals(m.getNbStations(),6);
			m.addStation(c7,c);
			assertEquals(m.getNbStations(),6);
		}
	}
	@Test
	public void RemoveStationWhenCityDontHaveStationTest() {
		assertFalse(c.getStation());
		m.removeStation(c);
		assertFalse(c.getStation());
	}
	@Test
	public void RemoveStationTest() throws MaxStationException {
		assertFalse(c.getStation());
		m.addStation(c);
		assertTrue(c.getStation());
		m.removeStation(c);
		assertFalse(c.getStation());	
	}
	

}
