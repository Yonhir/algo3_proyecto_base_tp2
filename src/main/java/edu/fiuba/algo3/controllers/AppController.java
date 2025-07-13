package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.views.GameView;
import edu.fiuba.algo3.views.PlayerPreparationView;
import edu.fiuba.algo3.views.components.DiscardCardDialog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppController {
    private final Stage stage;
    private void displayBoard(Board board) {
        GameView gameView = new GameView(
                board.getGame(), board.getCurrentPlayerHand(),
                board.getPlayer1Deck(), board.getPlayer2Deck(),
                board.getPlayer1DiscardPile(), board.getPlayer2DiscardPile(),
                board.getPlayer1CloseCombat(), board.getPlayer1Ranged(), board.getPlayer1Siege(),
                board.getPlayer2CloseCombat(), board.getPlayer2Ranged(), board.getPlayer2Siege(),
                board.getSpecialZone()
        );

        stage.setScene(gameView.createScene());
        stage.setFullScreen(true);

        stage.setOnCloseRequest(event -> {
            event.consume();
            gameView.showExitConfirmation();
        });
    }

    public AppController(Stage stage) {
        this.stage = stage;
    }

    public void startGameWithNames(String nombreJugador1, String nombreJugador2) {
        Board board;
        try {
            board = new Board(nombreJugador1, nombreJugador2);
        } catch (Exception e) {
            System.err.println("No se pudo inicializar el juego: " + e.getMessage());
            return;
        }
        stage.setTitle("Juego en curso - Gwent");
        PlayerPreparationView.show(stage, nombreJugador1, board.getPlayer1Hand(), board.getPlayer1DiscardPile(), board.getPlayer1Deck(),
                () -> PlayerPreparationView.show(stage, nombreJugador2, board.getPlayer2Hand(), board.getPlayer2DiscardPile(), board.getPlayer2Deck(),
                        () -> displayBoard(board)
                )
        );
    }
}

