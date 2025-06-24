package edu.fiuba.algo3.vistas.components;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Reusable game menu overlay component
 */
public class GameMenuOverlay {
    
    public static void show(StackPane rootPane, Runnable onBackToMenu, Runnable onToggleFullScreen, Runnable onExit) {
        // Get the current window size dynamically
        double windowWidth = rootPane.getScene().getWidth();
        double windowHeight = rootPane.getScene().getHeight();
        
        // Calculate responsive sizes based on window dimensions
        double menuWidth = Math.min(windowWidth * 0.4, 400); // 40% of window width, max 400px
        double menuHeight = Math.min(windowHeight * 0.5, 500); // 50% of window height, max 500px
        double buttonWidth = menuWidth * 0.7; // 70% of menu width
        double buttonHeight = menuHeight * 0.08; // 8% of menu height
        double buttonSpacing = menuHeight * 0.03; // 3% of menu height
        double titleFontSize = Math.max(menuHeight * 0.06, 18); // 6% of menu height, min 18px
        double buttonFontSize = Math.max(menuHeight * 0.04, 14); // 4% of menu height, min 14px
        double contentSpacing = menuHeight * 0.05; // 5% of menu height
        
        // Create semi-transparent background overlay
        Rectangle overlay = new Rectangle(windowWidth, windowHeight);
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(0.7);
        
        // Create menu title with dynamic font size
        GameLabel titleLabel = new GameLabel("Game Menu", 
            String.format("-fx-font-size: %.0fpx; -fx-font-weight: bold; -fx-text-fill: white;", titleFontSize));
        
        // Create menu buttons with dynamic sizing
        GameButton backToGameButton = new GameButton("Back to Game", buttonWidth, buttonHeight);
        backToGameButton.setStyle(String.format("-fx-font-size: %.0fpx; -fx-font-weight: bold;", buttonFontSize));
        backToGameButton.setOnAction(e -> hide(rootPane));
        
        GameButton backButton = new GameButton("Back to Menu", buttonWidth, buttonHeight);
        backButton.setStyle(String.format("-fx-font-size: %.0fpx; -fx-font-weight: bold; -fx-background-color: #3498db; -fx-text-fill: white;", buttonFontSize));
        backButton.setOnAction(e -> {
            hide(rootPane);
            onBackToMenu.run();
        });
        
        GameButton fullScreenButton = new GameButton("FullScreen", buttonWidth, buttonHeight);
        fullScreenButton.setStyle(String.format("-fx-font-size: %.0fpx; -fx-font-weight: bold;", buttonFontSize));
        fullScreenButton.setOnAction(e -> {
            hide(rootPane);
            onToggleFullScreen.run();
        });
        
        GameButton exitButton = new GameButton("Exit", buttonWidth, buttonHeight);
        exitButton.setStyle(String.format("-fx-font-size: %.0fpx; -fx-font-weight: bold; -fx-background-color: #e74c3c; -fx-text-fill: white;", buttonFontSize));
        exitButton.setOnAction(e -> {
            onExit.run();
        });
        
        // Create VBox to stack buttons vertically with dynamic spacing
        VBox buttonContainer = new VBox(buttonSpacing);
        buttonContainer.getChildren().addAll(backToGameButton, backButton, fullScreenButton, exitButton);
        buttonContainer.setAlignment(javafx.geometry.Pos.CENTER);
        
        // Create menu content with dynamic spacing
        VBox menuContent = new VBox(contentSpacing);
        menuContent.setAlignment(javafx.geometry.Pos.CENTER);
        menuContent.getChildren().addAll(titleLabel, buttonContainer);
        
        // Create menu background with dynamic size
        Rectangle menuBg = new Rectangle(menuWidth, menuHeight);
        menuBg.setFill(Color.rgb(44, 62, 80));
        menuBg.setArcWidth(10);
        menuBg.setArcHeight(10);
        
        // Create menu container
        StackPane menuPane = new StackPane(menuBg, menuContent);
        
        // Add overlay and menu to root pane
        rootPane.getChildren().addAll(overlay, menuPane);
    }
    
    public static void hide(StackPane rootPane) {
        System.out.println("GameMenuOverlay.hide() called, children count: " + rootPane.getChildren().size());
        
        // Remove the last two children (overlay and menu)
        // The overlay and menu are always the last two children added
        int childrenCount = rootPane.getChildren().size();
        if (childrenCount >= 2) {
            // Remove menu (last child)
            rootPane.getChildren().remove(childrenCount - 1);
            // Remove overlay (now last child)
            rootPane.getChildren().remove(childrenCount - 2);
            System.out.println("GameMenuOverlay hidden successfully");
        } else {
            System.out.println("GameMenuOverlay not found to hide");
        }
    }
} 