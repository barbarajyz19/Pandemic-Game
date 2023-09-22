package pandemic;

/*
 * class for disease
 */
public class Disease{
	/*the number max of cubes for a disease */
	private static final int MAX_CUBES = 24;
	/*the current number of cubes of the disease*/
	protected int nbCubes;
	/*the cure for the disease has been found*/
	protected boolean cureFound;
	/*the name of this disease*/
	protected int name;
	/**
	 * the constructor of the disease
	 * @param name the name of the disease
	 */
	public Disease(int name){
		this.cureFound = false;
		this.name = name;
		this.nbCubes=MAX_CUBES;
	}

	/**
	 * gives the name of the disease
	 * @return the name of the disease
	 */
	public int getName() {
		return this.name;
	}

	/**
	 * true if the cure have been found else false
	 * @return true if the cure have been found
	 */
	public boolean getCureFound() {
		return this.cureFound;
	}

	/**
	 * The remedy have been found
	 */
	public void setCureFound() {
		this.cureFound=true;
	}

	/**
	 * give the number max of cubes for the disease
	 * @return the number max of cubes
	 */
	public int getNbMaxOfCubes() {
		return MAX_CUBES;
	}

	/**
	 * gives the number of remaining cubes
	 * @return number of remaining cubes
	 */
	public int getNbCube(){
		return this.nbCubes;
	}
	/**
	 * set the number of remaining cubes
	 * @param nb the number of cubes
	 */
	public void setNbCube(int nb) {
		this.nbCubes = nb;
	}

	/**
	 * remove a cube in the city
	 */
	public void removeACube() {
		if(this.nbCubes > 0) {
			this.nbCubes -= 1;
			System.out.println("La maladie " + this.getName() + " a perdu un cube, elle en a encore " + this.getNbCube());
		}
	}

	/**
	 * to add cube
	 */
	public void addCube(){
		if(this.nbCubes<MAX_CUBES) {
			this.nbCubes +=1;
			System.out.println("La maladie " + this.getName() + " a récupéré un cube, elle en a maintenant " + this.getNbCube());
		}
	}

}