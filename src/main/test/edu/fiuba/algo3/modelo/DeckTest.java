package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckTest {
    @Test
    public void testElJugadorPoseeCartasSuficientesEnSuMazoParaEmpezarElJuego() {
        int minimoCartas = 21;

        List<Card> cartas = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion"),
                new TacticalAdvantage("Nombre", "Descripcion"),
                new Decoy("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"
        ));

        Deck mazo = new Deck(cartas);

        assertTrue(mazo.getCardCount() >= minimoCartas);
    }

    @Test
    public void testElJugadorPoseeCartasUnidadesSuficientesEnSuMazoParaEmpezarElJuego() {
        int minimoUnidades = 15;

        List<Card> cartas = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion"),
                new TacticalAdvantage("Nombre", "Descripcion"),
                new Decoy("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"
        ));

        Deck mazo = new Deck(cartas);

        assertTrue(mazo.getUnitsCount() >= minimoUnidades);
    }

    @Test
    public void testElJugadorPoseeCartasEspecialesSuficientesEnSuMazoParaEmpezarElJuego() {
        int minimoEspeciales = 6;

        List<Card> cartas = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion"),
                new TacticalAdvantage("Nombre", "Descripcion"),
                new Decoy("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"
        ));

        Deck mazo = new Deck(cartas);

        assertTrue(mazo.getSpecialsCount() >= minimoEspeciales);
    }

    @Test
    public void testNoSeCreaElMazoSiNoHaySuficientesCartasParaEmpezarElJuego() {
        List<Card> cartas = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, new ArrayList<Modifier>()),
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion")
        );

        assertThrows(IllegalArgumentException.class, () -> {
            Deck mazo = new Deck(cartas);
        });
    }

    @Test
    public void testNoSeCreaElMazoSiNoHaySuficientesCartasUnidadesParaEmpezarElJuego() {
        List<Card> cartas = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, new ArrayList<Modifier>()),
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion"),
                new TacticalAdvantage("Nombre", "Descripcion"),
                new Decoy("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion")
        );

        assertThrows(IllegalArgumentException.class, () -> {
            Deck mazo = new Deck(cartas);
        });
    }

    @Test
    public void testNoSeCreaElMazoSiNoHaySuficientesCartasEspecialesParaEmpezarElJuego() {
        List<Card> cartas = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, new ArrayList<Modifier>()),
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion")
        );

        assertThrows(IllegalArgumentException.class, () -> {
            Deck mazo = new Deck(cartas);
        });
    }
}