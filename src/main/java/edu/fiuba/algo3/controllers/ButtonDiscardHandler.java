package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardlist.UIHand;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;

public class ButtonDiscardHandler implements EventHandler<ActionEvent> {

    private final Hand hand;
    private final DiscardPile discardPile;
    private final Deck deck;
    private UIHand uiHand;
    private final Button buttonDescartar;

    private int discardCount = 0;
    public static final int MAX_DISCARD_COUNT = 2;

    public ButtonDiscardHandler(Hand hand, DiscardPile discardPile, Deck deck,
                                UIHand uiHand, Button buttonDescartar) {
        this.hand = hand;
        this.discardPile = discardPile;
        this.deck = deck;
        this.uiHand = uiHand;
        this.buttonDescartar = buttonDescartar;
    }

    @Override
    public void handle(ActionEvent event) {
        UICard selectedCard = uiHand.getSelectedCard();
        if (selectedCard != null && discardCount < MAX_DISCARD_COUNT) {
            discardPile.addCard(selectedCard.getModelCard());
            hand.retrieveCard(selectedCard.getModelCard());
            hand.insertCards(deck.retrieveNRandomCards(1));

            discardCount++;

            if (discardCount >= MAX_DISCARD_COUNT) {
                buttonDescartar.setDisable(true);
            }
        }
    }
}
