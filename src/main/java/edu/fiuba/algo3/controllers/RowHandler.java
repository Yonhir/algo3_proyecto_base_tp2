package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardlist.UIHand;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.beans.property.BooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class RowHandler implements EventHandler<MouseEvent> {

    private final BooleanProperty selected;
    private final UIHand hand;
    private final Round currentRound;
    private final UISpecialZone specialZone;
    private final List<UIRow> rows;

    public RowHandler(List<UIRow> rows, UISpecialZone specialZone, UIHand hand, Round currentRound, BooleanProperty cardSelected) {
        selected = cardSelected;
        this.specialZone = specialZone;
        this.hand = hand;
        this.currentRound = currentRound;
        this.rows = rows;
    }

    @Override
    public void handle(MouseEvent event) {
        //puede que el selected card este de mas, ya que puedo verificar si hand tiene en su carta seleccionada un null
        if(!selected.get()) return;
        UIRow row = (UIRow) event.getSource();
        UICard card = hand.getSelectedCard();

        row.playCard(card, currentRound, specialZone);

        hand.setSelectedCard(null);
        selected.set(false);
        switchOffRows();
        event.consume();
    }

    private void switchOffRows() {
        for (UIRow row : rows) {
            row.switchOff();
        }
    }

}
