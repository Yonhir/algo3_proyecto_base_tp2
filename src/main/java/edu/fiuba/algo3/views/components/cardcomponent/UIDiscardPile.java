package edu.fiuba.algo3.views.components.cardcomponent;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import javafx.scene.paint.Color;

public class UIDiscardPile extends BaseCardComponent {
    private final DiscardPile discardPile;
    
    public UIDiscardPile(DiscardPile discardPile) {
        super();
        this.discardPile = discardPile;
        this.discardPile.addObserver(this);
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
        // Update UI when discard pile changes (e.g., cards are added)
        // Could update card count display or visual representation
    }
}