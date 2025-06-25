package edu.fiuba.algo3.modelo.turnManagement;

import edu.fiuba.algo3.modelo.Colors.Blue;
import edu.fiuba.algo3.modelo.Colors.Red;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.discovery.DirectorySelector;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoundTest {

    private Player player1;
    private Player player2;
    private Round round;
    private Game game;
    private Unit unidad;
    private CloseCombat closeCombat;
    private Ranged ranged;
    private Siege siege;
    private DiscardPile discardPile;

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
        player1CloseCombatRow = new CloseCombat(discardPile);
        player1RangedRow = new Ranged(discardPile);
        player1SiegeRow = new Siege(discardPile);
        player2CloseCombatRow = new CloseCombat(discardPile);
        player2RangedRow = new Ranged(discardPile);
        player2SiegeRow = new Siege(discardPile);
        specialZone = new SpecialZone(
                player1CloseCombatRow, player1RangedRow, player1SiegeRow,
                player2CloseCombatRow, player2RangedRow, player2SiegeRow,
                discardPile1, discardPile2
        );

        closeCombat = new CloseCombat(discardPile);
        ranged = new Ranged(discardPile);
        siege = new Siege(discardPile);
        player1 = new Player("nombre1", new Deck(), closeCombat, ranged, siege, new Blue());
        player2 = new Player("nombre2", new Deck(), new CloseCombat(discardPile), new Ranged(discardPile), new Siege(discardPile), new Red());
        unidad = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<>());
        round = new Round(player1, player2);
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
        player1.playCard(unidad, closeCombat , round);

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

}

