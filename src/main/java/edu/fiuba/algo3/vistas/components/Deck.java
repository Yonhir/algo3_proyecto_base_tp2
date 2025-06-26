package edu.fiuba.algo3.vistas.components;

import edu.fiuba.algo3.modelo.Observable;
import javafx.scene.paint.Color;

/**
 * Component representing the deck of cards (shows back of card design)
 */
public class Deck extends BaseCardComponent {
    
    public Deck() {
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