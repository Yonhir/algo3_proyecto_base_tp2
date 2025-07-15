package edu.fiuba.algo3.views;

import edu.fiuba.algo3.controllers.AppController;
import edu.fiuba.algo3.controllers.RestartGameButtonHandler;
import edu.fiuba.algo3.models.turnManagement.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameWinnerView extends StackPane {

    public GameWinnerView(Player winner, Player player1, Player player2, AppController appController) {
        initializeLayout(winner, player1, player2, appController);
    }

    private void initializeLayout(Player winner, Player player1, Player player2, AppController appController) {
        VBox layout = new VBox(30);
        layout.setPadding(new Insets(50));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #C19A6B; -fx-border-color: #8B4513; -fx-border-width: 5px;");

        // Title
        Label title = new Label("¡Fin del Juego!");
        title.setStyle("-fx-font-size: 48px; -fx-text-fill: #4B2E0F; -fx-font-weight: bold;");

        // Winner announcement or draw
        Label resultLabel;
        if (winner == null) {
            // Draw scenario - both players have 2 rounds won
            resultLabel = new Label("¡Empate!");
            resultLabel.setStyle("-fx-font-size: 36px; -fx-text-fill: #D4AF37; -fx-font-weight: bold;");
        } else {
            // Winner scenario
            resultLabel = new Label("¡" + winner.getName() + " ha ganado!");
            resultLabel.setStyle("-fx-font-size: 36px; -fx-text-fill: #D4AF37; -fx-font-weight: bold;");
        }

        // Final score
        VBox scoreBox = new VBox(10);
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.setStyle("-fx-background-color: #F5DEB3; -fx-border-color: #8B4513; -fx-border-width: 2px; -fx-padding: 20;");

        Label scoreTitle = new Label("Puntuación Final:");
        scoreTitle.setStyle("-fx-font-size: 24px; -fx-text-fill: #4B2E0F; -fx-font-weight: bold;");

        Label player1Score = new Label(player1.getName() + ": " + player1.getRoundsWon() + " rondas ganadas");
        player1Score.setStyle("-fx-font-size: 20px; -fx-text-fill: #4B2E0F;");

        Label player2Score = new Label(player2.getName() + ": " + player2.getRoundsWon() + " rondas ganadas");
        player2Score.setStyle("-fx-font-size: 20px; -fx-text-fill: #4B2E0F;");

        scoreBox.getChildren().addAll(scoreTitle, player1Score, player2Score);

        // Buttons
        VBox buttonBox = new VBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        Button restartButton = new Button("Jugar de Nuevo");
        restartButton.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 15 30 15 30;");
        restartButton.setOnAction(new RestartGameButtonHandler(appController));

        Button exitButton = new Button("Salir");
        exitButton.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 15 30 15 30;");
        exitButton.setOnAction(event -> System.exit(0));

        buttonBox.getChildren().addAll(restartButton, exitButton);

        layout.getChildren().addAll(title, resultLabel, scoreBox, buttonBox);
        getChildren().add(layout);
    }
} 