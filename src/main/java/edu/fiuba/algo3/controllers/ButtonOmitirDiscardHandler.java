package edu.fiuba.algo3.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class ButtonOmitirDiscardHandler implements EventHandler<ActionEvent> {
    private final StackPane rootPane;
    private final Rectangle overlay;
    private final VBox dialogContent;
    private final Runnable onFinish;
    public ButtonOmitirDiscardHandler(StackPane rootPane, Rectangle overlay, VBox dialogContent, Runnable onFinish){
        this.rootPane = rootPane;
        this.overlay = overlay;
        this.dialogContent = dialogContent;
        this.onFinish = onFinish;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        rootPane.getChildren().removeAll(overlay, dialogContent);
        onFinish.run();

    }
}
