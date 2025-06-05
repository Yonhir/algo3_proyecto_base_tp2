package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class DeckBuilderTest {
    @Test
    public void testElJugadorPoseeCartasTotalesSuficientesEnSuMazoParaEmpezarElJuego() {
        DeckBuilder constructor = new DeckBuilder();
        int minimoCartas = 21;

        List<Card> cartas = constructor.getCards();
        List<Card> copiaCartas = new ArrayList<>(cartas);

        for (Card c : copiaCartas) {
            constructor.selectCard(c);
        }

        List<Card> seleccionadas = constructor.getSelection();

        assertTrue(seleccionadas.size() >= minimoCartas);
    }

    @Test
    public void testElJugadorPoseeCartasEspecialesSuficientesParaEmpezarElJuego() {
        DeckBuilder constructor = new DeckBuilder();
        int minimoEspeciales = 6;

        List<Card> cartas = constructor.getCards();
        List<Card> copiaCartas = new ArrayList<>(cartas);

        for (Card c : copiaCartas) {
            constructor.selectCard(c);
        }

        List<Card> especialesSeleccionadas = constructor.getSpecialsSelected();

        assertTrue(especialesSeleccionadas.size() >= minimoEspeciales);
    }

    @Test
    public void testElJugadorPoseeCartasUnidadesSuficientesParaComenzarElJuego() {
        DeckBuilder constructor = new DeckBuilder();
        int minimoUnidades = 15;

        List<Card> cartas = constructor.getCards();
        List<Card> copiaCartas = new ArrayList<>(cartas);

        for (Card c : copiaCartas) {
            constructor.selectCard(c);
        }

        List<Card> unidadesSeleccionadas = constructor.getUnitsSelected();

        assertTrue(unidadesSeleccionadas.size() >= minimoUnidades);
    }

    @Test
    public void testElMazoSeCreaCorrectamenteConLasSuficientesCartasParaComenzarElJuego() {
        DeckBuilder constructor = new DeckBuilder();
        int minimoCartas = 21;

        List<Card> cartas = constructor.getCards();
        List<Card> copiaCartas = new ArrayList<>(cartas);

        for (Card c : copiaCartas) {
            constructor.selectCard(c);
        }

        Deck mazo = constructor.buildDeck();

        assertTrue(mazo.getCardCount() >= minimoCartas);
    }

    @Test
    public void testElMazoSeCreaConSuficientesCartasUnidadesParaEmpezarElJuego() {
        DeckBuilder constructor = new DeckBuilder();
        int minimoUnidades = 15;

        List<Card> cartas = constructor.getCards();
        List<Card> copiaCartas = new ArrayList<>(cartas);

        for (Card c : copiaCartas) {
            constructor.selectCard(c);
        }

        Deck mazo = constructor.buildDeck();

        assertTrue(mazo.getUnitsCount() >= minimoUnidades);
    }

    @Test
    public void testElMazoSeCreaConSuficientesCartasEspecialesParaEmpezarElJuego() {
        DeckBuilder constructor = new DeckBuilder();
        int minimoEspeciales = 6;

        List<Card> cartas = constructor.getCards();
        List<Card> copiaCartas = new ArrayList<>(cartas);

        for (Card c : copiaCartas) {
            constructor.selectCard(c);
        }

        Deck mazo = constructor.buildDeck();

        assertTrue(mazo.getSpecialsCount() >= minimoEspeciales);
    }

    @Test
    public void testNoSePuedeCrearElMazoSiNoHaySuficientesCartasUnidades() {
        DeckBuilder constructor = new DeckBuilder();

        List<Card> cartas = constructor.getCards();
        List<Card> copiaCartas = new ArrayList<>(cartas);

        for (Card c : copiaCartas) {
            constructor.selectCard(c);
        }

        List<Card> unidades = constructor.getUnitsSelected();
        List<Card> unidadesCopia = new ArrayList<>(unidades);
        for (Card c : unidadesCopia) {
            constructor.unselectCard(c);
        }

        assertThrows(IllegalArgumentException.class, constructor::buildDeck);
    }

    @Test
    public void testNoSePuedeCrearElMazoSiNoHaySuficientesCartasEspeciales() {
        DeckBuilder constructor = new DeckBuilder();

        List<Card> cartas = constructor.getCards();
        List<Card> copiaCartas = new ArrayList<>(cartas);

        for (Card c : copiaCartas) {
            constructor.selectCard(c);
        }

        List<Card> especiales = constructor.getSpecialsSelected();
        List<Card> especialesCopia = new ArrayList<>(especiales);
        for (Card c : especialesCopia) {
            constructor.unselectCard(c);
        }

        assertThrows(IllegalArgumentException.class, constructor::buildDeck);
    }
}