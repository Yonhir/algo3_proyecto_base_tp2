package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SpecialZoneTest {
    private SpecialZone specialZone;
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
        specialZone = new SpecialZone(
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
}
