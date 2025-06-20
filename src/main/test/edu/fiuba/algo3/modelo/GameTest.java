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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;
    SpecialZone specialZone = new SpecialZone(List.of(), List.of(), List.of());

    @BeforeEach
    public void setUp() {
        player1 = new Player("nombre1", new Deck(), new CloseCombat(), new Ranged(), new Siege());
        player2 = new Player("nombre2", new Deck(), new CloseCombat(), new Ranged(), new Siege());
        game = new Game(player1, player2, specialZone);
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

    @Test
    public void testGameEndsInDrawAfterThreeRoundsWithOneWinEach() {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        SpecialZone specialZone = mock(SpecialZone.class);

        when(p1.getRoundsWon()).thenReturn(1);
        when(p2.getRoundsWon()).thenReturn(1);
        when(p1.hasWonGame()).thenReturn(false);
        when(p2.hasWonGame()).thenReturn(false);

        Game game = new Game(p1, p2, specialZone);

        assertTrue(game.isDraw());
    }

}
