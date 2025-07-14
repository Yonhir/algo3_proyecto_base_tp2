package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.controllers.PlayerNameScreenController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerNameScreenView extends StackPane {

    public static void show(String name, Stage stage, Runnable onContinue) {
        javafx.stage.Screen screen = javafx.stage.Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        double windowWidth = bounds.getWidth();
        double windowHeight = bounds.getHeight();

        VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: #C19A6B; -fx-border-color: #8B4513; -fx-border-width: 5px;");
        layout.setAlignment(Pos.CENTER);

        Label playerName = new Label("Es el turno del Jugador: " + name);
        playerName.setStyle("-fx-font-size: 36px; -fx-text-fill: #4B2E0F; -fx-font-weight: bold;");

        Button continueBtn = new Button("Continuar");
        continueBtn.setStyle("-fx-background-color: #D2691E; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10 20 10 20;");
        continueBtn.setOnAction(new PlayerNameScreenController(onContinue));

        layout.getChildren().add(playerName);
        layout.getChildren().add(continueBtn);

        StackPane root = new StackPane(layout);

        stage.setScene(new Scene(root, windowWidth, windowHeight));
        stage.setFullScreen(true);
    }
}
