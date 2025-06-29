package edu.fiuba.algo3.vistas.components.cardcomponent;

import edu.fiuba.algo3.modelo.Observable;
import javafx.scene.paint.Color;

public class UIDiscardPile extends BaseCardComponent {
    
    public UIDiscardPile() {
        super();
    }

    @Override
    protected void initializeComponent() {
        setupBackground();
    }

    @Override
    protected Color getFillColor() {
        return Color.rgb(169, 169, 169); // Light gray for discard pile
    }

    @Override
    protected Color getStrokeColor() {
        return Color.rgb(105, 105, 105); // Darker gray border
    }

    @Override
    protected String getScalingMethodName() {
        return "scaleDiscardPile";
    }

    public void scaleDiscardPile(double scaleFactorX, double scaleFactorY) {
        scaleComponent(scaleFactorX, scaleFactorY);
    }

    @Override
    public void update(Observable observable) {

    }
}