package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.views.components.CenterColumn;
import edu.fiuba.algo3.views.components.LeftColumn;
import edu.fiuba.algo3.views.components.RightColumn;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.List;

public class CardPlayingController {

    private final CardHandler handleCard;
    private final RowHandler handleRows;

    public CardPlayingController(LeftColumn leftColumn, CenterColumn centerColumn, RightColumn rightColumn, Round currentRound) {
        BooleanProperty cardSelected = new SimpleBooleanProperty(false);
        rightColumn.getCardViewer().setBooleanProperty(cardSelected);
        rightColumn.getCardViewer().setRowsToButtonCloseDescription(centerColumn.getUIRows());
        handleCard = new CardHandler(centerColumn.getUIRows(), rightColumn.getCardViewer(), centerColumn.getUIHand(), cardSelected);
        handleRows = new RowHandler(centerColumn.getUIRows(), leftColumn.getUISpecialZone(), centerColumn.getUIHand(), currentRound, cardSelected);
        setupHandlerCards(centerColumn.getUICards());
        setupHandlerRows(centerColumn.getUIRows());
    }

    private void setupHandlerCards(List<UICard> cards) {
        for (UICard uic : cards) {
            uic.setOnMouseClicked(handleCard);
            uic.setOnMouseEntered(handleCard);
            uic.setOnMouseExited(handleCard);
        }
    }

    private void setupHandlerRows(List<UIRow> uiRows) {
        for (UIRow uiRow : uiRows ) {
            uiRow.setOnMouseClicked(handleRows);
        }
    }
}

