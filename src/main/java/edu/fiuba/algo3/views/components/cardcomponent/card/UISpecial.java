package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.specials.Special;
import javafx.scene.input.MouseEvent;

public abstract class UISpecial extends UICard {

    protected Special model;

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

    public Card getModel() {
        return model;
    }

    protected abstract void encenderRows();

    protected abstract void apagarRows();

    protected abstract void colocarCarta(MouseEvent event);
}
