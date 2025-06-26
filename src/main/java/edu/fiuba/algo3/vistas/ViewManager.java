package edu.fiuba.algo3.vistas;

import javafx.scene.Scene;
import javafx.stage.Stage;
import edu.fiuba.algo3.controllers.MainMenuController;

public class ViewManager {
    private final Stage stage;
    private Scene currentScene;
    private boolean isFullScreen = false;
    private MainMenuView currentMainMenuView;
    private GameView currentGameView;

    public ViewManager(Stage stage) {
        this.stage = stage;
    }

    public void showMainMenu() {
        // Create the view with default constructor
        currentMainMenuView = new MainMenuView();
        // Create the controller with the view's root pane
        MainMenuController controller = new MainMenuController(this, currentMainMenuView);
        // Set the controller in the view
        currentMainMenuView.setController(controller);
        currentGameView = null; // Clear game view reference
        currentScene = currentMainMenuView.createScene();

        stage.setScene(currentScene);
        // Preserve fullscreen state
        if (isFullScreen) {
            stage.setFullScreen(true);
        }
    }

    public void showGameView() {
        currentGameView = new GameView(this);
        currentMainMenuView = null; // Clear main menu view reference
        currentScene = currentGameView.createScene();
        
        stage.setScene(currentScene);
        // Preserve fullscreen state
        if (isFullScreen) {
            stage.setFullScreen(true);
        }
    }

    public void toggleFullScreen() {
        isFullScreen = !isFullScreen;
        stage.setFullScreen(isFullScreen);
    }

    public void showExitConfirmation() {
        // Show exit confirmation on the current view's root pane
        if (currentMainMenuView != null) {
            System.out.println("Showing exit confirmation on MainMenuView");
            currentMainMenuView.showExitConfirmation();
        } else if (currentGameView != null) {
            System.out.println("Showing exit confirmation on GameView");
            // For game view, use its showExitConfirmation method
            currentGameView.showExitConfirmation();
        } else {
            System.out.println("No current view found for exit confirmation");
        }
    }
}
