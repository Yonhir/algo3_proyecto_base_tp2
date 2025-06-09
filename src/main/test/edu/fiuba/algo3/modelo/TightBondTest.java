package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TightBondTest {
    private DiscardPile discardPile;
    private Siege siegeRow;
    private Unit catapult1;
    private Unit catapult2;
    private Unit catapult3;
    private TightBond tightBondModifier1;
    private TightBond tightBondModifier2;
    private TightBond tightBondModifier3;

    @BeforeEach
    void setUp() {
        discardPile = new DiscardPile();
        siegeRow = new Siege();
        
        // Create three different TightBond modifiers
        tightBondModifier1 = new TightBond();
        tightBondModifier2 = new TightBond();
        tightBondModifier3 = new TightBond();

        // Create three identical catapult units with base points of 8
        List<Modifier> modifiers1 = new ArrayList<>();
        modifiers1.add(tightBondModifier1);

        List<Modifier> modifiers2 = new ArrayList<>();
        modifiers2.add(tightBondModifier2);

        List<Modifier> modifiers3 = new ArrayList<>();
        modifiers3.add(tightBondModifier3);

        catapult1 = new Unit(
            "Catapult", "Siege unit", 8,
            false, false, true,
            modifiers1
        );
        catapult2 = new Unit(
            "Catapult", "Siege unit", 8,
            false, false, true,
            modifiers2
        );
        catapult3 = new Unit(
            "Catapult", "Siege unit", 8,
            false, false, true,
            modifiers3
        );
    }

    @Test
    void testPlay1CatapultTightBondPointsCalculation() {
        // Place first catapult
        siegeRow.placeCard(catapult1);
        assertEquals(8, siegeRow.calculatePoints(), "First catapult should have base points");
    }

    @Test
    void testPlay3CatapultsTightBondPointsCalculation() {
        // Place first catapult
        siegeRow.placeCard(catapult1);
        assertEquals(8, siegeRow.calculatePoints(), "First catapult should have base points");

        // Place second catapult
        siegeRow.placeCard(catapult2);
        assertEquals(32, siegeRow.calculatePoints(), "Two catapults should double each other's points (8*2*2)");

        // Place third catapult
        siegeRow.placeCard(catapult3);
        assertEquals(96, siegeRow.calculatePoints(), "Three catapults should triple each other's points (8*2*2*3)");
    }

    @Test
    void testPlay2CatapultsTightBondAndPointsResetAfterRound() {
        // Place two catapults
        siegeRow.placeCard(catapult1);
        siegeRow.placeCard(catapult2);
        assertEquals(32, siegeRow.calculatePoints(), "Two catapults should double each other's points");

        // End round by discarding cards
        siegeRow.discardCards(discardPile);

        // Place the same cards again
        siegeRow.placeCard(catapult1);
        siegeRow.placeCard(catapult2);
        assertEquals(32, siegeRow.calculatePoints(), "Points should be recalculated correctly in new round");
    }
}
