package edu.fiuba.algo3.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StartGameButtonHandler implements EventHandler<ActionEvent> {

    private final TextField player1Field;
    private final TextField player2Field;
    private final Label errorLabel;
    private final AppController appController;

    public StartGameButtonHandler(TextField player1Field, TextField player2Field, Label errorLabel, AppController appController) {
        this.player1Field = player1Field;
        this.player2Field = player2Field;
        this.errorLabel = errorLabel;
        this.appController = appController;
    }

    @Override
    public void handle(ActionEvent event) {
        String nombre1 = player1Field.getText().trim();
        String nombre2 = player2Field.getText().trim();

        if (nombre1.isEmpty() || nombre2.isEmpty()) {
            errorLabel.setText("Ambos jugadores deben ingresar su nombre.");
            return;
        }

        errorLabel.setText("");

        appController.startGameWithNames(nombre1, nombre2);
    }
}
