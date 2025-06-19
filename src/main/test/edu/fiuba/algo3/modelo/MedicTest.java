package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
        closeCombat = new CloseCombat();
        modifiers.add(new Medic(discardPile));
        medicCard = new Unit("Regular", "Descripcion", 6, List.of(new CloseCombatType()), modifiers);
    }

    @Test
    public void testUnaMedicCardSeColocaCorrectamenteDevolviendoLaUnicaCardUnitDeDiscardPile(){
        Card unit1 = new Unit("Catapult", "Descripcion", 8, List.of(new CloseCombatType()), new ArrayList<>());

        closeCombat.placeCard(unit1);
        closeCombat.discardCards(discardPile);

        assertTrue(closeCombat.getCards().isEmpty());          //CloseCombat queda vacio
        assertEquals(1, discardPile.getCardCount());

        closeCombat.placeCard(medicCard);

        assertTrue(closeCombat.getCards().containsAll(List.of(unit1, medicCard))); //CloseCombat queda con Medico y la unitCard descartada
    }

    @Test
    public void testUnaMedicCardNoPuedeDevolverCartasQueNoSeanUnitDelDiscardPilePeroSeColocaCorrectamente(){
        Card specialCard = new BitingFrost("Nombre", "Descripcion");
        SpecialZone specialZone = new SpecialZone(
                List.of(closeCombat),
                List.of(new Ranged()),
                List.of(new Siege())
        );

        specialCard.play(specialZone);
        closeCombat.discardCards(discardPile);

        assertEquals(1, discardPile.getCardCount());
        assertThrows(IllegalStateException.class, () ->{
            closeCombat.placeCard(medicCard);
       });
        assertTrue(closeCombat.getCards().contains( medicCard)); //CloseCombat queda con Medico
    }

    @Test
    public void testSiLaDiscardPileEstaVaciaUnaMedicCardNoDevuelveNada(){
        assertThrows(IllegalStateException.class, () ->{
            closeCombat.placeCard(medicCard);
        });
        assertTrue(closeCombat.getCards().contains( medicCard));
    }



    @Test
    public void tesSeCalculaCorrectamenteElPuntajeUsandoUnaMedicCard(){
        Card unit1 = new Unit("Catapult", "Descripcion", 8, List.of(new CloseCombatType()), new ArrayList<>());
        Card unit2 = new Unit("Arquero", "Descripcion", 5, List.of(new CloseCombatType()), new ArrayList<>());
        Card unit3 = new Unit("Arquero", "Descripcion", 3, List.of(new CloseCombatType()), new ArrayList<>());

        closeCombat.placeCard(unit1);
        closeCombat.placeCard(unit2);
        closeCombat.placeCard(unit3);

        assertEquals(16, closeCombat.calculatePoints());

        closeCombat.discardCards(discardPile);
        closeCombat.placeCard(medicCard);

        assertEquals(9, closeCombat.calculatePoints());
        assertTrue(closeCombat.getCards().containsAll(List.of(unit3, medicCard)));
    }

}
