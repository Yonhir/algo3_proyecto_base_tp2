package edu.fiuba.algo3.models.cards.units.modifiers;

import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.errors.SectionTypeMismatchError;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SiegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testGetDescription() {
        String expectedDescription = "Ágil: Se pueden ubicar en dos o más secciones";
        Agile agileModifier = new Agile();

        assertEquals(expectedDescription, agileModifier.getDescription());
    }
}
