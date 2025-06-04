package edu.fiuba.algo3;

public class Unit extends Card {
    private int points;
    private RowType type;

    public Unit(String name, String description, int points, RowType row) {
        super(name, description);
        this.points = points;
        type = row;
    }

    public boolean sameType(RowType type) {
        return (this.type.getClass() == type.getClass());
    }

    public int getPoints() {
        return points;
    }
}
