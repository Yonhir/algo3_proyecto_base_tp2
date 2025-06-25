package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.colors.*;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.specials.MoraleBoost;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.errors.SectionPlayerMismatchError;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Row;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
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
    private Unit carta_espia;
    private Hand hand;
    private Deck deck;

    private final PlayerColor red = new Red();
    private final PlayerColor blue = new Blue();

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
        RangedRowOpponent = new Ranged();
        RangedRowOpponent.setColor(red);

        RangedRowOwner = new Ranged();
        RangedRowOwner.setColor(blue);

        deck = new Deck();
        deck.insertCards(cards);
        deck.setColorToCards(blue);

        hand = new Hand();

        carta_espia = new Unit("Nombre", "Descripcion", 4, r, List.of(new Spy(deck, hand)));

        carta_espia.setColor(blue);
    }
    @Test
    public void play_card_in_my_row() {
        Assertions.assertThrows(SectionPlayerMismatchError.class, () -> RangedRowOwner.placeCard(carta_espia));
    }

    @Test
    public void hand_get_2_cards_from_deck_play_opponent() {
        int expectedCardsInHand = 2;

        RangedRowOpponent.placeCard(carta_espia);

        Assertions.assertEquals(expectedCardsInHand, hand.getCardCount());
    }

    @Test
    public void deck_lose_2_cards_play_opponent() {
        int expectedCardsInHand = deck.getCardCount() - 2;

        RangedRowOpponent.placeCard(carta_espia);

        Assertions.assertEquals(expectedCardsInHand, deck.getCardCount());
    }
}
