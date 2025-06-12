package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpyTest {
    private Row RangedRowOpponent;
    private Row RangedRowOwner;

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
    public void play_card_in_my_row() {
        Deck deck = new Deck(cards);
        Hand hand = new Hand(new ArrayList<>());
        RangedRowOpponent = new Ranged();
        RangedRowOwner = new Ranged();
        Unit carta_espia = new Unit("Nombre", "Descripcion", 4, false, true, false, List.of(new Spy(deck, hand, RangedRowOwner)));

        RangedRowOwner.placeCard(carta_espia);

        Assertions.assertTrue(RangedRowOwner.getCards().contains(carta_espia));

    }

    @Test
    public void hand_get_2_cards_from_deck_play_opponent() {
        Deck deck = new Deck(cards);
        Hand hand = new Hand(new ArrayList<>());
        RangedRowOpponent = new Ranged();
        RangedRowOwner = new Ranged();
        Unit carta_espia = new Unit("Nombre", "Descripcion", 4, false, true, false, List.of(new Spy(deck, hand, RangedRowOwner)));
        int expectedCardsInHand = 2;

        RangedRowOpponent.placeCard(carta_espia);

        Assertions.assertEquals(expectedCardsInHand, hand.getCardCount());
    }

    @Test
    public void deck_lose_2_cards_play_opponent() {
        Deck deck = new Deck(cards);
        Hand hand = new Hand(new ArrayList<>());
        RangedRowOpponent = new Ranged();
        RangedRowOwner = new Ranged();
        Unit carta_espia = new Unit("Nombre", "Descripcion", 4, false, true, false, List.of(new Spy(deck, hand, RangedRowOwner)));
        int expectedCardsInHand = deck.getCardCount() - 2;

        RangedRowOpponent.placeCard(carta_espia);

        Assertions.assertEquals(expectedCardsInHand, deck.getCardCount());
    }

    @Test
    public void not_steals_cards_from_deck_if_play_in_my_row() {
        Deck deck = new Deck(cards);
        Hand hand = new Hand(new ArrayList<>());
        RangedRowOpponent = new Ranged();
        RangedRowOwner = new Ranged();
        Unit carta_espia = new Unit("Nombre", "Descripcion", 4, false, true, false, List.of(new Spy(deck, hand, RangedRowOwner)));
        int expectedCardsInHand = 0;

        RangedRowOwner.placeCard(carta_espia);

        Assertions.assertEquals(expectedCardsInHand, hand.getCardCount());
    }

    @Test
    public void not_lose_cards_from_deck_if_play_in_my_row() {
        Deck deck = new Deck(cards);
        Hand hand = new Hand(new ArrayList<>());
        RangedRowOpponent = new Ranged();
        RangedRowOwner = new Ranged();
        Unit carta_espia = new Unit("Nombre", "Descripcion", 4, false, true, false, List.of(new Spy(deck, hand, RangedRowOwner)));
        int expectedCardsInDeck = deck.getCardCount();

        RangedRowOwner.placeCard(carta_espia);

        Assertions.assertEquals(expectedCardsInDeck, deck.getCardCount());
    }
}
