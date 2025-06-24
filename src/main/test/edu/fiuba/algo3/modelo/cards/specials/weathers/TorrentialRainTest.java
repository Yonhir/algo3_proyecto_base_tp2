package edu.fiuba.algo3.modelo.cards.specials.weathers;

import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.Section;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TorrentialRainTest {
    Weather rain;
    CloseCombat closeCombat1;
    CloseCombat closeCombat2;
    Ranged ranged1;
    Ranged ranged2;
    Siege siege1;
    Siege siege2;

    @BeforeEach
    void setUp() {
        rain = new TorrentialRain("lluvia", "torrencial");
        closeCombat1 = new CloseCombat();
        closeCombat2 = new CloseCombat();
        ranged1 = new Ranged();
        ranged2 = new Ranged();
        siege1 = new Siege();
        siege2 = new Siege();
    }

    @Test
    public void testLaCartaTorrentialRainSeAplicaCorrectamenteEnLasFilasSiege() {
        Unit carta = new Unit("carta", "comun", 6, new SiegeType(), List.of());

        siege1.placeCard(carta);
        siege2.placeCard(carta);

        rain.apply(carta, siege1);
        rain.apply(carta, siege2);

        assertEquals(1, carta.calculatePoints());
    }

    @Test
    public void testLaCartaTorrentialRainUnicamenteAfectaALasFilasSiege() {
        Section specialZone = new SpecialZone(closeCombat1, ranged1, siege1, closeCombat2, ranged2, siege2);
        Unit carta1 = new Unit("carta", "comun", 6, new SiegeType(), List.of());
        Unit carta2 = new Unit("nombre", "descripcion", 7, new CloseCombatType(), List.of());
        Unit carta3 = new Unit("nombre", "descripcion", 5, new RangedType(), List.of());

        closeCombat1.placeCard(carta2);
        closeCombat2.placeCard(carta2);
        ranged1.placeCard(carta3);
        ranged2.placeCard(carta3);
        siege1.placeCard(carta1);
        siege2.placeCard(carta1);
        rain.play(specialZone);

        assertEquals(7, carta2.calculatePoints());
        assertEquals(5, carta3.calculatePoints());
        assertEquals(1, carta1.calculatePoints());
    }
}
