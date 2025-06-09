package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckTest {
    private List<Card> cartas;

    @BeforeEach
    void setUp() {
        cartas = Arrays.asList(
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
    }

    @Test
    public void testElJugadorPoseeCartasSuficientesEnSuMazoParaEmpezarElJuego() {
        int minimoCartas = 21;

        Deck mazo = new Deck(cartas);

        assertTrue(mazo.getCardCount() >= minimoCartas);
    }

    @Test
    public void testElJugadorPoseeCartasUnidadesSuficientesEnSuMazoParaEmpezarElJuego() {
        int minimoUnidades = 15;

        Deck mazo = new Deck(cartas);

        assertTrue(mazo.getUnitsCount() >= minimoUnidades);
    }

    @Test
    public void testElJugadorPoseeCartasEspecialesSuficientesEnSuMazoParaEmpezarElJuego() {
        int minimoEspeciales = 6;

        Deck mazo = new Deck(cartas);

        assertTrue(mazo.getSpecialsCount() >= minimoEspeciales);
    }

    @Test
    public void testNoSeCreaElMazoSiNoHaySuficientesCartasParaEmpezarElJuego() {
        List<Card> insuficientes = new ArrayList<>(cartas.subList(5, 18));

        assertThrows(IllegalArgumentException.class, () -> {
            Deck mazo = new Deck(insuficientes);
        });
    }

    @Test
    public void testNoSeCreaElMazoSiNoHaySuficientesCartasUnidadesParaEmpezarElJuego() {
        List<Card> insuficientes = new ArrayList<>(cartas.subList(5, 21));

        assertThrows(IllegalArgumentException.class, () -> {
            Deck mazo = new Deck(insuficientes);
        });
    }

    @Test
    public void testNoSeCreaElMazoSiNoHaySuficientesCartasEspecialesParaEmpezarElJuego() {
        List<Card> insuficientes = new ArrayList<>(cartas.subList(0, 18));

        assertThrows(IllegalArgumentException.class, () -> {
            Deck mazo = new Deck(insuficientes);
        });
    }
}