package edu.fiuba.algo3.models.cards.units.modifiers;

import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.specials.MoraleBoost;
import edu.fiuba.algo3.models.cards.specials.Scorch;
import edu.fiuba.algo3.models.cards.specials.weathers.BitingFrost;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.colors.Blue;
import edu.fiuba.algo3.models.colors.Red;
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
    private DiscardPile discardPile1;
    private DiscardPile discardPile2;
    private Round round;
    private int puntosEsperados;

    @BeforeEach
    void setUp() {
        Player player1 = new Player("jugador 1", new Blue());
        Player player2 = new Player("jugador 2", new Red());

        cartaConLegendaria = new Unit("cerys", "descripcion", 10, List.of(new CloseCombatType()), List.of(new Hero()));
        discardPile1 = player1.getDiscardPile();
        discardPile2 = player2.getDiscardPile();
        cartaConLegendaria.setColor(new Blue());
        closeCombat1 = player1.getCloseCombatRow();
        ranged1 = player1.getRangedRow();
        siege1 = player1.getSiegeRow();
        closeCombat2 = player2.getCloseCombatRow();
        ranged2 = player2.getRangedRow();
        siege2 = player2.getSiegeRow();

        round = new Round(player1, player2);
        puntosEsperados = 10;
    }

    @Test
    public void testSeJuegaUnaCartaConModificardorLegendariaCorrectamente() {
        closeCombat1.placeCard(cartaConLegendaria, round);

        assertEquals(puntosEsperados, closeCombat1.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorOtroModificador() {
        MoraleBoostModifier modifierMoral = new MoraleBoostModifier();
        Unit cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), List.of(modifierMoral));
        cardMoraleBoost.setColor(new Blue());

        closeCombat1.placeCard(cartaConLegendaria, round);
        closeCombat1.placeCard(cardMoraleBoost, round);

        assertEquals(puntosEsperados, cartaConLegendaria.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorCartaEspecial() {
        MoraleBoost especial = new MoraleBoost("MoraleBoost", "X2", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));
        especial.setColor(new Blue());

        closeCombat1.placeCard(cartaConLegendaria, round);
        especial.play(closeCombat1);

        assertEquals(puntosEsperados, cartaConLegendaria.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorCartaClima() {
        BitingFrost frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");

        closeCombat1.placeCard(cartaConLegendaria, round);
        frostWeather.play(new SpecialZone(
                closeCombat1, ranged1, siege1,
                closeCombat2, ranged2, siege2,
                discardPile1, discardPile2
        ));

        assertEquals(puntosEsperados, cartaConLegendaria.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorCartasEspeciales() {
        MoraleBoost especial = new MoraleBoost("MoraleBoost", "X2", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));
        especial.setColor(new Blue());
        BitingFrost frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");

        closeCombat1.placeCard(cartaConLegendaria, round);
        especial.play(closeCombat1);
        frostWeather.play(new SpecialZone(
                closeCombat1, ranged1, siege1,
                closeCombat2, ranged2, siege2,
                discardPile1, discardPile2
        ));

        assertEquals(puntosEsperados, cartaConLegendaria.calculatePoints());
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsQuemadaPorUnaTierraArrasada() {
        Scorch tierraArrasada = new Scorch("Tierra arrasada", "Descripcion", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));
        tierraArrasada.setColor(new Blue());

        closeCombat1.placeCard(cartaConLegendaria, round);

        tierraArrasada.play(new SpecialZone(
                closeCombat1, ranged1, siege1,
                closeCombat2, ranged2, siege2,
                discardPile1, discardPile2
        ));

        assertTrue(closeCombat1.containsCard(cartaConLegendaria));
    }
}