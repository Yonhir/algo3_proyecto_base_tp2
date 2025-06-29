package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.views.GameView;
import edu.fiuba.algo3.views.ViewManager;
import edu.fiuba.algo3.views.components.ExitConfirmationDialog;
import edu.fiuba.algo3.views.components.GameMenuOverlay;

public class GameController {
    private final ViewManager viewManager;
    private final GameView view;

    public GameController(ViewManager viewManager, GameView view) {
        this.viewManager = viewManager;
        this.view = view;

        view.setOnMenuRequested(event -> showMenuOverlay());
    }

    private void showMenuOverlay() {
        GameMenuOverlay.show(
                view,
                event -> backToMenu(),
                event -> toggleFullScreen(),
                event -> showExitConfirmation()
        );
    }

    private void backToMenu() {
        viewManager.showMainMenu();
    }

    private void toggleFullScreen() {
        viewManager.toggleFullScreen();
    }

    private void showExitConfirmation() {
        GameMenuOverlay.hide(view);
        ExitConfirmationDialog.show(view);
    }
}
