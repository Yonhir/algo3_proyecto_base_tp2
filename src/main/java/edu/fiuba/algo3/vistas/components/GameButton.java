package edu.fiuba.algo3.vistas.components;

import javafx.scene.control.Button;

/**
 * Reusable button component for the game with consistent styling
 */
public class GameButton extends Button {
    
    public GameButton(String text) {
        super(text);
        setDefaultStyle();
    }
    
    public GameButton(String text, String customStyle) {
        super(text);
        setStyle(customStyle);
    }

    public GameButton(String text, double width, double height) {
        super(text);
        setDefaultStyle();
        setPrefWidth(width);
        setPrefHeight(height);
    }
    
    public GameButton(String text, double width, double height, String customStyle) {
        super(text);
        setPrefWidth(width);
        setPrefHeight(height);
        setStyle(customStyle);
    }
    
    private void setDefaultStyle() {
        setPrefWidth(120);
        setPrefHeight(40);
        setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
    }
    
    public static GameButton createPrimaryButton(String text) {
        return new GameButton(text, 120, 40, 
            "-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #3498db; -fx-text-fill: white;");
    }
    
    public static GameButton createMenuButton() {
        String menuButtonStyle = "-fx-font-weight: bold; -fx-background-color: #34495e; -fx-text-fill: white; -fx-padding: 0; -fx-alignment: center; -fx-content-display: center;";
        return new GameButton("Menu", menuButtonStyle);
    }
}
