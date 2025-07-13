package edu.fiuba.algo3.models.cards.specials.weathers;

import edu.fiuba.algo3.models.colors.*;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.models.cardcollections.Deck;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.errors.SectionTypeMismatchError;
import edu.fiuba.algo3.models.sections.*;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SiegeType;

import java.util.List;

public class WeatherTest {
    private CloseCombat player1CloseCombatRow;
    private Ranged player1RangedRow;
    private Siege player1SiegeRow;
    private SpecialZone specialZone;

    private Unit soldier;
    private Unit archer;
    private Unit catapult;
    
    private Weather frostWeather;
    private Weather fogWeather;
    private Weather rainWeather;
    private Weather clearWeather;
    private Round round;
    private Deck deck;
    private CloseCombat player2CloseCombatRow;
    private Ranged player2RangedRow;
    private Siege player2SiegeRow;
    private DiscardPile discardPile1;
    private DiscardPile discardPile2;
    private Player player;
    private Player opponent;
    private final PlayerColor color = new Blue();

    @BeforeEach
    public void setup() {
        discardPile1 = new DiscardPile();
        discardPile2 = new DiscardPile();
        deck = new Deck();


        player1CloseCombatRow = new CloseCombat(discardPile1);
        player1RangedRow = new Ranged(discardPile1);
        player1SiegeRow = new Siege(discardPile1);
        player2CloseCombatRow = new CloseCombat(discardPile2);
        player2RangedRow = new Ranged(discardPile2);
        player2SiegeRow = new Siege(discardPile2);
        player = new Player("Gabriel", new Blue());
        opponent = new Player("Juan", new Red());
        round = new Round(player, opponent);


        // Initialize weather zone
        specialZone = new SpecialZone(player1CloseCombatRow, player1RangedRow, player1SiegeRow,
                player2CloseCombatRow, player2RangedRow, player2SiegeRow,
                discardPile1, discardPile2);

        // Initialize units
        soldier = new Unit("soldado", "pelea de cerca", 10, new CloseCombatType(), List.of());
        archer = new Unit("arquero", "tira flechas", 8, new RangedType(), List.of());
        catapult = new Unit("catapulta", "arma de asedio", 12, new SiegeType(), List.of());

        soldier.setColor(color);
        archer.setColor(color);
        catapult.setColor(color);

        // Initialize weather cards
        frostWeather = new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto");
        fogWeather = new ImpenetrableFog("Niebla", "Reduce todas las unidades a distancia a 1 punto");
        rainWeather = new TorrentialRain("Lluvia", "Reduce todas las unidades de asedio a 1 punto");
        clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        frostWeather.setColor(color);
        fogWeather.setColor(color);
        rainWeather.setColor(color);
        clearWeather.setColor(color);
        round = new Round(player, opponent);
    }

    @Test
    public void testWeatherPlayAppliesEffectToTarget() {
        // Arrange
        player1CloseCombatRow.placeCard(soldier, round);
        
        // Act
        frostWeather.play(specialZone);
        
        // Assert
        assertEquals(1, soldier.calculatePoints(), "El clima debería aplicar su efecto al ser jugado");
    }

    @Test
    public void testBitingFrostReducesCloseCombatUnitPoints() {
        // Act
        player1CloseCombatRow.placeCard(soldier, round);
        player1CloseCombatRow.applyWeather(frostWeather);

        // Assert
        assertEquals(1, soldier.calculatePoints(), "La escarcha debería reducir los puntos de la unidad cuerpo a cuerpo a 1");
    }

    @Test
    public void testBitingFrostDoesNotAffectRangedUnits() {
        // Act
        player1RangedRow.placeCard(archer, round);
        player1CloseCombatRow.applyWeather(frostWeather);
        
        // Assert
        assertEquals(8, archer.calculatePoints(), "La escarcha no debería afectar a las unidades a distancia");
    }

    @Test
    public void testImpenetrableFogReducesRangedUnitPoints() {
        // Act
        player1RangedRow.placeCard(archer, round);
        player1RangedRow.applyWeather(fogWeather);
        
        // Assert
        assertEquals(1, archer.calculatePoints(), "La niebla debería reducir los puntos de la unidad a distancia a 1");
    }

    @Test
    public void testImpenetrableFogDoesNotAffectCloseCombatUnits() {
        // Act
        player1CloseCombatRow.placeCard(soldier, round);
        player1CloseCombatRow.applyWeather(fogWeather);
        
        // Assert
        assertEquals(10, soldier.calculatePoints(), "La niebla no debería afectar a las unidades cuerpo a cuerpo");
    }

    @Test
    public void testTorrentialRainReducesSiegeUnitPoints() {
        // Act
        player1SiegeRow.placeCard(catapult, round);
        player1SiegeRow.applyWeather(rainWeather);
        
        // Assert
        assertEquals(1, catapult.calculatePoints(), "La lluvia debería reducir los puntos de la unidad de asedio a 1");
    }

    @Test
    public void testTorrentialRainDoesNotAffectCloseCombatUnits() {
        // Act
        player1CloseCombatRow.placeCard(soldier, round);
        player1CloseCombatRow.applyWeather(rainWeather);
        
        // Assert
        assertEquals(10, soldier.calculatePoints(), "La lluvia no debería afectar a las unidades cuerpo a cuerpo");
    }

    @Test
    public void testBitingFrostAffectsNewCloseCombatUnit() {
        // Act
        player1CloseCombatRow.applyWeather(frostWeather);
        player1CloseCombatRow.placeCard(soldier, round);
        
        // Assert
        assertEquals(1, soldier.calculatePoints(), "La escarcha debería afectar a las nuevas unidades cuerpo a cuerpo");
    }

    @Test
    public void testImpenetrableFogAffectsNewRangedUnit() {
        // Act
        player1RangedRow.applyWeather(fogWeather);
        player1RangedRow.placeCard(archer, round);
        
        // Assert
        assertEquals(1, archer.calculatePoints(), "La niebla debería afectar a las nuevas unidades a distancia");
    }

    @Test
    public void testTorrentialRainAffectsNewSiegeUnit() {
        // Act
        player1SiegeRow.applyWeather(rainWeather);
        player1SiegeRow.placeCard(catapult, round);
        
        // Assert
        assertEquals(1, catapult.calculatePoints(), "La lluvia debería afectar a las nuevas unidades de asedio");
    }

    @Test
    public void testClearWeatherRemovesFrostEffect() {
        // Arrange
        player1CloseCombatRow.placeCard(soldier, round);
        player1CloseCombatRow.applyWeather(frostWeather);
        
        // Act
        player1CloseCombatRow.applyWeather(clearWeather);
        
        // Assert
        assertEquals(10, soldier.calculatePoints(), "Las unidades cuerpo a cuerpo deberían volver a sus puntos originales");
    }

    @Test
    public void testClearWeatherRemovesFogEffect() {
        // Arrange
        player1RangedRow.placeCard(archer, round);
        player1RangedRow.applyWeather(fogWeather);
        
        // Act
        player1RangedRow.applyWeather(clearWeather);
        
        // Assert
        assertEquals(8, archer.calculatePoints(), "Las unidades a distancia deberían volver a sus puntos originales");
    }

    @Test
    public void testClearWeatherRemovesRainEffect() {
        // Arrange
        player1SiegeRow.placeCard(catapult, round);
        player1SiegeRow.applyWeather(rainWeather);
        
        // Act
        player1SiegeRow.applyWeather(clearWeather);
        
        // Assert
        assertEquals(12, catapult.calculatePoints(), "Las unidades de asedio deberían volver a sus puntos originales");
    }
    
    @Test
    public void testMultipleWeatherEffectsDoNotInterfereWithCloseCombat() {
        // Act
        player1CloseCombatRow.placeCard(soldier, round);
        specialZone.placeCard(fogWeather, round);
        specialZone.placeCard(rainWeather, round);
        specialZone.placeCard(frostWeather, round);
        
        // Assert
        assertEquals(1, soldier.calculatePoints(), "La escarcha debería ser el único efecto que afecta a las unidades cuerpo a cuerpo");
    }

    @Test
    public void testMultipleWeatherEffectsDoNotInterfereWithRanged() {
        // Act
        player1RangedRow.placeCard(archer, round);
        specialZone.placeCard(frostWeather, round);
        specialZone.placeCard(rainWeather, round);
        specialZone.placeCard(fogWeather, round);
        
        // Assert
        assertEquals(1, archer.calculatePoints(), "La niebla debería ser el único efecto que afecta a las unidades a distancia");
    }

    @Test
    public void testMultipleWeatherEffectsDoNotInterfereWithSiege() {
        // Act
        player1SiegeRow.placeCard(catapult, round);
        specialZone.placeCard(frostWeather, round);
        specialZone.placeCard(fogWeather, round);
        specialZone.placeCard(rainWeather, round);
        
        // Assert
        assertEquals(1, catapult.calculatePoints(), "La lluvia debería ser el único efecto que afecta a las unidades de asedio");
    }

    @Test
    public void testWeatherCannotBePlacedInInvalidTarget() {
        // Act
        assertThrows(SectionTypeMismatchError.class, () -> player1CloseCombatRow.placeCard(frostWeather, round),
            "Debería lanzar una excepción al intentar colocar clima en una fila normal");
    }

    @Test
    public void testClearWeatherCannotBePlacedInRow() {
        // Act & Assert
        assertThrows(SectionTypeMismatchError.class, () -> player1CloseCombatRow.placeCard(clearWeather, round),
            "Debería lanzar una excepción al intentar colocar ClearWeather en una fila normal");
    }

    @Test
    public void testBitingFrostCannotBePlacedInRow() {
        // Act & Assert
        assertThrows(SectionTypeMismatchError.class, () -> player1CloseCombatRow.placeCard(frostWeather, round),
            "Debería lanzar una excepción al intentar colocar BitingFrost en una fila normal");
    }

    @Test
    public void testImpenetrableFogCannotBePlacedInRow() {
        // Act & Assert
        assertThrows(SectionTypeMismatchError.class, () -> player1CloseCombatRow.placeCard(fogWeather, round),
            "Debería lanzar una excepción al intentar colocar ImpenetrableFog en una fila normal");
    }

    @Test
    public void testTorrentialRainCannotBePlacedInRow() {
        // Act & Assert
        assertThrows(SectionTypeMismatchError.class, () -> player1CloseCombatRow.placeCard(rainWeather, round),
            "Debería lanzar una excepción al intentar colocar TorrentialRain en una fila normal");
    }
}
