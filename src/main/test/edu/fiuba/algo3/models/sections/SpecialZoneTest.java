package edu.fiuba.algo3.models.sections;

import edu.fiuba.algo3.models.colors.*;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.models.cardcollections.Deck;

import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.specials.*;
import edu.fiuba.algo3.models.cards.specials.weathers.*;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SiegeType;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    private Round round;

    private DiscardPile discardPile1;
    private DiscardPile discardPile2;

    @BeforeEach
    public void setup() {
        Player player = new Player("Gabriel", new Blue());
        Player opponent = new Player("Juan", new Red());

        discardPile1 = player.getDiscardPile();
        discardPile2 = opponent.getDiscardPile();

        player1CloseCombatRow = player.getCloseCombatRow();
        player1RangedRow = player.getRangedRow();
        player1SiegeRow = player.getSiegeRow();

        player2CloseCombatRow = opponent.getCloseCombatRow();
        player2RangedRow = opponent.getRangedRow();
        player2SiegeRow = opponent.getSiegeRow();

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
        frostWeather.setColor(new Blue());
        fogWeather.setColor(new Blue());
        rainWeather.setColor(new Blue());

        round = new Round(player, opponent);
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
    public void testLaEscarchaAfectaALasUnidadesCuerpoACuerpoDelJugador1() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier, round);
        player2CloseCombatRow.placeCard(player2Soldier, round);
        
        // Act
        specialZone.placeCard(frostWeather, round);
        
        // Assert
        assertEquals(1, player1Soldier.calculatePoints(), "La escarcha debería afectar a las unidades cuerpo a cuerpo del jugador 1");
    }

    @Test
    public void testLaEscarchaAfectaALasUnidadesCuerpoACuerpoDelJugador2() {
        // Arrange
        player1CloseCombatRow.placeCard(player1Soldier, round);
        player2CloseCombatRow.placeCard(player2Soldier, round);
        
        // Act
        specialZone.placeCard(frostWeather, round);
        
        // Assert
        assertEquals(1, player2Soldier.calculatePoints(), "La escarcha debería afectar a las unidades cuerpo a cuerpo del jugador 2");
    }

    @Test
    public void testLaNieblaAfectaALasUnidadesADistanciaDelJugador1() {
        // Arrange
        player1RangedRow.placeCard(player1Archer, round);
        player2RangedRow.placeCard(player2Archer, round);
        
        // Act
        specialZone.placeCard(fogWeather, round);
        
        // Assert
        assertEquals(1, player1Archer.calculatePoints(), "La niebla debería afectar a las unidades a distancia del jugador 1");
    }

    @Test
    public void testLaNieblaAfectaALasUnidadesADistanciaDelJugador2() {
        // Arrange
        player1RangedRow.placeCard(player1Archer, round);
        player2RangedRow.placeCard(player2Archer, round);
        
        // Act
        specialZone.placeCard(fogWeather, round);
        
        // Assert
        assertEquals(1, player2Archer.calculatePoints(), "La niebla debería afectar a las unidades a distancia del jugador 2");
    }

    @Test
    public void testLaLluviaAfectaALasUnidadesDeAsedioDelJugador1() {
        // Arrange
        player1SiegeRow.placeCard(player1Catapult, round);
        player2SiegeRow.placeCard(player2Catapult, round);
        
        // Act
        specialZone.placeCard(rainWeather, round);
        
        // Assert
        assertEquals(1, player1Catapult.calculatePoints(), "La lluvia debería afectar a las unidades de asedio del jugador 1");
    }

    @Test
    public void testLaLluviaAfectaALasUnidadesDeAsedioDelJugador2() {
        // Arrange
        player1SiegeRow.placeCard(player1Catapult, round);
        player2SiegeRow.placeCard(player2Catapult, round);
        
        // Act
        specialZone.placeCard(rainWeather, round);
        
        // Assert
        assertEquals(1, player2Catapult.calculatePoints(), "La lluvia debería afectar a las unidades de asedio del jugador 2");
    }

    @Test
    public void testLaEscarchaSoloAfectaALasUnidadesCuerpoACuerpo() {
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
    public void testLaNieblaSoloAfectaALasUnidadesADistancia() {
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
    public void testLaLluviaSoloAfectaALasUnidadesDeAsedio() {
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
    public void testLasNuevasUnidadesCuerpoACuerpoSonAfectadasPorLaEscarchaExistente() {
        // Arrange
        specialZone.placeCard(frostWeather, round);
        
        // Act
        player1CloseCombatRow.placeCard(player1Soldier, round);
        
        // Assert
        assertEquals(1, player1Soldier.calculatePoints(), "Las nuevas unidades cuerpo a cuerpo deberían ser afectadas por la escarcha");
    }

    @Test
    public void testLasNuevasUnidadesADistanciaSonAfectadasPorLaNieblaExistente() {
        // Arrange
        specialZone.placeCard(fogWeather, round);
        
        // Act
        player1RangedRow.placeCard(player1Archer, round);
        
        // Assert
        assertEquals(1, player1Archer.calculatePoints(), "Las nuevas unidades a distancia deberían ser afectadas por la niebla");
    }

    @Test
    public void testLasNuevasUnidadesDeAsedioSonAfectadasPorLaLluviaExistente() {
        // Arrange
        specialZone.placeCard(rainWeather, round);
        
        // Act
        player1SiegeRow.placeCard(player1Catapult, round);
        
        // Assert
        assertEquals(1, player1Catapult.calculatePoints(), "Las nuevas unidades de asedio deberían ser afectadas por la lluvia");
    }

    @Test
    public void testElClimaDespejadoEliminaTodosLosEfectosDeClimaDeLasUnidadesCuerpoACuerpo() {
        // Arrange
        setupAllWeatherEffects();
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        clearWeather.setColor(new Blue());
        specialZone.placeCard(clearWeather, round);
        
        // Assert
        assertEquals(10, player1Soldier.calculatePoints(), "Las unidades cuerpo a cuerpo deberían volver a sus puntos originales");
    }

    @Test
    public void testElClimaDespejadoEliminaTodosLosEfectosDeClimaDeLasUnidadesADistancia() {
        // Arrange
        setupAllWeatherEffects();
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        clearWeather.setColor(new Blue());
        specialZone.placeCard(clearWeather, round);
        
        // Assert
        assertEquals(8, player1Archer.calculatePoints(), "Las unidades a distancia deberían volver a sus puntos originales");
    }

    @Test
    public void testElClimaDespejadoEliminaTodosLosEfectosDeClimaDeLasUnidadesDeAsedio() {
        // Arrange
        setupAllWeatherEffects();
        
        // Act
        Special clearWeather = new ClearWeather("Clima Despejado", "Elimina todos los efectos de clima");
        clearWeather.setColor(new Blue());
        specialZone.placeCard(clearWeather, round);
        
        // Assert
        assertEquals(12, player1Catapult.calculatePoints(), "Las unidades de asedio deberían volver a sus puntos originales");
    }

    @Test
    public void testLaEscarchaAfectaALasUnidadesCuerpoACuerpoDeAmbosJugadores() {
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
    public void testLaNieblaAfectaALasUnidadesADistanciaDeAmbosJugadores() {
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
    public void testLaLluviaAfectaALasUnidadesDeAsedioDeAmbosJugadores() {
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
    public void testElClimaDespejadoEliminaLosEfectosDeAmbosJugadores() {
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
        clearWeather.setColor(new Blue());
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
    public void testElConstructorDeSpecialZoneDeberiaLanzarExcepcionCuandoLasFilasCuerpoACuerpoSeRepiten() {
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
    public void testElConstructorDeSpecialZoneDeberiaLanzarExcepcionCuandoLasFilasADistanciaSeRepiten() {
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
    public void testElConstructorDeSpecialZoneDeberiaLanzarExcepcionCuandoLasFilasDeAsedioSeRepiten() {
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
    public void testElConstructorDeSpecialZoneDeberiaLanzarExcepcionCuandoMultiplesFilasSeRepiten() {
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

    @Test
    public void testSeAplicaLaCartaScorchParaTodasLasFilas() {
        Scorch scorch = new Scorch("tierra", "arrasada", List.of(new CloseCombatType(), new RangedType(), new SiegeType()));
        scorch.setColor(new Blue());

        player1CloseCombatRow.placeCard(player1Soldier, round);
        player1RangedRow.placeCard(player1Archer, round);
        player1SiegeRow.placeCard(player1Catapult, round);

        player2CloseCombatRow.placeCard(player2Soldier, round);
        player2RangedRow.placeCard(player2Archer, round);
        player2SiegeRow.placeCard(player2Catapult, round);

        specialZone.applyScorchInAllRows(scorch);

        assertFalse(player1SiegeRow.containsCard(player1Catapult));
        assertFalse(player2SiegeRow.containsCard(player2Catapult));
    }

    @Test
    public void testObtenerCartasDeClimaDeberiaRetornarListaVaciaCuandoNoSeAgreganCartasDeClima() {
        // Act
        List<Card> weatherCards = specialZone.getWeathersCards();
        
        // Assert
        assertTrue(weatherCards.isEmpty());
    }

    @Test
    public void testObtenerCartasDeClimaDeberiaRetornarLasCartasDeClimaAgregadas() {
        // Arrange
        specialZone.addCard(frostWeather);
        specialZone.addCard(fogWeather);
        
        // Act
        List<Card> weatherCards = specialZone.getWeathersCards();
        
        // Assert
        assertEquals(2, weatherCards.size(), "Should return the correct number of weather cards");
        assertTrue(weatherCards.contains(frostWeather), "Should contain the frost weather card");
        assertTrue(weatherCards.contains(fogWeather), "Should contain the fog weather card");
    }

    @Test
    public void testObtenerCartasDeClimaDeberiaRetornarListaVaciaDespuesDeLimpiarLaZona() {
        // Arrange
        specialZone.addCard(frostWeather);
        specialZone.addCard(fogWeather);
        specialZone.addCard(rainWeather);
        
        // Act
        specialZone.clearZone();
        List<Card> weatherCards = specialZone.getWeathersCards();
        
        // Assert
        assertTrue(weatherCards.isEmpty(), "Should return empty list after clearing the zone");
    }

    @Test
    public void testObtenerCartasDeClimaDeberiaRetornarCartasCuandoLasCartasSeAgreganViaPlaceCard() {
        // Arrange
        specialZone.placeCard(frostWeather, round);
        specialZone.placeCard(fogWeather, round);
        
        // Act
        List<Card> weatherCards = specialZone.getWeathersCards();
        
        // Assert
        assertEquals(2, weatherCards.size(), "Should return weather cards added via placeCard");
        assertTrue(weatherCards.contains(frostWeather), "Should contain frost weather added via placeCard");
        assertTrue(weatherCards.contains(fogWeather), "Should contain fog weather added via placeCard");
    }

}
