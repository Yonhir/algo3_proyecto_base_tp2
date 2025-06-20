package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroTest {
    private Unit cartaConLegendaria;
    private HeroStrategy estrategia;
    private Row closeCombat;
    private Row ranged;
    private Row siege;

    @BeforeEach
    void setUp() {
        cartaConLegendaria = new Unit("cerys", "descripcion", 10, List.of(new CloseCombatType()), List.of());
        estrategia = new HeroStrategy();
        cartaConLegendaria.setStrategy(estrategia);
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
        Unit cardMoraleBoost = new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), List.of(modifierMoral));
        cardMoraleBoost.setStrategy(new CommonStrategy());
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
}