package edu.fiuba.algo3.vistas.components;

import javafx.scene.paint.Color;

/**
 * Base component for all card types in the game
 */
public abstract class Card extends BaseCardComponent {
    
    protected String cardName;
    protected String description;
    
    public Card(String name, String description) {
        super();
        this.cardName = name;
        this.description = description;
    }

    @Override
    protected void initializeComponent() {
        setupBackground();
    }

    @Override
    protected Color getFillColor() {
        return Color.rgb(161, 92, 1); // Dark blue-gray
    }

    @Override
    protected Color getStrokeColor() {
        return Color.rgb(97, 47, 0); // Darker border
    }

    @Override
    protected String getScalingMethodName() {
        return "scaleCard";
    }

    public void scaleCard(double scaleFactorX, double scaleFactorY) {
        scaleComponent(scaleFactorX, scaleFactorY);
    }
    
    public String getDescription() {
        return description;
    }
} 