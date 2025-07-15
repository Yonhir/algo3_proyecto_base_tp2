package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.views.NameInputView;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class StartGameHandler implements EventHandler<ActionEvent> {

    private final TextField player1Field;
    private final TextField player2Field;
    private final Label errorLabel;
    private final Pane layout;
    private final NameInputView.OnStartGame callback;

    public StartGameHandler(TextField player1Field, TextField player2Field, Label errorLabel, Pane layout, NameInputView.OnStartGame callback) {
        this.player1Field = player1Field;
        this.player2Field = player2Field;
        this.errorLabel = errorLabel;
        this.layout = layout;
        this.callback = callback;
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

        FadeTransition fade = new FadeTransition(Duration.millis(1000), layout);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(e -> callback.start(nombre1, nombre2));
        fade.play();
    }
}
