package edu.fiuba.algo3.modelo;

import java.util.List;

public class ImpenetrableFog extends Weather {
    // Sets the strength of all Ranged cards to 1 for both players.
    
    public ImpenetrableFog(String name, String description) {
        super(name, description, List.of(new SpecialType()));
    }

    @Override
    public void play(Section section) {
        SpecialZone specialZone = (SpecialZone) section;
        specialZone.applyRangedWeather(this);
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit && row instanceof Ranged) {
            ((Unit) card).setPoints(1);
        }
    }
}
