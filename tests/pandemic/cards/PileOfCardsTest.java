package pandemic.cards;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PileOfCardsTest {

	@Test
	public void isEmptyTest() {
		PileOfCards<DiseaseCard> diseasePile = new PileOfCards<DiseaseCard>();
		assertEquals(diseasePile.isEmpty(), true);
	}
	
	@Test
	public void stackTest() {
		PileOfCards<DiseaseCard> diseasePile = new PileOfCards<DiseaseCard>();
		PileOfCards<DiseaseCard> diseasePile2 = new PileOfCards<DiseaseCard>();
		diseasePile2.listCards.add(diseasePile2.listCards.size(), new DiseaseCard("ville-1", 1));
		diseasePile2.listCards.add(diseasePile2.listCards.size(), new DiseaseCard("ville-2", 1));
		diseasePile.stack(diseasePile2);
		assertEquals(diseasePile.listCards.equals(diseasePile2.listCards),true);
	}
	
	
	
	@Test
	public void mixCardsTest() {
		PileOfCards<DiseaseCard> diseasePile = new PileOfCards<DiseaseCard>();
		diseasePile.listCards.add(diseasePile.listCards.size(), new DiseaseCard("ville-1", 1));
		diseasePile.listCards.add(diseasePile.listCards.size(), new DiseaseCard("ville-2", 1));
		diseasePile.listCards.add(diseasePile.listCards.size(), new DiseaseCard("ville-3", 1));
		diseasePile.listCards.add(diseasePile.listCards.size(), new DiseaseCard("ville-4", 1));
		List<DiseaseCard> initialPile = new ArrayList<>();
		for(DiseaseCard c : diseasePile.listCards) {initialPile.add(c);}
		diseasePile.mixCards();
		List<DiseaseCard> mixedPile = diseasePile.listCards;
		assertEquals(initialPile.equals(mixedPile), false);
	}
    
    @Test
	public void unstacktest() {
		PileOfCards<DiseaseCard> diseasePile = new PileOfCards<DiseaseCard>();
		diseasePile.listCards.add(diseasePile.listCards.size(), new DiseaseCard("ville-1", 1));
		diseasePile.listCards.add(diseasePile.listCards.size(), new DiseaseCard("ville-2", 1));
		DiseaseCard cardToUnstack = new DiseaseCard("ville-3", 1);
		diseasePile.listCards.add(diseasePile.listCards.size(), cardToUnstack);
		assertEquals(diseasePile.unstack(), cardToUnstack);
	}
}
