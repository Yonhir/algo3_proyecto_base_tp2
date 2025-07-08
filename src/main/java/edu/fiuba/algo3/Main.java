package edu.fiuba.algo3;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.colors.Blue;
import edu.fiuba.algo3.models.colors.Red;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.turnManagement.Game;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.json.GameLoader;
import edu.fiuba.algo3.views.App;
import javafx.application.Application;

public class Main {
    // Static attributes to save initialized objects
    private static Hand player1Hand, player2Hand;
    private static Deck player1Deck, player2Deck;
    private static DiscardPile player1DiscardPile, player2DiscardPile;
    private static CloseCombat player1CloseCombat, player2CloseCombat;
    private static Ranged player1Ranged, player2Ranged;
    private static Siege player1Siege, player2Siege;
    private static SpecialZone specialZone;
    private static Game game;

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
    
    public static void initializeGame(String nombreJugador1, String nombreJugador2) {
        try {
            // Create card collections for player 1
            player1Deck = new Deck();
            player1DiscardPile = new DiscardPile();
            
            // Create card collections for player 2
            player2Deck = new Deck();
            player2DiscardPile = new DiscardPile();
            
            // Create rows for player 1
            player1CloseCombat = new CloseCombat(player1DiscardPile);
            player1Ranged = new Ranged(player1DiscardPile);
            player1Siege = new Siege(player1DiscardPile);
            
            // Create rows for player 2
            player2CloseCombat = new CloseCombat(player2DiscardPile);
            player2Ranged = new Ranged(player2DiscardPile);
            player2Siege = new Siege(player2DiscardPile);
            
            // Create players first (they will create their own hands internally)
            Player player1 = new Player(nombreJugador1, player1Deck, player1DiscardPile,
                player1CloseCombat, player1Ranged, player1Siege, new Blue());
            Player player2 = new Player(nombreJugador2, player2Deck, player2DiscardPile,
                player2CloseCombat, player2Ranged, player2Siege, new Red());
            
            // Get the hands from the players
            player1Hand = player1.getHand();
            player2Hand = player2.getHand();
            
            // Load decks from JSON file using the actual player hands
            GameLoader gameLoader = new GameLoader();
            gameLoader.loadFromResource("gwent.json", 
                player1Deck, player1Hand, player1DiscardPile,
                player2Deck, player2Hand, player2DiscardPile);
            
            // Validate decks
            player1Deck.validate();
            player2Deck.validate();
            
            // Create the SpecialZone
            specialZone = new SpecialZone(
                player1CloseCombat, player1Ranged, player1Siege,
                player2CloseCombat, player2Ranged, player2Siege,
                player1DiscardPile, player2DiscardPile
            );
            
            // Create game
            game = new Game(player1, player2, specialZone);
            
            
        } catch (Exception e) {
            System.err.println("Error initializing game: " + e.getMessage());
        }
    }
    
    public static void dealInitialCards() {
        // Deal initial cards to players (10 cards each)
        player1Hand.getNCardsFromDeck(player1Deck, 10);
        player2Hand.getNCardsFromDeck(player2Deck, 10);
        
        System.out.println("Initial cards dealt successfully!");
        System.out.println("Player 1: " + player1Hand.getCardCount() + " cards in hand");
        System.out.println("Player 2: " + player2Hand.getCardCount() + " cards in hand");
    }

    public static Hand getCurrentPlayerHand() { return game.getCurrentRound().getCurrentPlayer().getHand(); }
    public static Deck getPlayer1Deck() { return player1Deck; }
    public static Deck getPlayer2Deck() { return player2Deck; }
    public static DiscardPile getPlayer1DiscardPile() { return player1DiscardPile; }
    public static DiscardPile getPlayer2DiscardPile() { return player2DiscardPile; }
    public static CloseCombat getPlayer1CloseCombat() { return player1CloseCombat; }
    public static CloseCombat getPlayer2CloseCombat() { return player2CloseCombat; }
    public static Ranged getPlayer1Ranged() { return player1Ranged; }
    public static Ranged getPlayer2Ranged() { return player2Ranged; }
    public static Siege getPlayer1Siege() { return player1Siege; }
    public static Siege getPlayer2Siege() { return player2Siege; }
    public static SpecialZone getSpecialZone() { return specialZone; }

}
