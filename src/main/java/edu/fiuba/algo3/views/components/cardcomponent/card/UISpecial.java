package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cards.specials.Special;

public class UISpecial extends UICard {


    public UISpecial(Special special) {
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
    }

    @Override
    public void update(Observable observable) {
        if (observable == model) {
            loadDataFromSpecial();
        }
    }

    @Override
    protected UICard createCopy() {
        return new UISpecial((Special) this.model);
    }
}
