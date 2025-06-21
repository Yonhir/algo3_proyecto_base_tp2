package edu.fiuba.algo3.modelo.cards.specials.weathers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.*;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;

import java.util.ArrayList;

public class SkelligeStormTest {
    private Ranged ranged;
    private Siege siege;

    private Ranged rangedOpponent;
    private Siege siegeOpponent;

    private SpecialZone specialZone;
    @BeforeEach
    void setUp() {
        CloseCombat closeCombatOpponent = new CloseCombat();
        rangedOpponent = new Ranged();
        siegeOpponent = new Siege();

        CloseCombat closeCombat = new CloseCombat();
        ranged = new Ranged();
        siege = new Siege();

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

        specialZone = new SpecialZone(closeCombat, ranged, siege, closeCombatOpponent, rangedOpponent, siegeOpponent);
    }

    @Test
    public void skelligeStormOwnerSideTest(){
        Weather skelligeStorm = new SkelligeStorm("Skellige Storm", "Reduce todas las unidades a distancia y de asedio a 1 punto");
        int expectedPointsSAndR = siege.getCards().size() + ranged.getCards().size();

        skelligeStorm.play(specialZone);

        int actualPointsSAndR = siege.calculatePoints() + ranged.calculatePoints();
        Assertions.assertEquals(expectedPointsSAndR, actualPointsSAndR);
    }

    @Test
    public void skelligeStormOpponentSideTest(){
        Weather skelligeStorm = new SkelligeStorm("Skellige Storm", "Reduce todas las unidades a distancia y de asedio a 1 punto");
        int expectedPointsSAndR = siegeOpponent.getCards().size() + rangedOpponent.getCards().size();

        skelligeStorm.play(specialZone);

        int actualPointsSAndR = siegeOpponent.calculatePoints() + rangedOpponent.calculatePoints();
        Assertions.assertEquals(expectedPointsSAndR, actualPointsSAndR);
    }

    @Test
    public void skelligeStormEmptySideTest(){
        siegeOpponent.discardCards(new DiscardPile());
        rangedOpponent.discardCards(new DiscardPile());
        Weather skelligeStorm = new SkelligeStorm("Skellige Storm", "Reduce todas las unidades a distancia y de asedio a 1 punto");
        int expectedPointsSAndR = siegeOpponent.getCards().size() + rangedOpponent.getCards().size();

        skelligeStorm.play(specialZone);

        int actualPointsSAndR = siegeOpponent.calculatePoints() + rangedOpponent.calculatePoints();
        Assertions.assertEquals(expectedPointsSAndR, actualPointsSAndR);
    }
}
