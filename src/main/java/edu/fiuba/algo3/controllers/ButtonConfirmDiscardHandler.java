package edu.fiuba.algo3.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class ButtonConfirmDiscardHandler implements EventHandler<ActionEvent> {

    private final StackPane rootPane;
    private final VBox dialogContent;
    private final Rectangle overlay;
    private final Runnable onFinish;

    public ButtonConfirmDiscardHandler(StackPane rootPane, VBox dialogContent, Rectangle overlay, Runnable onFinish) {
        this.rootPane = rootPane;
        this.dialogContent = dialogContent;
        this.overlay = overlay;
        this.onFinish = onFinish;
    }

    @Override
    public void handle(ActionEvent event) {
        rootPane.getChildren().removeAll(overlay, dialogContent);
        onFinish.run();
    }
}

