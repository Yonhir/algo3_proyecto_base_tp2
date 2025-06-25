package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.errors.SectionTypeMismatchError;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgileTest {
    Card cartaConAgile;
    Card cartaConAgileDosFilas;
    Row closeCombat;
    Row ranged;
    Row siege;
    DiscardPile discardPile;

    @BeforeEach
    void setUp() {
        discardPile = new DiscardPile();
        cartaConAgile = new Unit("carta con agile", "con agile", 4, List.of(new CloseCombatType(), new RangedType(), new SiegeType()), List.of(new Agile()));
        cartaConAgileDosFilas = new Unit("carta con agile", "con agile", 2, List.of(new RangedType(), new SiegeType()), List.of(new Agile()));
        closeCombat = new CloseCombat(discardPile);
        ranged = new Ranged(discardPile);
        siege = new Siege(discardPile);
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

    @Test
    public void testSeLanzaExcepcionSiSeQuiereJugarLaCartaConModificadorAgileEnUnaFilaQueNoCorresponde() {
        cartaConAgileDosFilas.play(ranged);

        assertTrue(ranged.containsCard(cartaConAgileDosFilas));
        assertThrows(SectionTypeMismatchError.class, () -> cartaConAgileDosFilas.play(closeCombat));
    }
}
