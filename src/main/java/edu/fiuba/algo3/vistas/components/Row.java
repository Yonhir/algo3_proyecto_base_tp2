package edu.fiuba.algo3.vistas.components;

/**
 * Component for displaying unit cards in game rows (close combat, ranged, siege)
 * Inherits from CardList with specialized row functionality
 */
public class Row extends CardList {
    
    private static final double DEFAULT_ROW_SPACING = 6.0;
    private static final double DEFAULT_ROW_MARGINS = 10.0;
    
    private final String rowType; // "Close Combat", "Ranged", or "Siege"
    private final boolean isOpponentRow; // true for opponent rows, false for player rows
    
    /**
     * Create a Row with default spacing and margins
     * @param rowType Type of row ("Close Combat", "Ranged", or "Siege")
     * @param isOpponentRow true if this is an opponent row, false if player row
     */
    public Row(String rowType, boolean isOpponentRow) {
        super(DEFAULT_ROW_SPACING, DEFAULT_ROW_MARGINS);
        this.rowType = rowType;
        this.isOpponentRow = isOpponentRow;
        setupRowProperties();
    }
    
    /**
     * Create a Row with custom spacing and margins
     * @param rowType Type of row ("Close Combat", "Ranged", or "Siege")
     * @param isOpponentRow true if this is an opponent row, false if player row
     * @param spacing Spacing between cards in pixels
     * @param margins Left and right margins in pixels
     */
    public Row(String rowType, boolean isOpponentRow, double spacing, double margins) {
        super(spacing, margins);
        this.rowType = rowType;
        this.isOpponentRow = isOpponentRow;
        setupRowProperties();
    }
    
    /**
     * Setup row-specific properties
     */
    private void setupRowProperties() {
        // Set row-specific styling based on type and ownership
        String backgroundColor = isOpponentRow ? "#ffebee" : "#e8f5e8"; // Light red for opponent, light green for player
        setStyle("-fx-background-color: " + backgroundColor + "; -fx-border-color: #666; -fx-border-width: 1;");
    }
    
    /**
     * Add a card to the row (accepts both UnitCard and SpecialCard)
     * @param card The card to add (UnitCard or SpecialCard)
     */
    @Override
    public void addCard(CardComponent card) {
        super.addCard(card);
    }
    
    /**
     * Add multiple cards to the row (accepts both UnitCard and SpecialCard)
     *
     * @param cardsToAdd Cards to add (UnitCard or SpecialCard)
     */
    public void addUnitCardsSafely(CardComponent... cardsToAdd) {
        for (CardComponent card : cardsToAdd) {
            super.addCard(card);
        }
    }
    
    /**
     * Check if the row is empty
     * @return true if row has no cards
     */
    public boolean isEmpty() {
        return getCardCount() == 0;
    }
    
    /**
     * Calculate the total power of all unit cards in this row
     * @return Total power of the row (only counts UnitCard points)
     */
    public int getTotalPower() {
        int totalPower = 0;
        for (int i = 0; i < getCardCount(); i++) {
            CardComponent card = getCard(i);
            if (card instanceof UnitCard) {
                UnitCard unitCard = (UnitCard) card;
                totalPower += unitCard.getPoints(); // Use getPoints() instead of getAttackPower()
            }
        }
        return totalPower;
    }
}
