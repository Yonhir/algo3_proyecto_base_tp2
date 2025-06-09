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

        rangedRow.placeCard(arquero);

        assertTrue(rangedRow.getCards().contains(arquero));
    }

    @Test
    public void testVariasCartasSonColocadasEnEspacioCloseCombat() {
        Row closeCombat = new CloseCombat();
        Unit soldado1 = new Unit("soldado1", "pelea de cerca", 10,true, false, true, List.of());
        Unit soldado2 = new Unit("soldado2", "pelea de cerca", 10,true, false, true, List.of());
        int cantidadCartasEsperadas = 2;

        closeCombat.placeCard(soldado1);
        closeCombat.placeCard(soldado2);

        List<Card> cartasEnLaFila = closeCombat.getCards();
        assertTrue(cartasEnLaFila.contains(soldado1));
        assertTrue(cartasEnLaFila.contains(soldado2));
        assertEquals(cantidadCartasEsperadas, cartasEnLaFila.size());
    }

    @Test
    public void TestUnaUnidadNoPuedeSerJugadaEnUnaFilaIncorrecta() {
        Unit arquero = new Unit("arquero", "tira flechas", 5, false, true, false, List.of()); // Solo Ranged
        Row closeCombatRow = new CloseCombat();

        assertThrows(IllegalArgumentException.class, () -> closeCombatRow.placeCard(arquero));
    }

    @Test
    public void testUnaUnidadEsColocadaEnEspacioSiegeRow() {
        Row siegeRow = new Siege();
        int puntosBase = 8;
        Unit catapulta = new Unit("catapulta", "dispara desde lejos", puntosBase, false, false, true, List.of());

        siegeRow.placeCard(catapulta);

        assertTrue(siegeRow.getCards().contains(catapulta));
    }
}
