package edu.fiuba.algo3.entrega_1;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.Card;
import edu.fiuba.algo3.CloseCombat;
import edu.fiuba.algo3.Deck;
import edu.fiuba.algo3.DeckBuilder;
import edu.fiuba.algo3.Game;

public class GameTest {

    private Game game;
    private DeckBuilder deckBuilder;
    private Deck deck1;
    private Deck deck2;

    @BeforeEach
    void setUp() {
        game = Game.getInstance();
        deckBuilder = game.getDeckBuilder();
        for (Card card : deckBuilder.getCards()) {
            deckBuilder.selectCard(card);
        }
        deck1 = (Deck) deckBuilder.buildDeck();
        deckBuilder.reset();
        for (Card card : deckBuilder.getCards()) {
            deckBuilder.selectCard(card);
        }
        deck2 = (Deck) deckBuilder.buildDeck();
    }

    @AfterEach
    void tearDown() {
        game.resetGame();
    }

    @Test
    void testSingletonInstance() {
        Game game2 = Game.getInstance();
        assertSame(game, game2, "Game should maintain singleton instance");
    }

    @Test
    void testRegisterPlayers() {
        game.registerPlayer("Player1", deck1);
        game.registerPlayer("Player2", deck2);
        
        assertThrows(IllegalStateException.class, () -> {
            game.registerPlayer("Player3", deck1);
        }, "Cannot register player during preparation phase");
    }

    @Test
    void testPreparationPhase() {
        game.registerPlayer("Player1", deck1);
        game.registerPlayer("Player2", deck2);
        
        game.confirmHand();
        game.confirmHand();

        List<Card> hand = game.getCurrentPlayerHand();
        assertNotNull(hand, "Hand should not be null after preparation phase");
    }

    @Test
    void testDrawAndDiscard() {
        game.registerPlayer("Player1", deck1);
        game.registerPlayer("Player2", deck2);

        List<Card> initialHand = game.getCurrentPlayerHand();
        assertFalse(initialHand.isEmpty(), "Initial hand should not be empty");
        
        Card cardToDiscard = initialHand.get(0);
        game.drawAndDiscard(cardToDiscard);
        List<Card> newHand = game.getCurrentPlayerHand();
        
        assertFalse(newHand.contains(cardToDiscard), "Discarded card should not be in hand");
    }

    @Test
    void testConfirmHand() {
        game.registerPlayer("Player1", deck1);
        game.registerPlayer("Player2", deck2);

        game.confirmHand();
        game.confirmHand();

        // After confirming hand, it should be player2's turn
        List<Card> player2Hand = game.getCurrentPlayerHand();
        assertNotNull(player2Hand, "Should be able to get player2's hand after turn change");
    }

    @Test
    void testStartGame() {
        game.registerPlayer("Player1", deck1);
        game.registerPlayer("Player2", deck2);
        
        game.confirmHand();
        game.confirmHand();

        // After starting game, we should be able to get current player's hand
        List<Card> hand = game.getCurrentPlayerHand();
        assertNotNull(hand, "Should be able to get hand after game starts");
    }

    @Test
    void testPlayCardAndPassRound() {
        game.registerPlayer("Player1", deck1);
        game.registerPlayer("Player2", deck2);
        
        game.confirmHand();
        game.confirmHand();

        List<Card> hand = game.getCurrentPlayerHand();
        if (!hand.isEmpty()) {
            Card cardToPlay = hand.get(0);
            game.playCardFromHand(cardToPlay, new CloseCombat());
        }
        
        game.playerPassRound();
        // After passing round, we should be able to get the next player's hand
        List<Card> nextPlayerHand = game.getCurrentPlayerHand();
        assertNotNull(nextPlayerHand, "Should be able to get next player's hand after passing round");
    }

    @Test
    void testGetRoundsResults() {
        game.registerPlayer("Player1", deck1);
        game.registerPlayer("Player2", deck2);
        
        game.confirmHand();
        game.confirmHand();

        // Round 1
        List<Card> hand = game.getCurrentPlayerHand();
        Card cardToPlay = hand.get(0);
        game.playCardFromHand(cardToPlay, cardToPlay.getPlayableRowTypes().get(0));
        hand = game.getCurrentPlayerHand();
        cardToPlay = hand.get(0);
        game.playCardFromHand(cardToPlay, cardToPlay.getPlayableRowTypes().get(0));
        game.playerPassRound();
        game.playerPassRound();

        // Round 2
        hand = game.getCurrentPlayerHand();
        cardToPlay = hand.get(0);
        game.playCardFromHand(cardToPlay, cardToPlay.getPlayableRowTypes().get(0));
        game.playerPassRound();

        // Round 3
        hand = game.getCurrentPlayerHand();
        cardToPlay = hand.get(0);
        game.playCardFromHand(cardToPlay, cardToPlay.getPlayableRowTypes().get(0));
        game.playerPassRound();

        Map<String, Object> results = game.getRoundsResults();
        assertNotNull(results, "Results should not be null");
        
        // Verify rounds data exists
        Map<Integer, Map<String, Object>> rounds = (Map<Integer, Map<String, Object>>) results.get("rounds");
        assertNotNull(rounds, "Rounds data should not be null");
        assertFalse(rounds.isEmpty(), "Rounds data should not be empty");

        // Verify first round data
        Map<String, Object> firstRound = rounds.get(1);
        assertNotNull(firstRound, "First round data should not be null");
        assertTrue(firstRound.containsKey("player1Points"), "First round should have player1Points");
        assertTrue(firstRound.containsKey("player2Points"), "First round should have player2Points");
        assertTrue(firstRound.containsKey("player1Name"), "First round should have player1Name");
        assertTrue(firstRound.containsKey("player2Name"), "First round should have player2Name");
        assertTrue(firstRound.containsKey("winner"), "First round should have winner");
        
        // Verify game winner
        String gameWinner = (String) results.get("gameWinner");
        assertNotNull(gameWinner, "Game winner should not be null");
        assertTrue(gameWinner.equals("Player1") || gameWinner.equals("Player2") || gameWinner.equals("Tie"),
                  "Game winner should be one of the players or Tie");
    }
}
