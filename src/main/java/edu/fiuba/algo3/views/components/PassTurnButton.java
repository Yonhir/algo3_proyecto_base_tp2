package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.controllers.PassTurnButtonHandler;
import edu.fiuba.algo3.models.turnManagement.Game;
import javafx.scene.control.Button;

public class PassTurnButton extends Button {

    public PassTurnButton(String text, Game game) {
        super(text);
        setPrefWidth(200);
        setPrefHeight(70);
        setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-background-color: #e74c3c");
        setOnAction(new PassTurnButtonHandler(game));
    }
}
