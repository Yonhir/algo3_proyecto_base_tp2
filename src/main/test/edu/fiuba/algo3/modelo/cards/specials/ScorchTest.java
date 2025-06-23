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
    private CloseCombat closeCombat1;
    private Ranged ranged1;
    private Siege siege1;
    private CloseCombat closeCombat2;
    private Ranged ranged2;
    private Siege siege2;
    private SpecialZone specialZone;
    @BeforeEach
    void setUp() {
        tierraArrasada = new Scorch("Tierra arrasada", "Desscripcion", List.of(new CloseCombatType(), new RangedType(), new SiegeType()), new DiscardPile());
        closeCombat1 = new CloseCombat();
        ranged1 = new Ranged();
        siege1 = new Siege();
        closeCombat2 = new CloseCombat();
        ranged2 = new Ranged();
        siege2 = new Siege();
        specialZone = new SpecialZone(
                closeCombat1, ranged1, siege1,
                closeCombat2, ranged2, siege2
        );
    }
    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminaLaCartaMasFuerteDeLaFilaCloseCombatCorrectamente() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 6, new CloseCombatType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        closeCombat1.placeCard(unidad1);
        closeCombat1.placeCard(unidad2);
        tierraArrasada.play(specialZone);

        assertFalse(closeCombat1.containsCard(unidad1));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminaLaCartaMasFuerteDeLaFilaRangedCorrectamente() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 4, new RangedType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 6, new RangedType(), List.of(new MoraleBoostModifier()));

        ranged2.placeCard(unidad1);
        ranged2.placeCard(unidad2);
        tierraArrasada.play(specialZone);

        assertFalse(ranged2.containsCard(unidad2));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminaLaCartaMasFuerteDeLaFilaSiegeCorrectamente() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 4, new SiegeType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 6, new SiegeType(), List.of(new MoraleBoostModifier()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of());

        siege1.placeCard(unidad1);
        siege1.placeCard(unidad2);
        siege1.placeCard(unidad3);
        tierraArrasada.play(specialZone);

        assertFalse(siege1.containsCard(unidad3));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeQuemanLasCartasMasFuertesDeLaFilaCloseCombat() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 6, new CloseCombatType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 6, new CloseCombatType(), List.of());

        closeCombat1.placeCard(unidad1);
        closeCombat1.placeCard(unidad2);
        tierraArrasada.play(specialZone);

        assertFalse(closeCombat1.containsCard(unidad1));
        assertFalse(closeCombat1.containsCard(unidad3));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeQuemanLasCartasMasFuertesDeLaFilaRanged() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 4, new RangedType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of(new MoraleBoostModifier()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of());

        ranged1.placeCard(unidad1);
        ranged1.placeCard(unidad2);
        tierraArrasada.play(specialZone);

        assertFalse(ranged1.containsCard(unidad2));
        assertFalse(ranged1.containsCard(unidad3));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeLaFilaSiege() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of());
        Unit unidad3 = new Unit("Nombre", "Descripcion", 6, new SiegeType(), List.of(new MoraleBoostModifier()));

        siege1.placeCard(unidad1);
        siege1.placeCard(unidad2);
        siege1.placeCard(unidad3);
        tierraArrasada.play(specialZone);

        assertFalse(siege1.containsCard(unidad1));
        assertFalse(siege1.containsCard(unidad2));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeTodasLasFilas() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of(new TightBond()));
        Unit unidad5 = new Unit("Nombre", "Descripcion", 6, new SiegeType(), List.of(new MoraleBoostModifier()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 4, new RangedType(), List.of(new TightBond()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of());
        Unit unidad4 = new Unit("Nombre", "Descripcion", 8, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        closeCombat1.placeCard(unidad4);
        ranged1.placeCard(unidad2);
        ranged1.placeCard(unidad3);
        siege1.placeCard(unidad1);
        siege1.placeCard(unidad5);
        tierraArrasada.play(specialZone);

        assertFalse(siege1.containsCard(unidad1));
        assertTrue(ranged1.containsCard(unidad3));
        assertTrue(closeCombat1.containsCard(unidad4));
    }

    @Test
    public void seJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesTantoDelJugadorPrincipalComoDelOponente() {
        Unit unidad1JugadorP = new Unit("Nombre", "Descripcion", 8, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Unit unidad1Oponente = new Unit("Nombre", "Descripcion", 8, new CloseCombatType(), List.of(new TightBond()));

        Unit unidad2JugadorP = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of(new TightBond()));
        Unit unidad2Oponente = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of(new TightBond()));

        Unit unidad3JugadorP = new Unit("Nombre", "Descripcion", 7, new SiegeType(), List.of(new TightBond()));
        Unit unidad3Oponente = new Unit("Nombre", "Descripcion", 5, new SiegeType(), List.of(new MoraleBoostModifier()));

        closeCombat1.placeCard(unidad1JugadorP);
        ranged1.placeCard(unidad2JugadorP);
        siege1.placeCard(unidad3JugadorP);

        closeCombat2.placeCard(unidad1Oponente);
        ranged2.placeCard(unidad2Oponente);
        siege2.placeCard(unidad3Oponente);

        tierraArrasada.play(specialZone);

        assertFalse(closeCombat1.containsCard(unidad1JugadorP));
        assertFalse(ranged1.containsCard(unidad2JugadorP));
        assertTrue(siege1.containsCard(unidad3JugadorP));

        assertFalse(closeCombat2.containsCard(unidad1Oponente));
        assertFalse(ranged2.containsCard(unidad2Oponente));
        assertTrue(siege2.containsCard(unidad3Oponente));
    }
}
