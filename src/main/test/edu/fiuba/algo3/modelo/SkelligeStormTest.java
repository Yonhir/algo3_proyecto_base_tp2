package edu.fiuba.algo3.modelo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SkelligeStormTest {
    private Ranged ranged;
    private Siege siege;

    private Ranged rangedOpponent;
    private Siege siegeOpponent;

    private SpecialZone specialZone;
    @BeforeEach
    void setUp() {
        ranged = new Ranged();
        siege = new Siege();

        ranged.addCard(new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>(), new CommonStrategy()));
        ranged.addCard(new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<>(), new CommonStrategy()));
        ranged.addCard(new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<>(), new CommonStrategy()));

        siege.addCard(new Unit("Nombre", "Descripcion", 2, new SiegeType(), new ArrayList<>(), new CommonStrategy()));
        siege.addCard(new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>(), new CommonStrategy()));
        siege.addCard(new Unit("Nombre", "Descripcion", 1, new SiegeType(), new ArrayList<>(), new CommonStrategy()));

        rangedOpponent = new Ranged();
        siegeOpponent = new Siege();

        rangedOpponent.addCard(new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>(), new CommonStrategy()));
        rangedOpponent.addCard(new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<>(), new CommonStrategy()));
        rangedOpponent.addCard(new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<>(), new CommonStrategy()));

        siegeOpponent.addCard(new Unit("Nombre", "Descripcion", 2, new SiegeType(), new ArrayList<>(), new CommonStrategy()));
        siegeOpponent.addCard(new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>(), new CommonStrategy()));
        siegeOpponent.addCard(new Unit("Nombre", "Descripcion", 1, new SiegeType(), new ArrayList<>(), new CommonStrategy()));

        specialZone = new SpecialZone(null , List.of(ranged, rangedOpponent), List.of(siege, siegeOpponent));
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
