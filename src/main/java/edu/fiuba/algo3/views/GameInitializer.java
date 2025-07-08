package edu.fiuba.algo3.views;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.colors.Blue;
import edu.fiuba.algo3.models.colors.Red;
import edu.fiuba.algo3.models.json.GameLoader;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.turnManagement.Game;
import edu.fiuba.algo3.models.turnManagement.Player;

import java.util.concurrent.ThreadLocalRandom;

public class GameInitializer {
    public GameState createInitialState(String nombreJugador1, String nombreJugador2) {
        Deck player1Deck = new Deck();
        DiscardPile player1DiscardPile = new DiscardPile();
        Deck player2Deck = new Deck();
        DiscardPile player2DiscardPile = new DiscardPile();

        CloseCombat player1CloseCombat = new CloseCombat(player1DiscardPile);
        Ranged player1Ranged = new Ranged(player1DiscardPile);
        Siege player1Siege = new Siege(player1DiscardPile);

        CloseCombat player2CloseCombat = new CloseCombat(player2DiscardPile);
        Ranged player2Ranged = new Ranged(player2DiscardPile);
        Siege player2Siege = new Siege(player2DiscardPile);

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

        SpecialZone specialZone = new SpecialZone(
                player1CloseCombat, player1Ranged, player1Siege,
                player2CloseCombat, player2Ranged, player2Siege,
                player1DiscardPile, player2DiscardPile
        );

        Game game = startGameWith(player1, player2, specialZone);

        player1Hand.getNCardsFromDeck(player1Deck, 10);
        player2Hand.getNCardsFromDeck(player2Deck, 10);

        return new GameState(
                game.getCurrentRound().getCurrentPlayer().getHand(),
                player1Deck, player2Deck,
                player1DiscardPile, player2DiscardPile,
                player1CloseCombat, player2CloseCombat,
                player1Ranged, player2Ranged,
                player1Siege, player2Siege,
                specialZone,
                game
        );


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
}

