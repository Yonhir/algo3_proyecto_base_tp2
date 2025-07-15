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

    public void restartGame() {
        initializeMatch();
    }

    private void initializeMatch() {
        player1 = new Player(DEFAULT_PLAYER_NAME_1, new Blue());
        player2 = new Player(DEFAULT_PLAYER_NAME_2, new Red());

        Hand player1Hand = player1.getHand();
        Hand player2Hand = player2.getHand();
        Deck player1Deck = player1.getDeck();
        Deck player2Deck = player2.getDeck();
        DiscardPile player1DiscardPile = player1.getDiscardPile();
        DiscardPile player2DiscardPile = player2.getDiscardPile();

        new GameLoader().loadFromResource("gwent.json",
                player1Deck, player1Hand, player1DiscardPile,
                player2Deck, player2Hand, player2DiscardPile);

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

    public Round getRound() {
        return game.getCurrentRound();
    }

    public Player getCurrentPlayer(){
        return game.getCurrentPlayer();
    }

    public Player getOpponentPlayer(){
        return game.getOpponentPlayer();
    }

    public Hand getCurrentPlayerHand() {
        return getCurrentPlayer().getHand();
    }

    public Hand getOpponentHand(){
        return getOpponentPlayer().getHand();
    }

    public Deck getCurrentPlayerDeck() {
        return getCurrentPlayer().getDeck();
    }

    public Deck getOpponentDeck() {
        return getOpponentPlayer().getDeck();
    }

    public DiscardPile getCurrentPlayerDiscardPile() {
        return getCurrentPlayer().getDiscardPile();
    }

    public DiscardPile getOpponentDiscardPile() {
        return getOpponentPlayer().getDiscardPile();
    }

    public CloseCombat getCurrentPlayerCloseCombat() {
        return getCurrentPlayer().getCloseCombatRow();
    }

    public CloseCombat getOpponentCloseCombat() {
        return getOpponentPlayer().getCloseCombatRow();
    }

    public Ranged getCurrentPlayerRanged() {
        return getCurrentPlayer().getRangedRow();
    }

    public Ranged getOpponentRanged() {
        return getOpponentPlayer().getRangedRow();
    }

    public Siege getCurrentPlayerSiege() {
        return getCurrentPlayer().getSiegeRow();
    }

    public Siege getOpponentSiege() {
        return getOpponentPlayer().getSiegeRow();
    }

    public SpecialZone getSpecialZone() {
        return specialZone;
    }

    public Game getGame() {
        return game;
    }

    public void setPlayerNames(String player1Name, String player2Name) {
        player1.changeName(player1Name);
        player2.changeName(player2Name);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
