package edu.fiuba.algo3.vistas.components;

import javafx.scene.control.Label;

/**
 * Reusable label component for the game with consistent styling
 */
public class GameLabel extends Label {
    
    public GameLabel(String text) {
        super(text);
    }
    
    public GameLabel(String text, String style) {
        super(text);
        setStyle(style);
    }
    
    public static GameLabel createTitleLabel(String text) {
        return new GameLabel(text, "-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
    }
    
    public static GameLabel createGameTitleLabel(String text) {
        return new GameLabel(text, "-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #e74c3c;");
    }
    
    public static GameLabel createSubtitleLabel(String text) {
        return new GameLabel(text, "-fx-font-size: 18px; -fx-text-fill: #34495e;");
    }
    
    public static GameLabel createDialogTitleLabel(String text) {
        return new GameLabel(text, "-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
    }
    
    public static GameLabel createDialogMessageLabel(String text) {
        return new GameLabel(text, "-fx-font-size: 16px; -fx-text-fill: white;");
    }
    
    public static GameLabel createWarningLabel(String text) {
        return new GameLabel(text, "-fx-font-size: 14px; -fx-text-fill: #ff6b6b;");
    }
} 