package edu.fiuba.algo3;

import edu.fiuba.algo3.controllers.GameState;
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
        // Initialize game objects and save them in static attributes
        initializeGame();

        // Deal initial cards to players
        dealInitialCards();
        
        // Start the JavaFX application with all game objects
        App.main(args, 
                game.getCurrentRound().getCurrentPlayer().getHand(),
                player1Deck, player2Deck,
                player1DiscardPile, player2DiscardPile,
                player1CloseCombat, player1Ranged, player1Siege,
                player2CloseCombat, player2Ranged, player2Siege,
                specialZone);
    }
    
    private static void initializeGame() {
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
            Player player1 = new Player("Player 1", player1Deck, player1DiscardPile,
                player1CloseCombat, player1Ranged, player1Siege, new Blue());
            Player player2 = new Player("Player 2", player2Deck, player2DiscardPile,
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

            GameState.getInstance().setRoundActual(game.getCurrentRound());
        } catch (Exception e) {
            System.err.println("Error initializing game: " + e.getMessage());
        }
    }
    
    private static void dealInitialCards() {
        // Deal initial cards to players (10 cards each)
        player1Hand.getNCardsFromDeck(player1Deck, 13);
        player2Hand.getNCardsFromDeck(player2Deck, 13);
        
        System.out.println("Initial cards dealt successfully!");
        System.out.println("Player 1: " + player1Hand.getCardCount() + " cards in hand");
        System.out.println("Player 2: " + player2Hand.getCardCount() + " cards in hand");
    }
}
