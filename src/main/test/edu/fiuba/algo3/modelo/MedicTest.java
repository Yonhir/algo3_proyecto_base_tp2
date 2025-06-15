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
    private Player mockPlayer;
    private Card medicCard;
    private List<Modifier> modifiers = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        discardPile = new DiscardPile();
        closeCombat = new CloseCombat();
        mockPlayer = mock(Player.class);
        modifiers.add(new Medic(discardPile,mockPlayer));
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
    public void testUnaMedicCardNoPuedeDevolverCartasQueNoSeanUnitDelDiscardPile(){
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
    }

    @Test
    public void testUnaMedicCardDaLaOpcionDeElejirUnaUnitCardDelDiscardPileSiHayMasDeUna(){
        Card unit1 = new Unit("Catapult", "Descripcion", 8, List.of(new CloseCombatType()), new ArrayList<>());
        Card unit2 = new Unit("Arquero", "Descripcion", 5, List.of(new CloseCombatType()), new ArrayList<>());
        Card unit3 = new Unit("Arquero", "Descripcion", 3, List.of(new CloseCombatType()), new ArrayList<>());

        closeCombat.placeCard(unit1);
        closeCombat.placeCard(unit2);
        closeCombat.placeCard(unit3);
        closeCombat.discardCards(discardPile);

        assertTrue(closeCombat.getCards().isEmpty());
        assertEquals(3, discardPile.getCardCount());

        List<Card> unitCards = discardPile.getUnitCards();
        when(mockPlayer.selectCard(unitCards)).thenReturn(unit2);

        closeCombat.placeCard(medicCard);

        assertTrue(closeCombat.getCards().containsAll(List.of(unit2, medicCard)));
    }

    @Test
    public void testSiLaDiscardPileEstaVaciaUnaMedicCardNoDevuelveNada(){
        assertThrows(IllegalStateException.class, () ->{
            closeCombat.placeCard(medicCard);
        });
    }

    @Test
    public void testElUsuarioNoPuedeElegirUnitCardDeDiferenteRowDondeJugoLaMedicCard(){
        Row siege = new Siege();
        Card unit1 = new Unit("Catapult", "Descripcion", 8, List.of(new CloseCombatType()), new ArrayList<>());
        Card unit2 = new Unit("Arquero", "Descripcion", 5, List.of(new SiegeType()), new ArrayList<>());
        Card unit3 = new Unit("Arquero", "Descripcion", 3, List.of(new SiegeType()), new ArrayList<>());

        closeCombat.placeCard(unit1);
        siege.placeCard(unit2);
        siege.placeCard(unit3);
        closeCombat.discardCards(discardPile);
        siege.discardCards(discardPile);

        assertTrue(closeCombat.getCards().isEmpty());
        assertTrue(siege.getCards().isEmpty());
        assertEquals(3, discardPile.getCardCount());

        List<Card> unitCards = discardPile.getUnitCards();
        when(mockPlayer.selectCard(unitCards)).thenReturn(unit2); //El usario elige una Unit Card de una Row difertente
                                                                   //a donde jugo Medic Card
        assertThrows(SectionTypeMismatchError.class, () ->{
            closeCombat.placeCard(medicCard);
        });
    }

    @Test
    public void testElUsuarioEligeCorrectamenteUnitCardDeMismoRowDondeJugoMedicCard(){
        Row siege = new Siege();
        Card unit1 = new Unit("Catapult", "Descripcion", 8, List.of(new CloseCombatType()), new ArrayList<>());
        Card unit2 = new Unit("Arquero", "Descripcion", 5, List.of(new SiegeType()), new ArrayList<>());
        Card unit3 = new Unit("Arquero", "Descripcion", 3, List.of(new SiegeType()), new ArrayList<>());

        closeCombat.placeCard(unit1);
        siege.placeCard(unit2);
        siege.placeCard(unit3);
        closeCombat.discardCards(discardPile);
        siege.discardCards(discardPile);

        assertTrue(closeCombat.getCards().isEmpty());
        assertTrue(siege.getCards().isEmpty());
        assertEquals(3, discardPile.getCardCount());

        List<Card> unitCards = discardPile.getUnitCards();
        when(mockPlayer.selectCard(unitCards)).thenReturn(unit1);

        closeCombat.placeCard(medicCard);

        assertTrue(closeCombat.getCards().containsAll(List.of(unit1, medicCard)));
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

        List<Card> unitCards = discardPile.getUnitCards();
        when(mockPlayer.selectCard(unitCards)).thenReturn(unit2);
        closeCombat.placeCard(medicCard);

        assertEquals(11, closeCombat.calculatePoints());
    }

}
