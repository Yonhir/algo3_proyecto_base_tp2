package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.sections.SpecialZone;

import java.util.List;

public class UISpecialZone extends CardList {

    public UISpecialZone() {
        super(false);
    }

    public UISpecialZone(SpecialZone modelSpecialZone) {
        super(false);
        setModel(modelSpecialZone);
    }

    @Override
    protected List<Card> getCardsFromModel() {
        return ((SpecialZone) model).getWeathersCards();
    }
}
