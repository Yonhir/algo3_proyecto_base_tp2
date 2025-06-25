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
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MoraleBoostModifierTest {
    private CloseCombat closeCombat;
    private Ranged ranged;
    private Siege siege;
    private MoraleBoostModifier modifierMoral;
    private Unit cardMoraleBoost;
    private Player player;
    private Player opponent;
    private Round round;
    private Deck deck;


    @BeforeEach
    void setUp() {
        deck = new Deck();
        closeCombat = new CloseCombat();
        ranged = new Ranged();
        siege = new Siege();
        player = new Player("Gabriel", deck, closeCombat, ranged, siege, new Blue());
        opponent = new Player("Juan", deck, closeCombat, ranged, siege, new Red());
        round = new Round(player, opponent);

        modifierMoral = new MoraleBoostModifier();

        closeCombat.addCard(new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>()));
        closeCombat.addCard(new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>()));
        closeCombat.addCard(new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<>()));

        ranged.addCard(new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>()));
        ranged.addCard(new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<>()));
        ranged.addCard(new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<>()));

        siege.addCard(new Unit("Nombre", "Descripcion", 2, new SiegeType(), new ArrayList<>()));
        siege.addCard(new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>()));
        siege.addCard(new Unit("Nombre", "Descripcion", 1, new SiegeType(), new ArrayList<>()));
    }

    @Test
    public void moral_boost_closeCombat() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), List.of(modifierMoral));
        int expectedPoints = closeCombat.calculatePoints() + closeCombat.getCardCount() + cardMoraleBoost.calculatePoints();

        closeCombat.placeCard(cardMoraleBoost, round);

        int actualPoints = closeCombat.calculatePoints();

        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void moral_boost_ranged() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new RangedType(), List.of(modifierMoral));
        int expectedPoints = ranged.calculatePoints() + ranged.getCardCount() + cardMoraleBoost.calculatePoints();

        ranged.placeCard(cardMoraleBoost, round);

        int actualPoints = ranged.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void moral_boost_siege() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new SiegeType(), List.of(modifierMoral));
        int expectedPoints = siege.calculatePoints() + siege.getCardCount() + cardMoraleBoost.calculatePoints();

        siege.placeCard(cardMoraleBoost, round);

        int actualPoints = siege.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void moral_boost_empty_row() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new SiegeType(), List.of(modifierMoral));
        int expectedPoints = cardMoraleBoost.calculatePoints();

        siege.discardCards(new DiscardPile());
        siege.placeCard(cardMoraleBoost, round);

        int actualPoints = siege.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }
}
