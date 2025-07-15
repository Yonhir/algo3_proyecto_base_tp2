package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.sections.types.SpecialType;

import java.util.List;

public class UISpecialZone extends CardList {
    private boolean switchedOn;

    public UISpecialZone(SpecialZone modelSpecialZone) {
        super();
        switchedOn = false;
        setModel(modelSpecialZone);
    }

    @Override
    protected List<Card> getCardsFromModel() {
        return ((SpecialZone) model).getWeathersCards();
    }

    public void switchOn(Card card) {
        if (card.haveSectionType(new SpecialType())) {
            applyHoverStyle();
            switchedOn = true;
        }
    }

    public void switchOff() {
        if (switchedOn) {
            applyDefaultStyle();
            switchedOn = false;
        }
    }

    public SpecialZone getModel() {
        return (SpecialZone) model;
    }
}
