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
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgileTest {

    @Test
    public void testLaCartaConModificadorAgileSePuedeUbicarEnLaFilaCloseCombatYRanged() {
        Row closeCombat = new CloseCombat();
        Row ranged = new Ranged();
        Card cartaConAgile = new Unit("carta con agile", "con agile", 4, List.of(new CloseCombatType(), new RangedType()), List.of(new Agile()));

        cartaConAgile.play(closeCombat);
        cartaConAgile.play(ranged);

        assertTrue(closeCombat.containsCard(cartaConAgile));
        assertTrue(ranged.containsCard(cartaConAgile));
    }

    @Test
    public void testLaCartaConModificadorAgileSePuedeUbicarEnLasTresFilas() {
        Row closeCombat = new CloseCombat();
        Row ranged = new Ranged();
        Row siege = new Siege();
        Card cartaConAgile = new Unit("carta con agile", "con agile", 4, List.of(new CloseCombatType(), new RangedType(), new SiegeType()), List.of(new Agile()));

        cartaConAgile.play(closeCombat);
        cartaConAgile.play(ranged);
        cartaConAgile.play(siege);

        assertTrue(closeCombat.containsCard(cartaConAgile));
        assertTrue(ranged.containsCard(cartaConAgile));
        assertTrue(siege.containsCard(cartaConAgile));
    }
}
