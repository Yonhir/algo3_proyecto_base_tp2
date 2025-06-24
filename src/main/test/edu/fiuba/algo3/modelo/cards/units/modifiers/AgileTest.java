package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Row;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgileTest {
    Card cartaConAgile;
    Row closeCombat;
    Row ranged;
    Row siege;

    @BeforeEach
    void setUp() {
        cartaConAgile = new Unit("carta con agile", "con agile", 4, List.of(new CloseCombatType(), new RangedType(), new SiegeType()), List.of(new Agile()));
        closeCombat = new CloseCombat();
        ranged = new Ranged();
        siege = new Siege();
    }

    @Test
    public void testLaCartaConModificadorAgileSePuedeUbicarEnLaFilaCloseCombat() {
        cartaConAgile.play(closeCombat);

        assertTrue(closeCombat.containsCard(cartaConAgile));
    }

    @Test
    public void testLaCartaConModificadorAgileSePuedeUbicarEnLaFilaRanged() {
        cartaConAgile.play(ranged);

        assertTrue(ranged.containsCard(cartaConAgile));
    }

    @Test
    public void testLaCartaConModificadorAgileSePuedeUbicarEnLaFilaSiege() {
        cartaConAgile.play(siege);

        assertTrue(siege.containsCard(cartaConAgile));
    }
}
