package edu.fiuba.algo3.views.components.cardcomponent;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cardcollections.Deck;
import javafx.scene.paint.Color;

public class UIDeck extends BaseCardComponent {
    private final Deck deck;
    
    public UIDeck(Deck deck) {
        super();
        this.deck = deck;
        this.deck.addObserver(this);
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
        // Update UI when deck changes (e.g., cards are drawn)
        // Could update card count display or visual representation
    }
}