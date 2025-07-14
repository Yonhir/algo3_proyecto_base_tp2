package edu.fiuba.algo3.views;

import edu.fiuba.algo3.controllers.AppController;
import edu.fiuba.algo3.controllers.StartGameButtonHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class NameInputView extends StackPane {

    public NameInputView(AppController appController) {
        initializeLayout(appController);
    }

    private void initializeLayout(AppController appController) {
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

        StartGameButtonHandler handler = new StartGameButtonHandler(player1Field, player2Field, errorLabel, appController);
        startButton.setOnAction(handler);

        VBox form = new VBox(15, player1Field, player2Field, startButton, errorLabel);
        form.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(title, form);

        getChildren().add(layout);
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
