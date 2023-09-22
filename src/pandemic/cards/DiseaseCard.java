package pandemic.cards;

/*
 * class for disease card
 */
public class DiseaseCard extends Card {
	/**
	 * constructor
	 * @param cityName
	 * @param diseaseName
	 */
	public DiseaseCard(String cityName, int diseaseName) {
		super(cityName,diseaseName);
	}
	
	/**
	 * the description
	 */
	public void description() {
		System.out.println("La carte est une carte MALADIE : la ville '" + this.cityName + "' reçois un cube du virus '" + this.diseaseName + "'");
	}
	
	
	/**
	 * toString for get the type of the card
	 * @return the card 
	 */
	public String toString() {
		return "DiseaseCard";
	}

}
