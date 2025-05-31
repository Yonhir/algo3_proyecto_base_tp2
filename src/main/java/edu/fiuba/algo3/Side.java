package edu.fiuba.algo3;

public class Side {
    private Player player;
    private Row closeCombatRow;
    private Row rangedRow;
    private Row siegeRow;

    public Side(Player player) {
        this.player = player;
        this.closeCombatRow = new Row(new CloseCombatRowType());
        this.rangedRow = new Row(new RangedRowType());
        this.siegeRow = new Row(new SiegeRowType());
    }

    public void placeCard(Card card, RowType rowType) {
        rowType.placeCardInRow(getRow(rowType), card);
    }

    public int calculateTotalPointsForRow(RowType rowType) {
        return getRow(rowType).calculateTotalPoints();
    }

    private Row getRow(RowType rowType) {
        if (rowType instanceof CloseCombatRowType) return closeCombatRow;
        if (rowType instanceof RangedRowType) return rangedRow;
        if (rowType instanceof SiegeRowType) return siegeRow;
        throw new IllegalArgumentException("RowType desconocido");
    }
}