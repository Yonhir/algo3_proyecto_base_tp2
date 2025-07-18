package edu.fiuba.algo3.models.cards.specials.weathers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.*;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SiegeType;

import java.util.ArrayList;

public class SkelligeStormTest {
    private Ranged ranged;
    private Siege siege;

    private Ranged rangedOpponent;
    private Siege siegeOpponent;

    private SpecialZone specialZone;
    private DiscardPile discardPile1;
    private DiscardPile discardPile2;
    @BeforeEach
    void setUp() {
        discardPile1 = new DiscardPile();
        discardPile2 = new DiscardPile();
        CloseCombat closeCombatOpponent = new CloseCombat(discardPile2);
        rangedOpponent = new Ranged(discardPile2);
        siegeOpponent = new Siege(discardPile2);

        CloseCombat closeCombat = new CloseCombat(discardPile1);
        ranged = new Ranged(discardPile1);
        siege = new Siege(discardPile1);

        ranged.addCard(new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>()));
        ranged.addCard(new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<>()));
        ranged.addCard(new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<>()));

        siege.addCard(new Unit("Nombre", "Descripcion", 2, new SiegeType(), new ArrayList<>()));
        siege.addCard(new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>()));
        siege.addCard(new Unit("Nombre", "Descripcion", 1, new SiegeType(), new ArrayList<>()));

        rangedOpponent.addCard(new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>()));
        rangedOpponent.addCard(new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<>()));
        rangedOpponent.addCard(new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<>()));

        siegeOpponent.addCard(new Unit("Nombre", "Descripcion", 2, new SiegeType(), new ArrayList<>()));
        siegeOpponent.addCard(new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>()));
        siegeOpponent.addCard(new Unit("Nombre", "Descripcion", 1, new SiegeType(), new ArrayList<>()));

        specialZone = new SpecialZone(closeCombat, ranged, siege, closeCombatOpponent, rangedOpponent, siegeOpponent, discardPile1, discardPile2);
    }

    @Test
    public void skelligeStormOwnerSideTest(){
        Weather skelligeStorm = new SkelligeStorm("Skellige Storm", "Reduce todas las unidades a distancia y de asedio a 1 punto");
        int expectedPointsSAndR = siege.getCardCount() + ranged.getCardCount();

        skelligeStorm.play(specialZone);

        int actualPointsSAndR = siege.calculatePoints() + ranged.calculatePoints();
        assertEquals(expectedPointsSAndR, actualPointsSAndR);
    }

    @Test
    public void skelligeStormOpponentSideTest(){
        Weather skelligeStorm = new SkelligeStorm("Skellige Storm", "Reduce todas las unidades a distancia y de asedio a 1 punto");
        int expectedPointsSAndR = siegeOpponent.getCardCount() + rangedOpponent.getCardCount();

        skelligeStorm.play(specialZone);

        int actualPointsSAndR = siegeOpponent.calculatePoints() + rangedOpponent.calculatePoints();
        assertEquals(expectedPointsSAndR, actualPointsSAndR);
    }

    @Test
    public void skelligeStormEmptySideTest(){
        siegeOpponent.discardCards();
        rangedOpponent.discardCards();
        Weather skelligeStorm = new SkelligeStorm("Skellige Storm", "Reduce todas las unidades a distancia y de asedio a 1 punto");
        int expectedPointsSAndR = siegeOpponent.getCardCount() + rangedOpponent.getCardCount();

        skelligeStorm.play(specialZone);

        int actualPointsSAndR = siegeOpponent.calculatePoints() + rangedOpponent.calculatePoints();
        assertEquals(expectedPointsSAndR, actualPointsSAndR);
    }
}
