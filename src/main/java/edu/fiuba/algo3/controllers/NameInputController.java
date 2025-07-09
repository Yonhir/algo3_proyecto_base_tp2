package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.views.components.NameInputView;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NameInputController {
    private final NameInputView.OnStartGame callback;
    private final Stage stage;
    private final Pane layout;
    private final Label errorLabel;

    public NameInputController(Stage stage, Pane layout, Label errorLabel, NameInputView.OnStartGame callback) {
        this.stage = stage;
        this.layout = layout;
        this.errorLabel = errorLabel;
        this.callback = callback;
    }

    public void handleStart(String nombre1, String nombre2) {
        if (nombre1.isEmpty() || nombre2.isEmpty()) {
            errorLabel.setText("Ambos jugadores deben ingresar su nombre.");
            return;
        }


        try {
            FadeTransition fade = new FadeTransition(Duration.millis(1000), layout);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(event -> callback.start(nombre1, nombre2));
            fade.play();
        } catch (Exception ex) {
            errorLabel.setText("Error al iniciar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

