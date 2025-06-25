package edu.fiuba.algo3.modelo.sections;


import edu.fiuba.algo3.modelo.Colors.Blue;
import edu.fiuba.algo3.modelo.Colors.Red;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.turnManagement.Player;
import edu.fiuba.algo3.modelo.turnManagement.Round;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import edu.fiuba.algo3.modelo.cards.specials.*;
import edu.fiuba.algo3.modelo.cards.specials.weathers.*;
import edu.fiuba.algo3.modelo.cards.units.Unit;
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

    private Player player;
    private Player opponent;
    private Round round;
    private Deck deck;

    private CloseCombat closeCombat;
    private Ranged ranged;
    private Siege siege;

    private DiscardPile discardPile1;
    private DiscardPile discardPile2;

    @BeforeEach
    public void setup() {

        discardPile1 = new DiscardPile();
        discardPile2 = new DiscardPile();
        deck = new Deck();
       // closeCombat = new CloseCombat(discardPile);
       // ranged = new Ranged(discardPile);
       // siege = new Siege(discardPile);
        // Initialize rows for both players
        player1CloseCombatRow = new CloseCombat(discardPile1);
        player1RangedRow = new Ranged(discardPile1);
        player1SiegeRow = new Siege(discardPile1);
        player2CloseCombatRow = new CloseCombat(discardPile2);
        player2RangedRow = new Ranged(discardPile2);
        player2SiegeRow = new Siege(discardPile2);
        player = new Player("Gabriel", deck, player1CloseCombatRow, player1RangedRow, player1SiegeRow, new Blue());
        opponent = new Player("Juan", deck, player2CloseCombatRow, player2RangedRow, player2SiegeRow, new Red());
        round = new Round(player, opponent);

        // Initialize weather zone with both players' rows
        specialZone = new SpecialZone(
            player1CloseCombatRow, player1RangedRow, player1SiegeRow,
            player2CloseCombatRow, player2RangedRow, player2SiegeRow,
            discardPile1, discardPile2
        );
        
        // Initialize units for both players
        player1Soldier = new Unit("soldado1", "pelea de cerca", 10, new CloseCombatType(), List.of());
        player1Archer = new Unit("arquero1", "tira flechas", 8, new RangedType(), List.of());
        player1Catapult = new Unit("catapulta1", "arma de asedio", 12, new SiegeType(), List.of());
        player2Soldier = new Unit("soldado2", "pelea de cerca", 10, new CloseCombatType(), List.of());
        player2Archer = new Unit("arquero2", "tira flechas", 8, new RangedType(), List.of());
        player2Catapult = new Unit("catapulta2", "arma de asedio", 12, new SiegeType(), List.of());
        
        // Initialize weather cards
        frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");
        fogWeather = new ImpenetrableFog("Niebla", "Reduce todas las unidades a distancia a 1 punto");
        rainWeather = new TorrentialRain("Lluvia", "Reduce todas las unidades de asedio a 1 punto");
    }

    private void setupAllWeatherEffects() {
        player1CloseCombatRow.placeCard(player1Soldier, round);
        player1RangedRow.placeCard(player1Archer, round);
        player1SiegeRow.placeCard(player1Catapult, round);
        
        specialZone.placeCard(frostWeather, round);
        specialZone.placeCard(fogWeather, round);
        specialZone.placeCard(rainWeather, round);
    }

    @Test
    public void testBitingFrostAffectsPlayer1CloseCombatRow() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier, round);
        player2CloseCombatRow.placeCard(player2Soldier, round);
        
        // Act
        specialZone.placeCard(frostWeather, round);
        
        // Assert
        assertEquals(1, player1Soldier.calculatePoints(), "La escarcha debería afectar a las unidades cuerpo a cuerpo del jugador 1");
    }

    @Test
    public void testBitingFrostAffectsPlayer2CloseCombatRow() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier, round);
        player2CloseCombatRow.placeCard(player2Soldier, round);
        
        // Act
        specialZone.placeCard(frostWeather, round);
        
        // Assert
        assertEquals(1, player2Soldier.calculatePoints(), "La escarcha debería afectar a las unidades cuerpo a cuerpo del jugador 2");
    }

    @Test
    public void testImpenetrableFogAffectsPlayer1RangedRow() {
        // Arrange
        player1RangedRow.placeCard(player1Archer, round);
        player2RangedRow.placeCard(player2Archer, round);
        
        // Act
        specialZone.placeCard(fogWeather, round);
        
        // Assert
        assertEquals(1, player1Archer.calculatePoints(), "La niebla debería afectar a las unidades a distancia del jugador 1");
    }

    @Test
    public void testImpenetrableFogAffectsPlayer2RangedRow() {
        // Arrange
        player1RangedRow.placeCard(player1Archer, round);
        player2RangedRow.placeCard(player2Archer, round);
        
        // Act
        specialZone.placeCard(fogWeather, round);
        
        // Assert
        assertEquals(1, player2Archer.calculatePoints(), "La niebla debería afectar a las unidades a distancia del jugador 2");
    }

    @Test
    public void testTorrentialRainAffectsPlayer1SiegeRow() {
        // Arrange
        player1SiegeRow.placeCard(player1Catapult, round);
        player2SiegeRow.placeCard(player2Catapult, round);
        
        // Act
        specialZone.placeCard(rainWeather, round);
        
        // Assert
        assertEquals(1, player1Catapult.calculatePoints(), "La lluvia debería afectar a las unidades de asedio del jugador 1");
    }

    @Test
    public void testTorrentialRainAffectsPlayer2SiegeRow() {
        // Arrange
        player1SiegeRow.placeCard(player1Catapult, round);
        player2SiegeRow.placeCard(player2Catapult, round);
        
        // Act
        specialZone.placeCard(rainWeather, round);
        
        // Assert
        assertEquals(1, player2Catapult.calculatePoints(), "La lluvia debería afectar a las unidades de asedio del jugador 2");
    }

    @Test
    public void testBitingFrostOnlyAffectsCloseCombatUnits() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier, round);
        player1RangedRow.placeCard(player1Archer, round);
        player1SiegeRow.placeCard(player1Catapult, round);
        
        // Act
        specialZone.placeCard(frostWeather, round);
        
        // Assert
        assertEquals(1, player1Soldier.calculatePoints(), "La escarcha solo debería afectar a las unidades cuerpo a cuerpo");
    }

    @Test
    public void testImpenetrableFogOnlyAffectsRangedUnits() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier, round);
        player1RangedRow.placeCard(player1Archer, round);
        player1SiegeRow.placeCard(player1Catapult, round);
        
        // Act
        specialZone.placeCard(fogWeather, round);
        
        // Assert
        assertEquals(1, player1Archer.calculatePoints(), "La niebla solo debería afectar a las unidades a distancia");
    }

    @Test
    public void testTorrentialRainOnlyAffectsSiegeUnits() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier, round);
        player1RangedRow.placeCard(player1Archer, round);
        player1SiegeRow.placeCard(player1Catapult, round);
        
        // Act
        specialZone.placeCard(rainWeather, round);
        
        // Assert
        assertEquals(1, player1Catapult.calculatePoints(), "La lluvia solo debería afectar a las unidades de asedio");
    }

    @Test
    public void testNewCloseCombatUnitsAreAffectedByExistingFrost() {
        // Arrange
        specialZone.placeCard(frostWeather, round);
        
        // Act
        player1CloseCombatRow.placeCard(player1Soldier, round);
        
        // Assert
        assertEquals(1, player1Soldier.calculatePoints(), "Las nuevas unidades cuerpo a cuerpo deberían ser afectadas por la escarcha");
    }

    @Test
    public void testNewRangedUnitsAreAffectedByExistingFog() {
        // Arrange
        specialZone.placeCard(fogWeather, round);
        
        // Act
        player1RangedRow.placeCard(player1Archer, round);
        
        // Assert
        assertEquals(1, player1Archer.calculatePoints(), "Las nuevas unidades a distancia deberían ser afectadas por la niebla");
    }

    @Test
    public void testNewSiegeUnitsAreAffectedByExistingRain() {
        // Arrange
        specialZone.placeCard(rainWeather, round);
        
        // Act
        player1SiegeRow.placeCard(player1Catapult, round);
        
        // Assert
        assertEquals(1, player1Catapult.calculatePoints(), "Las nuevas unidades de asedio deberían ser afectadas por la lluvia");
    }

    @Test
    public void testClearWeatherRemovesAllWeatherEffectsFromCloseCombat() {
        // Arrange
        setupAllWeatherEffects();
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        specialZone.placeCard(clearWeather, round);
        
        // Assert
        assertEquals(10, player1Soldier.calculatePoints(), "Las unidades cuerpo a cuerpo deberían volver a sus puntos originales");
    }

    @Test
    public void testClearWeatherRemovesAllWeatherEffectsFromRanged() {
        // Arrange
        setupAllWeatherEffects();
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        specialZone.placeCard(clearWeather, round);
        
        // Assert
        assertEquals(8, player1Archer.calculatePoints(), "Las unidades a distancia deberían volver a sus puntos originales");
    }

    @Test
    public void testClearWeatherRemovesAllWeatherEffectsFromSiege() {
        // Arrange
        setupAllWeatherEffects();
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        specialZone.placeCard(clearWeather, round);
        
        // Assert
        assertEquals(12, player1Catapult.calculatePoints(), "Las unidades de asedio deberían volver a sus puntos originales");
    }

    @Test
    public void testFrostAffectsBothPlayersCloseCombat() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier, round);
        player2CloseCombatRow.placeCard(player2Soldier, round);
        
        // Act
        specialZone.placeCard(frostWeather, round);
        
        // Assert
        assertEquals(1, player1Soldier.calculatePoints(), "La escarcha debería afectar a las unidades cuerpo a cuerpo del jugador 1");
        assertEquals(1, player2Soldier.calculatePoints(), "La escarcha debería afectar a las unidades cuerpo a cuerpo del jugador 2");
    }

    @Test
    public void testFogAffectsBothPlayersRanged() {
        // Arrange
        player1RangedRow.placeCard(player1Archer, round);
        player2RangedRow.placeCard(player2Archer, round);
        
        // Act
        specialZone.placeCard(fogWeather, round);
        
        // Assert
        assertEquals(1, player1Archer.calculatePoints(), "La niebla debería afectar a las unidades a distancia del jugador 1");
        assertEquals(1, player2Archer.calculatePoints(), "La niebla debería afectar a las unidades a distancia del jugador 2");
    }

    @Test
    public void testRainAffectsBothPlayersSiege() {
        // Arrange
        player1SiegeRow.placeCard(player1Catapult, round);
        player2SiegeRow.placeCard(player2Catapult, round);
        
        // Act
        specialZone.placeCard(rainWeather, round);
        
        // Assert
        assertEquals(1, player1Catapult.calculatePoints(), "La lluvia debería afectar a las unidades de asedio del jugador 1");
        assertEquals(1, player2Catapult.calculatePoints(), "La lluvia debería afectar a las unidades de asedio del jugador 2");
    }

    @Test
    public void testClearWeatherRemovesEffectsFromBothPlayers() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier, round);
        player1RangedRow.placeCard(player1Archer, round);
        player1SiegeRow.placeCard(player1Catapult, round);
        player2CloseCombatRow.placeCard(player2Soldier, round);
        player2RangedRow.placeCard(player2Archer, round);
        player2SiegeRow.placeCard(player2Catapult, round);
        
        // Apply all weather effects
        specialZone.placeCard(frostWeather, round);
        specialZone.placeCard(fogWeather, round);
        specialZone.placeCard(rainWeather, round);
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        specialZone.placeCard(clearWeather, round);
        
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
        CloseCombat sharedCloseCombatRow = new CloseCombat(discardPile1);
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SpecialZone(
                sharedCloseCombatRow, player1RangedRow, player1SiegeRow,
                sharedCloseCombatRow, player2RangedRow, player2SiegeRow,
                    discardPile1, discardPile2
            );
        }, "Should throw IllegalArgumentException when close combat rows are repeated");
        
        assertEquals("Close combat, ranged and siege rows must be different", exception.getMessage(),
            "Exception message should indicate that rows must be different");
    }

    @Test
    public void testSpecialZoneConstructor_ShouldThrowException_WhenRangedRowsAreRepeated() {
        // Arrange
        Ranged sharedRangedRow = new Ranged(discardPile1);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SpecialZone(
                player1CloseCombatRow, sharedRangedRow, player1SiegeRow,
                player2CloseCombatRow, sharedRangedRow, player2SiegeRow,
                    discardPile1,discardPile2
            );
        }, "Should throw IllegalArgumentException when ranged rows are repeated");
        
        assertEquals("Close combat, ranged and siege rows must be different", exception.getMessage(),
            "Exception message should indicate that rows must be different");
    }

    @Test
    public void testSpecialZoneConstructor_ShouldThrowException_WhenSiegeRowsAreRepeated() {
        // Arrange
        Siege sharedSiegeRow = new Siege(discardPile1);
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SpecialZone(
                player1CloseCombatRow, player1RangedRow, sharedSiegeRow,
                player2CloseCombatRow, player2RangedRow, sharedSiegeRow,
                    discardPile1, discardPile2
            );
        }, "Should throw IllegalArgumentException when siege rows are repeated");
        
        assertEquals("Close combat, ranged and siege rows must be different", exception.getMessage(),
            "Exception message should indicate that rows must be different");
    }

    @Test
    public void testSpecialZoneConstructor_ShouldThrowException_WhenMultipleRowsAreRepeated() {
        // Arrange
        CloseCombat sharedCloseCombatRow = new CloseCombat(discardPile1);
        Ranged sharedRangedRow = new Ranged(discardPile1);
        Siege sharedSiegeRow = new Siege(discardPile1);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SpecialZone(
                sharedCloseCombatRow, sharedRangedRow, sharedSiegeRow,
                sharedCloseCombatRow, sharedRangedRow, sharedSiegeRow,
                    discardPile1, discardPile2
            );
        }, "Should throw IllegalArgumentException when multiple rows are repeated");
        
        assertEquals("Close combat, ranged and siege rows must be different", exception.getMessage(),
            "Exception message should indicate that rows must be different");
    }
}
