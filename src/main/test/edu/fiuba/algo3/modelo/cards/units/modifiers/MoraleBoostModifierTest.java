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
    private CloseCombat closeCombat1;
    private Ranged ranged1;
    private Siege siege1;
    private CloseCombat closeCombat2;
    private Ranged ranged2;
    private Siege siege2;
    private MoraleBoostModifier modifierMoral;
    private Unit cardMoraleBoost;
    private Player player;
    private Player opponent;
    private Round round;
    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
        DiscardPile discardPile1 = new DiscardPile();
        DiscardPile discardPile2 = new DiscardPile();
        closeCombat1 = new CloseCombat(discardPile1);
        ranged1 = new Ranged(discardPile1);
        siege1 = new Siege(discardPile1);
        closeCombat2 = new CloseCombat(discardPile2);
        ranged2 = new Ranged(discardPile2);
        siege2 = new Siege(discardPile2);
        player = new Player("Gabriel", deck, discardPile1, closeCombat1, ranged1, siege1, new Blue());
        opponent = new Player("Juan", deck, discardPile2, closeCombat2, ranged2, siege2, new Red());
        round = new Round(player, opponent);

        modifierMoral = new MoraleBoostModifier();

        closeCombat1.addCard(new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>()));
        closeCombat1.addCard(new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>()));
        closeCombat1.addCard(new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<>()));

        ranged1.addCard(new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>()));
        ranged1.addCard(new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<>()));
        ranged1.addCard(new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<>()));

        siege1.addCard(new Unit("Nombre", "Descripcion", 2, new SiegeType(), new ArrayList<>()));
        siege1.addCard(new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>()));
        siege1.addCard(new Unit("Nombre", "Descripcion", 1, new SiegeType(), new ArrayList<>()));
    }

    @Test
    public void testSeJuegaUnaCartaConModificadorMoraleBoostEnLaFilaCloseCombat() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), List.of(modifierMoral));
        int cartasEnLaFila = 3;
        int expectedPoints = closeCombat1.calculatePoints() + cartasEnLaFila + cardMoraleBoost.calculatePoints();

        closeCombat1.placeCard(cardMoraleBoost, round);

        int actualPoints = closeCombat1.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testSeJuegaUnaCartaConModificadorMoraleBoostEnLaFilaRanged() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new RangedType(), List.of(modifierMoral));
        int cartasEnLaFila = 3;
        int expectedPoints = ranged1.calculatePoints() + cartasEnLaFila + cardMoraleBoost.calculatePoints();

        ranged1.placeCard(cardMoraleBoost, round);

        int actualPoints = ranged1.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testSeJuegaUnaCartaConModificadorMoraleBoostEnLaFilaSiege() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new SiegeType(), List.of(modifierMoral));
        int cartasEnLaFila = 3;
        int expectedPoints = siege1.calculatePoints() + cartasEnLaFila + cardMoraleBoost.calculatePoints();

        siege1.placeCard(cardMoraleBoost, round);

        int actualPoints = siege1.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testLaCartaConModificadorMoraleBoostNoAfectaSusPropiosPuntos() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new SiegeType(), List.of(modifierMoral));
        int expectedPoints = cardMoraleBoost.calculatePoints();

        siege1.discardCards();
        siege1.placeCard(cardMoraleBoost, round);

        int actualPoints = siege1.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }
}
