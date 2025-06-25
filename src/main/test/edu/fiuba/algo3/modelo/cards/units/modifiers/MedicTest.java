package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.specials.MoraleBoost;
import edu.fiuba.algo3.modelo.cards.specials.weathers.BitingFrost;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.sections.types.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MedicTest {
    private DiscardPile discardPile;
    private Row closeCombat;
    private Card medicCard;
    private List<Modifier> modifiers = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        discardPile = new DiscardPile();
        closeCombat = new CloseCombat(discardPile);
        modifiers.add(new Medic(discardPile));
        medicCard = new Unit("Regular", "Descripcion", 6, List.of(new CloseCombatType()), modifiers);
    }

    @Test
    public void testUnaMedicCardSeColocaCorrectamenteDevolviendoLaUnicaCardUnitDeDiscardPile(){
        Card unit1 = new Unit("Catapult", "Descripcion", 8, List.of(new CloseCombatType()), new ArrayList<>());

        closeCombat.placeCard(unit1);
        closeCombat.discardCards();
        closeCombat.placeCard(medicCard);

        assertTrue(closeCombat.containsCards(List.of(medicCard,unit1)));;
        assertTrue(discardPile.isEmpty());
    }

    @Test
    public void testUnaMedicCardNoPuedeDevolverCartasQueNoSeanUnitDelDiscardPilePeroSeColocaCorrectamente(){
        Card specialCard1 = new MoraleBoost("MoraleBoost", "X2", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));

        discardPile.addCard(specialCard1);
        closeCombat.placeCard(medicCard);

        assertTrue(closeCombat.containsCard(medicCard)); //CloseCombat queda solo con Medico
        assertEquals(discardPile.getCardCount(),1);
    }

    @Test
    public void testSiLaDiscardPileEstaVaciaUnaMedicCardNoDevuelveNada(){
        closeCombat.placeCard(medicCard);
        assertTrue(discardPile.isEmpty());
        assertTrue(closeCombat.containsCard(medicCard));
    }



    @Test
    public void testSeCalculaCorrectamenteElPuntajeUsandoUnaMedicCard(){
        Card unit1 = new Unit("Catapult", "Descripcion", 8, List.of(new CloseCombatType()), new ArrayList<>());
        Card unit2 = new Unit("Arquero", "Descripcion", 5, List.of(new CloseCombatType()), new ArrayList<>());
        Card unit3 = new Unit("Arquero", "Descripcion", 3, List.of(new CloseCombatType()), new ArrayList<>());
        int expectedPoints = 22;


        closeCombat.placeCard(unit1);
        closeCombat.placeCard(unit3);
        discardPile.addCard(unit2);
        closeCombat.placeCard(medicCard);

        assertEquals(expectedPoints, closeCombat.calculatePoints());
        assertTrue(closeCombat.containsCards(List.of(unit1, unit2, unit3, medicCard)));
        assertTrue(discardPile.isEmpty());
    }

    @Test
    public void testMedicCardNoDevuelveUnitCardDeDistintaRowDondeSeJugo(){
        Card unit1 = new Unit("Catapult", "Descripcion", 8, List.of(new SiegeType()), new ArrayList<>());
        Siege siege = new Siege(discardPile);

        siege.placeCard(unit1);
        siege.discardCards();
        closeCombat.placeCard(medicCard);

        assertTrue(closeCombat.containsCard(medicCard));
        assertTrue(discardPile.containsCard(unit1));
        assertTrue(siege.getCards().isEmpty());
    }

    @Test
    public void testMedicCardDevuelveLaUnitCardDeMismoRowDondeSeJugo(){
        Card unit1 = new Unit("Catapult", "Descripcion", 8, List.of(new CloseCombatType()), new ArrayList<>());
        Card unit2 = new Unit("Arquero", "Descripcion", 8, List.of(new SiegeType()), new ArrayList<>());
        Card unit3 = new Unit("Arquero", "Descripcion", 8, List.of(new RangedType()), new ArrayList<>());

        discardPile.addCard(unit1);
        discardPile.addCard(unit2);
        discardPile.addCard(unit3);
        closeCombat.placeCard(medicCard);

        assertTrue(closeCombat.containsCards(List.of(unit1, medicCard)));
        assertEquals(discardPile.getCardCount(),2);
    }

}
