package edu.fiuba.algo3.views.components.cardcomponent;

import edu.fiuba.algo3.models.Observable;
import javafx.scene.paint.Color;

public class UIDeck extends BaseCardComponent {
    
    public UIDeck() {
        super();
    }

    @Override
    protected void initializeComponent() {
        setupBackground();
    }

    @Override
    protected Color getFillColor() {
        return Color.rgb(139, 69, 19); // Saddle brown for deck back
    }

    @Override
    protected Color getStrokeColor() {
        return Color.rgb(101, 67, 33); // Darker brown border
    }

    @Override
    protected String getScalingMethodName() {
        return "scaleDeck";
    }

    public void scaleDeck(double scaleFactorX, double scaleFactorY) {
        scaleComponent(scaleFactorX, scaleFactorY);
    }

    @Override
    public void update(Observable observable) {

    }
}