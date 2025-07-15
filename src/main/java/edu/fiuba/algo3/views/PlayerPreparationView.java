package edu.fiuba.algo3.views;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.views.components.PlayerNameScreen;
import edu.fiuba.algo3.views.components.cardlist.UIHand;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import edu.fiuba.algo3.controllers.ButtonDiscardHandler;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class PlayerPreparationView extends StackPane {

    public PlayerPreparationView(String playerName, Hand hand, DiscardPile discardPile, Deck deck, EventHandler<ActionEvent> onContinueHandler) {
        PlayerNameScreen playerNameScreen = new PlayerNameScreen(playerName);
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double windowWidth = bounds.getWidth();
        double windowHeight = bounds.getHeight();
        Rectangle overlay = new Rectangle(windowWidth, windowHeight);
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(0.4);

        UIHand tempUIHand = new UIHand(hand);
        tempUIHand.setMaxWidth(1000);
        tempUIHand.setMaxHeight(150);
        tempUIHand.setStyle("-fx-background-color: #DEB887; -fx-border-color: #8B4513; -fx-border-width: 3;");

        VBox dialogContent = getDialogContent(playerName, tempUIHand);

        getChildren().addAll(overlay, dialogContent);
        getChildren().add(playerNameScreen);
        StackPane.setAlignment(playerNameScreen, Pos.CENTER);

        Button buttonDescartar = new Button("Descartar");
        buttonDescartar.setStyle("-fx-background-color: #FFA500; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
        buttonDescartar.setOnAction(new ButtonDiscardHandler(hand, discardPile, deck, tempUIHand, buttonDescartar));

        Button continueButton = new Button("Confirmar");
        continueButton.setStyle("-fx-background-color: #228B22; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        continueButton.setDisable(false);
        continueButton.setOnAction(onContinueHandler);

        HBox buttonBox = new HBox(20, buttonDescartar, continueButton);
        buttonBox.setAlignment(Pos.CENTER);
        dialogContent.getChildren().set(2, buttonBox);
    }

    private static VBox getDialogContent(String playerName, UIHand tempUIHand) {
        Label titleLabel = new Label(playerName + ": Podés descartar hasta 2 cartas de tu mano");
        titleLabel.setStyle("-fx-font-size: 23px; -fx-text-fill: white; -fx-font-weight: bold;");
        titleLabel.setScaleY(1.5);
        titleLabel.setScaleX(1.2);

        VBox dialogContent = new VBox(20, titleLabel, tempUIHand, new HBox(20));
        dialogContent.setAlignment(Pos.CENTER);
        dialogContent.setSpacing(50);
        dialogContent.setPadding(new Insets(20));
        dialogContent.setMaxWidth(1000);
        dialogContent.setMaxHeight(600);
        dialogContent.setStyle("-fx-background-color: #C19A6B; -fx-border-color: #8B4513; -fx-border-width: 3;");
        return dialogContent;
    }
}
