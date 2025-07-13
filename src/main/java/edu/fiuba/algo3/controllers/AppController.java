package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.Observer;
import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.views.GameView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppController implements Observer {
    private final Stage stage;
    private final Board board;

    public AppController(Stage stage, Board board) {
        this.stage = stage;
        this.board = board;
    }

    public void startGameWithNames(String player1Name, String player2Name) {
        board.setPlayerNames(player1Name, player2Name);
        loadGameView();
    }

    public void loadGameView() {
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

    @Override
    public void update(Observable observable) {
        loadGameView();
    }
}
