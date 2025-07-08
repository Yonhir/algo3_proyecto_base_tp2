package edu.fiuba.algo3.views;

import edu.fiuba.algo3.Main;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.SpecialZone;
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

    public static void startGame(Stage stage) {
        currentPlayerHand = Main.getCurrentPlayerHand();
        player1Deck = Main.getPlayer1Deck();
        player2Deck = Main.getPlayer2Deck();
        player1DiscardPile = Main.getPlayer1DiscardPile();
        player2DiscardPile = Main.getPlayer2DiscardPile();
        player1CloseCombat = Main.getPlayer1CloseCombat();
        player1Ranged = Main.getPlayer1Ranged();
        player1Siege = Main.getPlayer1Siege();
        player2CloseCombat = Main.getPlayer2CloseCombat();
        player2Ranged = Main.getPlayer2Ranged();
        player2Siege = Main.getPlayer2Siege();
        specialZone = Main.getSpecialZone();

        App app = new App();
        app.stage = stage;
        app.showGameView();

        stage.setFullScreen(true);
    }


    private void showExitConfirmation() {
        if (gameView != null) {
            gameView.showExitConfirmation();
        } else {
            ExitConfirmationDialog.show((StackPane) stage.getScene().getRoot());
        }
    }
}
