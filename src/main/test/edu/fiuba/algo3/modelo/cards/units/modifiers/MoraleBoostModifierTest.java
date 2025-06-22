package edu.fiuba.algo3.modelo.cards.units.modifiers;

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


    @BeforeEach
    void setUp() {
        closeCombat = new CloseCombat();
        ranged = new Ranged();
        siege = new Siege();

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
        int expectedPoints = closeCombat.calculatePoints() + closeCombat.getCards().size() + cardMoraleBoost.calculatePoints();

        closeCombat.placeCard(cardMoraleBoost);

        int actualPoints = closeCombat.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void moral_boost_ranged() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new RangedType(), List.of(modifierMoral));
        int expectedPoints = ranged.calculatePoints() + ranged.getCards().size() + cardMoraleBoost.calculatePoints();

        ranged.placeCard(cardMoraleBoost);

        int actualPoints = ranged.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void moral_boost_siege() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new SiegeType(), List.of(modifierMoral));
        int expectedPoints = siege.calculatePoints() + siege.getCards().size() + cardMoraleBoost.calculatePoints();

        siege.placeCard(cardMoraleBoost);

        int actualPoints = siege.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void moral_boost_empty_row() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new SiegeType(), List.of(modifierMoral));
        int expectedPoints = cardMoraleBoost.calculatePoints();

        siege.discardCards(new DiscardPile());
        siege.placeCard(cardMoraleBoost);

        int actualPoints = siege.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }
}
