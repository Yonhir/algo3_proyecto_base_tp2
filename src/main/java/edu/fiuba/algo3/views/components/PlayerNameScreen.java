package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.controllers.PlayerNameScreenController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PlayerNameScreen extends StackPane {
    
    private VBox layout;

    public PlayerNameScreen(String name) {
        initialize(name);
    }

    private void initialize(String name) {
        layout = new VBox(20);
        layout.setStyle("-fx-background-color: #C19A6B; -fx-border-color: #8B4513; -fx-border-width: 5px;");
        layout.setAlignment(Pos.CENTER);

        Label playerName = new Label("Es el turno del Jugador: " + name);
        playerName.setStyle("-fx-font-size: 36px; -fx-text-fill: #4B2E0F; -fx-font-weight: bold;");

        Button continueBtn = new Button("Continuar");
        continueBtn.setStyle("-fx-background-color: #D2691E; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10 20 10 20;");
        continueBtn.setOnAction(new PlayerNameScreenController(this));

        layout.getChildren().add(playerName);
        layout.getChildren().add(continueBtn);

        getChildren().add(layout);
    }
    
    public void hide() {
        setVisible(false);
    }
}
