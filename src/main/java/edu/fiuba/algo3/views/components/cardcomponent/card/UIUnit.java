package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.types.SectionType;
import edu.fiuba.algo3.views.components.PointsCircle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.io.InputStream;



public class UIUnit extends UICard {
    // Circle positioning constants
    private static final double CIRCLE_RADIUS_RATIO = 0.15;
    private static final double INITIAL_TRANSLATE_X = -25;
    private static final double INITIAL_TRANSLATE_Y = -45;
    private static final double SCALED_TRANSLATE_X_RATIO = 0.3;
    private static final double SCALED_TRANSLATE_Y_RATIO = 0.35;
    private static final double ICON_SIZE_RATIO = 0.30;
    private ImageView sectionIcon;
    
    private PointsCircle pointsCircle;
    private int points;
    
    public UIUnit(Unit unit) {
        super(unit.getName(), unit.getDescription());
        this.model = unit;
        this.points = unit.calculatePoints();
        setupPointsDisplay();
        setupSectionIcon(unit.getSectionType());
        subscribeToModel();
    }
    
    private void subscribeToModel() {
        if (model != null) {
            model.addObserver(this);
        }
    }
    
    private void setupPointsDisplay() {
        double circleRadius = Math.min(getPrefWidth(), getPrefHeight()) * CIRCLE_RADIUS_RATIO;
        
        pointsCircle = new PointsCircle(points, circleRadius);
        
        pointsCircle.setTranslateX(INITIAL_TRANSLATE_X);
        pointsCircle.setTranslateY(INITIAL_TRANSLATE_Y);

        getChildren().add(pointsCircle);
    }
    
    public void loadDataFromUnit() {
        if (model != null) {
            this.points = ((Unit)model).calculatePoints();
            if (pointsCircle != null) {
                pointsCircle.setPoints(points);
            } else {
                setupPointsDisplay();
            }
        }
    }

    @Override
    public void update(Observable observable) {
        if (observable == model) {
            loadDataFromUnit();
        }
    }

    public void setPoints(int newPoints) {
        this.points = newPoints;
        if (pointsCircle != null) {
            pointsCircle.setPoints(newPoints);
        }
    }

    @Override
    public void scaleCard(double scaleFactorX, double scaleFactorY) {
        super.scaleCard(scaleFactorX, scaleFactorY);
        
        double newWidth = baseWidth * scaleFactorX;
        double newHeight = baseHeight * scaleFactorY;
        double circleRadius = Math.min(newWidth, newHeight) * CIRCLE_RADIUS_RATIO;
        pointsCircle.setRadius(circleRadius);
        
        double translateX = -newWidth * SCALED_TRANSLATE_X_RATIO;
        double translateY = -newHeight * SCALED_TRANSLATE_Y_RATIO;
        pointsCircle.setTranslateX(translateX);
        pointsCircle.setTranslateY(translateY);

        double iconSize = Math.min(newWidth, newHeight) * ICON_SIZE_RATIO;
        sectionIcon.setFitWidth(iconSize);
        sectionIcon.setFitHeight(iconSize);
        sectionIcon.setLayoutX(newWidth - iconSize - 10);
        sectionIcon.setLayoutY(10);
    }
    private void setupSectionIcon(SectionType type) {
        InputStream iconStream = SectionTypeIconMapper.getIconStream(type);
        if (iconStream == null) return;

        Image icon = new Image(iconStream);
        sectionIcon = new ImageView(icon);

        double iconSize = Math.min(getPrefWidth(), getPrefHeight()) * ICON_SIZE_RATIO;
        sectionIcon.setFitWidth(iconSize);
        sectionIcon.setFitHeight(iconSize);

        StackPane.setAlignment(sectionIcon, Pos.TOP_RIGHT);
        StackPane.setMargin(sectionIcon, new Insets(5, 5, 0, 0));

        getChildren().add(sectionIcon);
    }

}

