package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.models.turnManagement.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class UIPlayerInfo extends VBox {

    private final Label nameLabel;
    private Label roundsValueLabel;
    private Label pointsValueLabel;
    private final Label currentPlayerIndicator;

    private final Player player;
    private final boolean isCurrentPlayer;

    public UIPlayerInfo(Player player, boolean isCurrentPlayer) {
        this.player = player;
        this.isCurrentPlayer = isCurrentPlayer;

        setAlignment(Pos.CENTER);
        setSpacing(15);
        setPadding(new Insets(20, 25, 20, 25));
        setMaxWidth(350);
        setMinHeight(120);
        
        // Create components first
        currentPlayerIndicator = createCurrentPlayerIndicator();
        nameLabel = new Label(player.getName());
        nameLabel.setAlignment(Pos.CENTER);
        
        VBox infoContainer = createInfoContainer();
        HBox roundsBox = createRoundsSection();
        HBox pointsBox = createPointsSection();
        infoContainer.getChildren().addAll(roundsBox, pointsBox);

        // Apply comprehensive styling based on player status
        if (isCurrentPlayer) {
            applyCurrentPlayerStyling();
        } else {
            applyOpponentStyling();
        }

        // Add components to main container
        if (isCurrentPlayer && currentPlayerIndicator != null) {
            getChildren().addAll(currentPlayerIndicator, nameLabel, infoContainer);
        } else {
            getChildren().addAll(nameLabel, infoContainer);
        }
    }

    private void applyCurrentPlayerStyling() {
        // Main container styling
        setStyle(
            "-fx-background-color: linear-gradient(to bottom right, #8B4513, #A0522D); " +
            "-fx-background-radius: 15px; " +
            "-fx-border-color: linear-gradient(to bottom right, #DAA520, #B8860B); " +
            "-fx-border-width: 3px; " +
            "-fx-border-radius: 15px; " +
            "-fx-effect: dropshadow(gaussian, rgba(139, 69, 19, 0.6), 12, 0, 0, 4);"
        );

        // Name label styling
        nameLabel.setStyle(
            "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
            "-fx-font-size: 22px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: #F5DEB3; " +
            "-fx-effect: dropshadow(gaussian, rgba(218, 165, 32, 0.8), 3, 0, 0, 2);"
        );

        // Rounds value styling
        roundsValueLabel.setStyle(
            "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
            "-fx-font-size: 20px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: #DAA520; " +
            "-fx-effect: dropshadow(gaussian, rgba(218, 165, 32, 0.6), 2, 0, 0, 1);"
        );

        // Points value styling
        pointsValueLabel.setStyle(
            "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
            "-fx-font-size: 20px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: #90EE90; " +
            "-fx-effect: dropshadow(gaussian, rgba(144, 238, 144, 0.6), 2, 0, 0, 1);"
        );
    }

    private void applyOpponentStyling() {
        // Main container styling
        setStyle(
            "-fx-background-color: linear-gradient(to bottom right, #696969, #808080); " +
            "-fx-background-radius: 15px; " +
            "-fx-border-color: linear-gradient(to bottom right, #A9A9A9, #C0C0C0); " +
            "-fx-border-width: 2px; " +
            "-fx-border-radius: 15px; " +
            "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 6, 0, 0, 2);"
        );

        // Name label styling
        nameLabel.setStyle(
            "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
            "-fx-font-size: 20px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: #D3D3D3; " +
            "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.6), 2, 0, 0, 1);"
        );

        // Rounds value styling
        roundsValueLabel.setStyle(
            "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
            "-fx-font-size: 18px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: #C0C0C0; " +
            "-fx-effect: dropshadow(gaussian, rgba(192, 192, 192, 0.3), 1, 0, 0, 1);"
        );

        // Points value styling
        pointsValueLabel.setStyle(
            "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
            "-fx-font-size: 18px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: #98FB98; " +
            "-fx-effect: dropshadow(gaussian, rgba(152, 251, 152, 0.3), 1, 0, 0, 1);"
        );
    }

    private Label createCurrentPlayerIndicator() {
        if (isCurrentPlayer) {
            Label indicator = new Label("TU TURNO");
            indicator.setStyle(
                "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
                "-fx-font-size: 12px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #DAA520; " +
                "-fx-background-color: rgba(218, 165, 32, 0.2); " +
                "-fx-background-radius: 8px; " +
                "-fx-padding: 4px 8px; " +
                "-fx-effect: dropshadow(gaussian, rgba(218, 165, 32, 0.5), 2, 0, 0, 1);"
            );
            indicator.setAlignment(Pos.CENTER);
            return indicator;
        }
        return null;
    }

    private VBox createInfoContainer() {
        VBox infoContainer = new VBox(8);
        infoContainer.setAlignment(Pos.CENTER);
        
        if (isCurrentPlayer) {
            infoContainer.setStyle(
                "-fx-background-color: rgba(218, 165, 32, 0.15); " +
                "-fx-background-radius: 10px; " +
                "-fx-padding: 12px; " +
                "-fx-border-color: rgba(218, 165, 32, 0.4); " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 10px;"
            );
        } else {
            infoContainer.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.08); " +
                "-fx-background-radius: 10px; " +
                "-fx-padding: 12px; " +
                "-fx-border-color: rgba(255, 255, 255, 0.15); " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 10px;"
            );
        }
        
        return infoContainer;
    }

    private HBox createRoundsSection() {
        HBox roundsBox = new HBox(10);
        roundsBox.setAlignment(Pos.CENTER);
        
        Label roundsTitleLabel = new Label("Rondas ganadas:");
        roundsTitleLabel.setStyle(
            "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
            "-fx-font-size: 14px; " +
            "-fx-text-fill: #bdc3c7; " +
            "-fx-font-weight: 500;"
        );
        
        roundsValueLabel = new Label(String.valueOf(player.getRoundsWon()));
        roundsBox.getChildren().addAll(roundsTitleLabel, roundsValueLabel);
        return roundsBox;
    }

    private HBox createPointsSection() {
        HBox pointsBox = new HBox(10);
        pointsBox.setAlignment(Pos.CENTER);
        
        Label pointsTitleLabel = new Label("Puntos:");
        pointsTitleLabel.setStyle(
            "-fx-font-family: 'Segoe UI', Arial, sans-serif; " +
            "-fx-font-size: 14px; " +
            "-fx-text-fill: #bdc3c7; " +
            "-fx-font-weight: 500;"
        );
        
        pointsValueLabel = new Label(String.valueOf(player.calculatePoints()));
        pointsBox.getChildren().addAll(pointsTitleLabel, pointsValueLabel);
        return pointsBox;
    }
}

