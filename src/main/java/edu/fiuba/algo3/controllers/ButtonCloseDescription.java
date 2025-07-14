package edu.fiuba.algo3.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

public class ButtonCloseDescription  implements EventHandler<ActionEvent> {

    private final StackPane parent;
    private final BooleanProperty showInfo;

    public ButtonCloseDescription(BooleanProperty showInfo, StackPane parent) {
        this.showInfo = showInfo;
        this.parent = parent;
    }

    @Override
    public void handle(ActionEvent event) {
        if (showInfo.getValue()) {
            parent.getChildren().remove(parent.getChildren().size() - 1);
            showInfo.set(false);
        }
    }
}
