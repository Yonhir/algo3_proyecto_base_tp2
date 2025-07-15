package edu.fiuba.algo3.models.turnManagement;


import edu.fiuba.algo3.models.colors.Blue;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cards.*;
import edu.fiuba.algo3.models.cards.specials.weathers.BitingFrost;
import edu.fiuba.algo3.models.cards.specials.weathers.ImpenetrableFog;
import edu.fiuba.algo3.models.cards.specials.weathers.TorrentialRain;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.colors.PlayerColor;
import edu.fiuba.algo3.models.colors.Red;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SiegeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private List<Card> cards;
    private Player player;
    private Player opponent;

    private CloseCombat closeCombat1;
    private Ranged ranged1;
    private Siege siege1;
    private CloseCombat closeCombat2;
    private Ranged ranged2;
    private Siege siege2;
    private Round round;


    private final PlayerColor color = new Blue();
    private Unit siegeCard;
    private Unit rangedCard;
    private Unit closeCombatCard;
    private DiscardPile discardPile1;
    private DiscardPile discardPile2;

    private TorrentialRain weather;

    @BeforeEach
    void setUp() {
        siegeCard = new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>());
        rangedCard = new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>());
        closeCombatCard = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>());

        siegeCard.setColor(color);
        rangedCard.setColor(color);
        closeCombatCard.setColor(color);

        // Create unit cards
        List<Card> unitCards = Arrays.asList(
                new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 5, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 6, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 0, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 10, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 2, new RangedType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 8, new SiegeType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 3, new CloseCombatType(), new ArrayList<>()),
                new Unit("Nombre", "Descripcion", 4, new RangedType(), new ArrayList<>())

        );

        weather = new TorrentialRain("Lluvia", "Reduce todas las unidades de asedio a 1 punto");
        // Create special cards (weather cards)
        List<Card> specialCards = Arrays.asList(
                new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto"),
                new ImpenetrableFog("Niebla", "Reduce todas las unidades a distancia a 1 punto"),
                weather,
                new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto"),
                new ImpenetrableFog("Niebla", "Reduce todas las unidades a distancia a 1 punto"),
                new TorrentialRain("Lluvia", "Reduce todas las unidades de asedio a 1 punto")
        );

        // Combine all cards
        cards = new ArrayList<>();
        cards.addAll(unitCards);
        cards.addAll(specialCards);

        // Initialize game components
        player = new Player("Gabriel", new Blue());
        opponent = new Player("Juan", new Red());

        Deck deck1 = player.getDeck();
        Deck deck2 = opponent.getDeck();
        deck1.insertCards(cards);
        deck2.insertCards(cards);
        discardPile1 = player.getDiscardPile();
        discardPile2 = opponent.getDiscardPile();
        closeCombat1 = player.getCloseCombatRow();
        ranged1 = player.getRangedRow();
        siege1 = player.getSiegeRow();
        closeCombat2 = opponent.getCloseCombatRow();
        ranged2 = opponent.getRangedRow();
        siege2 = opponent.getSiegeRow();

        Hand hand = player.getHand();

        hand.insertCards(Arrays.asList(siegeCard, closeCombatCard, rangedCard, weather));
        hand.getNCardsFromDeck(deck1, 6);

        round = new Round(player, opponent);
    }

   @Test
   public void testSeRegistranCorrectamenteLosNombresDeCadaPlayer(){
        String nombreJugadorEperado = "Gabriel";
        String nombreOponenteEperado = "Juan";

        assertEquals(player.getName(), nombreJugadorEperado);
       assertEquals(opponent.getName(), nombreOponenteEperado);

   }

    @Test
    public void testLaCartaJugadaYaNoSeEncuentraEnLaManoDelJugador() {
        int expectedCards = 9;

        player.playCard(siegeCard, siege1, round);

        int actualCards = player.getHand().getCardCount();

        assertEquals(expectedCards, actualCards);
    }

    @Test
    public void testSeJuegaUnaCartaEnLaFilaSiegeCorrectamente() {
        player.playCard(siegeCard, siege1, round);

        assertTrue(siege1.containsCard(siegeCard));
    }

    @Test
    public void testLosPuntosEnLaFilaAlJugarUnaCartaSonLosCorrectos() {
        int expected_points = ((Unit) siegeCard).calculatePoints();

        player.playCard(siegeCard, siege1, round);

        int actual_points = ((Unit) siegeCard).calculatePoints();

        assertEquals(expected_points, actual_points);
    }

    @Test
    public void testSeJueganCartasSeObtieneElPuntajeTotalDelJugador() {
        int expected_points = (siegeCard).calculatePoints() +
                              (closeCombatCard).calculatePoints() +
                              (rangedCard).calculatePoints();

        player.playCard(siegeCard, siege1, round);
        player.playCard(closeCombatCard, closeCombat1, round);
        player.playCard(rangedCard, ranged1, round);

        int actual_points = player.calculatePoints();

        assertEquals(expected_points, actual_points);
    }

    @Test
    public void testSeJueganCartasCorrectamenteEnCadaFila() {
        player.playCard(siegeCard, siege1, round);
        player.playCard(closeCombatCard, closeCombat1, round);
        player.playCard(rangedCard, ranged1, round);

        assertTrue(siege1.containsCard(siegeCard));
        assertTrue(closeCombat1.containsCard(closeCombatCard));
        assertTrue(ranged1.containsCard(rangedCard));
    }

    @Test
    public void testVerificarQueLasCartasJugadasNoSeEncuentrenEnLaMano() {
        player.playCard(siegeCard, siege1, round);
        player.playCard(closeCombatCard, closeCombat1, round);
        player.playCard(rangedCard, ranged1, round);

        Hand hand = player.getHand();
        assertFalse(hand.containsCards(Arrays.asList(siegeCard, closeCombatCard, rangedCard)));
    }

    @Test
    public void testLaPilaDeDescarteDelJugadorSeEncuentraEnElEstadoCorrectoAlInicio() {
        //ARRANGE
        DiscardPile expectedDiscardPile = new DiscardPile();
        
        //ACT
        DiscardPile actualDiscardPile = player.getDiscardPile();
        
        //ASSERT
        assertEquals(expectedDiscardPile.getCardCount(), actualDiscardPile.getCardCount());
    }

    @Test
    public void testElPuntajeInicialDelJugadorEsCero() {
        //ARRANGE
        int expectedPoints = 0;

        //ACT
        int actualPoints = player.calculatePoints();
        
        //ASSERT
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testElJugadorGanaUnaRondaCorrectamente() {
        player.winRound();

        assertEquals(1, player.getRoundsWon());
    }

    @Test
    public void testSiElJugadorGanaDosRondasEntoncesGanaElJuego() {
        player.winRound();
        player.winRound();

        assertTrue(player.hasWonGame());
    }

    @Test
    public void testSiElJugadorGanaUnaRondaNoGanoElJuego() {
        player.winRound();

        assertFalse(player.hasWonGame());
    }

    @Test
    public void testLasCartasQueElJugadorDescartaVanALaPilaDeDescarteCorrectamente() {
        siege1.placeCard(siegeCard, round);
        closeCombat1.placeCard(closeCombatCard, round);
        ranged1.placeCard(rangedCard, round);

        int expectedDiscardCount = 3;

        player.discardAllRows();

        assertFalse(siege1.containsCard(siegeCard));
        assertFalse(ranged1.containsCard(rangedCard));
        assertFalse(closeCombat1.containsCard(closeCombatCard));
        assertEquals(expectedDiscardCount, player.getDiscardPile().getCardCount());
    }

    @Test
    public void testSeAsignaGanadorDeLaRondaAlJugadorCorrecto() {
        Unit otraUnidad = new Unit("Nombre", "Descripcion", 2, new CloseCombatType(), new ArrayList<>());
        otraUnidad.setColor(new Red());

        closeCombat1.placeCard(closeCombatCard, round);
        closeCombat2.placeCard(otraUnidad, round);

        player.assignRoundVictoryToBetterPlayer(opponent);

        assertEquals(1, player.getRoundsWon());
    }

    @Test
    public void testSeObtieneElGanadorDelJuegoCorrecto() {
        Unit otraUnidad = new Unit("Nombre", "Descripcion", 2, new CloseCombatType(), new ArrayList<>());
        otraUnidad.setColor(new Red());
        Unit unidad = new Unit("Nombre", "Descripcion", 4, new RangedType(), new ArrayList<>());
        unidad.setColor(new Red());

        closeCombat1.placeCard(closeCombatCard, round);
        closeCombat2.placeCard(otraUnidad, round);
        player.assignRoundVictoryToBetterPlayer(opponent);

        ranged1.placeCard(rangedCard, round);
        ranged2.placeCard(unidad, round);
        player.assignRoundVictoryToBetterPlayer(opponent);

        assertEquals(player, player.chooseWinnerAgainst(opponent));
    }

    @Test
    public void testChooseWinnerAgainstRetornaNullCuandoAmbosJugadoresHanGanadoDosRondas() {
        // Arrange: Both players win 2 rounds each
        player.winRound();
        player.winRound();
        opponent.winRound();
        opponent.winRound();

        // Act
        Player winner = player.chooseWinnerAgainst(opponent);

        // Assert: Should return null for draw
        assertNull(winner);
    }

    @Test
    public void testChooseWinnerAgainstRetornaJugadorCuandoSoloElHaGanadoDosRondas() {
        // Arrange: Only player wins 2 rounds
        player.winRound();
        player.winRound();
        opponent.winRound(); // Only 1 round

        // Act
        Player winner = player.chooseWinnerAgainst(opponent);

        // Assert: Should return player as winner
        assertEquals(player, winner);
    }

    @Test
    public void testChooseWinnerAgainstRetornaOponenteCuandoSoloElOponenteHaGanadoDosRondas() {
        // Arrange: Only opponent wins 2 rounds
        player.winRound(); // Only 1 round
        opponent.winRound();
        opponent.winRound();

        // Act
        Player winner = player.chooseWinnerAgainst(opponent);

        // Assert: Should return opponent as winner
        assertEquals(opponent, winner);
    }

    @Test
    public void testElJugadorPuedeJugarUnaCartaEnSpecialZoneCorrectamente() {
        SpecialZone specialZone = new SpecialZone(closeCombat1, ranged1, siege1, closeCombat2, ranged2, siege2, discardPile1, discardPile2);

        siege1.placeCard(siegeCard, round);
        player.playCard(weather, specialZone, round);

        assertEquals(1, siegeCard.calculatePoints());
    }

    @Test
    public void testElJugadorPuedeCambiarSuNombreCorrectamente() {
        String nuevoNombre = "NuevoNombre";

        player.changeName(nuevoNombre);

        assertEquals(nuevoNombre, player.getName());
    }

    @Test
    public void testElJugadorObtieneSuNombreCorrectamente() {
        String nombreEsperado = "Gabriel";

        String nombreActual = player.getName();

        assertEquals(nombreEsperado, nombreActual);
    }
}
