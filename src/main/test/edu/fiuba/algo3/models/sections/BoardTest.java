package edu.fiuba.algo3.models.sections;

import edu.fiuba.algo3.models.cardcollections.Hand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class BoardTest {

    @Test
    public void testBoardContieneUnaManoInicialParaElJugadorActual() {

        Board board = new Board("Jugador 1", "Jugador 2");

        Hand manoActual = board.currentPlayerHand();

        assertNotNull(manoActual);

    }
}
