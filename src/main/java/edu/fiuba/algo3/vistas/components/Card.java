package edu.fiuba.algo3.vistas.components;

import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

/**
 * Base component for all card types in the game
 */
public abstract class Card extends BaseCardComponent {
    
    protected String cardName;
    protected String description;
    protected ImageView backgroundImage;
    
    // Drag functionality variables
    private double dragDeltaX;
    private double dragDeltaY;
    private boolean isDragging = false;
    private boolean isDraggable = false; // Default to non-draggable
    
    // Original position tracking
    private double originalX = 0;
    private double originalY = 0;
    
    public Card(String name, String description) {
        super();
        this.cardName = name;
        this.description = description;
        setupDragHandlers();
        loadCardImage();
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
        
        // Update background image size if it exists
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
        
        // Try to find image with .png extension first
        String pngPath = "/images/" + cardName + ".png";
        InputStream pngStream = getClass().getResourceAsStream(pngPath);
        
        if (pngStream != null) {
            loadImageFromStream(pngStream);
            return;
        }
        
        // Try to find image with .webp extension
        String webpPath = "/images/" + cardName + ".webp";
        InputStream webpStream = getClass().getResourceAsStream(webpPath);
        
        if (webpStream != null) {
            loadImageFromStream(webpStream);
            return;
        }
        
        // If no image found, keep the default background
        System.out.println("No image found for card: " + cardName);
    }
    
    private void loadImageFromStream(InputStream imageStream) {
        try {
            Image image = new Image(imageStream);
            backgroundImage = new ImageView(image);
            
            // Set the image to fit the card size using the base dimensions
            backgroundImage.setFitWidth(baseWidth);
            backgroundImage.setFitHeight(baseHeight);
            backgroundImage.setPreserveRatio(true);
            
            // Add the image as background (behind other elements)
            getChildren().add(0, backgroundImage); // Add at index 0 to be behind other elements
            
            System.out.println("Successfully loaded image for card: " + cardName);
        } catch (Exception e) {
            System.err.println("Error loading image for card " + cardName + ": " + e.getMessage());
        }
    }
    
    public String getDescription() {
        return description;
    }
    
    /**
     * Setup mouse event handlers for drag functionality
     */
    private void setupDragHandlers() {
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnMouseReleased(this::handleMouseReleased);
    }
    
    /**
     * Handle mouse press event - start of drag operation
     */
    private void handleMousePressed(MouseEvent event) {
        if (!isDraggable) {
            return; // Don't start drag if card is not draggable
        }
        
        isDragging = true;
        
        // Store original position before starting drag
        originalX = getTranslateX();
        originalY = getTranslateY();
        
        dragDeltaX = getTranslateX() - event.getSceneX();
        dragDeltaY = getTranslateY() - event.getSceneY();
        
        // Bring card to front when dragging starts
        toFront();
        
        event.consume();
    }
    
    /**
     * Handle mouse drag event - during drag operation
     */
    private void handleMouseDragged(MouseEvent event) {
        if (!isDraggable || !isDragging) {
            return; // Don't drag if card is not draggable or not currently dragging
        }
        
        setTranslateX(event.getSceneX() + dragDeltaX);
        setTranslateY(event.getSceneY() + dragDeltaY);
        
        event.consume();
    }
    
    /**
     * Handle mouse release event - end of drag operation
     */
    private void handleMouseReleased(MouseEvent event) {
        if (!isDraggable) {
            return; // Don't handle release if card is not draggable
        }
        
        isDragging = false;
        
        // Return card to original position
        setTranslateX(originalX);
        setTranslateY(originalY);
        
        // TODO: Add logic here to handle card placement
        // For example, check if the card is over a valid drop zone
        // and trigger controller actions
        
        event.consume();
    }
    
    /**
     * Reset the card position to its original location
     */
    public void resetPosition() {
        setTranslateX(originalX);
        setTranslateY(originalY);
    }
    
    /**
     * Get the card name
     */
    public String getCardName() {
        return cardName;
    }
    
    /**
     * Get the current drag state
     */
    public boolean isDragging() {
        return isDragging;
    }
    
    /**
     * Get the current position of the card
     */
    public double getCurrentX() {
        return getTranslateX();
    }
    
    public double getCurrentY() {
        return getTranslateY();
    }
    
    /**
     * Set whether the card can be dragged
     */
    public void setDraggable(boolean draggable) {
        this.isDraggable = draggable;
    }
    
    /**
     * Check if the card is draggable
     */
    public boolean isDraggable() {
        return isDraggable;
    }
    
    /**
     * Update the original position (useful when card is moved to a new location)
     */
    public void updateOriginalPosition() {
        originalX = getTranslateX();
        originalY = getTranslateY();
    }
} 