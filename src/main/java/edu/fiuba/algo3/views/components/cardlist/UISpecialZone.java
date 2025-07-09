package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.controllers.GameState;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;

import java.util.List;

public class UISpecialZone extends CardList {

    public UISpecialZone() {
        super(false);
    }

    public UISpecialZone(SpecialZone modelSpecialZone) {
        super(false);
        setModel(modelSpecialZone);
        GameState.getInstance().setSpecialZoneActual(this);
    }



    @Override
    protected List<Card> getCardsFromModel() {
        return ((SpecialZone) model).getWeathersCards();
    }

    public void colocarCartaPorArrastre(UICard uiCard){
        SpecialZone specialZone = (SpecialZone) model;
        specialZone.placeCard(uiCard.getModel(), GameState.getInstance().getRoundActual());
        addCard(uiCard);
        update(model);
        update(uiCard.getModel());
        for(UIRow row : GameState.getInstance().getRows()) {
            row.actualizarCartas();
        }
    }
}
