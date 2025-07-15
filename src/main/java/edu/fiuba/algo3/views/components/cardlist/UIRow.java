package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.models.sections.types.SpecialType;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;

import java.util.List;

public class UIRow extends CardList {
    private boolean switchedOn;

    public UIRow(Row modelRow) {
        super();
        switchedOn = false;
        setModel(modelRow);
    }

    @Override
    protected List<Card> getCardsFromModel() {
        return ((Row) model).getCards();
    }

    public void switchOn(Card card) {
        Row row = (Row) model;
        if(row.haveSameSectionType(card) && row.haveSamePlayerColor(card)) {
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
    
    public Object getModel() {
        return model;
    }
}
