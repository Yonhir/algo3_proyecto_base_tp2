package edu.fiuba.algo3.models.turnManagement;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.colors.*;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoundTest {

    private Player player1;
    private Player player2;
    private Round round;
    private Game game;
    private Unit unidad;

    private SpecialZone specialZone;
    private CloseCombat player1CloseCombatRow;
    private Ranged player1RangedRow;
    private Siege player1SiegeRow;
    private CloseCombat player2CloseCombatRow;
    private Ranged player2RangedRow;
    private Siege player2SiegeRow;
    private DiscardPile discardPile1;
    private DiscardPile discardPile2;
    @BeforeEach
    public void setUp() {
        discardPile1 = new DiscardPile();
        discardPile2 = new DiscardPile();
        player1CloseCombatRow = new CloseCombat(discardPile1);
        player1RangedRow = new Ranged(discardPile1);
        player1SiegeRow = new Siege(discardPile1);
        player2CloseCombatRow = new CloseCombat(discardPile2);
        player2RangedRow = new Ranged(discardPile2);
        player2SiegeRow = new Siege(discardPile2);

        player1 = new Player("nombre1", new Deck(), discardPile1, player1CloseCombatRow, player1RangedRow, player1SiegeRow, new Blue());
        player2 = new Player("nombre2", new Deck(), discardPile2, player2CloseCombatRow, player2RangedRow, player2SiegeRow, new Red());
        unidad = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>());
        unidad.setColor(new Blue());
        round = new Round(player1, player2);
        specialZone = new SpecialZone(player1CloseCombatRow, player1RangedRow, player1SiegeRow,
                player2CloseCombatRow, player2RangedRow, player2SiegeRow,
                discardPile1, discardPile2);
        game = new Game(player1, player2, specialZone);
    }

    @Test
    public void testCurrentPlayerSwitchesWhenOpponentHasNotPassed() {
        Player first = round.getCurrentPlayer();
        round.playerPlayedCard();
        assertNotSame(first, round.getCurrentPlayer());
    }

    @Test
    public void testCurrentPlayerDoesNotSwitchIfOpponentHasPassed() {
        round.passTurn(game);
        Player current = round.getCurrentPlayer();
        round.passTurn(game);
        assertSame(current, round.getCurrentPlayer());
    }

    @Test
    public void testRoundEndsWhenBothPlayersPass() {
        round.passTurn(game);
        round.passTurn(game);

        assertTrue(round.getState() instanceof BothPassedState);
    }

    @Test
    public void testAssignVictoryIncrementsWinnerRoundCount() {
        player1.getHand().addCard(unidad);
        player1.playCard(unidad, player1CloseCombatRow , round);

        round.assignVictory();

        assertEquals(1, player1.getRoundsWon());
        assertEquals(0, player2.getRoundsWon());
    }

    @Test
    public void testAssignVictoryDoesNothingOnDraw() {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);

        when(p1.calculatePoints()).thenReturn(10);
        when(p2.calculatePoints()).thenReturn(10);

        Round round = new Round(p1, p2);

        round.assignVictory();

        verify(p1, never()).winRound();
        verify(p2, never()).winRound();
    }

    @Test
    public void testAssignVictoryDoesNotIncrementRoundsWonOnDraw() {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);

        when(p1.calculatePoints()).thenReturn(10);
        when(p2.calculatePoints()).thenReturn(10);

        Round round = new Round(p1, p2);

        round.assignVictory();

        verify(p1, never()).winRound();
        verify(p2, never()).winRound();
    }

    @Test
    public void testPlayerSwitchesIfOpponentHasNotPassed() {
        Round round = new Round(player1, player2);
        Player first = round.getCurrentPlayer();

        round.playerPlayedCard();

        assertNotSame(first, round.getCurrentPlayer());
    }

    @Test
    public void testRondaCompletaConGanador() {
        //Arrange
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();

        Card cartaP1 = new Unit("Espadachín", "Fuerte", 10, new CloseCombatType(), new ArrayList<>());
        cartaP1.setColor(new Blue());
        deck1.addCard(cartaP1);

        Card cartaP2 = new Unit("Ladrón", "Débil", 2, new CloseCombatType(), new ArrayList<>());
        cartaP2.setColor(new Red());
        deck2.addCard(cartaP2);

        DiscardPile discardPile1 = new DiscardPile();
        DiscardPile discardPile2 = new DiscardPile();

        CloseCombat cc1 = new CloseCombat(discardPile1);
        Ranged ranged1 = new Ranged(discardPile1);
        Siege siege1 = new Siege(discardPile1);

        CloseCombat cc2 = new CloseCombat(discardPile2);
        Ranged ranged2 = new Ranged(discardPile2);
        Siege siege2 = new Siege(discardPile2);

        Player player1 = new Player("Jugador1", deck1, discardPile1, cc1, ranged1, siege1, new Blue());
        Player player2 = new Player("Jugador2", deck2, discardPile2, cc2, ranged2, siege2, new Red());

        SpecialZone specialZone = new SpecialZone(cc1, ranged1, siege1, cc2, ranged2, siege2, discardPile1, discardPile2);
        Game game = new Game(player1, player2, specialZone);
        Round round = game.getCurrentRound();

        //Repartimos las cartas a la mano
        player1.getHand().getNCardsFromDeck(deck1, 1);
        player2.getHand().getNCardsFromDeck(deck2, 1);

        //Turno jugador 1
        player1.playCard(cartaP1, cc1, round);

        //Turno jugador 2
        player2.playCard(cartaP2, cc2, round);

        // Ambos pasan
        game.passRound();
        game.passRound();

        //Assert
        assertEquals(1, player1.getRoundsWon());
        assertEquals(0, player2.getRoundsWon());
    }
}

