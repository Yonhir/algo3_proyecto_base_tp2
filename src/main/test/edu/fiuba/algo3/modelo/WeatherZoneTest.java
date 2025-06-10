package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherZoneTest {
    private WeatherZone weatherZone;
    private Row player1CloseCombatRow;
    private Row player1RangedRow;
    private Row player1SiegeRow;
    private Row player2CloseCombatRow;
    private Row player2RangedRow;
    private Row player2SiegeRow;
    
    private Unit player1Soldier;
    private Unit player1Archer;
    private Unit player1Catapult;
    private Unit player2Soldier;
    private Unit player2Archer;
    private Unit player2Catapult;
    
    private Weather frostWeather;
    private Weather fogWeather;
    private Weather rainWeather;

    @BeforeEach
    public void setup() {
        // Initialize rows for both players
        player1CloseCombatRow = new CloseCombat();
        player1RangedRow = new Ranged();
        player1SiegeRow = new Siege();
        player2CloseCombatRow = new CloseCombat();
        player2RangedRow = new Ranged();
        player2SiegeRow = new Siege();

        // Initialize weather zone with both players' rows
        weatherZone = new WeatherZone(
            List.of(player1CloseCombatRow, player2CloseCombatRow),
            List.of(player1RangedRow, player2RangedRow),
            List.of(player1SiegeRow, player2SiegeRow)
        );
        
        // Initialize units for both players
        player1Soldier = new Unit("soldado1", "pelea de cerca", 10, true, false, false, List.of());
        player1Archer = new Unit("arquero1", "tira flechas", 8, false, true, false, List.of());
        player1Catapult = new Unit("catapulta1", "arma de asedio", 12, false, false, true, List.of());
        player2Soldier = new Unit("soldado2", "pelea de cerca", 10, true, false, false, List.of());
        player2Archer = new Unit("arquero2", "tira flechas", 8, false, true, false, List.of());
        player2Catapult = new Unit("catapulta2", "arma de asedio", 12, false, false, true, List.of());
        
        // Initialize weather cards
        frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");
        fogWeather = new ImpenetrableFog("Niebla", "Reduce todas las unidades a distancia a 1 punto");
        rainWeather = new TorrentialRain("Lluvia", "Reduce todas las unidades de asedio a 1 punto");
    }

    private void setupAllWeatherEffects() {
        player1CloseCombatRow.placeCard(player1Soldier);
        player1RangedRow.placeCard(player1Archer);
        player1SiegeRow.placeCard(player1Catapult);
        
        weatherZone.placeCard(frostWeather);
        weatherZone.placeCard(fogWeather);
        weatherZone.placeCard(rainWeather);
    }

    @Test
    public void testBitingFrostAffectsPlayer1CloseCombatRow() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier);
        player2CloseCombatRow.placeCard(player2Soldier);
        
        // Act
        weatherZone.placeCard(frostWeather);
        
        // Assert
        assertEquals(1, player1Soldier.calculatePoints(), "La escarcha debería afectar a las unidades cuerpo a cuerpo del jugador 1");
    }

    @Test
    public void testBitingFrostAffectsPlayer2CloseCombatRow() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier);
        player2CloseCombatRow.placeCard(player2Soldier);
        
        // Act
        weatherZone.placeCard(frostWeather);
        
        // Assert
        assertEquals(1, player2Soldier.calculatePoints(), "La escarcha debería afectar a las unidades cuerpo a cuerpo del jugador 2");
    }

    @Test
    public void testImpenetrableFogAffectsPlayer1RangedRow() {
        // Arrange
        player1RangedRow.placeCard(player1Archer);
        player2RangedRow.placeCard(player2Archer);
        
        // Act
        weatherZone.placeCard(fogWeather);
        
        // Assert
        assertEquals(1, player1Archer.calculatePoints(), "La niebla debería afectar a las unidades a distancia del jugador 1");
    }

    @Test
    public void testImpenetrableFogAffectsPlayer2RangedRow() {
        // Arrange
        player1RangedRow.placeCard(player1Archer);
        player2RangedRow.placeCard(player2Archer);
        
        // Act
        weatherZone.placeCard(fogWeather);
        
        // Assert
        assertEquals(1, player2Archer.calculatePoints(), "La niebla debería afectar a las unidades a distancia del jugador 2");
    }

    @Test
    public void testTorrentialRainAffectsPlayer1SiegeRow() {
        // Arrange
        player1SiegeRow.placeCard(player1Catapult);
        player2SiegeRow.placeCard(player2Catapult);
        
        // Act
        weatherZone.placeCard(rainWeather);
        
        // Assert
        assertEquals(1, player1Catapult.calculatePoints(), "La lluvia debería afectar a las unidades de asedio del jugador 1");
    }

    @Test
    public void testTorrentialRainAffectsPlayer2SiegeRow() {
        // Arrange
        player1SiegeRow.placeCard(player1Catapult);
        player2SiegeRow.placeCard(player2Catapult);
        
        // Act
        weatherZone.placeCard(rainWeather);
        
        // Assert
        assertEquals(1, player2Catapult.calculatePoints(), "La lluvia debería afectar a las unidades de asedio del jugador 2");
    }

    @Test
    public void testBitingFrostOnlyAffectsCloseCombatUnits() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier);
        player1RangedRow.placeCard(player1Archer);
        player1SiegeRow.placeCard(player1Catapult);
        
        // Act
        weatherZone.placeCard(frostWeather);
        
        // Assert
        assertEquals(1, player1Soldier.calculatePoints(), "La escarcha solo debería afectar a las unidades cuerpo a cuerpo");
    }

    @Test
    public void testImpenetrableFogOnlyAffectsRangedUnits() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier);
        player1RangedRow.placeCard(player1Archer);
        player1SiegeRow.placeCard(player1Catapult);
        
        // Act
        weatherZone.placeCard(fogWeather);
        
        // Assert
        assertEquals(1, player1Archer.calculatePoints(), "La niebla solo debería afectar a las unidades a distancia");
    }

    @Test
    public void testTorrentialRainOnlyAffectsSiegeUnits() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier);
        player1RangedRow.placeCard(player1Archer);
        player1SiegeRow.placeCard(player1Catapult);
        
        // Act
        weatherZone.placeCard(rainWeather);
        
        // Assert
        assertEquals(1, player1Catapult.calculatePoints(), "La lluvia solo debería afectar a las unidades de asedio");
    }

    @Test
    public void testNewCloseCombatUnitsAreAffectedByExistingFrost() {
        // Arrange
        weatherZone.placeCard(frostWeather);
        
        // Act
        player1CloseCombatRow.placeCard(player1Soldier);
        
        // Assert
        assertEquals(1, player1Soldier.calculatePoints(), "Las nuevas unidades cuerpo a cuerpo deberían ser afectadas por la escarcha");
    }

    @Test
    public void testNewRangedUnitsAreAffectedByExistingFog() {
        // Arrange
        weatherZone.placeCard(fogWeather);
        
        // Act
        player1RangedRow.placeCard(player1Archer);
        
        // Assert
        assertEquals(1, player1Archer.calculatePoints(), "Las nuevas unidades a distancia deberían ser afectadas por la niebla");
    }

    @Test
    public void testNewSiegeUnitsAreAffectedByExistingRain() {
        // Arrange
        weatherZone.placeCard(rainWeather);
        
        // Act
        player1SiegeRow.placeCard(player1Catapult);
        
        // Assert
        assertEquals(1, player1Catapult.calculatePoints(), "Las nuevas unidades de asedio deberían ser afectadas por la lluvia");
    }

    @Test
    public void testClearWeatherRemovesAllWeatherEffectsFromCloseCombat() {
        // Arrange
        setupAllWeatherEffects();
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        weatherZone.placeCard(clearWeather);
        
        // Assert
        assertEquals(10, player1Soldier.calculatePoints(), "Las unidades cuerpo a cuerpo deberían volver a sus puntos originales");
    }

    @Test
    public void testClearWeatherRemovesAllWeatherEffectsFromRanged() {
        // Arrange
        setupAllWeatherEffects();
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        weatherZone.placeCard(clearWeather);
        
        // Assert
        assertEquals(8, player1Archer.calculatePoints(), "Las unidades a distancia deberían volver a sus puntos originales");
    }

    @Test
    public void testClearWeatherRemovesAllWeatherEffectsFromSiege() {
        // Arrange
        setupAllWeatherEffects();
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        weatherZone.placeCard(clearWeather);
        
        // Assert
        assertEquals(12, player1Catapult.calculatePoints(), "Las unidades de asedio deberían volver a sus puntos originales");
    }

    @Test
    public void testFrostAffectsBothPlayersCloseCombat() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier);
        player2CloseCombatRow.placeCard(player2Soldier);
        
        // Act
        weatherZone.placeCard(frostWeather);
        
        // Assert
        assertEquals(1, player1Soldier.calculatePoints(), "La escarcha debería afectar a las unidades cuerpo a cuerpo del jugador 1");
        assertEquals(1, player2Soldier.calculatePoints(), "La escarcha debería afectar a las unidades cuerpo a cuerpo del jugador 2");
    }

    @Test
    public void testFogAffectsBothPlayersRanged() {
        // Arrange
        player1RangedRow.placeCard(player1Archer);
        player2RangedRow.placeCard(player2Archer);
        
        // Act
        weatherZone.placeCard(fogWeather);
        
        // Assert
        assertEquals(1, player1Archer.calculatePoints(), "La niebla debería afectar a las unidades a distancia del jugador 1");
        assertEquals(1, player2Archer.calculatePoints(), "La niebla debería afectar a las unidades a distancia del jugador 2");
    }

    @Test
    public void testRainAffectsBothPlayersSiege() {
        // Arrange
        player1SiegeRow.placeCard(player1Catapult);
        player2SiegeRow.placeCard(player2Catapult);
        
        // Act
        weatherZone.placeCard(rainWeather);
        
        // Assert
        assertEquals(1, player1Catapult.calculatePoints(), "La lluvia debería afectar a las unidades de asedio del jugador 1");
        assertEquals(1, player2Catapult.calculatePoints(), "La lluvia debería afectar a las unidades de asedio del jugador 2");
    }

    @Test
    public void testClearWeatherRemovesEffectsFromBothPlayers() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier);
        player1RangedRow.placeCard(player1Archer);
        player1SiegeRow.placeCard(player1Catapult);
        player2CloseCombatRow.placeCard(player2Soldier);
        player2RangedRow.placeCard(player2Archer);
        player2SiegeRow.placeCard(player2Catapult);
        
        // Apply all weather effects
        weatherZone.placeCard(frostWeather);
        weatherZone.placeCard(fogWeather);
        weatherZone.placeCard(rainWeather);
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        weatherZone.placeCard(clearWeather);
        
        // Assert
        assertEquals(10, player1Soldier.calculatePoints(), "Las unidades cuerpo a cuerpo del jugador 1 deberían volver a sus puntos originales");
        assertEquals(8, player1Archer.calculatePoints(), "Las unidades a distancia del jugador 1 deberían volver a sus puntos originales");
        assertEquals(12, player1Catapult.calculatePoints(), "Las unidades de asedio del jugador 1 deberían volver a sus puntos originales");
        assertEquals(10, player2Soldier.calculatePoints(), "Las unidades cuerpo a cuerpo del jugador 2 deberían volver a sus puntos originales");
        assertEquals(8, player2Archer.calculatePoints(), "Las unidades a distancia del jugador 2 deberían volver a sus puntos originales");
        assertEquals(12, player2Catapult.calculatePoints(), "Las unidades de asedio del jugador 2 deberían volver a sus puntos originales");
    }
}
