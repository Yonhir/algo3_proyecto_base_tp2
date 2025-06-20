package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RowTest {

    @Test
    public void testUnaUnidadEsColocadaEnEspacioRangedRow() {
        Row rangedRow = new Ranged();
        int puntosBase = 5;
        Unit arquero = new Unit("arquero", "tira flechas", puntosBase, List.of(new RangedType()), List.of(), new CommonStrategy());

        rangedRow.placeCard(arquero);

        assertTrue(rangedRow.getCards().contains(arquero));
    }

    @Test
    public void testVariasCartasSonColocadasEnEspacioCloseCombat() {
        Row closeCombat = new CloseCombat();
        Unit soldado1 = new Unit("soldado1", "pelea de cerca", 10, List.of(new CloseCombatType()), List.of(), new CommonStrategy());
        Unit soldado2 = new Unit("soldado2", "pelea de cerca", 10, List.of(new CloseCombatType()), List.of(), new CommonStrategy());
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
        Unit arquero = new Unit("arquero", "tira flechas", 5, List.of(new RangedType()), List.of(), new CommonStrategy()); // Solo Ranged
        Row closeCombatRow = new CloseCombat();

        assertThrows(SectionTypeMismatchError.class, () -> closeCombatRow.placeCard(arquero));
    }

    @Test
    public void testUnaUnidadEsColocadaEnEspacioSiegeRow() {
        Row siegeRow = new Siege();
        int puntosBase = 8;
        Unit catapulta = new Unit("catapulta", "dispara desde lejos", puntosBase, List.of(new SiegeType()), List.of(), new CommonStrategy());

        siegeRow.placeCard(catapulta);

        assertTrue(siegeRow.getCards().contains(catapulta));
    }

    @Test
    public void testUnidadAgilPuedeSerColocadaEnFilaRanged() {
        Modifier agil = new Agile();
        Unit unitConAgil = new Unit("ágil", "puede ir a melee o rango", 6, List.of(new CloseCombatType(), new RangedType()), List.of(agil), new CommonStrategy());
        Row ranged = new Ranged();

        ranged.placeCard(unitConAgil);

        assertTrue(ranged.getCards().contains(unitConAgil));
    }

    @Test
    public void testUnidadAgilPuedeSerColocadaEnFilaCloseCombat() {
        Modifier agile = new Agile();
        Unit unitConAgil = new Unit("ágil", "puede ir a melee o rango", 6, List.of(new CloseCombatType(), new RangedType()), List.of(agile), new CommonStrategy());
        Row closeCombat = new CloseCombat();

        closeCombat.placeCard(unitConAgil);

        assertTrue(closeCombat.getCards().contains(unitConAgil));
    }

    @Test
    public void testCalcularPuntosDeUnaFila() {
        Row closeCombat = new CloseCombat();
        Unit soldado1 = new Unit("soldado1", "pelea de cerca", 10, List.of(new CloseCombatType()), List.of(), new CommonStrategy());
        Unit soldado2 = new Unit("soldado2", "pelea de cerca", 15, List.of(new CloseCombatType()), List.of(), new CommonStrategy());
        int puntosEsperados = 25;

        closeCombat.placeCard(soldado1);
        closeCombat.placeCard(soldado2);

        assertEquals(puntosEsperados, closeCombat.calculatePoints());
    }

    @Test
    public void testAgregarClimaAUnaFila() {
        Row ranged = new Ranged();
        Unit arquero = new Unit("arquero", "tira flechas", 5, List.of(new RangedType()), List.of(), new CommonStrategy());
        Weather niebla = new ImpenetrableFog("Niebla Impenetrable", "Reduce la fuerza de las unidades a 1");

        ranged.placeCard(arquero);
        ranged.applyWeather(niebla);

        assertEquals(1, arquero.calculatePoints());
    }

    @Test
    public void testDescartarCartasDeUnaFila() {
        Row siege = new Siege();
        Unit catapulta1 = new Unit("catapulta1", "dispara desde lejos", 8, List.of(new SiegeType()), List.of(), new CommonStrategy());
        Unit catapulta2 = new Unit("catapulta2", "dispara desde lejos", 8, List.of(new SiegeType()), List.of(), new CommonStrategy());
        DiscardPile discardPile = new DiscardPile();

        siege.placeCard(catapulta1);
        siege.placeCard(catapulta2);
        siege.discardCards(discardPile);

        assertTrue(siege.getCards().isEmpty());
        assertEquals(2, discardPile.getCardCount());
    }
}
