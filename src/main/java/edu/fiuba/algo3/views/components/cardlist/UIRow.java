package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardcomponent.card.UIUnit;
import edu.fiuba.algo3.views.components.cardcomponent.card.UISpecial;

public class UIRow extends CardList {
    private Row modelRow;

    public UIRow() {
        super(false);
    }

    public UIRow(Row modelRow) {
        super(false);
        this.modelRow = modelRow;
        subscribeToModel();
        loadCardsFromModel();
    }

    private void subscribeToModel() {
        if (modelRow != null) {
            modelRow.addObserver(this);
        }
    }

    private void loadCardsFromModel() {
        if (modelRow != null) {
            getChildren().clear();
            for (Card modelCard : modelRow.getCards()) {
                UICard uiCard = createUICard(modelCard);
                if (uiCard != null) {
                    addCard(uiCard);
                }
            }
        }
    }

    private UICard createUICard(Card modelCard) {
        if (modelCard instanceof edu.fiuba.algo3.models.cards.units.Unit) {
            return new UIUnit((edu.fiuba.algo3.models.cards.units.Unit) modelCard);
        } else if (modelCard instanceof edu.fiuba.algo3.models.cards.specials.Special) {
            return new UISpecial((edu.fiuba.algo3.models.cards.specials.Special) modelCard);
        } else {
            System.err.println("Unknown card type: " + modelCard.getClass().getSimpleName());
            return null;
        }
    }

    @Override
    public void update(Observable observable) {
        if (observable == modelRow) {
            loadCardsFromModel();
        }
    }

    public Row getModel() {
        return modelRow;
    }
}
