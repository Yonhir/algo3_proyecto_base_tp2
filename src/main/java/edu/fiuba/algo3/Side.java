package edu.fiuba.algo3;

public class Side {

    private final Row closeCombatRow;
    private final Row rangedRow;
    private final Row siegeRow;

    public Side() {
        this.closeCombatRow = new Row(new CloseCombat());
        this.rangedRow = new Row(new Ranged());
        this.siegeRow = new Row(new Siege());
    }

    public void placeCard(Card card, RowType rowType) {
        rowType.placeCardInRow(getRow(rowType), card);
    }

    public int calculateTotalPointsForRow(RowType rowType) {
        return getRow(rowType).calculateTotalPoints();
    }

    private Row getRow(RowType rowType) {
        if (rowType instanceof CloseCombat) return closeCombatRow;
        if (rowType instanceof Ranged) return rangedRow;
        if (rowType instanceof Siege) return siegeRow;
        throw new IllegalArgumentException("RowType desconocido");
    }
}