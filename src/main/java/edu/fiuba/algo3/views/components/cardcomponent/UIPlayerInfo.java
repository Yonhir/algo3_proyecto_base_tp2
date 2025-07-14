package edu.fiuba.algo3.views.components.cardcomponent;

import edu.fiuba.algo3.models.turnManagement.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UIPlayerInfo extends VBox {

    private final Label nameLabel;
    private final Label roundsLabel;
    private final Label pointsLabel;

    private final Player player;

    public UIPlayerInfo(Player player) {
        this.player = player;

        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(10));
        setStyle("-fx-background-color: #F5DEB3; -fx-border-color: #8B4513; -fx-border-width: 2px;");
        setMaxWidth(400);

        nameLabel = new Label(player.getName());
        nameLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");

        roundsLabel = new Label("Rondas ganadas: " + player.getRoundsWon());
        roundsLabel.setStyle("-fx-font-size: 16px;");

        pointsLabel = new Label("Puntos: " + player.calculatePoints());
        pointsLabel.setStyle("-fx-font-size: 16px;");

        getChildren().addAll(nameLabel, roundsLabel, pointsLabel);
    }

    public void refresh() {
        roundsLabel.setText("Rondas ganadas: " + player.getRoundsWon());
        pointsLabel.setText("Puntos: " + player.calculatePoints());
    }
}

