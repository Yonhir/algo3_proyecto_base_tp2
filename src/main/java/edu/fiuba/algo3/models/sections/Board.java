package edu.fiuba.algo3.models.sections;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.colors.Blue;
import edu.fiuba.algo3.models.colors.PlayerColor;
import edu.fiuba.algo3.models.colors.Red;
import edu.fiuba.algo3.models.json.GameLoader;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.turnManagement.Game;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private final Hand currentPlayerHand;
    private final SpecialZone specialZone;
    private final Game game;
    private final Player currentPlayer;
    private final Player opponent;

    public Board(String nombreJugador1, String nombreJugador2) {
        Player player1 = createPlayerWith(nombreJugador1, new Blue());
        Player player2 = createPlayerWith(nombreJugador2, new Red());

        Hand player1Hand = player1.getHand();
        Hand player2Hand = player2.getHand();
        Deck player1Deck = player1.getDeck();
        Deck player2Deck = player2.getDeck();

        new GameLoader().loadFromResource("gwent.json",
                player1Deck, player1Hand, player1.getDiscardPile(),
                player2Deck, player2Hand, player2.getDiscardPile());

        player1Deck.validate();
        player2Deck.validate();

        specialZone = new SpecialZone(
                player1.getCloseCombatRow(), player1.getRangedRow(), player1.getSiegeRow(),
                player2.getCloseCombatRow(), player2.getRangedRow(), player2.getSiegeRow(),
                player1.getDiscardPile(), player2.getDiscardPile()
        );

        game = startGameWith(player1, player2, specialZone);

        player1Hand.getNCardsFromDeck(player1Deck, 10);
        player2Hand.getNCardsFromDeck(player2Deck, 10);

        currentPlayerHand = game.currentPlayerHand();
        Round currentRound = game.getCurrentRound();
        currentPlayer = currentRound.getCurrentPlayer();
        opponent = currentRound.getOpponent();
    }

    private Player createPlayerWith(String name, PlayerColor color) {
        Deck playerDeck = new Deck();
        DiscardPile playerDiscardPile = new DiscardPile();

        CloseCombat playerCloseCombat = new CloseCombat(playerDiscardPile);
        Ranged playerRanged = new Ranged(playerDiscardPile);
        Siege playerSiege = new Siege(playerDiscardPile);

        return new Player(name, playerDeck, playerDiscardPile, playerCloseCombat, playerRanged, playerSiege, color);
    }

    private static Game startGameWith(Player aPlayer, Player anotherPlayer, SpecialZone specialZone) {
        int minimum = 0;
        int middle = 1;
        int maximum = 2;

        int number = ThreadLocalRandom.current().nextInt(minimum, maximum);

        if (number >= middle) {
            return new Game(anotherPlayer, aPlayer, specialZone);
        }
        return new Game(aPlayer, anotherPlayer, specialZone);
    }

    public Hand getCurrentPlayerHand() { return currentPlayerHand; }
    public Deck getCurrentPlayerDeck() { return currentPlayer.getDeck(); }
    public Deck getOpponentDeck() { return opponent.getDeck(); }
    public DiscardPile getCurrentPlayerDiscardPile() { return currentPlayer.getDiscardPile(); }
    public DiscardPile getOpponentDiscardPile() { return opponent.getDiscardPile(); }
    public CloseCombat getCurrentPlayerCloseCombat() { return currentPlayer.getCloseCombatRow(); }
    public CloseCombat getOpponentCloseCombat() { return opponent.getCloseCombatRow(); }
    public Ranged getCurrentPlayerRanged() { return currentPlayer.getRangedRow(); }
    public Ranged getOpponentRanged() { return opponent.getRangedRow(); }
    public Siege getCurrentPlayerSiege() { return currentPlayer.getSiegeRow(); }
    public Siege getOpponentSiege() { return opponent.getSiegeRow(); }
    public SpecialZone getSpecialZone() { return specialZone; }
    public Game getGame() { return game; }
}
