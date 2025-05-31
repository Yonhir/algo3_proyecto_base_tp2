package edu.fiuba.algo3;


public class Unit extends Card {
    private int basePoint;
    private RowType rowType;

    public Unit(int basePoint, RowType rowType) {
        super("Unit", "Basic unit");
        this.basePoint = basePoint;
        this.rowType = rowType;
    }

    public int getPoints() {
        return basePoint;
    }

    @Override
    public void play(Board board, Player owner, Player opponent) {

    }
}

