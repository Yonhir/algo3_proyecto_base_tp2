package edu.fiuba.algo3.models.cards.units;

import edu.fiuba.algo3.models.cards.units.modifiers.Spy;
import edu.fiuba.algo3.models.colors.PlayerColor;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.units.modifiers.Hero;
import edu.fiuba.algo3.models.cards.units.modifiers.Modifier;
import edu.fiuba.algo3.models.errors.SectionPlayerMismatchError;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.models.sections.Section;
import edu.fiuba.algo3.models.sections.types.SectionType;

import java.util.List;

public class Unit extends Card {
    private final int basePoints;
    private int currentPoints;
    private List<Modifier> modifiers;
    private boolean hasHeroModifier;

    public Unit(String name, String description, int points, List<SectionType> sectionTypes, List<Modifier> modifiers) {
        super(name, description, sectionTypes);
        this.basePoints = points;
        this.currentPoints = points;
        this.modifiers = modifiers;
        checkHeroModifier();
    }

    public Unit(String name, String description, int points, SectionType sectionType, List<Modifier> modifiers) {
        super(name, description, List.of(sectionType));
        this.basePoints = points;
        this.currentPoints = points;
        this.modifiers = modifiers;
        checkHeroModifier();
    }

    @Override
    public void verifyColor(PlayerColor playerColor) {
        boolean sameColor = playerColor.equals(this.playerColor);
        if (!sameColor) throw new SectionPlayerMismatchError("Side does not match for this card.");
    }

    @Override
    public void setColor(PlayerColor playerColor){
        if (modifiers.stream().anyMatch(modifier -> modifier.getClass() == Spy.class)) this.playerColor = playerColor.swapColor();
        else this.playerColor = playerColor;
    }

    public void play(Section section) {
        Row row = (Row) section;
        row.addCard(this);
        for (Modifier modifier : modifiers) {
            modifier.apply(row);
        }
    }

    private void checkHeroModifier() {
        this.hasHeroModifier = false;

        for (Modifier modifier : modifiers) {
            if (modifier instanceof Hero) {
                this.hasHeroModifier = true;
                break;
            }
        }
    }

    public boolean hasHeroAsModifier() {
        return hasHeroModifier;
    }

    public int calculatePoints() {
        return currentPoints;
    }

    public void resetPoints() {
        currentPoints = basePoints;
    }

    public void setPoints(int points) {
        if (!hasHeroModifier) {
            currentPoints = points;
        }
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
        if (this.currentPoints > unit.calculatePoints()) {
            return this;
        }
        return unit;
    }

    public boolean samePointsAs(Unit card) {
        return this.currentPoints == card.calculatePoints();
    }
}
