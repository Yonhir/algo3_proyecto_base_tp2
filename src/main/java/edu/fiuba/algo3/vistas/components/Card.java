package edu.fiuba.algo3.vistas.components;

import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Base component for all card types in the game
 */
public abstract class Card extends StackPane {
    
    protected static final double BASE_CARD_WIDTH = 80;
    protected static final double BASE_CARD_HEIGHT = 120;
    protected static final double CORNER_RADIUS = 6;
    protected static final double BASE_SCENE_WIDTH = 1920.0;
    protected static final double BASE_SCENE_HEIGHT = 1080.0;

    protected Rectangle background;
    protected String cardName;
    protected String description;
    
    // Base size for scaling calculations
    protected double baseWidth;
    protected double baseHeight;
    
    public Card(String name, String description) {
        this.cardName = name;
        this.description = description;
        this.baseWidth = BASE_CARD_WIDTH;
        this.baseHeight = BASE_CARD_HEIGHT;
        initializeCard();
    }

    private void initializeCard() {
        // Create the card background
        background = new Rectangle(BASE_CARD_WIDTH, BASE_CARD_HEIGHT);
        background.setFill(Color.rgb(161, 92, 1)); // Dark blue-gray
        background.setStroke(Color.rgb(97, 47, 0)); // Darker border
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

    public void scaleCard(double scaleFactorX, double scaleFactorY) {
        double newWidth = baseWidth * scaleFactorX;
        double newHeight = baseHeight * scaleFactorY;
        setCardSize(newWidth, newHeight);
    }
    
    private void setCardSize(double width, double height) {
        background.setWidth(width);
        background.setHeight(height);
        setPrefSize(width, height);
        setMaxSize(width, height);
    }
    
    public String getDescription() {
        return description;
    }

    public void setupSceneSizeListener(javafx.scene.Scene scene) {
        if (scene == null) {
            System.out.println("Warning: Scene is null, cannot setup size listener");
            return;
        }
        
        // Base resolution for which the card size is designed

        ChangeListener<Number> sizeListener = getNumberChangeListener(scene);

        // Add listeners to both width and height properties
        scene.widthProperty().addListener(sizeListener);
        scene.heightProperty().addListener(sizeListener);
        
        // Trigger initial sizing
        sizeListener.changed(null, 0, scene.getWidth());
    }

    private ChangeListener<Number> getNumberChangeListener(Scene scene) {

        // Create listener for scene size changes
        // Calculate scale factors for width and height
        // Apply scaling to the card
        return (observable, oldValue, newValue) -> {
            double sceneWidth = scene.getWidth();
            double sceneHeight = scene.getHeight();

            // Calculate scale factors for width and height
            double widthScaleFactor = sceneWidth / BASE_SCENE_WIDTH;
            double heightScaleFactor = sceneHeight / BASE_SCENE_HEIGHT;

            // Apply scaling to the card
            scaleCard(widthScaleFactor, heightScaleFactor);
        };
    }
} 