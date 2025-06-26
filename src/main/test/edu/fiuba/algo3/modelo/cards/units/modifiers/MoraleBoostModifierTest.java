package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.colors.*;
import edu.fiuba.algo3.modelo.errors.SectionPlayerMismatchError;
import edu.fiuba.algo3.modelo.turnManagement.Player;
import edu.fiuba.algo3.modelo.turnManagement.Round;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.colors.Blue;
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
    private CloseCombat closeCombat2;
    private Ranged ranged2;
    private Siege siege2;
    private MoraleBoostModifier modifierMoral;
    private Unit cardMoraleBoost;
    private Round round;
    private Deck deck;


    @BeforeEach
    void setUp() {
        deck = new Deck();

        DiscardPile discardPile1 = new DiscardPile();
        DiscardPile discardPile2 = new DiscardPile();
        closeCombat = new CloseCombat(discardPile1);
        ranged = new Ranged(discardPile1);
        siege = new Siege(discardPile1);
        closeCombat2 = new CloseCombat(discardPile2);
        ranged2 = new Ranged(discardPile2);
        siege2 = new Siege(discardPile2);
        Player player = new Player("Gabriel", deck, discardPile1, closeCombat, ranged, siege, new Blue());
        Player opponent = new Player("Juan", deck, discardPile2, closeCombat2, ranged2, siege2, new Red());

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
    public void testSeUsaLaCartaMoraleBoostEnLaFilaCloseCombat() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), List.of(modifierMoral));
        cardMoraleBoost.setColor(new Blue());
        int expectedPoints = closeCombat.calculatePoints() + closeCombat.getCards().size() + cardMoraleBoost.calculatePoints();

        closeCombat.placeCard(cardMoraleBoost, round);

        int actualPoints = closeCombat.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testSeUsaLaCartaMoraleBoostEnLaFilaRanged() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new RangedType(), List.of(modifierMoral));
        cardMoraleBoost.setColor(new Blue());
        int expectedPoints = ranged.calculatePoints() + ranged.getCards().size() + cardMoraleBoost.calculatePoints();

        ranged.placeCard(cardMoraleBoost, round);

        int actualPoints = ranged.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testSeUsaLaCartaMoraleBoostEnLaFilaSiege() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new SiegeType(), List.of(modifierMoral));
        cardMoraleBoost.setColor(new Blue());
        int expectedPoints = siege.calculatePoints() + siege.getCards().size() + cardMoraleBoost.calculatePoints();

        siege.placeCard(cardMoraleBoost, round);

        int actualPoints = siege.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testSeUsaLaCartaMoraleBoostEnUnaFilaVacia() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new SiegeType(), List.of(modifierMoral));
        cardMoraleBoost.setColor(new Blue());
        int expectedPoints = cardMoraleBoost.calculatePoints();

        siege.discardCards();
        siege.placeCard(cardMoraleBoost, round);

        int actualPoints = siege.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testLaCartaNoSePuedeJugarEnElSideEnemigoException(){
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new SiegeType(), List.of(modifierMoral));
        cardMoraleBoost.setColor(new Red());

        Assertions.assertThrows(SectionPlayerMismatchError.class, () -> siege.placeCard(cardMoraleBoost, round  ));
    }
}
