package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {

    private Player player1;
    private Player player2;
    private Round round;

    @BeforeEach
    public void setUp() {
        player1 = new Player("nombre1", 100, new Deck(), new SpecialZone(List.of(), List.of(), List.of()), new CloseCombat(), new Ranged(), new Siege());
        player2 = new Player("nombre2", 100, new Deck(), new SpecialZone(List.of(), List.of(), List.of()), new CloseCombat(), new Ranged(), new Siege());
        round = new Round(player1, player2);
    }

    @Test
    public void testCurrentPlayerSwitchesWhenOpponentHasNotPassed() {
        Player first = round.getCurrentPlayer();
        round.endTurn();
        assertNotSame(first, round.getCurrentPlayer());
    }

    @Test
    public void testCurrentPlayerDoesNotSwitchIfOpponentHasPassed() {
        round.passTurn();
        Player current = round.getCurrentPlayer();
        round.endTurn();
        assertSame(current, round.getCurrentPlayer());
    }

    @Test
    public void testRoundEndsWhenBothPlayersPass() {
        round.passTurn();
        round.passTurn();
        assertTrue(round.isOver());
    }

    @Test
    public void testAssignVictoryIncrementsPlayerWinCount() {
        round.getWinner().winRound();
        assertEquals(1, round.getWinner().getRoundsWon());
    }
}

