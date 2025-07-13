package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.views.GameView;
import edu.fiuba.algo3.views.PlayerPreparationView;
import javafx.stage.Stage;

public class AppController {
    private final Stage stage;

    private void displayBoard(Board board) {
        GameView gameView = new GameView(board);

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
        PlayerPreparationView.show(stage, nombreJugador1, board.getPlayer1Hand(), board.getCurrentPlayerDiscardPile(), board.getCurrentPlayerDeck(),
                () -> PlayerPreparationView.show(stage, nombreJugador2, board.getPlayer2Hand(), board.getOpponentDiscardPile(), board.getOpponentDeck(),
                        () -> displayBoard(board)
                )
        );
    }
}