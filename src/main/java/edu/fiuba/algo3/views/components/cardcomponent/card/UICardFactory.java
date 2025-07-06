package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.specials.Special;
import edu.fiuba.algo3.models.cards.units.Unit;

public class UICardFactory {
    
    public static UICard createUICard(Card modelCard) {
        if (modelCard == null) {
            throw new IllegalArgumentException("Model card cannot be null");
        }
        
        if (modelCard instanceof Unit) {
            return new UIUnit((Unit) modelCard);
        } else if (modelCard instanceof Special) {
            return new UISpecial((Special) modelCard);
        } else {
            throw new IllegalArgumentException("Unknown card type: " + modelCard.getClass().getSimpleName());
        }
    }
} 