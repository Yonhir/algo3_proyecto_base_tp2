package edu.fiuba.algo3.views;

import edu.fiuba.algo3.controllers.MainMenuController;
import edu.fiuba.algo3.views.components.GameButton;
import edu.fiuba.algo3.views.components.GameLabel;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainMenuView extends StackPane {
    private MainMenuController controller;
    private boolean uiInitialized = false;

    public void setController(MainMenuController controller) {
        this.controller = controller;
    }

    public Scene createScene() {
        if (!uiInitialized) {
            initializeUI();
            uiInitialized = true;
        }
        return new Scene(this, 640, 480);
    }

    private void initializeUI() {
        if (controller == null) {
            return; // Do nothing if the controller is not set
        }

        var label = GameLabel.createTitleLabel("GWENT");
        
        // Create buttons using reusable components
        GameButton startGameButton = GameButton.createPrimaryButton("Start Game");
        startGameButton.setOnAction(e -> {
            if (controller != null) {
                controller.handleStartGame();
            }
        });
        
        GameButton fullScreenButton = new GameButton("FullScreen");
        fullScreenButton.setOnAction(e -> {
            if (controller != null) {
                controller.handleToggleFullScreen();
            }
        });
        
        GameButton exitButton = new GameButton("Exit");
        exitButton.setOnAction(e -> {
            if (controller != null) {
                controller.handleExit();
            }
        });
        
        // Create VBox to stack buttons vertically
        VBox buttonContainer = new VBox(15); // 15 pixels spacing between buttons
        buttonContainer.getChildren().addAll(startGameButton, fullScreenButton, exitButton);
        buttonContainer.setAlignment(javafx.geometry.Pos.CENTER);
        
        // Create HBox to center the button container horizontally
        HBox centerContainer = new HBox();
        centerContainer.getChildren().add(buttonContainer);
        centerContainer.setAlignment(javafx.geometry.Pos.CENTER);
        
        // Create VBox to stack label and buttons vertically
        VBox vbox = new VBox(30); // 30 pixels spacing between elements
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        vbox.getChildren().addAll(label, centerContainer);
        
        // Add the main content to this StackPane
        getChildren().add(vbox);
    }
    
    public void showExitConfirmation() {
        if (controller != null) {
            controller.showExitConfirmation();
        }
    }
}
