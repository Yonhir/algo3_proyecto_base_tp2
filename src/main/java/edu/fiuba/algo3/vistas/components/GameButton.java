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
    
    public static GameButton createDangerButton(String text) {
        return new GameButton(text, 120, 40, 
            "-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #e74c3c; -fx-text-fill: white;");
    }
    
    public static GameButton createLargeButton(String text) {
        return new GameButton(text, 150, 40, 
            "-fx-font-size: 16px; -fx-font-weight: bold;");
    }
} 