package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.models.sections.types.SectionType;
import edu.fiuba.algo3.views.components.PointsCircle;
import edu.fiuba.algo3.views.components.cardcomponent.card.SectionTypeIconMapper;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import edu.fiuba.algo3.models.sections.types.SpecialType;
import edu.fiuba.algo3.models.turnManagement.Player;
import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;

import java.io.InputStream;
import java.util.List;

public abstract class UIRow extends CardList {

    private static final double INITIAL_TRANSLATE_X = -25;
    private static final double INITIAL_TRANSLATE_Y = 30;

    private boolean switchedOn;
    private int points;
    private final Row modelRow;
    private PointsCircle pointsCircle;
    protected SectionType type;
    private ImageView sectionIcon;

    public UIRow() {
        super();
        this.points = 0;
        modelRow = null;
    }

    public UIRow(Row modelRow) {
        super();
        switchedOn = false;
        this.modelRow = modelRow;
        setModel(modelRow);
        this.points = modelRow.calculatePoints();
        showPoints();
        subscribeToModel();
        setType();
        showIcon();
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
        }
    }

    abstract protected void setType();

    public void showIcon() {
        InputStream iconStream = SectionTypeIconMapper.getIconStream(type);

        Image icon = new Image(iconStream);
        sectionIcon = new ImageView(icon);

        double size = 80;
        sectionIcon.setFitHeight(size);
        sectionIcon.setFitWidth(size);
        sectionIcon.setOpacity(0.5);
        getChildren().add(0, sectionIcon);
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        if (sectionIcon != null) {
            double width = sectionIcon.prefWidth(-1);
            double height = sectionIcon.prefHeight(-1);

            double x = (getWidth() - width) / 2;
            double y = (getHeight() - height) / 2;

            sectionIcon.resizeRelocate(x, y, width, height);
        }
    }

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
