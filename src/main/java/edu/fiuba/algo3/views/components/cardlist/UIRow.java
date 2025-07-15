package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.views.components.PointsCircle;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import edu.fiuba.algo3.models.sections.types.SpecialType;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;

import java.util.List;

public class UIRow extends CardList {
    private static final double INITIAL_TRANSLATE_X = -25;
    private static final double INITIAL_TRANSLATE_Y = 30;

    private int points;
    private final Row modelRow;
    private PointsCircle pointsCircle;
    private boolean switchedOn;

    public UIRow(Row modelRow) {
        super();
        switchedOn = false;
        setModel(modelRow);
        this.points = modelRow.calculatePoints();
        showPoints();
        subscribeToModel();
    }

    public void showPoints() {
        double circleRadius = 20;
        HBox rowBox = new HBox();
        rowBox.setAlignment(Pos.CENTER_LEFT);
        pointsCircle = new PointsCircle(points, circleRadius);

        pointsCircle.setTranslateX(INITIAL_TRANSLATE_X);
        pointsCircle.setTranslateY(INITIAL_TRANSLATE_Y);

        rowBox.getChildren().add(pointsCircle);
        getChildren().add(rowBox);
    }

    public void loadPoints() {
        if (model != null) {
            this.points = modelRow.calculatePoints();
            if (pointsCircle != null) {
                pointsCircle.setPoints(points);
            } else {
                showPoints();
            }
        }
    }

    @Override
    protected List<Card> getCardsFromModel() {
        return ((Row) model).getCards();
    }


    @Override
    public void update(Observable observable) {
        if (isModel(observable)) {
            loadCardsFromModel();
            loadPoints();

    public void switchOn(Card card) {
        Row row = (Row) model;
        if(row.haveSameSectionType(card) && row.haveSamePlayerColor(card)) {
            applyHoverStyle();
            switchedOn = true;
        }

        if(card.haveSectionType(new SpecialType())){
            applyHoverStyle();
            switchedOn = true;
        }
    }

    public void switchOff() {
        if (switchedOn) {
            applyDefaultStyle();
            switchedOn = false;
        }
    }

    public void playCard(UICard card, Round currentRound, UISpecialZone specialZone) {
        if (switchedOn) {
            Card cardToPlay = card.getModelCard();

            if (cardToPlay.haveSectionType(new SpecialType())) {
                specialZone.playCard(card, currentRound);
            }else{
                Row row = (Row) model;
                Player currentPlayer = currentRound.getCurrentPlayer();
                currentPlayer.playCard(cardToPlay, row, currentRound);
                addCard(card);
                update(model);
            }

            update(currentRound.getCurrentPlayer().getHand());
        }
    }
}
