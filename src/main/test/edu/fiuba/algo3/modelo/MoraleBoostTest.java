package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MoraleBoostTest {
    private CloseCombat closeCombat;
    private Ranged ranged;
    private Siege siege;

    private MoraleBoost moraleBoost;


    @BeforeEach
    void setUp() {
        closeCombat = new CloseCombat();
        ranged = new Ranged();
        siege = new Siege();

        moraleBoost = new MoraleBoost("MoraleBoost", "X2", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));

        closeCombat.addCard(new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>(), new CommonStrategy()));
        closeCombat.addCard(new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>(), new CommonStrategy()));
        closeCombat.addCard(new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<>(), new CommonStrategy()));

        ranged.addCard(new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>(), new CommonStrategy()));
        ranged.addCard(new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<>(), new CommonStrategy()));
        ranged.addCard(new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<>(), new CommonStrategy()));

        siege.addCard(new Unit("Nombre", "Descripcion", 2, new SiegeType(), new ArrayList<>(), new CommonStrategy()));
        siege.addCard(new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>(), new CommonStrategy()));
        siege.addCard(new Unit("Nombre", "Descripcion", 1, new SiegeType(), new ArrayList<>(), new CommonStrategy()));
    }

    @Test
    public void use_moralBost_in_closeCombat() {
        int expectedPoints = closeCombat.calculatePoints() * 2;

        moraleBoost.play(closeCombat);

        int actualPoints = closeCombat.calculatePoints();

        Assertions.assertEquals(expectedPoints, actualPoints);

    }

    @Test
    public void use_moralBost_in_ranged() {
        int expectedPoints = ranged.calculatePoints() * 2;

        moraleBoost.play(ranged);

        int actualPoints = ranged.calculatePoints();

        Assertions.assertEquals(expectedPoints, actualPoints);

    }

    @Test
    public void use_moralBost_in_siege() {
        int expectedPoints = siege.calculatePoints() * 2;

        moraleBoost.play(siege);

        int actualPoints = siege.calculatePoints();

        Assertions.assertEquals(expectedPoints, actualPoints);

    }

    @Test
    public void use_moralBost_in_empty_row() {
        int expectedPoints = 0;

        siege.discardCards(new DiscardPile());

        moraleBoost.play(siege);

        int actualPoints = siege.calculatePoints();

        Assertions.assertEquals(expectedPoints, actualPoints);
    }
}
