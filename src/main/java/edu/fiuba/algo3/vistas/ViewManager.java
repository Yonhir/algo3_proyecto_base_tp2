package edu.fiuba.algo3.vistas;

import javafx.scene.Scene;
import javafx.stage.Stage;
import edu.fiuba.algo3.vistas.components.ExitConfirmationDialog;

/**
 * Manages different views/scenes in the application
 */
public class ViewManager {
    private Stage stage;
    private Scene currentScene;
    private boolean isFullScreen = false;
    private MainMenuView currentMainMenuView;
    private GameView currentGameView;

    public ViewManager(Stage stage) {
        this.stage = stage;
    }

    public void showMainMenu() {
        currentMainMenuView = new MainMenuView(this);
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

    public boolean isFullScreen() {
        return isFullScreen;
    }

    public Stage getStage() {
        return stage;
    }

    public double getWindowWidth() {
        return stage.getWidth();
    }

    public double getWindowHeight() {
        return stage.getHeight();
    }

    /**
     * Show exit confirmation dialog when user tries to close the window
     */
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