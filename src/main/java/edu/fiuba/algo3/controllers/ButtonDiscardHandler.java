package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardlist.UIHand;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;

public class ButtonDiscardHandler implements EventHandler<ActionEvent> {

    private final StackPane rootPane;
    private final VBox dialogContent;

    private final Hand hand;
    private final DiscardPile discardPile;
    private final Deck deck;
    private UIHand uiHand;
    private final Button buttonDescartar;

    private int discardCount = 0;
    public static final int MAX_DISCARD_COUNT = 2;
    private Card selectedCard = null;

    public ButtonDiscardHandler(StackPane rootPane, VBox dialogContent,
                                Hand hand, DiscardPile discardPile, Deck deck,
                                UIHand uiHand, Button buttonDescartar) {
        this.rootPane = rootPane;
        this.dialogContent = dialogContent;
        this.hand = hand;
        this.discardPile = discardPile;
        this.deck = deck;
        this.uiHand = uiHand;
        this.buttonDescartar = buttonDescartar;
        setupCardSelection();
    }

    private void setupCardSelection() {
        for (UICard uiCard : uiHand.getCards()) {
            uiCard.setOnMouseClicked(e -> {
                uiHand.getCards().forEach(c -> c.setStyle(""));
                selectedCard = uiCard.getModelCard();
                uiCard.setStyle("-fx-border-color: blue; -fx-border-width: 3;");
            });
        }
    }

    @Override
    public void handle(ActionEvent event) {
        if (selectedCard != null && discardCount < MAX_DISCARD_COUNT) {
            discardPile.addCard(selectedCard);
            hand.retrieveCard(selectedCard);

            if (!deck.isEmpty()) {
                hand.insertCards(deck.retrieveNRandomCards(1));
            }

            discardCount++;
            selectedCard = null;

            // Refrescar UIHand
            rootPane.getChildren().remove(uiHand);
            UIHand newUIHand = new UIHand(hand);
            newUIHand.setMaxWidth(1000);
            newUIHand.setMaxHeight(150);
            newUIHand.setStyle("-fx-background-color: #DEB887; -fx-border-color: #8B4513; -fx-border-width: 3;");
            uiHand = newUIHand;

            setupCardSelection();

            dialogContent.getChildren().set(1, newUIHand);

            if (discardCount >= MAX_DISCARD_COUNT) {
                buttonDescartar.setDisable(true);
            }
        }
    }
}
