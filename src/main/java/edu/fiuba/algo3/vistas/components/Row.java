package edu.fiuba.algo3.vistas.components;

public class Row extends CardList {
    private final boolean isOpponentRow; // true for opponent rows, false for player rows

    public Row(boolean isOpponentRow) {
        super();
        this.isOpponentRow = isOpponentRow;
        setupRowProperties();
    }

    private void setupRowProperties() {
        // Set row-specific styling based on type and ownership
        String backgroundColor = isOpponentRow ? "#ffebee" : "#e8f5e8"; // Light red for opponent, light green for player
        setStyle("-fx-background-color: " + backgroundColor + "; -fx-border-color: #666; -fx-border-width: 1;");
    }
}
