package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;

import java.util.List;

public class UISpecialZone extends CardList {

    public UISpecialZone(SpecialZone modelSpecialZone) {
        super();
        setModel(modelSpecialZone);
    }

    @Override
    protected List<Card> getCardsFromModel() {
        return ((SpecialZone) model).getWeathersCards();
    }

    public void playCard(UICard card, Round currentRound) {
        Card cardToPlay = card.getModelCard();
        Player currentPlayer = currentRound.getCurrentPlayer();
        SpecialZone specialZone = (SpecialZone) model;

        currentPlayer.playCard(cardToPlay, specialZone, currentRound);
        addCard(card);
        update(model);
    }

    public SpecialZone getModel() {
        return (SpecialZone) model;
    }
}
