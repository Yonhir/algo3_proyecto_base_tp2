package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PlayerTest {
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
    public void play_card_close_combat() {
        //ARRANGE
        int expected_cards = 1;
        int expected_cards_in_hand = 14;
        int expected_points = 4;

        Player player = new Player("Gabriel", 2);
        Row closeCombat = new CloseCombat();

        List<Card> hand = player.getHand().getCards();
        hand.addAll(cards);

        //ACT
        Card card_to_play = hand.remove(0);

        closeCombat.placeCard(card_to_play);

        List<Card> fila = closeCombat.getCards();

        int actual_points = ((Unit) fila.get(0)).calculatePoints();
        int actual_cards_in_hand = hand.size();
        int actual_cards = fila.size();

        //ASSERT
        Assertions.assertEquals(expected_cards_in_hand, actual_cards_in_hand);
        Assertions.assertEquals(expected_points, actual_points);
        Assertions.assertEquals(expected_cards, actual_cards);
        Assertions.assertFalse(hand.contains(card_to_play));
        Assertions.assertTrue(fila.contains(card_to_play));
    }

    @Test
    public void play_card_ranged() {
        //ARRANGE
        int expected_cards = 1;
        int expected_cards_in_hand = 14;
        int expected_points = 6;

        Player player = new Player("Gabriel", 2);
        Row ranged = new Ranged();

        List<Card> hand = player.getHand().getCards();
        hand.addAll(cards);

        //ACT
        Card card_to_play = hand.remove(2);

        ranged.placeCard(card_to_play);

        List<Card> fila = ranged.getCards();

        int actual_points = ((Unit) fila.get(0)).calculatePoints();
        int actual_cards_in_hand = hand.size();
        int actual_cards = fila.size();

        //ASSERT
        Assertions.assertEquals(expected_cards_in_hand, actual_cards_in_hand);
        Assertions.assertEquals(expected_points, actual_points);
        Assertions.assertEquals(expected_cards, actual_cards);
        Assertions.assertFalse(hand.contains(card_to_play));
        Assertions.assertTrue(fila.contains(card_to_play));
    }

    @Test
    public void play_card_siege() {
        //ARRANGE
        int expected_cards = 1;
        int expected_cards_in_hand = 14;
        int expected_points = 3;

        Player player = new Player("Gabriel", 2);
        Row siege = new Siege();

        List<Card> hand = player.getHand().getCards();
        hand.addAll(cards);

        //ACT
        Card card_to_play = hand.remove(3);

        siege.placeCard(card_to_play);

        List<Card> fila = siege.getCards();

        int actual_points = ((Unit) fila.get(0)).calculatePoints();
        int actual_cards_in_hand = hand.size();
        int actual_cards = fila.size();

        //ASSERT
        Assertions.assertEquals(expected_cards_in_hand, actual_cards_in_hand);
        Assertions.assertEquals(expected_points, actual_points);
        Assertions.assertEquals(expected_cards, actual_cards);
        Assertions.assertFalse(hand.contains(card_to_play));
        Assertions.assertTrue(fila.contains(card_to_play));
    }

    @Test
    public void play_card_error() {

        Player player = new Player("Gabriel", 2);
        Row siege = new Siege();

        List<Card> hand = player.getHand().getCards();
        hand.addAll(cards);

        //ACT
        Card card_to_play = hand.remove(0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {siege.placeCard(card_to_play);});
    }


}
