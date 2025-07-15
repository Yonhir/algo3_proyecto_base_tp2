package edu.fiuba.algo3.models.cardcollections;

import edu.fiuba.algo3.models.cards.*;
import edu.fiuba.algo3.models.cards.specials.weathers.BitingFrost;
import edu.fiuba.algo3.models.cards.specials.weathers.ImpenetrableFog;
import edu.fiuba.algo3.models.cards.specials.weathers.TorrentialRain;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.colors.Blue;
import edu.fiuba.algo3.models.colors.PlayerColor;
import edu.fiuba.algo3.models.colors.Red;
import edu.fiuba.algo3.models.errors.InvalidCardAmountError;
import edu.fiuba.algo3.models.errors.NotEnoughSpecialsCardsError;
import edu.fiuba.algo3.models.errors.NotEnoughUnitsCardsError;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SiegeType;
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
                new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, new RangedType(), new ArrayList<>())
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
        Deck mazo = new Deck();
        int minimoCartas = 21;
        cartas.addAll(unidades);
        cartas.addAll(especiales);

        mazo.insertCards(cartas);
        int cantidadObtenida = (int) mazo.getUnitsCount() + (int) mazo.getSpecialsCount();

        assertTrue(cantidadObtenida >= minimoCartas);
    }

    @Test
    public void testElJugadorPoseeCartasUnidadesSuficientesEnSuMazoParaEmpezarElJuego() {
        Deck mazo = new Deck();
        int minimoUnidades = 15;
        cartas.addAll(unidades);
        cartas.addAll(especiales);

        mazo.insertCards(cartas);

        assertTrue(mazo.getUnitsCount() >= minimoUnidades);
    }

    @Test
    public void testElJugadorPoseeCartasEspecialesSuficientesEnSuMazoParaEmpezarElJuego() {
        Deck mazo = new Deck();
        int minimoEspeciales = 6;
        cartas.addAll(unidades);
        cartas.addAll(especiales);

        mazo.insertCards(cartas);

        assertTrue(mazo.getSpecialsCount() >= minimoEspeciales);
    }

    @Test
    public void testNoSeCreaElMazoSiNoHaySuficientesCartasUnidadesParaEmpezarElJuego() {
        Deck mazo = new Deck();
        cartas.addAll(unidades.subList(5, 15));
        cartas.addAll(especiales);

        mazo.insertCards(cartas);

        assertThrows(NotEnoughUnitsCardsError.class, mazo::validate);
    }

    @Test
    public void testNoSeCreaElMazoSiNoHaySuficientesCartasEspecialesParaEmpezarElJuego() {
        Deck mazo = new Deck();
        cartas.addAll(especiales.subList(3, 6));
        cartas.addAll(unidades);

        mazo.insertCards(cartas);

        assertThrows(NotEnoughSpecialsCardsError.class, mazo::validate);
    }

    @Test
    public void testSeObtiene5CartasAleatoriasDelMazo(){
        Deck mazo = new Deck();
        cartas.addAll(unidades);
        cartas.addAll(especiales);
        int cartasEsperadas = 5;

        mazo.insertCards(cartas);

        List<Card> cards = mazo.retrieveNRandomCards(5);

        assertEquals(cartasEsperadas, cards.size());
    }

    @Test
    public void testSeEliminanLasCartasDelMazoDespuesDeRepartir(){
        Deck mazo = new Deck();
        cartas.addAll(unidades);
        cartas.addAll(especiales);
        int cartasEsperadas = 15;

        mazo.insertCards(cartas);

        mazo.retrieveNRandomCards(6);
        int obtenido = (int) mazo.getUnitsCount() + (int) mazo.getSpecialsCount();

        assertEquals(cartasEsperadas, obtenido);
    }

    @Test
    public void testNoSePuedePedirCartasDelMazoConNumerosMenoresACero(){
        Deck mazo = new Deck();
        cartas.addAll(unidades);
        cartas.addAll(especiales);

        mazo.insertCards(cartas);

        assertThrows(InvalidCardAmountError.class, () ->{
            mazo.retrieveNRandomCards(-5);
        });
    }

    @Test
    public void testNoSePuedePedirCartasDelMazoConNumeroIgualACero(){
        Deck mazo = new Deck();
        cartas.addAll(unidades);
        cartas.addAll(especiales);

        mazo.insertCards(cartas);

        assertThrows(InvalidCardAmountError.class, () -> {
            mazo.retrieveNRandomCards(0);
        });
    }

    @Test
    public void testSetColorAsignaColorATodasLasCartasDelMazo() {
        Deck mazo = new Deck();
        cartas.addAll(unidades);
        cartas.addAll(especiales);
        mazo.insertCards(cartas);
        PlayerColor colorAzul = new Blue();

        mazo.setColor(colorAzul);

        for (Card carta : cartas) {
            assertEquals(colorAzul, carta.getPlayerColor());
        }
    }

    @Test
    public void testSetColorConMazoVacioNoLanzaExcepcion() {
        Deck mazo = new Deck();
        PlayerColor colorRojo = new Red();

        mazo.setColor(colorRojo);
    }

    @Test
    public void testAddCardAsignaColorAutomaticamente() {
        Deck mazo = new Deck();
        PlayerColor colorAzul = new Blue();
        mazo.setColor(colorAzul);
        
        Unit nuevaCarta = new Unit("Nueva Carta", "Descripción", 5, new CloseCombatType(), new ArrayList<>());
        mazo.addCard(nuevaCarta);

        assertEquals(colorAzul, nuevaCarta.getPlayerColor());
    }

    @Test
    public void testInsertCardsAsignaColorAutomaticamente() {
        Deck mazo = new Deck();
        PlayerColor colorRojo = new Red();
        mazo.setColor(colorRojo);
        
        List<Card> nuevasCartas = Arrays.asList(
                new Unit("Carta 1", "Descripción", 5, new CloseCombatType(), new ArrayList<>()),
                new Unit("Carta 2", "Descripción", 6, new RangedType(), new ArrayList<>())
        );
        
        mazo.insertCards(nuevasCartas);

        for (Card carta : nuevasCartas) {
            assertEquals(colorRojo, carta.getPlayerColor());
        }
    }
}