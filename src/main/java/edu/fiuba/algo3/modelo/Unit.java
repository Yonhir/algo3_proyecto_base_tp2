package edu.fiuba.algo3.modelo;

import java.util.List;

public class Unit extends Card {
    private final int basePoints;
    private int currentPoints;
    private List<Modifier> modifiers;

    public Unit(String name, String description, int points, List<SectionType> sectionTypes, List<Modifier> modifiers) {
        super(name, description, sectionTypes);
        this.basePoints = points;
        this.currentPoints = points;
        this.modifiers = modifiers;
    }

    public Unit(String name, String description, int points, SectionType sectionType, List<Modifier> modifiers) {
        super(name, description, List.of(sectionType));
        this.basePoints = points;
        this.currentPoints = points;
        this.modifiers = modifiers;
    }

    public void play(Section section) {
        if (section instanceof Row) {
            Row row = (Row) section;
            row.addCard(this);
            for (Modifier modifier : modifiers) {
                modifier.apply(row);
            }
        }
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
