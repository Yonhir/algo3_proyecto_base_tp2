package edu.fiuba.algo3.vistas.components;

import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

/**
 * Component for displaying a list of cards horizontally with absolute positioning
 */
public class CardList extends Pane {
    
    private List<CardComponent> cards;
    private double spacing;
    private double margins; // Left and right margins
    
    /**
     * Create a CardList with default spacing
     */
    public CardList() {
        this(20.0); // Default 20px spacing
    }
    
    /**
     * Create a CardList with custom spacing
     * @param spacing Spacing between cards in pixels
     */
    public CardList(double spacing) {
        this(spacing, 20.0); // Default 20px margins
    }
    
    /**
     * Create a CardList with custom spacing and margins
     * @param spacing Default spacing between cards in pixels
     * @param margins Left and right margins in pixels
     */
    public CardList(double spacing, double margins) {
        this.spacing = spacing;
        this.margins = margins;
        this.cards = new ArrayList<>();
        
        // Allow the CardList to grow horizontally and vertically based on content
        setPrefHeight(0); // Start with 0 height, will be adjusted based on cards
        
        // Add listener to recalculate positioning when width changes
        widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() > 0) {
                calculateOptimalPositioning();
            }
        });
    }
    
    /**
     * Calculate and apply optimal positioning for all cards
     */
    public void calculateOptimalPositioning() {
        if (cards.isEmpty()) {
            setPrefHeight(0); // No cards, no height needed
            return;
        }
        
        // 1. Get the width of the CardList, but respect parent constraints
        double listWidth = getWidth();
        if (listWidth <= 0) {
            return; // Width not yet available
        }
        
        // 2. Check if we have a parent and get its width constraints
        if (getParent() != null) {
            double parentWidth = getParent().getLayoutBounds().getWidth();
            if (parentWidth > 0) {
                // Use the smaller of our width or parent's width
                listWidth = Math.min(listWidth, parentWidth);
            }
        }
        
        // 3. Calculate width without margins
        double widthWithoutMargins = listWidth - (2 * margins);
        
        // 4. Get the width and height of a card (all cards have the same dimensions)
        double cardWidth = cards.get(0).getPrefWidth();
        double cardHeight = cards.get(0).getPrefHeight();
        
        // 5. Set the CardList height to match the card height
        setPrefHeight(cardHeight);
        
        // 6. Calculate width without margins and cards
        double widthWithoutMarginsAndCards = widthWithoutMargins - cardWidth;
        
        // 7. Calculate padding by dividing remaining width by number of cards
        double padding;
        if (cards.size() > 1) {
            padding = widthWithoutMarginsAndCards / cards.size();
            padding = Math.round(padding); // Round to nearest pixel
        } else {
            padding = 0; // Only one card, no padding needed
        }
        
        // 8. Position each card using the formula: margin_left + (padding * N)
        for (int i = 0; i < cards.size(); i++) {
            CardComponent card = cards.get(i);
            double xPosition = margins + (padding * (i + 1));
            card.setLayoutX(xPosition);
            card.setLayoutY(0); // Align to top
        }
        
        System.out.println("CardList - Width: " + listWidth + 
                          ", Height: " + cardHeight +
                          ", Width without margins: " + widthWithoutMargins + 
                          ", Card Width: " + cardWidth + 
                          ", Width without margins and cards: " + widthWithoutMarginsAndCards +
                          ", Cards: " + cards.size() + 
                          ", Padding: " + padding);
        
        // Print individual card positions for debugging
        for (int i = 0; i < cards.size(); i++) {
            CardComponent card = cards.get(i);
            double xPos = margins + (padding * (i + 1));
            System.out.println("Card " + (i + 1) + " (" + card.getCardName() + ") - X: " + xPos);
        }
    }
    
    /**
     * Add a card to the list
     * @param card The card to add
     */
    public void addCard(CardComponent card) {
        cards.add(card);
        getChildren().add(card);
        calculateOptimalPositioning();
    }
    
    /**
     * Add multiple cards to the list
     * @param cardsToAdd List of cards to add
     */
    public void addCards(List<CardComponent> cardsToAdd) {
        for (CardComponent card : cardsToAdd) {
            addCard(card);
        }
    }
    
    /**
     * Add multiple cards to the list using varargs
     * @param cardsToAdd Cards to add
     */
    public void addCards(CardComponent... cardsToAdd) {
        for (CardComponent card : cardsToAdd) {
            addCard(card);
        }
    }
    
    /**
     * Remove a card from the list
     * @param card The card to remove
     */
    public void removeCard(CardComponent card) {
        cards.remove(card);
        getChildren().remove(card);
        calculateOptimalPositioning();
    }
    
    /**
     * Remove a card by index
     * @param index Index of the card to remove
     */
    public void removeCard(int index) {
        if (index >= 0 && index < cards.size()) {
            CardComponent card = cards.remove(index);
            getChildren().remove(card);
            calculateOptimalPositioning();
        }
    }
    
    /**
     * Clear all cards from the list
     */
    public void clearCards() {
        cards.clear();
        getChildren().clear();
    }
    
    /**
     * Get a card by index
     * @param index Index of the card
     * @return The card at the specified index, or null if index is invalid
     */
    public CardComponent getCard(int index) {
        if (index >= 0 && index < cards.size()) {
            return cards.get(index);
        }
        return null;
    }
    
    /**
     * Get all cards in the list
     * @return List of all cards
     */
    public List<CardComponent> getCards() {
        return new ArrayList<>(cards);
    }
    
    /**
     * Get the number of cards in the list
     * @return Number of cards
     */
    public int getCardCount() {
        return cards.size();
    }
    
    /**
     * Check if the list is empty
     * @return true if no cards, false otherwise
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    
    /**
     * Set the spacing between cards (for backward compatibility)
     * @param spacing New spacing in pixels
     */
    public void setCardSpacing(double spacing) {
        this.spacing = spacing;
        calculateOptimalPositioning();
    }
    
    /**
     * Get the current spacing between cards
     * @return Current spacing in pixels
     */
    public double getCardSpacing() {
        return spacing;
    }
    
    /**
     * Set the margins for the card list
     * @param margins Left and right margins in pixels
     */
    public void setMargins(double margins) {
        this.margins = margins;
        calculateOptimalPositioning();
    }
    
    /**
     * Get the current margins
     * @return Current margins in pixels
     */
    public double getMargins() {
        return margins;
    }
    
    /**
     * Force recalculation of optimal positioning
     * Useful when cards are added/removed programmatically
     */
    public void recalculatePositioning() {
        calculateOptimalPositioning();
    }
}
