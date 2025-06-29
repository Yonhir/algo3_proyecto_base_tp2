package edu.fiuba.algo3.vistas.components.cardcomponent;

import edu.fiuba.algo3.modelo.Observer;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class BaseCardComponent extends StackPane implements Observer {
    
    protected static final double BASE_CARD_WIDTH = 80;
    protected static final double BASE_CARD_HEIGHT = 120;
    protected static final double CORNER_RADIUS = 6;
    protected static final double BASE_SCENE_WIDTH = 1920.0;
    protected static final double BASE_SCENE_HEIGHT = 1080.0;

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

    protected abstract String getScalingMethodName();

    protected void setupBackground() {
        background = new Rectangle(BASE_CARD_WIDTH, BASE_CARD_HEIGHT);
        background.setFill(getFillColor());
        background.setStroke(getStrokeColor());
        background.setStrokeWidth(2);
        background.setArcWidth(CORNER_RADIUS);
        background.setArcHeight(CORNER_RADIUS);

        setPrefSize(BASE_CARD_WIDTH, BASE_CARD_HEIGHT);
        setMaxSize(BASE_CARD_WIDTH, BASE_CARD_HEIGHT);

        getChildren().add(background);

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

    public void setupSceneSizeListener(javafx.scene.Scene scene) {
        if (scene == null) {
            System.out.println("Warning: Scene is null, cannot setup size listener");
            return;
        }
        
        ChangeListener<Number> sizeListener = getNumberChangeListener(scene);

        scene.widthProperty().addListener(sizeListener);
        scene.heightProperty().addListener(sizeListener);

        sizeListener.changed(null, 0, scene.getWidth());
    }

    private ChangeListener<Number> getNumberChangeListener(Scene scene) {
        return (observable, oldValue, newValue) -> {
            double sceneWidth = scene.getWidth();
            double sceneHeight = scene.getHeight();

            double widthScaleFactor = sceneWidth / BASE_SCENE_WIDTH;
            double heightScaleFactor = sceneHeight / BASE_SCENE_HEIGHT;

            scaleComponent(widthScaleFactor, heightScaleFactor);
        };
    }
} 