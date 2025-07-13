package edu.fiuba.algo3.views;

import edu.fiuba.algo3.controllers.ButtonContinueForPreparationHandler;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.views.components.DiscardCardDialog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import edu.fiuba.algo3.models.cardcollections.Hand;


public class PlayerPreparationView {
    public static void show(
            Stage stage,
            String playerName,
            Hand hand,
            DiscardPile discardPile,
            Deck deck,
            Runnable onContinue
    ) {
        VBox screenPreparation = new VBox(20);
        screenPreparation.setPadding(new Insets(50));
        screenPreparation.setAlignment(Pos.CENTER);
        screenPreparation.setStyle("-fx-background-color: #C19A6B; -fx-border-color: #8B4513; -fx-border-width: 5px;");

        Label labelTurn = new Label("Turno de " + playerName);
        labelTurn.setStyle("-fx-font-size: 36px; -fx-text-fill: #4B2E0F; -fx-font-weight: bold;");

        Button continueButton = new Button("Continuar");
        continueButton.setStyle("-fx-font-size: 24px; -fx-background-color: #27AE60; -fx-text-fill: white;-fx-font-weight: bold;");
        continueButton.setOnAction(new ButtonContinueForPreparationHandler(stage, playerName, hand, discardPile, deck, onContinue));

        screenPreparation.getChildren().addAll(labelTurn, continueButton);
        stage.setScene(new Scene(screenPreparation, 1000, 700));
    }
}

