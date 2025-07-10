package edu.fiuba.algo3.controllers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;


public class CardPlayingController implements EventHandler<MouseEvent> {

    public CardPlayingController(Node card) {
        card.setOnMouseClicked(this::doubleClick);
        card.setOnMouseDragged(this::dragging);
        card.setOnMousePressed(this::press);
        card.setOnMouseReleased(this::relased);
    }

    @Override
    public void handle(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            doubleClick(event);
        } else if(event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            dragging(event);
        } else if(event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            press(event);
        } else if(event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            relased(event);
        }
    }

    private void doubleClick(MouseEvent event) {
        // Handle double-click event
        System.out.println("Card double-clicked at: " + event.getX() + ", " + event.getY());
        event.consume();
    }
    private void dragging(MouseEvent event) {
        // Handle dragging event
        System.out.println("Card being dragged at: " + event.getX() + ", " + event.getY());
        event.consume();
    }
    private void press(MouseEvent event) {
        // Handle mouse press event
        System.out.println("Card pressed at: " + event.getX() + ", " + event.getY());
        event.consume();
    }
    private void relased(MouseEvent event) {
        // Handle mouse release event
        System.out.println("Card released at: " + event.getX() + ", " + event.getY());
        event.consume();
    }
}