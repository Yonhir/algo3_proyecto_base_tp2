package edu.fiuba.algo3.views.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameMenuOverlay {
    
    public static void show(
        StackPane rootPane,
        EventHandler<ActionEvent> onBackToMenu,
        EventHandler<ActionEvent> onToggleFullScreen,
        EventHandler<ActionEvent> onExit) {

        double windowWidth = rootPane.getScene().getWidth();
        double windowHeight = rootPane.getScene().getHeight();
        
        double menuWidth = Math.min(windowWidth * 0.4, 400);
        double menuHeight = Math.min(windowHeight * 0.5, 500);
        double buttonWidth = menuWidth * 0.7;
        double buttonHeight = menuHeight * 0.08;
        double buttonSpacing = menuHeight * 0.03;
        double titleFontSize = Math.max(menuHeight * 0.06, 18);
        double buttonFontSize = Math.max(menuHeight * 0.04, 14);
        double contentSpacing = menuHeight * 0.05;
        
        Rectangle overlay = new Rectangle(windowWidth, windowHeight);
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(0.7);
        
        GameLabel titleLabel = new GameLabel("Game Menu", 
            String.format("-fx-font-size: %.0fpx; -fx-font-weight: bold; -fx-text-fill: white;", titleFontSize));
        
        GameButton backToGameButton = new GameButton("Back to Game", buttonWidth, buttonHeight);
        backToGameButton.setStyle(String.format("-fx-font-size: %.0fpx; -fx-font-weight: bold;", buttonFontSize));
        backToGameButton.setOnAction(e -> hide(rootPane));
        
        GameButton backButton = new GameButton("Back to Menu", buttonWidth, buttonHeight);
        backButton.setStyle(String.format("-fx-font-size: %.0fpx; -fx-font-weight: bold; -fx-background-color: #3498db; -fx-text-fill: white;", buttonFontSize));
        backButton.setOnAction(e -> {
            hide(rootPane);
            onBackToMenu.handle(e);
        });
        
        GameButton fullScreenButton = new GameButton("FullScreen", buttonWidth, buttonHeight);
        fullScreenButton.setStyle(String.format("-fx-font-size: %.0fpx; -fx-font-weight: bold;", buttonFontSize));
        fullScreenButton.setOnAction(e -> {
            hide(rootPane);
            onToggleFullScreen.handle(e);
        });
        
        GameButton exitButton = new GameButton("Exit", buttonWidth, buttonHeight);
        exitButton.setStyle(String.format("-fx-font-size: %.0fpx; -fx-font-weight: bold; -fx-background-color: #e74c3c; -fx-text-fill: white;", buttonFontSize));
        exitButton.setOnAction(onExit);
        
        VBox buttonContainer = new VBox(buttonSpacing);
        buttonContainer.getChildren().addAll(backToGameButton, backButton, fullScreenButton, exitButton);
        buttonContainer.setAlignment(javafx.geometry.Pos.CENTER);
        
        VBox menuContent = new VBox(contentSpacing);
        menuContent.setAlignment(javafx.geometry.Pos.CENTER);
        menuContent.getChildren().addAll(titleLabel, buttonContainer);
        
        Rectangle menuBg = new Rectangle(menuWidth, menuHeight);
        menuBg.setFill(Color.rgb(44, 62, 80));
        menuBg.setArcWidth(10);
        menuBg.setArcHeight(10);
        
        StackPane menuPane = new StackPane(menuBg, menuContent);
        
        rootPane.getChildren().addAll(overlay, menuPane);
    }
    
    public static void hide(StackPane rootPane) {
        int childrenCount = rootPane.getChildren().size();
        if (childrenCount >= 2) {
            rootPane.getChildren().remove(childrenCount - 1);
            rootPane.getChildren().remove(childrenCount - 2);
        }
    }
}
