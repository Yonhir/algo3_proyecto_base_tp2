package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cards.Card;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.Set;

public class ButtonConfirmDiscardHandler implements EventHandler<ActionEvent> {

    private final StackPane rootPane;
    private final VBox dialogContent;
    private final Rectangle overlay;
    private final Hand hand;
    private final DiscardPile discardPile;
    private final Deck deck;
    private final Set<Card> selectedCards;

    public ButtonConfirmDiscardHandler(
            StackPane rootPane,
            VBox dialogContent,
            Rectangle overlay,
            Hand hand,
            DiscardPile discardPile,
            Deck deck,
            Set<Card> selectedCards
    ) {
        this.rootPane = rootPane;
        this.dialogContent = dialogContent;
        this.overlay = overlay;
        this.hand = hand;
        this.discardPile = discardPile;
        this.deck = deck;
        this.selectedCards = selectedCards;
    }

    @Override
    public void handle(ActionEvent e) {
        for (Card card : selectedCards) {
            hand.retrieveCard(card);
            discardPile.addCard(card);
        }

        int amountCards = 10 - hand.getCardCount();
        hand.getNCardsFromDeck(deck, amountCards);

        rootPane.getChildren().removeAll(overlay , dialogContent);
    }
}


