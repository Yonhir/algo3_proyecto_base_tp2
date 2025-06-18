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

    private Card siegeCard;
    private Card rangedCard;
    private Card closeCombatCard;

    @BeforeEach
    void setUp() {
        siegeCard = new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>());
        rangedCard = new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>());
        closeCombatCard = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>());

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
        Hand hand = player.getHand();
        deck.getCards().remove(siegeCard);
        deck.getCards().remove(closeCombatCard);
        deck.getCards().remove(rangedCard);

        hand.addCards(Arrays.asList(siegeCard, closeCombatCard, rangedCard));
        hand.getNCardsFromDeck(deck, 7);
    }

    @Test
    public void countCardsInHandAfterPlayingCard() {
        int expected_cards = player.getHand().getCardCount() - 1;

        player.playCard(siegeCard, siege);

        int actual_cards = player.getHand().getCardCount();

        Assertions.assertEquals(expected_cards, actual_cards);
    }

    @Test
    public void countCardsInRowAfterPlayingCard() {
        int expected_cards = siege.getCards().size() + 1;

        player.playCard(siegeCard, siege);

        int actual_cards = siege.getCards().size();

        Assertions.assertEquals(expected_cards, actual_cards);
    }

    @Test
    public void CardNotInHandAfterPlayingCard() {
        player.playCard(siegeCard, siege);

        Assertions.assertFalse(player.getHand().getCards().contains(siegeCard));
    }

    @Test
    public void CardInRowAfterPlayingCard() {
        player.playCard(siegeCard, siege);

        Assertions.assertTrue(siege.getCards().contains(siegeCard));
    }

    @Test
    public void PointsInRowAfterPlayingCard() {
        int expected_points = ((Unit) siegeCard).calculatePoints();

        player.playCard(siegeCard, siege);

        int actual_points = ((Unit) siege.getCards().get(0)).calculatePoints();

        Assertions.assertEquals(expected_points, actual_points);
    }

    @Test
    public void PointsAfterPlayingSomeCards() {
        int expected_points = ((Unit) siegeCard).calculatePoints() +
                              ((Unit) closeCombatCard).calculatePoints() +
                              ((Unit) rangedCard).calculatePoints();

        player.playCard(siegeCard, siege);
        player.playCard(closeCombatCard, closeCombat);
        player.playCard(rangedCard, ranged);

        int actual_points = player.calculatePoints();

        Assertions.assertEquals(expected_points, actual_points);

    }

    @Test
    public void RowsAfterPlayingSomeCards() {
        player.playCard(siegeCard, siege);
        player.playCard(closeCombatCard, closeCombat);
        player.playCard(rangedCard, ranged);

        Assertions.assertTrue(
                siege.getCards().contains(siegeCard) &&
                closeCombat.getCards().contains(closeCombatCard) &&
                ranged.getCards().contains(rangedCard)
        );
    }

    @Test
    public void HandAfterPlayingSomeCards() {
        player.playCard(siegeCard, siege);
        player.playCard(closeCombatCard, closeCombat);
        player.playCard(rangedCard, ranged);

        Assertions.assertFalse(player.getHand().getCards().containsAll(Arrays.asList(siegeCard, closeCombatCard, rangedCard)));
    }

    //Falta un test error

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
    public void testCalculatePointsWithEmptyRows() {
        //ARRANGE
        int expectedPoints = 0;

        //ACT
        int actualPoints = player.calculatePoints();
        
        //ASSERT
        Assertions.assertEquals(expectedPoints, actualPoints);
    }
}
