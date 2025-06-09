package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class DiscardPileTest {
    private DiscardPile discardPile;
    private Unit unit1;
    private Unit unit2;

    @BeforeEach
    void setUp() {
        discardPile = new DiscardPile();
        unit1 = new Unit("Unit1", "Description1", 5, true, false, false, new ArrayList<>());
        unit2 = new Unit("Unit2", "Description2", 7, false, true, false, new ArrayList<>());
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
} 