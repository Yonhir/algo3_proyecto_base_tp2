package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.views.components.DiscardCardDialog;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class ButtonContinueForPreparationHandler implements EventHandler<ActionEvent> {

    private final Stage stage;
    private final String playerName;
    private final Hand hand;
    private final DiscardPile discardPile;
    private final Deck deck;
    private final Runnable onContinue;

    public ButtonContinueForPreparationHandler(Stage stage, String playerName, Hand hand,
                                    DiscardPile discardPile, Deck deck, Runnable onContinue) {
        this.stage = stage;
        this.playerName = playerName;
        this.hand = hand;
        this.discardPile = discardPile;
        this.deck = deck;
        this.onContinue = onContinue;
    }

    @Override
    public void handle(ActionEvent event) {
        StackPane dialogPane = new StackPane();
        dialogPane.setStyle("-fx-background-color: #C19A6B; -fx-border-color: #8B4513; -fx-border-width: 5px;");
        Scene scene = new Scene(dialogPane, 1000, 700);
        stage.setScene(scene);

        DiscardCardDialog.show(dialogPane, hand, playerName, discardPile, deck, onContinue);
    }
}

