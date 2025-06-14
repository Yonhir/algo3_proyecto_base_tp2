package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherTest {
    private Row closeCombatRow;
    private Row rangedRow;
    private Row siegeRow;
    private SpecialZone specialZone;

    private Unit soldier;
    private Unit archer;
    private Unit catapult;
    
    private Weather frostWeather;
    private Weather fogWeather;
    private Weather rainWeather;
    private Weather clearWeather;

    @BeforeEach
    public void setup() {
        // Initialize rows
        closeCombatRow = new CloseCombat();
        rangedRow = new Ranged();
        siegeRow = new Siege();
        
        // Initialize weather zone
        specialZone = new SpecialZone(
            List.of(closeCombatRow),
            List.of(rangedRow),
            List.of(siegeRow)
        );
        
        // Initialize units
        soldier = new Unit("soldado", "pelea de cerca", 10, new CloseCombatType(), List.of());
        archer = new Unit("arquero", "tira flechas", 8, new RangedType(), List.of());
        catapult = new Unit("catapulta", "arma de asedio", 12, new SiegeType(), List.of());
        
        // Initialize weather cards
        frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");
        fogWeather = new ImpenetrableFog("Niebla", "Reduce todas las unidades a distancia a 1 punto");
        rainWeather = new TorrentialRain("Lluvia", "Reduce todas las unidades de asedio a 1 punto");
        clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
    }

    @Test
    public void testWeatherPlayAppliesEffectToTarget() {
        // Arrange
        closeCombatRow.placeCard(soldier);
        
        // Act
        frostWeather.play(specialZone);
        
        // Assert
        assertEquals(1, soldier.calculatePoints(), "El clima debería aplicar su efecto al ser jugado");
    }

    @Test
    public void testBitingFrostReducesCloseCombatUnitPoints() {
        // Act
        closeCombatRow.placeCard(soldier);
        closeCombatRow.addWeather(frostWeather);

        // Assert
        assertEquals(1, soldier.calculatePoints(), "La escarcha debería reducir los puntos de la unidad cuerpo a cuerpo a 1");
    }

    @Test
    public void testBitingFrostDoesNotAffectRangedUnits() {
        // Act
        rangedRow.placeCard(archer);
        rangedRow.addWeather(frostWeather);
        
        // Assert
        assertEquals(8, archer.calculatePoints(), "La escarcha no debería afectar a las unidades a distancia");
    }

    @Test
    public void testImpenetrableFogReducesRangedUnitPoints() {
        // Act
        rangedRow.placeCard(archer);
        rangedRow.addWeather(fogWeather);
        
        // Assert
        assertEquals(1, archer.calculatePoints(), "La niebla debería reducir los puntos de la unidad a distancia a 1");
    }

    @Test
    public void testImpenetrableFogDoesNotAffectCloseCombatUnits() {
        // Act
        closeCombatRow.placeCard(soldier);
        closeCombatRow.addWeather(fogWeather);
        
        // Assert
        assertEquals(10, soldier.calculatePoints(), "La niebla no debería afectar a las unidades cuerpo a cuerpo");
    }

    @Test
    public void testTorrentialRainReducesSiegeUnitPoints() {
        // Act
        siegeRow.placeCard(catapult);
        siegeRow.addWeather(rainWeather);
        
        // Assert
        assertEquals(1, catapult.calculatePoints(), "La lluvia debería reducir los puntos de la unidad de asedio a 1");
    }

    @Test
    public void testTorrentialRainDoesNotAffectCloseCombatUnits() {
        // Act
        closeCombatRow.placeCard(soldier);
        closeCombatRow.addWeather(rainWeather);
        
        // Assert
        assertEquals(10, soldier.calculatePoints(), "La lluvia no debería afectar a las unidades cuerpo a cuerpo");
    }

    @Test
    public void testBitingFrostAffectsNewCloseCombatUnit() {
        // Act
        closeCombatRow.addWeather(frostWeather);
        closeCombatRow.placeCard(soldier);
        
        // Assert
        assertEquals(1, soldier.calculatePoints(), "La escarcha debería afectar a las nuevas unidades cuerpo a cuerpo");
    }

    @Test
    public void testImpenetrableFogAffectsNewRangedUnit() {
        // Act
        rangedRow.addWeather(fogWeather);
        rangedRow.placeCard(archer);
        
        // Assert
        assertEquals(1, archer.calculatePoints(), "La niebla debería afectar a las nuevas unidades a distancia");
    }

    @Test
    public void testTorrentialRainAffectsNewSiegeUnit() {
        // Act
        siegeRow.addWeather(rainWeather);
        siegeRow.placeCard(catapult);
        
        // Assert
        assertEquals(1, catapult.calculatePoints(), "La lluvia debería afectar a las nuevas unidades de asedio");
    }

    @Test
    public void testClearWeatherRemovesFrostEffect() {
        // Arrange
        closeCombatRow.placeCard(soldier);
        closeCombatRow.addWeather(frostWeather);
        
        // Act
        closeCombatRow.addWeather(clearWeather);
        
        // Assert
        assertEquals(10, soldier.calculatePoints(), "Las unidades cuerpo a cuerpo deberían volver a sus puntos originales");
    }

    @Test
    public void testClearWeatherRemovesFogEffect() {
        // Arrange
        rangedRow.placeCard(archer);
        rangedRow.addWeather(fogWeather);
        
        // Act
        rangedRow.addWeather(clearWeather);
        
        // Assert
        assertEquals(8, archer.calculatePoints(), "Las unidades a distancia deberían volver a sus puntos originales");
    }

    @Test
    public void testClearWeatherRemovesRainEffect() {
        // Arrange
        siegeRow.placeCard(catapult);
        siegeRow.addWeather(rainWeather);
        
        // Act
        siegeRow.addWeather(clearWeather);
        
        // Assert
        assertEquals(12, catapult.calculatePoints(), "Las unidades de asedio deberían volver a sus puntos originales");
    }
    
    @Test
    public void testMultipleWeatherEffectsDoNotInterfereWithCloseCombat() {
        // Act
        closeCombatRow.placeCard(soldier);
        specialZone.placeCard(fogWeather);
        specialZone.placeCard(rainWeather);
        specialZone.placeCard(frostWeather);
        
        // Assert
        assertEquals(1, soldier.calculatePoints(), "La escarcha debería ser el único efecto que afecta a las unidades cuerpo a cuerpo");
    }

    @Test
    public void testMultipleWeatherEffectsDoNotInterfereWithRanged() {
        // Act
        rangedRow.placeCard(archer);
        specialZone.placeCard(frostWeather);
        specialZone.placeCard(rainWeather);
        specialZone.placeCard(fogWeather);
        
        // Assert
        assertEquals(1, archer.calculatePoints(), "La niebla debería ser el único efecto que afecta a las unidades a distancia");
    }

    @Test
    public void testMultipleWeatherEffectsDoNotInterfereWithSiege() {
        // Act
        siegeRow.placeCard(catapult);
        specialZone.placeCard(frostWeather);
        specialZone.placeCard(fogWeather);
        specialZone.placeCard(rainWeather);
        
        // Assert
        assertEquals(1, catapult.calculatePoints(), "La lluvia debería ser el único efecto que afecta a las unidades de asedio");
    }

    @Test
    public void testWeatherCannotBePlacedInInvalidTarget() {
        // Act
        assertThrows(SectionTypeMismatchError.class, () -> closeCombatRow.placeCard(frostWeather),
            "Debería lanzar una excepción al intentar colocar clima en una fila normal");
    }
}
