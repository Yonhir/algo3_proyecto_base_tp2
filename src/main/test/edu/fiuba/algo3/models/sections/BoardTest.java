package edu.fiuba.algo3.models.sections;

import edu.fiuba.algo3.models.cardcollections.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class BoardTest {

    @Test
    public void testBoardContieneUnaManoInicialParaElJugadorActual() {

        Board board = new Board("Jugador 1", "Jugador 2");

        Hand manoActual = board.getCurrentPlayerHand();

        assertNotNull(manoActual);

    }

    @Test
    public void testBoardInicializaTodosLosComponentesCorrectamente() {
        Board board = new Board("Jugador 1", "Jugador 2");

        assertNotNull(board.getCurrentPlayerHand());
        assertNotNull(board.getPlayer1Deck());
        assertNotNull(board.getPlayer2Deck());
        assertNotNull(board.getPlayer1DiscardPile());
        assertNotNull(board.getPlayer2DiscardPile());
        assertNotNull(board.getPlayer1CloseCombat());
        assertNotNull(board.getPlayer2CloseCombat());
        assertNotNull(board.getPlayer1Ranged());
        assertNotNull(board.getPlayer2Ranged());
        assertNotNull(board.getPlayer1Siege());
        assertNotNull(board.getPlayer2Siege());
        assertNotNull(board.getSpecialZone());
        assertNotNull(board.getGame());
    }
}
