package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardcomponent.card.UIUnit;
import edu.fiuba.algo3.views.components.cardcomponent.card.UISpecial;

public class UISpecialZone extends CardList {
    private SpecialZone modelSpecialZone;

    public UISpecialZone() {
        super(false);
    }

    public UISpecialZone(SpecialZone modelSpecialZone) {
        super(false);
        this.modelSpecialZone = modelSpecialZone;
        subscribeToModel();
        loadCardsFromModel();
    }

    private void subscribeToModel() {
        if (modelSpecialZone != null) {
            modelSpecialZone.addObserver(this);
        }
    }

    private void loadCardsFromModel() {
        if (modelSpecialZone != null) {
            getChildren().clear();
            for (Card modelCard : modelSpecialZone.getWeathersCards()) {
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
        if (observable == modelSpecialZone) {
            loadCardsFromModel();
        }
    }

    public SpecialZone getModel() {
        return modelSpecialZone;
    }
}
