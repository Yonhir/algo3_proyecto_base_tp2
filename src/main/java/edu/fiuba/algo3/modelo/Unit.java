package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Unit extends Card {
    private final int basePoints;
    private final List<Modifier> modifiers;

    public Unit(String name, String description, int points, ArrayList<Modifier> modifiers) {
        super(name, description);
        this.basePoints = points;
        this.modifiers = modifiers;
    }

}