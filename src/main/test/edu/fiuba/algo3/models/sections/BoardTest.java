package edu.fiuba.algo3.models.sections;

import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.turnManagement.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    public void testBoardContieneUnaManoInicialParaElJugadorActual() {
        Board board = new Board();

        Hand manoActual = board.getCurrentPlayerHand();

        Assertions.assertNotNull(manoActual);
    }

    @Test
    public void testBoardInicializaTodosLosComponentesCorrectamente() {
        Board board = new Board();

        Assertions.assertNotNull(board.getCurrentPlayer());
        Assertions.assertNotNull(board.getOpponentPlayer());

        Assertions.assertNotNull(board.getCurrentPlayerHand());
        Assertions.assertNotNull(board.getOpponentHand());

        Assertions.assertNotNull(board.getCurrentPlayerDeck());
        Assertions.assertNotNull(board.getOpponentDeck());

        Assertions.assertNotNull(board.getCurrentPlayerDiscardPile());
        Assertions.assertNotNull(board.getOpponentDiscardPile());

        Assertions.assertNotNull(board.getCurrentPlayerCloseCombat());
        Assertions.assertNotNull(board.getOpponentCloseCombat());

        Assertions.assertNotNull(board.getCurrentPlayerRanged());
        Assertions.assertNotNull(board.getOpponentRanged());

        Assertions.assertNotNull(board.getCurrentPlayerSiege());
        Assertions.assertNotNull(board.getOpponentSiege());

        Assertions.assertNotNull(board.getSpecialZone());

        Assertions.assertNotNull(board.getGame());
    }

    @Test
    public void testRestartGameReiniciaElJuegoCorrectamente() {
        Board board = new Board();

        Hand manoInicial = board.getCurrentPlayerHand();
        Player jugadorInicial = board.getCurrentPlayer();

        board.restartGame();

        Assertions.assertNotSame(manoInicial, board.getCurrentPlayerHand());
        Assertions.assertNotSame(jugadorInicial, board.getCurrentPlayer());
    }

    @Test
    public void testSetPlayerNamesCambiaLosNombresDeLosJugadoresCorrectamente() {
        Board board = new Board();
        String nuevoNombre1 = "JugadorUno";
        String nuevoNombre2 = "JugadorDos";

        board.setPlayerNames(nuevoNombre1, nuevoNombre2);

        // Verificar que los nombres se cambiaron correctamente accediendo a través del Game
        String nombreJugador1 = board.getGame().getCurrentRound().getCurrentPlayer().getName();
        String nombreJugador2 = board.getGame().getCurrentRound().getOpponent().getName();

        // Los nombres pueden estar en cualquier orden debido a la selección aleatoria
        boolean nombresCambiados = (nombreJugador1.equals(nuevoNombre1) && nombreJugador2.equals(nuevoNombre2)) ||
                (nombreJugador1.equals(nuevoNombre2) && nombreJugador2.equals(nuevoNombre1));

        Assertions.assertTrue(nombresCambiados);
    }

    @Test
    public void testGetPlayer1RetornaElPrimerJugador() {
        Board board = new Board();

        Player player1 = board.getPlayer1();

        Assertions.assertNotNull(player1);
        Assertions.assertEquals("Player 1", player1.getName());
    }

    @Test
    public void testGetPlayer2RetornaElSegundoJugador() {
        Board board = new Board();

        Player player2 = board.getPlayer2();

        Assertions.assertNotNull(player2);
        Assertions.assertEquals("Player 2", player2.getName());
    }

    @Test
    public void testGetPlayer1YGetPlayer2RetornanJugadoresDiferentes() {
        Board board = new Board();

        Player player1 = board.getPlayer1();
        Player player2 = board.getPlayer2();

        Assertions.assertNotSame(player1, player2);
        Assertions.assertNotEquals(player1.getName(), player2.getName());
    }
}
