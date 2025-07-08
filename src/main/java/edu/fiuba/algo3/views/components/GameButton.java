package edu.fiuba.algo3.views.components;

import javafx.scene.control.Button;

public class GameButton extends Button {

    public GameButton(String text, String customStyle) {
        super(text);
        setStyle(customStyle);
    }
}
