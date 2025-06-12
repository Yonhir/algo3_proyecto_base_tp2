package edu.fiuba.algo3.modelo;


import java.util.ArrayList;
import java.util.List;

public class Unit extends Card {
    private final int basePoints;
    private int currentPoints;
    private List<Modifier> modifiers;
    private boolean canBeInCloseCombat;
    private boolean canBeInRanged;
    private boolean canBeInSiege;

    public Unit(String name, String description, int points, boolean closeCombat, boolean ranged, boolean siege, List<Modifier> modifiers) {
        super(name, description);
        this.basePoints = points;
        this.currentPoints = points;
        this.canBeInCloseCombat = closeCombat;
        this.canBeInRanged = ranged;
        this.canBeInSiege = siege;
        this.modifiers = modifiers;
    }

    public boolean canBeInCloseCombat() {
        return canBeInCloseCombat;
    }

    public boolean canBeInRanged() {
        return canBeInRanged;
    }

    public boolean canBeInSiege() {
        return canBeInSiege;
    }

    @Override
    public void play(Row row) {
        row.addCard(this);
    }

    public int calculatePoints() { return currentPoints; }

    @Override
    public boolean canBePlaced(Row row) {
        return row.canBePlacedIn(this);
    }
}
