package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;

import java.util.List;

public class UIHand extends CardList {

    private UICard selectedCard;

    public UIHand(Hand modelHand) {
        super();
        setModel(modelHand);
    }

    public void setSelectedCard(UICard card) { selectedCard = card; }

    public UICard getSelectedCard() { return selectedCard; }

    @Override
    protected List<Card> getCardsFromModel() {
        return ((Hand) model).getCards();
    }
}
