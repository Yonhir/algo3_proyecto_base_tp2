package edu.fiuba.algo3.modelo.turnManagement;

import edu.fiuba.algo3.modelo.Colors.Blue;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.modelo.cards.*;
import edu.fiuba.algo3.modelo.cards.specials.weathers.BitingFrost;
import edu.fiuba.algo3.modelo.cards.specials.weathers.ImpenetrableFog;
import edu.fiuba.algo3.modelo.cards.specials.weathers.TorrentialRain;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.RangedType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    private List<Card> cards;
    private Player player;
    private Deck deck;

    private CloseCombat closeCombat1;
    private Ranged ranged1;
    private Siege siege1;
    private CloseCombat closeCombat2;
    private Ranged ranged2;
    private Siege siege2;
    private Player opponent;
    private Round round;


    private Card siegeCard;
    private Card rangedCard;
    private Card closeCombatCard;

    @BeforeEach
    void setUp() {
        siegeCard = new Unit("Nombre", "Descripcion", 3, new SiegeType(), new ArrayList<>());
        rangedCard = new Unit("Nombre", "Descripcion", 6, new RangedType(), new ArrayList<>());
        closeCombatCard = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>());

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

        // Create special cards (weather cards)
        List<Card> specialCards = Arrays.asList(
                new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto"),
                new ImpenetrableFog("Niebla", "Reduce todas las unidades a distancia a 1 punto"),
                new TorrentialRain("Lluvia", "Reduce todas las unidades de asedio a 1 punto"),
                new BitingFrost("Escarcha", "Reduce todas las unidades cuerpo a cuerpo a 1 punto"),
                new ImpenetrableFog("Niebla", "Reduce todas las unidades a distancia a 1 punto"),
                new TorrentialRain("Lluvia", "Reduce todas las unidades de asedio a 1 punto")
        );

        // Combine all cards
        cards = new ArrayList<>();
        cards.addAll(unitCards);
        cards.addAll(specialCards);

        // Initialize game components
        deck = new Deck();
        deck.insertCards(cards);
        DiscardPile discardPile = new DiscardPile();
        closeCombat1 = new CloseCombat(discardPile);
        ranged1 = new Ranged(discardPile);
        siege1 = new Siege(discardPile);
        closeCombat2 = new CloseCombat(discardPile);
        ranged2 = new Ranged(discardPile);
        siege2 = new Siege(discardPile);
        player = new Player("Gabriel", deck, closeCombat1, ranged1, siege1, new Blue());
        opponent = new Player("Juan", deck, closeCombat2, ranged2, siege2, new Blue());
        Hand hand = player.getHand();

        hand.insertCards(Arrays.asList(siegeCard, closeCombatCard, rangedCard));
        hand.getNCardsFromDeck(deck, 7);
        round = new Round(player, opponent);
    }

    @Test
    public void testLaCartaJugadaYaNoSeEncuentraEnLaManoDelJugador() {
        int expectedCards = 9;

        player.playCard(siegeCard, siege1, round);

        int actualCards = player.getHand().getCardCount();

        Assertions.assertEquals(expectedCards, actualCards);
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

        Assertions.assertEquals(expected_points, actual_points);
    }

    @Test
    public void testSeJueganCartasSeObtieneElPuntajeTotalDelJugador() {
        int expected_points = ((Unit) siegeCard).calculatePoints() +
                              ((Unit) closeCombatCard).calculatePoints() +
                              ((Unit) rangedCard).calculatePoints();

        player.playCard(siegeCard, siege1, round);
        player.playCard(closeCombatCard, closeCombat1, round);
        player.playCard(rangedCard, ranged1, round);

        int actual_points = player.calculatePoints();

        Assertions.assertEquals(expected_points, actual_points);
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
    public void HandAfterPlayingSomeCards() {
        player.playCard(siegeCard, siege1, round);
        player.playCard(closeCombatCard, closeCombat1, round);
        player.playCard(rangedCard, ranged1, round);

        assertFalse(player.getHand().containsCards(Arrays.asList(siegeCard, closeCombatCard, rangedCard)));
    }

    @Test
    public void testLaPilaDeDescarteDelJugadorSeEncuentraEnElEstadoCorrecto() {
        //ARRANGE
        DiscardPile expectedDiscardPile = new DiscardPile();
        
        //ACT
        DiscardPile actualDiscardPile = player.getDiscardPile();
        
        //ASSERT
        Assertions.assertEquals(expectedDiscardPile.getCardCount(), actualDiscardPile.getCardCount());
    }

    @Test
    public void testElPuntajeInicialDelJugadorEsCero() {
        //ARRANGE
        int expectedPoints = 0;

        //ACT
        int actualPoints = player.calculatePoints();
        
        //ASSERT
        Assertions.assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testElJugadorGanaUnaRondaCorrectamente() {
        player.winRound();

        Assertions.assertEquals(1, player.getRoundsWon());
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
        Assertions.assertEquals(expectedDiscardCount, player.getDiscardPile().getCardCount());
    }
}
