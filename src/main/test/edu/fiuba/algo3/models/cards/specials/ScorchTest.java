package edu.fiuba.algo3.models.cards.specials;

import edu.fiuba.algo3.models.colors.*;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.cards.units.modifiers.MoraleBoostModifier;
import edu.fiuba.algo3.models.cards.units.modifiers.TightBond;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SiegeType;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScorchTest {
    private Special tierraArrasada;
    private CloseCombat closeCombatRow1;
    private Ranged rangedRow1;
    private Siege siegeRow1;
    private CloseCombat closeCombatRow2;
    private Ranged rangedRow2;
    private Siege siegeRow2;
    private SpecialZone specialZone;
    private DiscardPile discardPile1;
    private DiscardPile discardPile2;
    private Round round;

    @BeforeEach
    void setUp() {
        tierraArrasada = new Scorch("Tierra arrasada", "Desscripcion", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));
        tierraArrasada.setColor(new Blue());

        discardPile1 = new DiscardPile();
        discardPile2 = new DiscardPile();
        discardPile1.setColor(new Blue());
        discardPile2.setColor(new Red());
        closeCombatRow1 = new CloseCombat(discardPile1);
        rangedRow1 = new Ranged(discardPile1);
        siegeRow1 = new Siege(discardPile1);
        closeCombatRow2 = new CloseCombat(discardPile2);
        rangedRow2 = new Ranged(discardPile2);
        siegeRow2 = new Siege(discardPile2);
        specialZone = new SpecialZone(closeCombatRow1, rangedRow1, siegeRow1, closeCombatRow2, rangedRow2, siegeRow2, discardPile1, discardPile2);
        Player player1 = new Player("nombre", new Deck(), discardPile1, closeCombatRow1, rangedRow1, siegeRow1, new Blue());
        Player player2 = new Player("nombre", new Deck(), discardPile2, closeCombatRow2, rangedRow2, siegeRow2, new Red());
        round = new Round(player1, player2);
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminaLaCartaMasFuerteDeLaFilaCloseCombatCorrectamente() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 6, new CloseCombatType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        unidad1.setColor(new Blue());
        unidad2.setColor(new Blue());

        closeCombatRow1.placeCard(unidad1, round);
        closeCombatRow1.placeCard(unidad2, round);
        tierraArrasada.play(specialZone);

        assertFalse(closeCombatRow1.containsCard(unidad1));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminaLaCartaMasFuerteDeLaFilaRangedCorrectamente() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 4, new RangedType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 6, new RangedType(), List.of(new MoraleBoostModifier()));
        unidad1.setColor(new Red());
        unidad2.setColor(new Red());

        rangedRow2.placeCard(unidad1, round);
        rangedRow2.placeCard(unidad2, round);
        tierraArrasada.play(specialZone);

        assertFalse(rangedRow2.containsCard(unidad2));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminaLaCartaMasFuerteDeLaFilaSiegeCorrectamente() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 4, new SiegeType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 6, new SiegeType(), List.of(new MoraleBoostModifier()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of());
        unidad1.setColor(new Blue());
        unidad2.setColor(new Blue());
        unidad3.setColor(new Blue());

        siegeRow1.placeCard(unidad1, round);
        siegeRow1.placeCard(unidad2, round);
        siegeRow1.placeCard(unidad3, round);
        tierraArrasada.play(specialZone);

        assertFalse(siegeRow1.containsCard(unidad3));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeQuemanLasCartasMasFuertesDeLaFilaCloseCombat() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 6, new CloseCombatType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 6, new CloseCombatType(), List.of());
        unidad1.setColor(new Blue());
        unidad2.setColor(new Blue());
        unidad3.setColor(new Blue());

        closeCombatRow1.placeCard(unidad1, round);
        closeCombatRow1.placeCard(unidad2, round);
        tierraArrasada.play(specialZone);

        assertFalse(closeCombatRow1.containsCard(unidad1));
        assertFalse(closeCombatRow1.containsCard(unidad3));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeQuemanLasCartasMasFuertesDeLaFilaRanged() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 4, new RangedType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of(new MoraleBoostModifier()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of());
        unidad1.setColor(new Blue());
        unidad2.setColor(new Blue());
        unidad3.setColor(new Blue());

        rangedRow1.placeCard(unidad1, round);
        rangedRow1.placeCard(unidad2, round);
        tierraArrasada.play(specialZone);

        assertFalse(rangedRow1.containsCard(unidad2));
        assertFalse(rangedRow1.containsCard(unidad3));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeLaFilaSiege() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of(new TightBond()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of());
        Unit unidad3 = new Unit("Nombre", "Descripcion", 6, new SiegeType(), List.of(new MoraleBoostModifier()));
        unidad1.setColor(new Blue());
        unidad2.setColor(new Blue());
        unidad3.setColor(new Blue());

        siegeRow1.placeCard(unidad1, round);
        siegeRow1.placeCard(unidad2, round);
        siegeRow1.placeCard(unidad3, round);
        tierraArrasada.play(specialZone);

        assertFalse(siegeRow1.containsCard(unidad1));
        assertFalse(siegeRow1.containsCard(unidad2));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesDeTodasLasFilas() {
        Unit unidad1 = new Unit("Nombre", "Descripcion", 8, new SiegeType(), List.of(new TightBond()));
        Unit unidad5 = new Unit("Nombre", "Descripcion", 6, new SiegeType(), List.of(new MoraleBoostModifier()));
        Unit unidad2 = new Unit("Nombre", "Descripcion", 4, new RangedType(), List.of(new TightBond()));
        Unit unidad3 = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of());
        Unit unidad4 = new Unit("Nombre", "Descripcion", 8, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        unidad1.setColor(new Blue());
        unidad2.setColor(new Blue());
        unidad3.setColor(new Blue());
        unidad4.setColor(new Blue());
        unidad5.setColor(new Blue());

        closeCombatRow1.placeCard(unidad4, round);
        rangedRow1.placeCard(unidad2, round);
        rangedRow1.placeCard(unidad3, round);
        siegeRow1.placeCard(unidad1, round);
        siegeRow1.placeCard(unidad5, round);
        tierraArrasada.play(specialZone);

        assertFalse(siegeRow1.containsCard(unidad1));
        assertTrue(rangedRow1.containsCard(unidad3));
        assertTrue(closeCombatRow1.containsCard(unidad4));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaSeEliminanLasCartasMasFuertesTantoDelJugadorPrincipalComoDelOponente() {
        Unit unidad1JugadorP = new Unit("Nombre", "Descripcion", 8, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Unit unidad1Oponente = new Unit("Nombre", "Descripcion", 8, new CloseCombatType(), List.of(new TightBond()));

        Unit unidad2JugadorP = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of(new TightBond()));
        Unit unidad2Oponente = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of(new TightBond()));

        Unit unidad3JugadorP = new Unit("Nombre", "Descripcion", 7, new SiegeType(), List.of(new TightBond()));
        Unit unidad3Oponente = new Unit("Nombre", "Descripcion", 5, new SiegeType(), List.of(new MoraleBoostModifier()));
        unidad1JugadorP.setColor(new Blue());
        unidad1Oponente.setColor(new Red());
        unidad2JugadorP.setColor(new Blue());
        unidad2Oponente.setColor(new Red());
        unidad3JugadorP.setColor(new Blue());
        unidad3Oponente.setColor(new Red());

        closeCombatRow1.placeCard(unidad1JugadorP, round);
        rangedRow1.placeCard(unidad2JugadorP, round);
        siegeRow1.placeCard(unidad3JugadorP, round);

        closeCombatRow2.placeCard(unidad1Oponente, round);
        rangedRow2.placeCard(unidad2Oponente, round);
        siegeRow2.placeCard(unidad3Oponente, round);

        tierraArrasada.play(specialZone);

        assertFalse(closeCombatRow1.containsCard(unidad1JugadorP));
        assertFalse(rangedRow1.containsCard(unidad2JugadorP));
        assertTrue(siegeRow1.containsCard(unidad3JugadorP));

        assertFalse(closeCombatRow2.containsCard(unidad1Oponente));
        assertFalse(rangedRow2.containsCard(unidad2Oponente));
        assertTrue(siegeRow2.containsCard(unidad3Oponente));
    }

    @Test
    public void testSeJuegaUnaTierraArrasadaLasCartasQuemadasVanALaPilaDeDescarteCorrespondiente() {
        Unit unidad1JugadorP = new Unit("Nombre", "Descripcion", 8, new CloseCombatType(), List.of(new MoraleBoostModifier()));
        Unit unidad1Oponente = new Unit("Nombre", "Descripcion", 8, new CloseCombatType(), List.of(new TightBond()));

        Unit unidad2JugadorP = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of(new TightBond()));
        Unit unidad2Oponente = new Unit("Nombre", "Descripcion", 8, new RangedType(), List.of(new TightBond()));

        Unit unidad3JugadorP = new Unit("Nombre", "Descripcion", 7, new SiegeType(), List.of(new TightBond()));
        Unit unidad3Oponente = new Unit("Nombre", "Descripcion", 5, new SiegeType(), List.of(new MoraleBoostModifier()));

        unidad1JugadorP.setColor(new Blue());
        unidad1Oponente.setColor(new Red());
        unidad2JugadorP.setColor(new Blue());
        unidad2Oponente.setColor(new Red());
        unidad3JugadorP.setColor(new Blue());
        unidad3Oponente.setColor(new Red());


        closeCombatRow1.placeCard(unidad1JugadorP, round);
        rangedRow1.placeCard(unidad2JugadorP, round);
        siegeRow1.placeCard(unidad3JugadorP, round);

        closeCombatRow2.placeCard(unidad1Oponente, round);
        rangedRow2.placeCard(unidad2Oponente, round);
        siegeRow2.placeCard(unidad3Oponente, round);

        tierraArrasada.play(specialZone);

        assertTrue(discardPile1.containsCard(unidad1JugadorP));
        assertTrue(discardPile1.containsCard(unidad2JugadorP));
        assertFalse(discardPile1.containsCard(unidad3JugadorP));
        assertTrue(siegeRow1.containsCard(unidad3JugadorP));

        assertTrue(discardPile2.containsCard(unidad1Oponente));
        assertTrue(discardPile2.containsCard(unidad2Oponente));
        assertFalse(discardPile2.containsCard(unidad3Oponente));
        assertTrue(siegeRow2.containsCard(unidad3Oponente));
    }
}
