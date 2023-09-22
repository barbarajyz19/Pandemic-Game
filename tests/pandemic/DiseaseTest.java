package pandemic;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiseaseTest {

	@Test
	public void InitializationOfADiseaseTest() {
		Disease d = new Disease(1);
		assertEquals(d.getName(),1);
		assertEquals(d.getNbMaxOfCubes(),24);
		assertEquals(d.getNbCube(),24);
		assertEquals(d.getCureFound(),false);
	}

	@Test
	public void AddAndRemoveCubeTest() {
		Disease d = new Disease(1);
		d.addCube();
		/*verify that the function don't add a cube because the disease have already the max number of cubes*/
		assertEquals(d.getNbCube(),24);
		d.removeACube();
		/*verify the function remove*/
		assertEquals(d.getNbCube(),23);
		/*verify the number of current cubes don't go below 0*/
		for (int i =0;i<25;i++) {
			d.removeACube();
		}
		assertEquals(d.getNbCube(),0);
		d.addCube();
		assertEquals(d.getNbCube(),1);
	}
}
