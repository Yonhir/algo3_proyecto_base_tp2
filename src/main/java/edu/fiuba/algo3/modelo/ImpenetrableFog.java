package edu.fiuba.algo3.modelo;

import java.util.List;

public class ImpenetrableFog extends Weather {
    // Sets the strength of all Ranged cards to 1 for both players.
    
    public ImpenetrableFog(String name, String description) {
        super(name, description, List.of(new SpecialType()));
    }

    @Override
    public void play(Section target) {
        if (target instanceof SpecialZone) {
            ((SpecialZone) target).addRangedWeather(this);
        } else {
            throw new IllegalArgumentException("ImpenetrableFog can only be placed in a SpecialZone");
        }
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit && row instanceof Ranged) {
            ((Unit) card).setPoints(1);
        }
    }
}
