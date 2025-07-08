package edu.fiuba.algo3.views;

import edu.fiuba.algo3.Main;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.colors.Blue;
import edu.fiuba.algo3.models.colors.Red;
import edu.fiuba.algo3.models.json.GameLoader;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.turnManagement.Game;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.views.components.ExitConfirmationDialog;
import edu.fiuba.algo3.views.components.NameInputView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    private Stage stage;
    private GameView gameView;
    
    // Game objects passed from Main
    private static Hand currentPlayerHand;
    private static Deck player1Deck, player2Deck;
    private static DiscardPile player1DiscardPile, player2DiscardPile;
    private static CloseCombat player1CloseCombat, player2CloseCombat;
    private static Ranged player1Ranged, player2Ranged;
    private static Siege player1Siege, player2Siege;
    private static SpecialZone specialZone;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        NameInputView nameInputView = new NameInputView();
        Scene nameScene = nameInputView.createScene(stage);

        stage.setScene(nameScene);
        stage.setTitle("Ingresar Nombres De Jugadores");

        stage.setOnCloseRequest(event -> {
            event.consume();
            showExitConfirmation();
        });

        stage.show();
    }

    private void showGameView(GameState gameState) {
        gameView = new GameView(
                gameState.currentPlayerHand,
                gameState.player1Deck, gameState.player2Deck,
                gameState.player1DiscardPile, gameState.player2DiscardPile,
                gameState.player1CloseCombat, gameState.player1Ranged, gameState.player1Siege,
                gameState.player2CloseCombat, gameState.player2Ranged, gameState.player2Siege,
                gameState.specialZone
        );
        Scene scene = gameView.createScene();
        stage.setScene(scene);
    }

    public static void startGame(Stage stage, String nombreJugador1, String nombreJugador2) {
        App app = new App();
        app.stage = stage;

        GameState gameState = app.createGameState(nombreJugador1, nombreJugador2);
        if (gameState == null) return;

        app.showGameView(gameState);

        stage.setFullScreen(true);
    }
    private GameState createGameState(String nombreJugador1, String nombreJugador2) {
        try {
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

            Game game = new Game(player1, player2, specialZone);

            // Reparto inicial
            player1Hand.getNCardsFromDeck(player1Deck, 10);
            player2Hand.getNCardsFromDeck(player2Deck, 10);

            System.out.println("Initial cards dealt successfully!");
            System.out.println("Player 1: " + player1Hand.getCardCount() + " cards in hand");
            System.out.println("Player 2: " + player2Hand.getCardCount() + " cards in hand");

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
        } catch (Exception e) {
            System.err.println("Error initializing game: " + e.getMessage());
            return null;
        }
    }



    private void showExitConfirmation() {
        if (gameView != null) {
            gameView.showExitConfirmation();
        } else {
            ExitConfirmationDialog.show((StackPane) stage.getScene().getRoot());
        }
    }
}
