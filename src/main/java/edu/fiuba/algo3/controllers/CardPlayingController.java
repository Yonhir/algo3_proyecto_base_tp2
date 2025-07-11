package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;


public class CardPlayingController implements EventHandler<MouseEvent> {
    private UICard card;
    private ArrayList<UIRow> rows;
    private UISpecialZone specialZone;
    private ArrayList<Region> board;
    private double dragDeltaX;
    private double dragDeltaY;
    private boolean isDragging = false;
    private boolean isDraggable = false;

    private double originalX = 0;
    private double originalY = 0;


    @Override
    public void handle(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            relased(event);
        } else if(event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            dragging(event);
        } else if(event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            press(event);
        }
    }

    private void press(MouseEvent event) {
        if (!isDraggable) {
            return;
        }

        isDragging = true;

        originalX = card.getTranslateX();
        originalY = card.getTranslateY();

        dragDeltaX = card.getTranslateX() - event.getSceneX();
        dragDeltaY = card.getTranslateY() - event.getSceneY();

        card.toFront();

        card.switchOnRows(rows);

        event.consume();
    }

    private void dragging(MouseEvent event) {
        if (!isDraggable || !isDragging) {
            return;
        }

        card.setTranslateX(event.getSceneX() + dragDeltaX);
        card.setTranslateY(event.getSceneY() + dragDeltaY);

        event.consume();
    }

    private void relased(MouseEvent event) {

        if (!isDraggable) {
            return;
        }

        isDragging = false;

        card.setTranslateX(originalX);
        card.setTranslateY(originalY);

        card.placeUICard(event, board, rows, specialZone);

        card.switchOffRows(rows);

        event.consume();
    }

    public void setDraggable(boolean draggable) { this.isDraggable = draggable; }

    public void setCard(UICard card) { this.card = card; }

    public void setSpecialZone(UISpecialZone specialZone) { this.specialZone = specialZone; }

    public void setUIRows(List<UIRow> rows) { this.rows = new ArrayList<>(rows); }

    public void setBoard(List<Region> board) { this.board = new ArrayList<>(board); }
}