package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.controllers.CardPlayingController;
import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.specials.Special;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.util.ArrayList;

public abstract class UISpecial extends UICard {

    protected Special model;

    public UISpecial(Special special, CardPlayingController controllerCards) {
        super(special.getName(), special.getDescription(), controllerCards);
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

    public abstract void switchOnRows(ArrayList<UIRow> rows);

    public abstract void switchOffRows(ArrayList<UIRow> rows);

    public abstract void placeUICard(MouseEvent event, ArrayList<Region> board, ArrayList<UIRow> rows, UISpecialZone specialZone);
}
