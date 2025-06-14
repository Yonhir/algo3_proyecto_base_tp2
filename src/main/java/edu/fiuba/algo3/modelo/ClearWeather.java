package edu.fiuba.algo3.modelo;

import java.util.List;

public class ClearWeather extends Weather {
    public ClearWeather(String name, String description) {
        super(name, description, List.of(new SpecialType()));
    }

    @Override
    public void play(Section section) {
        if (section instanceof SpecialZone) {
            SpecialZone specialZone = (SpecialZone) section;
            specialZone.addCloseCombatWeather(this);
            specialZone.addRangedWeather(this);
            specialZone.addSiegeWeather(this);
        } else {
            throw new SectionTypeMismatchError("ClearWeather can only be placed in a SpecialZone");
        }
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit) {
            ((Unit) card).resetPoints();
        }
    }
}
