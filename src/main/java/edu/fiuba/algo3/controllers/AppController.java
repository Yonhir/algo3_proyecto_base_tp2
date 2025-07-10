package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.views.GameView;
import edu.fiuba.algo3.views.components.PlayerNameScreenView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppController {
    private final Stage stage;
    private Board board;

    public AppController(Stage stage) {
        this.stage = stage;
    }

    public void startGameWithNames(String nombreJugador1, String nombreJugador2) {
        try {
            board = new Board(nombreJugador1, nombreJugador2);
        } catch (Exception e) {
            System.err.println("No se pudo inicializar el juego: " + e.getMessage());
            return;
        }

        PlayerNameScreenView playerNameScreenView = new PlayerNameScreenView(board.getCurrentPlayer().getName());

        stage.setScene(playerNameScreenView.createScene(() -> { startGame(); }));

        stage.setFullScreen(true);
        stage.setTitle("Gwent - Juego en curso");
        stage.show();
    }

    public void startGame() {
        GameView gameView = new GameView(
                board.getCurrentPlayerHand(),
                board.getPlayer1Deck(), board.getPlayer2Deck(),
                board.getPlayer1DiscardPile(), board.getPlayer2DiscardPile(),
                board.getPlayer1CloseCombat(), board.getPlayer1Ranged(), board.getPlayer1Siege(),
                board.getPlayer2CloseCombat(), board.getPlayer2Ranged(), board.getPlayer2Siege(),
                board.getSpecialZone()
        );

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
