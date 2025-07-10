package edu.fiuba.algo3.views.components;

import javafx.scene.control.Button;

public class PassTurnButton extends Button {

    public PassTurnButton(String text) {
        super(text);

        setPrefWidth(200);
        setPrefHeight(70);
        setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-background-color: #e74c3c");
        setOnAction(e -> {
            System.out.println("el jugador pasó el turno");
        });
    }
}
