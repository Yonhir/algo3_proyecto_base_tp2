package edu.fiuba.algo3.modelo.cards.specials.weathers;

import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.colors.Blue;
import edu.fiuba.algo3.modelo.colors.Green;
import edu.fiuba.algo3.modelo.colors.PlayerColor;
import edu.fiuba.algo3.modelo.errors.SectionTypeMismatchError;
import edu.fiuba.algo3.modelo.sections.*;
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
    private final PlayerColor color = new Blue();
    private final PlayerColor green = new Green();


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

        soldier.setColor(color);
        archer.setColor(color);
        catapult.setColor(color);
        closeCombatRow.setColor(color);
        rangedRow.setColor(color);
        siegeRow.setColor(color);

        // Initialize weather cards
        frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");
        fogWeather = new ImpenetrableFog("Niebla", "Reduce todas las unidades a distancia a 1 punto");
        rainWeather = new TorrentialRain("Lluvia", "Reduce todas las unidades de asedio a 1 punto");
        clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        frostWeather.setColor(green);
        fogWeather.setColor(green);
        rainWeather.setColor(green);
        clearWeather.setColor(green);

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
        closeCombatRow.applyWeather(frostWeather);

        // Assert
        assertEquals(1, soldier.calculatePoints(), "La escarcha debería reducir los puntos de la unidad cuerpo a cuerpo a 1");
    }

    @Test
    public void testBitingFrostDoesNotAffectRangedUnits() {
        // Act
        rangedRow.placeCard(archer);
        rangedRow.applyWeather(frostWeather);
        
        // Assert
        assertEquals(8, archer.calculatePoints(), "La escarcha no debería afectar a las unidades a distancia");
    }

    @Test
    public void testImpenetrableFogReducesRangedUnitPoints() {
        // Act
        rangedRow.placeCard(archer);
        rangedRow.applyWeather(fogWeather);
        
        // Assert
        assertEquals(1, archer.calculatePoints(), "La niebla debería reducir los puntos de la unidad a distancia a 1");
    }

    @Test
    public void testImpenetrableFogDoesNotAffectCloseCombatUnits() {
        // Act
        closeCombatRow.placeCard(soldier);
        closeCombatRow.applyWeather(fogWeather);
        
        // Assert
        assertEquals(10, soldier.calculatePoints(), "La niebla no debería afectar a las unidades cuerpo a cuerpo");
    }

    @Test
    public void testTorrentialRainReducesSiegeUnitPoints() {
        // Act
        siegeRow.placeCard(catapult);
        siegeRow.applyWeather(rainWeather);
        
        // Assert
        assertEquals(1, catapult.calculatePoints(), "La lluvia debería reducir los puntos de la unidad de asedio a 1");
    }

    @Test
    public void testTorrentialRainDoesNotAffectCloseCombatUnits() {
        // Act
        closeCombatRow.placeCard(soldier);
        closeCombatRow.applyWeather(rainWeather);
        
        // Assert
        assertEquals(10, soldier.calculatePoints(), "La lluvia no debería afectar a las unidades cuerpo a cuerpo");
    }

    @Test
    public void testBitingFrostAffectsNewCloseCombatUnit() {
        // Act
        closeCombatRow.applyWeather(frostWeather);
        closeCombatRow.placeCard(soldier);
        
        // Assert
        assertEquals(1, soldier.calculatePoints(), "La escarcha debería afectar a las nuevas unidades cuerpo a cuerpo");
    }

    @Test
    public void testImpenetrableFogAffectsNewRangedUnit() {
        // Act
        rangedRow.applyWeather(fogWeather);
        rangedRow.placeCard(archer);
        
        // Assert
        assertEquals(1, archer.calculatePoints(), "La niebla debería afectar a las nuevas unidades a distancia");
    }

    @Test
    public void testTorrentialRainAffectsNewSiegeUnit() {
        // Act
        siegeRow.applyWeather(rainWeather);
        siegeRow.placeCard(catapult);
        
        // Assert
        assertEquals(1, catapult.calculatePoints(), "La lluvia debería afectar a las nuevas unidades de asedio");
    }

    @Test
    public void testClearWeatherRemovesFrostEffect() {
        // Arrange
        closeCombatRow.placeCard(soldier);
        closeCombatRow.applyWeather(frostWeather);
        
        // Act
        closeCombatRow.applyWeather(clearWeather);
        
        // Assert
        assertEquals(10, soldier.calculatePoints(), "Las unidades cuerpo a cuerpo deberían volver a sus puntos originales");
    }

    @Test
    public void testClearWeatherRemovesFogEffect() {
        // Arrange
        rangedRow.placeCard(archer);
        rangedRow.applyWeather(fogWeather);
        
        // Act
        rangedRow.applyWeather(clearWeather);
        
        // Assert
        assertEquals(8, archer.calculatePoints(), "Las unidades a distancia deberían volver a sus puntos originales");
    }

    @Test
    public void testClearWeatherRemovesRainEffect() {
        // Arrange
        siegeRow.placeCard(catapult);
        siegeRow.applyWeather(rainWeather);
        
        // Act
        siegeRow.applyWeather(clearWeather);
        
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

    @Test
    public void testClearWeatherCannotBePlacedInRow() {
        // Act & Assert
        assertThrows(SectionTypeMismatchError.class, () -> closeCombatRow.placeCard(clearWeather),
            "Debería lanzar una excepción al intentar colocar ClearWeather en una fila normal");
    }

    @Test
    public void testBitingFrostCannotBePlacedInRow() {
        // Act & Assert
        assertThrows(SectionTypeMismatchError.class, () -> closeCombatRow.placeCard(frostWeather),
            "Debería lanzar una excepción al intentar colocar BitingFrost en una fila normal");
    }

    @Test
    public void testImpenetrableFogCannotBePlacedInRow() {
        // Act & Assert
        assertThrows(SectionTypeMismatchError.class, () -> closeCombatRow.placeCard(fogWeather),
            "Debería lanzar una excepción al intentar colocar ImpenetrableFog en una fila normal");
    }

    @Test
    public void testTorrentialRainCannotBePlacedInRow() {
        // Act & Assert
        assertThrows(SectionTypeMismatchError.class, () -> closeCombatRow.placeCard(rainWeather),
            "Debería lanzar una excepción al intentar colocar TorrentialRain en una fila normal");
    }
}
