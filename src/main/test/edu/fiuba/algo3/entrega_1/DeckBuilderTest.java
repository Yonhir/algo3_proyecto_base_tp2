package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckBuilderTest {
    @Test
    public void testElJugadorPoseeCartasSuficientesEnSuMazoParaEmpezarElJuego() {
        DeckBuilder constructorMazo = new DeckBuilder();
        Deck mazo;
        Player jugador;

        constructorMazo.addNSpecialCards(6);
        constructorMazo.addNUnitCards(15);
        mazo = constructorMazo.builDeck();
        jugador = new Player("Pepito", 3, mazo);

        assertTrue(mazo.getCardCount() >= 21)
        assertTrue(mazo.getSpecialCount() >= 6)
        assertTrue(mazo.getUnitCount() >= 15)
    }
}