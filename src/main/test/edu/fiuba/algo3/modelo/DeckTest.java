package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckTest {
    private List<Card> units;
    private List<Card> specials;
    private List<Card> allCards;

    @BeforeEach
    void setUp() {
        // Initialize units
        units = Arrays.asList(
            new Unit("Unit1", "Description", 4, true, false, false, new ArrayList<>()),
            new Unit("Unit2", "Description", 5, true, false, false, new ArrayList<>()),
            new Unit("Unit3", "Description", 6, true, false, false, new ArrayList<>()),
            new Unit("Unit4", "Description", 3, true, false, false, new ArrayList<>()),
            new Unit("Unit5", "Description", 2, true, false, false, new ArrayList<>()),
            new Unit("Unit6", "Description", 0, true, false, false, new ArrayList<>()),
            new Unit("Unit7", "Description", 6, true, false, false, new ArrayList<>()),
            new Unit("Unit8", "Description", 8, true, false, false, new ArrayList<>()),
            new Unit("Unit9", "Description", 0, true, false, false, new ArrayList<>()),
            new Unit("Unit10", "Description", 10, true, false, false, new ArrayList<>()),
            new Unit("Unit11", "Description", 2, true, false, false, new ArrayList<>()),
            new Unit("Unit12", "Description", 4, true, false, false, new ArrayList<>()),
            new Unit("Unit13", "Description", 8, true, false, false, new ArrayList<>()),
            new Unit("Unit14", "Description", 3, true, false, false, new ArrayList<>()),
            new Unit("Unit15", "Description", 4, true, false, false, new ArrayList<>())
        );

        // Initialize special cards
        specials = Arrays.asList(
            new TorrentialRain("TorrentialRain", "Description"),
            new ImpenetrableFog("ImpenetrableFog", "Description"),
            new BitingFrost("BitingFrost", "Description"),
            new TacticalAdvantage("TacticalAdvantage", "Description"),
            new Decoy("Decoy", "Description"),
            new Scorch("Scorch", "Description")
        );

        // Combine all cards
        allCards = new ArrayList<>();
        allCards.addAll(units);
        allCards.addAll(specials);
    }

    @Test
    public void testElJugadorPoseeCartasSuficientesEnSuMazoParaEmpezarElJuego() {
        int minimoCartas = 21;
        Deck mazo = new Deck(allCards);
        assertTrue(mazo.getCardCount() >= minimoCartas);
    }

    @Test
    public void testElJugadorPoseeCartasUnidadesSuficientesEnSuMazoParaEmpezarElJuego() {
        int minimoUnidades = 15;
        Deck mazo = new Deck(allCards);
        assertTrue(mazo.getUnitsCount() >= minimoUnidades);
    }

    @Test
    public void testElJugadorPoseeCartasEspecialesSuficientesEnSuMazoParaEmpezarElJuego() {
        int minimoEspeciales = 6;
        Deck mazo = new Deck(allCards);
        assertTrue(mazo.getSpecialsCount() >= minimoEspeciales);
    }

    @Test
    public void testNoSeCreaElMazoSiNoHaySuficientesCartasParaEmpezarElJuego() {
        List<Card> cartasInsuficientes = new ArrayList<>();
        cartasInsuficientes.addAll(units.subList(0, 10)); // Only 10 units
        cartasInsuficientes.addAll(specials.subList(0, 3)); // Only 3 specials

        assertThrows(IllegalArgumentException.class, () -> {
            Deck mazo = new Deck(cartasInsuficientes);
        });
    }

    @Test
    public void testNoSeCreaElMazoSiNoHaySuficientesCartasUnidadesParaEmpezarElJuego() {
        List<Card> cartasInsuficientes = new ArrayList<>();
        cartasInsuficientes.addAll(units.subList(0, 10)); // Only 10 units
        cartasInsuficientes.addAll(specials); // All specials

        assertThrows(IllegalArgumentException.class, () -> {
            Deck mazo = new Deck(cartasInsuficientes);
        });
    }

    @Test
    public void testNoSeCreaElMazoSiNoHaySuficientesCartasEspecialesParaEmpezarElJuego() {
        List<Card> cartasInsuficientes = new ArrayList<>();
        cartasInsuficientes.addAll(units); // All units
        cartasInsuficientes.addAll(specials.subList(0, 3)); // Only 3 specials

        assertThrows(IllegalArgumentException.class, () -> {
            Deck mazo = new Deck(cartasInsuficientes);
        });
    }
}