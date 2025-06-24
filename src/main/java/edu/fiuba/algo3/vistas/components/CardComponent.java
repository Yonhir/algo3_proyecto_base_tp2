package edu.fiuba.algo3.vistas.components;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Base component for all card types in the game
 */
public abstract class CardComponent extends StackPane {
    
    protected static final double BASE_CARD_WIDTH = 80;
    protected static final double BASE_CARD_HEIGHT = 120;
    protected static final double CORNER_RADIUS = 6;
    
    protected Rectangle background;
    protected String cardName;
    protected String description;
    
    // Base size for scaling calculations
    protected double baseWidth;
    protected double baseHeight;
    
    public CardComponent(String name, String description) {
        this.cardName = name;
        this.description = description;
        initializeCard();
    }

    private void initializeCard() {
        // Create card background
        background = new Rectangle(BASE_CARD_WIDTH, BASE_CARD_HEIGHT);
        background.setFill(Color.rgb(52, 73, 94)); // Dark blue-gray
        background.setStroke(Color.rgb(44, 62, 80)); // Darker border
        background.setStrokeWidth(2);
        background.setArcWidth(CORNER_RADIUS);
        background.setArcHeight(CORNER_RADIUS);
        
        // Set card size
        setPrefSize(BASE_CARD_WIDTH, BASE_CARD_HEIGHT);
        setMaxSize(BASE_CARD_WIDTH, BASE_CARD_HEIGHT);
        
        // Add background as the first child (bottom layer)
        getChildren().add(background);
        
        // Add hover effect
        setupHoverEffects();
    }
    
    private void setupHoverEffects() {
        setOnMouseEntered(e -> {
            setScaleX(1.05);
            setScaleY(1.05);
            setTranslateY(-3);
        });
        
        setOnMouseExited(e -> {
            setScaleX(1.0);
            setScaleY(1.0);
            setTranslateY(0);
        });
    }
    
    /**
     * Scale the card based on a single conversion factor
     * @param scaleFactor The factor to scale the card by (e.g., 1.5 = 50% larger, 0.8 = 20% smaller)
     */
    public void scaleCard(double scaleFactor) {
        double newWidth = baseWidth * scaleFactor;
        double newHeight = baseHeight * scaleFactor;
        setCardSize(newWidth, newHeight);
    }
    
    private void setCardSize(double width, double height) {
        background.setWidth(width);
        background.setHeight(height);
        setPrefSize(width, height);
        setMaxSize(width, height);
    }
    
    public String getCardName() {
        return cardName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getBaseWidth() {
        return baseWidth;
    }
    
    public double getBaseHeight() {
        return baseHeight;
    }
} 