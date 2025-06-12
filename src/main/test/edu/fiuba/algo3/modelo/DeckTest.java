package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private List<Card> cartas;
    private List<Card> especiales;
    private List<Card> unidades;

    @BeforeEach
    void setUp() {
        unidades = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, true, false, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, true, false, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, false, true, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, false, false, true, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, true, false, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, false, false, true, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, false, false, true, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, false, true, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, false, true, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, true, false, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, false, true, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, false, false, true, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, false, false, true, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, true, false, false, new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, false, true, false, new ArrayList<Modifier>())
        );

        especiales = Arrays.asList(
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion"),
                new TacticalAdvantage("Nombre", "Descripcion"),
                new Decoy("Nombre", "Descripcion"),
                new Scorch("Nombre", "Descripcion"
        ));

        cartas = new ArrayList<>();
    }

    @Test
    public void testElJugadorPoseeCartasSuficientesEnSuMazoParaEmpezarElJuego() {
        int minimoCartas = 21;

        cartas.addAll(unidades);
        cartas.addAll(especiales);
        Deck mazo = new Deck(cartas);

        assertTrue(mazo.getCardCount() >= minimoCartas);
    }

    @Test
    public void testElJugadorPoseeCartasUnidadesSuficientesEnSuMazoParaEmpezarElJuego() {
        int minimoUnidades = 15;

        cartas.addAll(unidades);
        cartas.addAll(especiales);
        Deck mazo = new Deck(cartas);

        assertTrue(mazo.getUnitsCount() >= minimoUnidades);
    }

    @Test
    public void testElJugadorPoseeCartasEspecialesSuficientesEnSuMazoParaEmpezarElJuego() {
        int minimoEspeciales = 6;

        cartas.addAll(unidades);
        cartas.addAll(especiales);
        Deck mazo = new Deck(cartas);

        assertTrue(mazo.getSpecialsCount() >= minimoEspeciales);
    }

    @Test
    public void testNoSeCreaElMazoSiNoHaySuficientesCartasUnidadesParaEmpezarElJuego() {
        cartas.addAll(unidades.subList(5, 15));
        cartas.addAll(especiales);

        assertThrows(NotEnoughUnitsCardsError.class, () -> {
            Deck mazo = new Deck(cartas);
        });
    }

    @Test
    public void testNoSeCreaElMazoSiNoHaySuficientesCartasEspecialesParaEmpezarElJuego() {
        cartas.addAll(especiales.subList(3, 6));
        cartas.addAll(unidades);

        assertThrows(NotEnoughSpecialsCardsError.class, () -> {
            Deck mazo = new Deck(cartas);
        });
    }

    @Test
    public void testSeObtiene5CartasAleatoriasDelMazo(){
        cartas.addAll(unidades);
        cartas.addAll(especiales);
        Deck mazo = new Deck(cartas);
        int cartasEsperadas = 5;

        List<Card> cards = mazo.retrieveNRandomCards(5);

        assertEquals(cards.size(), cartasEsperadas);
    }

    @Test
    public void testSeEliminanLasCartasDelMazoDespuesDeRepartir(){
        cartas.addAll(unidades);
        cartas.addAll(especiales);
        Deck mazo = new Deck(cartas);
        int cartasEsperadas = 15;

        mazo.retrieveNRandomCards(6);

        assertEquals(mazo.getCardCount(), cartasEsperadas);
    }

    @Test
    public void testNoSePuedeObtenerUnaCartaQueNoEsteEnElMazo(){
        cartas.addAll(unidades);
        cartas.addAll(especiales);
        Deck mazo = new Deck(cartas);
        Card carta = new Unit("Arquero", "Descripcion", 4, false, true, false, new ArrayList<Modifier>());

        assertThrows(TheCardWasNotFound.class, () -> {
           mazo.retrieveCard(carta);
        });
    }

    @Test
    public void testNoSePuedePedirCartasDelMazoConNumerosMenoresOIgualesACero(){
        cartas.addAll(unidades);
        cartas.addAll(especiales);
        Deck mazo = new Deck(cartas);

        assertThrows(InvalidCardAmountError.class, () -> {
            mazo.retrieveNRandomCards(0);
        });
        assertThrows(InvalidCardAmountError.class, () ->{
            mazo.retrieveNRandomCards(-5);
        });
    }

}