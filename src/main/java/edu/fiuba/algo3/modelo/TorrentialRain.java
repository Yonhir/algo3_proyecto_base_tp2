package edu.fiuba.algo3.modelo;

import java.util.List;

public class TorrentialRain extends Weather {
    // Sets the strength of all Siege Combat cards to 1 for both players.

    public TorrentialRain(String name, String description) {
        super(name, description, List.of(new SpecialType()));
    }

    @Override
    public void play(Section target) {
        if (target instanceof SpecialZone) {
            ((SpecialZone) target).addSiegeWeather(this);
        } else {
            throw new IllegalArgumentException("TorrentialRain can only be placed in a SpecialZone");
        }
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit && row instanceof Siege) {
            ((Unit) card).setPoints(1);
        }
    }
}
