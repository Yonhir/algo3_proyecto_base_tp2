package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.specials.MoraleBoost;
import edu.fiuba.algo3.modelo.cards.specials.Scorch;
import edu.fiuba.algo3.modelo.cards.specials.weathers.BitingFrost;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
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
    private CloseCombat closeCombat1;
    private Ranged ranged1;
    private Siege siege1;
    private CloseCombat closeCombat2;
    private Ranged ranged2;
    private Siege siege2;

    private int puntosEsperados;

    @BeforeEach
    void setUp() {
        cartaConLegendaria = new Unit("cerys", "descripcion", 10, List.of(new CloseCombatType()), List.of(new Hero()));
        DiscardPile discardPile1 = new DiscardPile();
        DiscardPile discardPile2 = new DiscardPile();
        closeCombat1 = new CloseCombat(discardPile1);
        ranged1 = new Ranged(discardPile1);
        siege1 = new Siege(discardPile1);
        closeCombat2 = new CloseCombat(discardPile2);
        ranged2 = new Ranged(discardPile2);
        siege2 = new Siege(discardPile2);

        puntosEsperados = 10;
    }

    @Test
    public void testSeJuegaUnaCartaConModificardorLegendariaCorrectamente() {
        closeCombat1.placeCard(cartaConLegendaria);

        assertEquals(puntosEsperados, closeCombat1.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorOtroModificador() {
        MoraleBoostModifier modifierMoral = new MoraleBoostModifier();
        Unit cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), List.of(modifierMoral));

        closeCombat1.placeCard(cartaConLegendaria);
        closeCombat1.placeCard(cardMoraleBoost);

        assertEquals(puntosEsperados, cartaConLegendaria.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorCartaEspecial() {
        MoraleBoost especial = new MoraleBoost("MoraleBoost", "X2", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));

        closeCombat1.placeCard(cartaConLegendaria);
        especial.play(closeCombat1);

        assertEquals(puntosEsperados, cartaConLegendaria.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorCartaClima() {
        BitingFrost frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");

        closeCombat1.placeCard(cartaConLegendaria);
        frostWeather.play(new SpecialZone(
                closeCombat1, ranged1, siege1,
                closeCombat2, ranged2, siege2
        ));

        assertEquals(puntosEsperados, cartaConLegendaria.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorCartasEspeciales() {
        MoraleBoost especial = new MoraleBoost("MoraleBoost", "X2", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));
        BitingFrost frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");

        closeCombat1.placeCard(cartaConLegendaria);
        especial.play(closeCombat1);
        frostWeather.play(new SpecialZone(
                closeCombat1, ranged1, siege1,
                closeCombat2, ranged2, siege2
        ));

        assertEquals(puntosEsperados, cartaConLegendaria.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsQuemadaPorUnaTierraArrasada() {
        Scorch tierraArrasada = new Scorch("Tierra arrasada", "Descripcion", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));

        closeCombat1.placeCard(cartaConLegendaria);

        tierraArrasada.play(new SpecialZone(
                closeCombat1, ranged1, siege1,
                closeCombat2, ranged2, siege2
        ));

        assertTrue(closeCombat1.containsCard(cartaConLegendaria));
    }
}