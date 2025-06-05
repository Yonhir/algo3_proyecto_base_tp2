package edu.fiuba.algo3.entrega_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.CloseCombat;
import edu.fiuba.algo3.Deck;
import edu.fiuba.algo3.DiscardPile;
import edu.fiuba.algo3.Hand;
import edu.fiuba.algo3.Player;
import edu.fiuba.algo3.Ranged;
import edu.fiuba.algo3.Row;
import edu.fiuba.algo3.Side;
import edu.fiuba.algo3.Siege;
import edu.fiuba.algo3.TightBond;
import edu.fiuba.algo3.Unit;
import edu.fiuba.algo3.Game;
import edu.fiuba.algo3.Card;

public class TightBondTest {

    @Test
    public void testTightBondModifierAppliedToOneCard() {
        // Arrange
        String cardName = "TestCard";
        TightBond tightBond = new TightBond(cardName);
        int expectedPoints = 10;
        Unit unit = new Unit(cardName, "Test Description", 5, new CloseCombat(), List.of(tightBond));

        // Act
        tightBond.applyOnOwnerCard(unit);

        // Assert
        assertEquals(expectedPoints, unit.getPoints(), "The strength should be doubled due to TightBondModifier.");
    }

    @Test
    public void testTightBondModifierNotAppliedToOneCard() {
        // Arrange
        String cardName = "TestCard";
        TightBond tightBond = new TightBond(cardName);
        int expectedPoints = 5;
        Unit unit = new Unit("OtherCard", "Test Description", 5, new CloseCombat(), new ArrayList<>());

        // Act
        tightBond.applyOnOwnerCard(unit);

        // Assert
        assertEquals(expectedPoints, unit.getPoints(), "The strength should not be doubled due to TightBondModifier.");
    }

    @Test
    public void testTightBondModifierAppliedToRowWithTwoCards() {
        // Arrange
        String cardName = "TestCard";
        TightBond tightBond = new TightBond(cardName);
        int expectedPoints = 20;
        Unit unit = new Unit(cardName, "Test Description", 5, new CloseCombat(), List.of(tightBond));
        Unit unit2 = new Unit(cardName, "Test Description", 5, new CloseCombat(), List.of(tightBond));
        Row row = new Row(new CloseCombat());
        row.placeCard(unit);
        row.placeCard(unit2);

        // Act
        tightBond.applyOnOwnerRow(row, new CloseCombat());

        // Assert
        assertEquals(expectedPoints, row.calculateTotalPoints(), "The strength should be doubled due to TightBondModifier.");
    }

    @Test
    public void testTightBondModifierAppliedToRowWithThreeCards() {
        // Arrange
        String cardName = "TestCard";
        TightBond tightBond = new TightBond(cardName);
        int expectedPoints = 30;
        Unit unit = new Unit(cardName, "Test Description", 5, new Ranged(), List.of(tightBond));
        Unit unit2 = new Unit(cardName, "Test Description", 5, new Ranged(), List.of(tightBond));
        Unit unit3 = new Unit(cardName, "Test Description", 5, new Ranged(), List.of(tightBond));
        Row row = new Row(new Ranged());
        row.placeCard(unit);
        row.placeCard(unit2);
        row.placeCard(unit3);

        // Act
        row.applyEffectOnOwner(tightBond, new Ranged());

        // Assert
        assertEquals(expectedPoints, row.calculateTotalPoints(), "The strength should be tripled due to TightBondModifier.");
    }

    @Test
    public void testTightBondModifierAppliedToSideWithThreeCards() {
        // Arrange
        String cardName = "TestCard";
        TightBond tightBond = new TightBond(cardName);
        int expectedPoints = 30;
        Unit unit = new Unit(cardName, "Test Description", 5, new Siege(), List.of(tightBond));
        Unit unit2 = new Unit(cardName, "Test Description", 5, new Siege(), List.of(tightBond));
        Unit unit3 = new Unit(cardName, "Test Description", 5, new Siege(), List.of(tightBond));
        Side side = new Side();
        side.placeCard(unit, new Siege());
        side.placeCard(unit2, new Siege());
        side.placeCard(unit3, new Siege());

        // Act
        side.applyEffectOnOwner(tightBond, new Siege());

        // Assert
        assertEquals(expectedPoints, side.calculateTotalPoints(), "The strength should be tripled due to TightBondModifier.");
    }

    @Test
    public void testTightBondModifierAppliedToPlayerWithTwoCards() {
        // Arrange
        String cardName = "TestCard";
        TightBond tightBond = new TightBond(cardName);
        int expectedPoints = 20;
        Unit unit = new Unit(cardName, "Test Description", 5, new CloseCombat(), List.of(tightBond));
        Unit unit2 = new Unit(cardName, "Test Description", 5, new CloseCombat(), List.of(tightBond));
        Player player = new Player(
            "TestPlayer", 10,
            new Hand(List.of(unit, unit2)),
            new DiscardPile(new ArrayList<>()),
            new Deck(new ArrayList<>()),
            new Side()
        );
        player.playCard(unit, new CloseCombat());
        player.playCard(unit2, new CloseCombat());

        // Act
        player.applyEffectOnOwner(tightBond, new CloseCombat());

        // Assert
        assertEquals(expectedPoints, player.calculatePoints(), "The strength should be doubled due to TightBondModifier.");
    }

    @Test
    @Disabled("Pending check for random assing of player turn")
    public void testTightBondModifierAppliedToGameWithTwoPlayers() {
        // Arrange
        String cardName = "TestCard";
        TightBond tightBond = new TightBond(cardName);
        int expectedPoints = 20; // 5 points * 2 cards * 2 (tight bond multiplier)

        // Act: ----------------------------------------------------------------------------
        
        // Create decks with TightBond cards
        List<Card> player1Cards = new ArrayList<>();
        List<Card> player2Cards = new ArrayList<>();
        
        // Create 10 TightBond cards for each player
        for (int i = 0; i < 10; i++) {
            Unit unit1 = new Unit(cardName, "Test Description", 5, new CloseCombat(), List.of(tightBond));
            player1Cards.add(unit1);
            Unit unit2 = new Unit(cardName, "Test Description", 5, new CloseCombat(), new ArrayList<>());
            player2Cards.add(unit2);
        }

        Deck player1Deck = new Deck(player1Cards);
        Deck player2Deck = new Deck(player2Cards);

        // Register players
        Game game = Game.getInstance();
        game.registerPlayer("Player1", player1Deck);
        game.registerPlayer("Player2", player2Deck);

        // Confirm hands
        game.confirmHand();
        game.confirmHand();

        // Round 1: Player 1 plays 1 card, Player 2 plays 1 card, Player 1 plays 1 card, Player 2 pass round Player 1 pass.
        List<Card> hand = game.getCurrentPlayerHand();
        Card card = hand.get(0);
        game.playCardFromHand(card, card.getPlayableRowTypes().get(0));
        hand = game.getCurrentPlayerHand();
        card = hand.get(0);
        game.playCardFromHand(card, card.getPlayableRowTypes().get(0));
        hand = game.getCurrentPlayerHand();
        card = hand.get(0);
        game.playCardFromHand(card, card.getPlayableRowTypes().get(0));
        game.playerPassRound();
        game.playerPassRound();

        // Round 2: Player 1 plays 1 card, Player 2 plays 1 card, Player 1 plays 1 card, Player 2 pass round Player 1 pass.
        hand = game.getCurrentPlayerHand();
        card = hand.get(0);
        game.playCardFromHand(card, card.getPlayableRowTypes().get(0));
        hand = game.getCurrentPlayerHand();
        card = hand.get(0);
        game.playCardFromHand(card, card.getPlayableRowTypes().get(0));
        hand = game.getCurrentPlayerHand();
        card = hand.get(0);
        game.playCardFromHand(card, card.getPlayableRowTypes().get(0));
        game.playerPassRound();
        game.playerPassRound();

        // Get results
        Map<String, Object> results = game.getRoundsResults();
        Map<Integer, Map<String, Object>> rounds = (Map<Integer, Map<String, Object>>) results.get("rounds");

        // Verify first round results
        Map<String, Object> firstRound = rounds.get(1);
        int player1Points = (int) firstRound.get("player1Points");
        int player2Points = (int) firstRound.get("player2Points");

        // Player 1 should have 20 points (2 cards * 5 points * 2 tight bond multiplier)
        assertEquals(expectedPoints, player1Points, "Player 1 should have doubled points due to TightBond");
        // Player 2 should have 5 points (1 card * 5 points)
        assertEquals(5, player2Points, "Player 2 should have normal points");

        // Verify second round results
        Map<String, Object> secondRound = rounds.get(2);
        player1Points = (int) secondRound.get("player1Points");
        player2Points = (int) secondRound.get("player2Points");

        // Player 2 should have 20 points (2 cards * 5 points * 2 tight bond multiplier)
        assertEquals(expectedPoints, player2Points, "Player 2 should have doubled points due to TightBond");
        // Player 1 should have 5 points (1 card * 5 points)
        assertEquals(5, player1Points, "Player 1 should have normal points");

        // Reset game for next test
        game.resetGame();
    }

}
