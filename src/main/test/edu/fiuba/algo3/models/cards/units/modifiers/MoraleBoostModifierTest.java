package edu.fiuba.algo3.models.cards.units.modifiers;

import edu.fiuba.algo3.models.colors.*;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.colors.Blue;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SiegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoraleBoostModifierTest {
    private CloseCombat closeCombat;
    private Ranged ranged;
    private Siege siege;
    private MoraleBoostModifier modifierMoral;
    private Unit cardMoraleBoost;
    private Round round;


    @BeforeEach
    void setUp() {
        Player player = new Player("Gabriel", new Blue());
        Player opponent = new Player("Juan", new Red());

        closeCombat = player.getCloseCombatRow();
        ranged = player.getRangedRow();
        siege = player.getSiegeRow();

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
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testSeUsaLaCartaMoraleBoostEnLaFilaRanged() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new RangedType(), List.of(modifierMoral));
        cardMoraleBoost.setColor(new Blue());
        int expectedPoints = ranged.calculatePoints() + ranged.getCards().size() + cardMoraleBoost.calculatePoints();

        ranged.placeCard(cardMoraleBoost, round);

        int actualPoints = ranged.calculatePoints();
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testSeUsaLaCartaMoraleBoostEnLaFilaSiege() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new SiegeType(), List.of(modifierMoral));
        cardMoraleBoost.setColor(new Blue());
        int expectedPoints = siege.calculatePoints() + siege.getCards().size() + cardMoraleBoost.calculatePoints();

        siege.placeCard(cardMoraleBoost, round);

        int actualPoints = siege.calculatePoints();
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testSeUsaLaCartaMoraleBoostEnUnaFilaVacia() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new SiegeType(), List.of(modifierMoral));
        cardMoraleBoost.setColor(new Blue());
        int expectedPoints = cardMoraleBoost.calculatePoints();

        siege.discardCards();
        siege.placeCard(cardMoraleBoost, round);

        int actualPoints = siege.calculatePoints();
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testGetDescription() {
        String expectedDescription = "Impulso de Moral: Aumenta en 1 punto a todas las demás unidades de la fila";
        MoraleBoostModifier moraleBoostModifier = new MoraleBoostModifier();

        assertEquals(expectedDescription, moraleBoostModifier.getDescription());
    }
}
