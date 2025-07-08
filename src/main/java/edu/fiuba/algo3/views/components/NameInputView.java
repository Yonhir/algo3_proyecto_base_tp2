package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.Main;
import edu.fiuba.algo3.views.App;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NameInputView {

    public Scene createScene(Stage stage) {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(50));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #C19A6B; -fx-border-color: #8B4513; -fx-border-width: 5px;");

        Label title = new Label("¡Bienvenido a Gwent!");
        title.setStyle("-fx-font-size: 36px; -fx-text-fill: #4B2E0F; -fx-font-weight: bold;");

        TextField player1Field = createStyledTextField("Nombre del Jugador 1");
        TextField player2Field = createStyledTextField("Nombre del Jugador 2");

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setStyle("-fx-font-size: 16px;");

        Button startButton = new Button("Iniciar Juego");
        startButton.setStyle("-fx-background-color: #D2691E; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10 20 10 20;");

        startButton.setOnAction(e -> {
            String nombre1 = player1Field.getText().trim();
            String nombre2 = player2Field.getText().trim();

            if (nombre1.isEmpty() || nombre2.isEmpty()) {
                errorLabel.setText("Ambos jugadores deben ingresar su nombre.");
                return;
            }

            try {
                FadeTransition fade = new FadeTransition(Duration.millis(1000), layout);
                fade.setFromValue(1.0);
                fade.setToValue(0.0);
                fade.setOnFinished(event -> {
                    Main.nombreJugador1 = nombre1;
                    Main.nombreJugador2 = nombre2;
                    App app = new App();
                    try {
                        app.start(stage);
                    } catch (Exception ex) {
                        errorLabel.setText("No se pudo iniciar el juego.");
                        ex.printStackTrace();
                    }
                });
                fade.play();
            } catch (Exception ex) {
                errorLabel.setText("Error al iniciar: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        VBox form = new VBox(15, player1Field, player2Field, startButton, errorLabel);
        form.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(title, form);

        StackPane root = new StackPane(layout);
        Scene scene = new Scene(root, 800, 600);
        return scene;
    }

    private TextField createStyledTextField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-background-color: #F5DEB3;" +
                        "-fx-border-color: #654321;" +
                        "-fx-border-width: 2px;" +
                        "-fx-padding: 8;"
        );
        field.setMaxWidth(300);
        return field;
    }
}
