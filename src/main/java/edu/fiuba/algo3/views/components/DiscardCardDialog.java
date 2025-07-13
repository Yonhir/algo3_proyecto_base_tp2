package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.controllers.ButtonConfirmDiscardHandler;
import edu.fiuba.algo3.controllers.ButtonDiscardHandler;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
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



public class DiscardCardDialog {

    public static void show(
            StackPane rootPane,
            Hand hand,
            String playerName,
            DiscardPile discardPile,
            Deck deck,
            Runnable onFinish) {

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

        Label titleLabel = new Label(playerName + ": Podés descartar hasta 2 cartas de tu mano");
        titleLabel.setStyle("-fx-font-size: 23px; -fx-text-fill: white; -fx-font-weight: bold;");
        titleLabel.setScaleY(1.5);
        titleLabel.setScaleX(1.2);

        Button buttonDescartar = new Button("Descartar");
        buttonDescartar.setStyle("-fx-background-color: #FFA500; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");

        Button buttonConfirmar = new Button("Confirmar");
        buttonConfirmar.setStyle("-fx-background-color: #228B22; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        HBox buttonBox = new HBox(20, buttonDescartar, buttonConfirmar);
        buttonBox.setAlignment(Pos.CENTER);

        VBox dialogContent = new VBox(20, titleLabel, tempUIHand, buttonBox);
        dialogContent.setAlignment(Pos.CENTER);
        dialogContent.setSpacing(50);
        dialogContent.setPadding(new Insets(20));
        dialogContent.setMaxWidth(1000);
        dialogContent.setMaxHeight(600);
        dialogContent.setStyle("-fx-background-color: #C19A6B; -fx-border-color: #8B4513; -fx-border-width: 3;");

        rootPane.getChildren().addAll(overlay, dialogContent);

        ButtonDiscardHandler buttonDiscardHandler = new ButtonDiscardHandler(
                rootPane, dialogContent, hand, discardPile,
                deck, tempUIHand, buttonDescartar);
        buttonDescartar.setOnAction(buttonDiscardHandler);

        ButtonConfirmDiscardHandler buttonConfirmDiscardHandler = new ButtonConfirmDiscardHandler(rootPane, dialogContent, overlay, onFinish);
        buttonConfirmar.setOnAction(buttonConfirmDiscardHandler);
    }
}




