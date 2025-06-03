package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckBuilderTest {
    @Test
    public void testElJugadorPoseeCartasSuficientesEnSuMazoParaEmpezarElJuego() {
        DeckBuilder constructorMazo = new DeckBuilder();
        Deck mazo;

        constructorMazo.addSpecialCards(6);
        constructorMazo.addUnitCards(15);
        mazo = constructorMazo.builDeck();

        assertTrue(mazo.getCardCount() >= 21)
        assertTrue(mazo.getSpecialCount() >= 6)
        assertTrue(mazo.getUnitCount() >= 15)
    }
}