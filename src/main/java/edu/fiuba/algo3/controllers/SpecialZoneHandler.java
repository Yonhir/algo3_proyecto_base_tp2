package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.errors.SectionTypeMismatchError;
import edu.fiuba.algo3.models.sections.types.SpecialType;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardlist.UIHand;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SpecialZoneHandler implements EventHandler<MouseEvent> {

    private final UIHand hand;
    private final Round currentRound;
    private final UISpecialZone specialZone;

    public SpecialZoneHandler(UISpecialZone specialZone, UIHand hand, Round currentRound) {
        this.specialZone = specialZone;
        this.hand = hand;
        this.currentRound = currentRound;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("DEBUG: SpecialZoneHandler.handle() called");
        UICard card = hand.getSelectedCard();
        
        if (card != null) {
            playCard(card);
            hand.setSelectedCard(null);
        }
        
        event.consume();
    }
    
    private void playCard(UICard card) {
        Card cardToPlay = card.getModelCard();
        Player currentPlayer = currentRound.getCurrentPlayer();

        try{
            currentPlayer.playCard(cardToPlay, specialZone.getModel(), currentRound);
        }catch(SectionTypeMismatchError e){
            System.out.println("DEBUG: Error playing card in special zone: " + e.getMessage());
        }
    }
} 