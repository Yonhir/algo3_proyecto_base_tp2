package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.views.components.CardInfoView;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonCloseDescription  implements EventHandler<ActionEvent> {

    private final CardInfoView cardInfoView;
    private final BooleanProperty showInfo;

    public ButtonCloseDescription(BooleanProperty showInfo, CardInfoView cardInfoView) {
        this.showInfo = showInfo;
        this.cardInfoView = cardInfoView;
    }

    @Override
    public void handle(ActionEvent event) {
        if (showInfo.getValue()) {
            cardInfoView.getChildren().remove(cardInfoView.getChildren().size() - 1);
            showInfo.set(false);
        }
    }
}
