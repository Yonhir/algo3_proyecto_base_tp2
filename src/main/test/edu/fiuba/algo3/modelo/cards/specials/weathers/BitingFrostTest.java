package edu.fiuba.algo3.modelo.cards.specials.weathers;

import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.cards.units.modifiers.MoraleBoostModifier;
import edu.fiuba.algo3.modelo.sections.Section;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitingFrostTest {
    @Test
    public void testLaCartaTorrentialRainSeAplicaCorrectamenteEnLasFilasCloseCombat() {
        Weather frost = new BitingFrost("escarcha", "wow");
        CloseCombat closeCombat1 = new CloseCombat();
        CloseCombat closeCombat2 = new CloseCombat();
        Unit carta = new Unit("carta", "comun", 8, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        closeCombat1.placeCard(carta);
        closeCombat2.placeCard(carta);

        frost.apply(carta, closeCombat1);
        frost.apply(carta, closeCombat2);

        assertEquals(1, carta.calculatePoints());
    }

    @Test
    public void testLaCartaTorrentialRainUnicamenteAfectaALasFilasCloseCombat() {
        Weather frost = new BitingFrost("escarcha", "wow");
        CloseCombat closeCombat1 = new CloseCombat();
        CloseCombat closeCombat2 = new CloseCombat();
        Ranged ranged1 = new Ranged();
        Ranged ranged2 = new Ranged();
        Siege siege1 = new Siege();
        Siege siege2 = new Siege();
        Section specialZone = new SpecialZone(closeCombat1, ranged1, siege1, closeCombat2, ranged2, siege2);
        Unit carta1 = new Unit("carta", "comun", 2, new SiegeType(), List.of(new MoraleBoostModifier()));
        Unit carta2 = new Unit("nombre", "descripcion", 4, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Unit carta3 = new Unit("nombre", "descripcion", 8, new RangedType(), List.of(new MoraleBoostModifier()));

        closeCombat1.placeCard(carta2);
        closeCombat2.placeCard(carta2);
        ranged1.placeCard(carta3);
        ranged2.placeCard(carta3);
        siege1.placeCard(carta1);
        siege2.placeCard(carta1);
        frost.play(specialZone);

        assertEquals(2, carta1.calculatePoints());
        assertEquals(8, carta3.calculatePoints());
        assertEquals(1, carta2.calculatePoints());
    }
}
