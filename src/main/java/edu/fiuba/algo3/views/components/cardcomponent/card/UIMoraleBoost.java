package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.controllers.CardPlayingController;
import edu.fiuba.algo3.models.cards.specials.MoraleBoost;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.util.ArrayList;

public class UIMoraleBoost extends UISpecial{
    UIMoraleBoost(MoraleBoost moraleBoost, CardPlayingController controllerCards){
        super(moraleBoost, controllerCards);
    }

    public void switchOnRows(ArrayList<UIRow> rows){
        for(UIRow row : rows) {
            row.switchOn(model);
        }
    }

    public void switchOffRows(ArrayList<UIRow> rows){
        for(UIRow row : rows) {
            row.switchOff();
        }
    }

    public void placeUICard(MouseEvent event, ArrayList<Region> board, ArrayList<UIRow> rows, UISpecialZone specialZone){
        for(UIRow row : rows) {
            row.placeCardDragging(this, event);
        }
    }
}
