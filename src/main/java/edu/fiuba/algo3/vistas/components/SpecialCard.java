package edu.fiuba.algo3.vistas.components;

import edu.fiuba.algo3.modelo.Observable;
import edu.fiuba.algo3.modelo.cards.specials.Special;

public class SpecialCard extends Card {
    
    private Special model;

    public SpecialCard(Special special) {
        super(special.getName(), special.getDescription());
        this.model = special;
        subscribeToModel();
    }
    
    private void subscribeToModel() {
        if (model != null) {
            model.addObserver(this);
        }
    }
    
    public void loadDataFromSpecial() {
        if (model != null) {
            // Special cards don't have points, but we could load other data here
            // For example, special effects, descriptions, etc.
        }
    }

    @Override
    public void update(Observable observable) {
        // Update the card when the special model changes
        if (observable == model) {
            loadDataFromSpecial();
        }
    }
    
    public Special getModel() {
        return model;
    }
}
