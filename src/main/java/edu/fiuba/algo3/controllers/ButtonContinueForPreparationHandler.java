package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.views.components.DiscardCardDialog;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class ButtonContinueForPreparationHandler implements EventHandler<ActionEvent> {

    private final String playerName;
    private final Hand hand;
    private final DiscardPile discardPile;
    private final Deck deck;
    private final Runnable onContinue;

    public ButtonContinueForPreparationHandler(String playerName, Hand hand,
                                    DiscardPile discardPile, Deck deck, Runnable onContinue) {
        this.playerName = playerName;
        this.hand = hand;
        this.discardPile = discardPile;
        this.deck = deck;
        this.onContinue = onContinue;
    }

    @Override
    public void handle(ActionEvent event) {
        // Get the current node (the preparation view)
        Node currentView = ((javafx.scene.control.Button) event.getSource()).getScene().getRoot();
        
        // Cast to StackPane since we know the root is a StackPane
        StackPane rootPane = (StackPane) currentView;
        
        // Show the discard dialog on the current view
        DiscardCardDialog.show(rootPane, hand, playerName, discardPile, deck, onContinue);
    }
}

