package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.CardInfoView;
import edu.fiuba.algo3.controllers.UIHandCardSelectHandler;

import java.util.List;

public class UIHand extends CardList {

    private UICard selectedCard;
    private CardInfoView cardInfoView;

    public UIHand(Hand modelHand) {
        super();
        setModel(modelHand);
    }

    public void setCardInfoView(CardInfoView cardInfoView) {
        this.cardInfoView = cardInfoView;
    }

    public void setSelectedCard(UICard card) {
        for (UICard c : getCards()) {
            c.unselect();
        }
        if (card != null) {
            card.select();
        }
        selectedCard = card;
        
        // Notify CardInfoView when a card is selected
        if (cardInfoView != null && card != null) {
            cardInfoView.showInfoCard(card);
        }
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

    public List<UICard> getUICards() {
        return getCards();
    }
}
