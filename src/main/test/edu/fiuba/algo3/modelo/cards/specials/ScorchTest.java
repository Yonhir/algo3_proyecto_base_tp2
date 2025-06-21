package edu.fiuba.algo3.modelo.cards.specials;

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

public class ScorchTest {
    private Special tierraArrasada;
    private Row closeCombatRow;
    private Row rangedRow;
    private Row siegeRow;
    private SpecialZone specialZone;
    @BeforeEach
    void setUp() {
        tierraArrasada = new Scorch("Tierra arrasada", "Desscripcion", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));
        closeCombatRow = new CloseCombat();
        rangedRow = new Ranged();
        siegeRow = new Siege();
        specialZone = new SpecialZone(
                List.of(closeCombatRow),
                List.of(rangedRow),
                List.of(siegeRow)
        );
    }
    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminaLaCartaMasFuerteDeLaFilaCloseCombatCorrectamente() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 6, new CloseCombatType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        closeCombatRow.placeCard(unidad1);
        closeCombatRow.placeCard(unidad2);
        tierraArrasada.play(specialZone);

        assertFalse(closeCombatRow.getCards().contains(unidad1));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminaLaCartaMasFuerteDeLaFilaRangedCorrectamente() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 4, new RangedType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 6, new RangedType(), List.of(new MoraleBoostModifier()));

        rangedRow.placeCard(unidad1);
        rangedRow.placeCard(unidad2);
        tierraArrasada.play(specialZone);

        assertFalse(rangedRow.getCards().contains(unidad2));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminaLaCartaMasFuerteDeLaFilaSiegeCorrectamente() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 4, new SiegeType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 6, new SiegeType(), List.of(new MoraleBoostModifier()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of());

        siegeRow.placeCard(unidad1);
        siegeRow.placeCard(unidad2);
        siegeRow.placeCard(unidad3);
        tierraArrasada.play(specialZone);

        assertFalse(siegeRow.getCards().contains(unidad3));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeQuemanLasCartasMasFuertesDeLaFilaCloseCombat() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 6, new CloseCombatType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 6, new CloseCombatType(), List.of());

        closeCombatRow.placeCard(unidad1);
        closeCombatRow.placeCard(unidad2);
        tierraArrasada.play(specialZone);

        assertFalse(closeCombatRow.getCards().contains(unidad1));
        assertFalse(closeCombatRow.getCards().contains(unidad3));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeQuemanLasCartasMasFuertesDeLaFilaRanged() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 4, new RangedType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of(new MoraleBoostModifier()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of());

        rangedRow.placeCard(unidad1);
        rangedRow.placeCard(unidad2);
        tierraArrasada.play(specialZone);

        assertFalse(rangedRow.getCards().contains(unidad2));
        assertFalse(rangedRow.getCards().contains(unidad3));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminaLasCartasMasFuertesDeLaFilaSiege() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of());
        Unit unidad3 = new Unit("Nombre", "Descripcion", 6, new SiegeType(), List.of(new MoraleBoostModifier()));

        siegeRow.placeCard(unidad1);
        siegeRow.placeCard(unidad2);
        siegeRow.placeCard(unidad3);
        tierraArrasada.play(specialZone);

        assertFalse(siegeRow.getCards().contains(unidad1));
        assertFalse(siegeRow.getCards().contains(unidad2));
    }

    // falta test donde se juegue una Hero y esta no sea eliminada
}
