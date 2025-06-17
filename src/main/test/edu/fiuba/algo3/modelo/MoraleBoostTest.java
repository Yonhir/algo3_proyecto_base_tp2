package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MoraleBoostTest {
    private CloseCombat closeCombat;
    private Ranged ranged;
    private Siege siege;
    private CloseCombat closeCombatOpponent;

    private MoraleBoost moraleBoost;


    @BeforeEach
    void setUp() {
        closeCombat = new CloseCombat();
        ranged = new Ranged();
        siege = new Siege();

        closeCombatOpponent = new CloseCombat();

        moraleBoost = new MoraleBoost(closeCombat, ranged, siege);

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

    @Test
    public void use_moralBost_in_opponent_row() {
        Assertions.assertThrows(CardPlaceInOpponentPlaceException.class, () -> {
            moraleBoost.play(closeCombatOpponent);
        });
    }
}
