package edu.fiuba.algo3.modelo.cards.specials.weathers;

import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.*;

import java.util.List;

public class SkelligeStorm extends Weather {
    // Sets the strength of all Close Combat and Ranged cards to 1 for both players.

    public SkelligeStorm(String name, String description) {
        super(name, description, List.of(new SpecialType()));
    }

    @Override
    public void play(Section section) {
        SpecialZone specialZone = (SpecialZone) section;
        specialZone.applyCloseCombatWeather(this);
        specialZone.applyRangedWeather(this);
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit && (row instanceof CloseCombat || row instanceof Ranged)) {
            ((Unit) card).setPoints(1);
        }
    }
} 