package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.controllers.ButtonConfirmDiscardHandler;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardlist.UIHand;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Set;


public class DiscardCardDialog {
    private static Set<Card> enableCardSelection(UIHand tempUIHand) {
        Set<Card> selectedCards = new HashSet<>();
        for (UICard uiCard : tempUIHand.getCards()) {
            uiCard.setOnMouseClicked(e -> {
                Card card = uiCard.getModelCard();
                if (selectedCards.contains(card)) {
                    selectedCards.remove(card);
                    uiCard.setStyle("");
                } else if (selectedCards.size() < 2) {
                    selectedCards.add(card);
                    uiCard.setStyle("-fx-border-color: blue; -fx-border-width: 3;");
                }
            });
        }
        return selectedCards;
    }

    public static void show(
            StackPane rootPane,
            Hand hand,
            DiscardPile discardPile,
            Deck deck)
    {

        double windowWidth = rootPane.getScene().getWidth();
        double windowHeight = rootPane.getScene().getHeight();
        Rectangle overlay = new Rectangle(windowWidth, windowHeight);
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(0.4);

        UIHand tempUIHand = new UIHand(hand);
        tempUIHand.setMaxWidth(700);
        tempUIHand.setPrefWidth(700);
        tempUIHand.setMaxHeight(180);
        tempUIHand.setPrefHeight(180);
        tempUIHand.setStyle("-fx-background-color: #DEB887; -fx-border-color: #8B4513; -fx-border-width: 3;");

        Set<Card> selectedCards = enableCardSelection(tempUIHand);

        Label titleLabel = new Label("Podés descartar hasta 2 cartas de tu mano");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-font-weight: bold;");

        Button buttonConfirm = new Button("Confirmar");
        buttonConfirm.setStyle("-fx-background-color: #228B22; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        Button buttonSkip = new Button("Omitir");
        buttonSkip.setStyle("-fx-background-color: #B22222; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

        HBox buttonBox = new HBox(20, buttonSkip, buttonConfirm);
        buttonBox.setAlignment(Pos.CENTER);

        VBox dialogContent = new VBox(20, titleLabel, tempUIHand, buttonBox);
        dialogContent.setAlignment(Pos.CENTER);
        dialogContent.setPadding(new Insets(20));
        dialogContent.setMaxWidth(800);
        dialogContent.setMaxHeight(400);
        dialogContent.setStyle("-fx-background-color: #3E2723; -fx-border-color: white; -fx-border-width: 3;");

        buttonConfirm.setOnAction(new ButtonConfirmDiscardHandler(rootPane, dialogContent,overlay ,hand, discardPile, deck, selectedCards));
        buttonSkip.setOnAction(e -> {
            rootPane.getChildren().removeAll(overlay, dialogContent);
        });

        rootPane.getChildren().addAll(overlay, dialogContent);
    }
}



