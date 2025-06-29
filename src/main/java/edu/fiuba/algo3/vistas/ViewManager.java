package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.GameController;
import edu.fiuba.algo3.controllers.MainMenuController;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ViewManager {
    private final Stage stage;
    private Scene currentScene;

    private boolean isFullScreen = false;

    private MainMenuView mainMenuView;
    private GameView gameView;

    public ViewManager(Stage stage) {
        this.stage = stage;
    }

    public void showMainMenu() {
        mainMenuView = new MainMenuView();
        gameView = null;

        MainMenuController controller = new MainMenuController(this, mainMenuView);
        mainMenuView.setController(controller);

        currentScene = mainMenuView.createScene();

        stage.setScene(currentScene);
        if (isFullScreen) {
            stage.setFullScreen(true);
        }
    }

    public void showGameView() {
        gameView = new GameView();
        mainMenuView = null;

        GameController controller = new GameController(this, gameView);

        currentScene = gameView.createScene();

        stage.setScene(currentScene);
        if (isFullScreen) {
            stage.setFullScreen(true);
        }
    }

    public void toggleFullScreen() {
        isFullScreen = !isFullScreen;
        stage.setFullScreen(isFullScreen);
    }

    public void showExitConfirmation() {
        if (mainMenuView != null) {
            mainMenuView.showExitConfirmation();
        } else if (gameView != null) {
            gameView.showExitConfirmation();
        }
    }
}
