package edu.fiuba.algo3.models.sections;

import edu.fiuba.algo3.models.cards.specials.Scorch;
import edu.fiuba.algo3.models.cards.units.modifiers.Hero;
import edu.fiuba.algo3.models.colors.*;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.*;
import edu.fiuba.algo3.models.cards.specials.weathers.ImpenetrableFog;
import edu.fiuba.algo3.models.cards.specials.weathers.Weather;
import edu.fiuba.algo3.models.cards.units.modifiers.Agile;
import edu.fiuba.algo3.models.cards.units.modifiers.Modifier;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.cards.units.modifiers.MoraleBoostModifier;
import edu.fiuba.algo3.models.errors.SectionTypeMismatchError;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SiegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RowTest {
    private Round round;
    private DiscardPile discardPile1;

    private CloseCombat closeCombat;
    private Ranged ranged;
    private Siege siege;

    @BeforeEach
    void setUp() {
        Player player = new Player("Gabriel", new Blue());
        Player opponent = new Player("Juan", new Red());

        discardPile1 = player.getDiscardPile();
        closeCombat = player.getCloseCombatRow();
        ranged = player.getRangedRow();
        siege = player.getSiegeRow();

        round = new Round(player, opponent);
    }

    @Test
    public void testUnaUnidadEsColocadaEnEspacioRangedRow() {
        int puntosBase = 5;
        Unit arquero = new Unit("arquero", "tira flechas", puntosBase, List.of(new RangedType()), List.of() );
        arquero.setColor(new Blue());

        ranged.placeCard(arquero, round);

        assertEquals(arquero, ranged.getLastCard());
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

        assertTrue(closeCombat.containsCard(soldado1));
        assertTrue(closeCombat.containsCard(soldado2));
        assertEquals(cantidadCartasEsperadas, closeCombat.getCardCount());
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

        assertTrue(siege.containsCard(catapulta));
    }

    @Test
    public void testUnidadAgilPuedeSerColocadaEnFilaRanged() {
        Modifier agil = new Agile();
        Unit unitConAgil = new Unit("ágil", "puede ir a melee o rango", 6, List.of(new CloseCombatType(), new RangedType()), List.of(agil));
        unitConAgil.setColor(new Blue());

        ranged.placeCard(unitConAgil, round);

        assertTrue(ranged.containsCard(unitConAgil));
    }

    @Test
    public void testUnidadAgilPuedeSerColocadaEnFilaCloseCombat() {
        Modifier agile = new Agile();
        Unit unitConAgil = new Unit("ágil", "puede ir a melee o rango", 6, List.of(new CloseCombatType(), new RangedType()), List.of(agile));
        unitConAgil.setColor(new Blue());

        closeCombat.placeCard(unitConAgil, round);

        assertTrue(closeCombat.containsCard(unitConAgil));
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

        siege.placeCard(catapulta1, round);
        siege.placeCard(catapulta2, round);
        siege.discardCards();

        assertTrue(siege.getCards().isEmpty());
        assertEquals(2, discardPile1.getCardCount());
    }

    @Test
    public void testAlJugarUnaCartaEnLaFilaSeLaPuedeDescartar() {
        Unit unidadConMoraleBoost = new Unit("nombre", "descripcion", 8, List.of(new RangedType()), List.of(new MoraleBoostModifier()));
        unidadConMoraleBoost.setColor(new Blue());

        ranged.placeCard(unidadConMoraleBoost, round);
        ranged.discardCard(unidadConMoraleBoost);

        assertFalse(ranged.containsCard(unidadConMoraleBoost));
    }

    @Test
    public void testSeBuscaLasCartaMasFuerteDelTableroSinConsiderarLasQueTienenModificadorHero() {
        Unit unidadSinHero = new Unit("unidad", "sin hero", 5, new CloseCombatType(), List.of());
        Unit unidadConHero = new Unit("unidad", "con hero", 8, new CloseCombatType(), List.of(new Hero()));
        unidadSinHero.setColor(new Blue());
        unidadConHero.setColor(new Blue());
        Scorch tierraArrasada = new Scorch("nombre", "descripcion", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));

        closeCombat.placeCard(unidadSinHero, round);
        closeCombat.placeCard(unidadConHero, round);

        closeCombat.findStrongestCardWithoutHeroModifier(tierraArrasada);

        assertTrue(tierraArrasada.matchesStrongest(unidadSinHero));
    }

    @Test
    public void testSeObtienenTodasLasCartasFuertesSinCosiderarLasQueTienenModificadorHero() {
        Unit unidadSinHero = new Unit("unidad", "sin hero", 5, new CloseCombatType(), List.of());
        Unit otraUnidadSinHero = new Unit("unidad", "sin hero", 5, new CloseCombatType(), List.of());
        Unit unidadConHero = new Unit("unidad", "con hero", 8, new CloseCombatType(), List.of(new Hero()));
        Scorch tierraArrasada = new Scorch("nombre", "descripcion", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));
        unidadSinHero.setColor(new Blue());
        unidadConHero.setColor(new Blue());
        otraUnidadSinHero.setColor(new Blue());

        closeCombat.placeCard(unidadSinHero, round);
        closeCombat.placeCard(unidadConHero, round);
        closeCombat.placeCard(otraUnidadSinHero, round);

        closeCombat.findStrongestCardWithoutHeroModifier(tierraArrasada);
        List<Card> esperadas = List.of(unidadSinHero, otraUnidadSinHero);
        List<Card> obtenidas = closeCombat.findAllCardsWithoutHeroModifierWithSamePoints(tierraArrasada);

        assertEquals(esperadas, obtenidas);
    }

    @Test
    public void testHaveSamePlayerColorRetornaTrueCuandoLaCartaTieneElMismoColor() {
        Unit unidad = new Unit("unidad", "descripción", 5, new CloseCombatType(), List.of());
        unidad.setColor(new Blue());
        closeCombat.setColor(new Blue());

        assertTrue(closeCombat.haveSamePlayerColor(unidad));
    }

    @Test
    public void testHaveSamePlayerColorRetornaFalseCuandoLaCartaTieneColorDiferente() {
        Unit unidad = new Unit("unidad", "descripción", 5, new CloseCombatType(), List.of());
        unidad.setColor(new Red());
        ranged.setColor(new Blue());

        assertFalse(ranged.haveSamePlayerColor(unidad));
    }
}
