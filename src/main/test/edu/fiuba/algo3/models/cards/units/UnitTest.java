package edu.fiuba.algo3.models.cards.units;

import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.units.modifiers.Agile;
import edu.fiuba.algo3.models.cards.units.modifiers.MoraleBoostModifier;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SiegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    private DiscardPile discardPile;
    @BeforeEach
    void setUp() {
        discardPile = new DiscardPile();
    }

    @Test
    public void testAlCrearseLaCartaLosPuntosSonLosIniciales() {
        Unit unidad = new Unit("nombre", "descripcion", 6, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        assertEquals(6, unidad.calculatePoints());
    }

    @Test
    public void testLaCartaEsJugadaCorrectamente() {
        Ranged ranged = new Ranged(discardPile);
        Unit unidad = new Unit("nombre", "descripcion", 6, new RangedType(), List.of(new MoraleBoostModifier()));

        unidad.play(ranged);

        assertTrue(ranged.containsCard(unidad));
    }

    @Test
    public void testElPuntajeDeLaCartaEsModificadoDebidoAOtraCartaJugada() {
        Ranged ranged = new Ranged(discardPile);
        Unit unidad = new Unit("nombre", "descripcion", 6, new RangedType(), List.of(new MoraleBoostModifier()));
        Unit otraUnidad = new Unit("nombre", "descripcion", 5, new RangedType(), List.of(new MoraleBoostModifier()));

        unidad.play(ranged);
        otraUnidad.play(ranged);

        int puntajeActual = unidad.calculatePoints();

        assertEquals(7, puntajeActual);
    }

    @Test
    public void testElPuntajeDeLaCartaEsReseteadoASuPuntajeInicialCorrectamente() {
        Ranged ranged = new Ranged(discardPile);
        Unit unidad = new Unit("nombre", "descripcion", 6, new RangedType(), List.of(new MoraleBoostModifier()));
        Unit otraUnidad = new Unit("nombre", "descripcion", 5, new RangedType(), List.of(new MoraleBoostModifier()));

        unidad.play(ranged);
        otraUnidad.play(ranged);
        unidad.resetPoints();

        assertEquals(6, unidad.calculatePoints());
    }

    @Test
    public void testSePuedeCambiarElPuntajeDeLaCartaCorrectamente() {
        Unit unidad = new Unit("nombre", "descripcion", 6, new SiegeType(), List.of(new MoraleBoostModifier()));

        unidad.setPoints(4);

        assertEquals(4, unidad.calculatePoints());
    }

    @Test
    public void testSePuedeSaberSiContieneUnModificadorEnEspecifico() {
        MoraleBoostModifier modificador = new MoraleBoostModifier();
        Unit unidad = new Unit("nombre", "descripcion", 6, new SiegeType(), List.of(modificador));

        assertTrue(unidad.haveModifier(modificador));
    }

    @Test
    public void testUnaCartaPuedeTenerMasDeUnModificador() {
        MoraleBoostModifier modificador1 = new MoraleBoostModifier();
        Agile modificador2 = new Agile();
        Unit unidad = new Unit("carta", "con 2 modificadores", 4, List.of(new RangedType(), new CloseCombatType()), List.of(modificador1, modificador2));


        assertTrue(unidad.haveModifier(modificador1));
        assertTrue(unidad.haveModifier(modificador2));
    }

    @Test
    public void testUnaCartaPuedeNoTenerModificadores() {
        Agile modificador = new Agile();
        Unit unidad = new Unit("carta", "con 2 modificadores", 4, List.of(new RangedType(), new CloseCombatType()), List.of());

        assertFalse(unidad.haveModifier(modificador));
    }

    @Test
    public void testGetSectionTypeDevuelveElPrimerSectionType() {
        RangedType ranged = new RangedType();
        CloseCombatType closeCombat = new CloseCombatType();
        Unit unidad = new Unit("Arco", "Unidad de prueba", 5, List.of(ranged, closeCombat), List.of());

        assertEquals(ranged, unidad.getFirstSectionType());
    }
}
