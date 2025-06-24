package edu.fiuba.algo3.vistas.components;

/**
 * Component for displaying special cards (weather effects, morale boost, etc.)
 * Inherits from CardList with specialized special zone functionality
 */
public class SpecialZone extends CardList {
    
    private static final double DEFAULT_SPECIAL_SPACING = 10.0;
    private static final double DEFAULT_SPECIAL_MARGINS = 15.0;
    
    /**
     * Create a SpecialZone with default spacing and margins
     */
    public SpecialZone() {
        super(DEFAULT_SPECIAL_SPACING, DEFAULT_SPECIAL_MARGINS);
        setupSpecialZoneProperties();
    }
    
    /**
     * Create a SpecialZone with custom spacing and margins
     * @param spacing Spacing between cards in pixels
     * @param margins Left and right margins in pixels
     */
    public SpecialZone(double spacing, double margins) {
        super(spacing, margins);
        setupSpecialZoneProperties();
    }
    
    /**
     * Setup special zone-specific properties
     */
    private void setupSpecialZoneProperties() {
        // Set special zone-specific styling or behavior
        setStyle("-fx-background-color: transparent;");
    }
    
    /**
     * Add a card to the special zone (accepts both UnitCard and SpecialCard)
     * @param card The card to add (UnitCard or SpecialCard)
     */
    @Override
    public void addCard(CardComponent card) {
        super.addCard(card);
    }

    public void addSpecialCardsSafely(CardComponent... cardsToAdd) {
        for (CardComponent card : cardsToAdd) {
            super.addCard(card);
        }
    }

    public boolean isEmpty() {
        return getCardCount() == 0;
    }
}
