package edu.fiuba.algo3.modelo.sections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.fiuba.algo3.modelo.cards.specials.*;
import edu.fiuba.algo3.modelo.cards.specials.weathers.*;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.colors.Blue;
import edu.fiuba.algo3.modelo.colors.Green;
import edu.fiuba.algo3.modelo.colors.Red;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpecialZoneTest {
    private SpecialZone specialZone;
    private CloseCombat player1CloseCombatRow;
    private Ranged player1RangedRow;
    private Siege player1SiegeRow;
    private CloseCombat player2CloseCombatRow;
    private Ranged player2RangedRow;
    private Siege player2SiegeRow;
    
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
        player1CloseCombatRow.setColor(new Blue());
        player1RangedRow.setColor(new Blue());
        player1SiegeRow.setColor(new Blue());
        player2CloseCombatRow.setColor(new Red());
        player2RangedRow.setColor(new Red());
        player2SiegeRow.setColor(new Red());

        // Initialize weather zone with both players' rows
        specialZone = new SpecialZone(
            player1CloseCombatRow, player1RangedRow, player1SiegeRow,
            player2CloseCombatRow, player2RangedRow, player2SiegeRow
        );
        
        // Initialize units for both players
        player1Soldier = new Unit("soldado1", "pelea de cerca", 10, new CloseCombatType(), List.of());
        player1Archer = new Unit("arquero1", "tira flechas", 8, new RangedType(), List.of());
        player1Catapult = new Unit("catapulta1", "arma de asedio", 12, new SiegeType(), List.of());
        player2Soldier = new Unit("soldado2", "pelea de cerca", 10, new CloseCombatType(), List.of());
        player2Archer = new Unit("arquero2", "tira flechas", 8, new RangedType(), List.of());
        player2Catapult = new Unit("catapulta2", "arma de asedio", 12, new SiegeType(), List.of());

        player1Soldier.setColor(new Blue());
        player1Archer.setColor(new Blue());
        player1Catapult.setColor(new Blue());
        player2Soldier.setColor(new Red());
        player2Archer.setColor(new Red());
        player2Catapult.setColor(new Red());

        // Initialize weather cards
        frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");
        fogWeather = new ImpenetrableFog("Niebla", "Reduce todas las unidades a distancia a 1 punto");
        rainWeather = new TorrentialRain("Lluvia", "Reduce todas las unidades de asedio a 1 punto");
        frostWeather.setColor(new Green());
        fogWeather.setColor(new Green());
        rainWeather.setColor(new Green());
    }

    private void setupAllWeatherEffects() {
        player1CloseCombatRow.placeCard(player1Soldier);
        player1RangedRow.placeCard(player1Archer);
        player1SiegeRow.placeCard(player1Catapult);
        
        specialZone.placeCard(frostWeather);
        specialZone.placeCard(fogWeather);
        specialZone.placeCard(rainWeather);
    }

    @Test
    public void testBitingFrostAffectsPlayer1CloseCombatRow() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier);
        player2CloseCombatRow.placeCard(player2Soldier);
        
        // Act
        specialZone.placeCard(frostWeather);
        
        // Assert
        assertEquals(1, player1Soldier.calculatePoints(), "La escarcha debería afectar a las unidades cuerpo a cuerpo del jugador 1");
    }

    @Test
    public void testBitingFrostAffectsPlayer2CloseCombatRow() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier);
        player2CloseCombatRow.placeCard(player2Soldier);
        
        // Act
        specialZone.placeCard(frostWeather);
        
        // Assert
        assertEquals(1, player2Soldier.calculatePoints(), "La escarcha debería afectar a las unidades cuerpo a cuerpo del jugador 2");
    }

    @Test
    public void testImpenetrableFogAffectsPlayer1RangedRow() {
        // Arrange
        player1RangedRow.placeCard(player1Archer);
        player2RangedRow.placeCard(player2Archer);
        
        // Act
        specialZone.placeCard(fogWeather);
        
        // Assert
        assertEquals(1, player1Archer.calculatePoints(), "La niebla debería afectar a las unidades a distancia del jugador 1");
    }

    @Test
    public void testImpenetrableFogAffectsPlayer2RangedRow() {
        // Arrange
        player1RangedRow.placeCard(player1Archer);
        player2RangedRow.placeCard(player2Archer);
        
        // Act
        specialZone.placeCard(fogWeather);
        
        // Assert
        assertEquals(1, player2Archer.calculatePoints(), "La niebla debería afectar a las unidades a distancia del jugador 2");
    }

    @Test
    public void testTorrentialRainAffectsPlayer1SiegeRow() {
        // Arrange
        player1SiegeRow.placeCard(player1Catapult);
        player2SiegeRow.placeCard(player2Catapult);
        
        // Act
        specialZone.placeCard(rainWeather);
        
        // Assert
        assertEquals(1, player1Catapult.calculatePoints(), "La lluvia debería afectar a las unidades de asedio del jugador 1");
    }

    @Test
    public void testTorrentialRainAffectsPlayer2SiegeRow() {
        // Arrange
        player1SiegeRow.placeCard(player1Catapult);
        player2SiegeRow.placeCard(player2Catapult);
        
        // Act
        specialZone.placeCard(rainWeather);
        
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
        specialZone.placeCard(frostWeather);
        
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
        specialZone.placeCard(fogWeather);
        
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
        specialZone.placeCard(rainWeather);
        
        // Assert
        assertEquals(1, player1Catapult.calculatePoints(), "La lluvia solo debería afectar a las unidades de asedio");
    }

    @Test
    public void testNewCloseCombatUnitsAreAffectedByExistingFrost() {
        // Arrange
        specialZone.placeCard(frostWeather);
        
        // Act
        player1CloseCombatRow.placeCard(player1Soldier);
        
        // Assert
        assertEquals(1, player1Soldier.calculatePoints(), "Las nuevas unidades cuerpo a cuerpo deberían ser afectadas por la escarcha");
    }

    @Test
    public void testNewRangedUnitsAreAffectedByExistingFog() {
        // Arrange
        specialZone.placeCard(fogWeather);
        
        // Act
        player1RangedRow.placeCard(player1Archer);
        
        // Assert
        assertEquals(1, player1Archer.calculatePoints(), "Las nuevas unidades a distancia deberían ser afectadas por la niebla");
    }

    @Test
    public void testNewSiegeUnitsAreAffectedByExistingRain() {
        // Arrange
        specialZone.placeCard(rainWeather);
        
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
        clearWeather.setColor(new Green());
        specialZone.placeCard(clearWeather);
        
        // Assert
        assertEquals(10, player1Soldier.calculatePoints(), "Las unidades cuerpo a cuerpo deberían volver a sus puntos originales");
    }

    @Test
    public void testClearWeatherRemovesAllWeatherEffectsFromRanged() {
        // Arrange
        setupAllWeatherEffects();
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        clearWeather.setColor(new Green());
        specialZone.placeCard(clearWeather);
        
        // Assert
        assertEquals(8, player1Archer.calculatePoints(), "Las unidades a distancia deberían volver a sus puntos originales");
    }

    @Test
    public void testClearWeatherRemovesAllWeatherEffectsFromSiege() {
        // Arrange
        setupAllWeatherEffects();
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        clearWeather.setColor(new Green());
        specialZone.placeCard(clearWeather);
        
        // Assert
        assertEquals(12, player1Catapult.calculatePoints(), "Las unidades de asedio deberían volver a sus puntos originales");
    }

    @Test
    public void testFrostAffectsBothPlayersCloseCombat() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier);
        player2CloseCombatRow.placeCard(player2Soldier);
        
        // Act
        specialZone.placeCard(frostWeather);
        
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
        specialZone.placeCard(fogWeather);
        
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
        specialZone.placeCard(rainWeather);
        
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
        specialZone.placeCard(frostWeather);
        specialZone.placeCard(fogWeather);
        specialZone.placeCard(rainWeather);
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        clearWeather.setColor(new Green());
        specialZone.placeCard(clearWeather);
        
        // Assert
        assertEquals(10, player1Soldier.calculatePoints(), "Las unidades cuerpo a cuerpo del jugador 1 deberían volver a sus puntos originales");
        assertEquals(8, player1Archer.calculatePoints(), "Las unidades a distancia del jugador 1 deberían volver a sus puntos originales");
        assertEquals(12, player1Catapult.calculatePoints(), "Las unidades de asedio del jugador 1 deberían volver a sus puntos originales");
        assertEquals(10, player2Soldier.calculatePoints(), "Las unidades cuerpo a cuerpo del jugador 2 deberían volver a sus puntos originales");
        assertEquals(8, player2Archer.calculatePoints(), "Las unidades a distancia del jugador 2 deberían volver a sus puntos originales");
        assertEquals(12, player2Catapult.calculatePoints(), "Las unidades de asedio del jugador 2 deberían volver a sus puntos originales");
    }

    @Test
    public void testSpecialZoneConstructor_ShouldThrowException_WhenCloseCombatRowsAreRepeated() {
        // Arrange
        CloseCombat sharedCloseCombatRow = new CloseCombat();
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SpecialZone(
                sharedCloseCombatRow, player1RangedRow, player1SiegeRow,
                sharedCloseCombatRow, player2RangedRow, player2SiegeRow
            );
        }, "Should throw IllegalArgumentException when close combat rows are repeated");
        
        assertEquals("Close combat, ranged and siege rows must be different", exception.getMessage(),
            "Exception message should indicate that rows must be different");
    }

    @Test
    public void testSpecialZoneConstructor_ShouldThrowException_WhenRangedRowsAreRepeated() {
        // Arrange
        Ranged sharedRangedRow = new Ranged();
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SpecialZone(
                player1CloseCombatRow, sharedRangedRow, player1SiegeRow,
                player2CloseCombatRow, sharedRangedRow, player2SiegeRow
            );
        }, "Should throw IllegalArgumentException when ranged rows are repeated");
        
        assertEquals("Close combat, ranged and siege rows must be different", exception.getMessage(),
            "Exception message should indicate that rows must be different");
    }

    @Test
    public void testSpecialZoneConstructor_ShouldThrowException_WhenSiegeRowsAreRepeated() {
        // Arrange
        Siege sharedSiegeRow = new Siege();
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SpecialZone(
                player1CloseCombatRow, player1RangedRow, sharedSiegeRow,
                player2CloseCombatRow, player2RangedRow, sharedSiegeRow
            );
        }, "Should throw IllegalArgumentException when siege rows are repeated");
        
        assertEquals("Close combat, ranged and siege rows must be different", exception.getMessage(),
            "Exception message should indicate that rows must be different");
    }

    @Test
    public void testSpecialZoneConstructor_ShouldThrowException_WhenMultipleRowsAreRepeated() {
        // Arrange
        CloseCombat sharedCloseCombatRow = new CloseCombat();
        Ranged sharedRangedRow = new Ranged();
        Siege sharedSiegeRow = new Siege();
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SpecialZone(
                sharedCloseCombatRow, sharedRangedRow, sharedSiegeRow,
                sharedCloseCombatRow, sharedRangedRow, sharedSiegeRow
            );
        }, "Should throw IllegalArgumentException when multiple rows are repeated");
        
        assertEquals("Close combat, ranged and siege rows must be different", exception.getMessage(),
            "Exception message should indicate that rows must be different");
    }
}
