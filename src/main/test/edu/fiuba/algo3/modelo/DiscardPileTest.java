package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class DiscardPileTest {
    private DiscardPile discardPile;
    private Unit unit1;
    private Unit unit2;
    private List<Card> closeCombatUnits;
    private List<Card> rangedUnits;
    private List<Card> siegeUnits;
    private List<Card> cards;


    @BeforeEach
    void setUp() {
        discardPile = new DiscardPile();
        unit1 = new Unit("Unit1", "Description1", 5, List.of(new CloseCombatType()), new ArrayList<>());
        unit2 = new Unit("Unit2", "Description2", 7, List.of(new RangedType()), new ArrayList<>());
        closeCombatUnits = Arrays.asList(
            new Unit("Nombre", "Descripcion", 4, List.of(new CloseCombatType()), new ArrayList<>()),
            new Unit("Nombre", "Descripcion", 5, List.of(new CloseCombatType()), new ArrayList<>()),
            new Unit("Nombre", "Descripcion", 2, List.of(new CloseCombatType()), new ArrayList<>()),
            new Unit("Nombre", "Descripcion", 10, List.of(new CloseCombatType()), new ArrayList<>()),
            new Unit("Nombre", "Descripcion", 3, List.of(new CloseCombatType()), new ArrayList<>())
        );
        rangedUnits = Arrays.asList(
            new Unit("Nombre", "Descripcion", 6, List.of(new RangedType()), new ArrayList<>()),
            new Unit("Nombre", "Descripcion", 8, List.of(new RangedType()), new ArrayList<>()),
            new Unit("Nombre", "Descripcion", 0, List.of(new RangedType()), new ArrayList<>()),
            new Unit("Nombre", "Descripcion", 2, List.of(new RangedType()), new ArrayList<>()),
            new Unit("Nombre", "Descripcion", 4, List.of(new RangedType()), new ArrayList<>())
        );
        siegeUnits = Arrays.asList(
            new Unit("Nombre", "Descripcion", 3, List.of(new SiegeType()), new ArrayList<>()),
            new Unit("Nombre", "Descripcion", 0, List.of(new SiegeType()), new ArrayList<>()),
            new Unit("Nombre", "Descripcion", 6, List.of(new SiegeType()), new ArrayList<>()),
            new Unit("Nombre", "Descripcion", 4, List.of(new SiegeType()), new ArrayList<>()),
            new Unit("Nombre", "Descripcion", 8, List.of(new SiegeType()), new ArrayList<>())
        );
        cards = new ArrayList<>();
        cards.addAll(closeCombatUnits);
        cards.addAll(rangedUnits);
        cards.addAll(siegeUnits);
    }

    @Test
    void testDiscardPileStartsEmpty() {
        assertEquals(0, discardPile.getCardCount(), "Discard pile should start empty");
    }

    @Test
    void testAddCardToDiscardPile() {
        discardPile.addCard(unit1);
        assertEquals(1, discardPile.getCardCount(), "Discard pile should have one card");
        assertEquals(unit1, discardPile.getLastCard(), "Last card should be the one just added");
    }

    @Test
    void testAddMultipleCardsToDiscardPile() {
        discardPile.addCard(unit1);
        discardPile.addCard(unit2);
        assertEquals(2, discardPile.getCardCount(), "Discard pile should have two cards");
        assertEquals(unit2, discardPile.getLastCard(), "Last card should be the most recently added");
    }

    @Test
    void testGetLastCardFromEmptyDiscardPile() {
        assertThrows(IllegalStateException.class, () -> discardPile.getLastCard(), 
            "Getting last card from empty discard pile should throw exception");
    }

    @Test
    void testUnitPointsResetWhenAddedToDiscardPile() {
        // Modify unit1's points
        unit1.setPoints(10);
        assertEquals(10, unit1.calculatePoints(), "Unit points should be modified");

        // Add unit1 to discard pile
        discardPile.addCard(unit1);

        // Get the card back from discard pile
        Unit discardedUnit = (Unit) discardPile.getLastCard();
        assertEquals(5, discardedUnit.calculatePoints(), "Unit points should be reset to base value");
    }

    @Test
    public void cards_count_go_to_discardPile(){
        int expectedSize = 15;
        Row ranged = new Ranged();
        Row closeCombat = new CloseCombat();
        Row siege = new Siege();

        for (Card card : closeCombatUnits) {
            closeCombat.placeCard(card);
        }
        for (Card card : rangedUnits) {
            ranged.placeCard(card);
        }
        for (Card card : siegeUnits) {
            siege.placeCard(card);
        }

        siege.discardCards(discardPile);
        ranged.discardCards(discardPile);
        closeCombat.discardCards(discardPile);

        int actualSize = discardPile.getCardCount();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void cards_go_to_discardPile(){
        Row ranged = new Ranged();
        Row closeCombat = new CloseCombat();
        Row siege = new Siege();

        for (Card card : cards) {
            if(card.canBePlaced(ranged)) ranged.placeCard(card);
            if (card.canBePlaced(closeCombat)) closeCombat.placeCard(card);
            if (card.canBePlaced(siege)) siege.placeCard(card);
        }

        siege.discardCards(discardPile);
        ranged.discardCards(discardPile);
        closeCombat.discardCards(discardPile);

        assertTrue(cards.containsAll(discardPile.getCards()));
    }
}
