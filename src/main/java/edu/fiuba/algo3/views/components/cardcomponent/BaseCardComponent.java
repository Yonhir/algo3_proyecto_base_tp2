package edu.fiuba.algo3.views.components.cardcomponent;

import edu.fiuba.algo3.models.Observer;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class BaseCardComponent extends StackPane implements Observer {
    
    protected static final double BASE_CARD_WIDTH = 80;
    protected static final double BASE_CARD_HEIGHT = 120;
    protected static final double CORNER_RADIUS = 6;
    
    // Stroke and hover constants
    protected static final double STROKE_WIDTH = 2;
    protected static final double HOVER_SCALE_X = 1.05;
    protected static final double HOVER_SCALE_Y = 1.05;
    protected static final double HOVER_TRANSLATE_Y = -3;
    protected static final double NORMAL_SCALE = 1.0;
    protected static final double NORMAL_TRANSLATE_Y = 0;

    protected Rectangle background;

    protected double baseWidth;
    protected double baseHeight;
    
    public BaseCardComponent() {
        this.baseWidth = BASE_CARD_WIDTH;
        this.baseHeight = BASE_CARD_HEIGHT;
        initializeComponent();
    }

    protected abstract void initializeComponent();

    protected abstract Color getFillColor();

    protected abstract Color getStrokeColor();

    protected void setupBackground() {
        background = new Rectangle(BASE_CARD_WIDTH, BASE_CARD_HEIGHT);
        background.setFill(getFillColor());
        background.setStroke(getStrokeColor());
        background.setStrokeWidth(STROKE_WIDTH);
        background.setArcWidth(CORNER_RADIUS);
        background.setArcHeight(CORNER_RADIUS);

        setPrefSize(BASE_CARD_WIDTH, BASE_CARD_HEIGHT);
        setMaxSize(BASE_CARD_WIDTH, BASE_CARD_HEIGHT);

        getChildren().add(background);

        setupHoverEffects();
    }
    
    private void setupHoverEffects() {
        setOnMouseEntered(e -> {
            setScaleX(HOVER_SCALE_X);
            setScaleY(HOVER_SCALE_Y);
            setTranslateY(HOVER_TRANSLATE_Y);
        });
        
        setOnMouseExited(e -> {
            setScaleX(NORMAL_SCALE);
            setScaleY(NORMAL_SCALE);
            setTranslateY(NORMAL_TRANSLATE_Y);
        });
    }

    public void scaleComponent(double scaleFactorX, double scaleFactorY) {
        double newWidth = baseWidth * scaleFactorX;
        double newHeight = baseHeight * scaleFactorY;
        setComponentSize(newWidth, newHeight);
    }
    
    private void setComponentSize(double width, double height) {
        background.setWidth(width);
        background.setHeight(height);
        setPrefSize(width, height);
        setMaxSize(width, height);
    }
} 