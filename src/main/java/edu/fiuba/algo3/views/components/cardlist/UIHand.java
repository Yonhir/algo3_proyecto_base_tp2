package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.controllers.CardPlayingController;
import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICardFactory;

import java.util.List;

public class UIHand extends CardList {

    public UIHand() {
        super(true);
    }
    
    public UIHand(Hand modelHand, CardPlayingController controllerCards) {
        super(true);
        setModel(modelHand, controllerCards);
    }

    protected void setModel(Observable model, CardPlayingController controllerCards) {
        this.model = model;
        subscribeToModel();
        loadCardsFromModel(controllerCards);
    }

    protected void subscribeToModel() {
        if (model != null) {
            model.addObserver(this);
        }
    }

    protected void loadCardsFromModel(CardPlayingController controllerCards) {
        getChildren().clear();

        List<Card> modelCards = getCardsFromModel();
        for (Card modelCard : modelCards) {
            UICard uiCard = createUICard(modelCard, controllerCards);
            addCard(uiCard);
        }
    }

    protected UICard createUICard(Card modelCard, CardPlayingController controllerCards) {
        return UICardFactory.createUICard(modelCard, controllerCards);
    }


    @Override
    protected List<Card> getCardsFromModel() {
        return ((Hand) model).getCards();
    }
}
