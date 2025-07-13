package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.Observer;
import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.views.GameView;
import edu.fiuba.algo3.views.PlayerPreparationView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppController implements Observer {
    private final Stage stage;
    private final Board board;
    private Round currentRound;

    public AppController(Stage stage, Board board) {
        this.stage = stage;
        this.board = board;
        this.currentRound = board.getGame().getCurrentRound();
        this.currentRound.addObserver(this);
    }

    public void startGameWithNames(String player1Name, String player2Name) {
        board.setPlayerNames(player1Name, player2Name);

        PlayerPreparationView.show(stage, player1Name, board.getCurrentPlayerHand(), board.getCurrentPlayerDiscardPile(), board.getCurrentPlayerDeck(), () -> PlayerPreparationView.show(stage, player2Name, board.getOpponentHand(), board.getOpponentDiscardPile(), board.getOpponentDeck(), () -> loadGameView()));
    }

    public void loadGameView() {
        GameView gameView = new GameView(board);
        Scene scene = gameView.createScene();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("Gwent - Juego en curso");

        stage.setOnCloseRequest(closeEvent -> {
            closeEvent.consume();
            gameView.showExitConfirmation();
        });

        stage.show();
    }

    @Override
    public void update(Observable observable) {
        if (currentRound != board.getGame().getCurrentRound()) {
            currentRound = board.getGame().getCurrentRound();
            currentRound.addObserver(this);
        }
        loadGameView();
    }
}
