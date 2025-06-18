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
                new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 6, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 2, new RangedType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 8, new SiegeType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 3, new CloseCombatType(), new ArrayList<Modifier>()),
                new Unit("Nombre", "Descripcion", 4, new RangedType(), new ArrayList<Modifier>())
        );

        especiales = Arrays.asList(
                new TorrentialRain("Nombre", "Descripcion"),
                new TorrentialRain("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new ImpenetrableFog("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion"),
                new BitingFrost("Nombre", "Descripcion"
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
        Card carta = new Unit("Arquero", "Descripcion", 4, new RangedType(), new ArrayList<Modifier>());

        assertThrows(TheCardWasNotFound.class, () -> {
           mazo.retrieveCard(carta);
        });
    }

    @Test
    public void testNoSePuedePedirCartasDelMazoConNumerosMenoresACero(){
        cartas.addAll(unidades);
        cartas.addAll(especiales);
        Deck mazo = new Deck(cartas);

        assertThrows(InvalidCardAmountError.class, () ->{
            mazo.retrieveNRandomCards(-5);
        });
    }
    @Test
    public void testNoSePuedePedirCartasDelMazoConNumeroIgualACero(){
        cartas.addAll(unidades);
        cartas.addAll(especiales);
        Deck mazo = new Deck(cartas);

        assertThrows(InvalidCardAmountError.class, () -> {
            mazo.retrieveNRandomCards(0);
        });
    }
}