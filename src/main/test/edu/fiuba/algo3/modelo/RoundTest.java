package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoundTest {

    private Player player1;
    private Player player2;
    private Round round;

    @BeforeEach
    public void setUp() {
        player1 = new Player("nombre1", new Deck(), new CloseCombat(), new Ranged(), new Siege());
        player2 = new Player("nombre2", new Deck(), new CloseCombat(), new Ranged(), new Siege());
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
    public void testAssignVictoryIncrementsWinnerRoundCount() {
        Player winner = mock(Player.class);
        Player loser = mock(Player.class);
        Round round = new Round(winner, loser);

        when(winner.calculatePoints()).thenReturn(15);
        when(loser.calculatePoints()).thenReturn(10);


        when(winner.winner(loser)).thenReturn(Optional.of(winner));
        when(loser.winner(winner)).thenReturn(Optional.of(winner));

        round.assignVictory();

        verify(winner).winRound();
        verify(loser, never()).winRound();
    }


    @Test
    public void testAssignVictoryDoesNothingOnDraw() {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);

        when(p1.calculatePoints()).thenReturn(10);
        when(p2.calculatePoints()).thenReturn(10);

        Round round = new Round(p1, p2);

        round.assignVictory();

        verify(p1, never()).winRound();
        verify(p2, never()).winRound();
    }

    @Test
    public void testAssignVictoryDoesNotIncrementRoundsWonOnDraw() {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);

        when(p1.calculatePoints()).thenReturn(10);
        when(p2.calculatePoints()).thenReturn(10);

        Round round = new Round(p1, p2);

        round.assignVictory();

        verify(p1, never()).winRound();
        verify(p2, never()).winRound();
    }

}

