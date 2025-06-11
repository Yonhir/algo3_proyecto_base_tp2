package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HandTest {

    private List<Card> cards;
    @BeforeEach
    void setUp(){
        cards = new ArrayList<>(Arrays.asList(
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
                new Scorch("Nombre", "Descripcion")));
    }

    @Test
    public void testSePideUnaCartaDelDeckALaHandCorrectamente(){
        Deck deck = new Deck(cards);
        Hand hand = new Hand(new ArrayList<>());
        int expectedCards = 1;

        hand.getNCardsFromDeck(deck,1);
        int resultObtained = hand.getCardCount();

        assertEquals(expectedCards, resultObtained);
    }
    @Test
    public void testSeReparten10CardsAlaHand(){
        Deck deck = new Deck(cards);
        Hand hand = new Hand(new ArrayList<>());
        int expectedCards = 10;

        hand.getNCardsFromDeck(deck,10);
        int resultObtained = hand.getCardCount();

        assertEquals(expectedCards, resultObtained);
    }

    @Test
    public void testNoSePuedeObtenerCartasSiElDeckEstaVacio(){
        Deck deck = new Deck(cards);
        Hand hand = new Hand(new ArrayList<>());

        for(Card card: new ArrayList<>(cards)){
            deck.retrieveCard(card);
        }

        assertThrows(NotEnoughtCardsInDeckError.class, () -> {
            hand.getNCardsFromDeck(deck,10);;
        });
    }

    @Test
    public void testNoSePuedePedirMasCartasDeLasQueTieneElDeck(){
        Deck deck = new Deck(new ArrayList<>(cards));
        Hand hand = new Hand(new ArrayList<>());

        for (int i = 0; i < 15; i++) {
            Card card = cards.get(i);
            deck.retrieveCard(card);
        }

        assertThrows(NotEnoughtCardsInDeckError.class, () -> {
            hand.getNCardsFromDeck(deck,10);;
        });
    }
}
