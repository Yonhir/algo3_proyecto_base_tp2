package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardlist.UIHand;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class UIHandCardSelectHandler implements EventHandler<MouseEvent> {
    private final UIHand hand;
    private final UICard card;
    public UIHandCardSelectHandler(UIHand hand, UICard card) {
        this.hand = hand;
        this.card = card;
    }
    @Override
    public void handle(MouseEvent event) {
        hand.setSelectedCard(card);
    }
} 