package edu.fiuba.algo3.modelo.turnManagement;

import edu.fiuba.algo3.modelo.Colors.Blue;
import edu.fiuba.algo3.modelo.Colors.Red;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoundTest {

    private Player player1;
    private Player player2;
    private Round round;
    private Game game;
    private Unit unidad;
    private CloseCombat closeCombat;
    private Ranged ranged;
    private Siege siege;

    @BeforeEach
    public void setUp() {
        DiscardPile discardPile = new DiscardPile();
        DiscardPile otherDiscardPile = new DiscardPile();
        closeCombat = new CloseCombat(discardPile);
        ranged = new Ranged(discardPile);
        siege = new Siege(discardPile);
        player1 = new Player("nombre1", new Deck(), discardPile, closeCombat, ranged, siege, new Blue());
        player2 = new Player("nombre2", new Deck(), otherDiscardPile, new CloseCombat(otherDiscardPile), new Ranged(otherDiscardPile), new Siege(otherDiscardPile), new Red());
        unidad = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>());
        round = new Round(player1, player2);
        game = new Game(player1, player2);
    }

    @Test
    public void testCurrentPlayerSwitchesWhenOpponentHasNotPassed() {
        Player first = round.getCurrentPlayer();
        round.playerPlayedCard();
        assertNotSame(first, round.getCurrentPlayer());
    }

    @Test
    public void testCurrentPlayerDoesNotSwitchIfOpponentHasPassed() {
        round.passTurn(game);
        Player current = round.getCurrentPlayer();
        round.passTurn(game);
        assertSame(current, round.getCurrentPlayer());
    }

    @Test
    public void testRoundEndsWhenBothPlayersPass() {
        round.passTurn(game);
        round.passTurn(game);

        assertTrue(round.getState() instanceof BothPassedState);
    }

    @Test
    public void testAssignVictoryIncrementsWinnerRoundCount() {
        player1.getHand().addCard(unidad);
        player1.playCard(unidad, closeCombat , round);

        round.assignVictory();

        assertEquals(1, player1.getRoundsWon());
        assertEquals(0, player2.getRoundsWon());
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

    @Test
    public void testPlayerSwitchesIfOpponentHasNotPassed() {
        Round round = new Round(player1, player2);
        Player first = round.getCurrentPlayer();

        round.playerPlayedCard();

        assertNotSame(first, round.getCurrentPlayer());
    }
}

