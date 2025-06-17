package edu.fiuba.algo3.modelo;

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

        closeCombat.addCard(new Unit("Nombre", "Descripcion", 4, true, false, false, new ArrayList<>()));
        closeCombat.addCard(new Unit("Nombre", "Descripcion", 4, true, false, false, new ArrayList<>()));
        closeCombat.addCard(new Unit("Nombre", "Descripcion", 5, true, false, false, new ArrayList<>()));

        ranged.addCard(new Unit("Nombre", "Descripcion", 6, false, true, false, new ArrayList<>()));
        ranged.addCard(new Unit("Nombre", "Descripcion", 8, false, true, false, new ArrayList<>()));
        ranged.addCard(new Unit("Nombre", "Descripcion", 0, false, true, false, new ArrayList<>()));

        siege.addCard(new Unit("Nombre", "Descripcion", 2, false, false, true, new ArrayList<>()));
        siege.addCard(new Unit("Nombre", "Descripcion", 3, false, false, true, new ArrayList<>()));
        siege.addCard(new Unit("Nombre", "Descripcion", 1, false, false, true, new ArrayList<>()));
    }

    @Test
    public void moral_boost_closeCombat() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, true, false, false, List.of(modifierMoral));
        int expectedPoints = closeCombat.calculatePoints() + closeCombat.getCards().size() + cardMoraleBoost.calculatePoints();

        closeCombat.placeCard(cardMoraleBoost);

        int actualPoints = closeCombat.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void moral_boost_ranged() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, false, true, false, List.of(modifierMoral));
        int expectedPoints = ranged.calculatePoints() + ranged.getCards().size() + cardMoraleBoost.calculatePoints();

        ranged.placeCard(cardMoraleBoost);

        int actualPoints = ranged.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void moral_boost_siege() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, false, false, true, List.of(modifierMoral));
        int expectedPoints = siege.calculatePoints() + siege.getCards().size() + cardMoraleBoost.calculatePoints();

        siege.placeCard(cardMoraleBoost);

        int actualPoints = siege.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void moral_boost_empty_row() {
        cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, false, false, true, List.of(modifierMoral));
        int expectedPoints = cardMoraleBoost.calculatePoints();

        siege.discardCards(new DiscardPile());
        siege.placeCard(cardMoraleBoost);

        int actualPoints = siege.calculatePoints();
        Assertions.assertEquals(expectedPoints, actualPoints);
    }
}
