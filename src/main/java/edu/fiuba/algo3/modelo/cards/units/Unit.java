package edu.fiuba.algo3.modelo.cards.units;

import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.units.modifiers.Modifier;
import edu.fiuba.algo3.modelo.sections.rows.Row;
import edu.fiuba.algo3.modelo.sections.Section;
import edu.fiuba.algo3.modelo.sections.types.SectionType;

import java.util.ArrayList;
import java.util.List;

public class Unit extends Card {
    private final int basePoints;
    private int currentPoints;
    private List<Modifier> modifiers;
    private CalculatePointsStrategy pointsStrategy;

    public Unit(String name, String description, int points, List<SectionType> sectionTypes, List<Modifier> modifiers, CalculatePointsStrategy strategy) {
        super(name, description, sectionTypes);
        this.basePoints = points;
        this.currentPoints = points;
        this.modifiers = modifiers;
        this.pointsStrategy = strategy;
    }

    public Unit(String name, String description, int points, SectionType sectionTypes, List<Modifier> modifiers, CalculatePointsStrategy strategy) {
        super(name, description, List.of(sectionTypes));
        this.basePoints = points;
        this.currentPoints = points;
        this.modifiers = modifiers;
        this.pointsStrategy = strategy;
    }

    public void play(Section section) {
        Row row = (Row) section;
        pointsStrategy.playIn(section, this);
        for (Modifier modifier : modifiers) {
            modifier.apply(row);
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
        pointsStrategy.affectPointsFromWith(this, points);
    }

    public void affectPointsWith(int points) {
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

    public Unit strongerThan(Unit unit) {
        return pointsStrategy.chooseStronger(this, unit);
    }

    public boolean samePointsAs(Unit card) {
        return this.currentPoints == card.calculatePoints();
    }
}
