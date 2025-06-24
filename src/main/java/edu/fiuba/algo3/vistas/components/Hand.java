package edu.fiuba.algo3.vistas.components;

/**
 * Component for displaying the player's hand of cards
 * Inherits from CardList with specialized hand functionality
 */
public class Hand extends CardList {
    
    private static final double DEFAULT_HAND_SPACING = 8.0;
    private static final double DEFAULT_HAND_MARGINS = 15.0;
    
    /**
     * Create a Hand with default spacing and margins
     */
    public Hand() {
        super(DEFAULT_HAND_SPACING, DEFAULT_HAND_MARGINS);
        setupHandProperties();
    }
    
    /**
     * Create a Hand with custom spacing and margins
     * @param spacing Spacing between cards in pixels
     * @param margins Left and right margins in pixels
     */
    public Hand(double spacing, double margins) {
        super(spacing, margins);
        setupHandProperties();
    }
    
    /**
     * Setup hand-specific properties
     */
    private void setupHandProperties() {
        // Set hand-specific styling or behavior
        setStyle("-fx-background-color: transparent;");
    }
    
    /**
     * Add a card to the hand (accepts both UnitCard and SpecialCard)
     * @param card The card to add (UnitCard or SpecialCard)
     */
    @Override
    public void addCard(CardComponent card) {
        super.addCard(card);
    }
    
    /**
     * Add multiple cards to the hand (accepts both UnitCard and SpecialCard)
     * @param cardsToAdd Cards to add (UnitCard or SpecialCard)
     * @return Number of cards actually added
     */
    public int addCardsSafely(CardComponent... cardsToAdd) {
        int addedCount = 0;
        for (CardComponent card : cardsToAdd) {
            super.addCard(card);
            addedCount++;
        }
        return addedCount;
    }
    
    /**
     * Check if the hand is empty
     * @return true if hand has no cards
     */
    public boolean isEmpty() {
        return getCardCount() == 0;
    }
    
    /**
     * Draw a card from the hand (remove and return the first card)
     * @return The card drawn, or null if hand is empty
     */
    public CardComponent drawCard() {
        if (!isEmpty()) {
            CardComponent drawnCard = getCard(0);
            removeCard(0);
            return drawnCard;
        }
        return null;
    }
    
    /**
     * Draw a specific card from the hand
     * @param card The card to draw
     * @return true if card was drawn successfully, false if card not found
     */
    public boolean drawCard(CardComponent card) {
        if (getCards().contains(card)) {
            removeCard(card);
            return true;
        }
        return false;
    }
    
    /**
     * Get hand information as a string
     * @return String representation of hand status
     */
    public String getHandInfo() {
        return String.format("Hand: %d cards", getCardCount());
    }
} 