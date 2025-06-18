package edu.fiuba.algo3.modelo;

import java.util.List;

public class ClearWeather extends Weather {
    public ClearWeather(String name, String description) {
        super(name, description, List.of(new SpecialType()));
    }

    @Override
    public void play(Section section) {
        SpecialZone specialZone = (SpecialZone) section;
        specialZone.applyCloseCombatWeather(this);
        specialZone.applyRangedWeather(this);
        specialZone.applySiegeWeather(this);
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit) {
            ((Unit) card).resetPoints();
        }
    }
}
