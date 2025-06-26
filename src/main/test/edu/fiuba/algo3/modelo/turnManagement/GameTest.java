package edu.fiuba.algo3.modelo.turnManagement;

import edu.fiuba.algo3.modelo.cards.specials.weathers.TorrentialRain;
import edu.fiuba.algo3.modelo.cards.units.modifiers.Modifier;
import edu.fiuba.algo3.modelo.colors.*;
import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.specials.weathers.BitingFrost;
import edu.fiuba.algo3.modelo.cards.specials.weathers.Weather;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.errors.NotEnoughCardsInDeckError;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;
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
    private DiscardPile discardPile;

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
    private List<Card> especiales;
    private List<Card> unidades;
    private Card unit1;
    private Card special2;
    @BeforeEach
    public void setUp() {
        unit1 = new Unit("Nombre", "Descripcion", 4, new CloseCombatType(), new ArrayList<Modifier>());
        special2 = new TorrentialRain("Nombre", "Descripcion");
        deck1 = new Deck();
        deck2 = new Deck();
        deck1.addCard(unit1);
        deck2.addCard(special2);

        discardPile1 = new DiscardPile();
        discardPile2 = new DiscardPile();
        player1CloseCombatRow = new CloseCombat(discardPile1);
        player1RangedRow = new Ranged(discardPile1);
        player1SiegeRow = new Siege(discardPile1);
        player2CloseCombatRow = new CloseCombat(discardPile2);
        player2RangedRow = new Ranged(discardPile2);
        player2SiegeRow = new Siege(discardPile2);
        specialZone = new SpecialZone(
                player1CloseCombatRow, player1RangedRow, player1SiegeRow,
                player2CloseCombatRow, player2RangedRow, player2SiegeRow,
                discardPile1, discardPile2
        );


        round = new Round(player1, player2);
        player1 = new Player("nombre1", deck1, discardPile1, player1CloseCombatRow, player1RangedRow, player1SiegeRow, new Blue());
        player2 = new Player("nombre2", deck2, discardPile2, player2CloseCombatRow, player2RangedRow, player2SiegeRow, new Red());
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
        player1CloseCombatRow.placeCard(unit1,round);
        specialZone.placeCard(special2, round);

        game.passRound();
        game.passRound();

        assertTrue(discardPile1.containsCard(unit1));
        assertFalse(discardPile1.containsCard(special2));
        assertTrue(discardPile2.containsCard(special2));
        assertFalse(discardPile2.containsCard(unit1));

    }
}
