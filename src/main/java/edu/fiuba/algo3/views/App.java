package edu.fiuba.algo3.views;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.views.components.ExitConfirmationDialog;
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

    public static void main(String[] args, 
                          Hand currentPlayerHand,
                          Deck player1Deck, Deck player2Deck,
                          DiscardPile player1DiscardPile, DiscardPile player2DiscardPile,
                          CloseCombat player1CloseCombat, Ranged player1Ranged, Siege player1Siege,
                          CloseCombat player2CloseCombat, Ranged player2Ranged, Siege player2Siege,
                          SpecialZone specialZone) {
        // Store the game objects
        App.currentPlayerHand = currentPlayerHand;
        App.player1Deck = player1Deck;
        App.player2Deck = player2Deck;
        App.player1DiscardPile = player1DiscardPile;
        App.player2DiscardPile = player2DiscardPile;
        App.player1CloseCombat = player1CloseCombat;
        App.player1Ranged = player1Ranged;
        App.player1Siege = player1Siege;
        App.player2CloseCombat = player2CloseCombat;
        App.player2Ranged = player2Ranged;
        App.player2Siege = player2Siege;
        App.specialZone = specialZone;
        
        // Start the JavaFX application
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        showGameView();
        
        stage.setOnCloseRequest(event -> {
            event.consume();
            showExitConfirmation();
        });
        
        stage.setFullScreen(true);
        stage.show();
    }

    private void showGameView() {
        gameView = new GameView(
            currentPlayerHand,
            player1Deck, player2Deck,
            player1DiscardPile, player2DiscardPile,
            player1CloseCombat, player1Ranged, player1Siege,
            player2CloseCombat, player2Ranged, player2Siege,
            specialZone
        );
        Scene scene = gameView.createScene();
        stage.setScene(scene);
    }

    private void showExitConfirmation() {
        if (gameView != null) {
            gameView.showExitConfirmation();
        } else {
            ExitConfirmationDialog.show((StackPane) stage.getScene().getRoot());
        }
    }
}
