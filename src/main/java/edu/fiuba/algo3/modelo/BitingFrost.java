package edu.fiuba.algo3.modelo;

import java.util.List;

public class BitingFrost extends Weather {
    // Sets the strength of all Close Combat cards to 1 for both players.

    public BitingFrost(String name, String description) {
        super(name, description, List.of(new SpecialType()));
    }

    @Override
    public void play(Section section) {
        SpecialZone specialZone = (SpecialZone) section;
        specialZone.addCloseCombatWeather(this);
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit && row instanceof CloseCombat) {
            ((Unit) card).setPoints(1);
        }
    }
}
