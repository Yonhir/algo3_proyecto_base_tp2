package edu.fiuba.algo3.models.turnManagement;

import edu.fiuba.algo3.models.cards.specials.weathers.TorrentialRain;
import edu.fiuba.algo3.models.cards.units.modifiers.Modifier;
import edu.fiuba.algo3.models.colors.*;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;

    private SpecialZone specialZone;
    private CloseCombat player1CloseCombatRow;
    private Ranged player1RangedRow;
    private Siege player1SiegeRow;
    private CloseCombat player2CloseCombatRow;
    private Ranged player2RangedRow;
    private Siege player2SiegeRow;
    private Round round;
    private DiscardPile discardPile1;
    private DiscardPile discardPile2;

    private Deck deck1;
    private Deck deck2;

    private Card unit1;
    private Card special2;

    @BeforeEach
    public void setUp() {
        unit1 = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<Modifier>());
        special2 = new TorrentialRain("Nombre", "Descripcion");
        player1 = new Player("nombre1", new Blue());
        player2 = new Player("nombre2", new Red());
        deck1 = player1.getDeck();
        deck2 = player2.getDeck();
        unit1.setColor(new Blue());
        special2.setColor(new Red());
        deck1.addCard(unit1);
        deck2.addCard(special2);

        discardPile1 = player1.getDiscardPile();
        discardPile2 = player2.getDiscardPile();
        player1CloseCombatRow = player1.getCloseCombatRow();
        player1RangedRow = player1.getRangedRow();
        player1SiegeRow = player1.getSiegeRow();
        player2CloseCombatRow = player2.getCloseCombatRow();
        player2RangedRow = player2.getRangedRow();
        player2SiegeRow = player2.getSiegeRow();
        specialZone = new SpecialZone(
                player1CloseCombatRow, player1RangedRow, player1SiegeRow,
                player2CloseCombatRow, player2RangedRow, player2SiegeRow,
                discardPile1, discardPile2
        );


        round = new Round(player1, player2);
        game = new Game(player1, player2, specialZone);
    }

    @Test
    public void testPlayersCanPassAndRoundEnds() {
        game.passRound();
        game.passRound();
        assertFalse(game.gameFinished());
    }

    @Test
    public void testGameEndsAfterTwoWins() {
        player1.winRound();
        player1.winRound();
        assertTrue(game.gameFinished());
        assertEquals(player1, game.gameWinner());
    }

    @Test
    public void testClearBoardCallsDiscardAllRows() {
        Player mock1 = mock(Player.class);
        Player mock2 = mock(Player.class);
        SpecialZone mock3 = mock(SpecialZone.class);

        when(mock1.getRoundsWon()).thenReturn(0);
        when(mock2.getRoundsWon()).thenReturn(0);

        Game game = new Game(mock1, mock2, mock3);

        game.passRound();
        game.passRound();

        verify(mock1).discardAllRows();
        verify(mock2).discardAllRows();
    }

    @Test
    public void testGameEndsInDrawAfterThreeRoundsWithOneWinEach() {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        SpecialZone mock3 = mock(SpecialZone.class);


        when(p1.getRoundsWon()).thenReturn(1);
        when(p2.getRoundsWon()).thenReturn(1);
        when(p1.hasWonGame()).thenReturn(false);
        when(p2.hasWonGame()).thenReturn(false);

        Game game = new Game(p1, p2, mock3);

        assertTrue(game.bothPlayersWonARound());
    }

    @Test
    public void testClearBoardAndAllCardsGoToTheirCorrespondingDiscardPile(){
        player1CloseCombatRow.placeCard(unit1, round);
        specialZone.placeCard(special2, round);

        game.passRound();
        game.passRound();

        assertTrue(discardPile1.containsCard(unit1));
        assertFalse(discardPile1.containsCard(special2));
        assertTrue(discardPile2.containsCard(special2));
        assertFalse(discardPile2.containsCard(unit1));

    }

    @Test
    public void testPartidaCompletaConVictoriaFinalDelJugador1() {
        //Preparación de mazos
        Player player1 = new Player("Jugador1", new Blue());
        Player player2 = new Player("Jugador2", new Red());

        Deck deck1 = player1.getDeck();
        Deck deck2 = player2.getDeck();

        Card cartaFuerte1 = new Unit("Espadachín", "Fuerte", 10, new CloseCombatType(), new ArrayList<>());
        cartaFuerte1.setColor(new Blue());
        Card cartaFuerte2 = new Unit("Arquero", "Fuerte", 9, new RangedType(), new ArrayList<>());
        cartaFuerte2.setColor(new Blue());
        Card cartaDebil1 = new Unit("debilucho1", "Débil", 2, new CloseCombatType(), new ArrayList<>());
        cartaDebil1.setColor(new Red());
        Card cartaDebil2 = new Unit("debilucho2", "Débil", 1, new RangedType(), new ArrayList<>());
        cartaDebil2.setColor(new Red());

        deck1.insertCardsInOrder(Arrays.asList(cartaFuerte1, cartaFuerte2));
        deck2.insertCardsInOrder(Arrays.asList(cartaDebil1, cartaDebil2));

        DiscardPile discardPile1 = player1.getDiscardPile();
        DiscardPile discardPile2 = player2.getDiscardPile();

        CloseCombat cc1 = player1.getCloseCombatRow();
        Ranged ranged1 = player1.getRangedRow();
        Siege siege1 = player1.getSiegeRow();

        CloseCombat cc2 = player2.getCloseCombatRow();
        Ranged ranged2 = player2.getRangedRow();
        Siege siege2 = player2.getSiegeRow();

        SpecialZone specialZone = new SpecialZone(cc1, ranged1, siege1, cc2, ranged2, siege2, discardPile1, discardPile2);
        Game game = new Game(player1, player2, specialZone);

        // --- Ronda 1 ---
        Card carta1P1 = deck1.retrieveNTopCards(1).get(0); player1.getHand().addCard(carta1P1);
        Card carta1P2 = deck2.retrieveNTopCards(1).get(0); player2.getHand().addCard(carta1P2);

        Round round1 = game.getCurrentRound();
        player1.playCard(carta1P1, cc1, round1);
        player2.playCard(carta1P2, cc2, round1);

        game.passRound();
        game.passRound();

        assertEquals(1, player1.getRoundsWon());
        assertEquals(0, player2.getRoundsWon());

        // --- Ronda 2 ---
        Card carta2P1 = deck1.retrieveNTopCards(1).get(0); player1.getHand().addCard(carta2P1);
        Card carta2P2 = deck2.retrieveNTopCards(1).get(0); player2.getHand().addCard(carta2P2);

        Round round2 = game.getCurrentRound();
        player1.playCard(carta2P1, ranged1, round2);
        player2.playCard(carta2P2, ranged2, round2);

        game.passRound();
        game.passRound();

        // Verificaciones finales
        assertTrue(game.gameFinished());
        assertEquals(player1, game.gameWinner());
        assertEquals(2, player1.getRoundsWon());
        assertEquals(0, player2.getRoundsWon());
    }

    @Test
    public void testSePuedeObtenerElJugadorActual() {
        Player currentPlayer = game.getCurrentPlayer();

        assertEquals(player1, currentPlayer);
    }


}
