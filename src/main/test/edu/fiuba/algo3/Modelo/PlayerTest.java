package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    public void testSeReparten10CardsAlaHand(){
        List<Card> cards = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, true, false, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, true, false, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, false, true, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, false, false, true, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, true, false, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, false, false, true, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, false, false, true, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, false, true, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, false, true, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, true, false, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, false, true, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, false, false, true, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, false, false, true, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, true, false, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, false, true, false, new ArrayList<Modifier>()),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"
                ));
        Deck deck = new Deck(cards);
        Hand hand = new Hand(new ArrayList<>());
        int expectedCards = 10;

        hand.getNCardsFromDeck(deck,10);
        int resultObtained = hand.getCardCount();

        assertEquals(expectedCards, resultObtained);
    }
}
