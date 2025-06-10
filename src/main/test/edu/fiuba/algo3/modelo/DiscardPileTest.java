package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiscardPileTest {
    private List<Card> cards;

    @BeforeEach
    void setUp() {
        cards = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, true, false, false, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 5, true, false, false, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, false, true, false, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, false, false, true, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, true, false, false, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, false, false, true, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, false, false, true, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, false, true, false, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, false, true, false, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 10, true, false, false, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, false, true, false, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, false, false, true, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, false, false, true, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, true, false, false, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, false, true, false, new ArrayList<>())
        );
    }

    @Test
    public void cards_go_to_discardPile(){
        int expectedSize = 15;

        Player player = new Player("Player1", 3);
        DiscardPile discardPile = player.getDiscardPile();

        Row ranged = new Ranged();
        Row closeCombat = new CloseCombat();
        Row siege = new Siege();

        for (Card card : cards) {
            if(card.canBePlaced(ranged)) ranged.placeCard(card);
            if (card.canBePlaced(closeCombat)) closeCombat.placeCard(card);
            if (card.canBePlaced(siege)) siege.placeCard(card);
        }

        siege.discardCards(discardPile);
        ranged.discardCards(discardPile);
        closeCombat.discardCards(discardPile);

        int actualSize = discardPile.size();

        Assertions.assertEquals(expectedSize, actualSize);
        Assertions.assertTrue(cards.containsAll(discardPile.getCards()));
    }
}
