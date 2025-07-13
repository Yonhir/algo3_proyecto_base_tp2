package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.views.GameView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppController {
    private final Stage stage;

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

        GameView gameView = new GameView(board);

        Scene scene = gameView.createScene();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("Gwent - Juego en curso");

        stage.setOnCloseRequest(event -> {
            event.consume();
            gameView.showExitConfirmation();
        });

        stage.show();
    }
}
