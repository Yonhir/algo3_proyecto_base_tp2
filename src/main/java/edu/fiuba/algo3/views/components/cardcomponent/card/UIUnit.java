package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.views.components.PointsCircle;

public class UIUnit extends UICard {
    // Circle positioning constants
    private static final double CIRCLE_RADIUS_RATIO = 0.15;
    private static final double INITIAL_TRANSLATE_X = -25;
    private static final double INITIAL_TRANSLATE_Y = -45;
    private static final double SCALED_TRANSLATE_X_RATIO = 0.3;
    private static final double SCALED_TRANSLATE_Y_RATIO = 0.35;
    
    private PointsCircle pointsCircle;
    private int points;
    private final Unit model;
    
    public UIUnit(Unit unit) {
        super(unit.getName(), unit.getDescription());
        this.model = unit;
        this.points = unit.calculatePoints();
        setupPointsDisplay();
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
            this.points = model.calculatePoints();
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
    }
}
