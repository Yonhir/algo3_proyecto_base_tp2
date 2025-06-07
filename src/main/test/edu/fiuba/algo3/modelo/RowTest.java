package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RowTest {

    @Test
    public void testUnaUnidadEsColocadaEnEspacioRangedRow() {
        Row rangedRow = new Ranged();
        int puntosBase = 5;
        Unit arquero = new Unit("arquero", "tira flechas", puntosBase, false, true, false, List.of() );

        arquero.play(rangedRow);

        assertTrue(rangedRow.getCards().contains(arquero));
    }

    @Test
    public void testVariasCartasSonColocadasEnEspacioCloseCombat() {
        Row closeCombat = new CloseCombat();
        Unit soldado1 = new Unit("soldado1", "pelea de cerca", 10,true, false, true, List.of());
        Unit soldado2 = new Unit("soldado2", "pelea de cerca", 10,true, false, true, List.of());
        int cantidadCartasEsperadas = 2;

        soldado1.play(closeCombat);
        soldado2.play(closeCombat);

        List<Card> cartasEnLaFila = closeCombat.getCards();
        assertTrue(cartasEnLaFila.contains(soldado1));
        assertTrue(cartasEnLaFila.contains(soldado2));
        assertEquals(cantidadCartasEsperadas, cartasEnLaFila.size());
    }

    @Test
    public void TestUnaUnidadNoPuedeSerJugadaEnUnaFilaIncorrecta() {
        Unit arquero = new Unit("arquero", "tira flechas", 5, false, true, false, List.of()); // Solo Ranged
        Row closeCombatRow = new CloseCombat();

        assertThrows(IllegalArgumentException.class, () -> arquero.play(closeCombatRow));
    }

}
