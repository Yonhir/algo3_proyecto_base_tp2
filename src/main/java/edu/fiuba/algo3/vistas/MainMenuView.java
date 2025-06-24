package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.vistas.components.ExitConfirmationDialog;
import edu.fiuba.algo3.vistas.components.GameButton;
import edu.fiuba.algo3.vistas.components.GameLabel;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Main Menu View - Contains the main menu interface
 */
public class MainMenuView {
    private final ViewManager viewManager;
    private StackPane rootPane;

    public MainMenuView(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public Scene createScene() {
        var label = GameLabel.createTitleLabel("GWENT");
        
        // Create buttons using reusable components
        GameButton startGameButton = GameButton.createPrimaryButton("Start Game");
        startGameButton.setOnAction(e -> startGame());
        
        GameButton fullScreenButton = new GameButton("FullScreen");
        fullScreenButton.setOnAction(e -> toggleFullScreen());
        
        GameButton exitButton = new GameButton("Exit");
        exitButton.setOnAction(e -> showExitConfirmation());
        
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
        
        // Create root pane to hold main content and overlay
        rootPane = new StackPane(vbox);
        
        return new Scene(rootPane, 640, 480);
    }
    
    private void toggleFullScreen() {
        viewManager.toggleFullScreen();
    }
    
    private void startGame() {
        viewManager.showGameView();
    }
    
    public void showExitConfirmation() {
        ExitConfirmationDialog.show(rootPane);
    }
} 