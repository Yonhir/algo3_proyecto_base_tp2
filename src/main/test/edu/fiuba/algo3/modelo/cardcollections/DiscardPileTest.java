package edu.fiuba.algo3.modelo.cardcollections;

import edu.fiuba.algo3.modelo.Colors.Blue;
import edu.fiuba.algo3.modelo.Colors.Red;
import edu.fiuba.algo3.modelo.turnManagement.Player;
import edu.fiuba.algo3.modelo.turnManagement.Round;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Row;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class DiscardPileTest {
    private DiscardPile discardPile1;
    private DiscardPile discardPile2;
    private Unit unit1;
    private Unit unit2;
    private Unit unit3;
    private List<Card> closeCombatUnits;
    private List<Card> rangedUnits;
    private List<Card> siegeUnits;
    private List<Card> cards;

    private Player player;
    private Player opponent;
    private Round round;
    private Deck deck;

    private CloseCombat closeCombat1;
    private Ranged ranged1;
    private Siege siege1;

    @BeforeEach
    void setUp() {
        discardPile1 = new DiscardPile();
        discardPile2 = new DiscardPile();
        deck = new Deck();
        closeCombat1 = new CloseCombat(discardPile1);
        ranged1 = new Ranged(discardPile1);
        siege1 = new Siege(discardPile1);
        CloseCombat closeCombat2 = new CloseCombat(discardPile2);
        Ranged ranged2 = new Ranged(discardPile2);
        Siege siege2 = new Siege(discardPile2);
        player = new Player("Gabriel", deck, closeCombat1, ranged1, siege1, new Blue());
        opponent = new Player("Juan", deck, closeCombat2, ranged2, siege2, new Red());
        round = new Round(player, opponent);

        unit1 = new Unit("Unit1", "Description1", 5, List.of(new CloseCombatType()), new ArrayList<>());
        unit2 = new Unit("Unit2", "Description2", 7, List.of(new RangedType()), new ArrayList<>());
        unit3 = new Unit("Nombre", "Descripcion3", 3, List.of(new SiegeType()), new ArrayList<>());
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
    void testAddCardToDiscardPile() {
        discardPile1.addCard(unit1);
        assertEquals(unit1, discardPile1.getLastCard(), "Last card should be the one just added");
    }

    @Test
    void testAddMultipleCardsToDiscardPile() {
        discardPile1.addCard(unit1);
        discardPile1.addCard(unit2);
        assertEquals(unit2, discardPile1.getLastCard(), "Last card should be the most recently added");
    }

    @Test
    void testGetLastCardFromEmptyDiscardPile() {
        assertThrows(IllegalStateException.class, () -> discardPile1.getLastCard(),
            "Getting last card from empty discard pile should throw exception");
    }

    @Test
    void testUnitPointsResetWhenAddedToDiscardPile() {
        // Modify unit1's points
        unit1.setPoints(10);
        assertEquals(10, unit1.calculatePoints(), "Unit points should be modified");

        // Add unit1 to discard pile
        discardPile1.addCard(unit1);

        // Get the card back from discard pile
        Unit discardedUnit = (Unit) discardPile1.getLastCard();
        assertEquals(5, discardedUnit.calculatePoints(), "Unit points should be reset to base value");
    }

    @Test
    void testUnitCardsPointsResetWhenAddedToDiscardPile() {
        // Modify their points
        unit1.setPoints(10);
        unit2.setPoints(8);
        unit3.setPoints(6);

        List<Card> unitCards = Arrays.asList(unit1, unit2, unit3);

        // Add the cards to discard pile
        discardPile1.insertCards(unitCards);

        List<Integer> pointsGotten = Arrays.asList(unit1.calculatePoints(), unit2.calculatePoints(), unit3.calculatePoints());
        List<Integer> pointsExpected = Arrays.asList(5, 7, 3);

        assertEquals(pointsGotten, pointsExpected, "Points should be reset to base value");
    }

    @Test
    public void testCardsCountGoToDiscardPile(){
        int expectedSize = 15;
        DiscardPile discardPile = new DiscardPile();
        Row ranged = new Ranged(discardPile);
        Row closeCombat = new CloseCombat(discardPile);
        Row siege = new Siege(discardPile);

        for (Card card : closeCombatUnits) {
            closeCombat.placeCard(card, round);
        }
        for (Card card : rangedUnits) {
            ranged.placeCard(card, round);
        }
        for (Card card : siegeUnits) {
            siege.placeCard(card, round);
        }

        siege.discardCards();
        ranged.discardCards();
        closeCombat.discardCards();

        int actualSize = discardPile.getCardCount();

        assertEquals(expectedSize, actualSize);
    }
}
