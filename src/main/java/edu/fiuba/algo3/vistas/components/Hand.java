package edu.fiuba.algo3.vistas.components;

import edu.fiuba.algo3.modelo.Observable;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.specials.Special;
import edu.fiuba.algo3.modelo.cards.units.Unit;

public class Hand extends CardList {

    private edu.fiuba.algo3.modelo.cardcollections.Hand modelHand;

    public Hand() {
        super(true); // Hand cards should be draggable
    }
    
    public Hand(edu.fiuba.algo3.modelo.cardcollections.Hand modelHand) {
        super(true); // Hand cards should be draggable
        this.modelHand = modelHand;
        subscribeToModel();
        loadCardsFromModel();
    }
    
    private void subscribeToModel() {
        if (modelHand != null) {
            modelHand.addObserver(this);
        }
    }
    
    private void loadCardsFromModel() {
        if (modelHand != null) {
            // Clear existing cards
            getChildren().clear();
            
            // Get cards from model and create UI components
            for (Card modelCard : modelHand.getCards()) {
                edu.fiuba.algo3.vistas.components.Card uiCard = createUICard(modelCard);
                if (uiCard != null) {
                    addCard(uiCard);
                }
            }
        }
    }
    
    private edu.fiuba.algo3.vistas.components.Card createUICard(Card modelCard) {
        if (modelCard instanceof Unit) {
            return new UnitCard((Unit) modelCard);
        } else if (modelCard instanceof Special) {
            return new SpecialCard((Special) modelCard);
        } else {
            // Fallback for unknown card types
            System.err.println("Unknown card type: " + modelCard.getClass().getSimpleName());
            return null;
        }
    }

    @Override
    public void update(Observable observable) {
        // Update the hand when the model changes
        if (observable == modelHand) {
            loadCardsFromModel();
        }
    }
    
    public edu.fiuba.algo3.modelo.cardcollections.Hand getModel() {
        return modelHand;
    }
}
