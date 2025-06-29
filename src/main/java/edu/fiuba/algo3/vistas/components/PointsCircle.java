package edu.fiuba.algo3.vistas.components;

import edu.fiuba.algo3.modelo.Observable;
import edu.fiuba.algo3.modelo.Observer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;

public class PointsCircle extends StackPane implements Observer {
    
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
        circle.setStrokeWidth(2);

        pointsText = new Text(String.valueOf(points));
        pointsText.setFont(Font.font("Arial", FontWeight.BOLD, radius * 0.8));
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
        pointsText.setFont(Font.font("Arial", FontWeight.BOLD, radius * 0.8));
    }
    
    public int getPoints() {
        return points;
    }
}
