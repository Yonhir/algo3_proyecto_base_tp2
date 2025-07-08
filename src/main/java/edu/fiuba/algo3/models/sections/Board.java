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

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private final Hand currentPlayerHand;
    private final Deck player1Deck, player2Deck;
    private final DiscardPile player1DiscardPile, player2DiscardPile;
    private final CloseCombat player1CloseCombat, player2CloseCombat;
    private final Ranged player1Ranged, player2Ranged;
    private final Siege player1Siege, player2Siege;
    private final SpecialZone specialZone;
    private final Game game;

    public Board(String nombreJugador1, String nombreJugador2) {

        player1Deck = new Deck();
        player2Deck = new Deck();
        player1DiscardPile = new DiscardPile();
        player2DiscardPile = new DiscardPile();


        player1CloseCombat = new CloseCombat(player1DiscardPile);
        player1Ranged = new Ranged(player1DiscardPile);
        player1Siege = new Siege(player1DiscardPile);

        player2CloseCombat = new CloseCombat(player2DiscardPile);
        player2Ranged = new Ranged(player2DiscardPile);
        player2Siege = new Siege(player2DiscardPile);


        Player player1 = new Player(nombreJugador1, player1Deck, player1DiscardPile,
                player1CloseCombat, player1Ranged, player1Siege, new Blue());
        Player player2 = new Player(nombreJugador2, player2Deck, player2DiscardPile,
                player2CloseCombat, player2Ranged, player2Siege, new Red());

        Hand player1Hand = player1.getHand();
        Hand player2Hand = player2.getHand();


        new GameLoader().loadFromResource("gwent.json",
                player1Deck, player1Hand, player1DiscardPile,
                player2Deck, player2Hand, player2DiscardPile);
        player1Deck.validate();
        player2Deck.validate();


        specialZone = new SpecialZone(
                player1CloseCombat, player1Ranged, player1Siege,
                player2CloseCombat, player2Ranged, player2Siege,
                player1DiscardPile, player2DiscardPile
        );


        game = startGameWith(player1, player2, specialZone);


        player1Hand.getNCardsFromDeck(player1Deck, 10);
        player2Hand.getNCardsFromDeck(player2Deck, 10);


        currentPlayerHand = game.currentPlayerHand();
    }

    private Game startGameWith(Player a, Player b, SpecialZone zone) {
        return ThreadLocalRandom.current().nextInt(2) == 0
                ? new Game(a, b, zone)
                : new Game(b, a, zone);
    }


    public Hand currentPlayerHand() { return currentPlayerHand; }
    public Deck player1Deck() { return player1Deck; }
    public Deck player2Deck() { return player2Deck; }
    public DiscardPile player1DiscardPile() { return player1DiscardPile; }
    public DiscardPile player2DiscardPile() { return player2DiscardPile; }
    public CloseCombat player1CloseCombat() { return player1CloseCombat; }
    public CloseCombat player2CloseCombat() { return player2CloseCombat; }
    public Ranged player1Ranged() { return player1Ranged; }
    public Ranged player2Ranged() { return player2Ranged; }
    public Siege player1Siege() { return player1Siege; }
    public Siege player2Siege() { return player2Siege; }
    public SpecialZone specialZone() { return specialZone; }
    public Game game() { return game; }
}
