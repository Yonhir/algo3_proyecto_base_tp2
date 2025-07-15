package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.controllers.UIHandCardSelectHandler;

import java.util.List;

public class UIHand extends CardList {

    private UICard selectedCard;

    public UIHand(Hand modelHand) {
        super();
        setModel(modelHand);
    }

    public void setSelectedCard(UICard card) {
        for (UICard c : getCards()) {
            c.unselect();
        }
        if (card != null) {
            card.select();
        }
        selectedCard = card;
    }

    public UICard getSelectedCard() { return selectedCard; }

    private void setupCardSelection() {
        for (UICard uiCard : getCards()) {
            uiCard.setOnMouseClicked(new UIHandCardSelectHandler(this, uiCard));
        }
    }

    @Override
    protected void loadCardsFromModel() {
        super.loadCardsFromModel();
        setupCardSelection();
    }

    @Override
    protected List<Card> getCardsFromModel() {
        return ((Hand) model).getCards();
    }
}
