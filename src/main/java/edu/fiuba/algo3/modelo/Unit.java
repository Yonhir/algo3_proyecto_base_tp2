package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Unit extends Card {
    private final int basePoints;
    private int currentPoints;
    private List<Modifier> modifiers;
    private CalculatePointsStrategy pointsStrategy;

    public Unit(String name, String description, int points, List<SectionType> sectionTypes, List<Modifier> modifiers) {
        super(name, description, sectionTypes);
        this.basePoints = points;
        this.currentPoints = points;
        this.modifiers = modifiers;
    }

    public Unit(String name, String description, int points, SectionType sectionTypes, List<Modifier> modifiers) {
        super(name, description, List.of(sectionTypes));
        this.basePoints = points;
        this.currentPoints = points;
        this.modifiers = modifiers;
    }

    public void setStrategy(CalculatePointsStrategy strategy) {
        this.pointsStrategy = strategy;
    }

    public void play(Section section) {
        Row row = (Row) section;
        for (Modifier modifier : modifiers) {
            modifier.apply(row);
        }
        pointsStrategy.playIn(section, this);
    }

    @Override
    public void verifySectionType(SectionType sectionType) {
        super.verifySectionType(sectionType);
    }

    public int calculatePoints() {
        return currentPoints;
    }

    public void resetPoints() {
        currentPoints = basePoints;
    }

    public void setPoints(int points) {
        pointsStrategy.affectPointsFrom(this);
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
