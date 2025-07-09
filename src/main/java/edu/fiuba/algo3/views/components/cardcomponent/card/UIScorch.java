package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.controllers.GameState;
import edu.fiuba.algo3.models.cards.specials.Scorch;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class UIScorch extends UISpecial{
    UIScorch(Scorch scorch) {
        super(scorch);
    }

    protected void encenderRows(){
        for(UIRow row : GameState.getInstance().getRows()) {
            row.encenderSpecial();
        }
    }

    protected void apagarRows(){
        for(UIRow row : GameState.getInstance().getRows()) {
            row.apagar();
        }
    }

    protected void colocarCarta(MouseEvent event){
        ArrayList<Node> rows = getBoard();
        UISpecialZone specialZone =  GameState.getInstance().getSpecialZoneActual();
        for (Node row : rows) {
            Bounds bounds = row.localToScene(row.getBoundsInLocal());
            if (bounds.contains(event.getSceneX(), event.getSceneY())) {
                specialZone.colocarCartaPorArrastre(this);
            }
        }
    }
}
