package edu.fiuba.algo3.vistas.components;

import javafx.scene.control.Label;

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
} 