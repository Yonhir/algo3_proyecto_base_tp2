package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.controllers.GameState;
import edu.fiuba.algo3.models.cards.specials.MoraleBoost;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import javafx.scene.input.MouseEvent;

public class UIMoraleBoost extends UISpecial{
    UIMoraleBoost(MoraleBoost moraleBoost){
        super(moraleBoost);
    }

    protected void encenderRows(){
        for(UIRow row : GameState.getInstance().getRows()) {
            row.encender(model);
        }
    }

    protected void apagarRows(){
        for(UIRow row : GameState.getInstance().getRows()) {
            row.apagar();
        }
    }

    protected void colocarCarta(MouseEvent event){
        for(UIRow row : GameState.getInstance().getRows()) {
            row.colocarCartaPorArrastre(this, event);
        }
    }
}
