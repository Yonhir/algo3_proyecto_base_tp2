package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.ViewManager;
import edu.fiuba.algo3.vistas.components.ExitConfirmationDialog;
import javafx.scene.layout.StackPane;


public class MainMenuController {
    private final ViewManager viewManager;
    private final StackPane rootPane;

    public MainMenuController(ViewManager viewManager, StackPane rootPane) {
        this.viewManager = viewManager;
        this.rootPane = rootPane;
    }

    public void handleStartGame() {
        // Any business logic before starting the game can go here,
        // For example validation, initialization, etc.
        viewManager.startGame();
    }

    public void handleToggleFullScreen() {
        viewManager.toggleFullScreen();
    }

    public void handleExit() {
        showExitConfirmation();
    }

    public void showExitConfirmation() {
        ExitConfirmationDialog.show(rootPane);
    }
}
