package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.Section;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.SectionType;
import edu.fiuba.algo3.views.components.PointsCircle;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.InputStream;
import java.util.List;

public abstract class UIRow extends CardList {

    private static final double INITIAL_TRANSLATE_X = -25;
    private static final double INITIAL_TRANSLATE_Y = 30;

    private int points;
    private final Row modelRow;
    private PointsCircle pointsCircle;
    protected SectionType type;

    public UIRow() {
        super(false);
        this.points = 0;
        modelRow = null;
    }

    public UIRow(Row modelRow) {
        super(false);
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
        ImageView sectionIcon = new ImageView(icon);

        double size = 20;
        sectionIcon.setFitHeight(size);
        sectionIcon.setFitWidth(size);

        StackPane.setAlignment(sectionIcon, Pos.CENTER);

        getChildren().add(sectionIcon);
    }
}
