package edu.fiuba.algo3.vistas.components;


public class UnitCard extends Card {
    
    private PointsCircle pointsCircle;
    private int points;
    
    public UnitCard(String name, String description, int points) {
        super(name, description);
        this.points = points;
        setupPointsDisplay();
    }
    
    private void setupPointsDisplay() {
        // Calculate circle size based on card size - adjusted for smaller cards
        double circleRadius = Math.min(getPrefWidth(), getPrefHeight()) * 0.15; // Adjusted from 0.13
        
        // Create points' circle
        pointsCircle = new PointsCircle(points, circleRadius);
        
        // Position the circle in the top-left corner inside the card using absolute positioning
        // Adjusted for smaller card size
        pointsCircle.setTranslateX(-25); // Adjusted from -35
        pointsCircle.setTranslateY(-45); // Adjusted from -65

        // Add the circle to the card (it will be on top of the background)
        getChildren().add(pointsCircle);
    }
    
    public void setPoints(int newPoints) {
        this.points = newPoints;
        pointsCircle.setPoints(newPoints);
    }
    
    public int getPoints() {
        return points;
    }
    
    @Override
    public void scaleCard(double scaleFactorX, double scaleFactorY) {
        // Call parent scaleCard method to handle the card background scaling
        super.scaleCard(scaleFactorX, scaleFactorY);
        
        // Update circle size based on the new card dimensions
        double newWidth = baseWidth * scaleFactorX;
        double newHeight = baseHeight * scaleFactorY;
        double circleRadius = Math.min(newWidth, newHeight) * 0.15;
        pointsCircle.setRadius(circleRadius);
        
        // Update circle position based on new card size
        // Position the circle in the top-left corner, proportional to card size
        double translateX = -newWidth * 0.3; // 30% of card width to the left
        double translateY = -newHeight * 0.35; // 40% of card height to the top
        pointsCircle.setTranslateX(translateX);
        pointsCircle.setTranslateY(translateY);
    }
}
