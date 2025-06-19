package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        player1 = new Player("nombre1", 100, new Deck(), new SpecialZone(List.of(), List.of(), List.of()), new CloseCombat(), new Ranged(), new Siege());
        player2 = new Player("nombre2", 100, new Deck(), new SpecialZone(List.of(), List.of(), List.of()), new CloseCombat(), new Ranged(), new Siege());
        game = new Game(player1, player2);
    }

    @Test
    public void testPlayersCanPassAndRoundEnds() {
        game.passTurn();
        game.passTurn();
        assertFalse(game.isGameOver());
    }

    @Test
    public void testGameEndsAfterTwoWins() {
        player1.winRound();
        player1.winRound();
        assertTrue(game.isGameOver());
        assertEquals(player1, game.getGameWinner());
    }

    @Test
    public void testClearBoardCallsDiscardAllRows() {
        player1.passRound();
        player2.passRound();
        game.startNewRound();

        assertTrue(player1.getDiscardPile().getCards().isEmpty());
        assertTrue(player2.getDiscardPile().getCards().isEmpty());
    }
}
