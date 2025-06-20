package edu.fiuba.algo3.modelo;

import java.util.List;

public class SkelligeStorm extends Weather {

    public SkelligeStorm(String name, String description) {
        super(name, description, List.of(new SpecialType()));
    }

    @Override
    public void play(Section section) {
        SpecialZone specialZone = (SpecialZone) section;
        specialZone.applyRangedWeather(this);
        specialZone.applySiegeWeather(this);
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit && (row instanceof Ranged || row instanceof Siege)) {
            ((Unit) card).setPoints(1);
        }
    }
}
