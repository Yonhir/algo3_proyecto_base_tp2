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
        assertNotNull(board.getCurrentPlayerDeck());
        assertNotNull(board.getOpponentDeck());
        assertNotNull(board.getCurrentPlayerDiscardPile());
        assertNotNull(board.getOpponentDiscardPile());
        assertNotNull(board.getCurrentPlayerCloseCombat());
        assertNotNull(board.getOpponentCloseCombat());
        assertNotNull(board.getCurrentPlayerRanged());
        assertNotNull(board.getOpponentRanged());
        assertNotNull(board.getCurrentPlayerSiege());
        assertNotNull(board.getOpponentSiege());
        assertNotNull(board.getSpecialZone());
        assertNotNull(board.getGame());
    }
}
