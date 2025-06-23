package edu.fiuba.algo3.modelo.cards.units;

import edu.fiuba.algo3.modelo.cards.units.modifiers.MoraleBoostModifier;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTest {

    @Test
    public void testAlCrearseLaCartaLosPuntosSonLosIniciales() {
        Unit unidad = new Unit("nombre", "descripcion", 6, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        assertEquals(6, unidad.calculatePoints());
    }

    @Test
    public void testLaCartaEsJugadaCorrectamente() {
        Ranged ranged = new Ranged();
        Unit unidad = new Unit("nombre", "descripcion", 6, new RangedType(), List.of(new MoraleBoostModifier()));

        unidad.play(ranged);

        assertTrue(ranged.containsCard(unidad));
    }

    @Test
    public void testElPuntajeDeLaCartaEsModificadoDebidoAOtraCartaJugada() {
        Ranged ranged = new Ranged();
        Unit unidad = new Unit("nombre", "descripcion", 6, new RangedType(), List.of(new MoraleBoostModifier()));
        Unit otraUnidad = new Unit("nombre", "descripcion", 5, new RangedType(), List.of(new MoraleBoostModifier()));

        unidad.play(ranged);
        otraUnidad.play(ranged);

        int puntajeActual = unidad.calculatePoints();

        assertEquals(7, puntajeActual);
    }

    @Test
    public void testElPuntajeDeLaCartaEsReseteadoASuPuntajeInicialCorrectamente() {
        Ranged ranged = new Ranged();
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
}
