package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.Colors.Blue;
import edu.fiuba.algo3.modelo.Colors.Red;
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
    private TightBond tightBondModifier1;
    private TightBond tightBondModifier2;
    private TightBond tightBondModifier3;
    private Player player;
    private Player opponent;
    private Round round;
    private Deck deck;

    private CloseCombat closeCombat1;
    private Ranged ranged1;
    private Siege siege1;
    private CloseCombat closeCombat2;
    private Ranged ranged2;
    private Siege siege2;


    @BeforeEach
    void setUp() {
        discardPile = new DiscardPile();
        closeCombat1 = new CloseCombat(discardPile);
        ranged1 = new Ranged(discardPile);
        siege1 = new Siege(discardPile);
        closeCombat2 = new CloseCombat(discardPile);
        ranged2 = new Ranged(discardPile);
        siege2 = new Siege(discardPile);
        deck = new Deck();
        player = new Player("Gabriel", deck, closeCombat1, ranged1, siege1, new Blue());
        opponent = new Player("Juan", deck, closeCombat2, ranged2, siege2, new Red());
        round = new Round(player, opponent);

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
    }

    @Test
    void testSeJuegaUnaCartaCatapultaEnLaFilaSiegeCorrectamente() {
        // Arrange
        int expectedPoints = 8;
        
        // Act
        siege1.placeCard(catapult1, round);

        // Assert
        assertEquals(expectedPoints, siege1.calculatePoints(), "First catapult should have base points");
    }

    @Test
    void testSeJueganTresCartasConModificadorTightBondEnLaFilaSiegeLosPuntosSeTriplicanCorrectamente() {
        // Arrange
        int expectedPoints = 72;

        // Act
        siege1.placeCard(catapult1, round);
        siege1.placeCard(catapult2, round);
        siege1.placeCard(catapult3, round);
        int pointsAfterThird = siege1.calculatePoints();

        // Assert
        assertEquals(expectedPoints, pointsAfterThird, "Three catapults should triple each other's points (8*3*3)");
    }

    @Test
    void testSeJueganDosCartasConModificadorTightBondSeDescartanLasCartasYAlJugarUnaCartaUnitSinModificadorEstaNoEsAfectada() {
        // Arrange
        int expectedPoints = 6;

        // Act
        siege1.placeCard(catapult1, round);
        siege1.placeCard(catapult2, round);
        // End of round
        siege1.discardCards();
        siege1.placeCard(regularUnit, round);
        int pointsAfterNewRound = siege1.calculatePoints();

        // Assert
        assertEquals(expectedPoints, pointsAfterNewRound, "Points should be recalculated correctly in new round");
    }

    @Test
    void testSeJuegaUnaCartaUnidadSinModificadorYCartasConModificadorTightBondLaUnidadComunNoEsAfectada() {
        // Arrange
        int expectedPoints = 38;

        // Act
        siege1.placeCard(regularUnit, round);
        siege1.placeCard(catapult1, round);
        siege1.placeCard(catapult2, round);
        int finalRowPoints = siege1.calculatePoints();

        // Assert
        assertEquals(expectedPoints, finalRowPoints, "Total points should be 38 ((8*2)*2 for TightBond units) + 6 (regular unit)");
    }
}
