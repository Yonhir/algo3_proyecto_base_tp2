package edu.fiuba.algo3;

public class Unit extends Card{
    private int basePoint;
    private RowType rowType;

    public Unit(String name, String description, int basePoint, RowType rowType) {
        super(name, description);
        this.basePoint = basePoint;
        this.rowType = rowType;
    }

    @Override
    public void selectInHand(Hand hand) {
        hand.selectUnitCard(this);
    }
}
