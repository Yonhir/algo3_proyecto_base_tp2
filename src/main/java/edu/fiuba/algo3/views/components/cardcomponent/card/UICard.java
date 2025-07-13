package edu.fiuba.algo3.views.components.cardcomponent.card;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.views.components.cardcomponent.BaseCardComponent;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public abstract class UICard extends BaseCardComponent {
    
    protected String cardName;
    protected String description;
    protected ImageView backgroundImage;
    
    private double dragDeltaX;
    private double dragDeltaY;
    private boolean isDragging = false;
    private boolean isDraggable = false;
    
    private double originalX = 0;
    private double originalY = 0;

    protected  Card model;
    
    public UICard(String name, String description) {
        super();
        this.cardName = name;
        this.description = description;
        setupDragHandlers();
        loadCardImage();
    }

    public Card getModelCard(){
        return model;
    }

    @Override
    protected void initializeComponent() {
        setupBackground();
    }

    @Override
    protected Color getFillColor() {
        return Color.rgb(161, 92, 1);
    }

    @Override
    protected Color getStrokeColor() {
        return Color.rgb(97, 47, 0);
    }

    public void scaleCard(double scaleFactorX, double scaleFactorY) {
        scaleComponent(scaleFactorX, scaleFactorY);
        
        if (backgroundImage != null) {
            double newWidth = baseWidth * scaleFactorX;
            double newHeight = baseHeight * scaleFactorY;
            backgroundImage.setFitWidth(newWidth);
            backgroundImage.setFitHeight(newHeight);
        }
    }
    
    private void loadCardImage() {
        if (cardName == null || cardName.trim().isEmpty()) {
            return;
        }
        
        String pngPath = "/images/" + cardName + ".png";
        InputStream pngStream = getClass().getResourceAsStream(pngPath);
        
        if (pngStream != null) {
            loadImageFromStream(pngStream);
            return;
        }
        
        String webpPath = "/images/" + cardName + ".webp";
        InputStream webpStream = getClass().getResourceAsStream(webpPath);
        
        if (webpStream != null) {
            loadImageFromStream(webpStream);
        }
    }
    
    private void loadImageFromStream(InputStream imageStream) {
        try {
            Image image = new Image(imageStream);
            backgroundImage = new ImageView(image);
            backgroundImage.setFitWidth(baseWidth);
            backgroundImage.setFitHeight(baseHeight);
            backgroundImage.setPreserveRatio(true);
            getChildren().add(0, backgroundImage);
        } catch (Exception e) {
            System.err.println("Error loading image for card " + cardName + ": " + e.getMessage());
        }
    }
    
    public String getDescription() {
        return description;
    }
    
    private void setupDragHandlers() {
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseReleased);
    }
    
    private void handleMousePressed(MouseEvent event) {
        if (!isDraggable) {
            return;
        }
        
        isDragging = true;
        
        originalX = getTranslateX();
        originalY = getTranslateY();
        
        dragDeltaX = getTranslateX() - event.getSceneX();
        dragDeltaY = getTranslateY() - event.getSceneY();
        
        toFront();
        
        event.consume();
    }
    
    private void handleMouseDragged(MouseEvent event) {
        if (!isDraggable || !isDragging) {
            return;
        }
        
        setTranslateX(event.getSceneX() + dragDeltaX);
        setTranslateY(event.getSceneY() + dragDeltaY);
        
        event.consume();
    }
    
    private void handleMouseReleased(MouseEvent event) {
        if (!isDraggable) {
            return;
        }
        
        isDragging = false;
        
        setTranslateX(originalX);
        setTranslateY(originalY);
        
        event.consume();
    }
    
    public void resetPosition() {
        setTranslateX(originalX);
        setTranslateY(originalY);
    }
    
    public void setDraggable(boolean draggable) {
        this.isDraggable = draggable;
    }
    
    public void updateOriginalPosition() {
        originalX = getTranslateX();
        originalY = getTranslateY();
    }
}
