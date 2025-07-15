package edu.fiuba.algo3.models.sections;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.colors.Blue;
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
    private static final String DEFAULT_PLAYER_NAME_1 = "Player 1";
    private static final String DEFAULT_PLAYER_NAME_2 = "Player 2";

    private SpecialZone specialZone;
    private Game game;
    private Player player1, player2;

    public Board() {
        initializeMatch();
    }

    private void initializeMatch() {
        player1 = new Player(DEFAULT_PLAYER_NAME_1, new Blue());
        player2 = new Player(DEFAULT_PLAYER_NAME_2, new Red());

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

    public Round getRound() { return game.getCurrentRound(); }
    public Player getCurrentPlayer(){ return game.getCurrentPlayer();}
    public Player getOpponentPlayer(){ return game.getOpponentPlayer();}
    public Hand getCurrentPlayerHand() { return game.currentPlayerHand(); }
    public Hand getOpponentHand(){ return game.getCurrentRound().getOpponent().getHand(); }
    public Deck getCurrentPlayerDeck() { return game.getCurrentRound().getCurrentPlayer().getDeck(); }
    public Deck getOpponentDeck() { return game.getCurrentRound().getOpponent().getDeck(); }
    public DiscardPile getCurrentPlayerDiscardPile() { return game.getCurrentRound().getCurrentPlayer().getDiscardPile(); }
    public DiscardPile getOpponentDiscardPile() { return game.getCurrentRound().getOpponent().getDiscardPile(); }
    public CloseCombat getCurrentPlayerCloseCombat() { return game.getCurrentRound().getCurrentPlayer().getCloseCombatRow(); }
    public CloseCombat getOpponentCloseCombat() { return game.getCurrentRound().getOpponent().getCloseCombatRow(); }
    public Ranged getCurrentPlayerRanged() { return game.getCurrentRound().getCurrentPlayer().getRangedRow(); }
    public Ranged getOpponentRanged() { return game.getCurrentRound().getOpponent().getRangedRow(); }
    public Siege getCurrentPlayerSiege() { return game.getCurrentRound().getCurrentPlayer().getSiegeRow(); }
    public Siege getOpponentSiege() { return game.getCurrentRound().getOpponent().getSiegeRow(); }
    public SpecialZone getSpecialZone() { return specialZone; }
    public Game getGame() { return game; }

    public void setPlayerNames(String player1Name, String player2Name) {
        player1.changeName(player1Name);
        player2.changeName(player2Name);
    }
}
