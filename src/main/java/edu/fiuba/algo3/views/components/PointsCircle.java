package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.Observer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;

public class PointsCircle extends StackPane implements Observer {
    // Circle constants
    private static final double STROKE_WIDTH = 2;
    private static final double FONT_SIZE_RATIO = 0.8;
    
    private Circle circle;
    private Text pointsText;
    private int points;
    
    public PointsCircle(int points, double radius) {
        this.points = points;
        setupCircle(radius);
    }
    
    private void setupCircle(double radius) {
        circle = new Circle(radius);
        circle.setFill(Color.rgb(231, 76, 60));
        circle.setStroke(Color.rgb(192, 57, 43));
        circle.setStrokeWidth(STROKE_WIDTH);

        pointsText = new Text(String.valueOf(points));
        pointsText.setFont(Font.font("Arial", FontWeight.BOLD, radius * FONT_SIZE_RATIO));
        pointsText.setFill(Color.WHITE);
        
        getChildren().addAll(circle, pointsText);
        setAlignment(javafx.geometry.Pos.CENTER);
    }

    public void update(Observable o) {

    }
    
    public void setPoints(int newPoints) {
        this.points = newPoints;
        pointsText.setText(String.valueOf(newPoints));
    }
    
    public void setRadius(double radius) {
        circle.setRadius(radius);
        pointsText.setFont(Font.font("Arial", FontWeight.BOLD, radius * FONT_SIZE_RATIO));
    }
    
    public int getPoints() {
        return points;
    }
}
