package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.views.GameView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppController {
    private final Stage stage;
    private final Board board;

    public AppController(Stage stage, Board board) {
        this.stage = stage;
        this.board = board;
    }

    public void startGameWithNames(String player1Name, String player2Name) {
        board.setPlayerNames(player1Name, player2Name);

        GameView gameView = new GameView(
                board.getGame(), board.getCurrentPlayerHand(),
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
