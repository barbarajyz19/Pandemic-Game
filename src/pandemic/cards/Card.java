package pandemic.cards;
/*
 * class for card
 */
public class Card{
	/*the name of the city*/
	protected String cityName;
	/*the name of the disease*/
	protected int diseaseName;
	
	/**
	 * the constructor
	 * @param cityName the name of the city
	 * @param diseaseName the name of the disease in the city
	 */
	public Card(String cityName, int diseaseName) {
		this.cityName=cityName;
		this.diseaseName=diseaseName;
	}
	
	/**
	 * give the name of the city
	 * @return the name of the city
	 */
	public String getCityName() {
		return this.cityName;
	}
	
	/**
	 * give the name of the disease in the city
	 * @return the name of the disease in the city
	 */
	public int getDiseaseName() {
		return this.diseaseName;
	}
}