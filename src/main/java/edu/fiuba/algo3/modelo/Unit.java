package edu.fiuba.algo3.modelo;

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
        for (Modifier modifier : modifiers) {
            modifier.apply(row);
        }
    }

    public int calculatePoints() { return currentPoints; }

    @Override
    public boolean canBePlaced(Row row) {
        return row.canBePlacedIn(this);
    }

    public int calculatePoints() {
        return currentPoints;
    }

    public void resetPoints() {
        currentPoints = basePoints;
    }

    public void setPoints(int points) {
        currentPoints = points;
    }

    public boolean haveModifier(Modifier modifierInstance) {
        for (Modifier modifier : modifiers) {
            if (modifier.getClass().isInstance(modifierInstance)) {
                return true;
            }
        }
        return false;
    }
}
