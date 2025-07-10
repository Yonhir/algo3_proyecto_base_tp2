package edu.fiuba.algo3.views.components;


import edu.fiuba.algo3.controllers.StartGameHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NameInputView {

    public interface OnStartGame {
        void start(String nombreJugador1, String nombreJugador2);
    }

    public Scene createScene(OnStartGame callback) {
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

        StartGameHandler handler = new StartGameHandler(player1Field, player2Field, errorLabel, layout, callback);
        startButton.setOnAction(handler);

        VBox form = new VBox(15, player1Field, player2Field, startButton, errorLabel);
        form.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(title, form);

        StackPane root = new StackPane(layout);
        return new Scene(root, 800, 600);
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
