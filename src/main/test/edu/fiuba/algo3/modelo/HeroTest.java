package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.specials.MoraleBoost;
import edu.fiuba.algo3.modelo.cards.specials.Scorch;
import edu.fiuba.algo3.modelo.cards.specials.weathers.BitingFrost;
import edu.fiuba.algo3.modelo.cards.units.CommonStrategy;
import edu.fiuba.algo3.modelo.cards.units.HeroStrategy;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.cards.units.modifiers.MoraleBoostModifier;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeroTest {
    private Unit cartaConLegendaria;
    private HeroStrategy estrategia;
    private Row closeCombat;
    private Row ranged;
    private Row siege;

    @BeforeEach
    void setUp() {
        estrategia = new HeroStrategy();
        cartaConLegendaria = new Unit("cerys", "descripcion", 10, List.of(new CloseCombatType()), List.of(), estrategia);
        closeCombat = new CloseCombat();
        ranged = new Ranged();
        siege = new Siege();
    }

    @Test
    public void testSeJuegaUnaCartaConModificardorLegendariaCorrectamente() {
        int puntosEsperados = 10;

        closeCombat.placeCard(cartaConLegendaria);

        assertEquals(puntosEsperados, closeCombat.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorOtroModificador() {
        MoraleBoostModifier modifierMoral = new MoraleBoostModifier();
        Unit cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), List.of(modifierMoral), new CommonStrategy());
        int puntosEsperados = 10;

        closeCombat.placeCard(cartaConLegendaria);
        closeCombat.placeCard(cardMoraleBoost);

        assertEquals(puntosEsperados, cartaConLegendaria.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorCartaEspecial() {
        MoraleBoost especial = new MoraleBoost("MoraleBoost", "X2", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));
        int puntosEsperados = 10;

        closeCombat.placeCard(cartaConLegendaria);
        especial.play(closeCombat);

        assertEquals(puntosEsperados, cartaConLegendaria.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorCartaClima() {
        BitingFrost frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");
        int puntosEsperados = 10;

        closeCombat.placeCard(cartaConLegendaria);
        frostWeather.play(new SpecialZone(
                List.of(closeCombat),
                List.of(ranged),
                List.of(siege)
        ));

        assertEquals(puntosEsperados, cartaConLegendaria.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorCartasEspeciales() {
        int puntosEsperados = 10;
        MoraleBoost especial = new MoraleBoost("MoraleBoost", "X2", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));
        BitingFrost frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");

        closeCombat.placeCard(cartaConLegendaria);
        especial.play(closeCombat);
        frostWeather.play(new SpecialZone(
                List.of(closeCombat),
                List.of(ranged),
                List.of(siege)
        ));

        assertEquals(puntosEsperados, cartaConLegendaria.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsQuemadaPorUnaTierraArrasada() {
        Scorch tierraArrasada = new Scorch("Tierra arrasada", "Desscripcion", List.of(new CloseCombatType(), new RangedType(), new SiegeType()), new DiscardPile());

        closeCombat.placeCard(cartaConLegendaria);
        tierraArrasada.play(new SpecialZone(
                List.of(closeCombat),
                List.of(ranged),
                List.of(siege)
        ));

        assertTrue(closeCombat.containsCard(cartaConLegendaria));
    }
}