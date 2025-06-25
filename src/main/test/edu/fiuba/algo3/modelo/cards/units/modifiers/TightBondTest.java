package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.colors.*;
import edu.fiuba.algo3.modelo.turnManagement.Player;
import edu.fiuba.algo3.modelo.turnManagement.Round;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TightBondTest {
    private DiscardPile discardPile;
    private Unit catapult1;
    private Unit catapult2;
    private Unit catapult3;
    private Unit regularUnit;
    private Siege siegeRow;
    private Round round;

    @BeforeEach
    void setUp() {
        discardPile = new DiscardPile();
        CloseCombat closeCombat = new CloseCombat();
        Ranged ranged = new Ranged();
        siegeRow = new Siege();
        Deck deck = new Deck();
        Player player = new Player("Gabriel", deck, closeCombat, ranged, siegeRow, new Blue());
        Player opponent = new Player("Juan", new Deck(), new CloseCombat(), new Ranged(), new Siege(), new Red());
        round = new Round(player, opponent);

        // Create three different TightBond modifiers
        TightBond tightBondModifier1 = new TightBond();
        TightBond tightBondModifier2 = new TightBond();
        TightBond tightBondModifier3 = new TightBond();

        // Create three identical catapult units with base points of 8
        List<Modifier> modifiers1 = new ArrayList<>();
        modifiers1.add(tightBondModifier1);

        List<Modifier> modifiers2 = new ArrayList<>();
        modifiers2.add(tightBondModifier2);

        List<Modifier> modifiers3 = new ArrayList<>();
        modifiers3.add(tightBondModifier3);

        catapult1 = new Unit(
            "Catapult", "Siege unit", 8,
            new SiegeType(),
            modifiers1
        );
        catapult2 = new Unit(
            "Catapult", "Siege unit", 8,
            new SiegeType(),
            modifiers2
        );
        catapult3 = new Unit(
            "Catapult", "Siege unit", 8,
            new SiegeType(),
            modifiers3
        );

        // Create a regular unit without TightBond modifier
        regularUnit = new Unit(
            "Regular Unit", "Regular unit", 6,
            new SiegeType(),
            new ArrayList<>()
        );

        catapult1.setColor(new Blue());
        catapult2.setColor(new Blue());
        catapult3.setColor(new Blue());
        regularUnit.setColor(new Blue());
    }

    @Test
    void testPlay1CatapultTightBondPointsCalculation() {
        // Arrange
        int expectedPoints = 8;
        
        // Act
        siegeRow.placeCard(catapult1, round);

        // Assert
        assertEquals(expectedPoints, siegeRow.calculatePoints(), "First catapult should have base points");
    }

    @Test
    void testPlay3CatapultsTightBondPointsCalculation() {
        // Arrange
        int expectedPoints = 72;

        // Act
        siegeRow.placeCard(catapult1, round);
        siegeRow.placeCard(catapult2, round);
        siegeRow.placeCard(catapult3, round);
        int pointsAfterThird = siegeRow.calculatePoints();

        // Assert
        assertEquals(expectedPoints, pointsAfterThird, "Three catapults should triple each other's points (8*3*3)");
    }

    @Test
    void testPlay2CatapultsTightBondAndPointsResetAfterRound() {
        // Arrange
        int expectedPoints = 6;

        // Act
        siegeRow.placeCard(catapult1, round);
        siegeRow.placeCard(catapult2, round);
        // End of round
        siegeRow.discardCards(discardPile);
        siegeRow.placeCard(regularUnit, round);
        int pointsAfterNewRound = siegeRow.calculatePoints();

        // Assert
        assertEquals(expectedPoints, pointsAfterNewRound, "Points should be recalculated correctly in new round");
    }

    @Test
    void testRegularUnitNotAffectedByTightBond() {
        // Arrange
        int expectedPoints = 38;

        // Act
        siegeRow.placeCard(regularUnit, round);
        siegeRow.placeCard(catapult1, round);
        siegeRow.placeCard(catapult2, round);
        int finalRowPoints = siegeRow.calculatePoints();

        // Assert
        assertEquals(expectedPoints, finalRowPoints, "Total points should be 38 ((8*2)*2 for TightBond units) + 6 (regular unit)");
    }
}
