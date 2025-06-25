package edu.fiuba.algo3.modelo.turnManagement;

import edu.fiuba.algo3.modelo.Colors.Blue;
import edu.fiuba.algo3.modelo.Colors.Red;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        DiscardPile discardPile1 = new DiscardPile();
        DiscardPile discardPile2 = new DiscardPile();
        player1 = new Player("nombre1", new Deck(), discardPile1, new CloseCombat(discardPile1), new Ranged(discardPile1), new Siege(discardPile1), new Blue());
        player2 = new Player("nombre2", new Deck(), discardPile2, new CloseCombat(discardPile2), new Ranged(discardPile2), new Siege(discardPile2), new Red());
        game = new Game(player1, player2);
    }

    @Test
    public void testPlayersCanPassAndRoundEnds() {
        game.passRound();
        game.passRound();
        assertFalse(game.gameFinished());
    }

    @Test
    public void testGameEndsAfterTwoWins() {
        player1.winRound();
        player1.winRound();
        assertTrue(game.gameFinished());
        assertEquals(player1, game.gameWinner());
    }

    @Test
    public void testClearBoardCallsDiscardAllRows() {
        Player mock1 = mock(Player.class);
        Player mock2 = mock(Player.class);

        when(mock1.getRoundsWon()).thenReturn(0);
        when(mock2.getRoundsWon()).thenReturn(0);

        Game game = new Game(mock1, mock2);

        game.passRound();
        game.passRound();

        verify(mock1).discardAllRows();
        verify(mock2).discardAllRows();
    }

    @Test
    public void testGameEndsInDrawAfterThreeRoundsWithOneWinEach() {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);

        when(p1.getRoundsWon()).thenReturn(1);
        when(p2.getRoundsWon()).thenReturn(1);
        when(p1.hasWonGame()).thenReturn(false);
        when(p2.hasWonGame()).thenReturn(false);

        Game game = new Game(p1, p2);

        assertTrue(game.bothPlayersWonARound());
    }
}
