package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.views.components.CardInfoView;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

public class ButtonCloseDescription  implements EventHandler<ActionEvent> {

    private final CardInfoView cardInfoView;
    private final BooleanProperty showInfo;
    private List<UIRow> rows;

    public ButtonCloseDescription(BooleanProperty showInfo, CardInfoView cardInfoView) {
        this.showInfo = showInfo;
        this.cardInfoView = cardInfoView;
    }

    @Override
    public void handle(ActionEvent event) {
        if (showInfo.getValue()) {
            cardInfoView.getChildren().remove(cardInfoView.getChildren().size() - 1);
            switchOffRows();
            showInfo.set(false);
        }
    }

    private void switchOffRows() {
        for (UIRow row : rows) {
            row.switchOff();
            row.update(row.getModel());
        }
    }

    public void setRows(List<UIRow> rows) {
        this.rows = rows;
    }
}
