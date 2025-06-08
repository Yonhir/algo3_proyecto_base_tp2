package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    public void testSeReparten10CardsAlPlayer(){
        List<Card> cards = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"
                ));
        Player player = new Player("Andy", 2, new Deck(cards), new Hand(new ArrayList<>()));
        int expectedCards = 10;

        Hand handPlayer = player.getHand();
        Deck deck = player.getDeck();
        handPlayer.getNCardsFromDeck(deck, 10);

        assertEquals(expectedCards, handPlayer.getCardCount());
    }

    @Test
    public void testSeReparten10CardsAlPlayer2() {
        List<Card> cards = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"
                ));
        Player player = new Player("Andy", 2, new Deck(cards), new Hand(new ArrayList<>()));
        int expectedCards = 10;

        player.getNCardsForHand(10);
        Hand handPlayer = player.getHand();

        assertEquals(expectedCards, handPlayer.getCardCount());
    }

}
