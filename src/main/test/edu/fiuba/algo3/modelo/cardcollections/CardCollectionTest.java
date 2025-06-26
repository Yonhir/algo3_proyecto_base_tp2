package edu.fiuba.algo3.modelo.cardcollections;

import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.cards.units.modifiers.MoraleBoostModifier;
import edu.fiuba.algo3.modelo.cards.units.modifiers.TightBond;
import edu.fiuba.algo3.modelo.errors.TheCardWasNotFound;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardCollectionTest {

    @Test
    public void testLaManoSeCreaConUnaListaVacia() {
        CardCollection mano = new Hand();

        assertTrue(mano.isEmpty());
    }

    @Test
    public void testElMazoSeCreaConUnaListaVacia() {
        CardCollection mazo = new Deck();

        assertTrue(mazo.isEmpty());
    }

    @Test
    public void testLaPilaDeDescarteSeCreaConUnaListaVacia() {
        CardCollection pilaDeDescarte = new DiscardPile();

        assertTrue(pilaDeDescarte.isEmpty());
    }

    @Test
    public void testSePuedeAgregarUnaCartaALaManoCorrectamente() {
        CardCollection mano = new Hand();
        Card unidad = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));

        mano.addCard(unidad);

        assertFalse(mano.isEmpty());
    }

    @Test
    public void testSePuedeAgregarUnaCartaAlMazoCorrectamente() {
        CardCollection mazo = new Deck();
        Card unidad = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));

        mazo.addCard(unidad);

        assertFalse(mazo.isEmpty());
    }

    @Test
    public void testSePuedenAgregarCartasALaManoCorrectamente() {
        CardCollection mano = new Hand();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));
        Card unidad2 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Card unidad3 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        mano.insertCards(List.of(unidad1, unidad2, unidad3));

        assertFalse(mano.isEmpty());
    }

    @Test
    public void testSePuedenAgregarCartasAlMazoCorrectamente() {
        CardCollection mazo = new Deck();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));
        Card unidad2 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Card unidad3 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        mazo.insertCards(List.of(unidad1, unidad2, unidad3));

        assertFalse(mazo.isEmpty());
    }

    @Test
    public void testSeObtienenLasCartasDeLaManoCorrectamente() {
        CardCollection mano = new Hand();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));
        Card unidad2 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Card unidad3 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        List<Card> cartas = List.of(unidad1, unidad2, unidad3);

        mano.insertCards(cartas);
        List<Card> obtenidas = mano.getCards();

        assertEquals(cartas, obtenidas);
    }

    @Test
    public void testSeObtienenLasCartasDelMazoCorrectamente() {
        CardCollection mazo = new Deck();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));
        Card unidad2 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Card unidad3 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        List<Card> cartas = List.of(unidad1, unidad2, unidad3);

        mazo.insertCards(cartas);
        List<Card> obtenidas = mazo.getCards();

        assertEquals(cartas, obtenidas);
    }

    @Test
    public void testSeObtienenLasCartasDeLaPilaDeDescarteCorrectamente() {
        CardCollection pilaDeDescarte = new DiscardPile();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));
        Card unidad2 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Card unidad3 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        List<Card> cartas = List.of(unidad1, unidad2, unidad3);

        pilaDeDescarte.insertCards(cartas);
        List<Card> obtenidas = pilaDeDescarte.getCards();

        assertEquals(cartas, obtenidas);
    }

    @Test
    public void testSeObtieneCorrectamenteLaCantidadDeCartasQueHayEnLaMano() {
        CardCollection mano = new Hand();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));
        Card unidad2 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Card unidad3 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        List<Card> cartas = List.of(unidad1, unidad2, unidad3);

        mano.insertCards(cartas);

        assertEquals(3, mano.getCardCount());
    }

    @Test
    public void testSeObtieneCorrectamenteLaCantidadDeCartasQueHayEnElMazo() {
        CardCollection mazo = new Deck();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));
        Card unidad2 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        List<Card> cartas = List.of(unidad1, unidad2);

        mazo.insertCards(cartas);

        assertEquals(2, mazo.getCardCount());
    }

    @Test
    public void testSeObtieneCorrectamenteLaCantidadDeCartasQueHayEnLaPilaDeDescarte() {
        CardCollection pilaDeDescarte = new DiscardPile();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));
        Card unidad2 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Card unidad3 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Card unidad4 = new Unit("unidad comun", "descripcion", 8, new CloseCombatType(), List.of(new TightBond()));
        List<Card> cartas = List.of(unidad1, unidad2, unidad3, unidad4);

        pilaDeDescarte.insertCards(cartas);

        assertEquals(4, pilaDeDescarte.getCardCount());
    }

    @Test
    public void testSePuedeQuitarDeLaManoUnaCartaExistenteCorrectamente() {
        CardCollection mano = new Hand();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));

        mano.addCard(unidad1);

        assertDoesNotThrow(() -> mano.retrieveCard(unidad1));
        assertTrue(mano.isEmpty());
    }

    @Test
    public void testSePuedeQuitarDelMazoUnaCartaExistenteCorrectamente() {
        CardCollection mazo = new Deck();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));

        mazo.addCard(unidad1);

        assertDoesNotThrow(() -> mazo.retrieveCard(unidad1));
        assertTrue(mazo.isEmpty());
    }

    @Test
    public void testSePuedeQuitarDelaPilaDeDescarteUnaCartaExistenteCorrectamente() {
        CardCollection pilaDeDescarte = new DiscardPile();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));

        pilaDeDescarte.addCard(unidad1);

        assertDoesNotThrow(() -> pilaDeDescarte.retrieveCard(unidad1));
        assertTrue(pilaDeDescarte.isEmpty());
    }

    @Test
    public void testSeLanzaExcepcionSiQuiereQuitarUnaCartaDeLaManoCuandoEstaVacia() {
        CardCollection mano = new Hand();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));

        assertThrows(TheCardWasNotFound.class, () -> mano.retrieveCard(unidad1));
    }

    @Test
    public void testSeLanzaExcepcionSiQuiereQuitarUnaCartaDelMazoCuandoEstaVacio() {
        CardCollection mazo = new Deck();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));

        assertThrows(TheCardWasNotFound.class, () -> mazo.retrieveCard(unidad1));
    }

    @Test
    public void testSeLanzaExcepcionSiQuiereQuitarUnaCartaDeLaPilaDeDescarteCuandoEstaVacia() {
        CardCollection pilaDeDescarte = new DiscardPile();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));

        assertThrows(TheCardWasNotFound.class, () -> pilaDeDescarte.retrieveCard(unidad1));
    }

    @Test
    public void testSeLanzaExcepcionSiQuiereQuitarUnaCartaQueNoExisteDeLaMano() {
        CardCollection mano = new Hand();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));
        Card unidad2 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        mano.addCard(unidad1);

        assertThrows(TheCardWasNotFound.class, () -> mano.retrieveCard(unidad2));
    }

    @Test
    public void testSeLanzaExcepcionSiQuiereQuitarUnaCartaQueNoExisteDelMazo() {
        CardCollection mazo = new Deck();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));
        Card unidad2 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        mazo.addCard(unidad1);

        assertThrows(TheCardWasNotFound.class, () -> mazo.retrieveCard(unidad2));
    }

    @Test
    public void testSeLanzaExcepcionSiQuiereQuitarUnaCartaQueNoExisteDeLaPilaDeDescarte() {
        CardCollection pilaDeDescarte = new DiscardPile();
        Card unidad1 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new TightBond()));
        Card unidad2 = new Unit("unidad comun", "descripcion", 5, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        pilaDeDescarte.addCard(unidad1);

        assertThrows(TheCardWasNotFound.class, () -> pilaDeDescarte.retrieveCard(unidad2));
    }
}
