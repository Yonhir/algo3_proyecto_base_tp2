package edu.fiuba.algo3.views;

import edu.fiuba.algo3.views.components.ExitConfirmationDialog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    private Stage stage;
    private GameView gameView;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        showGameView();
        
        // Handle window close request (X button)
        stage.setOnCloseRequest(event -> {
            event.consume(); // Prevent default close behavior
            showExitConfirmation();
        });
        
        stage.setFullScreen(true);
        stage.show();
    }

    private void showGameView() {
        gameView = new GameView();
        Scene scene = gameView.createScene();
        stage.setScene(scene);
    }

    private void showExitConfirmation() {
        if (gameView != null) {
            gameView.showExitConfirmation();
        } else {
            ExitConfirmationDialog.show((StackPane) stage.getScene().getRoot());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}