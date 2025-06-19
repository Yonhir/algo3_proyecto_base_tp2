package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.specials.MoraleBoost;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpyTest {
    private Row RangedRowOpponent;
    private Row RangedRowOwner;
    private CloseCombatType cct ;
    private RangedType r ;
    private SiegeType s ;

    private List<Card> cards;
    @BeforeEach
    void setUp(){
        cct = new CloseCombatType();
        r = new RangedType();
        s = new SiegeType();
        cards = new ArrayList<>(Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, cct, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 5, cct, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, r, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, s, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, cct, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, s, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, s, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, r, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, r, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 10, cct, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, r, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, s, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, s, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, cct, new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, r, new ArrayList<>()),
                new MoraleBoost("Nombre", "Descripcion", List.of(r)),
                new MoraleBoost("Nombre", "Descripcion", List.of(r)),
                new MoraleBoost("Nombre", "Descripcion", List.of(r)),
                new MoraleBoost("Nombre", "Descripcion", List.of(r)),
                new MoraleBoost("Nombre", "Descripcion", List.of(r)),
                new MoraleBoost("Nombre", "Descripcion", List.of(r))));
    }
    @Test
    public void play_card_in_my_row() {
        Deck deck = new Deck();
        deck.insertCards(cards);
        Hand hand = new Hand();
        RangedRowOpponent = new Ranged();
        RangedRowOwner = new Ranged();
        Unit carta_espia = new Unit("Nombre", "Descripcion", 4, r, List.of(new Spy(deck, hand, RangedRowOwner)));

        RangedRowOwner.placeCard(carta_espia);

        Assertions.assertTrue(RangedRowOwner.getCards().contains(carta_espia));

    }

    @Test
    public void hand_get_2_cards_from_deck_play_opponent() {
        Deck deck = new Deck();
        deck.insertCards(cards);
        Hand hand = new Hand();
        RangedRowOpponent = new Ranged();
        RangedRowOwner = new Ranged();
        Unit carta_espia = new Unit("Nombre", "Descripcion", 4, r, List.of(new Spy(deck, hand, RangedRowOwner)));
        int expectedCardsInHand = 2;

        RangedRowOpponent.placeCard(carta_espia);

        Assertions.assertEquals(expectedCardsInHand, hand.getCardCount());
    }

    @Test
    public void deck_lose_2_cards_play_opponent() {
        Deck deck = new Deck();
        deck.insertCards(cards);
        Hand hand = new Hand();
        RangedRowOpponent = new Ranged();
        RangedRowOwner = new Ranged();
        Unit carta_espia = new Unit("Nombre", "Descripcion", 4, r, List.of(new Spy(deck, hand, RangedRowOwner)));
        int expectedCardsInHand = deck.getCardCount() - 2;

        RangedRowOpponent.placeCard(carta_espia);

        Assertions.assertEquals(expectedCardsInHand, deck.getCardCount());
    }

    @Test
    public void not_steals_cards_from_deck_if_play_in_my_row() {
        Deck deck = new Deck();
        deck.insertCards(cards);
        Hand hand = new Hand();
        RangedRowOpponent = new Ranged();
        RangedRowOwner = new Ranged();
        Unit carta_espia = new Unit("Nombre", "Descripcion", 4, r, List.of(new Spy(deck, hand, RangedRowOwner)));
        int expectedCardsInHand = 0;

        RangedRowOwner.placeCard(carta_espia);

        Assertions.assertEquals(expectedCardsInHand, hand.getCardCount());
    }

    @Test
    public void not_lose_cards_from_deck_if_play_in_my_row() {
        Deck deck = new Deck();
        deck.insertCards(cards);
        Hand hand = new Hand();
        RangedRowOpponent = new Ranged();
        RangedRowOwner = new Ranged();
        Unit carta_espia = new Unit("Nombre", "Descripcion", 4, r, List.of(new Spy(deck, hand, RangedRowOwner)));
        int expectedCardsInDeck = deck.getCardCount();

        RangedRowOwner.placeCard(carta_espia);

        Assertions.assertEquals(expectedCardsInDeck, deck.getCardCount());
    }
}
