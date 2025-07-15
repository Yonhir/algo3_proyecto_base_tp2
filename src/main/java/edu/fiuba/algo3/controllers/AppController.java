package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.Observer;
import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.views.GameView;
import edu.fiuba.algo3.views.GameWinnerView;
import edu.fiuba.algo3.views.NameInputView;
import edu.fiuba.algo3.views.PlayerPreparationView;
import edu.fiuba.algo3.models.turnManagement.Player;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import edu.fiuba.algo3.views.components.PlayerNameScreen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AppController implements Observer {
    private final Stage stage;
    private final Board board;
    private Round currentRound;
    private final StackPane rootPane;

    public AppController(Stage stage, Board board) {
        this.stage = stage;
        this.board = board;
        this.currentRound = board.getRound();
        this.currentRound.addObserver(this);

        // Initialize the main scene with a root pane
        this.rootPane = new StackPane();
        this.rootPane.setStyle("-fx-background-color: black;");
        Scene mainScene = new Scene(rootPane);
        stage.setScene(mainScene);
        stage.setFullScreen(true);
        stage.setTitle("Gwent");
        stage.show();
    }

    public void loadNameInputView() {
        NameInputView nameInputView = new NameInputView(this);
        transitionToView(nameInputView);
    }

    public void startGameWithNames(String player1Name, String player2Name) {
        board.setPlayerNames(player1Name, player2Name);
        loadPlayer1PreparationView();
    }

    private void loadPlayerPreparationView(String playerName, Hand hand, DiscardPile discardPile, Deck deck, EventHandler<ActionEvent> onContinueHandler) {
        PlayerPreparationView preparationView = new PlayerPreparationView(playerName, hand, discardPile, deck, onContinueHandler);
        transitionToView(preparationView);
    }

    public void loadGameView() {
        // Check if game is finished before loading GameView
        if (board.getGame().gameFinished()) {
            loadGameWinnerView();
            return;
        }

        GameView gameView = new GameView(board);
        transitionToView(gameView);
        stage.setOnCloseRequest(closeEvent -> {
            closeEvent.consume();
            gameView.showExitConfirmation();
        });
    }

    private void loadGameWinnerView() {
        Player winner = board.getGame().gameWinner();
        Player player1 = board.getPlayer1();
        Player player2 = board.getPlayer2();
        
        GameWinnerView winnerView = new GameWinnerView(winner, player1, player2, this);
        transitionToView(winnerView);
    }

    public void restartGame() {
        board.restartGame();
        this.currentRound = board.getRound();
        this.currentRound.addObserver(this);
        loadNameInputView();
    }

    public void transitionToView(Node newView) {
        // Set initial opacity to 0 for fade-in effect
        newView.setOpacity(0);

        // Add the new view to the root pane
        rootPane.getChildren().add(newView);

        // Create fade-in transition
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newView);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        // If there's an existing view, fade it out first
        if (rootPane.getChildren().size() > 1) {
            Node oldView = rootPane.getChildren().get(0);

            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), oldView);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            fadeOut.setOnFinished(event -> {
                rootPane.getChildren().remove(oldView);
                fadeIn.play();
            });

            fadeOut.play();
        } else {
            // No existing view, fade in
            fadeIn.play();
        }
    }

    public void loadPlayer1PreparationView() {
        Player player1 = board.getPlayer1();
        loadPlayerPreparationView(
            player1.getName(),
            player1.getHand(),
            player1.getDiscardPile(),
            player1.getDeck(),
            new ButtonConfirmPlayer1PreparationHandler(this)
        );
    }

    public void loadPlayer2PreparationView() {
        Player player2 = board.getPlayer2();
        loadPlayerPreparationView(
            player2.getName(),
            player2.getHand(),
            player2.getDiscardPile(),
            player2.getDeck(),
            new ButtonConfirmPlayer2PreparationHandler(this)
        );
    }

    @Override
    public void update(Observable observable) {
        if (currentRound != board.getRound()) {
            currentRound = board.getRound();
            currentRound.addObserver(this);
        }
        loadGameView();
    }
}
