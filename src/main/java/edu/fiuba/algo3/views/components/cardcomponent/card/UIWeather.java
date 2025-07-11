package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.controllers.CardPlayingController;
import edu.fiuba.algo3.models.cards.specials.weathers.Weather;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.util.ArrayList;

public class UIWeather extends UISpecial{
    UIWeather(Weather weather, CardPlayingController controllerCards){
        super(weather, controllerCards);
    }

    public void switchOnRows(ArrayList<UIRow> rows){
        for(UIRow row : rows) {
            row.switchOnSpecial();
        }
    }

    public void switchOffRows(ArrayList<UIRow> rows){
        for(UIRow row : rows) {
            row.switchOff();
        }
    }

    public void placeUICard(MouseEvent event, ArrayList<Region> board, ArrayList<UIRow> rows, UISpecialZone specialZone){
        for (Node row : board) {
            Bounds bounds = row.localToScene(row.getBoundsInLocal());
            if (bounds.contains(event.getSceneX(), event.getSceneY())) {
                specialZone.placeCardDragging(this);
            }
        }
    }
}
