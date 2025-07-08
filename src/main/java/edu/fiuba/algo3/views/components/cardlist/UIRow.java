package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.sections.rows.Row;

import java.util.List;

public class UIRow extends CardList {

    public UIRow() {
        super(false);
    }

    public UIRow(Row modelRow) {
        super(false);
        setModel(modelRow);
    }

    @Override
    protected List<Card> getCardsFromModel() {
        return ((Row) model).getCards();
    }
}
