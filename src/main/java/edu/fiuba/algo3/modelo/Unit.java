package edu.fiuba.algo3.modelo;

import java.util.List;

public class Unit extends Card {
    private int points;
    private boolean closeCombat;
    private boolean ranged;
    private boolean siege;
    private List<Modifier> modifiers;

    public Unit(String name, String description, int points, boolean closeCombat, boolean ranged, boolean siege, List<Modifier> modifiers) {
        super(name, description);
        this.points = points;
        this.closeCombat = closeCombat;
        this.ranged = ranged;
        this.siege = siege;
        this.modifiers = modifiers;
    }
}