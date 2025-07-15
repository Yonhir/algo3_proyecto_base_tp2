package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.CardInfoView;
import edu.fiuba.algo3.views.components.cardlist.UIHand;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.List;


public class CardHandler implements EventHandler<MouseEvent> {

    private static final double HOVER_SCALE_X = 1.05;
    private static final double HOVER_SCALE_Y = 1.05;
    private static final double HOVER_TRANSLATE_Y = -3;
    private static final double NORMAL_SCALE = 1.0;
    private static final double NORMAL_TRANSLATE_Y = 0;

    private final BooleanProperty cardSelected;
    private final List<UIRow> rows;
    private final CardInfoView cardInfoView;
    private final UIHand hand;

    public CardHandler(List<UIRow> rows, CardInfoView cardInfoView, UIHand hand, BooleanProperty cardSelected) {
        this.rows = rows;
        this.cardInfoView = cardInfoView;
        this.cardSelected = cardSelected;
        this.hand = hand;
    }

    @Override
    public void handle(MouseEvent event) {
        UICard card = (UICard) event.getSource();
        if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            clickOnCard(card, event);
        }else if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
            enteredOnCard(card);
        }else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
            exitedOnCard(card);
        }
    }

    private void clickOnCard(UICard card, MouseEvent event) {
        placeCard(card, event);
        showInfo(card, event);
        event.consume();
    }

    private void placeCard(UICard card, MouseEvent event) {
        if (event.getClickCount() == 1) {
            cardSelected.set(true);
            hand.setSelectedCard(card);
            switchOnRows(card);

        }
    }

    private void switchOnRows(UICard card) {
        if (cardSelected.get()) switchOffRows();
        for (UIRow row : rows) {
            row.switchOn(card.getModelCard());
        }
    }

    private void switchOffRows() {
        for (UIRow row : rows) {
            row.switchOff();
        }
    }

    private void showInfo(UICard card, MouseEvent event) {
        if (event.getClickCount() == 2) {
            cardSelected.set(true);
            hand.setSelectedCard(card);
            cardInfoView.showInfoCard(card);
        }
    }

    private void enteredOnCard(UICard card) {
        howPlay(card);
        card.setScaleX(HOVER_SCALE_X);
        card.setScaleY(HOVER_SCALE_Y);
        card.setTranslateY(HOVER_TRANSLATE_Y);
    }

    private void howPlay(UICard card){
        Tooltip tooltip = new Tooltip("Double click to view card info\nOne click to select card");

        tooltip.setShowDelay(Duration.seconds(0.4));
        tooltip.setHideDelay(Duration.ZERO);

        Tooltip.install(card, tooltip);
    }

    private void exitedOnCard(UICard card) {
        card.setScaleX(NORMAL_SCALE);
        card.setScaleY(NORMAL_SCALE);
        card.setTranslateY(NORMAL_TRANSLATE_Y);
    }
}
