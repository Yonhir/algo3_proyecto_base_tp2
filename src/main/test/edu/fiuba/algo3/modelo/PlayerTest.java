package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerTest {
    private List<Card> cards;
    private Player player;
    private Deck deck;
    private SpecialZone specialZone;
    private CloseCombat closeCombat;
    private Ranged ranged;
    private Siege siege;

    @BeforeEach
    void setUp() {
        // Create unit cards
        List<Card> unitCards = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, new RangedType(), new ArrayList<>())
        );

        // Create special cards (weather cards)
        List<Card> specialCards = Arrays.asList(
                new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto"),
                new ImpenetrableFog("Niebla", "Reduce todas las unidades a distancia a 1 punto"),
                new TorrentialRain("Lluvia", "Reduce todas las unidades de asedio a 1 punto"),
                new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto"),
                new ImpenetrableFog("Niebla", "Reduce todas las unidades a distancia a 1 punto"),
                new TorrentialRain("Lluvia", "Reduce todas las unidades de asedio a 1 punto")
        );

        // Combine all cards
        cards = new ArrayList<>();
        cards.addAll(unitCards);
        cards.addAll(specialCards);
        
        // Initialize game components
        deck = new Deck(cards);
        closeCombat = new CloseCombat();
        ranged = new Ranged();
        siege = new Siege();
        specialZone = new SpecialZone(
            List.of(closeCombat),
            List.of(ranged),
            List.of(siege)
        );
        player = new Player("Gabriel", 2, deck, specialZone, closeCombat, ranged, siege);
    }

    @Test
    public void testPlayCardCloseCombat() {
        //ARRANGE
        int expected_cards = 1;
        int expected_cards_in_hand = 20; // 15 unit cards + 6 special cards - 1 played card
        int expected_points = 4;

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
    public void testPlayCardRanged() {
        //ARRANGE
        int expected_cards = 1;
        int expected_cards_in_hand = 20; // 15 unit cards + 6 special cards - 1 played card
        int expected_points = 6;

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
    public void testPlayCardSiege() {
        //ARRANGE
        int expected_cards = 1;
        int expected_cards_in_hand = 20; // 15 unit cards + 6 special cards - 1 played card
        int expected_points = 3;

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
    public void testPlayCardError() {
        //ARRANGE
        List<Card> hand = player.getHand().getCards();
        hand.addAll(cards);
        Card card_to_play = hand.remove(0);

        //ASSERT + ACT
        Assertions.assertThrows(SectionTypeMismatchError.class, () -> siege.placeCard(card_to_play));
    }

    @Test
    public void testGetDiscardPile() {
        //ARRANGE
        DiscardPile expectedDiscardPile = new DiscardPile();
        
        //ACT
        DiscardPile actualDiscardPile = player.getDiscardPile();
        
        //ASSERT
        Assertions.assertEquals(expectedDiscardPile.getCardCount(), actualDiscardPile.getCardCount());
    }

    @Test
    public void testGetHand() {
        //ARRANGE
        Hand expectedHand = new Hand(new ArrayList<>());
        
        //ACT
        Hand actualHand = player.getHand();
        
        //ASSERT
        Assertions.assertEquals(expectedHand.getCardCount(), actualHand.getCardCount());
    }

    @Test
    public void testCalculatePoints() {
        //ARRANGE
        // Use cards from setUp: card[0] = 4 points (close combat), card[2] = 6 points (ranged), card[3] = 3 points (siege)
        Unit closeCombatUnit = (Unit) cards.get(0);
        Unit rangedUnit = (Unit) cards.get(2);
        Unit siegeUnit = (Unit) cards.get(3);
        int expectedPoints = 13; // 4 + 6 + 3
        closeCombat.placeCard(closeCombatUnit);
        ranged.placeCard(rangedUnit);
        siege.placeCard(siegeUnit);
        
        //ACT
        int actualPoints = player.calculatePoints();
        
        //ASSERT
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testCalculatePointsWithEmptyRows() {
        //ARRANGE
        int expectedPoints = 0;

        //ACT
        int actualPoints = player.calculatePoints();
        
        //ASSERT
        Assertions.assertEquals(expectedPoints, actualPoints);
    }
}
