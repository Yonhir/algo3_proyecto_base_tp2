package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoundTest {

    private Player player1;
    private Player player2;
    private Round round;

    @BeforeEach
    public void setUp() {
        player1 = new Player("nombre1", new Deck(), new SpecialZone(List.of(), List.of(), List.of()), new CloseCombat(), new Ranged(), new Siege());
        player2 = new Player("nombre2", new Deck(), new SpecialZone(List.of(), List.of(), List.of()), new CloseCombat(), new Ranged(), new Siege());
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
    @Test
    public void testRoundIsDrawWhenPlayersHaveSamePoints() {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        when(p1.calculatePoints()).thenReturn(10);
        when(p2.calculatePoints()).thenReturn(10);

        Round round = new Round(p1, p2);

        assertTrue(round.isDraw());
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

    @Test
    public void testAssignVictoryGivesRoundToWinner() {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Round round = new Round(p1, p2);

        when(p1.calculatePoints()).thenReturn(15);
        when(p2.calculatePoints()).thenReturn(10);

        when(p1.hasMoreOrEqualPointsThan(p2)).thenCallRealMethod();


        round.assignVictory();

        verify(p1, times(1)).winRound();
        verify(p2, never()).winRound();
    }


}

