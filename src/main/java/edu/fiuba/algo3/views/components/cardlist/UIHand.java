package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.specials.Special;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardcomponent.card.UISpecial;
import edu.fiuba.algo3.views.components.cardcomponent.card.UIUnit;

public class UIHand extends CardList {

    private edu.fiuba.algo3.models.cardcollections.Hand modelHand;

    public UIHand() {
        super(true);
    }
    
    public UIHand(edu.fiuba.algo3.models.cardcollections.Hand modelHand) {
        super(true);
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
            getChildren().clear();
            
            for (Card modelCard : modelHand.getCards()) {
                UICard uiCard = createUICard(modelCard);
                if (uiCard != null) {
                    addCard(uiCard);
                }
            }
        }
    }
    
    private UICard createUICard(Card modelCard) {
        if (modelCard instanceof Unit) {
            return new UIUnit((Unit) modelCard);
        } else if (modelCard instanceof Special) {
            return new UISpecial((Special) modelCard);
        } else {
            System.err.println("Unknown card type: " + modelCard.getClass().getSimpleName());
            return null;
        }
    }

    @Override
    public void update(Observable observable) {
        if (observable == modelHand) {
            loadCardsFromModel();
        }
    }
    
    public edu.fiuba.algo3.models.cardcollections.Hand getModel() {
        return modelHand;
    }
}
