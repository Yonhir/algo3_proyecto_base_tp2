package edu.fiuba.algo3.modelo.sections;

import edu.fiuba.algo3.modelo.Colors.Red;
import edu.fiuba.algo3.modelo.turnManagement.Player;
import edu.fiuba.algo3.modelo.turnManagement.Round;
import edu.fiuba.algo3.modelo.Colors.Blue;
import edu.fiuba.algo3.modelo.Colors.Color;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.modelo.cards.*;
import edu.fiuba.algo3.modelo.cards.specials.weathers.ImpenetrableFog;
import edu.fiuba.algo3.modelo.cards.specials.weathers.Weather;
import edu.fiuba.algo3.modelo.cards.units.modifiers.Agile;
import edu.fiuba.algo3.modelo.cards.units.modifiers.Modifier;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.cards.units.modifiers.MoraleBoostModifier;
import edu.fiuba.algo3.modelo.errors.SectionTypeMismatchError;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Row;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RowTest {
    private Player player;
    private Player opponent;
    private Round round;
    private Deck deck;

    private CloseCombat closeCombat1;
    private Ranged ranged1;
    private Siege siege1;

    private CloseCombat closeCombat2;
    private Ranged ranged2;
    private Siege siege2;

    @BeforeEach
    void setUp() {
        deck = new Deck();
        DiscardPile discardPile1 = new DiscardPile();
        DiscardPile discardPile2 = new DiscardPile();
        closeCombat1 = new CloseCombat(discardPile1);
        ranged1 = new Ranged(discardPile1);
        siege1 = new Siege(discardPile1);
        closeCombat2 = new CloseCombat(discardPile2);
        ranged2 = new Ranged(discardPile2);
        siege2 = new Siege(discardPile2);

        player = new Player("Gabriel", deck, discardPile1, closeCombat1, ranged1, siege1, new Blue());
        opponent = new Player("Juan", deck, discardPile2, closeCombat2, ranged2, siege2, new Red());
        round = new Round(player, opponent);
    }

    @Test
    public void testLasFilasTienenElMismoColorQueElJugador(){
        Color blue = new Blue();
        DiscardPile discardPile = new DiscardPile();
        CloseCombat closeCombat = new CloseCombat(discardPile);
        Ranged ranged = new Ranged(discardPile);
        Siege siege = new Siege(discardPile);

        new Player("Gabriel", new Deck(), discardPile, closeCombat, ranged, siege, blue);

        Assertions.assertTrue(closeCombat.sameColor(blue));
        Assertions.assertTrue(ranged.sameColor(blue));
        Assertions.assertTrue(siege.sameColor(blue));
    }

    @Test
    public void testUnaUnidadEsColocadaEnEspacioRangedRow() {
        DiscardPile discardPile = new DiscardPile();
        Row rangedRow = new Ranged(discardPile);
        int puntosBase = 5;
        Unit arquero = new Unit("arquero", "tira flechas", puntosBase, List.of(new RangedType()), List.of() );

        rangedRow.placeCard(arquero, round);

        assertTrue(rangedRow.getCards().contains(arquero));
    }

    @Test
    public void testVariasCartasSonColocadasEnEspacioCloseCombat() {
        DiscardPile discardPile = new DiscardPile();
        Row closeCombat = new CloseCombat(discardPile);
        Unit soldado1 = new Unit("soldado1", "pelea de cerca", 10, List.of(new CloseCombatType()), List.of());
        Unit soldado2 = new Unit("soldado2", "pelea de cerca", 10, List.of(new CloseCombatType()), List.of());
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
        DiscardPile discardPile = new DiscardPile();
        Row closeCombatRow = new CloseCombat(discardPile);

        assertThrows(SectionTypeMismatchError.class, () -> closeCombatRow.placeCard(arquero, round));
    }

    @Test
    public void testUnaUnidadEsColocadaEnEspacioSiegeRow() {
        DiscardPile discardPile = new DiscardPile();
        Row siegeRow = new Siege(discardPile);
        int puntosBase = 8;
        Unit catapulta = new Unit("catapulta", "dispara desde lejos", puntosBase, List.of(new SiegeType()), List.of());

        siegeRow.placeCard(catapulta, round);

        assertTrue(siegeRow.containsCard(catapulta));
    }

    @Test
    public void testUnidadAgilPuedeSerColocadaEnFilaRanged() {
        Modifier agil = new Agile();
        Unit unitConAgil = new Unit("ágil", "puede ir a melee o rango", 6, List.of(new CloseCombatType(), new RangedType()), List.of(agil));
        DiscardPile discardPile = new DiscardPile();
        Row ranged = new Ranged(discardPile);

        ranged.placeCard(unitConAgil, round);

        assertTrue(ranged.containsCard(unitConAgil));
    }

    @Test
    public void testUnidadAgilPuedeSerColocadaEnFilaCloseCombat() {
        Modifier agile = new Agile();
        Unit unitConAgil = new Unit("ágil", "puede ir a melee o rango", 6, List.of(new CloseCombatType(), new RangedType()), List.of(agile));
        DiscardPile discardPile = new DiscardPile();
        Row closeCombat = new CloseCombat(discardPile);

        closeCombat.placeCard(unitConAgil, round);

        assertTrue(closeCombat.containsCard(unitConAgil));
    }

    @Test
    public void testCalcularPuntosDeUnaFila() {
        DiscardPile discardPile = new DiscardPile();
        Row closeCombat = new CloseCombat(discardPile);
        Unit soldado1 = new Unit("soldado1", "pelea de cerca", 10, List.of(new CloseCombatType()), List.of());
        Unit soldado2 = new Unit("soldado2", "pelea de cerca", 15, List.of(new CloseCombatType()), List.of());
        int puntosEsperados = 25;

        closeCombat.placeCard(soldado1, round);
        closeCombat.placeCard(soldado2, round);

        assertEquals(puntosEsperados, closeCombat.calculatePoints());
    }

    @Test
    public void testAgregarClimaAUnaFila() {
        DiscardPile discardPile = new DiscardPile();
        Row ranged = new Ranged(discardPile);
        Unit arquero = new Unit("arquero", "tira flechas", 5, List.of(new RangedType()), List.of());
        Weather niebla = new ImpenetrableFog("Niebla Impenetrable", "Reduce la fuerza de las unidades a 1");

        ranged.placeCard(arquero, round);
        ranged.applyWeather(niebla);

        assertEquals(1, arquero.calculatePoints());
    }

    @Test
    public void testDescartarCartasDeUnaFila() {
        DiscardPile discardPile = new DiscardPile();
        Row siege = new Siege(discardPile);
        Unit catapulta1 = new Unit("catapulta1", "dispara desde lejos", 8, List.of(new SiegeType()), List.of());
        Unit catapulta2 = new Unit("catapulta2", "dispara desde lejos", 8, List.of(new SiegeType()), List.of());

        siege.placeCard(catapulta1, round);
        siege.placeCard(catapulta2, round);
        siege.discardCards();

        assertTrue(siege.getCards().isEmpty());
        assertEquals(2, discardPile.getCardCount());
    }

    @Test
    public void testAlJugarUnaCartaEnLaFilaSeLaPuedeDescartar() {
        DiscardPile discardPile = new DiscardPile();
        Row ranged = new Ranged(discardPile);
        Unit unidadConMoraleBoost = new Unit("nombre", "descripcion", 8, List.of(new RangedType()), List.of(new MoraleBoostModifier()));

        ranged.placeCard(unidadConMoraleBoost, round);
        ranged.discardCard(unidadConMoraleBoost);

        assertFalse(ranged.containsCard(unidadConMoraleBoost));
    }
}
