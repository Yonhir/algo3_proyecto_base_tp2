package edu.fiuba.algo3.modelo.sections;

import edu.fiuba.algo3.modelo.colors.*;
import edu.fiuba.algo3.modelo.turnManagement.Player;
import edu.fiuba.algo3.modelo.turnManagement.Round;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.*;
import edu.fiuba.algo3.modelo.cards.specials.weathers.ImpenetrableFog;
import edu.fiuba.algo3.modelo.cards.specials.weathers.Weather;
import edu.fiuba.algo3.modelo.cards.units.modifiers.Agile;
import edu.fiuba.algo3.modelo.cards.units.modifiers.Modifier;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.errors.SectionTypeMismatchError;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RowTest {
    private Round round;

    private CloseCombat closeCombat;
    private Ranged ranged;
    private Siege siege;

    @BeforeEach
    void setUp() {
        Deck deck = new Deck();
        closeCombat = new CloseCombat();
        ranged = new Ranged();
        siege = new Siege();
        Player player = new Player("Gabriel", deck, closeCombat, ranged, siege, new Blue());
        Player opponent = new Player("Juan", new Deck(), new CloseCombat(), new Ranged(), new Siege(), new Red());
        round = new Round(player, opponent);
    }

    @Test
    public void testUnaUnidadEsColocadaEnEspacioRangedRow() {
         int puntosBase = 5;
        Unit arquero = new Unit("arquero", "tira flechas", puntosBase, List.of(new RangedType()), List.of() );
        arquero.setColor(new Blue());

        ranged.placeCard(arquero, round);

        assertTrue(ranged.getCards().contains(arquero));
    }

    @Test
    public void testVariasCartasSonColocadasEnEspacioCloseCombat() {
        Unit soldado1 = new Unit("soldado1", "pelea de cerca", 10, List.of(new CloseCombatType()), List.of());
        Unit soldado2 = new Unit("soldado2", "pelea de cerca", 10, List.of(new CloseCombatType()), List.of());
        soldado2.setColor(new Blue());
        soldado1.setColor(new Blue());
        int cantidadCartasEsperadas = 2;

        closeCombat.placeCard(soldado1, round);
        closeCombat.placeCard(soldado2, round);

        List<Card> cartasEnLaFila = closeCombat.getCards();
        assertTrue(cartasEnLaFila.contains(soldado1));
        assertTrue(cartasEnLaFila.contains(soldado2));
        assertEquals(cantidadCartasEsperadas, cartasEnLaFila.size());
    }

    @Test
    public void TestUnaUnidadNoPuedeSerJugadaEnUnaFilaIncorrecta() {
        Unit arquero = new Unit("arquero", "tira flechas", 5, List.of(new RangedType()), List.of()); // Solo Ranged
        arquero.setColor(new Blue());

        assertThrows(SectionTypeMismatchError.class, () -> closeCombat.placeCard(arquero, round));
    }

    @Test
    public void testUnaUnidadEsColocadaEnEspacioSiegeRow() {
        int puntosBase = 8;
        Unit catapulta = new Unit("catapulta", "dispara desde lejos", puntosBase, List.of(new SiegeType()), List.of());
        catapulta.setColor(new Blue());

        siege.placeCard(catapulta, round);

        assertTrue(siege.getCards().contains(catapulta));
    }

    @Test
    public void testUnidadAgilPuedeSerColocadaEnFilaRanged() {
        Modifier agil = new Agile();
        Unit unitConAgil = new Unit("ágil", "puede ir a melee o rango", 6, List.of(new CloseCombatType(), new RangedType()), List.of(agil));
        unitConAgil.setColor(new Blue());

        ranged.placeCard(unitConAgil, round);

        assertTrue(ranged.getCards().contains(unitConAgil));
    }

    @Test
    public void testUnidadAgilPuedeSerColocadaEnFilaCloseCombat() {
        Modifier agile = new Agile();
        Unit unitConAgil = new Unit("ágil", "puede ir a melee o rango", 6, List.of(new CloseCombatType(), new RangedType()), List.of(agile));
        unitConAgil.setColor(new Blue());

        closeCombat.placeCard(unitConAgil, round);

        assertTrue(closeCombat.getCards().contains(unitConAgil));
    }

    @Test
    public void testCalcularPuntosDeUnaFila() {
        Unit soldado1 = new Unit("soldado1", "pelea de cerca", 10, List.of(new CloseCombatType()), List.of());
        Unit soldado2 = new Unit("soldado2", "pelea de cerca", 15, List.of(new CloseCombatType()), List.of());
        soldado1.setColor(new Blue());
        soldado2.setColor(new Blue());
        int puntosEsperados = 25;

        closeCombat.placeCard(soldado1, round);
        closeCombat.placeCard(soldado2, round);

        assertEquals(puntosEsperados, closeCombat.calculatePoints());
    }

    @Test
    public void testAgregarClimaAUnaFila() {
        Unit arquero = new Unit("arquero", "tira flechas", 5, List.of(new RangedType()), List.of());
        arquero.setColor(new Blue());
        Weather niebla = new ImpenetrableFog("Niebla Impenetrable", "Reduce la fuerza de las unidades a 1");
        niebla.setColor(new Blue());

        ranged.placeCard(arquero, round);
        ranged.applyWeather(niebla);

        assertEquals(1, arquero.calculatePoints());
    }

    @Test
    public void testDescartarCartasDeUnaFila() {
        Unit catapulta1 = new Unit("catapulta1", "dispara desde lejos", 8, List.of(new SiegeType()), List.of());
        Unit catapulta2 = new Unit("catapulta2", "dispara desde lejos", 8, List.of(new SiegeType()), List.of());
        catapulta1.setColor(new Blue());
        catapulta2.setColor(new Blue());
        DiscardPile discardPile = new DiscardPile();

        siege.placeCard(catapulta1, round);
        siege.placeCard(catapulta2, round);
        siege.discardCards(discardPile);

        assertTrue(siege.getCards().isEmpty());
        assertEquals(2, discardPile.getCardCount());
    }
}
