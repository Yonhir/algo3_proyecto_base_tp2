package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.controllers.CardPlayingController;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.specials.MoraleBoost;
import edu.fiuba.algo3.models.cards.specials.Scorch;
import edu.fiuba.algo3.models.cards.specials.Special;
import edu.fiuba.algo3.models.cards.specials.weathers.Weather;
import edu.fiuba.algo3.models.cards.units.Unit;

public class UICardFactory {
    
    public static UICard createUICard(Card modelCard, CardPlayingController controllerCards) {
        if (modelCard == null) {
            throw new IllegalArgumentException("Model card cannot be null");
        }
        
        if (modelCard instanceof Unit) {
            return new UIUnit((Unit) modelCard, controllerCards);
        } else if (modelCard instanceof Special) {
            if(modelCard instanceof Weather) return new UIWeather((Weather) modelCard, controllerCards);
            else if(modelCard instanceof MoraleBoost) return new UIMoraleBoost((MoraleBoost) modelCard, controllerCards);
            else if (modelCard instanceof Scorch) return new UIScorch((Scorch) modelCard, controllerCards);
            throw new IllegalArgumentException("Unknown card type: " + modelCard.getClass().getSimpleName());
        } else {
            throw new IllegalArgumentException("Unknown card type: " + modelCard.getClass().getSimpleName());
        }
    }
} 