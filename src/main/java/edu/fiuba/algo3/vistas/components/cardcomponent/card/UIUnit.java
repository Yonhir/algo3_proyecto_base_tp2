package edu.fiuba.algo3.vistas.components.cardcomponent.card;

import edu.fiuba.algo3.modelo.Observable;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.vistas.components.PointsCircle;

public class UIUnit extends UICard {
    
    private PointsCircle pointsCircle;
    private int points;
    private Unit model;
    
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
        double circleRadius = Math.min(getPrefWidth(), getPrefHeight()) * 0.15;
        
        pointsCircle = new PointsCircle(points, circleRadius);
        
        pointsCircle.setTranslateX(-25);
        pointsCircle.setTranslateY(-45);

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
        double circleRadius = Math.min(newWidth, newHeight) * 0.15;
        pointsCircle.setRadius(circleRadius);
        
        double translateX = -newWidth * 0.3;
        double translateY = -newHeight * 0.35;
        pointsCircle.setTranslateX(translateX);
        pointsCircle.setTranslateY(translateY);
    }
}
