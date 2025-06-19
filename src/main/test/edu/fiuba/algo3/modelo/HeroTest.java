package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroTest {
    @Test
    public void testSeJuegaUnaCartaConModificardorLegendariaCorrectamente() {
        Card cartaConLegendaria = new Unit("cerys", "descripcion", 10, List.of(new CloseCombatType()), List.of(new Hero()));
        Row closeCombat = new CloseCombat();

        closeCombat.placeCard(cartaConLegendaria);

        int obtenido = closeCombat.calculatePoints();

        assertEquals(obtenido, 10);
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorOtroModificador() {
        Card cartaConLegendaria = new Unit("cerys", "descripcion", 10, List.of(new CloseCombatType()), List.of(new Hero()));
        Row closeCombat = new CloseCombat();
        Card cartaConMoraleBoost = new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), List.of(new MoraleBoostModifier()));

        closeCombat.placeCard(cartaConLegendaria);
        closeCombat.placeCard(cartaConMoraleBoost);

        int obtenido = closeCombat.calculatePoints();
        assertEquals(obtenido, 20);
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorCartaEspecial() {
        Card cartaConLegendaria = new Unit("cerys", "descripcion", 10, List.of(new CloseCombatType()), List.of(new Hero()));
        Row closeCombat = new CloseCombat();
        MoraleBoost moraleBoost = new MoraleBoost("MoraleBoost", "X2", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));

        closeCombat.placeCard(cartaConLegendaria);
        closeCombat.placeCard(moraleBoost);

        int obtenido = closeCombat.calculatePoints();
        assertEquals(obtenido, 10);
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorCartaClima() {
        Unit cartaConLegendaria = new Unit("cerys", "descripcion", 10, List.of(new CloseCombatType()), List.of(new Hero()));
        Row closeCombat = new CloseCombat();
        Row ranged = new Ranged();
        Row siege = new Siege();
        Weather frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");

        SpecialZone specialZone = new SpecialZone(
                List.of(closeCombat),
                List.of(ranged),
                List.of(siege)
        );

        closeCombat.placeCard(cartaConLegendaria);

        frostWeather.play(specialZone);

        assertEquals(cartaConLegendaria.calculatePoints(), 10);
    }

    @Test
    public void testCartaConModificadorLegendariaNoEsAfectadaPorCartasEspeciales() {
        Unit cartaConLegendaria = new Unit("cerys", "descripcion", 10, List.of(new CloseCombatType()), List.of(new Hero()));
        Row closeCombat = new CloseCombat();
        Row ranged = new Ranged();
        Row siege = new Siege();
        MoraleBoost moraleBoost = new MoraleBoost("MoraleBoost", "X2", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));
        Weather frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");

        SpecialZone specialZone = new SpecialZone(
                List.of(closeCombat),
                List.of(ranged),
                List.of(siege)
        );

        closeCombat.placeCard(cartaConLegendaria);
        closeCombat.placeCard(moraleBoost);
        frostWeather.play(specialZone);

        assertEquals(cartaConLegendaria.calculatePoints(), 10);
    }
}