package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cardcollections.Hand;

import java.util.List;

public class UIHand extends CardList {

    public UIHand() {
        super(true);
    }
    
    public UIHand(Hand modelHand) {
        super(true);
        setModel(modelHand);
    }
    
    @Override
    protected List<Card> getCardsFromModel() {
        return ((Hand) model).getCards();
    }
}
