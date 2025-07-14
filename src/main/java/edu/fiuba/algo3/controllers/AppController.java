package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.Observer;
import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.views.GameView;
import edu.fiuba.algo3.views.NameInputView;
import edu.fiuba.algo3.views.PlayerPreparationView;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
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
        this.currentRound = board.getGame().getCurrentRound();
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

        // Start with player 1 preparation
        loadPlayerPreparationView(player1Name, board.getCurrentPlayerHand(), board.getCurrentPlayerDiscardPile(), board.getCurrentPlayerDeck(),
            () -> loadPlayerPreparationView(player2Name, board.getOpponentHand(), board.getOpponentDiscardPile(), board.getOpponentDeck(),
                this::loadGameView));
    }

    private void loadPlayerPreparationView(String playerName, edu.fiuba.algo3.models.cardcollections.Hand hand,
                                           edu.fiuba.algo3.models.cardcollections.DiscardPile discardPile,
                                           edu.fiuba.algo3.models.cardcollections.Deck deck,
                                           Runnable onContinue) {
        PlayerPreparationView preparationView = new PlayerPreparationView(playerName, hand, discardPile, deck, onContinue);
        transitionToView(preparationView);
    }

    public void loadGameView() {
        GameView gameView = new GameView(board);
        transitionToView(gameView);
        stage.setOnCloseRequest(closeEvent -> {
            closeEvent.consume();
            gameView.showExitConfirmation();
        });
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

    @Override
    public void update(Observable observable) {
        if (currentRound != board.getGame().getCurrentRound()) {
            currentRound = board.getGame().getCurrentRound();
            currentRound.addObserver(this);
        }
        loadGameView();
    }
}
