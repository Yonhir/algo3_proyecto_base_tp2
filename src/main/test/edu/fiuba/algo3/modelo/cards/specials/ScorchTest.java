package edu.fiuba.algo3.modelo.cards.specials;

import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.cards.units.modifiers.MoraleBoostModifier;
import edu.fiuba.algo3.modelo.cards.units.modifiers.TightBond;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScorchTest {
    private Special tierraArrasada;
    private Row closeCombatRow1;
    private Row rangedRow1;
    private Row siegeRow1;
    private Row closeCombatRow2;
    private Row rangedRow2;
    private Row siegeRow2;
    private SpecialZone specialZone;
    @BeforeEach
    void setUp() {
        tierraArrasada = new Scorch("Tierra arrasada", "Desscripcion", List.of(new CloseCombatType(), new RangedType(), new SiegeType()), new DiscardPile());
        closeCombatRow1 = new CloseCombat();
        rangedRow1 = new Ranged();
        siegeRow1 = new Siege();
        closeCombatRow2 = new CloseCombat();
        rangedRow2 = new Ranged();
        siegeRow2 = new Siege();
        specialZone = new SpecialZone(
                List.of(closeCombatRow1, closeCombatRow2),
                List.of(rangedRow1, rangedRow2),
                List.of(siegeRow1, siegeRow2)
        );
    }
    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminaLaCartaMasFuerteDeLaFilaCloseCombatCorrectamente() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 6, new CloseCombatType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        closeCombatRow1.placeCard(unidad1);
        closeCombatRow1.placeCard(unidad2);
        tierraArrasada.play(specialZone);

        assertFalse(closeCombatRow1.getCards().contains(unidad1));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminaLaCartaMasFuerteDeLaFilaRangedCorrectamente() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 4, new RangedType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 6, new RangedType(), List.of(new MoraleBoostModifier()));

        rangedRow2.placeCard(unidad1);
        rangedRow2.placeCard(unidad2);
        tierraArrasada.play(specialZone);

        assertFalse(rangedRow2.getCards().contains(unidad2));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminaLaCartaMasFuerteDeLaFilaSiegeCorrectamente() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 4, new SiegeType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 6, new SiegeType(), List.of(new MoraleBoostModifier()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of());

        siegeRow1.placeCard(unidad1);
        siegeRow1.placeCard(unidad2);
        siegeRow1.placeCard(unidad3);
        tierraArrasada.play(specialZone);

        assertFalse(siegeRow1.getCards().contains(unidad3));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeQuemanLasCartasMasFuertesDeLaFilaCloseCombat() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 6, new CloseCombatType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 6, new CloseCombatType(), List.of());

        closeCombatRow1.placeCard(unidad1);
        closeCombatRow1.placeCard(unidad2);
        tierraArrasada.play(specialZone);

        assertFalse(closeCombatRow1.getCards().contains(unidad1));
        assertFalse(closeCombatRow1.getCards().contains(unidad3));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeQuemanLasCartasMasFuertesDeLaFilaRanged() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 4, new RangedType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of(new MoraleBoostModifier()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of());

        rangedRow1.placeCard(unidad1);
        rangedRow1.placeCard(unidad2);
        tierraArrasada.play(specialZone);

        assertFalse(rangedRow1.getCards().contains(unidad2));
        assertFalse(rangedRow1.getCards().contains(unidad3));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeLaFilaSiege() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of());
        Unit unidad3 = new Unit("Nombre", "Descripcion", 6, new SiegeType(), List.of(new MoraleBoostModifier()));

        siegeRow1.placeCard(unidad1);
        siegeRow1.placeCard(unidad2);
        siegeRow1.placeCard(unidad3);
        tierraArrasada.play(specialZone);

        assertFalse(siegeRow1.getCards().contains(unidad1));
        assertFalse(siegeRow1.getCards().contains(unidad2));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeTodasLasFilas() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of(new TightBond()));
        Unit unidad5 = new Unit("Nombre", "Descripcion", 6, new SiegeType(), List.of(new MoraleBoostModifier()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 4, new RangedType(), List.of(new TightBond()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of());
        Unit unidad4 = new Unit("Nombre", "Descripcion", 8, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        closeCombatRow1.placeCard(unidad4);
        rangedRow1.placeCard(unidad2);
        rangedRow1.placeCard(unidad3);
        siegeRow1.placeCard(unidad1);
        siegeRow1.placeCard(unidad5);
        tierraArrasada.play(specialZone);

        assertFalse(siegeRow1.getCards().contains(unidad1));
        assertTrue(rangedRow1.getCards().contains(unidad3));
        assertTrue(closeCombatRow1.getCards().contains(unidad4));
    }

    @Test
    public void seJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesTantoDelJugadorPrincipalComoDelOponente() {
        Unit unidad1JugadorP = new Unit("Nombre", "Descripcion", 8, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Unit unidad1Oponente = new Unit("Nombre", "Descripcion", 8, new CloseCombatType(), List.of(new TightBond()));

        Unit unidad2JugadorP = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of(new TightBond()));
        Unit unidad2Oponente = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of(new TightBond()));

        Unit unidad3JugadorP = new Unit("Nombre", "Descripcion", 7, new SiegeType(), List.of(new TightBond()));
        Unit unidad3Oponente = new Unit("Nombre", "Descripcion", 5, new SiegeType(), List.of(new MoraleBoostModifier()));

        closeCombatRow1.placeCard(unidad1JugadorP);
        rangedRow1.placeCard(unidad2JugadorP);
        siegeRow1.placeCard(unidad3JugadorP);

        closeCombatRow2.placeCard(unidad1Oponente);
        rangedRow2.placeCard(unidad2Oponente);
        siegeRow2.placeCard(unidad3Oponente);

        tierraArrasada.play(specialZone);

        assertFalse(closeCombatRow1.getCards().contains(unidad1JugadorP));
        assertFalse(rangedRow1.getCards().contains(unidad2JugadorP));
        assertTrue(siegeRow1.getCards().contains(unidad3JugadorP));

        assertFalse(closeCombatRow2.getCards().contains(unidad1Oponente));
        assertFalse(rangedRow2.getCards().contains(unidad2Oponente));
        assertTrue(siegeRow2.getCards().contains(unidad3Oponente));
    }

    // falta test donde se juegue una Hero y esta no sea eliminada
}
